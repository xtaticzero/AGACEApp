package mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes;

import java.io.IOException;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.CifrasAttributeHelper;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ug.ordenes.helper.CifrasColumasDetalleHelper;

@ManagedBean(name = "cifrasConsultaManagedBean")
@ViewScoped
public class CifrasConsultaManagedBean extends CifrasManagedBean {

    private static final long serialVersionUID = 1L;

    private boolean vieneDetalleOrdenUnif;

    private AgaceOrden ordenSeleccionado;

    private boolean reporteVisible;

    @PostConstruct
    public void init() {

        if (getSession().getAttribute("ordenSeleccionadaC") != null) {
            setOrdenSeleccionado((AgaceOrden) getSession().getAttribute("ordenSeleccionadaC"));
            setVieneDetalleOrdenUnif(true);
            getSession().removeAttribute("ordenSeleccionadaC");
        }
        setColumnaDetalleHelper(new CifrasColumasDetalleHelper());
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        setTotalCifras(getCifrasService().obtenerEncabezadoCifras(idOrden));
        setCifrasAttributeHelper(new CifrasAttributeHelper());
        obtenerTotalEncabezado();
        reporteVisible();
    }

    public void reporteVisible() {
        if (getListaEncabezadoCifras() != null
                && !getListaEncabezadoCifras().isEmpty()) {
            setReporteVisible(false);
        } else {
            setReporteVisible(true);
        }
    }

    public void obtenerHistoricoEncabezado() {
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        obtenerHistoricoEncabezado(idOrden);
    }

    public void mostrarDetalleHistorico() {
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        mostrarDetalleHistorico(idOrden);
    }

    public void onRowSelect(SelectEvent event) {
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        setImpuestosSeleccionados(
                getCifrasService().obtenerCifrasPorOrdenCifraTipoCifra(idOrden, getEncabezadoCifraSeleccionada().getTipoCifra().getIdCifra()));
        if (getImpuestosSeleccionados() != null && !getImpuestosSeleccionados().isEmpty()) {
            getCifrasAttributeHelper().setTablaImpuestosVisible(true);
        }
    }

    public void onTabChange(TabChangeEvent event) {
        String indiceActivo = ((AccordionPanel) event.getComponent()).getActiveIndex();
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        int tipoCifra = Integer.parseInt(indiceActivo) + 1;
        configurarPantalla(tipoCifra);
        obtenerListaCifras(idOrden, new BigDecimal(tipoCifra));
        getCifrasAttributeHelper().setTablaImpuestosVisible(false);
        setTabSeleccionado(tipoCifra);
        reporteVisible();
    }

    public void redireccionaRegreso() throws IOException {

        String urlRedireccion = "/faces/consultarDocumentos/detalleOrdenDocUnificado.jsf";

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        externalContext.redirect(origRequest.getContextPath() + urlRedireccion);

    }

    public StreamedContent getArchivoExcel() {
        BigDecimal idOrden = getOrdenSeleccionado().getId();
        DefaultStreamedContent excel = null;
        try {
            excel = new DefaultStreamedContent(getCifrasService().getReporteConsulta(idOrden, new BigDecimal(getTabSeleccionado())),
                    "application/vnd.ms-excel", "reporte_cifras.xls");
        } catch (Exception e) {
            logger.error("error en xls ", e);
        }
        return excel;
    }

    public boolean isVieneDetalleOrdenUnif() {
        return vieneDetalleOrdenUnif;
    }

    public void setVieneDetalleOrdenUnif(boolean vieneDetalleOrdenUnif) {
        this.vieneDetalleOrdenUnif = vieneDetalleOrdenUnif;
    }

    public boolean isReporteVisible() {
        return reporteVisible;
    }

    public void setReporteVisible(boolean reporteVisible) {
        this.reporteVisible = reporteVisible;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

}
