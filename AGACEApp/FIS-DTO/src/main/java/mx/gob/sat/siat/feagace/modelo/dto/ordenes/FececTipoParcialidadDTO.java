package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececTipoParcialidadDTO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BigDecimal getIdParcialidad() {
		return idParcialidad;
	}

	public void setIdParcialidad(BigDecimal idParcialidad) {
		this.idParcialidad = idParcialidad;
	}

	public String getTipoParcialidad() {
		return tipoParcialidad;
	}

	public void setTipoParcialidad(String tipoParcialidad) {
		this.tipoParcialidad = tipoParcialidad;
	}

	private BigDecimal idParcialidad;
	
	private String tipoParcialidad;

}
