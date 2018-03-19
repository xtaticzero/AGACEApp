/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.SerializationUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsociadoFuncionarioDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilPropuestas;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilPropuestas;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@SessionScoped
@ManagedBean(name = "consultarPropuestasAsignadasManagedBean")
public class ConsultarPropuestasAsignadasManagedBean extends ConsultarPropuestasAsignadasManagedBeanAbstract {

    private static final long serialVersionUID = -6740059723949538400L;
    private static final String ARCHIVO_YA_CARGADO = "El archivo ya fue cargado";
    private static final String LISTA_OFICIOS_SESION = "listaOficios";
    private static final String ERROR_AL_GUARDAR = "Error al guardar: ";
    private static final String DETALLE_PROP_ASIGNADAS_REDIRECT = "/propuestas/auditor/detallePropAsignadas.jsf?faces-redirect=true";
    private static final String DIALOGO_UPDATE_ORDEN = "PF('confirmaOperacionUpdateDocOrden').show();";

    public void cargaDatos() {
        Object vieneDePropOrd = getSession().getAttribute("vieneDeConsultaPropuestasOrdenes");
        if (vieneDePropOrd != null) {
            getCpaBooleanHelper().setVieneConsultaPropOrd((Boolean) vieneDePropOrd);
            getCpaBooleanHelper().setMuestraBotonesAccion(true);
            FecetPropuesta propuesta = (FecetPropuesta) getSession().getAttribute("propuestaSelAnalizar");
            FecetContribuyente contribuyente = (FecetContribuyente) getSession().getAttribute("fecetContribuyente");
            getCpaDtoHelper().setPropuestaSelAnalizar(propuesta);
            getCpaDtoHelper().getPropuestaSelAnalizar().setFecetContribuyente(contribuyente);
            cargaAraces();
            getConsultarPropAsignadasMBHelper().completaPropuesta((List<AraceDTO>) getSession().getAttribute(LISTA_ARACES), propuesta);
            enviaDetalleAnalizar();
            getSession().removeAttribute("vieneDeConsultaPropuestasOrdenes");
        }
        setEmpleadoLogueado(getConsultarPropuestasAsignadasService().obtenerEmpleadoFirmado(getRFCSession()));
        if (getEmpleadoLogueado() != null) {
            if (getEmpleadoLogueado().getDetalleEmpleado().get(0).getTipoEmpleado().equals(TipoEmpleadoEnum.AUDITOR)) {
                getCpaBooleanHelper().setVieneConsultaPropOrd(false);
                listaUnidadAdministrativaRegional();
                obtenerListaFechasComiteSegunUsuario();
                cargaAraces();
                cargaDatosPropuestas(getCpaParamHelper());
            }
            if (!getEmpleadoLogueado().getDetalleEmpleado().get(0).getTipoEmpleado().equals(TipoEmpleadoEnum.AUDITOR)
                    && !getCpaBooleanHelper().isVieneConsultaPropOrd()) {
                getConsultarPropAsignadasMBHelper().informeErrorSession(new NegocioException(ERROR_LOGGEO));
            }
        } else {
            getConsultarPropAsignadasMBHelper().informeErrorSession(new NegocioException(ERROR_LOGGEO));
        }
    }

    public void enviaDetalleAnalizar() {
        getCpaDtoHelper().setSumaPresuntiva(BigDecimal.ZERO);
        listadoPropuestas();
        detallePropuesta();

        if (getCpaDtoHelper().getContribuyenteIdc().isExistenCambios()) {
            getCpaBooleanHelper().setMuestraContribuyenteAnterior(true);
            getCpaBooleanHelper().setMuestraPropuestas(false);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('contribuyenteActualizado').show();");
        } else {
            getCpaBooleanHelper().setMuestraDetalleContribuyente(true);
            getCpaBooleanHelper().setMuestraDetalle1(true);
            getCpaBooleanHelper().setMuestraPrevios1(true);
            getCpaBooleanHelper().setMuestraPropuestas(false);
        }
    }

    public void muestraDetallePropuesta() {
        getCpaBooleanHelper().setMuestraDetalleContribuyente(true);
        getCpaBooleanHelper().setMuestraDetalle1(true);
        getCpaBooleanHelper().setMuestraPrevios1(true);
        getCpaBooleanHelper().setMuestraContribuyenteAnterior(false);
    }

    public void llamaAnalizarRechazoPropuesta() {
        getCpaStringHelper().setLabelRegistro(getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro());
        getCpaBooleanHelper().setMuestraFormularioRechazo(true);
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
    }

    public void llamaAnalizarTransferirPropuesta() {
        getCpaStringHelper().setLabelRegistro(getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro());
        getConsultarPropAsignadaHelper().setAraces((List<AraceDTO>) getSession().getAttribute(LISTA_ARACES));
        getConsultarPropAsignadaHelper().setAraces(getConsultarPropAsignadasMBHelper().unidadesValidasTransferencia(getConsultarPropAsignadaHelper().getAraces(), getCpaDtoHelper().getPropuestaSelAnalizar(), getEmpleadoLogueado()));
        getCpaBooleanHelper().setMuestraFormularioTransferir(true);
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
    }

    public void llamaAnalizarRetroalimentarPropuesta() {
        getCpaStringHelper().setLabelRegistro(getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro());
        getCpaBooleanHelper().setMuestraFormularioRetroalimentar(true);
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
    }

    public void llamaAnalizarCancelacionPropuesta() {
        getCpaStringHelper().setLabelRegistro(getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro());
        getCpaBooleanHelper().setMuestraFormularioCancelar(true);
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
    }

    public String generarRevision() {

        String paginaRetorno = null;
        if (getValidaAccionesHelper().validaActualizarOrden(getCpaDtoHelper().getPropuestaSelAnalizar(), getConsultarPropuestasAsignadasService())) {
            if (getConsultarPropAsignadaHelper().getOficiosActualizados().size() > 0 || getConsultarPropAsignadaHelper().getDocOrdenActualizado().size() > 0) {
                int idEstatus = Constantes.ESTATUS_PROPUESTA_ARCH_ADJ;
                BigDecimal estatusEntrante = getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus();
                getCpaDtoHelper().getPropuestaSelAnalizar().setIdEstatus(new BigDecimal(String.valueOf(idEstatus)));
                try {
                    List<FecetOficio> docOficios = (List<FecetOficio>) getSession().getAttribute(LISTA_OFICIOS_SESION);
                    if (getCpaBooleanHelper().isOrdenActualizable() && getCpaBooleanHelper().isOficioActaulizable()) {
                        getConsultarPropuestasAsignadasService().cambiarEstatusPropuestaRechazar(getCpaDtoHelper().getPropuestaSelAnalizar(), "1", docOficios,
                                estatusEntrante, getEmpleadoLogueado());
                        terminarRevision();
                        addMessage(null, "El Registro ha sido enviado al Firmante", "");
                        paginaRetorno = DETALLE_PROP_ASIGNADAS_REDIRECT;
                    } else if (getCpaBooleanHelper().isOrdenActualizable() && !getCpaBooleanHelper().isOficioActaulizable()) {
                        getConsultarPropuestasAsignadasService().cambiarEstatusPropuestaRechazar(getCpaDtoHelper().getPropuestaSelAnalizar(), "2", docOficios,
                                estatusEntrante, getEmpleadoLogueado());
                        terminarRevision();
                        addMessage(null, "El Registro ha sido enviado al Firmante", "");
                        paginaRetorno = DETALLE_PROP_ASIGNADAS_REDIRECT;
                    } else if (!getCpaBooleanHelper().isOrdenActualizable() && getCpaBooleanHelper().isOficioActaulizable()) {
                        getConsultarPropuestasAsignadasService().cambiarEstatusPropuestaRechazar(getCpaDtoHelper().getPropuestaSelAnalizar(), "3", docOficios,
                                estatusEntrante, getEmpleadoLogueado());
                        terminarRevision();
                        addMessage(null, "El Registro ha sido enviado al Firmante", "");
                        paginaRetorno = DETALLE_PROP_ASIGNADAS_REDIRECT;
                    } else {
                        addErrorMessage(null, "No se pudo generar la revisi\u00f3n, no existe documentaci\u00f3n actualizada", "");
                    }
                    getSession().setAttribute(LISTA_OFICIOS_SESION, null);
                    getConsultarPropAsignadaHelper().getOficiosActualizados().clear();
                    getConsultarPropAsignadaHelper().getDocOrdenActualizado().clear();
                    getCpaBooleanHelper().setSeActualizoDocumentos(false);
                } catch (NegocioException e) {
                    getSession().setAttribute(LISTA_OFICIOS_SESION, null);
                    addErrorMessage(null, "No se pudo generar la revisi\u00f3n", "");
                }
            } else {
                addErrorMessage(null, "No se pudo generar la revisi\u00f3n, no existe documentaci\u00f3n actualizada", "");
            }
        } else {
            getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
            getCpaBooleanHelper().setMuestraDetalle1(false);
            getCpaBooleanHelper().setMuestraPrevios1(false);
            getCpaBooleanHelper().setMuestraPropuestas(true);
            paginaRetorno = "/propuestas/docElectronico/adjuntarDocumento.jsf?faces-redirect=true";
        }
        return paginaRetorno;
    }

