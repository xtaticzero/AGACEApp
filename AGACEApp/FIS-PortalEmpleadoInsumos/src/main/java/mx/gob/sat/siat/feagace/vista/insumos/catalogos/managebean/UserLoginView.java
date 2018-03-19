/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos.managebean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ManagedBean
public class UserLoginView extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = -3549276923658965206L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) throws IOException {
        String message = null;
        
        if (username != null && username.equals(getMessageResourceString("user.catalogos")) && password != null && password.equals(getMessageResourceString("pass.catalogos"))) {
            message = "Welcome";
            ServletContext dir = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    dir.getContextPath()
                    + "/faces/catalogos/unidadesAdministrativas/adminCatUnidadAdminModulo.jsf");
            
        } else {
            message = "Credenciales no válidas";
        }

        addErrorMessage(message);        
    }
}
