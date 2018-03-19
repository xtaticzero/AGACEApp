package mx.gob.sat.siat.feagace.negocio.helper;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.NombreMes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

public class CargaDocumentosHelper {

    private static final String REPLACE = "||||";


    public String armaRutaArchivoPromocionOficio(FecetOficio oficio, BigDecimal idPromocionOficio) {
        String rutaArchivo;
        rutaArchivo = RutaArchivosUtil.armarRutaDestinoPromocionOficio(oficio, idPromocionOficio);
        return rutaArchivo;
    }

    public FecetPromocionOficio llenaPromocionOficio(FirmaDTO firmaDTO, FecetPromocionOficio promocionOficioGuardar,
            PromocionDocsVO docs, BigDecimal idPromocionOficio) {
        promocionOficioGuardar.setIdPromocionOficio(idPromocionOficio);
        String rutaAcuse
                = armaRutaArchivoPromocionOficio(docs.getOficioSeleccionado(), promocionOficioGuardar.getIdPromocionOficio());
        promocionOficioGuardar.setNombreAcuse(Constantes.NOMBRE_ACUSE_RECIBO);
        promocionOficioGuardar.setRutaAcuse(rutaAcuse + Constantes.NOMBRE_ACUSE_RECIBO);
        promocionOficioGuardar.setCadenaOriginal(firmaDTO.getCadena());
        promocionOficioGuardar.setFirmaElectronica(firmaDTO.getFirma());
        promocionOficioGuardar.setRutaArchivo(armaRutaArchivoPromocionOficio(docs.getOficioSeleccionado(),
                promocionOficioGuardar.getIdPromocionOficio()));
        promocionOficioGuardar.setRutaArchivo(promocionOficioGuardar.getRutaArchivo() + promocionOficioGuardar.getNombreArchivo());
        //FALTA INSERTAR EXTEMPORANEA
        promocionOficioGuardar.setIdOficio(docs.getOficioSeleccionado().getIdOficio());
        promocionOficioGuardar.setIdAsociadoCarga(docs.getPerfil().getIdAsociado());
        return promocionOficioGuardar;
    }

