package mx.gob.sat.siat.feagace.vista.ordenes.promocion.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PromocionDocsVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.helper.FirmaPromocionHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.promocion.firma.service.FirmaPromocionService;
import mx.gob.sat.siat.feagace.vista.common.FirmaManagedBean;

@ManagedBean(name = "firmaPromocionOrdenManagedBean")
@ViewScoped
public class FirmaPromocionOrdenManagedBean extends FirmaManagedBean {

    @SuppressWarnings("compatibility:8959732774784914823")
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{firmaPromocionService}")
    private transient FirmaPromocionService firmaPromocionService;

    @ManagedProperty(value = "#{firmaPromocionHelper}")
    private FirmaPromocionHelper firmaPromocionHelper;

    private PromocionDocsVO promocionDocsVO;

    private transient StreamedContent acuseFilePromocion;

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        this.setPromocionDocsVO((PromocionDocsVO) getSession().getAttribute("promocionFirmar"));
        super.iniciarFirmas();
    }

    @Override
    public Object getDatosFirma() {
        return promocionDocsVO;
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("promocionFirmar");

        File acuse = getAcuse();
        if (acuse != null) {
            logger.info(" :::::::::::::::::::::::::::::   valida acuse no sea null   ::::::::::::::::::");
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

    @Override
    public FirmaService getFirmaService() {
        return firmaPromocionService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaPromocionHelper;
    }

    public FirmaPromocionService getFirmaPromocionService() {
        return firmaPromocionService;
    }

    public void setFirmaPromocionService(FirmaPromocionService firmaPromocionService) {
        this.firmaPromocionService = firmaPromocionService;
    }

    public StreamedContent getAcuseFilePromocion() {
        return acuseFilePromocion;
    }

    public void setAcuseFilePromocion(StreamedContent acuseFilePromocion) {
        this.acuseFilePromocion = acuseFilePromocion;
    }

    public void setPromocionDocsVO(PromocionDocsVO promocionDocsVO) {
        this.promocionDocsVO = promocionDocsVO;
    }

    public FirmaPromocionHelper getFirmaPromocionHelper() {
        return firmaPromocionHelper;
    }

    public void setFirmaPromocionHelper(FirmaPromocionHelper firmaPromocionHelper) {
        this.firmaPromocionHelper = firmaPromocionHelper;
    }

}
