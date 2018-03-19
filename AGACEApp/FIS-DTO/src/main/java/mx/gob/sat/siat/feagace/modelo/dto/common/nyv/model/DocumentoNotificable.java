/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sergio.vaca
 *
 */
public class DocumentoNotificable implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String estatusDocumento;

    private String rutaDocumento;

    private String firma;

    private Date fechaDocumento;

    private String cadenaOriginal;

    private String tipoDocumento;
    
    private String cveTipoDocumento;

    private String rfcFirmante;

    private String nombreFirmante;

    private String puestoFirmante;

    private Date fechaVigenciaFiel;

    /**
     * @return the estatusDocumento
     */
    public String getEstatusDocumento() {
        return estatusDocumento;
    }

    /**
     * @param estatusDocumento
     *            the estatusDocumento to set
     */
    public void setEstatusDocumento(String estatusDocumento) {
        this.estatusDocumento = estatusDocumento;
    }

    /**
     * @return the rutaDocumento
     */
    public String getRutaDocumento() {
        return rutaDocumento;
    }

    /**
     * @param rutaDocumento
     *            the rutaDocumento to set
     */
    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    /**
     * @return the firma
     */
    public String getFirma() {
        return firma;
    }

    /**
     * @param firma
     *            the firma to set
     */
    public void setFirma(String firma) {
        this.firma = firma;
    }

    /**
     * @return the fechaDocumento
     */
    public Date getFechaDocumento() {
        return (fechaDocumento != null) ? (Date) fechaDocumento.clone() : null;
    }

    /**
     * @param fechaDocumento
     *            the fechaDocumento to set
     */
    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento != null ? new Date(fechaDocumento.getTime()) : null;
    }

    /**
     * @return the cadenaOriginal
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * @param cadenaOriginal
     *            the cadenaOriginal to set
     */
    public void setCadenaOriginal(String cadenaOriginal) {
        this.cadenaOriginal = cadenaOriginal;
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento
     *            the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the rfcFirmante
     */
    public String getRfcFirmante() {
        return rfcFirmante;
    }

    /**
     * @param rfcFirmante
     *            the rfcFirmante to set
     */
    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    /**
     * @return the nombreFirmante
     */
    public String getNombreFirmante() {
        return nombreFirmante;
    }

    /**
     * @param nombreFirmante
     *            the nombreFirmante to set
     */
    public void setNombreFirmante(String nombreFirmante) {
        this.nombreFirmante = nombreFirmante;
    }

    /**
     * @return the puestoFirmante
     */
    public String getPuestoFirmante() {
        return puestoFirmante;
    }

    /**
     * @param puestoFirmante
     *            the puestoFirmante to set
     */
    public void setPuestoFirmante(String puestoFirmante) {
        this.puestoFirmante = puestoFirmante;
    }

    /**
     * @return the fechaVigenciaFiel
     */
    public Date getFechaVigenciaFiel() {
        return (fechaVigenciaFiel != null) ? (Date) fechaVigenciaFiel.clone() : null;
    }

    /**
     * @param fechaVigenciaFiel
     *            the fechaVigenciaFiel to set
     */
    public void setFechaVigenciaFiel(Date fechaVigenciaFiel) {
        this.fechaVigenciaFiel = fechaVigenciaFiel != null ? new Date(fechaVigenciaFiel.getTime()) : null;
    }

    /**
     * @param cveTipoDocumento
     *            the cveTipoDocumento to set
     */
    
	public String getCveTipoDocumento() {
		return cveTipoDocumento;
	}
	/**
     * @return the cveTipoDocumento
     */
	public void setCveTipoDocumento(String cveTipoDocumento) {
		this.cveTipoDocumento = cveTipoDocumento;
	}
    
    
}
