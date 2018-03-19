package mx.gob.sat.siat.feagace.vista.propuestas.origen.helper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;

public class OrigenCentralHelper implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6346131926178426348L;
    private String rfcSeleccionado;
    private FecetContribuyente contribuyente;
    private String folioSeleccionado;
    private BigDecimal idPropuestaSeleccionada;
    private FececEmpleado fececEmpleado;
    private FecetPropuesta propuesta;
    private FecetImpuesto impuestoVO;
    private FececTipoImpuesto tipoImpuestoSeleccionado;
    private transient UploadedFile archivoCarga;
    private FecetImpuesto impuestoSeleccionado;
    private FecetDocExpediente documentoSeleccionado;
    private Date fechaActual;
    private Date fechaInformeComReg;
    private Date fechaPresentacionComReg;
    private StreamedContent documentoSeleccionDescarga;
    private PropuestaOrigenCentralRegDTO centralRegSeleccionado;
    private String tipoFechaComite;
    private String metodo;
    private Date fechaActualPeriodo;
    private transient EmpleadoDTO empleadoDTO;

    public String getRfcSeleccionado() {
        return rfcSeleccionado;
    }

    public void setRfcSeleccionado(String rfcSeleccionado) {
        this.rfcSeleccionado = rfcSeleccionado;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getFolioSeleccionado() {
        return folioSeleccionado;
    }

    public void setFolioSeleccionado(String folioSeleccionado) {
        this.folioSeleccionado = folioSeleccionado;
    }

    public BigDecimal getIdPropuestaSeleccionada() {
        return idPropuestaSeleccionada;
    }

    public void setIdPropuestaSeleccionada(BigDecimal idPropuestaSeleccionada) {
        this.idPropuestaSeleccionada = idPropuestaSeleccionada;
    }

    public FececEmpleado getFececEmpleado() {
        return fececEmpleado;
    }

    public void setFececEmpleado(FececEmpleado fececEmpleado) {
        this.fececEmpleado = fececEmpleado;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetImpuesto getImpuestoVO() {
        return impuestoVO;
    }

    public void setImpuestoVO(FecetImpuesto impuestoVO) {
        this.impuestoVO = impuestoVO;
    }

    public FececTipoImpuesto getTipoImpuestoSeleccionado() {
        return tipoImpuestoSeleccionado;
    }

    public void setTipoImpuestoSeleccionado(FececTipoImpuesto tipoImpuestoSeleccionado) {
        this.tipoImpuestoSeleccionado = tipoImpuestoSeleccionado;
    }

    public UploadedFile getArchivoCarga() {
        return archivoCarga;
    }

    public void setArchivoCarga(UploadedFile archivoCarga) {
        this.archivoCarga = archivoCarga;
    }

    public FecetImpuesto getImpuestoSeleccionado() {
        return impuestoSeleccionado;
    }

    public void setImpuestoSeleccionado(FecetImpuesto impuestoSeleccionado) {
        this.impuestoSeleccionado = impuestoSeleccionado;
    }

    public FecetDocExpediente getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(FecetDocExpediente documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public Date getFechaActual() {
        return (fechaActual != null) ? (Date) fechaActual.clone() : null;

    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = (fechaActual != null) ? (Date) fechaActual.clone() : null;
    }

    public StreamedContent getDocumentoSeleccionDescarga() {
        return documentoSeleccionDescarga;
    }

    public void setDocumentoSeleccionDescarga(StreamedContent documentoSeleccionDescarga) {
        this.documentoSeleccionDescarga = documentoSeleccionDescarga;
    }

    public Date getFechaInformeComReg() {
        return (fechaInformeComReg != null) ? (Date) fechaInformeComReg.clone() : null;
    }

    public void setFechaInformeComReg(Date fechaInformeComReg) {
        this.fechaInformeComReg = (fechaInformeComReg != null) ? (Date) fechaInformeComReg.clone() : null;
    }

    public Date getFechaPresentacionComReg() {
        return (fechaPresentacionComReg != null) ? (Date) fechaPresentacionComReg.clone() : null;
    }

    public void setFechaPresentacionComReg(Date fechaPresentacionComReg) {
        this.fechaPresentacionComReg = (fechaPresentacionComReg != null) ? (Date) fechaPresentacionComReg.clone()
                : null;
    }

    public PropuestaOrigenCentralRegDTO getCentralRegSeleccionado() {
        return centralRegSeleccionado;
    }

    public void setCentralRegSeleccionado(PropuestaOrigenCentralRegDTO centralRegSeleccionado) {
        this.centralRegSeleccionado = centralRegSeleccionado;
    }

    public String getTipoFechaComite() {
        return tipoFechaComite;
    }

    public void setTipoFechaComite(String tipoFechaComite) {
        this.tipoFechaComite = tipoFechaComite;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setFechaActualPeriodo(Date fechaActualPeriodo) {
        this.fechaActualPeriodo = fechaActualPeriodo != null ? new Date(fechaActualPeriodo.getTime()) : null;
    }

    public Date getFechaActualPeriodo() {
        return (fechaActualPeriodo != null) ? (Date) fechaActualPeriodo.clone() : null;
    }

    public EmpleadoDTO getEmpleadoDTO() {
        return empleadoDTO;
    }

    public void setEmpleadoDTO(EmpleadoDTO empleadoDTO) {
        this.empleadoDTO = empleadoDTO;
    }

}
