package mx.gob.sat.siat.feagace.vista.reportes.managedbean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.RepPistaAuditModel;
import mx.gob.sat.siat.feagace.negocio.reportes.ExportarReportesService;
import mx.gob.sat.siat.feagace.negocio.reportes.RepPistaAuditoriaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.helper.ValidaReportePistAuditHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarReportesMapHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name = "repPistaAuditMB")
public class RepPistaAuditMB extends BaseManagedBean{

    @SuppressWarnings("compatibility:5607543202380567693")
    private static final long serialVersionUID = 8044829251683829292L;

    @ManagedProperty(value = "#{repPistaAuditoriaService}")
    private transient RepPistaAuditoriaService repPistaAuditoriaService;
    
    @ManagedProperty(value = "#{exportarReportesService}")
    private transient ExportarReportesService exportarReportesService;
    
    @ManagedProperty(value = "#{validarReportesMapHelper}")
    private ValidarReportesMapHelper validarReportesMapHelper;
    
    @ManagedProperty(value = "#{validaReportePistAuditHelper}")
    private ValidaReportePistAuditHelper validaReportePistAuditHelper;
    
    private RepPistaAuditModel repPistAuditModel;
    private transient StreamedContent archivoExcel;
    private transient StreamedContent archivoPDF;
    private List<ReportePistaAuditoriaInternaDTO> listRepInterno;
    
    private static final int TRES = 3;
    private static final String NOMBRE_REP = "Consultar Pistas de Auditoria  Modulo Interno";
    private static final String TITULO_REP = "Pista de Auditoria";
    private static final String RANGO_DIAS = "Es necesario proporcionar un rango de dias";
    private static final String CAMPOS_NULOS = "Es necesario colocar un valor para la busqueda";
    
    public RepPistaAuditMB() {
        super();
    }
    
    @PostConstruct
    public void init(){
        repPistAuditModel = new RepPistaAuditModel();

        repPistAuditModel.setPnlBusqueda(true);
        repPistAuditModel.setPnlTabla(false);
        repPistAuditModel.setIdRegistro(null);
        repPistAuditModel.setNumOrden(null);
        repPistAuditModel.setRfcUsuario(null);
        repPistAuditModel.setNomUsuario(null);
        
        repPistAuditModel.setCampoActivoOrden(false);
        repPistAuditModel.setCampoActivoRegistro(false);
        repPistAuditModel.setCampoActivoRfcUser(false);
        repPistAuditModel.setCampoActivoUsuario(false);
        repPistAuditModel.setCampoActivoCalendar(false);
        this.establecerFecha();
    }
    
    public void buscarPistaAuditoria(){
        //I11112015025, PESA820315T68
        if(validaReportePistAuditHelper.getContador() != 0){
            ReportePistaAuditoriaInternaDTO pistaDTO = new ReportePistaAuditoriaInternaDTO();
            validaConsultas(pistaDTO);
            this.generaExcel();
            this.generaPdf();
        }else{
            FacesUtil.addErrorMessage(null, CAMPOS_NULOS, "");
        }
    }
    
