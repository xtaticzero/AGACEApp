package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * This class represents the primary key of the FECET_REASIGNACION_INSUMO table.
 */
public class FecetReasignacionInsumoPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -8999952090269178563L;
    private BigDecimal idReasignacion;

    /**
     * Sets the value of idReasignacion
     */
    public void setIdReasignacion(BigDecimal idReasignacion) {
        this.idReasignacion = idReasignacion;
    }

    /**
     * Gets the value of idReasignacion
     */
    public BigDecimal getIdReasignacion() {
        return idReasignacion;
    }

    /**
     * Method 'FecetReasignacionInsumoPk'
     *
     */
    public FecetReasignacionInsumoPk() {
    }

    /**
     * Method 'FecetReasignacionInsumoPk'
     *
     * @param idReasignacion
     */
    public FecetReasignacionInsumoPk(final BigDecimal idReasignacion) {
        this.idReasignacion = idReasignacion;
    }
}
