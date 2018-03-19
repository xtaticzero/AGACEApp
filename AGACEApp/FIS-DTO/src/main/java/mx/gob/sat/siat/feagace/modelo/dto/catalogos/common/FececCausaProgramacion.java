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

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececCausaProgramacion extends BaseModel implements Serializable {


    @SuppressWarnings("compatibility:-7286845352681368632")
    private static final long serialVersionUID = 5599027779211599066L;

    /**
     * This attribute maps to the column ID_CAUSA_PROGRAMACION in the FECEC_CAUSA_PROGRAMACION table.
     */
    private BigDecimal idCausaProgramacion;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_CAUSA_PROGRAMACION table.
     */
    private String descripcion;

    /**
     * Method 'FececCausaProgramacion'
     *
     */
    public FececCausaProgramacion() {
    }

    /**
     * Method 'getIdCausaProgramacion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdCausaProgramacion() {
        return idCausaProgramacion;
    }

    /**
     * Method 'setIdCausaProgramacion'
     *
     * @param idCausaProgramacion
     */
    public void setIdCausaProgramacion(final BigDecimal idCausaProgramacion) {
        this.idCausaProgramacion = idCausaProgramacion;
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
     * @return FececCausaProgramacionPk
     */
    public FececCausaProgramacionPk createPk() {
        return new FececCausaProgramacionPk(idCausaProgramacion);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececCausaProgramacion: ");
        ret.append("idCausaProgramacion=").append(idCausaProgramacion);
        ret.append(", descripcion=").append(descripcion);
        return ret.toString();
    }

}
