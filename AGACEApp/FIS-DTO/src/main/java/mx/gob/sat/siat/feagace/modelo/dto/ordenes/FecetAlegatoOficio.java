package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetAlegatoOficio extends BaseModel {
    @SuppressWarnings("compatibility:4893112141482405655")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ALEGATO in the FECET_ALEGATO table.
     */
    private BigDecimal idAlegatoOficio;
    private BigDecimal idPromocionOficio;
    private Date fechaCarga;
    private String nombreArchivo;
    private String rutaArchivo;
    private transient InputStream archivo;
    private Long tamanioArchivo;
    private BigDecimal idArchivoTemp;


    public BigDecimal getIdAlegatoOficio() {
        return idAlegatoOficio;
    }

    public void setIdAlegatoOficio(BigDecimal idAlegatoOficio) {
        this.idAlegatoOficio = idAlegatoOficio;
    }

    public BigDecimal getIdPromocionOficio() {
        return idPromocionOficio;
    }

    public void setIdPromocionOficio(BigDecimal idPromocionOficio) {
        this.idPromocionOficio = idPromocionOficio;
    }

    public Date getFechaCarga() {
        return (fechaCarga != null) ? (Date)fechaCarga.clone() : null;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga != null ? new Date(fechaCarga.getTime()) : null;
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

    public InputStream getArchivo() {
        return archivo;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public Long getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Long tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }
}
