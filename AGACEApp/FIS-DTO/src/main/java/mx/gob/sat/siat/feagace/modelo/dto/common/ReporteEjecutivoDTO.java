package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;

public class ReporteEjecutivoDTO {

    private BigDecimal idMetodo;
    private String metodo;
    private String area;
    private BigDecimal estado;
    private BigDecimal numeroOrdenes;


    public void setIdMetodo(BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }

    public BigDecimal getEstado() {
        return estado;
    }

    public void setNumeroOrdenes(BigDecimal numeroOrdenes) {
        this.numeroOrdenes = numeroOrdenes;
    }

    public BigDecimal getNumeroOrdenes() {
        return numeroOrdenes;
    }
}
