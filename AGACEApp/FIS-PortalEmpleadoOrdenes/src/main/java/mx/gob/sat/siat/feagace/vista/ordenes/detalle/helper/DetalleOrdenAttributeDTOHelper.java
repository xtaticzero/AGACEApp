package mx.gob.sat.siat.feagace.vista.ordenes.detalle.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DetalleOrdenAttributeDTOHelper implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean monstrarPanelOrdenes;
    private boolean mostrarPanelOrdenAsociarColaboradores;
    private boolean mostrarDatosApoderadoLegal;
    private boolean mostrarDatosAgenteAduanal;
    private boolean mostrarPruebasAlegatos;

    private String rfcRLLista;
    private String tipoColaborador;
    private String mensajeNoIdc;
    private String mensajeNoMediosContacto;
    private String mensajeNoEnvioCorreo;
    private BigDecimal idPromocionSeleccionada;
    private BigDecimal idProrrogaOrdenSeleccionada;
    private Date fechaEnvioPromocionSeleccionada;
    private Date fechaEnvioProrrogaOrdenSeleccionada;
    
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
    
    public void setMostrarPruebasAlegatos(boolean mostrarPruebasAlegatos) {
        this.mostrarPruebasAlegatos = mostrarPruebasAlegatos;
    }

    public boolean isMostrarPruebasAlegatos() {
        return mostrarPruebasAlegatos;
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
    
    public void setMonstrarPanelOrdenes(boolean monstrarPanelOrdenes) {
        this.monstrarPanelOrdenes = monstrarPanelOrdenes;
    }

    public boolean isMonstrarPanelOrdenes() {
        return monstrarPanelOrdenes;
    }

    public void setMostrarDatosApoderadoLegal(boolean mostrarDatosApoderadoLegal) {
        this.mostrarDatosApoderadoLegal = mostrarDatosApoderadoLegal;
    }

    public boolean isMostrarDatosApoderadoLegal() {
        return mostrarDatosApoderadoLegal;
    }

    public void setMostrarDatosAgenteAduanal(boolean mostrarDatosAgenteAduanal) {
        this.mostrarDatosAgenteAduanal = mostrarDatosAgenteAduanal;
    }

    public boolean isMostrarDatosAgenteAduanal() {
        return mostrarDatosAgenteAduanal;
    }
    
    public void setRfcRLLista(String rfcRLLista) {
        this.rfcRLLista = rfcRLLista;
    }

    public String getRfcRLLista() {
        return rfcRLLista;
    }

    public void setMostrarPanelOrdenAsociarColaboradores(boolean mostrarPanelOrdenAsociarColaboradores) {
        this.mostrarPanelOrdenAsociarColaboradores = mostrarPanelOrdenAsociarColaboradores;
    }

    public boolean isMostrarPanelOrdenAsociarColaboradores() {
        return mostrarPanelOrdenAsociarColaboradores;
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
    
    public Date getFechaEnvioPromocionSeleccionada1() {
        return (fechaEnvioPromocionSeleccionada != null) ? (Date) fechaEnvioPromocionSeleccionada.clone() : null;
    }

}
