package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

public class AsociarColaboradoresFileStreamedAbstractHelper  implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private transient StreamedContent archivoDescargaOficio;
    private transient StreamedContent archivoDescargaOficioAcuse;
    private transient StreamedContent archivoDescargaAcuseCompulsa;
    private transient StreamedContent archivoDescargaAcuseProrrogaCompulsa;
    private transient StreamedContent archivoDescargaOficioCompulsa;
    private transient StreamedContent archivoDescargaOfDependiente;
    private transient StreamedContent archivoDescargaProrrogaResolucionOrden;
    private transient StreamedContent archivoDescargaPruebaPericialResolucion;

    

    private transient UploadedFile archivoPromocion;
    private transient UploadedFile archivoPruebasAlegatos;
    private transient UploadedFile archivoProrroga;
    
    public StreamedContent getArchivoDescargaPruebaPericialResolucion() {
        return archivoDescargaPruebaPericialResolucion;
    }

    public void setArchivoDescargaPruebaPericialResolucion(StreamedContent archivoDescargaPruebaPericialResolucion) {
        this.archivoDescargaPruebaPericialResolucion = archivoDescargaPruebaPericialResolucion;
    }
    
    public StreamedContent getArchivoDescargaOficio() {
        return archivoDescargaOficio;
    }

    public void setArchivoDescargaOficio(StreamedContent archivoDescargaOficio) {
        this.archivoDescargaOficio = archivoDescargaOficio;
    }

    public StreamedContent getArchivoDescargaOficioAcuse() {
        return archivoDescargaOficioAcuse;
    }

    public void setArchivoDescargaOficioAcuse(StreamedContent archivoDescargaOficioAcuse) {
        this.archivoDescargaOficioAcuse = archivoDescargaOficioAcuse;
    }

    public StreamedContent getArchivoDescargaAcuseCompulsa() {
        return archivoDescargaAcuseCompulsa;
    }

    public void setArchivoDescargaAcuseCompulsa(StreamedContent archivoDescargaAcuseCompulsa) {
        this.archivoDescargaAcuseCompulsa = archivoDescargaAcuseCompulsa;
    }

    public StreamedContent getArchivoDescargaAcuseProrrogaCompulsa() {
        return archivoDescargaAcuseProrrogaCompulsa;
    }

    public void setArchivoDescargaAcuseProrrogaCompulsa(StreamedContent archivoDescargaAcuseProrrogaCompulsa) {
        this.archivoDescargaAcuseProrrogaCompulsa = archivoDescargaAcuseProrrogaCompulsa;
    }

    public StreamedContent getArchivoDescargaOficioCompulsa() {
        return archivoDescargaOficioCompulsa;
    }

    public void setArchivoDescargaOficioCompulsa(StreamedContent archivoDescargaOficioCompulsa) {
        this.archivoDescargaOficioCompulsa = archivoDescargaOficioCompulsa;
    }

    public UploadedFile getArchivoPromocion() {
        return archivoPromocion;
    }

    public void setArchivoPromocion(UploadedFile archivoPromocion) {
        this.archivoPromocion = archivoPromocion;
    }

    public UploadedFile getArchivoPruebasAlegatos() {
        return archivoPruebasAlegatos;
    }

    public void setArchivoPruebasAlegatos(UploadedFile archivoPruebasAlegatos) {
        this.archivoPruebasAlegatos = archivoPruebasAlegatos;
    }

    public UploadedFile getArchivoProrroga() {
        return archivoProrroga;
    }

    public void setArchivoProrroga(UploadedFile archivoProrroga) {
        this.archivoProrroga = archivoProrroga;
    }

    public void setArchivoDescargaOfDependiente(StreamedContent archivoDescargaOfDependiente) {
        this.archivoDescargaOfDependiente = archivoDescargaOfDependiente;
    }

    public StreamedContent getArchivoDescargaOfDependiente() {
        return archivoDescargaOfDependiente;
    }

    public void setArchivoDescargaProrrogaResolucionOrden(StreamedContent archivoDescargaProrrogaResolucionOrden) {
        this.archivoDescargaProrrogaResolucionOrden = archivoDescargaProrrogaResolucionOrden;
    }

    public StreamedContent getArchivoDescargaProrrogaResolucionOrden() {
        return archivoDescargaProrrogaResolucionOrden;
    }

}
