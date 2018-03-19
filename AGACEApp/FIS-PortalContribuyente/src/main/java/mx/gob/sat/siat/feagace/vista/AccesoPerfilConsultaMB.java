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

@ManagedBean(name = "accesoPerfilConsultaMB")
@ViewScoped
public class AccesoPerfilConsultaMB extends AccesoPerfilConsultaAbstract {

    private static final long serialVersionUID = 1L;

    private BigDecimal idTipoAsociado;
    private List<FececTipoAsociado> listaTipoAsociado;
    private String rfcPerfilContribuyente;
    private boolean mostrarComboPerfilRfc;
    private List<String> listaRfcContribuyente;
    private PerfilContribuyenteVO perfil;
    private UserProfileDTO userProfileDTO;

    public AccesoPerfilConsultaMB() {
        super();
    }
    
    @PostConstruct
    public void init() {
        logger.info("AccesoPerfilConsultaMB.init");
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
    }

    public void preRender() {
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
        if (getUserProfileDTO() != null) {
            setListaTipoAsociado(getAsociadosService().construyeComboPerfil(getUserProfileDTO().getRfc()));
        } else {
            addErrorMessage(null, FacesUtil.getMessageResourceString("error.contribuyente.logging"));
        }
    }

    public void llenaComboPerfilRfc() {
        if (!(getIdTipoAsociado().equals(Constantes.COMBO_SELECCIONA))
                && !(getIdTipoAsociado().equals(Constantes.ID_CONTRIBUYENTE))) {
            setMostrarComboPerfilRfc(true);
            setRfcPerfilContribuyente(Constantes.COMBO_SELECCIONA_CADENA);
            
            if(getAsociadosService()==null || getContribuyenteService()==null){
                logger.info("getAsociadosService()==null || getContribuyenteService()==null");
                super.init();
            }
            
            setListaRfcContribuyente(getAsociadosService().construyeComboPerfilRFC(getUserProfileDTO().getRfc(),
                    getIdTipoAsociado()));
        } else {
            setMostrarComboPerfilRfc(false);
        }
        logger.debug("Id tipo asociado " + getIdTipoAsociado());
    }

    public String enviaPerfil() {
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
            setMostrarComboPerfilRfc(false);
            setRfcPerfilContribuyente(Constantes.COMBO_SELECCIONA_CADENA);
            getPerfil().setIdTipoAsociado(getIdTipoAsociado());
            getPerfil().setRfc(getUserProfileDTO().getRfc());
            getPerfil().setNombre(getUserProfileDTO().getNombreCompleto());
            getPerfil().setIdAsociado(null);
            getPerfil().setRfcContribuyente(null);
            getSession().setAttribute("perfil", getPerfil());
            return "consultarReimprimirDoc.jsf";
        } else {
            if (!isMostrarComboPerfilRfc()) {
                llenaComboPerfilRfc();
                return null;
            } else if (getRfcPerfilContribuyente().equals(Constantes.COMBO_SELECCIONA_CADENA)) {
                setIdTipoAsociado(Constantes.COMBO_SELECCIONA);
                addErrorMessage(null, ConstantesError.ERROR_SELECCIONAR_RFC, "");
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
                return "consultarReimprimirDoc.jsf";
            }
        }
    }

    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
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
