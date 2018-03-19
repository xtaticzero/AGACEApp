package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.RutaArchivosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.constantes.DateUtil;
import mx.gob.sat.siat.feagace.negocio.util.constantes.FormatosFechasEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.RutaArchivosUtilOrdenes;
import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.UploadedFile;

public abstract class DocumentacionOficioMBSubAbstract extends DocumentacionAttributesAbstarct {

    private static final long serialVersionUID = -186291372740322208L;

    private boolean panelDocumentacionVisible;
    private boolean prorrogaVisible;
    private boolean sinMediosCompulsa;
    private int numeroDocumentoPapelTrabajoOficio;
    private Date fechaCargaPapelTrabajo;

    private FecetProrrogaOficio prorrogaOficioSeleccionada;
    private transient StreamedContent archivoDescargaProrroga;
    private transient StreamedContent archivoDescargaResolucionProrrogaOficio;
    private transient StreamedContent archivoDescargaAcuseProrrogaOficio;

    public void getDocumentacionProrrogaContribuyente() {
        setListaDocumentosProrroga(getSeguimientoOrdenesService()
                .getDocumentacionContribuyenteProrrogaOficio(getProrrogaOficioSeleccionada().getIdProrrogaOficio()));
    }

    public boolean isPanelDocumentacionVisible() {
        return panelDocumentacionVisible;
    }

    public void setPanelDocumentacionVisible(boolean panelDocumentacionVisible) {
        this.panelDocumentacionVisible = panelDocumentacionVisible;
    }

    public boolean isProrrogaVisible() {
        return prorrogaVisible;
    }

    public void setProrrogaVisible(boolean prorrogaVisible) {
        this.prorrogaVisible = prorrogaVisible;
    }

    public Date getFechaCargaPapelTrabajo() {
        return (fechaCargaPapelTrabajo != null) ? (Date) fechaCargaPapelTrabajo.clone() : null;
    }

    public void setFechaCargaPapelTrabajo(Date fechaCargaPapelTrabajo) {
        this.fechaCargaPapelTrabajo = fechaCargaPapelTrabajo != null ? new Date(fechaCargaPapelTrabajo.getTime()) : null;
    }

    public int getNumeroDocumentoPapelTrabajoOficio() {
        return numeroDocumentoPapelTrabajoOficio;
    }

    public void setNumeroDocumentoPapelTrabajoOficio(int numeroDocumentoPapelTrabajoOficio) {
        this.numeroDocumentoPapelTrabajoOficio = numeroDocumentoPapelTrabajoOficio;
    }

    public boolean isSinMediosCompulsa() {
        return sinMediosCompulsa;
    }

    public void setSinMediosCompulsa(boolean sinMediosCompulsa) {
        this.sinMediosCompulsa = sinMediosCompulsa;
    }

