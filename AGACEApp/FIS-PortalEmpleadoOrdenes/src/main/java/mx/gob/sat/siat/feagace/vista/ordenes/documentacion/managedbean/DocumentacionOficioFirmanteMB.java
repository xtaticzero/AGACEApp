package mx.gob.sat.siat.feagace.vista.ordenes.documentacion.managedbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.DocumentoVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.vista.ordenes.documentacion.DocumentacionOficioFirmanteMBAbstract;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "documentacionOficioFirmanteMB")
@SessionScoped
public class DocumentacionOficioFirmanteMB extends DocumentacionOficioFirmanteMBAbstract {

    @SuppressWarnings("compatibility:-6487877761133449993")
    private static final long serialVersionUID = 143567L;

    @PostConstruct
    public void init() {
        logger.debug("::::::::::::::INIT:::::::::::::::::::::::::::.Documentacion Oficio");
        setRenderBtnFirmaProrroga(false);
        setRenderBtnFirmaOficio(false);
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
        setMotivoRechazo("");
        setFirmadoProrroga(false);
        setListaPromocionesOficio(new ArrayList<FecetPromocionOficio>());
        setListaProrrogasOficioFirmadas(new ArrayList<FecetProrrogaOficio>());
        setListaProrrogasOficioPendientes(new ArrayList<FecetProrrogaOficio>());
    }

    public void limpiar() {
        setMotivoRechazo("");
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
        setProrrogaOficioSeleccionada(new FecetProrrogaOficio());
    }

    public void cargaPromocion() {
        logger.debug("idOficio {} ", getOficioSeleccionado());
        if (getListaPromocionesOficio().isEmpty()) {
            setListaPromocionesOficio(
                    getDocumentacionOficioFirmanteService().getPruebasAlegatosOficio(getOficioSeleccionado()));
        }
    }

    public void getDocumentacionContribuyentePromocion() {
        setListAlegatosOficio(new ArrayList<FecetAlegatoOficio>());
        setListAlegatosOficio(
                getDocumentacionOficioFirmanteService().getPruebasAlegatosPromocion(getPromocionOficioSeleccionado().getIdPromocionOficio()));
        setIdPromocionOficioSeleccionada(getPromocionOficioSeleccionado().getIdPromocionOficio());
        setFechaEnvioPromocionOficioSeleccionada(getPromocionOficioSeleccionado().getFechaCarga());
        RequestContext.getCurrentInstance().execute("PF('dialogAlegatosAnexos').show();");
    }

    public void cargaProrrogasOficio() {
        if (getListaProrrogasOficioFirmadas().isEmpty() || getListaProrrogasOficioPendientes().isEmpty()) {

            setListaProrrogasOficioFirmadas(
                    getDocumentacionOficioFirmanteService().getProrrogasFirmadas(getOficioSeleccionado().getIdOficio()));
            setListaProrrogasOficioPendientes(
                    getDocumentacionOficioFirmanteService().getProrrogasPendientes(getOficioSeleccionado().getIdOficio()));
        }
    }

    /**
     * @param event
     */
    public void validaSelectRadioOficioProrroga(SelectEvent event) {
        if (getProrrogaOficioSeleccionada().getIdProrrogaOficio() != null) {
            setFirmadoProrroga(true);
        } else {
            setFirmadoProrroga(false);
        }
    }

    /**
     * Dialogs
     */
    /**
     * Prorroga Anexos de resolucion
     */
    public void getAnexosResolucionProrroga() {
        setListaAnexosProrrogaOficio(new ArrayList<FecetAnexosProrrogaOficio>());
        setListaAnexosProrrogaOficio(getDocumentacionOficioFirmanteService().getAnexosProrrogaOficio(getProrrogaOficioFirmadaSeleccionada()));
        RequestContext.getCurrentInstance().execute("PF('dialogAnexoResolucion').show();");
        RequestContext.getCurrentInstance().update("documentacionOficioFirmanteMB:panelAnexosOficioProrrogaFirmada");
    }

    public void getDocumentacionContibuyenteProrroga() {
        setListaDocumentacionProrrogaOficio(new ArrayList<FecetDocProrrogaOficio>());
        setListaDocumentacionProrrogaOficio(
                getDocumentacionOficioFirmanteService().getDocumentacionProrrogaContribuyenteOficio(getProrrogaOficioFirmadaSeleccionada().getIdProrrogaOficio()));

    }

    /**
     * Incia Metodos para firmar y rechazar Prorroga Oficio
     *
     * Redirecciona a la firma para firmar la prorroga
     * @return 
     */
    public String enviaFirmarProrroga() {
        if (getProrrogaOficioSeleccionada() != null) {
            getProrrogaOficioSeleccionada().setOrden(getFirmanteSeguimientoMB().getOrdenSeleccionada());
            getProrrogaOficioSeleccionada().setOficio(getOficioSeleccionado());
            getSession().setAttribute("prorrogaOrdenSeleccionada", getProrrogaOficioSeleccionada());
            limpiar();
            init();
            return FIRMA_PRORROGA_APPLET;
        } else {
            addErrorMessage(null, "Debe seleccionar por lo menos un oficio, verifique por favor.");
            return null;
        }

    }

