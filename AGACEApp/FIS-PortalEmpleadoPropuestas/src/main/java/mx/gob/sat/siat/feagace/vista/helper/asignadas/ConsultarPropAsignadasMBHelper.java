package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@Component
public class ConsultarPropAsignadasMBHelper extends BaseManagedBean {

    private static final long serialVersionUID = -5804643977163983995L;

    public ColaboradorVO validaRfc(String rfc, FacesUtil mensajes, ContribuyenteService contribuyenteService,
            ConsultaMediosContactoService mediosContactoService) {

        ColaboradorVO agenteAduanal = new ColaboradorVO();
        Pattern patron = Pattern.compile(Constantes.PATRON_RFC);
        Matcher esValido;

        esValido = patron.matcher(rfc.toUpperCase());

        if (esValido.find()) {
            agenteAduanal = buscarColaborador(rfc.toUpperCase(), contribuyenteService, mediosContactoService, mensajes);
        }

        return agenteAduanal;
    }

    private ColaboradorVO buscarColaborador(String rfc, ContribuyenteService contribuyenteService,
            ConsultaMediosContactoService mediosContactoService, FacesUtil mensajes) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        ColaboradorVO agenteAduanal = validaColaboradorBuscado(rfc, requestContext, contribuyenteService,
                mediosContactoService, mensajes);

