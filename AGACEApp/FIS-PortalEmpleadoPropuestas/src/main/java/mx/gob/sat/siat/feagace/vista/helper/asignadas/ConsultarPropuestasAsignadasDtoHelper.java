/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.helper.asignadas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocumentoOrdenModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropCancelada;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.enums.TipoFechasComiteEnum;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultarPropuestasAsignadasDtoHelper implements Serializable {

    private static final long serialVersionUID = 5648686307932524820L;

    private int numeroDocumentosOrden;
    private int numeroOficiosActaulizables;
    private BigDecimal sumaPresuntiva;
    private Date fechaCargaArchivo;
    private BigDecimal motivoRechazoSeleccionado;
    private BigDecimal idAraceSeleccionado;
    private BigDecimal idRetroalimentacionSeleccionado;
    private FecetPropuesta propuestaSeleccionada;
    private FecetPropuesta propuestaSelAnalizar;
    private FecetRechazoPropuesta propuestaRechazada;
    private FecetRetroalimentacion propuestaRetroalimentada;
    private FecetContribuyente contribuyente;
    private FecetRechazoPropuesta docRechazoSeleccionado;
    private FecetTransferencia docTransferenciaSeleccionado;
    private FecetRetroalimentacion docRetroalimentarSeleccionado;
    private FecetDocOrden docOrdenSeleccionado;
    private DocumentoOrdenModel documentoOrdenSeleccionado;
    private FecetPropCancelada docCancelacionSeleccionado;
    private BigDecimal causaCancelacionSeleccion;
    private CargaDocumentoElectronicoDTO contribuyenteIdc;
    private List<TipoFechasComiteEnum> listaFechasComiteEnum;
    private List<UnidadAdministrativaEnum> listaUnidadAdminRegional;
    private ColaboradorVO agenteAduanalVO;
    private BigDecimal tipoOficioSeleccionado;
    private FecetOficio oficioSeleccionado;
    private int btnPapelTrabajo;

    public ConsultarPropuestasAsignadasDtoHelper() {
        this.propuestaSeleccionada = new FecetPropuesta();
        this.propuestaSelAnalizar = new FecetPropuesta();
        this.propuestaRechazada = new FecetRechazoPropuesta();
        this.fechaCargaArchivo = new Date();
        this.motivoRechazoSeleccionado = null;
        this.idAraceSeleccionado = null;
        this.idRetroalimentacionSeleccionado = null;
        this.contribuyente = new FecetContribuyente();
        this.sumaPresuntiva = BigDecimal.ZERO;
        this.docRechazoSeleccionado = new FecetRechazoPropuesta();
        this.docTransferenciaSeleccionado = new FecetTransferencia();
        this.docRetroalimentarSeleccionado = new FecetRetroalimentacion();
        this.propuestaRetroalimentada = new FecetRetroalimentacion();
        this.docOrdenSeleccionado = new FecetDocOrden();
        this.numeroDocumentosOrden = 0;
        this.documentoOrdenSeleccionado = new DocumentoOrdenModel();
        this.docCancelacionSeleccionado = new FecetPropCancelada();
        this.causaCancelacionSeleccion = null;
        this.contribuyenteIdc = new CargaDocumentoElectronicoDTO();
        this.agenteAduanalVO = new ColaboradorVO();
        this.tipoOficioSeleccionado = null;
        this.btnPapelTrabajo = 0;
        this.oficioSeleccionado = new FecetOficio();
        this.numeroOficiosActaulizables = 0;
    }

    public int getBtnPapelTrabajo() {
        return btnPapelTrabajo;
    }

    public void setBtnPapelTrabajo(int btnPapelTrabajo) {
        this.btnPapelTrabajo = btnPapelTrabajo;
    }

    public void setNumeroDocumentosOrden(int numeroDocumentosOrden) {
        this.numeroDocumentosOrden = numeroDocumentosOrden;
    }

    public int getNumeroDocumentosOrden() {
        return numeroDocumentosOrden;
    }

    public void setDocumentoOrdenSeleccionado(DocumentoOrdenModel documentoOrdenSeleccionado) {
        this.documentoOrdenSeleccionado = documentoOrdenSeleccionado;
    }

    public DocumentoOrdenModel getDocumentoOrdenSeleccionado() {
        return documentoOrdenSeleccionado;
    }

    public void setDocOrdenSeleccionado(FecetDocOrden docOrdenSeleccionado) {
        this.docOrdenSeleccionado = docOrdenSeleccionado;
    }

    public FecetDocOrden getDocOrdenSeleccionado() {
        return docOrdenSeleccionado;
    }

    public void setDocRechazoSeleccionado(FecetRechazoPropuesta docRechazoSeleccionado) {
        this.docRechazoSeleccionado = docRechazoSeleccionado;
    }

    public FecetRechazoPropuesta getDocRechazoSeleccionado() {
        return docRechazoSeleccionado;
    }

    public void setDocTransferenciaSeleccionado(FecetTransferencia docTransferenciaSeleccionado) {
        this.docTransferenciaSeleccionado = docTransferenciaSeleccionado;
    }

    public FecetTransferencia getDocTransferenciaSeleccionado() {
        return docTransferenciaSeleccionado;
    }

    public void setDocRetroalimentarSeleccionado(FecetRetroalimentacion docRetroalimentarSeleccionado) {
        this.docRetroalimentarSeleccionado = docRetroalimentarSeleccionado;
    }

    public FecetRetroalimentacion getDocRetroalimentarSeleccionado() {
        return docRetroalimentarSeleccionado;
    }

    public void setPropuestaRechazada(FecetRechazoPropuesta propuestaRechazada) {
        this.propuestaRechazada = propuestaRechazada;
    }

    public FecetRechazoPropuesta getPropuestaRechazada() {
        return propuestaRechazada;
    }

    public void setPropuestaRetroalimentada(FecetRetroalimentacion propuestaRetroalimentada) {
        this.propuestaRetroalimentada = propuestaRetroalimentada;
    }

    public FecetRetroalimentacion getPropuestaRetroalimentada() {
        return propuestaRetroalimentada;
    }

    public void setPropuestaSelAnalizar(FecetPropuesta propuestaSelAnalizar) {
        this.propuestaSelAnalizar = propuestaSelAnalizar;
    }

    public FecetPropuesta getPropuestaSelAnalizar() {
        return propuestaSelAnalizar;
    }

    public void setPropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetPropuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setpropuestaSeleccionada(FecetPropuesta propuestaSeleccionada) {
        this.propuestaSeleccionada = propuestaSeleccionada;
    }

    public FecetPropuesta getpropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public void setSumaPresuntiva(BigDecimal sumaPresuntiva) {
        this.sumaPresuntiva = sumaPresuntiva;
    }

    public BigDecimal getSumaPresuntiva() {
        return sumaPresuntiva;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setFechaCargaArchivo(Date fechaCargaArchivo) {
        this.fechaCargaArchivo = (fechaCargaArchivo != null) ? (Date) fechaCargaArchivo.clone() : null;
    }

    public Date getFechaCargaArchivo() {
        return (fechaCargaArchivo != null) ? (Date) fechaCargaArchivo.clone() : null;
    }

    public void setMotivoRechazoSeleccionado(BigDecimal motivoRechazoSeleccionado) {
        this.motivoRechazoSeleccionado = motivoRechazoSeleccionado;
    }

    public BigDecimal getMotivoRechazoSeleccionado() {
        return motivoRechazoSeleccionado;
    }

    public void setIdAraceSeleccionado(BigDecimal idAraceSeleccionado) {
        this.idAraceSeleccionado = idAraceSeleccionado;
    }

    public BigDecimal getIdAraceSeleccionado() {
        return idAraceSeleccionado;
    }

    public void setIdRetroalimentacionSeleccionado(BigDecimal idRetroalimentacionSeleccionado) {
        this.idRetroalimentacionSeleccionado = idRetroalimentacionSeleccionado;
    }

    public BigDecimal getIdRetroalimentacionSeleccionado() {
        return idRetroalimentacionSeleccionado;
    }

    public FecetPropCancelada getDocCancelacionSeleccionado() {
        return docCancelacionSeleccionado;
    }

    public void setDocCancelacionSeleccionado(FecetPropCancelada docCancelacionSeleccionado) {
        this.docCancelacionSeleccionado = docCancelacionSeleccionado;
    }

    public BigDecimal getCausaCancelacionSeleccion() {
        return causaCancelacionSeleccion;
    }

    public void setCausaCancelacionSeleccion(BigDecimal causaCancelacionSeleccion) {
        this.causaCancelacionSeleccion = causaCancelacionSeleccion;
    }

    public CargaDocumentoElectronicoDTO getContribuyenteIdc() {
        return contribuyenteIdc;
    }

    public void setContribuyenteIdc(CargaDocumentoElectronicoDTO contribuyenteIdc) {
        this.contribuyenteIdc = contribuyenteIdc;
    }

    public List<TipoFechasComiteEnum> getListaFechasComiteEnum() {
        return listaFechasComiteEnum;
    }

    public void setListaFechasComiteEnum(List<TipoFechasComiteEnum> listaFechasComiteEnum) {
        this.listaFechasComiteEnum = listaFechasComiteEnum;
    }

    public List<UnidadAdministrativaEnum> getListaUnidadAdminRegional() {
        return listaUnidadAdminRegional;
    }

    public void setListaUnidadAdminRegional(List<UnidadAdministrativaEnum> listaUnidadAdminRegional) {
        this.listaUnidadAdminRegional = listaUnidadAdminRegional;
    }

    public ColaboradorVO getAgenteAduanalVO() {
        return agenteAduanalVO;
    }

    public void setAgenteAduanalVO(ColaboradorVO agenteAduanalVO) {
        this.agenteAduanalVO = agenteAduanalVO;
    }

    public BigDecimal getTipoOficioSeleccionado() {
        return tipoOficioSeleccionado;
    }

    public void setTipoOficioSeleccionado(BigDecimal tipoOficioSeleccionado) {
        this.tipoOficioSeleccionado = tipoOficioSeleccionado;
    }

    public FecetOficio getOficioSeleccionado() {
        return oficioSeleccionado;
    }

    public void setOficioSeleccionado(FecetOficio oficioSeleccionado) {
        this.oficioSeleccionado = oficioSeleccionado;
    }

    public int getNumeroOficiosActaulizables() {
        return numeroOficiosActaulizables;
    }

    public void setNumeroOficiosActaulizables(int numeroOficiosActaulizables) {
        this.numeroOficiosActaulizables = numeroOficiosActaulizables;
    }
}
