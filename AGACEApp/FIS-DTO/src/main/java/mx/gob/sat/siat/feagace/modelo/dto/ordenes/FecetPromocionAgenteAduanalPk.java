package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetPromocionAgenteAduanalPk implements Serializable {
    @SuppressWarnings("compatibility:-5930331409272360658")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idPromocionAgenteAduanal;
    
    public FecetPromocionAgenteAduanalPk(){
    }

    public FecetPromocionAgenteAduanalPk(final BigDecimal idPromocionAgenteAduanal) {
        this.idPromocionAgenteAduanal =idPromocionAgenteAduanal;
    }

    public void setIdPromocionAgenteAduanal(BigDecimal idPromocionAgenteAduanal) {
        this.idPromocionAgenteAduanal = idPromocionAgenteAduanal;
    }

    public BigDecimal getIdPromocionAgenteAduanal() {
        return idPromocionAgenteAduanal;
    }
}
