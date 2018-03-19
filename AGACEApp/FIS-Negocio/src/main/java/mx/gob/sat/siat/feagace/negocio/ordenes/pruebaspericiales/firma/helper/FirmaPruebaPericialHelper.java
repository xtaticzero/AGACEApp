package mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.EmpleadoSuplenteDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class FirmaPruebaPericialHelper extends FirmaHelper {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ERROR = "Error: ";
    private static final String REPORTES = "REPORTES";
    private static final String ARCHIVO_REPORTES = Propiedades.get("jasper.ubicacion.reportes");
    private static final String ARCHIVO_ACUSE = "acuse.jasper";
    private static final String CADENA_SUPLENTE = "Atiende por suplencia en ausencia de %s, %s, con fundamento en el art\u00edculo %s.";
    private static final int TAMANIO_CADENA_EMPLEADO = 11;

    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        FecetPruebasPericiales pruebaPericial = (FecetPruebasPericiales) objDatos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (pruebaPericial != null) {
            FirmaDTO firmaDTO = new FirmaDTO();
            firmaDTO.setId(String.valueOf(pruebaPericial.getIdPruebasPericiales()));
            StringBuilder cadena = new StringBuilder("|");
            Date date = new Date();
            cadena.append(pruebaPericial.getOrden().getNumeroOrden());
            cadena.append("|").append(pruebaPericial.getIdPruebasPericiales());
            cadena.append("|").append(sdf.format(date));
            cadena.append("|").append(
                    pruebaPericial.getOrden().getFecetContribuyente().getRfc());
            cadena.append("|").append(pruebaPericial.getIdPruebasPericiales());
            cadena.append("|").append(pruebaPericial.getNombreAcuse());
            if (pruebaPericial.getAprobada()) {
                cadena.append("|").append("Aprobada");
            } else {
                cadena.append("|").append("Rechazada");
            }
            cadena.append("|").append(
                    pruebaPericial.getFirmante() == null ? "" : pruebaPericial
                            .getFirmante().getDetalleEmpleado().get(0).getCentral().getNombre());
            cadena.append("|").append(
                    pruebaPericial.getFirmante() == null ? "" : pruebaPericial
                            .getFirmante().getRfc());
            cadena.append(pruebaPericial.getIdOrden());
            cadena.append("|").append(
                    (new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(date));
            firmaDTO.setCadena(cadena.toString());
            firmas.add(firmaDTO);
        }
        return firmas;
    }

    public byte[] generarAcuseRevisionElectronica(FirmaDTO firma,
            FecetPruebasPericiales pruebaPericial) {
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        acuse.setTituloGeneral("Documento de Acuse.");
        acuse.setCadenaOriginal(firma.getCadena());
        acuse.setEmisionAcuse(new Date().toString());
        acuse.setNombreArchivoProrroga(pruebaPericial.getNombreAcuse());
        acuse.setRutaAcuse(pruebaPericial.getRutaAcuse());
        return getArregloReportePdf(acuse);
    }

    public byte[] getArregloReportePdf(AcuseRevisionElectronica acuse) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        List<AcuseRevisionElectronica> acuses = new ArrayList<AcuseRevisionElectronica>();
        acuses.add(acuse);
        try {
            parametros.put("RUTA_IMAGENES",
                    Propiedades.get("jasper.ubicacion.acuse.imagenes"));
            parametros.put(REPORTES, ARCHIVO_REPORTES);
            File archivoReporte = new File(ARCHIVO_REPORTES + ARCHIVO_ACUSE);
            return JasperRunManager.runReportToPdf(archivoReporte.getPath(),
                    parametros, new JRBeanCollectionDataSource(acuses));
        } catch (NoClassDefFoundError e) {
            logger.error(ERROR + e);
        } catch (JRException e) {
            logger.error(ERROR + e);
        } catch (Exception e) {
            logger.error(ERROR + e);
        }
        return null;
    }

    public String armarRutaAnexosPruebasPericialesAprobadaFirmante(final BigDecimal idFlujoPruebaPericial) {
        return (Constantes.DIRECTORIO_ARCHIVOS_ORDENES.concat("resolucion_pruebapericial/").concat(idFlujoPruebaPericial.toString()).concat("/"));        
    }

    public void crearCadenaSuplente(final StringBuilder cadena, final EmpleadoSuplenteDTO suplente) {
        if (cadena != null && suplente != null) {
            cadena.append("/n/");
            cadena.append(String.format(CADENA_SUPLENTE, suplente.getNombreFirmante(), suplente.getNombreSuplente(), suplente.getMotivo()));
        }
    }

    public void checkArgument(boolean condicion) {
        if (condicion) {
            throw new IllegalArgumentException("Error al obtener los valores para realizar el registro en NYV");
        }
    }

    public void generarActoAdministrativo(AgaceOrden orden, FecetPruebasPericiales pruebasPericiales, EmpleadoDTO empleado,
            FececActosAdm actoAdmin, Date fechaVigenciaFiel, String rutaResolucion) {
        DatosNotificable notificable = new DatosNotificable();
        DetalleEmpleadoDTO detalleEmpleado = null;

        if (empleado.getDetalleEmpleado() != null) {
            detalleEmpleado = getDetalleEmpleadoDtoByTipoEmpleado(empleado, TipoEmpleadoEnum.FIRMANTE);
            notificable.setIdActoAdmon(actoAdmin.getIdNyv());
            notificable.setDescActoAdmon(actoAdmin.getProcesoOrigen());
            notificable.setNumeroEmpleado(StringUtils.leftPad(empleado.getIdEmpleado().toString(), TAMANIO_CADENA_EMPLEADO, "0"));
            notificable.setRfcContribuyente(orden.getFecetContribuyente().getRfc());
            notificable.setRfcFirmante(empleado.getRfc());
            notificable.setNumeroReferencia(String.format("%s-PP-%s", orden.getNumeroOrden(), pruebasPericiales.getId()));
        }
        DocumentoNotificable doctoNotificable = null;
        for (DocumentoActoAdministrativo documentoActo : actoAdmin.getDocumentosActo()) {
            doctoNotificable = new DocumentoNotificable();

            doctoNotificable.setEstatusDocumento(actoAdmin.getEstatusDocumento());
            if (actoAdmin.getIdDocumento().equals(documentoActo.getCveDocumentoNyv())) {
                doctoNotificable.setRutaDocumento(rutaResolucion);
            } else {
                String nombreArchivo = UtileriasMapperDao.getNameFileFromPath(rutaResolucion);
                String rutaDestino = rutaResolucion.replace(nombreArchivo, (documentoActo.getTipoDocumento() + ".pdf"));
                doctoNotificable.setRutaDocumento(rutaDestino);
            }
            doctoNotificable.setFirma(pruebasPericiales.getFirmaFirmante());
            doctoNotificable.setFechaDocumento(new Date());
            doctoNotificable.setCadenaOriginal(pruebasPericiales.getCadenaFirmante());
            doctoNotificable.setTipoDocumento(documentoActo.getTipoDocumento());
            doctoNotificable.setCveTipoDocumento(documentoActo.getCveDocumentoTipo());
            doctoNotificable.setRfcFirmante(empleado.getRfc());
            doctoNotificable.setNombreFirmante(empleado.getNombreCompleto());
            doctoNotificable.setPuestoFirmante(detalleEmpleado != null ? detalleEmpleado.getTipoEmpleado().getDescripcion() : "");
            doctoNotificable.setFechaVigenciaFiel(fechaVigenciaFiel);

            notificable.getDocumentosNotificables().add(doctoNotificable);
        }
        pruebasPericiales.setDatosNotificable(notificable);
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
    
    public Map<String, String> armaCorreo(Map<String, String> data, OrdenNotificacion orden, String remitente, String tipo){
        StringBuilder datos = new StringBuilder();        
        if(remitente.equals(Constantes.CONTRIBUYENTE)){            
            datos.append("<b>Folio de Carga: </b>");
            datos.append(orden.getFolioCarga().toString());
            datos.append(" <br/> ");
            data.put(Constantes.FOLIO_CARGA,datos.toString());
            data.put(Constantes.METODO_ORDEN, orden.getDescripcionMetodo());
            data.put(Constantes.NUMERO_ORDEN, orden.getNumeroOrden());
            datos = new StringBuilder();
            datos.append("<b>Nombre del Contribuyente: </b>");
            datos.append("<%nombreContribuyente%> <br/>");
            datos.append("<b>RFC del Contribuyente: </b>");
            datos.append("<%rfcContribuyente%> <br/>");            
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            data.put(Constantes.RFC_CONTRIBUYENTE, orden.getRfcContribuyente());    
        }
        else if(remitente.equals(Constantes.AUDITOR)){
            datos.append("<b>Folio de Carga: </b>");
            datos.append(orden.getFolioCarga().toString());
            datos.append(" <br/> ");
            data.put(Constantes.FOLIO_CARGA,datos.toString());
            data.put(Constantes.METODO_ORDEN, orden.getDescripcionMetodo());
            data.put(Constantes.NUMERO_ORDEN, orden.getNumeroOrden());
            datos = new StringBuilder();
            datos.append("<b>Nombre del Contribuyente: </b>");
            datos.append("<%nombreContribuyente%> <br/>");
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            data.put("APROBADA_RECHAZADA", tipo);
        }
        
        else if(remitente.equals(Constantes.FIRMANTE)){
            datos.append("<b>Folio de Carga: </b>");
            datos.append(orden.getFolioCarga().toString());
            datos.append(" <br/> ");
            data.put(Constantes.FOLIO_CARGA,datos.toString());
            data.put(Constantes.METODO_ORDEN, orden.getDescripcionMetodo());
            data.put(Constantes.NUMERO_ORDEN, orden.getNumeroOrden());
            datos = new StringBuilder();
            datos.append("<b>Nombre del Contribuyente: </b>");
            datos.append("<%nombreContribuyente%> <br/>");
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            data.put("APROBADA_RECHAZADA", tipo);
        }
        
        return data;
        
    }

}
