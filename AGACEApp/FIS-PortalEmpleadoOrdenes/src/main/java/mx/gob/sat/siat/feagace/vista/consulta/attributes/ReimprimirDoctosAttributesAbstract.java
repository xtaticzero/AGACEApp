/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.consulta.attributes;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.vista.consulta.ReimprimirDocumentosAbstractMB;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.FlujoProrrogaOrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.ProrrogaOrdenConsultaDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ReimprimirDoctosAttributesAbstract extends ReimprimirDocumentosAbstractMB{
    private static final long serialVersionUID = 1161052593788869295L;
    
    protected static final String INEXISTENCIA_REGISTROS = "No se encontraron registros con la informaci\u00f3n ingresada, favor de verificar.";
    protected static final String AGACE_ORDEN_BUSQ = "agaceOrdenBusq";
    
    private AgaceOrden agaceOrden;
    private OrdenConsultaDTO ordenConsultaSeleccionada;
    private PromocionConsultaDTO promocionConsultaSeleccionada;
    private ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada;
    private PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada;
    private OficioConsultaDTO oficioConsultaSeleccionado;
    private OficioConsultaDTO oficioConsultaDependeienteSeleccionado;
    private ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada;
    private FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada;
    private FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada;
    private FecetContribuyente contribuyente;
    private FecetDocOrden expediente;
    private List<FecetOficio> listaOficiosAdmin;
    private FecetOficio docOficioSeleccionado;

    private List<AgaceOrden> listOrden;
    private List<OrdenConsultaDTO> listOrdenConsulta;
    
    public List<AgaceOrden> getListOrden() {
        return listOrden;
    }

    public void setListOrden(List<AgaceOrden> listOrden) {
        this.listOrden = listOrden;
    }

    public List<OrdenConsultaDTO> getListOrdenConsulta() {
        return listOrdenConsulta;
    }

    public void setListOrdenConsulta(List<OrdenConsultaDTO> listOrdenConsulta) {
        this.listOrdenConsulta = listOrdenConsulta;
    }

    public OrdenConsultaDTO getOrdenConsultaSeleccionada() {
        return ordenConsultaSeleccionada;
    }

    public void setOrdenConsultaSeleccionada(OrdenConsultaDTO ordenConsultaSeleccionada) {
        this.ordenConsultaSeleccionada = ordenConsultaSeleccionada;
    }

    public AgaceOrden getAgaceOrden() {
        return agaceOrden;
    }

    public void setAgaceOrden(AgaceOrden agaceOrdenArg) {
        this.agaceOrden = agaceOrdenArg;
    }

    public PromocionConsultaDTO getPromocionConsultaSeleccionada() {
        return promocionConsultaSeleccionada;
    }

    public void setPromocionConsultaSeleccionada(PromocionConsultaDTO promocionConsultaSeleccionada) {
        this.promocionConsultaSeleccionada = promocionConsultaSeleccionada;
    }

    public OficioConsultaDTO getOficioConsultaSeleccionado() {
        return oficioConsultaSeleccionado;
    }

    public void setOficioConsultaSeleccionado(OficioConsultaDTO oficioConsultaSeleccionado) {
        this.oficioConsultaSeleccionado = oficioConsultaSeleccionado;
    }

    public OficioConsultaDTO getOficioConsultaDependeienteSeleccionado() {
        return oficioConsultaDependeienteSeleccionado;
    }

    public void setOficioConsultaDependeienteSeleccionado(OficioConsultaDTO oficioConsultaDependeienteSeleccionado) {
        this.oficioConsultaDependeienteSeleccionado = oficioConsultaDependeienteSeleccionado;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public PromocionOficioConsultaDTO getPromocionOficioConsultaSeleccionada() {
        return promocionOficioConsultaSeleccionada;
    }

    public void setPromocionOficioConsultaSeleccionada(PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada) {
        this.promocionOficioConsultaSeleccionada = promocionOficioConsultaSeleccionada;
    }

    public ProrrogaOrdenConsultaDTO getProrrogaOrdenConsultaSeleccionada() {
        return prorrogaOrdenConsultaSeleccionada;
    }

    public void setProrrogaOrdenConsultaSeleccionada(ProrrogaOrdenConsultaDTO prorrogaOrdenConsultaSeleccionada) {
        this.prorrogaOrdenConsultaSeleccionada = prorrogaOrdenConsultaSeleccionada;
    }

    public ProrrogaOficioConsultaDTO getProrrogaOficioConsultaSeleccionada() {
        return prorrogaOficioConsultaSeleccionada;
    }

    public void setProrrogaOficioConsultaSeleccionada(ProrrogaOficioConsultaDTO prorrogaOficioConsultaSeleccionada) {
        this.prorrogaOficioConsultaSeleccionada = prorrogaOficioConsultaSeleccionada;
    }

    public FlujoProrrogaOrdenConsultaDTO getFlujoProrrogaOrdenConsultaDTOSeleccionada() {
        return flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOrdenConsultaDTOSeleccionada(FlujoProrrogaOrdenConsultaDTO flujoProrrogaOrdenConsultaDTOSeleccionada) {
        this.flujoProrrogaOrdenConsultaDTOSeleccionada = flujoProrrogaOrdenConsultaDTOSeleccionada;
    }

    public FlujoProrrogaOficioConsultaDTO getFlujoProrrogaOficioConsultaDTOSeleccionada() {
        return flujoProrrogaOficioConsultaDTOSeleccionada;
    }

    public void setFlujoProrrogaOficioConsultaDTOSeleccionada(FlujoProrrogaOficioConsultaDTO flujoProrrogaOficioConsultaDTOSeleccionada) {
        this.flujoProrrogaOficioConsultaDTOSeleccionada = flujoProrrogaOficioConsultaDTOSeleccionada;
    }

    public FecetDocOrden getExpediente() {
        return expediente;
    }

    public void setExpediente(FecetDocOrden expediente) {
        this.expediente = expediente;
    }

    public List<FecetOficio> getListaOficiosAdmin() {
        return listaOficiosAdmin;
    }

    public void setListaOficiosAdmin(List<FecetOficio> listaOficiosAdmin) {
        this.listaOficiosAdmin = listaOficiosAdmin;
    }

    public FecetOficio getDocOficioSeleccionado() {
        return docOficioSeleccionado;
    }

    public void setDocOficioSeleccionado(FecetOficio docOficioSeleccionado) {
        this.docOficioSeleccionado = docOficioSeleccionado;
    }
    
}
