package mx.gob.sat.siat.feagace.vista.ordenes.oficio.promocion.firma;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.promocion.firma.helper.FirmaPromocionOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.promocion.firma.service.FirmaPromocionOficioService;
import mx.gob.sat.siat.feagace.vista.common.FirmaManagedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "firmaPromocionOficioManagedBean")
@ViewScoped
public class FirmaPromocionOficioManagedBean extends FirmaManagedBean {

    @SuppressWarnings("compatibility:3669192590621064763")
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{firmaPromocionOficioHelper}")
    private FirmaPromocionOficioHelper firmaPromocionOficioHelper;

    @ManagedProperty(value = "#{firmaPromocionOficioService}")
    private transient FirmaPromocionOficioService firmaPromocionOficioService;

    private PromocionDocsVO docs;

    private transient StreamedContent acuseFilePromocion;

    @Override
    public Object getDatosFirma() {
        return docs;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setDocs((PromocionDocsVO) getSession().getAttribute("promocionOficioFirmar"));
        super.iniciarFirmas();
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("promocionOficioFirmar");
        File acuse = getAcuse();
        if (acuse != null) {
            try {
                setAcuseFilePromocion(new DefaultStreamedContent(new FileInputStream(acuse), "pdf",
                        Constantes.NOMBRE_ACUSE_RECIBO));
            } catch (FileNotFoundException e) {
                logger.error(e.getMessage(), e);
            }
            getSession().setAttribute("promocionAcuse", getAcuseFilePromocion());
        }
        return "../asociarColaboradores/indexAsociar.jsf?faces-redirect=true";
    }

    public void setDocs(PromocionDocsVO docs) {
        this.docs = docs;
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaPromocionOficioService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaPromocionOficioHelper;
    }

    public void setFirmaPromocionOficioHelper(FirmaPromocionOficioHelper firmaPromocionOficioHelper) {
        this.firmaPromocionOficioHelper = firmaPromocionOficioHelper;
    }

    public FirmaPromocionOficioHelper getFirmaPromocionOficioHelper() {
        return firmaPromocionOficioHelper;
    }

    public void setFirmaPromocionOficioService(FirmaPromocionOficioService firmaPromocionOficioService) {
        this.firmaPromocionOficioService = firmaPromocionOficioService;
    }

    public FirmaPromocionOficioService getFirmaPromocionOficioService() {
        return firmaPromocionOficioService;
    }

    public void setAcuseFilePromocion(StreamedContent acuseFilePromocion) {
        this.acuseFilePromocion = acuseFilePromocion;
    }

    public StreamedContent getAcuseFilePromocion() {
        return acuseFilePromocion;
    }
}
