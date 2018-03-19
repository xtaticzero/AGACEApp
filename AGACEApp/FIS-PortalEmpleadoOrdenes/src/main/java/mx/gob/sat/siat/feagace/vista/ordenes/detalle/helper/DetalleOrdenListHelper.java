/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleOrdenListHelper implements Serializable {

    private static final long serialVersionUID = -4642661194259064271L;

    private List<FecetPromocion> listaPromociones;
    private List<FecetAlegato> listaPruebasAlegatos;
    private List<FecetProrrogaOrden> listaProrroga;
    private List<FecetDocProrrogaOrden> listaDocsProrrogaOrden;
    private List<FecetPromocion> listaPromocionesCargadas;
    private List<FecetAlegato> listaPruebasAlegatosCargadas;
    private List<FecetOficio> listaOficiosFirmados;

    public void setListaDocsProrrogaOrden(List<FecetDocProrrogaOrden> listaDocsProrrogaOrden) {
        this.listaDocsProrrogaOrden = listaDocsProrrogaOrden;
    }

    public List<FecetDocProrrogaOrden> getListaDocsProrrogaOrden() {
        return listaDocsProrrogaOrden;
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

    public void setListaPromociones(List<FecetPromocion> listaPromociones) {
        this.listaPromociones = listaPromociones;
    }

    public List<FecetPromocion> getListaPromociones() {
        return listaPromociones;
    }

}
