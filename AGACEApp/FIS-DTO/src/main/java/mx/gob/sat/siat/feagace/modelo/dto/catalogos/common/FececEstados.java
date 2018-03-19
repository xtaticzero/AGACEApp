package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FececEstados extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ESTADOS in the FECEC_ESTADOS table.
     */
    private Long idEstado;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_ESTADOS table.
     */
    private String nombre;

    /**
     * This attribute maps to the column FECHA_INICIO in the FECEC_ESTADOS table.
     */
    private Date fechaInicio;

    /**
     * This attribute maps to the column FECHAFIN in the FECEC_ESTADOS table.
     */
    private Date fechaFin;

    /**
     * This attribute maps to the column BLN_ACTIVO in the FECEC_ESTADOS table.
     */
    private int activo;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

}
