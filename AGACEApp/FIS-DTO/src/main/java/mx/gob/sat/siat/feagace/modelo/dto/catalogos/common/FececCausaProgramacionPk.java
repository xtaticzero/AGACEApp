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

/** 
 * This class represents the primary key of the FECEC_CAUSA_PROGRAMACION table.
 */
public class FececCausaProgramacionPk implements Serializable {
    private BigDecimal idCausaProgramacion;

    /** 
     * Sets the value of idCausaProgramacion
     */
    public void setIdCausaProgramacion(final BigDecimal idCausaProgramacion) {
        this.idCausaProgramacion = idCausaProgramacion;
    }

    /** 
     * Gets the value of idCausaProgramacion
     */
    public BigDecimal getIdCausaProgramacion() {
        return idCausaProgramacion;
    }

    /**
     * Method 'FececCausaProgramacionPk'
     * 
     */
    public FececCausaProgramacionPk() {
    }

    /**
     * Method 'FececCausaProgramacionPk'
     * 
     * @param idCausaProgramacion
     */
    public FececCausaProgramacionPk(final BigDecimal idCausaProgramacion) {
        this.idCausaProgramacion = idCausaProgramacion;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dto.FececCausaProgramacionPk: " );
        ret.append( "idCausaProgramacion=").append(idCausaProgramacion );
        return ret.toString();
    }

}
