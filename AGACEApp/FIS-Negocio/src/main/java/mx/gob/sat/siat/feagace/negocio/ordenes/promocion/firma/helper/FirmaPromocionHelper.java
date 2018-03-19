package mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.helper;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.BusinessUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CadenaFirmadoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Component("firmaPromocionHelper")
public class FirmaPromocionHelper extends FirmaHelper {

    private static final long serialVersionUID = 8344781244066931550L;

    private static final String REPLACE = "||||";

    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        logger.info(rfc);
        PromocionDocsVO docs = (PromocionDocsVO)objDatos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        Date date = new Date();
        StringBuilder nombreArchivos = new StringBuilder();
        nombreArchivos.append(docs.getNombreArchivo());
        nombreArchivos.append(" ");
        for (FecetAlegato alegato : docs.getListaPruebasAlegatosCargadas()) {
            nombreArchivos.append(alegato.getNombreArchivo());
            nombreArchivos.append(" ");
        }

        int totalDocs = docs.getListaPruebasAlegatosCargadas().size() + 1;
        String rfcTmp = docs.getPerfil().getRfcContribuyente() == null ? " " : docs.getPerfil().getRfcContribuyente();
        String nombreRol =
            docs.getPerfil().getNombreRol().equals(Constantes.CONTRIBUYENTE_COMBO) ? " " : docs.getPerfil().getNombreRol();

        final CadenaFirmadoUtil cadenasFirmado =
            new CadenaFirmadoUtil(docs.getOrdenSeleccionado().getIdOrden().toString(),
                                  docs.getOrdenSeleccionado().getNumeroOrden() + "| | |" +
                                  DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, date) +
                                  "|" + DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, date) + "|" +
                                  docs.getPerfil().getRfc() + "|" +
                                  BusinessUtil.getTipoPromocionOrdenPorMetodo(docs.getOrdenSeleccionado().getIdMetodo()) +
                                  "|" + nombreArchivos.toString() + "|" + nombreRol + "|" + rfcTmp + "|" + totalDocs);

        FirmaDTO firmaDTO = new FirmaDTO();
        firmaDTO.setId(String.valueOf(docs.getOrdenSeleccionado().getIdOrden()));
        firmaDTO.setCadena(cadenasFirmado.getCadena());
        firmas.add(firmaDTO);

        return firmas;
    }

    public String armaRutaArchivoPromocion(AgaceOrden orden, BigDecimal idPromocion) {
        String rutaArchivo;
        rutaArchivo = RutaArchivosUtil.armarRutaDestinoPromocion(orden, idPromocion);
        return rutaArchivo;
    }

    public FecetPromocion llenaPromocion(FirmaDTO firmaDTO, FecetPromocion promocionGuardar, PromocionDocsVO docs,
                                         BigDecimal idPromocion) {
        promocionGuardar.setIdPromocion(idPromocion);
        String rutaAcuse = armaRutaArchivoPromocion(docs.getOrdenSeleccionado(), promocionGuardar.getIdPromocion());
        promocionGuardar.setNombreAcuse(Constantes.NOMBRE_ACUSE_RECIBO);
        promocionGuardar.setRutaAcuse(rutaAcuse + Constantes.NOMBRE_ACUSE_RECIBO);
        promocionGuardar.setCadenaOriginal(firmaDTO.getCadena());
        promocionGuardar.setFirmaElectronica(firmaDTO.getFirma());
        promocionGuardar.setRutaArchivo(armaRutaArchivoPromocion(docs.getOrdenSeleccionado(),
                                                                 promocionGuardar.getIdPromocion()));
        promocionGuardar.setRutaArchivo(promocionGuardar.getRutaArchivo() + promocionGuardar.getNombreArchivo());
        promocionGuardar.setIdOrden(docs.getOrdenSeleccionado().getIdOrden());
        promocionGuardar.setIdAsociadoCarga(docs.getPerfil().getIdAsociado());
        return promocionGuardar;
    }

    public AcuseRevisionElectronica llenaAcuse(AcuseRevisionElectronica acuse, FecetPromocion promocionGuardar,
                                               long size, PromocionDocsVO docs, String respuestaSelladora) {
        logger.info(" :::::::::::::::::::::::::::::   llena ACUSE    ::::::::::::::::::");
        final Long tamanioMB = 1024L;
        acuse.setRfc(docs.getOrdenSeleccionado().getFecetContribuyente().getRfc());
        acuse.setNombre(docs.getOrdenSeleccionado().getFecetContribuyente().getNombre());
        acuse.setRevision(docs.getOrdenSeleccionado().getNumeroOrden());
        acuse.setFiel(respuestaSelladora);
        acuse.setCadenaOriginal(promocionGuardar.getCadenaOriginal().replace(REPLACE, "|"));
        acuse.setRutaAcuse(promocionGuardar.getRutaAcuse());
        acuse.setFolioRecepcionPromocion(promocionGuardar.getIdPromocion().toString());
        acuse.setNombreArchivoPromocion(CargaArchivoUtil.limpiarPathArchivo(CargaArchivoUtil.aplicarCodificacionTexto(docs.getNombreArchivo())));
        acuse.setTamanioArchivoPromocion(Long.toString(size / tamanioMB));
        acuse.setListaEnviarPruebasAlegatos(docs.getListaPruebasAlegatosCargadas());
        acuse.setFecheEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                                                        promocionGuardar.getFechaCarga()));
        acuse.setHoraEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM,
                                                       promocionGuardar.getFechaCarga()));
        acuse.setFechaReparacion(crearFechaString(promocionGuardar.getFechaCarga()));
        acuse.setHoraReparacion(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM,
                                                          promocionGuardar.getFechaCarga()));
        logger.info(" :::::::::::::::::::::::::::::   regresa ACUSE    ::::::::::::::::::");
        return acuse;
    }

    public String crearFechaString(Date fecha) {
        StringBuilder fechaString = new StringBuilder();
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        fechaString.append(" Ciudad de M\u00e9xico a ");
        fechaString.append(fechaCalendar.get(Calendar.DAY_OF_MONTH));
        fechaString.append(" de ");
        fechaString.append(NombreMes.obtenerNombre(fechaCalendar.get(Calendar.MONTH)));
        fechaString.append(" del ");
        fechaString.append(fechaCalendar.get(Calendar.YEAR));
        fechaString.append(".");
        return fechaString.toString();
    }


}
