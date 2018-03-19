package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class TotalCifrasDTO extends BaseModel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public BigDecimal getIdTipoCifra() {
		return idTipoCifra;
	}

	public void setIdTipoCifra(BigDecimal idTipoCifra) {
		this.idTipoCifra = idTipoCifra;
	}

	public BigDecimal getMontoCifra() {
		return montoCifra;
	}

	public void setMontoCifra(BigDecimal montoCifra) {
		this.montoCifra = montoCifra;
	}

	private String descripcionTipo;
	
	private BigDecimal idTipoCifra;
	
	private BigDecimal montoCifra;

}
