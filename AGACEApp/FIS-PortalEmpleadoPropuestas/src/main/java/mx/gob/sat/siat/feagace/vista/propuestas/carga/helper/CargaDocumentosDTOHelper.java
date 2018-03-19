/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.carga.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosDTOHelper implements Serializable {

    private static final long serialVersionUID = -9206717070460001495L;

    private AgaceOrden ordenSeleccionado;
    private PerfilContribuyenteVO perfil;
    private FecetPromocion promocionSeleccionada;
    private FecetAlegato pruebaSeleccionada;
    private AcuseRevisionElectronica acuse;
    private FecetOficio oficioSeleccionado;
    private FecetPromocionOficio promocionOficioSeleccionada;
    private FecetAlegatoOficio pruebaOficioSeleccionada;
    private FecetAlegatoOficio pruebaHistoricoOficioSeleccionada;
    private FecetPromocionOficio promocionHistoricoOficioSeleccionada;
    private FecetProrrogaOficio prorrogaOficioSeleccionada;
    private FecetDocProrrogaOficio docProrrogaOficioSeleccionado;

    private List<FecetPromocion> listaPromocionesCargadas;
    private List<FecetAlegato> listaPruebasAlegatosCargadas;
    private List<String> listaCadenas;
    private List<FecetPromocionOficio> listaPromocionesOficioCargadas;
    private List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas;
    private List<FecetPromocionOficio> listaHistoricoPromocionesOficio;
    private List<FecetAlegatoOficio> listaHistoricoPruebasAlegatos;
    private List<FecetProrrogaOficio> listaProrrogaOficio;
    private List<FecetDocProrrogaOficio> listaDocProrrogaOficio;

    public void setProrrogaOficioSeleccionada(FecetProrrogaOficio prorrogaOficioSeleccionada) {
        this.prorrogaOficioSeleccionada = prorrogaOficioSeleccionada;
    }

    public FecetProrrogaOficio getProrrogaOficioSeleccionada() {
        return prorrogaOficioSeleccionada;
    }

    public void setListaDocProrrogaOficio(List<FecetDocProrrogaOficio> listaDocProrrogaOficio) {
        this.listaDocProrrogaOficio = listaDocProrrogaOficio;
    }

    public List<FecetDocProrrogaOficio> getListaDocProrrogaOficio() {
        return listaDocProrrogaOficio;
    }

    public void setDocProrrogaOficioSeleccionado(FecetDocProrrogaOficio docProrrogaOficioSeleccionado) {
        this.docProrrogaOficioSeleccionado = docProrrogaOficioSeleccionado;
    }

    public FecetDocProrrogaOficio getDocProrrogaOficioSeleccionado() {
        return docProrrogaOficioSeleccionado;
    }

    public void setPruebaHistoricoOficioSeleccionada(FecetAlegatoOficio pruebaHistoricoOficioSeleccionada) {
        this.pruebaHistoricoOficioSeleccionada = pruebaHistoricoOficioSeleccionada;
    }

    public FecetAlegatoOficio getPruebaHistoricoOficioSeleccionada() {
        return pruebaHistoricoOficioSeleccionada;
    }

    public void setListaProrrogaOficio(List<FecetProrrogaOficio> listaProrrogaOficio) {
        this.listaProrrogaOficio = listaProrrogaOficio;
    }

    public List<FecetProrrogaOficio> getListaProrrogaOficio() {
        return listaProrrogaOficio;
    }

    public void setListaHistoricoPruebasAlegatos(List<FecetAlegatoOficio> listaHistoricoPruebasAlegatos) {
        this.listaHistoricoPruebasAlegatos = listaHistoricoPruebasAlegatos;
    }

    public List<FecetAlegatoOficio> getListaHistoricoPruebasAlegatos() {
        return listaHistoricoPruebasAlegatos;
    }

    public void setPromocionSeleccionada(FecetPromocion promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocion getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    public void setPromocionOficioSeleccionada(FecetPromocionOficio promocionOficioSeleccionada) {
        this.promocionOficioSeleccionada = promocionOficioSeleccionada;
    }

    public FecetPromocionOficio getPromocionOficioSeleccionada() {
        return promocionOficioSeleccionada;
    }

    public void setListaHistoricoPromocionesOficio(List<FecetPromocionOficio> listaHistoricoPromocionesOficio) {
        this.listaHistoricoPromocionesOficio = listaHistoricoPromocionesOficio;
    }

    public List<FecetPromocionOficio> getListaHistoricoPromocionesOficio() {
        return listaHistoricoPromocionesOficio;
    }

    public void setPromocionHistoricoOficioSeleccionada(FecetPromocionOficio promocionHistoricoOficioSeleccionada) {
        this.promocionHistoricoOficioSeleccionada = promocionHistoricoOficioSeleccionada;
    }

    public FecetPromocionOficio getPromocionHistoricoOficioSeleccionada() {
        return promocionHistoricoOficioSeleccionada;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setListaPromocionesOficioCargadas(List<FecetPromocionOficio> listaPromocionesOficioCargadas) {
        this.listaPromocionesOficioCargadas = listaPromocionesOficioCargadas;
    }

    public List<FecetPromocionOficio> getListaPromocionesOficioCargadas() {
        return listaPromocionesOficioCargadas;
    }

    public void setListaPruebasAlegatosOficioCargadas(List<FecetAlegatoOficio> listaPruebasAlegatosOficioCargadas) {
        this.listaPruebasAlegatosOficioCargadas = listaPruebasAlegatosOficioCargadas;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatosOficioCargadas() {
        return listaPruebasAlegatosOficioCargadas;
    }

    public void setPruebaOficioSeleccionada(FecetAlegatoOficio pruebaOficioSeleccionada) {
        this.pruebaOficioSeleccionada = pruebaOficioSeleccionada;
    }

    public FecetAlegatoOficio getPruebaOficioSeleccionada() {
        return pruebaOficioSeleccionada;
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

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setPruebaSeleccionada(FecetAlegato pruebaSeleccionada) {
        this.pruebaSeleccionada = pruebaSeleccionada;
    }

    public FecetAlegato getPruebaSeleccionada() {
        return pruebaSeleccionada;
    }

    public void setListaCadenas(List<String> listaCadenas) {
        this.listaCadenas = listaCadenas;
    }

    public List<String> getListaCadenas() {
        return listaCadenas;
    }

    public void setAcuse(AcuseRevisionElectronica acuse) {
        this.acuse = acuse;
    }

    public AcuseRevisionElectronica getAcuse() {
        return acuse;
    }

}
