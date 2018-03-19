package mx.gob.sat.siat.feagace.vista.common;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

public class ReportesManagedBean extends ReportesManagedBeanAbstract {

    private static final long serialVersionUID = 6620947154594145088L;

    public void actualizaOrden() {
        setVisibleCompleja(false);
        setVisibleOrden(false);
        if (getTipoBusqueda() != null && getTipoBusqueda().intValue() == TIPO_BUSQUEDA_ORDEN) {

            if (getMetodoSeleccionado().intValue() != -1) {
                setOrdenVisible(true);
                for (FececMetodo feceaMetodo : getListaMetodos()) {
                    if (feceaMetodo.getIdMetodo().equals(getMetodoSeleccionado())) {
                        setLabelOrden(feceaMetodo.getNombre());
                    }
                }
            } else {
                setOrdenVisible(false);
            }
        } else {
            setOrdenVisible(false);
        }
    }

    public void consultaEjecutivo() {
        if (!validaRango()) {
            return;
        }

        setListaEjecutivo(getReportesService().consultaReporteEjecutivo(getRangoInferior(), getRangoSuperior(),
                getMetodoSeleccionado(), getAreaEmisora(),
                getEstatus()));
        if (getListaEjecutivo() != null && !getListaEjecutivo().isEmpty()) {
            setValoresGraficaEjecutivo(new CartesianChartModel());
            ChartSeries ordenes = new ChartSeries();
            for (ReporteEjecutivoDTO reporteEjecutivo : getListaEjecutivo()) {
                ordenes.set(reporteEjecutivo.getMetodo(), reporteEjecutivo.getNumeroOrdenes());
            }
            getValoresGraficaEjecutivo().addSeries(ordenes);
            setVisibleGrafica(true);
        } else {
            setVisibleGrafica(false);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "", "No se encontraron registros."));

        }

    }

    private boolean validaTipoBusqueda() {
        if (getTipoBusqueda() == null) {
            FacesUtil.addErrorMessage(null, "Debe seleccionar un tipo de b�squeda.");
            return false;
        }
        return true;
    }

    public void consultaGerencial() {
        if (!validaTipoBusqueda()) {
            return;
        }

        if (getTipoBusqueda() != null && getTipoBusqueda().intValue() == TIPO_BUSQUEDA_ORDEN) {
            if (!validaMetodoOrden()) {
                setVisibleCompleja(false);
                setVisibleOrden(false);
                return;
            }
            setVisibleCompleja(false);
            setVisibleOrden(true);

            setListaReporteGerencial(getReportesService().consultaReporteGerencial(getCveOrden()));
            if (getListaReporteGerencial() != null && getListaReporteGerencial().size() > 0) {
                setVisibleExportarGerencial(true);
            } else {
                setVisibleExportarGerencial(false);
            }
            if (getListaReporteGerencial() != null && getListaReporteGerencial().size() > 0) {
                setReporteGerencialDTO(getListaReporteGerencial().get(0));
            } else {
                setReporteGerencialDTO(new ReporteGerencialDTO());
            }

            
        } else if (getTipoBusqueda() != null && getTipoBusqueda().intValue() == TIPO_BUSQUEDA_COMPLEJA) {
            if (!validaMetodoOrdenEstado()) {
                setVisibleCompleja(false);
                setVisibleOrden(false);
                return;
            }

            setVisibleCompleja(true);
            setVisibleOrden(false);
            /**
             * aun en proceso de definicion *
             */
            setListaReporteGerencial(getReportesService().consultaReporteGerencial(getMetodoSeleccionado(),
                    BigDecimal.valueOf(1),
                    getAreaEmisora()));
            if (getListaReporteGerencial() != null && getListaReporteGerencial().size() > 0) {
                setVisibleExportarGerencial(true);
            } else {
                setVisibleExportarGerencial(false);
            }
        }       
    }

    private boolean validaMetodoOrden() {
        boolean valida = true;

        if (getMetodoSeleccionado().intValue() == -1) {
            FacesUtil.addErrorMessage(null, "Debe seleccionar un método para realizar la consulta");
            valida = false;
        } else if (getCveOrden() == null || getCveOrden().trim().length() < Constantes.ENTERO_TRECE) {
            FacesUtil.addErrorMessage(null, "N�mero de �rden incorrecta");
            valida = false;
        }
        return valida;
    }

    private boolean validaMetodoOrdenEstado() {
        boolean valida = true;

        if (getMetodoSeleccionado().intValue() == -1) {
            FacesUtil.addErrorMessage(null, "Debe seleccionar un método para realizar la consulta");
            valida = false;
        } else if (getEstatus() == null || getEstatus().intValue() == -1) {
            FacesUtil.addErrorMessage(null, "Estatus incorrecta");
            valida = false;
        }
        return valida;
    }

    public void actualizaBusqueda() {
        setMetodoSeleccionado(BigDecimal.valueOf(-1));
        setAreaEmisora(BigDecimal.valueOf(-1));
        setOrdenVisible(false);

        setVisibleCompleja(false);
        setVisibleOrden(false);
        if (getTipoBusqueda().intValue() == TIPO_BUSQUEDA_ORDEN) {
            setTipoBusquedaNumeroOrden(false);
            setTipoBusquedaCompleja(true);
        } else if (getTipoBusqueda().intValue() == TIPO_BUSQUEDA_COMPLEJA) {
            setTipoBusquedaCompleja(false);
            setTipoBusquedaNumeroOrden(false);
        }
    }

    public void exportaReporteGerencial() {

    }

    public boolean validaRango() {

        boolean valida = true;
        StringBuilder error = new StringBuilder();
        error.append(FacesUtil.getMessageResourceString("error.campo.obligatorio")).append(": ");
        if (getRangoInferior() == null) {
            error.append(" [Rango inicial de b�squeda]");

            valida = false;
        }
        if (getRangoSuperior() == null) {
            error.append(" [Rango final de b�squeda]");
            valida = false;
        }
        if (getRangoInferior() != null && getRangoSuperior() != null
                && getRangoInferior().compareTo(getRangoSuperior()) >= 0) {
            error.append(" [Rango final debe ser mayor a rango inicial.]");
            valida = false;
        }
        if (!valida) {
            FacesUtil.addErrorMessage(null, error.toString());
        }

        return valida;
    }

    private static class Grafica {

        private String metodo;
        private BigDecimal numeroOrdenes;

        public void setMetodo(String metodo) {
            this.metodo = metodo;
        }

        public String getMetodo() {
            return metodo;
        }

        public void setNumeroOrdenes(BigDecimal numeroOrdenes) {
            this.numeroOrdenes = numeroOrdenes;
        }

        public BigDecimal getNumeroOrdenes() {
            return numeroOrdenes;
        }
    }
}
