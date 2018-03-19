/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.ordenes.compulsas;

import java.util.List;
import javax.faces.bean.ManagedProperty;

import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficioAnexos;
import mx.gob.sat.siat.feagace.negocio.common.ConsultaMediosContactoService;
import mx.gob.sat.siat.feagace.negocio.common.ContribuyenteService;
import mx.gob.sat.siat.feagace.negocio.ordenes.SeguimientoOrdenesService;
import mx.gob.sat.siat.feagace.negocio.propuestas.consulta.ConsultarPapelesTrabajoService;
import mx.gob.sat.siat.feagace.vista.common.AbstractManagedBean;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class CompulsasAbstractMB extends AbstractManagedBean {

    private static final long serialVersionUID = -6443526701853337232L;

    private AgaceOrden ordenSeleccionada;
    private FecetContribuyente contribuyente;
    private FecetContribuyente representanteLegalContrib;
    private ColaboradorVO representanteLegalVO;

    private List<FecetOficio> listaOfCompulsaTercero;
    private List<FecetOficioAnexos> listaAnexosCompulsaTercero;
    private List<FecetOficio> listaOfAvisoContribuyente;
    private List<FecetOficioAnexos> listaAnexosAvisoContribuyente;
    private String autoridadCompulsada;
    
    @ManagedProperty(value = "#{seguimientoOrdenesService}")
    private transient SeguimientoOrdenesService seguimientoOrdenesService;

    @ManagedProperty(value = "#{consultaMediosContactoService}")
    private transient ConsultaMediosContactoService consultaMediosContactoService;

    @ManagedProperty(value = "#{contribuyenteService}")
    private transient ContribuyenteService contribuyenteService;

    @ManagedProperty(value = "#{consultarPapelesTrabajoService}")
    private transient ConsultarPapelesTrabajoService consultarPapelesTrabajoService ;
    
    @ManagedProperty(value = "#{archivoTempService}")
    private transient ArchivoTempService archivoTempService;
    
    
    public void setSeguimientoOrdenesService(final SeguimientoOrdenesService seguimientoOrdenesService) {
        this.seguimientoOrdenesService = seguimientoOrdenesService;
    }

    public SeguimientoOrdenesService getSeguimientoOrdenesService() {
        return seguimientoOrdenesService;
    }

    public void setContribuyenteService(final ContribuyenteService contribuyenteService) {
        this.contribuyenteService = contribuyenteService;
    }

    public ContribuyenteService getContribuyenteService() {
        return contribuyenteService;
    }

    @Override
    public void setConsultaMediosContactoService(final ConsultaMediosContactoService consultaMediosContactoService) {
        this.consultaMediosContactoService = consultaMediosContactoService;
    }

    @Override
    public ConsultaMediosContactoService getConsultaMediosContactoService() {
        return consultaMediosContactoService;
    }

    public void setListaOfAvisoContribuyente(final List<FecetOficio> listaOfAvisoContribuyente) {
        this.listaOfAvisoContribuyente = listaOfAvisoContribuyente;
    }

    public List<FecetOficio> getListaOfAvisoContribuyente() {
        return listaOfAvisoContribuyente;
    }

    public void setListaAnexosAvisoContribuyente(final List<FecetOficioAnexos> listaAnexosAvisoContribuyente) {
        this.listaAnexosAvisoContribuyente = listaAnexosAvisoContribuyente;
    }

    public List<FecetOficioAnexos> getListaAnexosAvisoContribuyente() {
        return listaAnexosAvisoContribuyente;
    }

    public void setListaOfCompulsaTercero(final List<FecetOficio> listaOfCompulsaTercero) {
        this.listaOfCompulsaTercero = listaOfCompulsaTercero;
    }

    public List<FecetOficio> getListaOfCompulsaTercero() {
        return listaOfCompulsaTercero;
    }

    public void setListaAnexosCompulsaTercero(final List<FecetOficioAnexos> listaAnexosCompulsaTercero) {
        this.listaAnexosCompulsaTercero = listaAnexosCompulsaTercero;
    }

    public List<FecetOficioAnexos> getListaAnexosCompulsaTercero() {
        return listaAnexosCompulsaTercero;
    }

    

    public void setOrdenSeleccionada(final AgaceOrden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }

    public AgaceOrden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }

    public void setContribuyente(final FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    

    public void setRepresentanteLegalVO(final ColaboradorVO representanteLegalVO) {
        this.representanteLegalVO = representanteLegalVO;
    }

    public ColaboradorVO getRepresentanteLegalVO() {
        return representanteLegalVO;
    }

    

    public void setRepresentanteLegalContrib(final FecetContribuyente representanteLegalContrib) {
        this.representanteLegalContrib = representanteLegalContrib;
    }

    public FecetContribuyente getRepresentanteLegalContrib() {
        return representanteLegalContrib;
    }

    public ConsultarPapelesTrabajoService getConsultarPapelesTrabajoService() {
        return consultarPapelesTrabajoService;
    }

    public void setConsultarPapelesTrabajoService(ConsultarPapelesTrabajoService consultarPapelesTrabajoService) {
        this.consultarPapelesTrabajoService = consultarPapelesTrabajoService;
    }

    public ArchivoTempService getArchivoTempService() {
        return archivoTempService;
    }

    public void setArchivoTempService(ArchivoTempService archivoTempService) {
        this.archivoTempService = archivoTempService;
    }

    public String getAutoridadCompulsada() {
        return autoridadCompulsada;
    }

    public void setAutoridadCompulsada(String autoridadCompulsada) {
        this.autoridadCompulsada = autoridadCompulsada;
    }
    
    

}
