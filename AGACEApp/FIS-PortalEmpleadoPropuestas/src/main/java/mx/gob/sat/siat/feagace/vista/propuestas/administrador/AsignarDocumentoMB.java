package mx.gob.sat.siat.feagace.vista.propuestas.administrador;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.NotificacionService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.asignar.AsignarDocumentoService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.AsignarDocumentoHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.PropuestaVO;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.SubAdministradorVO;

@ManagedBean(name = "asignarDocMB")
@ViewScoped
public class AsignarDocumentoMB extends BaseManagedBean {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{asignarDocumentoService}")
    private transient AsignarDocumentoService asignarDocumentoService;
    @ManagedProperty(value = "#{notificacionService}")
    private transient NotificacionService notificacionService;
    private List<PropuestaVO> listaProps;
    private List<SubAdministradorVO> listaSubAdmin;
    private SubAdministradorVO subSeleccionado;
    private List<PropuestaVO> listaSelecccionados;
    private List<FecetDocOrden> listaDocumentosOrden;
    private List<FecetOficio> oficiosPendientesDeFirmar;
    private String mensaje1;
    private String mensaje2;
    private boolean mostrarPanelPrincipal;
    private PropuestaVO expediente;
    private transient StreamedContent archivoSeleccionDescarga;
    private transient StreamedContent archivoDescargaDocumentacion;
    private FecetOficio oficioSeleccionado;
    private FecetDocOrden docSeleccionado;
    private EmpleadoDTO empleadoLogueado;

    @PostConstruct
    public void init() {
        empleadoLogueado = asignarDocumentoService.verificarTipoEmpleadoAdministrador(getRFCSession());

        if (empleadoLogueado != null) {
            logger.debug("ID_EMPLEADO::: ?", empleadoLogueado.getIdEmpleado());
            this.listaProps = new ArrayList<PropuestaVO>();
            this.listaSubAdmin = new ArrayList<SubAdministradorVO>();
            this.expediente = new PropuestaVO();
            List<FecetPropuesta> lista = asignarDocumentoService.obtenerPropuestasPendientesDeValidacion(
                    new BigDecimal(empleadoLogueado.getDetalleEmpleado().get(0).getCentral().getIdArace()));
            this.listaProps = AsignarDocumentoHelper.llenarListaDeDto(lista);
            for (PropuestaVO propVO : listaProps) {
                propVO.setPresuntiva(asignarDocumentoService.calcularPresuntivaDePropuesta(propVO.getIdPropuesta()));
            }

            Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = empleadoLogueado
                    .getSubordinados();
            Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados
                    .get(TipoEmpleadoEnum.ADMINISTRADOR_EMISION_ORDENES);
            List<EmpleadoDTO> lstSubs = sub.get(TipoEmpleadoEnum.SUBADMINISTRADOR_EMISION_ORDENES);

            this.listaSubAdmin = AsignarDocumentoHelper.obtenerSubAministradores(lstSubs);

            for (SubAdministradorVO subAdmin : this.listaSubAdmin) {
                subAdmin.setPropAsignadas(
                        asignarDocumentoService.obtenerCantidadDePropuestasAsignadas(subAdmin.getRfc()).intValue());
            }
            mostrarPanelPrincipal = true;
            subSeleccionado = new SubAdministradorVO();
        } else {
            mostrarPanelPrincipal = false;
            this.informeErrorSession(new NegocioException("No se encontro el Empleado solicitado"));

        }

    }

