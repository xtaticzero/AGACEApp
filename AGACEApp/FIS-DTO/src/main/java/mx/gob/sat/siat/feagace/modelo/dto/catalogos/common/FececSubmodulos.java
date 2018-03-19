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

public class FececSubmodulos implements Serializable {
    /**
     * This attribute maps to the column ID_SUBMODULO in the FECEC_SUBMODULOS table.
     */
    private BigDecimal idSubmodulo;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_SUBMODULOS table.
     */
    private String nombre;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_SUBMODULOS table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA in the FECEC_SUBMODULOS table.
     */
    private Date fecha;

    /**
     * Method 'FececSubmodulos'
     *
     */
    public FececSubmodulos() {
    }

    /**
     * Method 'getIdSubmodulo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdSubmodulo() {
        return idSubmodulo;
    }

    /**
     * Method 'setIdSubmodulo'
     *
     * @param idSubmodulo
     */
    public void setIdSubmodulo(BigDecimal idSubmodulo) {
        this.idSubmodulo = idSubmodulo;
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
        return (fecha != null) ? (Date)fecha.clone() : null;                
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
     * @return FececSubmodulosPk
     */
    public FececSubmodulosPk createPk() {
        return new FececSubmodulosPk(idSubmodulo);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececSubmodulos: ");
        ret.append("idSubmodulo=").append(idSubmodulo);
        ret.append(", nombre=").append(nombre);
        ret.append(", descripcion=").append(descripcion);
        ret.append(", fecha=").append(fecha);
        return ret.toString();
    }

}
