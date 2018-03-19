/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.consulta;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.base.constante.ConstantesSesion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.DetalleEmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.AciaceEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaInsumosBO;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.ConsultaEjecutivaInsumosService;
import mx.gob.sat.siat.feagace.negocio.consulta.insumos.filtro.FiltroConsultaInsumos;
import mx.gob.sat.siat.feagace.negocio.exception.NoSeGeneroReporteException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaInsumosServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.helper.ItemCombo;
import mx.gob.sat.siat.feagace.vista.insumos.helper.ConsultaInsumosHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.util.constantes.SessionAttributeInsumosEnum;
import mx.gob.sat.siat.feagace.vista.util.constantes.URLsPortalInsumos;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ConsultaInsumosAbstractMBean extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 3738053065623920721L;
    protected static final int PLAZO_MAXIMO = 20;
    protected static final int PLAZO_INICIAL = -1;
    protected static final int UNO = 1;
    protected static final String DESCRIPCION_PLAZO = " d\u00eda";
    
    private boolean controlDeAcceso;

    private boolean pantallaEstatusFiltrado;
    private boolean pantallaTotalEmpleados;
    private boolean consultaXPlazo;

    protected static final String ERROR_DESCARGA_ARCHIVO = "error.descarga.archivo";
    protected static final String ERROR_SIN_SESSION = "Error inicio de sesión, no se firmó correctamente…";

    //Url relativa
    protected static final String URL_RELATIVA_INSUMOS = "../";
    //url's de cada perfil 
    protected static final String URL_CENTRAL = "central/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_ADMINISTRADOR = "administrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_SUBADMINISTRADOR = "subadministrador/consultaXEstatus.xhtml?faces-redirect=true";
    protected static final String URL_USUARIO_ACIACE = "aciace/consultaXEstatus.xhtml?faces-redirect=true";

    //nombres para los filtros de búsqueda de cada perfil que se almacenan en sesión 
    protected static final String FILTRO_CONSULTA_CENTRAL = "filtroCentral";
    protected static final String FILTRO_CONSULTA_ADMINISTRADOR = "filtroAdministrador";
    protected static final String FILTRO_CONSULTA_SUBADMINISTRADOR = "filtroSubadministrador";
    protected static final String FILTRO_CONSULTA_USUARIO_ACIACE = "filtroUsuarioAciace";

    //nombres para los objetos de negocio de cada perfil que se almacenan en sesión 
    protected static final String BO_CENTRAL = "centralBO";
    protected static final String BO_ADMINISTRADOR = "administradorBO";
    protected static final String BO_SUBADMINISTRADOR = "subadministradorBO";
    protected static final String BO_USUARIO_ACIACE = "usuarioAciaceBO";

    //helper de session origen
    protected static final String HELPER_ORIGEN = "helperOrigen";

    private String nombreArchivoDescarga;
    private String rutaArchivoDescarga;

    private ConsultaInsumosHelper consultaInsumosHelper;

    @ManagedProperty(value = "#{consultaEjecutivaInsumosService}")
    private ConsultaEjecutivaInsumosService consultaEjecutivaInsumosService;

    public ConsultaInsumosAbstractMBean() {
        consultaInsumosHelper = new ConsultaInsumosHelper();
        controlDeAcceso = false;
        pantallaEstatusFiltrado = false;
        consultaXPlazo = false;
        pantallaTotalEmpleados = false;
    }

    public void informeErrorSession(Object err) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName(), err);
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + URLsPortalInsumos.URL_ERROR);
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error", f);
        }
    }

    private void limpiarSession() {
        if (getRFCSession() != null) {
            getSession().removeAttribute(BO_CENTRAL);
            getSession().removeAttribute(BO_ADMINISTRADOR);
            getSession().removeAttribute(BO_SUBADMINISTRADOR);
            getSession().removeAttribute(BO_USUARIO_ACIACE);
            getSession().removeAttribute(FILTRO_CONSULTA_CENTRAL);
            getSession().removeAttribute(FILTRO_CONSULTA_ADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR);
            getSession().removeAttribute(FILTRO_CONSULTA_USUARIO_ACIACE);
            getSession().removeAttribute(HELPER_ORIGEN);
        }
    }

    public void prerender() {
        try {
            String url;
            if (getRFCSession() != null) {
                limpiarSession();
                addMessage(getRFCSession());
                EmpleadoDTO empleadoSession = getAccesoEmpleado(getRFCSession());
                
                empleadoSession.setRfc(getRFCSession());
                consultaInsumosHelper.setConsultaInsumosBO(getConsultaEjecutivaInsumosService().getAccesoEmpleadoAConsultaInsumos(empleadoSession,getMapGrupos()!=null&&!getMapGrupos().isEmpty()?getMapGrupos():(Map<BigDecimal, GrupoUnidadesAdminXGeneral>)getSession().getAttribute(ConstantesSesion.MAP_GRUPOS_UNIDADES_ADMIN)));
                if (consultaInsumosHelper.getConsultaInsumosBO() != null) {
                    url = "";
                    TipoEmpleadoEnum tipo = consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado();
                    url = getUrlAndSetAttribute(tipo, consultaInsumosHelper.getConsultaInsumosBO(), null, false);
                    if (!url.isEmpty()) {
                        irAPantallaInsumos(url);
                    }
                }
            }
        } catch (ConsultaEjecutivaInsumosServiceException ex) {
            logger.error(ex.getMessage(), ex);
            informeErrorSession(ex.getMessage());
        }
    }

    public void irAPantallaInsumos(String url) {
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
            consultaInsumosHelper = ((ConsultaInsumosHelper) (getSession().getAttribute(HELPER_ORIGEN)));
            consultaInsumosHelper.setFlgRegresarASubordinado(false);
            getSession().removeAttribute(HELPER_ORIGEN);
            return true;
        } else {
            consultaInsumosHelper.setConsultaInsumosBO(
                    (ConsultaEjecutivaInsumosBO) (getSession().getAttribute(nombreBO)));
            return false;
        }
    }

    public void prerenderCentral() {

        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_CENTRAL) != null) {
                consultaInsumosHelper.setFiltro((FiltroConsultaInsumos) getSession().getAttribute(FILTRO_CONSULTA_CENTRAL));
                consultaInsumosHelper.setFlgRegresarASubordinado(true);
         } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_CENTRAL, FILTRO_CONSULTA_CENTRAL);
            }

            if (isSesionIniciada(consultaInsumosHelper.getConsultaInsumosBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void prerenderAdministrador() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_ADMINISTRADOR) != null) {
                consultaInsumosHelper.setFiltro((FiltroConsultaInsumos) getSession().getAttribute(FILTRO_CONSULTA_ADMINISTRADOR));
                consultaInsumosHelper.setFlgRegresarASubordinado(true);
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_ADMINISTRADOR, FILTRO_CONSULTA_ADMINISTRADOR);
            }

            if (isSesionIniciada(consultaInsumosHelper.getConsultaInsumosBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void prerenderSubadministrador() {
        if (!controlDeAcceso) {
            boolean flgCargarPgOrg = false;
            if (getSession().getAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR) != null) {
                consultaInsumosHelper.setFiltro((FiltroConsultaInsumos) getSession().getAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR));
                consultaInsumosHelper.setFlgRegresarASubordinado(true);
            } else {
                flgCargarPgOrg = cargaPaginaOrigen(BO_SUBADMINISTRADOR, FILTRO_CONSULTA_SUBADMINISTRADOR);
            }
            if (isSesionIniciada(consultaInsumosHelper.getConsultaInsumosBO()) && !flgCargarPgOrg) {
                cargarValoresIniciales();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void prerenderUsuarioAciace() {
        if (!controlDeAcceso) {
            consultaInsumosHelper.setConsultaInsumosBO(
                    (ConsultaEjecutivaInsumosBO) (getSession().getAttribute(BO_USUARIO_ACIACE)));
            if (isSesionIniciada(consultaInsumosHelper.getConsultaInsumosBO())) {
                cargarValoresIniciales();
            }
        }
    }

    private boolean isSesionIniciada(ConsultaEjecutivaInsumosBO consultaBO) {
        if (consultaBO != null) {
            return true;
        }
        informeErrorSession(ERROR_SIN_SESSION);
        return false;
    }

    public void cargarValoresIniciales() {
        if (consultaInsumosHelper.getFiltro() == null) {
            consultaInsumosHelper.setFiltro(new FiltroConsultaInsumos());
            consultaInsumosHelper.getFiltro().setDiasConcluirPlazo(null);
            consultaInsumosHelper.getFiltro().setEstatusFiltro(consultaInsumosHelper.
                    getConsultaInsumosBO().
                    getLstEstatusValidos());
            consultaInsumosHelper.getFiltro().setEmpleadoConsultaFiltro(consultaInsumosHelper.getConsultaInsumosBO().getEmpleadoConsulta());
            if (consultaInsumosHelper.getConsultaInsumosBO().isIsAciace() 
                    || (consultaInsumosHelper.getConsultaInsumosBO().isCentralACPPCE())) {
                mostrarPanelUnidadesDesahogo();
            } else {
                addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
                consultarInsumos();
                mostrarPanelEstatusSemaforo();
            }
        } else {
            ConsultaInsumosHelper helperorigen = (ConsultaInsumosHelper) getSession().getAttribute(HELPER_ORIGEN);

            if (helperorigen != null) {
                consultaInsumosHelper.setPlazoSeleccionado(helperorigen.getPlazoSeleccionado());
                consultaInsumosHelper.setSemaforoSeleccionado(helperorigen.getSemaforoSeleccionado());
                consultaInsumosHelper.setEstatusSeleccionado(helperorigen.getEstatusSeleccionado());
                consultaInsumosHelper.setUnidadAdminSeleccionada(helperorigen.getUnidadAdminSeleccionada());
                if (consultaInsumosHelper.getEstatusSeleccionado() != null) {
                    if (helperorigen.getConsultaInsumosBO().isIsAciace()) {
                        List<TipoEstatusEnum> listEstatus = AciaceEstatusEnum.obtenerEstatusConsulta(consultaInsumosHelper.getEstatusSeleccionado().getKey());
                        addEstatusAlFiltro(listEstatus.toArray(new TipoEstatusEnum[listEstatus.size()]));
                    } else {
                        addEstatusAlFiltro(consultaInsumosHelper.getEstatusSeleccionado().getKey());
                    }
                } else {
                    consultaInsumosHelper.getFiltro().setEstatusFiltro(consultaInsumosHelper.getConsultaInsumosBO().getLstEstatusValidos());
                }
                consultaInsumosHelper.getConsultaInsumosBO().setIsAciace(helperorigen.getConsultaInsumosBO().isIsAciace());
                consultaInsumosHelper.getConsultaInsumosBO().setCentralACPPCE(helperorigen.getConsultaInsumosBO().isCentralACPPCE());
                consultarInsumos();
                consultarXEstatusOSemaforo();
                mostrarPanelXCategorias();
            }
        }
    }

    protected void consultarInsumos() {
        try {
            getConsultaEjecutivaInsumosService().consultarInsumos(consultaInsumosHelper.getConsultaInsumosBO(), consultaInsumosHelper.getFiltro());
        } catch (ConsultaEjecutivaInsumosServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void addUnidadesDesagoAlFiltro(AraceDTO... unidadesDesahogo) {
        if (consultaInsumosHelper.getFiltro() != null && unidadesDesahogo != null) {
            consultaInsumosHelper.getFiltro().setUnidadAdmtvaDesahogoFiltro(new ArrayList<AraceDTO>());
            consultaInsumosHelper.getFiltro().getUnidadAdmtvaDesahogoFiltro().addAll(Arrays.asList(unidadesDesahogo));
        }
    }

    public void addEstatusAlFiltro(TipoEstatusEnum... tipoEstatus) {
        if (consultaInsumosHelper.getFiltro() != null && tipoEstatus != null) {
            consultaInsumosHelper.getFiltro().setEstatusFiltro(new ArrayList<TipoEstatusEnum>());
            consultaInsumosHelper.getFiltro().getEstatusFiltro().addAll(Arrays.asList(tipoEstatus));
        }
    }

    public void mostrarPanelUnidadesDesahogo() {
        limpiarFiltros();
        if (consultaInsumosHelper.getConsultaInsumosBO().isCentralACPPCE()) {
            consultaInsumosHelper.getConsultaInsumosBO().setIsAciace(false);
        }
        consultaInsumosHelper.setEmpleadoSeleccionado(null);
        consultaInsumosHelper.setSemaforoSeleccionado(null);
        consultaInsumosHelper.setEstatusSeleccionado(null);
        consultaInsumosHelper.setPlazoSeleccionado(null);
        consultaInsumosHelper.setUnidadAdminSeleccionada(null);
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(true);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(false);
        consultaInsumosHelper.setFlgEstatusFiltrados(false);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(false);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(false);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(false);

    }

    public void mostrarPanelEstatusSemaforo() {
        pantallaEstatusFiltrado = false;
        consultaXPlazo = false;
        consultaInsumosHelper.setEmpleadoSeleccionado(null);
        consultaInsumosHelper.setSemaforoSeleccionado(null);
        consultaInsumosHelper.setEstatusSeleccionado(null);
        consultaInsumosHelper.setPlazoSeleccionado(null);
        consultaInsumosHelper.setFlgPaginaEstatusSemaforos(false);
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(true);
        consultaInsumosHelper.setFlgEstatusFiltrados(false);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(false);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(false);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(false);
    }

    public void mostrarPanelEstatusFiltrado() {
        pantallaEstatusFiltrado = true;
        consultaInsumosHelper.setEstatusSeleccionado(null);
        consultaAServicioXEstatusOSemaforo();
        consultaInsumosHelper.setFlgPaginaEstatusSemaforos(false);
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(false);
        consultaInsumosHelper.setFlgEstatusFiltrados(true);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(false);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(false);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(false);
    }

    public void mostrarPanelXCategorias() {
        consultaInsumosHelper.setSegundoSemaforoSeleccionado(null);
        consultaInsumosHelper.setEmpleadoSeleccionado(null);
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(false);
        consultaInsumosHelper.setFlgEstatusFiltrados(false);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(true);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(false);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(false);
    }

    public void mostrarPanelInsumos() {
        limpiarFiltros();
        ordenarListaResult();
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(false);
        consultaInsumosHelper.setFlgEstatusFiltrados(false);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(false);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(true);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(false);
        consultaInsumosHelper.setLstInsumosFiltered(null);
    }

    public void mostrarPanelDetalleInsumo() {
        consultaInsumosHelper.setFlgMostrarUnidadesDesahogo(false);
        consultaInsumosHelper.setFlgMostrarEstatusSemaforo(false);
        consultaInsumosHelper.setFlgEstatusFiltrados(false);
        consultaInsumosHelper.setFlgMostrarTlbCategorias(false);
        consultaInsumosHelper.setFlgMostrarTlbInsumos(false);
        consultaInsumosHelper.setFlgMostrarDetalleInsumo(true);
    }

    public AraceDTO obtenerUnidadAdministrativaEmpleado() {
        boolean flgRule = consultaInsumosHelper.getConsultaInsumosBO().getEmpleadoConsulta() != null;
        if (flgRule) {
            for (DetalleEmpleadoDTO detalleEmp : consultaInsumosHelper.getConsultaInsumosBO().getEmpleadoConsulta().getDetalleEmpleado()) {
                flgRule = detalleEmp.getTipoEmpleado() != null && detalleEmp.getTipoEmpleado().equals(consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado());
                if (flgRule) {
                    return detalleEmp.getCentral();
                }
            }
        }
        return null;
    }

    public ConsultaEjecutivaInsumosService getConsultaEjecutivaInsumosService() {
        return consultaEjecutivaInsumosService;
    }

    public void setConsultaEjecutivaInsumosService(ConsultaEjecutivaInsumosService consultaEjecutivaInsumosService) {
        this.consultaEjecutivaInsumosService = consultaEjecutivaInsumosService;
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

    public ConsultaInsumosHelper getConsultaInsumosHelper() {
        return consultaInsumosHelper;
    }

    public void setConsultaInsumosHelper(ConsultaInsumosHelper consultaInsumosHelper) {
        this.consultaInsumosHelper = consultaInsumosHelper;
    }

    private void ordenarListaResult() {
        if (consultaInsumosHelper.getLstInsumosResult() != null) {
            try {
                Collections.sort(consultaInsumosHelper.getLstInsumosResult(), new Comparator<FecetInsumo>() {
                    @Override
                    public int compare(FecetInsumo insumoOrg, FecetInsumo insumoCom) {
                        int result = 0;
                        result = insumoCom.getPlazoRestante() - insumoOrg.getPlazoRestante();
                        if (result == 0) {
                            result = insumoOrg.getPrioridadDto().getValor().compareTo(insumoCom.getPrioridadDto().getValor());
                        }
                        return result;
                    }
                });
            } catch (Exception e) {
                logger.error("existe error al ordenar la lista de insumos");
            }
        }
    }

    public void consultarASubordinado() {
        String url;
        FiltroConsultaInsumos filtroCompartido = new FiltroConsultaInsumos();

        if (consultaInsumosHelper.getEmpleadoSeleccionado() != null) {
            filtroCompartido.setEmpleadoConsultaFiltro(consultaInsumosHelper.getEmpleadoSeleccionado().getKey());
            filtroCompartido.setDiasConcluirPlazo(consultaInsumosHelper.getPlazoSeleccionado());
            filtroCompartido.setSemaforoSeleccionado(consultaInsumosHelper.getSemaforoSeleccionado() != null ? consultaInsumosHelper.getSemaforoSeleccionado().getKey() : null);

            if (getSession().getAttribute(HELPER_ORIGEN) == null) {
                getSession().setAttribute(HELPER_ORIGEN, consultaInsumosHelper);
            }

            TipoEmpleadoEnum tipo = consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado();
            url = getUrlAndSetAttribute(tipo, consultaInsumosHelper.getConsultaInsumosBO(), filtroCompartido, true);
            if (!url.isEmpty()) {
                irAPantallaInsumos(url);
            }
        }
    }

    private String getUrlAndSetAttribute(TipoEmpleadoEnum tipoEmpleado, ConsultaEjecutivaInsumosBO consultaBO, FiltroConsultaInsumos filtro, boolean subordinadoUrl) {
        String url = "";
        String urlRelativa = URL_RELATIVA_INSUMOS;
        if (TipoEmpleadoEnum.CONSULTOR_INSUMOS.equals(tipoEmpleado)) {
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
        if (TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(tipoEmpleado)) {
            if (subordinadoUrl) {
                getSession().setAttribute(BO_SUBADMINISTRADOR, consultaBO);
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
        if (TipoEmpleadoEnum.VALIDADOR_INSUMOS.equals(tipoEmpleado)) {
            url = URL_SUBADMINISTRADOR;
            getSession().setAttribute(BO_SUBADMINISTRADOR, consultaBO);
            if (filtro != null) {
                getSession().setAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR, filtro);
            }
        }
        if (TipoEmpleadoEnum.USUARIO_INSUMOS.equals(tipoEmpleado)) {
            url = URL_USUARIO_ACIACE;
            getSession().setAttribute(BO_USUARIO_ACIACE, consultaBO);
        }

        return url;

    }

    public void regresarNivelDeEmpleado() {
        TipoEmpleadoEnum tipoEmpleado = consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado();
        String url = "";
        if (TipoEmpleadoEnum.ASIGNADOR_INSUMOS.equals(tipoEmpleado)) {
            url = URL_RELATIVA_INSUMOS.concat(URL_CENTRAL);
            getSession().setAttribute(BO_ADMINISTRADOR, null);
            getSession().setAttribute(FILTRO_CONSULTA_ADMINISTRADOR, null);

        }
        if (TipoEmpleadoEnum.VALIDADOR_INSUMOS.equals(tipoEmpleado)) {
            url = URL_RELATIVA_INSUMOS.concat(URL_ADMINISTRADOR);
            getSession().setAttribute(BO_SUBADMINISTRADOR, null);
            getSession().setAttribute(FILTRO_CONSULTA_SUBADMINISTRADOR, null);

        }
        irAPantallaInsumos(url);

    }

    public void regresarSubordinadosSemaforo() {
        if (pantallaEstatusFiltrado) {
            mostrarPanelEstatusFiltrado();
        } else {
            mostrarPanelEstatusSemaforo();
        }
    }

    public void consultarTotalEmpleados() {
        try {
            pantallaTotalEmpleados = true;
            consultaInsumosHelper.setLstInsumosResult(consultaInsumosHelper.getConsultaInsumosBO().getLstInsumoTotalEmpleado());
            consultaInsumosHelper.setLstInsumosFiltered(null);
            mostrarPanelInsumos();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarXEmpleadoSemaforoSeleccionado() {
        controlDeAcceso = true;
        pantallaTotalEmpleados = true;
        List<AraceDTO> unidadesAdminFiltro = new ArrayList<AraceDTO>();
        List<SemaforoEnum> lstSemaforosFiltro = new ArrayList<SemaforoEnum>();
        List<TipoEstatusEnum> lstEstatusFiltro = new ArrayList<TipoEstatusEnum>();
        EmpleadoDTO empleadoFiltro;

        try {
            if (consultaInsumosHelper.getUnidadAdminSeleccionada() != null) {
                unidadesAdminFiltro.add(consultaInsumosHelper.getUnidadAdminSeleccionada());
            } 
            if (consultaInsumosHelper.getSemaforoSeleccionado() != null || consultaInsumosHelper.getSegundoSemaforoSeleccionado() != null) {
                if (consultaInsumosHelper.getSemaforoSeleccionado() != null) {
                    lstSemaforosFiltro.add(consultaInsumosHelper.getSemaforoSeleccionado().getKey());
                }
                if (consultaInsumosHelper.getSegundoSemaforoSeleccionado() != null) {
                    lstSemaforosFiltro.add(consultaInsumosHelper.getSegundoSemaforoSeleccionado().getKey());
                }
            } else {
                for (Map.Entry<SemaforoEnum, Integer> key : consultaInsumosHelper.getConsultaInsumosBO().getDetalleXSemaforoFiltrado().entrySet()) {
                    lstSemaforosFiltro.add(key.getKey());
                }
            }

            if (consultaInsumosHelper.getEstatusSeleccionado() != null) {
                if (consultaInsumosHelper.getConsultaInsumosBO().isIsAciace()) {
                    lstEstatusFiltro.addAll(AciaceEstatusEnum.obtenerEstatusConsulta(consultaInsumosHelper.getEstatusSeleccionado().getKey()));
                } else {
                    lstEstatusFiltro.add(consultaInsumosHelper.getEstatusSeleccionado().getKey());
                }
            } else {
                lstEstatusFiltro = consultaInsumosHelper.getConsultaInsumosBO().getLstEstatusValidos();
            }

            if (consultaInsumosHelper.getEmpleadoSeleccionado() != null) {
                empleadoFiltro = consultaInsumosHelper.getEmpleadoSeleccionado().getKey();
            } else {
                empleadoFiltro = consultaInsumosHelper.getConsultaInsumosBO().getEmpleadoConsulta();
            }
            getConsultaEjecutivaInsumosService().getInsumosXEmpleadoSemaforoEstadoPlazo(consultaInsumosHelper.getConsultaInsumosBO(), unidadesAdminFiltro, empleadoFiltro, lstSemaforosFiltro, lstEstatusFiltro, consultaInsumosHelper.getPlazoSeleccionado());
            consultaInsumosHelper.setLstInsumosResult(consultaInsumosHelper.getConsultaInsumosBO().getLstInsumoMutiplesFiltros());

            mostrarPanelInsumos();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarXEstatusOSemaforo() {
        controlDeAcceso = true;
        try {
            consultaAServicioXEstatusOSemaforo();

            if ((consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) || (consultaInsumosHelper.getConsultaInsumosBO().getRolEmpleado().equals(TipoEmpleadoEnum.USUARIO_INSUMOS))) {
                mostrarPanelInsumos();
            } else {
                mostrarPanelXCategorias();
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarXSemaforo() {
        controlDeAcceso = true;
        try {
            consultaAServicioXEstatusOSemaforo();
            mostrarPanelEstatusFiltrado();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void consultaAServicioXEstatusOSemaforo() {
        try {
            boolean flgEstatus = consultaInsumosHelper.getEstatusSeleccionado() != null;
            boolean flgSemaforo = consultaInsumosHelper.getSemaforoSeleccionado() != null;

            if (flgEstatus || flgSemaforo) {
                getConsultaEjecutivaInsumosService().getInsumosXSemaforoEstatus(
                        consultaInsumosHelper.getConsultaInsumosBO(),
                        flgSemaforo ? consultaInsumosHelper.getSemaforoSeleccionado().getKey() : null,
                        flgEstatus ? consultaInsumosHelper.getEstatusSeleccionado().getKey() : null);

                consultaInsumosHelper.setLstInsumosResult(consultaInsumosHelper.getConsultaInsumosBO().getLstInsumosXFiltro());
            } else {
                consultaInsumosHelper.setLstInsumosResult(consultaInsumosHelper.getConsultaInsumosBO().getLstInsumoResult());
            }
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

    public boolean isConsultaXPlazo() {
        return consultaXPlazo;
    }

    public void setConsultaXPlazo(boolean consultaXPlazo) {
        this.consultaXPlazo = consultaXPlazo;
    }

    public boolean isPantallaTotalEmpleados() {
        return pantallaTotalEmpleados;
    }

    public void setPantallaTotalEmpleados(boolean pantallaTotalEmpleados) {
        this.pantallaTotalEmpleados = pantallaTotalEmpleados;
    }
    
    public List<ItemCombo> getLstPalzosParaConcluir() {
        List<ItemCombo> lstPlazos = new ArrayList<ItemCombo>();
        for (int i = PLAZO_INICIAL; i <= PLAZO_MAXIMO; i++) {
            ItemCombo item = new ItemCombo();
            item.setValor(i);

            switch (i) {
                case PLAZO_INICIAL:
                    item.setDescripcion("Sin atender");
                    break;
                case 0:
                    item.setDescripcion("Plazo vencido");
                    break;
                case UNO:
                    item.setDescripcion(i + DESCRIPCION_PLAZO);
                    break;
                default:
                    item.setDescripcion(i + DESCRIPCION_PLAZO + "s");
                    break;
            }

            lstPlazos.add(item);
        }
        return lstPlazos;
    }
    
    public void getReportData() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType("application/vnd.ms-excel.12");
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"ConsultaEstatus.xlsx\"");
        try {
            final List<FecetInsumo> registrosReporte = new ArrayList<FecetInsumo>();
            if (consultaInsumosHelper.getLstInsumosFiltered() != null && !consultaInsumosHelper.getLstInsumosFiltered().isEmpty()) {
                registrosReporte.addAll(consultaInsumosHelper.getLstInsumosFiltered());
            } else {
                registrosReporte.addAll(consultaInsumosHelper.getLstInsumosResult());
            }
            getConsultaEjecutivaInsumosService().generarReporte(registrosReporte, obtenerParametrosReporte(), externalContext.getResponseOutputStream());
        } catch (IOException e) {
            logger.error("Error al exportar el excel.", e);
            FacesUtil.addErrorMessage(null, "Ocurrio un error al generar el reporte", "");
        } catch (NoSeGeneroReporteException e) {
            logger.error("Error al exportar el excel.", e);
            FacesUtil.addErrorMessage(null, "Ocurrio un error al generar el reporte", "");
        }
        facesContext.responseComplete();
    }
    
    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formConsultaXEstatusCentral:tablaDetalleInsumo");
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
        requestContext.reset(":formConsultaXEstatusCentral:tablaDetalleInsumo");
        requestContext.update(":formConsultaXEstatusCentral");
    }

    private Map<String, Object> obtenerParametrosReporte() {
        Entry<TipoEstatusEnum, Integer> estatus = consultaInsumosHelper.getEstatusSeleccionado();
        Entry<SemaforoEnum, Integer> semaforo = consultaInsumosHelper.getSemaforoSeleccionado();
        Integer plazoSeleccionado = consultaInsumosHelper.getPlazoSeleccionado();
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("estatus", estatus != null ? estatus.getKey().getDescripcion() : null);
        parametro.put("semaforo", semaforo != null ? semaforo.getKey().getNombre() : null);
        parametro.put("plazo", plazoSeleccionado != null ? getLstPalzosParaConcluir().get(plazoSeleccionado + 1).getDescripcion() : null);
        return parametro;
    }

}
