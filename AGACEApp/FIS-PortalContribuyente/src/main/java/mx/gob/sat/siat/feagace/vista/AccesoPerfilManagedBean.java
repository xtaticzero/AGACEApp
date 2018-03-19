package mx.gob.sat.siat.feagace.vista;

import java.math.BigDecimal;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.base.dto.UserProfileDTO;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

@ManagedBean(name = "accesoPerfilManagedBean")
@ViewScoped
public class AccesoPerfilManagedBean extends AccesoAbstractMB {

    @SuppressWarnings("compatibility:-3864936897015797462")
    private static final long serialVersionUID = 1L;

    private BigDecimal idTipoAsociado;
    private List<FececTipoAsociado> listaTipoAsociado;
    private String rfcPerfilContribuyente;
    private boolean mostrarComboPerfilRfc;
    private List<String> listaRfcContribuyente;
    private PerfilContribuyenteVO perfil;
    private UserProfileDTO userProfileDTO;

    @PostConstruct
    public void init() {
        try {
            if (getUserProfileDTO() == null) {
                setUserProfileDTO(getUserProfile());
                inicializaVariables();
                llenaComboLoggeo();
            } else if (!getUserProfileDTO().getRfc().equals(getUserProfile().getRfc())) {
                preRender();
                setUserProfileDTO(getUserProfile());
                inicializaVariables();
                llenaComboLoggeo();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void preRender() {
        logger.info("preRender");
        idTipoAsociado = null;
        listaTipoAsociado = null;
        rfcPerfilContribuyente = null;
        mostrarComboPerfilRfc = false;
        listaRfcContribuyente = null;
        perfil = null;
        userProfileDTO = null;
    }

    private void inicializaVariables() {
        setIdTipoAsociado(Constantes.COMBO_SELECCIONA);
    }

    private void llenaComboLoggeo() {
        try {
            if (getUserProfileDTO() != null) {
                setListaTipoAsociado(getAsociadosService().construyeComboPerfil(getUserProfileDTO().getRfc()));
            } else {
                addErrorMessage(null, FacesUtil.getMessageResourceString("error.contribuyente.logging"));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void llenaComboPerfilRfc() {
        try {
            if (!(getIdTipoAsociado().equals(Constantes.COMBO_SELECCIONA))
                    && !(getIdTipoAsociado().equals(Constantes.ID_CONTRIBUYENTE))) {
                setMostrarComboPerfilRfc(true);
                setRfcPerfilContribuyente(Constantes.COMBO_SELECCIONA_CADENA);
                if (getUserProfileDTO() != null && getUserProfileDTO().getRfc() != null) {
                    logger.info("getUserProfileDTO().getRfc() : " + getUserProfileDTO().getRfc());
                }
                if (getIdTipoAsociado() != null) {
                    logger.info("getIdTipoAsociado() : " + getIdTipoAsociado());
                }

                if (getAsociadosService() != null) {
                    logger.info("AsociadosService not null ");
                } else {
                    logger.error("AsociadosService is null");
                }

                setListaRfcContribuyente(getAsociadosService().construyeComboPerfilRFC(getUserProfileDTO().getRfc(),
                        getIdTipoAsociado()));
            } else {
                setMostrarComboPerfilRfc(false);
            }
            logger.debug("rfc loggeado " + getUserProfileDTO().getRfc());
            logger.debug("Id tipo asociado " + getIdTipoAsociado());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String enviaPerfil() {
        try {
            setPerfil(new PerfilContribuyenteVO());
            for (FececTipoAsociado tipoAsociado : getListaTipoAsociado()) {
                if (getIdTipoAsociado().equals(tipoAsociado.getIdTipoAsociado())) {
                    getPerfil().setNombreRol(tipoAsociado.getNombre());
                }
            }
            if (getIdTipoAsociado().equals(Constantes.COMBO_SELECCIONA)) {
                addErrorMessage(null, ConstantesError.ERROR_SELECCIONAR_PERFIL, "");
                setRfcPerfilContribuyente(Constantes.COMBO_SELECCIONA_CADENA);
                return null;
            } else if (getIdTipoAsociado().equals(Constantes.ID_CONTRIBUYENTE)) {
                setRfcPerfilContribuyente(Constantes.COMBO_SELECCIONA_CADENA);
                getPerfil().setIdTipoAsociado(getIdTipoAsociado());
                getPerfil().setRfc(getUserProfileDTO().getRfc());
                getPerfil().setNombre(getUserProfileDTO().getNombreCompleto());
                getPerfil().setIdAsociado(null);
                getPerfil().setRfcContribuyente(null);
                getSession().setAttribute("perfil", getPerfil());
                return "indexAsociar.jsf";
            } else {
                if (getRfcPerfilContribuyente().equals(Constantes.COMBO_SELECCIONA_CADENA)) {
                    addErrorMessage(null, ConstantesError.ERROR_SELECCIONAR_RFC, "");
                    setIdTipoAsociado(Constantes.COMBO_SELECCIONA);
                    return null;
                } else {
                    getPerfil().setIdTipoAsociado(getIdTipoAsociado());
                    getPerfil().setRfc(getUserProfileDTO().getRfc());
                    getPerfil().setNombre(getUserProfileDTO().getNombreCompleto());
                    getPerfil().setRfcContribuyente(getRfcPerfilContribuyente());
                    if (getPerfil().getIdTipoAsociado().equals(Constantes.ID_APODERADO_LEGAL)) {
                        getPerfil().setIdAsociado(getAsociadosService().obtenerIdAsociado(getPerfil().getRfc(),
                                getPerfil().getIdTipoAsociado(),
                                getPerfil().getRfcContribuyente()));
                    }
                    getSession().setAttribute("perfil", getPerfil());
                    return "indexAsociar.jsf";
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
        logger.info("setIdTipoAsociado:" + idTipoAsociado);
        this.idTipoAsociado = idTipoAsociado;
    }

    public BigDecimal getIdTipoAsociado() {
        return idTipoAsociado;
    }

    public void setRfcPerfilContribuyente(String rfcPerfilContribuyente) {
        this.rfcPerfilContribuyente = rfcPerfilContribuyente;
    }

    public String getRfcPerfilContribuyente() {
        return rfcPerfilContribuyente;
    }

    public void setMostrarComboPerfilRfc(boolean mostrarComboPerfilRfc) {
        this.mostrarComboPerfilRfc = mostrarComboPerfilRfc;
    }

    public boolean isMostrarComboPerfilRfc() {
        return mostrarComboPerfilRfc;
    }

    public void setListaRfcContribuyente(List<String> listaRfcContribuyente) {
        this.listaRfcContribuyente = listaRfcContribuyente;
    }

    public List<String> getListaRfcContribuyente() {
        return listaRfcContribuyente;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setListaTipoAsociado(List<FececTipoAsociado> listaTipoAsociado) {
        this.listaTipoAsociado = listaTipoAsociado;
    }

    public List<FececTipoAsociado> getListaTipoAsociado() {
        return listaTipoAsociado;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
    }

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }
}
