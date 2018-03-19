/*
 1,831 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.validarretro;

import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORESTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_NORTE_CENTRO;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_OCCIDENTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_PACIFICO_NORTE;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.ARACE_SUR;
import static mx.gob.sat.siat.feagace.modelo.util.Constantes.CAMPO_OBLIGATORIO_IMPUESTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.base.excepcion.ValidarRetroalimentarPropuestaException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.util.ConstantesPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarHelper;
import mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar.ValidarYRetroalimentarListHelper;
import mx.gob.sat.siat.feagace.vista.propuestas.common.ValidarRetrolaimentarFuncionesVistaAbstract;

/**
 *
 * @author emmanuel.estrada
 */
@ViewScoped
@ManagedBean(name = "validarRetroMB")
public class ValidarRetroManageBean extends ValidarRetrolaimentarFuncionesVistaAbstract {

    private static final long serialVersionUID = 4185201856798192360L;
    private static final String ERROR_DESCARGA_ARCHIVO = "error.descarga.archivo";
    private static final String MSG_RETRO_EXITO_RECHAZO = "msj.prpuestas.validar.retro.exito.rechazar";
    private static final String NO_SE_PUDO_ELIMINAR_EL_ARCHIVO = "No se pudo eliminar el archivo de rechazo de la propuesta [{0}] Archivo : ";
    private static final String ARCHIVO_REPETIDO = "file.upload.archivo.repetido";
    private static final String SIN_IMPUESTO = "Para poder declarar una propuesta sin impuestos, debe eliminar los impuestos que declar\u00f3 previamente";
    private static final String IMPUESTO_DUPLICADO = "No se puede adjuntar un impuesto duplicado, favor de verificar";
    private static final String CMB_IMPUESTO = "formValidaRetroalimentarPropuesta:cmbImpuesto";

    @PostConstruct
    public void init() {

        try {
            setValidarYRetroalimentarHelper(new ValidarYRetroalimentarHelper());
            setRetroalimentarLstHelper(new ValidarYRetroalimentarListHelper());
            setTipoFieldset("");
            inicializaPanels();

            if (getRetroalimentarLstHelper().getListaPropuestasPendientes() == null
                    || getRetroalimentarLstHelper().getListaPropuestasPendientes().isEmpty()) {
                getRetroalimentarLstHelper().setListaPropuestasPendientes(new ArrayList<FecetPropuesta>());
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }

    public void eliminarArchivoRechazo() {
        try {
            getRetroalimentarLstHelper().getListaDocumentoRechazo()
                    .remove(getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado());
            getValidarYRetroalimentarHelper().setFile(null);
            habiltarRechazo();
        } catch (Exception ex) {
            logger.error(NO_SE_PUDO_ELIMINAR_EL_ARCHIVO,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }
    }

    public void eliminarArchivoPendiente() {
        try {
            getRetroalimentarLstHelper().getListaDocumentoPendiente()
                    .remove(getValidarYRetroalimentarHelper().getArchivoPendienteSeleccionado());
            getValidarYRetroalimentarHelper().setFile(null);
            habiltarPendiente();
        } catch (Exception ex) {
            logger.error(NO_SE_PUDO_ELIMINAR_EL_ARCHIVO,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoPendienteSeleccionado().getNombreArchivo());
        }
    }

    public void eliminarArchivoRetroalimentacion() {
        try {
            getRetroalimentarLstHelper().getLstDocRetroalimentacion()
                    .remove(getValidarYRetroalimentarHelper().getDocRetroSeleccionado());
            getValidarYRetroalimentarHelper().setFile(null);
        } catch (Exception ex) {
            logger.error(NO_SE_PUDO_ELIMINAR_EL_ARCHIVO,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }

    }

    public void filePendienteUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            getValidarYRetroalimentarHelper().setFile(event.getFile());
            agregarArchivoPendienteALst(getValidarYRetroalimentarHelper().getFile());
        }
    }

    public void fileRechazoUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            getValidarYRetroalimentarHelper().setFile(event.getFile());
            agregarArchivoRechazoALst(getValidarYRetroalimentarHelper().getFile());
        }
    }

