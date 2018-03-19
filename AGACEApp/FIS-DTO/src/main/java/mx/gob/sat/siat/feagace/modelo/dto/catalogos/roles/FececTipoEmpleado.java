package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.io.Serializable;

import java.math.BigDecimal;

public class FececTipoEmpleado implements Serializable {
    /**
     * This attribute maps to the column ID_TIPO_EMPLEADO in the FECEC_TIPO_EMPLEADO table.
     */
    private BigDecimal idTipoEmpleado;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_TIPO_EMPLEADO table.
     */
    private String descripcion;

    /**
     * Method 'FececTipoEmpleado'
     *
     */
    public FececTipoEmpleado() {
    }

    /**
     * Method 'getIdTipoEmpleado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    /**
     * Method 'setIdTipoEmpleado'
     *
     * @param idTipoEmpleado
     */
    public void setIdTipoEmpleado(BigDecimal idTipoEmpleado) {
        this.idTipoEmpleado = idTipoEmpleado;
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
     * @return FececTipoEmpleadoPk
     */
    public FececTipoEmpleadoPk createPk() {
        return new FececTipoEmpleadoPk(idTipoEmpleado);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececTipoEmpleado: ");
        ret.append("idTipoEmpleado=").append(idTipoEmpleado);
        ret.append(", descripcion=").append(descripcion);
        return ret.toString();
    }

}
