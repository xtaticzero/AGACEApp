package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_ESTATUS_REASIGNACION table.
 */
public class FececEstatusReasignacionPk implements Serializable {
    @SuppressWarnings("compatibility:-5571412916322035214")
    private static final long serialVersionUID = 1L;
    private BigDecimal idEstatus;

    /**
     * Sets the value of idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * Gets the value of idEstatus
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'FececEstatusReasignacionPk'
     *
     */
    public FececEstatusReasignacionPk() {
    }

    /**
     * Method 'FececEstatusReasignacionPk'
     *
     * @param idEstatus
     */
    public FececEstatusReasignacionPk(final BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

}
