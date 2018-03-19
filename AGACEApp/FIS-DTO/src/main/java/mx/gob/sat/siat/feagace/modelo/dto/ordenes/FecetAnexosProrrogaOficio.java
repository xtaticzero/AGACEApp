package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;

public class FecetAnexosProrrogaOficio extends BaseModel {
    
    
    @SuppressWarnings("compatibility:-460530010067821800")
    private static final long serialVersionUID = 1345634L;
    
    /**
     * This attribute maps to the column ID_ANEXOS_PRORROGA_ORDEN in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private BigDecimal idAnexosProrrogaOficio;
            
    /**
     * This attribute maps to the column ID_FLUJO_PRORROGA_OFICIO in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private BigDecimal idFlujoProrrogaOficio;

    /**
     * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column BLN_ACTIVO in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private Boolean blnActivo;

    /**
     * This attribute maps to the column FECHA_FIN in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private Date fechaFin;

    /**
     * This attribute maps to the column TIPO_ANEXO in the FECET_ANEXOS_PRORROGA_OFICIO table.
     */
    private String tipoAnexo;

    /**
     * This attribute transfer content from FileUploaded to an attribute for this DTO.
     */
    private transient InputStream archivo;
    
    
    private FececEmpleado fececEmpleado;


    public BigDecimal getIdAnexosProrrogaOficio() {
        return idAnexosProrrogaOficio;
    }

    public void setIdAnexosProrrogaOficio(BigDecimal idAnexosProrrogaOficio) {
        this.idAnexosProrrogaOficio = idAnexosProrrogaOficio;
    }

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
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    public void setBlnActivo(Boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Boolean getBlnActivo() {
        return blnActivo;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

  
    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }
}
