/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.consulta.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PerfilContribuyenteVO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.vista.model.OficioConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.OrdenConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionConsultaDTO;
import mx.gob.sat.siat.feagace.vista.model.PromocionOficioConsultaDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ReimprimirDocumentosDTOHelper extends ReimprimirDocumentosDTOAbstractHelper {

    private static final long serialVersionUID = -9104753464594727138L;

    private AgaceOrden agaceOrden;
    private String rutaArchivo;
    private String nombreArchivo;
    private List<AgaceOrden> listOrden;
    private List<OrdenConsultaDTO> listOrdenConsulta;
    private OrdenConsultaDTO ordenConsultaSeleccionada;
    private PromocionConsultaDTO promocionConsultaSeleccionada;
    private PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada;
    private List<FecetDocOrden> expedienteOrden;
    private List<FecetOficio> oficiosFirmados;
    private List<FecetPruebasPericiales> listaPruebasPericiales;
    private List<FecetDocPruebasPericiales> listaDocPericiales;
    private FecetPruebasPericiales pruebaPericialSeleccionada;
    private BigDecimal idPromocionSeleccionada;
    private Date fechaEnvioSeleccionada;
    private BigDecimal idProrrogaOrdenSeleccionada;
    private Date fechaEnvioProrrogaOrdenSeleccionada;
    private BigDecimal idPromocionOficioSeleccionada;
    private Date fechaEnvioOficioSeleccionada;
    private BigDecimal idProrrogaOficioSeleccionada;
    private Date fechaEnvioProrrogaOficioSeleccionada;
    private PerfilContribuyenteVO perfilSession;
    private List<ColaboradorVO> asociados;
    private String tituloDoc;
    private ColaboradorVO asociadoSeleccionado;
    private OficioConsultaDTO oficio;

    public PromocionOficioConsultaDTO getPromocionOficioConsultaSeleccionada() {
        return promocionOficioConsultaSeleccionada;
    }

    public void setPromocionOficioConsultaSeleccionada(PromocionOficioConsultaDTO promocionOficioConsultaSeleccionada) {
        this.promocionOficioConsultaSeleccionada = promocionOficioConsultaSeleccionada;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public AgaceOrden getAgaceOrden() {
        return agaceOrden;
    }

    public void setAgaceOrden(AgaceOrden agaceOrden) {
        this.agaceOrden = agaceOrden;
    }

    public PromocionConsultaDTO getPromocionConsultaSeleccionada() {
        return promocionConsultaSeleccionada;
    }

    public void setPromocionConsultaSeleccionada(PromocionConsultaDTO promocionConsultaSeleccionada) {
        this.promocionConsultaSeleccionada = promocionConsultaSeleccionada;
    }

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

    public final List<FecetDocOrden> getExpedienteOrden() {
        return expedienteOrden;
    }

    public final void setExpedienteOrden(List<FecetDocOrden> expedienteOrden) {
        this.expedienteOrden = expedienteOrden;
    }

    public final List<FecetOficio> getOficiosFirmados() {
        return oficiosFirmados;
    }

    public final void setOficiosFirmados(List<FecetOficio> oficiosFirmados) {
        this.oficiosFirmados = oficiosFirmados;
    }

    public final List<FecetPruebasPericiales> getListaPruebasPericiales() {
        return listaPruebasPericiales;
    }

    public final void setListaPruebasPericiales(List<FecetPruebasPericiales> listaPruebasPericiales) {
        this.listaPruebasPericiales = listaPruebasPericiales;
    }

    public final FecetPruebasPericiales getPruebaPericialSeleccionada() {
        return pruebaPericialSeleccionada;
    }

    public final void setPruebaPericialSeleccionada(FecetPruebasPericiales pruebaPericialSeleccionada) {
        this.pruebaPericialSeleccionada = pruebaPericialSeleccionada;
    }

    public final List<FecetDocPruebasPericiales> getListaDocPericiales() {
        return listaDocPericiales;
    }

    public final void setListaDocPericiales(List<FecetDocPruebasPericiales> listaDocPericiales) {
        this.listaDocPericiales = listaDocPericiales;
    }

    public BigDecimal getIdPromocionSeleccionada() {
        return idPromocionSeleccionada;
    }

    public void setIdPromocionSeleccionada(BigDecimal idPromocionSeleccionada) {
        this.idPromocionSeleccionada = idPromocionSeleccionada;
    }

    public Date getFechaEnvioSeleccionada() {
        return (fechaEnvioSeleccionada != null) ? (Date) fechaEnvioSeleccionada.clone() : null;
    }

    public void setFechaEnvioSeleccionada(Date fechaEnvioSeleccionada) {
        this.fechaEnvioSeleccionada = fechaEnvioSeleccionada != null ? new Date(fechaEnvioSeleccionada.getTime()) : null;
    }

    public BigDecimal getIdProrrogaOrdenSeleccionada() {
        return idProrrogaOrdenSeleccionada;
    }

    public void setIdProrrogaOrdenSeleccionada(BigDecimal idProrrogaOrdenSeleccionada) {
        this.idProrrogaOrdenSeleccionada = idProrrogaOrdenSeleccionada;
    }

    public Date getFechaEnvioProrrogaOrdenSeleccionada() {
        return (fechaEnvioProrrogaOrdenSeleccionada != null) ? (Date) fechaEnvioProrrogaOrdenSeleccionada.clone() : null;
    }

    public void setFechaEnvioProrrogaOrdenSeleccionada(Date fechaEnvioProrrogaOrdenSeleccionada) {
        this.fechaEnvioProrrogaOrdenSeleccionada = fechaEnvioProrrogaOrdenSeleccionada != null ? new Date(fechaEnvioProrrogaOrdenSeleccionada.getTime()) : null;
    }

    public BigDecimal getIdPromocionOficioSeleccionada() {
        return idPromocionOficioSeleccionada;
    }

    public void setIdPromocionOficioSeleccionada(BigDecimal idPromocionOficioSeleccionada) {
        this.idPromocionOficioSeleccionada = idPromocionOficioSeleccionada;
    }

    public Date getFechaEnvioOficioSeleccionada() {
        return (fechaEnvioOficioSeleccionada != null) ? (Date) fechaEnvioOficioSeleccionada.clone() : null;
    }

    public void setFechaEnvioOficioSeleccionada(Date fechaEnvioOficioSeleccionada) {
        this.fechaEnvioOficioSeleccionada = fechaEnvioOficioSeleccionada != null ? new Date(fechaEnvioOficioSeleccionada.getTime()) : null;
    }

    public BigDecimal getIdProrrogaOficioSeleccionada() {
        return idProrrogaOficioSeleccionada;
    }

    public void setIdProrrogaOficioSeleccionada(BigDecimal idProrrogaOficioSeleccionada) {
        this.idProrrogaOficioSeleccionada = idProrrogaOficioSeleccionada;
    }

    public Date getFechaEnvioProrrogaOficioSeleccionada() {
        return (fechaEnvioProrrogaOficioSeleccionada != null) ? (Date) fechaEnvioProrrogaOficioSeleccionada.clone() : null;
    }

    public void setFechaEnvioProrrogaOficioSeleccionada(Date fechaEnvioProrrogaOficioSeleccionada) {
        this.fechaEnvioProrrogaOficioSeleccionada = fechaEnvioProrrogaOficioSeleccionada != null ? new Date(fechaEnvioProrrogaOficioSeleccionada.getTime()) : null;
    }

    public PerfilContribuyenteVO getPerfilSession() {
        return perfilSession;
    }

    public void setPerfilSession(PerfilContribuyenteVO perfilSession) {
        this.perfilSession = perfilSession;
    }

    public List<ColaboradorVO> getAsociados() {
        return asociados;
    }

    public void setAsociados(List<ColaboradorVO> asociados) {
        this.asociados = asociados;
    }

    public String getTituloDoc() {
        return tituloDoc;
    }

    public void setTituloDoc(String tituloDoc) {
        this.tituloDoc = tituloDoc;
    }

    public ColaboradorVO getAsociadoSeleccionado() {
        return asociadoSeleccionado;
    }

    public void setAsociadoSeleccionado(ColaboradorVO asociadoSeleccionado) {
        this.asociadoSeleccionado = asociadoSeleccionado;
    }

    public OficioConsultaDTO getOficio() {
        return oficio;
    }

    public void setOficio(OficioConsultaDTO oficio) {
        this.oficio = oficio;
    }

}
