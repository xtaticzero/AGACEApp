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

/**
 * This class represents the primary key of the FECET_BITACORA table.
 */
public class FecetBitacoraPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idBitacora;

    /**
     * Sets the value of idBitacora
     */
    public void setIdBitacora(BigDecimal idBitacora) {
        this.idBitacora = idBitacora;
    }

    /**
     * Gets the value of idBitacora
     */
    public BigDecimal getIdBitacora() {
        return idBitacora;
    }

    /**
     * Method 'FecetBitacoraPk'
     * 
     */
    public FecetBitacoraPk() {
    }

    /**
     * Method 'FecetBitacoraPk'
     * 
     * @param idBitacora
     */
    public FecetBitacoraPk(final BigDecimal idBitacora) {
        this.idBitacora = idBitacora;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetBitacoraPk: ");
        ret.append("idBitacora=");
        ret.append(idBitacora);
        return ret.toString();
    }

}
