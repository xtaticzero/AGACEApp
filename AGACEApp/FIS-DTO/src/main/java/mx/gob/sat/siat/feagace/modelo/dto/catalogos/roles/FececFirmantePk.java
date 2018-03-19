/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_FIRMANTE table.
 */
public class FececFirmantePk implements Serializable {
    private BigDecimal idFirmante;

    /**
     * Method 'FececFirmantePk'
     *
     */
    public FececFirmantePk() {
    }

    /**
     * Method 'FececFirmantePk'
     *
     * @param idFirmante
     */
    public FececFirmantePk(final BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    /**
     * Sets the value of idFirmante
     */
    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    /**
     * Gets the value of idFirmante
     */
    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececFirmantePk: ");
        ret.append("idFirmante=").append(idFirmante);
        return ret.toString();
    }

}
