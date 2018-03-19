
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;
import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetContadorPropuestasRechazados extends BaseModel{
    private static final long serialVersionUID = 1009858085305038053L;
        
    private BigDecimal idRechazoPropuestas;
    private Date fechaRechazo;
    private String observaciones;
    private String descripcion;
    private int contador;
    private BigDecimal idPropuesta;

    public void setIdRechazoPropuestas(BigDecimal idRechazoPropuestas) {
        this.idRechazoPropuestas = idRechazoPropuestas;
    }

    public BigDecimal getIdRechazoPropuestas() {
        return idRechazoPropuestas;
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

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
