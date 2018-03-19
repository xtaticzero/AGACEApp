package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocProrrogaOficio extends BaseModel {

    @SuppressWarnings("compatibility:1571182762980217340")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idDocProrrogaOficio;
    private BigDecimal idProrrogaOficio;
    private String nombreArchivo;
    private String rutaArchivo;
    private transient InputStream archivo;    
    private Long tamanioArchivo;
    private BigDecimal idArchivoTemp;
    private Date fechaCarga;

    public BigDecimal getIdDocProrrogaOficio() {
        return idDocProrrogaOficio;
    }

    public void setIdDocProrrogaOficio(BigDecimal idDocProrrogaOficio) {
        this.idDocProrrogaOficio = idDocProrrogaOficio;
    }

    public BigDecimal getIdProrrogaOficio() {
        return idProrrogaOficio;
    }

    public void setIdProrrogaOficio(BigDecimal idProrrogaOficio) {
        this.idProrrogaOficio = idProrrogaOficio;
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

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setTamanioArchivo(Long tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public Long getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }
    
    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date) fechaCarga.clone() : null;
    }
    
    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime())
                : null;
    }
}
