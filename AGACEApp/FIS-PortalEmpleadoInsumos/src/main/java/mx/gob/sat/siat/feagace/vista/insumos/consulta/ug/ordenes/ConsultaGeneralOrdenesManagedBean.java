package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.base.constante.CommonConstants;
import mx.gob.sat.siat.base.dto.UserProfileDTO;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.CifrasOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesRule;
import mx.gob.sat.siat.feagace.negocio.consulta.general.impl.ConsultaGeneralOrdenesServiceImpl;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.impl.ConsultaEjecutivaOrdenesServiceImpl;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.ConsultaGeneralOrdenesHelper;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;

@ManagedBean(name = "consultaUGOrdenesMB")
@ViewScoped
public class ConsultaGeneralOrdenesManagedBean extends ConsultaGeneralOrdenesAbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private static final String ES_REDIRECT = "esRedirect";
    private static final String MAPA_BANDERAS = "mapaBanderas";
    private static final String CONSULTA_ORDENES_HELPER = "consultaOrdenesHelper";

    private static final String SESSION_PE = "accesoEF";

    @PostConstruct
    public void init() {
        try {
            logger.error("ConsultaGeneralOrdenesManagedBean init is null");
            
            UserProfileDTO userProfileDTO = getUserProfileDTO();
            
            if (userProfileDTO == null) {
                logger.error("userProfileDTO is null");                
            }

            if (userProfileDTO != null && userProfileDTO.getRfc() != null) {
                EmpleadoDTO empleadoSession = new EmpleadoDTO();
                empleadoSession.setRfc(userProfileDTO.getRfc());

                if (getConsultaOrdenesHelper() == null) {
                    setConsultaOrdenesHelper(new ConsultaGeneralOrdenesHelper());
                    getConsultaOrdenesHelper().setEsRedirect(false);
                    if (getConsultaGeneralOrdenesService() == null) {
                        setConsultaGeneralOrdenesService(new ConsultaGeneralOrdenesServiceImpl());
                    }
                    getConsultaOrdenesHelper().setConsultaOrdenesBO(getConsultaGeneralOrdenesService().getAccesoSuperusuarioAConsultaOrdenes(empleadoSession));
                }
                if (getSession() != null) {
                    getSession().removeAttribute(getEsRedirect());
                } else {
                    logger.error("Session is null ConsultaGeneralOrdenesManagedBean");
                }
                if (getConsultaEjecutivaOrdenesService() == null) {
                    setConsultaEjecutivaOrdenesService(new ConsultaEjecutivaOrdenesServiceImpl());
                }
            }
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error("Error al cargar valores de inicio ConsultaGeneralOrdenesManagedBean");
            logger.error(ex.getMessage(), ex);
        }
    }

    private UserProfileDTO getUserProfileDTO() {
        try {
            HttpSession session = null;
            AccesoUsr accesoUsr;

            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            UserProfileDTO user = (UserProfileDTO) session
                    .getAttribute(CommonConstants.USER_PROFILE);

            if (user != null && user.getRfc() != null) {
                return user;
            }
            logger.error("El usuario  UserProfileDTO vienen null de EmpleadoSessionFilter");
            logger.info("Se genera nuevo UserProfileDTO ");
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            accesoUsr = (AccesoUsr) session.getAttribute(SESSION_PE);

            logger.info("ConsultaGeneralOrdenesManagedBean init()");

            if (accesoUsr != null && accesoUsr.getUsuario() != null) {
                user = convert(accesoUsr);
                session.setAttribute(CommonConstants.USER_PROFILE, user);
                return user;
            }

            return null;
        } catch (Exception ex) {
            logger.error("Error al obtener UserProfileDTO de session", ex);
            return null;
        }
    }

    private UserProfileDTO convert(AccesoUsr accesoUsr) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsuario(accesoUsr.getUsuario());
        userProfileDTO.setRfc(accesoUsr.getUsuario());
        userProfileDTO.setRfcCorto(accesoUsr.getRfcCorto());
        userProfileDTO.setNombreCompleto(accesoUsr.getNombreCompleto());
        userProfileDTO.setNombreMaquina(accesoUsr.getMac());
        userProfileDTO.setIdEmpleado(new BigDecimal(accesoUsr.getNumeroEmp()));
        userProfileDTO.setIp(accesoUsr.getIp());
        userProfileDTO.setEsContribuyente("1".equals(accesoUsr
                .getIdTipoPersona()));
        Collection<String> roles = new ArrayList<String>();
        if (null != accesoUsr.getRoles()) {
            for (String rol : accesoUsr.getRoles().split(",")) {
                roles.add(rol);
            }
        }
        userProfileDTO.setRolesNovell(roles);
        userProfileDTO.setRoles(roles);
        return userProfileDTO;
    }

    public void consultarXUnidadAdminSeleccionada() {
        setControlDeAcceso(true);
        setUnidadAdminByID(getConsultaOrdenesHelper().getIdUnidadAdminSeleccionada());
        if (getConsultaOrdenesHelper().getUnidadAdminSeleccionada() == null) {
            getConsultaOrdenesHelper().getFiltro().setUnidadAdmtvaDesahogoFiltro(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo());
        } else {
            addUnidadesDesagoAlFiltro(getConsultaOrdenesHelper().getUnidadAdminSeleccionada());
        }
        try {
            getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
            getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(true);
            getConsultaEjecutivaOrdenesService().consultarOrdenes(getConsultaOrdenesHelper().getConsultaOrdenesBO(), getConsultaOrdenesHelper().getFiltro());
            mostrarPanelXMetodo();

        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void prerenderSuperUsuario() {
        try {
            boolean cargaListaOrdenes = false;
            Map<String, Boolean> mapaBanderas = null;
            Enumeration<?> e = getSession().getAttributeNames();
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                if (ES_REDIRECT.equals(name)) {
                    cargaListaOrdenes = (Boolean) getSession().getAttribute(ES_REDIRECT);
                    getSession().removeAttribute(ES_REDIRECT);
                }
                if (MAPA_BANDERAS.equals(name)) {
                    mapaBanderas = (Map<String, Boolean>) getSession().getAttribute(MAPA_BANDERAS);
                    getSession().removeAttribute(MAPA_BANDERAS);
                }
            }
            if (cargaListaOrdenes) {
                ConsultaGeneralOrdenesHelper helperTmp = (ConsultaGeneralOrdenesHelper) getSession().getAttribute(CONSULTA_ORDENES_HELPER);
                getConsultaOrdenesHelper().setPropiedadesHelper(helperTmp);
                getSession().removeAttribute(CONSULTA_ORDENES_HELPER);
                if (mapaBanderas != null) {
                    llenaBanderas(mapaBanderas);
                }
                mostrarPanelOrdenes();
            } else {
                if (!isControlDeAcceso()) {
                    cargarValoresIniciales();
                }
            }
        } catch (Exception e) {
            logger.error("Error prerenderSuperUsuario");
            logger.error(e.getMessage(), e);
        }

    }

    public boolean cargarValoresIniciales() {
        try {
            if (getConsultaOrdenesHelper().getFiltro() == null) {
                getConsultaOrdenesHelper().setFiltro(new FiltroConsultaOrdenes());
                EmpleadoDTO empleadoSession = new EmpleadoDTO();
                empleadoSession.setRfc(getUserProfileDTO().getRfc());
                getConsultaOrdenesHelper().setEsRedirect(false);
                getSession().removeAttribute(getEsRedirect());
                try {
                    getConsultaOrdenesHelper().setConsultaOrdenesBO(getConsultaGeneralOrdenesService().getAccesoSuperusuarioAConsultaOrdenes(empleadoSession));
                } catch (ConsultaEjecutivaOrdenesServiceException e) {
                    logger.error("Error al consultar Usuario:", e.getMessage());
                }
                getConsultaOrdenesHelper().getFiltro().setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getConsultaOrdenesBO().getEmpleadoConsulta());
                getConsultaOrdenesHelper().setFlgMostrarTlbUnidades(true);
                try {
                    setUnidadesAdmon(getEmpleadoService().getUnidadesAdministrativas(empleadoSession));
                    getConsultaOrdenesHelper().getConsultaOrdenesBO().setLstUnidadesAdministrativasDesahogo(getUnidadesAdmon());
                    getConsultaOrdenesHelper().getConsultaOrdenesBO().setRule(ConsultaEjecutivaOrdenesRule.UNIDADES_ADMINISTRATIVAS_DESAHOGO);
                    getConsultaOrdenesHelper().getConsultaOrdenesBO().getRule().process(getConsultaOrdenesHelper().getConsultaOrdenesBO());
                    AraceDTO araceTodas = new AraceDTO();
                    araceTodas.setIdArace(0);
                    araceTodas.setNombre("TODAS");
                    getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo().add(araceTodas);
                    setUnidadesAdmon(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo());
                    setBtnConsulta(true);
                } catch (EmpleadoServiceException e) {
                    logger.error(e.getMessage(), e);
                }
                setControlDeAcceso(true);
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return true;
        }

    }

    public void redirecciona(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

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
        setLstPrioridadSugerida(new ArrayList<String>());
        getLstPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
        mostrarPanelOrdenes();
    }

    public void consultarTotalEstatusFiltro() {
        getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesXFiltro());
        setLstPrioridadSugerida(new ArrayList<String>());
        getLstPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
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
            getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(true);
            try {
                getConsultaEjecutivaOrdenesService()
                        .consultarOrdenes(getConsultaOrdenesHelper().getConsultaOrdenesBO(), getConsultaOrdenesHelper().getFiltro());
                mostrarPanelXMetodo();
            } catch (ConsultaEjecutivaOrdenesServiceException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        // es consulta por semaforo
        if (isPantallaTotalEmpleados() || isConsultaXFirmante()) {
            // si es todas las unidades regresa a la pantalla de semaforo 1 o 2
            if (getConsultaOrdenesHelper().getUnidadAdminSeleccionada() == null) {
                if (isPantallaEstatusFiltrado()) {
                    mostrarPanelXEstatus();
                } else {
                    mostrarPanelXMetodo();
                }
                setPantallaTotalEmpleados(false);
            } else {
                if (isConsultaXFirmante()) {
                    mostrarPanelXSubordinadoAuditor();
                } else {
                    mostrarPanelXSubordinados();
                }
            }
        } else {
            if (isPantallaEstatusFiltrado()) {
                mostrarPanelXEstatus();
            } else {
                mostrarPanelXMetodo();
            }
        }
    }

    public void consultarCifras() {
        setControlDeAcceso(true);
        setConsultaXCifras(true);
        getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(true);
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
            setLstPrioridadSugerida(new ArrayList<String>());
            getLstPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));
            getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
            mostrarPanelOrdenes();
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
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

    public void cambiaVisibilidadBotonConsulta() {
        if (getConsultaOrdenesHelper().getIdUnidadAdminSeleccionada().equals(-1)) {
            setBtnConsulta(true);
        } else {
            setBtnConsulta(false);
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

    public void consultarXEstatus() {
        setPantallaTotalEmpleados(false);
        setPantallaEstatusEmpleados(true);
        setConsultaXSemaforo(false);
        if (getConsultaOrdenesHelper().getUnidadAdminSeleccionada() == null) {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelOrdenes();
        } else {
            consultarFirmantesXCentral();
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

    public void consultarXSemaforo() {
        setPantallaTotalEmpleados(true);
        setPantallaEstatusEmpleados(false);
        getConsultaOrdenesHelper().setGrupoEstatusSeleccionado(null);
        getConsultaOrdenesHelper().getFiltro().setEstatusFiltro(null);
        if (getConsultaOrdenesHelper().getUnidadAdminSeleccionada() == null) {
            consultaAServicioXMetodoOSemaforo();
            mostrarPanelOrdenes();
        } else {
            setConsultaXSemaforo(true);
            consultarFirmantesXCentral();
        }

    }

    public void consultarXEmpleadoSemaforoSeleccionado() {
        setControlDeAcceso(true);
        List<SemaforoEnum> lstSemaforosFiltro = new ArrayList<SemaforoEnum>();
        List<TipoEstatusEnum> estatusFiltro = new ArrayList<TipoEstatusEnum>();
        FiltroConsultaOrdenes filtroConsultaOrdenes = new FiltroConsultaOrdenes();
        try {
            filtroConsultaOrdenes.setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            if (getConsultaOrdenesHelper().getUnidadAdminSeleccionada() != null) {
                filtroConsultaOrdenes.getUnidadAdmtvaDesahogoFiltro().add(getConsultaOrdenesHelper().getUnidadAdminSeleccionada());
            } else {
                filtroConsultaOrdenes.getUnidadAdmtvaDesahogoFiltro().addAll(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo());
            }

            if (getConsultaOrdenesHelper().getSemaforoSeleccionado() != null || getConsultaOrdenesHelper().getSemaforoFiltradoSeleccionado() != null) {
                if (getConsultaOrdenesHelper().getSemaforoSeleccionado() != null) {
                    lstSemaforosFiltro.add(getConsultaOrdenesHelper().getSemaforoSeleccionado().getKey());
                }
                if (getConsultaOrdenesHelper().getSemaforoFiltradoSeleccionado() != null) {
                    lstSemaforosFiltro.add(getConsultaOrdenesHelper().getSemaforoFiltradoSeleccionado().getKey());
                }
            } else {
                for (Map.Entry<SemaforoEnum, Integer> key : getConsultaOrdenesHelper().getConsultaOrdenesBO().getDetalleXSemaforoFiltrado().entrySet()) {
                    lstSemaforosFiltro.add(key.getKey());
                }
            }

            filtroConsultaOrdenes.setMetodoFiltro(new ArrayList<TipoMetodoEnum>());
            if (getConsultaOrdenesHelper().getMetodoSeleccionado() != null) {
                filtroConsultaOrdenes.getMetodoFiltro().add(getConsultaOrdenesHelper().getMetodoSeleccionado().getKey());
            } else {
                filtroConsultaOrdenes.getMetodoFiltro().addAll(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstMetodosValidos());
            }

            if (getConsultaOrdenesHelper().getEmpleadoFiltradoSeleccionado() != null) {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getEmpleadoFiltradoSeleccionado().getKey());
                tipoEmpleadoFiltro(getConsultaOrdenesHelper().getEmpleadoFiltradoSeleccionado().getKey(), filtroConsultaOrdenes);
            } else if (getConsultaOrdenesHelper().getEmpleadoSeleccionado() != null) {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getEmpleadoSeleccionado().getKey());
                tipoEmpleadoFiltro(getConsultaOrdenesHelper().getEmpleadoSeleccionado().getKey(), filtroConsultaOrdenes);
            } else {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(getConsultaOrdenesHelper().getConsultaOrdenesBO().getEmpleadoConsulta());
                if (getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado() != null
                        && (getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId())
                        || getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId()))) {
                    filtroConsultaOrdenes.setConsultaEmpleado(true);
                    filtroConsultaOrdenes.setTipoEmpleadoConsulta(getConsultaOrdenesHelper().getConsultaOrdenesBO().getRolEmpleado());
                } else {
                    filtroConsultaOrdenes.setConsultaEmpleado(false);
                }
            }

            if (getConsultaOrdenesHelper().getGrupoEstatusSeleccionado() != null) {
                AgrupadorEstatusOrdenesEnum agrupadorFiltro = getConsultaOrdenesHelper().getGrupoEstatusSeleccionado().getKey();
                for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : getConsultaOrdenesHelper().getConsultaOrdenesBO()
                        .getGruposDeEstatusValidos().entrySet()) {
                    if (grupoEstatus.getKey().getIdGrupo() == agrupadorFiltro.getIdGrupo()) {
                        List<TipoEstatusEnum> listaEstatus = grupoEstatus.getValue();
                        for (TipoEstatusEnum estatus : listaEstatus) {
                            estatusFiltro.add(estatus);
                        }
                    }
                }
            }
            filtroConsultaOrdenes.setEstatusFiltro(estatusFiltro);

            getConsultaEjecutivaOrdenesService().getOrdenesXEmpleadoSemaforoMetodo(getConsultaOrdenesHelper().getConsultaOrdenesBO(), filtroConsultaOrdenes, lstSemaforosFiltro);

            if (getConsultaOrdenesHelper().getGrupoEstatusSeleccionado() != null
                    && (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == getConsultaOrdenesHelper().getGrupoEstatusSeleccionado().getKey().getIdGrupo() || AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO
                    .getIdGrupo() == getConsultaOrdenesHelper().getGrupoEstatusSeleccionado().getKey().getIdGrupo())) {
                List<AgaceOrden> filtro = new ArrayList<AgaceOrden>();
                for (AgaceOrden orden : getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros()) {
                    if (getConsultaEjecutivaOrdenesService().tieneOficios(orden.getIdOrden())) {
                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO.getIdGrupo() == getConsultaOrdenesHelper().getGrupoEstatusSeleccionado().getKey()
                                .getIdGrupo()) {
                            filtro.add(orden);
                        }
                    } else {
                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == getConsultaOrdenesHelper().getGrupoEstatusSeleccionado().getKey().getIdGrupo()) {
                            filtro.add(orden);
                        }
                    }
                }
                getConsultaOrdenesHelper().getConsultaOrdenesBO().setLstOrdenesMutiplesFiltros(new ArrayList<AgaceOrden>());
                getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros().addAll(filtro);
            }

            getConsultaOrdenesHelper().setLstOrdenesResult(getConsultaOrdenesHelper().getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros());
            setLstPrioridadSugerida(new ArrayList<String>());
            getLstPrioridadSugerida().addAll(buscarPrioridadPropuesta(getConsultaOrdenesHelper().getLstOrdenesResult()));

            if (getConsultaOrdenesHelper().getEmpleadoSeleccionado() == null) {
                mostrarPanelOrdenes();
            } else {
                if (getConsultaOrdenesHelper().isFlgMostrarTlbSubordinadoAuditor()) {
                    mostrarPanelOrdenes();
                } else {
                    mostrarPanelXSubordinadoAuditor();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
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

    public void regresarDeFirmante() {
        setPantallaTotalEmpleados(false);
        if (isPantallaEstatusFiltrado()) {
            getConsultaOrdenesHelper().setSemaforoFiltradoSeleccionado(null);
            mostrarPanelXEstatus();
        } else {
            mostrarPanelXMetodo();
        }
    }

    public void regresarDeAuditor() {
        consultarFirmantesXCentral();
    }

    public void regresarPantallaLstOrdenes() {
        limpiarFiltros();
        getConsultaOrdenesHelper().setOrdenSeleccionada(null);
        mostrarPanelOrdenes();
    }
}
