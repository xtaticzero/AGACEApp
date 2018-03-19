/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * This class represents the primary key of the FECEC_TIPO_PROPUESTA table.
 */
public class FececTipoPropuestaPk implements Serializable {
    private BigDecimal idTipoPropuesta;

    /** 
     * Sets the value of idTipoPropuesta
     */
    public void setIdTipoPropuesta(final BigDecimal idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }

    /** 
     * Gets the value of idTipoPropuesta
     */
    public BigDecimal getIdTipoPropuesta() {
        return idTipoPropuesta;
    }

    /**
     * Method 'FececTipoPropuestaPk'
     * 
     */
    public FececTipoPropuestaPk() {
    }

    /**
     * Method 'FececTipoPropuestaPk'
     * 
     * @param idTipoPropuesta
     */
    public FececTipoPropuestaPk(final BigDecimal idTipoPropuesta) {
        this.idTipoPropuesta = idTipoPropuesta;
    }


    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dto.FececTipoPropuestaPk: " );
        ret.append( "idTipoPropuesta=" + idTipoPropuesta );
        return ret.toString();
    }

}
