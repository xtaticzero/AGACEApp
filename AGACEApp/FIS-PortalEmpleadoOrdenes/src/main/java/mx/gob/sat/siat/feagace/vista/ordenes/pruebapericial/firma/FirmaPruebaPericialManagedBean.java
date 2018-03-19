package mx.gob.sat.siat.feagace.vista.ordenes.pruebapericial.firma;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.helper.FirmaPruebaPericialHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.pruebaspericiales.firma.service.FirmaPruebaPericialService;
import mx.gob.sat.siat.feagace.vista.common.firma.FirmaManagedBean;

@ManagedBean(name = "firmaPruebaPericialManagedBean")
@ViewScoped
public class FirmaPruebaPericialManagedBean extends FirmaManagedBean {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{firmaPruebaPericialService}")
    private transient FirmaPruebaPericialService firmaPruebaPericialService;

    @ManagedProperty(value = "#{firmaPruebaPericialHelper}")
    private FirmaPruebaPericialHelper firmaPruebaPericialHelper;

    private FecetPruebasPericiales pruebaPericial;

    @Override
    public Object getDatosFirma() {
        return pruebaPericial;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setPruebaPericial((FecetPruebasPericiales) getSession().getAttribute("pruebaPericialSeleccionada"));

        getPruebaPericial().setFirmante((EmpleadoDTO) getSession().getAttribute("empleadoFirmante"));
        super.iniciarFirmas();
        getSession().setAttribute("listaFirmas", getFirmas());
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("listaFirmas");
        getSession().removeAttribute("pruebaPericialSeleccionada");
        return "documentacionOrden";
    }

    public FecetPruebasPericiales getPruebaPericial() {
        return pruebaPericial;
    }

    public void setPruebaPericial(FecetPruebasPericiales pruebaPericial) {
        this.pruebaPericial = pruebaPericial;
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaPruebaPericialService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaPruebaPericialHelper;
    }

    public FirmaPruebaPericialService getFirmaPruebaPericialService() {
        return firmaPruebaPericialService;
    }

    public void setFirmaPruebaPericialService(FirmaPruebaPericialService firmaPruebaPericialService) {
        this.firmaPruebaPericialService = firmaPruebaPericialService;
    }

    public FirmaPruebaPericialHelper getFirmaPruebaPericialHelper() {
        return firmaPruebaPericialHelper;
    }

    public void setFirmaPruebaPericialHelper(FirmaPruebaPericialHelper firmaPruebaPericialHelper) {
        this.firmaPruebaPericialHelper = firmaPruebaPericialHelper;
    }
}