        return agenteAduanal;
    }

    @SuppressWarnings("static-access")
    private ColaboradorVO validaColaboradorBuscado(String rfc, RequestContext requestContext,
            ContribuyenteService contribuyenteService, ConsultaMediosContactoService mediosContactoService,
            FacesUtil mensajes) {
        ColaboradorVO agente = new ColaboradorVO();

        if (!rfc.isEmpty()) {
            FecetContribuyente contribuyenteIDC;
            try {
                contribuyenteIDC = contribuyenteService.getContribuyenteIDC(rfc);
                if (contribuyenteIDC != null) {
                    agente.setVisibleBusquedaColaborador(false);
                    agente.setNombre(contribuyenteIDC.getNombre());
                    agente.setRfc(contribuyenteIDC.getRfc());
                    agente.setDeshabilitarCampos(true);
                    agente.setDeshabilitarCorreo(true);

                    if (checkMediosContacto(contribuyenteIDC.getRfc(), mediosContactoService)) {
                        agente.setMedioContactoBoolean(true);
                    } else {
                        requestContext.execute("PF('confirmarMediosContacto').show();");
                    }
                } else {
                    requestContext.execute("PF('noIdc').show();");
                }
            } catch (NoExisteContribuyenteException nec) {
                requestContext.execute("PF('noIdc').show();");
            }
        } else {
            agente.setDeshabilitarCampos(false);
            if (mensajes == null) {
                addErrorMessage(null, "Debe introducir un RFC", "");
            } else {
                mensajes.addErrorMessage(null, "Debe introducir un RFC", "");
            }
        }

        return agente;
    }

    private boolean checkMediosContacto(String rfc, ConsultaMediosContactoService mediosContactoService) {
        ValidaMediosContactoBO validaMediosContactoBO = mediosContactoService.validaMediosContacto(rfc.toUpperCase());
        return validaMediosContactoBO.isFlag();
    }

    public List<AsociadoFuncionarioDTO> obtenerJefeDeptoAsignado(BigDecimal idPropuesta,
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {

        AsociadoFuncionarioDTO jefeDepartamento = new AsociadoFuncionarioDTO();

        jefeDepartamento.setIdTipoEmpleado(Constantes.USUARIO_JEFE_DEPARTAMENTO);
        jefeDepartamento.setIdPropuesta(idPropuesta);
        jefeDepartamento.setBlnActivo(Constantes.ENTERO_UNO);

        return consultarPropuestasAsignadasService.buscarAsociadoFuncionarioActivoByPropuesta(jefeDepartamento);
    }

    public List<AsociadoFuncionarioDTO> obtenerEnlaceAsignado(BigDecimal idPropuesta,
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {

        AsociadoFuncionarioDTO enlace = new AsociadoFuncionarioDTO();

        enlace.setIdTipoEmpleado(Constantes.USUARIO_ENLACE);
        enlace.setIdPropuesta(idPropuesta);
        enlace.setBlnActivo(Constantes.ENTERO_UNO);

        return consultarPropuestasAsignadasService.buscarAsociadoFuncionarioActivoByPropuesta(enlace);
    }

    public boolean esEmpleadoActivo(List<EmpleadoDTO> empleado, BigDecimal idEmpleado) {

        for (EmpleadoDTO emp : empleado) {
            if (emp.getIdEmpleado().equals(idEmpleado)) {
                return true;
            }
        }

        return false;
    }

    public String construyeMensaje(boolean jefeDeptoActivo, boolean enlaceActivo, String nombreJefeDepto,
            String nombreEnlace) {

        StringBuilder mensajeEmpleadoBaja = new StringBuilder();
        mensajeEmpleadoBaja.append("");

        if (!nombreJefeDepto.isEmpty() || !nombreEnlace.isEmpty()) {
            if (!jefeDeptoActivo && !enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.JEFE_DEPARTAMENTO);
                mensajeEmpleadoBaja.append(nombreJefeDepto);
                mensajeEmpleadoBaja.append(Constantes.CONJUNCION_REASIGNAR_CONSULTA);
                mensajeEmpleadoBaja.append(Constantes.ENLACE);
                mensajeEmpleadoBaja.append(nombreEnlace);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }

            if (!jefeDeptoActivo && enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.JEFE_DEPARTAMENTO);
                mensajeEmpleadoBaja.append(nombreJefeDepto);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }

            if (jefeDeptoActivo && !enlaceActivo) {
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA1);
                mensajeEmpleadoBaja.append(Constantes.ENLACE);
                mensajeEmpleadoBaja.append(nombreEnlace);
                mensajeEmpleadoBaja.append(Constantes.MENSAJE_REASIGNAR_CONSULTA2);
            }
        }

        return mensajeEmpleadoBaja.toString();
    }

    public boolean validaConstruccionMensaje(boolean tieneJefe, boolean tieneEnlace, boolean jefeActivo,
            boolean enlaceActivo) {

        boolean isConstruccionValida = false;

        if (tieneJefe && !jefeActivo) {
            isConstruccionValida = true;
        }

        if (tieneEnlace && !enlaceActivo) {
            isConstruccionValida = true;
        }

        return isConstruccionValida;
    }

    public void validaMuestraMensajeError(boolean isProcesable) {
        if (!isProcesable) {
            addErrorMessage(null, "Para continuar debes adjuntar un archivo", "");
        }
    }

    public List<AraceDTO> unidadesValidasTransferencia(List<AraceDTO> unidadesDisponibles, FecetPropuesta propuesta,
            EmpleadoDTO empleadoFirmado) {

        List<AraceDTO> unidadAdminNoAplicable = new ArrayList<AraceDTO>();

        for (AraceDTO unidad : unidadesDisponibles) {

            if (!empleadoFirmado.getDetalleEmpleado().get(0).getCentral().getIdArace()
                    .equals(UnidadAdministrativaEnum.ACAOCE.getIdUnidad().intValue())
                    && !empleadoFirmado.getDetalleEmpleado().get(0).getCentral().getIdArace()
                            .equals(UnidadAdministrativaEnum.ACOECE.getIdUnidad().intValue())) {

                if (unidad.getIdArace().equals(Constantes.ACAOCE.intValue())
                        || unidad.getIdArace().equals(Constantes.ACOECE.intValue())
                        || unidad.getIdArace().equals(propuesta.getIdArace().intValue())) {
                    unidadAdminNoAplicable.add(unidad);
                }
            } else {
                if (unidad.getIdArace().equals(propuesta.getIdArace().intValue())) {
                    unidadAdminNoAplicable.add(unidad);
                }
            }

        }

        unidadesDisponibles.removeAll(unidadAdminNoAplicable);

        return unidadesDisponibles;
    }

    public void completaPropuesta(List<AraceDTO> araces, FecetPropuesta propuesta) {

        for (AraceDTO unidadAsociada : araces) {
            if (propuesta.getIdArace().intValue() == unidadAsociada.getIdArace().intValue()) {
                FececArace uniAdmin = new FececArace();
                uniAdmin.setNombre(unidadAsociada.getNombre());
                propuesta.setFececArace(uniAdmin);
                break;
            }
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
            addErrorMessage(null, "No se pudo redireccionar a la pagina de error");
        }

    }

    public EmpleadoDTO obtenEmpleadoById(ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService,
            FecebAccionPropuesta accion, Map<BigDecimal, EmpleadoDTO> listaEmpleados) {

        EmpleadoDTO empleado = null;
        
        if (!listaEmpleados.containsKey(accion.getIdEmpleado())) {
            empleado = consultarPropuestasAsignadasService.obtenerEmpleadoXId(accion.getIdEmpleado());
            if (empleado != null) {
                listaEmpleados.put(empleado.getIdEmpleado(), empleado);
            }
        } else {
            empleado = listaEmpleados.get(accion.getIdEmpleado());
        }

        return empleado;
    }

    public TipoEmpleadoEnum obtenerRolEmpleado(FecebAccionPropuesta accion, EmpleadoDTO empleado) {

        TipoEmpleadoEnum tipoEmpleadoEnum = null;

        if (empleado != null && empleado.getDetalleEmpleado().get(0).getTipoEmpleado().equals(TipoEmpleadoEnum.FIRMANTE)
                && accion.getIdAccion().equals(AccionesFuncionarioEnum.VALIDACION_EMISION_ORDENES_11.getIdAccion())) {
            accion.getFececAccionesFuncionario().setIdTipoEmpleado(TipoEmpleadoEnum.FIRMANTE.getBigId());
            tipoEmpleadoEnum = TipoEmpleadoEnum
                    .parse(accion.getFececAccionesFuncionario().getIdTipoEmpleado().intValue());
        } else {
            tipoEmpleadoEnum = TipoEmpleadoEnum
                    .parse(accion.getFececAccionesFuncionario().getIdTipoEmpleado().intValue());
        }

        return tipoEmpleadoEnum;
    }

    public ColaboradorVO construyeAgenteAduanalVO(FecetAsociado asociado) {
        ColaboradorVO agente = new ColaboradorVO();
        agente.setRfc(asociado.getRfc());
        agente.setNombre(asociado.getNombre());
        agente.setCorreo(asociado.getCorreo());
        agente.setMedioContactoBoolean(asociado.isMedioContactoBoolean());
        return agente;
    }

    public void limpiarComponentes(ConsultarPropuestasAsignadasDtoHelper cpaDtoHelper,
            ConsultarPropuestasAsignadasStringHelper cpaStringHelper,
            ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper,
            ConsultarPropuestasAsignadasBooleanHelper cpaBooleanHelper) {

        cpaDtoHelper.setMotivoRechazoSeleccionado(null);
        cpaStringHelper.setArchivoRechazo(null);
        cpaStringHelper.setObservaciones(null);
        consultarPropAsignadaHelper.getListaRechazo().clear();
        cpaDtoHelper.setIdRetroalimentacionSeleccionado(null);
        cpaStringHelper.setArchivoRetroalimentar(null);
        cpaStringHelper.setDescripcionRetroalimentar(null);
        consultarPropAsignadaHelper.getListaRetroalimentar().clear();
        cpaDtoHelper.setIdAraceSeleccionado(null);
        cpaStringHelper.setArchivoTransferir(null);
        cpaStringHelper.setDescripcionTransferir(null);
        consultarPropAsignadaHelper.getListaTransferir().clear();
        cpaDtoHelper.setCausaCancelacionSeleccion(null);
        cpaStringHelper.setArchivoCancelar(null);
        consultarPropAsignadaHelper.getListaCancelar().clear();
        consultarPropAsignadaHelper.getDocumentoOrden().clear();
        consultarPropAsignadaHelper.getListaPapeles().clear();
        consultarPropAsignadaHelper.getListaPapelesTrabajo().clear();
        consultarPropAsignadaHelper.getDocOficios().clear();
        consultarPropAsignadaHelper.getListaOrdenes().clear();
        consultarPropAsignadaHelper.getListaOficios().clear();
        cpaDtoHelper.setAgenteAduanalVO(new ColaboradorVO());
        cpaDtoHelper.setTipoOficioSeleccionado(null);
        consultarPropAsignadaHelper.setListaJefeDepartamento(null);
        consultarPropAsignadaHelper.setListaEnlace(null);
        cpaBooleanHelper.setDeshabilitaBtnFormulario(false);
        cpaBooleanHelper.setMuestraDesabilitaPapel(false);
    }

}
