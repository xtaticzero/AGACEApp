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
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.FececLeyendaService;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.insumos.SeguimientoInsumosService;
import mx.gob.sat.siat.feagace.negocio.propuestas.validar.ValidarProcedenciaInsumoService;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.AsignacionPropuesta;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoAttributesHelper;
import mx.gob.sat.siat.feagace.vista.insumos.validar.helper.ValidarProcedenciaInsumoDTOHelper;

public class ValidarProcedenciaInsumoManagedBeanAbstract extends AbstractManagedBean {

    private static final long serialVersionUID = 2409538610838810807L;

    private ValidarProcedenciaInsumoAttributesHelper vpInsumoAttributesHelper;
    private ValidarProcedenciaInsumoDTOHelper vpInsumoDTOHelper;

    @ManagedProperty(value = "#{validarProcedenciaInsumoService}")
    private transient ValidarProcedenciaInsumoService validarProcedenciaInsumoService;

    @ManagedProperty(value = "#{seguimientoInsumosService}")
    private transient SeguimientoInsumosService seguimientoInsumosService;

    @ManagedProperty(value = "#{fececLeyendaService}")
    private transient FececLeyendaService fececLeyendaService;

    public FececLeyendaService getFececLeyendaService() {
        return fececLeyendaService;
    }

    public void setFececLeyendaService(FececLeyendaService fececLeyendaService) {
        this.fececLeyendaService = fececLeyendaService;
    }

    public SeguimientoInsumosService getSeguimientoInsumosService() {
        return seguimientoInsumosService;
    }

    public void setSeguimientoInsumosService(SeguimientoInsumosService seguimientoInsumosService) {
        this.seguimientoInsumosService = seguimientoInsumosService;
    }

    private transient UploadedFile archivoRechazo;
    private transient UploadedFile archivoRetroalimentacion;
    private transient UploadedFile archivoCarga;
    private transient StreamedContent archivoDescargaRetroalimentacion;
    private transient StreamedContent archivoOficioAnexo;
    private StreamedContent documentoSeleccionDescarga;
    private FecetDocExpInsumo documentoSeleccionado;

    public StreamedContent getDocumentoSeleccionDescarga() {
        documentoSeleccionDescarga = getDescargaArchivo(
                this.documentoSeleccionado.getRutaArchivo() + this.documentoSeleccionado.getNombre(),
                this.documentoSeleccionado.getNombre());

        return documentoSeleccionDescarga;
    }