    public void generaExcel(){
        this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_INT_EXCEL);
        this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
        this.getExportarReportesService().agregarRegistrosReporte(this.getValidarReportesMapHelper().listaPistaAudit(listRepInterno));
    }   
    
    public void generaPdf(){
        logger.debug("Exportar PDF");
            this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_INT_PDF);
            this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
            this.getExportarReportesService().agregarRegistrosReporte(this.getValidarReportesMapHelper().listaPistaAudit(listRepInterno));
    }
    
    public void establecerFecha(){
        int anio;
        Calendar calendarInicio = GregorianCalendar.getInstance();
        anio = validaReportePistAuditHelper.validaMes(calendarInicio.get(Calendar.MONTH), calendarInicio.get(Calendar.YEAR));
        
        calendarInicio.set(Calendar.DATE, calendarInicio.get(Calendar.DATE));
        calendarInicio.add(Calendar.MONTH, - TRES ); 
        calendarInicio.set(Calendar.YEAR, anio);
        
        repPistAuditModel.setFechaInicio(calendarInicio.getTime());
        
        Calendar calendarFin = GregorianCalendar.getInstance();
        repPistAuditModel.setFechaFin(calendarFin.getTime());
    }
    
    public void validaConsultas(ReportePistaAuditoriaInternaDTO pistaDTO){
        boolean cReg = validaConsultaReg(pistaDTO);
        if(!cReg){
            boolean cOrden = validaConsultaOrden(pistaDTO);
            if(!cOrden){
                boolean cRfcUser = validaConsultaRfcUser(pistaDTO);
                if(!cRfcUser){
                    validaConsultaNomUser(pistaDTO);
                }
            }
        }
    }
    
    public boolean validaConsultaReg(ReportePistaAuditoriaInternaDTO pistaDTO){
        if (validaReportePistAuditHelper.getContReg() != 0){
            pistaDTO.setIdRegistro(repPistAuditModel.getIdRegistro());
            listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO);
            this.activaPnlTabla();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaOrden(ReportePistaAuditoriaInternaDTO pistaDTO){
        if(validaReportePistAuditHelper.getContOrden() !=0){
                pistaDTO.setIdRegistro(repPistAuditModel.getNumOrden());
            listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO);
            this.activaPnlTabla();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaRfcUser(ReportePistaAuditoriaInternaDTO pistaDTO){
        if(validaReportePistAuditHelper.getContRfcUser() != 0){
            if(repPistAuditModel.getFechaBusqInicio() != null && repPistAuditModel.getFechaBusqFin()!= null){
                pistaDTO.setRfcUsuario(repPistAuditModel.getRfcUsuario().trim());
                pistaDTO.setFechaBusqInicio(repPistAuditModel.getFechaBusqInicio());
                pistaDTO.setFechaBusqFin(repPistAuditModel.getFechaBusqFin());
                listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO);
                this.activaPnlTabla();
                return true;
            }else{
                FacesUtil.addErrorMessage(null, RANGO_DIAS, "");
                return false;
            }
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaNomUser(ReportePistaAuditoriaInternaDTO pistaDTO){
        if(validaReportePistAuditHelper.getContNomUser() != 0){
                if(repPistAuditModel.getFechaBusqInicio() != null && repPistAuditModel.getFechaBusqFin()!= null){
                pistaDTO.setNomUsuario(repPistAuditModel.getNomUsuario());
                pistaDTO.setFechaBusqInicio(repPistAuditModel.getFechaBusqInicio());
                pistaDTO.setFechaBusqFin(repPistAuditModel.getFechaBusqFin());
                listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO);
                this.activaPnlTabla();
                return true;
            }else{
                FacesUtil.addErrorMessage(null, RANGO_DIAS, "");
                return false;
            }
        }else{
            return false;
        }
    }
    
    public void validaCampos(){
        boolean uno = validaReportePistAuditHelper.validaCampoReg(repPistAuditModel);
        if(!uno){
            boolean dos = validaReportePistAuditHelper.validaCampoOrden(repPistAuditModel);
            if(!dos){
                boolean tres = validaReportePistAuditHelper.validaCampoRfcUser(repPistAuditModel);
                if(!tres){
                    validaReportePistAuditHelper.validaCampoNomUser(repPistAuditModel);
                }
            }
        }
    }
    
    public void btnRegresarPanel(){ 
        repPistAuditModel.setPnlBusqueda(true);
        repPistAuditModel.setPnlTabla(false);
        this.clean();
    }
    
    public void cleanBusqueda(){
        repPistAuditModel.setIdRegistro(null);
        repPistAuditModel.setNumOrden(null);
        repPistAuditModel.setRfcUsuario(null);
        repPistAuditModel.setNomUsuario(null);
        
        repPistAuditModel.setCampoActivoOrden(false);
        repPistAuditModel.setCampoActivoRegistro(false);
        repPistAuditModel.setCampoActivoRfcUser(false);
        repPistAuditModel.setCampoActivoUsuario(false);
        repPistAuditModel.setCampoActivoCalendar(false);
        repPistAuditModel.setFechaBusqInicio(null);
        repPistAuditModel.setFechaBusqFin(null);
        this.establecerFecha();
    }
    
    public void clean(){
        repPistAuditModel.setIdRegistro(null);
        repPistAuditModel.setNumOrden(null);
        repPistAuditModel.setRfcUsuario(null);
        repPistAuditModel.setNomUsuario(null);
        
        repPistAuditModel.setCampoActivoOrden(false);
        repPistAuditModel.setCampoActivoRegistro(false);
        repPistAuditModel.setCampoActivoRfcUser(false);
        repPistAuditModel.setCampoActivoUsuario(false);
        repPistAuditModel.setCampoActivoCalendar(false);
        repPistAuditModel.setFechaBusqInicio(null);
        repPistAuditModel.setFechaBusqFin(null);
        this.establecerFecha();
        listRepInterno = new ArrayList<ReportePistaAuditoriaInternaDTO>();
    }
    
    public void activaPnlTabla(){
        repPistAuditModel.setPnlBusqueda(false);
        repPistAuditModel.setPnlTabla(true);
    }

    public void setListRepInterno(List<ReportePistaAuditoriaInternaDTO> listRepInterno) {
        this.listRepInterno = listRepInterno;
    }

    public List<ReportePistaAuditoriaInternaDTO> getListRepInterno() {
        return listRepInterno;
    }

    public void setRepPistaAuditoriaService(RepPistaAuditoriaService repPistaAuditoriaService) {
        this.repPistaAuditoriaService = repPistaAuditoriaService;
    }

    public RepPistaAuditoriaService getRepPistaAuditoriaService() {
        return repPistaAuditoriaService;
    }
    
    public void setExportarReportesService(ExportarReportesService exportarReportesService) {
        this.exportarReportesService = exportarReportesService;
    }

    public ExportarReportesService getExportarReportesService() {
        return exportarReportesService;
    }
    
    public void setValidarReportesMapHelper(ValidarReportesMapHelper validarReportesMapHelper) {
        this.validarReportesMapHelper = validarReportesMapHelper;
    }

    public ValidarReportesMapHelper getValidarReportesMapHelper() {
        return validarReportesMapHelper;
    }

    public void setArchivoExcel(StreamedContent archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public StreamedContent getArchivoExcel() {
        try{
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS, "xls");
            this.setArchivoExcel(new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/vnd.ms-excel", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS + ".xls"));
        } catch (FileNotFoundException e) {
            logger.error("no funka xls ", e);
        }
        return archivoExcel;
    }

    public void setArchivoPDF(StreamedContent archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public StreamedContent getArchivoPDF() {
        try{
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF, "pdf");
            this.setArchivoPDF(new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/pdf", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF + ".pdf"));
        } catch (FileNotFoundException e) {
            logger.error("no funka pdf ", e);
        }
        return archivoPDF;
    }

    public void setRepPistAuditModel(RepPistaAuditModel repPistAuditModel) {
        this.repPistAuditModel = repPistAuditModel;
    }

    public RepPistaAuditModel getRepPistAuditModel() {
        return repPistAuditModel;
    }

    public void setValidaReportePistAuditHelper(ValidaReportePistAuditHelper validaReportePistAuditHelper) {
        this.validaReportePistAuditHelper = validaReportePistAuditHelper;
    }

    public ValidaReportePistAuditHelper getValidaReportePistAuditHelper() {
        return validaReportePistAuditHelper;
    }
}
