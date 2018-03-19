/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececFeriados extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column IDFERIADO in the FECECFERIADOS table.
     */
    private BigDecimal idFeriado;

    /**
     * This attribute maps to the column FECHA in the FECECFERIADOS table.
     */
    private Date fecha;

    /**
     * This attribute maps to the column DESCRIPCION in the FECECFERIADOS table.
     */
    private String descripcion;

    /**
     * Method 'FececFeriados'
     *
     */
    public FececFeriados() {
    }

    /**
     * Method 'getIdFeriado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdFeriado() {
        return idFeriado;
    }

    /**
     * Method 'setIdFeriado'
     *
     * @param idFeriado
     */
    public void setIdFeriado(final BigDecimal idFeriado) {
        this.idFeriado = idFeriado;
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
    public void setFecha(final Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
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
    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'createPk'
     *
     * @return FececFeriadosPk
     */
    public FececFeriadosPk createPk() {
        return new FececFeriadosPk(idFeriado);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececFeriados: ");
        ret.append("idFeriado=" + idFeriado);
        ret.append(", fecha=" + fecha);
        ret.append(", descripcion=" + descripcion);
        return ret.toString();
    }

}
