package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececUnidadAdministrativa extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idUnidadAdministrativa;
    private String nombre;
    private String descripcion;
    private Boolean central;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean blnActivo;

    /**
     * @return the idUnidadAdministrativa
     */
    public BigDecimal getIdUnidadAdministrativa() {
        return idUnidadAdministrativa;
    }

    /**
     * @param idUnidadAdministrativa
     *            the idUnidadAdministrativa to set
     */
    public void setIdUnidadAdministrativa(BigDecimal idUnidadAdministrativa) {
        this.idUnidadAdministrativa = idUnidadAdministrativa;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    /**
     * @param fechaInicio
     *            the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    /**
     * @param fechaFin
     *            the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    /**
     * @return the blnActivo
     */
    public Boolean getBlnActivo() {
        return blnActivo;
    }

    /**
     * @param blnActivo
     *            the blnActivo to set
     */
    public void setBlnActivo(Boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public Boolean getCentral() {
        return central;
    }

    public void setCentral(Boolean central) {
        this.central = central;
    }
    
    

}
