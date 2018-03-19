/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultarPropuestasAsignadasHelper extends ConsultarPropuestasAsignadasPartHelper implements Serializable {

    private static final long serialVersionUID = 3330306426555173980L;

    private List<FecetPropuesta> listaPropuestasPorAnalizar;
    private List<FecetPropuesta> listaPropuestasRechazadas;
    private List<FececMotivo> motivosOperacion;
    private List<FececMotivo> motivosRetroalimentacion;
    private List<FececMotivo> motivosCancelacion;
    private List<AraceDTO> araces;
    private List<FecetDocExpediente> listaExpedientes;
    private List<FecetRechazoPropuesta> listaRechazo;
    private List<FecetRechazoPropuesta> listaDocumentosTablaRechazo;
    private List<FecetRechazoPropuesta> listaHistoricoRechazo;
    private List<FecetRechazoPropuesta> documentosRechazo;
    private List<FecetDocOrden> listaInfoComplementaria;
    private List<FecetTransferencia> listaTransferir;
    private List<FecetTransferencia> listaDocTransferir;
    private List<FecetTransferencia> documentosTransferir;
    private List<FecetRetroalimentacion> listaRetroalimentar;
    private List<FecetRetroalimentacion> listaDocRetroalimentar;
    private List<FecebAccionPropuesta> listaHistoricoAccion;
    private FecebAccionPropuesta historicoAccion;
    private List<FecetRetroalimentacion> listaHistoricoRetroalimentar;
    private List<FecetRetroalimentacion> documentosRetroalimentar;
    private List<FecetImpuestoDescripcion> listaImpuestosDescripcion;
    private List<FecetDocOrden> documentoOrden;
    private List<FecetDocExpediente> expedientePropuesta;
    private List<FecetDocOrden> docOrdenActualizado;
    private List<FecetPropuesta> listaEnviarPropuestas;
    private List<FececFirmante> datosFirmanteAsignadoAuditor;
    private List<DocumentoOrdenModel> listaOrdenes;
    private List<DocumentoOrdenModel> listaOficios;

    public ConsultarPropuestasAsignadasHelper() {
        this.datosFirmanteAsignadoAuditor = new ArrayList<FececFirmante>();
        this.listaPropuestasPorAnalizar = new ArrayList<FecetPropuesta>();
        this.listaPropuestasRechazadas = new ArrayList<FecetPropuesta>();
        this.listaImpuestosDescripcion = new ArrayList<FecetImpuestoDescripcion>();
        this.listaExpedientes = new ArrayList<FecetDocExpediente>();
        this.listaRechazo = new ArrayList<FecetRechazoPropuesta>();
        this.listaDocumentosTablaRechazo = new ArrayList<FecetRechazoPropuesta>();
        this.listaHistoricoRechazo = new ArrayList<FecetRechazoPropuesta>();
        this.listaDocRetroalimentar = new ArrayList<FecetRetroalimentacion>();
        this.listaHistoricoRetroalimentar = new ArrayList<FecetRetroalimentacion>();
        this.documentosRechazo = new ArrayList<FecetRechazoPropuesta>();
        this.listaDocTransferir = new ArrayList<FecetTransferencia>();
        this.listaTransferir = new ArrayList<FecetTransferencia>();
        this.listaRetroalimentar = new ArrayList<FecetRetroalimentacion>();
        this.motivosOperacion = new ArrayList<FececMotivo>();
        this.motivosRetroalimentacion = new ArrayList<FececMotivo>();
        this.motivosCancelacion = new ArrayList<FececMotivo>();
        this.araces = new ArrayList<AraceDTO>();
        this.documentosRetroalimentar = new ArrayList<FecetRetroalimentacion>();
        this.documentosTransferir = new ArrayList<FecetTransferencia>();
        this.documentoOrden = new ArrayList<FecetDocOrden>();
        this.expedientePropuesta = new ArrayList<FecetDocExpediente>();
        this.listaInfoComplementaria = new ArrayList<FecetDocOrden>();
        this.docOrdenActualizado = new ArrayList<FecetDocOrden>();
        this.listaOrdenes = new ArrayList<DocumentoOrdenModel>();
        this.listaOficios = new ArrayList<DocumentoOrdenModel>();
    }

    public void setListaPropuestasPorAnalizar(List<FecetPropuesta> listaPropuestasPorAnalizar) {
        this.listaPropuestasPorAnalizar = listaPropuestasPorAnalizar;
    }

    public List<FecetPropuesta> getListaPropuestasPorAnalizar() {
        return listaPropuestasPorAnalizar;
    }

    public void setlistaEnviarPropuestas(List<FecetPropuesta> listaEnviarPropuestas) {
        this.listaEnviarPropuestas = listaEnviarPropuestas;
    }

    public List<FecetPropuesta> getlistaEnviarPropuestas() {
        return listaEnviarPropuestas;
    }

    public void setListaExpedientes(List<FecetDocExpediente> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public List<FecetDocExpediente> getListaExpedientes() {
        return listaExpedientes;
    }

    public void setListaPropuestasRechazadas(List<FecetPropuesta> listaPropuestasRechazadas) {
        this.listaPropuestasRechazadas = listaPropuestasRechazadas;
    }

    public List<FecetPropuesta> getListaPropuestasRechazadas() {
        return listaPropuestasRechazadas;
    }

    public void setListaRechazo(List<FecetRechazoPropuesta> listaRechazo) {
        this.listaRechazo = listaRechazo;
    }

    public List<FecetRechazoPropuesta> getListaRechazo() {
        return listaRechazo;
    }

    public void setListaTransferir(List<FecetTransferencia> listaTransferir) {
        this.listaTransferir = listaTransferir;
    }

    public List<FecetTransferencia> getListaTransferir() {
        return listaTransferir;
    }

    public void setListaRetroalimentar(List<FecetRetroalimentacion> listaRetroalimentar) {
        this.listaRetroalimentar = listaRetroalimentar;
    }

    public List<FecetRetroalimentacion> getListaRetroalimentar() {
        return listaRetroalimentar;
    }

    public void setDatosFirmanteAsignadoAuditor(List<FececFirmante> datosFirmanteAsignadoAuditor) {
        this.datosFirmanteAsignadoAuditor = datosFirmanteAsignadoAuditor;
    }

    public List<FececFirmante> getDatosFirmanteAsignadoAuditor() {
        return datosFirmanteAsignadoAuditor;
    }

    public void setListaImpuestosDescripcion(List<FecetImpuestoDescripcion> listaImpuestosDescripcion) {
        this.listaImpuestosDescripcion = listaImpuestosDescripcion;
    }

    public List<FecetImpuestoDescripcion> getListaImpuestosDescripcion() {
        return listaImpuestosDescripcion;
    }

    public void setMotivosOperacion(List<FececMotivo> motivosOperacion) {
        this.motivosOperacion = motivosOperacion;
    }

    public List<FececMotivo> getMotivosOperacion() {
        return motivosOperacion;
    }

    public void setMotivosRetroalimentacion(List<FececMotivo> motivosRetroalimentacion) {
        this.motivosRetroalimentacion = motivosRetroalimentacion;
    }

    public List<FececMotivo> getMotivosRetroalimentacion() {
        return motivosRetroalimentacion;
    }

    public void setListaDocumentosTablaRechazo(List<FecetRechazoPropuesta> listaDocumentosTablaRechazo) {
        this.listaDocumentosTablaRechazo = listaDocumentosTablaRechazo;
    }

    public List<FecetRechazoPropuesta> getListaDocumentosTablaRechazo() {
        return listaDocumentosTablaRechazo;
    }

    public void setListaDocTransferir(List<FecetTransferencia> listaDocTransferir) {
        this.listaDocTransferir = listaDocTransferir;
    }

    public List<FecetTransferencia> getListaDocTransferir() {
        return listaDocTransferir;
    }

    public void setListaDocRetroalimentar(List<FecetRetroalimentacion> listaDocRetroalimentar) {
        this.listaDocRetroalimentar = listaDocRetroalimentar;
    }

    public List<FecetRetroalimentacion> getListaDocRetroalimentar() {
        return listaDocRetroalimentar;
    }

    public void setListaHistoricoRechazo(List<FecetRechazoPropuesta> listaHistoricoRechazo) {
        this.listaHistoricoRechazo = listaHistoricoRechazo;
    }

    public List<FecetRechazoPropuesta> getListaHistoricoRechazo() {
        return listaHistoricoRechazo;
    }

    public void setListaHistoricoRetroalimentar(List<FecetRetroalimentacion> listaHistoricoRetroalimentar) {
        this.listaHistoricoRetroalimentar = listaHistoricoRetroalimentar;
    }

    public List<FecetRetroalimentacion> getListaHistoricoRetroalimentar() {
        return listaHistoricoRetroalimentar;
    }

    public void setDocumentosRechazo(List<FecetRechazoPropuesta> documentosRechazo) {
        this.documentosRechazo = documentosRechazo;
    }

    public List<FecetRechazoPropuesta> getDocumentosRechazo() {
        return documentosRechazo;
    }

    public void setDocumentosRetroalimentar(List<FecetRetroalimentacion> documentosRetroalimentar) {
        this.documentosRetroalimentar = documentosRetroalimentar;
    }

    public List<FecetRetroalimentacion> getDocumentosRetroalimentar() {
        return documentosRetroalimentar;
    }

    public void setDocumentosTransferir(List<FecetTransferencia> documentosTransferir) {
        this.documentosTransferir = documentosTransferir;
    }

    public List<FecetTransferencia> getDocumentosTransferir() {
        return documentosTransferir;
    }

    public void setDocumentoOrden(List<FecetDocOrden> documentoOrden) {
        this.documentoOrden = documentoOrden;
    }

    public List<FecetDocOrden> getDocumentoOrden() {
        return documentoOrden;
    }

    public void setExpedientePropuesta(List<FecetDocExpediente> expedientePropuesta) {
        this.expedientePropuesta = expedientePropuesta;
    }

    public List<FecetDocExpediente> getExpedientePropuesta() {
        return expedientePropuesta;
    }

    public void setListaInfoComplementaria(List<FecetDocOrden> listaInfoComplementaria) {
        this.listaInfoComplementaria = listaInfoComplementaria;
    }

    public List<FecetDocOrden> getListaInfoComplementaria() {
        return listaInfoComplementaria;
    }

    public void setDocOrdenActualizado(List<FecetDocOrden> docOrdenActualizado) {
        this.docOrdenActualizado = docOrdenActualizado;
    }

    public List<FecetDocOrden> getDocOrdenActualizado() {
        return docOrdenActualizado;
    }

    public void setListaOrdenes(List<DocumentoOrdenModel> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<DocumentoOrdenModel> getListaOrdenes() {
        return listaOrdenes;
    }

    public List<FececMotivo> getMotivosCancelacion() {
        return motivosCancelacion;
    }

    public void setMotivosCancelacion(List<FececMotivo> motivosCancelacion) {
        this.motivosCancelacion = motivosCancelacion;
    }

    public List<FecebAccionPropuesta> getListaHistoricoAccion() {
        return listaHistoricoAccion;
    }

    public void setListaHistoricoAccion(List<FecebAccionPropuesta> listaHistoricoAccion) {
        this.listaHistoricoAccion = listaHistoricoAccion;
    }

    public FecebAccionPropuesta getHistoricoAccion() {
        return historicoAccion;
    }

    public void setHistoricoAccion(FecebAccionPropuesta historicoAccion) {
        this.historicoAccion = historicoAccion;
    }

    public List<DocumentoOrdenModel> getListaOficios() {
        return listaOficios;
    }

    public void setListaOficios(List<DocumentoOrdenModel> listaOficios) {
        this.listaOficios = listaOficios;
    }

    public List<AraceDTO> getAraces() {
        return araces;
    }

    public void setAraces(List<AraceDTO> araces) {
        this.araces = araces;
    }
}
