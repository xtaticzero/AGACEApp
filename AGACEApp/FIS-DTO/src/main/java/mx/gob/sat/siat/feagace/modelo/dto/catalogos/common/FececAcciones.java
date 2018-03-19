/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;


import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececAcciones extends BaseModel {
    
    /**
     * Serial
     */
    private static final long serialVersionUID = 7884244674597894332L;

    /**
     * This attribute maps to the column ID_ACCION in the FECEC_ACCIONES table.
     */
    private BigDecimal idAccion;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_ACCIONES table.
     */
    private String nombre;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_ACCIONES table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA in the FECEC_ACCIONES table.
     */
    private Date fecha;

    /**
     * Method 'FececAcciones'
     *
     */
    public FececAcciones() {
    }

    /**
     * Method 'getIdAccion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAccion() {
        return idAccion;
    }

    /**
     * Method 'setIdAccion'
     *
     * @param idAccion
     */
    public void setIdAccion(BigDecimal idAccion) {
        this.idAccion = idAccion;
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
     * @return FececAccionesPk
     */
    public FececAccionesPk createPk() {
        return new FececAccionesPk(idAccion);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(" mx.gob.sat.siat.feagace.modelo.dto.FececAcciones: ");
        ret.append(" idAccion = ").append(idAccion);
        ret.append(", nombre = ").append(nombre);
        ret.append(", descripcion = ").append(descripcion);
        ret.append(", fecha = ").append(fecha);
        return ret.toString();
    }

}
