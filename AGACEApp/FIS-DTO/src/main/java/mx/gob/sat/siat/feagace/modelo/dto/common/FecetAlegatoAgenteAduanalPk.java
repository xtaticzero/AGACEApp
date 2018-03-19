package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;

public class FecetAlegatoAgenteAduanalPk implements Serializable  {

    @SuppressWarnings("compatibility:5473435790386840591")
    private static final long serialVersionUID = 1L;
    private BigDecimal idAlegatoAgenteAduanal;
    
    public FecetAlegatoAgenteAduanalPk() {       
    }
    
    public FecetAlegatoAgenteAduanalPk(final BigDecimal idAlegatoAgenteAduanal) {
        this.idAlegatoAgenteAduanal = idAlegatoAgenteAduanal;
    }

    public void setIdAlegatoAgenteAduanal(BigDecimal idAlegatoAgenteAduanal) {
        this.idAlegatoAgenteAduanal = idAlegatoAgenteAduanal;
    }

    public BigDecimal getIdAlegatoAgenteAduanal() {
        return idAlegatoAgenteAduanal;
    }
}
