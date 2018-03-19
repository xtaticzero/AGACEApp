/**
 * 
 */
package mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

/**
 * @author sergio.vaca
 *
 */
public class AbstractValidarYRetroalimentar implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private DocRetroalimentacionDTO docRetroSeleccionado;
    private Map<String, FecetContribuyente> informacionContribuyente;

    private Date fechaRegistro;
    private transient UploadedFile file;
    private StreamedContent fileRechazoDescarga;

    private BigDecimal areaOrigen;
    private transient InputStream archivoDescargableIS;
    private String nombreArchivoDescarga;
    private String rutaArchivoDescarga;
    private String informacionComite;
    private String presentacionComite;
    
    private FecetRechazoPropuesta archivoRechazoSeleccionado;
    private FecetPropPendiente archivoPendienteSeleccionado;

    public Date getFechaRegistro() {
        return (fechaRegistro != null) ? (Date) fechaRegistro.clone() : null;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = (fechaRegistro != null) ? (Date) fechaRegistro.clone() : null;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public StreamedContent getFileRechazoDescarga() {
        fileRechazoDescarga = (new DefaultStreamedContent(archivoRechazoSeleccionado.getArchivo(),
                "application/octet-stream", archivoRechazoSeleccionado.getNombreArchivo()));
        return fileRechazoDescarga;
    }

    public void setFileRechazoDescarga(StreamedContent fileDescarga) {
        this.fileRechazoDescarga = fileDescarga;
    }
    
    public FecetRechazoPropuesta getArchivoRechazoSeleccionado() {
        return archivoRechazoSeleccionado;
    }

    public void setArchivoRechazoSeleccionado(FecetRechazoPropuesta archivoRechazoSeleccionado) {
        this.archivoRechazoSeleccionado = archivoRechazoSeleccionado;
    }
    
    public void setInformacionContribuyente(Map<String, FecetContribuyente> informacionContribuyente) {
        this.informacionContribuyente = informacionContribuyente;
    }

    public Map<String, FecetContribuyente> getInformacionContribuyente() {
        return informacionContribuyente;
    }
    
    public InputStream getArchivoDescargableIS() {
        return archivoDescargableIS;
    }

    public void setArchivoDescargableIS(InputStream archivoDescargableIS) {
        this.archivoDescargableIS = archivoDescargableIS;
    }

    public String getNombreArchivoDescarga() {
        return nombreArchivoDescarga;
    }

    public void setNombreArchivoDescarga(String nombreArchivoDescarga) {
        this.nombreArchivoDescarga = nombreArchivoDescarga;
    }
    
    public DocRetroalimentacionDTO getDocRetroSeleccionado() {
        return docRetroSeleccionado;
    }

    public void setDocRetroSeleccionado(DocRetroalimentacionDTO docRetroSeleccionado) {
        this.docRetroSeleccionado = docRetroSeleccionado;
    }
    public void setAreaOrigen(BigDecimal areaOrigen) {
        this.areaOrigen = areaOrigen;
    }

    public BigDecimal getAreaOrigen() {
        return areaOrigen;
    }

    public String getRutaArchivoDescarga() {
        return rutaArchivoDescarga;
    }

    public void setRutaArchivoDescarga(String rutaArchivoDescarga) {
        this.rutaArchivoDescarga = rutaArchivoDescarga;
    }

    public FecetPropPendiente getArchivoPendienteSeleccionado() {
        return archivoPendienteSeleccionado;
    }

    public void setArchivoPendienteSeleccionado(FecetPropPendiente archivoPendienteSeleccionado) {
        this.archivoPendienteSeleccionado = archivoPendienteSeleccionado;
    }

    public String getInformacionComite() {
        return informacionComite;
    }

    public void setInformacionComite(String informacionComite) {
        this.informacionComite = informacionComite;
    }

    public String getPresentacionComite() {
        return presentacionComite;
    }

    public void setPresentacionComite(String presentacionComite) {
        this.presentacionComite = presentacionComite;
    }
}
