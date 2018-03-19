/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.helper;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.primefaces.model.UploadedFile;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaIndividualDTOAbstract implements Serializable {

    private static final long serialVersionUID = -737247564668693085L;

    private List<AraceDTO> listaUnidades;
    private List<FececSubprograma> listaSubprograma;
    private List<FececMetodo> listaMetodoRevision;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececSector> listaSector;
    private List<FececRevision> listaTipoRevision;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private Set<String> destinatarios = null;
    private List<FecetDocExpediente> listaDocumento;
    private List<FecetDocExpInsumo> listaDocumentoInsumo;
    private List<FecetDocExpediente> listaDocumentoExp;
    private List<ImpuestoVO> listaImpuestos;
    private List<FececPrioridad> listaPrioridad;
    private List<FececConcepto> listaConcepto;

    private transient UploadedFile archivoCarga;


    public void setListaDocumentoInsumo(List<FecetDocExpInsumo> listaDocumentoInsumo) {
        this.listaDocumentoInsumo = listaDocumentoInsumo;
    }

    public List<FecetDocExpInsumo> getListaDocumentoInsumo() {
        return listaDocumentoInsumo;
    }

    public void setListaDocumentoExp(List<FecetDocExpediente> listaDocumentoExp) {
        this.listaDocumentoExp = listaDocumentoExp;
    }

    public List<FecetDocExpediente> getListaDocumentoExp() {
        return listaDocumentoExp;
    }

    public void setDestinatarios(Set<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Set<String> getDestinatarios() {
        return destinatarios;
    }

    public void setListaSubprograma(final List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaMetodoRevision(List<FececMetodo> listaMetodoRevision) {
        this.listaMetodoRevision = listaMetodoRevision;
    }

    public List<FececMetodo> getListaMetodoRevision() {
        return listaMetodoRevision;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setListaSector(final List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaTipoImpuesto(List<FececTipoImpuesto> listaTipoImpuesto) {
        this.listaTipoImpuesto = listaTipoImpuesto;
    }

    public List<FececTipoImpuesto> getListaTipoImpuesto() {
        return listaTipoImpuesto;
    }

    public void setListaImpuestos(List<ImpuestoVO> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<ImpuestoVO> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaDocumento(List<FecetDocExpediente> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<FecetDocExpediente> getListaDocumento() {
        return listaDocumento;
    }

    public void setArchivoCarga(final UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setListaPrioridad(List<FececPrioridad> listaPrioridad) {
        this.listaPrioridad = listaPrioridad;
    }

    public List<FececPrioridad> getListaPrioridad() {
        return listaPrioridad;
    }

    public void setListaConcepto(List<FececConcepto> listaConcepto) {
        this.listaConcepto = listaConcepto;
    }

    public List<FececConcepto> getListaConcepto() {
        return listaConcepto;
    }

    public void setListaUnidades(List<AraceDTO> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    public List<AraceDTO> getListaUnidades() {
        return listaUnidades;
    }
}
