/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECEC_ADMINISTRADOR table.
 */
public class FececAdministradorPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8708379006479286651L;

    private BigDecimal idAdministrador;

    /**
     * Sets the value of idAdministrador
     */
    public void setIdAdministrador(final BigDecimal idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    /**
     * Gets the value of idAdministrador
     */
    public BigDecimal getIdAdministrador() {
        return idAdministrador;
    }

    /**
     * Method 'FececAdministradorPk'
     *
     */
    public FececAdministradorPk() {
    }

    /**
     * Method 'FececAdministradorPk'
     *
     * @param idAdministrador
     */
    public FececAdministradorPk(final BigDecimal idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

}
