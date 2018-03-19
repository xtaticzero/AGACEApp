package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;

public class SolicitudContribuyenteAnexoVO extends BaseModel {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;
    private BigDecimal idFlujo;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private Boolean blnActivo;
    private FecetAnexoPruebasPericiales fecetAnexoPruebasPericiales;
    private FecetAnexosProrrogaOrden fecetAnexosProrrogaOrden;

    public SolicitudContribuyenteAnexoVO() {
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setIdFlujo(BigDecimal idFlujo) {
        this.idFlujo = idFlujo;
    }

    public BigDecimal getIdFlujo() {
        return idFlujo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
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

    public void setBlnActivo(Boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Boolean getBlnActivo() {
        return blnActivo;
    }

    public void setFecetAnexoPruebasPericiales(FecetAnexoPruebasPericiales fecetAnexoPruebasPericiales) {
        this.fecetAnexoPruebasPericiales = fecetAnexoPruebasPericiales;
    }

    public FecetAnexoPruebasPericiales getFecetAnexoPruebasPericiales() {
        return fecetAnexoPruebasPericiales;
    }

    public void setFecetAnexosProrrogaOrden(FecetAnexosProrrogaOrden fecetAnexosProrrogaOrden) {
        this.fecetAnexosProrrogaOrden = fecetAnexosProrrogaOrden;
    }

    public FecetAnexosProrrogaOrden getFecetAnexosProrrogaOrden() {
        return fecetAnexosProrrogaOrden;
    }
}
