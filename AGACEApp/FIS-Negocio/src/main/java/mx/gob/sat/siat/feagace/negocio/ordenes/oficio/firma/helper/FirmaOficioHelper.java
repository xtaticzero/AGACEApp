package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DatosNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoNotificable;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OficioPorFirmarVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CadenaFirmadoUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;

@Component
public class FirmaOficioHelper extends FirmaHelper {

    private static final long serialVersionUID = 5342238869291569108L;
    private static final int TAMANIO_CADENA_EMPLEADO = 11;
    private static final String CADENA_SUPLENTE = "Atiende por suplencia en ausencia de %s, %s, con fundamento en el art\u00edculo %s.";

    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        OficioPorFirmarVO oficioPorFirmarVO = (OficioPorFirmarVO)objDatos;        
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        
        firmas.add(creaCadena(oficioPorFirmarVO.getOficioSeleccionado(), oficioPorFirmarVO.getOrdenSeleccionado(), oficioPorFirmarVO.getRfcAuditado(), oficioPorFirmarVO.getClaveAdministracion(), oficioPorFirmarVO.getRfcFirmante()));
        
        for(FecetOficio oficioDependiente: oficioPorFirmarVO.getOficiosDependientes()){
            firmas.add(creaCadena(oficioDependiente, oficioPorFirmarVO.getOrdenSeleccionado(), oficioPorFirmarVO.getRfcAuditado(), oficioPorFirmarVO.getClaveAdministracion(), oficioPorFirmarVO.getRfcFirmante()));            
        }         
        return firmas;
    }
    
    private FirmaDTO creaCadena(FecetOficio oficio, AgaceOrden orden, String rfcAuditado, String claveAdministracion, String rfcFirmante){
        Date date = new Date();   
        final CadenaFirmadoUtil cadenasFirmado =
                new CadenaFirmadoUtil(oficio.getIdOficio().toString(),
                        orden.getNumeroOrden() + "|" +
                                oficio.getIdOficio() + "|" +
                                oficio.getFecetTipoOficio().getDescripcion() + "|" +                                  
                                      DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, date) + "|" + 
                                      DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_24H_HHMM, date) + "|" +
                                      rfcAuditado + "|" +
                                      claveAdministracion + "|" +
                                      rfcFirmante);

            FirmaDTO firmaDTO = new FirmaDTO();
            firmaDTO.setId(String.valueOf(oficio.getIdOficio()));
            firmaDTO.setCadena(cadenasFirmado.getCadena());
            
            return firmaDTO;
    }

    public DatosNotificable generarActoAdministrativo(FecetOficio oficio, EmpleadoDTO empleado, FececActosAdm actoBD,
            Date fechaVigenciaFiel, String rfcContribuyente, String referencia) {

        DatosNotificable notificable = new DatosNotificable();
        DetalleEmpleadoDTO detalleEmpleado = null;

        if (empleado.getDetalleEmpleado() != null) {
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            notificable.setIdActoAdmon(actoBD.getIdNyv());
            notificable.setDescActoAdmon(actoBD.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().toString(), TAMANIO_CADENA_EMPLEADO, "0"));
            
            if (oficio.getRfcCompulsado() != null) {
                notificable.setRfcContribuyente(oficio.getRfcCompulsado());
            } else {
                notificable.setRfcContribuyente(rfcContribuyente);
            }
            
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(referencia);
        }

        DocumentoNotificable doctoNotificable;
        for (DocumentoActoAdministrativo documentoActo : actoBD.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();

            doctoNotificable.setEstatusDocumento(actoBD.getEstatusDocumento());
            if (actoBD.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(oficio.getRutaArchivo());
            } else {
                String nombreArchivo = UtileriasMapperDao.getNameFileFromPath(oficio.getRutaArchivo());
                String rutaDestino = oficio.getRutaArchivo().replace(nombreArchivo, (documentoActo.getCveDocumentoNyv() + ".pdf"));
                doctoNotificable.setRutaDocumento(rutaDestino);
            }
            doctoNotificable.setFirma(oficio.getFirmaElectronica());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(oficio.getCadenaOriginal());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);

            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        oficio.setDatosNotificable(notificable);

        return notificable;
    }

    public DatosNotificable generarActoAdministrativo(FecetProrrogaOficio prorroga, EmpleadoDTO empleado,
            FececActosAdm actoBD, Date fechaVigenciaFiel, String rfcContribuyente, 
            String referencia) {
        DatosNotificable notificable = new DatosNotificable();
        DetalleEmpleadoDTO detalleEmpleado = null;
        if (empleado.getDetalleEmpleado() != null) {
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            notificable.setIdActoAdmon(actoBD.getIdNyv());
            notificable.setDescActoAdmon(actoBD.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().toString(), TAMANIO_CADENA_EMPLEADO, "0"));
            notificable.setRfcContribuyente(rfcContribuyente);
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(referencia);
        }

        DocumentoNotificable doctoNotificable;
        for (DocumentoActoAdministrativo documentoActo : actoBD.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();
            doctoNotificable.setEstatusDocumento(actoBD.getEstatusDocumento());
            if (actoBD.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(prorroga.getRutaResolucion());
            } else {
                String nombreArchivo = UtileriasMapperDao.getNameFileFromPath(prorroga.getRutaResolucion());
                String rutaDestino = prorroga.getRutaResolucion().replace(nombreArchivo, (documentoActo.getCveDocumentoNyv() + ".pdf"));
                doctoNotificable.setRutaDocumento(rutaDestino);
            }
            doctoNotificable.setFirma(prorroga.getFirmaFirmante());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(prorroga.getCadenaFirmante());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());            
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);
            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        prorroga.setDatosNotificable(notificable);

        return notificable;
    }

    public void checkArgument(boolean condicion) {
        if (condicion) {
            throw new IllegalArgumentException("Error al obtener los valores para realizar el registro en NYV");
        }
    }

    public void generarActoAdministrativo(AgaceOrden orden, FecetProrrogaOrden prorroga, EmpleadoDTO empleado, 
            FececActosAdm actoBD, Date fechaVigenciaFiel, String rutaResolucion) {
        DatosNotificable notificable = new DatosNotificable();
        DetalleEmpleadoDTO detalleEmpleado = null;
        if (empleado.getDetalleEmpleado() != null) {
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            notificable.setIdActoAdmon(actoBD.getIdNyv());
            notificable.setDescActoAdmon(actoBD.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().toString(), TAMANIO_CADENA_EMPLEADO, "0"));
            notificable.setRfcContribuyente(orden.getFecetContribuyente().getRfc());
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(String.format("%s-P-%s", orden.getNumeroOrden(), prorroga.getId()));
        }

        DocumentoNotificable doctoNotificable;
        for (DocumentoActoAdministrativo documentoActo : actoBD.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();
            doctoNotificable.setEstatusDocumento(actoBD.getEstatusDocumento());
            if (actoBD.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(rutaResolucion);
            } else {
                String nombreArchivo = UtileriasMapperDao.getNameFileFromPath(rutaResolucion);
                String rutaDestino = rutaResolucion.replace(nombreArchivo, (documentoActo.getTipoDocumento() + ".pdf"));
                doctoNotificable.setRutaDocumento(rutaDestino);       
            }
            doctoNotificable.setFirma(prorroga.getFirmaFirmante());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(prorroga.getCadenaFirmante());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);

            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        prorroga.setDatosNotificable(notificable);
    }

    public String generaNumeroOficion(ClaveFolioOficioDTO claveDTO, BigDecimal idOficio) {
        StringBuilder numeroOficio = new StringBuilder();
        numeroOficio.append("E");
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveAdministracionGeneral());
        //Falta validar la administracion central de la administracion general
        numeroOficio.append("-00-");
        numeroOficio.append(claveDTO.getClaveAdministracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveSubadmistracionArea());
        numeroOficio.append("-");
        numeroOficio.append(claveDTO.getClaveJefaturaDepto());
        numeroOficio.append("-");
        Calendar cal = Calendar.getInstance();
        numeroOficio.append(cal.get(Calendar.YEAR));
        numeroOficio.append("-");
        numeroOficio.append(idOficio);

        return numeroOficio.toString();
    }

    public void crearCadenaSuplente(final StringBuilder cadena, final EmpleadoSuplenteDTO suplente) {
        if (cadena != null && suplente != null) {
            cadena.append("/n/");
            cadena.append(String.format(CADENA_SUPLENTE, suplente.getNombreFirmante(), suplente.getNombreSuplente(), suplente.getMotivo()));
        }
    }

    private DetalleEmpleadoDTO getDetalleEmpleadoDtoByTipoEmpleado(EmpleadoDTO empleadoDto, TipoEmpleadoEnum tipoEmpleado) {
        if (empleadoDto != null) {
            if (empleadoDto.getDetalleEmpleado() != null && tipoEmpleado != null) {
                for (DetalleEmpleadoDTO det : empleadoDto.getDetalleEmpleado()) {
                    if (det.getTipoEmpleado().equals(tipoEmpleado)) {
                        return det;
                    }
                }
            }
            if (empleadoDto.getDetalleEmpleado() != null && tipoEmpleado == null) {
                for (DetalleEmpleadoDTO det : empleadoDto.getDetalleEmpleado()) {
                    return det;
                }
            }
        }
        return null;
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
