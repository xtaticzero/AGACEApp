package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class PapelesTrabajo extends BaseModel {

    private static final long serialVersionUID = -757571569296725068L;

    private BigDecimal idPapelesTrabajo;

    private BigDecimal idPropuesta;

    private BigDecimal idOrden;

    private BigDecimal idOficio;

    private String rutaArchivo;

    private String nombreArchivo;

    private Date fechaCreacion;

    private BigDecimal blnActivo;

    private Date fechaFin;

    private transient InputStream archivo;

    private BigDecimal idTipoOficio;

    public void setIdPapelesTrabajo(BigDecimal idPapelesTrabajo) {
        this.idPapelesTrabajo = idPapelesTrabajo;
    }

    public BigDecimal getIdPapelesTrabajo() {
        return idPapelesTrabajo;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setIdOficio(BigDecimal idOficio) {
        this.idOficio = idOficio;
    }

    public BigDecimal getIdOficio() {
        return idOficio;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setBlnActivo(BigDecimal blnActivo) {
        this.blnActivo = blnActivo;
    }

    public BigDecimal getBlnActivo() {
        return blnActivo;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public BigDecimal getIdTipoOficio() {
        return idTipoOficio;
    }

    public void setIdTipoOficio(BigDecimal idTipoOficio) {
        this.idTipoOficio = idTipoOficio;
    }

}
