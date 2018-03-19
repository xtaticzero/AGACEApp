/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.propuestas;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropTransferidasPendValidListHelper implements Serializable{
    private static final long serialVersionUID = -6650893445149004545L;
    
    private List<FececMetodo> listaMetodo;
    private List<FececSubprograma> listaSubprograma;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececRevision> listaTipoRevision;
    private List<FecetImpuesto> listaImpuestos;
    private List<FecetDocExpediente> listaDocumentosTabla;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private List<FecetTransferencia> listaTransferencias;
    private List<FececMotivo> listaMotivos;
    private List<FecetRechazoPropuesta> listaRechazo;
    private List<FecetTransferencia> listaDocTransferencias;
    
    public void setListaMotivos(List<FececMotivo> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public List<FececMotivo> getListaMotivos() {
        return listaMotivos;
    }
    
    public void setListaRechazo(List<FecetRechazoPropuesta> listaRechazo) {
        this.listaRechazo = listaRechazo;
    }

    public List<FecetRechazoPropuesta> getListaRechazo() {
        return listaRechazo;
    }
    
    public void setListaTransferencias(List<FecetTransferencia> listaTransferencias) {
        this.listaTransferencias = listaTransferencias;
    }

    public List<FecetTransferencia> getListaTransferencias() {
        return listaTransferencias;
    }

    public void setListaDocTransferencias(List<FecetTransferencia> listaDocTransferencias) {
        this.listaDocTransferencias = listaDocTransferencias;
    }

    public List<FecetTransferencia> getListaDocTransferencias() {
        return listaDocTransferencias;
    }
    
    public void setListaMetodo(List<FececMetodo> listaMetodo) {
        this.listaMetodo = listaMetodo;
    }

    public List<FececMetodo> getListaMetodo() {
        return listaMetodo;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaDocumentosTabla(List<FecetDocExpediente> listaDocumentosTabla) {
        this.listaDocumentosTabla = listaDocumentosTabla;
    }

    public List<FecetDocExpediente> getListaDocumentosTabla() {
        return listaDocumentosTabla;
    }

    public void setListaTipoImpuesto(List<FececTipoImpuesto> listaTipoImpuesto) {
        this.listaTipoImpuesto = listaTipoImpuesto;
    }

    public List<FececTipoImpuesto> getListaTipoImpuesto() {
        return listaTipoImpuesto;
    }
}
