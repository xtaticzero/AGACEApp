package mx.gob.sat.siat.feagace.vista.ordenes.prorroga.firma;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.helper.FirmaProrrogaHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaService;
import mx.gob.sat.siat.feagace.vista.common.firma.FirmaManagedBean;

@ManagedBean(name = "firmaProrrogaManagedBean")
@ViewScoped
public class FirmaProrrogaManagedBean extends FirmaManagedBean {

    private static final long serialVersionUID = -5654338392364442406L;

    @ManagedProperty(value = "#{firmaProrrogaService}")
    private transient FirmaProrrogaService firmaProrrogaService;

    @ManagedProperty(value = "#{firmaProrrogaHelper}")
    private FirmaProrrogaHelper firmaProrrogaHelper;

    private FecetProrrogaOrden prorroga;

    @Override
    public Object getDatosFirma() {
        return prorroga;
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setProrroga((FecetProrrogaOrden) getSession().getAttribute("prorrogaOrdenSeleccionada"));
        getProrroga().setFirmante((EmpleadoDTO) getSession().getAttribute("empleadoFirmante"));

        super.iniciarFirmas();
        getSession().setAttribute("listaFirmas", getFirmas());
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("listaFirmas");
        getSession().removeAttribute("prorrogaOrdenSeleccionada");
        return "documentacionOrden";
    }

    @Override
    public FirmaService getFirmaService() {
        return firmaProrrogaService;
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaProrrogaHelper;
    }

    public FecetProrrogaOrden getProrroga() {
        return prorroga;
    }

    public void setProrroga(FecetProrrogaOrden prorroga) {
        this.prorroga = prorroga;
    }

    public FirmaProrrogaService getFirmaProrrogaService() {
        return firmaProrrogaService;
    }

    public void setFirmaProrrogaService(FirmaProrrogaService firmaProrrogaService) {
        this.firmaProrrogaService = firmaProrrogaService;
    }

    public FirmaProrrogaHelper getFirmaProrrogaHelper() {
        return firmaProrrogaHelper;
    }

    public void setFirmaProrrogaHelper(FirmaProrrogaHelper firmaProrrogaHelper) {
        this.firmaProrrogaHelper = firmaProrrogaHelper;
    }
}
