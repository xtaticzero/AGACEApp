package mx.gob.sat.siat.feagace.vista.propuestas;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.CAMPO_OBLIGATORIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.OrigenCentralRegionalServ;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasTransferidasPendValidService;
import mx.gob.sat.siat.feagace.negocio.propuestas.RegistrarPropuestaIndividualService;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;
import mx.gob.sat.siat.feagace.vista.helper.propuestas.PropTransferidasPendValidDtoHelper;
import mx.gob.sat.siat.feagace.vista.helper.propuestas.PropTransferidasPendValidListHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.ContadorOrdenesValidarFirmarMB;

@ManagedBean(name = "propuestasTransferidasPendValidMB")
@SessionScoped
public class PropuestasTransferidasPendValidMB extends BaseManagedBean {

    private static final long serialVersionUID = 5296312856798192360L;

    @ManagedProperty(value = "#{propuestasTransferidasPendValidService}")
    private transient PropuestasTransferidasPendValidService propuestasTransferidasPendValidService;

    private static final String ERROR_DOCUMENTO_ADJUNTO = "No se pudo adjuntar el documento ";
    private static final String ARCHIVO_CARGADO = "Archivo cargado exitosamente";
    private static final String TIPO_ARCHIVO_INVALIDO = "Debe subir un archivo de tipo: PDF, Word o Excel 2007 o superior";
    private static final String ARCHIVO_DUPLICADO = "El archivo ya fue cargado";
    private static final String RECHAZO = "RECHAZO";

    private static final long N_4196000L = 4194304L;

    @ManagedProperty(value = "#{propuestasRechazadasVerifDoctoService}")
    private transient PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService;

    @ManagedProperty(value = "#{origenCentralRegionalService}")
    private transient OrigenCentralRegionalServ origenCentralRegionalService;

    @ManagedProperty(value = "#{registrarPropuestaIndividualService}")
    private transient RegistrarPropuestaIndividualService registrarPropuestaIndividualService;

    @ManagedProperty(value = "#{contadorOrdenesValidarFirmar}")
    private ContadorOrdenesValidarFirmarMB contadorOrdenesValidarFirmar;

    private PropTransferidasPendValidDtoHelper propTransPendValidDtoHelper;
    private PropTransferidasPendValidListHelper propTransPendValidLstHelper;

    public PropuestasTransferidasPendValidMB() {
        propTransPendValidDtoHelper = new PropTransferidasPendValidDtoHelper();
        propTransPendValidLstHelper = new PropTransferidasPendValidListHelper();
    }

    public String consultaContribuyenteBD() {
        logger.info("Se va a consultar rfc:{} idRegistro:{} idPropuesta:{}", propTransPendValidDtoHelper.getRfc(),
                propTransPendValidDtoHelper.getIdRegistro(), propTransPendValidDtoHelper.getIdPropuesta());
        try {
            propTransPendValidDtoHelper.setPropuesta(origenCentralRegionalService
                    .obtienePropuestaByidPropuesta(new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta())));
            tipoPropuesta();
        } catch (NegocioException e) {
            logger.error("Error al consultar los datos de la propuesta: {}", e.getMessage());
        }
        try {
            propTransPendValidDtoHelper.setContribuyente(origenCentralRegionalService
                    .getContribuyente(propTransPendValidDtoHelper.getPropuesta().getIdContribuyente()));
        } catch (NegocioException e) {
            logger.error("Error al consultar los datos del contribuyente: {}", e.getMessage());
        }

