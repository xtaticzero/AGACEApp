package mx.gob.sat.siat.feagace.vista.insumos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.AdministradorReasignacion;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.vista.model.insumos.AsignarInsumoSubadminDTO;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ViewScoped
@ManagedBean(name = "asignarInsumoSubadminManagedBean")
public class AsignarInsumoSubadminManagedBean extends AbstractAsignarInsumoSubadminMB {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    private static final BigDecimal ID_ASIGNADO_SUBADMINISTRADOR = new BigDecimal(4L);

    @PostConstruct
    public void init() {
        setAsignarInsumoSubadminDTO(new AsignarInsumoSubadminDTO());
        getAsignarInsumoSubadminDTO().setListaInsumosPorAsignar(new ArrayList<FecetInsumo>());
        getAsignarInsumoSubadminDTO().setMuestraDetalleContribuyente(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoAceptarReasignar(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoCancelarReasignar(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoAsignar(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignar(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoRechazarReasignar(false);
        getAsignarInsumoSubadminDTO().setMuestraConfirmarRechazarReasignacion(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignarResultado(false);
        getAsignarInsumoSubadminDTO().setMuestraInsumos(true);
        getAsignarInsumoSubadminDTO().setVisualizaAsignacionSubadministrador(false);
        getAsignarInsumoSubadminDTO().setVisualizaReasignacionAdministrador(false);
        getAsignarInsumoSubadminDTO().setListaExpedientes(new ArrayList<FecetDocExpInsumo>());
        getAsignarInsumoSubadminDTO().setListaSubAdministrador(new ArrayList<EmpleadoDTO>());
        getAsignarInsumoSubadminDTO().setListaAdministrador(new ArrayList<AdministradorReasignacion>());
        getAsignarInsumoSubadminDTO().setListaReasignaciones(new ArrayList<FecetReasignacionInsumo>());
        getAsignarInsumoSubadminDTO().setListaReasignacionesInsumos(new ArrayList<FecetInsumo>());

        getAsignarInsumoSubadminDTO().setRfcSubAdminSeleccionado(null);
        getAsignarInsumoSubadminDTO().setAdministradorSeleccionado(null);
        getAsignarInsumoSubadminDTO().setMotivoReasignacion(null);
        getAsignarInsumoSubadminDTO().setMotivoRechazo("");
        getAsignarInsumoSubadminDTO().setInsumoSeleccionado(null);
        getAsignarInsumoSubadminDTO().setInsumosSeleccionados(null);
        getAsignarInsumoSubadminDTO().setAccionAdministrador(null);

        try {
            getDatosAdministrador();
            cargaInsumosAsignados();
            cargaSubadministradores();
            cargaContadorInsumos();
            cargaAdministradores();
            cargaReasignaciones();
            cargaReasignacionesExt();

            limpiaFiltros("formInsumos:tablaInsumosAsignar");
            limpiaFiltros("formInsumos:tablaInsumosReasignar");

        } catch (NegocioException e) {
            logger.error("No se pudo cargar la informacion basica de la pagina", e);
            informeErrorSession(e);
        } catch (NoExisteEmpleadoException e) {
            logger.error("No se pudo cargar la informacion basica de la pagina", e);
            informeErrorSession(e);
        }
    }

    public void enviaDetalle() {
        getAsignarInsumoSubadminDTO().setMuestraDetalleContribuyente(true);
        getAsignarInsumoSubadminDTO().setMuestraInsumos(false);
        getAsignarInsumoSubadminDTO().setListaExpedientes(getConsultaAsignacionInsumoService().traeExpedientesCargadosAdmon(
                getAsignarInsumoSubadminDTO().getInsumoSeleccionado(), Constantes.CERO));
        getAsignarInsumoSubadminDTO().setListaDocumentosJustificacion(
                getConsultaAsignacionInsumoService().traeDocumentoJustificacionById(getAsignarInsumoSubadminDTO().getInsumoSeleccionado().getIdInsumo()));
        getAsignarInsumoSubadminDTO().setNumeroDeDocumentos(getAsignarInsumoSubadminDTO().getListaDocumentosJustificacion() == null ? 0 : getAsignarInsumoSubadminDTO().getListaDocumentosJustificacion().size());
    }

    public void enviaDetalleInsumoReasignado() {
        getAsignarInsumoSubadminDTO().setMuestraDetalleContribuyente(true);
        getAsignarInsumoSubadminDTO().setMuestraInsumos(false);
        getAsignarInsumoSubadminDTO().setInsumoSeleccionado(getAsignarInsumoSubadminDTO().getSeleccionReasignacionInsumoSeleccionado());
        getAsignarInsumoSubadminDTO().setListaExpedientes(getConsultaAsignacionInsumoService().traeExpedientesCargados(
                getAsignarInsumoSubadminDTO().getInsumoSeleccionado().getIdInsumo(), Constantes.UNO));
    }

    public void reset() {
        init();
    }

    public void validaSeleccionReasignacion() {
        getAsignarInsumoSubadminDTO().setMensajeReasignacion("");
        if (getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados() == null || getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados().isEmpty()) {
            FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un insumo");
        } else {
            String mensaje = String.format("%s%s%s",
                    FacesUtil.getMessageResourceString("msg.aceptar.reasignacion.insumo.uno"), getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados().size() + " ",
                    FacesUtil.getMessageResourceString("msg.aceptar.reasignacion.insumo.dos"));
            getAsignarInsumoSubadminDTO().setMensajeReasignacion(mensaje);
            getAsignarInsumoSubadminDTO().setMuestraDialogoAceptarReasignar(true);
        }
    }

    public void validaSeleccionRechazoReasignacion() {
        getAsignarInsumoSubadminDTO().setMotivoRechazo(null);
        if (getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados() == null || getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados().isEmpty()) {
            FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un insumo");
        } else {
            getAsignarInsumoSubadminDTO().setMuestraDialogoRechazarReasignar(true);
        }
    }

    public void validaSeleccion() {
        return;
    }

    public void validaSeleccionSubadministrador() throws NegocioException {
        getAsignarInsumoSubadminDTO().setMensajeReasignacion("");
        if (getAsignarInsumoSubadminDTO().getInsumosSeleccionados() != null && !getAsignarInsumoSubadminDTO().getInsumosSeleccionados().isEmpty()) {
            if (!getAsignarInsumoSubadminDTO().getRfcSubAdminSeleccionado().equals("-1")) {
                try {
                    EmpleadoDTO empleadoDTO = obtenerEmpleadoAcceso(getAsignarInsumoSubadminDTO().getRfcSubAdminSeleccionado(), TipoEmpleadoEnum.VALIDADOR_INSUMOS);
                    getAsignarInsumoSubadminDTO().setFececEmpleadoSubadministradorSeleccionado(empleadoDTO);
                    getAsignarInsumoSubadminDTO().setMuestraDialogoAsignar(true);
                    getAsignarInsumoSubadminDTO().setMensajeReasignacion("" + getAsignarInsumoSubadminDTO().getInsumosSeleccionados().size());
                } catch (NoExisteEmpleadoException e) {
                    throw new NegocioException(ERROR_LOGGEO, e);
                }
                getAsignarInsumoSubadminDTO().setMuestraDialogoAsignar(true);
            } else {
                FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un subadministrador");
            }
        } else {
            FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un insumo");
        }
    }

    public void validaSeleccionAdministrador() throws NegocioException {
        getAsignarInsumoSubadminDTO().setMensajeReasignacion("");
        if (getAsignarInsumoSubadminDTO().getInsumosSeleccionados() != null && !getAsignarInsumoSubadminDTO().getInsumosSeleccionados().isEmpty()) {
            if (!getAsignarInsumoSubadminDTO().getAdministradorSeleccionado().equals("-1")) {
                if (getAsignarInsumoSubadminDTO().getMotivoReasignacion() != null && !getAsignarInsumoSubadminDTO().getMotivoReasignacion().trim().equals(Constantes.CADENA_VACIA)) {
                    try {
                        EmpleadoDTO empleadoDTO = obtenerEmpleadoAcceso(getAsignarInsumoSubadminDTO().getAdministradorSeleccionado(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS);
                        getAsignarInsumoSubadminDTO().setFececEmpleadoAdministradorSeleccionado(empleadoDTO);
                        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignar(true);
                        getAsignarInsumoSubadminDTO().setMensajeReasignacion("" + getAsignarInsumoSubadminDTO().getInsumosSeleccionados().size());
                    } catch (NoExisteEmpleadoException e) {
                        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignar(false);
                        throw new NegocioException(ERROR_LOGGEO, e);
                    }
                    getAsignarInsumoSubadminDTO().setMuestraDialogoReasignar(true);
                } else {
                    FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe introducir un motivo.");
                }
            } else {
                FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un administrador.");
            }
        } else {
            FacesUtil.addErrorMessage(null, Constantes.VERIFIQUE, "Se debe seleccionar un insumo.");
        }
    }

    public void validaMotivoRechazo() {
        getAsignarInsumoSubadminDTO().setMensajeReasignacion("");
        if (getAsignarInsumoSubadminDTO().getMotivoRechazo() == null
                || getAsignarInsumoSubadminDTO().getMotivoRechazo().trim().equalsIgnoreCase(Constantes.CADENA_VACIA)) {
            FacesUtil.addErrorMessage("formInsumos:motivoRechazo", Constantes.VERIFIQUE,
                    "Se debe introducir un motivo de rechazo.");
        } else {
            getAsignarInsumoSubadminDTO().setMuestraDialogoRechazarReasignar(false);
            getAsignarInsumoSubadminDTO().setMuestraConfirmarRechazarReasignacion(true);
            getAsignarInsumoSubadminDTO().setMensajeReasignacion("" + getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados().size());
        }

    }

    public void aceptarReasignacionInsumo() throws NegocioException {
        for (FecetInsumo insumo : getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados()) {
            insumo.getFecetReasignacionInsumo().setEstatus(Constantes.ACEPTACION_REASIGNACION_A_ADMINISTRADOR);
            getReasignacionInsumosService().aceptaReasignacion(insumo.getFecetReasignacionInsumo(), insumo);
            enviarCorreoAprobarReasignacionInsumo(insumo.getFecetReasignacionInsumo());
        }
        init();
        FacesUtil.addInfoMessage(null, FacesUtil.getMessageResourceString("msg.exito.insumo.reasignado.administrador"), "");

    }

    public void rechazarReasignacionInsumo() throws NegocioException {
        for (FecetInsumo insumo : getAsignarInsumoSubadminDTO().getInsumosReasignacionSeleccionados()) {
            insumo.getFecetReasignacionInsumo().setEstatus(Constantes.RECHAZO_REASIGNACION_A_ADMINISTRADOR);
            insumo.getFecetReasignacionInsumo().setMotivoRechazo(getAsignarInsumoSubadminDTO().getMotivoRechazo());
            getReasignacionInsumosService().rechazaReasignacionInsumo(insumo.getFecetReasignacionInsumo(), insumo);

            enviarCorreoRechazoReasignacionInsumo(insumo.getFecetReasignacionInsumo());
        }
        init();
        FacesUtil.addInfoMessage(null, FacesUtil.getMessageResourceString("msg.exito.insumo.rechazado.administrador"), "");

    }

    public void reasignarInsumo() throws NegocioException {
        getAsignarInsumoSubadminDTO().setReasignacionesAceptadas(new ArrayList<FecetReasignacionInsumo>());
        getAsignarInsumoSubadminDTO().setReasignacionesRechazadas(new ArrayList<FecetInsumo>());
        FecetReasignacionInsumo fecetReasignacionInsumo = null;
        String validacion;
        for (FecetInsumo insumo : getAsignarInsumoSubadminDTO().getInsumosSeleccionados()) {
            validacion = validarReasignacion(insumo);
            if (validacion != null) {
                insumo.setDescripcionRechazo(validacion);
                getAsignarInsumoSubadminDTO().getReasignacionesRechazadas().add(insumo);
                continue;
            }
            fecetReasignacionInsumo = new FecetReasignacionInsumo();
            fecetReasignacionInsumo.setMotivo(getAsignarInsumoSubadminDTO().getMotivoReasignacion());
            fecetReasignacionInsumo.setMotivoRechazo(getAsignarInsumoSubadminDTO().getMotivoRechazo());
            fecetReasignacionInsumo.setRfcAdministradorDestino(getAsignarInsumoSubadminDTO().getAdministradorSeleccionado());
            fecetReasignacionInsumo.setRfcAdministradorOrigen(getAsignarInsumoSubadminDTO().getAdministrador().getRfc());
            fecetReasignacionInsumo.setIdInsumo(insumo.getIdInsumo());
            fecetReasignacionInsumo.setIdRegistroInsumo(insumo.getIdRegistro());
            fecetReasignacionInsumo.setEstatus(Constantes.REASIGNADA_A_ADMINISTRADOR);
            fecetReasignacionInsumo.setBlnActivo(Constantes.ENTERO_UNO);
            getReasignacionInsumosService().insert(fecetReasignacionInsumo);
            getAsignarInsumoSubadminDTO().getReasignacionesAceptadas().add(fecetReasignacionInsumo);
        }
        enviarCorreoReasignacionInsumoAdmin(getAsignarInsumoSubadminDTO().getReasignacionesAceptadas());
        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignar(false);
        getAsignarInsumoSubadminDTO().setMuestraDialogoReasignarResultado(true);
    }

    public void asignarSubadministrador() throws NegocioException {
        for (FecetInsumo insumo : getAsignarInsumoSubadminDTO().getInsumosSeleccionados()) {
            insumo.setRfcSubadministrador(getAsignarInsumoSubadminDTO().getRfcSubAdminSeleccionado());
            insumo.setIdEstatus(ID_ASIGNADO_SUBADMINISTRADOR);
            getConsultaAsignacionInsumoService().guardarAsignacionSubadministrador(insumo);
        }
        enviarCorreoReasignacionInsumoSubAdmin(getAsignarInsumoSubadminDTO().getInsumosSeleccionados());
        init();
        FacesUtil.addInfoMessage(null, FacesUtil.getMessageResourceString("msg.exito.insumo.asignado.subadministrador"), "");
    }

    public void actualizaAccion() {
        getAsignarInsumoSubadminDTO().setMotivoReasignacion(null);
        if (getAsignarInsumoSubadminDTO().getAccionAdministrador() != null
                && getAsignarInsumoSubadminDTO().getAccionAdministrador().intValue() == 0) {
            getAsignarInsumoSubadminDTO().setVisualizaAsignacionSubadministrador(true);
            getAsignarInsumoSubadminDTO().setVisualizaReasignacionAdministrador(false);
        } else if (getAsignarInsumoSubadminDTO().getAccionAdministrador() != null
                && getAsignarInsumoSubadminDTO().getAccionAdministrador().intValue() == 1) {
            getAsignarInsumoSubadminDTO().setVisualizaAsignacionSubadministrador(false);
            getAsignarInsumoSubadminDTO().setVisualizaReasignacionAdministrador(true);
        }
    }

    private void cargaInsumosAsignados() throws NegocioException {
        //quitar la linea hard code
        getAsignarInsumoSubadminDTO().setListaInsumosPorAsignar(getConsultaAsignacionInsumoService().traeInsumosSinAsignar(getAsignarInsumoSubadminDTO().getAdministrador().getRfc(), obtenerGrupoUnidadesAdminXGeneral(getAsignarInsumoSubadminDTO().getAdministrador())));
        getAsignarInsumoSubadminDTO().setListaEstatusInsumo(new ArrayList<String>());
        if (!getAsignarInsumoSubadminDTO().getListaInsumosPorAsignar().isEmpty()) {
            for (FecetInsumo insumo : getAsignarInsumoSubadminDTO().getListaInsumosPorAsignar()) {
                if (!getAsignarInsumoSubadminDTO().getListaEstatusInsumo().contains(insumo.getFececEstatus().getDescripcion())) {
                    getAsignarInsumoSubadminDTO().getListaEstatusInsumo().add(insumo.getFececEstatus().getDescripcion());
                }
            }
            Collections.sort(getAsignarInsumoSubadminDTO().getListaEstatusInsumo());
        }
    }

    private void cargaAdministradores() throws NegocioException {
        getAsignarInsumoSubadminDTO().setListaAdministrador(getConsultaAsignacionInsumoService().getAdministradoresACPPCE(getAsignarInsumoSubadminDTO().getAdministrador()));
    }

    private void cargaReasignaciones() throws NegocioException {
        getAsignarInsumoSubadminDTO().setListaReasignaciones(getReasignacionInsumosService()
                .getReasignacionInsumoByAdministradorEstatus(getAsignarInsumoSubadminDTO().getAdministrador(), Constantes.REASIGNADA_A_ADMINISTRADOR));
    }

    private void cargaReasignacionesExt() throws NegocioException {

        getAsignarInsumoSubadminDTO().setListaReasignacionesInsumos(
                getReasignacionInsumosService().getReasignacionInsumoByAdministradorEstatusExt(
                        getAsignarInsumoSubadminDTO().getAdministrador().getRfc(),
                        Constantes.REASIGNADA_A_ADMINISTRADOR));
    }

    private void cargaSubadministradores() throws NegocioException {
        getAsignarInsumoSubadminDTO().setListaSubAdministrador(getConsultaAsignacionInsumoService().getSubAdministradoresACPPCE(getAsignarInsumoSubadminDTO().getAdministrador()));
    }

    private void cargaContadorInsumos() throws NegocioException {
        getAsignarInsumoSubadminDTO().setListaContadorInsumos(getConsultaAsignacionInsumoService().getContadorInsumos(getAsignarInsumoSubadminDTO().getListaSubAdministrador()));
    }

    private void getDatosAdministrador() throws NoExisteEmpleadoException {
        getAsignarInsumoSubadminDTO().setAdministrador(obtenerEmpleadoAcceso(getRFCSession(), TipoEmpleadoEnum.ASIGNADOR_INSUMOS));
    }

    private String validarReasignacion(FecetInsumo insumo) {
        String resultado = null;
        if (!insumo.isEsTransferible()) {
            resultado = "El plazo de reasignaci\u00F3n est\u00E1 vencido";
        } else if (Constantes.ACEPTACION_REASIGNACION_A_ADMINISTRADOR.equals(insumo.getIdEstatus())) {
            resultado = FacesUtil.getMessageResourceString("msg.aviso.cancelacion.reasignacion.insumo");
        }
        return resultado;
    }
}
