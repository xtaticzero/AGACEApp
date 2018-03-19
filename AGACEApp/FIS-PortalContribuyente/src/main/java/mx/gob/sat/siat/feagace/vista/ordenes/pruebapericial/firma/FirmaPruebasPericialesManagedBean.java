package mx.gob.sat.siat.feagace.vista.ordenes.pruebapericial.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper.FirmaPruebasPericialesContribuyenteHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.FirmaPruebasPericialesContribuyenteService;
import mx.gob.sat.siat.feagace.vista.common.FirmaManagedBean;

@ManagedBean(name = "firmaPruebasPericialesManagedBean")
@ViewScoped
public class FirmaPruebasPericialesManagedBean extends FirmaManagedBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private PruebasPericialesDocsVO docs;

    @ManagedProperty(value = "#{firmaPruebasPericialesContribuyenteHelper}")
    private FirmaPruebasPericialesContribuyenteHelper firmaPruebasPericialesContribuyenteHelper;

    @ManagedProperty(value = "#{firmaPruebasPericialesContribuyenteService}")
    private transient FirmaPruebasPericialesContribuyenteService firmaPruebasPericialesContribuyenteService;

    private transient StreamedContent acuseFilePruebaPericial;

    @Override
    public Object getDatosFirma() {
        return docs;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setDocs((PruebasPericialesDocsVO) getSession().getAttribute("pruebaPericialFirmar"));
        super.iniciarFirmas();
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("pruebaPericialFirmar");
        File acuse = getAcuse();
        if (acuse != null) {
            try {
                setAcuseFilePruebaPericial(new DefaultStreamedContent(new FileInputStream(acuse), "pdf",
                        Constantes.NOMBRE_ACUSE_RECIBO));
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
            getSession().setAttribute("pruebaPericialAcuse", getAcuseFilePruebaPericial());
        }
        return "../asociarColaboradores/indexAsociar.jsf?faces-redirect=true";
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaPruebasPericialesContribuyenteService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaPruebasPericialesContribuyenteHelper;
    }

    public void setDocs(PruebasPericialesDocsVO docs) {
        this.docs = docs;
    }

    public PruebasPericialesDocsVO getDocs() {
        return docs;
    }

    public void setFirmaPruebasPericialesContribuyenteHelper(FirmaPruebasPericialesContribuyenteHelper firmaPruebasPericialesContribuyenteHelper) {
        this.firmaPruebasPericialesContribuyenteHelper = firmaPruebasPericialesContribuyenteHelper;
    }

    public void setFirmaPruebasPericialesContribuyenteService(FirmaPruebasPericialesContribuyenteService firmaPruebasPericialesContribuyenteService) {
        this.firmaPruebasPericialesContribuyenteService = firmaPruebasPericialesContribuyenteService;
    }

    public void setAcuseFilePruebaPericial(StreamedContent acuseFilePruebaPericial) {
        this.acuseFilePruebaPericial = acuseFilePruebaPericial;
    }

    public StreamedContent getAcuseFilePruebaPericial() {
        return acuseFilePruebaPericial;
    }

}
