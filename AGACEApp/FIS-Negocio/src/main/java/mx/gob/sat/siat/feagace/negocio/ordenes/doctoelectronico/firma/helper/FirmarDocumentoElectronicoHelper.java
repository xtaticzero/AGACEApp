package mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.helper;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.constants.FirmadoOrdenesConstants;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtil;

@Component
public class FirmarDocumentoElectronicoHelper extends FirmaHelper {

    private static final long serialVersionUID = 3298434379756745671L;

    private static final int CUATRO = 4;
    private static final int DOS = 2;
    private static final int TAMANIO_CADENA_EMPLEADO = 11;

    private static final String CADENA_SUPLENTE = "Atiende por suplencia en ausencia de %s, %s, con fundamento en el art\u00edculo %s.";

    private static final String DOCUMENTO_ORDEN = "documentoOrden";
    private static final String DOCUMENTO_OFICIO = "documentosOficio";
    private static final String TIPO_OFICIO = "tipoOficio";

    public FecetPropuesta construirPropuesta(OrdenPorFirmar ordenPorFirmar) {
        FecetContribuyente fecetContribuyente = new FecetContribuyente();
        fecetContribuyente.setRfc(ordenPorFirmar.getRfcContribuyente());
        FecetPropuesta propuesta = new FecetPropuesta();
        propuesta.setIdRegistro(ordenPorFirmar.getIdRegistro());
        propuesta.setFecetContribuyente(fecetContribuyente);
        return propuesta;
    }

    public FecetDocOrden inicializarDocumentoOrden(OrdenPorFirmar ordenPorFirmar, String rutaArchivo) {
        FecetDocOrden fecetDocOrden = new FecetDocOrden();
        fecetDocOrden.setIdOrden(new BigDecimal(ordenPorFirmar.getIdOrden()));
        fecetDocOrden.setNombreArchivo(ordenPorFirmar.getNumeroOrden().concat(".pdf"));
        fecetDocOrden.setRutaArchivo(rutaArchivo);
        fecetDocOrden.setDocumentoPdf(FirmadoOrdenesConstants.DOCUMENTO_PFD);
        fecetDocOrden.setFechaCreacion(new Date());
        fecetDocOrden.setEstatus(FirmadoOrdenesConstants.ESTATUS_DOCUMENTO);
        fecetDocOrden.setBlnActivo(FirmadoOrdenesConstants.ACTIVO);
        fecetDocOrden.setFechaFin(null);
        return fecetDocOrden;
    }

