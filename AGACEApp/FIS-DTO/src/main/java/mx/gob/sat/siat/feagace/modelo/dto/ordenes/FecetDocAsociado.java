/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author sergio.vaca
 *
 */
public class FecetDocAsociado extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idDocAsociado;
    private BigDecimal idAsociado;
    private String rutaArchivo;
    private String nombreArchivo;
    private Date fechaCreacion;
    private BigDecimal blnActivo;
    private Date fechaFin;
    private BigDecimal idTemporal;
    private transient InputStream archivo;
    private boolean eliminar;

    public BigDecimal getIdDocAsociado() {
        return idDocAsociado;
    }

    public void setIdDocAsociado(BigDecimal idDocAsociado) {
        this.idDocAsociado = idDocAsociado;
    }

    public BigDecimal getIdAsociado() {
        return idAsociado;
    }

    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
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

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public BigDecimal getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(BigDecimal idTemporal) {
        this.idTemporal = idTemporal;
    }

    public boolean isEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
