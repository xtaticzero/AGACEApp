/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

public class FeceaOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_OPERACION in the FECEA_OPERACIONES
     * table.
     */
    private BigDecimal idOperacion;

    /**
     * This attribute maps to the column ID_MODULO in the FECEA_OPERACIONES
     * table.
     */
    private BigDecimal idModulo;

    /**
     * This attribute maps to the column ID_SUBMODULO in the FECEA_OPERACIONES
     * table.
     */
    private BigDecimal idSubmodulo;

    /**
     * This attribute maps to the column ID_ACCION in the FECEA_OPERACIONES
     * table.
     */
    private BigDecimal idAccion;

    /**
     * This attribute maps to the column FECHA in the FECEA_OPERACIONES table.
     */
    private Date fecha;

    /**
     * Method 'FeceaOperaciones'
     * 
     */
    public FeceaOperaciones() {
    }

    /**
     * Method 'getIdOperacion'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdOperacion() {
        return idOperacion;
    }

    /**
     * Method 'setIdOperacion'
     * 
     * @param idOperacion
     */
    public void setIdOperacion(BigDecimal idOperacion) {
        this.idOperacion = idOperacion;
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
     * @return FeceaOperacionesPk
     */
    public FeceaOperacionesPk createPk() {
        return new FeceaOperacionesPk(idOperacion);
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FeceaOperaciones: ");
        ret.append("idOperacion=");
        ret.append(idOperacion);
        ret.append(", idModulo=");
        ret.append(idModulo);
        ret.append(", idSubmodulo=");
        ret.append(idSubmodulo);
        ret.append(", idAccion=");
        ret.append(idAccion);
        ret.append(", fecha=");
        ret.append(fecha);
        return ret.toString();
    }

}
