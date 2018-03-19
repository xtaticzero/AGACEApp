/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECEC_ACCIONES table.
 */
public class FececAccionesPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = 356891169969413915L;
    
    private BigDecimal idAccion;

    /**
     * Sets the value of idAccion
     */
    public void setIdAccion(BigDecimal idAccion) {
        this.idAccion = idAccion;
    }

    /**
     * Gets the value of idAccion
     */
    public BigDecimal getIdAccion() {
        return idAccion;
    }

    /**
     * Method 'FececAccionesPk'
     * 
     */
    public FececAccionesPk() {
    }

    /**
     * Method 'FececAccionesPk'
     * 
     * @param idAccion
     */
    public FececAccionesPk(final BigDecimal idAccion) {
        this.idAccion = idAccion;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececAccionesPk: ");
        ret.append("idAccion=").append(idAccion);
        return ret.toString();
    }

}
