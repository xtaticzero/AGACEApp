package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * Clase que sirve para encapsular informacion para las propuestas
 * rechazadas en verificacion de procedencia de documento electronico
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetPropRechVerifProcedencia extends BaseModel implements Serializable {
    
    @SuppressWarnings("compatibility:5858578036336333469")
    private static final long serialVersionUID = 2743844185321730573L;

    private String idPropuesta;
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

    public void setIdPropuesta(String idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    public String getIdPropuesta() {
        return idPropuesta;
    }
}
