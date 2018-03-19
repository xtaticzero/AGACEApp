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

public class FecetDocExpInsumo extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 2793440543999967807L;
    
    private static final int CONSTANT_HASH = 7;
    private static final int CONSTANT_HCODE = 67;

    /**
     * This attribute maps to the column numDocRegistro in the
     * register.
     */
    private BigDecimal numDocRegistro;
        
    /**
     * This attribute maps to the column ID_DOC_EXP_INSUMO in the
     * FECET_DOC_EXP_INSUMO table.
     */
    private BigDecimal idDocExpInsumo;

    /**
     * This attribute maps to the column ID_INSUMO in the FECET_DOC_EXP_INSUMO
     * table.
     */
    private BigDecimal idInsumo;
    
    private String idRegistroInsumo;

    /**
     * This attribute maps to the column NOMBRE in the FECET_DOC_EXP_INSUMO
     * table.
     */
    private String nombre;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the
     * FECET_DOC_EXP_INSUMO table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CREACION in the
     * FECET_DOC_EXP_INSUMO table.
     */
    private Date fechaCreacion;

    private boolean blnActivo;

    private Date fechaFin;
    /**
     * Este atributo contiene el archivo para ser almacenado en el service
     */
    private transient InputStream archivo;

    /**
     * Method 'FecetDocExpInsumo'
     * 
     */
    public BigDecimal getNumDocRegistro() {
        return numDocRegistro;
    }

    public void setNumDocRegistro(BigDecimal numDocRegistro) {
        this.numDocRegistro = numDocRegistro;
    }

    /**
     * Method 'FecetDocExpInsumo'
     * 
     */
    public FecetDocExpInsumo() {
    }

    /**
     * Method 'getIdDocExpInsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdDocExpInsumo() {
        return idDocExpInsumo;
    }

    /**
     * Method 'setIdDocExpInsumo'
     * 
     * @param idDocExpInsumo
     */
    public void setIdDocExpInsumo(BigDecimal idDocExpInsumo) {
        this.idDocExpInsumo = idDocExpInsumo;
    }

    /**
     * Method 'getIdInsumo'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    /**
     * Method 'setIdInsumo'
     * 
     * @param idInsumo
     */
    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Method 'getNombre'
     * 
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
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
     * @return FecetDocExpInsumoPk
     */
    public FecetDocExpInsumoPk createPk() {
        return new FecetDocExpInsumoPk(idDocExpInsumo);
    }

    /**
     * @return the blnActivo
     */
    public boolean isBlnActivo() {
        return blnActivo;
    }

    /**
     * @param blnActivo
     *            the blnActivo to set
     */
    public void setBlnActivo(boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    @Override
    public int hashCode() {
        int hash = CONSTANT_HASH;
        hash = CONSTANT_HCODE * hash + (this.idDocExpInsumo != null ? this.idDocExpInsumo.hashCode() : 0);
        hash = CONSTANT_HCODE * hash + (this.idInsumo != null ? this.idInsumo.hashCode() : 0);
        hash = CONSTANT_HCODE * hash + (this.rutaArchivo != null ? this.rutaArchivo.hashCode() : 0);
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
        final FecetDocExpInsumo other = (FecetDocExpInsumo) obj;
        if (this.idDocExpInsumo != other.idDocExpInsumo && (this.idDocExpInsumo == null || !this.idDocExpInsumo.equals(other.idDocExpInsumo))) {
            return false;
        }
        if (this.idInsumo != other.idInsumo && (this.idInsumo == null || !this.idInsumo.equals(other.idInsumo))) {
            return false;
        }
        if ((this.rutaArchivo == null) ? (other.rutaArchivo != null) : !this.rutaArchivo.equals(other.rutaArchivo)) {
            return false;
        }
        return true;
    }

    public String getIdRegistroInsumo() {
        return idRegistroInsumo;
    }

    public void setIdRegistroInsumo(String idRegistroInsumo) {
        this.idRegistroInsumo = idRegistroInsumo;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
}