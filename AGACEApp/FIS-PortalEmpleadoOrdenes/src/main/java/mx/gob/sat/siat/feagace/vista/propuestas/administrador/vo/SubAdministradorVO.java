package mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubAdministradorVO implements Serializable{
    
    private static final long serialVersionUID = -1138402629133024452L;
    
    private BigDecimal idSubAdmin;
    private String nombre;
    private int propAsignadas;
    private String rfc;
    private String correo;


    public void setIdSubAdmin(BigDecimal idSubAdmin) {
        this.idSubAdmin = idSubAdmin;
    }

    public BigDecimal getIdSubAdmin() {
        return idSubAdmin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPropAsignadas(int propAsignadas) {
        this.propAsignadas = propAsignadas;
    }

    public int getPropAsignadas() {
        return propAsignadas;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }
}
