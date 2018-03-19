package mx.gob.sat.siat.feagace.vista.common;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.negocio.common.AuditoriaService;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;

@ManagedBean(name = "firmaManagedBean")
@ViewScoped
public abstract class FirmaManagedBean extends BaseManagedBean {

    private static final long serialVersionUID = 8268898751649868751L;

    private List<FirmaDTO> firmas;

    private File acuse;

    private String rfcFirma;
    
    @ManagedProperty(value = "#{auditoriaContribuyente}")
    private transient AuditoriaService auditoriaService;

    public void iniciarFirmas() {
        setFirmas(getFirmaHelper().armarCadena(getDatosFirma(), getRFCSession()));
    }

    public abstract Object getDatosFirma();

    public String guardarFirma() {
        if (!rfcFirma.equals(getRFCSession())) {
            addErrorMessage("El RFC Logeado no coincide con el certificado ingresado");
            return null;
        } else {
            Map<String, FirmaDTO> mapaFirmas = procesarFirma();
            acuse = getFirmaService().guardarFirma(mapaFirmas, getDatosFirma(), getRFCSession());
            addMessage("Se firmo correctamente");
            return regresar();
        }
    }

    public Map<String, FirmaDTO> procesarFirma() {
        Map<String, FirmaDTO> mapFirma = new HashMap<String, FirmaDTO>();
        for (FirmaDTO firma : getFirmas()) {
            mapFirma.put(firma.getId(), firma);
        }
        return mapFirma;
    }

    public String getUrlFirmadoJS() {
        return getFirmaHelper().getUrlFirmadoJS();
    }

    public abstract String regresar();

    public List<FirmaDTO> getFirmas() {
        return firmas;
    }

    public void setFirmas(List<FirmaDTO> firmas) {
        this.firmas = firmas;
    }

    public abstract FirmaService getFirmaService();

    public abstract FirmaHelper getFirmaHelper();

    public File getAcuse() {
        return acuse;
    }

    public String getRfcFirma() {
        return rfcFirma;
    }

    public void setRfcFirma(String rfcFirma) {
        this.rfcFirma = rfcFirma;
    }
    
    public AuditoriaService getAuditoriaService() {
        return auditoriaService;
    }

    public void setAuditoriaService(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

}
