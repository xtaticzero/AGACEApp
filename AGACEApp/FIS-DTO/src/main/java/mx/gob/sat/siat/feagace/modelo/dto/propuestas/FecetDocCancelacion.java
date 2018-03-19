/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class FecetDocCancelacion extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idDocCancelacion;
    private BigDecimal idCancelacion;
    private String rutaArchivo;
    private String nombreArchivo;
    private Date fechaCreacion;
    private Date fechaFin;
    private BigDecimal blnActivo;

    public BigDecimal getIdDocCancelacion() {
        return idDocCancelacion;
    }

    public void setIdDocCancelacion(BigDecimal idDocCancelacion) {
        this.idDocCancelacion = idDocCancelacion;
    }

    public BigDecimal getIdCancelacion() {
        return idCancelacion;
    }

    public void setIdCancelacion(BigDecimal idCancelacion) {
        this.idCancelacion = idCancelacion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }
}
