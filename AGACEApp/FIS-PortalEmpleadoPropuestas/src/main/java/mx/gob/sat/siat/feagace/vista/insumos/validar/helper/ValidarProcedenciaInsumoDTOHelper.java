/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.validar.helper;

import java.io.Serializable;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.ImpuestoVO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocretroinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarProcedenciaInsumoDTOHelper implements Serializable {
    private static final long serialVersionUID = 6977960779507932540L;

    private FecetPropuesta propuesta;
    private FecetInsumo insumoSeleccionado;
    private FecetDocExpediente documentoSeleccionado;
    private FecetDocExpediente documentoSeleccionadoPropuesta;
    private ImpuestoVO impuestoVO;
    private ImpuestoVO impuestoSeleccionado;
    private FecetDocretroinsumo docretroInsumoSeleccionado;
    private FecetDocrechazoinsumo docrechazoInsumoSeleccionado;
    private FecetContadorInsumos documentoSeleccionadoRetro;
    private FecetDocretroinsumo docretroInsumoCargadoSeleccionado;
    private FececEmpleado fececEmpleado;

    private List<FecetInsumo> listaInsumo;
    private List<String> listaEstatusInsumo;
    private List<FececUnidadAdministrativa> listaUnidades;
    private List<FececMetodo> listaMetodoRevision;
    private List<FececTipoPropuesta> listaTipoPropuesta;
    private List<FececCausaProgramacion> listaCausaProgramacion;
    private List<FececRevision> listaTipoRevision;
    private List<FececTipoImpuesto> listaTipoImpuesto;
    private List<ImpuestoVO> listaImpuestos;
    private List<FecetDocExpediente> listaDocumento;
    private List<FecetDocExpInsumo> listaDocumentoInsumo;
    private List<FecetDocretroinsumo> listaDocRetroInsumo;
    private List<FecetDocrechazoinsumo> listaDocRechazoInsumo;
    private List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador;
    private List<FecetDocretroinsumo> listaDocretroInsumoCargados;
    private List<FecetDocExpediente> listaDocumentoPropuesta;

    public void setListaDocumentoPropuesta(List<FecetDocExpediente> listaDocumento) {
        this.listaDocumentoPropuesta = listaDocumento;
    }

    public List<FecetDocExpediente> getListaDocumentoPropuesta() {
        return listaDocumentoPropuesta;
    }

    public void setDocumentoSeleccionadoPropuesta(FecetDocExpediente documentoSeleccionadoPropuesta) {
        this.documentoSeleccionadoPropuesta = documentoSeleccionadoPropuesta;
    }

    public FecetDocExpediente getDocumentoSeleccionadoPropuesta() {
        return documentoSeleccionadoPropuesta;
    }

    public void setListaDocRechazoInsumo(List<FecetDocrechazoinsumo> listaDocRechazoInsumo) {
        this.listaDocRechazoInsumo = listaDocRechazoInsumo;
    }

    public List<FecetDocrechazoinsumo> getListaDocRechazoInsumo() {
        return listaDocRechazoInsumo;
    }

    public void setDocrechazoInsumoSeleccionado(FecetDocrechazoinsumo docrechazoInsumoSeleccionado) {
        this.docrechazoInsumoSeleccionado = docrechazoInsumoSeleccionado;
    }

    public FecetDocrechazoinsumo getDocrechazoInsumoSeleccionado() {
        return docrechazoInsumoSeleccionado;
    }

  

    public List<FececUnidadAdministrativa> getListaUnidades() {
        return listaUnidades;
    }

    public void setListaUnidades(List<FececUnidadAdministrativa> listaUnidades) {
        this.listaUnidades = listaUnidades;
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

    public void setImpuestoVO(ImpuestoVO impuestoVO) {
        this.impuestoVO = impuestoVO;
    }

    public ImpuestoVO getImpuestoVO() {
        return impuestoVO;
    }

    public void setImpuestoSeleccionado(ImpuestoVO impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public ImpuestoVO getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setInsumoSeleccionado(FecetInsumo insumoSeleccionado) {
        this.insumoSeleccionado = insumoSeleccionado;
    }

    public FecetInsumo getInsumoSeleccionado() {
        return insumoSeleccionado;
    }

    public void setListaInsumo(List<FecetInsumo> listaInsumo) {
        this.listaInsumo = listaInsumo;
    }

    public List<FecetInsumo> getListaInsumo() {
        return listaInsumo;
    }

    public void setListaDocumentoInsumo(List<FecetDocExpInsumo> listaDocumentoInsumo) {
        this.listaDocumentoInsumo = listaDocumentoInsumo;
    }

    public List<FecetDocExpInsumo> getListaDocumentoInsumo() {
        return listaDocumentoInsumo;
    }

    public void setListaDocumento(List<FecetDocExpediente> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public List<FecetDocExpediente> getListaDocumento() {
        return listaDocumento;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setListaDocRetroInsumo(List<FecetDocretroinsumo> listaDocRetroInsumo) {
        this.listaDocRetroInsumo = listaDocRetroInsumo;
    }

    public List<FecetDocretroinsumo> getListaDocRetroInsumo() {
        return listaDocRetroInsumo;
    }

    public void setDocretroInsumoSeleccionado(FecetDocretroinsumo docretroInsumoSeleccionado) {
        this.docretroInsumoSeleccionado = docretroInsumoSeleccionado;
    }

    public FecetDocretroinsumo getDocretroInsumoSeleccionado() {
        return docretroInsumoSeleccionado;
    }

    public void setInsumosRetroalimentadosContador(List<FecetRetroalimentacionInsumo> insumosRetroalimentadosContador) {
        this.insumosRetroalimentadosContador = insumosRetroalimentadosContador;
    }

    public List<FecetRetroalimentacionInsumo> getInsumosRetroalimentadosContador() {
        return insumosRetroalimentadosContador;
    }

    public void setDocumentoSeleccionadoRetro(FecetContadorInsumos documentoSeleccionadoRetro) {
        this.documentoSeleccionadoRetro = documentoSeleccionadoRetro;
    }

    public FecetContadorInsumos getDocumentoSeleccionadoRetro() {
        return documentoSeleccionadoRetro;
    }

    public void setListaDocretroInsumoCargados(List<FecetDocretroinsumo> listaDocretroInsumoCargados) {
        this.listaDocretroInsumoCargados = listaDocretroInsumoCargados;
    }

    public List<FecetDocretroinsumo> getListaDocretroInsumoCargados() {
        return listaDocretroInsumoCargados;
    }

    public void setDocretroInsumoCargadoSeleccionado(FecetDocretroinsumo docretroInsumoCargadoSeleccionado) {
        this.docretroInsumoCargadoSeleccionado = docretroInsumoCargadoSeleccionado;
    }

    public FecetDocretroinsumo getDocretroInsumoCargadoSeleccionado() {
        return docretroInsumoCargadoSeleccionado;
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public List<String> getListaEstatusInsumo() {
        return listaEstatusInsumo;
    }

    public void setListaEstatusInsumo(List<String> listaEstatusInsumo) {
        this.listaEstatusInsumo = listaEstatusInsumo;
    }

}
