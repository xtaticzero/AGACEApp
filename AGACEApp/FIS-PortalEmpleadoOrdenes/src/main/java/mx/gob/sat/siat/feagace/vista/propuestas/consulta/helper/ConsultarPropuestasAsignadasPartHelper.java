/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ConsultarPropuestasAsignadasPartHelper implements Serializable {

    private static final long serialVersionUID = 3330306426555173980L;

    private List<DocumentoOrdenModel> listaPapelesTrabajo;
    private List<ContadorPropuestasAsignadas> resumenPropuestasAsignadas;
    private List<PapelesTrabajo> listaPapeles;
    private List<FecetPropCancelada> listaCancelar;
    private List<FecetPropCancelada> listaDocCancelacion;
    private List<TipoFechasComiteEnum> listaFechasComite;
    private List<FecetRetroalimentacion> listaRetroalimentacion;
    private List<FecetRetroalimentacion> listaSolicitudRetroalimentacion;
    private FecetRetroalimentacion solicitudRetroalimentacion;
    private DocumentoOrdenModel papelesTrabajo;
    private List<FecetRetroalimentacion> listaDocumentos;
    private List<FecetImpuesto> listaImpuestos;
    private BigDecimal presuntiva;
    private List<EmpleadoDTO> listaJefeDepartamento;
    private List<EmpleadoDTO> listaEnlace;
    private BigDecimal idEmpleadoEnlace;
    private BigDecimal idEmpleadoJefeDepartamento;
    private List<FecetDocExpediente> listaDocumentosExpediente;
    private String rutaArchivo;
    private String nombreArchivo;
    private List<FecetDocOrden> listaHistorialOrden;
    private List<FecetOficio> listaHistorialOficio;
    private List<FecetTipoOficio> oficios;
    private List<FecetOficio> docOficios;
    private List<FecetOficio> oficiosActualizados;

    public DocumentoOrdenModel getPapelesTrabajo() {
        return papelesTrabajo;
    }

    public void setPapelesTrabajo(DocumentoOrdenModel papelesTrabajo) {
        this.papelesTrabajo = papelesTrabajo;
    }

    public List<FecetDocOrden> getListaHistorialOrden() {
        return listaHistorialOrden;
    }

    public void setListaHistorialOrden(List<FecetDocOrden> listaHistorialOrden) {
        this.listaHistorialOrden = listaHistorialOrden;
    }

    public List<FecetOficio> getListaHistorialOficio() {
        return listaHistorialOficio;
    }

    public void setListaHistorialOficio(List<FecetOficio> listaHistorialOficio) {
        this.listaHistorialOficio = listaHistorialOficio;
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

    public List<FecetDocExpediente> getListaDocumentosExpediente() {
        return listaDocumentosExpediente;
    }

    public void setListaDocumentosExpediente(List<FecetDocExpediente> listaDocumentosExpediente) {
        this.listaDocumentosExpediente = listaDocumentosExpediente;
    }

    public BigDecimal getIdEmpleadoEnlace() {
        return idEmpleadoEnlace;
    }

    public void setIdEmpleadoEnlace(BigDecimal idEmpleadoEnlace) {
        this.idEmpleadoEnlace = idEmpleadoEnlace;
    }

    public BigDecimal getIdEmpleadoJefeDepartamento() {
        return idEmpleadoJefeDepartamento;
    }

    public void setIdEmpleadoJefeDepartamento(BigDecimal idEmpleadoJefeDepartamento) {
        this.idEmpleadoJefeDepartamento = idEmpleadoJefeDepartamento;
    }

    public ConsultarPropuestasAsignadasPartHelper() {
        this.resumenPropuestasAsignadas = new ArrayList<ContadorPropuestasAsignadas>();
        this.listaPapeles = new ArrayList<PapelesTrabajo>();
        this.listaCancelar = new ArrayList<FecetPropCancelada>();
        this.listaDocCancelacion = new ArrayList<FecetPropCancelada>();
        this.listaFechasComite = new ArrayList<TipoFechasComiteEnum>();
        this.oficios = new ArrayList<FecetTipoOficio>();
        this.docOficios = new ArrayList<FecetOficio>();
        this.listaPapelesTrabajo = new ArrayList<DocumentoOrdenModel>();
        this.oficiosActualizados = new ArrayList<FecetOficio>();
    }

    public List<ContadorPropuestasAsignadas> getResumenPropuestasAsignadas() {
        return resumenPropuestasAsignadas;
    }

    public void setResumenPropuestasAsignadas(List<ContadorPropuestasAsignadas> resumenPropuestasAsignadas) {
        this.resumenPropuestasAsignadas = resumenPropuestasAsignadas;
    }

    public List<PapelesTrabajo> getListaPapeles() {
        return listaPapeles;
    }

    public void setListaPapeles(List<PapelesTrabajo> listaPapeles) {
        this.listaPapeles = listaPapeles;
    }

    public List<FecetPropCancelada> getListaDocCancelacion() {
        return listaDocCancelacion;
    }

    public void setListaDocCancelacion(List<FecetPropCancelada> listaDocCancelacion) {
        this.listaDocCancelacion = listaDocCancelacion;
    }

    public List<FecetPropCancelada> getListaCancelar() {
        return listaCancelar;
    }

    public void setListaCancelar(List<FecetPropCancelada> listaCancelar) {
        this.listaCancelar = listaCancelar;
    }

    public List<TipoFechasComiteEnum> getListaFechasComite() {
        return listaFechasComite;
    }

    public void setListaFechasComite(List<TipoFechasComiteEnum> listaFechasComite) {
        this.listaFechasComite = listaFechasComite;
    }

    public List<FecetRetroalimentacion> getListaRetroalimentacion() {
        return listaRetroalimentacion;
    }

    public void setListaRetroalimentacion(List<FecetRetroalimentacion> listaRetroalimentacion) {
        this.listaRetroalimentacion = listaRetroalimentacion;
    }

    public List<FecetRetroalimentacion> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<FecetRetroalimentacion> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public FecetRetroalimentacion getSolicitudRetroalimentacion() {
        return solicitudRetroalimentacion;
    }

    public void setSolicitudRetroalimentacion(FecetRetroalimentacion solicitudRetroalimentacion) {
        this.solicitudRetroalimentacion = solicitudRetroalimentacion;
    }

    public List<FecetRetroalimentacion> getListaSolicitudRetroalimentacion() {
        return listaSolicitudRetroalimentacion;
    }

    public void setListaSolicitudRetroalimentacion(List<FecetRetroalimentacion> listaSolicitudRetroalimentacion) {
        this.listaSolicitudRetroalimentacion = listaSolicitudRetroalimentacion;
    }

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public List<FecetTipoOficio> getOficios() {
        return oficios;
    }

    public void setOficios(List<FecetTipoOficio> oficios) {
        this.oficios = oficios;
    }

    public List<FecetOficio> getDocOficios() {
        return docOficios;
    }

    public void setDocOficios(List<FecetOficio> docOficios) {
        this.docOficios = docOficios;
    }

    public List<DocumentoOrdenModel> getListaPapelesTrabajo() {
        return listaPapelesTrabajo;
    }

    public void setListaPapelesTrabajo(List<DocumentoOrdenModel> listaPapelesTrabajo) {
        this.listaPapelesTrabajo = listaPapelesTrabajo;
    }

    public List<FecetOficio> getOficiosActualizados() {
        return oficiosActualizados;
    }

    public void setOficiosActualizados(List<FecetOficio> oficiosActualizados) {
        this.oficiosActualizados = oficiosActualizados;
    }

    public List<EmpleadoDTO> getListaJefeDepartamento() {
        return listaJefeDepartamento;
    }

    public void setListaJefeDepartamento(List<EmpleadoDTO> listaJefeDepartamento) {
        this.listaJefeDepartamento = listaJefeDepartamento;
    }

    public List<EmpleadoDTO> getListaEnlace() {
        return listaEnlace;
    }

    public void setListaEnlace(List<EmpleadoDTO> listaEnlace) {
        this.listaEnlace = listaEnlace;
    }
}
