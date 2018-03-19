package mx.gob.sat.siat.feagace.vista.propuestas;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetCancelacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.enums.TiposEstatusPropuestaEnum;

public class AbstractValidarFirmarDoctoAtrributes extends BaseManagedBean {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String rfcFirmante;
    private String observaciones;
    private Date fechaNoAprobacion;
    private BigDecimal idEmpleadoFirmado;
    private FecetPropuesta propuestaActual;
    private FecetDocExpediente expendieteActual;
    private TiposEstatusPropuestaEnum estatusActual;
    private FecetCancelacion cancelacionActual;
    private FecetContadorPropuestasRechazados rechazoActual;
    private FecetTransferenciaContador transferenciaActual;
    private FecetRetroContador retroActual;
    private DocumentoVista documentoActual;
    private FecebAccionPropuesta historicoAccion;
    private String nombreArchivo;
    private String rutaArchivo;
    private FecetRetroalimentacion solicitudRetroalimentacion;
    private BigDecimal presuntiva;
    private String labelHeader;
    
    public final String getRfcFirmante() {
        return rfcFirmante;
    }

    public final void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public final FecetPropuesta getPropuestaActual() {
        return propuestaActual;
    }

    public final void setPropuestaActual(FecetPropuesta propuestaActual) {
        this.propuestaActual = propuestaActual;
    }

    public final FecetDocExpediente getExpendieteActual() {
        return expendieteActual;
    }

    public final void setExpendieteActual(FecetDocExpediente expendieteActual) {
        this.expendieteActual = expendieteActual;
    }

    public final TiposEstatusPropuestaEnum getEstatusActual() {
        return estatusActual;
    }

    public final void setEstatusActual(TiposEstatusPropuestaEnum estatusActual) {
        this.estatusActual = estatusActual;
    }

    public final FecetCancelacion getCancelacionActual() {
        return cancelacionActual;
    }

    public final void setCancelacionActual(FecetCancelacion cancelacionActual) {
        this.cancelacionActual = cancelacionActual;
    }

    public final FecetContadorPropuestasRechazados getRechazoActual() {
        return rechazoActual;
    }

    public final void setRechazoActual(FecetContadorPropuestasRechazados rechazoActual) {
        this.rechazoActual = rechazoActual;
    }

    public final DocumentoVista getDocumentoActual() {
        return documentoActual;
    }

    public final void setDocumentoActual(DocumentoVista documentoActual) {
        this.documentoActual = documentoActual;
    }

    public final FecetTransferenciaContador getTransferenciaActual() {
        return transferenciaActual;
    }

    public final void setTransferenciaActual(FecetTransferenciaContador transferenciaActual) {
        this.transferenciaActual = transferenciaActual;
    }
    
    public final FecetRetroContador getRetroActual() {
        return retroActual;
    }

    public final void setRetroActual(FecetRetroContador retroActual) {
        this.retroActual = retroActual;
    }

    public final String getObservaciones() {
        return observaciones;
    }

    public final void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public final Date getFechaNoAprobacion() {
        return fechaNoAprobacion != null ? (Date)fechaNoAprobacion.clone() : null;
    }

    public final void setFechaNoAprobacion(Date fechaNoAprobacion) {
        this.fechaNoAprobacion = fechaNoAprobacion != null ? (Date)fechaNoAprobacion.clone() : null;
    }

    public final BigDecimal getIdEmpleadoFirmado() {
        return idEmpleadoFirmado;
    }

    public final void setIdEmpleadoFirmado(BigDecimal idEmpleadoFirmado) {
        this.idEmpleadoFirmado = idEmpleadoFirmado;
    }

    public FecebAccionPropuesta getHistoricoAccion() {
        return historicoAccion;
    }

    public void setHistoricoAccion(FecebAccionPropuesta historicoAccion) {
        this.historicoAccion = historicoAccion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public FecetRetroalimentacion getSolicitudRetroalimentacion() {
        return solicitudRetroalimentacion;
    }

    public void setSolicitudRetroalimentacion(FecetRetroalimentacion solicitudRetroalimentacion) {
        this.solicitudRetroalimentacion = solicitudRetroalimentacion;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public String getLabelHeader() {
        return labelHeader;
    }

    public void setLabelHeader(String labelHeader) {
        this.labelHeader = labelHeader;
    }
}
