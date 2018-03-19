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
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececPrioridad extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la columna ID_PRIORIDAD de la tabla FECEC_PRIORIDAD
     */
    private int idPrioridad;

    /**
     * Este atributo mapea la columna ID_GENERAL de la tabla FECEC_PRIORIDAD
     */
    private BigDecimal idGeneral;
    
    /**
     * Este atributo mapea la columna PRIORIDAD de la tabla FECEC_PRIORIDAD
     */
    private String prioridad;

    /**
     * Este atributo mapea la columna FECHA_INICIO de la tabla FECEC_PRIORIDAD
     */
    private Date fechaInicio;

    /**
     * Este atributo mapea la columna FECHA_FIN de la tabla FECEC_PRIORIDAD
     */
    private Date fechaFin;

    /**
     * Este atributo mapea la columna BLN_ACTIVO de la tabla FECEC_PRIORIDAD
     */
    private int blnActivo;

    /**
     * Metodo setIdPrioridad
     * 
     * @param idPrioridad
     */
    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    /**
     * Metodo getIdPrioridad
     * 
     * @return int
     */
    public int getIdPrioridad() {
        return idPrioridad;
    }

    /**
     * Metodo setPrioridad
     * 
     * @param prioridad
     */
    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * Metodo getPrioridad
     * 
     * @return String
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Metodo setFechaInicio
     * 
     * @param fechaInicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    /**
     * Metodo getFechaInicio
     * 
     * @return Date
     */
    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    /**
     * Metodo setFechaFin
     * 
     * @param fechaFin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    /**
     * Metodo getFechaFin
     * 
     * @return Date
     */
    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    /**
     * Metodo setBlnActivo
     * 
     * @param blnActivo
     */
    public void setBlnActivo(int blnActivo) {
        this.blnActivo = blnActivo;
    }

    /**
     * Metodo getBlnActivo
     * 
     * @return int
     */
    public int getBlnActivo() {
        return blnActivo;
    }

    public BigDecimal getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(BigDecimal idGeneral) {
        this.idGeneral = idGeneral;
    }
    
    
    
}
