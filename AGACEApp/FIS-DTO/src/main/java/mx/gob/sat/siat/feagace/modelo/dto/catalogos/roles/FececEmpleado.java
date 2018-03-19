/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleado;

public class FececEmpleado extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -6387233504322166408L;

    /**
     * This attribute maps to the column ID_EMPLEADO in the FECEC_EMPLEADO table.
     */
    private BigDecimal idEmpleado;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_EMPLEADO table.
     */
    private String nombre;

    /**
     * This attribute maps to the column RFC in the FECEC_EMPLEADO table.
     */
    private String rfc;

    /**
     * This attribute maps to the column CORREO in the FECEC_EMPLEADO table.
     */
    private String correo;

    /**
     * This attribute maps to the column ID_ESTATUS_EMPLEADO in the FECEC_EMPLEADO table.
     */
    private BigDecimal idEstatusEmpleado;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECEC_EMPLEADO table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECEC_EMPLEADO table.
     */
    private Date fechaBaja;

    private FececEstatusEmpleado fececEstatusEmpleado;
    private FececTipoEmpleado fececTipoEmpleado;
    private FecetDetalleEmpleado fecetDetalleEmpleado;
    private String sede;
    private String regional;

    /**
     * Method 'FececEmpleado'
     *
     */
    public FececEmpleado() {
    }

    /**
     * Method 'getIdEmpleado'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Method 'setIdEmpleado'
     *
     * @param idEmpleado
     */
    public void setIdEmpleado(BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public void setRfc(String rfc) {
        this.rfc = rfc;
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
    public void setCorreo(String correo) {
        this.correo = correo;
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
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     *
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    public void setFececEstatusEmpleado(FececEstatusEmpleado fececEstatusEmpleado) {
        this.fececEstatusEmpleado = fececEstatusEmpleado;
    }

    public FececEstatusEmpleado getFececEstatusEmpleado() {
        return fececEstatusEmpleado;
    }

    public void setFececTipoEmpleado(FececTipoEmpleado fececTipoEmpleado) {
        this.fececTipoEmpleado = fececTipoEmpleado;
    }

    public FececTipoEmpleado getFececTipoEmpleado() {
        return fececTipoEmpleado;
    }

    public void setFecetDetalleEmpleado(FecetDetalleEmpleado fecetDetalleEmpleado) {
        this.fecetDetalleEmpleado = fecetDetalleEmpleado;
    }

    public FecetDetalleEmpleado getFecetDetalleEmpleado() {
        return fecetDetalleEmpleado;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getSede() {
        return sede;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getRegional() {
        return regional;
    }

    /**
     * Method 'createPk'
     *
     * @return FececEmpleadoPk
     */
    public FececEmpleadoPk createPk() {
        return new FececEmpleadoPk(idEmpleado);
    }

    @Override
    public String toString() {
        return "FececEmpleado{" + "idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", rfc=" + rfc + ", correo=" + correo + ", idEstatusEmpleado=" + idEstatusEmpleado + ", fechaCreacion=" + fechaCreacion + ", fechaBaja=" + fechaBaja + ", fececEstatusEmpleado=" + fececEstatusEmpleado + ", fececTipoEmpleado=" + fececTipoEmpleado + ", fecetDetalleEmpleado=" + fecetDetalleEmpleado + ", sede=" + sede + ", regional=" + regional + '}';
    }
    
}