    public void fileDocRetroUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            getValidarYRetroalimentarHelper().setFile(event.getFile());
            agregarArchivoRetroALst(getValidarYRetroalimentarHelper().getFile());
        }
    }

    public void agregarArchivoRechazoALst(UploadedFile file) {
        try {
            if (!isArchivoExistenteEnLista(file.getFileName())) {
                FecetRechazoPropuesta docRechazo = new FecetRechazoPropuesta();
                docRechazo.setArchivo(file.getInputstream());
                docRechazo.setNombreArchivo(file.getFileName());
                docRechazo.setFechaRechazo(new Date());
                getRetroalimentarLstHelper().getListaDocumentoRechazo().add(docRechazo);
                habiltarRechazo();
            }
        } catch (IOException ex) {
            logger.error("No se pudo agregar el archivo de rechazo de la propuesta [{0}] Archivo : ",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }
    }

    public void agregarArchivoPendienteALst(UploadedFile file) {
        try {
            if (!isArchivoExistenteEnListaPend(file.getFileName())) {
                FecetPropPendiente docPendiente = new FecetPropPendiente();
                docPendiente.setArchivo(file.getInputstream());
                docPendiente.setNombreArchivo(file.getFileName());
                docPendiente.setFechaPendiente(new Date());
                getRetroalimentarLstHelper().getListaDocumentoPendiente().add(docPendiente);
                habiltarPendiente();
            }
        } catch (IOException ex) {
            logger.error("No se pudo agregar el archivo de pendiente de la propuesta [{0}] Archivo : ",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }
    }

    public void agregarArchivoRetroALst(UploadedFile file) {
        try {
            if (!isArchivoExistenteEnListaRetroDoc(file.getFileName())) {
                DocRetroalimentacionDTO docRetro = new DocRetroalimentacionDTO();

                docRetro.setIdPropuesta(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
                docRetro.setBlnEstatus(EstadoBooleanodeRegistroEnum.ACTIVO);
                docRetro.setArchivo(file.getInputstream());
                docRetro.setIdPropuesta(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
                docRetro.setNombreArchivo(CargaArchivoUtilPropuestas.limpiarPathArchivo(file.getFileName()));
                docRetro.setFechaCreacion(new Date());
                getRetroalimentarLstHelper().getLstDocRetroalimentacion().add(docRetro);
            }
        } catch (IOException ex) {
            logger.error("No se pudo agregar el archivo de rechazo de la propuesta [{0}] Archivo : ",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }
    }

    private boolean isArchivoExistenteEnListaPend(String nombreArchivo) {
        boolean flgExiste = false;
        for (FecetPropPendiente archivoCargado : getRetroalimentarLstHelper().getListaDocumentoPendiente()) {
            flgExiste = (nombreArchivo.equals(archivoCargado.getNombreArchivo()));
            if (flgExiste) {
                addMessage(getMessageResourceString(ARCHIVO_REPETIDO));
                break;
            }
        }
        return flgExiste;
    }

    private boolean isArchivoExistenteEnLista(String nombreArchivo) {
        boolean flgExiste = false;
        for (FecetRechazoPropuesta archivoCargado : getRetroalimentarLstHelper().getListaDocumentoRechazo()) {
            flgExiste = (nombreArchivo.equals(archivoCargado.getNombreArchivo()));
            if (flgExiste) {
                addMessage(getMessageResourceString(ARCHIVO_REPETIDO));
                break;
            }
        }
        return flgExiste;
    }

    private boolean isArchivoExistenteEnListaRetroDoc(String nombreArchivo) {
        boolean flgExiste = false;
        for (DocRetroalimentacionDTO archivoCargado : getRetroalimentarLstHelper().getLstDocRetroalimentacion()) {
            flgExiste = (nombreArchivo.equals(archivoCargado.getNombreArchivo()));
            if (flgExiste) {
                addMessage(getMessageResourceString(ARCHIVO_REPETIDO));
                break;
            }
        }
        return flgExiste;
    }

    public void obtenerArchivoRechazo() {
        getValidarYRetroalimentarHelper().setFileRechazoDescarga(new DefaultStreamedContent(
                getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getArchivo(), "application/pdf",
                getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo()));
    }

    public void habiltarRechazo() {
        setFlgHabilitarRechazo(!(getRetroalimentarLstHelper().getListaDocumentoRechazo() == null
                || getRetroalimentarLstHelper().getListaDocumentoRechazo().isEmpty()
                || (getValidarYRetroalimentarHelper().getPropuestaRechazo().getDescripcion().trim().isEmpty())));
    }

    public boolean getHabiltarRetroalimentacion() {

        boolean flgLstDocRetro = (getRetroalimentarLstHelper().getLstDocRetroalimentacion() != null)
                && (!getRetroalimentarLstHelper().getLstDocRetroalimentacion().isEmpty());

        boolean flgDescripcion = (getValidarYRetroalimentarHelper().getRetroalimentacion().getDescripcion() != null)
                && (!getValidarYRetroalimentarHelper().getRetroalimentacion().getDescripcion().trim().isEmpty());

        boolean flgMotivo = (getValidarYRetroalimentarHelper().getRetroalimentacion().getIdMotivo() == null);

        return isRetroValida(flgLstDocRetro, flgDescripcion, flgMotivo);
    }

    private boolean isRetroValida(boolean... segmentosFormRetro) {
        if (segmentosFormRetro != null && segmentosFormRetro.length > 0) {
            for (boolean segmeto : segmentosFormRetro) {
                if (!segmeto) {
                    return segmeto;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void habiltarPendiente() {
        setFlgHabilitarPendiente(!(getRetroalimentarLstHelper().getListaDocumentoPendiente() == null
                || getRetroalimentarLstHelper().getListaDocumentoPendiente().isEmpty()
                || getValidarYRetroalimentarHelper().getPropuestaPendiente().getObservaciones() == null
                || getValidarYRetroalimentarHelper().getPropuestaPendiente().getObservaciones().trim().isEmpty()));
    }

    public void aprobarPropuesta() {
        if (!getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada().isEmpty()) {
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().clear();
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().addAll(getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada());
        }
        for (FecetPropuesta propuesta : getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada()) {
            getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(propuesta);
            try {

                eliminarImpuestoSeleccionados();
                if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasXvalidar")) {
                    getValidarRetroalimentarPropuestaService()
                            .marcarAprobarPropuestaValida(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
                } else {
                    if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasPendiente")) {
                        getValidarRetroalimentarPropuestaService()
                                .marcarAprobarPropuestaPendiente(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
                    } else {
                        getValidarRetroalimentarPropuestaService().marcarPropuestaValida(propuesta);
                    }
                }
                logger.debug(getMessageResourceString("msj.prpuestas.validar.retro.exito.aprobar",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                addMessage(getMessageResourceString("msj.prpuestas.validar.retro.exito.aprobar",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                addErrorMessage("No se pudo aprobar la propuesta : "
                        + getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro());
            }

        }
        cerrarFieldeset();
        getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
        getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(null);
        setEsProcesable(false);
        inicializaPanels();
    }

    public void rechazarPropuesta() {

        try {
            BigDecimal idEmpleado = getValidarRetroalimentarPropuestaService()
                    .getEmpleadoProgramadorActivo(getRFCSession()).getIdEmpleado();
            Date fechaDeRechazo = new Date();
            for (FecetRechazoPropuesta rechazo : getRetroalimentarLstHelper().getListaDocumentoRechazo()) {
                rechazo.setIdEmpleado(idEmpleado);
                rechazo.setRfcRechazo(getRFCSession());
                rechazo.setFechaRechazo(fechaDeRechazo);
                rechazo.setFechaCreacion(fechaDeRechazo);
                rechazo.setDescripcion(getValidarYRetroalimentarHelper().getPropuestaRechazo().getDescripcion());
            }

            if (isFlgPropuestaPendiente()) {
                logger.debug("se rechazo pendiente");
                if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasXvalidar")) {
                    getValidarRetroalimentarPropuestaService().marcarPropuestaRechazada(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                            getRetroalimentarLstHelper().getListaDocumentoRechazo());

                }

                if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasPendiente")) {
                    getValidarRetroalimentarPropuestaService().marcarPropuestaPendienteRechazada(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                            getRetroalimentarLstHelper().getListaDocumentoRechazo());
                }
                resetPanelPropuestasPendientes();
                logger.debug(getMessageResourceString(MSG_RETRO_EXITO_RECHAZO,
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                addMessage(getMessageResourceString(MSG_RETRO_EXITO_RECHAZO,
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));

            } else {
                logger.debug("se rechazo");
                if (!getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada().isEmpty()) {
                    getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().clear();
                    getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().addAll(getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada());
                }

                for (FecetPropuesta propuesta : getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada()) {
                    getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(propuesta);

                    if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasXvalidar")) {
                        getValidarRetroalimentarPropuestaService().marcarPropuestaRechazada(
                                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                                getRetroalimentarLstHelper().getListaDocumentoRechazo());

                    }

                    if (getTipoFieldset() != null && getTipoFieldset().equals("propuestasPendiente")) {
                        getValidarRetroalimentarPropuestaService().marcarPropuestaPendienteRechazada(
                                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                                getRetroalimentarLstHelper().getListaDocumentoRechazo());
                    }

                    logger.debug(getMessageResourceString(MSG_RETRO_EXITO_RECHAZO,
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                    addMessage(getMessageResourceString(MSG_RETRO_EXITO_RECHAZO,
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                }
                cerrarFieldeset();
            }
            getRetroalimentarLstHelper().setListaDocumentoRechazo(new ArrayList<FecetRechazoPropuesta>());
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(null);

            setEsProcesable(false);
            inicializaPanels();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
            visiblePanelDetalle();
        } finally {
            setFlgHabilitarRechazo(false);
        }
    }

    public void retroalimentarPropuesta() {

        if (validaFormulario()) {
            try {
                EmpleadoDTO idEmpleado = getValidarRetroalimentarPropuestaService()
                        .getEmpleadoProgramadorActivo(getRFCSession());
                Date fechaDeRechazo = new Date();
                eliminarImpuestoSeleccionados();
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdPropuesta(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setFechaCreacion(fechaDeRechazo);
                getValidarYRetroalimentarHelper().getRetroalimentacion().setFechaRetroalimentacion(fechaDeRechazo);
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdEmpleado(idEmpleado.getIdEmpleado());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setRfcRetroalimentacion(idEmpleado.getRfc());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdArace(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getIdArace());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdSubprograma(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getIdSubprograma());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdMetodo(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getIdMetodo());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdRevision(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getIdRevision());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setIdTipoPropuesta(getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada()
                                .getIdTipoPropuesta());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setIdCausaProgramacion(getValidarYRetroalimentarHelper()
                                .getRetroalimentarHistoricoSeleccionada().getIdCausaProgramacion());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setIdSector(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getIdSector());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setFechaInicioPeriodo(getValidarYRetroalimentarHelper()
                                .getRetroalimentarHistoricoSeleccionada().getFechaInicioPeriodo());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setFechaFinPeriodo(getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada()
                                .getFechaFinPeriodo());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setFechaPresentacion(getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada()
                                .getFechaPresentacion());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setFechaInforme(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getFechaInforme());
                getValidarYRetroalimentarHelper().getRetroalimentacion()
                        .setPrioridadSugerida(getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada()
                                .getPrioridadSugerida());
                getValidarYRetroalimentarHelper().getRetroalimentacion().setLstImpuestos(
                        getValidarYRetroalimentarHelper().getRetroalimentarHistoricoSeleccionada().getLstImpuestos());

                getValidarRetroalimentarPropuestaService().marcarPropuestaRetroalimentada(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                        getValidarYRetroalimentarHelper().getRetroalimentacion(),
                        getRetroalimentarLstHelper().getLstDocRetroalimentacion());
                resetPanelPropuestasPorValidar();
                logger.debug(getMessageResourceString("msj.prpuestas.validar.retro.exito.retroalimentada",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                addMessage(getMessageResourceString("msj.prpuestas.validar.retro.exito.retroalimentada",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                inicializaPanels();
                generarRetroalimentacion();
                resetPanelRetroalimentar();
            } catch (ValidarRetroalimentarPropuestaException ex) {
                visiblePanelDetalle();
                logger.error(ex.getMessage(), ex);
                addErrorMessage(ex.getMessage());
            } catch (Exception ex) {
                visiblePanelDetalle();
                logger.error(ex.getMessage(), ex);
                addErrorMessage("No se pudo retroalimentar la propuesta : "
                        + getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro());
            }
        }
    }

    public void cancelarRechazoPropuesta() {
        getRetroalimentarLstHelper().setListaDocumentoRechazo(new ArrayList<FecetRechazoPropuesta>());
        getValidarYRetroalimentarHelper().setFile(null);
        getValidarYRetroalimentarHelper().setPropuestaRechazo(new FecetRechazoPropuesta());
        setFlgHabilitarRechazo(false);
    }

    public void cancelarRetroalimentacionPropuesta() {
        getRetroalimentarLstHelper().setLstDocRetroalimentacion(new ArrayList<DocRetroalimentacionDTO>());
        getValidarYRetroalimentarHelper().setFile(null);
        getValidarYRetroalimentarHelper().setRetroalimentacion(new FecetRetroalimentacion());
    }

    public void postergarPropuesta() {
        try {
            for (FecetPropuesta propuesta : getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada()) {
                getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(propuesta);

                if (propuesta != null) {
                    getValidarYRetroalimentarHelper().getPropuestaPendiente().setIdPropuesta(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
                    getValidarYRetroalimentarHelper().getPropuestaPendiente().setRfcCreacion(getRFCSession());
                }
                getValidarRetroalimentarPropuestaService().marcarPropuestaPendiente(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada(),
                        getRetroalimentarLstHelper().getListaDocumentoPendiente(),
                        getValidarYRetroalimentarHelper().getPropuestaPendiente());
                logger.debug(getMessageResourceString("msj.prpuestas.validar.retro.exito.pendiente",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
                addMessage(getMessageResourceString("msj.prpuestas.validar.retro.exito.pendiente",
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdRegistro()));
            }
            getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(null);
            getValidarYRetroalimentarHelper().setPropuestaPendiente(new FecetPropPendiente());
            getRetroalimentarLstHelper().setListaDocumentoPendiente(new ArrayList<FecetPropPendiente>());
            getRetroalimentarLstHelper().setListaPropuestaXValidarSeleccionada(new ArrayList<FecetPropuesta>());
            setEsProcesable(false);
            inicializaPanels();
            cerrarFieldeset();
        } catch (ValidarRetroalimentarPropuestaException ex) {
            visiblePanelDetalle();
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            visiblePanelDetalle();
            logger.error(ex.getMessage(), ex);
            addErrorMessage("No se pudo postergar la propuesta");
        }
        getValidarYRetroalimentarHelper().setPropuestasXValidarSeleccionada(null);

    }

    public void regresarPanelListas() {
        logger.debug("se regresa al panel origen");
        inicializaPanels();
        iniciaVisibilidadComboMetodo();
        setFlgDetalleXValidar(false);
    }

    public StreamedContent getDocumento() {
        logger.debug("Se descarga docto...");

        try {

            if (getValidarYRetroalimentarHelper().getArchivoDescargableIS() == null) {
                addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO,
                        getValidarYRetroalimentarHelper().getNombreArchivoDescarga()));
                return null;
            } else {
                return new DefaultStreamedContent(getValidarYRetroalimentarHelper().getArchivoDescargableIS(),
                        "application/octet-stream", getValidarYRetroalimentarHelper().getNombreArchivoDescarga());
            }

        } catch (Exception fne) {
            addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO,
                    getValidarYRetroalimentarHelper().getNombreArchivoDescarga()));
            logger.error(fne.getMessage(), fne);
            return null;
        }

    }

    public StreamedContent getDocumentoExpediente() {
        logger.debug("Se descarga docto...");

        try {

            if (getValidarYRetroalimentarHelper().getRutaArchivoDescarga() != null
                    && !getValidarYRetroalimentarHelper().getRutaArchivoDescarga().isEmpty()) {
                return new DefaultStreamedContent(
                        new FileInputStream(getValidarYRetroalimentarHelper().getRutaArchivoDescarga()),
                        "application/octet-stream", getValidarYRetroalimentarHelper().getNombreArchivoDescarga());
            } else {

                addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO,
                        getValidarYRetroalimentarHelper().getNombreArchivoDescarga()));
                return null;

            }

        } catch (Exception fne) {
            addErrorMessage(getMessageResourceString(ERROR_DESCARGA_ARCHIVO,
                    getValidarYRetroalimentarHelper().getNombreArchivoDescarga()));
            logger.error(fne.getMessage(), fne);
            return null;
        }

    }

    public void agregarImpuesto() {
        if (validarImpuesto()) {
            FecetImpuesto impuesto = getValidarYRetroalimentarHelper().getImpuestoPropuesta();
            impuesto.setFececConcepto(new FececConcepto());
            if (impuesto.getIdTipoImpuesto() != null && impuesto.getIdTipoImpuesto().compareTo(BigDecimal.ZERO) > 0
                    && impuesto.getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                impuesto.setMonto(new BigDecimal("0.0"));
                impuesto.getFececConcepto().setDescripcion(buscarDescripcionConcepto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto()));
                impuesto.getFececConcepto().setIdConcepto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto().intValue());
                impuesto.setFececTipoImpuesto(buscarDescripcionImpuesto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()));

            } else {
                getRetroalimentarLstHelper().setListaConcepto(
                        getValidarRetroalimentarPropuestaService().getConceptoByImpuesto(impuesto.getIdTipoImpuesto()));
                impuesto.getFececConcepto().setDescripcion(buscarDescripcionConcepto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto()));
                impuesto.getFececConcepto().setIdConcepto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto().intValue());
                impuesto.setFececTipoImpuesto(buscarDescripcionImpuesto(
                        getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()));
            }
            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getLstImpuestos().add(impuesto);
            getValidarYRetroalimentarHelper().setImpuestoPropuesta(new FecetImpuesto());
        }
    }

    /**
     * Metodo que permite capturar un impuesto en cero
     *
     */
    public void agregarImpuestoCero() {
        FecetImpuesto impuesto = getValidarYRetroalimentarHelper().getImpuestoPropuesta();
        if (impuesto.getIdTipoImpuesto() != null && impuesto.getIdTipoImpuesto().compareTo(BigDecimal.ZERO) > 0
                && impuesto.getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
            impuesto.setMonto(new BigDecimal("0.0"));
            getValidarYRetroalimentarHelper().setImpuestoPropuesta(impuesto);
            getRetroalimentarLstHelper().setListaConcepto(
                    getValidarRetroalimentarPropuestaService().getConceptoByImpuesto(impuesto.getIdTipoImpuesto()));
        } else {
            getRetroalimentarLstHelper().setListaConcepto(
                    getValidarRetroalimentarPropuestaService().getConceptoByImpuesto(impuesto.getIdTipoImpuesto()));
            impuesto.setMonto(null);
            getValidarYRetroalimentarHelper().setImpuestoPropuesta(impuesto);

        }
    }

    public void eliminarImpuestoSeleccionados() {
        for (FecetImpuesto impuestoBorrado : getRetroalimentarLstHelper().getListaImpuesto()) {
            getValidarRetroalimentarPropuestaService().descartarImpuesto(impuestoBorrado);
        }
    }

    public void descartarImpuesto() {
        try {
            for (FecetImpuesto impuestoBorrado : getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                    .getLstImpuestos()) {
                if (getValidarYRetroalimentarHelper().getImpuestoSeleccionado().equals(impuestoBorrado)) {
                    getRetroalimentarLstHelper().getListaImpuesto().add(impuestoBorrado);
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getLstImpuestos().remove(impuestoBorrado);
                    break;
                }
            }
            addMessage(null, "Se elimin\u00f3 el impuesto correctamente.", "");
        } catch (Exception e) {
            addErrorMessage(null, getMessageResourceString("mensaje.problema"),
                    "No se pudo descartar el impuesto");
            logger.error("No se pudo cargar la información de propuestas por validar", e);
        }
    }

    public void fileExpedienteUpload(FileUploadEvent event) {
        if (event.getFile() != null) {
            getValidarYRetroalimentarHelper().setFile(event.getFile());
            agregarArchivoExpedienteList(getValidarYRetroalimentarHelper().getFile());
        }
    }

    public void agregarArchivoExpedienteList(UploadedFile file) {
        try {
            if (!isArchivoExistenteEnListaExp(file.getFileName())) {
                FecetDocExpediente docExpediente = new FecetDocExpediente();
                docExpediente.setArchivo(file.getInputstream());
                docExpediente.setIdPropuesta(
                        getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta());
                docExpediente.setNombre(file.getFileName());
                docExpediente.setFechaCreacion(new Date());
                getRetroalimentarLstHelper().getListaDocumentoExpediente().add(docExpediente);
                habiltarRechazo();
            }
        } catch (IOException ex) {
            logger.error("No se pudo agregar el archivo de rechazo de la propuesta [{0}] Archivo : ",
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoRechazoSeleccionado().getNombreArchivo());
        }
    }

    private boolean isArchivoExistenteEnListaExp(String nombreArchivo) {
        boolean flgExiste = false;
        for (FecetDocExpediente archivoCargado : getRetroalimentarLstHelper().getListaDocumentoExpediente()) {
            flgExiste = (nombreArchivo.equals(archivoCargado.getNombre()));
            if (flgExiste) {
                addMessage(getMessageResourceString(ARCHIVO_REPETIDO));
                break;
            }
        }
        return flgExiste;
    }

    public void eliminarArchivoExp() {
        try {
            getRetroalimentarLstHelper().getListaDocumentoExpediente()
                    .remove(getValidarYRetroalimentarHelper().getArchivoSeleccionado());
            getValidarYRetroalimentarHelper().setFile(null);
            habiltarRechazo();
        } catch (Exception ex) {
            logger.error(NO_SE_PUDO_ELIMINAR_EL_ARCHIVO,
                    getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta(),
                    getValidarYRetroalimentarHelper().getArchivoSeleccionado().getNombre());
        }
    }

    public BigDecimal getTotalMonto() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getLstImpuestos() != null) {
            for (FecetImpuesto imp : getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                    .getLstImpuestos()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public FececTipoImpuesto buscarDescripcionImpuesto(final BigDecimal idTipoImpuesto) {
        FececTipoImpuesto tipoImpuestoTemp = new FececTipoImpuesto();
        for (FececTipoImpuesto tipoImpuesto : getRetroalimentarLstHelper().getListaTipoImpuesto()) {
            if (tipoImpuesto.getIdTipoImpuesto().equals(idTipoImpuesto)) {
                tipoImpuestoTemp = tipoImpuesto;
                break;
            }
        }
        return tipoImpuestoTemp;
    }

    public boolean validarImpuesto() {
        boolean datosValidos = true;

        for (FecetImpuesto impuestos : getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada()
                .getLstImpuestos()) {
            if (impuestos.getIdTipoImpuesto()
                    .equals(getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto())
                    && impuestos.getIdConcepto().equals(getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto())) {
                addErrorMessage(CMB_IMPUESTO, IMPUESTO_DUPLICADO);
                datosValidos = false;
            }

            if (!getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)
                    && impuestos.getIdTipoImpuesto().equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                addErrorMessage(CMB_IMPUESTO, SIN_IMPUESTO);
                datosValidos = false;
            }

        }

        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getLstImpuestos().size() > 0
                && getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()
                .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
            addErrorMessage(CMB_IMPUESTO, SIN_IMPUESTO);
            datosValidos = false;
        }

        if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto() == null) {
            addErrorMessage(CMB_IMPUESTO, CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()
                    .compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage(CMB_IMPUESTO, CAMPO_OBLIGATORIO_IMPUESTO);
                datosValidos = false;
            }
        }

        if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto() == null) {
            addErrorMessage("formValidaRetroalimentarPropuesta:cmbConcepto", CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdConcepto()
                    .compareTo(BigDecimal.ZERO) <= 0) {
                addErrorMessage("formValidaRetroalimentarPropuesta:cmbConcepto", CAMPO_OBLIGATORIO_IMPUESTO);
                datosValidos = false;
            }
        }

        if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getMonto() == null) {
            addErrorMessage("formValidaRetroalimentarPropuesta:txtMonto", CAMPO_OBLIGATORIO_IMPUESTO);
            datosValidos = false;
        } else {
            if (getValidarYRetroalimentarHelper().getImpuestoPropuesta().getMonto().compareTo(BigDecimal.ZERO) <= 0
                    && !getValidarYRetroalimentarHelper().getImpuestoPropuesta().getIdTipoImpuesto()
                    .equals(ConstantesPropuestas.TIPO_IMPUESTO_NO_APLICA)) {
                addErrorMessage("formValidaRetroalimentarPropuesta:txtMonto", "Debe ser mayor a 0");
                datosValidos = false;
            }
        }

        return datosValidos;
    }

    public void mostrarHistorico() {
        try {
            logger.debug("Se muestra el historico");
            visiblePanelHistorico();
            getRetroalimentarLstHelper().setListaHistoricoRechazo(
                    getValidarRetroalimentarPropuestaService().getLstHistoricoRechazoPropuesta(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta()));
            getPistasAuditoriasPropuestasService().pistaHistoricoRechazoPropuesta(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada());
            getRetroalimentarLstHelper().setListaHistoricoRetroalimentar(
                    getValidarRetroalimentarPropuestaService().getLstHistorialRetroalimentacionPropuesta(
                            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdPropuesta()));

            setRetroOrigen(true);

            if (getRetroalimentarLstHelper().getListaHistoricoRetroalimentar().size() == 0) {
                getRetroalimentarLstHelper().setListaHistoricoRetroalimentar(getValidarRetroalimentarPropuestaService()
                        .getLstHistoricoRetroalimentacionPropuesta(getValidarYRetroalimentarHelper()
                                .getPropuestasXValidarSeleccionada().getIdPropuesta()));

                setRetroOrigen(false);

            }

        } catch (Exception ex) {
            addErrorMessage("No se puede mostrar Historico");
        }
    }

    public boolean isVisibleListasHistoricos() {
        return !(isFlgDetalleHistoricoRechazo() || isFlgDetalleHistoricoRetro() || isVisibleListasHistoricosOrigen()
                || isFlgImpuestoHistorico());
    }

    public void llamaDetalleRechazoHistorico() {
        logger.debug("Se llama llamaDetalleRechazoHistorico");

        try {
            setFlgDetalleHistoricoRechazo(true);
            setFlgDetalleHistoricoRetro(false);
            setVisibleListasHistoricosOrigen(false);

            getRetroalimentarLstHelper().setLstDocHistoricoRechazo(
                    getValidarRetroalimentarPropuestaService().getDetalleDocRechazoByIdRechazo(
                            getValidarYRetroalimentarHelper().getHistoricoRechazo().getIdRechazoPropuesta()));
        } catch (Exception ex) {
            addErrorMessage("No se puede cargar los documentos de rechazo");
        }

    }

    public BigDecimal getTotalMontoRetro() {
        BigDecimal totalImpuesto = BigDecimal.ZERO;
        if (getRetroalimentarLstHelper().getListaImpuesto() != null) {
            for (FecetImpuesto imp : getRetroalimentarLstHelper().getListaImpuesto()) {
                totalImpuesto = totalImpuesto.add(imp.getMonto());
            }
        }
        return totalImpuesto;
    }

    public void llamaImpuestoHistorico() {
        logger.debug("Se llama llamaImpuestoHistorico");

        try {
            setFlgDetalleHistoricoRechazo(false);
            setFlgDetalleHistoricoRetro(false);
            setVisibleListasHistoricosOrigen(false);
            setFlgImpuestoHistorico(true);

            getRetroalimentarLstHelper()
                    .setListaImpuesto(getValidarRetroalimentarPropuestaService().getImpuestosRetroPropuesta(
                                    getValidarYRetroalimentarHelper().getHistoricoRetroalimentar().getIdRetroalimentacion()));

        } catch (Exception ex) {
            addErrorMessage("No se puede cargar los documentos de rechazo");
        }

    }

    public void llamaDetalleRetroaliemntarHistorico() {
        logger.debug("Se llama llamaDetalleRetroaliemntarHistorico");
        try {
            setFlgDetalleHistoricoRechazo(false);
            setFlgDetalleHistoricoRetro(true);
            setVisibleListasHistoricosOrigen(false);

            getRetroalimentarLstHelper().setLstDocHistoricoRetroalimentar(
                    getValidarRetroalimentarPropuestaService().getDetalleDocRetroalimentacionByIdRetro(
                            getValidarYRetroalimentarHelper().getHistoricoRetroalimentar().getIdRetroalimentacion()));
        } catch (Exception ex) {
            addErrorMessage("No se puede cargar los documentos de retroalimentacion");
        }
    }

    public void llamaDetalleRetroaliemntarSolicitud() {
        logger.debug("Se llama llamaDetalleRetroaliemntarSolicitud");
        try {
            setFlgDetalleHistoricoRechazo(false);
            setFlgDetalleHistoricoRetro(false);
            setVisibleListasHistoricosOrigen(true);
            getRetroalimentarLstHelper().setListaHistoricoRetroalimentar(getValidarRetroalimentarPropuestaService()
                    .getLstOrigenRetroalimentacionPropuesta(getValidarYRetroalimentarHelper()
                            .getHistoricoRetroalimentar().getIdRetroalimentacionOrigen()));

        } catch (Exception ex) {
            addErrorMessage("No se puede cargar los documentos de retroalimentacion");
        }
    }

    public boolean validarAraceParte1() {
        boolean araceParte1 = false;
        if (getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_PACIFICO_NORTE)
                || getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_NORTE_CENTRO)
                || getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_NORESTE)) {
            araceParte1 = true;
        }
        return araceParte1;
    }

    public boolean validarAraceParte2() {
        boolean araceParte2 = false;
        if (getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_OCCIDENTE)
                || getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_CENTRO)
                || getValidarYRetroalimentarHelper().getAreaOrigen().equals(ARACE_SUR)) {
            araceParte2 = true;
        }
        return araceParte2;
    }

    public boolean validarAraceParte3() {
        boolean araceParte3 = false;
        if (validarAraceParte1()) {
            araceParte3 = true;
        }
        if (validarAraceParte2()) {
            araceParte3 = true;
        }
        return araceParte3;
    }

    public String buscarDescripcionConcepto(final BigDecimal idConcepto) {
        String descripcionConcepto = "";
        for (FececConcepto impuesto : getRetroalimentarLstHelper().getListaConcepto()) {
            if (impuesto.getIdConcepto() == idConcepto.intValue()) {
                descripcionConcepto = impuesto.getDescripcion();
                break;
            }
        }
        return descripcionConcepto;
    }

    public void actualizarTipoRevision(AjaxBehaviorEvent event) {
        TipoMetodoEnum metodo = null;
        if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo() != null) {
            metodo = TipoMetodoEnum.getById(getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getIdMetodo().longValue());
        }

        if (metodo != null && TipoMetodoEnum.ORG.equals(metodo)) {
            setFlgTipoRevision(false);
            if (getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().getPrioridadSugerida().equals("2")) {
                setFlgTipoRevision(true);
                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().setIdRevision(null);
                getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().setIdMetodo(null);
                addErrorMessage("formValidaRetroalimentarPropuesta:cmbMetodo", "No se puede seleccionar este m\u00e9todo \n con prioridad 2");
            }
        } else {
            setFlgTipoRevision(true);
            getValidarYRetroalimentarHelper().getPropuestasXValidarSeleccionada().setIdRevision(null);
        }
    }

    public void actualizarBotonesValidar(SelectEvent event) {
        FecetPropuesta propuesta = (FecetPropuesta) event.getObject();
        if (getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada().contains(propuesta)) {
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().add(propuesta);
        }

        try {
            if (propuesta != null) {
                setEsProcesable(getValidarRetroalimentarPropuestaService().esProcesableFecha(propuesta).intValue() > 0);
            }
        } catch (AgacePropuestasRulesException e) {
            addErrorMessage(getMessageResourceString(ERROR_FECHA_PRESENTACION,
                    propuesta.getIdRegistro(),
                    DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                            propuesta.getFechaPresentacion())));
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().remove(propuesta);
        }
        habilitarBotones();
    }

    public void actualizarBotonesSinSeleccionValidar(UnselectEvent event) {
        FecetPropuesta propuesta = (FecetPropuesta) event.getObject();
        getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().remove(propuesta);

        habilitarBotones();
    }

    private void habilitarBotones() {
        if (!getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().isEmpty()) {
            setEsProcesable(true);
        } else {
            setEsProcesable(false);
        }
    }

    public void headerToggleSelectValidar(ToggleSelectEvent event) {
        if (!getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada().isEmpty()) {
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().clear();
            getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().addAll(getRetroalimentarLstHelper().getListaPropuestaPendienteSeleccionada());
        }

        for (int i = 0; i < getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().size(); i++) {
            try {
                if (getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().get(i) != null) {
                    setEsProcesable(getValidarRetroalimentarPropuestaService().esProcesableFecha(getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().get(i)).intValue() > 0);
                }
            } catch (AgacePropuestasRulesException e) {
                addErrorMessage(getMessageResourceString(ERROR_FECHA_PRESENTACION,
                        getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().get(i).getIdRegistro(),
                        DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_DIAGONALES_DDMMYYYY,
                                getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().get(i).getFechaPresentacion())));
                getRetroalimentarLstHelper().getListaPropuestaXValidarSeleccionada().remove(i);
                i--;
            }
        }

        habilitarBotones();
    }

}
