package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FececAccionesFuncionario implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal idAccion;
    private String descripcion;
    private BigDecimal idTipoEmpleado;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer blnEstatus;

    public BigDecimal getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(BigDecimal idAccion) {
        this.idAccion = idAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Integer getBlnEstatus() {
        return blnEstatus;
    }

    public void setBlnEstatus(Integer blnEstatus) {
        this.blnEstatus = blnEstatus;
    }

}
