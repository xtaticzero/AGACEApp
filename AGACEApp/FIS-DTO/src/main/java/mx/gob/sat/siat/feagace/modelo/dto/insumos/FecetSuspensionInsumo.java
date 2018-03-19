/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class FecetSuspensionInsumo extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idSuspensionInsumo;
    private BigDecimal idInsumo;
    private Date fechaInicioSuspension;
    private Date fechaFinSuspension;
    private Date fechaCreacion;

    public BigDecimal getIdSuspensionInsumo() {
        return idSuspensionInsumo;
    }

    public void setIdSuspensionInsumo(BigDecimal idSuspensionInsumo) {
        this.idSuspensionInsumo = idSuspensionInsumo;
    }

    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Date getFechaInicioSuspension() {
        return (fechaInicioSuspension != null) ? (Date) fechaInicioSuspension.clone() : null;
    }

    public void setFechaInicioSuspension(Date fechaInicioSuspension) {
        this.fechaInicioSuspension = fechaInicioSuspension != null ? new Date(fechaInicioSuspension.getTime()) : null;
    }

    public Date getFechaFinSuspension() {
        return (fechaFinSuspension != null) ? (Date) fechaFinSuspension.clone() : null;
    }

    public void setFechaFinSuspension(Date fechaFinSuspension) {
        this.fechaFinSuspension = fechaFinSuspension != null ? new Date(fechaFinSuspension.getTime()) : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

}
