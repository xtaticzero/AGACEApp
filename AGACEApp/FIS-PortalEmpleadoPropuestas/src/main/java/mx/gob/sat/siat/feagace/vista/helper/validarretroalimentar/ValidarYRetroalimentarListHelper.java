/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.validarretroalimentar;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarYRetroalimentarListHelper implements Serializable {

    private static final long serialVersionUID = 8283252888344676180L;

    private List<FecetImpuesto> listaImpuestos;
    private List<AraceDTO> listaUnidades;
    private List<FececSubprograma> listaSubprograma;
    private List<FececMetodo> listaMetodoRevision;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececSector> listaSector;
    private List<FececRevision> listaTipoRevision;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private List<FecetPropuesta> listaCambioMetodo;
    private List<FecetPropuesta> listaPropuestasXValidar;
    private List<FecetPropuesta> listaPropuestasPendientes;
    private List<FecetPropuesta> listaPropuestasXRetroalimentar;
    private List<FecetRechazoPropuesta> listaHistoricoRechazo;
    private List<FecetRetroalimentacion> listaHistoricoRetroalimentar;
    private List<FecetRechazoPropuesta> lstDocHistoricoRechazo;
    private List<FecetRetroalimentacion> lstDocHistoricoRetroalimentar;
    private List<DocRetroalimentacionDTO> lstDocRetroalimentacion;
    private List<FecetPropuesta> listaPropuestasTransferidas;
    private List<FecetRechazoPropuesta> listaDocumentoRechazo;
    private List<FecetDocExpediente> listaDocumentoExpediente;
    private List<FecetImpuesto> listaImpuesto;
    private List<FececConcepto> listaConcepto;
    private List<FecetPropPendiente> listaDocumentoPendiente;
    private List<FececPrioridad> listaPrioridad;
    private List<FecetPropuesta> listaPropuestaXValidarSeleccionada;
    private List<FecetPropuesta> listaPropuestaPendienteSeleccionada;

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececMetodo> getListaMetodoRevision() {
        return listaMetodoRevision;
    }

    public void setListaMetodoRevision(List<FececMetodo> listaMetodoRevision) {
        this.listaMetodoRevision = listaMetodoRevision;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaSector(List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececTipoImpuesto> getListaTipoImpuesto() {
        return listaTipoImpuesto;
    }

    public void setListaTipoImpuesto(List<FececTipoImpuesto> listaTipoImpuesto) {
        this.listaTipoImpuesto = listaTipoImpuesto;
    }

    public List<FecetPropuesta> getListaCambioMetodo() {
        return listaCambioMetodo;
    }

    public void setListaCambioMetodo(List<FecetPropuesta> listaCambioMetodo) {
        this.listaCambioMetodo = listaCambioMetodo;
    }

    public List<FecetPropuesta> getListaPropuestasXValidar() {
        return listaPropuestasXValidar;
    }

    public void setListaPropuestasXValidar(List<FecetPropuesta> listaPropuestasXValidar) {
        this.listaPropuestasXValidar = listaPropuestasXValidar;
    }

    public List<FecetPropuesta> getListaPropuestasPendientes() {
        return listaPropuestasPendientes;
    }

    public void setListaPropuestasPendientes(List<FecetPropuesta> listaPropuestasPendientes) {
        this.listaPropuestasPendientes = listaPropuestasPendientes;
    }

    public List<FecetPropuesta> getListaPropuestasXRetroalimentar() {
        return listaPropuestasXRetroalimentar;
    }

    public void setListaPropuestasXRetroalimentar(List<FecetPropuesta> listaPropuestasXRetroalimentar) {
        this.listaPropuestasXRetroalimentar = listaPropuestasXRetroalimentar;
    }

    public List<FecetRechazoPropuesta> getListaHistoricoRechazo() {
        return listaHistoricoRechazo;
    }

    public void setListaHistoricoRechazo(List<FecetRechazoPropuesta> listaHistoricoRechazo) {
        this.listaHistoricoRechazo = listaHistoricoRechazo;
    }

    public List<FecetRetroalimentacion> getListaHistoricoRetroalimentar() {
        return listaHistoricoRetroalimentar;
    }

    public void setListaHistoricoRetroalimentar(List<FecetRetroalimentacion> listaHistoricoRetroalimentar) {
        this.listaHistoricoRetroalimentar = listaHistoricoRetroalimentar;
    }

    public List<FecetRechazoPropuesta> getLstDocHistoricoRechazo() {
        return lstDocHistoricoRechazo;
    }

    public void setLstDocHistoricoRechazo(List<FecetRechazoPropuesta> lstDocHistoricoRechazo) {
        this.lstDocHistoricoRechazo = lstDocHistoricoRechazo;
    }

    public List<FecetRetroalimentacion> getLstDocHistoricoRetroalimentar() {
        return lstDocHistoricoRetroalimentar;
    }

    public void setLstDocHistoricoRetroalimentar(List<FecetRetroalimentacion> lstDocHistoricoRetroalimentar) {
        this.lstDocHistoricoRetroalimentar = lstDocHistoricoRetroalimentar;
    }

    public List<DocRetroalimentacionDTO> getLstDocRetroalimentacion() {
        return lstDocRetroalimentacion;
    }

    public void setLstDocRetroalimentacion(List<DocRetroalimentacionDTO> lstDocRetroalimentacion) {
        this.lstDocRetroalimentacion = lstDocRetroalimentacion;
    }

    public List<FecetPropuesta> getListaPropuestasTransferidas() {
        return listaPropuestasTransferidas;
    }

    public void setListaPropuestasTransferidas(List<FecetPropuesta> listaPropuestasTransferidas) {
        this.listaPropuestasTransferidas = listaPropuestasTransferidas;
    }

    public List<FecetRechazoPropuesta> getListaDocumentoRechazo() {
        return listaDocumentoRechazo;
    }

    public void setListaDocumentoRechazo(List<FecetRechazoPropuesta> listaDocumentoRechazo) {
        this.listaDocumentoRechazo = listaDocumentoRechazo;
    }

    public List<FecetDocExpediente> getListaDocumentoExpediente() {
        return listaDocumentoExpediente;
    }

    public void setListaDocumentoExpediente(List<FecetDocExpediente> listaDocumentoExpediente) {
        this.listaDocumentoExpediente = listaDocumentoExpediente;
    }

    public List<FecetImpuesto> getListaImpuesto() {
        return listaImpuesto;
    }

    public void setListaImpuesto(List<FecetImpuesto> listaImpuesto) {
        this.listaImpuesto = listaImpuesto;
    }

    public List<FececConcepto> getListaConcepto() {
        return listaConcepto;
    }

    public void setListaConcepto(List<FececConcepto> listaConcepto) {
        this.listaConcepto = listaConcepto;
    }

    public List<FecetPropPendiente> getListaDocumentoPendiente() {
        return listaDocumentoPendiente;
    }

    public void setListaDocumentoPendiente(List<FecetPropPendiente> listaDocumentoPendiente) {
        this.listaDocumentoPendiente = listaDocumentoPendiente;
    }

    public List<AraceDTO> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List<AraceDTO> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public List<FececPrioridad> getListaPrioridad() {
        return listaPrioridad;
    }

    public void setListaPrioridad(List<FececPrioridad> listaPrioridad) {
        this.listaPrioridad = listaPrioridad;
    }

    public List<FecetPropuesta> getListaPropuestaXValidarSeleccionada() {
        return listaPropuestaXValidarSeleccionada;
    }

    public void setListaPropuestaXValidarSeleccionada(List<FecetPropuesta> listaPropuestaXValidarSeleccionada) {
        this.listaPropuestaXValidarSeleccionada = listaPropuestaXValidarSeleccionada;
    }

    public List<FecetPropuesta> getListaPropuestaPendienteSeleccionada() {
        return listaPropuestaPendienteSeleccionada;
    }

    public void setListaPropuestaPendienteSeleccionada(List<FecetPropuesta> listaPropuestaPendienteSeleccionada) {
        this.listaPropuestaPendienteSeleccionada = listaPropuestaPendienteSeleccionada;
    }

}
