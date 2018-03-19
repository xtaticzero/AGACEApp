package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetDetalleContribuyentePk implements Serializable {

    @SuppressWarnings("compatibility:-415533917266428157")
    private static final long serialVersionUID = 1L;
    private BigDecimal idDetalleContribuyente;

    public FecetDetalleContribuyentePk(final BigDecimal idDetalleContribuyente) {
        this.idDetalleContribuyente = idDetalleContribuyente;
    }

    public void setIdDetalleContribuyente(BigDecimal idDetalleContribuyente) {
        this.idDetalleContribuyente = idDetalleContribuyente;
    }

    public BigDecimal getIdDetalleContribuyente() {
        return idDetalleContribuyente;
    }
}
