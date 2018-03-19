package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;

public class AdministradorReasignacion {
    
    private BigDecimal idAdministrador;
    private String rfcAdministrador;
    private String nombreAdministrador;

    public void setIdAdministrador(BigDecimal idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public BigDecimal getIdAdministrador() {
        return idAdministrador;
    }

    public void setRfcAdministrador(String rfcAdministrador) {
        this.rfcAdministrador = rfcAdministrador;
    }

    public String getRfcAdministrador() {
        return rfcAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }
}
