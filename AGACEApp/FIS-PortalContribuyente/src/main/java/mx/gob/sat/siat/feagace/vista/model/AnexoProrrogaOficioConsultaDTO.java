package mx.gob.sat.siat.feagace.vista.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnexoProrrogaOficioConsultaDTO implements Serializable {

    private static final long serialVersionUID = 1403121938087028633L;

    private BigDecimal idFlujoProrrogaOficio;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private String tipoAnexo;
    private String presentadoPor;

    public BigDecimal getIdFlujoProrrogaOficio() {
        return idFlujoProrrogaOficio;
    }

    public void setIdFlujoProrrogaOficio(BigDecimal idFlujoProrrogaOficio) {
        this.idFlujoProrrogaOficio = idFlujoProrrogaOficio;
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

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public String getPresentadoPor() {
        return presentadoPor;
    }

    public void setPresentadoPor(String presentadoPor) {
        this.presentadoPor = presentadoPor;
    }

}
