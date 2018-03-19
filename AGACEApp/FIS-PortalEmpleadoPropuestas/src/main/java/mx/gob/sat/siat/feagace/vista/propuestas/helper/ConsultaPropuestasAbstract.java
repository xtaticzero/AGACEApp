package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.io.Serializable;

public class ConsultaPropuestasAbstract implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private boolean flgMostrarOpciones;
    private Integer idOpcion;
    private boolean flgMostrarComboPerfiles;
    
    public boolean isFlgMostrarOpciones() {
        return flgMostrarOpciones;
    }
    public void setFlgMostrarOpciones(boolean flgMostrarOpciones) {
        this.flgMostrarOpciones = flgMostrarOpciones;
    }
    public Integer getIdOpcion() {
        return idOpcion;
    }
    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public boolean isFlgMostrarComboPerfiles() {
        return flgMostrarComboPerfiles;
    }
    public void setFlgMostrarComboPerfiles(boolean flgMostrarComboPerfiles) {
        this.flgMostrarComboPerfiles = flgMostrarComboPerfiles;
    }
}