    private void informeErrorSession(Exception e) {
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

    public void validaCampos(ActionEvent actionEvent) {
        this.listaSelecccionados = new ArrayList<PropuestaVO>();
        logger.debug("SubAdmin: " + subSeleccionado.getIdSubAdmin());

        for (PropuestaVO p : listaProps) {
            logger.debug("p.isSeleccionado()...." + p.isSeleccionado());
            if (p.isSeleccionado()) {
                this.listaSelecccionados.add(p);
            }
        }
        if (listaSelecccionados.isEmpty()) {
            addErrorMessage(null, getMessageResourceString("mensaje.validar.informacion"),
                    getMessageResourceString("mensaje.validar.propuesta"));
        } else if (subSeleccionado.getIdSubAdmin().equals(BigDecimal.ZERO)) {
            addErrorMessage(null, getMessageResourceString("mensaje.validar.informacion"),
                    getMessageResourceString("mensaje.validar.subadministrador"));
        } else {
            subSeleccionado = AsignarDocumentoHelper.obtenerSubAdmin(subSeleccionado.getIdSubAdmin(), listaSubAdmin);
            mensaje1 = AsignarDocumentoHelper.crearMensaje1(this.listaSelecccionados.size(),
                    subSeleccionado.getNombre());
            mensaje2 = AsignarDocumentoHelper.crearMensaje2(this.listaSelecccionados.size(),
                    subSeleccionado.getNombre());
            RequestContext.getCurrentInstance().execute("PF('msg1').show();");
        }

    }

    public void asignarPropuesta() {
        for (PropuestaVO pVO : listaSelecccionados) {
            logger.debug("Id=== ", pVO.getIdPropuesta());
            asignarDocumentoService
                    .actualizarPropuesta(pVO.getIdPropuesta(),
                            (new BigDecimal(
                                    TipoEstatusEnum.PROPUESTA_REGISTRO_ASIGNADO_SUBADMINISTRADOR_EMISION_ODENES.getId())
                            .intValueExact()),
                            subSeleccionado.getRfc());
            FecebAccionPropuesta accion = new FecebAccionPropuesta();
            accion.setFechaHora(new Date());
            accion.setIdPropuesta(pVO.getIdPropuesta());
            accion.setIdAccion(AccionesFuncionarioEnum.VALIDACION_EMISION_ORDENES_11.getIdAccion());
            accion.setIdEmpleado(empleadoLogueado.getIdEmpleado());
            asignarDocumentoService.insertaAccion(accion);
            asignarDocumentoService.updateAccionIdPropuesta(pVO.getIdPropuesta());
            listaProps.remove(pVO);
        }
        List<String> destinatarios = new ArrayList<String>();
        for (SubAdministradorVO subAdmin : listaSubAdmin) {
            if (subAdmin.getRfc().equals(subSeleccionado.getRfc())) {
                subAdmin.setPropAsignadas(subAdmin.getPropAsignadas() + listaSelecccionados.size());

            }
        }
        destinatarios.add(subSeleccionado.getCorreo());
        subSeleccionado = new SubAdministradorVO();
        enviarNotificacion(destinatarios, listaSelecccionados);
        RequestContext.getCurrentInstance().execute("PF('msg2').show();");

    }

    public void cargaDocumentosOrden() {
        AgaceOrden orden = asignarDocumentoService.obtenerOrdenByIdPropuesta(getExpediente().getIdPropuesta());
        setListaDocumentosOrden(asignarDocumentoService.obtenerDocOrden(orden.getIdOrden()));
        setOficiosPendientesDeFirmar(asignarDocumentoService.getOficiosPorFirmar(orden.getIdOrden()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosOrden').show();");
    }

    private void enviarNotificacion(List<String> destinatarios, List<PropuestaVO> propuestasSeleccionadas) {

        HashSet<String> dest = new HashSet<String>(destinatarios);

        for (PropuestaVO propuesta : propuestasSeleccionadas) {

            String[] rfcNotificacion = new String[Constantes.ENTERO_CUATRO];

            rfcNotificacion[Constantes.ENTERO_CERO] = propuesta.getRfcCreacion();
            rfcNotificacion[Constantes.ENTERO_UNO] = propuesta.getRfcAuditor();
            rfcNotificacion[Constantes.ENTERO_DOS] = propuesta.getRfcFirmante();
            rfcNotificacion[Constantes.ENTERO_TRES] = propuesta.getRfcAdminEmision();
            asignarDocumentoService.enviarCorreo(rfcNotificacion, propuesta.getIdRegistro(), dest,
                    propuesta.getIdArace());

        }

    }

    /**
     * Metodo getArchivoSeleccionDescarga
     *
     * @return StreamedContent Regresa el path del archivo que se desea
     * descargar
     */
    public StreamedContent getArchivoSeleccionDescarga() {
        archivoSeleccionDescarga = getArchivoDescarga(getDocSeleccionado().getRutaArchivo(),
                getDocSeleccionado().getNombreArchivo());
        return archivoSeleccionDescarga;
    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        archivoDescargaDocumentacion = getArchivoDescarga(getOficioSeleccionado().getRutaArchivo(),
                getOficioSeleccionado().getNombreArchivo());
        return archivoDescargaDocumentacion;
    }

    public StreamedContent getArchivoDescarga(final String url, final String nombreDescarga) {
        StreamedContent archivo = null;
        if (url != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(CargaArchivoUtilPropuestas.limpiarPathArchivo(url)),
                        CargaArchivoUtilPropuestas.obtenContentTypeArchivo(nombreDescarga),
                        CargaArchivoUtilPropuestas.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]", e.getMessage());
                addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    public void setListaProps(List<PropuestaVO> listaProps) {
        this.listaProps = listaProps;
    }

    public List<PropuestaVO> getListaProps() {
        return listaProps;
    }

    public void setListaSubAdmin(List<SubAdministradorVO> listaSubAdmin) {
        this.listaSubAdmin = listaSubAdmin;
    }

    public List<SubAdministradorVO> getListaSubAdmin() {
        return listaSubAdmin;
    }

    public void setSubSeleccionado(SubAdministradorVO subSeleccionado) {
        this.subSeleccionado = subSeleccionado;
    }

    public SubAdministradorVO getSubSeleccionado() {
        return subSeleccionado;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setAsignarDocumentoService(AsignarDocumentoService asignarDocumentoService) {
        this.asignarDocumentoService = asignarDocumentoService;
    }

    public AsignarDocumentoService getAsignarDocumentoService() {
        return asignarDocumentoService;
    }

    public void setMostrarPanelPrincipal(boolean mostrarPanelPrincipal) {
        this.mostrarPanelPrincipal = mostrarPanelPrincipal;
    }

    public boolean isMostrarPanelPrincipal() {
        return mostrarPanelPrincipal;
    }

    public void setListaSelecccionados(List<PropuestaVO> listaSelecccionados) {
        this.listaSelecccionados = listaSelecccionados;
    }

    public List<PropuestaVO> getListaSelecccionados() {
        return listaSelecccionados;
    }

    public PropuestaVO getExpediente() {
        return expediente;
    }

    public void setExpediente(PropuestaVO expediente) {
        this.expediente = expediente;
    }

    public List<FecetDocOrden> getListaDocumentosOrden() {
        return listaDocumentosOrden;
    }

    public void setListaDocumentosOrden(List<FecetDocOrden> listaDocumentosOrden) {
        this.listaDocumentosOrden = listaDocumentosOrden;
    }

    public List<FecetOficio> getOficiosPendientesDeFirmar() {
        return oficiosPendientesDeFirmar;
    }

    public void setOficiosPendientesDeFirmar(List<FecetOficio> oficiosPendientesDeFirmar) {
        this.oficiosPendientesDeFirmar = oficiosPendientesDeFirmar;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetDocOrden getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setDocSeleccionado(FecetDocOrden docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public void setArchivoSeleccionDescarga(StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public void setArchivoDescargaDocumentacion(StreamedContent archivoDescargaDocumentacion) {
        this.archivoDescargaDocumentacion = archivoDescargaDocumentacion;
    }

    public NotificacionService getNotificacionService() {
        return notificacionService;
    }

    public void setNotificacionService(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }
}
