package mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class TipoTiempoDTO extends BaseModel {

    @SuppressWarnings("compatibility:-7987783676509106939")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idTipoTiempo;

    private String clave;

    private String descripcion;

    private Date fechaCreacion;

    private Date fechaBaja;
    
    public TipoTiempoDTO() {
        super();
    }

    public void setIdTipoTiempo(BigDecimal idTipoTiempo) {
        this.idTipoTiempo = idTipoTiempo;
    }

    public BigDecimal getIdTipoTiempo() {
        return idTipoTiempo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
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
}
