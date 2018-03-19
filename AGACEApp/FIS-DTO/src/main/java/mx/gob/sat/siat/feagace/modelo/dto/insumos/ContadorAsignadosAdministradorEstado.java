package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.util.List;

public class ContadorAsignadosAdministradorEstado {

    private String nombreAdministrador;
    private List<ContadorAsignadosAdministrador> contadorAsignadosAdministrador;

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public List<ContadorAsignadosAdministrador> getContadorAsignadosAdministrador() {
        return contadorAsignadosAdministrador;
    }

    public void setContadorAsignadosAdministrador(List<ContadorAsignadosAdministrador> contadorAsignadosAdministrador) {
        this.contadorAsignadosAdministrador = contadorAsignadosAdministrador;
    }

}
