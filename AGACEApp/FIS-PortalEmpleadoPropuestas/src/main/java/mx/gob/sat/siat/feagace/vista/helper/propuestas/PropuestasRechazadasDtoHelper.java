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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestasRechazadasDtoHelper implements Serializable {

    private static final long serialVersionUID = 368751349673313131L;

    private String rfc;
    private String idRegistro;
    private String idPropuesta;
    private String idRechazoPropuesta;
    private String divMostrar;

    private boolean botonEnviarAuditor;
    private boolean tipoPropuesta;

    private FecetContribuyente contribuyenteAnt = new FecetContribuyente();
    private FecetContribuyente contribuyente;
    private FecetPropuesta propuesta = new FecetPropuesta();
    private FecetDocExpediente documentoSeleccionado;
    private FecetDocExpediente documentoSeleccionadoOrden;
    private FecetDocExpediente documentoSeleccionadoRechazo;
    private FecetAsociado representanteLegal;
    private FecetAsociado agenteAduanal;

    private List<FececMetodo> listaMetodo;
    private List<FececSubprograma> listaSubprograma;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececRevision> listaTipoRevision;
    private List<FecetImpuesto> listaImpuestos;
    private List<FecetRechazoPropuesta> listaRechazos;
    private List<FecetDocExpediente> listaDocumentosTabla;
    private List<FecetDocExpediente> listaDocumentosOrden;
    private List<FecetDocExpediente> listaDocumentosRechazo;
    private List<FececTipoImpuesto> listaTipoImpuesto;

    public void setDocumentoSeleccionadoOrden(FecetDocExpediente documentoSeleccionadoOrden) {
        this.documentoSeleccionadoOrden = documentoSeleccionadoOrden;
    }

    public FecetDocExpediente getDocumentoSeleccionadoOrden() {
        return documentoSeleccionadoOrden;
    }

    public void setDocumentoSeleccionadoRechazo(FecetDocExpediente documentoSeleccionadoRechazo) {
        this.documentoSeleccionadoRechazo = documentoSeleccionadoRechazo;
    }

    public FecetDocExpediente getDocumentoSeleccionadoRechazo() {
        return documentoSeleccionadoRechazo;
    }

    public void setBotonEnviarAuditor(boolean botonEnviarAuditor) {
        this.botonEnviarAuditor = botonEnviarAuditor;
    }

    public boolean isBotonEnviarAuditor() {
        return botonEnviarAuditor;
    }

    public void setListaRechazos(List<FecetRechazoPropuesta> listaRechazos) {
        this.listaRechazos = listaRechazos;
    }

    public List<FecetRechazoPropuesta> getListaRechazos() {
        return listaRechazos;
    }

    public void setIdRechazoPropuesta(String idRechazoPropuesta) {
        this.idRechazoPropuesta = idRechazoPropuesta;
    }

    public String getIdRechazoPropuesta() {
        return idRechazoPropuesta;
    }

    public void setRepresentanteLegal(FecetAsociado representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public FecetAsociado getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setAgenteAduanal(FecetAsociado agenteAduanal) {
        this.agenteAduanal = agenteAduanal;
    }

    public FecetAsociado getAgenteAduanal() {
        return agenteAduanal;
    }

    public void setDivMostrar(String divMostrar) {
        this.divMostrar = divMostrar;
    }

    public String getDivMostrar() {
        return divMostrar;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setListaDocumentosOrden(List<FecetDocExpediente> listaDocumentosOrden) {
        this.listaDocumentosOrden = listaDocumentosOrden;
    }

    public List<FecetDocExpediente> getListaDocumentosOrden() {
        return listaDocumentosOrden;
    }

    public void setListaDocumentosRechazo(List<FecetDocExpediente> listaDocumentosRechazo) {
        this.listaDocumentosRechazo = listaDocumentosRechazo;
    }

    public List<FecetDocExpediente> getListaDocumentosRechazo() {
        return listaDocumentosRechazo;
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

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setListaMetodo(List<FececMetodo> listaMetodo) {
        this.listaMetodo = listaMetodo;
    }

    public List<FececMetodo> getListaMetodo() {
        return listaMetodo;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }

    public void setContribuyenteAnt(FecetContribuyente contribuyenteAnt) {
        this.contribuyenteAnt = contribuyenteAnt;
    }

    public FecetContribuyente getContribuyenteAnt() {
        return contribuyenteAnt;
    }

    public boolean isTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(boolean tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

}
