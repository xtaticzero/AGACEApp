package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.util.List;

/**
 * Clase que almacena la informacion de las ordenes por firmar
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class OrdenPorFirmar implements Serializable {

    private static final long serialVersionUID = -141733608100235243L;

    private String idRegistro;
    private String idPropuesta;
    private String idOrden;
    private String nombreAuditor;
    private String fechaCarga;
    private String nombreArchivo;
    private String rutaArchivo;
    private String prioridadSugerida;
    private String rfcContribuyente;
    private String nombreContribuyente;
    private String numeroOrden;
    private String numeroOficio;
    
    private List<FecetOficio> oficios;

    public String getNumeroOficio() {
        return numeroOficio;
    }

    public void setNumeroOficio(String numeroOficio) {
        this.numeroOficio = numeroOficio;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
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

	public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }

    public String getIdOrden() {
        return idOrden;
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

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNumeroOrden() {
        return numeroOrden;
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

	public List<FecetOficio> getOficios() {
		return oficios;
	}

	public void setOficios(List<FecetOficio> oficios) {
		this.oficios = oficios;
	}
    
}
