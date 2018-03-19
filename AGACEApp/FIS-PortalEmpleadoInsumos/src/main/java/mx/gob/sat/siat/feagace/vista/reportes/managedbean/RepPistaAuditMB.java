package mx.gob.sat.siat.feagace.vista.reportes.managedbean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReportePistaAuditoriaInternaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.RepPistaAuditModel;
import mx.gob.sat.siat.feagace.negocio.reportes.ExportarReportesService;
import mx.gob.sat.siat.feagace.negocio.reportes.RepPistaAuditoriaService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesReportes;
import mx.gob.sat.siat.feagace.vista.helper.ValidaReportePistAuditHelper;
import mx.gob.sat.siat.feagace.vista.helper.ValidarReportesMapHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ViewScoped
@ManagedBean(name = "repPistaAuditMB")
public class RepPistaAuditMB extends BaseManagedBean {

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
    private List<ReportePistaAuditoriaInternaDTO> listRepInterno;
    private static final List<EmpleadoDTO> LST_EMPLEADOS = new ArrayList<EmpleadoDTO>();
    private Set<String> lstRfcsEmpleado;
    private Set<String> lstNombreEmpleados;
    private boolean panelTipoConsulta;
    
    private static final String NOMBRE_REP = "Consultar Pistas de Auditoria  Modulo Interno";
    private static final String TITULO_REP = "Pista de Auditoria";
    private static final String RANGO_DIAS = "Es necesario proporcionar un rango de dias";
    private static final String CAMPOS_NULOS = "Es necesario colocar un valor para la busqueda";
    private static final String COMBO_SINOPCION = "Es necesario seleccionar un valor para la busqueda";
    private static final int INDEX_CADENA = -1;
    public static final int CERO = 0;
    public static final int UNO = 1;
    public static final int DOS = 2;
    
    private int tipoConsulta;
        
    public RepPistaAuditMB() {
        super();
    }
    
    @PostConstruct
    public void init(){
        repPistAuditModel = new RepPistaAuditModel();
        setPanelTipoConsulta(true);
        setTipoConsulta(CERO);

        repPistAuditModel.setPnlBusqueda(false);
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
        lstRfcsEmpleado = new HashSet<String>();
        lstNombreEmpleados = new HashSet<String>();
        this.establecerFecha();
        
        if (LST_EMPLEADOS.isEmpty()) {
            LST_EMPLEADOS.addAll(repPistaAuditoriaService.obtenerTodosEmpleados());
        }
        
        obtenerRfcsNombreEmpleado();
    }
    
    public void buscarPistaAuditoria(){
        //I11112015025, PESA820315T68
        if(validaReportePistAuditHelper.getContador() != 0){
            ReportePistaAuditoriaInternaDTO pistaDTO = new ReportePistaAuditoriaInternaDTO();
            validaConsultas(pistaDTO);
        }else{
            FacesUtil.addErrorMessage(null, CAMPOS_NULOS, "");
        }
    }
    
