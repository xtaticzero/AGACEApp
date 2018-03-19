package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.InputStream;
import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;

public class SolicitudContribuyenteDocVO extends BaseModel {

    private static final long serialVersionUID = 1L;
    
    private BigDecimal idDoc;
    private BigDecimal id;
    private String rutaArchivo;
    private String nombreArchivo;
    private transient InputStream archivo;
    private FecetDocProrrogaOrden fecetDocProrrogaOrden;
    private FecetDocPruebasPericiales fecetDocPruebasPericiales;

    public SolicitudContribuyenteDocVO() {
    }

    public void setIdDoc(BigDecimal idDoc) {
        this.idDoc = idDoc;
    }

    public BigDecimal getIdDoc() {
        return idDoc;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
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

    public void setFecetDocProrrogaOrden(FecetDocProrrogaOrden fecetDocProrrogaOrden) {
        this.fecetDocProrrogaOrden = fecetDocProrrogaOrden;
    }

    public FecetDocProrrogaOrden getFecetDocProrrogaOrden() {
        return fecetDocProrrogaOrden;
    }

    public void setFecetDocPruebasPericiales(FecetDocPruebasPericiales fecetDocPruebasPericiales) {
        this.fecetDocPruebasPericiales = fecetDocPruebasPericiales;
    }

    public FecetDocPruebasPericiales getFecetDocPruebasPericiales() {
        return fecetDocPruebasPericiales;
    }
}
