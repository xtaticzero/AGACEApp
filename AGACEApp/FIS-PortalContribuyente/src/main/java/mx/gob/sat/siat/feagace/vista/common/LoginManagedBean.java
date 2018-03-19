/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ViewScoped
@ManagedBean(name = "loginMB")
public class LoginManagedBean extends BaseManagedBean {

    private static final long serialVersionUID = 453194613554484173L;

    private String userRfc;
    private String url;
    private boolean seFirmo;

    private List<FececEmpleado> lstRfc;

    @PostConstruct
    public void init() {
        seFirmo = true;
    }

    public void validaFirma() {
        seFirmo = !((url != null && !url.isEmpty()) && (userRfc != null && !userRfc.isEmpty()));
    }

    public void subirRfcASession() {
        try {
            UserProfileDTO empleadoUser = new UserProfileDTO();

            empleadoUser.setNombreCompleto(userRfc);
            empleadoUser.setRfc(userRfc);

            setUserProfile(empleadoUser);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + url);
        } catch (IOException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> autocompletarRFC(String query) {
        List<String> results = new ArrayList<String>();

        try {
            if ((query != null) && (query.length() > 0)) {
                results.clear();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return results;
    }

    public void redirigirURL() {
        try {
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + "/faces/propuestas/programador/validarRetroalimentar.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getUserRfc() {
        return userRfc;
    }

    public void setUserRfc(String userRfc) {
        this.userRfc = userRfc;
    }

    public List<FececEmpleado> getLstRfc() {
        return lstRfc;
    }

    public void setLstRfc(List<FececEmpleado> lstRfc) {
        this.lstRfc = lstRfc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSeFirmo() {
        return seFirmo;
    }

    public void setSeFirmo(boolean seFirmo) {
        this.seFirmo = seFirmo;
    }
}
