/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.io.Serializable;

import java.util.*;

import java.math.BigDecimal;


public class FececEmpleadoAciace implements Serializable {
    /**
     * This attribute maps to the column ID_EMPLEADO_ACIACE in the FECEC_EMPLEADO_ACIACE table.
     */
    private BigDecimal idEmpleadoAciace;

    /**
     * This attribute maps to the column RFC in the FECEC_EMPLEADO_ACIACE table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_EMPLEADO_ACIACE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column CORREO in the FECEC_EMPLEADO_ACIACE table.
     */
    private String correo;

    /**
     * Method 'FececEmpleadoAciace'
     *
     */
    public FececEmpleadoAciace() {
    }

    /**
     * Method 'getIdEmpleadoAciace'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEmpleadoAciace() {
        return idEmpleadoAciace;
    }

    /**
     * Method 'setIdEmpleadoAciace'
     *
     * @param idEmpleadoAciace
     */
    public void setIdEmpleadoAciace(final BigDecimal idEmpleadoAciace) {
        this.idEmpleadoAciace = idEmpleadoAciace;
    }

    /**
     * Method 'getRfc'
     *
     * @return String
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Method 'setRfc'
     *
     * @param rfc
     */
    public void setRfc(final String rfc) {
        this.rfc = rfc;
    }

    /**
     * Method 'getNombre'
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     *
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method 'getCorreo'
     *
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Method 'setCorreo'
     *
     * @param correo
     */
    public void setCorreo(final String correo) {
        this.correo = correo;
    }

    /**
     * Method 'createPk'
     *
     * @return FececEmpleadoAciacePk
     */
    public FececEmpleadoAciacePk createPk() {
        return new FececEmpleadoAciacePk(idEmpleadoAciace);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FececEmpleadoAciace: ");
        ret.append("idEmpleadoAciace=" + idEmpleadoAciace);
        ret.append(", rfc=" + rfc);
        ret.append(", nombre=" + nombre);
        ret.append(", correo=" + correo);
        return ret.toString();
    }

}
