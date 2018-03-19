package mx.gob.sat.siat.feagace.vista.ordenes.doctoelectronico.firma;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenPorFirmar;
import mx.gob.sat.siat.feagace.negocio.common.firma.helper.FirmaHelper;
import mx.gob.sat.siat.feagace.negocio.common.firma.service.FirmaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.OrdenesPorValidarReService;
import mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.helper.FirmarDocumentoElectronicoHelper;
import mx.gob.sat.siat.feagace.negocio.ordenes.doctoelectronico.firma.service.FirmaDocumentoElectronicoService;
import mx.gob.sat.siat.feagace.vista.common.firma.FirmaManagedBean;

@ManagedBean(name = "firmaDocumentoElectronicoManagedBean")
@ViewScoped
public class FirmaDocumentoElectronicoManagedBean extends FirmaManagedBean {

    private static final long serialVersionUID = -2840532397087743833L;

    @ManagedProperty(value = "#{firmaDocumentoElectronicoService}")
    private transient FirmaDocumentoElectronicoService firmaDocumentoElectronicoService;

    @ManagedProperty(value = "#{firmarDocumentoElectronicoHelper}")
    private FirmarDocumentoElectronicoHelper firmarDocumentoElectronicoHelper;

    @ManagedProperty(value = "#{ordenesPorValidarReService}")
    private transient OrdenesPorValidarReService ordenesPorValidarReService;

    private List<OrdenPorFirmar> ordenesPorFirmar;

    static final String SIN_SELECCION = "error.lista.sin.seleccion.odrenes.firmar";

    @PostConstruct
    public void init() {
        setFirmas((List<FirmaDTO>) getSession().getAttribute("listaFirmas"));
        setOrdenesPorFirmar((List<OrdenPorFirmar>) getSession().getAttribute(
                "ordenesPorFirmar"));
    }

    @Override
    public String regresar() {
        getSession().removeAttribute("listaFirmas");
        getSession().removeAttribute("ordenesPorFirmar");
        return "firmarOrdenes";
    }

    public String getRfcLogeado() {
        return getRFCSession();
    }

    public FirmaDocumentoElectronicoService getFirmaDocumentoElectronicoService() {
        return firmaDocumentoElectronicoService;
    }

    public void setFirmaDocumentoElectronicoService(
            FirmaDocumentoElectronicoService firmaDocumentoElectronicoService) {
        this.firmaDocumentoElectronicoService = firmaDocumentoElectronicoService;
    }

    public void setFirmarDocumentoElectronicoHelper(
            FirmarDocumentoElectronicoHelper firmarDocumentoElectronicoHelper) {
        this.firmarDocumentoElectronicoHelper = firmarDocumentoElectronicoHelper;
    }

    public FirmarDocumentoElectronicoHelper getFirmarDocumentoElectronicoHelper() {
        return firmarDocumentoElectronicoHelper;
    }

    public FirmaHelper getFirmaHelper() {
        return firmarDocumentoElectronicoHelper;
    }

    @Override
    public Object getDatosFirma() {
        return ordenesPorFirmar;
    }

    public List<OrdenPorFirmar> getOrdenesPorFirmar() {
        return ordenesPorFirmar;
    }

    public void setOrdenesPorFirmar(List<OrdenPorFirmar> ordenesPorFirmar) {
        this.ordenesPorFirmar = ordenesPorFirmar;
    }

    public FirmaService getFirmaService() {
        return firmaDocumentoElectronicoService;
    }

    /**
     * Metodo que realiza la validacion para saber si se seleccionaron ordenes
     */
    public void firmarOrdenes() {
        boolean firma = true;
        logger.info("Voy a firmar ordenes...");
        if (this.ordenesPorFirmar != null && !this.ordenesPorFirmar.isEmpty()) {
            logger.info("Se van a firmar las ordenes");
            for (OrdenPorFirmar orden : ordenesPorFirmar) {
                if (orden.getRutaArchivo() == null) {
                    StringBuilder errorMsg = new StringBuilder();
                    errorMsg.append("La orden ");
                    errorMsg.append(orden.getIdRegistro());
                    errorMsg.append(" no cuenta con documento");
                    addErrorMessage(null, errorMsg.toString());
                    firma = false;
                }
            }
            if (firma) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('firmarRegistros').show();");
            }
        } else {
            addErrorMessage(null,
                    getMessageResourceString(SIN_SELECCION));
        }
    }

    /**
     * Metodo firmarOrdenesSeleccionadas
     *
     * @return String
     */
    public String firmarOrdenesSeleccionadas() {
        if (this.ordenesPorFirmar != null && !this.ordenesPorFirmar.isEmpty()) {
            for (OrdenPorFirmar orden : ordenesPorFirmar) {
                orden.setOficios(ordenesPorValidarReService
                        .getOficiosPorFirmar(new BigDecimal(orden.getIdOrden())));
            }
            iniciarFirmas();
            getSession().setAttribute("listaFirmas", getFirmas());
            getSession().setAttribute("ordenesPorFirmar", getDatosFirma());
            return "firmarDoctoElectronico?faces-redirect=true";
        } else {
            addErrorMessage(null,
                    getMessageResourceString(SIN_SELECCION));
            return "";
        }
    }

    public OrdenesPorValidarReService getOrdenesPorValidarReService() {
        return ordenesPorValidarReService;
    }

    public void setOrdenesPorValidarReService(
            OrdenesPorValidarReService ordenesPorValidarReService) {
        this.ordenesPorValidarReService = ordenesPorValidarReService;
    }

}
