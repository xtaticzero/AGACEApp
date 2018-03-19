/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.common;

import mx.gob.sat.siat.base.vista.BaseManagedBean;

/**
 *
 * @author emmanuel.estrada
 */
public abstract class ValidarRetroalimentarAbstractMB extends BaseManagedBean {

    private static final long serialVersionUID = -6606896136508378577L;

    private boolean flgPanelDetalle;
    private boolean flgDetalleXValidar;
    private boolean flgDetalleTransferida;
    private boolean flgPanelListas;
    private boolean flgAddArchivoRechazo;
    private boolean flgHabilitarRechazo;
    private boolean flgHabilitarPendiente;
    private boolean flgPropuestaPendiente;
    private boolean flgContribuyenteActual;
    private boolean flgContribuyenteAnterior;
    private boolean flgPanelDetalleMetodoPropuesta;
    private boolean flgRetroalimentacion;
    private boolean flgDetalleHistoricoRechazo;
    private boolean flgDetalleHistoricoRetro;
    private boolean flgBtnPendiente;
    private boolean esProcesable;
    private boolean esTransferible;
    private boolean tipoPropuesta;
    private boolean flujoXValidar;
    private boolean visibleFechaPre;
    private boolean visibleFechaInf;
    private boolean visibleListasHistoricosOrigen;
    private boolean flgImpuestoHistorico;
    private boolean retroOrigen;
    private boolean flgComboMetodo;
    private boolean flgTipoRevision;
 
    
    
    

    public ValidarRetroalimentarAbstractMB() {
        flgPanelDetalle = false;
        flgPanelListas = true;
        flgHabilitarRechazo = true;
        flgHabilitarPendiente = true;
        flgDetalleHistoricoRechazo = false;
        flgDetalleHistoricoRetro = false;
        visibleListasHistoricosOrigen = false;
        flgBtnPendiente = false;
        esProcesable = false;
        flgImpuestoHistorico = false;
        retroOrigen = true;
    }

    public void inicializaPanels() {
        flgPanelDetalle = false;
        flgPanelListas = true;
    }

    public void visiblePanelDetalle() {
        flgPanelDetalle = true;
        flgPanelListas = false;
        flgDetalleXValidar = true;
        flgDetalleTransferida = false;
        flgPropuestaPendiente = false;
    }

    public void iniciaVisibilidadComboMetodo() {
        flgComboMetodo = true;
        flgTipoRevision = true;
    }

    public void visiblePanelDetalleTransferida() {
        flgPanelDetalle = true;
        flgPanelListas = false;
        flgDetalleXValidar = false;
        flgDetalleTransferida = true;
        flgPropuestaPendiente = false;
    }

    public void visiblePanelCambioMetodoPropuesta() {
        flgPanelDetalle = false;
        flgPanelListas = false;
        flgPanelDetalleMetodoPropuesta = true;

    }

    public void visiblePanelListas() {
        flgPanelDetalle = false;
        flgPanelListas = true;
    }

    public void visiblePanelHistorico() {
        flgDetalleHistoricoRechazo = false;
        flgDetalleHistoricoRetro = false;
        visibleListasHistoricosOrigen = false;
        flgImpuestoHistorico = false;
    }

    public boolean isFlgPanelDetalle() {
        return flgPanelDetalle;
    }

    public void setFlgPanelDetalle(boolean flgPanelDetalle) {
        this.flgPanelDetalle = flgPanelDetalle;
    }

    public boolean isFlgPanelListas() {
        return flgPanelListas;
    }

    public void setFlgPanelListas(boolean flgPanelListas) {
        this.flgPanelListas = flgPanelListas;
    }

    public boolean isFlgAddArchivoRechazo() {
        return flgAddArchivoRechazo;
    }

    public void setFlgAddArchivoRechazo(boolean flgAddArchivoRechazo) {
        this.flgAddArchivoRechazo = flgAddArchivoRechazo;
    }

    public boolean isFlgHabilitarRechazo() {
        return flgHabilitarRechazo;
    }

    public void setFlgHabilitarRechazo(boolean flgHabilitarRechazo) {
        this.flgHabilitarRechazo = flgHabilitarRechazo;
    }

    public boolean isFlgHabilitarPendiente() {
        return flgHabilitarPendiente;
    }

    public void setFlgHabilitarPendiente(boolean flgHabilitarPendiente) {
        this.flgHabilitarPendiente = flgHabilitarPendiente;
    }

    public void setFlgDetalleXValidar(boolean flgDetalleXValidar) {
        this.flgDetalleXValidar = flgDetalleXValidar;
    }

    public boolean isFlgDetalleXValidar() {
        return flgDetalleXValidar;
    }

