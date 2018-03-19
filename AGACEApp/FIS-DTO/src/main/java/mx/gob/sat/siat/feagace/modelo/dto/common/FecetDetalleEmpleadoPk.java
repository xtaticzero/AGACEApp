/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECET_DETALLE_EMPLEADO table.
 */
public class FecetDetalleEmpleadoPk implements Serializable {

    private static final long serialVersionUID = 1L;
    private BigDecimal idDetalleEmpleado;

    /**
     * Sets the value of idDetalleEmpleado
     */
    public void setIdDetalleEmpleado(BigDecimal idDetalleEmpleado) {
        this.idDetalleEmpleado = idDetalleEmpleado;
    }

    /**
     * Gets the value of idDetalleEmpleado
     */
    public BigDecimal getIdDetalleEmpleado() {
        return idDetalleEmpleado;
    }

    /**
     * Method 'FecetDetalleEmpleadoPk'
     * 
     */
    public FecetDetalleEmpleadoPk() {
    }

    /**
     * Method 'FecetDetalleEmpleadoPk'
     * 
     * @param idDetalleEmpleado
     */
    public FecetDetalleEmpleadoPk(final BigDecimal idDetalleEmpleado) {
        this.idDetalleEmpleado = idDetalleEmpleado;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetDetalleEmpleadoPk: ");
        ret.append("idDetalleEmpleado=");
        ret.append(idDetalleEmpleado);
        return ret.toString();
    }

}
