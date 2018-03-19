package mx.gob.sat.siat.feagace.vista.model.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AnexoProrrogaOrdenConsultaDTO implements Serializable{
    private static final long serialVersionUID = 2950579714068162133L;

    private BigDecimal idFlujoProrrogaOrden;
    private String nombreArchivo;
    private String rutaArchivo;
    private Date fechaCreacion;
    private String tipoAnexo;
    private String presentadoPor;

    public BigDecimal getIdFlujoProrrogaOrden() {
        return idFlujoProrrogaOrden;
    }

    public void setIdFlujoProrrogaOrden(BigDecimal idFlujoProrrogaOrden) {
        this.idFlujoProrrogaOrden = idFlujoProrrogaOrden;
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
