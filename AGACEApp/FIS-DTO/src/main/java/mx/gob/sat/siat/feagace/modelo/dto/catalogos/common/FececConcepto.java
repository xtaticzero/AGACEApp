/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.io.Serializable;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececConcepto extends BaseModel implements Serializable{

    @SuppressWarnings("compatibility:-4468900403761397183")
    private static final long serialVersionUID = 3255292054777504136L;
    
    /**
     * Este atributo mapea la columna ID_CONCEPTO de la tabla FECEC_CONCEPTO
     */
    private int idConcepto;
    
    /**
     * Este atributo mapea la columna DESCRIPCION de la tabla FECEC_CONCEPTO
     */
    private String descripcion;
    
    /**
     * Este atributo mapea la columna FECHA_INICIO de la tabla FECEC_CONCEPTO
     */
    private Date fechaInicio;
    
    /**
     * Este atributo mapea la columna FECHA_FIN de la tabla FECEC_CONCEPTO
     */
    private Date fechaFin;
    
    /**
     * Este atributo mapea la columna BLN_ACTIVO de la tabla FECEC_CONCEPTO
     */
    private int blnActivo;

    /**
     * Metodo setIdConcepto
     * @param idConcepto
     */
    public void setIdConcepto(int idConcepto) {
        this.idConcepto = idConcepto;
    }

    /**
    * Metodo getIdConcepto
    * @return int
    */
    public int getIdConcepto() {
        return idConcepto;
    }

    /**
     * Metodo setDescripcion
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
    * Metodo getDescripcion
    * @return String
    */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo setFechaInicio
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    /**
    * Metodo getFechaInicio
    * @return Date
    */
    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date)fechaInicio.clone() : null;
    }

    /**
     * Metodo setFechaFin
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    /**
    * Metodo getFechaFin
    * @return Date
    */
    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    /**
     * Metodo setBlnActivo
     * @param blnActivo
     */
    public void setBlnActivo(int blnActivo) {
        this.blnActivo = blnActivo;
    }

    /**
    * Metodo getBlnActivo
    * @return int
    */
    public int getBlnActivo() {
        return blnActivo;
    }
}
