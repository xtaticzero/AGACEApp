package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;
import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocPruebasPericiales extends BaseModel {

    private static final long serialVersionUID = 1L;

    private BigDecimal idDocPruebasPericiales;
    private BigDecimal idPruebasPericiales;
    private String rutaArchivo;
    private String nombreArchivo;
    private transient InputStream archivo;
    private Long tamanioArchivo;
    private BigDecimal idArchivoTemp;

    public FecetDocPruebasPericiales() {
    }

    public void setIdDocPruebasPericiales(BigDecimal idDocPruebasPericiales) {
        this.idDocPruebasPericiales = idDocPruebasPericiales;
    }

    public BigDecimal getIdDocPruebasPericiales() {
        return idDocPruebasPericiales;
    }

    public void setIdPruebasPericiales(BigDecimal idPruebasPericiales) {
        this.idPruebasPericiales = idPruebasPericiales;
    }

    public BigDecimal getIdPruebasPericiales() {
        return idPruebasPericiales;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
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

    public FecetDocPruebasPericialesPk createPk() {
        return new FecetDocPruebasPericialesPk(idDocPruebasPericiales);
    }
}
