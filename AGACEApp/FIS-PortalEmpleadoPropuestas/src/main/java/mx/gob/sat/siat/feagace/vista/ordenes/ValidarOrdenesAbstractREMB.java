/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaPorValidar;
import mx.gob.sat.siat.feagace.negocio.common.FececMotivoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FirmadoCadenasOriginalesService;
import mx.gob.sat.siat.feagace.negocio.ordenes.OrdenesPorValidarReService;
import mx.gob.sat.siat.feagace.negocio.propuestas.FecetPropuestaService;
import mx.gob.sat.siat.feagace.negocio.propuestas.PropuestasRechazadasVerifDoctoService;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.ordenes.helper.ValidarOrdenesHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ValidarOrdenesAbstractREMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = -7441773931155558328L;

    private BigDecimal metodoSeleccionado;
    private String rechazoDescripcion;
    private Integer totalValidar;

    private List<PropuestaPorValidar> listaValidarOrdenesSeleccionadas;
    private List<AgaceOrden> listaOrdenesValidar;
    private List<PropuestaPorValidar> listaPropuestaPorValidar;

    private AgaceOrden ordenSeleccionDescarga;
    private PropuestaPorValidar propuestaSeleccionadaParaRech;
    private PropuestaPorValidar propuestaSeleccionadaParaEnviar;
    private PropuestaPorValidar propuestaParaDescargar;
    private AgaceOrden rechazoSeleccionado;
    private FecetRechazoPropuesta documentoSeleccionadoRechazo;

    private List<FecetDocOrden> listaDocumentosOrden;
    private List<FecetOficio> oficiosPendientesDeFirmar;
    private FecetOficio oficioSeleccionado;
    private FecetDocOrden docSeleccionado;
    private List<FecetDocOrden> listaSeleccionDocumentosOrden;
    private List<FecetOficio> listaSeleccionOficiosPendientes;
    private BigDecimal accionOrigen;
    private String titulo;

    @ManagedProperty(value = "#{ordenesPorValidarReService}")
    private transient OrdenesPorValidarReService ordenesPorValidarReService;

    @ManagedProperty(value = "#{fecetPropuestaService}")
    private transient FecetPropuestaService fecetPropuestaService;

    @ManagedProperty(value = "#{firmadoCadenasOriginalesService}")
    private transient FirmadoCadenasOriginalesService firmadoCadenasOriginalesService;

    @ManagedProperty(value = "#{fececMotivoService}")
    private transient FececMotivoService fececMotivoService;

    @ManagedProperty(value = "#{propuestasRechazadasVerifDoctoService}")
    private transient PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService;

    @ManagedProperty(value = "#{validarOrdenesHelper}")
    private transient ValidarOrdenesHelper validarOrdenesHelper;

    public PropuestasRechazadasVerifDoctoService getPropuestasRechazadasVerifDoctoService() {
        return propuestasRechazadasVerifDoctoService;
    }

    public void setPropuestasRechazadasVerifDoctoService(
            PropuestasRechazadasVerifDoctoService propuestasRechazadasVerifDoctoService) {
        this.propuestasRechazadasVerifDoctoService = propuestasRechazadasVerifDoctoService;
    }

    public void setFececMotivoService(FececMotivoService fececMotivoService) {
        this.fececMotivoService = fececMotivoService;
    }

    public FececMotivoService getFececMotivoService() {
        return fececMotivoService;
    }

    public void setFecetPropuestaService(
            FecetPropuestaService fecetPropuestaService) {
        this.fecetPropuestaService = fecetPropuestaService;
    }

    public FecetPropuestaService getFecetPropuestaService() {
        return fecetPropuestaService;
    }

    public void setFirmadoCadenasOriginalesService(
            FirmadoCadenasOriginalesService firmadoCadenasOriginalesService) {
        this.firmadoCadenasOriginalesService = firmadoCadenasOriginalesService;
    }

    public FirmadoCadenasOriginalesService getFirmadoCadenasOriginalesService() {
        return firmadoCadenasOriginalesService;
    }

    public void setOrdenesPorValidarReService(
            OrdenesPorValidarReService ordenesPorValidarReService) {
        this.ordenesPorValidarReService = ordenesPorValidarReService;
    }

    public OrdenesPorValidarReService getOrdenesPorValidarReService() {
        return ordenesPorValidarReService;
    }

    public void setDocumentoSeleccionadoRechazo(
            FecetRechazoPropuesta documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public FecetRechazoPropuesta getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public void setPropuestaSeleccionadaParaEnviar(
            PropuestaPorValidar propuestaSeleccionadaParaEnviar) {
        this.propuestaSeleccionadaParaEnviar = propuestaSeleccionadaParaEnviar;
    }

    public PropuestaPorValidar getPropuestaSeleccionadaParaEnviar() {
        return propuestaSeleccionadaParaEnviar;
    }

    public void setRechazoSeleccionado(AgaceOrden rechazoSeleccionado) {
        this.rechazoSeleccionado = rechazoSeleccionado;
    }

    public AgaceOrden getRechazoSeleccionado() {
        return rechazoSeleccionado;
    }

    public void setRechazoDescripcion(String rechazoDescripcion) {
        this.rechazoDescripcion = rechazoDescripcion;
    }

    public String getRechazoDescripcion() {
        return rechazoDescripcion;
    }

    public Integer getTotalValidar() {
        return totalValidar;
    }

    public void setTotalValidar(Integer totalValidar) {
        this.totalValidar = totalValidar;
    }

    public void setListaPropuestaPorValidar(
            List<PropuestaPorValidar> listaPropuestaPorValidar) {
        this.listaPropuestaPorValidar = listaPropuestaPorValidar;
    }

    public List<PropuestaPorValidar> getListaPropuestaPorValidar() {
        return listaPropuestaPorValidar;
    }

    public void setPropuestaSeleccionadaParaRech(
            PropuestaPorValidar propuestaSeleccionadaParaRech) {
        this.propuestaSeleccionadaParaRech = propuestaSeleccionadaParaRech;
    }

    public PropuestaPorValidar getPropuestaSeleccionadaParaRech() {
        return propuestaSeleccionadaParaRech;
    }

    public void setPropuestaParaDescargar(
            PropuestaPorValidar propuestaParaDescargar) {
        this.propuestaParaDescargar = propuestaParaDescargar;
    }

    public PropuestaPorValidar getPropuestaParaDescargar() {
        return propuestaParaDescargar;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    public void setListaOrdenesValidar(List<AgaceOrden> listaOrdenesValidar) {
        this.listaOrdenesValidar = listaOrdenesValidar;
    }

    public List<AgaceOrden> getListaOrdenesValidar() {
        return listaOrdenesValidar;
    }

    public void setListaValidarOrdenesSeleccionadas(
            List<PropuestaPorValidar> listaValidarOrdenesSeleccionadas) {
        this.listaValidarOrdenesSeleccionadas = listaValidarOrdenesSeleccionadas;
    }

    public List<PropuestaPorValidar> getListaValidarOrdenesSeleccionadas() {
        return listaValidarOrdenesSeleccionadas;
    }

    public BigDecimal getMetodoSeleccionado() {
        return metodoSeleccionado;
    }

    public void setMetodoSeleccionado(BigDecimal metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }

    public List<FecetDocOrden> getListaDocumentosOrden() {
        return listaDocumentosOrden;
    }

    public void setListaDocumentosOrden(List<FecetDocOrden> listaDocumentosOrden) {
        this.listaDocumentosOrden = listaDocumentosOrden;
    }

    public List<FecetOficio> getOficiosPendientesDeFirmar() {
        return oficiosPendientesDeFirmar;
    }

    public void setOficiosPendientesDeFirmar(
            List<FecetOficio> oficiosPendientesDeFirmar) {
        this.oficiosPendientesDeFirmar = oficiosPendientesDeFirmar;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetDocOrden getDocSeleccionado() {
        return docSeleccionado;
    }

    public void setDocSeleccionado(FecetDocOrden docSeleccionado) {
        this.docSeleccionado = docSeleccionado;
    }

    public ValidarOrdenesHelper getValidarOrdenesHelper() {
        return validarOrdenesHelper;
    }

    public void setValidarOrdenesHelper(
            ValidarOrdenesHelper validarOrdenesHelper) {
        this.validarOrdenesHelper = validarOrdenesHelper;
    }

    public List<FecetDocOrden> getListaSeleccionDocumentosOrden() {
        return listaSeleccionDocumentosOrden;
    }

    public void setListaSeleccionDocumentosOrden(
            List<FecetDocOrden> listaSeleccionDocumentosOrden) {
        this.listaSeleccionDocumentosOrden = listaSeleccionDocumentosOrden;
    }

    public List<FecetOficio> getListaSeleccionOficiosPendientes() {
        return listaSeleccionOficiosPendientes;
    }

    public void setListaSeleccionOficiosPendientes(
            List<FecetOficio> listaSeleccionOficiosPendientes) {
        this.listaSeleccionOficiosPendientes = listaSeleccionOficiosPendientes;
    }

    public BigDecimal getAccionOrigen() {
        return accionOrigen;
    }

    public void setAccionOrigen(BigDecimal accionOrigen) {
        this.accionOrigen = accionOrigen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
