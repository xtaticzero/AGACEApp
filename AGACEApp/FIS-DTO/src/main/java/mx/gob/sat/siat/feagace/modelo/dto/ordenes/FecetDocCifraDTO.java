package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocCifraDTO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private BigDecimal idDocumentoCifra;
	
	private String rutaArchivo;
	
	private Date fechaCreacion;
	
	private Date fechaFinal;
	
	private String nombre;
	
	private transient InputStream documento;

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
	
	public InputStream getDocumento() {
		return documento;
	}

	public void setDocumento(InputStream documento) {
		this.documento = documento;
	}

	public BigDecimal getIdDocumentoCifra() {
		return idDocumentoCifra;
	}

	public void setIdDocumentoCifra(BigDecimal idDocumentoCifra) {
		this.idDocumentoCifra = idDocumentoCifra;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public Date getFechaCreacion() {
		return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
	}	
	
	public Date getFechaFinal() {
		return (fechaFinal != null) ? (Date) fechaFinal.clone() : null;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = (fechaFinal != null) ? (Date) fechaFinal.clone() : null;
	}
}
