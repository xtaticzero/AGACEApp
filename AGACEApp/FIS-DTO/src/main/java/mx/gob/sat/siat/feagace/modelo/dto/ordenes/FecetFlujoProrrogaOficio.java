/*
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;

public class FecetFlujoProrrogaOficio extends BaseModel {
    /** 
     * This attribute maps to the column ID_FLUJO_PRORROGA_OFICIO in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private BigDecimal idFlujoProrrogaOficio;

    /** 
     * This attribute maps to the column ID_PRORROGA_OFICIO in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private BigDecimal idProrrogaOficio;

    /** 
     * This attribute maps to the column FECHA_CREACION in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private Date fechaCreacion;

    /** 
     * This attribute maps to the column JUSTIFICACION in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private String justificacion;

    /** 
     * This attribute maps to the column JUSTIFICACION_FIRMANTE in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private String justificacionFirmante;

    /** 
     * This attribute maps to the column APROBADA in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private Boolean aprobada;

    /** 
     * This attribute maps to the column ID_ESTATUS in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private BigDecimal idEstatus;
    
    
    private FececEstatus fececEstatus;

    /** 
     * This attribute maps to the column FECHA_RECHAZO_FIRMANTE in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    private Date fechaRechazoFirmante;
    
    /**
     * This attribute does not map column. Just to fill values in execution time.
     */
    private Integer totalAnexos;
    
   

    /**
     * Method 'FecetFlujoProrrogaOficio'
     * 
     */
    public FecetFlujoProrrogaOficio() {
    }

    /**
     * Method 'getIdFlujoProrrogaOficio'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdFlujoProrrogaOficio() {
        return idFlujoProrrogaOficio;
    }

    /**
     * Method 'setIdFlujoProrrogaOficio'
     * 
     * @param idFlujoProrrogaOficio
     */
    public void setIdFlujoProrrogaOficio(BigDecimal idFlujoProrrogaOficio) {
        this.idFlujoProrrogaOficio = idFlujoProrrogaOficio;
    }

    /**
     * Method 'getIdProrrogaOficio'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdProrrogaOficio() {
        return idProrrogaOficio;
    }

    /**
     * Method 'setIdProrrogaOficio'
     * 
     * @param idProrrogaOficio
     */
    public void setIdProrrogaOficio(BigDecimal idProrrogaOficio) {
        this.idProrrogaOficio = idProrrogaOficio;
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
     * Method 'getJustificacion'
     * 
     * @return String
     */
    public String getJustificacion() {
        return justificacion;
    }

    /**
     * Method 'setJustificacion'
     * 
     * @param justificacion
     */
    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    /**
     * Method 'getJustificacionFirmante'
     * 
     * @return String
     */
    public String getJustificacionFirmante() {
        return justificacionFirmante;
    }

    /**
     * Method 'setJustificacionFirmante'
     * 
     * @param justificacionFirmante
     */
    public void setJustificacionFirmante(String justificacionFirmante) {
        this.justificacionFirmante = justificacionFirmante;
    }

    /**
     * Method 'getAprobada'
     * 
     * @return Boolean
     */
    public Boolean getAprobada() {
        return aprobada;
    }

    /**
     * Method 'setAprobada'
     * 
     * @param aprobada
     */
    public void setAprobada(final Boolean aprobada) {
        this.aprobada = aprobada;
    }

    /**
     * Method 'getIdEstatus'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdEstatus() {
        return idEstatus;
    }

    /**
     * Method 'setIdEstatus'
     * 
     * @param idEstatus
     */
    public void setIdEstatus(BigDecimal idEstatus) {
        this.idEstatus = idEstatus;
    }

    /**
     * Method 'getFechaRechazoFirmante'
     * 
     * @return Date
     */
    public Date getFechaRechazoFirmante() {
        return (fechaRechazoFirmante != null) ? (Date)fechaRechazoFirmante.clone() : null;
    }

    /**
     * Method 'setFechaRechazoFirmante'
     * 
     * @param fechaRechazoFirmante
     */
    public void setFechaRechazoFirmante(Date fechaRechazoFirmante) {
        this.fechaRechazoFirmante = fechaRechazoFirmante != null ? new Date(fechaRechazoFirmante.getTime()) : null;
    }
    
    
    
    
    



    public FececEstatus getFececEstatus() {
        return fececEstatus;
    }

    public void setFececEstatus(FececEstatus fececEstatus) {
        this.fececEstatus = fececEstatus;
    }
    
    

    public Integer getTotalAnexos() {
        return totalAnexos;
    }

    public void setTotalAnexos(Integer totalAnexos) {
        this.totalAnexos = totalAnexos;
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append( "mx.gob.sat.siat.feagace.modelo.dto.FecetFlujoProrrogaOficio: " );
        ret.append( "idFlujoProrrogaOficio=").append(idFlujoProrrogaOficio );
        ret.append( ", idProrrogaOficio=").append(idProrrogaOficio );
        ret.append( ", fechaCreacion=").append(fechaCreacion );
        ret.append( ", justificacion=").append(justificacion );
        ret.append( ", justificacionFirmante=").append(justificacionFirmante );
        ret.append( ", aprobada=").append(aprobada );
        ret.append( ", idEstatus=").append(idEstatus );
        ret.append( ", fechaRechazoFirmante=").append(fechaRechazoFirmante );
        return ret.toString();
    }

}
