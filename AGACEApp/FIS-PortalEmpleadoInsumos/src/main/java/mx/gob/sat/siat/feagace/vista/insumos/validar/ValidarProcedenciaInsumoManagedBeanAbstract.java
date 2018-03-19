package mx.gob.sat.siat.feagace.vista.insumos.validar;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ACPPCE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORESTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORTE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_OCCIDENTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_PACIFICO_NORTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_SUR;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteEmpleadoException;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AsignacionPropuesta;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.SolicitudRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoAttributesHelper;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoDTOHelper;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class ValidarProcedenciaInsumoManagedBeanAbstract extends ValidarProcedenciaFieldAbstractMB {

    private static final long serialVersionUID = 2409538610838810807L;

    public ValidarProcedenciaInsumoManagedBeanAbstract() {
        setVpInsumoAttributesHelper(new ValidarProcedenciaInsumoAttributesHelper());
        setVpInsumoDTOHelper(new ValidarProcedenciaInsumoDTOHelper());
    }

    @PostConstruct
    public void init() {
        reseteaPaneles();
        getVpInsumoAttributesHelper().setIdAraceSeleccionada(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setIdMetodoSeleccionado(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setMostrarPanelOrdenes(true);
        getVpInsumoAttributesHelper().setMostrarTablaArchivosRetro(false);
        getVpInsumoAttributesHelper().setMostrarBotonProgramacion(false);
        getVpInsumoAttributesHelper().setFechaActual(new Date());
        getVpInsumoAttributesHelper().setIdInsumoHistorico("");
        getVpInsumoAttributesHelper().setQuintoOctavoDigito(new SimpleDateFormat("yyyy").format(new Date()));

        getVpInsumoDTOHelper().setDocumentoSeleccionadoRetro(new FecetContadorInsumos());
        getVpInsumoDTOHelper().setDocumentoSeleccionadoPropuesta(new FecetDocExpediente());
        getVpInsumoDTOHelper().setListaDocretroInsumoCargados(new ArrayList<FecetDocretroinsumo>());
        getVpInsumoDTOHelper().setDocretroInsumoCargadoSeleccionado(new FecetDocretroinsumo());
        getVpInsumoDTOHelper().setListaInsumo(new ArrayList<FecetInsumo>());
        getVpInsumoDTOHelper().setListaDocRetroInsumo(new ArrayList<FecetDocretroinsumo>());
        getVpInsumoDTOHelper().setListaDocRechazoInsumo(new ArrayList<FecetDocrechazoinsumo>());
        getVpInsumoDTOHelper().setListaDocumentoPropuesta(new ArrayList<FecetDocExpediente>());
        getVpInsumoDTOHelper().setDocretroInsumoSeleccionado(new FecetDocretroinsumo());
        getVpInsumoDTOHelper().setDocrechazoInsumoSeleccionado(new FecetDocrechazoinsumo());
        getVpInsumoDTOHelper().setImpuestoVO(new ImpuestoVO());
        getVpInsumoAttributesHelper().setTipoEmpleado(true);

        limpiaFiltros("formValidarInsumo:tablaOrdenPorValidarSeguimiento");
        limpiaFiltros("formValidarInsumo:tablaInsumosFiltro");
        try {
            getVpInsumoDTOHelper().setEmpleadoDTO(obtenerEmpleadoAcceso(getRFCSession(), TipoEmpleadoEnum.VALIDADOR_INSUMOS));
            if (getVpInsumoDTOHelper().getEmpleadoDTO() != null && !getVpInsumoDTOHelper().getEmpleadoDTO().getDetalleEmpleado().isEmpty()
                    && !getVpInsumoDTOHelper().getEmpleadoDTO().getDetalleEmpleado().get(0).getTipoEmpleado().equals(TipoEmpleadoEnum.ASIGNADOR_INSUMOS)) {
                getVpInsumoAttributesHelper().setTipoEmpleado(false);
            }

            getVpInsumoAttributesHelper().setAreaOrigen(new BigDecimal(getVpInsumoDTOHelper().getEmpleadoDTO().getDetalleEmpleado().get(0).getCentral().getIdArace()));
            cargaInsumos();
        } catch (NoExisteEmpleadoException e) {
            informeErrorSession(e);
        }
    }

    public void cargaCombos() {
        //Carga Unidades
        getVpInsumoDTOHelper().setListaUnidades(getValidarProcedenciaInsumoService().getCatalogoUnidadInsumos());
        //Carga metodos de revision
        getVpInsumoDTOHelper().setListaMetodoRevision(getValidarProcedenciaInsumoService().getCatalogoMetodo());
        //Carga tipo propuestas
        getVpInsumoDTOHelper().setListaTipoPropuesta(getValidarProcedenciaInsumoService().getCatalogoTipoPropuesta());
        //Carga causa Programacion
        getVpInsumoDTOHelper().setListaCausaProgramacion(getValidarProcedenciaInsumoService().getCatalogoCausaProgramacion());
        //Carga Tipo Revision
        getVpInsumoDTOHelper().setListaTipoRevision(getValidarProcedenciaInsumoService().getCatalogoRevision());
        //Carga Tipo Impuesto
        getVpInsumoDTOHelper().setListaTipoImpuesto(getValidarProcedenciaInsumoService().getCatalogoImpuesto());
        //Limpia lista de impuestos
        getVpInsumoDTOHelper().setListaImpuestos(new ArrayList<ImpuestoVO>());
    }

    private void cargaInsumos() {
        getVpInsumoDTOHelper().setListaInsumo(getValidarProcedenciaInsumoService().obtenerInsumos(getRFCSession(), obtenerGrupoUnidadesAdminXGeneral(getVpInsumoDTOHelper().getEmpleadoDTO())));
        getVpInsumoDTOHelper().setListaEstatusInsumo(new ArrayList<String>());
        if (!getVpInsumoDTOHelper().getListaInsumo().isEmpty()) {
            for (FecetInsumo insumo : getVpInsumoDTOHelper().getListaInsumo()) {
                if (!getVpInsumoDTOHelper().getListaEstatusInsumo().contains(insumo.getFececEstatus().getDescripcion())) {
                    getVpInsumoDTOHelper().getListaEstatusInsumo().add(insumo.getFececEstatus().getDescripcion());
                }
            }
            Collections.sort(getVpInsumoDTOHelper().getListaEstatusInsumo());
        }
    }

    public void reseteaPaneles() {
        getVpInsumoAttributesHelper().setMostrarPanelOrdenes(false);
        getVpInsumoAttributesHelper().setMostrarPanelInsumo(false);
        getVpInsumoAttributesHelper().setMostrarOpcionesPropuesta(false);
    }

    public void inicializarVariables() {
        reseteaPaneles();
        init();
        getVpInsumoAttributesHelper().setIdAraceSeleccionada(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setIdMetodoSeleccionado(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setMostrarPanelOrdenes(true);
    }

    public boolean validarRegistroInsumo() {
        boolean datosValidos = true;

        if (getVpInsumoAttributesHelper().getIdAraceSeleccionada().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbUnidad", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }

        if (getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getPropuesta().getIdTipoPropuesta().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbPropuesta", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getPropuesta().getIdCausaProgramacion().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbCausa", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleRevision()
                && getVpInsumoDTOHelper().getPropuesta().getIdRevision().equals(BigDecimal.valueOf(-1L))) {
            FacesUtil.addErrorMessage("formValidarInsumo:cmbRevision", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleFechaPre()
                && getVpInsumoDTOHelper().getPropuesta().getFechaPresentacion() == null) {
            FacesUtil.addErrorMessage("formValidarInsumo:txtFechaPresentacion", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleFechaInf()
                && getVpInsumoDTOHelper().getPropuesta().getFechaInforme() == null) {
            FacesUtil.addErrorMessage("formValidarInsumo:txtFechaInformacion", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        return datosValidos;
    }

    public void registrarPropuesta() {
        validarDuplicidadAntecedentes(getVpInsumoDTOHelper().getPropuesta().getFecetContribuyente().getRfc(),
                getVpInsumoDTOHelper().getPropuesta().getFechaInicioPeriodo(),
                getVpInsumoDTOHelper().getPropuesta().getFechaFinPeriodo());
    }

    private void validarDuplicidadAntecedentes(final String rfcContribuyente, final Date fechaInicial,
            final Date fechaFinal) {
        List<String> listaAntecedentes = verificarAntecedentes(rfcContribuyente, fechaInicial, fechaFinal);

        if (listaAntecedentes.isEmpty()) {
            guardarPropuesta();
        } else {
            StringBuilder texto = new StringBuilder();
            for (int index = 0; index < listaAntecedentes.size(); index++) {
                texto.append(index + 1).append(".- ").append(listaAntecedentes.get(index)).append("<br>");
            }
            getVpInsumoAttributesHelper().setMensajeAntecedentes(texto.toString());
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('confirmarContinuarRegistrando').show();");
        }
    }

    public void guardarPropuesta() {
        try {

            final BigDecimal idInsumoOriginal = getVpInsumoDTOHelper().getInsumoSeleccionado().getIdInsumo();
            final BigDecimal clave = getVpInsumoDTOHelper().getPropuesta().getIdArace();

            String terceroCuartoDigito;
            if (clave.toString().length() > 1) {
                terceroCuartoDigito = clave.toString();
            } else {
                terceroCuartoDigito = "0" + clave.toString();
            }

            getVpInsumoAttributesHelper().setNovenoDoceavoDigito(consecutivoPropuesta());
            String folioNuevaPropuesta = "E" + getVpInsumoAttributesHelper().getSegundoDigito() + terceroCuartoDigito
                    + getVpInsumoAttributesHelper().getQuintoOctavoDigito()
                    + getVpInsumoAttributesHelper().getNovenoDoceavoDigito();

            getVpInsumoDTOHelper().getPropuesta().setIdArace(getVpInsumoAttributesHelper().getIdAraceSeleccionada());
            getVpInsumoDTOHelper().getPropuesta().setIdMetodo(getVpInsumoAttributesHelper().getIdMetodoSeleccionado());

            getValidarProcedenciaInsumoService().registrarPropuesta(idInsumoOriginal,
                    getVpInsumoDTOHelper().getPropuesta(), folioNuevaPropuesta,
                    getVpInsumoDTOHelper().getListaImpuestos(), getVpInsumoDTOHelper().getListaDocumentoInsumo());

            getVpInsumoDTOHelper().getPropuesta().setIdRegistro(folioNuevaPropuesta);
            enviarCorreoRegistroInsumoPropuesta(getVpInsumoDTOHelper().getPropuesta());

            init();
            FacesUtil.addInfoMessage(null, "La Propuesta con Id " + folioNuevaPropuesta
                    + " ha sido registrada y ser\u00e1 revisada por el area correspondiente.", "");
        } catch (NegocioException e) {
            logger.error("No se pudo registrar la propuesta  " + e.getCause(), e);
        }
    }

    private void enviarCorreoRegistroInsumoPropuesta(final FecetPropuesta propuesta) {
        Map<String, String> data = new HashMap<String, String>();

        data.put(Common.SUBJECT.toString(),
                FacesUtil.getMessageResourceString("email.notificacion.asignacion.propuesta"));
        data.put(AsignacionPropuesta.NUM_ID.toString(), propuesta.getIdRegistro());
        data.put(Common.RECEIVER.toString(), getRFCSession());
    }

    @SuppressWarnings("resource")
    private String consecutivoPropuesta() {
        BigDecimal consecutivo;
        String claveFinal = null;
        String str3cerosizq = "%04d";
        Formatter fmt = null;

        fmt = new Formatter();

        getVpInsumoDTOHelper().getPropuesta().setIdPropuesta(getValidarProcedenciaInsumoService().getConsecutivo());
        consecutivo = getValidarProcedenciaInsumoService().getIdRegistroConsecutivo();

        claveFinal = fmt.format(str3cerosizq, Long.parseLong(consecutivo.toString())).toString();

        return claveFinal;
    }

    public void limpiarFormulario() {
        getVpInsumoDTOHelper().getPropuesta().setIdArace(new BigDecimal(-1));
        getVpInsumoDTOHelper().getPropuesta().setIdMetodo(new BigDecimal(-1));
        getVpInsumoDTOHelper().getPropuesta().setIdTipoPropuesta(new BigDecimal(-1));
        getVpInsumoDTOHelper().getPropuesta().setIdCausaProgramacion(new BigDecimal(-1));
        getVpInsumoDTOHelper().getPropuesta().setIdRevision(new BigDecimal(-1));
        getVpInsumoDTOHelper().getPropuesta().setFechaPresentacion(null);
        getVpInsumoDTOHelper().getPropuesta().setFechaInforme(null);
        cargaListaDocumentos();
        getVpInsumoDTOHelper().setListaImpuestos(new ArrayList<ImpuestoVO>());
        getVpInsumoAttributesHelper().setIdAraceSeleccionada(new BigDecimal(-1));
        getVpInsumoAttributesHelper().setIdMetodoSeleccionado(new BigDecimal(-1));
        activarCampos();
    }

    public void activarCampos() {
        boolean validaAreaOrigen = (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE))
                || (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO))
                || (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_NORESTE))
                || (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_OCCIDENTE));
        if (getVpInsumoAttributesHelper().getAreaOrigen().equals(ACPPCE)) {
            getVpInsumoAttributesHelper().setVisibleUnidad(true);
            getVpInsumoAttributesHelper().setVisibleRevision(false);
            getVpInsumoAttributesHelper().setVisibleFechaPre(false);
            getVpInsumoAttributesHelper().setVisibleFechaInf(false);
            getVpInsumoAttributesHelper().setFechaRegional(":");
        } else if (validaAreaOrigen || (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_CENTRO))
                || (getVpInsumoAttributesHelper().getAreaOrigen().equals(ARACE_SUR))) {
            getVpInsumoAttributesHelper().setVisibleUnidad(false);
            getVpInsumoAttributesHelper().setVisibleRevision(true);
            getVpInsumoAttributesHelper().setVisibleFechaPre(true);
            getVpInsumoAttributesHelper().setVisibleFechaInf(true);
            getVpInsumoAttributesHelper().setFechaRegional(" Regional:");
        }
    }

    public void descartarDocumentoPropuesta() {
        List<FecetDocExpediente> toRemove = new ArrayList<FecetDocExpediente>();
        for (FecetDocExpediente documento : getVpInsumoDTOHelper().getListaDocumentoPropuesta()) {
            if (getVpInsumoDTOHelper().getDocumentoSeleccionadoPropuesta().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        getVpInsumoDTOHelper().getListaDocumentoPropuesta().removeAll(toRemove);
        FacesUtil.addInfoMessage("formInsumo:msgExitoDescartarOficioAnexo", "Se descarto el documento correctamente.");
    }

    public void cargaListaDocumentos() {
        getVpInsumoDTOHelper().setListaDocumentoInsumo(getValidarProcedenciaInsumoService().obtenerDocumentos(getVpInsumoDTOHelper().getInsumoSeleccionado()));
        getVpInsumoDTOHelper().setListaDocumentosJustificacion(getValidarProcedenciaInsumoService().obtenerDocumentosByIdInsumo(getVpInsumoDTOHelper().getInsumoSeleccionado()));
        getVpInsumoDTOHelper().setNumeroDeDocumentos(getVpInsumoDTOHelper().getListaDocumentosJustificacion() == null ? 0 : getVpInsumoDTOHelper().getListaDocumentosJustificacion().size());
        getVpInsumoAttributesHelper().setMostrarBotonProgramacion(Constantes.INSUMO_ACEPTADO.equals(getVpInsumoDTOHelper().getInsumoSeleccionado().getIdEstatus()));
    }

    protected void enviarCorreoSolicitudRetroalimentacionInsumo(final FecetInsumo insumo)
            throws NegocioException {
        Set<String> destinatarios = new TreeSet<String>();
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcCreacion(), Constantes.USUARIO_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcSubadministrador(), Constantes.USUARIO_VALIDADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getIdUnidadAdministrativa(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);

        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_SOLICITUD_RETROALIMENTACION_INSUMO);
        enviarCorreoNotificacion(insumo, data, destinatarios, null);
    }

    protected void enviarCorreoRechazoInsumo(final FecetInsumo insumo) throws NegocioException {
        String nombre = "";
        try {
            EmpleadoDTO empleado = obtenerEmpleadoAcceso(insumo.getRfcSubadministrador(), TipoEmpleadoEnum.VALIDADOR_INSUMOS);
            nombre = empleado.getNombreCompleto();
        } catch (NoExisteEmpleadoException e) {
            logger.error("Error al obtener empleado", e);
        }
        Set<String> destinatarios = new TreeSet<String>();
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcCreacion(), Constantes.USUARIO_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcAdministrador(), Constantes.USUARIO_ASIGNADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(insumo.getRfcSubadministrador(), Constantes.USUARIO_VALIDADOR_INSUMOS, destinatarios, ClvSubModulosAgace.INSUMOS);

        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, insumo.getFececUnidadAdministrativa().getIdUnidadAdministrativa(), destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_CONSULTOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_ASIGNADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);
        getNotificacionService().obtenerCorreoEmpleado(Constantes.USUARIO_VALIDADOR_INSUMOS, Constantes.ACIACE, destinatarios, ClvSubModulosAgace.INSUMOS);

        Map<String, String> data = getNotificacionService().obtenerDatosCorreo(Constantes.LEYENDA_INSUMO_RECHAZADO);
        enviarCorreoNotificacion(insumo, data, destinatarios, nombre);
    }

    protected void enviarCorreoNotificacion(FecetInsumo insumo, Map<String, String> data, Set<String> destinatarios, String nombre) {
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO.toString(), insumo.getIdRegistro());
        data.put(SolicitudRetroalimentacionInsumo.ID_REGISTRO_ESPACIO.toString(), insumo.getIdRegistro());
        data.put("Id_ Registro", insumo.getIdRegistro());
        data.put(SolicitudRetroalimentacionInsumo.NOMBRE_SUB_ADMINISTRADOR.toString(), nombre);
        try {
            getNotificacionService().enviarNotificacionGenerico(data, ReportType.AVISOS_INSUMO_GENERICO, destinatarios);
        } catch (BusinessException ex) {
            logger.error("Error al enviar el correo [{}]", ex.getCause());
        }
    }

    public StreamedContent getArchivoOficioAnexo() {
        return getDescargaArchivo(getVpInsumoDTOHelper().getDocumentoSeleccionado().getRutaArchivo() + getVpInsumoDTOHelper().getDocumentoSeleccionado().getNombre(), getVpInsumoDTOHelper().getDocumentoSeleccionado().getNombre());
    }

    public StreamedContent getArchivoDescargaRetroalimentacion() {
        return getDescargaArchivo(
                getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getRutaArchivo()
                + getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getNombreArchivo(),
                getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getNombreArchivo());
    }
}
