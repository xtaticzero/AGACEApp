/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

/**
 * This class represents the primary key of the FECEC_EMPLEADO_ACIACE table.
 */
public class FececEmpleadoAciacePk implements Serializable {
    private BigDecimal idEmpleadoAciace;

    /**
     * Sets the value of idEmpleadoAciace
     */
    public void setIdEmpleadoAciace(final BigDecimal idEmpleadoAciace) {
        this.idEmpleadoAciace = idEmpleadoAciace;
    }

    /**
     * Gets the value of idEmpleadoAciace
     */
    public BigDecimal getIdEmpleadoAciace() {
        return idEmpleadoAciace;
    }

    /**
     * Method 'FececEmpleadoAciacePk'
     *
     */
    public FececEmpleadoAciacePk() {
    }

    /**
     * Method 'FececEmpleadoAciacePk'
     *
     * @param idEmpleadoAciace
     */
    public FececEmpleadoAciacePk(final BigDecimal idEmpleadoAciace) {
        this.idEmpleadoAciace = idEmpleadoAciace;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececEmpleadoAciacePk: ");
        ret.append("idEmpleadoAciace=" + idEmpleadoAciace);
        return ret.toString();
    }

}
