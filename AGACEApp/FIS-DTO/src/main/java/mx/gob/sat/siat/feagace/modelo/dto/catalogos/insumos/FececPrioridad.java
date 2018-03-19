package mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececPrioridad extends BaseModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private BigDecimal idPrioridad;
    private BigDecimal idGeneral;
    private String valor;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal blnActivo;

    public BigDecimal getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(BigDecimal idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(BigDecimal idGeneral) {
        this.idGeneral = idGeneral;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

}
