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

/**
 * This class represents the primary key of the FECEC_ESTATUS_EMPLEADO table.
 */
public class FececEstatusEmpleadoPk implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private BigDecimal idEstatusEmpleado;

    /**
     * Sets the value of idEstatusEmpleado
     */
    public void setIdEstatusEmpleado(BigDecimal idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
    }

    /**
     * Gets the value of idEstatusEmpleado
     */
    public BigDecimal getIdEstatusEmpleado() {
        return idEstatusEmpleado;
    }

    /**
     * Method 'FececEstatusEmpleadoPk'
     * 
     */
    public FececEstatusEmpleadoPk() {
    }

    /**
     * Method 'FececEstatusEmpleadoPk'
     * 
     * @param idEstatusEmpleado
     */
    public FececEstatusEmpleadoPk(final BigDecimal idEstatusEmpleado) {
        this.idEstatusEmpleado = idEstatusEmpleado;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececEstatusEmpleadoPk: ");
        ret.append("idEstatusEmpleado=").append(idEstatusEmpleado);
        return ret.toString();
    }

}
