package mx.gob.sat.siat.feagace.vista.ordenes.prorroga.firma;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.helper.FirmaProrrogaOficioHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.prorroga.firma.service.FirmaProrrogaOficioService;
import mx.gob.sat.siat.feagace.vista.common.firma.FirmaManagedBean;

@ManagedBean(name = "firmaProrrogaOficioManagedBean")
@ViewScoped
public class FirmaProrrogaOficioManagedBean extends FirmaManagedBean {

    @SuppressWarnings("compatibility:-1662756874383051261")
    private static final long serialVersionUID = 4567654321L;

    @ManagedProperty(value = "#{firmaProrrogaOficioService}")
    private transient FirmaProrrogaOficioService firmaProrrogaOficioService;

    @ManagedProperty(value = "#{firmaProrrogaOficioHelper}")
    private FirmaProrrogaOficioHelper firmaProrrogaOficioHelper;

    private FecetProrrogaOficio prorroga;

    @Override
    public Object getDatosFirma() {
        return getProrroga();
    }

    @PostConstruct
    @Override
    public void iniciarFirmas() {
        setProrroga((FecetProrrogaOficio) getSession().getAttribute(
                "prorrogaOrdenSeleccionada"));
        getProrroga().setFirmante( (EmpleadoDTO) getSession().getAttribute("empleadoFirmante"));
        super.iniciarFirmas();
        getSession().setAttribute("listaFirmas", getFirmas());
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("listaFirmas");
        getSession().removeAttribute("prorrogaOrdenSeleccionada");
        return "documentacionOficio";
    }

    @Override
    public FirmaService getFirmaService() {
        return getFirmaProrrogaOficioService();
    }

    @Override
    public FirmaHelper getFirmaHelper() {
        return firmaProrrogaOficioHelper;
    }

    public FecetProrrogaOficio getProrroga() {
        return prorroga;
    }

    public void setProrroga(FecetProrrogaOficio prorroga) {
        this.prorroga = prorroga;
    }

   
    public FirmaProrrogaOficioHelper getFirmaProrrogaOficioHelper() {
        return firmaProrrogaOficioHelper;
    }

    public void setFirmaProrrogaOficioHelper(FirmaProrrogaOficioHelper firmaProrrogaOficioHelper) {
        this.firmaProrrogaOficioHelper = firmaProrrogaOficioHelper;
    }

    public FirmaProrrogaOficioService getFirmaProrrogaOficioService() {
        return firmaProrrogaOficioService;
    }

    public void setFirmaProrrogaOficioService(FirmaProrrogaOficioService firmaProrrogaOficioService) {
        this.firmaProrrogaOficioService = firmaProrrogaOficioService;
    }
}
