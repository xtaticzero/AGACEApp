package mx.gob.sat.siat.feagace.vista.ordenes.prorroga.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.helper.FirmaProrrogaOrdenContribuyenteHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaOrdenContribuyenteService;
import mx.gob.sat.siat.feagace.vista.common.FirmaManagedBean;

@ManagedBean(name = "firmaProrrogaOrdenManagedBean")
@ViewScoped
public class FirmaProrrogaOrdenManagedBean extends FirmaManagedBean {

    @SuppressWarnings("compatibility:8288962726900513768")
    private static final long serialVersionUID = 1L;

    private ProrrogaDocsVO docs;

    @ManagedProperty(value = "#{firmaProrrogaOrdenContribuyenteHelper}")
    private FirmaProrrogaOrdenContribuyenteHelper firmaProrrogaOrdenContribuyenteHelper;

    @ManagedProperty(value = "#{firmaProrrogaOrdenContribuyenteService}")
    private transient FirmaProrrogaOrdenContribuyenteService firmaProrrogaOrdenContribuyenteService;

    private transient StreamedContent acuseFileProrroga;

    @Override
    public Object getDatosFirma() {
        return docs;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setDocs((ProrrogaDocsVO) getSession().getAttribute("prorrogaOrdenFirmar"));
        super.iniciarFirmas();
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("prorrogaOrdenFirmar");
        File acuse = getAcuse();
        if (acuse != null) {
            try {
                setAcuseFileProrroga(new DefaultStreamedContent(new FileInputStream(acuse), "pdf",
                        Constantes.NOMBRE_ACUSE_RECIBO));
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
            getSession().setAttribute("prorrogaAcuse", getAcuseFileProrroga());
        }
        return "../asociarColaboradores/indexAsociar.jsf?faces-redirect=true";
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaProrrogaOrdenContribuyenteService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaProrrogaOrdenContribuyenteHelper;
    }

    public void setDocs(ProrrogaDocsVO docs) {
        this.docs = docs;
    }

    public ProrrogaDocsVO getDocs() {
        return docs;
    }

    public void setFirmaProrrogaOrdenContribuyenteHelper(FirmaProrrogaOrdenContribuyenteHelper firmaProrrogaOrdenContribuyenteHelper) {
        this.firmaProrrogaOrdenContribuyenteHelper = firmaProrrogaOrdenContribuyenteHelper;
    }

    public void setFirmaProrrogaOrdenContribuyenteService(FirmaProrrogaOrdenContribuyenteService firmaProrrogaOrdenContribuyenteService) {
        this.firmaProrrogaOrdenContribuyenteService = firmaProrrogaOrdenContribuyenteService;
    }

    public void setAcuseFileProrroga(StreamedContent acuseFileProrroga) {
        this.acuseFileProrroga = acuseFileProrroga;
    }

    public StreamedContent getAcuseFileProrroga() {
        return acuseFileProrroga;
    }
}
