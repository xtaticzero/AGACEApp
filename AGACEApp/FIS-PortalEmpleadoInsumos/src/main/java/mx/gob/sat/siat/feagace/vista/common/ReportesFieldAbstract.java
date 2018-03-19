package mx.gob.sat.siat.feagace.vista.common;

import java.math.BigDecimal;
import java.util.Date;

public class ReportesFieldAbstract extends AbstractManagedBean {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean visibleCompleja;
    private boolean visibleOrden;
    private boolean ordenVisible;
    private boolean tipoBusquedaNumeroOrden;
    private boolean tipoBusquedaCompleja;
    private boolean visibleExportarGerencial;
    private boolean visibleGrafica;
    private String labelOrden;
    private String cveOrden;
    private BigDecimal metodoSeleccionado;
    private BigDecimal tipoBusqueda;
    private BigDecimal areaEmisora;
    private BigDecimal estatus;
    private Date rangoInferior;
    private Date rangoSuperior;
    
    public void setMetodoSeleccionado(BigDecimal metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }

    public BigDecimal getMetodoSeleccionado() {
        return metodoSeleccionado;
    }


    public void setOrdenVisible(boolean ordenVisible) {
        this.ordenVisible = ordenVisible;
    }

    public boolean isOrdenVisible() {
        return ordenVisible;
    }

    public void setTipoBusqueda(BigDecimal tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public BigDecimal getTipoBusqueda() {
        return tipoBusqueda;
    }

    public void setTipoBusquedaNumeroOrden(boolean tipoBusquedaNumeroOrden) {
        this.tipoBusquedaNumeroOrden = tipoBusquedaNumeroOrden;
    }

    public boolean isTipoBusquedaNumeroOrden() {
        return tipoBusquedaNumeroOrden;
    }

    public void setTipoBusquedaCompleja(boolean tipoBusquedaCompleja) {
        this.tipoBusquedaCompleja = tipoBusquedaCompleja;
    }

    public boolean isTipoBusquedaCompleja() {
        return tipoBusquedaCompleja;
    }

    public void setLabelOrden(String labelOrden) {
        this.labelOrden = labelOrden;
    }

    public String getLabelOrden() {
        return labelOrden;
    }
    
    public void setAreaEmisora(BigDecimal areaEmisora) {
        this.areaEmisora = areaEmisora;
    }

    public BigDecimal getAreaEmisora() {
        return areaEmisora;
    }
    
    public void setVisibleGrafica(boolean visibleGrafica) {
        this.visibleGrafica = visibleGrafica;
    }

    public boolean isVisibleGrafica() {
        return visibleGrafica;
    }

    public void setRangoInferior(final Date rangoInferior) {
        this.rangoInferior = (rangoInferior != null) ? (Date)rangoInferior.clone() : null;
    }

    public Date getRangoInferior() {
        return (rangoInferior != null) ? (Date)rangoInferior.clone() : null;
    }

    public void setRangoSuperior(final Date rangoSuperior) {
        this.rangoSuperior = (rangoSuperior != null) ? (Date)rangoSuperior.clone() : null;
    }

    public Date getRangoSuperior() {
        return (rangoSuperior != null) ? (Date)rangoSuperior.clone() : null;
    }

    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public BigDecimal getEstatus() {
        return estatus;
    }
    
    public void setVisibleCompleja(boolean visibleCompleja) {
        this.visibleCompleja = visibleCompleja;
    }

    public boolean isVisibleCompleja() {
        return visibleCompleja;
    }

    public void setVisibleOrden(boolean visibleOrden) {
        this.visibleOrden = visibleOrden;
    }

    public boolean isVisibleOrden() {
        return visibleOrden;
    }

    public void setCveOrden(String cveOrden) {
        this.cveOrden = cveOrden;
    }

    public String getCveOrden() {
        return cveOrden;
    }

    public void setVisibleExportarGerencial(boolean visibleExportarGerencial) {
        this.visibleExportarGerencial = visibleExportarGerencial;
    }

    public boolean isVisibleExportarGerencial() {
        return visibleExportarGerencial;
    }
}
