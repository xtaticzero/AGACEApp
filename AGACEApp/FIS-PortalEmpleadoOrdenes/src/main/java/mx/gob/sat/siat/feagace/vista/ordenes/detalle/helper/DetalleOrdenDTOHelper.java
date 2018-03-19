/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class DetalleOrdenDTOHelper  extends DetalleOrdenAttributeDTOHelper implements Serializable {

    private static final long serialVersionUID = -7076228056183224387L;   

    private FecetContribuyente contribuyente;
    private FecetPromocion promocionSeleccionada;
    private FecetProrrogaOrden prorrogaOrdenSeleccionada;
    private FecetDocProrrogaOrden docProrrogaOrdenSeleccionado;
    private FecetOficio oficioSeleccionado;
    private FecetAlegato pruebaAlegatoSeleccionada;
    private AgaceOrden ordenes;
    private AgaceOrden ordenSeleccionado;
    private AgaceOrden ordenSeleccionDescarga;
    private ColaboradorVO apoderadoLegalVO;
    private ColaboradorVO apoderadoLegalRLVO;
    private ColaboradorVO agenteAduanalVO;
    private ColaboradorVO representanteLegalVO;

   

    public void setProrrogaOrdenSeleccionada(FecetProrrogaOrden prorrogaOrdenSeleccionada) {
        this.prorrogaOrdenSeleccionada = prorrogaOrdenSeleccionada;
    }

    public FecetProrrogaOrden getProrrogaOrdenSeleccionada() {
        return prorrogaOrdenSeleccionada;
    }

    public void setPruebaAlegatoSeleccionada(FecetAlegato pruebaAlegatoSeleccionada) {
        this.pruebaAlegatoSeleccionada = pruebaAlegatoSeleccionada;
    }

    public FecetAlegato getPruebaAlegatoSeleccionada() {
        return pruebaAlegatoSeleccionada;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }    

    public void setPromocionSeleccionada(FecetPromocion promocionSeleccionada) {
        this.promocionSeleccionada = promocionSeleccionada;
    }

    public FecetPromocion getPromocionSeleccionada() {
        return promocionSeleccionada;
    }

    

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setOrdenSeleccionado(AgaceOrden ordenSeleccionado) {
        this.ordenSeleccionado = ordenSeleccionado;
    }

    public AgaceOrden getOrdenSeleccionado() {
        return ordenSeleccionado;
    }

    public void setOrdenSeleccionDescarga(AgaceOrden ordenSeleccionDescarga) {
        this.ordenSeleccionDescarga = ordenSeleccionDescarga;
    }

    public AgaceOrden getOrdenSeleccionDescarga() {
        return ordenSeleccionDescarga;
    }

    

    public void setApoderadoLegalVO(ColaboradorVO apoderadoLegalVO) {
        this.apoderadoLegalVO = apoderadoLegalVO;
    }

    public ColaboradorVO getApoderadoLegalVO() {
        return apoderadoLegalVO;
    }

    

    public void setApoderadoLegalRLVO(ColaboradorVO apoderadoLegalRLVO) {
        this.apoderadoLegalRLVO = apoderadoLegalRLVO;
    }

    public ColaboradorVO getApoderadoLegalRLVO() {
        return apoderadoLegalRLVO;
    }

    public void setAgenteAduanalVO(ColaboradorVO agenteAduanalVO) {
        this.agenteAduanalVO = agenteAduanalVO;
    }

    public ColaboradorVO getAgenteAduanalVO() {
        return agenteAduanalVO;
    }

    public void setRepresentanteLegalVO(ColaboradorVO representanteLegalVO) {
        this.representanteLegalVO = representanteLegalVO;
    }

    public ColaboradorVO getRepresentanteLegalVO() {
        return representanteLegalVO;
    }

    

    public void setDocProrrogaOrdenSeleccionado(FecetDocProrrogaOrden docProrrogaOrdenSeleccionado) {
        this.docProrrogaOrdenSeleccionado = docProrrogaOrdenSeleccionado;
    }

    public FecetDocProrrogaOrden getDocProrrogaOrdenSeleccionado() {
        return docProrrogaOrdenSeleccionado;
    }

    public void setOrdenes(AgaceOrden ordenes) {
        this.ordenes = ordenes;
    }

    public AgaceOrden getOrdenes() {
        return ordenes;
    }

   

}
