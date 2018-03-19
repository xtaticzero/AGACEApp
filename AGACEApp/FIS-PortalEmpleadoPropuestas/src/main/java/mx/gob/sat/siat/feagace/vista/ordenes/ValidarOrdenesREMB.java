package mx.gob.sat.siat.feagace.vista.ordenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaPorValidar;
import mx.gob.sat.siat.feagace.modelo.enums.LeyendasPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.util.MetodosGenericos;

/**
 *
 * @author Luis Rodriguez
 * @version 1.1
 */
@ManagedBean(name = "validarOrdenesRE")
@SessionScoped
public class ValidarOrdenesREMB extends ValidarOrdenesAbstractREMB {

    private static final long serialVersionUID = -8645665589333000708L;

    static final String EXITO = "label.exito.validar.enviar";
    static final String CONSULTAR_ORDENES = "Error al consultar las ordenes por validar ";
    static final String SIN_SELECCION = "error.lista.sin.seleccion";
    static final String SIN_RECHAZO_DESCRIPCION = "error.rechazo.orden.sin.descripcion";
    static final String ORDEN_RECHAZO_ESTADO = "error.rechazo.orden.estatus";
    static final String TITULO_VALIDAR = "titulo.panel.ordenes.validar";
    static final String MAIL_VALIDAR_PROPUESTA = "email.notificacion.asignacion.a.administrador.ppce";

    private transient StreamedContent archivoSeleccionDescarga;
    private transient HttpSession session = null;
    private transient StreamedContent archivoDescargaDocumentacion;
    private BigDecimal idEmpleado = BigDecimal.ZERO;

