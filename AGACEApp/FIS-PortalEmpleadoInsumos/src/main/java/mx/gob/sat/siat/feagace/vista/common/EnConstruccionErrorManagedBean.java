package mx.gob.sat.siat.feagace.vista.common;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import mx.gob.sat.siat.feagace.vista.util.constantes.SessionAttributeInsumosEnum;

@SessionScoped
@ManagedBean(name = "enConstruccionErrorManagedBean")
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
        HttpSession session = (HttpSession) faces.getExternalContext().getSession(true);
        if (session.getAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName()) != null) {
            Object obj = session.getAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName());

            if (obj instanceof Exception) {
                Exception e = (Exception) session.getAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName());
                setError(e.getMessage());
            } else {
                String e = (String) session.getAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName());
                setError(e);
            }

            session.removeAttribute(SessionAttributeInsumosEnum.MSG_ERROR_SESSION.getAttributeName());
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
