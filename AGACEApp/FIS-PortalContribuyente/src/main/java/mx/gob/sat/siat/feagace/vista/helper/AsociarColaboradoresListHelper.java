/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class AsociarColaboradoresListHelper implements Serializable {

    private static final long serialVersionUID = -9083978273725781761L;

    private List<FecetPromocion> listaPromociones;
    private List<FecetAlegato> listaPruebasAlegatos;
    private List<FecetProrrogaOrden> listaProrroga;
    private List<FecetDocProrrogaOrden> listaDocsProrrogaOrden;
    private List<FecetPromocion> listaPromocionesCargadas;
    private List<FecetAlegato> listaPruebasAlegatosCargadas;
    private List<FecetOficio> listaOficiosFirmados;
    private List<FecetOficio> listaOficiosDependientes;
    private List<FecetOficioAnexos> listaAnexosOficio;
    private List<AgaceOrden> listaOrdenes;
    private List<FecetCompulsas> listaCompulsas;
    private List<FecetOficio> listaOficiosAdmin;
    private List<FecetPruebasPericiales> listaPruebasPericiales;
    private List<FecetDocPruebasPericiales> listaDocsPruebasPericiales;
    private List<FecetDocAsociado> documentosRL;
    private List<FecetDocAsociado> documentosAL;
    private List<FecetDocAsociado> documentosALOriginal;
    private List<FecetDocAsociado> documentosRLOriginal;
    private List<FecetDocAsociado> documentosALRLOriginal;
    private List<FecetDocAsociado> documentosALRL;

    public List<FecetDocAsociado> getDocumentosAL() {
        return documentosAL;
    }

    public void setDocumentosAL(List<FecetDocAsociado> documentosAL) {
        this.documentosAL = documentosAL;
    }

    public List<FecetDocPruebasPericiales> getListaDocsPruebasPericiales() {
        return listaDocsPruebasPericiales;
    }

    public void setListaDocsPruebasPericiales(List<FecetDocPruebasPericiales> listaDocsPruebasPericiales) {
        this.listaDocsPruebasPericiales = listaDocsPruebasPericiales;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericiales() {
        return listaPruebasPericiales;
    }

    public void setListaPruebasPericiales(List<FecetPruebasPericiales> listaPruebasPericiales) {
        this.listaPruebasPericiales = listaPruebasPericiales;
    }

    public List<FecetOficio> getListaOficiosAdmin() {
        return listaOficiosAdmin;
    }

    public void setListaOficiosAdmin(List<FecetOficio> listaOficiosAdmin) {
        this.listaOficiosAdmin = listaOficiosAdmin;
    }

    public void setListaPromocionesCargadas(List<FecetPromocion> listaPromocionesCargadas) {
        this.listaPromocionesCargadas = listaPromocionesCargadas;
    }

    public List<FecetPromocion> getListaPromocionesCargadas() {
        return listaPromocionesCargadas;
    }

    public void setListaPruebasAlegatosCargadas(List<FecetAlegato> listaPruebasAlegatosCargadas) {
        this.listaPruebasAlegatosCargadas = listaPruebasAlegatosCargadas;
    }

    public List<FecetAlegato> getListaPruebasAlegatosCargadas() {
        return listaPruebasAlegatosCargadas;
    }

    public void setListaOficiosFirmados(List<FecetOficio> listaOficiosFirmados) {
        this.listaOficiosFirmados = listaOficiosFirmados;
    }

    public List<FecetOficio> getListaOficiosFirmados() {
        return listaOficiosFirmados;
    }

    public void setListaOrdenes(List<AgaceOrden> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<AgaceOrden> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaPromociones(List<FecetPromocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<FecetPromocion> getListaPromociones() {
        return listaPromociones;
    }

    public void setListaPruebasAlegatos(List<FecetAlegato> listaPruebasAlegatos) {
        this.listaPruebasAlegatos = listaPruebasAlegatos;
    }

    public List<FecetAlegato> getListaPruebasAlegatos() {
        return listaPruebasAlegatos;
    }

    public void setListaProrroga(List<FecetProrrogaOrden> listaProrroga) {
        this.listaProrroga = listaProrroga;
    }

    public List<FecetProrrogaOrden> getListaProrroga() {
        return listaProrroga;
    }

    public void setListaCompulsas(List<FecetCompulsas> listaCompulsas) {
        this.listaCompulsas = listaCompulsas;
    }

    public List<FecetCompulsas> getListaCompulsas() {
        return listaCompulsas;
    }

    public void setListaOficiosDependientes(List<FecetOficio> listaOficiosDependientes) {
        this.listaOficiosDependientes = listaOficiosDependientes;
    }

    public List<FecetOficio> getListaOficiosDependientes() {
        return listaOficiosDependientes;
    }

    public void setListaAnexosOficio(List<FecetOficioAnexos> listaAnexosOficio) {
        this.listaAnexosOficio = listaAnexosOficio;
    }

    public List<FecetOficioAnexos> getListaAnexosOficio() {
        return listaAnexosOficio;
    }

    public void setListaDocsProrrogaOrden(List<FecetDocProrrogaOrden> listaDocsProrrogaOrden) {
        this.listaDocsProrrogaOrden = listaDocsProrrogaOrden;
    }

    public List<FecetDocProrrogaOrden> getListaDocsProrrogaOrden() {
        return listaDocsProrrogaOrden;
    }

    public List<FecetDocAsociado> getDocumentosRL() {
        return documentosRL;
    }

    public void setDocumentosRL(List<FecetDocAsociado> documentosRL) {
        this.documentosRL = documentosRL;
    }

    public List<FecetDocAsociado> getDocumentosALRL() {
        return documentosALRL;
    }

    public void setDocumentosALRL(List<FecetDocAsociado> documentosALRL) {
        this.documentosALRL = documentosALRL;
    }

    public List<FecetDocAsociado> getDocumentosALOriginal() {
        return documentosALOriginal;
    }

    public void setDocumentosALOriginal(List<FecetDocAsociado> documentosALOriginal) {
        this.documentosALOriginal = documentosALOriginal;
    }

    public List<FecetDocAsociado> getDocumentosRLOriginal() {
        return documentosRLOriginal;
    }

    public void setDocumentosRLOriginal(List<FecetDocAsociado> documentosRLOriginal) {
        this.documentosRLOriginal = documentosRLOriginal;
    }

    public List<FecetDocAsociado> getDocumentosALRLOriginal() {
        return documentosALRLOriginal;
    }

    public void setDocumentosALRLOriginal(List<FecetDocAsociado> documentosALRLOriginal) {
        this.documentosALRLOriginal = documentosALRLOriginal;
    }

}
