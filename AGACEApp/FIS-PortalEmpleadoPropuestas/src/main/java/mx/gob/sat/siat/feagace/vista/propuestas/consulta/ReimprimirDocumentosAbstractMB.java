/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta;

import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.ordenes.AsociadosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.ConsultarReimprimirDocumentosService;
import mx.gob.sat.siat.feagace.negocio.ordenes.FecetDocAsociadoService;
import mx.gob.sat.siat.feagace.negocio.ordenes.ReimprimirDocsPistaService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ConsultarReimprimirDocumentosRules;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarMetodoOficioAsociadoRule;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper.ConsultarReimprimirDocumentosHelper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ReimprimirDocumentosAbstractMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = 7902077569836346441L;

    private boolean renderTablaOrdenesGrl;
    private boolean visiblePnlConsultarDocBusqueda;
    private boolean visiblePnlConsultarDocRelacionada;
    private boolean visiblePnlDocPruebasAlegatos;
    private boolean visiblePnlOficiosDependientes;
    private boolean visiblePnlAnexosOficiosDependientes;
    private boolean visiblePnlAnexosOficios;
    private boolean visiblePnlOficiosRequerimiento;
    private boolean visiblePnlDocOficioPruebasAlegatos;
    private boolean visiblePnlDocProrrogaOrden;
    private boolean visiblePnlDocProrrogaOficio;
    private boolean visiblepnlDocAnexosResolProrrogaOrden;
    private boolean visiblepnlDocAnexosResolProrrogaOficio;

    private String rutaArchivo;
    private String nombreArchivo;

    @ManagedProperty(value = "#{consultarReimprimirDocumentosService}")
    private transient ConsultarReimprimirDocumentosService consultarReimprimirDocumentosService;
    @ManagedProperty(value = "#{consultarReimprimirDocumentosHelper}")
    private transient ConsultarReimprimirDocumentosHelper consultarReimprimirDocumentosHelper;
    @ManagedProperty(value = "#{consultarReimprimirDocumentosRules}")
    private transient ConsultarReimprimirDocumentosRules consultarReimprimirDocumentosRules;
    @ManagedProperty(value = "#{validarMetodoOficioAsociadoRule}")
    private transient ValidarMetodoOficioAsociadoRule validarMetodoOficioAsociadoRule;
    @ManagedProperty(value = "#{asociadosService}")
    private transient AsociadosService asociadosService;
    @ManagedProperty(value = "#{fecetDocAsociadoService}")
    private transient FecetDocAsociadoService fecetDocAsociadoService;
    @ManagedProperty(value = "#{consultarPapelesTrabajoService}")
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService;
    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;
    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;
    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;
    @ManagedProperty(value = "#{plazosService}")
    private transient PlazosServiceOrdenes plazosService;
    @ManagedProperty(value = "#{reimprimirDocsPistaService}")
    private transient ReimprimirDocsPistaService reimprimirDocsPistaService;

    public ConsultarReimprimirDocumentosService getConsultarReimprimirDocumentosService() {
        return consultarReimprimirDocumentosService;
    }

    public void setConsultarReimprimirDocumentosService(
            ConsultarReimprimirDocumentosService consultarReimprimirDocumentosService) {
        this.consultarReimprimirDocumentosService = consultarReimprimirDocumentosService;
    }

    public ConsultarReimprimirDocumentosHelper getConsultarReimprimirDocumentosHelper() {
        return consultarReimprimirDocumentosHelper;
    }

    public void setConsultarReimprimirDocumentosHelper(
            ConsultarReimprimirDocumentosHelper consultarReimprimirDocumentosHelper) {
        this.consultarReimprimirDocumentosHelper = consultarReimprimirDocumentosHelper;
    }

    public ConsultarReimprimirDocumentosRules getConsultarReimprimirDocumentosRules() {
        return consultarReimprimirDocumentosRules;
    }

    public void setConsultarReimprimirDocumentosRules(
            ConsultarReimprimirDocumentosRules consultarReimprimirDocumentosRules) {
        this.consultarReimprimirDocumentosRules = consultarReimprimirDocumentosRules;
    }

    public ValidarMetodoOficioAsociadoRule getValidarMetodoOficioAsociadoRule() {
        return validarMetodoOficioAsociadoRule;
    }

    public void setValidarMetodoOficioAsociadoRule(ValidarMetodoOficioAsociadoRule validarMetodoOficioAsociadoRule) {
        this.validarMetodoOficioAsociadoRule = validarMetodoOficioAsociadoRule;
    }

    public boolean isVisiblepnlDocAnexosResolProrrogaOrden() {
        return visiblepnlDocAnexosResolProrrogaOrden;
    }

    public void setVisiblepnlDocAnexosResolProrrogaOrden(boolean visiblepnlDocAnexosResolProrrogaOrden) {
        this.visiblepnlDocAnexosResolProrrogaOrden = visiblepnlDocAnexosResolProrrogaOrden;
    }

    public boolean isVisiblepnlDocAnexosResolProrrogaOficio() {
        return visiblepnlDocAnexosResolProrrogaOficio;
    }

    public void setVisiblepnlDocAnexosResolProrrogaOficio(boolean visiblepnlDocAnexosResolProrrogaOficio) {
        this.visiblepnlDocAnexosResolProrrogaOficio = visiblepnlDocAnexosResolProrrogaOficio;
    }

    public boolean isVisiblePnlDocProrrogaOficio() {
        return visiblePnlDocProrrogaOficio;
    }

    public void setVisiblePnlDocProrrogaOficio(boolean visiblePnlDocProrrogaOficio) {
        this.visiblePnlDocProrrogaOficio = visiblePnlDocProrrogaOficio;
    }

    public boolean isVisiblePnlDocOficioPruebasAlegatos() {
        return visiblePnlDocOficioPruebasAlegatos;
    }

    public void setVisiblePnlDocOficioPruebasAlegatos(boolean visiblePnlDocOficioPruebasAlegatos) {
        this.visiblePnlDocOficioPruebasAlegatos = visiblePnlDocOficioPruebasAlegatos;
    }

    public boolean isVisiblePnlDocProrrogaOrden() {
        return visiblePnlDocProrrogaOrden;
    }

    public void setVisiblePnlDocProrrogaOrden(boolean visiblePnlDocProrrogaOrden) {
        this.visiblePnlDocProrrogaOrden = visiblePnlDocProrrogaOrden;
    }

    public boolean isVisiblePnlAnexosOficios() {
        return visiblePnlAnexosOficios;
    }

    public void setVisiblePnlAnexosOficios(boolean visiblePnlAnexosOficios) {
        this.visiblePnlAnexosOficios = visiblePnlAnexosOficios;
    }

    public boolean isVisiblePnlOficiosRequerimiento() {
        return visiblePnlOficiosRequerimiento;
    }

    public void setVisiblePnlOficiosRequerimiento(boolean visiblePnlOficiosRequerimiento) {
        this.visiblePnlOficiosRequerimiento = visiblePnlOficiosRequerimiento;
    }

    public boolean isVisiblePnlOficiosDependientes() {
        return visiblePnlOficiosDependientes;
    }

    public void setVisiblePnlOficiosDependientes(boolean visiblePnlOficiosDependientes) {
        this.visiblePnlOficiosDependientes = visiblePnlOficiosDependientes;
    }

    public boolean isVisiblePnlAnexosOficiosDependientes() {
        return visiblePnlAnexosOficiosDependientes;
    }

    public void setVisiblePnlAnexosOficiosDependientes(boolean visiblePnlAnexosOficiosDependientes) {
        this.visiblePnlAnexosOficiosDependientes = visiblePnlAnexosOficiosDependientes;
    }

    public boolean isVisiblePnlDocPruebasAlegatos() {
        return visiblePnlDocPruebasAlegatos;
    }

    public void setVisiblePnlDocPruebasAlegatos(boolean visiblePnlDocPruebasAlegatos) {
        this.visiblePnlDocPruebasAlegatos = visiblePnlDocPruebasAlegatos;
    }

    public boolean isRenderTablaOrdenesGrl() {
        return renderTablaOrdenesGrl;
    }

    public void setRenderTablaOrdenesGrl(boolean renderTablaOrdenesGrl) {
        this.renderTablaOrdenesGrl = renderTablaOrdenesGrl;
    }

    public boolean isVisiblePnlConsultarDocBusqueda() {
        return visiblePnlConsultarDocBusqueda;
    }

    public void setVisiblePnlConsultarDocBusqueda(boolean visiblePnlConsultarDocBusqueda) {
        this.visiblePnlConsultarDocBusqueda = visiblePnlConsultarDocBusqueda;
    }

    public boolean isVisiblePnlConsultarDocRelacionada() {
        return visiblePnlConsultarDocRelacionada;
    }

    public void setVisiblePnlConsultarDocRelacionada(boolean visiblePnlConsultarDocRelacionada) {
        this.visiblePnlConsultarDocRelacionada = visiblePnlConsultarDocRelacionada;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public AsociadosService getAsociadosService() {
        return asociadosService;
    }

    public void setAsociadosService(AsociadosService asociadosService) {
        this.asociadosService = asociadosService;
    }

    public FecetDocAsociadoService getFecetDocAsociadoService() {
        return fecetDocAsociadoService;
    }

    public void setFecetDocAsociadoService(FecetDocAsociadoService fecetDocAsociadoService) {
        this.fecetDocAsociadoService = fecetDocAsociadoService;
    }

    public ConsultarPapelesTrabajoService getConsultarPapelesTrabajoService() {
        return consultarPapelesTrabajoService;
    }

    public void setConsultarPapelesTrabajoService(ConsultarPapelesTrabajoService consultarPapelesTrabajoService) {
        this.consultarPapelesTrabajoService = consultarPapelesTrabajoService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setSeguimientoOrdenesService(SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    public void setContribuyenteService(ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public PlazosServiceOrdenes getPlazosService() {
        return plazosService;
    }

    public void setPlazosService(PlazosServiceOrdenes plazosService) {
        this.plazosService = plazosService;
    }

    public ReimprimirDocsPistaService getReimprimirDocsPistaService() {
        return reimprimirDocsPistaService;
    }

    public void setReimprimirDocsPistaService(ReimprimirDocsPistaService reimprimirDocsPistaService) {
        this.reimprimirDocsPistaService = reimprimirDocsPistaService;
    }

}
