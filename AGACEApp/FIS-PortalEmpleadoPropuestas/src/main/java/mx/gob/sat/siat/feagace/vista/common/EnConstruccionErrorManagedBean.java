package mx.gob.sat.siat.feagace.vista.common;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.feagace.vista.util.constantes.SessionAttributePropuestasEnum;

/**
 * ManageBean para la pantalla de accesoDenegado.
 *
 * @author Abel Zebadua.
 */
public class EnConstruccionErrorManagedBean implements Serializable {

    @SuppressWarnings("compatibility:4132756709103186193")
    private static final long serialVersionUID = 1L;
    private String error;

    @PostConstruct
    public void init() {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faces.getExternalContext().getSession(true);
        if (session.getAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName()) != null) {
            Object obj = session.getAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName());

            if (obj instanceof Exception) {
                Exception e = (Exception) session.getAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName());
                setError(e.getMessage());
            } else {
                String e = (String) session.getAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName());
                setError(e);
            }

            session.removeAttribute(SessionAttributePropuestasEnum.MSG_ERROR_SESSION.getAttributeName());
        }
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
