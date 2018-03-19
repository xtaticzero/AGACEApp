/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

public class DatosAuditor {  
    
    /**
     * Estes atributo mapea los datos de la columna  ID_AUD
     */
    private BigDecimal idAud;
    
    /**
     * Estes atributo mapea los datos de la columna RFC_AUD
     */
    private String rfcAud;
    
    /**
     * Estes atributo mapea los datos de la columna NOM_AUD
     */
    private String nomAud;
    
    /**
     * Estes atributo mapea los datos de la columna CORREO_AUD
     */
    private String correoAud;
    
    /**
     * Metodo setIdAud
     * @param idAud
     */
    public void setIdAud(final BigDecimal idAud) {
        this.idAud = idAud;
    }
    
    /**
     * Metodo getIdAud
     * @return BigDecimal
     */
    public BigDecimal getIdAud() {
        return idAud;
    }
    
    /**
     * Metodo setRfcAud
     * @param rfcAud
     */
    public void setRfcAud(final String rfcAud) {
        this.rfcAud = rfcAud;
    }
    
    /**
     * Metodo getRfcAud
     * @return String
     */
    public String getRfcAud() {
        return rfcAud;
    }
    
    /**
     * Metodo setNomAud
     * @param nomAud
     */
    public void setNomAud(final String nomAud) {
        this.nomAud = nomAud;
    }
    
    /**
     * Metodo getNomAud
     * @return String
     */
    public String getNomAud() {
        return nomAud;
    }
    
    /**
     * Metodo setCorreoAud
     * @param correoAud
     */
    public void setCorreoAud(final String correoAud) {
        this.correoAud = correoAud;
    }

    /**
     * Metodo getCorreoAud
     * @return String
     */
    public String getCorreoAud() {
        return correoAud;
    }
}