        propTransPendValidLstHelper.setListaTipoImpuesto(registrarPropuestaIndividualService.getCatalogoImpuesto());
        try {
            propTransPendValidLstHelper.setListaImpuestos(origenCentralRegionalService
                    .getImpuestosByPropuesta(propTransPendValidDtoHelper.getPropuesta().getIdPropuesta()));
        } catch (NegocioException e) {
            logger.error("Error al consultar los impuestos de la propuesta: {}", e.getMessage());
        }
        propTransPendValidLstHelper.setListaDocumentosTabla(propuestasRechazadasVerifDoctoService
                .buscarDoctosExpedienteProp(new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta())));
        propTransPendValidLstHelper.setListaTransferencias(propuestasTransferidasPendValidService
                .buscarTransferenciasPorIdPropuesta(new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta())));
        propTransPendValidDtoHelper.setTransferencia((propTransPendValidLstHelper.getListaTransferencias() != null
                && propTransPendValidLstHelper.getListaTransferencias().get(0) != null)
                        ? propTransPendValidLstHelper.getListaTransferencias().get(0) : new FecetTransferencia());
        propTransPendValidDtoHelper.setFechaActual(new Date());
        propTransPendValidLstHelper.setListaRechazo(new ArrayList<FecetRechazoPropuesta>());
        propTransPendValidLstHelper.setListaMotivos(propuestasTransferidasPendValidService.buscarMotivos(RECHAZO));
        propTransPendValidDtoHelper.setIsTransferible(false);
        return "/firmante/transferenciaPendienteValidacion/propuestasTransferidasPendValidacion.jsf?faces-redirect=true";
    }

    public void tipoPropuesta() {
        BigDecimal tipo = propTransPendValidDtoHelper.getPropuesta().getIdTipoPropuesta();
        if (tipo.intValue() == Constantes.ENTERO_UNO) {
            propTransPendValidDtoHelper.setTipoPropuesta(false);
        } else {
            propTransPendValidDtoHelper.setTipoPropuesta(true);
        }
    }

    @Override
    public StreamedContent getDescargaArchivo(final String path, final String nombreDescarga) {
        StreamedContent archivo = null;
        logger.info("--- path archivo: [{}]", path);
        if (path != null && nombreDescarga != null) {
            try {
                archivo = new DefaultStreamedContent(new FileInputStream(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(path)),
                        PropuestaVistaCargaArchivosUtil.obtenContentTypeArchivo(nombreDescarga),
                        PropuestaVistaCargaArchivosUtil.aplicarCodificacionTexto(nombreDescarga));
            } catch (FileNotFoundException e) {
                logger.error("No se pudo descargar el archivo. [{}]", e.getMessage());
                addErrorMessage(null, "No se encontro el documento seleccionado", "");
            }
        } else {
            addErrorMessage(null, "No se encontro el documento seleccionado", "");
        }
        return archivo;
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (propTransPendValidLstHelper.getListaImpuestos() != null) {
            for (FecetImpuesto imp : propTransPendValidLstHelper.getListaImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public void visualizarDocumentosTransfer() {
        logger.info("--- Se van a obtener documentos de idTransferencia: {}",
                propTransPendValidDtoHelper.getIdTransferencia());
        buscarDocumentosTransfer(propTransPendValidDtoHelper.getIdTransferencia());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogoDocumentosTransfer').show();");
    }

    public void buscarDocumentosTransfer(String idTransferencia) {
        propTransPendValidLstHelper.setListaDocTransferencias(propuestasTransferidasPendValidService
                .buscarTransferenciasPorIdTransferencia(new BigDecimal(idTransferencia)));
    }

    public void muestraDialogoAprobarTransfer() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogConfirmarAprobarTransfer').show();");
    }

    public void aprobarTransfer() {
        String correoAuditor;
        String correoProgramador;
        BigDecimal idProgramador = BigDecimal.ZERO;
        BigDecimal idTipoEmpleado;
        List<ProgramadorPropuestaAsignadaDTO> programadores;

        propuestasTransferidasPendValidService
                .actualizaEstatusPropuesta(new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta()));

        crearPropuestaNueva();

        if (propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino()
                .intValue() == Constantes.ENTERO_DIECINUEVE
                || propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino()
                .intValue() == Constantes.ENTERO_VEINTE) {

            idTipoEmpleado = propuestasTransferidasPendValidService
                    .buscarTipoEmpleadoPropuesta(propTransPendValidDtoHelper.getPropuesta().getIdPropuesta());
            logger.info("--- idTipoEmpleado:{}", idTipoEmpleado);
            if (idTipoEmpleado.intValue() == Constantes.ENTERO_CUATRO) {
                programadores = propuestasTransferidasPendValidService.getProgramadorTransferencia(BigDecimal.ONE);
                if (programadores.size() > 0) {
                    idProgramador = programadores.get(0).getIdEmpleado();
                }
            } else {

                idProgramador = propuestasTransferidasPendValidService
                        .obtenIdProgramadorPropuesta(propTransPendValidDtoHelper.getPropuesta().getIdPropuesta());
            }
            logger.info("idProgramador:{}", idProgramador);
        } else {

            programadores = propuestasTransferidasPendValidService
                    .getProgramadorTransferencia(propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino());
            if (programadores.size() > 0) {
                idProgramador = programadores.get(0).getIdEmpleado();
            }
        }

        propuestasTransferidasPendValidService.actualizarIdProgramador(
                propTransPendValidDtoHelper.getPropuestaNueva().getIdPropuesta(), idProgramador);

        propuestasTransferidasPendValidService.desactivaTransferencia(
                propTransPendValidDtoHelper.getTransferencia().getIdTransferencia().longValue());

        propuestasTransferidasPendValidService.updateIdPropuestaNueva(
                propTransPendValidDtoHelper.getPropuestaNueva().getIdPropuesta(),
                propTransPendValidDtoHelper.getTransferencia().getIdTransferencia());

        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogConfirmarAprobarTransfer').hide();");
        propTransPendValidDtoHelper.setIsTransferible(true);
        try {

            correoAuditor = propuestasRechazadasVerifDoctoService
                    .obtenerToCorreo(propTransPendValidDtoHelper.getPropuesta().getRfcAuditor());
            correoProgramador = propuestasRechazadasVerifDoctoService.obtenerToCorreoIdEmpleado(idProgramador);
            if (correoAuditor != null && !correoAuditor.equals("")) {
                propuestasRechazadasVerifDoctoService.enviaCorreo(
                        getMessageResourceString(
                                "asunto.correo.aviso.autorizacion.propTransPendValidDtoHelper.getPropuesta().transferida"),
                        propTransPendValidDtoHelper.getIdRegistro(),
                        ReportType.AVISO_AUTORIZACION_ENVIO_PROPUESTA_TRANSFERIDA, correoAuditor);
            }
            if (correoProgramador != null && !correoProgramador.equals("")) {
                propuestasRechazadasVerifDoctoService.enviaCorreo(
                        getMessageResourceString(
                                "asunto.correo.aviso.autorizacion.propTransPendValidDtoHelper.getPropuesta().transferida"),
                        propTransPendValidDtoHelper.getPropuestaNueva().getIdRegistro(),
                        ReportType.AVISO_AUTORIZACION_ENVIO_PROPUESTA_TRANSFERIDA, correoProgramador);
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al enviar el correo: {}", e.getMessage());
        }
    }

    public void crearPropuestaNueva() {
        propTransPendValidDtoHelper.setPropuestaNueva(new FecetPropuesta());
        String folio = generarFolioPropuesta();
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdContribuyente(propTransPendValidDtoHelper.getContribuyente().getIdContribuyente());
        propTransPendValidDtoHelper.getPropuestaNueva().setIdRegistro(folio);
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdEstatus(ConstantesPropuestas.PROPUESTA_TRANSFERENCIA_APROBADA);
        propTransPendValidDtoHelper.getPropuestaNueva().setFechaCreacion(new Date());
        propTransPendValidDtoHelper.getPropuestaNueva().setOrigenPropuestaId(ConstantesPropuestas.PROPUESTA_INDIVIDUAL);
        propTransPendValidDtoHelper.getPropuestaNueva().setRfcCreacion(getRFCSession());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdArace(propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdSubprograma(propTransPendValidDtoHelper.getPropuesta().getIdSubprograma());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setFechaInicioPeriodo(propTransPendValidDtoHelper.getPropuesta().getFechaInicioPeriodo());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setFechaFinPeriodo(propTransPendValidDtoHelper.getPropuesta().getFechaFinPeriodo());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setPrioridad(propTransPendValidDtoHelper.getPropuesta().getPrioridad());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdMetodo(propTransPendValidDtoHelper.getPropuesta().getIdMetodo());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdRevision(propTransPendValidDtoHelper.getPropuesta().getIdRevision());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdTipoPropuesta(propTransPendValidDtoHelper.getPropuesta().getIdTipoPropuesta());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdCausaProgramacion(propTransPendValidDtoHelper.getPropuesta().getIdCausaProgramacion());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setIdSector(propTransPendValidDtoHelper.getPropuesta().getIdSector());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setFechaPresentacion(propTransPendValidDtoHelper.getPropuesta().getFechaPresentacion());
        propTransPendValidDtoHelper.getPropuestaNueva()
                .setFechaInforme(propTransPendValidDtoHelper.getPropuesta().getFechaInforme());
        getRegistrarPropuestaIndividualService()
                .insertarPropuestaPropuestas(propTransPendValidDtoHelper.getPropuestaNueva());
        logger.info("--- Id propuesta nueva:[{}]", propTransPendValidDtoHelper.getPropuestaNueva().getIdPropuesta());
        for (FecetImpuesto impuestoItem : propTransPendValidLstHelper.getListaImpuestos()) {
            FecetImpuesto impuesto = new FecetImpuesto();
            impuesto.setIdPropuesta(propTransPendValidDtoHelper.getPropuestaNueva().getIdPropuesta());
            impuesto.setIdTipoImpuesto(impuestoItem.getIdTipoImpuesto());
            impuesto.setMonto(impuestoItem.getMonto());
            impuesto.setPeriodoInicial(impuestoItem.getPeriodoInicial());
            impuesto.setPeriodoFinal(impuestoItem.getPeriodoFinal());
            getRegistrarPropuestaIndividualService().insertarImpuesto(impuesto);
        }
        for (FecetDocExpediente docExpItem : propTransPendValidLstHelper.getListaDocumentosTabla()) {
            FecetDocExpediente fecetDocExpediente = new FecetDocExpediente();
            fecetDocExpediente.setIdPropuesta(propTransPendValidDtoHelper.getPropuestaNueva().getIdPropuesta());
            fecetDocExpediente.setNombre(docExpItem.getNombre());
            fecetDocExpediente.setFechaCreacion(new Date());
            propTransPendValidDtoHelper.getPropuestaNueva()
                    .setFecetContribuyente(propTransPendValidDtoHelper.getContribuyente());
            fecetDocExpediente.setRutaArchivo(
                    RutaArchivosUtilPropuestas.armarRutaDestinoPropuesta(propTransPendValidDtoHelper.getPropuestaNueva())
                    .concat(fecetDocExpediente.getNombre()));
            getRegistrarPropuestaIndividualService().insertarDocumento(fecetDocExpediente);
            String path = docExpItem.getRutaArchivo();
            try {
                InputStream inputStream = new FileInputStream(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(path));
                PropuestaVistaCargaArchivosUtil.guardarArchivo(inputStream, fecetDocExpediente.getRutaArchivo(),
                        docExpItem.getNombre());
            } catch (IOException e) {
                logger.info("No se pudo guardar archivo: {}", e.getMessage());
            }
        }
    }

    public String generarFolioPropuesta() {
        logger.info("-- folio anterior:[{}]", propTransPendValidDtoHelper.getPropuesta().getIdRegistro());
        String folio = propTransPendValidDtoHelper.getPropuesta().getIdRegistro();
        String folioNuevo = "";
        folioNuevo = folioNuevo.concat("E");
        folioNuevo = folioNuevo.concat(folio.substring(1, 2));
        if (propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino() != null) {
            folioNuevo = folioNuevo
                    .concat(propTransPendValidDtoHelper.getTransferencia().getIdAraceDestino().toString());
        }

        String fechaStr = DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY, new Date());
        folioNuevo = folioNuevo.concat(fechaStr.substring(Constantes.ENTERO_SEIS));
        String consecutivo = consecutivoPropuesta();
        if (consecutivo != null) {
            folioNuevo = folioNuevo.concat(consecutivo);
        }
        logger.info("-- folio nuevo:[{}]", folioNuevo);
        return folioNuevo;
    }

    private String consecutivoPropuesta() {
        BigDecimal consecutivo;
        String claveFinal = null;
        String str3CerosIzq = "%04d";
        Formatter fmt;
        try {
            fmt = new Formatter();
            propTransPendValidDtoHelper.getPropuestaNueva()
                    .setIdPropuesta(getRegistrarPropuestaIndividualService().getConsecutivo());
            consecutivo = this.consecutivoPropuestaAnio();
            claveFinal = fmt.format(str3CerosIzq, Long.parseLong(consecutivo.toString())).toString();
        } catch (NegocioException e) {
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        }
        return claveFinal;
    }

    private BigDecimal consecutivoPropuestaAnio() {
        BigDecimal consecutivoActual;
        BigDecimal consecutivoNuevo;
        try {
            consecutivoActual = getRegistrarPropuestaIndividualService().getConsecutivoAnio(this.obtenerFechaInicial(),
                    this.obtenerFechaFinal());
            if (consecutivoActual != null) {
                logger.debug("Consecutivo Actual: [{}]", consecutivoActual);
                consecutivoNuevo = consecutivoActual.add(ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA);
            } else {
                consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            }
            propTransPendValidDtoHelper.getPropuestaNueva().setConsecutivoAnio(consecutivoNuevo);
        } catch (NegocioException e) {
            consecutivoNuevo = ConstantesPropuestas.INICIAR_FOLIO_PROPUESTA;
            logger.error("No se pudo obtener el consecutivo [{}] ", e);
        }
        logger.info("--- Nuevo Consecutivo: [{}]", consecutivoNuevo);
        return consecutivoNuevo;
    }

    private Date obtenerFechaInicial() {
        Date fechaInicio = new Date();
        String fechaInicioStr = "01/01/"
                .concat(Integer.toString(propTransPendValidDtoHelper.getCalendario().get(Calendar.YEAR)));
        try {
            fechaInicio = DateUtil.getDateToFormattedString(FormatosFechasEnum.FORMATO_DIAGONAL_DDMYYYY,
                    fechaInicioStr);
        } catch (Exception e) {
            logger.error("No se pudo pasear la fecha inicial [{}] ", fechaInicioStr);
        }
        return fechaInicio;
    }

    private Date obtenerFechaFinal() {
        Date fechaFinal = new Date();
        String fechaFinalStr = "31/12/"
                .concat(Integer.toString(propTransPendValidDtoHelper.getCalendario().get(Calendar.YEAR)));
        try {
            fechaFinal = DateUtil.getDateToFormattedString(FormatosFechasEnum.FORMATO_DIAGONAL_DDMYYYY, fechaFinalStr);
        } catch (Exception e) {
            logger.error("No se pudo pasear la fecha final [{}] ", fechaFinalStr);
        }
        return fechaFinal;
    }

    public void cargaArchivoRechazo(final FileUploadEvent event) {
        if (validaArchivoCargaInsumoPropuesta(event.getFile())) {
            try {
                propTransPendValidDtoHelper.setArchivoCarga(event.getFile());
                FecetRechazoPropuesta dto = new FecetRechazoPropuesta();
                final Date fecha = new Date();
                dto.setFechaRechazo(fecha);
                dto.setNombreArchivo(PropuestaVistaCargaArchivosUtil.limpiarPathArchivo(PropuestaVistaCargaArchivosUtil
                        .aplicarCodificacionTexto(propTransPendValidDtoHelper.getArchivoCarga().getFileName())));
                if (!validaNombreArchivo(propTransPendValidLstHelper.getListaRechazo(), dto.getNombreArchivo())) {
                    dto.setArchivo(propTransPendValidDtoHelper.getArchivoCarga().getInputstream());
                    propTransPendValidLstHelper.getListaRechazo().add(dto);
                    addMessage(null, ARCHIVO_CARGADO, "");
                } else {
                    addMessage(null, ARCHIVO_DUPLICADO, "");
                }
            } catch (IOException e) {
                logger.error(ERROR_DOCUMENTO_ADJUNTO, e);
            }
        } else {
            addErrorMessage(null, TIPO_ARCHIVO_INVALIDO, "");
        }
    }

    public Boolean validaArchivoCargaInsumoPropuesta(final UploadedFile archivo) {
        if (archivo.getFileName().endsWith(Constantes.ARCHIVO_WORD_DESPUES_2007)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_PDF)
                || archivo.getFileName().endsWith(Constantes.ARCHIVO_EXCEL_DESPUES_2007)) {
            if (validaTamanoArchivo(archivo)) {
                return true;
            }
        } else {
            addErrorMessage(null, "Archivo invalido",
                    "Solo se aceptan archivos WORD, EXCEL o PDF versi\u00f3n 2007 o superior");
        }
        return false;
    }

    private Boolean validaTamanoArchivo(final UploadedFile archivo) {
        if (archivo.getSize() > 0L && archivo.getSize() <= N_4196000L) {
            return true;
        } else {
            addErrorMessage(null, "Error al cargar el archivo.", "Debe ser mayor a 1 KB y menor que 4 MB.");
        }
        return false;
    }

    public boolean validaNombreArchivo(List<FecetRechazoPropuesta> listaRechazo, String nombre) {
        for (FecetRechazoPropuesta rechazoPropuesta : listaRechazo) {
            if (rechazoPropuesta.getNombreArchivo().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void descartarDocumentoRechazo() {
        List<FecetRechazoPropuesta> toRemove = new ArrayList<FecetRechazoPropuesta>();
        for (FecetRechazoPropuesta documento : propTransPendValidLstHelper.getListaRechazo()) {
            if (propTransPendValidDtoHelper.getDocumentoSeleccionadoRechazo().equals(documento)) {
                toRemove.add(documento);
                break;
            }
        }
        propTransPendValidLstHelper.getListaRechazo().removeAll(toRemove);
        addMessage(null, "Se descarto el documento correctamente.", "");
    }

    public void validarRechazoInsumo() {
        boolean bandera = true;
        logger.info("--- listaRechazo size:{}", propTransPendValidLstHelper.getListaRechazo().size());
        if (propTransPendValidLstHelper.getListaRechazo() == null
                || propTransPendValidLstHelper.getListaRechazo().size() <= 0) {
            addErrorMessage("formRechazadasVerifDocto:fulExpedienteRechazo", "Debes cargar un documento");
            bandera = false;
        }
        logger.info("--- motivoRechazo:{}", propTransPendValidDtoHelper.getMotivoRechazo());
        if (propTransPendValidDtoHelper.getMotivoRechazo().equals("-1")) {
            addErrorMessage("formRechazadasVerifDocto:cmbMotivo", CAMPO_OBLIGATORIO);
            bandera = false;
        }
        logger.info("--- bandera:{}", bandera);
        if (bandera) {
            RequestContext rc = RequestContext.getCurrentInstance();
            rc.execute("PF('confirmarRechazo').show()");
        }
    }

    public void rechazarTransferencia() {
        String correoAuditor;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        FecetRechazoPropuesta fecetRechazo = new FecetRechazoPropuesta();
        fecetRechazo.setIdPropuesta(propTransPendValidDtoHelper.getPropuesta().getIdPropuesta());
        fecetRechazo.setIdMotivo(new BigDecimal(propTransPendValidDtoHelper.getMotivoRechazo()));
        UserProfileDTO userProfileDTO = (UserProfileDTO) session.getAttribute("userProfile");
        session.setAttribute("essuplente", false);
        BigDecimal idEmpleado = BigDecimal.ONE;
        List<AgaceOrden> orden;
        BigDecimal estatus;

        idEmpleado = propuestasRechazadasVerifDoctoService.obtenerIdEmpleado(userProfileDTO.getRfc());

        orden = propuestasTransferidasPendValidService
                .obtenerOrden(new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta()));
        if (orden != null && !orden.isEmpty()) {
            estatus = new BigDecimal(Constantes.ENTERO_CIENTO_TREINTANUEVE);
        } else {
            estatus = new BigDecimal(Constantes.ENTERO_CINCUENTATRES);
        }

        fecetRechazo.setIdEmpleado(idEmpleado);
        fecetRechazo.setRfcRechazo(getRFCSession());
        fecetRechazo.setIdEstatus(estatus);
        fecetRechazo.setEstatus(estatus);
        fecetRechazo.setFechaRechazo(new Date());
        fecetRechazo.setBlnEstatus(BigDecimal.ONE);

        propuestasTransferidasPendValidService.actualizaEstatusPropuestaRechazoT(
                new BigDecimal(propTransPendValidDtoHelper.getIdPropuesta()), estatus);
        propTransPendValidDtoHelper.getPropuesta()
                .setFecetContribuyente(propTransPendValidDtoHelper.getContribuyente());
        propuestasTransferidasPendValidService.rechazarPropuesta(propTransPendValidDtoHelper.getPropuesta(),
                fecetRechazo, propTransPendValidLstHelper.getListaRechazo());

        propuestasTransferidasPendValidService.desactivaTransferencia(
                propTransPendValidDtoHelper.getTransferencia().getIdTransferencia().longValue());

        propuestasTransferidasPendValidService.updateFechaMotivoRechazo(new Date(), fecetRechazo.getIdMotivo(),
                propTransPendValidDtoHelper.getTransferencia().getIdTransferencia());
        propTransPendValidDtoHelper.setIsTransferible(true);
        try {
            // se envia correo al auditor
            correoAuditor = propuestasRechazadasVerifDoctoService
                    .obtenerToCorreo(propTransPendValidDtoHelper.getPropuesta().getRfcAuditor());
            propuestasRechazadasVerifDoctoService.enviaCorreo(
                    getMessageResourceString("asunto.correo.aviso.rechazo.transferencia"),
                    propTransPendValidDtoHelper.getIdRegistro(), ReportType.AVISO_RECHAZO_SOLICITUD_TRANSFERENCIA,
                    correoAuditor);
        } catch (Exception e) {
            logger.error("Ocurrio un error al enviar el correo: {}", e.getMessage());
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmarRechazo').hide();");
        context.execute("PF('rechazarTransferencia').hide();");
    }

    public void muestraDialogoRechazarTransfer() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('rechazarTransferencia').show();");
    }

    public String regresar() {
        contadorOrdenesValidarFirmar.init();
        return "/firmante/validarFirmarDocElectronico/indexValidarFirmarOrdenes?faces-redirect=true";
    }

    public void setOrigenCentralRegionalService(OrigenCentralRegionalServ origenCentralRegionalService) {
        this.origenCentralRegionalService = origenCentralRegionalService;
    }

    public OrigenCentralRegionalServ getOrigenCentralRegionalService() {
        return origenCentralRegionalService;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        propTransPendValidDtoHelper.setDocumentoSeleccionDescarga(
                getDescargaArchivo(propTransPendValidDtoHelper.getDocumentoSeleccionado().getRutaArchivo(),
                        propTransPendValidDtoHelper.getDocumentoSeleccionado().getNombre()));
        return propTransPendValidDtoHelper.getDocumentoSeleccionDescarga();
    }

    public void setPropuestasRechazadasVerifDoctoService(
            PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService) {
        this.propuestasRechazadasVerifDoctoService = propuestasRechazadasVerifDoctoService;
    }

    public PropuestasRechazadasVerifDoctoService getPropuestasRechazadasVerifDoctoService() {
        return propuestasRechazadasVerifDoctoService;
    }

    public void setRegistrarPropuestaIndividualService(
            RegistrarPropuestaIndividualService registrarPropuestaIndividualService) {
        this.registrarPropuestaIndividualService = registrarPropuestaIndividualService;
    }

    public RegistrarPropuestaIndividualService getRegistrarPropuestaIndividualService() {
        return registrarPropuestaIndividualService;
    }

    public void setPropuestasTransferidasPendValidService(
            PropuestasTransferidasPendValidService propuestasTransferidasPendValidService) {
        this.propuestasTransferidasPendValidService = propuestasTransferidasPendValidService;
    }

    public PropuestasTransferidasPendValidService getPropuestasTransferidasPendValidService() {
        return propuestasTransferidasPendValidService;
    }

    public StreamedContent getDocumentoSeleccionDescargaTransfer() {
        propTransPendValidDtoHelper.setDocumentoSeleccionDescargaTransfer(
                getDescargaArchivo(propTransPendValidDtoHelper.getDocumentoSeleccionadoTransfer().getRutaArchivo(),
                        propTransPendValidDtoHelper.getDocumentoSeleccionadoTransfer().getNombreArchivo()));
        return propTransPendValidDtoHelper.getDocumentoSeleccionDescargaTransfer();
    }

    public void setContadorOrdenesValidarFirmar(ContadorOrdenesValidarFirmarMB contadorOrdenesValidarFirmar) {
        this.contadorOrdenesValidarFirmar = contadorOrdenesValidarFirmar;
    }

    public ContadorOrdenesValidarFirmarMB getContadorOrdenesValidarFirmar() {
        return contadorOrdenesValidarFirmar;
    }

    public PropTransferidasPendValidDtoHelper getPropTransPendValidDtoHelper() {
        return propTransPendValidDtoHelper;
    }

    public void setPropTransPendValidDtoHelper(PropTransferidasPendValidDtoHelper propTransPendValidDtoHelper) {
        this.propTransPendValidDtoHelper = propTransPendValidDtoHelper;
    }

    public PropTransferidasPendValidListHelper getPropTransPendValidLstHelper() {
        return propTransPendValidLstHelper;
    }

    public void setPropTransPendValidLstHelper(PropTransferidasPendValidListHelper propTransPendValidLstHelper) {
        this.propTransPendValidLstHelper = propTransPendValidLstHelper;
    }
}
