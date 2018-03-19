/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.documentacion;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteAnexoVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteDocVO;
import mx.gob.sat.siat.feagace.modelo.dto.common.SolicitudContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class DocumentacionFirmanteAbstractMB extends DocumentacionFirmanteMBAbstract {

    private static final long serialVersionUID = -6967589767765134298L;
    private List<FecetPromocion> listaPromociones;
    private List<FecetOficio> listaOficiosPorFirmar;
    private List<FecetOficio> listaOficiosFirmados;
    private List<FecetProrrogaOrden> listaProrrogasPendientes;
    private List<FecetProrrogaOrden> listaProrrogasFirmadas;
    private List<FecetAlegato> listAlegatosAnexos;
    private List<FecetOficioAnexos> listOficioAnexos;
    private List<FecetOficio> listaOficiosDependientes;
    private List<FecetDocProrrogaOrden> listaDocumentacionContribuyente;
    private List<FecetAnexosProrrogaOrden> listaAnexosProrroga;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteFirmadas;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyentePendientes;
    private List<SolicitudContribuyenteVO> listaSolicitudContribuyenteAux;
    private List<SolicitudContribuyenteDocVO> listaDocumentacionSolicitudContribuyente;
    private List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyente;
    private List<FecetPruebasPericiales> listaPruebasPericialesPendientes;
    private List<FecetPruebasPericiales> listaPruebasPericialesFirmadas;
    private List<FecetAnexoPruebasPericiales> listaAnexosPruebasPericiales;
    private List<SolicitudContribuyenteAnexoVO> listaDocumentosAnexosRechazoSolicitudContribuyente;

    public List<SolicitudContribuyenteAnexoVO> getListaDocumentosAnexosRechazoSolicitudContribuyente() {
        return listaDocumentosAnexosRechazoSolicitudContribuyente;
    }

    public void setListaDocumentosAnexosRechazoSolicitudContribuyente(
            List<SolicitudContribuyenteAnexoVO> listaDocumentosAnexosRechazoSolicitudContribuyente) {
        this.listaDocumentosAnexosRechazoSolicitudContribuyente = listaDocumentosAnexosRechazoSolicitudContribuyente;
    }

    public List<FecetAnexoPruebasPericiales> getListaAnexosPruebasPericiales() {
        return listaAnexosPruebasPericiales;
    }

    public void setListaAnexosPruebasPericiales(List<FecetAnexoPruebasPericiales> listaAnexosPruebasPericiales) {
        this.listaAnexosPruebasPericiales = listaAnexosPruebasPericiales;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericialesPendientes() {
        return listaPruebasPericialesPendientes;
    }

    public void setListaPruebasPericialesPendientes(List<FecetPruebasPericiales> listaPruebasPericialesPendientes) {
        this.listaPruebasPericialesPendientes = listaPruebasPericialesPendientes;
    }

    public List<FecetPruebasPericiales> getListaPruebasPericialesFirmadas() {
        return listaPruebasPericialesFirmadas;
    }

    public void setListaPruebasPericialesFirmadas(List<FecetPruebasPericiales> listaPruebasPericialesFirmadas) {
        this.listaPruebasPericialesFirmadas = listaPruebasPericialesFirmadas;
    }

    public List<SolicitudContribuyenteDocVO> getListaDocumentacionSolicitudContribuyente() {
        return listaDocumentacionSolicitudContribuyente;
    }

    public void setListaDocumentacionSolicitudContribuyente(
            List<SolicitudContribuyenteDocVO> listaDocumentacionSolicitudContribuyente) {
        this.listaDocumentacionSolicitudContribuyente = listaDocumentacionSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteAnexoVO> getListaAnexosSolicitudContribuyente() {
        return listaAnexosSolicitudContribuyente;
    }

    public void setListaAnexosSolicitudContribuyente(
            List<SolicitudContribuyenteAnexoVO> listaAnexosSolicitudContribuyente) {
        this.listaAnexosSolicitudContribuyente = listaAnexosSolicitudContribuyente;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyentePendientes() {
        return listaSolicitudContribuyentePendientes;
    }

    public void setListaSolicitudContribuyentePendientes(
            List<SolicitudContribuyenteVO> listaSolicitudContribuyentePendientes) {
        this.listaSolicitudContribuyentePendientes = listaSolicitudContribuyentePendientes;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteFirmadas() {
        return listaSolicitudContribuyenteFirmadas;
    }

    public void setListaSolicitudContribuyenteFirmadas(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteFirmadas) {
        this.listaSolicitudContribuyenteFirmadas = listaSolicitudContribuyenteFirmadas;
    }

    public void setListaPromociones(List<FecetPromocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<FecetPromocion> getListaPromociones() {
        return listaPromociones;
    }

    public List<FecetOficio> getListaOficiosPorFirmar() {
        return listaOficiosPorFirmar;
    }

    public void setListaOficiosPorFirmar(List<FecetOficio> listaOficiosPorFirmar) {
        this.listaOficiosPorFirmar = listaOficiosPorFirmar;
    }

    public List<FecetOficio> getListaOficiosFirmados() {
        return listaOficiosFirmados;
    }

    public void setListaOficiosFirmados(List<FecetOficio> listaOficiosFirmados) {
        this.listaOficiosFirmados = listaOficiosFirmados;
    }

    public List<FecetProrrogaOrden> getListaProrrogasPendientes() {
        return listaProrrogasPendientes;
    }

    public void setListaProrrogasPendientes(List<FecetProrrogaOrden> listaProrrogasPendientes) {
        this.listaProrrogasPendientes = listaProrrogasPendientes;
    }

    public List<FecetProrrogaOrden> getListaProrrogasFirmadas() {
        return listaProrrogasFirmadas;
    }

    public void setListaProrrogasFirmadas(List<FecetProrrogaOrden> listaProrrogasFirmadas) {
        this.listaProrrogasFirmadas = listaProrrogasFirmadas;
    }

    public List<FecetAlegato> getListAlegatosAnexos() {
        return listAlegatosAnexos;
    }

    public void setListAlegatosAnexos(List<FecetAlegato> listAlegatosAnexos) {
        this.listAlegatosAnexos = listAlegatosAnexos;
    }

    public List<FecetOficioAnexos> getListOficioAnexos() {
        return listOficioAnexos;
    }

    public void setListOficioAnexos(List<FecetOficioAnexos> listOficioAnexos) {
        this.listOficioAnexos = listOficioAnexos;
    }

    public List<FecetOficio> getListaOficiosDependientes() {
        return listaOficiosDependientes;
    }

    public void setListaOficiosDependientes(List<FecetOficio> listaOficiosDependientes) {
        this.listaOficiosDependientes = listaOficiosDependientes;
    }

    public List<FecetDocProrrogaOrden> getListaDocumentacionContribuyente() {
        return listaDocumentacionContribuyente;
    }

    public void setListaDocumentacionContribuyente(List<FecetDocProrrogaOrden> listaDocumentacionContribuyente) {
        this.listaDocumentacionContribuyente = listaDocumentacionContribuyente;
    }

    public List<FecetAnexosProrrogaOrden> getListaAnexosProrroga() {
        return listaAnexosProrroga;
    }

    public void setListaAnexosProrroga(List<FecetAnexosProrrogaOrden> listaAnexosProrroga) {
        this.listaAnexosProrroga = listaAnexosProrroga;
    }

    public List<SolicitudContribuyenteVO> getListaSolicitudContribuyenteAux() {
        return listaSolicitudContribuyenteAux;
    }

    public void setListaSolicitudContribuyenteAux(List<SolicitudContribuyenteVO> listaSolicitudContribuyenteAux) {
        this.listaSolicitudContribuyenteAux = listaSolicitudContribuyenteAux;
    }
}
