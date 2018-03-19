package mx.gob.sat.siat.feagace.vista.ordenes.cifras;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaCifraImpuestoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.TotalCifrasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.ordenes.cifras.service.CifrasService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;
import mx.gob.sat.siat.feagace.vista.ordenes.cifras.helper.CifrasAttributeHelper;
import mx.gob.sat.siat.feagace.vista.ordenes.cifras.helper.CifrasColumasDetalleHelper;

public class CifrasManagedBean extends AbstractManagedBean {

    private static final long serialVersionUID = 1L;

    public static final BigDecimal SINOPCION = new BigDecimal(-1);
    private static final int NUM_1 = 1;
    private static final int NUM_2 = 2;
    private static final int NUM_3 = 3;

    @ManagedProperty(value = "#{cifrasService}")
    private transient CifrasService cifrasService;

    private transient CifrasAttributeHelper cifrasAttributeHelper;

    private transient CifrasColumasDetalleHelper columnaDetalleHelper;

    private List<TotalCifrasDTO> totalCifras;

    private List<FecetCifraDTO> listaEncabezadoCifras;

    private List<FecetCifraDTO> listaEncabezadoCifrasHistorico;

    private FecetCifraDTO encabezadoCifraSeleccionada;

    private FecetCifraDTO encabezadoCifraHistoricoSeleccionada;

    private BigDecimal totalEncabezado;

    private BigDecimal totalMontoCifras;

    private List<FeceaCifraImpuestoDTO> impuestosSeleccionados;

    private FeceaCifraImpuestoDTO impuestoSeleccionado;

    private List<FeceaCifraImpuestoDTO> impuestosDetalle;
    
    private int tabSeleccionado;

    public void configurarPantalla(int tipoPantalla) {
        switch (tipoPantalla) {
        case NUM_1:
            setValoresPantalla(true, false, false);
            break;
        case NUM_2:
            setValoresPantalla(false, true, false);
            break;
        case NUM_3:
            setValoresPantalla(false, false, true);
            break;
        default:
            setValoresPantalla(false, false, false);
            break;
        }
    }

    public void setValoresPantalla(boolean cobradas, boolean virtuales, boolean liquidadas) {
        getCifrasAttributeHelper().setPanelCifrasCobradasVisible(cobradas);
        getCifrasAttributeHelper().setPanelCifrasVirtualesVisible(virtuales);
        getCifrasAttributeHelper().setPanelCifrasLiquidadasVisible(liquidadas);
    }

    public void configurarDetalle(int tipoCifra) {
        switch (tipoCifra) {
        case NUM_1:
            setValoresDetalle(true, true, true, true, true);
            break;
        case NUM_2:
            setValoresDetalle(false, false, false, false, false);
            break;
        case NUM_3:
            setValoresDetalle(true, true, true, true, false);
            break;
        default:
            break;
        }
    }

    public void setValoresDetalle(boolean actualizacionesVisible, boolean multasVisible, boolean recargosVisible, boolean totalVisible,
            boolean parcialidadVisible) {
        getColumnaDetalleHelper().setActualizacionesVisible(actualizacionesVisible);
        getColumnaDetalleHelper().setMultasVisible(multasVisible);
        getColumnaDetalleHelper().setParcialidadVisible(parcialidadVisible);
        getColumnaDetalleHelper().setRecargosVisible(recargosVisible);
        getColumnaDetalleHelper().setTotalVisible(totalVisible);
    }

    public void mostrarDetalle() {
        if (getImpuestoSeleccionado().getParcialidad() == null) {
            getImpuestoSeleccionado().setParcialidad(new FecetParcialidadCifraDTO());
        }
        setImpuestosDetalle(new ArrayList<FeceaCifraImpuestoDTO>());
        getImpuestosDetalle().add(getImpuestoSeleccionado());
        configurarDetalle(getTabSeleccionado());
    }

    public void mostrarDetalleHistorico(BigDecimal idOrden) {
        setImpuestosDetalle(getCifrasService().obtenerImpuestosCifrasHistorico(idOrden, getEncabezadoCifraHistoricoSeleccionada().getTipoCifra().getIdCifra(),
                getEncabezadoCifraHistoricoSeleccionada().getConsecutivo()));
        configurarDetalle(getTabSeleccionado());
    }

    public void obtenerListaCifras(BigDecimal idOrden, BigDecimal tipoCifra) {
        BigDecimal montoTotal = Constantes.BIG_DECIMAL_CERO;
        setListaEncabezadoCifras(cifrasService.obtenerCifrasPorOrden(idOrden, tipoCifra));
        for (FecetCifraDTO cifra : getListaEncabezadoCifras()) {
            montoTotal = montoTotal.add(cifra.getTotal());
        }
        setTotalMontoCifras(montoTotal);
    }

    public void obtenerTotalEncabezado() {
        BigDecimal sumatoria = Constantes.BIG_DECIMAL_CERO;
        for (TotalCifrasDTO total : getTotalCifras()) {
            sumatoria = sumatoria.add(total.getMontoCifra());
        }
        setTotalEncabezado(sumatoria);
    }

