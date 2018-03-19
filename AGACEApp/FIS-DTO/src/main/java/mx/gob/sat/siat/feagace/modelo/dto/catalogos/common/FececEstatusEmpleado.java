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

public class FececEstatusEmpleado implements Serializable {
    /**
     * This attribute maps to the column ID_ESTATUS_EMPLEADO in the FECEC_ESTATUS_EMPLEADO table.
     */
    private BigDecimal idEstatusEmpleado;

    /**
     * This attribute maps to the column DESCRIPCION in the FECEC_ESTATUS_EMPLEADO table.
     */
    private String descripcion;

    /**
     * Method 'FececEstatusEmpleado'
     *
     */
    public FececEstatusEmpleado() {
    }

    /**
     * Method 'getIdEstatusEmpleado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEstatusEmpleado() {
        return idEstatusEmpleado;
    }

    /**
     * Method 'setIdEstatusEmpleado'
     *
     * @param idEstatusEmpleado
     */
    public void setIdEstatusEmpleado(BigDecimal idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
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
     * @return FececEstatusEmpleadoPk
     */
    public FececEstatusEmpleadoPk createPk() {
        return new FececEstatusEmpleadoPk(idEstatusEmpleado);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececEstatusEmpleado: ");
        ret.append("idEstatusEmpleado=").append(idEstatusEmpleado);
        ret.append(", descripcion=").append(descripcion);
        return ret.toString();
    }

}