    public void setFlgDetalleTransferida(boolean flgDetalleTransferida) {
        this.flgDetalleTransferida = flgDetalleTransferida;
    }

    public boolean isFlgDetalleTransferida() {
        return flgDetalleTransferida;
    }

    public boolean isFlgPropuestaPendiente() {
        return flgPropuestaPendiente;
    }

    public void setFlgPropuestaPendiente(boolean flgPropuestaPendiente) {
        this.flgPropuestaPendiente = flgPropuestaPendiente;
    }

    public void setFlgContribuyenteActual(boolean flgContribuyenteActual) {
        this.flgContribuyenteActual = flgContribuyenteActual;
    }

    public boolean isFlgContribuyenteActual() {
        return flgContribuyenteActual;
    }

    public void setFlgContribuyenteAnterior(boolean flgContribuyenteAnterior) {
        this.flgContribuyenteAnterior = flgContribuyenteAnterior;
    }

    public boolean isFlgContribuyenteAnterior() {
        return flgContribuyenteAnterior;
    }

    public void setFlgPanelDetalleMetodoPropuesta(boolean flgPanelDetalleMetodoPropuesta) {
        this.flgPanelDetalleMetodoPropuesta = flgPanelDetalleMetodoPropuesta;
    }

    public boolean isFlgPanelDetalleMetodoPropuesta() {
        return flgPanelDetalleMetodoPropuesta;
    }

    public boolean isFlgRetroalimentacion() {
        return flgRetroalimentacion;
    }

    public void setFlgRetroalimentacion(boolean flgRetroalimentacion) {
        this.flgRetroalimentacion = flgRetroalimentacion;
    }

    public boolean isFlgDetalleHistoricoRechazo() {
        return flgDetalleHistoricoRechazo;
    }

    public void setFlgDetalleHistoricoRechazo(boolean flgDetalleHistoricoRechazo) {
        this.flgDetalleHistoricoRechazo = flgDetalleHistoricoRechazo;
    }

    public boolean isFlgDetalleHistoricoRetro() {
        return flgDetalleHistoricoRetro;
    }

    public void setFlgDetalleHistoricoRetro(boolean flgDetalleHistoricoRetro) {
        this.flgDetalleHistoricoRetro = flgDetalleHistoricoRetro;
    }

    public boolean isFlgBtnPendiente() {
        return flgBtnPendiente;
    }

    public void setFlgBtnPendiente(boolean flgBtnPendiente) {
        this.flgBtnPendiente = flgBtnPendiente;
    }

    public boolean isEsProcesable() {
        return esProcesable;
    }

    public void setEsProcesable(boolean esProcesable) {
        this.esProcesable = esProcesable;
    }

    public void setEsTransferible(boolean esTransferible) {
        this.esTransferible = esTransferible;
    }

    public boolean isEsTransferible() {
        return esTransferible;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public boolean isFlujoXValidar() {
        return flujoXValidar;
    }

    public void setFlujoXValidar(boolean flujoXValidar) {
        this.flujoXValidar = flujoXValidar;
    }

    public boolean isVisibleFechaPre() {
        return visibleFechaPre;
    }

    public void setVisibleFechaPre(boolean visibleFechaPre) {
        this.visibleFechaPre = visibleFechaPre;
    }

    public boolean isVisibleFechaInf() {
        return visibleFechaInf;
    }

    public void setVisibleFechaInf(boolean visibleFechaInf) {
        this.visibleFechaInf = visibleFechaInf;
    }

    public boolean isVisibleListasHistoricosOrigen() {
        return visibleListasHistoricosOrigen;
    }

    public void setVisibleListasHistoricosOrigen(boolean visibleListasHistoricosOrigen) {
        this.visibleListasHistoricosOrigen = visibleListasHistoricosOrigen;
    }

    public boolean isFlgImpuestoHistorico() {
        return flgImpuestoHistorico;
    }

    public void setFlgImpuestoHistorico(boolean flgImpuestoHistorico) {
        this.flgImpuestoHistorico = flgImpuestoHistorico;
    }

    public boolean isRetroOrigen() {
        return retroOrigen;
    }

    public void setRetroOrigen(boolean retroOrigen) {
        this.retroOrigen = retroOrigen;
    }

    public boolean isFlgComboMetodo() {
        return flgComboMetodo;
    }

    public void setFlgComboMetodo(boolean flgComboMetodo) {
        this.flgComboMetodo = flgComboMetodo;
    }

    public boolean isFlgTipoRevision() {
        return flgTipoRevision;
    }

    public void setFlgTipoRevision(boolean flgTipoRevision) {
        this.flgTipoRevision = flgTipoRevision;
    }
   
    
}
