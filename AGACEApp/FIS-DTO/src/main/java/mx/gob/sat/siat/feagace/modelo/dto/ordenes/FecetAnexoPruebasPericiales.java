package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;

public class FecetAnexoPruebasPericiales extends BaseModel {

    private static final long serialVersionUID = 1L;

    private BigDecimal idAnexoPruebasPericiales;
    private BigDecimal idFlujoPruebasPericiales;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private Boolean blnActivo;
    private Date fechaFin;
    private String tipoAnexo;
    private transient InputStream archivo;
    private FececEmpleado fececEmpleado;

    public FecetAnexoPruebasPericiales() {
    }

    public void setIdAnexoPruebasPericiales(BigDecimal idAnexoPruebasPericiales) {
        this.idAnexoPruebasPericiales = idAnexoPruebasPericiales;
    }

    public BigDecimal getIdAnexoPruebasPericiales() {
        return idAnexoPruebasPericiales;
    }

    public void setIdFlujoPruebasPericiales(BigDecimal idFlujoPruebasPericiales) {
        this.idFlujoPruebasPericiales = idFlujoPruebasPericiales;
    }

    public BigDecimal getIdFlujoPruebasPericiales() {
        return idFlujoPruebasPericiales;
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

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public FecetAnexoPruebasPericialesPk createPk() {
        return new FecetAnexoPruebasPericialesPk(idAnexoPruebasPericiales);
    }
}
