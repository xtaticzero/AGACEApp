package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;


public class FecetAnexosProrrogaOrden extends BaseModel {
    @SuppressWarnings("compatibility:5407386241570935748")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ANEXOS_PRORROGA_ORDEN in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    private BigDecimal idAnexosProrrogaOrden;
            
     /**
      * This attribute maps to the column ID_FLUJO_PRORROGA_OFICIO in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private BigDecimal idFlujoProrrogaOrden;

     /**
      * This attribute maps to the column NOMBRE_ARCHIVO in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private String nombreArchivo;

     /**
      * This attribute maps to the column RUTA_ARCHIVO in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private String rutaArchivo;

     /**
      * This attribute maps to the column FECHA_CREACION in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private Date fechaCreacion;

     /**
      * This attribute maps to the column BLN_ACTIVO in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private Boolean blnActivo;

     /**
      * This attribute maps to the column FECHA_FIN in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private Date fechaFin;

     /**
      * This attribute maps to the column TIPO_ANEXO in the FECET_ANEXOS_PRORROGA_ORDEN table.
      */
     private String tipoAnexo;

     /**
      * This attribute transfer content from FileUploaded to an attribute for this DTO.
      */
     private transient InputStream archivo;
     
     private FececEmpleado fececEmpleado;


     public BigDecimal getIdAnexosProrrogaOrden() {
         return idAnexosProrrogaOrden;
     }

     public void setIdAnexosProrrogaOrden(BigDecimal idAnexosProrrogaOrden) {
         this.idAnexosProrrogaOrden = idAnexosProrrogaOrden;
     }

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

    /**
     * Method 'createPk'
     *
     * @return FecetProrrogaPk
     */
    public FecetAnexosProrrogaOrdenPk createPk() {
        return new FecetAnexosProrrogaOrdenPk(idAnexosProrrogaOrden);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetAnexosProrrogaOrden: ");
        ret.append("idAnexosProrrogaOrden=" + idAnexosProrrogaOrden);
        ret.append(", idFlujoProrrogaOrden=" + idFlujoProrrogaOrden);
        ret.append(", nombreArchivo=" + nombreArchivo);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", fechaCarga=" + fechaCreacion);
        ret.append(", blnActivo=" + blnActivo);
        ret.append(", fechaFin=" + fechaFin);
        ret.append(", tipoAnexo=" + tipoAnexo);
        return ret.toString();
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }
}
