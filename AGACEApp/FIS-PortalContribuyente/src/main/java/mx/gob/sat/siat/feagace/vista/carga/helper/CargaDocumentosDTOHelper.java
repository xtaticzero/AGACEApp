/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.carga.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseRevisionElectronica;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAlegatoOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class CargaDocumentosDTOHelper extends CargaDocumentosDTOAbstractHelper implements Serializable {

    private static final long serialVersionUID = 460639060972942708L;

    private AgaceOrden ordenSeleccionado;
    private PerfilContribuyenteVO perfil;
    private FecetPromocion promocionSeleccionada;
    private FecetAlegato pruebaSeleccionada;
    private AcuseRevisionElectronica acuse;
    private FecetOficio oficioSeleccionado;
    private FecetPromocionOficio promocionOficioSeleccionada;
    private FecetAlegatoOficio pruebaOficioSeleccionada;
    private FecetPromocionOficio promocionHistoricoOficioSeleccionada;
    private FecetAlegatoOficio pruebaHistoricoOficioSeleccionada;

    public void setPruebaHistoricoOficioSeleccionada(FecetAlegatoOficio pruebaHistoricoOficioSeleccionada) {
        this.pruebaHistoricoOficioSeleccionada = pruebaHistoricoOficioSeleccionada;
    }

    public FecetAlegatoOficio getPruebaHistoricoOficioSeleccionada() {
        return pruebaHistoricoOficioSeleccionada;
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

    public void setPruebaOficioSeleccionada(FecetAlegatoOficio pruebaOficioSeleccionada) {
        this.pruebaOficioSeleccionada = pruebaOficioSeleccionada;
    }

    public FecetAlegatoOficio getPruebaOficioSeleccionada() {
        return pruebaOficioSeleccionada;
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

    public void setAcuse(AcuseRevisionElectronica acuse) {
        this.acuse = acuse;
    }

    public AcuseRevisionElectronica getAcuse() {
        return acuse;
    }

}
