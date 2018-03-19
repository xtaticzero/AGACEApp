package mx.gob.sat.siat.feagace.vista.propuestas.origen.helper;

import java.io.Serializable;
import java.util.List;

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
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;

public class OrigenCentralListaHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6620260546137724168L;
    private List<FecetImpuesto> listaImpuestos;
    private List<FececSubprograma> listaSubprograma;
    private List<FececMetodo> listaMetodoRevision;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececSector> listaSector;
    private List<FececRevision> listaTipoRevision;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private List<FecetDocExpediente> listaDocumento;
    private List<FecetDocExpediente> listaDocumentosTabla;
    private List<FecetImpuesto> toRemoveImpuesto = null;
    private List<FececPrioridad> listPrioridad;
    private List<FececConcepto> listConcepto;
    private List<UnidadAdministrativaEnum> listaUnidadAdminRegional;
    private List<TipoFechasComiteEnum> listaFechasComiteEnum;
    private List<AraceDTO> listaUnidadAdministrativa;
    private List<PropuestaOrigenCentralRegDTO> centralRegDTOs;
    private List<String> listaMetodo;

    public List<FecetImpuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<FecetImpuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<FececSubprograma> getListaSubprograma() {
        return listaSubprograma;
    }

    public void setListaSubprograma(List<FececSubprograma> listaSubprograma) {
        this.listaSubprograma = listaSubprograma;
    }

    public List<FececMetodo> getListaMetodoRevision() {
        return listaMetodoRevision;
    }

    public void setListaMetodoRevision(List<FececMetodo> listaMetodoRevision) {
        this.listaMetodoRevision = listaMetodoRevision;
    }

    public List<FececTipoPropuesta> getListaTipoPropuesta() {
        return listaTipoPropuesta;
    }

    public void setListaTipoPropuesta(List<FececTipoPropuesta> listaTipoPropuesta) {
        this.listaTipoPropuesta = listaTipoPropuesta;
    }

    public List<FececCausaProgramacion> getListaCausaProgramacion() {
        return listaCausaProgramacion;
    }

    public void setListaCausaProgramacion(List<FececCausaProgramacion> listaCausaProgramacion) {
        this.listaCausaProgramacion = listaCausaProgramacion;
    }

    public List<FececSector> getListaSector() {
        return listaSector;
    }

    public void setListaSector(List<FececSector> listaSector) {
        this.listaSector = listaSector;
    }

    public List<FececRevision> getListaTipoRevision() {
        return listaTipoRevision;
    }

    public void setListaTipoRevision(List<FececRevision> listaTipoRevision) {
        this.listaTipoRevision = listaTipoRevision;
    }

    public List<FececTipoImpuesto> getListaTipoImpuesto() {
        return listaTipoImpuesto;
    }

    public void setListaTipoImpuesto(List<FececTipoImpuesto> listaTipoImpuesto) {
        this.listaTipoImpuesto = listaTipoImpuesto;
    }

    public List<FecetDocExpediente> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<FecetDocExpediente> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<FecetDocExpediente> getListaDocumentosTabla() {
        return listaDocumentosTabla;
    }

    public void setListaDocumentosTabla(List<FecetDocExpediente> listaDocumentosTabla) {
        this.listaDocumentosTabla = listaDocumentosTabla;
    }

    public List<FecetImpuesto> getToRemoveImpuesto() {
        return toRemoveImpuesto;
    }

    public void setToRemoveImpuesto(List<FecetImpuesto> toRemoveImpuesto) {
        this.toRemoveImpuesto = toRemoveImpuesto;
    }

    public List<FececPrioridad> getListPrioridad() {
        return listPrioridad;
    }

    public void setListPrioridad(List<FececPrioridad> listPrioridad) {
        this.listPrioridad = listPrioridad;
    }

    public List<FececConcepto> getListConcepto() {
        return listConcepto;
    }

    public void setListConcepto(List<FececConcepto> listConcepto) {
        this.listConcepto = listConcepto;
    }

    public List<UnidadAdministrativaEnum> getListaUnidadAdminRegional() {
        return listaUnidadAdminRegional;
    }

    public void setListaUnidadAdminRegional(List<UnidadAdministrativaEnum> listaUnidadAdminRegional) {
        this.listaUnidadAdminRegional = listaUnidadAdminRegional;
    }

    public List<TipoFechasComiteEnum> getListaFechasComiteEnum() {
        return listaFechasComiteEnum;
    }

    public void setListaFechasComiteEnum(List<TipoFechasComiteEnum> listaFechasComiteEnum) {
        this.listaFechasComiteEnum = listaFechasComiteEnum;
    }

    public List<PropuestaOrigenCentralRegDTO> getCentralRegDTOs() {
        return centralRegDTOs;
    }

    public void setCentralRegDTOs(List<PropuestaOrigenCentralRegDTO> centralRegDTOs) {
        this.centralRegDTOs = centralRegDTOs;
    }

    public List<String> getListaMetodo() {
        return listaMetodo;
    }

    public void setListaMetodo(List<String> listaMetodo) {
        this.listaMetodo = listaMetodo;
    }

    public List<AraceDTO> getListaUnidadAdministrativa() {
        return listaUnidadAdministrativa;
    }

    public void setListaUnidadAdministrativa(List<AraceDTO> listaUnidadAdministrativa) {
        this.listaUnidadAdministrativa = listaUnidadAdministrativa;
    }

}
