package mx.gob.sat.siat.feagace.vista.ordenes.consulta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.ConsultaEjecutivaOrdenesService;
import mx.gob.sat.siat.feagace.negocio.consulta.ordenes.filtro.FiltroConsultaOrdenes;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaOrdenesServiceException;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.ordenes.consulta.helper.ConsultaOrdenesHelper;
import mx.gob.sat.siat.feagace.vista.util.SessionAttributeOrdenesEnum;
import mx.gob.sat.siat.feagace.vista.util.URLsPortalOrdenes;

public abstract class ConsultaOrdenesAbstractMBean extends BaseManagedBean {


    private static final long serialVersionUID = -3779510681629580845L;
    private static final String ES_REDIRECT = "esRedirect";
    private static final String MAPA_BANDERAS = "mapaBanderas";
    private static final String CONSULTA_ORDENES_HELPER = "consultaOrdenesHelper";

    private boolean controlDeAcceso;

    private boolean pantallaEstatusFiltrado;
    private boolean pantallaTotalEmpleados;
    private boolean pantallaEstatusEmpleados;
    private boolean consultaXMetodo;
    private boolean consultaXStatus;
    private boolean consultaXSemaforo;
    private boolean consultaXFirmante;
    private boolean consultaXCifras;

    private ConsultaOrdenesHelper consultaOrdenesHelper;

    @ManagedProperty(value = "#{consultaEjecutivaOrdenesService}")
    private transient ConsultaEjecutivaOrdenesService consultaEjecutivaOrdenesService;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    // Url relativa
    protected static final String URL_RELATIVA_ORDENES = "../";
    // url's de cada perfil
    protected static final String URL_CENTRAL = "central/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_ADMINISTRADOR = "administrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_SUBADMINISTRADOR = "subadministrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_FIRMANTE = "firmante/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_AUDITOR = "auditor/consultaXEstatus.xhtml?faces-redirect=true";
    // nombres para los objetos de negocio de cada perfil que se almacenan en
    // sesión
    protected static final String BO_CENTRAL = "centralBO";
    protected static final String BO_ADMINISTRADOR = "administradorBO";
    protected static final String BO_SUBADMINISTRADOR = "subadministradorBO";
    protected static final String BO_FIRMANTE = "firmanteBO";
    protected static final String BO_AUDITOR = "auditorBO";
    // nombres para los filtros de búsqueda de cada perfil que se almacenan en
    // sesión
    protected static final String FILTRO_CONSULTA_CENTRAL = "filtroCentral";
    protected static final String FILTRO_CONSULTA_ADMINISTRADOR = "filtroAdministrador";
    protected static final String FILTRO_CONSULTA_SUBADMINISTRADOR = "filtroSubadministrador";
    protected static final String FILTRO_CONSULTA_FIRMANTE = "filtroFirmante";
    protected static final String FILTRO_CONSULTA_AUDITOR = "filtroAuditor";

    protected static final String ERROR_SIN_SESSION = "Error inicio de sesión, no se firmó correctamente…";

    // helper de session origen
    protected static final String HELPER_ORIGEN = "helperOrigen";
    
    private StreamedContent xlsReporte;
    
    private List<String> listaPrioridadSugerida;

    public ConsultaOrdenesAbstractMBean() {
        consultaOrdenesHelper = new ConsultaOrdenesHelper();
        controlDeAcceso = false;
        pantallaEstatusFiltrado = false;
        pantallaTotalEmpleados = false;
        pantallaEstatusEmpleados = false;
    }

    public void prerender() {
        try {
            String url;
            if (getRFCSession() != null) {
                addMessage(getRFCSession());
                EmpleadoDTO empleadoSession = new EmpleadoDTO();
                empleadoSession.setRfc(getRFCSession());
                consultaOrdenesHelper.setEsRedirect(false);
                getSession().removeAttribute(ES_REDIRECT);
                consultaOrdenesHelper.setConsultaOrdenesBO(consultaEjecutivaOrdenesService.getAccesoEmpleadoAConsultaOrdenes(empleadoSession));
                if (consultaOrdenesHelper.getConsultaOrdenesBO() != null) {
                    url = "";
                    TipoEmpleadoEnum tipo = consultaOrdenesHelper.getConsultaOrdenesBO().getRolEmpleado();
                    url = getUrlAndSetAttribute(tipo, consultaOrdenesHelper.getConsultaOrdenesBO(), null, false);
                    if (!url.isEmpty()) {
                        irAPantallaConsultaXRolPropuestas(url);
                    }
                }
            }
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        }
    }

