package mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.helper;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Component;

@Component
public class FirmaProrrogaHelper extends FirmaHelper {

    private static final long serialVersionUID = 5342238869291569108L;

    private static final String ERROR = "Error: ";
    private static final String REPORTES = "REPORTES";
    private static final String ARCHIVO_REPORTES = Propiedades
            .get("jasper.ubicacion.reportes");
    private static final String ARCHIVO_ACUSE = "acuse.jasper";


    @Override
    public List<FirmaDTO> armarCadena(Object objDatos, String rfc) {
        FecetProrrogaOrden prorroga = (FecetProrrogaOrden) objDatos;
        List<FirmaDTO> firmas = new ArrayList<FirmaDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (prorroga != null) {
            FirmaDTO firmaDTO = new FirmaDTO();
            firmaDTO.setId(String.valueOf(prorroga.getIdProrrogaOrden()));
            StringBuilder cadena = new StringBuilder("|");
            Date date = new Date();
            cadena.append(prorroga.getOrden().getNumeroOrden());
            cadena.append("|").append(prorroga.getIdProrrogaOrden());
            cadena.append("|").append(sdf.format(date));
            cadena.append("|").append(
                    prorroga.getOrden().getFecetContribuyente().getRfc());
            cadena.append("|").append(prorroga.getIdProrrogaOrden());
            cadena.append("|").append(prorroga.getNombreAcuse());
            if (prorroga.getAprobada()) {
                cadena.append("|").append("Aprobada");
            } else {
                cadena.append("|").append("Rechazada");
            }
            cadena.append("|").append(
                    prorroga.getFirmante() == null ? "" : prorroga
                            .getFirmante().getDetalleEmpleado().get(0).getCentral().getNombre());
            cadena.append("|").append(
                    prorroga.getFirmante() == null ? "" : prorroga
                            .getFirmante().getRfc());
            cadena.append(prorroga.getIdOrden());
            cadena.append("|").append(
                    (new SimpleDateFormat("dd/MM/yyyy HH:mm")).format(date));
            firmaDTO.setCadena(cadena.toString());
            firmas.add(firmaDTO);
        }
        return firmas;
    }

    public byte[] generarAcuseRevisionElectronica(FirmaDTO firma,
            FecetProrrogaOrden prorroga) {
        AcuseRevisionElectronica acuse = new AcuseRevisionElectronica();
        acuse.setTituloGeneral("Documento de Acuse.");
        acuse.setCadenaOriginal(firma.getCadena());
        acuse.setEmisionAcuse(new Date().toString());
        acuse.setNombreArchivoProrroga(prorroga.getNombreAcuse());
        acuse.setRutaAcuse(prorroga.getRutaAcuse());
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

    public String armarRutaAnexosProrrogaAprobadaFirmante(final BigDecimal idFlujoProrroga) {
        StringBuilder rutaDestino =
            new StringBuilder(Constantes.DIRECTORIO_ARCHIVOS_ORDENES.concat("resolucion_prorroga/").concat(idFlujoProrroga.toString()).concat("/"));        
        return rutaDestino.toString();
    }
    
    public Map<String, String> armaCorreo(Map<String, String> data, OrdenNotificacion orden, String remitente, String tipo, FecetOficio oficio){
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
            if(oficio != null){
                datos.append("<b>Oficio: </b>");
                datos.append("<%nombreOficio%> <br/>");                
            }
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            if(oficio != null){
                data.put(Constantes.NOMBRE_OFICIO, TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue()).getNombre());
            }
            data.put(Constantes.RFC_CONTRIBUYENTE, orden.getRfcContribuyente());
            if(oficio != null){
                data.put(Constantes.NUMERO, "Oficio:" + oficio.getNumeroOficio());
            }
            else{
                data.put(Constantes.NUMERO, "Orden:" + orden.getNumeroOrden());
            }
            
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
            if(oficio != null){
                datos.append("<b>Oficio: </b>");
                datos.append("<%nombreOficio%> <br/>");               
            }
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            if(oficio != null){
                data.put(Constantes.NOMBRE_OFICIO, TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue()).getNombre());
            }            
            data.put("APROBADA_RECHAZADA", tipo);
            if(oficio != null){
                data.put(Constantes.NUMERO, "Oficio:" + oficio.getNumeroOficio());
            }
            else{
                data.put(Constantes.NUMERO, "Orden:" + orden.getNumeroOrden());
            }
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
            if(oficio != null){
                datos.append("<b>Oficio: </b>");
                datos.append("<%nombreOficio%> <br/>");               
            }
            data.put(Constantes.DATOS_EXTRA, datos.toString());
            data.put(Constantes.NOMBRE_CONTRIBUYENTE, orden.getNombreContribuyente());
            if(oficio != null){
                data.put(Constantes.NOMBRE_OFICIO, TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue()).getNombre());
            }
            data.put("APROBADA_RECHAZADA", tipo);
            if(oficio != null){
                data.put(Constantes.NUMERO, "Oficio:" + oficio.getNumeroOficio());
            }
            else{
                data.put(Constantes.NUMERO, "Orden:" + orden.getNumeroOrden());
            }
        }
        
        return data;
        
    }
}
