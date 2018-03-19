package mx.gob.sat.siat.feagace.vista.propuestas;

import static mx.gob.sat.siat.feagace.negocio.ordenes.constants.FirmadoOrdenesConstants.EXTENSION_ARCHIVO_ORDEN_PDF;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.ConsultaPropuestasOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetPropuestaService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;

@ManagedBean(name = "consultaPropuestasOrdenesMB")
@ViewScoped
public class ConsultaPropuestasOrdenesMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 9076790559498962435L;

    @ManagedProperty(value = "#{consultaPropuestasOrdenesService}")
    private transient ConsultaPropuestasOrdenesService consultaPropuestasOrdenesService;

    @ManagedProperty(value = "#{fecetPropuestaService}")
    private transient FecetPropuestaService fecetPropuestaService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    private static final String URL_CONSULTA_PROPUESTAS = "/faces/propuestas/jefeDpto/consultaPropuestasOrdenes.jsf";
    private static final Logger LOGGER = Logger.getLogger(ConsultaPropuestasOrdenesMB.class);
    private EmpleadoDTO empleado;

    private List<AgaceOrden> propuestasOrdenenes;
    private String idRegistroPropuestaSeleccionada;
    private AgaceOrden ordenSeleccionada;
    private FecetPropuesta propuestaSeleccionada;
    private FecetDocOrden expediente;
    private List<FecetOficio> listaOficiosAdmin;
    private FecetOficio docOficioSeleccionado;
    private List<String> listaPrioridadSugerida;

    private boolean orden;

    @PostConstruct
    public void init() {

        try {
            // validamos que el usuario loggeado sea Jefe de Departamento o
            // Enlace
            try {
                obtnerEmpleado();
                if (!getEmpleadoService().validarExistenciaTipoEmpleado(empleado, TipoEmpleadoEnum.JEFE_DE_DEPARTAMENTO)
                        && !getEmpleadoService().validarExistenciaTipoEmpleado(empleado, TipoEmpleadoEnum.ENLACE)) {
                    informeErrorSession(
                            new NegocioException("No se encontro el usuario JEFE DE DEPARTAMENTO / ENLACE logueado"));
                }
            } catch (EmpleadoServiceException e) {
                informeErrorSession(new NegocioException("Error al ejecturar el Servicio de Empleados"));
            }

            this.propuestasOrdenenes = new ArrayList<AgaceOrden>();
            this.propuestasOrdenenes = consultaPropuestasOrdenesService
                    .consultarPropuestasOrdenes(this.empleado.getIdEmpleado());

            listaPrioridadSugerida = buscarPrioridadPropuesta(this.propuestasOrdenenes);

        } catch (NegocioException e) {
            LOGGER.error("Error en el ManagedBean Consulta Propuestas Ordenes", e);
        }
    }

    public List<String> buscarPrioridadPropuesta(List<AgaceOrden> propuestasOrdenes) {
        List<String> listaPrioridad = new ArrayList<String>();

        if (propuestasOrdenes != null && !propuestasOrdenes.isEmpty()) {
            for (AgaceOrden propuestaOrden : propuestasOrdenes) {
                if (!listaPrioridad.contains(propuestaOrden.getPrioridadSugerida())) {
                    listaPrioridad.add(propuestaOrden.getPrioridadSugerida());
                }
            }
            Collections.sort(listaPrioridad);
        }
        return listaPrioridad;
    }

    public void obtnerEmpleado() throws EmpleadoServiceException {
        empleado = null;
        empleado = getEmpleadoService().getEmpleadoCompleto(getRFCSession());
    }

    public void redirecciona() throws IOException {

        getSession().setAttribute("vieneDeConsultaPropuestasOrdenes", Boolean.TRUE);
        getSession().setAttribute("urlRegreso", URL_CONSULTA_PROPUESTAS);

        String urlRedireccion = "/faces/propuestas/auditor/detallePropAsignadas.jsf?faces-redirect=true";
        buscaPropuestaSel();
        buscaInfoContribuyente();

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        RequestContext.getCurrentInstance().execute("PF('docDialog').hide();");
        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    public void redireccionaOrden() throws IOException {
        getSession().setAttribute("origenConsulta", "consultarPropuestasOrdenes");
        getSession().setAttribute("ordenConsultaSeleccionada", convertToOrdenConsulta(getOrdenSeleccionada()));
        getSession().setAttribute("userProfile", super.getUserProfile());

        getSession().setAttribute("urlRegreso", URL_CONSULTA_PROPUESTAS);

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);
    }

    private void buscaPropuestaSel() {
        try {
            propuestaSeleccionada = fecetPropuestaService
                    .obtenerPropuestaByidPropuesta(new BigDecimal(getIdRegistroPropuestaSeleccionada()));
            getSession().setAttribute("propuestaSelAnalizar", propuestaSeleccionada);
        } catch (NegocioException e) {
            LOGGER.error(e.getCause());
        }
    }

    private void buscaInfoContribuyente() {
        try {
            getSession().setAttribute("fecetContribuyente",
                    contribuyenteService.obtenerContribuyente(propuestaSeleccionada.getIdContribuyente()));
        } catch (Exception e) {
            LOGGER.error(e.getCause());
        }
    }

    public void informeErrorSession(Exception e) {
        try {
            HttpSession session;
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            session.setAttribute("mensaje", e);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/error/indexError.jsf");
        } catch (IOException f) {
            logger.error("No se pudo redireccionar a la pagina de error");
        }
    }

    public void cargaDocumentosOrdenOficio() {
        setExpediente(getSeguimientoOrdenesService().obtenerExpedienteOrden(getOrdenSeleccionada()));
        setListaOficiosAdmin(
                getSeguimientoOrdenesService().getOficiosAdministrables(getOrdenSeleccionada().getIdOrden()));
        RequestContext.getCurrentInstance().execute("PF('docDialog').show();");
    }

    public void limpiarOficiosAdmin() {
        setListaOficiosAdmin(new ArrayList<FecetOficio>());
        setExpediente(new FecetDocOrden());
    }

    public StreamedContent getArchivoSeleccionDescarga() {
        final String nombre = getOrdenSeleccionada().getNumeroOrden() + EXTENSION_ARCHIVO_ORDEN_PDF;
        final String path = RutaArchivosUtilPropuestas.armarRutaDocOrden(getOrdenSeleccionada()) + nombre;

        return getDescargaArchivo(path, nombre);
    }

    public StreamedContent getArchivoOficioSeleccionDescarga() {
        final String nombre = getDocOficioSeleccionado().getNombreArchivo();
        final String path = getDocOficioSeleccionado().getRutaArchivo();

        return getDescargaArchivo(path, nombre);
    }

    /**
     * Convierte el objeto de dominio AgaceOrden a OrdenConsulta para presentar
     * en la vista.
     *
     * @param listAgaceOrden
     * @return
     */
    private OrdenConsultaDTO convertToOrdenConsulta(AgaceOrden agaceOrden) {

        OrdenConsultaDTO ordenConsultaDTO = new OrdenConsultaDTO();
        ordenConsultaDTO.setIdOrden(agaceOrden.getIdOrden());
        ordenConsultaDTO.setIdPropuesta(agaceOrden.getIdPropuesta());
        ordenConsultaDTO.setIdRegistro(agaceOrden.getIdRegistroPropuesta());
        ordenConsultaDTO.setNumeroOrden(agaceOrden.getNumeroOrden());
        ordenConsultaDTO.setRutaArchivo(RutaArchivosUtilPropuestas.armarRutaDocOrden(agaceOrden));
        ordenConsultaDTO.setNombreArchivo(PropuestaVistaCargaArchivosUtil.getNombreArchivoOrdenPdf(agaceOrden));
        ordenConsultaDTO.setIdMetodo(agaceOrden.getIdMetodo());
        ordenConsultaDTO.setMetodo(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getDescripcion());
        ordenConsultaDTO.setMetodoSiglas(TipoMetodoEnum.getById(agaceOrden.getIdMetodo().longValue()).getSiglas());
        ordenConsultaDTO.setRfc(agaceOrden.getFecetContribuyente().getRfc());
        ordenConsultaDTO.setNombreContribuyente(agaceOrden.getFecetContribuyente().getNombre());
        ordenConsultaDTO.setEstatus(agaceOrden.getFececEstatus().getDescripcion());
        ordenConsultaDTO.setPlazoRestante(agaceOrden.getDiasRestantesPlazo());
        ordenConsultaDTO.setDescripcionPlazoRestante(agaceOrden.getDescripcionPlazoRestante());
        ordenConsultaDTO.setIdFirmante(agaceOrden.getIdFirmante());
        ordenConsultaDTO.setIdAuditor(agaceOrden.getIdAuditor());
        ordenConsultaDTO.setIdContribuyente(agaceOrden.getIdContribuyente());

        return ordenConsultaDTO;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoDTO empleado) {
        this.empleado = empleado;
    }

    public List<AgaceOrden> getPropuestasOrdenenes() {
        return propuestasOrdenenes;
    }

    public void setPropuestasOrdenenes(List<AgaceOrden> propuestasOrdenenes) {
        this.propuestasOrdenenes = propuestasOrdenenes;
    }

    public String getIdRegistroPropuestaSeleccionada() {
        return idRegistroPropuestaSeleccionada;
    }

    public void setIdRegistroPropuestaSeleccionada(String idRegistroPropuestaSeleccionada) {
        this.idRegistroPropuestaSeleccionada = idRegistroPropuestaSeleccionada;
    }

    public boolean isOrden() {
        return orden;
    }

    public void setOrden(boolean orden) {
        this.orden = orden;
    }

    public ConsultaPropuestasOrdenesService getConsultaPropuestasOrdenesService() {
        return consultaPropuestasOrdenesService;
    }

    public void setConsultaPropuestasOrdenesService(ConsultaPropuestasOrdenesService consultaPropuestasOrdenesService) {
        this.consultaPropuestasOrdenesService = consultaPropuestasOrdenesService;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setOrdenSeleccionada(AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public FecetPropuestaService getFecetPropuestaService() {
        return fecetPropuestaService;
    }

    public void setFecetPropuestaService(FecetPropuestaService fecetPropuestaService) {
        this.fecetPropuestaService = fecetPropuestaService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public FecetDocOrden getExpediente() {
        return expediente;
    }

    public void setExpediente(FecetDocOrden expediente) {
        this.expediente = expediente;
    }

    public List<FecetOficio> getListaOficiosAdmin() {
        return listaOficiosAdmin;
    }

    public void setListaOficiosAdmin(List<FecetOficio> listaOficiosAdmin) {
        this.listaOficiosAdmin = listaOficiosAdmin;
    }

    public FecetOficio getDocOficioSeleccionado() {
        return docOficioSeleccionado;
    }

    public void setDocOficioSeleccionado(FecetOficio docOficioSeleccionado) {
        this.docOficioSeleccionado = docOficioSeleccionado;
    }

    public List<String> getListaPrioridadSugerida() {
        return listaPrioridadSugerida;
    }

    public void setListaPrioridadSugerida(List<String> listaPrioridadSugerida) {
        this.listaPrioridadSugerida = listaPrioridadSugerida;
    }

}