    public AcuseRevisionElectronica llenaAcuseOficio(AcuseRevisionElectronica acuse,
            FecetPromocionOficio promocionOficioGuardar, long size,
            PromocionDocsVO docs, String respuestaSelladora) {
        final Long tamanioMB = 1024L;
        acuse.setRfc(docs.getOrdenSeleccionado().getFecetContribuyente().getRfc());
        acuse.setNombre(docs.getOrdenSeleccionado().getFecetContribuyente().getNombre());
        acuse.setRevision(docs.getOficioSeleccionado().getIdOficio().toString());
        acuse.setFiel(respuestaSelladora);
        acuse.setCadenaOriginal(promocionOficioGuardar.getCadenaOriginal().replace(REPLACE, "|"));
        acuse.setRutaAcuse(promocionOficioGuardar.getRutaAcuse());
        acuse.setFolioRecepcionPromocion(promocionOficioGuardar.getIdPromocionOficio().toString());
        acuse.setNombreArchivoPromocion(CargaArchivoUtil.limpiarPathArchivo(CargaArchivoUtil.aplicarCodificacionTexto((docs.getNombreArchivo()))));
        acuse.setTamanioArchivoPromocion(Long.toString(size / tamanioMB));
        acuse.setListaEnviarPruebasAlegatosOficio(docs.getListaPruebasAlegatosOficioCargadas());
        acuse.setFecheEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, promocionOficioGuardar.getFechaCarga()));
        acuse.setHoraEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, promocionOficioGuardar.getFechaCarga()));

        acuse.setFechaReparacion(crearFechaString(promocionOficioGuardar.getFechaCarga()));
        acuse.setHoraReparacion(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, promocionOficioGuardar.getFechaCarga()));
        return acuse;
    }

    public FecetProrrogaOrden llenaProrroga(FirmaDTO dto, FecetProrrogaOrden prorrogaGuardar, BigDecimal idProrroga,
            ProrrogaDocsVO docs) {
        prorrogaGuardar.setIdProrrogaOrden(idProrroga);
        prorrogaGuardar.setIdOrden(docs.getOrdenSeleccionado().getIdOrden());
        final Date fecha = new Date();
        prorrogaGuardar.setFechaCarga(fecha);
        prorrogaGuardar.setNombreAcuse(Constantes.NOMBRE_ACUSE_RECIBO);
        String rutaAcuse = armaRutaArchivoProrroga(docs.getOrdenSeleccionado(), idProrroga).concat(Constantes.NOMBRE_ACUSE_RECIBO);
        prorrogaGuardar.setRutaAcuse(rutaAcuse);
        prorrogaGuardar.setCadenaContribuyente(dto.getCadena());
        prorrogaGuardar.setFirmaContribuyente(dto.getFirma());
        prorrogaGuardar.setIdEstatus(Constantes.ESTATUS_PRORROGA_PENDIENTE_APROBACION);
        return prorrogaGuardar;
    }

    public String armaRutaArchivoProrroga(AgaceOrden orden, BigDecimal idProrroga) {
        return RutaArchivosUtil.armarRutaDestinoProrroga(idProrroga, orden);
    }

    public AcuseRevisionElectronica llenaAcuseProrroga(AcuseRevisionElectronica acuse, FecetProrrogaOrden prorroga,
            ProrrogaDocsVO docs, String respuestaSelladora) {
        acuse.setRfc(docs.getOrdenSeleccionado().getFecetContribuyente().getRfc());
        acuse.setNombre(docs.getOrdenSeleccionado().getFecetContribuyente().getNombre());
        acuse.setRevision(docs.getOrdenSeleccionado().getNumeroOrden());
        acuse.setFiel(respuestaSelladora);
        acuse.setCadenaOriginal(prorroga.getCadenaContribuyente().replace(REPLACE, "|"));
        acuse.setRutaAcuse(prorroga.getRutaAcuse());
        acuse.setFolioRecepcionProrroga(prorroga.getIdProrrogaOrden().toString());
        acuse.setListaDocProrrogaOrden(docs.getListaDocsProrrogasOrden());
        acuse.setFecheEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, prorroga.getFechaCarga()));
        acuse.setHoraEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, prorroga.getFechaCarga()));

        acuse.setFechaReparacion(crearFechaString(prorroga.getFechaCarga()));
        acuse.setHoraReparacion(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, prorroga.getFechaCarga()));
        return acuse;
    }

    public FecetProrrogaOficio llenaProrrogaOficio(FirmaDTO dto, FecetProrrogaOficio prorrogaGuardar, BigDecimal idProrroga,
            ProrrogaDocsVO docs) {
        prorrogaGuardar.setIdProrrogaOficio(idProrroga);
        prorrogaGuardar.setIdOficio(docs.getOficioSeleccionado().getIdOficio());
        final Date fecha = new Date();
        prorrogaGuardar.setFechaCarga(fecha);
        prorrogaGuardar.setNombreAcuse(Constantes.NOMBRE_ACUSE_RECIBO);
        String rutaAcuse = armaRutaArchivoProrrogaOficio(docs.getOficioSeleccionado(), idProrroga).concat(Constantes.NOMBRE_ACUSE_RECIBO);
        prorrogaGuardar.setRutaAcuse(rutaAcuse);
        prorrogaGuardar.setCadenaContribuyente(dto.getCadena());
        prorrogaGuardar.setFirmaContribuyente(dto.getFirma());
        prorrogaGuardar.setIdEstatus(Constantes.ESTATUS_PRORROGA_PENDIENTE_APROBACION);
        return prorrogaGuardar;
    }

    public String armaRutaArchivoProrrogaOficio(FecetOficio oficio, BigDecimal idProrrogaOficio) {
        return RutaArchivosUtil.armarRutaDestinoProrrogaOficio(oficio, idProrrogaOficio);
    }

    public AcuseRevisionElectronica llenaAcuseProrrogaOficio(AcuseRevisionElectronica acuse, FecetProrrogaOficio prorroga,
            ProrrogaDocsVO docs, String respuestaSelladora) {
        acuse.setRfc(docs.getOrdenSeleccionado().getFecetContribuyente().getRfc());
        acuse.setNombre(docs.getOrdenSeleccionado().getFecetContribuyente().getNombre());
        acuse.setRevision(docs.getOficioSeleccionado().getIdOficio().toString());
        acuse.setFiel(respuestaSelladora);
        acuse.setCadenaOriginal(prorroga.getCadenaContribuyente().replace(REPLACE, "|"));
        acuse.setRutaAcuse(prorroga.getRutaAcuse());
        acuse.setFolioRecepcionProrroga(prorroga.getIdProrrogaOficio().toString());
        acuse.setListaDocProrrogaOficio(docs.getListaDocsProrrogasOficio());
        acuse.setFecheEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, prorroga.getFechaCarga()));
        acuse.setHoraEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, prorroga.getFechaCarga()));

        acuse.setFechaReparacion(crearFechaString(prorroga.getFechaCarga()));
        acuse.setHoraReparacion(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, prorroga.getFechaCarga()));
        return acuse;
    }
    
    public String crearFechaString(Date fecha){
        StringBuilder fechaString  = new StringBuilder();
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
    
    public FecetPruebasPericiales llenaPruebaPericial(FirmaDTO dto, FecetPruebasPericiales pruebaPericialGuardar, BigDecimal idPruebaPericial,
            PruebasPericialesDocsVO docs) {
        pruebaPericialGuardar.setIdPruebasPericiales(idPruebaPericial);
        pruebaPericialGuardar.setIdOrden(docs.getOrdenSeleccionado().getIdOrden());
        final Date fecha = new Date();
        pruebaPericialGuardar.setFechaCarga(fecha);
        pruebaPericialGuardar.setNombreAcuse(Constantes.NOMBRE_ACUSE_RECIBO);
        String rutaAcuse = armaRutaArchivoPruebaPericial(docs.getOrdenSeleccionado(), idPruebaPericial).concat(Constantes.NOMBRE_ACUSE_RECIBO);
        pruebaPericialGuardar.setRutaAcuse(rutaAcuse);
        pruebaPericialGuardar.setCadenaContribuyente(dto.getCadena());
        pruebaPericialGuardar.setFirmaContribuyente(dto.getFirma());
        pruebaPericialGuardar.setIdEstatus(Constantes.ESTATUS_PRUEBAS_PERICIALES_PENDIENTE_APROBACION);
        return pruebaPericialGuardar;
    }
    
    public String armaRutaArchivoPruebaPericial(AgaceOrden orden, BigDecimal idPruebaPericial) {
        return RutaArchivosUtil.armarRutaDestinoPruebasPericiales(idPruebaPericial, orden);
    }
    
    public AcuseRevisionElectronica llenaAcusePruebaPericial(AcuseRevisionElectronica acuse, FecetPruebasPericiales pruebaPericial,
            PruebasPericialesDocsVO docs, String respuestaSelladora) {
        acuse.setRfc(docs.getOrdenSeleccionado().getFecetContribuyente().getRfc());
        acuse.setNombre(docs.getOrdenSeleccionado().getFecetContribuyente().getNombre());
        acuse.setRevision(docs.getOrdenSeleccionado().getNumeroOrden());
        acuse.setFiel(respuestaSelladora);
        acuse.setCadenaOriginal(pruebaPericial.getCadenaContribuyente().replace(REPLACE, "|"));
        acuse.setRutaAcuse(pruebaPericial.getRutaAcuse());
        acuse.setFolioRecepcionProrroga(pruebaPericial.getIdPruebasPericiales().toString());
        acuse.setListaDocPruebasPericiales(docs.getListaDocsPruebasPericiales());
        acuse.setFecheEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, pruebaPericial.getFechaCarga()));
        acuse.setHoraEmision(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, pruebaPericial.getFechaCarga()));

        acuse.setFechaReparacion(crearFechaString(pruebaPericial.getFechaCarga()));
        acuse.setHoraReparacion(DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, pruebaPericial.getFechaCarga()));
        return acuse;
    }

}
