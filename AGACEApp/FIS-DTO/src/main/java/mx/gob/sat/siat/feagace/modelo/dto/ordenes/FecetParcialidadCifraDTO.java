package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetParcialidadCifraDTO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BigDecimal getIdParcialidadHistorico() {
		return idParcialidadHistorico;
	}

	public void setIdParcialidadHistorico(BigDecimal idParcialidadHistorico) {
		this.idParcialidadHistorico = idParcialidadHistorico;
	}

	public Date getFechaAlta() {
		return (fechaAlta != null) ? (Date) fechaAlta.clone() : null;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = (fechaAlta != null) ? (Date) fechaAlta.clone() : null;
	}

	public BigDecimal getNumeroParcialidades() {
		return numeroParcialidades;
	}

	public void setNumeroParcialidades(BigDecimal numeroParcialidades) {
		this.numeroParcialidades = numeroParcialidades;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Date getFechaInicio() {
		return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
	}

	public Date getFechaFin() {
		return (fechaFin != null) ? (Date) fechaFin.clone() : null;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = (fechaFin != null) ? (Date) fechaFin.clone() : null;
	}

	public FececTipoParcialidadDTO getTipoParcialidad() {
		return tipoParcialidad;
	}

	public void setTipoParcialidad(FececTipoParcialidadDTO tipoParcialidad) {
		this.tipoParcialidad = tipoParcialidad;
	}

	private BigDecimal idParcialidadHistorico;
	
	private Date fechaAlta;
	
	private BigDecimal numeroParcialidades;
	
	private BigDecimal montoTotal;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private FececTipoParcialidadDTO tipoParcialidad;
	

}
