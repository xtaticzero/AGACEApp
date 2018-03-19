package mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class DocumentoActoAdministrativo extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1791819512354249343L;
    
    private BigDecimal idDocumentoAdm;
    private long cveDocumentoNyv;
    private String tipoDocumento;
    private boolean blnResolucion;
    private BigDecimal idNyv;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean blnActivo;
    private String cveDocumentoTipo;
	
    
    public BigDecimal getIdDocumentAdm() {
		return idDocumentoAdm;
	}
	public void setIdDocumentoAdm(BigDecimal idDocumentoAdm) {
		this.idDocumentoAdm = idDocumentoAdm;
	}
	public long getCveDocumentoNyv() {
		return cveDocumentoNyv;
	}
	public void setCveDocumentoNyv(long cveDocumentoNyv) {
		this.cveDocumentoNyv = cveDocumentoNyv;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public boolean isBlnResolucion() {
		return blnResolucion;
	}
	public void setBlnResolucion(boolean blnResolucion) {
		this.blnResolucion = blnResolucion;
	}
	public BigDecimal getIdNyv() {
		return idNyv;
	}
	public void setIdNyv(BigDecimal idNyv) {
		this.idNyv = idNyv;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public boolean isBlnActivo() {
		return blnActivo;
	}
	public void setBlnActivo(boolean blnActivo) {
		this.blnActivo = blnActivo;
	}
	public String getCveDocumentoTipo() {
		return cveDocumentoTipo;
	}
	public void setCveDocumentoTipo(String cveDocumentoTipo) {
		this.cveDocumentoTipo = cveDocumentoTipo;
	}
}