    private void terminarRevision() {
        getCpaBooleanHelper().setMuestraPropuestas(true);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
        getCpaBooleanHelper().setMuestraDetalle1(false);
        getCpaBooleanHelper().setMuestraPrevios1(false);
    }

    public void llamaActualizarOrden() {
        getConsultarPropAsignadaHelper().setDocumentoOrden(
                getConsultarPropuestasAsignadasService().getDocumentosOrdenByIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
        getCpaDtoHelper().setNumeroDocumentosOrden(getConsultarPropAsignadaHelper().getDocumentoOrden().size());
        getCpaBooleanHelper().setOrdenActualizable(getValidaAccionesHelper().isOrdenActualizable(getConsultarPropAsignadaHelper().getDocumentoOrden().get(0).getBlnActivo()));
        getCpaBooleanHelper().setPermiteOficios(getValidaAccionesHelper().permiteCargarOficios(getCpaDtoHelper().getPropuestaSelAnalizar().getIdMetodo(),
                getConsultarPropuestasAsignadasService()));
        if (getCpaBooleanHelper().isPermiteOficios()) {
            getConsultarPropAsignadaHelper().setDocOficios(getConsultarPropuestasAsignadasService().getOficiosByIdOrden(getConsultarPropAsignadaHelper().getDocumentoOrden().get(0).getIdOrden()));
            getConsultarPropAsignadaHelper().setOficios(getConsultarPropuestasAsignadasService().getOficiosAdministrables(getConsultarPropAsignadaHelper().getDocOficios()));
            if (getConsultarPropAsignadaHelper().getOficios() != null && !getConsultarPropAsignadaHelper().getOficios().isEmpty()
                    && getConsultarPropAsignadaHelper().getDocOficios().size() > 0) {
                getCpaDtoHelper().setNumeroOficiosActaulizables(getConsultarPropAsignadaHelper().getOficios().size());
                getCpaBooleanHelper().setOficioActaulizable(true);
            } else {
                getCpaBooleanHelper().setOficioActaulizable(false);
            }
        }
        getCpaBooleanHelper().setMuestraAgenteAduanal(getCpaDtoHelper().getPropuestaSelAnalizar().getIdMetodo().longValue() == TipoMetodoEnum.EHO.getId());
        if (getCpaBooleanHelper().isMuestraAgenteAduanal()) {
            getCpaDtoHelper().setAgenteAduanalVO(
                    getConsultarPropAsignadasMBHelper().construyeAgenteAduanalVO(
                            getConsultarPropuestasAsignadasService().traeAgenteAduanal(getConsultarPropAsignadaHelper().getDocumentoOrden().get(0).getIdOrden())));
        }
        copiarDocumentos();
        getCpaBooleanHelper().setMostrarInfoComplementaria(true);
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
    }

    public void handleFileUploadRechazo(FileUploadEvent event) {

        if (getValidaAccionesHelper().validaArchivoCarga(event.getFile())) {
            try {
                getCpaStringHelper().setArchivoRechazo(event.getFile());
                FecetRechazoPropuesta dto = new FecetRechazoPropuesta();
                final Date fecha = new Date();
                dto.setFechaRechazo(fecha);
                dto.setNombreArchivo(
                        CargaArchivoUtilPropuestas.limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoRechazo().getFileName())));
                if (!getValidaAccionesHelper().validaNombreArchivoRechazo(getConsultarPropAsignadaHelper().getListaRechazo(), dto.getNombreArchivo(),
                        getConsultarPropAsignadaHelper().getListaDocumentosTablaRechazo())) {
                    dto.setArchivo(getCpaStringHelper().getArchivoRechazo().getInputstream());
                    getConsultarPropAsignadaHelper().getListaRechazo().add(dto);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, ARCHIVO_YA_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void descartarDocRechazo() {

        List<FecetRechazoPropuesta> descartarRechazo = new ArrayList<FecetRechazoPropuesta>();
        for (FecetRechazoPropuesta documento : getConsultarPropAsignadaHelper().getListaRechazo()) {
            if (getCpaDtoHelper().getDocRechazoSeleccionado().equals(documento)) {
                descartarRechazo.add(documento);
                break;
            }
        }
        getConsultarPropAsignadaHelper().getListaRechazo().removeAll(descartarRechazo);
    }

    public void handleFileUploadTransferir(FileUploadEvent event) {

        if (getValidaAccionesHelper().validaArchivoCarga(event.getFile())) {
            try {
                getCpaStringHelper().setArchivoTransferir(event.getFile());
                FecetTransferencia transferencia = new FecetTransferencia();
                final Date fecha = new Date();
                transferencia.setFechaTraspaso(fecha);
                transferencia.setNombreArchivo(CargaArchivoUtilPropuestas
                        .limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoTransferir().getFileName())));
                if (!getValidaAccionesHelper().validaNombreArchivoTransferir(getConsultarPropAsignadaHelper().getListaTransferir(),
                        transferencia.getNombreArchivo(), getConsultarPropAsignadaHelper().getListaDocTransferir())) {
                    transferencia.setArchivo(getCpaStringHelper().getArchivoTransferir().getInputstream());
                    getConsultarPropAsignadaHelper().getListaTransferir().add(transferencia);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, ARCHIVO_YA_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void descartarDocTransferir() {

        List<FecetTransferencia> descartarTransferir = new ArrayList<FecetTransferencia>();
        for (FecetTransferencia documento : getConsultarPropAsignadaHelper().getListaTransferir()) {
            if (getCpaDtoHelper().getDocTransferenciaSeleccionado().equals(documento)) {
                descartarTransferir.add(documento);
                break;
            }
        }
        getConsultarPropAsignadaHelper().getListaTransferir().removeAll(descartarTransferir);
    }

    public void handleFileUploadRetroalimentar(FileUploadEvent event) {

        if (getValidaAccionesHelper().validaArchivoCarga(event.getFile())) {
            try {
                getCpaStringHelper().setArchivoRetroalimentar(event.getFile());
                FecetRetroalimentacion retroalimentar = new FecetRetroalimentacion();
                final Date fecha = new Date();
                retroalimentar.setFechaRetroalimentacion(fecha);
                retroalimentar.setNombreArchivo(CargaArchivoUtilPropuestas
                        .limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoRetroalimentar().getFileName())));
                if (!getValidaAccionesHelper().validaNombreArchivoRetroalimentar(getConsultarPropAsignadaHelper().getListaRetroalimentar(),
                        retroalimentar.getNombreArchivo(), getConsultarPropAsignadaHelper().getListaDocRetroalimentar())) {
                    retroalimentar.setArchivo(getCpaStringHelper().getArchivoRetroalimentar().getInputstream());
                    getConsultarPropAsignadaHelper().getListaRetroalimentar().add(retroalimentar);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, ARCHIVO_YA_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void descartarDocRetroaliemntar() {

        List<FecetRetroalimentacion> descartarRetroalimentar = new ArrayList<FecetRetroalimentacion>();
        for (FecetRetroalimentacion documento : getConsultarPropAsignadaHelper().getListaRetroalimentar()) {
            if (getCpaDtoHelper().getDocRetroalimentarSeleccionado().equals(documento)) {
                descartarRetroalimentar.add(documento);
                break;
            }
        }
        getConsultarPropAsignadaHelper().getListaRetroalimentar().removeAll(descartarRetroalimentar);
    }

    public void handleFileUploadCancelar(FileUploadEvent event) {

        if (getValidaAccionesHelper().validaArchivoCarga(event.getFile())) {
            try {
                getCpaStringHelper().setArchivoCancelar(event.getFile());
                FecetPropCancelada cancelar = new FecetPropCancelada();
                cancelar.setFechaCancelacion(new Date());
                cancelar.setNombreArchivo(CargaArchivoUtilPropuestas
                        .limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoCancelar().getFileName())));
                if (!getValidaAccionesHelper().validaNombreArchivoCancelar(getConsultarPropAsignadaHelper().getListaCancelar(), cancelar.getNombreArchivo(),
                        getConsultarPropAsignadaHelper().getListaDocCancelacion())) {
                    cancelar.setArchivo(getCpaStringHelper().getArchivoCancelar().getInputstream());
                    getConsultarPropAsignadaHelper().getListaCancelar().add(cancelar);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, ARCHIVO_YA_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void descartarDocCancelacion() {

        List<FecetPropCancelada> descartarCancelar = new ArrayList<FecetPropCancelada>();
        for (FecetPropCancelada documento : getConsultarPropAsignadaHelper().getListaCancelar()) {
            if (getCpaDtoHelper().getDocCancelacionSeleccionado().equals(documento)) {
                descartarCancelar.add(documento);
                break;
            }
        }
        getConsultarPropAsignadaHelper().getListaCancelar().removeAll(descartarCancelar);
    }

    public void handleFileUploadPapelesTrabajo(FileUploadEvent event) {
        if (getValidaAccionesHelper().validaArchivoCarga(event.getFile())) {
            try {
                DocumentoOrdenModel orden = new DocumentoOrdenModel();
                getCpaStringHelper().setPapelTrabajo(event.getFile());
                PapelesTrabajo papel = new PapelesTrabajo();
                papel.setFechaCreacion(new Date());
                papel.setNombreArchivo(
                        CargaArchivoUtilPropuestas.limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getPapelTrabajo().getFileName())));
                if (!getValidaAccionesHelper().validaNombrePapelTrabajo(papel.getNombreArchivo(), getConsultarPropAsignadaHelper().getListaPapeles())) {
                    papel.setArchivo(getCpaStringHelper().getPapelTrabajo().getInputstream());
                    getConsultarPropAsignadaHelper().getListaPapeles().add(papel);
                    orden.setPapelesTrabajo(papel);
                    orden.setInput(papel.getArchivo());
                    orden.setIsEliminar(true);
                    getConsultarPropAsignadaHelper().getListaPapelesTrabajo().add(orden);
                    getCpaDtoHelper().setBtnPapelTrabajo(getCpaDtoHelper().getBtnPapelTrabajo() + 1);
                    getCpaBooleanHelper().setMuestraDesabilitaPapel(true);
                    addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                } else {
                    addErrorMessage(null, ARCHIVO_YA_CARGADO, "");
                }
            } catch (IOException e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void handleFileUploadInformacionComplementaria(FileUploadEvent event) {
        if (getValidaAccionesHelper().validaArchivoOrden(event.getFile())) {
            try {
                getCpaStringHelper().setArchivoInfoComplementaria(event.getFile());

                FecetDocOrden docOrden = new FecetDocOrden();
                docOrden.setFechaCreacion(new Date());
                docOrden.setNombreArchivo(CargaArchivoUtilPropuestas
                        .limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoInfoComplementaria().getFileName())));
                getConsultarPropAsignadaHelper().setDocOrdenActualizado(new ArrayList<FecetDocOrden>());

                DocumentoOrdenModel orden = new DocumentoOrdenModel();
                orden.setDocumentoOrden(docOrden);
                docOrden.setArchivo(getCpaStringHelper().getArchivoInfoComplementaria().getInputstream());
                orden.setIsEliminar(true);
                orden.setInput(docOrden.getArchivo());
                getConsultarPropAsignadaHelper().getDocOrdenActualizado().add(docOrden);

                if (getConsultarPropAsignadaHelper().getListaOrdenes().size() == getCpaDtoHelper().getNumeroDocumentosOrden()) {
                    getConsultarPropAsignadaHelper().getListaOrdenes().add(orden);
                } else {
                    getConsultarPropAsignadaHelper().getListaOrdenes().set(0, orden);
                }

                Collections.sort(getConsultarPropAsignadaHelper().getListaOrdenes(), new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        DocumentoOrdenModel d1 = (DocumentoOrdenModel) o1;
                        DocumentoOrdenModel d2 = (DocumentoOrdenModel) o2;
                        return d2.getDocumentoOrden().getFechaCreacion().compareTo(d1.getDocumentoOrden().getFechaCreacion());
                    }
                });
                addMessage(null, Constantes.ARCHIVO_CARGADO, "");
            } catch (Exception e) {
                logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
            }
        }
    }

    public void handleFileUploadOficios(FileUploadEvent event) {
        Long conversor = -1L;
        BigDecimal tipoOficioSel = new BigDecimal(conversor);

        if (getCpaDtoHelper().getTipoOficioSeleccionado() != null && !getCpaDtoHelper().getTipoOficioSeleccionado().equals(tipoOficioSel)) {
            if (getValidaAccionesHelper().validaArchivoOrden(event.getFile())) {
                try {
                    getCpaStringHelper().setArchivoOficio(event.getFile());
                    FecetOficio docOficio = new FecetOficio();
                    FecetTipoOficio tipoOficio = new FecetTipoOficio();
                    int estatusOficio = EstatusOficiosOrdenesEnum.OFICIO_PENDIENTE_FIRMA.getIdEstatus();
                    docOficio.setFechaCreacion(new Date());
                    docOficio.setNombreArchivo(CargaArchivoUtilPropuestas
                            .limpiarPathArchivo(CargaArchivoUtilPropuestas.aplicarCodificacionTexto(getCpaStringHelper().getArchivoOficio().getFileName())));
                    tipoOficio.setIdTipoOficio(getCpaDtoHelper().getTipoOficioSeleccionado());
                    tipoOficio.setBlnActivo(BigDecimal.ZERO);
                    docOficio.setFecetTipoOficio(tipoOficio);
                    docOficio.setDocumentoPdf("0");
                    docOficio.setSuspencionPlazo("0");
                    docOficio.setIdEstatus(new BigDecimal(estatusOficio));

                    if (getConsultarPropAsignadaHelper().getOficiosActualizados() == null) {
                        getConsultarPropAsignadaHelper().setOficiosActualizados(new ArrayList<FecetOficio>());
                    }

                    if (!getValidaAccionesHelper().validaNombreOficio(getConsultarPropAsignadaHelper().getOficiosActualizados(),
                            docOficio.getFecetTipoOficio().getIdTipoOficio())) {
                        DocumentoOrdenModel oficio = new DocumentoOrdenModel();
                        oficio.setOficio(docOficio);
                        docOficio.setArchivo(getCpaStringHelper().getArchivoOficio().getInputstream());
                        oficio.setIsEliminar(true);
                        oficio.setInput(docOficio.getArchivo());
                        getConsultarPropAsignadaHelper().getListaOficios().add(oficio);
                        getConsultarPropAsignadaHelper().getOficiosActualizados().add(docOficio);

                        Collections.sort(getConsultarPropAsignadaHelper().getListaOficios(), new Comparator() {
                            @Override
                            public int compare(Object o1, Object o2) {
                                DocumentoOrdenModel f1 = (DocumentoOrdenModel) o1;
                                DocumentoOrdenModel f2 = (DocumentoOrdenModel) o2;
                                return f2.getOficio().getFechaCreacion().compareTo(f1.getOficio().getFechaCreacion());
                            }
                        });
                        addMessage(null, Constantes.ARCHIVO_CARGADO, "");
                    } else {
                        addErrorMessage(null, "El oficio ya fue cargado", "");
                    }
                } catch (Exception e) {
                    logger.error(Constantes.ERROR_DOCUMENTO_ADJUNTO, e);
                }
            } else {
                addErrorMessage(null, Constantes.ARCHIVO_WORD_INVALIDO, "");
            }
        } else {
            addErrorMessage(null, "Debes seleccionar un tipo de Oficio", "");
        }
    }

    public void descartarDocumentoOrden() {
        List<DocumentoOrdenModel> descartarOrden = new ArrayList<DocumentoOrdenModel>();

        for (DocumentoOrdenModel documento : getConsultarPropAsignadaHelper().getListaOrdenes()) {
            if (getCpaDtoHelper().getDocumentoOrdenSeleccionado().equals(documento)) {
                descartarOrden.add(documento);
                break;
            }
        }
        getConsultarPropAsignadaHelper().getListaOrdenes().removeAll(descartarOrden);
        getConsultarPropAsignadaHelper().getDocOrdenActualizado().clear();
    }

    public void descartarOficio() {
        List<DocumentoOrdenModel> descartaOficio = new ArrayList<DocumentoOrdenModel>();
        List<FecetOficio> descartar = new ArrayList<FecetOficio>();

        for (DocumentoOrdenModel oficio : getConsultarPropAsignadaHelper().getListaOficios()) {
            if (getCpaDtoHelper().getDocumentoOrdenSeleccionado().equals(oficio)) {
                descartaOficio.add(oficio);
                descartar.add(oficio.getOficio());
            }
        }
        getConsultarPropAsignadaHelper().getListaOficios().removeAll(descartaOficio);
        getConsultarPropAsignadaHelper().getOficiosActualizados().removeAll(descartar);
    }

    public String listadoPropuestas() {
        getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
        getCpaBooleanHelper().setMuestraDetalle1(false);
        getCpaBooleanHelper().setMuestraPrevios1(false);
        getCpaBooleanHelper().setMuestraContribuyenteAnterior(false);
        getCpaBooleanHelper().setMuestraFormularioCancelar(false);
        getCpaBooleanHelper().setMuestraFormularioRechazo(false);
        getCpaBooleanHelper().setMuestraFormularioRetroalimentar(false);
        getCpaBooleanHelper().setMuestraFormularioTransferir(false);
        getCpaBooleanHelper().setMostrarInfoComplementaria(false);
        getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        getCpaBooleanHelper().setMuestraPropuestas(true);
        getSession().removeAttribute("vieneDeConsulta");
        return DETALLE_PROP_ASIGNADAS_REDIRECT;
    }

    public void regresaDetalle() {
        if (getCpaBooleanHelper().getMuestraFormularioRechazo()) {
            getCpaBooleanHelper().setMuestraFormularioRechazo(false);
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        }

        if (getCpaBooleanHelper().getMuestraFormularioRetroalimentar()) {
            getCpaBooleanHelper().setMuestraFormularioRetroalimentar(false);
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        }

        if (getCpaBooleanHelper().getMuestraFormularioTransferir()) {
            getCpaBooleanHelper().setMuestraFormularioTransferir(false);
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        }

        if (getCpaBooleanHelper().isMuestraFormularioCancelar()) {
            getCpaBooleanHelper().setMuestraFormularioCancelar(false);
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        }

        if (getCpaBooleanHelper().isMostrarInfoComplementaria()) {
            getCpaBooleanHelper().setMostrarInfoComplementaria(false);
            if (!getCpaBooleanHelper().isSeActualizoDocumentos()) {
                getConsultarPropAsignadaHelper().getOficiosActualizados().clear();
                getConsultarPropAsignadaHelper().getDocOrdenActualizado().clear();
            }
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
        }

        getConsultarPropAsignadaHelper()
                .setListaPapeles(getConsultarPapelesTrabajoService().getPapelesByIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
        copiarDocumentos();
        getCpaBooleanHelper().setMuestraPropuestas(false);
        getCpaBooleanHelper().setMuestraDetalleContribuyente(true);
    }

    public boolean filtraFecha(Object value, Object filter, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        return sdf.format(value).contains(String.valueOf(filterText));
    }

    public void buscarAgenteAduanal() {
        FacesUtil mensajes = null;
        if (getCpaDtoHelper().getAgenteAduanalVO().getRfc() != null && !getCpaDtoHelper().getAgenteAduanalVO().getRfc().isEmpty()) {
            getCpaDtoHelper().setAgenteAduanalVO(getConsultarPropAsignadasMBHelper().validaRfc(getCpaDtoHelper().getAgenteAduanalVO().getRfc(), mensajes,
                    getContribuyenteService(), getConsultaMediosContactoService()));
        } else {
            addErrorMessage(null, "Debes ingresar un RFC", "");
        }
    }

    public void deshabilitaEmailColaborador() {
        getCpaDtoHelper().getAgenteAduanalVO().setDeshabilitarCorreo(true);
    }

    public void habilitaEmailColaborador() {
        getCpaDtoHelper().getAgenteAduanalVO().setDeshabilitarCorreo(false);
    }

    public void eliminarPapelTrabajo() {
        DocumentoOrdenModel papelTrabajo = getConsultarPropAsignadaHelper().getPapelesTrabajo();
        List<DocumentoOrdenModel> listaPapelesTrabajo = getConsultarPropAsignadaHelper().getListaPapelesTrabajo();
        List<PapelesTrabajo> listaPapeles = getConsultarPropAsignadaHelper().getListaPapeles();

        if (listaPapelesTrabajo.contains(papelTrabajo)) {
            listaPapelesTrabajo.remove(papelTrabajo);
            getCpaDtoHelper().setBtnPapelTrabajo(getCpaDtoHelper().getBtnPapelTrabajo() - 1);
        }

        if (listaPapeles.contains(papelTrabajo.getPapelesTrabajo())) {
            listaPapeles.remove(papelTrabajo.getPapelesTrabajo());
        }

        if (getCpaDtoHelper().getBtnPapelTrabajo() == 0) {
            getCpaBooleanHelper().setMuestraDesabilitaPapel(false);
        }
    }

    public void registrarPapelTrabajo() {
        if (getConsultarPropAsignadaHelper().getListaPapelesTrabajo() != null && !getConsultarPropAsignadaHelper().getListaPapelesTrabajo().isEmpty()) {
            for (DocumentoOrdenModel papel : getConsultarPropAsignadaHelper().getListaPapelesTrabajo()) {
                if (papel.isIsEliminar()) {
                    papel.getPapelesTrabajo().setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
                    String carpetasRFC = getCpaDtoHelper().getPropuestaSelAnalizar().getFecetContribuyente().getRfc();
                    String carpetaIdRegistro = getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro();
                    String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
                    papel.getPapelesTrabajo()
                            .setRutaArchivo(RutaArchivosUtilPropuestas.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PAPELES_TRABAJO_PROPUESTA, carpetasRFC,
                                            carpetaIdRegistro, carpetaUnica) + papel.getPapelesTrabajo().getNombreArchivo());
                    papel.getPapelesTrabajo().setBlnActivo(BigDecimal.ONE);
                    getConsultarPapelesTrabajoService().insertaPapelesTrabajo(papel.getPapelesTrabajo());
                    getConsultarPropuestasAsignadasService().guardarPapelTrabajo(papel);
                }
            }
            getConsultarPropAsignadaHelper()
                    .setListaPapeles(getConsultarPapelesTrabajoService().getPapelesByIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
            getConsultarPropAsignadaHelper().setListaPapelesTrabajo(new ArrayList<DocumentoOrdenModel>());
            copiarDocumentos();
            getCpaDtoHelper().setBtnPapelTrabajo(0);
            getCpaBooleanHelper().setMuestraDesabilitaPapel(false);
        }
    }

    public void agregarAgenteAduanal() {
        if (!getCpaDtoHelper().getAgenteAduanalVO().getRfc().isEmpty() || !getCpaDtoHelper().getAgenteAduanalVO().getNombre().isEmpty()) {
            StringBuilder mensaje = new StringBuilder();
            mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_4);
            mensaje.append(getCpaDtoHelper().getAgenteAduanalVO().getNombre());
            mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_2);
            addMessage(null, mensaje.toString(), "");
            getCpaDtoHelper().getAgenteAduanalVO().setDeshabilitarCorreo(false);
        } else {
            addErrorMessage(null, "Debes buscar un Agente Aduanal para poder guardarlo", "");
        }
    }

    public void validaActualizarDocumentoOrden() {

        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (getCpaBooleanHelper().isOrdenActualizable() && getCpaBooleanHelper().isOficioActaulizable()) {
            if (!getConsultarPropAsignadaHelper().getOficiosActualizados().isEmpty() && !getConsultarPropAsignadaHelper().getDocOrdenActualizado().isEmpty()
                    && getCpaDtoHelper().getNumeroOficiosActaulizables() == getConsultarPropAsignadaHelper().getOficiosActualizados().size()) {
                requestContext.execute(DIALOGO_UPDATE_ORDEN);
            } else {
                addErrorMessage(null, "Debes adjuntar el Documento Orden y/o Oficio", "");
            }
        }

        if (getCpaBooleanHelper().isOrdenActualizable() && !getCpaBooleanHelper().isOficioActaulizable()) {
            if (!getConsultarPropAsignadaHelper().getDocOrdenActualizado().isEmpty()) {
                requestContext.execute(DIALOGO_UPDATE_ORDEN);
            } else {
                addErrorMessage(null, "Debes adjuntar el Documento Orden", "");
            }
        }

        if (!getCpaBooleanHelper().isOrdenActualizable() && getCpaBooleanHelper().isOficioActaulizable()) {
            if (!getConsultarPropAsignadaHelper().getOficiosActualizados().isEmpty()
                    && getCpaDtoHelper().getNumeroOficiosActaulizables() == getConsultarPropAsignadaHelper().getOficiosActualizados().size()) {
                requestContext.execute(DIALOGO_UPDATE_ORDEN);
            } else {
                addErrorMessage(null, "Debes adjuntar el Oficio", "");
            }
        }

        if (getCpaBooleanHelper().isMuestraAgenteAduanal() && getCpaDtoHelper().getAgenteAduanalVO().getNombre() == null) {
            FacesUtil.addErrorMessage(null, "Debes agregar un Agente Aduanal", "");
        }

    }

    public void actualizarOrden() {
        List<ColaboradorVO> colaboradores = new ArrayList<ColaboradorVO>();
        colaboradores.add(getCpaDtoHelper().getAgenteAduanalVO());
        try {
            getConsultarPropuestasAsignadasService().insertarDocumentoOrden(getCpaDtoHelper().getPropuestaSelAnalizar(),
                    getConsultarPropAsignadaHelper().getDocOrdenActualizado(), getConsultarPropAsignadaHelper().getOficiosActualizados(), colaboradores,
                    getCpaBooleanHelper().isMuestraAgenteAduanal());
            List<FecetOficio> copiaOficios = (List<FecetOficio>) SerializationUtils.clone((Serializable) getConsultarPropAsignadaHelper().getOficiosActualizados());
            getSession().setAttribute(LISTA_OFICIOS_SESION, copiaOficios);
            getCpaBooleanHelper().setMostrarInfoComplementaria(false);
            getCpaBooleanHelper().setMuestraPropuestas(false);
            getCpaBooleanHelper().setMuestraDetalleContribuyente(true);
            getCpaBooleanHelper().setSeActualizoDocumentos(true);
            getConsultarPropAsignadasMBHelper().limpiarComponentes(getCpaDtoHelper(), getCpaStringHelper(), getConsultarPropAsignadaHelper(), getCpaBooleanHelper());
            getConsultarPropAsignadaHelper().setListaPapeles(getConsultarPapelesTrabajoService().getPapelesByIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta()));
            copiarDocumentos();
        } catch (NegocioException e) {
            addErrorMessage(null, "No pudo actualizar el Documento Orden", "");
        }
    }

    public void validarRechazoPropuesta() {
        Long conversor = -1L;
        BigDecimal idMotivoRechazo = new BigDecimal(conversor);
        if (getCpaStringHelper().getArchivoRechazo() != null && getConsultarPropAsignadaHelper().getListaRechazo().size() > 0) {
            if (getCpaDtoHelper().getMotivoRechazoSeleccionado() != null && !getCpaDtoHelper().getMotivoRechazoSeleccionado().equals(idMotivoRechazo)) {
                if (!getCpaStringHelper().getObservaciones().trim().isEmpty()) {
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmaOperacionRechazo').show();");
                } else {
                    addErrorMessage(null, "Debes agregar Observaciones", "");
                }
            } else {
                addErrorMessage(null, "Debes seleccionar un motivo de rechazo", "");
            }
        } else {
            addErrorMessage(null, "Debes cargar un documento", "");
        }
    }

    public void rechazarPropuesta() {
        if (!getConsultarPropAsignadaHelper().getListaRechazo().isEmpty()) {
            try {
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(true);
                guardarRechazoPropuesta();
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                getCpaBooleanHelper().setMuestraFormularioRechazo(false);
                getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
                getCpaBooleanHelper().setMuestraPropuestas(true);
                getCpaDtoHelper().setMotivoRechazoSeleccionado(null);
                getCpaStringHelper().setArchivoRechazo(null);
                getCpaStringHelper().setObservaciones(null);
                getConsultarPropAsignadaHelper().getListaRechazo().clear();
            } catch (Exception e) {
                logger.error(ERROR_AL_GUARDAR + e.getCause(), e);
                if (getCpaBooleanHelper().isDeshabilitaBtnFormulario()) {
                    addErrorMessage(null, Constantes.ERROR_GUARDAR_REGISTRO, "");
                    getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                }
            }
        } else {
            getConsultarPropAsignadasMBHelper().validaMuestraMensajeError(getCpaBooleanHelper().isDeshabilitaBtnFormulario());
        }
    }

    private void guardarRechazoPropuesta() {
        FecetRechazoPropuesta propuestaRechazar = getConsultarPropAsignadaHelper().getListaRechazo().get(0);
        BigDecimal idEstatus;
        BigDecimal estatusEntrante = getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus();

        try {
            idEstatus = new BigDecimal(TipoEstatusEnum.PROPUESTA_RECHAZADA_PENDIENTE_VALIDACION.getId());
            propuestaRechazar.setIdMotivo(getCpaDtoHelper().getMotivoRechazoSeleccionado());
            propuestaRechazar.setDescripcion(getCpaStringHelper().getObservaciones());
            propuestaRechazar.setFechaInformeRechazo(new Date());
            propuestaRechazar.setIdEmpleado(getEmpleadoLogueado().getIdEmpleado());
            propuestaRechazar.setFechaRechazo(new Date());
            propuestaRechazar.setRfcRechazo(getRFCSession());
            propuestaRechazar.setEstatus(idEstatus);
            propuestaRechazar.setBlnEstatus(new BigDecimal(1L));
            propuestaRechazar.setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
            getCpaDtoHelper().getPropuestaSelAnalizar().setIdEstatus(idEstatus);
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcFirmante(getCpaDtoHelper().getPropuestaSelAnalizar().getRfcFirmante());
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcAuditor(getRFCSession());
            getConsultarPropuestasAsignadasService().guardarPropuestaRechazada(getCpaDtoHelper().getPropuestaSelAnalizar(), propuestaRechazar,
                    getConsultarPropAsignadaHelper().getListaRechazo(), estatusEntrante);
            addMessage(null, Constantes.REGISTRO + getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro() + Constantes.REGISTRO_RECHAZADO,
                    "");
        } catch (Exception e) {
            getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
            logger.error("Error al guardar rechazo: " + e.getCause());
        }
    }

    public void validaCancelarPropuesta() {
        Long conversor = -1L;
        BigDecimal idCausaCancelacion = new BigDecimal(conversor);

        if (getCpaDtoHelper().getCausaCancelacionSeleccion() != null && !getCpaDtoHelper().getCausaCancelacionSeleccion().equals(idCausaCancelacion)) {
            if (getCpaStringHelper().getArchivoCancelar() != null && getConsultarPropAsignadaHelper().getListaCancelar().size() > 0) {
                if (!getCpaStringHelper().getObservaciones().trim().isEmpty()) {
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmaOperacionCancelacion').show();");
                } else {
                    addErrorMessage(null, "Debes agregar observaciones", "");
                }
            } else {
                addErrorMessage(null, "Debes cargar un documento", "");
            }
        } else {
            addErrorMessage(null, "Debes seleccionar una causa de cancelaci\u00f3n", "");
        }
    }

    public void cancelarPropuesta() {
        if (!getConsultarPropAsignadaHelper().getListaCancelar().isEmpty()) {
            try {
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(true);
                guardarCancelacionPropuesta();
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                getCpaBooleanHelper().setMuestraFormularioCancelar(false);
                getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
                getCpaBooleanHelper().setMuestraPropuestas(true);
                getCpaDtoHelper().setCausaCancelacionSeleccion(null);
                getCpaStringHelper().setArchivoCancelar(null);
                getCpaStringHelper().setObservaciones(null);
                getConsultarPropAsignadaHelper().getListaCancelar().clear();
            } catch (Exception e) {
                logger.error(ERROR_AL_GUARDAR + e.getCause(), e);
                if (getCpaBooleanHelper().isDeshabilitaBtnFormulario()) {
                    addErrorMessage(null, Constantes.ERROR_GUARDAR_REGISTRO, "");
                    getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                }
            }
        } else {
            getConsultarPropAsignadasMBHelper().validaMuestraMensajeError(getCpaBooleanHelper().isDeshabilitaBtnFormulario());
        }
    }

    private void guardarCancelacionPropuesta() {

        BigDecimal idEstatus;
        FecetPropCancelada cancelarPropuesta = getConsultarPropAsignadaHelper().getListaCancelar().get(0);
        BigDecimal estatusEntrante = getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus();

        try {
            idEstatus = new BigDecimal(TipoEstatusEnum.CANCELACION_PENDIENTE_DE_VALIDACION.getId());
            cancelarPropuesta.setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
            cancelarPropuesta.setIdCausa(getCpaDtoHelper().getCausaCancelacionSeleccion());
            cancelarPropuesta.setObservaciones(getCpaStringHelper().getObservaciones());
            cancelarPropuesta.setFechaCreacion(new Date());
            cancelarPropuesta.setRfcCreacion(getRFCSession());
            cancelarPropuesta.setFechaCancelacion(new Date());
            cancelarPropuesta.setFechaRechazo(null);
            cancelarPropuesta.setDescripcionRechazo(null);
            cancelarPropuesta.setIdEmpleado(getEmpleadoLogueado().getIdEmpleado());
            cancelarPropuesta.setIdEstatus(idEstatus);
            cancelarPropuesta.setBlnEstatus(new BigDecimal(1L));
            getCpaDtoHelper().getPropuestaSelAnalizar().setIdEstatus(idEstatus);
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcFirmante(getCpaDtoHelper().getPropuestaSelAnalizar().getRfcFirmante());
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcAuditor(getRFCSession());
            getConsultarPropuestasAsignadasService().guardarCancelacionPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar(), cancelarPropuesta,
                    getConsultarPropAsignadaHelper().getListaCancelar(), estatusEntrante);

            addMessage(null, Constantes.REGISTRO + getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro() + Constantes.REGISTRO_CANCELADO, "");
        } catch (Exception e) {
            getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
            logger.error("No se pudo guardar la cancelaci\u00f3n: " + e.getCause(), e);
        }
    }

    public void validarTransferirPropuesta() {
        Long conversor = -1L;
        BigDecimal idArace = new BigDecimal(conversor);
        if (getCpaDtoHelper().getIdAraceSeleccionado() != null && !getCpaDtoHelper().getIdAraceSeleccionado().equals(idArace)) {
            if (getCpaStringHelper().getArchivoTransferir() != null && getConsultarPropAsignadaHelper().getListaTransferir().size() > 0) {
                if (!getCpaStringHelper().getDescripcionTransferir().trim().isEmpty()) {
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmaOperacionTransferir').show();");
                } else {
                    addErrorMessage(null, "Debes agregar una Causa", "");
                }
            } else {
                addErrorMessage(null, "Debes cargar un documento", "");
            }
        } else {
            addErrorMessage(null, "Debes seleccionar una Unidad Administrativa", "");
        }
    }

    public void transferirPropuesta() {
        if (!getConsultarPropAsignadaHelper().getListaTransferir().isEmpty()) {
            try {
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(true);
                guardarTransferirPropuesta();
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                getCpaBooleanHelper().setMuestraFormularioTransferir(false);
                getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
                getCpaBooleanHelper().setMuestraPropuestas(true);
                getCpaDtoHelper().setIdAraceSeleccionado(null);
                getCpaStringHelper().setArchivoTransferir(null);
                getCpaStringHelper().setDescripcionTransferir(null);
                getConsultarPropAsignadaHelper().getListaTransferir().clear();
            } catch (Exception e) {
                logger.error("Error al guardar: " + e.getCause(), e);
                if (getCpaBooleanHelper().isDeshabilitaBtnFormulario()) {
                    addErrorMessage(null, Constantes.ERROR_GUARDAR_REGISTRO, "");
                    getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                }
            }
        } else {
            getConsultarPropAsignadasMBHelper().validaMuestraMensajeError(getCpaBooleanHelper().isDeshabilitaBtnFormulario());
        }
    }

    private void guardarTransferirPropuesta() {
        BigDecimal idEstatus;
        FecetTransferencia transferirPropuesta = getConsultarPropAsignadaHelper().getListaTransferir().get(0);
        BigDecimal estatusEntrante = getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus();

        try {
            idEstatus = new BigDecimal(TipoEstatusEnum.PROPUESTA_REGISTRO_ENVIADO_APROBACION_TRANSFERIDO.getId());
            transferirPropuesta.setIdAraceDestino(getCpaDtoHelper().getIdAraceSeleccionado());
            transferirPropuesta.setFechaTraspaso(new Date());
            transferirPropuesta.setRfc(getRFCSession());
            transferirPropuesta.setObservaciones(getCpaStringHelper().getDescripcionTransferir());
            transferirPropuesta.setFechaRechazo(null);
            transferirPropuesta.setEstatus(idEstatus);
            transferirPropuesta.setIdEmpleado(getEmpleadoLogueado().getIdEmpleado());
            transferirPropuesta.setBlnEstatus(new BigDecimal(1L));
            transferirPropuesta.setIdAraceOrigen(getCpaDtoHelper().getPropuestaSelAnalizar().getIdArace());
            transferirPropuesta.setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
            getCpaDtoHelper().getPropuestaSelAnalizar().setIdEstatus(idEstatus);
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcFirmante(getCpaDtoHelper().getPropuestaSelAnalizar().getRfcFirmante());
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcAuditor(getRFCSession());
            getConsultarPropuestasAsignadasService().guardarTransferirPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar(), transferirPropuesta,
                    getConsultarPropAsignadaHelper().getListaTransferir(), estatusEntrante);

            addMessage(null, "El registro " + getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro() + Constantes.REGISTRO_TRANSFERIDO, "");
        } catch (Exception e) {
            getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
            logger.error("Error al guardar transferencia: " + e.getCause());
        }
    }

    public void validarRetroalimentarPropuesta() {
        Long conversor = -1L;
        BigDecimal idMotivoRetro = new BigDecimal(conversor);

        if (getCpaDtoHelper().getIdRetroalimentacionSeleccionado() != null && !getCpaDtoHelper().getIdRetroalimentacionSeleccionado().equals(idMotivoRetro)) {
            if (getCpaStringHelper().getArchivoRetroalimentar() != null && getConsultarPropAsignadaHelper().getListaRetroalimentar().size() > 0) {
                if (!getCpaStringHelper().getDescripcionRetroalimentar().trim().isEmpty()) {
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.execute("PF('confirmaOperacionRetroalimentacion').show();");
                } else {
                    addErrorMessage(null, "Debes agregar el Detalle", "");
                }
            } else {
                addErrorMessage(null, "Debes cargar un archivo por retroalimentar", "");
            }
        } else {
            addErrorMessage(null, "Debes seleccionar un motivo de Retroalimentaci\u00f3n", "");
        }
    }

    public void retroalimentarPropuesta() {
        if (!getConsultarPropAsignadaHelper().getListaRetroalimentar().isEmpty()) {
            try {
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(true);
                guardarRetroalimentarPropuesta();
                getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                getCpaBooleanHelper().setMuestraFormularioRetroalimentar(false);
                getCpaBooleanHelper().setMuestraDetalleContribuyente(false);
                getCpaBooleanHelper().setMuestraPropuestas(true);
                getCpaDtoHelper().setIdRetroalimentacionSeleccionado(null);
                getCpaStringHelper().setArchivoRetroalimentar(null);
                getCpaStringHelper().setDescripcionRetroalimentar(null);
                getConsultarPropAsignadaHelper().getListaRetroalimentar().clear();
            } catch (Exception e) {
                logger.error(ERROR_AL_GUARDAR + e.getCause(), e);
                if (getCpaBooleanHelper().isDeshabilitaBtnFormulario()) {
                    addErrorMessage(null, Constantes.ERROR_GUARDAR_REGISTRO, "");
                    getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
                }
            }
        } else {
            getConsultarPropAsignadasMBHelper().validaMuestraMensajeError(getCpaBooleanHelper().isDeshabilitaBtnFormulario());
        }
    }

    private void guardarRetroalimentarPropuesta() {
        BigDecimal idEstatus;
        FecetRetroalimentacion retroalimentarPropuesta = getConsultarPropAsignadaHelper().getListaRetroalimentar().get(0);
        BigDecimal estatusEntrante = getCpaDtoHelper().getPropuestaSelAnalizar().getIdEstatus();

        try {
            idEstatus = new BigDecimal(TipoEstatusEnum.RETROALIMENTACION_PENDIENTE_DE_VALIDACION.getId());
            retroalimentarPropuesta.setIdMotivo(getCpaDtoHelper().getIdRetroalimentacionSeleccionado());
            retroalimentarPropuesta.setDescripcion(getCpaStringHelper().getDescripcionRetroalimentar());
            retroalimentarPropuesta.setRfcRetroalimentacion(getRFCSession());
            retroalimentarPropuesta.setFechaRetroalimentacion(new Date());
            retroalimentarPropuesta.setFechaRechazo(null);
            retroalimentarPropuesta.setDescripcionRechazo(null);
            retroalimentarPropuesta.setIdEmpleado(getEmpleadoLogueado().getIdEmpleado());
            retroalimentarPropuesta.setEstatus(idEstatus);
            retroalimentarPropuesta.setBlnEstatus(new BigDecimal(1L));
            retroalimentarPropuesta.setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
            retroalimentarPropuesta.setFechaCreacion(getCpaDtoHelper().getPropuestaSelAnalizar().getFechaCreacion());
            getCpaDtoHelper().getPropuestaSelAnalizar().setIdEstatus(idEstatus);
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcFirmante(getCpaDtoHelper().getPropuestaSelAnalizar().getRfcFirmante());
            getCpaDtoHelper().getPropuestaSelAnalizar().setRfcAuditor(getRFCSession());
            getConsultarPropuestasAsignadasService().guardarRetroalimentarPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar(), retroalimentarPropuesta,
                    getConsultarPropAsignadaHelper().getListaRetroalimentar(), estatusEntrante);

            addMessage(null, Constantes.REGISTRO + getCpaDtoHelper().getPropuestaSelAnalizar().getIdRegistro() + Constantes.REGISTRO_RETROALIMENTADO, "");
        } catch (Exception e) {
            getCpaBooleanHelper().setDeshabilitaBtnFormulario(false);
            logger.error("No se pudo guardar la retroalimentacion: " + e.getCause(), e);
        }
    }

    public void cargaAsignarConsulta() {
        try {
            getConsultarPropAsignadaHelper().setIdEmpleadoJefeDepartamento(null);
            getConsultarPropAsignadaHelper().setIdEmpleadoEnlace(null);

            cargarJefeDepartamento();
            cargarEnlace();
            List<AsociadoFuncionarioDTO> jefeDepto = getConsultarPropAsignadasMBHelper().obtenerJefeDeptoAsignado(
                    getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                    getConsultarPropuestasAsignadasService());
            List<AsociadoFuncionarioDTO> enlace = getConsultarPropAsignadasMBHelper().obtenerEnlaceAsignado(
                    getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta(),
                    getConsultarPropuestasAsignadasService());
            boolean enlaceActivo = false;
            boolean jefeDeptoActivo = false;
            boolean tieneJefeAsignado = false;
            boolean tieneEnlaceAsignado = false;
            String nombreJefeDepartamento = null;
            String nombreEnlace = null;

            if (jefeDepto != null && !jefeDepto.isEmpty()) {
                tieneJefeAsignado = true;

                if (getConsultarPropAsignadasMBHelper().esEmpleadoActivo(
                        getConsultarPropAsignadaHelper().getListaJefeDepartamento(),
                        jefeDepto.get(0).getIdEmpleado())) {
                    getConsultarPropAsignadaHelper().setIdEmpleadoJefeDepartamento(jefeDepto.get(0).getIdEmpleado());
                    jefeDeptoActivo = true;
                    try {
                        for (EmpleadoDTO empleadoJefe : getConsultarPropAsignadaHelper().getListaJefeDepartamento()) {
                            if (empleadoJefe.getIdEmpleado().equals(jefeDepto.get(0).getIdEmpleado())) {
                                EmpleadoDTO jefeAsignado = new EmpleadoDTO();
                                jefeAsignado.setNombre(empleadoJefe.getNombreCompleto());
                                nombreJefeDepartamento = jefeAsignado.getNombre();
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage(), ex);
                        nombreJefeDepartamento = "";
                    }
                }
            }

            if (enlace != null && !enlace.isEmpty()) {
                tieneEnlaceAsignado = true;
                if (getConsultarPropAsignadasMBHelper().esEmpleadoActivo(
                        getConsultarPropAsignadaHelper().getListaEnlace(), enlace.get(0).getIdEmpleado())) {
                    getConsultarPropAsignadaHelper().setIdEmpleadoEnlace(enlace.get(0).getIdEmpleado());
                    enlaceActivo = true;
                    try {
                        for (EmpleadoDTO empleadoEnlace : getConsultarPropAsignadaHelper().getListaEnlace()) {
                            if (empleadoEnlace.getIdEmpleado().equals(enlace.get(0).getIdEmpleado())) {
                                EmpleadoDTO enlaceAsignado = new EmpleadoDTO();
                                enlaceAsignado.setNombre(empleadoEnlace.getNombreCompleto());
                                nombreEnlace = enlaceAsignado.getNombre();
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                        nombreEnlace = "";
                    }
                }
            }

            RequestContext requestContext = RequestContext.getCurrentInstance();

            if (getConsultarPropAsignadasMBHelper().validaConstruccionMensaje(tieneJefeAsignado, tieneEnlaceAsignado,
                    jefeDeptoActivo, enlaceActivo)) {
                String mensajeAsignacionConsulta = getConsultarPropAsignadasMBHelper().construyeMensaje(jefeDeptoActivo,
                        enlaceActivo, nombreJefeDepartamento, nombreEnlace);
                addMessage(null, mensajeAsignacionConsulta, "");
                requestContext.update("formPropuestas:msgAsociado");
            }
            getCpaBooleanHelper().setMuestraAsignarConsulta(true);
            requestContext.execute("PF('dlgAsignarConsulta').show();");
        } catch (Exception e) {
            logger.error(e.getMessage());
            addErrorMessage(null, "El empleado no cuenta con Jefe de Departamento y/o un Enlace asociados", "");
        }
    }

    public void asignarConsulta() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        boolean realizoAsignacion = false;

        if (getConsultarPropAsignadaHelper().getIdEmpleadoJefeDepartamento() != null && !getConsultarPropAsignadaHelper().getIdEmpleadoJefeDepartamento().equals(BigDecimal.ZERO)) {
            insertarAsociadoFuncionario(getConsultarPropAsignadaHelper().getListaJefeDepartamento());
            realizoAsignacion = true;

        }

        if (getConsultarPropAsignadaHelper().getIdEmpleadoEnlace() != null && !getConsultarPropAsignadaHelper().getIdEmpleadoEnlace().equals(BigDecimal.ZERO)) {
            insertarAsociadoFuncionario(getConsultarPropAsignadaHelper().getListaEnlace());
            realizoAsignacion = true;
        }

        if (getConsultarPropAsignadaHelper().getIdEmpleadoJefeDepartamento() == null && getConsultarPropAsignadaHelper().getIdEmpleadoEnlace() == null) {
            addErrorMessage(null, "Es necesario seleccionar un Jefe de Departamento y/o un Enlace para realizar la asignaci\u00f3n de la consulta", "");
            requestContext.update("formPropuestas:msgAsociado");
        } else {
            requestContext.execute("PF('dlgAsignarConsulta').hide();");
        }

        if (realizoAsignacion) {
            getPistasAuditoriasPropuestasService().pistaAsignarConsulta(getCpaDtoHelper().getPropuestaSelAnalizar());
            addMessage(null, "Se ha asignado la consulta de documentaci\u00f3n correctamente", "");
            requestContext.update("formPropuesta:msgAdicional");
            getConsultarPropAsignadaHelper().getListaJefeDepartamento().clear();
            getConsultarPropAsignadaHelper().getListaEnlace().clear();
        }
    }

    private AsociadoFuncionarioDTO llenarAsociadoFuncionario(EmpleadoDTO empleado) {
        AsociadoFuncionarioDTO asociadoFuncionarioDTO = new AsociadoFuncionarioDTO();
        asociadoFuncionarioDTO.setIdPropuesta(getCpaDtoHelper().getPropuestaSelAnalizar().getIdPropuesta());
        asociadoFuncionarioDTO.setIdEmpleado(empleado.getIdEmpleado());
        asociadoFuncionarioDTO.setIdTipoEmpleado(empleado.getDetalleEmpleado().get(0).getTipoEmpleado().getBigId());
        asociadoFuncionarioDTO.setBlnActivo(Constantes.ENTERO_UNO);

        return asociadoFuncionarioDTO;
    }

    private void insertarAsociadoFuncionario(List<EmpleadoDTO> listaFuncionarios) {
        EmpleadoDTO empleado = new EmpleadoDTO();
        AsociadoFuncionarioDTO asociadoFuncionario;

        for (EmpleadoDTO funcionario : listaFuncionarios) {
            if (funcionario.getIdEmpleado().equals(getConsultarPropAsignadaHelper().getIdEmpleadoJefeDepartamento())) {
                empleado = funcionario;
                break;
            }
            if (funcionario.getIdEmpleado().equals(getConsultarPropAsignadaHelper().getIdEmpleadoEnlace())) {
                empleado = funcionario;
                break;
            }
        }

        asociadoFuncionario = llenarAsociadoFuncionario(empleado);
        List<AsociadoFuncionarioDTO> asociadoRepetido = getConsultarPropuestasAsignadasService()
                .buscarAsociadoFuncionarioActivoByPropuesta(asociadoFuncionario);

        if (asociadoRepetido != null && !asociadoRepetido.isEmpty()) {
            asociadoRepetido.get(0).setBlnActivo(Constantes.ENTERO_CERO);
            getConsultarPropuestasAsignadasService().actualizarAsociadoFuncionario(asociadoRepetido.get(0));
        }

        getConsultarPropuestasAsignadasService().insertarAsociadoFuncionario(asociadoFuncionario, getCpaDtoHelper().getPropuestaSelAnalizar());
    }

    public void redireccionaRegreso() throws IOException {
        String urlRegreso = (String) getSession().getAttribute("urlRegreso");
        getSession().removeAttribute("urlRegreso");
        try {
            if (urlRegreso != null) {
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

                externalContext.redirect(origRequest.getContextPath() + urlRegreso);
            }
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
        }
    }
}
