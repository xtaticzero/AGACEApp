package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.Serializable;
import java.math.BigDecimal;

public class FecetAdminOficioPk implements Serializable {
    
    private BigDecimal idAdminOficio;

    private boolean idAdminOficioNull;
    
    public FecetAdminOficioPk() {
    }

    public FecetAdminOficioPk(final BigDecimal idAdminOficio) {
        this.idAdminOficio = idAdminOficio;
    }

    public BigDecimal getIdAdminOficio() {
        return idAdminOficio;
    }

    public void setIdAdminOficio(BigDecimal idAdminOficio) {
        this.idAdminOficio = idAdminOficio;
    }

    public boolean isIdAdminOficioNull() {
        return idAdminOficioNull;
    }

    public void setIdAdminOficioNull(boolean idAdminOficioNull) {
        this.idAdminOficioNull = idAdminOficioNull;
    }
    
    public String toString() {
        StringBuffer ret = new StringBuffer();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficioPk: ");
        ret.append("idAdminOficio=");
        ret.append(idAdminOficio);
        return ret.toString();
    }

}
