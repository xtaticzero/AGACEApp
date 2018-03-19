package mx.gob.sat.siat.feagace.vista.ordenes.consulta.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CifrasOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.ordenes.consulta.ConsultaOrdenesAbstractMBean;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

@ViewScoped
@ManagedBean(name = "consultaAdminOrdenesMB")
public class ConsultaAdministrativaOrdenesMBean extends ConsultaOrdenesAbstractMBean {

    private static final long serialVersionUID = 693171635894880550L;
    
    private static final int CONSULTA_TIPO_CENTRAL = 1;
    private static final int CONSULTA_TIPO_FIRMANTE = 7;

    public List<Map.Entry<AgrupadorEstatusOrdenesEnum, Integer>> getDetalleEstatus() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEstatusFiltrado() != null) {
            Set<Map.Entry<AgrupadorEstatusOrdenesEnum, Integer>> estatusSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEstatusFiltrado()
                    .entrySet();
            return new ArrayList<Map.Entry<AgrupadorEstatusOrdenesEnum, Integer>>(estatusSet);
        }
        return new ArrayList<Map.Entry<AgrupadorEstatusOrdenesEnum, Integer>>();
    }

    public List<Map.Entry<TipoMetodoEnum, Integer>> getDetalleMetodo() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXMetodo() != null) {
            Set<Map.Entry<TipoMetodoEnum, Integer>> metodoSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXMetodo().entrySet();
            return new ArrayList<Map.Entry<TipoMetodoEnum, Integer>>(metodoSet);
        }
        return new ArrayList<Map.Entry<TipoMetodoEnum, Integer>>();
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getDetalleSemaforo() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXSemaforo() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> semaforoSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXSemaforo().entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(semaforoSet);
        }
        return new ArrayList<Map.Entry<SemaforoEnum, Integer>>();
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getDetalleSemaforoFiltrado() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXSemaforoFiltrado() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> semaforoFiltradoSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXSemaforoFiltrado()
                    .entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(semaforoFiltradoSet);
        }
        return new ArrayList<Map.Entry<SemaforoEnum, Integer>>();
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getDetalleCategoriaEmpleado() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEmpleado() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> empleadosSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEmpleado().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(empleadosSet);
        }
        return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>();
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getDetalleEmpleadoFiltrado() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEmpleadoFiltrado() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> empleadosSet = getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXEmpleadoFiltrado().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(empleadosSet);
        }
        return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>();
    }

    public void consultarTotalGeneral() {
        getConsultaOrdenesHelper().setFlgPantallaGrupoEstatus(true);
        getConsultaOrdenesHelper().setFlgPaginaEstatusSemaforos(!isPantallaEstatusFiltrado());
        getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenResult());
        setListaPrioridadSugerida(new ArrayList<String>());
        getListaPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
        mostrarPanelOrdenes();
    }

    public void consultarTotalEstatusFiltro() {
        getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesXFiltro());
        setListaPrioridadSugerida(new ArrayList<String>());
        getListaPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
        mostrarPanelOrdenes();
    }

    public void cargaDocumentosOrdenOficio() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosFirmadosPdf').show();");
    }

    public boolean getEsORG() {
        return (getConsultaOrdenesHelper().getPropuestaSeleccionada() != null)
                && (getConsultaOrdenesHelper().getPropuestaSeleccionada().getIdMetodo().longValue() == (TipoMetodoEnum.ORG.getId()));
    }

    public void regresarDeDetalleOrdenes() {
        getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
        limpiarFiltros();
        if (isConsultaXCifras()) {
            setConsultaXCifras(false);
            getConsultaOrdenesHelper().setCifraDesde(null);
            getConsultaOrdenesHelper().setCifraHasta(null);
            getConsultaOrdenesHelper().setCifraSeleccionada(null);
            getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
            getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(false);
            try {
                getConsultaEjecutivaOrdenesService()
                        .consultarOrdenes(getConsultaOrdenesHelper().getConsultaOrdenesBO(), getConsultaOrdenesHelper().getFiltro());
                mostrarPanelXCategorias();
            } catch (ConsultaEjecutivaOrdenesServiceException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // es consulta por semaforo
        if (isPantallaTotalEmpleados() || isConsultaXFirmante()) {
            // si es acppce regresa a la pantalla de semaforo 1 o 2
            if (getConsultaOrdenesHelper().getConsultaOrdenesBO().isIsAcppce()) {
                if (isPantallaEstatusFiltrado()) {
                    mostrarPanelXEstatus();
                } else {
                    mostrarPanelXCategorias();
                }
                setPantallaTotalEmpleados(false);
            } else {
                if (TipoEmpleadoEnum.CENTRAL.getBigId().equals(getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId())) {
                    if (isConsultaXFirmante()) {
                        mostrarPanelXSubordinadoAuditor();
                    } else {
                        mostrarPanelXSubordinados();
                    }
                } else if (TipoEmpleadoEnum.FIRMANTE.getBigId().equals(getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId())) {
                    mostrarPanelXSubordinadoAuditor();
                } else {
                    // si es auditor regresa a la pantalla de semaforo 1 o 2
                    if (isPantallaEstatusFiltrado()) {
                        mostrarPanelXEstatus();
                    } else {
                        mostrarPanelXCategorias();
                    }
                    setPantallaTotalEmpleados(false);
                }
            }
            return;
        } else {
            if (isPantallaEstatusFiltrado()) {
                mostrarPanelXEstatus();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void regresarDeAuditor() {
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().isIsAcppce()) {
            if (isPantallaEstatusFiltrado()) {
                mostrarPanelXEstatus();
            } else {
                mostrarPanelXCategorias();
            }
        } else {
            // si es firmante regresa a semaforos
            if (TipoEmpleadoEnum.FIRMANTE.getBigId().equals(getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId())) {
                if (isPantallaTotalEmpleados()) {
                    setPantallaTotalEmpleados(false);
                }
                setConsultaXFirmante(false);
                if (isPantallaEstatusFiltrado()) {
                    mostrarPanelXEstatus();
                } else {
                    mostrarPanelXCategorias();
                }
            } else if (TipoEmpleadoEnum.CENTRAL.getBigId().equals(getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId())) {
                // si es central regresa a la pantalla de firmantes
                consultarFirmantesXCentral();
            }
        }
    }

    public void regresarDeFirmante() {
        setPantallaTotalEmpleados(false);
        if (isPantallaEstatusFiltrado()) {
            getConsultaOrdenesHelper().setSemaforoFiltradoSeleccionado(null);
            mostrarPanelXEstatus();
        } else {
            mostrarPanelXCategorias();
        }
    }

    public void regresarPantallaLstOrdenes() {
        limpiarFiltros();
        getConsultaOrdenesHelper().setOrdenSeleccionada(null);
        mostrarPanelOrdenes();
    }

    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
                    .findComponent(":formConsultaEjecutivaOrdenes:tablaDetalleOrdenes");
            dataTable.reset();
            if (!dataTable.getFilters().isEmpty()) {
                logger.info("dataTable.getFilters().isEmpty() :" + dataTable.getFilters().isEmpty());
                dataTable.getFilteredValue().clear();
                dataTable.setFilteredValue(null);
                dataTable.setFilters(null);
                dataTable.setFilterMetadata(null);
                dataTable.reset();
            }
        } catch (Exception e) {
            logger.error("no pudo limpiar" + e.getMessage());
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.reset(":formConsultaEjecutivaOrdenes:tablaDetalleOrdenes");
        requestContext.update(":formConsultaEjecutivaOrdenes");
    }

    public List<CifrasOrdenesEnum> getLstTipoCifras() {
        List<CifrasOrdenesEnum> lstCifras = new ArrayList<CifrasOrdenesEnum>();
        for (CifrasOrdenesEnum cifra : CifrasOrdenesEnum.values()) {
            lstCifras.add(cifra);
        }
        return lstCifras;
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
        String urlRegreso = "/faces/consulta/";
        TipoEmpleadoEnum tipo = getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado();
        urlRegreso = urlRegreso + getUrlAndSetAttribute(tipo, getConsultaOrdenesHelper().getConsultaOrdenesBO(), null, false);
        getSession().setAttribute("urlRegreso", urlRegreso);

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }
    
    public void consultarCifras() {
        setControlDeAcceso(true);
        setConsultaXCifras(true);
        getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(false);
        getConsultaOrdenesHelper().getFiltro().setConsultaCifras(true);
        List<CifrasOrdenesEnum> listaCifras = new ArrayList<CifrasOrdenesEnum>();
        if (getConsultaOrdenesHelper().getCifraSeleccionada() != null) {
            listaCifras.add(getConsultaOrdenesHelper().getCifraSeleccionada());
            getConsultaOrdenesHelper().getFiltro().setCifraFiltro(listaCifras);
        } else {
            for (CifrasOrdenesEnum cifra : CifrasOrdenesEnum.values()) {
                listaCifras.add(cifra);
            }
            getConsultaOrdenesHelper().getFiltro().setCifraFiltro(listaCifras);
        }
        if (getConsultaOrdenesHelper().getCifraDesde() != null) {
            getConsultaOrdenesHelper().getFiltro().setCifraDe(getConsultaOrdenesHelper().getCifraDesde());
        } else {
            getConsultaOrdenesHelper().getFiltro().setCifraDe(Constantes.BIG_DECIMAL_CERO);
        }
        if (getConsultaOrdenesHelper().getCifraHasta() != null) {
            if (getConsultaOrdenesHelper().getCifraHasta().intValue() == 0) {
                getConsultaOrdenesHelper().getFiltro().setCifraHasta(null);
            } else {
                getConsultaOrdenesHelper().getFiltro().setCifraHasta(getConsultaOrdenesHelper().getCifraHasta());
            }
        } else {
            getConsultaOrdenesHelper().getFiltro().setCifraHasta(null);
        }
        try {
            getConsultaEjecutivaOrdenesService().consultarOrdenes(getConsultaOrdenesHelper().getConsultaOrdenesBO(), getConsultaOrdenesHelper().getFiltro());
            getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenResult());
            setListaPrioridadSugerida(new ArrayList<String>());
            getListaPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
            getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
            mostrarPanelOrdenes();
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    public void regresarSubordinadosSemaforo() {
        if (isPantallaEstatusFiltrado()) {
            mostrarPanelXEstatus();
        } else {
            mostrarPanelXCategorias();
        }
    }
    
    public void consultarXEstatus() {
        setPantallaTotalEmpleados(false);
        setPantallaEstatusEmpleados(true);
        setConsultaXSemaforo(false);
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().isIsAcppce()) {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelOrdenes();
        } else {
            int tipoEmpleado = (int) getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getId();
            switch (tipoEmpleado) {
            case CONSULTA_TIPO_CENTRAL:
                consultarFirmantesXCentral();
                break;
            case CONSULTA_TIPO_FIRMANTE:
                consultarXFirmante();
                break;
            default:
                consultaAServicioXMetodoOSemaforo();
                mostrarPanelOrdenes();
                break;
            }
        }
    }
    
    public void consultarOrdenesXMetodo() {
        setControlDeAcceso(true);
        setConsultaXMetodo(false);
        setConsultaXStatus(true);
        setConsultaXSemaforo(false);
        setConsultaXFirmante(false);
        setPantallaTotalEmpleados(false);
        try {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelXEstatus();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    public void consultarXSemaforo() {
        setPantallaTotalEmpleados(true);
        setPantallaEstatusEmpleados(false);
        getConsultaOrdenesHelper().setGrupoEstatusSeleccionado(null);
        getConsultaOrdenesHelper().getFiltro().setEstatusFiltro(null);
        if (getConsultaOrdenesHelper().getConsultaOrdenesBO().isIsAcppce()) {
            if (!isConsultaXStatus()) {
                // si es primer panel de semaforos llenar la lista filtrada por
                // semaforo
                consultaAServicioXMetodoOSemaforo();
                mostrarPanelOrdenes();
            } else { 
                // si es el segundo panel de semaforos
                consultarXEmpleadoSemaforoSeleccionado();
            }
        } else {
            int tipoEmpleado = (int) getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getId();
            switch (tipoEmpleado) {
            case CONSULTA_TIPO_CENTRAL:
                setConsultaXSemaforo(true);
                consultarFirmantesXCentral();
                break;
            case CONSULTA_TIPO_FIRMANTE:
                consultarXFirmante();
                break;
            default:
                if (!isConsultaXStatus()) { 
                    // si es primer panel de semaforos
                    consultaAServicioXMetodoOSemaforo();
                    mostrarPanelOrdenes();
                } else {
                    // si es el segundo panel de semaforos
                    consultarXEmpleadoSemaforoSeleccionado();
                }
                break;
            }
        }
    }
    
    public void consultarXFirmante() {
        setControlDeAcceso(true);
        setConsultaXMetodo(false);
        setConsultaXStatus(false);
        setConsultaXSemaforo(false);
        setConsultaXFirmante(true);
        try {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelXSubordinadoAuditor();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarFirmantesXCentral() {
        setControlDeAcceso(true);
        setConsultaXMetodo(false);
        setConsultaXStatus(false);
        setConsultaXFirmante(false);
        getConsultaOrdenesHelper().setEmpleadoSeleccionado(null);
        getConsultaOrdenesHelper().getFiltro().setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getConsultaOrdenesBO().getEmpleadoConsulta());
        try {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelXSubordinados();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}