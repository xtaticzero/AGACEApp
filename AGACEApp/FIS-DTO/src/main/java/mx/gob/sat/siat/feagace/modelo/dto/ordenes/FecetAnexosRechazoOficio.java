package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class FecetAnexosRechazoOficio implements Serializable{
    private static final long serialVersionUID = 1841490675954071850L;

    private BigDecimal idAnexosRechazoOficio;
    private BigDecimal idRechazoOficio;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCarga;
    private BigDecimal idEmpleado;
    private transient InputStream archivo;

    public BigDecimal getIdAnexosRechazoOficio() {
        return idAnexosRechazoOficio;
    }

    public void setIdAnexosRechazoOficio(BigDecimal idAnexosRechazoOficio) {
        this.idAnexosRechazoOficio = idAnexosRechazoOficio;
    }

    public BigDecimal getIdRechazoOficio() {
        return idRechazoOficio;
    }

    public void setIdRechazoOficio(BigDecimal idRechazoOficio) {
        this.idRechazoOficio = idRechazoOficio;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
    }

    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }
}