    public void setDocumentoSeleccionado(final FecetDocExpInsumo documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpInsumo getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionDescarga(StreamedContent documentoSeleccionDescarga) {
        this.documentoSeleccionDescarga = documentoSeleccionDescarga;
    }

    public ValidarProcedenciaInsumoManagedBeanAbstract() {
        vpInsumoAttributesHelper = new ValidarProcedenciaInsumoAttributesHelper();
        vpInsumoDTOHelper = new ValidarProcedenciaInsumoDTOHelper();
    }

    @PostConstruct
    public void init() {
        reseteaPaneles();
        getVpInsumoAttributesHelper().setIdAraceSeleccionada(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setIdMetodoSeleccionado(BigDecimal.valueOf(-1L));
        getVpInsumoAttributesHelper().setMostrarPanelOrdenes(true);
        getVpInsumoAttributesHelper().setMostrarTablaArchivosRetro(false);
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

        limpiaFiltros("formValidarInsumo:tablaOrdenPorValidarSeguimiento");
        limpiaFiltros("formValidarInsumo:tablaInsumosFiltro");
        try {

            if (getVpInsumoDTOHelper().getFececEmpleado() != null) {
                getVpInsumoAttributesHelper().setAreaOrigen(
                        getVpInsumoDTOHelper().getFececEmpleado().getFecetDetalleEmpleado().getIdCentral());

                cargaInsumos();
            } else {
                if (getSession().getAttribute("isRegistroPropuesta") == null) {
                    throw new NegocioException("No se encuentra el perfil del empleado");
                }
            }

        } catch (NegocioException e) {
            informeErrorSession(e);
        }
    }

    private void cargaInsumos() throws NegocioException {

        getVpInsumoDTOHelper().setListaInsumo(validarProcedenciaInsumoService.obtenerInsumos(getRFCSession()));
        getVpInsumoDTOHelper().setListaEstatusInsumo(new ArrayList<String>());
        if (!getVpInsumoDTOHelper().getListaInsumo().isEmpty()) {
            for (FecetInsumo insumo : getVpInsumoDTOHelper().getListaInsumo()) {

                if (!getVpInsumoDTOHelper().getListaEstatusInsumo()
                        .contains(insumo.getFececEstatus().getDescripcion())) {
                    getVpInsumoDTOHelper().getListaEstatusInsumo().add(insumo.getFececEstatus().getDescripcion());
                }

            }
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
            addErrorMessage("formValidarInsumo:cmbUnidad", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }

        if (getVpInsumoAttributesHelper().getIdMetodoSeleccionado().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formValidarInsumo:cmbMetodo", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getPropuesta().getIdTipoPropuesta().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formValidarInsumo:cmbPropuesta", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoDTOHelper().getPropuesta().getIdCausaProgramacion().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formValidarInsumo:cmbCausa", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleRevision()
                && getVpInsumoDTOHelper().getPropuesta().getIdRevision().equals(BigDecimal.valueOf(-1L))) {
            addErrorMessage("formValidarInsumo:cmbRevision", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleFechaPre()
                && getVpInsumoDTOHelper().getPropuesta().getFechaPresentacion() == null) {
            addErrorMessage("formValidarInsumo:txtFechaPresentacion", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        }
        if (getVpInsumoAttributesHelper().isVisibleFechaInf()
                && getVpInsumoDTOHelper().getPropuesta().getFechaInforme() == null) {
            addErrorMessage("formValidarInsumo:txtFechaInformacion", Constantes.CAMPO_OBLIGATORIO_IMPUESTO);
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
            addMessage(null, "La Propuesta con Id " + folioNuevaPropuesta
                    + " ha sido registrada y ser\u00e1 revisada por el area correspondiente.", "");
        } catch (NegocioException e) {
            logger.error("No se pudo registrar la propuesta  " + e.getCause(), e);
        }
    }

    private void enviarCorreoRegistroInsumoPropuesta(final FecetPropuesta propuesta) {
        Map<String, String> data = new HashMap<String, String>();

        data.put(Common.SUBJECT.toString(),
                getMessageResourceString("email.notificacion.asignacion.propuesta"));
        data.put(AsignacionPropuesta.NUM_ID.toString(), propuesta.getIdRegistro());
        data.put(Common.RECEIVER.toString(), getRFCSession());
    }

    @SuppressWarnings("resource")
    private String consecutivoPropuesta() {
        BigDecimal consecutivo;
        String claveFinal = null;
        String str3cerosizq = "%04d";
        Formatter fmt = new Formatter();

        getVpInsumoDTOHelper().getPropuesta().setIdPropuesta(validarProcedenciaInsumoService.getConsecutivo());
        consecutivo = validarProcedenciaInsumoService.getIdRegistroConsecutivo();

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
        addMessage("formInsumo:msgExitoDescartarOficioAnexo", "Se descarto el documento correctamente.");
    }

    public void cargaListaDocumentos() {

        getVpInsumoDTOHelper().setListaDocumentoInsumo(validarProcedenciaInsumoService.obtenerDocumentos(getVpInsumoDTOHelper().getInsumoSeleccionado()));

    }

    public StreamedContent getArchivoOficioAnexo() {
        archivoOficioAnexo = getDescargaArchivo(
                getVpInsumoDTOHelper().getDocumentoSeleccionado().getRutaArchivo()
                + getVpInsumoDTOHelper().getDocumentoSeleccionado().getNombre(),
                getVpInsumoDTOHelper().getDocumentoSeleccionado().getNombre());
        return archivoOficioAnexo;
    }

    public void setArchivoOficioAnexo(StreamedContent archivoOficioAnexo) {
        this.archivoOficioAnexo = archivoOficioAnexo;
    }

    public void setValidarProcedenciaInsumoService(ValidarProcedenciaInsumoService validarProcedenciaInsumoService) {
        this.validarProcedenciaInsumoService = validarProcedenciaInsumoService;
    }

    public ValidarProcedenciaInsumoService getValidarProcedenciaInsumoService() {
        return validarProcedenciaInsumoService;
    }

    public UploadedFile getArchivoRechazo() {
        return archivoRechazo;
    }

    public void setArchivoRechazo(UploadedFile archivoRechazo) {
        this.archivoRechazo = archivoRechazo;
    }

    public UploadedFile getArchivoRetroalimentacion() {
        return archivoRetroalimentacion;
    }

    public void setArchivoRetroalimentacion(UploadedFile archivoRetroalimentacion) {
        this.archivoRetroalimentacion = archivoRetroalimentacion;
    }

    public void setArchivoDescargaRetroalimentacion(StreamedContent archivoDescargaRetroalimentacion) {
        this.archivoDescargaRetroalimentacion = archivoDescargaRetroalimentacion;
    }

    public StreamedContent getArchivoDescargaRetroalimentacion() {
        archivoDescargaRetroalimentacion = getDescargaArchivo(
                getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getRutaArchivo()
                + getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getNombreArchivo(),
                getVpInsumoDTOHelper().getDocretroInsumoCargadoSeleccionado().getNombreArchivo());
        return archivoDescargaRetroalimentacion;
    }

    public void setArchivoCarga(final UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public ValidarProcedenciaInsumoAttributesHelper getVpInsumoAttributesHelper() {
        return vpInsumoAttributesHelper;
    }

    public void setVpInsumoAttributesHelper(ValidarProcedenciaInsumoAttributesHelper vpInsumoAttributesHelper) {
        this.vpInsumoAttributesHelper = vpInsumoAttributesHelper;
    }

    public ValidarProcedenciaInsumoDTOHelper getVpInsumoDTOHelper() {
        return vpInsumoDTOHelper;
    }

    public void setVpInsumoDTOHelper(ValidarProcedenciaInsumoDTOHelper vpInsumoDTOHelper) {
        this.vpInsumoDTOHelper = vpInsumoDTOHelper;
    }

}
