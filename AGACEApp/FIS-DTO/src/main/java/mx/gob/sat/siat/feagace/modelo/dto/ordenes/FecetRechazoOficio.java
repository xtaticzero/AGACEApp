/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;


import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetRechazoOficio extends BaseModel {
    @SuppressWarnings("compatibility:-65803353566594895")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_RECHAZO_OFICIO in the FECET_RECHAZO_OFICIO table.
     */
    private BigDecimal idRechazoOficio;

    /**
     * This attribute maps to the column ID_ORDEN in the FECET_RECHAZO_OFICIO table.
     */
    private BigDecimal idOficio;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_RECHAZO_OFICIO table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column FECHA_RECHAZO in the FECET_RECHAZO_OFICIO table.
     */
    private Date fechaRechazo;

    /**
     * This attribute maps to the column ID_EMPLEADO in the FECET_RECHAZO_OFICIO table.
     */
    private BigDecimal idEmpleado;

    /**
     * This attribute maps to the column ESTATUS in the FECET_RECHAZO_OFICIO table.
     */
    private String estatus;

    /**
     * Method 'FecetRechazoOficio'
     *
     */
    public FecetRechazoOficio() {
    }

    /**
     * Method 'getIdRechazoOficio'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdRechazoOficio() {
        return idRechazoOficio;
    }

    /**
     * Method 'setIdRechazoOficio'
     *
     * @param idRechazoOficio
     */
    public void setIdRechazoOficio(final BigDecimal idRechazoOficio) {
        this.idRechazoOficio = idRechazoOficio;
    }

    /**
     * Method 'getIdOficio'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdOficio() {
        return idOficio;
    }

    /**
     * Method 'setIdOficio'
     *
     * @param idOficio
     */
    public void setIdOficio(final BigDecimal idOficio) {
        this.idOficio = idOficio;
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
     * Method 'getFechaRechazo'
     *
     * @return Date
     */
    public Date getFechaRechazo() {
        return (fechaRechazo != null) ? (Date)fechaRechazo.clone() : null;                
    }

    /**
     * Method 'setFechaRechazo'
     *
     * @param fechaRechazo
     */
    public void setFechaRechazo(Date fechaRechazo) {
        this.fechaRechazo = fechaRechazo != null ? new Date(fechaRechazo.getTime()) : null;
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
    public void setIdEmpleado(final BigDecimal idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Method 'getEstatus'
     *
     * @return String
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.dto.FecetRechazoOficio: ");
        ret.append("idRechazoOficio=").append(idRechazoOficio);
        ret.append(", idOficio=").append(idOficio);
        ret.append(", idEmpleado=").append(idEmpleado);
        ret.append(", descripcion=").append(descripcion);
        ret.append(", fechaRechazo=").append(fechaRechazo);
        ret.append(", estatus=").append(estatus);
        return ret.toString();
    }
    
}
