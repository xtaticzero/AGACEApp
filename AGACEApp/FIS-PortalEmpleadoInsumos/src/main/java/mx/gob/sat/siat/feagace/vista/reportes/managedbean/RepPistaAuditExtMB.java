package mx.gob.sat.siat.feagace.vista.reportes.managedbean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaExternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.RepPistaAuditExtModel;
import mx.gob.sat.siat.feagace.negocio.reportes.ExportarReportesService;
import mx.gob.sat.siat.feagace.negocio.reportes.RepPistaAuditoriaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.ValidaReportePistAuditExtHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarReportesMapHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ViewScoped
@ManagedBean(name = "repPistaAuditExtMB")
public class RepPistaAuditExtMB extends BaseManagedBean {

    private static final long serialVersionUID = 3866630968023603487L;
    
    @ManagedProperty(value = "#{repPistaAuditoriaService}")
    private transient RepPistaAuditoriaService repPistaAuditoriaService;
    
    @ManagedProperty(value = "#{exportarReportesService}")
    private transient ExportarReportesService exportarReportesService;
    
    @ManagedProperty(value = "#{validarReportesMapHelper}")
    private transient ValidarReportesMapHelper validarReportesMapHelper;
    
    @ManagedProperty(value = "#{validaReportePistAuditExtHelper}")
    private transient ValidaReportePistAuditExtHelper validaReportePistAuditExtHelper;
    
    private RepPistaAuditExtModel repPistaAuditExtModel;
    private List<ReportePistaAuditoriaExternaDTO> listRepExter;
    
    private static final String NOMBRE_REP = "Consultar Pistas de Auditoria  Modulo Externo";
    private static final String TITULO_REP = "Pista de Auditoria";
    private static final String RANGO_DE_DIAS = "Es necesario proporcionar un rango de dias";
    private static final String CAMPOS_NULOS = "Es necesario colocar un valor para la busqueda";
    

    public RepPistaAuditExtMB() {
        super();
    }

    @PostConstruct
    public void init() {
        repPistaAuditExtModel =  new RepPistaAuditExtModel();
        
        repPistaAuditExtModel.setRfcContribuyente(null);
        repPistaAuditExtModel.setRfcRepLegal(null);
        repPistaAuditExtModel.setRfcApodLegal(null);
        repPistaAuditExtModel.setRfcAgentAduanal(null);
        repPistaAuditExtModel.setRfcApodLegalRepLegal(null);
        repPistaAuditExtModel.setNumOrden(null);
        repPistaAuditExtModel.setIdRegistro(null);
        
        repPistaAuditExtModel.setPnlBusqueda(true);
        repPistaAuditExtModel.setPnlTabla(false);
        
        repPistaAuditExtModel.setCampoActivoRfcContribuyente(false);
        repPistaAuditExtModel.setCampoActivoRfcRepLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(false);
        repPistaAuditExtModel.setCampoActivoNumOreden(false);
        repPistaAuditExtModel.setCampoActivoIdRegistro(false);
        repPistaAuditExtModel.setCampoActivoCanlendar(false);
        
        this.establecerFecha();
    }

    public void buscarPistaAuditoria() {
        if(validaReportePistAuditExtHelper.getContador() != 0){
            ReportePistaAuditoriaExternaDTO pistaDTo = new ReportePistaAuditoriaExternaDTO();
            this.validaConsultas(pistaDTo);
        }else{
            FacesUtil.addErrorMessage(null, CAMPOS_NULOS, "");
        }
    }
    
