/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECET_PROPUESTA table.
 */
public class FecetPropuestaPk extends BaseModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idPropuesta;

    /**
     * This attribute represents whether the primitive attribute idPropuesta is
     * null.
     */
    private boolean idPropuestaNull;

    /**
     * Sets the value of idPropuesta
     */
    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * Gets the value of idPropuesta
     */
    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    /**
     * Method 'FecetPropuestaPk'
     * 
     */
    public FecetPropuestaPk() {
    }

    /**
     * Method 'FecetPropuestaPk'
     * 
     * @param idPropuesta
     */
    public FecetPropuestaPk(final BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * Sets the value of idPropuestaNull
     */
    public void setIdPropuestaNull(boolean idPropuestaNull) {
        this.idPropuestaNull = idPropuestaNull;
    }

    /**
     * Gets the value of idPropuestaNull
     */
    public boolean isIdPropuestaNull() {
        return idPropuestaNull;
    }

}
