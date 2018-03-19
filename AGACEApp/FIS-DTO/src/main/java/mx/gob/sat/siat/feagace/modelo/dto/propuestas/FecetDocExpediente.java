/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDocExpediente extends BaseModel implements Serializable {

    @SuppressWarnings("compatibility:5678653565262149077")
    private static final long serialVersionUID = 1L;

    private static final int HASH_INICIAL = 3;
    private static final int SESENTAYUNO = 61;

    /**
     * This attribute maps to the column ID_DOC_EXPEDIENTE in the
     * FECET_DOC_EXPEDIENTE table.
     */
    private BigDecimal idDocExpediente;

    /**
     * This attribute maps to the column ID_PROPUESTA in the
     * FECET_DOC_EXPEDIENTE table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column NOMBRE in the FECET_DOC_EXPEDIENTE
     * table.
     */
    private String nombre;

    /**
     * This attribute maps to the column RUTA_ARCHIVO in the
     * FECET_DOC_EXPEDIENTE table.
     */
    private String rutaArchivo;

    /**
     * This attribute maps to the column FECHA_CREACION in the
     * FECET_DOC_EXPEDIENTE table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_CREACION in the
     * FECET_DOC_EXPEDIENTE table.
     */
    private Timestamp fechaCreacionTimeStamp;

    /**
     * This attribute maps to the column BLN_ACTIVO in the FECET_DOC_EXPEDIENTE
     * table.
     */
    private Integer blnActivo;

    /**
     * This attribute maps to the column FECHA_FIN in the FECET_DOC_EXPEDIENTE
     * table.
     */
    private Date fechaFin;

    /**
     * Archivo que se guardara en el fileSystem
     */
    private transient InputStream archivo;

    /**
     * Method 'FecetDocExpediente'
     *
     */
    public FecetDocExpediente() {
    }

    /**
     * Method 'getIdDocExpediente'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdDocExpediente() {
        return idDocExpediente;
    }

    /**
     * Method 'setIdDocExpediente'
     *
     * @param idDocExpediente
     */
    public void setIdDocExpediente(final BigDecimal idDocExpediente) {
        this.idDocExpediente = idDocExpediente;
    }

    /**
     * Method 'getIdPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    /**
     * Method 'setIdPropuesta'
     *
     * @param idPropuesta
     */
    public void setIdPropuesta(final BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
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
    public void setNombre(final String nombre) {
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
    public void setRutaArchivo(final String rutaArchivo) {
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

    public void setBlnActivo(Integer blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Integer getBlnActivo() {
        return blnActivo;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
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
     * @return FecetDocExpedientePk
     */
    public FecetDocExpedientePk createPk() {
        return new FecetDocExpedientePk(idDocExpediente);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetDocExpediente: ");
        ret.append("idDocExpediente=" + idDocExpediente);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", nombre=" + nombre);
        ret.append(", rutaArchivo=" + rutaArchivo);
        ret.append(", fechaCreacion=" + fechaCreacion);
        return ret.toString();
    }

    @Override
    public int hashCode() {
        int hash = HASH_INICIAL;
        hash = SESENTAYUNO * hash + (this.idDocExpediente != null ? this.idDocExpediente.hashCode() : 0);
        hash = SESENTAYUNO * hash + (this.idPropuesta != null ? this.idPropuesta.hashCode() : 0);
        hash = SESENTAYUNO * hash + (this.rutaArchivo != null ? this.rutaArchivo.hashCode() : 0);
        hash = SESENTAYUNO * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
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
        final FecetDocExpediente other = (FecetDocExpediente) obj;
        if (this.idDocExpediente != other.idDocExpediente && (this.idDocExpediente == null || !this.idDocExpediente.equals(other.idDocExpediente))) {
            return false;
        }
        if (this.idPropuesta != other.idPropuesta && (this.idPropuesta == null || !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        if ((this.rutaArchivo == null) ? (other.rutaArchivo != null) : !this.rutaArchivo.equals(other.rutaArchivo)) {
            return false;
        }
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        
        return true;
    }

    public Timestamp getFechaCreacionTimeStamp() {
        return fechaCreacionTimeStamp != null ? new Timestamp(fechaCreacionTimeStamp.getTime()) : null;
    }

    public void setFechaCreacionTimeStamp(Timestamp fechaCreacionTimeStamp) {
        this.fechaCreacionTimeStamp = fechaCreacionTimeStamp != null ? new Timestamp(fechaCreacionTimeStamp.getTime()) : null;
    }
}
