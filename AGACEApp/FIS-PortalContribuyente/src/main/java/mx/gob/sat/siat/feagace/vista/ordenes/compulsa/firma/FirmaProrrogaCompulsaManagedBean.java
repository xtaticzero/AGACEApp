package mx.gob.sat.siat.feagace.vista.ordenes.compulsa.firma;

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
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.prorroga.firma.helper.FirmaProrrogaOficioContribuyenteHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.prorroga.firma.service.FirmaProrrogaOficioContribuyenteService;
import mx.gob.sat.siat.feagace.vista.common.FirmaManagedBean;

@ManagedBean(name = "firmaProrrogaCompulsaManagedBean")
@ViewScoped
public class FirmaProrrogaCompulsaManagedBean extends FirmaManagedBean {

    @SuppressWarnings("compatibility:2509390050321530653")
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value = "#{firmaProrrogaOficioContribuyenteHelper}")
    private FirmaProrrogaOficioContribuyenteHelper firmaProrrogaOficioContribuyenteHelper;
    
    @ManagedProperty(value = "#{firmaProrrogaOficioContribuyenteService}")
    private transient FirmaProrrogaOficioContribuyenteService firmaProrrogaOficioContribuyenteService;
    
    private transient StreamedContent acuseFileProrroga;
    
    private ProrrogaDocsVO docs;

    @Override
    public Object getDatosFirma() {
        return docs;
    }
    
    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setDocs((ProrrogaDocsVO) getSession().getAttribute("prorrogaCompulsaFirmar"));
        getAuditoriaService().cargarProrrogaOficio(((ProrrogaDocsVO)getDatosFirma()).getOficioSeleccionado());  
        super.iniciarFirmas();
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("prorrogaCompulsaFirmar");
        File acuse = getAcuse();
        if (acuse != null) {
            try {
                setAcuseFileProrroga(new DefaultStreamedContent(new FileInputStream(acuse), "pdf",
                        Constantes.NOMBRE_ACUSE_RECIBO));
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(),e);
            }
            getSession().setAttribute("prorrogaCompulsaAcuse", getAcuseFileProrroga());
        }
        return "../asociarColaboradores/indexAsociar.jsf?faces-redirect=true";
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaProrrogaOficioContribuyenteService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaProrrogaOficioContribuyenteHelper;
    }

    public void setDocs(ProrrogaDocsVO docs) {
        this.docs = docs;
    }

    public void setFirmaProrrogaOficioContribuyenteHelper(FirmaProrrogaOficioContribuyenteHelper firmaProrrogaOficioContribuyenteHelper) {
        this.firmaProrrogaOficioContribuyenteHelper = firmaProrrogaOficioContribuyenteHelper;
    }

    public void setAcuseFileProrroga(StreamedContent acuseFileProrroga) {
        this.acuseFileProrroga = acuseFileProrroga;
    }

    public StreamedContent getAcuseFileProrroga() {
        return acuseFileProrroga;
    }

    public void setFirmaProrrogaOficioContribuyenteService(FirmaProrrogaOficioContribuyenteService firmaProrrogaOficioContribuyenteService) {
        this.firmaProrrogaOficioContribuyenteService = firmaProrrogaOficioContribuyenteService;
    }
}
