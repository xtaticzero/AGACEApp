package mx.gob.sat.siat.feagace.vista.model.insumos;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class CrearInsumoComplexAbstract extends CrearInsumoBasicAbstract {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private List<FececPrioridad> listaFececPrioridad;
    private List<FececSubprograma> listaSubprograma;
    private List<FececSector> listaSector;
    private List<FececUnidadAdministrativa> unidadesAdministrativas;
    private List<FececTipoInsumo> listTipoInsumo;
    private List<FecetInsumo> listaPropuesta;
    private List<FecetInsumo> insumosSeleccionados;
    private List<FecetInsumo> listaInsumosRegistrados;
    private List<FecetInsumo> listaInsumosNoRegistrados;
    private List<FecetInsumo> listaInsumosRegistradosDetalle;
    private List<FecetDocExpInsumo> listaDocumentosExpediente;
    private List<FecetDocExpInsumo> listaDocumentosExpedienteDetalle;
    private List<FecetDocExpInsumo> listaDocumentosBorrar;
    private List<InsumoExpedienteDTO> insumosExpedienteDTO;
    private List<InsumoExpedienteDTO> insumosExpedientesSeleccionados;

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaSector(List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaPropuesta(List<FecetInsumo> listaPropuesta) {
        this.listaPropuesta = listaPropuesta;
    }

    public List<FecetInsumo> getListaPropuesta() {
        return listaPropuesta;
    }

    public void setListaDocumentosExpediente(List<FecetDocExpInsumo> listaDocumentosExpediente) {
        this.listaDocumentosExpediente = listaDocumentosExpediente;
    }

    public List<FecetDocExpInsumo> getListaDocumentosExpediente() {
        return listaDocumentosExpediente;
    }

    public void setInsumosSeleccionados(List<FecetInsumo> insumosSeleccionados) {
        this.insumosSeleccionados = insumosSeleccionados;
    }

    public List<FecetInsumo> getInsumosSeleccionados() {
        return insumosSeleccionados;
    }

    public void setListaInsumosRegistrados(List<FecetInsumo> listaInsumosRegistrados) {
        this.listaInsumosRegistrados = listaInsumosRegistrados;
    }

    public List<FecetInsumo> getListaInsumosRegistrados() {
        return listaInsumosRegistrados;
    }

    public void setListaInsumosNoRegistrados(List<FecetInsumo> listaInsumosNoRegistrados) {
        this.listaInsumosNoRegistrados = listaInsumosNoRegistrados;
    }

    public List<FecetInsumo> getListaInsumosNoRegistrados() {
        return listaInsumosNoRegistrados;
    }

    public List<FececPrioridad> getListaFececPrioridad() {
        return listaFececPrioridad;
    }

    public void setListaFececPrioridad(List<FececPrioridad> listaFececPrioridad) {
        this.listaFececPrioridad = listaFececPrioridad;
    }

    public List<FecetDocExpInsumo> getListaDocumentosExpedienteDetalle() {
        return listaDocumentosExpedienteDetalle;
    }

    public void setListaDocumentosExpedienteDetalle(
            List<FecetDocExpInsumo> listaDocumentosExpedienteDetalle) {
        this.listaDocumentosExpedienteDetalle = listaDocumentosExpedienteDetalle;
    }

    public List<FecetInsumo> getListaInsumosRegistradosDetalle() {
        return listaInsumosRegistradosDetalle;
    }

    public void setListaInsumosRegistradosDetalle(List<FecetInsumo> listaInsumosRegistradosDetalle) {
        this.listaInsumosRegistradosDetalle = listaInsumosRegistradosDetalle;
    }

    public List<FececUnidadAdministrativa> getUnidadesAdministrativas() {
        return unidadesAdministrativas;
    }

    public void setUnidadesAdministrativas(List<FececUnidadAdministrativa> unidadesAdministrativas) {
        this.unidadesAdministrativas = unidadesAdministrativas;
    }

    public List<InsumoExpedienteDTO> getInsumosExpedienteDTO() {
        return insumosExpedienteDTO;
    }

    public void setInsumosExpedienteDTO(List<InsumoExpedienteDTO> insumosExpedienteDTO) {
        this.insumosExpedienteDTO = insumosExpedienteDTO;
    }

    public List<InsumoExpedienteDTO> getInsumosExpedientesSeleccionados() {
        return insumosExpedientesSeleccionados;
    }

    public void setInsumosExpedientesSeleccionados(
            List<InsumoExpedienteDTO> insumosExpedientesSeleccionados) {
        this.insumosExpedientesSeleccionados = insumosExpedientesSeleccionados;
    }

    public List<FecetDocExpInsumo> getListaDocumentosBorrar() {
        return listaDocumentosBorrar;
    }

    public void setListaDocumentosBorrar(List<FecetDocExpInsumo> listaDocumentosBorrar) {
        this.listaDocumentosBorrar = listaDocumentosBorrar;
    }

    public List<FececTipoInsumo> getListTipoInsumo() {
        return listTipoInsumo;
    }

    public void setListTipoInsumo(List<FececTipoInsumo> listTipoInsumo) {
        this.listTipoInsumo = listTipoInsumo;
    }
    
    
}
