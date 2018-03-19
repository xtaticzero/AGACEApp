package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

/**
 * Clase que almacena la informacion de las propuestas por validar por el 
 * firmante
 * @author Luis Rodriguez
 * @version 1.0
 */
public class PropuestaPorValidar implements Serializable {
    
    private static final long serialVersionUID = 7240931407116822411L;

    private String idOrden;
    private String idPropuesta;
    private String idRegistro;
    private String nombreAuditor;
    private String fechaCarga;
    private String prioridadSugerida;
    private String rfcFirmante;
    private String rfcContribuyente;
    private String nombreContribuyente;
    private String rutaArchivo;
    private String nombreArchivo;
    private String idDocOrden;

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setNombreAuditor(String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    public String getNombreAuditor() {
        return nombreAuditor;
    }

    public void setFechaCarga(String fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getFechaCarga() {
        return fechaCarga;
    }

    public String getPrioridadSugerida() {
		return prioridadSugerida;
	}

	public void setPrioridadSugerida(String prioridadSugerida) {
		this.prioridadSugerida = prioridadSugerida;
	}

	public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdOrden() {
        return idOrden;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public String getRfcFirmante() {
        return rfcFirmante;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setNombreContribuyente(String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setIdDocOrden(String idDocOrden) {
        this.idDocOrden = idDocOrden;
    }

    public String getIdDocOrden() {
        return idDocOrden;
    }
}
