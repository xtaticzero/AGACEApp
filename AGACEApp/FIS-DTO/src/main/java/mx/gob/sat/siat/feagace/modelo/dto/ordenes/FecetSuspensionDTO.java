package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetSuspensionDTO extends BaseModel{

    @SuppressWarnings("compatibility:-161511356390482034")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idSuspensionTiempo;
    private BigDecimal idOrden;
    private Date fechaInicioSuspension;
    private Date fechaFinSuspension;
    private Date fechaCreacion;
    private Date fechaBaja;
    private BigDecimal idObjeto;
    private BigDecimal idOficio;
    private String nombreOficio;

    public String getNombreOficio() {
		return nombreOficio;
	}

	public void setNombreOficio(String nombreOficio) {
		this.nombreOficio = nombreOficio;
	}

	public void setIdSuspensionTiempo(BigDecimal idSuspensionTiempo) {
        this.idSuspensionTiempo = idSuspensionTiempo;
    }

    public BigDecimal getIdSuspensionTiempo() {
        return idSuspensionTiempo;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setFechaInicioSuspension(Date fechaInicioSuspension) {
        this.fechaInicioSuspension = (fechaInicioSuspension != null) ? (Date)fechaInicioSuspension.clone() : null;
    }

    public Date getFechaInicioSuspension() {
        return (fechaInicioSuspension != null) ? (Date)fechaInicioSuspension.clone() : null;
    }

    public void setFechaFinSuspension(Date fechaFinSuspension) {
        this.fechaFinSuspension = (fechaFinSuspension != null) ? (Date)fechaFinSuspension.clone() : null;
    }

    public Date getFechaFinSuspension() {
        return (fechaFinSuspension != null) ? (Date)fechaFinSuspension.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    public void setIdObjeto(BigDecimal idObjeto) {
        this.idObjeto = idObjeto;
    }

    public BigDecimal getIdObjeto() {
        return idObjeto;
    }

	public BigDecimal getIdOficio() {
		return idOficio;
	}

	public void setIdOficio(BigDecimal idOficio) {
		this.idOficio = idOficio;
	}
}
