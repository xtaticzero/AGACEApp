package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetDocProrrogaOrden extends BaseModel{

    @SuppressWarnings("compatibility:-6878529411021540058")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_DOC_PRORROGA_ORDEN in the FECET_PRORROGA_ORDEN table.
     */
    private BigDecimal idDocProrrogaOrden;
            
    /**
     * This attribute maps to the column ID_PRORROGA_ORDEN in the FECET_PRORROGA_ORDEN table.
     */
    private BigDecimal idProrrogaOrden;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_PRORROGA_ORDEN table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_PRORROGA_ORDEN table.
     */
    private String rutaArchivo;
    
    private transient InputStream archivo;
    
    private Long tamanioArchivo;
    
    private BigDecimal idArchivoTemp;
    
    private Date fechaCarga;
    
    public FecetDocProrrogaOrden() {
        super();
    }

    public void setIdDocProrrogaOrden(BigDecimal idDocProrrogaOrden) {
        this.idDocProrrogaOrden = idDocProrrogaOrden;
    }

    public BigDecimal getIdDocProrrogaOrden() {
        return idDocProrrogaOrden;
    }

    public void setIdProrrogaOrden(BigDecimal idProrrogaOrden) {
        this.idProrrogaOrden = idProrrogaOrden;
    }

    public BigDecimal getIdProrrogaOrden() {
        return idProrrogaOrden;
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

    /**
     * Method 'createPk'
     *
     * @return FecetDocProrrogaPk
     */
    public FecetDocProrrogaOrdenPk createPk() {
        return new FecetDocProrrogaOrdenPk(idDocProrrogaOrden);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetDocProrrogaOrden: ");
        ret.append("idDocProrrogaOrden=" + idDocProrrogaOrden);
        ret.append(", idProrrogaOrden=" + idProrrogaOrden);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        return ret.toString();
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
