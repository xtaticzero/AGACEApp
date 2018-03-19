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
 * This class represents the primary key of the FECET_RETROALIMENTACION table.
 */
public class FecetRetroalimentacionPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -7733089588085358807L;

    private BigDecimal idRetroalimentacion;

    /**
     * Sets the value of idRetroalimentacion
     */
    public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    /**
     * Gets the value of idRetroalimentacion
     */
    public BigDecimal getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    /**
     * Method 'FecetRetroalimentacionPk'
     *
     */
    public FecetRetroalimentacionPk() {
    }

    /**
     * Method 'FecetRetroalimentacionPk'
     *
     * @param idRetroalimentacion
     */
    public FecetRetroalimentacionPk(final BigDecimal idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

}
