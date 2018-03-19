/**
 * 
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * @author Sergio.vaca
 *
 */
public class FecetRetroContador extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idRetroalimentacion;
    private BigDecimal idPropuesta;
    private BigDecimal idMotivo;
    private String detalle;
    private Date fechaCreacion;
    private int totalDocumentos;
    private String motivo;
    private String tipoMotivo;

    public BigDecimal getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(BigDecimal idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public int getTotalDocumentos() {
        return totalDocumentos;
    }

    public void setTotalDocumentos(int totalDocumentos) {
        this.totalDocumentos = totalDocumentos;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
    }
}
