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
 * This class represents the primary key of the FECEC_SUBADMINISTRADOR table.
 */
public class FececSubadministradorPk implements Serializable {
    private BigDecimal idSubadministrador;

    /**
     * Sets the value of idSubadministrador
     */
    public void setIdSubadministrador(final BigDecimal idSubadministrador) {
        this.idSubadministrador = idSubadministrador;
    }

    /**
     * Gets the value of idSubadministrador
     */
    public BigDecimal getIdSubadministrador() {
        return idSubadministrador;
    }

    /**
     * Method 'FececSubadministradorPk'
     *
     */
    public FececSubadministradorPk() {
    }

    /**
     * Method 'FececSubadministradorPk'
     *
     * @param idSubadministrador
     */
    public FececSubadministradorPk(final BigDecimal idSubadministrador) {
        this.idSubadministrador = idSubadministrador;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececSubadministradorPk: ");
        ret.append("idSubadministrador=").append(idSubadministrador);
        return ret.toString();
    }

}
