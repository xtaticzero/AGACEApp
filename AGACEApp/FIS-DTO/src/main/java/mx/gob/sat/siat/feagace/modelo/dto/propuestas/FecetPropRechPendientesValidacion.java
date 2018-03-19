package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * Clase que encapsula la informacion de propuestas rechazadas
 * pendientes de validacion
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropRechPendientesValidacion extends BaseModel implements Serializable {
    
    @SuppressWarnings("compatibility:-1027835212385371218")
    private static final long serialVersionUID = -6609907843996782560L;

    private String idRegistro;
    private String rfcContribuyente;
    private String prioridad;
    private String metodo;
    private BigDecimal presuntiva;

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
    
}
