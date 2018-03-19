package mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar;

import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */

public class ValidarYRetroalimentarPartHelper implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2177065315095025048L;

    private FecetImpuesto impuesto;
    private String msjDocumento;
    private String msjRechazo;

    private DocRetroalimentacionDTO docRetroSeleccionado;
    private Map<String, FecetContribuyente> informacionContribuyente;

    private Date fechaRegistro;
    private transient UploadedFile file;

    private BigDecimal areaOrigen;
    private transient InputStream archivoDescargableIS;
    private String nombreArchivoDescarga;
    private String rutaArchivoDescarga;
    private String informacionComite;
    private String presentacionComite;
    private String mensajeAntecedentes;
    private Date fechaActual;

    public FecetImpuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(FecetImpuesto impuesto) {
        this.impuesto = impuesto;
    }

    public String getMsjDocumento() {
        return msjDocumento;
    }

    public void setMsjDocumento(String msjDocumento) {
        this.msjDocumento = msjDocumento;
    }

    public String getMsjRechazo() {
        return msjRechazo;
    }

    public void setMsjRechazo(String msjRechazo) {
        this.msjRechazo = msjRechazo;
    }

    public DocRetroalimentacionDTO getDocRetroSeleccionado() {
        return docRetroSeleccionado;
    }

    public void setDocRetroSeleccionado(DocRetroalimentacionDTO docRetroSeleccionado) {
        this.docRetroSeleccionado = docRetroSeleccionado;
    }

    public Map<String, FecetContribuyente> getInformacionContribuyente() {
        return informacionContribuyente;
    }

    public void setInformacionContribuyente(Map<String, FecetContribuyente> informacionContribuyente) {
        this.informacionContribuyente = informacionContribuyente;
    }

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

    public BigDecimal getAreaOrigen() {
        return areaOrigen;
    }

    public void setAreaOrigen(BigDecimal areaOrigen) {
        this.areaOrigen = areaOrigen;
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

    public String getRutaArchivoDescarga() {
        return rutaArchivoDescarga;
    }

    public void setRutaArchivoDescarga(String rutaArchivoDescarga) {
        this.rutaArchivoDescarga = rutaArchivoDescarga;
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

    public String getMensajeAntecedentes() {
        return mensajeAntecedentes;
    }

    public void setMensajeAntecedentes(String mensajeAntecedentes) {
        this.mensajeAntecedentes = mensajeAntecedentes;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }
}
