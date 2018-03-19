/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.negocio.util.constantes.CargaArchivoUtilOrdenes;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocumentacionAttributesAbstarct extends DocumentacionOficioMBAbstract {

    private static final long serialVersionUID = 3119976181687226154L;

    protected static final int SIZE_BUFFER = 1024;

    private transient UploadedFile ofMulta;
    private transient UploadedFile anexoOfMulta;
    private transient UploadedFile ofAvisoContribuyente;
    private transient UploadedFile archivoPromocionOficio;
    private transient UploadedFile archivoPruebasAlegatosOficio;
    private transient StreamedContent archivoDescargaAnexoProrrogaOficio;
    private transient StreamedContent anexoSeleccionDescarga;
    private transient StreamedContent oficioSeleccionDescarga;
    private transient StreamedContent promocionOficioSeleccionadaDescarga;
    private transient StreamedContent pruebaOficioSeleccionadaDescarga;

    private List<FecetOficio> listaOfMulta;
    private List<FecetOficioAnexos> listaAnexosMulta;
    private List<FecetOficio> listaOfAvisoContribuyente;
    private List<FecetOficioAnexos> listaAnexosAvisoContribuyente;
    private List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficioRechazo;
    private List<FecetAnexosProrrogaOficio> listaResolucionProrrogaOficio;
    private List<FecetAnexosProrrogaOficio> listaResolucionProrrogaOficioRechazo;
    private List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio;
    private List<FecetPromocionOficio> listaPromocionesOficioCargadas;
    private List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas;
    private List<DocumentoOrdenModel> listaPapelesTrabajoOficio;

    private FecetOficioAnexos anexoSeleccionado;
    private FecetFlujoProrrogaOficio fecetFlujoProrrogaOficioRechazo;
    private FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionadoRechazo;
    private FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio;
    private FecetPromocionOficio promocionOficioSeleccionada;
    private DocumentoOrdenModel papelTrabajoSeleccionado;

    public DocumentoOrdenModel getPapelTrabajoSeleccionado() {
        return papelTrabajoSeleccionado;
    }

    public void setPapelTrabajoSeleccionado(DocumentoOrdenModel papelTrabajoSeleccionado) {
        this.papelTrabajoSeleccionado = papelTrabajoSeleccionado;
    }

    public void setListaResolucionProrrogaOficio(List<FecetAnexosProrrogaOficio> listaResolucionProrrogaOficio) {
        this.listaResolucionProrrogaOficio = listaResolucionProrrogaOficio;
    }

    public List<FecetAnexosProrrogaOficio> getListaResolucionProrrogaOficioRechazo() {
        return listaResolucionProrrogaOficioRechazo;
    }

    public void setListaResolucionProrrogaOficioRechazo(
            List<FecetAnexosProrrogaOficio> listaResolucionProrrogaOficioRechazo) {
        this.listaResolucionProrrogaOficioRechazo = listaResolucionProrrogaOficioRechazo;
    }

    public UploadedFile getAnexoOfMulta() {
        return anexoOfMulta;
    }

    public void setAnexoOfMulta(UploadedFile anexoOfMulta) {
        this.anexoOfMulta = anexoOfMulta;
    }

    public void setFecetFlujoProrrogaOficioRechazo(final FecetFlujoProrrogaOficio fecetFlujoProrrogaOficioRechazo) {
        this.fecetFlujoProrrogaOficioRechazo = fecetFlujoProrrogaOficioRechazo;
    }

    public FecetFlujoProrrogaOficio getFecetFlujoProrrogaOficioRechazo() {
        return fecetFlujoProrrogaOficioRechazo;
    }

    public void setListaAnexosProrrogaOficioRechazo(
            final List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficioRechazo) {
        this.listaAnexosProrrogaOficioRechazo = listaAnexosProrrogaOficioRechazo;
    }

    public List<FecetAnexosProrrogaOficio> getListaAnexosProrrogaOficioRechazo() {
        return listaAnexosProrrogaOficioRechazo;
    }

    public void setAnexoProrrogaOficioSeleccionadoRechazo(
            final FecetAnexosProrrogaOficio anexoProrrogaOficioSeleccionadoRechazo) {
        this.anexoProrrogaOficioSeleccionadoRechazo = anexoProrrogaOficioSeleccionadoRechazo;
    }

    public FecetAnexosProrrogaOficio getAnexoProrrogaOficioSeleccionadoRechazo() {
        return anexoProrrogaOficioSeleccionadoRechazo;
    }

    public void setFecetFlujoProrrogaOficio(final FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio) {
        this.fecetFlujoProrrogaOficio = fecetFlujoProrrogaOficio;
    }

    public FecetFlujoProrrogaOficio getFecetFlujoProrrogaOficio() {
        return fecetFlujoProrrogaOficio;
    }

    public void setListaAnexosProrrogaOficio(final List<FecetAnexosProrrogaOficio> listaAnexosProrrogaOficio) {
        this.listaAnexosProrrogaOficio = listaAnexosProrrogaOficio;
    }

    public List<FecetAnexosProrrogaOficio> getListaAnexosProrrogaOficio() {
        return listaAnexosProrrogaOficio;
    }

    public void setArchivoDescargaAnexoProrrogaOficio(final StreamedContent archivoDescargaAnexoProrrogaOficio) {
        this.archivoDescargaAnexoProrrogaOficio = archivoDescargaAnexoProrrogaOficio;
    }

    public StreamedContent getArchivoDescargaAnexoProrrogaOficio() {
        archivoDescargaAnexoProrrogaOficio = getDescargaArchivo(getAnexoProrrogaOficioSeleccionado().getRutaArchivo(),
                getAnexoProrrogaOficioSeleccionado().getNombreArchivo());

        return archivoDescargaAnexoProrrogaOficio;
    }

    public void setArchivoPromocionOficio(final UploadedFile archivoPromocionOficio) {
        this.archivoPromocionOficio = archivoPromocionOficio;
    }

    public UploadedFile getArchivoPromocionOficio() {
        return archivoPromocionOficio;
    }

    public void setArchivoPruebasAlegatosOficio(final UploadedFile archivoPruebasAlegatosOficio) {
        this.archivoPruebasAlegatosOficio = archivoPruebasAlegatosOficio;
    }

    public UploadedFile getArchivoPruebasAlegatosOficio() {
        return archivoPruebasAlegatosOficio;
    }

    public void setListaPromocionesOficioCargadas(final List<FecetPromocionOficio> listaPromocionesOficioCargadas) {
        this.listaPromocionesOficioCargadas = listaPromocionesOficioCargadas;
    }

    public List<FecetPromocionOficio> getListaPromocionesOficioCargadas() {
        return listaPromocionesOficioCargadas;
    }

    public void setListaPruebasAlegatosOficioCargadas(
            final List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas) {
        this.listaPruebasAlegatosOficioCargadas = listaPruebasAlegatosOficioCargadas;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatosOficioCargadas() {
        return listaPruebasAlegatosOficioCargadas;
    }

    public List<FecetAnexosProrrogaOficio> getListaResolucionProrrogaOficio() {
        return listaResolucionProrrogaOficio;
    }

    public UploadedFile getOfMulta() {
        return ofMulta;
    }

    public void setOfMulta(UploadedFile ofMulta) {
        this.ofMulta = ofMulta;
    }

    public UploadedFile getOfAvisoContribuyente() {
        return ofAvisoContribuyente;
    }

    public void setOfAvisoContribuyente(UploadedFile ofAvisoContribuyente) {
        this.ofAvisoContribuyente = ofAvisoContribuyente;
    }

    public StreamedContent getAnexoSeleccionDescarga() {
        byte[] archivoBytes = lecturaArchivo(getAnexoSeleccionado().getArchivo());
        getAnexoSeleccionado().setArchivo(new ByteArrayInputStream(archivoBytes));
        setAnexoSeleccionDescarga(new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                CargaArchivoUtilOrdenes.obtenContentTypeArchivoPropuesta(getAnexoSeleccionado().getNombreArchivo()),
                CargaArchivoUtilOrdenes.aplicarCodificacionTexto(getAnexoSeleccionado().getNombreArchivo())));

        return this.anexoSeleccionDescarga;
    }

    public void setAnexoSeleccionDescarga(StreamedContent anexoSeleccionDescarga) {
        this.anexoSeleccionDescarga = anexoSeleccionDescarga;
    }

    public StreamedContent getOficioSeleccionDescarga() {
        byte[] archivoBytes = lecturaArchivo(getOficioSeleccionado().getArchivo());
        getOficioSeleccionado().setArchivo(new ByteArrayInputStream(archivoBytes));
        setOficioSeleccionDescarga(new DefaultStreamedContent(new ByteArrayInputStream(archivoBytes),
                CargaArchivoUtilOrdenes.obtenContentTypeArchivoPropuesta(getOficioSeleccionado().getNombreArchivo()),
                CargaArchivoUtilOrdenes.aplicarCodificacionTexto(getOficioSeleccionado().getNombreArchivo())));

        return oficioSeleccionDescarga;
    }

    public void setOficioSeleccionDescarga(StreamedContent oficioSeleccionDescarga) {
        this.oficioSeleccionDescarga = oficioSeleccionDescarga;
    }

    public StreamedContent getPromocionOficioSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(promocionOficioSeleccionada.getIdArchivoTemp(),
                        getRFCSession());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        this.promocionOficioSeleccionadaDescarga
                = new DefaultStreamedContent(byteArchivo, promocionOficioSeleccionada.getNombreArchivo(),
                        promocionOficioSeleccionada.getNombreArchivo());

        return promocionOficioSeleccionadaDescarga;

    }

    public void setPromocionOficioSeleccionadaDescarga(
            StreamedContent promocionOficioSeleccionadaDescarga) {
        this.promocionOficioSeleccionadaDescarga = promocionOficioSeleccionadaDescarga;
    }

    public StreamedContent getPruebaOficioSeleccionadaDescarga() {
        byte[] archivo
                = getArchivoTempService().consultaArchivoTemp(getPruebaAlegatoSeleccionada().getIdArchivoTemp(),
                        getRFCSession());
        ByteArrayInputStream byteArchivo = new ByteArrayInputStream(archivo);
        pruebaOficioSeleccionadaDescarga = new DefaultStreamedContent(byteArchivo, getPruebaAlegatoSeleccionada().getNombreArchivo(),
                getPruebaAlegatoSeleccionada().getNombreArchivo());

        return pruebaOficioSeleccionadaDescarga;
    }

    public void setPruebaOficioSeleccionadaDescarga(
            StreamedContent pruebaOficioSeleccionadaDescarga) {
        this.pruebaOficioSeleccionadaDescarga = pruebaOficioSeleccionadaDescarga;
    }

    public List<FecetOficio> getListaOfMulta() {
        return listaOfMulta;
    }

    public void setListaOfMulta(List<FecetOficio> listaOfMulta) {
        this.listaOfMulta = listaOfMulta;
    }

    public List<FecetOficioAnexos> getListaAnexosMulta() {
        return listaAnexosMulta;
    }

    public void setListaAnexosMulta(List<FecetOficioAnexos> listaAnexosMulta) {
        this.listaAnexosMulta = listaAnexosMulta;
    }

    public List<FecetOficio> getListaOfAvisoContribuyente() {
        return listaOfAvisoContribuyente;
    }

    public void setListaOfAvisoContribuyente(List<FecetOficio> listaOfAvisoContribuyente) {
        this.listaOfAvisoContribuyente = listaOfAvisoContribuyente;
    }

    public List<FecetOficioAnexos> getListaAnexosAvisoContribuyente() {
        return listaAnexosAvisoContribuyente;
    }

    public void setListaAnexosAvisoContribuyente(List<FecetOficioAnexos> listaAnexosAvisoContribuyente) {
        this.listaAnexosAvisoContribuyente = listaAnexosAvisoContribuyente;
    }

    public FecetPromocionOficio getPromocionOficioSeleccionada() {
        return promocionOficioSeleccionada;
    }

    public void setPromocionOficioSeleccionada(
            FecetPromocionOficio promocionOficioSeleccionada) {
        this.promocionOficioSeleccionada = promocionOficioSeleccionada;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajoOficio() {
        return listaPapelesTrabajoOficio;
    }

    public void setListaPapelesTrabajoOficio(List<DocumentoOrdenModel> listaPapelesTrabajoOficio) {
        this.listaPapelesTrabajoOficio = listaPapelesTrabajoOficio;
    }

    public void setAnexoSeleccionado(FecetOficioAnexos anexoSeleccionado) {
        this.anexoSeleccionado = anexoSeleccionado;
    }

    public FecetOficioAnexos getAnexoSeleccionado() {
        return anexoSeleccionado;
    }

    private byte[] lecturaArchivo(InputStream archivoEntrada) {
        final ByteArrayOutputStream resultado = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[SIZE_BUFFER];
        try {
            while ((nRead = archivoEntrada.read(data, 0, data.length)) != -1) {
                resultado.write(data, 0, nRead);
            }
            resultado.flush();
        } catch (IOException e) {
            logger.error("Error al realizar la lectura de archivo para descarga", e);
        }
        return resultado.toByteArray();
    }

}
