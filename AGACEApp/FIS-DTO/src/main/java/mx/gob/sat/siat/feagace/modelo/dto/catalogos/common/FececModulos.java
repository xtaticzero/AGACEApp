/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececModulos extends BaseModel implements Serializable {

    private static final long serialVersionUID = -5000444394790481670L;
    /**
     * This attribute maps to the column ID_MODULO in the FECEC_MODULOS table.
     */
    private BigDecimal idModulo;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_MODULOS table.
     */
    private String nombre;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_MODULOS table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA in the FECEC_MODULOS table.
     */
    private Date fecha;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECEC_MODULOS table.
     */
    private Date fechaBaja;

    /**
     * Method 'FececModulos'
     *
     */
    public FececModulos() {
    }

    public FececModulos(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    

    /**
     * Method 'getIdModulo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdModulo() {
        return idModulo;
    }

    /**
     * Method 'setIdModulo'
     *
     * @param idModulo
     */
    public void setIdModulo(BigDecimal idModulo) {
        this.idModulo = idModulo;
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
     * Method 'getDescripcion'
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method 'setDescripcion'
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'getFecha'
     *
     * @return Date
     */
    public Date getFecha() {
        return (fecha != null) ? (Date) fecha.clone() : null;
    }

    /**
     * Method 'setFecha'
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
    }

    /**
     * Method 'createPk'
     *
     * @return FececModulosPk
     */
    public FececModulosPk createPk() {
        return new FececModulosPk(idModulo);
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String toString() {
        return "FececModulos{" + "idModulo=" + idModulo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + ", fechaBaja=" + fechaBaja + '}';
    }

}
