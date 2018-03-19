/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocretroinsumo extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -5242655836435655883L;
    
    private static final int HAS_CONSTANT = 43;
    private static final int CONSTANT = 3;

    /**
     * This attribute maps to the column ID_DOCRETROINSUMO in the
     * FECET_DOCRETROINSUMO table.
     */
    private BigDecimal idDocretroinsumo;

    /**
     * This attribute maps to the column ID_RETROALIMENTACION_INSUMO in the
     * FECET_DOCRETROINSUMO table.
     */
    private BigDecimal idRetroalimentacionInsumo;

    /**
     * This attribute maps to the column NOMBRE in the FECET_DOCRETROINSUMO
     * table.
     */
    private String nombreArchivo;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the
     * FECET_DOCRETROINSUMO table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CREACION in the
     * FECET_DOCRETROINSUMO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column TIPO_DOCUMENTO in the
     * FECET_DOCRETROINSUMO table.
     */
    private String tipoDocumento;
    
    private BigDecimal idTipoEmpleado;     

    /**
     * This attribute transfer content from FileUploaded to an attribute for
     * this DTO.
     */
    private transient InputStream archivo;

    /**
     * Method 'FecetDocretroinsumo'
     * 
     */
    public FecetDocretroinsumo() {
    }

    /**
     * Method 'getIdDocretroinsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdDocretroinsumo() {
        return idDocretroinsumo;
    }

    /**
     * Method 'setIdDocretroinsumo'
     * 
     * @param idDocretroinsumo
     */
    public void setIdDocretroinsumo(BigDecimal idDocretroinsumo) {
        this.idDocretroinsumo = idDocretroinsumo;
    }

    /**
     * Method 'getIdRetroalimentacionInsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdRetroalimentacionInsumo() {
        return idRetroalimentacionInsumo;
    }

    /**
     * Method 'setIdRetroalimentacionInsumo'
     * 
     * @param idRetroalimentacionInsumo
     */
    public void setIdRetroalimentacionInsumo(
            BigDecimal idRetroalimentacionInsumo) {
        this.idRetroalimentacionInsumo = idRetroalimentacionInsumo;
    }

    /**
     * Method 'getNombre'
     * 
     * @return String
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Method 'setNombre'
     * 
     * @param nombre
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Method 'getRutaArchivo'
     * 
     * @return String
     */
    public String getRutaArchivo() {
        return rutaArchivo;
    }

    /**
     * Method 'setRutaArchivo'
     * 
     * @param rutaArchivo
     */
    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Method 'getFechaCreacion'
     * 
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     * 
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(
                fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getTipoDocumento'
     * 
     * @return String
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Method 'setArchivo'
     *
     * @param archivo
     */
    public void setArchivo(final InputStream archivo) {
        this.archivo = archivo;
    }

    /**
     * Method 'getArchivo'
     *
     * @return archivo
     */
    public InputStream getArchivo() {
        return archivo;
    }

    /**
     * Method 'setTipoDocumento'
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Method 'createPk'
     * 
     * @return FecetDocretroinsumoPk
     */
    public FecetDocretroinsumoPk createPk() {
        return new FecetDocretroinsumoPk(idDocretroinsumo);
    }

    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = CONSTANT;
        hash = HAS_CONSTANT * hash + (this.idDocretroinsumo != null ? this.idDocretroinsumo.hashCode() : 0);
        hash = HAS_CONSTANT * hash + (this.rutaArchivo != null ? this.rutaArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FecetDocretroinsumo other = (FecetDocretroinsumo) obj;
        if (this.idDocretroinsumo != other.idDocretroinsumo && (this.idDocretroinsumo == null || !this.idDocretroinsumo.equals(other.idDocretroinsumo))) {
            return false;
        }
        if ((this.rutaArchivo == null) ? (other.rutaArchivo != null) : !this.rutaArchivo.equals(other.rutaArchivo)) {
            return false;
        }
        return true;
    }
    
    

}