    public String armarRutaDestinoPdfOficio(final FecetPropuesta propuesta, final FecetOficio oficio) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);
        rutaDestino.append(propuesta.getIdRegistro()).append("/").append(propuesta.getFecetContribuyente().getRfc()).append("/");
        rutaDestino.append(DOCUMENTO_OFICIO).append("/").append(TIPO_OFICIO + oficio.getFecetTipoOficio().getIdTipoOficio()).append("/");
        return rutaDestino.toString();
    }

    public String armarRutaDestinoPdfDoctoOrden(final FecetPropuesta propuesta) {
        StringBuilder rutaDestino = new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_PROPUESTAS_FEAGACE);
        rutaDestino.append(propuesta.getIdRegistro()).append("/").append(propuesta.getFecetContribuyente().getRfc()).append("/");
        rutaDestino.append(DOCUMENTO_ORDEN).append("/");
        return rutaDestino.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FirmaDTO> armarCadena(Object datos, String rfc) {
        List<OrdenPorFirmar> ordenes = (List<OrdenPorFirmar>) datos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        for (OrdenPorFirmar orden : ordenes) {
            FirmaDTO firmaOrdenDTO = new FirmaDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = new Date();
            firmaOrdenDTO.setId(orden.getIdOrden());
            StringBuilder cadenaOrden = new StringBuilder("");
            cadenaOrden.append("|").append(orden.getNumeroOrden());
            cadenaOrden.append("|").append(orden.getNumeroOficio());
            cadenaOrden.append("|").append(sdf.format(date));
            cadenaOrden.append("|").append(orden.getRfcContribuyente());
            cadenaOrden.append("|").append(orden.getIdRegistro().substring(DOS, CUATRO));
            cadenaOrden.append("|").append(rfc);
            logger.info("Cadena orden:" + cadenaOrden.toString());
            firmaOrdenDTO.setCadena(cadenaOrden.toString());
            firmas.add(firmaOrdenDTO);

            if (orden.getOficios() != null) {
                for (FecetOficio oficio : orden.getOficios()) {
                    FirmaDTO firmaOficioDTO = new FirmaDTO();
                    firmaOficioDTO.setId(orden.getIdOrden() + "|" + oficio.getIdOficio());
                    StringBuilder cadenaOficio = new StringBuilder("");
                    cadenaOficio.append("|").append(orden.getNumeroOrden());
                    cadenaOficio.append("|").append(oficio.getIdOficio());
                    cadenaOficio.append("|").append(oficio.getFecetTipoOficio().getNombre());
                    cadenaOficio.append("|").append(sdf.format(date));
                    cadenaOficio.append("|").append(orden.getRfcContribuyente());
                    cadenaOficio.append("|").append(orden.getIdRegistro().substring(DOS, CUATRO));
                    cadenaOficio.append("|").append(rfc);
                    logger.info("Cadena oficio:" + cadenaOficio.toString());
                    firmaOficioDTO.setCadena(cadenaOficio.toString());
                    firmas.add(firmaOficioDTO);
                }
            }
        }
        return firmas;
    }

    public void crearCadenaSuplente(final StringBuilder cadena, final EmpleadoSuplenteDTO suplente) {
        if (cadena != null && suplente != null) {
            cadena.append("/n/");
            cadena.append(String.format(CADENA_SUPLENTE, suplente.getNombreFirmante(), suplente.getNombreSuplente(), suplente.getMotivo()));
        }
    }

    public void generarActoAdministrativo(AgaceOrden orden, EmpleadoDTO empleado, Date fechaVigenciaFiel, FececActosAdm actoAdmin) {
        DatosNotificable notificable = new DatosNotificable();
        DetalleEmpleadoDTO detalleEmpleado = null;
        if (empleado.getDetalleEmpleado() != null) {
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            if (detalleEmpleado == null) {
                throw new IllegalArgumentException("Empleado incorrecto para firma.");
            }
            notificable.setClaveUnidadAdmin(String.valueOf(detalleEmpleado.getCentral().getIdArace()));
            notificable.setIdActoAdmon(actoAdmin.getIdNyv());
            notificable.setDescActoAdmon(actoAdmin.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().longValue() + "", TAMANIO_CADENA_EMPLEADO, "0"));
            notificable.setRfcContribuyente(orden.getFecetContribuyente().getRfc());
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(orden.getNumeroOrden());

        }

        DocumentoNotificable doctoNotificable = null;
        for (DocumentoActoAdministrativo documentoActo : actoAdmin.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();
            doctoNotificable.setEstatusDocumento(actoAdmin.getEstatusDocumento());
            if (actoAdmin.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(RutaArchivosUtil.armarRutaDocOrden(orden).concat(CargaArchivoUtil.getNombreArchivoOrdenPdf(orden)));
            } else {
                String rutaDestino = RutaArchivosUtil.armarRutaDocOrden(orden) + documentoActo.getTipoDocumento() + ".pdf";
                doctoNotificable.setRutaDocumento(rutaDestino);
            }
            doctoNotificable.setFirma(orden.getFirmaElectronica());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(orden.getCadenaOriginal());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);
            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        orden.setDatosNotificable(notificable);
    }

    public void checkArgument(boolean condicion) throws NegocioException {
        if (condicion) {
            throw new IllegalArgumentException("Error al obtener los valores para realizar el registro en NYV");
        }
    }

    public DatosNotificable generarActoAdministrativo(FecetOficio oficio, EmpleadoDTO empleado,
            Date fechaVigenciaFiel, String rfcContribuyente, String referencia, FececActosAdm actoAdmin) {
        DatosNotificable notificable = new DatosNotificable();

        DetalleEmpleadoDTO detalleEmpleado = null;

        if (empleado.getDetalleEmpleado() != null) {
            
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            if (detalleEmpleado == null) {
                throw new IllegalArgumentException("Empleado incorrecto para firma.");
            }
            notificable.setClaveUnidadAdmin(String.valueOf(detalleEmpleado.getCentral().getIdArace()));
            notificable.setIdActoAdmon(actoAdmin.getIdNyv());
            notificable.setDescActoAdmon(actoAdmin.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().longValue() + "", TAMANIO_CADENA_EMPLEADO, "0"));
            notificable.setRfcContribuyente(rfcContribuyente);
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(referencia);
        }

        DocumentoNotificable doctoNotificable = null;
        for (DocumentoActoAdministrativo documentoActo : actoAdmin.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();

            doctoNotificable.setEstatusDocumento(actoAdmin.getEstatusDocumento());
            if (actoAdmin.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(oficio.getRutaArchivo());
            } else {
                String nombreArchivo = UtileriasMapperDao.getNameFileFromPath(oficio.getRutaArchivo());
                String rutaDestino = oficio.getRutaArchivo().replace(nombreArchivo, (documentoActo.getTipoDocumento() + ".pdf"));
                doctoNotificable.setRutaDocumento(rutaDestino);
            }
            doctoNotificable.setFirma(oficio.getFirmaElectronica());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(oficio.getCadenaOriginal());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());
            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        oficio.setDatosNotificable(notificable);

        return notificable;
    }

    private DetalleEmpleadoDTO getDetalleEmpleadoDtoByTipoEmpleado(EmpleadoDTO empleadoDto, TipoEmpleadoEnum tipoEmpleado) {
        if (empleadoDto != null && tipoEmpleado != null && empleadoDto.getDetalleEmpleado() != null) {
            for (DetalleEmpleadoDTO det : empleadoDto.getDetalleEmpleado()) {
                if (det.getTipoEmpleado().equals(tipoEmpleado)) {
                    return det;
                }
            }
        }
        return null;
    }

    public FecebAccionPropuesta creaAccionPropuestaFirmar(OrdenPorFirmar ordenPorFirmar, BigDecimal idFirmante) {
        FecebAccionPropuesta accionPropuesta = new FecebAccionPropuesta();
        accionPropuesta.setIdAccion(AccionesFuncionarioEnum.FIRMA_ORDEN.getIdAccion());
        accionPropuesta.setIdAccionOrigen(null);
        accionPropuesta.setIdPropuesta(new BigDecimal(ordenPorFirmar.getIdPropuesta()));
        accionPropuesta.setIdDetalleAccion(null);
        accionPropuesta.setObservaciones(null);
        accionPropuesta.setFechaHora(new Date());
        accionPropuesta.setIdEmpleado(idFirmante);
        return accionPropuesta;
    }
    
    public Long obtenerCentralEmpleado(EmpleadoDTO empleadoRespuesta) {
        Long resultado = null;
        if (empleadoRespuesta.getDetalleEmpleado() != null && !empleadoRespuesta.getDetalleEmpleado().isEmpty()) {
            for (DetalleEmpleadoDTO detalle : empleadoRespuesta.getDetalleEmpleado()) {
                resultado = Long.valueOf(detalle.getCentral().getIdArace());
                break;
            }
        }
        return resultado;
    }
}
