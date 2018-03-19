/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ClvSubModulosAgace;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.vista.util.MenuURLEnum;
import mx.gob.sat.siat.modelo.dto.AccesoUsr;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ViewScoped
@ManagedBean(name = "loginMB")
public class LoginManagedBean extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 453194613554484173L;
    private static final int INDEX_CADENA = -1;

    private String userRfc;
    private String url;
    private boolean seFirmo;

    private Set<String> lstRfcs;
    private static final Set<String> LST_RFC_EMPLEADO = new TreeSet<String>();

    @PostConstruct
    public void init() {
        try {
            seFirmo = true;
            if (LST_RFC_EMPLEADO.isEmpty()) {
                LST_RFC_EMPLEADO.addAll(getEmpleadoService().getAllRfcsEmpleados(ClvSubModulosAgace.PROPUESTAS));
            }
        } catch (EmpleadoServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(ex.getMessage());
        }
    }

    public void validaFirma() {
        seFirmo = !((url != null && !url.isEmpty()) && (userRfc != null && !userRfc.isEmpty()));
    }

    public void subirRfcASession() {
        try {
            UserProfileDTO empleadoUser = new UserProfileDTO();
            String ipAddr = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                    .getRemoteAddr();
            String remoteHost = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest().getRemoteHost();
            empleadoUser.setNombreCompleto(userRfc);
            empleadoUser.setRfc(userRfc);
            empleadoUser.setIdEmpleado(Constantes.BIG_DECIMAL_CERO);
            empleadoUser.setIp(ipAddr);
            empleadoUser.setNombreMaquina(remoteHost);
            empleadoUser.setUsuario(userRfc);
            setUserProfile(empleadoUser);

            AccesoUsr acceso = new AccesoUsr();
            acceso.setCentroCosto("110");
            acceso.setCentroCostoOp("110");
            acceso.setNombreCompleto(userRfc);
            acceso.setUsuario(userRfc);
            getSession().setAttribute("acceso", acceso);

            logger.info("Se subiio...", userRfc);

            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath() + url);
        } catch (IOException ex) {
            Logger.getLogger(LoginManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> autocompletarRFC(String query) {
        List<String> lstRfcsResult = new ArrayList<String>();

        try {
            if ((query != null) && (query.length() > 0)) {
                lstRfcsResult.clear();
                for (String rfcTmp : LST_RFC_EMPLEADO) {
                    if (esParteDeRfc(query, rfcTmp)) {
                        lstRfcsResult.add(rfcTmp);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return lstRfcsResult;
    }

    private boolean esParteDeRfc(String query, String rfc) {
        if (query != null && !query.isEmpty() && rfc != null) {
            String queryUpperCase = query.toUpperCase();
            String rfcUpperCase = rfc.toUpperCase();

            return rfcUpperCase.indexOf(queryUpperCase) > INDEX_CADENA;
        }
        return false;
    }

    public void redirigirURL() {
        try {
            ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(dir.getContextPath() + "/faces/propuestas/programador/validarRetroalimentar.xhtml");
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

    public MenuURLEnum[] getUrlsValues() {
        return MenuURLEnum.values();
    }

    public Set<String> getLstRfcs() {
        return lstRfcs;
    }

    public void setLstRfcs(Set<String> lstRfcs) {
        this.lstRfcs = lstRfcs;
    }

    public Set<String> getLstRfcEmpleado() {
        return LST_RFC_EMPLEADO;
    }
}
