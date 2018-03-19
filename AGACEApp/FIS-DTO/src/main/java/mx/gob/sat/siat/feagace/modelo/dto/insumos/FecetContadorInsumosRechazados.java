package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetContadorInsumosRechazados extends BaseModel {
        
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idRechazoInsumo;
    private Date fechaRechazo;
    private String descripcion;
    private int contador;
    private BigDecimal idInsumo;


    public void setIdRechazoInsumo(BigDecimal idRechazoInsumo) {
        this.idRechazoInsumo = idRechazoInsumo;
    }

    public BigDecimal getIdRechazoInsumo() {
        return idRechazoInsumo;
    }

    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
        
    }

    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date)fechaRechazo.clone() : null;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }
}