    public void validaConsultas(ReportePistaAuditoriaExternaDTO pistaDTO){
        boolean cReg =  validaConsultaReg(pistaDTO);
        if(!cReg){
            boolean cOrden = validaConsultaOrden(pistaDTO);
            if(!cOrden){
                boolean cRfcAgentAduanal = validaConsultaRfcAgentAduanal(pistaDTO);
                if(!cRfcAgentAduanal){
                    boolean cRfcApodLegal = validaConsultaRfcApodLegal(pistaDTO);
                    if(!cRfcApodLegal){
                        boolean cRfcApodLegalRepLegal = validaConsultaRfcApodLegalRepLegal(pistaDTO);
                        if(!cRfcApodLegalRepLegal){
                            boolean cRfcContry = validaConsultaRfcContry(pistaDTO);
                            if (!cRfcContry){
                                validaConsultaRfcRepLegal(pistaDTO);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean validaConsultaReg(ReportePistaAuditoriaExternaDTO pistaDTO){
        if (validaReportePistAuditExtHelper.getContadorIdRegistro() != 0){
            pistaDTO.setIdRegistro(repPistaAuditExtModel.getIdRegistro());
            listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
            this.activaPnlTabla();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaOrden(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorNumOreden() !=0){
                pistaDTO.setNumOreden(repPistaAuditExtModel.getNumOrden());
            listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
            this.activaPnlTabla();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaRfcAgentAduanal(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorRfcAgentAduanal() !=0){
            if(repPistaAuditExtModel.getFechaBusqInicio() != null && repPistaAuditExtModel.getFechaBusqFin()!= null){
                    pistaDTO.setRfcAgentAduanal(repPistaAuditExtModel.getRfcAgentAduanal());
                    pistaDTO.setFechaBusqInicio(repPistaAuditExtModel.getFechaBusqInicio());
                    pistaDTO.setFechaBusqFin(repPistaAuditExtModel.getFechaBusqFin());
                    listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
                    this.activaPnlTabla();
                    return true;
                }else{
                    FacesUtil.addErrorMessage(null, RANGO_DE_DIAS, "");
                    return false;
                }
        }else{
            return false;
        }

    }
    
    public boolean validaConsultaRfcApodLegal(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorRfcApodLegal() != 0){
            if(repPistaAuditExtModel.getFechaBusqInicio() != null && repPistaAuditExtModel.getFechaBusqFin()!= null){
                    pistaDTO.setRfcApodLegal(repPistaAuditExtModel.getRfcApodLegal());
                    pistaDTO.setFechaBusqInicio(repPistaAuditExtModel.getFechaBusqInicio());
                    pistaDTO.setFechaBusqFin(repPistaAuditExtModel.getFechaBusqFin());
                    listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
                    this.activaPnlTabla();
                    return true;
                }else{
                    FacesUtil.addErrorMessage(null, RANGO_DE_DIAS, "");
                    return false;
                }
        }else{
            return false;
        }

    }
    
    public boolean validaConsultaRfcApodLegalRepLegal(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorRfcApodLegalRepLegal() != 0){
            if(repPistaAuditExtModel.getFechaBusqInicio() != null && repPistaAuditExtModel.getFechaBusqFin()!= null){
                    pistaDTO.setRfcApodLegalRepLegal(repPistaAuditExtModel.getRfcApodLegalRepLegal());
                    pistaDTO.setFechaBusqInicio(repPistaAuditExtModel.getFechaBusqInicio());
                    pistaDTO.setFechaBusqFin(repPistaAuditExtModel.getFechaBusqFin());
                    listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
                    this.activaPnlTabla();
                    return true;
                }else{
                    FacesUtil.addErrorMessage(null, RANGO_DE_DIAS, "");
                    return false;
                }
        }else{
            return false;
        }

    }
    
    public boolean validaConsultaRfcContry(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorRfcContribuyente() != 0){
            if(repPistaAuditExtModel.getFechaBusqInicio() != null && repPistaAuditExtModel.getFechaBusqFin()!= null){
                    pistaDTO.setRfcContribuyente(repPistaAuditExtModel.getRfcContribuyente());
                    pistaDTO.setFechaBusqInicio(repPistaAuditExtModel.getFechaBusqInicio());
                    pistaDTO.setFechaBusqFin(repPistaAuditExtModel.getFechaBusqFin());
                    listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
                    this.activaPnlTabla();
                    return true;
                }else{
                    FacesUtil.addErrorMessage(null, RANGO_DE_DIAS, "");
                    return false;
                }
        }else{
            return false;
        }

    }
    
    public boolean validaConsultaRfcRepLegal(ReportePistaAuditoriaExternaDTO pistaDTO){
        if(validaReportePistAuditExtHelper.getContadorRfcRepLegal() !=0){
            if(repPistaAuditExtModel.getFechaBusqInicio() != null && repPistaAuditExtModel.getFechaBusqFin()!= null){
                    pistaDTO.setRfcRepLegal(repPistaAuditExtModel.getRfcRepLegal());
                    pistaDTO.setFechaBusqInicio(repPistaAuditExtModel.getFechaBusqInicio());
                    pistaDTO.setFechaBusqFin(repPistaAuditExtModel.getFechaBusqFin());
                    listRepExter = repPistaAuditoriaService.buscaPistaAuditoriaExterna(pistaDTO);
                    this.activaPnlTabla();
                    return true;
                }else{
                    FacesUtil.addErrorMessage(null, RANGO_DE_DIAS, "");
                    return false;
                }
        }else{
            return false;
        }

    }

    public void generaExcel() {
        this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_EXT_EXCEL);
        this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
        this.getExportarReportesService().agregarRegistrosReporte(validarReportesMapHelper.listaPistaAuditExt(listRepExter != null ? listRepExter : new ArrayList<ReportePistaAuditoriaExternaDTO>()));

    }

    public void generaPdf() {
       this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_EXT_PDF);
       this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
       this.getExportarReportesService().agregarRegistrosReporte(validarReportesMapHelper.listaPistaAuditExt(listRepExter != null ? listRepExter : new ArrayList<ReportePistaAuditoriaExternaDTO>()));

    }

    public void establecerFecha() {
        Calendar calendarFin = GregorianCalendar.getInstance();
        repPistaAuditExtModel.setFechaFin(calendarFin.getTime());
        logger.debug("fechaFin : ", repPistaAuditExtModel.getFechaFin().toString());
    }
    
    public void validaCampos(){
        boolean uno = validaReportePistAuditExtHelper.validaCampoReg(repPistaAuditExtModel);
        if(!uno){
            boolean dos = validaReportePistAuditExtHelper.validaCampoOrden(repPistaAuditExtModel);
            if(!dos){
                boolean tres = validaReportePistAuditExtHelper.validaCampoRfcAgentAduanal(repPistaAuditExtModel);
                if(!tres){
                    boolean cuatro = validaReportePistAuditExtHelper.validaCampoRfcApodLegal(repPistaAuditExtModel);
                    if(!cuatro){
                        boolean cinco = validaReportePistAuditExtHelper.validaCampoRfcApodLegalRepLegal(repPistaAuditExtModel);
                        if(!cinco){
                            boolean seis = validaReportePistAuditExtHelper.validaCampoRfcContry(repPistaAuditExtModel);
                            if(!seis){
                                 validaReportePistAuditExtHelper.validaCampoRfcRepLegal(repPistaAuditExtModel);
                            }
                        }
                    }
                }
            }
        }
    }

    public void btnRegresarPanel() {
        repPistaAuditExtModel.setPnlBusqueda(true);
        repPistaAuditExtModel.setPnlTabla(false);
        this.clean();
    }

    public void cleanBusqueda() {
        repPistaAuditExtModel.setNumOrden(null);
        repPistaAuditExtModel.setIdRegistro(null);
        repPistaAuditExtModel.setRfcContribuyente(null);
        repPistaAuditExtModel.setRfcRepLegal(null);
        repPistaAuditExtModel.setRfcAgentAduanal(null);
        repPistaAuditExtModel.setRfcApodLegal(null);
        repPistaAuditExtModel.setRfcApodLegalRepLegal(null);
        
        repPistaAuditExtModel.setCampoActivoRfcContribuyente(false);
        repPistaAuditExtModel.setCampoActivoRfcRepLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(false);
        repPistaAuditExtModel.setCampoActivoNumOreden(false);
        repPistaAuditExtModel.setCampoActivoIdRegistro(false);
        repPistaAuditExtModel.setCampoActivoCanlendar(false);
        
        repPistaAuditExtModel.setFechaBusqInicio(null);
        repPistaAuditExtModel.setFechaBusqFin(null);
        this.establecerFecha();

    }

    public void clean() {
        repPistaAuditExtModel.setNumOrden(null);
        repPistaAuditExtModel.setIdRegistro(null);
        repPistaAuditExtModel.setRfcContribuyente(null);
        repPistaAuditExtModel.setRfcRepLegal(null);
        repPistaAuditExtModel.setRfcAgentAduanal(null);
        repPistaAuditExtModel.setRfcApodLegal(null);
        repPistaAuditExtModel.setRfcApodLegalRepLegal(null);
        
        repPistaAuditExtModel.setCampoActivoRfcContribuyente(false);
        repPistaAuditExtModel.setCampoActivoRfcRepLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegal(false);
        repPistaAuditExtModel.setCampoActivoRfcAgentAduanal(false);
        repPistaAuditExtModel.setCampoActivoRfcApodLegalRepLegal(false);
        repPistaAuditExtModel.setCampoActivoNumOreden(false);
        repPistaAuditExtModel.setCampoActivoIdRegistro(false);
        repPistaAuditExtModel.setCampoActivoCanlendar(false);
        listRepExter = new ArrayList<ReportePistaAuditoriaExternaDTO>();
        repPistaAuditExtModel.setFechaBusqInicio(null);
        repPistaAuditExtModel.setFechaBusqFin(null);
        this.establecerFecha();
    }
    
    public void activaPnlTabla(){
        repPistaAuditExtModel.setPnlBusqueda(false);
        repPistaAuditExtModel.setPnlTabla(true);
    }
    
    public void regresaPaginaPrincipal() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultaPistaAuditInterno.jsf");
        } catch (IOException e) {
            logger.error("ERROR: " + e.getMessage());
        }
    }

    public void setListRepExter(List<ReportePistaAuditoriaExternaDTO> listRepExter) {
        this.listRepExter = listRepExter;
    }

    public List<ReportePistaAuditoriaExternaDTO> getListRepExter() {
        return listRepExter;
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

    public StreamedContent getArchivoExcel() {
        try{
            logger.error("Descargando Excel Pistas Auditoría Externa");
            this.generaExcel();
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS, "xls");
            return new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/vnd.ms-excel", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS + ".xls");
        } catch (Exception e) {
            logger.error("Error: ", e.getMessage());
            return null;
        }
    }

    public StreamedContent getArchivoPDF() {
        try{
            logger.error("Descargando PDF Pistas Auditoría Externa");
            this.generaPdf();
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF, "pdf");
            return new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/pdf", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF + ".pdf");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void setRepPistaAuditExtModel(RepPistaAuditExtModel repPistaAuditExtModel) {
        this.repPistaAuditExtModel = repPistaAuditExtModel;
    }

    public RepPistaAuditExtModel getRepPistaAuditExtModel() {
        return repPistaAuditExtModel;
    }

    public void setValidarReportesMapHelper(ValidarReportesMapHelper validarReportesMapHelper) {
        this.validarReportesMapHelper = validarReportesMapHelper;
    }

    public ValidarReportesMapHelper getValidarReportesMapHelper() {
        return validarReportesMapHelper;
    }

    public void setValidaReportePistAuditExtHelper(ValidaReportePistAuditExtHelper validaReportePistAuditExtHelper) {
        this.validaReportePistAuditExtHelper = validaReportePistAuditExtHelper;
    }

    public ValidaReportePistAuditExtHelper getValidaReportePistAuditExtHelper() {
        return validaReportePistAuditExtHelper;
    }
}