    public void generaExcel(){
        this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_INT_EXCEL);
        this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
        this.getExportarReportesService().agregarRegistrosReporte(this.getValidarReportesMapHelper().listaPistaAudit(listRepInterno != null ? listRepInterno : new ArrayList<ReportePistaAuditoriaInternaDTO>()));
    }   
    
    public void generaPdf(){
        logger.debug("Exportar PDF");
            this.getExportarReportesService().setPathJasper(ConstantesReportes.PATH_REPORTE + ConstantesReportes.REPORTE_PA + ConstantesReportes.REPORTE_PIS_AUDIT_INT_PDF);
            this.getExportarReportesService().crearHeaderReporte(NOMBRE_REP, TITULO_REP);
            this.getExportarReportesService().agregarRegistrosReporte(this.getValidarReportesMapHelper().listaPistaAudit(listRepInterno != null ? listRepInterno : new ArrayList<ReportePistaAuditoriaInternaDTO>()));
    }
    
    public void establecerFecha(){        
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
            listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO, LST_EMPLEADOS);
            this.activaPnlTabla();
            return true;
        }else{
            return false;
        }
    }
    
    public boolean validaConsultaOrden(ReportePistaAuditoriaInternaDTO pistaDTO){
        if(validaReportePistAuditHelper.getContOrden() !=0){
                pistaDTO.setNumOreden(repPistAuditModel.getNumOrden());
            listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO, LST_EMPLEADOS);
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
                listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO, LST_EMPLEADOS);
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
                listRepInterno = repPistaAuditoriaService.buscaPistaAuditoriaInterna(pistaDTO, LST_EMPLEADOS);
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

    public StreamedContent getArchivoExcel() {
        try{
            logger.error("Descargando Excel Pistas Auditoría Interna");
            this.generaExcel();
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS, "xls");
            return new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/vnd.ms-excel", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_XLS + ".xls");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    public StreamedContent getArchivoPDF() {
        try{
            logger.error("Descargando PDF Pistas Auditoría Interna");
            this.generaPdf();
            this.getExportarReportesService().crearArchivo(ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF, "pdf");
            return new DefaultStreamedContent(new FileInputStream(this.getExportarReportesService().getFile()), "application/pdf", ConstantesReportes.ARCHIVO_PISTAS_AUDITORIA_PDF + ".pdf");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    
    public List<String> autocompletarNombre(String query) {
        List<String> lstNombresResult = new ArrayList<String>();

        try {
            if ((query != null) && (query.length() > 0)) {
                lstNombresResult.clear();
                for (String rfcTmp : lstNombreEmpleados) {
                    if (esParteDeCadena(query, rfcTmp)) {
                        lstNombresResult.add(rfcTmp);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return lstNombresResult;
    }
    
    public List<String> autocompletarRFC(String query) {
        List<String> lstRfcsResult = new ArrayList<String>();

        try {
            if ((query != null) && (query.length() > 0)) {
                lstRfcsResult.clear();
                for (String rfcTmp : lstRfcsEmpleado) {
                    if (esParteDeCadena(query, rfcTmp)) {
                        lstRfcsResult.add(rfcTmp);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return lstRfcsResult;
    }

    private boolean esParteDeCadena(String query, String nombre) {
        if (query != null && !query.isEmpty() && nombre != null) {
            String queryUpperCase = query.toUpperCase();
            String rfcUpperCase = nombre.toUpperCase();
            return rfcUpperCase.indexOf(queryUpperCase) > INDEX_CADENA;
        }
        return false;
    }
    
    private void obtenerRfcsNombreEmpleado() {
        for (EmpleadoDTO empleado : LST_EMPLEADOS) {
            lstRfcsEmpleado.add(empleado.getRfc());
            lstNombreEmpleados.add(empleado.getNombreCompleto());
        } 
    }
    
    public void redireccionaConsulta() {
        try {
            switch (tipoConsulta) {
                case UNO:
                    //habilita panel pistas internas
                    activaPanelBusqueda();
                    break;
                case DOS:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("consultaPistaAuditExterno.jsf");
                    break;
                default:
                    FacesUtil.addErrorMessage(null, COMBO_SINOPCION, "");
                    break;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    private void activaPanelBusqueda() {
        
        repPistAuditModel.setPnlBusqueda(true);
        repPistAuditModel.setPnlTabla(false);
        setPanelTipoConsulta(false);
    }
    
    public void activaPanelTipoConsulta() {
        repPistAuditModel.setPnlBusqueda(false);
        repPistAuditModel.setPnlTabla(false);
        setPanelTipoConsulta(true);
        setTipoConsulta(CERO);
        this.clean();
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

    public Set<String> getLstRfcsEmpleado() {
        return lstRfcsEmpleado;
    }

    public void setLstRfcsEmpleado(Set<String> lstRfcsEmpleado) {
        this.lstRfcsEmpleado = lstRfcsEmpleado;
    }

    public Set<String> getLstNombreEmpleados() {
        return lstNombreEmpleados;
    }

    public void setLstNombreEmpleados(Set<String> lstNombreEmpleados) {
        this.lstNombreEmpleados = lstNombreEmpleados;
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public boolean isPanelTipoConsulta() {
        return panelTipoConsulta;
    }

    public void setPanelTipoConsulta(boolean panelTipoConsulta) {
        this.panelTipoConsulta = panelTipoConsulta;
    }
}