    /**
     * @return
     */
    public String rechazarFirmadoProrrogaOficio() {
        logger.debug("::::::::: ProrrogaOficioSeleccionada :::::: {} ", getProrrogaOficioSeleccionada());
        HttpServletRequest request
                = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String motivoFirmanteRechazo;
        motivoFirmanteRechazo = request.getParameter(PARAM_DESCRIPCION_RECHAZO_PRORROGA);
        logger.debug(motivoFirmanteRechazo);
        if (motivoFirmanteRechazo != null && !motivoFirmanteRechazo.trim().equals("")) {
            logger.debug(":: getListaDocumentoUpLoad() ::: ", getListaDocumentoUpLoad());   
            getProrrogaOficioSeleccionada().setOrden(getFirmanteSeguimientoMB().getOrdenSeleccionada());
            getDocumentacionOficioFirmanteService().rechazaFirmaProrrogaFirmante(getProrrogaOficioSeleccionada(),
                    motivoFirmanteRechazo,
                    EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus(),
                    getListaDocumentoUpLoad(), getOficioSeleccionado());
            RequestContext.getCurrentInstance().execute("PF('motivoRechazoProrroga').hide();");
            RequestContext.getCurrentInstance().execute("PF('exitoRechazoProrroga').show();");
            init();
        } else {
            addErrorMessage("El campo de Motivo es obligatorio. ");
        }

        return RETURN_INDEX_OFICIO;
    }

    public void validarRechazoProrroga() {
        RequestContext.getCurrentInstance().execute("PF('motivoRechazoProrroga').show();");
    }

    /**
     * Valida Documentos Cargados.
     */
    public boolean validaDocumentos() {
        if (getListaDocumentoUpLoad().isEmpty()) {
            addErrorMessage(null, "Debe seleccionar por lo menos un documento, verifique por favor.");
            RequestContext.getCurrentInstance().execute("PF('motivoRechazoProrroga').show();");
            return false;
        }
        return true;
    }

    /**
     * @return String
     */
    public String cancelUpLoad() {
        limpiar();
        return RETURN_INDEX_OFICIO;
    }

    /**
     * @return String
     */
    public String navegaReturn() {
        setProrrogaOficioSeleccionada(new FecetProrrogaOficio());
        limpiarVariables();
        return "documentacionOrden?faces-redirect=true";
    }

    public void limpiarVariables() {
        setRenderBtnFirmaProrroga(false);
        setRenderBtnFirmaOficio(false);
        setListaDocumentoUpLoad(new ArrayList<DocumentoVO>());
        setMotivoRechazo("");
        setFirmadoProrroga(false);
        setListaPromocionesOficio(new ArrayList<FecetPromocionOficio>());
        setListaProrrogasOficioFirmadas(new ArrayList<FecetProrrogaOficio>());
        setListaProrrogasOficioPendientes(new ArrayList<FecetProrrogaOficio>());
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        logger.debug("contentType {} ", event.getFile().getContentType());
        if (OrdenesDocumentacionArchivoUtil.validaContentType(event.getFile().getContentType())) {
            DocumentoVO docAdjunto = new DocumentoVO();
            docAdjunto.setNombreArchivo(event.getFile().getFileName());
            docAdjunto.setContenido(event.getFile().getContents());
            docAdjunto.setInputStream(event.getFile().getInputstream());
            docAdjunto.setContentType(event.getFile().getContentType());
            docAdjunto.setTamanio(event.getFile().getSize());

            for (Iterator<DocumentoVO> iter = getListaDocumentoUpLoad().listIterator(); iter.hasNext();) {
                DocumentoVO a = iter.next();
                if (a.getNombreArchivo().equalsIgnoreCase(docAdjunto.getNombreArchivo())) {
                    logger.error("Documento duplicado ... ");
                    addErrorMessage(null, "Documento Duplicado");
                    iter.remove();
                }
            }
            getListaDocumentoUpLoad().add(docAdjunto);
        } else {
            addErrorMessage(null, "Solo se aceptan archivos tipo Word, Excel o PDF versi\u00f3n 2007 o superior.");
        }
    }

    public void eliminaDocumentoAdjunto() {
        for (Iterator<DocumentoVO> iter = getListaDocumentoUpLoad().iterator(); iter.hasNext();) {
            final DocumentoVO doc = iter.next();
            if (doc.getNombreArchivo().equals(getDocEliminarSeleccionado().getNombreArchivo())) {
                iter.remove();
            }
        }
    }

}
