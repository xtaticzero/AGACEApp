package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AsociarColaboradoresAttributeAbstractHelper implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private boolean monstrarPanelOrdenes;

    private BigDecimal idProrrogaOrdenSeleccionada;
    private Date fechaEnvioProrrogaOrdenSeleccionada;
    private BigDecimal idPromocionSeleccionada;
    private Date fechaEnvioPromocionSeleccionada;

    private String tipoColaborador;
    private String mensajeNoIdc;
    private String mensajeNoMediosContacto;
    private String mensajeNoEnvioCorreo;
    private String leyendaContribuyenteMedios;
    private String rfcRLLista;
    private String nombreDocumentoOrden;
    private BigDecimal idPruebaPericialSeleccionada;
    private Date fechaEnvioPruebaPericialSeleccionada;
    private Date fechaOrdenDocExpediente;
    
    public Date getFechaEnvioPruebaPericialSeleccionada() {
        return (fechaEnvioPruebaPericialSeleccionada != null) ? (Date) fechaEnvioPruebaPericialSeleccionada.clone() : null;
    }

    public void setFechaEnvioPruebaPericialSeleccionada(Date fechaEnvioPruebaPericialSeleccionada) {
        this.fechaEnvioPruebaPericialSeleccionada = fechaEnvioPruebaPericialSeleccionada != null ? new Date(fechaEnvioPruebaPericialSeleccionada.getTime()) : null;
    }

    public BigDecimal getIdPruebaPericialSeleccionada() {
        return idPruebaPericialSeleccionada;
    }

    public void setIdPruebaPericialSeleccionada(BigDecimal idPruebaPericialSeleccionada) {
        this.idPruebaPericialSeleccionada = idPruebaPericialSeleccionada;
    }

    public String getNombreDocumentoOrden() {
        return nombreDocumentoOrden;
    }

    public void setNombreDocumentoOrden(String nombreDocumentoOrden) {
        this.nombreDocumentoOrden = nombreDocumentoOrden;
    }
    
    public void setMonstrarPanelOrdenes(boolean monstrarPanelOrdenes) {
        this.monstrarPanelOrdenes = monstrarPanelOrdenes;
    }

    public boolean isMonstrarPanelOrdenes() {
        return monstrarPanelOrdenes;
    }
    
    public void setIdPromocionSeleccionada(BigDecimal idPromocionSeleccionada) {
        this.idPromocionSeleccionada = idPromocionSeleccionada;
    }

    public BigDecimal getIdPromocionSeleccionada() {
        return idPromocionSeleccionada;
    }

    public void setFechaEnvioPromocionSeleccionada(Date fechaEnvioPromocionSeleccionada) {
        this.fechaEnvioPromocionSeleccionada
                = (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public Date getFechaEnvioPromocionSeleccionada() {
        return (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada : null;
    }

    public void setIdProrrogaOrdenSeleccionada(BigDecimal idProrrogaOrdenSeleccionada) {
        this.idProrrogaOrdenSeleccionada = idProrrogaOrdenSeleccionada;
    }

    public BigDecimal getIdProrrogaOrdenSeleccionada() {
        return idProrrogaOrdenSeleccionada;
    }

    public void setFechaEnvioProrrogaOrdenSeleccionada(Date fechaEnvioProrrogaOrdenSeleccionada) {
        this.fechaEnvioProrrogaOrdenSeleccionada
                = (fechaEnvioProrrogaOrdenSeleccionada != null) ? (Date) fechaEnvioProrrogaOrdenSeleccionada : null;
    }

    public Date getFechaEnvioProrrogaOrdenSeleccionada() {
        return (fechaEnvioProrrogaOrdenSeleccionada != null) ? (Date) fechaEnvioProrrogaOrdenSeleccionada : null;
    }

    public void setRfcRLLista(String rfcRLLista) {
        this.rfcRLLista = rfcRLLista;
    }

    public String getRfcRLLista() {
        return rfcRLLista;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setMensajeNoIdc(String mensajeNoIdc) {
        this.mensajeNoIdc = mensajeNoIdc;
    }

    public String getMensajeNoIdc() {
        return mensajeNoIdc;
    }

    public void setMensajeNoMediosContacto(String mensajeNoMediosContacto) {
        this.mensajeNoMediosContacto = mensajeNoMediosContacto;
    }

    public String getMensajeNoMediosContacto() {
        return mensajeNoMediosContacto;
    }

    public String getMensajeNoEnvioCorreo() {
        return mensajeNoEnvioCorreo;
    }

    public void setMensajeNoEnvioCorreo(String mensajeNoEnvioCorreo) {
        this.mensajeNoEnvioCorreo = mensajeNoEnvioCorreo;
    }

    public void setLeyendaContribuyenteMedios(String leyendaContribuyenteMedios) {
        this.leyendaContribuyenteMedios = leyendaContribuyenteMedios;
    }

    public String getLeyendaContribuyenteMedios() {
        return leyendaContribuyenteMedios;
    }

    public Date getFechaOrdenDocExpediente() {
        return (fechaOrdenDocExpediente != null) ? (Date) fechaOrdenDocExpediente.clone() : null;
    }

    public void setFechaOrdenDocExpediente(Date fechaOrdenDocExpediente) {
        this.fechaOrdenDocExpediente = fechaOrdenDocExpediente != null ? new Date(fechaOrdenDocExpediente.getTime()) : null;
    }

}
