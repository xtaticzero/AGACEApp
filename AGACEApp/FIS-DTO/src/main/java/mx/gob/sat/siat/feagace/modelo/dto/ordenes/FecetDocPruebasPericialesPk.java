package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetDocPruebasPericialesPk implements Serializable {
    @SuppressWarnings("compatibility:2171766277237434173")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idDocPruebasPericiales;

    public FecetDocPruebasPericialesPk() {
    }

    public void setIdDocPruebasPericiales(BigDecimal idDocPruebasPericiales) {
        this.idDocPruebasPericiales = idDocPruebasPericiales;
    }

    public BigDecimal getIdDocPruebasPericiales() {
        return idDocPruebasPericiales;
    }
    
    public FecetDocPruebasPericialesPk(final BigDecimal idDocPruebasPericiales) {
        this.idDocPruebasPericiales = idDocPruebasPericiales;
    }
}