    public String getUrlAndSetAttribute(TipoEmpleadoEnum tipoEmpleado, ConsultaEjecutivaOrdenesBO consultaBO, FiltroConsultaOrdenes filtro,
            boolean subordinadoUrl) {
        String url = "";
        String urlRelativa = URL_RELATIVA_ORDENES;
        if (TipoEmpleadoEnum.CENTRAL.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(BO_ADMINISTRADOR, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_ADMINISTRADOR, filtro);
                }
                return urlRelativa.concat(URL_ADMINISTRADOR);
            } else {
                url = URL_CENTRAL;
                getSession().setAttribute(BO_CENTRAL, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_CENTRAL, filtro);
                }
            }
        }
        if (TipoEmpleadoEnum.FIRMANTE.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(BO_AUDITOR, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_AUDITOR, filtro);
                }
                return urlRelativa.concat(URL_AUDITOR);
            } else {
                url = URL_FIRMANTE;
                getSession().setAttribute(BO_FIRMANTE, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_FIRMANTE, filtro);
                }
            }
        }
        if (TipoEmpleadoEnum.AUDITOR.equals(tipoEmpleado)) {
            url = URL_AUDITOR;
            getSession().setAttribute(BO_AUDITOR, consultaBO);
            if (filtro != null) {
                getSession().setAttribute(FILTRO_CONSULTA_AUDITOR, filtro);
            }
        }

        return url;

    }

    public void mostrarPanelXCategorias() {
        consultaXMetodo = true;
        consultaXStatus = false;
        consultaXSemaforo = false;
        consultaXFirmante = false;
        pantallaEstatusFiltrado = false;
        consultaOrdenesHelper.setFlgRegresarASubordinado(false);
        consultaOrdenesHelper.setGrupoEstatusSeleccionado(null);
        consultaOrdenesHelper.setEmpleadoSeleccionado(null);
        consultaOrdenesHelper.setMetodoSeleccionado(null);
        consultaOrdenesHelper.setCifraSeleccionada(null);
        consultaOrdenesHelper.setSemaforoSeleccionado(null);
        consultaOrdenesHelper.setSemaforoFiltradoSeleccionado(null);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(true);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelXEstatus() {
        pantallaEstatusFiltrado = true;
        consultaOrdenesHelper.setGrupoEstatusSeleccionado(null);
        consultaOrdenesHelper.setEmpleadoSeleccionado(null);
        consultaOrdenesHelper.setCifraSeleccionada(null);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(true);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(true);
    }

    public void mostrarPanelXSubordinados() {
        consultaOrdenesHelper.setFlgRegresarASubordinado(true);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
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

    public void mostrarPanelXSubordinadoAuditor() {
        consultaOrdenesHelper.setFlgRegresarASubordinado(true);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
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

    public void mostrarPanelUnidadesDesahogo() {
        consultaOrdenesHelper.setEmpleadoSeleccionado(null);
        consultaOrdenesHelper.setGrupoEstatusSeleccionado(null);
        consultaOrdenesHelper.setUnidadAdminSeleccionada(null);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(true);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(false);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
    }

    public void mostrarPanelOrdenes() {
        ordenarListaResult();
        consultaOrdenesHelper.setOrdenSeleccionada(null);
        consultaOrdenesHelper.setCifraSeleccionada(null);
        consultaOrdenesHelper.setEmpleadoFiltradoSeleccionado(null);
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaOrdenesHelper.setFlgMostrarTlbCategorias(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinados(false);
        consultaOrdenesHelper.setFlgMostrarTlbSubordinadoAuditor(false);
        consultaOrdenesHelper.setFlgMostrarTlbOrdenes(true);
        consultaOrdenesHelper.setFlgMostrarTlbMetodo(false);
        consultaOrdenesHelper.setFlgMostrarTlbEstatus(false);
        consultaOrdenesHelper.setFlgMostrarTlbCifras(false);
        consultaOrdenesHelper.setFlgMostrarDetallePropuesta(false);
        consultaOrdenesHelper.setFlgMostrarDetalleOrden(false);
        consultaOrdenesHelper.setFlgMostrarEstatusSemaforo(false);

    }

    public void mostrarPanelDetallePropuesta() {

        if (getSession().getAttribute("vieneDeConsultaPropuestasOrdenes") != null) {

            ConsultaOrdenesHelper helper = new ConsultaOrdenesHelper();

            helper.setFlgVienePropOrdenes((Boolean) getSession().getAttribute("vieneDeConsultaPropuestasOrdenes"));
            FecetPropuesta propuesta = (FecetPropuesta) getSession().getAttribute("propuestaSelAnalizar");

            setConsultaOrdenesHelper(helper);
            getConsultaOrdenesHelper().setIdRegistroPropuestaSeleccionada(propuesta.getIdRegistro());

            getSession().removeAttribute("vieneDeConsultaPropuestasOrdenes");
        }

        consultaOrdenesHelper.setPropuestaSeleccionada(null);
        consultaOrdenesHelper.setPropuestaSeleccionada(consultaEjecutivaOrdenesService.consultaPropuestaDetalle(getConsultaOrdenesHelper()
                .getIdRegistroPropuestaSeleccionada()));
        AraceDTO arace = obtenerUnidadAdministrativaEmpleado();
        if (arace != null) {
            FececUnidadAdministrativa unidad = new FececUnidadAdministrativa();
            unidad.setNombre(arace.getNombre());
            unidad.setIdUnidadAdministrativa(new BigDecimal(arace.getIdArace()));
            consultaOrdenesHelper.getPropuestaSeleccionada().setUnidadAdministrativa(unidad);
        }
        consultaOrdenesHelper.setFlgMostrarUnidadesDesahogo(false);
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

    private void ordenarListaResult() {
        if (consultaOrdenesHelper.getLstOrdenesResult() != null) {
            try {
                Collections.sort(consultaOrdenesHelper.getLstOrdenesResult(), new Comparator<AgaceOrden>() {
                    @Override
                    public int compare(AgaceOrden propOrg, AgaceOrden propCom) {
                        return propOrg.getPrioridadSugerida().compareTo(propCom.getPrioridadSugerida());
                    }
                });
            } catch (Exception e) {
                logger.error("existe error al ordenar la lista de ordenes");
            }
        }
    }

    public void irAPantallaConsultaXRolPropuestas(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        }
    }

    public void informeErrorSession(Object err) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute(SessionAttributeOrdenesEnum.MSG_ERROR_SESSION.getAttributeName(), err);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + URLsPortalOrdenes.URL_ERROR);
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error", f);
        }
    }

    @SuppressWarnings("unchecked")
    public void prerenderCentral() {
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
            ConsultaOrdenesHelper helperTmp = (ConsultaOrdenesHelper) getSession().getAttribute(CONSULTA_ORDENES_HELPER);
            consultaOrdenesHelper.setPropiedadesHelper(helperTmp);
            getSession().removeAttribute(CONSULTA_ORDENES_HELPER);
            if (mapaBanderas != null) {
                llenaBanderas(mapaBanderas);
            }
            mostrarPanelOrdenes();
        } else {
            if (!controlDeAcceso) {
                boolean flgCargarPgOrg = false;
                if (getSession().getAttribute(FILTRO_CONSULTA_CENTRAL) != null) {
                    consultaOrdenesHelper.setFiltro((FiltroConsultaOrdenes) getSession().getAttribute(FILTRO_CONSULTA_CENTRAL));
                    try {
                        consultaOrdenesHelper.setConsultaOrdenesBO(consultaEjecutivaOrdenesService.getAccesoEmpleadoAConsultaOrdenes(consultaOrdenesHelper
                                .getFiltro().getEmpleadoConsultaFiltro()));
                    } catch (ConsultaEjecutivaOrdenesServiceException ex) {
                        logger.error(ex.getMessage());
                        addErrorMessage(ex.getMessage());
                    }
                } else {
                    flgCargarPgOrg = cargaPaginaOrigen(BO_CENTRAL, FILTRO_CONSULTA_CENTRAL);
                }
                if (isSesionIniciada(consultaOrdenesHelper.getConsultaOrdenesBO()) && !flgCargarPgOrg) {
                    cargarValoresIniciales();
                } else {
                    mostrarPanelXSubordinados();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void prerenderFirmante() {
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
            ConsultaOrdenesHelper helperTmp = (ConsultaOrdenesHelper) getSession().getAttribute(CONSULTA_ORDENES_HELPER);
            consultaOrdenesHelper.setPropiedadesHelper(helperTmp);
            getSession().removeAttribute(CONSULTA_ORDENES_HELPER);
            if (mapaBanderas != null) {
                llenaBanderas(mapaBanderas);
            }
            mostrarPanelOrdenes();
        } else {
            if (!controlDeAcceso) {
                boolean flgCargarPgOrg = false;
                if (getSession().getAttribute(FILTRO_CONSULTA_FIRMANTE) != null) {
                    consultaOrdenesHelper.setFiltro((FiltroConsultaOrdenes) getSession().getAttribute(FILTRO_CONSULTA_FIRMANTE));
                    try {
                        consultaOrdenesHelper.setConsultaOrdenesBO(consultaEjecutivaOrdenesService.getAccesoEmpleadoAConsultaOrdenes(consultaOrdenesHelper
                                .getFiltro().getEmpleadoConsultaFiltro()));
                    } catch (ConsultaEjecutivaOrdenesServiceException ex) {
                        logger.error(ex.getMessage());
                        addErrorMessage(ex.getMessage());
                    }
                } else {
                    flgCargarPgOrg = cargaPaginaOrigen(BO_FIRMANTE, FILTRO_CONSULTA_FIRMANTE);
                }
                if (isSesionIniciada(consultaOrdenesHelper.getConsultaOrdenesBO()) && !flgCargarPgOrg) {
                    cargarValoresIniciales();
                } else {
                    mostrarPanelXSubordinados();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void prerenderAuditor() {
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
            ConsultaOrdenesHelper helperTmp = (ConsultaOrdenesHelper) getSession().getAttribute(CONSULTA_ORDENES_HELPER);
            consultaOrdenesHelper.setPropiedadesHelper(helperTmp);
            getSession().removeAttribute(CONSULTA_ORDENES_HELPER);
            if (mapaBanderas != null) {
                llenaBanderas(mapaBanderas);
            }
            mostrarPanelOrdenes();
        } else {
            if (!controlDeAcceso) {
                boolean flgCargarPgOrg = false;
                if (getSession().getAttribute(FILTRO_CONSULTA_AUDITOR) != null) {
                    consultaOrdenesHelper.setFiltro((FiltroConsultaOrdenes) getSession().getAttribute(FILTRO_CONSULTA_AUDITOR));
                    try {
                        consultaOrdenesHelper.setConsultaOrdenesBO(consultaEjecutivaOrdenesService.getAccesoEmpleadoAConsultaOrdenes(consultaOrdenesHelper
                                .getFiltro().getEmpleadoConsultaFiltro()));
                    } catch (ConsultaEjecutivaOrdenesServiceException ex) {
                        logger.error(ex.getMessage());
                        addErrorMessage(ex.getMessage());
                    }
                } else {
                    flgCargarPgOrg = cargaPaginaOrigen(BO_AUDITOR, FILTRO_CONSULTA_AUDITOR);
                }
                if (isSesionIniciada(consultaOrdenesHelper.getConsultaOrdenesBO()) && !flgCargarPgOrg) {
                    cargarValoresIniciales();
                } else {
                    mostrarPanelXSubordinados();
                }
            }
        }
    }

    private boolean cargaPaginaOrigen(String nombreBO, String nombreFiltro) {
        if (((getSession().getAttribute(HELPER_ORIGEN)) != null) && ((getSession().getAttribute(nombreBO)) != null)
                && ((getSession().getAttribute(nombreFiltro)) == null)) {
            consultaOrdenesHelper = ((ConsultaOrdenesHelper) (getSession().getAttribute(HELPER_ORIGEN)));
            consultaOrdenesHelper.setFlgRegresarASubordinado(false);
            getSession().removeAttribute(HELPER_ORIGEN);
            return true;
        } else {
            consultaOrdenesHelper.setConsultaOrdenesBO((ConsultaEjecutivaOrdenesBO) (getSession().getAttribute(nombreBO)));
            return false;
        }
    }

    private boolean isSesionIniciada(ConsultaEjecutivaOrdenesBO consultaBO) {
        if (consultaBO != null) {
            return true;
        }
        informeErrorSession(ERROR_SIN_SESSION);
        return false;
    }

    public boolean cargarValoresIniciales() {
        if (consultaOrdenesHelper.getFiltro() == null) {
            List<TipoEmpleadoEnum> rolesCombo = null;
            consultaOrdenesHelper.setLstRolesValidos(new ArrayList<TipoEmpleadoEnum>());
            consultaOrdenesHelper.setFiltro(new FiltroConsultaOrdenes());
            consultaOrdenesHelper.getFiltro().setEmpleadoConsultaFiltro(consultaOrdenesHelper.getConsultaOrdenesBO().getEmpleadoConsulta());
            rolesCombo = consultaOrdenesHelper.numeroRolesValidos(consultaOrdenesHelper.getConsultaOrdenesBO().getEmpleadoConsulta().getDetalleEmpleado());
            if (rolesCombo != null && !rolesCombo.isEmpty() && rolesCombo.size() > 1) {
                consultaOrdenesHelper.getLstRolesValidos().addAll(rolesCombo);
                consultaOrdenesHelper.setFlgMostrarComboRoles(true);
                mostrarPanelUnidadesDesahogo();
            } else {
                addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
                consultarOrdenes();
                mostrarPanelXCategorias();
            }
            return false;
        } else {
            ConsultaOrdenesHelper helperorigen = (ConsultaOrdenesHelper) getSession().getAttribute(HELPER_ORIGEN);
            if (helperorigen != null) {
                consultaOrdenesHelper.setGrupoEstatusSeleccionado(helperorigen.getGrupoEstatusSeleccionado());
                consultaOrdenesHelper.setUnidadAdminSeleccionada(helperorigen.getUnidadAdminSeleccionada());

                if (consultaOrdenesHelper.getUnidadAdminSeleccionada() != null) {
                    addUnidadesDesagoAlFiltro(consultaOrdenesHelper.getUnidadAdminSeleccionada());
                } else {
                    consultaOrdenesHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(
                            consultaOrdenesHelper.getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo());
                }
                if (consultaOrdenesHelper.getGrupoEstatusSeleccionado() != null) {
                    consultarOrdenesXEstatusoGrupo();
                    consultaOrdenesHelper.setFlgRegresarASubordinado(true);
                }
                consultarOrdenes();
                consultarOrdenesXEstatusoGrupo();
                mostrarPanelXSubordinados();
            }
            return true;
        }
    }

    public void consultaInicialRol() {
        if (consultaOrdenesHelper.getRolSeleccionado() != null) {
            getConsultaOrdenesHelper().getConsultaOrdenesBO().setRolEmpleado(getConsultaOrdenesHelper().getRolSeleccionado());
        } else {
            addErrorMessage(null, "Es necesario seleccionar un rol");
            return;
        }
        addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
        consultarOrdenes();
        mostrarPanelXCategorias();
    }

    public AraceDTO obtenerUnidadAdministrativaEmpleado() {
        boolean flgRule = consultaOrdenesHelper.getConsultaOrdenesBO().getEmpleadoConsulta() != null;
        if (flgRule) {
            for (DetalleEmpleadoDTO detalleEmp : consultaOrdenesHelper.getConsultaOrdenesBO().getEmpleadoConsulta().getDetalleEmpleado()) {
                flgRule = detalleEmp.getTipoEmpleado() != null
                        && detalleEmp.getTipoEmpleado().equals(consultaOrdenesHelper.getConsultaOrdenesBO().getRolEmpleado());
                if (flgRule) {
                    return detalleEmp.getCentral();
                }
            }
        }
        return null;
    }

    private void consultarOrdenes() {
        getConsultaOrdenesHelper().getFiltro().setVisibilidadACPPCE(false);
        getConsultaOrdenesHelper().getFiltro().setConsultaCifras(false);
        try {
            consultaEjecutivaOrdenesService.consultarOrdenes(consultaOrdenesHelper.getConsultaOrdenesBO(), consultaOrdenesHelper.getFiltro());
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    private void consultarOrdenesXEstatusoGrupo() {
        try {
            consultaEjecutivaOrdenesService.consultarOrdenesXEstatusoGrupo(consultaOrdenesHelper.getConsultaOrdenesBO(), consultaOrdenesHelper.getFiltro(),
                    consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey());
        } catch (ConsultaEjecutivaOrdenesServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void consultaAServicioXMetodoOSemaforo() {
        try {
            boolean flgMetodo = consultaOrdenesHelper.getMetodoSeleccionado() != null;
            boolean flgSemaforo = consultaOrdenesHelper.getSemaforoSeleccionado() != null;
            boolean flgSemaforoFiltrado = consultaOrdenesHelper.getSemaforoFiltradoSeleccionado() != null;
            boolean flgEmpleado = consultaOrdenesHelper.getEmpleadoSeleccionado() != null;
            boolean flgEstatus = consultaOrdenesHelper.getGrupoEstatusSeleccionado() != null;

                if (flgEmpleado) {
                    consultaOrdenesHelper.getFiltro().setEmpleadoConsultaFiltro(consultaOrdenesHelper.getEmpleadoSeleccionado().getKey());
                }
                consultaEjecutivaOrdenesService.getOrdenesXSemaforoMetodo(consultaOrdenesHelper.getConsultaOrdenesBO(), flgSemaforo ? consultaOrdenesHelper
                        .getSemaforoSeleccionado().getKey() : null, flgSemaforoFiltrado ? consultaOrdenesHelper.getSemaforoFiltradoSeleccionado().getKey()
                        : null, flgMetodo ? consultaOrdenesHelper.getMetodoSeleccionado().getKey() : null, flgEstatus ? consultaOrdenesHelper
                        .getGrupoEstatusSeleccionado().getKey() : null, consultaOrdenesHelper.getFiltro().getFiltroDAO(), flgEmpleado);

                consultaOrdenesHelper.setLstOrdenesResult(consultaOrdenesHelper.getConsultaOrdenesBO().getLstOrdenesXFiltro());
                listaPrioridadSugerida = new ArrayList<String>();
                listaPrioridadSugerida = buscarPrioridadPropuesta(consultaOrdenesHelper.getLstOrdenesResult());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarXEmpleadoSemaforoSeleccionado() {
        controlDeAcceso = true;
        List<SemaforoEnum> lstSemaforosFiltro = new ArrayList<SemaforoEnum>();
        List<TipoEstatusEnum> estatusFiltro = new ArrayList<TipoEstatusEnum>();
        FiltroConsultaOrdenes filtroConsultaOrdenes = new FiltroConsultaOrdenes();
        try {
            filtroConsultaOrdenes.setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            if (consultaOrdenesHelper.getUnidadAdminSeleccionada() != null) {
                filtroConsultaOrdenes.getUnidadAdmtvaDesahogoFiltro().add(consultaOrdenesHelper.getUnidadAdminSeleccionada());
            } else {
                filtroConsultaOrdenes.getUnidadAdmtvaDesahogoFiltro().addAll(consultaOrdenesHelper.getConsultaOrdenesBO().getLstUnidadesAdministrativasDesahogo());
            }

            if (consultaOrdenesHelper.getSemaforoSeleccionado() != null || consultaOrdenesHelper.getSemaforoFiltradoSeleccionado() != null) {
                if (consultaOrdenesHelper.getSemaforoSeleccionado() != null) {
                    lstSemaforosFiltro.add(consultaOrdenesHelper.getSemaforoSeleccionado().getKey());
                }
                if (consultaOrdenesHelper.getSemaforoFiltradoSeleccionado() != null) {
                    lstSemaforosFiltro.add(consultaOrdenesHelper.getSemaforoFiltradoSeleccionado().getKey());
                }
            } else {
                for (Map.Entry<SemaforoEnum, Integer> key : consultaOrdenesHelper.getConsultaOrdenesBO().getDetalleXSemaforoFiltrado().entrySet()) {
                    lstSemaforosFiltro.add(key.getKey());
                }
            }
            
            filtroConsultaOrdenes.setMetodoFiltro(new ArrayList<TipoMetodoEnum>());
            if (consultaOrdenesHelper.getMetodoSeleccionado() != null) {
                filtroConsultaOrdenes.getMetodoFiltro().add(consultaOrdenesHelper.getMetodoSeleccionado().getKey());
            } else {
                filtroConsultaOrdenes.getMetodoFiltro().addAll(consultaOrdenesHelper.getConsultaOrdenesBO().getLstMetodosValidos());
            }
            
            if (consultaOrdenesHelper.getEmpleadoFiltradoSeleccionado() != null) {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(consultaOrdenesHelper.getEmpleadoFiltradoSeleccionado().getKey());
                tipoEmpleadoFiltro(consultaOrdenesHelper.getEmpleadoFiltradoSeleccionado().getKey(), filtroConsultaOrdenes);
            } else if (consultaOrdenesHelper.getEmpleadoSeleccionado() != null) {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(consultaOrdenesHelper.getEmpleadoSeleccionado().getKey());
                tipoEmpleadoFiltro(consultaOrdenesHelper.getEmpleadoSeleccionado().getKey(), filtroConsultaOrdenes);
            } else {
                filtroConsultaOrdenes.setEmpleadoConsultaFiltro(consultaOrdenesHelper.getConsultaOrdenesBO().getEmpleadoConsulta());
                if (consultaOrdenesHelper.getConsultaOrdenesBO().getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId())
                        || consultaOrdenesHelper.getConsultaOrdenesBO().getRolEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId())) {
                    filtroConsultaOrdenes.setConsultaEmpleado(true);
                    filtroConsultaOrdenes.setTipoEmpleadoConsulta(consultaOrdenesHelper.getConsultaOrdenesBO().getRolEmpleado());
                } else {
                    filtroConsultaOrdenes.setConsultaEmpleado(false);
                }
            }

            if (consultaOrdenesHelper.getGrupoEstatusSeleccionado() != null) {
                AgrupadorEstatusOrdenesEnum agrupadorFiltro = consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey();
                for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : consultaOrdenesHelper.getConsultaOrdenesBO()
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
            

            consultaEjecutivaOrdenesService.getOrdenesXEmpleadoSemaforoMetodo(consultaOrdenesHelper.getConsultaOrdenesBO(), filtroConsultaOrdenes, lstSemaforosFiltro);

            if (consultaOrdenesHelper.getGrupoEstatusSeleccionado() != null
                    && (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey().getIdGrupo() || AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO
                            .getIdGrupo() == consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey().getIdGrupo())) {
                List<AgaceOrden> filtro = new ArrayList<AgaceOrden>();
                for (AgaceOrden orden : consultaOrdenesHelper.getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros()) {
                    if (getConsultaEjecutivaOrdenesService().tieneOficios(orden.getIdOrden())) {
                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EN_PROCESO.getIdGrupo() == consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey()
                                .getIdGrupo()) {
                            filtro.add(orden);
                        }
                    } else {
                        if (AgrupadorEstatusOrdenesEnum.ORDEN_EMITIDA.getIdGrupo() == consultaOrdenesHelper.getGrupoEstatusSeleccionado().getKey().getIdGrupo()) {
                            filtro.add(orden);
                        }
                    }
                }
                consultaOrdenesHelper.getConsultaOrdenesBO().setLstOrdenesMutiplesFiltros(new ArrayList<AgaceOrden>());
                consultaOrdenesHelper.getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros().addAll(filtro);
            }

            consultaOrdenesHelper.setLstOrdenesResult(consultaOrdenesHelper.getConsultaOrdenesBO().getLstOrdenesMutiplesFiltros());
            listaPrioridadSugerida = new ArrayList<String>();
            listaPrioridadSugerida = buscarPrioridadPropuesta(consultaOrdenesHelper.getLstOrdenesResult());
            if (consultaOrdenesHelper.getEmpleadoSeleccionado() == null) {
                mostrarPanelOrdenes();
            } else {
                if (consultaOrdenesHelper.isFlgMostrarTlbSubordinadoAuditor()) {
                    mostrarPanelOrdenes();
                } else {
                    mostrarPanelXSubordinadoAuditor();
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    public void tipoEmpleadoFiltro(EmpleadoDTO empleado, FiltroConsultaOrdenes filtroConsultaOrdenes){
        for (DetalleEmpleadoDTO lstDetalle : empleado.getDetalleEmpleado()) {
            if (lstDetalle.getTipoEmpleado() != null &&(lstDetalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.FIRMANTE.getBigId())
                        || lstDetalle.getTipoEmpleado().getBigId().equals(TipoEmpleadoEnum.AUDITOR.getBigId()))) {
                    filtroConsultaOrdenes.setConsultaEmpleado(true);
                    filtroConsultaOrdenes.setTipoEmpleadoConsulta(lstDetalle.getTipoEmpleado());
                    return;
                }
            
        }
        filtroConsultaOrdenes.setConsultaEmpleado(false);
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

    public boolean isControlDeAcceso() {
        return controlDeAcceso;
    }

    public void setControlDeAcceso(boolean controlDeAcceso) {
        this.controlDeAcceso = controlDeAcceso;
    }

    public ConsultaOrdenesHelper getConsultaOrdenesHelper() {
        return consultaOrdenesHelper;
    }

    public void setConsultaOrdenesHelper(ConsultaOrdenesHelper consultaOrdenesHelper) {
        this.consultaOrdenesHelper = consultaOrdenesHelper;
    }

    public ConsultaEjecutivaOrdenesService getConsultaEjecutivaOrdenesService() {
        return consultaEjecutivaOrdenesService;
    }

    public void setConsultaEjecutivaOrdenesService(ConsultaEjecutivaOrdenesService consultaEjecutivaOrdenesService) {
        this.consultaEjecutivaOrdenesService = consultaEjecutivaOrdenesService;
    }

    public void addUnidadesDesagoAlFiltro(AraceDTO... unidadesDesahogo) {
        if (consultaOrdenesHelper.getFiltro() != null && unidadesDesahogo != null) {
            consultaOrdenesHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            consultaOrdenesHelper.getFiltro().getUnidadAdmtvaDesahogoFiltro().addAll(Arrays.asList(unidadesDesahogo));
        }
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
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

    public boolean isConsultaXCifras() {
        return consultaXCifras;
    }

    public void setConsultaXCifras(boolean consultaXCifras) {
        this.consultaXCifras = consultaXCifras;
    }

    public boolean isPantallaEstatusEmpleados() {
        return pantallaEstatusEmpleados;
    }

    public void setPantallaEstatusEmpleados(boolean pantallaEstatusEmpleados) {
        this.pantallaEstatusEmpleados = pantallaEstatusEmpleados;
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

    public void setXlsReporte(StreamedContent xlsReporte) {
        this.xlsReporte = xlsReporte;
    }

    public List<String> getListaPrioridadSugerida() {
        return listaPrioridadSugerida;
    }

    public void setListaPrioridadSugerida(List<String> listaPrioridadSugerida) {
        this.listaPrioridadSugerida = listaPrioridadSugerida;
    }

}