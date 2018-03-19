package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetOficioAnexos extends BaseModel{


    @SuppressWarnings("compatibility:-8407818741063094412")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ANEXOS_OFICIO in the FECET_OFICIO_ANEXOS table.
     */
    private BigDecimal idOficioAnexos;
            
    /**
     * This attribute maps to the column ID_OFICIO in the FECET_OFICIO_ANEXOS table.
     */
    private BigDecimal idOficio;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_OFICIO_ANEXOS table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_OFICIO_ANEXOS table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CARGA in the FECET_OFICIO_ANEXOS table.
     */
    private Date fechaCreacion;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;
    
    
    private String presentadoPor;
    
    public FecetOficioAnexos() {
        super();
    }

    public void setIdOficioAnexos(final BigDecimal idOficioAnexos) {
        this.idOficioAnexos = idOficioAnexos;
    }

    public BigDecimal getIdOficioAnexos() {
        return idOficioAnexos;
    }

    public void setIdOficio(final BigDecimal idOficio) {
        this.idOficio = idOficio;
    }

    public BigDecimal getIdOficio() {
        return idOficio;
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
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetAnexosProrrogaOrden: ");
        ret.append("idOficioAnexos=" + idOficioAnexos);
        ret.append(", idOficio=" + idOficio);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", fechaCreacion=" + fechaCreacion);
        return ret.toString();
    }

    public String getPresentadoPor() {
        return presentadoPor;
    }

    public void setPresentadoPor(String presentadoPor) {
        this.presentadoPor = presentadoPor;
    }
}
