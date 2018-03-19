/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.insumos;

public class ContadorAsignadosAdministrador {

    private String rfcAdministrador;
    private String nombreAdministrador;
    private String entidad;
    private int totalPropuestasAsignadas;

    public void setRfcAdministrador(final String rfcAdministrador) {
        this.rfcAdministrador = rfcAdministrador;
    }

    public String getRfcAdministrador() {
        return rfcAdministrador;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public int getTotalPropuestasAsignadas() {
        return totalPropuestasAsignadas;
    }

    public void setTotalPropuestasAsignadas(int totalPropuestasAsignadas) {
        this.totalPropuestasAsignadas = totalPropuestasAsignadas;
    }

    public void setNombreAdministrador(final String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

}
