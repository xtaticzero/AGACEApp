package mx.gob.sat.siat.feagace.modelo.dto.insumos;


import java.io.Serializable;

import java.math.BigDecimal;

public class FececEstatusReasignacion implements Serializable {
    /**
     * This attribute maps to the column ID_ESTATUS in the FECEC_ESTATUS_REASIGNACION table.
     */
    private BigDecimal idEstatus;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_ESTATUS_REASIGNACION table.
     */
    private String descripcion;

    /**
     * Method 'FececEstatusReasignacion'
     *
     */
    public FececEstatusReasignacion() {
    }

    /**
     * Method 'getIdEstatus'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'setIdEstatus'
     *
     * @param idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * Method 'getDescripcion'
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method 'setDescripcion'
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method 'createPk'
     *
     * @return FececEstatusReasignacionPk
     */
    public FececEstatusReasignacionPk createPk() {
        return new FececEstatusReasignacionPk(idEstatus);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dao.insumos.dto.FececEstatusReasignacion: ");
        ret.append(new StringBuffer("idEstatus=").append(idEstatus));
        ret.append(new StringBuffer(", descripcion=").append(descripcion));
        return ret.toString();
    }

}
