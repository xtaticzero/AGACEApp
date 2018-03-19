/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosCompulsasDTOHelper extends CargaDocumentosCompulsasDTOAbstractHelper implements Serializable {

    private static final long serialVersionUID = 460639060972942708L;

    private FecetPromocionOficio promocionHistoricoCompulsaSeleccionada;
    private FecetPromocionOficio promocionCompulsaSeleccionada;
    private FecetAlegatoOficio pruebaCompulsaSeleccionada;
    private FecetCompulsas compulsaSeleccionado;
    private AcuseRevisionElectronica acuse;
    private PerfilContribuyenteVO perfil;

    private List<FecetPromocionOficio> listaPromocionesCompulsaCargadas;
    private List<FecetAlegatoOficio> listaPruebasAlegatosCompulsaCargadas;
    private List<FecetPromocionOficio> listaHistoricoPromocionesCompulsas;
    private List<FecetAlegatoOficio> listaHistoricoPruebasAlegatosCompulsa;
    private List<FecetProrrogaOficio> listaProrrogaCompulsa;
    private List<FecetDocProrrogaOficio> listaDocProrrogaCompulsa;

    public void setCompulsaSeleccionado(FecetCompulsas compulsaSeleccionado) {
        this.compulsaSeleccionado = compulsaSeleccionado;
    }

    public FecetCompulsas getCompulsaSeleccionado() {
        return compulsaSeleccionado;
    }

    public void setPerfil(PerfilContribuyenteVO perfil) {
        this.perfil = perfil;
    }

    public PerfilContribuyenteVO getPerfil() {
        return perfil;
    }

    public void setPromocionCompulsaSeleccionada(FecetPromocionOficio promocionCompulsaSeleccionada) {
        this.promocionCompulsaSeleccionada = promocionCompulsaSeleccionada;
    }

    public FecetPromocionOficio getPromocionCompulsaSeleccionada() {
        return promocionCompulsaSeleccionada;
    }

    public void setPruebaCompulsaSeleccionada(FecetAlegatoOficio pruebaCompulsaSeleccionada) {
        this.pruebaCompulsaSeleccionada = pruebaCompulsaSeleccionada;
    }

    public FecetAlegatoOficio getPruebaCompulsaSeleccionada() {
        return pruebaCompulsaSeleccionada;
    }

    public void setListaPromocionesCompulsaCargadas(List<FecetPromocionOficio> listaPromocionesCompulsaCargadas) {
        this.listaPromocionesCompulsaCargadas = listaPromocionesCompulsaCargadas;
    }

    public List<FecetPromocionOficio> getListaPromocionesCompulsaCargadas() {
        return listaPromocionesCompulsaCargadas;
    }

    public void setListaPruebasAlegatosCompulsaCargadas(List<FecetAlegatoOficio> listaPruebasAlegatosCompulsaCargadas) {
        this.listaPruebasAlegatosCompulsaCargadas = listaPruebasAlegatosCompulsaCargadas;
    }

    public List<FecetAlegatoOficio> getListaPruebasAlegatosCompulsaCargadas() {
        return listaPruebasAlegatosCompulsaCargadas;
    }

    public void setListaHistoricoPromocionesCompulsas(List<FecetPromocionOficio> listaHistoricoPromocionesCompulsas) {
        this.listaHistoricoPromocionesCompulsas = listaHistoricoPromocionesCompulsas;
    }

    public List<FecetPromocionOficio> getListaHistoricoPromocionesCompulsas() {
        return listaHistoricoPromocionesCompulsas;
    }

    public void setListaProrrogaCompulsa(List<FecetProrrogaOficio> listaProrrogaCompulsa) {
        this.listaProrrogaCompulsa = listaProrrogaCompulsa;
    }

    public List<FecetProrrogaOficio> getListaProrrogaCompulsa() {
        return listaProrrogaCompulsa;
    }

    public void setPromocionHistoricoCompulsaSeleccionada(FecetPromocionOficio promocionHistoricoCompulsaSeleccionada) {
        this.promocionHistoricoCompulsaSeleccionada = promocionHistoricoCompulsaSeleccionada;
    }

    public FecetPromocionOficio getPromocionHistoricoCompulsaSeleccionada() {
        return promocionHistoricoCompulsaSeleccionada;
    }

    public void setListaHistoricoPruebasAlegatosCompulsa(List<FecetAlegatoOficio> listaHistoricoPruebasAlegatosCompulsa) {
        this.listaHistoricoPruebasAlegatosCompulsa = listaHistoricoPruebasAlegatosCompulsa;
    }

    public List<FecetAlegatoOficio> getListaHistoricoPruebasAlegatosCompulsa() {
        return listaHistoricoPruebasAlegatosCompulsa;
    }

    public void setListaDocProrrogaCompulsa(List<FecetDocProrrogaOficio> listaDocProrrogaCompulsa) {
        this.listaDocProrrogaCompulsa = listaDocProrrogaCompulsa;
    }

    public List<FecetDocProrrogaOficio> getListaDocProrrogaCompulsa() {
        return listaDocProrrogaCompulsa;
    }

    public void setAcuse(AcuseRevisionElectronica acuse) {
        this.acuse = acuse;
    }

    public AcuseRevisionElectronica getAcuse() {
        return acuse;
    }

}
