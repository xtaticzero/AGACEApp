package mx.gob.sat.siat.feagace.vista.propuestas.administrador.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PropuestaVO implements Serializable {

    @SuppressWarnings("compatibility:-3272856311629027837")
    private static final long serialVersionUID = 1L;
    private boolean seleccionado;
    private BigDecimal idPropuesta;
    private String idRegistro;
    private String metodo;
    private BigDecimal presuntiva;
    private String prioridad;
    private String subprograma;
    private String rfcContribuyente;
    private BigDecimal idContribuyente;
    private String observacion;
    private String rfcCreacion;
    private String rfcSubadmin;
    private String rfcAuditor;
    private String rfcFirmante;
    private String rfcAdminEmision;
    private BigDecimal idArace;

    public PropuestaVO() {
        this.seleccionado = false;
        this.idRegistro = "";
        this.metodo = "";
        this.presuntiva = BigDecimal.ZERO;
        this.prioridad = "";
        this.subprograma = "";
        this.observacion = "";
        this.rfcAuditor = "";
        this.rfcCreacion = "";
        this.rfcFirmante = "";
        this.rfcSubadmin = "";
        this.rfcAdminEmision = "";
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setPresuntiva(BigDecimal presuntiva) {
        this.presuntiva = presuntiva;
    }

    public BigDecimal getPresuntiva() {
        return presuntiva;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setSubprograma(String subprograma) {
        this.subprograma = subprograma;
    }

    public String getSubprograma() {
        return subprograma;
    }

    public void setIdPropuesta(BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setIdContribuyente(BigDecimal idContribuyente) {
        this.idContribuyente = idContribuyente;
    }

    public BigDecimal getIdContribuyente() {
        return idContribuyente;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getRfcCreacion() {
        return rfcCreacion;
    }

    public void setRfcCreacion(String rfcCreacion) {
        this.rfcCreacion = rfcCreacion;
    }

    public String getRfcSubadmin() {
        return rfcSubadmin;
    }

    public void setRfcSubadmin(String rfcSubadmin) {
        this.rfcSubadmin = rfcSubadmin;
    }

    public String getRfcAuditor() {
        return rfcAuditor;
    }

    public void setRfcAuditor(String rfcAuditor) {
        this.rfcAuditor = rfcAuditor;
    }

    public String getRfcFirmante() {
        return rfcFirmante;
    }

    public void setRfcFirmante(String rfcFirmante) {
        this.rfcFirmante = rfcFirmante;
    }

    public String getRfcAdminEmision() {
        return rfcAdminEmision;
    }

    public void setRfcAdminEmision(String rfcAdminEmision) {
        this.rfcAdminEmision = rfcAdminEmision;
    }

    public BigDecimal getIdArace() {
        return idArace;
    }

    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }
}
