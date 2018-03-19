package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

public class ImpuestoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal idImpuesto;
    private String descripcion;
    private String monto;
    private String periodoInicio;
    private String periodoFin;
    private String concepto;

    public void setIdImpuesto(BigDecimal idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public BigDecimal getIdImpuesto() {
        return idImpuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMonto() {
        return monto;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoFin(String periodoFin) {
        this.periodoFin = periodoFin;
    }

    public String getPeriodoFin() {
        return periodoFin;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

}
