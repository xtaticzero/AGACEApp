package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.consulta.general.ConsultaGeneralOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.ConsultaEjecutivaOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.ConsultaGeneralOrdenesHelper;
import mx.gob.sat.siat.feagace.vista.model.ordenes.OrdenConsultaDTO;

public class ConsultaGeneralOrdenesAbstractManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = -6177985043595942182L;

    private static final String ES_REDIRECT = "esRedirect";
    private static final String URL_CONSULTA_SUPERUSUARIO = "/faces/consultaGeneral/ordenes/consultaXEstatus.xhtml?faces-redirect=true";

    @ManagedProperty(value = "#{consultaEjecutivaOrdenesService}")
    private transient ConsultaEjecutivaOrdenesService consultaEjecutivaOrdenesService;

    @ManagedProperty(value = "#{consultaGeneralOrdenesService}")
    private transient ConsultaGeneralOrdenesService consultaGeneralOrdenesService;

    private ConsultaGeneralOrdenesHelper consultaOrdenesHelper;

    private List<AraceDTO> unidadesAdmon;
    private List<String> lstPrioridadSugerida;

    private boolean controlDeAcceso;

    private boolean pantallaEstatusFiltrado;
    private boolean pantallaTotalEmpleados;
    private boolean pantallaEstatusEmpleados;
    private boolean consultaXMetodo;
    private boolean consultaXStatus;
    private boolean consultaXSemaforo;
    private boolean consultaXFirmante;
    private boolean consultaXCifras;

    private boolean btnConsulta;
    
    private StreamedContent xlsReporte;

    public ConsultaGeneralOrdenesAbstractManagedBean() {
        consultaOrdenesHelper = new ConsultaGeneralOrdenesHelper();
        controlDeAcceso = false;
        pantallaEstatusFiltrado = false;
        pantallaTotalEmpleados = false;
        pantallaEstatusEmpleados = false;
    }

    public void setUnidadAdminByID(Integer idUnidadAdmin) {
        if (idUnidadAdmin != null && idUnidadAdmin > 0) {
            for (AraceDTO unidad : consultaOrdenesHelper.getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo()) {
                if (idUnidadAdmin.intValue() == unidad.getIdArace()) {
                    consultaOrdenesHelper.setUnidadAdminSeleccionada(unidad);
                    break;
                }
            }
        } else {
            consultaOrdenesHelper.setUnidadAdminSeleccionada(null);
        }
    }

    public void addUnidadesDesagoAlFiltro(AraceDTO... unidadesDesahogo) {
        if (consultaOrdenesHelper.getFiltro() != null && unidadesDesahogo != null) {
            consultaOrdenesHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            consultaOrdenesHelper.getFiltro().getUnidadAdmtvaDesahogoFiltro().addAll(Arrays.asList(unidadesDesahogo));
        }
    }

    public void llenaBanderas(Map<String, Boolean> mapaBanderas) {
        if (mapaBanderas != null) {
            controlDeAcceso = mapaBanderas.get("controlDeAcceso");
            pantallaEstatusFiltrado = mapaBanderas.get("pantallaEstatusFiltrado");
            pantallaTotalEmpleados = mapaBanderas.get("pantallaTotalEmpleados");
            pantallaEstatusEmpleados = mapaBanderas.get("pantallaEstatusEmpleados");
            consultaXMetodo = mapaBanderas.get("consultaXMetodo");
            consultaXStatus = mapaBanderas.get("consultaXStatus");
            consultaXSemaforo = mapaBanderas.get("consultaXSemaforo");
            consultaXFirmante = mapaBanderas.get("consultaXFirmante");
            consultaXCifras = mapaBanderas.get("consultaXCifras");
        }
    }
    
    public void consultaAServicioXMetodoOSemaforo() {
        try {
            boolean flgMetodo = getConsultaOrdenesHelper().getMetodoSeleccionado() != null;
            boolean flgSemaforo = getConsultaOrdenesHelper().getSemaforoSeleccionado() != null;
            boolean flgSemaforoFiltrado = getConsultaOrdenesHelper().getSemaforoFiltradoSeleccionado() != null;
            boolean flgEmpleado = getConsultaOrdenesHelper().getEmpleadoSeleccionado() != null;
            boolean flgEstatus = getConsultaOrdenesHelper().getGrupoEstatusSeleccionado() != null;

            if (flgEmpleado) {
                getConsultaOrdenesHelper().getFiltro().setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getEmpleadoSeleccionado().getKey());
            }
            getConsultaEjecutivaOrdenesService().getOrdenesXSemaforoMetodo(getConsultaOrdenesHelper().getConsultaOrdenesBO(), flgSemaforo ? getConsultaOrdenesHelper()
                    .getSemaforoSeleccionado().getKey() : null, flgSemaforoFiltrado ? getConsultaOrdenesHelper().getSemaforoFiltradoSeleccionado().getKey()
                            : null, flgMetodo ? getConsultaOrdenesHelper().getMetodoSeleccionado().getKey() : null, flgEstatus ? getConsultaOrdenesHelper()
                            .getGrupoEstatusSeleccionado().getKey() : null, getConsultaOrdenesHelper().getFiltro().getFiltroDAO(), flgEmpleado);
            
            getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesXFiltro());
            setLstPrioridadSugerida(new ArrayList<String>());
            getLstPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    public List<String> buscarPrioridadPropuesta(List<AgaceOrden> propuestasOrdenes) {
        List<String> listaPrioridad = new ArrayList<String>();
        Set<String> lstPrioridadTmp = new HashSet<String>();

        if (propuestasOrdenes != null && !propuestasOrdenes.isEmpty()) {
            for (AgaceOrden propuestaOrden : propuestasOrdenes) {
                if (!lstPrioridadTmp.contains(propuestaOrden.getPrioridadSugerida())) {
                    lstPrioridadTmp.add(propuestaOrden.getPrioridadSugerida());
                }
            }
            listaPrioridad.addAll(lstPrioridadTmp);
            Collections.sort(listaPrioridad);
        }
        return listaPrioridad;
    } 

    public void consultarOrdenes() {
        getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(true);
        getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
        try {
            consultaEjecutivaOrdenesService.consultarOrdenes(consultaOrdenesHelper.getConsultaOrdenesBO(), consultaOrdenesHelper.getFiltro());
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void mostrarPanelXMetodo() {
        setConsultaXMetodo(true);
        setConsultaXStatus(false);
        setConsultaXSemaforo(false);
        setConsultaXFirmante(false);
        setPantallaEstatusFiltrado(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbUnidades(false);
        getConsultaOrdenesHelper().setFlgRegresarASubordinado(false);
        getConsultaOrdenesHelper().setGrupoEstatusSeleccionado(null);
        getConsultaOrdenesHelper().setEmpleadoSeleccionado(null);
        getConsultaOrdenesHelper().setMetodoSeleccionado(null);
        getConsultaOrdenesHelper().setCifraSeleccionada(null);
        getConsultaOrdenesHelper().setSemaforoSeleccionado(null);
        getConsultaOrdenesHelper().setSemaforoFiltradoSeleccionado(null);
        getConsultaOrdenesHelper().setFlgMostrarTlbCategorias(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinados(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinadoAuditor(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbOrdenes(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbMetodo(true);
        getConsultaOrdenesHelper().setFlgMostrarTlbEstatus(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbCifras(false);
        getConsultaOrdenesHelper().setFlgMostrarDetallePropuesta(false);
        getConsultaOrdenesHelper().setFlgMostrarDetalleOrden(false);
        getConsultaOrdenesHelper().setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelXEstatus() {
        setPantallaEstatusFiltrado(true);
        getConsultaOrdenesHelper().setGrupoEstatusSeleccionado(null);
        getConsultaOrdenesHelper().setEmpleadoSeleccionado(null);
        getConsultaOrdenesHelper().setCifraSeleccionada(null);
        consultaAServicioXMetodoOSemaforo();
        getConsultaOrdenesHelper().setFlgMostrarTlbCategorias(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinados(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinadoAuditor(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbOrdenes(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbMetodo(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbEstatus(true);
        getConsultaOrdenesHelper().setFlgMostrarTlbCifras(false);
        getConsultaOrdenesHelper().setFlgMostrarDetallePropuesta(false);
        getConsultaOrdenesHelper().setFlgMostrarDetalleOrden(false);
        getConsultaOrdenesHelper().setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelOrdenes() {
        getConsultaOrdenesHelper().ordenarListaResult(getConsultaOrdenesHelper().getLstOrdenesResult(), logger);
        getConsultaOrdenesHelper().setOrdenSeleccionada(null);
        getConsultaOrdenesHelper().setCifraSeleccionada(null);
        getConsultaOrdenesHelper().setEmpleadoFiltradoSeleccionado(null);
        getConsultaOrdenesHelper().setFlgMostrarTlbCategorias(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinados(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbSubordinadoAuditor(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbOrdenes(true);
        getConsultaOrdenesHelper().setFlgMostrarTlbMetodo(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbEstatus(false);
        getConsultaOrdenesHelper().setFlgMostrarTlbCifras(false);
        getConsultaOrdenesHelper().setFlgMostrarDetallePropuesta(false);
        getConsultaOrdenesHelper().setFlgMostrarDetalleOrden(false);
        getConsultaOrdenesHelper().setFlgMostrarEstatusSemaforo(false);
    }

    public void mostrarPanelXSubordinadoAuditor() {
        consultaOrdenesHelper.setFlgRegresarASubordinado(true);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(true);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelXSubordinados() {
        consultaOrdenesHelper.setFlgRegresarASubordinado(true);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(true);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelDetallePropuesta() {
        consultaOrdenesHelper.setPropuestaSeleccionada(null);
        consultaOrdenesHelper.setPropuestaSeleccionada(consultaEjecutivaOrdenesService.consultaPropuestaDetalle(getConsultaOrdenesHelper()
                .getIdRegistroPropuestaSeleccionada()));
        FececUnidadAdministrativa unidad = new FececUnidadAdministrativa();
        for (AraceDTO arace : unidadesAdmon) {
            if (consultaOrdenesHelper.getPropuestaSeleccionada().getIdArace().intValue() == arace.getIdArace().intValue()) {
                unidad.setNombre(arace.getNombre());
                unidad.setIdUnidadAdministrativa(new BigDecimal(arace.getIdArace()));
                consultaOrdenesHelper.getPropuestaSeleccionada().setUnidadAdministrativa(unidad);
            }
        }
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(true);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(false);
    }
    
    public void mostrarPanelUnidadesDesahogo() {
        consultaOrdenesHelper.setEmpleadoSeleccionado(null);
        consultaOrdenesHelper.setGrupoEstatusSeleccionado(null);
        consultaOrdenesHelper.setUnidadAdminSeleccionada(null);
        consultaOrdenesHelper.setIdUnidadAdminSeleccionada(null);
        getConsultaOrdenesHelper().setFlgMostrarTlbUnidades(true);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
    }

    public void tipoEmpleadoFiltro(EmpleadoDTO empleado, FiltroConsultaOrdenes filtroConsultaOrdenes) {
        for (DetalleEmpleadoDTO lstDetalle : empleado.getDetalleEmpleado()) {
            if (lstDetalle.getTipoEmpleado() != null && (lstDetalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId())
                    || lstDetalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId()))) {
                filtroConsultaOrdenes.setConsultaEmpleado(true);
                filtroConsultaOrdenes.setTipoEmpleadoConsulta(lstDetalle.getTipoEmpleado());
                return;
            }

        }
        filtroConsultaOrdenes.setConsultaEmpleado(false);
    }
    
    public void redirecciona() throws IOException {
        OrdenConsultaDTO ordenConsultaDTO = getConsultaOrdenesHelper().convertToOrdenConsulta(getConsultaOrdenesHelper().getOrdenSeleccionada());
        getSession().setAttribute("origenConsulta", "consultaInfoOrdenes");
        getSession().setAttribute("ordenConsultaSeleccionada", ordenConsultaDTO);
        getSession().setAttribute("userProfile", super.getUserProfile());

        getSession().setAttribute("consultaOrdenesHelper", getConsultaOrdenesHelper());
        Map<String, Boolean> mapaBanderas = new HashMap<String, Boolean>();
        mapaBanderas.put("controlDeAcceso", isControlDeAcceso());
        mapaBanderas.put("pantallaEstatusFiltrado", isPantallaEstatusFiltrado());
        mapaBanderas.put("pantallaTotalEmpleados", isPantallaTotalEmpleados());
        mapaBanderas.put("pantallaEstatusEmpleados", isPantallaEstatusEmpleados());
        mapaBanderas.put("consultaXMetodo", isConsultaXMetodo());
        mapaBanderas.put("consultaXStatus", isConsultaXStatus());
        mapaBanderas.put("consultaXSemaforo", isConsultaXSemaforo());
        mapaBanderas.put("consultaXFirmante", isConsultaXFirmante());
        mapaBanderas.put("consultaXCifras", isConsultaXCifras());
        getSession().setAttribute("mapaBanderas", mapaBanderas);

        getSession().setAttribute("esRedirect", true);
        getSession().setAttribute("urlRegreso", URL_CONSULTA_SUPERUSUARIO);

        String urlRedireccion = "/faces/consultaGeneral/ordenes/detalleOrden/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public List<AraceDTO> getUnidadesAdmon() {
        return unidadesAdmon;
    }

    public void setUnidadesAdmon(List<AraceDTO> unidadesAdmon) {
        this.unidadesAdmon = unidadesAdmon;
    }

    public boolean isControlDeAcceso() {
        return controlDeAcceso;
    }

    public void setControlDeAcceso(boolean controlDeAcceso) {
        this.controlDeAcceso = controlDeAcceso;
    }

    public ConsultaGeneralOrdenesHelper getConsultaOrdenesHelper() {
        return consultaOrdenesHelper;
    }

    public void setConsultaOrdenesHelper(ConsultaGeneralOrdenesHelper consultaOrdenesHelper) {
        this.consultaOrdenesHelper = consultaOrdenesHelper;
    }

    public ConsultaEjecutivaOrdenesService getConsultaEjecutivaOrdenesService() {
        return consultaEjecutivaOrdenesService;
    }

    public void setConsultaEjecutivaOrdenesService(ConsultaEjecutivaOrdenesService consultaEjecutivaOrdenesService) {
        this.consultaEjecutivaOrdenesService = consultaEjecutivaOrdenesService;
    }

    public boolean isPantallaEstatusFiltrado() {
        return pantallaEstatusFiltrado;
    }

    public void setPantallaEstatusFiltrado(boolean pantallaEstatusFiltrado) {
        this.pantallaEstatusFiltrado = pantallaEstatusFiltrado;
    }

    public boolean isPantallaTotalEmpleados() {
        return pantallaTotalEmpleados;
    }

    public void setPantallaTotalEmpleados(boolean pantallaTotalEmpleados) {
        this.pantallaTotalEmpleados = pantallaTotalEmpleados;
    }

    public boolean isPantallaEstatusEmpleados() {
        return pantallaEstatusEmpleados;
    }

    public void setPantallaEstatusEmpleados(boolean pantallaEstatusEmpleados) {
        this.pantallaEstatusEmpleados = pantallaEstatusEmpleados;
    }

    public boolean isConsultaXMetodo() {
        return consultaXMetodo;
    }

    public void setConsultaXMetodo(boolean consultaXMetodo) {
        this.consultaXMetodo = consultaXMetodo;
    }

    public boolean isConsultaXStatus() {
        return consultaXStatus;
    }

    public void setConsultaXStatus(boolean consultaXStatus) {
        this.consultaXStatus = consultaXStatus;
    }

    public boolean isConsultaXSemaforo() {
        return consultaXSemaforo;
    }

    public void setConsultaXSemaforo(boolean consultaXSemaforo) {
        this.consultaXSemaforo = consultaXSemaforo;
    }

    public boolean isConsultaXFirmante() {
        return consultaXFirmante;
    }

    public void setConsultaXFirmante(boolean consultaXFirmante) {
        this.consultaXFirmante = consultaXFirmante;
    }

    public boolean isConsultaXCifras() {
        return consultaXCifras;
    }

    public void setConsultaXCifras(boolean consultaXCifras) {
        this.consultaXCifras = consultaXCifras;
    }

    public static String getEsRedirect() {
        return ES_REDIRECT;
    }

    public ConsultaGeneralOrdenesService getConsultaGeneralOrdenesService() {
        return consultaGeneralOrdenesService;
    }

    public void setConsultaGeneralOrdenesService(ConsultaGeneralOrdenesService consultaGeneralOrdenesService) {
        this.consultaGeneralOrdenesService = consultaGeneralOrdenesService;
    }

    public boolean isBtnConsulta() {
        return btnConsulta;
    }

    public void setBtnConsulta(boolean btnConsulta) {
        this.btnConsulta = btnConsulta;
    }
    
    public StreamedContent getXlsReporte() {
        File file = null;
        HSSFWorkbook workbook = getConsultaEjecutivaOrdenesService().creaExcel(getConsultaOrdenesHelper().getLstOrdenesResult());

        FileOutputStream out = null;

        try {
            file = File.createTempFile("reporteGerencial", "xls");
            out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            setXlsReporte(new DefaultStreamedContent(new FileInputStream(file), "application/xls", "reporte.xls"));
            file.deleteOnExit();
        } catch (IOException e) {
            logger.error("Error: ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("No se pudo limpiar la memoria", e);
                }
            }
        }        
        return xlsReporte;
    }

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }

    public List<String> getLstPrioridadSugerida() {
        return lstPrioridadSugerida;
    }

    public void setLstPrioridadSugerida(List<String> lstPrioridadSugerida) {
        this.lstPrioridadSugerida = lstPrioridadSugerida;
    }

}
