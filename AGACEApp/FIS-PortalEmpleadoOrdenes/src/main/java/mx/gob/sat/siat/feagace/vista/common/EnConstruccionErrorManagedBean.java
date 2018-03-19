package mx.gob.sat.siat.feagace.vista.common;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 * ManageBean para la pantalla de accesoDenegado.
 *
 * @author Abel Zebadua.
 */
public class EnConstruccionErrorManagedBean implements Serializable {
    @SuppressWarnings("compatibility:4132756709103186193")
    private static final long serialVersionUID = 1L;
    private String error;

    /**
     * PostConstruct que obtiene la exception de session he imprimer el error.
     */
    @PostConstruct
    public void init() {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)faces.getExternalContext().getSession(true);
        if (session.getAttribute("mensaje") != null) {
            Exception e = (Exception)session.getAttribute("mensaje");
            setError(e.getMessage());
            session.removeAttribute("mensaje");
        }
    }

    /**
     * De tipo String.
     *
     * @param error Tipo de dato String.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * De tipo String.
     *
     * @return error.
     */
    public String getError() {
        return error;
    }
}
