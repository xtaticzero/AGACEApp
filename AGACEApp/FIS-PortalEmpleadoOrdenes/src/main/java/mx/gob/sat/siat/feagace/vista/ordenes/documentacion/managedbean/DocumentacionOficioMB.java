package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.managedbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.poi.util.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.buzon.model.MedioComunicacion;
import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.bo.ordenes.rules.ReglasNegocioOrdenesBO;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.exception.NoExisteContribuyenteException;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.DocumentacionOficioMBSubAbstract;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "documentacionOficioMB")
@SessionScoped
public class DocumentacionOficioMB extends DocumentacionOficioMBSubAbstract {

    private static final long serialVersionUID = 1L;

    public void init() {
        if (getRecargarInformacion()) {
            setFechaCargaPapelTrabajo(new Date());
            setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
            setNumeroDocumentoPapelTrabajoOficio(0);
            setSinMediosCompulsa(false);

            setListaPromociones(getSeguimientoOrdenesService().getPromocionContadorPruebasAlegatosOficio(getOficioSeleccionado()));

            setProrrogaVisible(getSeguimientoOrdenesService().validaMetodoProrrogaOficio(getOrdenSeleccionada(), getOficioSeleccionado()));

            if (getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())) {
                try {
                    FecetContribuyente contribuyente = getContribuyenteService().getContribuyenteIDC(getOficioSeleccionado().getRfcCompulsado().toUpperCase());
                    ValidaMediosContactoBO validaMediosContactoBO = validaMediosContacto(contribuyente.getRfc().toUpperCase());
                    if (!validaMediosContactoBO.getMediosComunicacion().isEmpty()) {
                        for (MedioComunicacion medio : validaMediosContactoBO.getMediosComunicacion()) {
                            if (medio.getDescMedio().contains("correo") || !medio.getMedio().trim().isEmpty()) {
                                validaMediosContactoBO.setFlag(true);
                                break;
                            }
                        }
                    }
                    if (validaMediosContactoBO.isFlag()) {
                        setSinMediosCompulsa(false);
                    } else {
                        setSinMediosCompulsa(true);
                        for (FecetPromocionOficio promocionOficio : getListaPromociones()) {
                            promocionOficio.setExtemporanea("0");
                        }
                    }
                } catch (NoExisteContribuyenteException e) {
                    logger.error("No se encuentra registrada informaci\u00f3n para el RFC: " + getOficioSeleccionado().getRfcCompulsado().toUpperCase()
                            + "; favor de verificar.");
                }
            }

            setListaProrroga(getSeguimientoOrdenesService().getProrrogaPorOficioEstatusPendienteAuditor(getOficioSeleccionado().getIdOficio()));

            setListaProrrogaHistorico(getSeguimientoOrdenesService().getHistoricoProrrogaOficio(getOficioSeleccionado().getIdOficio()));

            setListaOficiosRechazados(new ArrayList<FecetOficio>());

            cargarTabsOficio();

            limpiarOficiosCargados();

            setRecargarInformacion(false);

            cargarPapelesTrabajoOficio();

        }
    }

    public void limpiaFormulario() {
        setRecargarInformacion(true);
    }

    public void limpiarOficiosCargados() {
        setOfMulta(null);
        setAnexoOfMulta(null);
        setListaOfMulta(new ArrayList<FecetOficio>());
        setListaAnexosMulta(new ArrayList<FecetOficioAnexos>());

        setOfAvisoContribuyente(null);
        setListaOfAvisoContribuyente(new ArrayList<FecetOficio>());
        setListaAnexosAvisoContribuyente(new ArrayList<FecetOficioAnexos>());

        setProrrogaOficioSeleccionada(new FecetProrrogaOficio());
        setArchivoDescargaProrroga(null);

        setListaDocumentosProrroga(new ArrayList<FecetDocProrrogaOficio>());
        setDocumentacionProrrogaOficioSeleccionado(new FecetDocProrrogaOficio());

        setArchivoDescargaDocumentacionProrroga(null);

        setFecetFlujoProrrogaOficioRechazo(new FecetFlujoProrrogaOficio());
        setListaAnexosProrrogaOficioRechazo(new ArrayList<FecetAnexosProrrogaOficio>());
        setAnexoProrrogaOficioSeleccionadoRechazo(new FecetAnexosProrrogaOficio());

        setFecetFlujoProrrogaOficio(new FecetFlujoProrrogaOficio());
        setListaAnexosProrrogaOficio(new ArrayList<FecetAnexosProrrogaOficio>());

        setArchivoDescargaAcuseProrrogaOficio(null);

        setArchivoPromocionOficio(null);
        setArchivoPruebasAlegatosOficio(null);

        setListaPromocionesOficioCargadas(new ArrayList<FecetPromocionOficio>());
        setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
        setListaResolucionProrrogaOficio(new ArrayList<FecetAnexosProrrogaOficio>());
        setListaResolucionProrrogaOficioRechazo(new ArrayList<FecetAnexosProrrogaOficio>());
        setPanelDocumentacionVisible(false);
    }

    // Multas
    public void cargaOficioMulta(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            setOfMulta(event.getFile());
            cargarTablaOfMulta();
        }
    }

    public void cargarTablaOfMulta() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.MULTA));
        oficio.setIdOficioPrincipal(getOficioSeleccionado().getIdOficio());

        setListaOfMulta(cargarTablaOficio(oficio, getOfMulta(), getOrdenSeleccionada(), getListaOfMulta()));
    }

    public void limpiarMulta() {
        setListaOfMulta(new ArrayList<FecetOficio>());
    }

    public void cargaAnexosOficioMulta(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())) {
            setAnexoOfMulta(event.getFile());
            cargarTablaAnexosOfMulta();
        }
    }

    public void cargarTablaAnexosOfMulta() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.MULTA));
        oficio.setIdOficioPrincipal(getOficioSeleccionado().getIdOficio());
        setListaAnexosMulta(cargarTablaAnexos(getAnexoOfMulta(), getListaAnexosMulta()));
    }

    /**
     * ***********************************************************************
     * AVISO
     * CONTRIBUYENTE************************************************************
     * *****************
     */
    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaOficioAvisoContribuyente(final FileUploadEvent event) {
        if (validaArchivoCarga(event.getFile(), Constantes.DOCUMENTO_CARGADO_WORD)) {
            setOfAvisoContribuyente(event.getFile());
            cargarTablaOfAvisoContribuyente();
        }
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosAvisoContribuyente(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosOficio(getListaAnexosAvisoContribuyente(), event.getFile().getFileName())) {
            cargarTablaAnexosAvisoContribuyente(event.getFile());
        }
    }

    public void cargarTablaOfAvisoContribuyente() {
        FecetOficio oficio = new FecetOficio();
        oficio.setFecetTipoOficio(FecetTipoOficio.construirTipoOficio(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE));
        oficio.setIdOficioPrincipal(getOficioSeleccionado().getIdOficio());

        setListaOfAvisoContribuyente(new ArrayList<FecetOficio>());
        setListaOfAvisoContribuyente(cargarTablaOficio(oficio, getOfAvisoContribuyente(), getOrdenSeleccionada(), getListaOfAvisoContribuyente()));
    }

    public void cargarTablaAnexosAvisoContribuyente(UploadedFile file) {
        setListaAnexosAvisoContribuyente(cargarTablaAnexos(file, getListaAnexosAvisoContribuyente()));
    }

    public void limpiarAvisoContribuyente() {
        setListaOfAvisoContribuyente(new ArrayList<FecetOficio>());
    }

    public void eliminarAnexoOficioMulta() {
        eliminarDocumentoIdenticoAnexosMulta(getListaAnexosMulta());
    }

    public void eliminarDocumentoIdenticoAnexosMulta(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    public void eliminarAnexoOficioAvisoContribuyente() {
        eliminarDocumentoIdenticoAnexosLista(getListaAnexosAvisoContribuyente());
    }

    public void limpiarAnexosResolucionProrroga() {
        setListaAnexosProrrogaOficio(new ArrayList<FecetAnexosProrrogaOficio>());
        setListaAnexosProrrogaOficioRechazo(new ArrayList<FecetAnexosProrrogaOficio>());
        setListaResolucionProrrogaOficio(new ArrayList<FecetAnexosProrrogaOficio>());
        setListaResolucionProrrogaOficioRechazo(new ArrayList<FecetAnexosProrrogaOficio>());
    }

    public void guardarAvisoContribuyente() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getListaOfAvisoContribuyente());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getListaAnexosAvisoContribuyente());

            getSeguimientoOrdenesService().guardarAvisoContribuyente(reglasNegocioOrdenesBO);
            addMessage(MENSAJE_CARGA_OFICIO, "La informacion se guardo exitosamente");
            this.setRecargarInformacion(true);
            init();
        } catch (NegocioException e) {
            addErrorMessage(MENSAJE_CARGA_OFICIO, e.getMessage(), "");
        }
    }

    public void guardarOficioMulta() {
        try {
            ReglasNegocioOrdenesBO reglasNegocioOrdenesBO = new ReglasNegocioOrdenesBO();
            reglasNegocioOrdenesBO.setOrden(getOrdenSeleccionada());
            reglasNegocioOrdenesBO.setListaOfPrincipal(getListaOfMulta());
            reglasNegocioOrdenesBO.setListaAnexosPrincipal(getListaAnexosMulta());

            getSeguimientoOrdenesService().guardarAvisoContribuyente(reglasNegocioOrdenesBO);
            addMessage(MENSAJE_CARGA_OFICIO, "La informacion se guardo exitosamente");
            this.setRecargarInformacion(true);
            init();
        } catch (NegocioException e) {
            addErrorMessage(MENSAJE_CARGA_OFICIO, e.getMessage(), "");
        }
    }

    /**
     * ***********************************************************************
     * METODOS
     * PRIVADOS*****************************************************************
     * ************
     */
    private void cargarTabsOficio() {
        getSeguimientoOrdenesService()
                .setListaOficiosEnProcesoVencidos(getSeguimientoOrdenesService().getOficiosPorOrdenEnProcesoOVencidos(getOficioSeleccionado().getIdOrden()));
        setVisualizarTabAvisoContribuyente(
                getGenerarOficioService().validarVisualizacionTabAvisoContribuyenteOpcional(getOrdenSeleccionada(), getOficioSeleccionado()));
    }

    /**
     * ***********************************************************************
     * METODOS
     * COMUNES******************************************************************
     * ***********
     */
    public List<FecetOficio> cargarTablaOficio(FecetOficio oficio, UploadedFile file, final AgaceOrden orden, List<FecetOficio> listaOficioUnico) {
        if (file != null) {
            try {
                oficio.setFechaCreacion(new Date());
                oficio.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                oficio.setArchivo(file.getInputstream());
                oficio.setIdOrden(orden.getIdOrden());
                oficio.setIdEstatus(Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);
                listaOficioUnico.add(oficio);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaOficioUnico;
    }

    public final List<FecetOficioAnexos> cargarTablaAnexos(final UploadedFile file, final List<FecetOficioAnexos> listaAnexos) {
        FecetOficioAnexos anexo = new FecetOficioAnexos();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void eliminarDocumentoIdenticoAnexosLista(final List<FecetOficioAnexos> listaAnexos) {
        for (Iterator<FecetOficioAnexos> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetOficioAnexos anexo = iter.next();
            if (anexo == getAnexoSeleccionado()) {
                iter.remove();
            }
        }
    }

    // ***********************************************************************PRORROGA
    // ORDEN*****************************************************************************
    public final List<FecetAnexosProrrogaOficio> cargarTablaAnexosProrrogaOficio(final UploadedFile file, final List<FecetAnexosProrrogaOficio> listaAnexos) {
        FecetAnexosProrrogaOficio anexo = new FecetAnexosProrrogaOficio();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void cargarTablaAnexosProrrogaOficio(UploadedFile file) {
        setListaAnexosProrrogaOficio(cargarTablaAnexosProrrogaOficio(file, getListaAnexosProrrogaOficio()));
    }

    // /**
    // * Metodo utilizado para guardar el documento dentro del fileSystem y
    // * regresar los datos y respuesta del almacenamiento.
    // *
    // * @param event Evento de carga temporal de archivo.
    // */
    public void cargaAnexosProrrogaOficio(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosProrrogaOficio(getListaAnexosProrrogaOficio(), event.getFile().getFileName())) {
            cargarTablaAnexosProrrogaOficio(event.getFile());
        }
    }

    public void eliminarAnexoProrrogaOficio() {
        eliminarDocumentoIdenticoAnexosListaProrrogaOficio(getListaAnexosProrrogaOficio());
    }

    public void eliminarDocumentoIdenticoAnexosListaProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOficio> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOficio anexo = iter.next();
            if (anexo == getAnexoProrrogaOficioSeleccionado()) {
                iter.remove();
            }
        }
    }

    // PRORROGA
    // ORDEN
    // RECHAZO*****************************************************************************
    public final List<FecetAnexosProrrogaOficio> cargarTablaAnexosProrrogaOficioRechazo(final UploadedFile file,
            final List<FecetAnexosProrrogaOficio> listaAnexos) {
        FecetAnexosProrrogaOficio anexo = new FecetAnexosProrrogaOficio();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void cargarTablaAnexosProrrogaOficioRechazo(UploadedFile file) {
        setListaAnexosProrrogaOficio(cargarTablaAnexosProrrogaOficioRechazo(file, getListaAnexosProrrogaOficioRechazo()));
    }

    /**
     * Metodo utilizado para guardar el documento dentro del fileSystem y
     * regresar los datos y respuesta del almacenamiento.
     *
     * @param event
     *            Evento de carga temporal de archivo.
     */
    public void cargaAnexosProrrogaOficioRechazo(final FileUploadEvent event) {
        if (validaArchivoCargaWordExcelPdf(event.getFile())
                && !validaArchivoDuplicadoAnexosProrrogaOficio(getListaAnexosProrrogaOficioRechazo(), event.getFile().getFileName())) {
            cargarTablaAnexosProrrogaOficioRechazo(event.getFile());
        }
    }

    public void eliminarAnexoProrrogaOficioRechazo() {
        eliminarDocumentoIdenticoAnexosListaProrrogaOficioRechazo(getListaAnexosProrrogaOficioRechazo());
    }

    public void eliminarDocumentoIdenticoAnexosListaProrrogaOficioRechazo(final List<FecetAnexosProrrogaOficio> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOficio> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOficio anexo = iter.next();
            if (anexo == getAnexoProrrogaOficioSeleccionadoRechazo()) {
                iter.remove();
            }
        }
    }

    public void guardarFlujoProrrogaAprobada() {
        try {
            if (getListaResolucionProrrogaOficio() != null && !getListaResolucionProrrogaOficio().isEmpty()) {
                if (getFecetFlujoProrrogaOficio().getJustificacion() != null && !getFecetFlujoProrrogaOficio().getJustificacion().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoProrrogaOficioAprobadaAuditor(getProrrogaOficioSeleccionada(), getFecetFlujoProrrogaOficio(),
                            getListaAnexosProrrogaOficio());
                    getProrrogaOficioSeleccionada().setIdAuditor(getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucionOficio(getProrrogaOficioSeleccionada(), getListaResolucionProrrogaOficio());
                    getAuditoriaService().aprobarProrrogaOficioAuditor(getOficioSeleccionado());                    
                    getSeguimientoOrdenesService().enviarCorreoProrrogaAprobada(getOrdenSeleccionada(), getProrrogaOficioSeleccionada());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarAutorizarProrrogaAuditor').hide();");
                } else {
                    addErrorMessage("msgProrrogaAprobadaError", FacesUtil.getMessageResourceString("error.aprobado.orden.sin.descripcion"), "");
                }

            } else {
                addErrorMessage("msgProrrogaAprobadaError", getMessageResourceString(MENSAJE_ERROR_CARGA_RESOLUCION), "");
            }
            RequestContext.getCurrentInstance().execute("PF('confirmarAprobarProrroga').hide();");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:pnlProrroga");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:messagesProrrogaAprobada");
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgProrrogaAprobadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute("PF('confirmarAprobarProrroga').hide();");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:messagesProrrogaAprobada");
        }
    }

    public void guardarFlujoProrrogaRechazada() {
        try {
            if (getListaResolucionProrrogaOficioRechazo() != null && !getListaResolucionProrrogaOficioRechazo().isEmpty()) {
                if (getFecetFlujoProrrogaOficioRechazo().getJustificacion() != null
                        && !getFecetFlujoProrrogaOficioRechazo().getJustificacion().trim().equals("")) {
                    getSeguimientoOrdenesService().guardarFlujoProrrogaOficioRechazadaAuditor(getProrrogaOficioSeleccionada(),
                            getFecetFlujoProrrogaOficioRechazo(), getListaAnexosProrrogaOficioRechazo());
                    getProrrogaOficioSeleccionada().setIdAuditor(getOrdenSeleccionada().getIdAuditor());
                    getSeguimientoOrdenesService().actuallizarResolucionOficio(getProrrogaOficioSeleccionada(), getListaResolucionProrrogaOficioRechazo());
                    getAuditoriaService().noAprobarProrrogaOficioAuditor(getOficioSeleccionado());
                    getSeguimientoOrdenesService().enviarCorreoProrrogaRechazada(getOrdenSeleccionada(), getProrrogaOficioSeleccionada());
                    addMessage(null, getMessageResourceString(MENSAJE_EXITO_CARGA_OFICIO), "");
                    setRecargarInformacion(true);
                    init();
                    RequestContext.getCurrentInstance().execute("PF('confirmarRechazarProrrogaAuditor').hide();");
                } else {
                    addErrorMessage("msgProrrogaRechazadaError", FacesUtil.getMessageResourceString("error.rechazo.orden.sin.descripcion"), "");
                }
            } else {
                addErrorMessage("msgProrrogaRechazadaError", getMessageResourceString(MENSAJE_ERROR_CARGA_RESOLUCION), "");
            }
            RequestContext.getCurrentInstance().execute("PF('confirmarRechazarProrroga').hide();");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:pnlProrroga");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:messagesProrrogaRechazada");
        } catch (NegocioException e) {
            logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
            addErrorMessage("msgProrrogaRechazadaError", e.getMessage(), "");
            RequestContext.getCurrentInstance().execute("PF('confirmarRechazarProrroga').hide();");
            RequestContext.getCurrentInstance().update("formDocumentacionOficio:messagesProrrogaRechazada");
        }
    }

    public void handleFileUploadPromocion(FileUploadEvent event) {
        try {
            FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
            final Date fecha = new Date();
            setArchivoPromocionOficio(event.getFile());
            FecetPromocionOficio dto = new FecetPromocionOficio();
            dto.setFechaCarga(new Date());
            dto.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(OrdenesDocumentacionArchivoUtil.aplicarCodificacionTexto(getArchivoPromocionOficio().getFileName())));

            dto.setArchivo(event.getFile().getInputstream());
            setListaPromocionesOficioCargadas(new ArrayList<FecetPromocionOficio>());
            archivoTemp.setSessionUUID(getRFCSession());
            archivoTemp.setArchivoByte(getArchivoPromocionOficio().getContents());
            archivoTemp.setFecha(fecha);

            dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));

            getListaPromocionesOficioCargadas().add(dto);
            addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
        } catch (IOException e) {
            logger.error("Error al cargar la promocion: " + e.getCause(), e);
        }
    }

    public void limpiarPromocion() {
        if (getListaPromocionesOficioCargadas() != null) {
            getListaPromocionesOficioCargadas().clear();
            setListaPromocionesOficioCargadas(new ArrayList<FecetPromocionOficio>());
            setArchivoPromocionOficio(null);

            getListaPruebasAlegatosOficioCargadas().clear();
            setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
            setArchivoPruebasAlegatosOficio(null);
        }

    }

    public Boolean validaArchivoDuplicadoAlegatoOficio(final List<FecetAlegatoOficio> listaAlegatos, final String nombreArchivo) {
        Boolean archivoDuplicado = false;
        for (FecetAlegatoOficio documento : listaAlegatos) {

            if (documento.getNombreArchivo().equals(nombreArchivo)) {
                archivoDuplicado = true;
                addErrorMessage(null, "Archivo ya existente en la lista de documentos, verifique por favor", "");
                break;
            }
        }

        return archivoDuplicado;
    }

    public void handleFileUploadPruebasAlegatos(FileUploadEvent event) {
        setArchivoPruebasAlegatosOficio(event.getFile());
        FecetAlegatoOficio dto = new FecetAlegatoOficio();
        FecetArchivoTemp archivoTemp = new FecetArchivoTemp();
        final Date fecha = new Date();
        dto.setFechaCarga(fecha);
        dto.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(OrdenesDocumentacionArchivoUtil.aplicarCodificacionTexto(getArchivoPruebasAlegatosOficio().getFileName())));

        if (!validaArchivoDuplicadoAlegatoOficio(getListaPruebasAlegatosOficioCargadas(), dto.getNombreArchivo())) {
            if (getListaPruebasAlegatosOficioCargadas() == null || getListaPruebasAlegatosOficioCargadas().isEmpty()) {
                setListaPruebasAlegatosOficioCargadas(new ArrayList<FecetAlegatoOficio>());
            }
            try {
                dto.setArchivo(getArchivoPruebasAlegatosOficio().getInputstream());
                archivoTemp.setSessionUUID(getRFCSession());
                archivoTemp.setArchivoByte(getArchivoPruebasAlegatosOficio().getContents());
                archivoTemp.setFecha(fecha);
                dto.setIdArchivoTemp(getArchivoTempService().insertaArchivoTemp(archivoTemp));
                getListaPruebasAlegatosOficioCargadas().add(dto);
                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }

        }
    }

    public void descartarPruebaLista() {
        List<FecetAlegatoOficio> listaNueva = new ArrayList<FecetAlegatoOficio>();
        for (FecetAlegatoOficio alegato : getListaPruebasAlegatosOficioCargadas()) {
            if (!alegato.getNombreArchivo().equals(getPruebaAlegatoSeleccionada().getNombreArchivo())) {
                listaNueva.add(alegato);
            }
        }
        setListaPruebasAlegatosOficioCargadas(listaNueva);
    }

    public void getPruebasAlegatosPromocion() {
        setListaPruebasAlegatos(getCargarFirmaPruebasPromoService().getPruebasAlegatosPromocionOficio(getPromocionSeleccionada().getIdPromocionOficio()));
        setPanelDocumentacionVisible(true);
        logger.debug("oficios obtenidos [{}]", getListaPruebasAlegatos().size());
    }

    public void cierraVentana(CloseEvent event) {
        setPanelDocumentacionVisible(false);
    }

    public void cargandoDatosDocumentacion() {
        try {
            getCargarFirmaPruebasPromoService().guardarPromocionPruebasAlegatosOficioAuditor(getListaPromocionesOficioCargadas(),
                    getListaPruebasAlegatosOficioCargadas(), getOficioSeleccionado());
            addMessage(null, getMessageResourceString("label.archivo.guardado.exitosamente"), "");
            setListaPromociones(getSeguimientoOrdenesService().getPromocionContadorPruebasAlegatosOficio(getOficioSeleccionado()));
            setRecargarInformacion(true);
            init();
        } catch (NegocioException e) {
            addErrorMessage(null, e.getMessage(), "");
        }
    }

    public void getAnexosProrrogaOficio() {
        setListaAnexosProrrogaOficio(getSeguimientoOrdenesService()
                .getAnexosProrrogaOficioHistorico(getProrrogaOficioSeleccionada().getFecetFlujoProrrogaOficio().getIdFlujoProrrogaOficio()));
    }

    public void cargaResolucionProrrogaOficioRechazo(final FileUploadEvent event) {
        if (validaArchivoCargaWord(event.getFile())
                && !validaArchivoResolucionProrrogaOficio(getListaResolucionProrrogaOficioRechazo(), event.getFile().getFileName())) {
            cargarTablaResolucionProrrogaOficioRechazo(event.getFile());
        }
    }

    public void cargarTablaResolucionProrrogaOficioRechazo(UploadedFile file) {
        setListaResolucionProrrogaOficioRechazo(cargarTablaResolucionProrrogaOficioRechazo(file, getListaResolucionProrrogaOficioRechazo()));
    }

    public final List<FecetAnexosProrrogaOficio> cargarTablaResolucionProrrogaOficioRechazo(final UploadedFile file,
            final List<FecetAnexosProrrogaOficio> listaAnexos) {
        FecetAnexosProrrogaOficio anexo = new FecetAnexosProrrogaOficio();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void cargaResolucionProrrogaOficio(final FileUploadEvent event) {
        if (validaArchivoCargaWord(event.getFile())
                && !validaArchivoResolucionProrrogaOficio(getListaResolucionProrrogaOficio(), event.getFile().getFileName())) {
            cargarTablaResolucionProrrogaOficio(event.getFile());
        }
    }

    public void cargarTablaResolucionProrrogaOficio(UploadedFile file) {
        setListaResolucionProrrogaOficio(cargarTablaResolucionProrrogaOficio(file, getListaResolucionProrrogaOficio()));
    }

    public final List<FecetAnexosProrrogaOficio> cargarTablaResolucionProrrogaOficio(final UploadedFile file,
            final List<FecetAnexosProrrogaOficio> listaAnexos) {
        FecetAnexosProrrogaOficio anexo = new FecetAnexosProrrogaOficio();
        final Date fecha = new Date();

        if (file != null) {
            try {
                anexo.setFechaCreacion(fecha);
                anexo.setNombreArchivo(OrdenesDocumentacionArchivoUtil.limpiarPathArchivo(file.getFileName()));
                anexo.setArchivo(file.getInputstream());
                listaAnexos.add(anexo);

                addMessage(null, Constantes.CARGA_EXITOSA_DOC, "");
            } catch (IOException e) {
                logger.error(Constantes.FALLA_CARGA + e.getCause(), e);
                addErrorMessage(null, Constantes.FALLA_CARGA, "");
            }
        }

        return listaAnexos;
    }

    public void eliminarResolucionProrrogaOficio() {
        eliminarResolucionListaProrrogaOficio(getListaResolucionProrrogaOficio());
    }

    public void eliminarResolucionListaProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOficio> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOficio anexo = iter.next();
            if (anexo.equals(getAnexoProrrogaOficioSeleccionado())) {
                iter.remove();
            }
        }
    }

    public void eliminarResolucionProrrogaOficioRechazo() {
        eliminarResolucionListaProrrogaOficioRechazo(getListaResolucionProrrogaOficioRechazo());
    }

    public void eliminarResolucionListaProrrogaOficioRechazo(final List<FecetAnexosProrrogaOficio> listaAnexos) {
        for (Iterator<FecetAnexosProrrogaOficio> iter = listaAnexos.iterator(); iter.hasNext();) {
            final FecetAnexosProrrogaOficio anexo = iter.next();
            if (anexo.equals(getAnexoProrrogaOficioSeleccionadoRechazo())) {
                iter.remove();
            }
        }
    }

    public void getDocumentacionRechazoProrroga() {
        setListaDocumentosRechazoProrroga(getSeguimientoOrdenesService()
                .getAnexosRechazoProrrogaOficio(getProrrogaOficioSeleccionada().getFecetFlujoProrrogaOficio().getIdFlujoProrrogaOficio()));
    }

    public void fileUploadPapelesTrabajoOficio(FileUploadEvent event) {

        if (validaArchivoCargaWordExcelPdfIdMessage(event.getFile(), "msgPapelesTrabajoIdOficio")) {
            UploadedFile archivoPapelTrabajo = event.getFile();
            cargarTablaPapelTrabajo(archivoPapelTrabajo, "msgPapelesTrabajoIdOficio");
        }

    }

    public void cargarTablaPapelTrabajo(UploadedFile archivoPapelTrabajo, String idMensaje) {
        PapelesTrabajo papel = new PapelesTrabajo();
        llenarPapelTrabajo(papel, archivoPapelTrabajo);
        int numeroDocumentoPapelTrabajo = 0;

        List<DocumentoOrdenModel> listaPapelTrabajo = getListaPapelesTrabajoOficio();
        numeroDocumentoPapelTrabajo = getNumeroDocumentoPapelTrabajoOficio();
        if (!duplicidadPapelTrabajo(papel, listaPapelTrabajo)) {
            FecetArchivoTemp fecetArchivoTemp = new FecetArchivoTemp();
            fecetArchivoTemp.setArchivo(papel.getNombreArchivo());
            fecetArchivoTemp.setSessionUUID(getRFCSession());
            try {
                DocumentoOrdenModel papelTrabajo = new DocumentoOrdenModel();
                papelTrabajo.setIsEliminar(true);
                fecetArchivoTemp.setArchivoByte(IOUtils.toByteArray(archivoPapelTrabajo.getInputstream()));
                papelTrabajo.setIdDocumentoTemporal(getArchivoTempService().insertaArchivoTemp(fecetArchivoTemp));
                papelTrabajo.setPapelesTrabajo(papel);
                setListaPapelesTrabajoOficio(ordenarListaPapelTrabajo(papelTrabajo, listaPapelTrabajo));
                setNumeroDocumentoPapelTrabajoOficio(numeroDocumentoPapelTrabajo + 1);
                addMessage(idMensaje, Constantes.ADJUNTAR_ARCHIVO, papel.getNombreArchivo());
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        } else {
            addErrorMessage(idMensaje, "El archivo ya fue cargado", "");
        }
    }

}