    public StreamedContent getArchivoDescargaPapelTrabajo() {

        if (getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo() == null) {
            InputStream myInputStream = new ByteArrayInputStream(
                    getArchivoTempService().consultaArchivoTemp(getPapelTrabajoSeleccionado().getIdDocumentoTemporal(), getRFCSession()));
            return new DefaultStreamedContent(myInputStream, "application/octet-stream", getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
        }

        return getArchivoGenerico(getPapelTrabajoSeleccionado().getPapelesTrabajo().getRutaArchivo(),
                getPapelTrabajoSeleccionado().getPapelesTrabajo().getNombreArchivo());
    }

    private StreamedContent getArchivoGenerico(final String path, final String nombre) {
        String nuevoPath = "";
        if (path != null && nombre != null) {
            nuevoPath = path.replace(nombre, "").concat(nombre);
        }
        return getDescargaArchivo(nuevoPath, nombre);
    }

    public void setArchivoDescargaProrroga(StreamedContent archivoDescargaProrroga) {
        this.archivoDescargaProrroga = archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaProrroga() {
        archivoDescargaProrroga = getDescargaArchivo(prorrogaOficioSeleccionada.getRutaAcuse(),
                prorrogaOficioSeleccionada.getNombreAcuse());

        return archivoDescargaProrroga;
    }

    public StreamedContent getArchivoDescargaResolucionProrrogaOficio() {
        this.archivoDescargaResolucionProrrogaOficio = getDescargaArchivo(
                prorrogaOficioSeleccionada.getRutaResolucion(), prorrogaOficioSeleccionada.getNombreResolucion());

        return archivoDescargaResolucionProrrogaOficio;
    }

    public void setArchivoDescargaResolucionProrrogaOficio(StreamedContent archivoDescargaResolucionProrrogaOficio) {
        this.archivoDescargaResolucionProrrogaOficio = archivoDescargaResolucionProrrogaOficio;
    }

    public void setProrrogaOficioSeleccionada(final FecetProrrogaOficio prorrogaOficioSeleccionada) {
        this.prorrogaOficioSeleccionada = prorrogaOficioSeleccionada;
    }

    public FecetProrrogaOficio getProrrogaOficioSeleccionada() {
        return prorrogaOficioSeleccionada;
    }

    public void setArchivoDescargaAcuseProrrogaOficio(StreamedContent archivoDescargaAcuseProrrogaOficio) {
        this.archivoDescargaAcuseProrrogaOficio = archivoDescargaAcuseProrrogaOficio;
    }

    public StreamedContent getArchivoDescargaAcuseProrrogaOficio() {
        this.archivoDescargaAcuseProrrogaOficio = getDescargaArchivo(getProrrogaOficioSeleccionada().getRutaAcuse(),
                getProrrogaOficioSeleccionada().getNombreAcuse());

        return this.archivoDescargaAcuseProrrogaOficio;
    }

    protected List<DocumentoOrdenModel> ordenarListaPapelTrabajo(DocumentoOrdenModel papelTrabajo, List<DocumentoOrdenModel> listaPapelTrabajo) {
        List<DocumentoOrdenModel> listaPapelTrabajoAgregado = new ArrayList<DocumentoOrdenModel>();
        listaPapelTrabajoAgregado.add(papelTrabajo);
        listaPapelTrabajoAgregado.addAll(listaPapelTrabajo);
        return listaPapelTrabajoAgregado;
    }

    protected void llenarPapelTrabajo(PapelesTrabajo papel, UploadedFile archivoPapelTrabajo) {
        papel.setFechaCreacion(new Date());
        papel.setNombreArchivo(CargaArchivoUtilOrdenes.limpiarPathArchivo(CargaArchivoUtilOrdenes.aplicarCodificacionTexto(archivoPapelTrabajo.getFileName())));
        papel.setBlnActivo(BigDecimal.ONE);
        papel.setIdPropuesta(getOrdenSeleccionada().getIdPropuesta());
        papel.setIdOrden(getOrdenSeleccionada().getIdOrden());
        papel.setIdOficio(getOficioSeleccionado().getId());
        papel.setIdTipoOficio(getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio());
    }

    public void cargarPapelesTrabajoOficio() {
        setListaPapelesTrabajoOficio(new ArrayList<DocumentoOrdenModel>());
        List<PapelesTrabajo> listaPapelTrabajo = getConsultarPapelesTrabajoService().getPapelesOnlyIdOfcio(getOficioSeleccionado().getId());

        if (listaPapelTrabajo != null) {
            for (PapelesTrabajo papel : listaPapelTrabajo) {
                DocumentoOrdenModel documento = new DocumentoOrdenModel();
                documento.setPapelesTrabajo(papel);
                getListaPapelesTrabajoOficio().add(documento);
            }

            for (DocumentoOrdenModel papel : getListaPapelesTrabajoOficio()) {
                if (DateUtils.truncatedCompareTo(getOficioSeleccionado().getFechaFirma(), papel.getPapelesTrabajo().getFechaCreacion(), Calendar.MILLISECOND) < 0) {
                    papel.setIsEliminar(true);
                }
            }

        }
    }

    public void eliminarPapelTrabajoOficio() {
        DocumentoOrdenModel papelTrabajo = getPapelTrabajoSeleccionado();

        if (papelTrabajo.getPapelesTrabajo().getIdPapelesTrabajo() == null && getListaPapelesTrabajoOficio().contains(papelTrabajo)) {
            getListaPapelesTrabajoOficio().remove(papelTrabajo);
            addMessage("msgPapelesTrabajoOficio", "Se realiz\u00f3 la eliminaci\u00f3n del documento ", papelTrabajo.getPapelesTrabajo().getNombreArchivo());
            setNumeroDocumentoPapelTrabajoOficio(getNumeroDocumentoPapelTrabajoOficio() - 1);
        }

        if (papelTrabajo.getPapelesTrabajo().getIdPapelesTrabajo() != null && getListaPapelesTrabajoOficio().contains(papelTrabajo)) {
            getListaPapelesTrabajoOficio().remove(papelTrabajo);
            papelTrabajo.getPapelesTrabajo().setBlnActivo(BigDecimal.ZERO);
            papelTrabajo.getPapelesTrabajo().setFechaFin(new Date());
            getConsultarPapelesTrabajoService().actualizarPapelTrabajo(papelTrabajo.getPapelesTrabajo());
            addMessage("msgPapelesTrabajoOficio", "Se realiz\u00f3 la eliminaci\u00f3n del documento ", papelTrabajo.getPapelesTrabajo().getNombreArchivo());
        }

    }

    public void guardarPapeleTrabajoOficio() {
        try {
            String carpetaPapelTrabajo = Constantes.CARPETAPAPELTRABAJO;
            String carpetaUnica = (DateUtil.getFormattedDate(FormatosFechasEnum.FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM, new Date()));
            for (DocumentoOrdenModel papel : getListaPapelesTrabajoOficio()) {
                if (papel.getPapelesTrabajo().getIdPapelesTrabajo() == null) {
                    StringBuilder carpetaOfico = new StringBuilder();
                    carpetaOfico.append("Oficio_").append(getOficioSeleccionado().getFecetTipoOficio().getIdTipoOficio());

                    papel.getPapelesTrabajo().setRutaArchivo(RutaArchivosUtilOrdenes.generarRutaArchivoValida(RutaArchivosEnum.RUTA_DOCUMENTOS_PAPELES_TRABAJO_ORDENES,
                            getOrdenSeleccionada().getNumeroOrden(), carpetaPapelTrabajo, carpetaOfico.toString(), carpetaUnica)
                            + papel.getPapelesTrabajo().getNombreArchivo());

                    getConsultarPapelesTrabajoService().insertaPapelesTrabajo(papel.getPapelesTrabajo());
                    InputStream myInputStream = new ByteArrayInputStream(
                            getArchivoTempService().consultaArchivoTemp(papel.getIdDocumentoTemporal(), getRFCSession()));
                    papel.setInput(myInputStream);
                    getConsultarPapelesTrabajoService().guardarPapelTrabajo(papel);
                }
            }
            addMessage("msgPapelesTrabajoIdOficio", "Se han guardado exitosamente los papeles de trabajo en el sistema.", "");
            setNumeroDocumentoPapelTrabajoOficio(0);
            cargarPapelesTrabajoOficio();
        } catch (IOException e) {
            addMessage("msgPapelesTrabajoIdOficio", e.getMessage(), "");
        }
    }

}
