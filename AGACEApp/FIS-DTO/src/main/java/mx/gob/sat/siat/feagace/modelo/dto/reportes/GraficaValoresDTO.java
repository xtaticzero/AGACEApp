package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class GraficaValoresDTO extends BaseModel{

    @SuppressWarnings("compatibility:-5137156961710070184")
    private static final long serialVersionUID = 1L;
    
    private String mes;
    private String estatus;
    private String entidad;
    private String metodo;
    private String unidad;
    private BigDecimal idUnidad;
    private Integer cantidad;

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMes() {
        return mes;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public BigDecimal getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(BigDecimal idUnidad) {
        this.idUnidad = idUnidad;
    }

   
    
    
}
