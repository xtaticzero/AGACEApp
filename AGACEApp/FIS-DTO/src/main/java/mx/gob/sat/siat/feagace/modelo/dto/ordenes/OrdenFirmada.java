package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Clase que almacen la informacion de las ordenes que ya han sido
 * firmadas
 * 
 * @author Luis Rodriguez
 * @version 1.0
 */
public class OrdenFirmada implements Serializable{
    private static final long serialVersionUID = -5054442075669453733L;
    
    private String numeroOrden;
    private String numeroOficio;
    private String folioNyV;
    private String fechaEmision;
    private String rfcContribuyente;
    private String nombreContribuyente;
    private String nombreAuditor;
    private String rutaArchivo;
    private String nombreArchivo;
    private Date fechaDocOrden;
    
    private List<FecetOficio> oficiosFirmados;

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setFolioNyV(String folioNyV) {
        this.folioNyV = folioNyV;
    }

    public String getFolioNyV() {
        return folioNyV;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaEmision() {
        return fechaEmision;
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

    public void setNombreAuditor(String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    public String getNombreAuditor() {
        return nombreAuditor;
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

	public List<FecetOficio> getOficiosFirmados() {
		return oficiosFirmados;
	}

	public void setOficiosFirmados(List<FecetOficio> oficiosFirmados) {
		this.oficiosFirmados = oficiosFirmados;
	}

	public Date getFechaDocOrden() {
		return (fechaDocOrden != null) ? (Date)fechaDocOrden.clone() : null;
	}

	public void setFechaDocOrden(Date fechaDocOrden) {
		this.fechaDocOrden = fechaDocOrden != null ? new Date(fechaDocOrden.getTime()) : null;
	}
    
}
