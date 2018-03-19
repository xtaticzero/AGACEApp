package mx.gob.sat.siat.feagace.vista.ordenes.oficio.firma;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OficioPorFirmarVO;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.helper.FirmaOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.oficio.firma.service.FirmaOficioService;
import mx.gob.sat.siat.feagace.vista.common.firma.FirmaManagedBean;


@ManagedBean(name = "firmaOficioManagedBean")
@ViewScoped

public class FirmaOficioManagedBean extends FirmaManagedBean {

    private static final long serialVersionUID = -5654338392364442406L;

    @ManagedProperty(value = "#{firmaOficioService}")
    private transient FirmaOficioService firmaOficioService;

    @ManagedProperty(value = "#{firmaOficioHelper}")
    private FirmaOficioHelper firmaOficioHelper;

    private OficioPorFirmarVO oficioPorFirmarVO;

    @Override
    public Object getDatosFirma() {
        return oficioPorFirmarVO;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        //setOficio((FecetOficio) getSession().getAttribute("oficioPorFirmar"))
        setOficioPorFirmarVO((OficioPorFirmarVO) getSession().getAttribute("oficioPorFirmarVO"));
        super.iniciarFirmas();
        getSession().setAttribute("listaFirmas", getFirmas());
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaOficioService;
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("oficioPorFirmar");
        return "documentacionOrden";
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaOficioHelper;
    }

    public FirmaOficioService getFirmaOficioService() {
        return firmaOficioService;
    }

    public void setFirmaOficioService(FirmaOficioService firmaOficioService) {
        this.firmaOficioService = firmaOficioService;
    }

    public FirmaOficioHelper getFirmaOficioHelper() {
        return firmaOficioHelper;
    }

    public void setFirmaOficioHelper(FirmaOficioHelper firmaOficioHelper) {
        this.firmaOficioHelper = firmaOficioHelper;
    }
    
    public OficioPorFirmarVO getOficioPorFirmarVO() {
        return oficioPorFirmarVO;
    }

    public void setOficioPorFirmarVO(OficioPorFirmarVO oficioPorFirmarVO) {
        this.oficioPorFirmarVO = oficioPorFirmarVO;
    }

}
