package mx.gob.sat.siat.feagace.modelo.dto.common.tiempos.model;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


/**
 * DTO para la tabla de tiempos de los plazos
 * @author eolf
 */
public class TiempoDTO extends BaseModel {

    @SuppressWarnings("compatibility:-9140365307350170813")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idTiempo;
    
    private String clave;
    
    private BigDecimal idTipoTiempo;
    
    private BigDecimal idAgrupadorTiempo;
    
    private int tiempo;
    
    private BigDecimal idTipoPlazo;
    
    private BigDecimal idMetodo;
    
    private Date fechaCreacion;
    
    private Date fechaBaja;
    
    private TipoTiempoDTO tipoTiempoDTO;
    
    private int idObjeto;

    public TiempoDTO() {
        super();
        tipoTiempoDTO = new TipoTiempoDTO();
    }

    public void setIdTiempo(BigDecimal idTiempo) {
        this.idTiempo = idTiempo;
    }

    public BigDecimal getIdTiempo() {
        return idTiempo;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setIdTipoTiempo(BigDecimal idTipoTiempo) {
        this.idTipoTiempo = idTipoTiempo;
    }

    public BigDecimal getIdTipoTiempo() {
        return idTipoTiempo;
    }

    public void setIdAgrupadorTiempo(BigDecimal idAgrupadorTiempo) {
        this.idAgrupadorTiempo = idAgrupadorTiempo;
    }

    public BigDecimal getIdAgrupadorTiempo() {
        return idAgrupadorTiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setIdTipoPlazo(BigDecimal idTipoPlazo) {
        this.idTipoPlazo = idTipoPlazo;
    }

    public BigDecimal getIdTipoPlazo() {
        return idTipoPlazo;
    }

    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    public void setTipoTiempoDTO(TipoTiempoDTO tipoTiempoDTO) {
        this.tipoTiempoDTO = tipoTiempoDTO;
    }

    public TipoTiempoDTO getTipoTiempoDTO() {
        return tipoTiempoDTO;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public int getIdObjeto() {
        return idObjeto;
    }
}