    @PostConstruct
    public void init() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        setListaValidarOrdenesSeleccionadas(new ArrayList<PropuestaPorValidar>());
        setListaOrdenesValidar(new ArrayList<AgaceOrden>());
    }

    public String configuraOrdenesValidar() {
        setMetodoSeleccionado(new BigDecimal(MetodosGenericos.getParametro("metodo").toString()));
        setAccionOrigen(null);
        setTitulo(getMessageResourceString(TITULO_VALIDAR));
        return cargarOrdenesValidar();
    }

    public String cargarOrdenesValidar() {

        EmpleadoDTO empleadoLogueado = null;

        try {
            empleadoLogueado = getOrdenesPorValidarReService().obtenerDatosFirmanteSuplente(getRFCSession());
            idEmpleado = empleadoLogueado.getIdEmpleado();
        } catch (Exception e) {
            addErrorMessage(null, "No se encuentra el empleado " + getRFCSession());
        }

        boolean isSuplente = (Boolean) session.getAttribute("essuplente");
        if (isSuplente) {
            BigDecimal idSuplente = new BigDecimal(session.getAttribute("suplente").toString());
            setListaPropuestaPorValidar(getOrdenesPorValidarReService().getOrdenesPorValidar(
                    new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_VALIDACION.getId()), idSuplente,
                    getMetodoSeleccionado(), getAccionOrigen(), empleadoLogueado));
        } else {
            setListaPropuestaPorValidar(getOrdenesPorValidarReService().getOrdenesPorValidar(
                    new BigDecimal(TipoEstatusEnum.PROPUESTA_PENDIENTE_VALIDACION.getId()), idEmpleado,
                    getMetodoSeleccionado(), getAccionOrigen(), empleadoLogueado));
        }
        logger.info("Numero de registros: " + getListaPropuestaPorValidar().size());
        return "validarOrdenes?faces-redirect=true";

    }

    public void validarSeleccion(ActionEvent actionEvent) {
        logger.info("Entro a validarSeleccion...");
        if (getListaValidarOrdenesSeleccionadas() != null && !getListaValidarOrdenesSeleccionadas().isEmpty()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('aceptarOrdenesSeleccionadas').show();");
        } else {
            addErrorMessage(null, getMessageResourceString(SIN_SELECCION));
        }
    }

    public void validarSeleccionTodas(ActionEvent actionEvent) {
        logger.info("Entro a validarSeleccion...");
        if (getListaValidarOrdenesSeleccionadas() != null && !getListaValidarOrdenesSeleccionadas().isEmpty()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('aceptarOrdenesTodasSeleccionadas').show();");
        } else {
            addErrorMessage(null, getMessageResourceString(SIN_SELECCION));
        }
    }

    public void validarRechazosSeleccionados() {
        logger.info("Entro a validar los rechazos");
        AgaceOrden orden = getOrdenesPorValidarReService()
                .obtenerOrdenByIdPropuesta(new BigDecimal(getPropuestaSeleccionadaParaRech().getIdPropuesta()));
        setListaDocumentosOrden(getOrdenesPorValidarReService().obtenerDocOrden(orden.getIdOrden()));
        setOficiosPendientesDeFirmar(getOrdenesPorValidarReService().getOficiosPorFirmar(orden.getIdOrden()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogNoAprobarDocumentosOrden').show();");
    }

    public void validarOrdenesSeleccionadas() {
        logger.info("en validarOrdenesSeleccionadas ");
        if (getListaValidarOrdenesSeleccionadas() != null && !getListaValidarOrdenesSeleccionadas().isEmpty()) {
            FecetPropuesta propuesta;
            BigDecimal central;
            String rfcAdministrador = "";
            // Se recupera el id central de la propuesta
            propuesta = getOrdenesPorValidarReService()
                    .getPropuesta(getPropuestaSeleccionadaParaEnviar().getIdPropuesta());
            // se crea el dto historico
            FecebAccionPropuesta accionPropuesta = getValidarOrdenesHelper()
                    .creaAccionPropuestaAprobar(getPropuestaSeleccionadaParaEnviar(), idEmpleado, propuesta);

            // Se actualiza el estatus de la propuesta, se actualiza la accion y
            // se inserta el historico            
            getOrdenesPorValidarReService().enviarValidarOrdenes(getPropuestaSeleccionadaParaEnviar(), accionPropuesta, propuesta);

            if (propuesta.getIdArace().longValue() == TipoAraceEnum.ACOECE.getId()
                    || propuesta.getIdArace().longValue() == TipoAraceEnum.ACAOCE.getId()) {
                central = new BigDecimal(Constantes.ENTERO_NOVENTAOCHO);
            } else {
                central = propuesta.getIdArace();
            }
            // Se recupera el administrador

            List<EmpleadoDTO> empleadoDetalle = getOrdenesPorValidarReService().obtenerAdministradorEmision(central);

            if (empleadoDetalle != null && !empleadoDetalle.isEmpty()) {
                rfcAdministrador = empleadoDetalle.get(0).getRfc();
            } else {
                rfcAdministrador = "";
            }

            logger.info("Se va a insertar RFC de administrador: {} propuesta: {}", rfcAdministrador,
                    getPropuestaSeleccionadaParaEnviar().getIdPropuesta());
            getOrdenesPorValidarReService().actualizarRfcAdministrador(
                    new BigDecimal(getPropuestaSeleccionadaParaEnviar().getIdPropuesta()), rfcAdministrador);
            addMessage(null, getMessageResourceString(EXITO));
            cargarOrdenesValidar();
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formValidarPropuestas");
            setTotalValidar(getListaOrdenesValidar().size());
            // Metodo para mandar correo
            if (propuesta.getRfcSubadministrador() != null) {
                getOrdenesPorValidarReService().enviarCorreoAprobar(getPropuestaSeleccionadaParaEnviar(),
                        LeyendasPropuestasEnum.ASIGNACION_VERIFICACION_PROCEDENCIA_DOCTO.getIdLeyenda());
            } else {
                getOrdenesPorValidarReService().enviarCorreoAprobar(getPropuestaSeleccionadaParaEnviar(),
                        LeyendasPropuestasEnum.ASIGNACION_PROPUESTA_A_ADMINISTRADOR.getIdLeyenda());   
            }
        } else {
            addErrorMessage(null, getMessageResourceString(SIN_SELECCION));
        }
    }

    public void validarOrdenesTodasSeleccionadas() {
        logger.info("en validarOrdenesSeleccionadasTodas ");
        if (getListaValidarOrdenesSeleccionadas() != null && !getListaValidarOrdenesSeleccionadas().isEmpty()) {
            for (PropuestaPorValidar propuestaSeleccionadaXEnviar : getListaValidarOrdenesSeleccionadas()) {
                FecetPropuesta propuesta;
                BigDecimal central;
                String rfcAdministrador;
                // Se recupera el id central de la propuesta
                propuesta = getOrdenesPorValidarReService().getPropuesta(propuestaSeleccionadaXEnviar.getIdPropuesta());
                // se crea el dto historico
                FecebAccionPropuesta accionPropuesta = getValidarOrdenesHelper()
                        .creaAccionPropuestaAprobar(propuestaSeleccionadaXEnviar, idEmpleado, propuesta);
                // Se actualiza el estatus de las ordenes, se actualiza la
                // accion y se inserta el historico
                getOrdenesPorValidarReService().enviarValidarOrdenes(propuestaSeleccionadaXEnviar, accionPropuesta, propuesta);

                if (propuesta.getIdArace().longValue() == TipoAraceEnum.ACOECE.getId()
                        || propuesta.getIdArace().longValue() == TipoAraceEnum.ACAOCE.getId()) {
                    central = new BigDecimal(Constantes.ENTERO_NOVENTAOCHO);
                } else {
                    central = propuesta.getIdArace();
                }
                // Se recupera el administrador

                List<EmpleadoDTO> empleadoDetalle = getOrdenesPorValidarReService()
                        .obtenerAdministradorEmision(central);

                if (empleadoDetalle != null && !empleadoDetalle.isEmpty()) {
                    rfcAdministrador = empleadoDetalle.get(0).getRfc();
                } else {
                    rfcAdministrador = "";
                }

                // Se inserta el rfc del administrador en propuesta
                logger.info("Se va a insertar RFC de administrador: {} propuesta: {}", rfcAdministrador,
                        propuestaSeleccionadaXEnviar.getIdPropuesta());
                getOrdenesPorValidarReService().actualizarRfcAdministrador(
                        new BigDecimal(propuestaSeleccionadaXEnviar.getIdPropuesta()), rfcAdministrador);
                cargarOrdenesValidar();
                RequestContext context = RequestContext.getCurrentInstance();
                context.update("formValidarPropuestas");
                setTotalValidar(getListaOrdenesValidar().size());
                // Metodo para mandar correo
                if (propuesta.getRfcSubadministrador() != null) {
                    getOrdenesPorValidarReService().enviarCorreoAprobar(propuestaSeleccionadaXEnviar,
                            LeyendasPropuestasEnum.ASIGNACION_VERIFICACION_PROCEDENCIA_DOCTO.getIdLeyenda());
                } else {
                    getOrdenesPorValidarReService().enviarCorreoAprobar(propuestaSeleccionadaXEnviar,
                            LeyendasPropuestasEnum.ASIGNACION_PROPUESTA_A_ADMINISTRADOR.getIdLeyenda());   
                }
            }
            addMessage(null, getMessageResourceString(EXITO));
        } else {
            addErrorMessage(null, getMessageResourceString(SIN_SELECCION));
        }

    }

    public void mostrarConfirmarRechazo() {
        if (getRechazoDescripcion().trim().equals("")) {
            addErrorMessage("formValidarPropuestas:rechazoDescripcion", "Debes ingresar una descripcion");
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('rechazarOrdenesSeleccionadas').show();");
        }
    }

    /**
     * Metodo que modifica el estatus de la propuesta y la accion, inserta la
     * accion historica y manda correo de aviso
     */
    public void enviarPropuestasRechazadas() {
        logger.info("Voy a cambiar el estatus de la propuesta y mandar correo...");
        try {
            if (getRechazoDescripcion().trim().equals("")) {
                addErrorMessage("formValidarPropuestas:rechazoDescripcion", "Debes ingresar una descripcion");
                return;
            } else {
                // construir el objeto FecebAccionPropuesta
                FecebAccionPropuesta accionPropuesta = getValidarOrdenesHelper().creaAccionPropuestaNoAprobar(
                        getPropuestaSeleccionadaParaRech(), getRechazoDescripcion(), idEmpleado);
                // se actualiza el estatus de la propuesta y de la accion, se
                // inserta la accion historica
                getOrdenesPorValidarReService().enviarRechazoOrden(getPropuestaSeleccionadaParaRech(), accionPropuesta);
                // se actualiza bln_estatus del docto orden o del oficio
                if (getListaSeleccionDocumentosOrden() != null && !getListaSeleccionDocumentosOrden().isEmpty()) {
                    FecetRechazoOrden rechazoOrden = getValidarOrdenesHelper().creaRechazoOrdenDto(
                            getListaSeleccionDocumentosOrden().get(0).getIdOrden(),
                            getListaSeleccionDocumentosOrden().get(0).getIdDocOrden(), idEmpleado);
                    getOrdenesPorValidarReService().inactivaDoctoOrden(rechazoOrden);
                }
                if (getListaSeleccionOficiosPendientes() != null && !getListaSeleccionOficiosPendientes().isEmpty()) {
                    for (FecetOficio oficio : getListaSeleccionOficiosPendientes()) {
                        FecetRechazoOficio rechazoOficio = getValidarOrdenesHelper()
                                .creaRechazoOficioDto(oficio.getIdOficio(), idEmpleado);
                        getOrdenesPorValidarReService().inactivaOficio(rechazoOficio);
                    }
                }
                getOrdenesPorValidarReService().enviarCorreoNoAprobar(getPropuestaSeleccionadaParaRech(),
                        LeyendasPropuestasEnum.RECHAZO_PROPUESTA_POR_ADECUAR.getIdLeyenda());
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogRechazoNotificado').show();");
                context.execute("PF('rechazarOrdenesSeleccionadas').hide();");
                context.execute("PF('motivoRechazo').hide();");
                cargarOrdenesValidar();
                context.update("formValidarPropuestas");
                setRechazoDescripcion(null);
            }
        } catch (Exception e) {
            logger.error("Hubo un problema al cambiar el estatus de la propuesta y la accion" + e.getMessage());
        }
    }

    public String regresarPrincipal() {
        return "indexValidarFirmarOrdenes?faces-redirect=true";
    }

    /**
     * Metodo getArchivoSeleccionDescarga
     *
     * @return StreamedContent Regresa el path del archivo que se desea
     *         descargar
     */
    public StreamedContent getArchivoSeleccionDescarga() {
        archivoSeleccionDescarga = getArchivoDescarga(getDocSeleccionado().getRutaArchivo(),
                getDocSeleccionado().getNombreArchivo());
        return archivoSeleccionDescarga;
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

    public void cargaDocumentosOrden() {
        PropuestaPorValidar propuesta = getPropuestaParaDescargar();
        AgaceOrden orden = getOrdenesPorValidarReService()
                .obtenerOrdenByIdPropuesta(new BigDecimal(propuesta.getIdPropuesta()));
        setListaDocumentosOrden(getOrdenesPorValidarReService().obtenerDocOrden(orden.getIdOrden()));
        setOficiosPendientesDeFirmar(getOrdenesPorValidarReService().getOficiosPorFirmar(orden.getIdOrden()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogDocumentosOrden').show();");
    }

    public void setArchivoSeleccionDescarga(StreamedContent archivoSeleccionDescarga) {
        this.archivoSeleccionDescarga = archivoSeleccionDescarga;
    }

    public StreamedContent getArchivoDescargaDocumentacion() {
        archivoDescargaDocumentacion = getArchivoDescarga(getOficioSeleccionado().getRutaArchivo(),
                getOficioSeleccionado().getNombreArchivo());
        return archivoDescargaDocumentacion;
    }

    public void muestraMotivoNoAprobar() {
        RequestContext context = RequestContext.getCurrentInstance();
        if ((getListaSeleccionDocumentosOrden() != null && !getListaSeleccionDocumentosOrden().isEmpty())
                || (getListaSeleccionOficiosPendientes() != null && !getListaSeleccionOficiosPendientes().isEmpty())) {
            context.execute("PF('dialogNoAprobarDocumentosOrden').show();");
            context.execute("PF('dialogNoAprobarDocumento').show();");
        } else {
            addErrorMessage("formValidarPropuestas:tablaAprobarDocumentacionOrden",
                    getMessageResourceString(SIN_SELECCION));
            context.execute("PF('dialogNoAprobarDocumentosOrden').show();");
        }
    }

    public void setArchivoDescargaDocumentacion(StreamedContent archivoDescargaDocumentacion) {
        this.archivoDescargaDocumentacion = archivoDescargaDocumentacion;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
