package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FeceaCifraTipoCifraDTO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BigDecimal getIdCifra() {
		return idCifra;
	}

	public void setIdCifra(BigDecimal idCifra) {
		this.idCifra = idCifra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private BigDecimal idCifra;
	
	private String descripcion;

}
