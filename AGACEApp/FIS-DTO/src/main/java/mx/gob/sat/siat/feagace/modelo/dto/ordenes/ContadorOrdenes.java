/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class ContadorOrdenes extends BaseModel{

    @SuppressWarnings("compatibility:-2256615645525772186")
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMAR_EH obtenido de un conteo de ordenes a firmar para el metodo EHO
     */
    private Long totalFirmarEHO;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMAR_ORG obtenido de un conteo de ordenes a firmar para el metodo ORG
     */
    private Long totalFirmarORG;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMAR_MCA obtenido de un conteo de ordenes a firmar para el metodo MCA
     */
    private Long totalFirmarMCA;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMAR_REE obtenido de un conteo de ordenes a firmar para el metodo REE
     */
    private Long totalFirmarREE;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_FIRMAR_UCA obtenido de un conteo de ordenes a firmar para el metodo UCA
     */
    private Long totalFirmarUCA;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_VALIDAR_EH obtenido de un conteo de ordenes a validar para el metodo EH
     */
    private Long totalValidarEHO;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_VALIDAR_ORG obtenido de un conteo de ordenes a validar para el metodo ORG
     */
    private Long totalValidarORG;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_VALIDAR_MCA obtenido de un conteo de ordenes a validar para el metodo MCA
     */
    private Long totalValidarMCA;
    
    /**
     * Este atributo mapea el valor de la columna TOTAL_VALIDAR_REE obtenido de un conteo de ordenes a validar para el metodo REE
     */
    private Long totalValidarREE;
    
    private Long totalValidarUCA;
    
    
    /**
     * Metodo setTotalFirmarEH
     * @param totalFirmarEH
     */
    public void setTotalFirmarEHO(final Long totalFirmarEH) {
        this.totalFirmarEHO = totalFirmarEH;
    }
    
    /**
     * Metodo getTotalFirmarEH
     * @return Long
     */
    public Long getTotalFirmarEHO() {
        return totalFirmarEHO;
    }
    
    /**
     * Metodo setTotalFirmarORG
     * @param totalFirmarORG
     */
    public void setTotalFirmarORG(final Long totalFirmarORG) {
        this.totalFirmarORG = totalFirmarORG;
    }
    
    /**
     * Metodo getTotalFirmarORG
     * @return Long
     */
    public Long getTotalFirmarORG() {
        return totalFirmarORG;
    }

    /**
     * Metodo setTotalFirmarMCA
     * @param totalFirmarMCA
     */
    public void setTotalFirmarMCA(final Long totalFirmarMCA) {
        this.totalFirmarMCA = totalFirmarMCA;
    }
    
    /**
     * Metodo getTotalFirmarMCA
     * @return Long
     */
    public Long getTotalFirmarMCA() {
        return totalFirmarMCA;
    }
    
    /**
     * Metodo setTotalFirmarREE
     * @param totalFirmarREE
     */
    public void setTotalFirmarREE(final Long totalFirmarREE) {
        this.totalFirmarREE = totalFirmarREE;
    }
    
    /**
     * Metodo getTotalFirmarREE
     * @return Long
     */
    public Long getTotalFirmarREE() {
        return totalFirmarREE;
    }
    
    /**
     * Metodo setTotalValidarEH
     * @param totalValidarEH
     */
    public void setTotalValidarEHO(final Long totalValidarEHO) {
        this.totalValidarEHO = totalValidarEHO;
    }
    
    /**
     * Metodo getTotalValidarEH
     * @return Long
     */
    public Long getTotalValidarEHO() {
        return totalValidarEHO;
    }
     /**
     * Metodo setTotalValidarORG
     * @param totalValidarORG
     */
    public void setTotalValidarORG(final Long totalValidarORG) {
        this.totalValidarORG = totalValidarORG;
    }
    
    /**
     * Metodo getTotalValidarORG
     * @return Long
     */
    public Long getTotalValidarORG() {
        return totalValidarORG;
    }
    
    /**
     * Metodo setTotalValidarMCA
     * @param totalValidarMCA
     */
    public void setTotalValidarMCA(final Long totalValidarMCA) {
        this.totalValidarMCA = totalValidarMCA;
    }
    
    /**
     * Metodo getTotalValidarMCA
     * @return Long
     */
    public Long getTotalValidarMCA() {
        return totalValidarMCA;
    }
    
    /**
     * Metodo setTotalValidarREE
     * @param totalValidarREE
     */
    public void setTotalValidarREE(final Long totalValidarREE) {
        this.totalValidarREE = totalValidarREE;
    }
    
    /**
     * Metodo getTotalValidarREE
     * @return Long
     */
    public Long getTotalValidarREE() {
        return totalValidarREE;
    }

    public void setTotalFirmarUCA(Long totalFirmarUCA) {
        this.totalFirmarUCA = totalFirmarUCA;
    }

    public Long getTotalFirmarUCA() {
        return totalFirmarUCA;
    }

    public void setTotalValidarUCA(Long totalValidarUCA) {
        this.totalValidarUCA = totalValidarUCA;
    }

    public Long getTotalValidarUCA() {
        return totalValidarUCA;
    }
}
