package mx.gob.sat.siat.feagace.vista.propuestas.subadmin.vo;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo.PropuestaVO;

import org.primefaces.model.UploadedFile;

public class VerificarProcedenciaVO {
    private List<PropuestaVO> listaPropuestas;
    private PropuestaVO propuestaVO;
    private boolean mostrarContribuyentePropuesta;
    private boolean mostrarInfo;
    private boolean mostrarInfoProps;
    private String mensaje1;
    private String mensaje2;
    private String mensajeRechazo1;
    private String mensajeRechazo2;
    private Date fechaHoy;
    private String motivoRechazo;
    private UploadedFile archivoCarga;
    private List<FecetRechazoPropuesta> listaRechazo;
    private List<FececMotivo> listaMotivos;
    private BigDecimal tipoRechazoSeleccionado;
    private boolean mostrarHistorico;
    private List<FecetDocOrden> listaDoctoOrden;

    public VerificarProcedenciaVO() {
        this.listaPropuestas = new ArrayList<PropuestaVO>();
    }

    public void setListaPropuestas(List<PropuestaVO> listaPropuestas) {
        this.listaPropuestas = listaPropuestas;
    }

    public List<PropuestaVO> getListaPropuestas() {
        return listaPropuestas;
    }

    public void setMostrarContribuyentePropuesta(boolean mostrarContribuyentePropuesta) {
        this.mostrarContribuyentePropuesta = mostrarContribuyentePropuesta;
    }

    public boolean isMostrarContribuyentePropuesta() {
        return mostrarContribuyentePropuesta;
    }

    public void setMostrarInfoProps(boolean mostrarInfoProps) {
        this.mostrarInfoProps = mostrarInfoProps;
    }

    public boolean isMostrarInfoProps() {
        return mostrarInfoProps;
    }

    public void setMensaje1(String mensaje1) {
        this.mensaje1 = mensaje1;
    }

    public String getMensaje1() {
        return mensaje1;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensajeRechazo1(String mensajeRechazo1) {
        this.mensajeRechazo1 = mensajeRechazo1;
    }

    public String getMensajeRechazo1() {
        return mensajeRechazo1;
    }

    public void setMensajeRechazo2(String mensajeRechazo2) {
        this.mensajeRechazo2 = mensajeRechazo2;
    }

    public String getMensajeRechazo2() {
        return mensajeRechazo2;
    }

    public void setPropuestaVO(PropuestaVO propuestaVO) {
        this.propuestaVO = propuestaVO;
    }

    public PropuestaVO getPropuestaVO() {
        return propuestaVO;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy != null ? new Date(fechaHoy.getTime()) : null;
    }

    public Date getFechaHoy() {
        return (fechaHoy != null) ? (Date) fechaHoy.clone() : null;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setListaRechazo(List<FecetRechazoPropuesta> listaRechazo) {
        this.listaRechazo = listaRechazo;
    }

    public List<FecetRechazoPropuesta> getListaRechazo() {
        return listaRechazo;
    }

    public void setListaMotivos(List<FececMotivo> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public List<FececMotivo> getListaMotivos() {
        return listaMotivos;
    }

    public void setTipoRechazoSeleccionado(BigDecimal tipoRechazoSeleccionado) {
        this.tipoRechazoSeleccionado = tipoRechazoSeleccionado;
    }

    public BigDecimal getTipoRechazoSeleccionado() {
        return tipoRechazoSeleccionado;
    }

    public void setMostrarHistorico(boolean mostrarHistorico) {
        this.mostrarHistorico = mostrarHistorico;
    }

    public boolean isMostrarHistorico() {
        return mostrarHistorico;
    }

    public void setListaDoctoOrden(List<FecetDocOrden> listaDoctoOrden) {
        this.listaDoctoOrden = listaDoctoOrden;
    }

    public List<FecetDocOrden> getListaDoctoOrden() {
        return listaDoctoOrden;
    }

    public boolean isMostrarInfo() {
        return mostrarInfo;
    }

    public void setMostrarInfo(boolean mostrarInfo) {
        this.mostrarInfo = mostrarInfo;
    }

}
