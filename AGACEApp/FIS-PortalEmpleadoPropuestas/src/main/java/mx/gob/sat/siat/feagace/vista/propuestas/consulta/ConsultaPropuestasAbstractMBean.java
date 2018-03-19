/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasBO;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasRule;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.ConsultaEjecutivaPropuestasService;
import mx.gob.sat.siat.feagace.negocio.consulta.propuestas.filtro.FiltroConsultaPropuestas;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaPropuestasServiceException;
import mx.gob.sat.siat.feagace.vista.propuestas.helper.ConsultaPropuestasHelper;
import mx.gob.sat.siat.feagace.vista.util.constantes.SessionAttributePropuestasEnum;
import mx.gob.sat.siat.feagace.vista.util.constantes.URLsPortalPropuestas;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ConsultaPropuestasAbstractMBean extends BaseManagedBean {

    private static final long serialVersionUID = -6539616526677555261L;

    private boolean controlDeAcceso;

    private boolean pantallaEstatusFiltrado;
    private boolean pantallaTotalEmpleados;

    protected static final String ERROR_DESCARGA_ARCHIVO = "error.descarga.archivo";
    protected static final String ERROR_SIN_SESSION = "Error inicio de sesión, no se firmó correctamente…";

    //Url relativa
    protected static final String URL_RELATIVA_PROPUESTAS = "../";
    //url's de cada perfil 
    protected static final String URL_CENTRAL = "central/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_ADMINISTRADOR = "administrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_SUBADMINISTRADOR = "subadministrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_FIRMANTE = "firmante/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_AUDITOR = "auditor/consultaXEstatus.xhtml?faces-redirect=true";

    //nombres para los filtros de búsqueda de cada perfil que se almacenan en sesión 
    protected static final String FILTRO_CONSULTA_CENTRAL = "filtroCentral";
    protected static final String FILTRO_CONSULTA_ADMINISTRADOR = "filtroAdministrador";
    protected static final String FILTRO_CONSULTA_SUBADMINISTRADOR = "filtroSubadministrador";
    protected static final String FILTRO_CONSULTA_FIRMANTE = "filtroFirmante";
    protected static final String FILTRO_CONSULTA_AUDITOR = "filtroAuditor";

    //nombres para los objetos de negocio de cada perfil que se almacenan en sesión 
    protected static final String BO_CENTRAL = "centralBO";
    protected static final String BO_ADMINISTRADOR = "administradorBO";
    protected static final String BO_SUBADMINISTRADOR = "subadministradorBO";
    protected static final String BO_FIRMANTE = "firmanteBO";
    protected static final String BO_AUDITOR = "auditorBO";
    private static final String SUBORDINADO = "subordinado";

    //helper de session origen
    protected static final String HELPER_ORIGEN = "helperOrigen";

    private String nombreArchivoDescarga;
    private String rutaArchivoDescarga;

    private ConsultaPropuestasHelper consultaPropuestasHelper;

    @ManagedProperty(value = "#{consultaEjecutivaPropuestasService}")
    private ConsultaEjecutivaPropuestasService consultaEjecutivaPropuestasService;

    private StreamedContent xlsReporte;
    protected static final String MSJ_PROPUESTAS_RECHAZADAS = "No se mostrar\u00e1 la relaci\u00f3n Firmante/Auditor para aquellas propuestas que fueron rechazadas por el \u00e1rea de programaci\u00f3n. (No fueron consideradas por el programador para ser validadas por un Auditor o Firmante).";

    public ConsultaPropuestasAbstractMBean() {
        consultaPropuestasHelper = new ConsultaPropuestasHelper();
        controlDeAcceso = false;
        pantallaEstatusFiltrado = false;
        pantallaTotalEmpleados = false;
    }

    public void informeErrorSession(Object err) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(true);

            session.setAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName(), err);

            ServletContext dir = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();

            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            dir.getContextPath()
                            + URLsPortalPropuestas.URL_ERROR);
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error", f);
        }
    }

    private void limpiarSession() {
        if (getRFCSession() != null) {
            getSession().removeAttribute(BO_CENTRAL);
            getSession().removeAttribute(BO_ADMINISTRADOR);
            getSession().removeAttribute(BO_SUBADMINISTRADOR);
            getSession().removeAttribute(BO_FIRMANTE);
            getSession().removeAttribute(BO_AUDITOR);
            getSession().removeAttribute(FILTRO_CONSULTA_CENTRAL);
            getSession().removeAttribute(FILTRO_CONSULTA_ADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_FIRMANTE);
            getSession().removeAttribute(FILTRO_CONSULTA_AUDITOR);
            getSession().removeAttribute(HELPER_ORIGEN);
            getSession().removeAttribute(SUBORDINADO);
        }
    }

    public void prerender() {
        try {
            String url;
            if (getRFCSession() != null) {
                limpiarSession();
                addMessage(getRFCSession());
                EmpleadoDTO empleadoSession = new EmpleadoDTO();
                empleadoSession.setRfc(getRFCSession());
                consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(empleadoSession));
                if (consultaPropuestasHelper.getConsultaPropuestasBO() != null) {
                    url = "";
                    if (getSession().getAttribute(SUBORDINADO) != null) {
                        getConsultaEjecutivaPropuestasService().validarExistenciaTipoEmpleado(consultaPropuestasHelper.getConsultaPropuestasBO().getEmpleadoConsulta(), (TipoEmpleadoEnum) getSession().getAttribute(SUBORDINADO));
                        consultaPropuestasHelper.getConsultaPropuestasBO().setRolEmpleado((TipoEmpleadoEnum) getSession().getAttribute(SUBORDINADO));
                    }
                    TipoEmpleadoEnum tipo = consultaPropuestasHelper.getConsultaPropuestasBO().getRolEmpleado();
                    url = getUrlAndSetAttribute(tipo, consultaPropuestasHelper.getConsultaPropuestasBO(), null, false);
                    if (!url.isEmpty()) {
                        irAPantallaConsultaXRolPropuestas(url);
                    }
                }
            }
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        }
    }

    public void irAPantallaConsultaXRolPropuestas(String url) {
        try {
            FacesContext.getCurrentInstance().
                    getExternalContext().redirect(url);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        }
    }

    public StreamedContent getDescargaDocumento() {
        logger.debug("Se descarga docto...");
        try {
            if (rutaArchivoDescarga != null && !rutaArchivoDescarga.isEmpty()) {

                int indice = rutaArchivoDescarga.lastIndexOf('/');
                if ((rutaArchivoDescarga.length() - 1) == indice) {
                    rutaArchivoDescarga = rutaArchivoDescarga.concat(nombreArchivoDescarga);
                }

                indice = rutaArchivoDescarga.lastIndexOf('\\');
                if ((rutaArchivoDescarga.length() - 1) == indice) {
                    rutaArchivoDescarga = rutaArchivoDescarga.concat(nombreArchivoDescarga);
                }

                return new DefaultStreamedContent(new FileInputStream(rutaArchivoDescarga),
                        "application/octet-stream",
                        nombreArchivoDescarga != null ? nombreArchivoDescarga : "archivo");
            } else {
                addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO, nombreArchivoDescarga));
                return null;
            }
        } catch (Exception fne) {
            addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO, nombreArchivoDescarga));
            logger.error(fne.getMessage(), fne);
            return null;
        }
    }

    private boolean cargaPaginaOrigen(String nombreBO, String nombreFiltro) {
        if (((getSession().getAttribute(HELPER_ORIGEN)) != null) && ((getSession().getAttribute(nombreBO)) != null) && ((getSession().getAttribute(nombreFiltro)) == null)) {
            consultaPropuestasHelper = ((ConsultaPropuestasHelper) (getSession().getAttribute(HELPER_ORIGEN)));
            consultaPropuestasHelper.setFlgRegresarASubordinado(false);
            getSession().removeAttribute(HELPER_ORIGEN);
            return true;
        } else {
            consultaPropuestasHelper.setConsultaPropuestasBO((ConsultaEjecutivaPropuestasBO) (getSession().getAttribute(nombreBO)));
            return false;
        }
    }

    public void prerenderCentral() {

        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_CENTRAL) != null) {
                consultaPropuestasHelper.setFiltro((FiltroConsultaPropuestas) getSession().getAttribute(FILTRO_CONSULTA_CENTRAL));
                consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                try {
                    consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(consultaPropuestasHelper.getFiltro().getEmpleadoConsultaFiltro()));
                } catch (ConsultaEjecutivaPropuestasServiceException ex) {
                    logger.error(ex.getMessage());
                    addErrorMessage(ex.getMessage());
                }
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_CENTRAL, FILTRO_CONSULTA_CENTRAL);
            }

            if (isSesionIniciada(consultaPropuestasHelper.getConsultaPropuestasBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXSubordinados();
            }
        }
    }

    public void prerenderAdministrador() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_ADMINISTRADOR) != null) {
                consultaPropuestasHelper.setFiltro((FiltroConsultaPropuestas) getSession().getAttribute(FILTRO_CONSULTA_ADMINISTRADOR));
                consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                try {
                    consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(consultaPropuestasHelper.getFiltro().getEmpleadoConsultaFiltro()));
                    consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(true);

                    getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                } catch (ConsultaEjecutivaPropuestasServiceException ex) {
                    logger.error(ex.getMessage());
                    addErrorMessage(ex.getMessage());
                }
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_ADMINISTRADOR, FILTRO_CONSULTA_ADMINISTRADOR);
            }

            if (isSesionIniciada(consultaPropuestasHelper.getConsultaPropuestasBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXSubordinados();
            }
        }
    }

    public void prerenderSubadministrador() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR) != null) {
                consultaPropuestasHelper.setFiltro((FiltroConsultaPropuestas) getSession().getAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR));
                consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                try {
                    consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(consultaPropuestasHelper.getFiltro().getEmpleadoConsultaFiltro()));
                    consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(true);

                    getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                } catch (ConsultaEjecutivaPropuestasServiceException ex) {
                    logger.error(ex.getMessage());
                    addErrorMessage(ex.getMessage());
                }
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_SUBADMINISTRADOR, FILTRO_CONSULTA_SUBADMINISTRADOR);
            }
            if (isSesionIniciada(consultaPropuestasHelper.getConsultaPropuestasBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void prerenderFirmante() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_FIRMANTE) != null) {
                consultaPropuestasHelper.setFiltro((FiltroConsultaPropuestas) getSession().getAttribute(FILTRO_CONSULTA_FIRMANTE));
                consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                try {
                    consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(consultaPropuestasHelper.getFiltro().getEmpleadoConsultaFiltro()));
                    consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(false);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                } catch (ConsultaEjecutivaPropuestasServiceException ex) {
                    logger.error(ex.getMessage());
                    addErrorMessage(ex.getMessage());
                }
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_FIRMANTE, FILTRO_CONSULTA_FIRMANTE);
            }
            if (isSesionIniciada(consultaPropuestasHelper.getConsultaPropuestasBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXSubordinados();
            }
        }
    }

    public void prerenderAuditor() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_AUDITOR) != null) {
                consultaPropuestasHelper.setFiltro((FiltroConsultaPropuestas) getSession().getAttribute(FILTRO_CONSULTA_AUDITOR));
                consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                try {
                    consultaPropuestasHelper.setConsultaPropuestasBO(consultaEjecutivaPropuestasService.getAccesoEmpleadoAConsultaPropuestas(consultaPropuestasHelper.getFiltro().getEmpleadoConsultaFiltro()));
                    consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(false);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
                    getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                } catch (ConsultaEjecutivaPropuestasServiceException ex) {
                    logger.error(ex.getMessage());
                    addErrorMessage(ex.getMessage());
                }
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_AUDITOR, FILTRO_CONSULTA_AUDITOR);
            }
            if (isSesionIniciada(consultaPropuestasHelper.getConsultaPropuestasBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    private boolean isSesionIniciada(ConsultaEjecutivaPropuestasBO consultaBO) {
        if (consultaBO != null) {
            return true;
        }
        informeErrorSession(ERROR_SIN_SESSION);
        return false;
    }

    public boolean cargarValoresIniciales() {
        consultaPropuestasHelper.getConsultaPropuestasBO().setAdace(false);
        if (getSession().getAttribute(SUBORDINADO) == null) {
            consultaPropuestasHelper.getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.MOSTRAR_COMBOS);
            consultaPropuestasHelper.setFlgMostrarComboPerfiles(consultaPropuestasHelper.getConsultaPropuestasBO().getRule().process(consultaPropuestasHelper.getConsultaPropuestasBO()));
            if (consultaPropuestasHelper.isFlgMostrarComboPerfiles() && consultaPropuestasHelper.getConsultaPropuestasBO().getTipoRolSeleccionado() == null) {
                mostrarPanelUnidadesDesahogo();
                return false;
            }
        }
        if (consultaPropuestasHelper.getFiltro() == null) {
            consultaPropuestasHelper.setFiltro(new FiltroConsultaPropuestas());
            consultaPropuestasHelper.getFiltro().setEmpleadoConsultaFiltro(consultaPropuestasHelper.
                    getConsultaPropuestasBO().getEmpleadoConsulta());
            if (consultaPropuestasHelper.getConsultaPropuestasBO().isIsAcppce()) {
                consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(true);
                mostrarPanelUnidadesDesahogo();
            } else {
                consultaPropuestasHelper.getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ES_REGIONAL);
                if (consultaPropuestasHelper.getConsultaPropuestasBO().getRule().process(consultaPropuestasHelper.getConsultaPropuestasBO())) {
                    consultaPropuestasHelper.setIdOpcion(null);
                    addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
                    TipoEmpleadoEnum rol = consultaPropuestasHelper.getConsultaPropuestasBO().getRolEmpleado();
                    if (TipoEmpleadoEnum.CENTRAL.equals(rol)) {
                        consultaPropuestasHelper.getConsultaPropuestasBO().setAdace(true);
                        consultaPropuestasHelper.setFlgBtnCentralRegional(true);
                        mostrarPanelUnidadesDesahogo();
                    } else if (TipoEmpleadoEnum.FIRMANTE.equals(rol) || TipoEmpleadoEnum.AUDITOR.equals(rol)) {
                        consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(false);
                        consultarPropuestas();
                        mostrarPanelXCategorias();
                    } else {
                        consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(true);
                        addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
                        consultarPropuestas();
                        mostrarPanelXCategorias();
                    }
                } else {
                    consultaPropuestasHelper.getConsultaPropuestasBO().setProgramacion(false);
                    addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
                    consultarPropuestas();
                    mostrarPanelXCategorias();
                }
            }
            return false;
        } else {
            ConsultaPropuestasHelper helperorigen = (ConsultaPropuestasHelper) getSession().getAttribute(HELPER_ORIGEN);
            if (helperorigen != null) {
                consultaPropuestasHelper.setGrupoEstatusSeleccionado(helperorigen.getGrupoEstatusSeleccionado());
                consultaPropuestasHelper.setUnidadAdminSeleccionada(helperorigen.getUnidadAdminSeleccionada());
                consultaPropuestasHelper.setFlgBtnCentralRegional(helperorigen.isFlgBtnCentralRegional());

                if (consultaPropuestasHelper.getUnidadAdminSeleccionada() != null) {
                    addUnidadesDesagoAlFiltro(consultaPropuestasHelper.getUnidadAdminSeleccionada());
                } else {
                    consultaPropuestasHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(consultaPropuestasHelper.getConsultaPropuestasBO().getLstUnidadesAdministrativasDesahogo());
                }

                if (helperorigen.getConsultaPropuestasBO().isProgramacion()) {
                    consultaPropuestasHelper.getConsultaPropuestasBO().setGruposDeEstatusValidos(helperorigen.getConsultaPropuestasBO().getGruposDeEstatusValidos());
                }

                if (consultaPropuestasHelper.getGrupoEstatusSeleccionado() != null) {
                    consultarPropuestasXEstatusoGrupo();
                    consultaPropuestasHelper.setFlgRegresarASubordinado(true);
                }
                consultarPropuestas();
                consultarPropuestasXEstatusoGrupo();
                mostrarPanelXSubordinados();
            }
            return true;
        }
    }

    private void consultarPropuestas() {
        try {
            consultaPropuestasHelper.getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_CONSULTA_PROPUESTAS);
            consultaPropuestasHelper.getConsultaPropuestasBO().getRule().process(consultaPropuestasHelper.getConsultaPropuestasBO());
            consultaEjecutivaPropuestasService.consultarPropuestas(consultaPropuestasHelper.getConsultaPropuestasBO(), consultaPropuestasHelper.getFiltro());
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    private void consultarPropuestasXEstatusoGrupo() {
        try {
            consultaEjecutivaPropuestasService.consultarPropuestasXEstatusoGrupo(consultaPropuestasHelper.getConsultaPropuestasBO(), consultaPropuestasHelper.getFiltro(), consultaPropuestasHelper.getGrupoEstatusSeleccionado().getKey());
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void setUnidadAdminByID(Integer idUnidadAdmin) {
        if (idUnidadAdmin != null && idUnidadAdmin > 0) {
            for (AraceDTO unidad : consultaPropuestasHelper.getConsultaPropuestasBO().getLstUnidadesAdministrativasDesahogo()) {
                if (idUnidadAdmin.intValue() == unidad.getIdArace()) {
                    consultaPropuestasHelper.setUnidadAdminSeleccionada(unidad);
                    break;
                }
            }
        } else {
            consultaPropuestasHelper.setUnidadAdminSeleccionada(null);
        }
    }

    public void addUnidadesDesagoAlFiltro(AraceDTO... unidadesDesahogo) {
        if (consultaPropuestasHelper.getFiltro() != null && unidadesDesahogo != null) {
            consultaPropuestasHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            consultaPropuestasHelper.getFiltro().getUnidadAdmtvaDesahogoFiltro().addAll(Arrays.asList(unidadesDesahogo));
        }
    }

    public void mostrarPanelUnidadesDesahogo() {
        consultaPropuestasHelper.setEmpleadoSeleccionado(null);
        consultaPropuestasHelper.setGrupoEstatusSeleccionado(null);
        consultaPropuestasHelper.setUnidadAdminSeleccionada(null);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(false);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(false);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(false);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(false);
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(consultaPropuestasHelper.getConsultaPropuestasBO().isIsAcppce());
        consultaPropuestasHelper.setFlgMostrarOpciones(consultaPropuestasHelper.getConsultaPropuestasBO().isAdace());

    }

    public void mostrarPanelOpciones() {
        consultaPropuestasHelper.setEmpleadoSeleccionado(null);
        consultaPropuestasHelper.setGrupoEstatusSeleccionado(null);
        consultaPropuestasHelper.setUnidadAdminSeleccionada(null);
        consultaPropuestasHelper.setIdOpcion(null);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(false);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(false);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(false);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(false);
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaPropuestasHelper.setFlgMostrarOpciones(true);

    }

    public void mostrarPanelXCategorias() {
        if (consultaPropuestasHelper.isFlgRegresarASubordinado()) {
            mostrarPanelXSubordinados();
            return;
        }
        consultaPropuestasHelper.setFlgRegresarASubordinado(false);
        consultaPropuestasHelper.setGrupoEstatusSeleccionado(null);
        consultaPropuestasHelper.setEmpleadoSeleccionado(null);
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaPropuestasHelper.setFlgMostrarOpciones(false);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(true);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(false);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(false);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(false);
    }

    public void mostrarPanelXSubordinados() {
        consultaPropuestasHelper.setEmpleadoSeleccionado(null);
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaPropuestasHelper.setFlgMostrarOpciones(false);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(false);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(true);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(false);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(false);
    }

    public void mostrarPanelPropuestas() {
        ordenarListaResult();
        consultaPropuestasHelper.setPropuestaSeleccionada(null);
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaPropuestasHelper.setFlgMostrarOpciones(false);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(false);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(false);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(true);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(false);
    }

    public void mostrarPanelDetallePropuesta() {
        consultaPropuestasHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaPropuestasHelper.setFlgMostrarOpciones(false);
        consultaPropuestasHelper.setFlgMostrarTlbCategorias(false);
        consultaPropuestasHelper.setFlgMostrarTlbSubordinados(false);
        consultaPropuestasHelper.setFlgMostrarTlbPropuestas(false);
        consultaPropuestasHelper.setFlgMostrarDetallePropuesta(true);
    }

    public AraceDTO obtenerUnidadAdministrativaEmpleado() {
        boolean flgRule = consultaPropuestasHelper.getConsultaPropuestasBO().getEmpleadoConsulta() != null;
        if (flgRule) {
            for (DetalleEmpleadoDTO detalleEmp : consultaPropuestasHelper.getConsultaPropuestasBO().getEmpleadoConsulta().getDetalleEmpleado()) {
                flgRule = detalleEmp.getTipoEmpleado() != null && detalleEmp.getTipoEmpleado().equals(consultaPropuestasHelper.getConsultaPropuestasBO().getRolEmpleado());
                if (flgRule) {
                    return detalleEmp.getCentral();
                }
            }
        }
        return null;
    }

    public ConsultaEjecutivaPropuestasService getConsultaEjecutivaPropuestasService() {
        return consultaEjecutivaPropuestasService;
    }

    public void setConsultaEjecutivaPropuestasService(ConsultaEjecutivaPropuestasService consultaEjecutivaPropuestasService) {
        this.consultaEjecutivaPropuestasService = consultaEjecutivaPropuestasService;
    }

    public String getNombreArchivoDescarga() {
        return nombreArchivoDescarga;
    }

    public void setNombreArchivoDescarga(String nombreArchivoDescarga) {
        this.nombreArchivoDescarga = nombreArchivoDescarga;
    }

    public String getRutaArchivoDescarga() {
        return rutaArchivoDescarga;
    }

    public void setRutaArchivoDescarga(String rutaArchivoDescarga) {
        this.rutaArchivoDescarga = rutaArchivoDescarga;
    }

    public ConsultaPropuestasHelper getConsultaPropuestasHelper() {
        return consultaPropuestasHelper;
    }

    public void setConsultaPropuestasHelper(ConsultaPropuestasHelper consultaPropuestasHelper) {
        this.consultaPropuestasHelper = consultaPropuestasHelper;
    }

    private void ordenarListaResult() {
        if (consultaPropuestasHelper.getLstPropuestasResult() != null) {
            try {
                Collections.sort(consultaPropuestasHelper.getLstPropuestasResult(), new Comparator<FecetPropuesta>() {
                    @Override
                    public int compare(FecetPropuesta propOrg, FecetPropuesta propCom) {
                        return propOrg.getPrioridadSugerida().compareTo(propCom.getPrioridadSugerida());
                    }
                });
            } catch (Exception e) {
                logger.error("existe error al ordenar la lista de insumos");
            }
        }
    }

    private String getUrlAndSetAttribute(TipoEmpleadoEnum tipoEmpleado, ConsultaEjecutivaPropuestasBO consultaBO, FiltroConsultaPropuestas filtro, boolean subordinadoUrl) {
        String url = "";
        String urlRelativa = URL_RELATIVA_PROPUESTAS;
        getSession().removeAttribute(SUBORDINADO);
        if (TipoEmpleadoEnum.CENTRAL.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(consultaBO.isProgramacion() ? BO_ADMINISTRADOR : BO_FIRMANTE, consultaBO);
                getSession().setAttribute(SUBORDINADO, consultaBO.isProgramacion() ? TipoEmpleadoEnum.ADMINISTRADOR : TipoEmpleadoEnum.FIRMANTE);
                if (filtro != null) {
                    getSession().setAttribute(consultaBO.isProgramacion() ? FILTRO_CONSULTA_ADMINISTRADOR : FILTRO_CONSULTA_FIRMANTE, filtro);
                }
                return consultaBO.isProgramacion() ? urlRelativa.concat(URL_ADMINISTRADOR) : urlRelativa.concat(URL_FIRMANTE);
            } else {
                url = URL_CENTRAL;
                getSession().setAttribute(BO_CENTRAL, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_CENTRAL, filtro);
                }
            }
        }
        if (TipoEmpleadoEnum.ADMINISTRADOR.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(BO_SUBADMINISTRADOR, consultaBO);
                getSession().setAttribute(SUBORDINADO, TipoEmpleadoEnum.SUBADMINISTRADOR);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR, filtro);
                }
                return urlRelativa.concat(URL_SUBADMINISTRADOR);
            } else {
                url = URL_ADMINISTRADOR;
                getSession().setAttribute(BO_ADMINISTRADOR, consultaBO);
                if (filtro != null) {
                    getSession().setAttribute(FILTRO_CONSULTA_ADMINISTRADOR, filtro);
                }
            }
        }
        if (TipoEmpleadoEnum.FIRMANTE.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(BO_AUDITOR, consultaBO);
                getSession().setAttribute(SUBORDINADO, TipoEmpleadoEnum.AUDITOR);
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
        if (TipoEmpleadoEnum.SUBADMINISTRADOR.equals(tipoEmpleado)) {
            url = URL_SUBADMINISTRADOR;
            getSession().setAttribute(BO_SUBADMINISTRADOR, consultaBO);
            if (filtro != null) {
                getSession().setAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR, filtro);
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

    public void regresarNivelDeEmpleado() {
        TipoEmpleadoEnum tipoEmpleado = consultaPropuestasHelper.getConsultaPropuestasBO().getRolEmpleado();
        String url = "";
        if (TipoEmpleadoEnum.ADMINISTRADOR.equals(tipoEmpleado)) {
            url = URL_RELATIVA_PROPUESTAS.concat(URL_CENTRAL);
            getSession().removeAttribute(BO_ADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_ADMINISTRADOR);

        }
        if (TipoEmpleadoEnum.SUBADMINISTRADOR.equals(tipoEmpleado)) {
            url = URL_RELATIVA_PROPUESTAS.concat(URL_ADMINISTRADOR);
            getSession().removeAttribute(BO_SUBADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR);

        }
        if (TipoEmpleadoEnum.FIRMANTE.equals(tipoEmpleado)) {
            url = URL_RELATIVA_PROPUESTAS.concat(URL_CENTRAL);
            getSession().removeAttribute(BO_FIRMANTE);
            getSession().removeAttribute(FILTRO_CONSULTA_FIRMANTE);

        }
        if (TipoEmpleadoEnum.AUDITOR.equals(tipoEmpleado)) {
            url = URL_RELATIVA_PROPUESTAS.concat(URL_FIRMANTE);
            getSession().removeAttribute(BO_AUDITOR);
            getSession().removeAttribute(FILTRO_CONSULTA_AUDITOR);

        }
        irAPantallaConsultaXRolPropuestas(url);

    }

    public void consultarASubordinado() {
        String url;
        FiltroConsultaPropuestas filtroCompartido = new FiltroConsultaPropuestas();

        if (consultaPropuestasHelper.getEmpleadoSeleccionado() != null) {
            filtroCompartido.setEmpleadoConsultaFiltro(consultaPropuestasHelper.getEmpleadoSeleccionado().getKey());

            if (getSession().getAttribute(HELPER_ORIGEN) == null) {
                getSession().setAttribute(HELPER_ORIGEN, consultaPropuestasHelper);
            }

            TipoEmpleadoEnum tipo = consultaPropuestasHelper.getConsultaPropuestasBO().getRolEmpleado();
            url = getUrlAndSetAttribute(tipo, consultaPropuestasHelper.getConsultaPropuestasBO(), filtroCompartido, true);
            if (!url.isEmpty()) {
                irAPantallaConsultaXRolPropuestas(url);
            }
        }
    }

    public void consultarTotalEmpleados() {
        try {
            setControlDeAcceso(true);
            pantallaTotalEmpleados = true;
            consultaPropuestasHelper.setLstPropuestasResult(consultaPropuestasHelper.getConsultaPropuestasBO().getLstPropuestasTotalEmpleado());
            mostrarPanelPropuestas();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public boolean isControlDeAcceso() {
        return controlDeAcceso;
    }

    public void setControlDeAcceso(boolean controlDeAcceso) {
        this.controlDeAcceso = controlDeAcceso;
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

    public StreamedContent getXlsReporte() {
        File file = null;
        HSSFWorkbook workbook = getConsultaEjecutivaPropuestasService().creaExcel(getConsultaPropuestasHelper().getLstPropuestasResult());

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
}
