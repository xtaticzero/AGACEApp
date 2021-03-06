package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * Clase que almacena la informacion relacionada a las propuestas
 * transferidas pendientes de validacion
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropTransferPendValidacion extends BaseModel implements Serializable {
    
    @SuppressWarnings("compatibility:-4691191141104191230")
    private static final long serialVersionUID = -1326788569093522818L;

    private String idPropuesta;
    private String idRegistro;
    private String rfcContribuyente;
    private String prioridad;
    private String metodo;
    private BigDecimal presuntiva;
    private String unidadAdmvaATransfer;

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
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

    public void setUnidadAdmvaATransfer(String unidadAdmvaATransfer) {
        this.unidadAdmvaATransfer = unidadAdmvaATransfer;
    }

    public String getUnidadAdmvaATransfer() {
        return unidadAdmvaATransfer;
    }

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }
}
