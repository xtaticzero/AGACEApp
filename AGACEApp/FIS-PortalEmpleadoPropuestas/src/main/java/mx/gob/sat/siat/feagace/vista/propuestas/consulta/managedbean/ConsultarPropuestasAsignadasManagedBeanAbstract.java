package mx.gob.sat.siat.feagace.vista.propuestas.consulta.managedbean;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.PistasAuditoriasPropuestasService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecebAccionPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.HistoricoPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPropuestasAsignadasService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarRetroalimentarPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.VerificarProcedenciaService;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropAsignadasMBHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasBooleanHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasDtoHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasParamHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ConsultarPropuestasAsignadasStringHelper;
import mx.gob.sat.siat.feagace.vista.helper.asignadas.ValidaAccionesHelper;

public class ConsultarPropuestasAsignadasManagedBeanAbstract extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    private ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper;
    private ConsultarPropuestasAsignadasBooleanHelper cpaBooleanHelper;
    private ConsultarPropuestasAsignadasDtoHelper cpaDtoHelper;
    private ConsultarPropuestasAsignadasStringHelper cpaStringHelper;
    private ConsultarPropuestasAsignadasParamHelper cpaParamHelper;

    @ManagedProperty(value = "#{consultarPropuestasAsignadasService}")
    private transient ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService;
    @ManagedProperty(value = "#{consultarPapelesTrabajoService}")
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService;
    @ManagedProperty(value = "#{verificarProcedenciaService}")
    private transient VerificarProcedenciaService verificarProcedenciaService;
    @ManagedProperty(value = "#{validaAccionesHelper}")
    private ValidaAccionesHelper validaAccionesHelper;
    @ManagedProperty(value = "#{consultarPropAsignadasMBHelper}")
    private ConsultarPropAsignadasMBHelper consultarPropAsignadasMBHelper;
    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;
    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    @ManagedProperty(value = "#{validarRetroalimentarPropuestaService}")
    private transient ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService;

    @ManagedProperty(value = "#{fecebAccionPropuestaService}")
    private transient FecebAccionPropuestaService fecebAccionPropuestaService;

    @ManagedProperty(value = "#{historicoDocPropuestaService}")
    private transient HistoricoPropuestaService historicoPropuestaService;

    @ManagedProperty(value = "#{auditoriaPropuestas}")
    private transient PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService;

    private EmpleadoDTO empleadoLogueado;

    public static final String LISTA_ARACES = "unidades";
    private static final String TOGLE_VISIBLE = "VISIBLE";

    @PostConstruct
    public void init() {
        consultarPropAsignadaHelper = new ConsultarPropuestasAsignadasHelper();
        cpaBooleanHelper = new ConsultarPropuestasAsignadasBooleanHelper();
        cpaDtoHelper = new ConsultarPropuestasAsignadasDtoHelper();
        cpaStringHelper = new ConsultarPropuestasAsignadasStringHelper();
        cpaParamHelper = new ConsultarPropuestasAsignadasParamHelper();

        Object vieneDePropOrd = getSession().getAttribute("vieneDeConsultaPropuestasOrdenes");
        if (vieneDePropOrd == null) {
            setEmpleadoLogueado(consultarPropuestasAsignadasService.obtenerDatosAuditor(getRFCSession()));

            if (getEmpleadoLogueado() != null) {
                listaUnidadAdministrativaRegional();
                obtenerListaFechasComiteSegunUsuario();
                cargaAraces();
            } else {
                addErrorMessage(null, ERROR_LOGGEO);
            }
        }
    }

    protected void obtenerListaFechasComiteSegunUsuario() {
        cpaDtoHelper.setListaFechasComiteEnum(new ArrayList<TipoFechasComiteEnum>());

        UnidadAdministrativaEnum unidadAministrativa = UnidadAdministrativaEnum
                .parse(empleadoLogueado.getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (cpaDtoHelper.getListaUnidadAdminRegional().contains(unidadAministrativa)) {
            cpaDtoHelper.getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_INFORME_COMITE_REGIONAL);
            cpaDtoHelper.getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE_REGIONAL);
        } else {
            cpaDtoHelper.getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_INFORME_COMITE);
            cpaDtoHelper.getListaFechasComiteEnum().add(TipoFechasComiteEnum.FECHA_PRESENTACION_COMITE);
        }
    }

    public void listaUnidadAdministrativaRegional() {
        List<UnidadAdministrativaEnum> unidadAdmin = new ArrayList<UnidadAdministrativaEnum>();
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_CENTRO);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_NOROESTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_NORTE_CENTRO);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_OCCIDENTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_PACIFICO_NORTE);
        unidadAdmin.add(UnidadAdministrativaEnum.ADACE_SUR);
        cpaDtoHelper.setListaUnidadAdminRegional(unidadAdmin);
    }

    public void detallePropuesta() {
        try {
            BigDecimal presuntivaConvertida;

            if (getCpaDtoHelper().getPropuestaSelAnalizar() != null) {
                consultarPropAsignadaHelper.setListaImpuestosDescripcion(consultarPropuestasAsignadasService
                        .traeImpuestosDescripcion(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));

                for (FecetImpuestoDescripcion impuestoSumar : consultarPropAsignadaHelper
                        .getListaImpuestosDescripcion()) {
                    presuntivaConvertida = impuestoSumar.getFecetImpuesto().getMonto();
                    getCpaDtoHelper()
                            .setSumaPresuntiva(getCpaDtoHelper().getSumaPresuntiva().add(presuntivaConvertida));
                }
                getCpaDtoHelper().getPropuestaSelAnalizar()
                        .setFececCausaProgramacion(
                                consultarPropuestasAsignadasService
                                .traeCausaProgramacion(
                                        getCpaDtoHelper().getPropuestaSelAnalizar().getIdCausaProgramacion())
                                .get(0));
                getCpaDtoHelper().getPropuestaSelAnalizar().setFececTipoPropuesta(consultarPropuestasAsignadasService
                        .traeTipoPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdTipoPropuesta()).get(0));

                if (getCpaDtoHelper().getPropuestaSelAnalizar().getIdRevision() != null) {
                    getCpaDtoHelper().getPropuestaSelAnalizar().setFececRevision(
                            traeTipoRevision(getCpaDtoHelper().getPropuestaSelAnalizar().getIdRevision()).get(0));
                }

                getCpaDtoHelper().getPropuestaSelAnalizar().setFeceaMetodo(consultarPropuestasAsignadasService
                        .traeDescripcionMetodo(getCpaDtoHelper().getPropuestaSelAnalizar().getIdMetodo()).get(0));
                getConsultarPropAsignadaHelper().getListaPapelesTrabajo().clear();
                getConsultarPropAsignadaHelper().getListaPapeles().clear();
                consultarPropAsignadaHelper.setListaExpedientes(consultarPropuestasAsignadasService
                        .traeExpedientesCargados(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
                consultarPropAsignadaHelper.setListaPapeles(consultarPapelesTrabajoService
                        .getPapelesByIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
                cpaBooleanHelper.setMuestraBotonesAccion(validaAccionesHelper.validaPropuestaInformada(
                        getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus(),
                        getCpaBooleanHelper().isVieneConsultaPropOrd()));
                cpaBooleanHelper.setMuestraActualizaOrden(validaAccionesHelper.validaActualizarOrden(
                        getCpaDtoHelper().getPropuestaSelAnalizar(), consultarPropuestasAsignadasService));
                cpaStringHelper.setLabelFechaComite(
                        validaAccionesHelper.validaFechaComite(getCpaDtoHelper().getPropuestaSelAnalizar().getIdArace(),
                                getCpaDtoHelper().getPropuestaSelAnalizar().getFechaInforme(),
                                getCpaDtoHelper().getPropuestaSelAnalizar().getFechaPresentacion()));

                copiarDocumentos();

                if (!cpaBooleanHelper.isMuestraBotonesAccion()) {

                    cpaDtoHelper.setContribuyenteIdc(verificarProcedenciaService.validarContribuyenteVersusIdc(
                            getCpaDtoHelper().getPropuestaSelAnalizar().getFecetContribuyente().getRfc(),
                            getCpaDtoHelper().getPropuestaSelAnalizar().getFecetContribuyente().getIdContribuyente()));

                    if (cpaDtoHelper.getContribuyenteIdc().isExistenCambios()) {
                        verificarProcedenciaService
                                .actualizaContribuyente(cpaDtoHelper.getContribuyenteIdc().getContribuyenteIdc());
                    }
                }

                BigDecimal tipo = getCpaDtoHelper().getPropuestaSelAnalizar().getIdTipoPropuesta();
                logger.debug(tipo + " tipo de propuesta");
                if (tipo.intValue() == Constantes.ENTERO_UNO) {
                    getCpaBooleanHelper().setTipoPropuesta(false);
                } else {
                    getCpaBooleanHelper().setTipoPropuesta(true);
                }

            }

        } catch (NegocioException e) {
            addErrorMessage(null, Constantes.ERROR, e.getCause().toString());
            logger.error(Constantes.ERROR + e.getCause(), e);
        }
    }

    private List<FececRevision> traeTipoRevision(final BigDecimal idTipoRevision) throws NegocioException {
        return consultarPropuestasAsignadasService.traeTipoRevision(idTipoRevision);
    }

    public void cargaAraces() {

        List<AraceDTO> unidadesAdmin = consultarPropuestasAsignadasService.getAraces();
        List<AraceDTO> araces = (List<AraceDTO>) SerializationUtils.clone((Serializable) unidadesAdmin);
        getSession().setAttribute(LISTA_ARACES, araces);
    }

    protected void cargaDatosPropuestas(ConsultarPropuestasAsignadasParamHelper cpaParamHelper) {
        List<String> parametrosConsulta = new ArrayList<String>();
        parametrosConsulta.add(cpaParamHelper.getEstatusPropuesta1().toString());
        parametrosConsulta.add(cpaParamHelper.getEstatusPropuesta2().toString());
        parametrosConsulta.add(cpaParamHelper.getAccionPropuesta().toString());

        cpaStringHelper.setEncabezadoPropuestas(validaAccionesHelper.construyeHeaderPorEstatus(
                cpaParamHelper.getEstatusPropuesta1(), cpaParamHelper.getEstatusPropuesta2()));
        consultarPropAsignadaHelper.setListaPropuestasPorAnalizar(
                consultarPropuestasAsignadasService.traePropuestasPorAnalizar(getRFCSession(), parametrosConsulta,
                        (List<AraceDTO>) getSession().getAttribute("unidades")));

        if (consultarPropAsignadaHelper.getListaPropuestasPorAnalizar() != null
                && !consultarPropAsignadaHelper.getListaPropuestasPorAnalizar().isEmpty()) {
            for (FecetPropuesta propuesta : consultarPropAsignadaHelper.getListaPropuestasPorAnalizar()) {
                if (propuesta.getFechaInforme() != null) {
                    propuesta.setTipoComite(cpaDtoHelper.getListaFechasComiteEnum().get(0));
                } else {
                    propuesta.setTipoComite(cpaDtoHelper.getListaFechasComiteEnum().get(1));
                }

            }

        }

        consultarPropAsignadaHelper.setMotivosOperacion(consultarPropuestasAsignadasService.traeMotivosRechazo());
        consultarPropAsignadaHelper
                .setMotivosRetroalimentacion(consultarPropuestasAsignadasService.traeMotivosRetroalimentacion());
        consultarPropAsignadaHelper.setMotivosCancelacion(consultarPropuestasAsignadasService.findMotivosCancelacion());
    }

    public void setConsultarPropuestasAsignadasService(
            ConsultarPropuestasAsignadasService consultarPropuestasAsignadasService) {
        this.consultarPropuestasAsignadasService = consultarPropuestasAsignadasService;
    }

    public ConsultarPropuestasAsignadasService getConsultarPropuestasAsignadasService() {
        return consultarPropuestasAsignadasService;
    }

    public void copiarDocumentos() {

        if (consultarPropAsignadaHelper.getListaPapeles() != null
                && !consultarPropAsignadaHelper.getListaPapeles().isEmpty()) {
            DocumentoOrdenModel documento = null;
            List<DocumentoOrdenModel> papelTrabajo = new ArrayList<DocumentoOrdenModel>();

            for (PapelesTrabajo papel : consultarPropAsignadaHelper.getListaPapeles()) {
                documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                documento.setIsEliminar(false);
                documento.setInput(null);
                papelTrabajo.add(documento);
            }
            consultarPropAsignadaHelper.getListaPapelesTrabajo().addAll(papelTrabajo);
        }

        if (consultarPropAsignadaHelper.getDocumentoOrden() != null
                && !consultarPropAsignadaHelper.getDocumentoOrden().isEmpty()) {
            DocumentoOrdenModel orden = null;
            List<DocumentoOrdenModel> docOrden = new ArrayList<DocumentoOrdenModel>();

            for (FecetDocOrden miDocOrden : consultarPropAsignadaHelper.getDocumentoOrden()) {
                orden = new DocumentoOrdenModel();
                orden.setDocumentoOrden(miDocOrden);
                orden.setIsEliminar(false);
                orden.setInput(null);
                docOrden.add(orden);
            }
            consultarPropAsignadaHelper.getListaOrdenes().addAll(docOrden);
        }

        if (consultarPropAsignadaHelper.getDocOficios() != null
                && !consultarPropAsignadaHelper.getDocOficios().isEmpty()) {
            DocumentoOrdenModel oficio = null;
            List<DocumentoOrdenModel> docOficio = new ArrayList<DocumentoOrdenModel>();

            for (FecetOficio miOficio : consultarPropAsignadaHelper.getDocOficios()) {
                oficio = new DocumentoOrdenModel();
                oficio.setOficio(miOficio);
                oficio.setIsEliminar(false);
                oficio.setInput(null);
                docOficio.add(oficio);
            }
            consultarPropAsignadaHelper.getListaOficios().addAll(docOficio);
        }
    }

    public StreamedContent getDocumentoDescarga() {

        if (getCpaStringHelper().getArchivoDescargableIS() == null) {
            if (getCpaDtoHelper().getDocOrdenSeleccionado().getRutaArchivo() == null) {
                getCpaStringHelper().setDocumentoDescarga(
                        getDescargaArchivo(getCpaDtoHelper().getOficioSeleccionado().getRutaArchivo(),
                                getCpaDtoHelper().getOficioSeleccionado().getNombreArchivo()));
            } else {
                getCpaStringHelper().setDocumentoDescarga(
                        getDescargaArchivo(getCpaDtoHelper().getDocOrdenSeleccionado().getRutaArchivo(),
                                getCpaDtoHelper().getDocOrdenSeleccionado().getNombreArchivo()));
            }
        } else {
            getCpaStringHelper()
                    .setDocumentoDescarga(new DefaultStreamedContent(getCpaStringHelper().getArchivoDescargableIS(),
                                    "application/octet-stream", getCpaStringHelper().getNombreArchivoDescarga()));
        }

        return getCpaStringHelper().getDocumentoDescarga();
    }

    public void handleToggleRetrolimentacion(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            consultarPropAsignadaHelper.setListaHistoricoRetroalimentar(
                    validarRetroalimentarPropuestaService.getLstHistorialRetroalimentacionPropuesta(
                            getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void handleToggleAcciones(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            logger.error("Consultando Historial de Acciones de la propuesta");
            Map<BigDecimal, EmpleadoDTO> listaEmpleados = new HashMap<BigDecimal, EmpleadoDTO>();

            consultarPropAsignadaHelper.setListaHistoricoAccion(new ArrayList<FecebAccionPropuesta>());

            List<FecebAccionPropuesta> listaAccionSinTabla = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaSinTabla(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());

            List<FecebAccionPropuesta> listaAccionTablaRechazo = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaRechazadas(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                            AccionesFuncionarioEnum.RECHAZAR);

            List<FecebAccionPropuesta> listaAccionTablaCancelar = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaCancelada(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                            AccionesFuncionarioEnum.CANCELAR);

            List<FecebAccionPropuesta> listaAccionTablaRetrolimentar = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaRetroalimentar(
                            getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                            AccionesFuncionarioEnum.RETROALIMENTAR, AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA);

            List<FecebAccionPropuesta> listaAccionTablaTransferir = fecebAccionPropuestaService
                    .obtenerAccionesPropuestaTransferir(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                            AccionesFuncionarioEnum.TRANSFERIR);

            agregarDocumentoLista(listaAccionSinTabla);
            agregarDocumentoLista(listaAccionTablaRechazo);
            agregarDocumentoLista(listaAccionTablaCancelar);
            agregarDocumentoLista(listaAccionTablaRetrolimentar);
            agregarDocumentoLista(listaAccionTablaTransferir);

            Collections.sort(consultarPropAsignadaHelper.getListaHistoricoAccion(), new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    FecebAccionPropuesta p1 = (FecebAccionPropuesta) o1;
                    FecebAccionPropuesta p2 = (FecebAccionPropuesta) o2;
                    return p1.getIdAccionPropuesta().compareTo(p2.getIdAccionPropuesta());
                }
            });

            for (FecebAccionPropuesta accion : consultarPropAsignadaHelper.getListaHistoricoAccion()) {
                try {
                    EmpleadoDTO empleado = consultarPropAsignadasMBHelper.obtenEmpleadoById(
                            consultarPropuestasAsignadasService, accion, listaEmpleados);
                    TipoEmpleadoEnum tipoEmpleadoEnum = consultarPropAsignadasMBHelper.obtenerRolEmpleado(accion,
                            empleado);
                    accion.setDescripcionTipoEmpleado(tipoEmpleadoEnum.getDescripcion());
                    accion.setEmpleadoDto(empleado);
                } catch (Exception e) {
                    EmpleadoDTO empleado = new EmpleadoDTO();
                    accion.setEmpleadoDto(empleado);
                    logger.error(e.getMessage());
                }
            }
        }
    }

    public void handleToggleExpedienteOrden(ToggleEvent event) {
        if (event.getVisibility().toString().equals(TOGLE_VISIBLE)) {
            consultarPropAsignadaHelper.setListaHistorialOrden(historicoPropuestaService
                    .obtenerDocsOrdenHistorial(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));

            consultarPropAsignadaHelper.setListaHistorialOficio(null);
            if (consultarPropAsignadaHelper.getListaHistorialOrden() != null
                    && !consultarPropAsignadaHelper.getListaHistorialOrden().isEmpty()) {

                consultarPropAsignadaHelper
                        .setListaHistorialOficio(historicoPropuestaService.oficiosHistorial(verificarProcedenciaService
                                        .obtenerOrden(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()).get(0)
                                        .getIdOrden()));
            }
        }
    }

    private void agregarDocumentoLista(List<FecebAccionPropuesta> listaDocumetos) {
        if (listaDocumetos != null && !listaDocumetos.isEmpty()) {
            consultarPropAsignadaHelper.getListaHistoricoAccion().addAll(listaDocumetos);
        }
    }

    public void cargarJefeDepartamento() {

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = empleadoLogueado
                .getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.AUDITOR);
        List<EmpleadoDTO> lstJefesDpto = sub.get(TipoEmpleadoEnum.JEFE_DE_DEPARTAMENTO);

        if (lstJefesDpto != null) {
            for (EmpleadoDTO empleadoJefe : lstJefesDpto) {
                empleadoJefe.setNombre(empleadoJefe.getNombreCompleto());
            }
        }

        consultarPropAsignadaHelper.setListaJefeDepartamento(lstJefesDpto != null ? lstJefesDpto : new ArrayList<EmpleadoDTO>());
    }

    public void cargarEnlace() {

        Map<TipoEmpleadoEnum, Map<TipoEmpleadoEnum, List<EmpleadoDTO>>> suboordinados = empleadoLogueado
                .getSubordinados();
        Map<TipoEmpleadoEnum, List<EmpleadoDTO>> sub = suboordinados.get(TipoEmpleadoEnum.AUDITOR);
        List<EmpleadoDTO> lstEnlaces = sub.get(TipoEmpleadoEnum.ENLACE);

        if (lstEnlaces != null) {
            for (EmpleadoDTO empleadoEnlace : lstEnlaces) {
                empleadoEnlace.setNombre(empleadoEnlace.getNombreCompleto());
            }
        }

        consultarPropAsignadaHelper.setListaEnlace(lstEnlaces != null ? lstEnlaces : new ArrayList<EmpleadoDTO>());
    }

    public void obtenerSolictudRetroalimentacion() {
        consultarPropAsignadaHelper.setListaSolicitudRetroalimentacion(
                getValidarRetroalimentarPropuestaService().getLstOrigenRetroalimentacionPropuesta(
                        consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoRetroalimentacion').show();");
    }

    public void obtenerDocumentosRetroalimentacion() {

        consultarPropAsignadaHelper
                .setListaDocumentos(getValidarRetroalimentarPropuestaService().getDetalleDocRetroalimentacionByIdRetro(
                                consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacionOrigen()));

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public void obtenerDocumentosRetroalimentados() {
        consultarPropAsignadaHelper
                .setListaDocumentos(getValidarRetroalimentarPropuestaService().getDetalleDocRetroalimentacionByIdRetro(
                                consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacion()));
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionRetro').show();");
    }

    public void obtenerImpuestosRetro() {
        consultarPropAsignadaHelper
                .setListaImpuestos(getValidarRetroalimentarPropuestaService().getImpuestosRetroPropuesta(
                                consultarPropAsignadaHelper.getSolicitudRetroalimentacion().getIdRetroalimentacion()));

        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (consultarPropAsignadaHelper.getListaImpuestos() != null) {
            for (FecetImpuesto imp : consultarPropAsignadaHelper.getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }

        consultarPropAsignadaHelper.setPresuntiva(totalImpuesto);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoImpuestosRetro').show();");
    }

    public void obtenerDocumentosHistoricoAccion() {

        AccionesFuncionarioEnum accionesFuncionarioEnum = consultarPropAsignadaHelper.getHistoricoAccion()
                .getAccionFuncionarioEnum();
        List<FecetDocExpediente> listaDocumentos = new ArrayList<FecetDocExpediente>();

        if (accionesFuncionarioEnum != null) {
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RECHAZAR) {
                listaDocumentos = historicoPropuestaService.buscarDoctosRechazoPorIdRechazo(
                        consultarPropAsignadaHelper.getHistoricoAccion().getIdRechazo());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTAR) {
                listaDocumentos = historicoPropuestaService.obtenerDocumentoByIdRetro(
                        consultarPropAsignadaHelper.getHistoricoAccion().getIdRetroalimentacion());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.TRANSFERIR) {
                listaDocumentos = historicoPropuestaService.obtenerDocumentoByIdTransferencia(
                        consultarPropAsignadaHelper.getHistoricoAccion().getIdTransferencia());
            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.CANCELAR) {
                listaDocumentos = historicoPropuestaService.obtenerDocumentoByIdCancelacion(
                        consultarPropAsignadaHelper.getHistoricoAccion().getIdCancelacion());

            }
            if (accionesFuncionarioEnum == AccionesFuncionarioEnum.RETROALIMENTACION_ATENDIDA) {
                listaDocumentos = historicoPropuestaService.obtenerDocumentoByIdRetro(
                        consultarPropAsignadaHelper.getHistoricoAccion().getIdRetroalimentacion());
            }
        }
        consultarPropAsignadaHelper.setListaDocumentosExpediente(listaDocumentos);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dlgHistoricoDocumentacionAccion').show();");
    }

    public HistoricoPropuestaService getHistoricoPropuestaService() {
        return historicoPropuestaService;
    }

    public void setHistoricoPropuestaService(HistoricoPropuestaService historicoPropuestaService) {
        this.historicoPropuestaService = historicoPropuestaService;
    }

    public StreamedContent getDescargarArchivo() {
        StreamedContent archivo = null;
        byte[] stream;
        try {
            if (cpaParamHelper.getDocPapelSeleccionado().getInput() != null) {
                stream = IOUtils.toByteArray(cpaParamHelper.getDocPapelSeleccionado().getInput());
                cpaParamHelper.getDocPapelSeleccionado().setInput(new ByteArrayInputStream(stream));
                InputStream myInputStream = new ByteArrayInputStream(stream);
                archivo = new DefaultStreamedContent(myInputStream, "application/octet-stream",
                        cpaParamHelper.getDocPapelSeleccionado().getPapelesTrabajo().getNombreArchivo());
            } else {

                archivo = new DefaultStreamedContent(
                        new FileInputStream(
                                CargaArchivoUtilPropuestas.limpiarPathArchivo(consultarPropAsignadaHelper.getRutaArchivo())),
                        CargaArchivoUtilPropuestas.obtenContentTypeArchivo(consultarPropAsignadaHelper.getNombreArchivo()),
                        CargaArchivoUtilPropuestas.aplicarCodificacionTexto(consultarPropAsignadaHelper.getNombreArchivo()));
            }
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontr\u00f3 el documento seleccionado", "");
        } catch (IOException e) {
            logger.error("No se pudo descargar el archivo. [{}]", e);
            addErrorMessage(null, "No se encontr\u00f3 el documento seleccionado", "");
            archivo = null;
        }

        return archivo;
    }

    public ConsultarPropuestasAsignadasHelper getConsultarPropAsignadaHelper() {
        return consultarPropAsignadaHelper;
    }

    public void setConsultarPropAsignadaHelper(ConsultarPropuestasAsignadasHelper consultarPropAsignadaHelper) {
        this.consultarPropAsignadaHelper = consultarPropAsignadaHelper;
    }

    public ConsultarPropuestasAsignadasBooleanHelper getCpaBooleanHelper() {
        return cpaBooleanHelper;
    }

    public void setCpaBooleanHelper(ConsultarPropuestasAsignadasBooleanHelper cpaBooleanHelper) {
        this.cpaBooleanHelper = cpaBooleanHelper;
    }

    public ConsultarPropuestasAsignadasDtoHelper getCpaDtoHelper() {
        return cpaDtoHelper;
    }

    public void setCpaDtoHelper(ConsultarPropuestasAsignadasDtoHelper cpaDtoHelper) {
        this.cpaDtoHelper = cpaDtoHelper;
    }

    public ConsultarPropuestasAsignadasStringHelper getCpaStringHelper() {
        return cpaStringHelper;
    }

    public void setCpaStringHelper(ConsultarPropuestasAsignadasStringHelper cpaStringHelper) {
        this.cpaStringHelper = cpaStringHelper;
    }

    public ConsultarPropuestasAsignadasParamHelper getCpaParamHelper() {
        return cpaParamHelper;
    }

    public void setCpaParamHelper(ConsultarPropuestasAsignadasParamHelper cpaParamHelper) {
        this.cpaParamHelper = cpaParamHelper;
    }

    public ConsultarPapelesTrabajoService getConsultarPapelesTrabajoService() {
        return consultarPapelesTrabajoService;
    }

    public void setConsultarPapelesTrabajoService(ConsultarPapelesTrabajoService consultarPapelesTrabajoService) {
        this.consultarPapelesTrabajoService = consultarPapelesTrabajoService;
    }

    public ValidaAccionesHelper getValidaAccionesHelper() {
        return validaAccionesHelper;
    }

    public void setValidaAccionesHelper(ValidaAccionesHelper validaAccionesHelper) {
        this.validaAccionesHelper = validaAccionesHelper;
    }

    public ValidarRetroalimentarPropuestaService getValidarRetroalimentarPropuestaService() {
        return validarRetroalimentarPropuestaService;
    }

    public void setValidarRetroalimentarPropuestaService(
            ValidarRetroalimentarPropuestaService validarRetroalimentarPropuestaService) {
        this.validarRetroalimentarPropuestaService = validarRetroalimentarPropuestaService;
    }

    public VerificarProcedenciaService getVerificarProcedenciaService() {
        return verificarProcedenciaService;
    }

    public void setVerificarProcedenciaService(VerificarProcedenciaService verificarProcedenciaService) {
        this.verificarProcedenciaService = verificarProcedenciaService;
    }

    public FecebAccionPropuestaService getFecebAccionPropuestaService() {
        return fecebAccionPropuestaService;
    }

    public void setFecebAccionPropuestaService(FecebAccionPropuestaService fecebAccionPropuestaService) {
        this.fecebAccionPropuestaService = fecebAccionPropuestaService;
    }

    public ConsultarPropAsignadasMBHelper getConsultarPropAsignadasMBHelper() {
        return consultarPropAsignadasMBHelper;
    }

    public void setConsultarPropAsignadasMBHelper(ConsultarPropAsignadasMBHelper consultarPropAsignadasMBHelper) {
        this.consultarPropAsignadasMBHelper = consultarPropAsignadasMBHelper;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public void setConsultaMediosContactoService(ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    public EmpleadoDTO getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public void setEmpleadoLogueado(EmpleadoDTO empleadoLogueado) {
        this.empleadoLogueado = empleadoLogueado;
    }

    public PistasAuditoriasPropuestasService getPistasAuditoriasPropuestasService() {
        return pistasAuditoriasPropuestasService;
    }

    public void setPistasAuditoriasPropuestasService(PistasAuditoriasPropuestasService pistasAuditoriasPropuestasService) {
        this.pistasAuditoriasPropuestasService = pistasAuditoriasPropuestasService;
    }

}
