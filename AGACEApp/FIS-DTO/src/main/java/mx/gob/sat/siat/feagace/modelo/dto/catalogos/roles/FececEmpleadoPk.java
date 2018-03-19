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
 * This class represents the primary key of the FECEC_EMPLEADO table.
 */
public class FececEmpleadoPk extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -4132080984904377867L;

    private BigDecimal idEmpleado;

    /**
     * Sets the value of idEmpleado
     */
    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Gets the value of idEmpleado
     */
    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Method 'FececEmpleadoPk'
     * 
     */
    public FececEmpleadoPk() {
    }

    /**
     * Method 'FececEmpleadoPk'
     * 
     * @param idEmpleado
     */
    public FececEmpleadoPk(final BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

}
