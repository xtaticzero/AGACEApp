/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.consulta.common;

import mx.gob.sat.siat.base.vista.BaseManagedBean;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ReimprimirDocumentosAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = 7702829096139837137L;

    protected static final String PERFIL = "perfil";

    private boolean renderTablaOrdenesGrl;
    private boolean visiblePnlConsultarDocBusqueda;
    private boolean visiblePnlConsultarDocRelacionada;
    private boolean visiblePnlDocPruebasAlegatos;
    private boolean visiblePnlDocOficioPruebasAlegatos;
    private boolean visiblePnlOficiosDependientes;
    private boolean visiblePnlAnexosOficiosDependientes;
    private boolean visiblePnlAnexosOficios;
    private boolean visiblePnlOficiosRequerimiento;
    private boolean visiblePnlDocProrrogaOrden;
    private boolean visiblePnlDocProrrogaOficio;
    private boolean visiblepnlDocAnexosResolProrrogaOrden;
    private boolean visiblepnlDocAnexosResolProrrogaOficio;
    private boolean visiblePruebasPericiales;

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

    public boolean isVisiblePnlDocPruebasAlegatos() {
        return visiblePnlDocPruebasAlegatos;
    }

    public void setVisiblePnlDocPruebasAlegatos(boolean visiblePnlDocPruebasAlegatos) {
        this.visiblePnlDocPruebasAlegatos = visiblePnlDocPruebasAlegatos;
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

    public boolean isVisiblepnlDocAnexosResolProrrogaOrden() {
        return visiblepnlDocAnexosResolProrrogaOrden;
    }

    public void setVisiblepnlDocAnexosResolProrrogaOrden(boolean visiblepnlDocAnexosResolProrrogaOrden) {
        this.visiblepnlDocAnexosResolProrrogaOrden = visiblepnlDocAnexosResolProrrogaOrden;
    }

    public boolean isVisiblePnlDocProrrogaOficio() {
        return visiblePnlDocProrrogaOficio;
    }

    public void setVisiblePnlDocProrrogaOficio(boolean visiblePnlDocProrrogaOficio) {
        this.visiblePnlDocProrrogaOficio = visiblePnlDocProrrogaOficio;
    }

    public boolean isVisiblepnlDocAnexosResolProrrogaOficio() {
        return visiblepnlDocAnexosResolProrrogaOficio;
    }

    public void setVisiblepnlDocAnexosResolProrrogaOficio(boolean visiblepnlDocAnexosResolProrrogaOficio) {
        this.visiblepnlDocAnexosResolProrrogaOficio = visiblepnlDocAnexosResolProrrogaOficio;
    }

    public final boolean isVisiblePruebasPericiales() {
        return visiblePruebasPericiales;
    }

    public final void setVisiblePruebasPericiales(boolean visiblePruebasPericiales) {
        this.visiblePruebasPericiales = visiblePruebasPericiales;
    }
}
