package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public class CargaDocumentosCompulsasDTOAbstractHelper implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean visibleBtnEliminar;
    private boolean mostrarProrrogas;
    private boolean mostrarBtnProrrogas;
    private boolean mostrarPruebasAlegatosCompulsa;

    private String leyendaDoc;
    private String leyenda;
    private BigDecimal idPromocionCompulsaSeleccionadaHistorico;
    private BigDecimal idProrrogaCompulsaSeleccionado;
    private Date fechaEnvioProrrogaCompulsaSeleccionada;
    private Date fechaEnvioPromocionCompulsaSeleccionadaHistorico;

    private FecetContribuyente contribuyenteInfo;
    private FecetDocProrrogaOficio docProrrogaCompulsaSeleccionado;
    private FecetAlegatoOficio pruebaHistoricoCompulsaSeleccionada;
    private FecetProrrogaOficio prorrogaCompulsaSeleccionada;
    
    public void setMostrarPruebasAlegatosCompulsa(boolean mostrarPruebasAlegatosCompulsa) {
        this.mostrarPruebasAlegatosCompulsa = mostrarPruebasAlegatosCompulsa;
    }

    public boolean isMostrarPruebasAlegatosCompulsa() {
        return mostrarPruebasAlegatosCompulsa;
    }
    
    public void setLeyendaDoc(String leyendaDoc) {
        this.leyendaDoc = leyendaDoc;
    }

    public String getLeyendaDoc() {
        return leyendaDoc;
    }
    
    public void setIdPromocionCompulsaSeleccionadaHistorico(BigDecimal idPromocionCompulsaSeleccionadaHistorico) {
        this.idPromocionCompulsaSeleccionadaHistorico = idPromocionCompulsaSeleccionadaHistorico;
    }

    public BigDecimal getIdPromocionCompulsaSeleccionadaHistorico() {
        return idPromocionCompulsaSeleccionadaHistorico;
    }

    public void setFechaEnvioPromocionCompulsaSeleccionadaHistorico(Date fechaEnvioPromocionCompulsaSeleccionadaHistorico) {
        this.fechaEnvioPromocionCompulsaSeleccionadaHistorico
                = (fechaEnvioPromocionCompulsaSeleccionadaHistorico != null) ? (Date) fechaEnvioPromocionCompulsaSeleccionadaHistorico : null;
    }

    public Date getFechaEnvioPromocionCompulsaSeleccionadaHistorico() {
        return (fechaEnvioPromocionCompulsaSeleccionadaHistorico != null) ? (Date) fechaEnvioPromocionCompulsaSeleccionadaHistorico : null;
    }
    
    public void setProrrogaCompulsaSeleccionada(FecetProrrogaOficio prorrogaCompulsaSeleccionada) {
        this.prorrogaCompulsaSeleccionada = prorrogaCompulsaSeleccionada;
    }

    public FecetProrrogaOficio getProrrogaCompulsaSeleccionada() {
        return prorrogaCompulsaSeleccionada;
    }
    
    public void setIdProrrogaCompulsaSeleccionado(BigDecimal idProrrogaCompulsaSeleccionado) {
        this.idProrrogaCompulsaSeleccionado = idProrrogaCompulsaSeleccionado;
    }

    public BigDecimal getIdProrrogaCompulsaSeleccionado() {
        return idProrrogaCompulsaSeleccionado;
    }
    
    public void setFechaEnvioProrrogaCompulsaSeleccionada(Date fechaEnvioProrrogaCompulsaSeleccionada) {
        this.fechaEnvioProrrogaCompulsaSeleccionada
                = (fechaEnvioProrrogaCompulsaSeleccionada != null) ? (Date) fechaEnvioProrrogaCompulsaSeleccionada : null;
    }

    public Date getFechaEnvioProrrogaCompulsaSeleccionada() {
        return (fechaEnvioProrrogaCompulsaSeleccionada != null) ? (Date) fechaEnvioProrrogaCompulsaSeleccionada : null;
    }

    public void setPruebaHistoricoCompulsaSeleccionada(FecetAlegatoOficio pruebaHistoricoCompulsaSeleccionada) {
        this.pruebaHistoricoCompulsaSeleccionada = pruebaHistoricoCompulsaSeleccionada;
    }

    public FecetAlegatoOficio getPruebaHistoricoCompulsaSeleccionada() {
        return pruebaHistoricoCompulsaSeleccionada;
    }
    
    public void setDocProrrogaCompulsaSeleccionado(FecetDocProrrogaOficio docProrrogaCompulsaSeleccionado) {
        this.docProrrogaCompulsaSeleccionado = docProrrogaCompulsaSeleccionado;
    }

    public FecetDocProrrogaOficio getDocProrrogaCompulsaSeleccionado() {
        return docProrrogaCompulsaSeleccionado;
    }
    
    public void setVisibleBtnEliminar(boolean visibleBtnEliminar) {
        this.visibleBtnEliminar = visibleBtnEliminar;
    }

    public boolean isVisibleBtnEliminar() {
        return visibleBtnEliminar;
    }

    public void setMostrarProrrogas(boolean mostrarProrrogas) {
        this.mostrarProrrogas = mostrarProrrogas;
    }

    public boolean isMostrarProrrogas() {
        return mostrarProrrogas;
    }

    public void setMostrarBtnProrrogas(boolean mostrarBtnProrrogas) {
        this.mostrarBtnProrrogas = mostrarBtnProrrogas;
    }

    public boolean isMostrarBtnProrrogas() {
        return mostrarBtnProrrogas;
    }

    public void setContribuyenteInfo(FecetContribuyente contribuyenteInfo) {
        this.contribuyenteInfo = contribuyenteInfo;
    }

    public FecetContribuyente getContribuyenteInfo() {
        return contribuyenteInfo;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getLeyenda() {
        return leyenda;
    }

}