    public void obtenerHistoricoEncabezado(BigDecimal idOrden) {
        setListaEncabezadoCifrasHistorico(getCifrasService().obtenerEncabezadoCifrasHistorico(getEncabezadoCifraSeleccionada().getIdCifra(), idOrden));
    }

    public CifrasAttributeHelper getCifrasAttributeHelper() {
        return cifrasAttributeHelper;
    }

    public void actualizaTotales() {
        setTotalMontoCifras(BigDecimal.ZERO);
        for (FecetCifraDTO cifra : getListaEncabezadoCifras()) {
            BigDecimal totalCifra = BigDecimal.ZERO;
            for (FeceaCifraImpuestoDTO cifraImpuesto : cifra.getCifras()) {
                totalCifra = totalCifra.add(cifraImpuesto.getTotal());
            }
            cifra.setTotal(totalCifra);
            setTotalMontoCifras(getTotalMontoCifras().add(cifra.getTotal()));
        }
    }

    public void setCifrasAttributeHelper(CifrasAttributeHelper cifrasAttributeHelper) {
        this.cifrasAttributeHelper = cifrasAttributeHelper;
    }

    public CifrasService getCifrasService() {
        return cifrasService;
    }

    public void setCifrasService(CifrasService cifrasService) {
        this.cifrasService = cifrasService;
    }

    public List<TotalCifrasDTO> getTotalCifras() {
        return totalCifras;
    }

    public void setTotalCifras(List<TotalCifrasDTO> totalCifras) {
        this.totalCifras = totalCifras;
    }

    public List<FecetCifraDTO> getListaEncabezadoCifras() {
        return listaEncabezadoCifras;
    }

    public void setListaEncabezadoCifras(List<FecetCifraDTO> listaEncabezadoCifras) {
        this.listaEncabezadoCifras = listaEncabezadoCifras;
    }

    public FecetCifraDTO getEncabezadoCifraSeleccionada() {
        return encabezadoCifraSeleccionada;
    }

    public void setEncabezadoCifraSeleccionada(FecetCifraDTO encabezadoCifraSeleccionada) {
        this.encabezadoCifraSeleccionada = encabezadoCifraSeleccionada;
    }

    public BigDecimal getTotalEncabezado() {
        return totalEncabezado;
    }

    public void setTotalEncabezado(BigDecimal totalEncabezado) {
        this.totalEncabezado = totalEncabezado;
    }

    public BigDecimal getTotalMontoCifras() {
        return totalMontoCifras;
    }

    public void setTotalMontoCifras(BigDecimal totalMontoCifras) {
        this.totalMontoCifras = totalMontoCifras;
    }

    public List<FeceaCifraImpuestoDTO> getImpuestosSeleccionados() {
        return impuestosSeleccionados;
    }

    public void setImpuestosSeleccionados(List<FeceaCifraImpuestoDTO> impuestosSeleccionados) {
        this.impuestosSeleccionados = impuestosSeleccionados;
    }

    public FeceaCifraImpuestoDTO getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }

    public void setImpuestoSeleccionado(FeceaCifraImpuestoDTO impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public CifrasColumasDetalleHelper getColumnaDetalleHelper() {
        return columnaDetalleHelper;
    }

    public void setColumnaDetalleHelper(CifrasColumasDetalleHelper columnaDetalleHelper) {
        this.columnaDetalleHelper = columnaDetalleHelper;
    }

    public List<FeceaCifraImpuestoDTO> getImpuestosDetalle() {
        return impuestosDetalle;
    }

    public void setImpuestosDetalle(List<FeceaCifraImpuestoDTO> impuestosDetalle) {
        this.impuestosDetalle = impuestosDetalle;
    }

    public List<FecetCifraDTO> getListaEncabezadoCifrasHistorico() {
        return listaEncabezadoCifrasHistorico;
    }

    public void setListaEncabezadoCifrasHistorico(List<FecetCifraDTO> listaEncabezadoCifrasHistorico) {
        this.listaEncabezadoCifrasHistorico = listaEncabezadoCifrasHistorico;
    }

    public FecetCifraDTO getEncabezadoCifraHistoricoSeleccionada() {
        return encabezadoCifraHistoricoSeleccionada;
    }

    public void setEncabezadoCifraHistoricoSeleccionada(FecetCifraDTO encabezadoCifraHistoricoSeleccionada) {
        this.encabezadoCifraHistoricoSeleccionada = encabezadoCifraHistoricoSeleccionada;
    }
    
    public StreamedContent getArchivoPromocionDescarga() {
        getCifrasAttributeHelper().setArchivoDescarga(
                getDescargaArchivo(getCifrasAttributeHelper().getDocumentoSeleccionado().getRutaArchivo(),
                getCifrasAttributeHelper().getDocumentoSeleccionado().getNombre()));
        return getCifrasAttributeHelper().getArchivoDescarga();
    }
    
    public int getTabSeleccionado() {
        return tabSeleccionado;
    }

    public void setTabSeleccionado(int tabSeleccionado) {
        this.tabSeleccionado = tabSeleccionado;
    }

}
