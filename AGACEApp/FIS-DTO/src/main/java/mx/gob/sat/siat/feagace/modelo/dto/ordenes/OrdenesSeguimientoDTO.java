/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

public class OrdenesSeguimientoDTO {
    
    /**
     * Este atributo mapea los datos de la columna ID_METODO
     */
    private BigDecimal idMetodo;
    
    /**
     * Este atributo mapea los datos de la columna STR_METODO
     */
    private String strMetodo;
    
    /**
     * Este atributo mapea los datos de la columna INT_ORD_VALIDA
     */
    private Long intOrdValida;
    
    /**
     * Este atributo mapea los datos de la columna INT_ORD_FIRMA
     */
    private Long intOrdFirma;
    
    /**
     * Metodo setStrMetodo
     * @param strMetodo
     */
    public void setStrMetodo(final String strMetodo) {
        this.strMetodo = strMetodo;
    }
    
    /**
     * Metodo getStrMetodo
     * @return String 
     */
    public String getStrMetodo() {
        return strMetodo;
    }
    
    /**
     * Metodo setIntOrdValida
     * @param intOrdValida
     */
    public void setIntOrdValida(final Long intOrdValida) {
        this.intOrdValida = intOrdValida;
    }

    /**
     * Metodo getIntOrdValida
     * @return Long
     */
    public Long getIntOrdValida() {
        return intOrdValida;
    }

    /**
     * Metodo setIntOrdFirma
     * @param intOrdFirma
     */
    public void setIntOrdFirma(final Long intOrdFirma) {
        this.intOrdFirma = intOrdFirma;
    }
    
    /**
     * Metodo getIntOrdFirma
     * @return Long
     */
    public Long getIntOrdFirma() {
        return intOrdFirma;
    }
    
    /**
     * Metodo setIdMetodo
     * @param idMetodo
     */
    public void setIdMetodo(final BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }
    
    /**
     * Metodo getIdMetodo
     * @return BigDecimal
     */
    public BigDecimal getIdMetodo() {
        return idMetodo;
    }
    
}
