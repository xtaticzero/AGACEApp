package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetPruebasPericialesPk implements Serializable {
    @SuppressWarnings("compatibility:-6402775959170796186")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idPruebasPericiales;

    public FecetPruebasPericialesPk() {
    }
    
    public void setIdPruebasPericiales(final BigDecimal idPruebasPericiales) {
        this.idPruebasPericiales = idPruebasPericiales;
    }
    
    public BigDecimal getIdPruebasPericiales() {
        return idPruebasPericiales;
    }
    
    public FecetPruebasPericialesPk(final BigDecimal idPruebasPericiales) {
        this.idPruebasPericiales = idPruebasPericiales;
    }
}
