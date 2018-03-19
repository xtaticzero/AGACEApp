package mx.gob.sat.siat.feagace.negocio.reportes.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.reportes.ExportarReportesService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.springframework.stereotype.Service;


/**
 * Servicios que genera el archivo File desde JasperReport
 *
 * @author Domingo Fernandez
 * @version 1.0
 *
 */
@Service("exportarReportesService")
public class ExportarReportesServiceImpl extends BaseBusinessServices implements ExportarReportesService {

    @SuppressWarnings("compatibility:-5528846822111147465")
    private static final long serialVersionUID = 1L;
    
    private File file;
    private transient JRMapCollectionDataSource dataSource;
    private JasperPrint jasperPrint;
    private List<Map<String,?>> listaRegistros = new ArrayList<Map<String, ?>> ();
    private Map<String,Object> headerReporte = new HashMap<String, Object>();
    private String pathJasper;
    private String nombreArchivo;
    
    private void crearReferenciaReporte(){
        try{
            this.setJasperPrint(JasperFillManager.fillReport(this.getPathJasper(), this.getHeaderReporte(),this.getDataSource()));
        }catch(JRException e){
            logger.error("JRException: [{}]", e.getMessage());
        }
    }
    
    @Override
    public void crearHeaderReporte(String titulo, String nombreReporte){
        this.getHeaderReporte().put("logoSHCP", ConstantesReportes.PATH_REPORTE_IMAGENES + Constantes.LOGO_SHCP_JPG);
        this.getHeaderReporte().put("logoSAT", ConstantesReportes.PATH_REPORTE_IMAGENES + Constantes.LOGO_SAT_JPG);
        this.getHeaderReporte().put("titulo2", titulo.toUpperCase());
        this.getHeaderReporte().put("nombreReporte", nombreReporte.toUpperCase());
    }
    
    @Override
    public void agregarRegistrosReporte(List<Map<String,?>> registros){
        this.setListaRegistros(registros);
        this.setDataSource(new JRMapCollectionDataSource(this.getListaRegistros()));
    }
    
    @Override
    public void crearArchivo(String nombre, String tipo){
        this.setFile(null);
        this.setNombreArchivo(nombre);
        this.crearReferenciaReporte();
        try{
            this.setFile(File.createTempFile(this.getNombreArchivo(),tipo));
            if(tipo.trim().equals("pdf")){
                JasperExportManager.exportReportToPdfStream(this.getJasperPrint(), new FileOutputStream(this.getFile()));
            } else if(tipo.trim().equals("xls")){
                JRXlsExporter xlsExporter=new JRXlsExporter();
                xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);  
                xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(this.getFile())); 
                xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
                xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
                xlsExporter.setParameter(JExcelApiExporterParameter.CHARACTER_ENCODING, "UTF-8");
                xlsExporter.exportReport(); 
            }
        } catch(JRException e){
            logger.error("JRException: [{}]", e.getMessage());
        } catch (IOException ioe){
            logger.error("IOException: [{}]", ioe.getMessage());
        }
    }
    
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public File getFile() {
        return file;
    }

    public void setDataSource(JRMapCollectionDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JRMapCollectionDataSource getDataSource() {
        return dataSource;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setListaRegistros(List<Map<String, ?>> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }

    public List<Map<String, ?>> getListaRegistros() {
        return listaRegistros;
    }

    public void setHeaderReporte(Map<String, Object> headerReporte) {
        this.headerReporte = headerReporte;
    }

    public Map<String, Object> getHeaderReporte() {
        return headerReporte;
    }

    @Override
    public void setPathJasper(String pathJasper) {
        this.pathJasper = pathJasper;
    }

    public String getPathJasper() {
        return pathJasper;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }
}
