/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;


import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececMetodo extends BaseModel{

    @SuppressWarnings("compatibility:-1117558352248529217")
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo mapea la columna ID_METODO de la tabla FECEC_METODO
     */
    private BigDecimal idMetodo;
    
    /**
     * Este atributo mapea la columna NOMBRE_METODO de la tabla FECEC_METODO
     */
    private String nombre;
    
    /**
     * Este atributo mapea la columna ABREVIATURA de la tabla FECEC_METODO
     */
    private String abreviatura;
    
    /**
     * Este atributo mapea la columna DURACION_PLAZO_ORDEN de la tabla FECEC_METODO
     */
    private Integer duracionPlazoOrden;
    
    /**
     * Este atributo mapea la columna ID_TIPO_PLAZO de la tabla FECEC_METODO
     */
    private BigDecimal idTipoPlazo;
    
    /**
     * Este atributo mapea la columna DIAS_PLAZO_DOCUMENTACION de la tabla FECEC_METODO
     */
    private Integer diasPlazoDocumentacion;
    
    /**
     * Este atributo mapea la columna DIAS_PLAZO_PRORROGA de la tabla FECEC_METODO
     */
    private Integer diasPlazoProrroga;
    
    /**
     * Este atributo mapea la columna PRORROGA de la tabla FECEC_METODO
     */
    private Boolean prorroga;
    
    /**
     * Este atributo mapea la columna PLAZO_EMISION_RESOLUCION de la tabla FECEC_METODO
     */
    private Integer plazoEmisionResolucion;
    
    /**
     * Este atributo mapea la columna ID_TIPO_PLAZO_RESOLUCION de la tabla FECEC_METODO
     */
    private BigDecimal idTipoPlazoResolucion;

    /**
     * Metodo setIdMetodo
     * @param idMetodo
     */
    public void setIdMetodo(final BigDecimal idMetodo) {
        this.idMetodo = idMetodo;
    }

     /**
     * Metodo getIdMetodo
     * @return Long
     */
    public BigDecimal getIdMetodo() {
        return idMetodo;
    }

    /**
     * Metodo setNombreMetodo
     * @param nombre
     */
    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo getNombreMetodo
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo setAbreviatura
     * @param abreviatura
     */
    public void setAbreviatura(final String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * Metodo getAbreviatura
     * @return String
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Metodo setDuracionPlazoOrden
     * @param duracionPlazoOrden
     */
    public void setDuracionPlazoOrden(final Integer duracionPlazoOrden) {
        this.duracionPlazoOrden = duracionPlazoOrden;
    }

    /**
     * Metodo getDuracionPlazoOrden
     * @return Integer
     */
    public Integer getDuracionPlazoOrden() {
        return duracionPlazoOrden;
    }

    /**
     * Metodo setIdTipoPlazo
     * @param idTipoPlazo
     */
    public void setIdTipoPlazo(final BigDecimal idTipoPlazo) {
        this.idTipoPlazo = idTipoPlazo;
    }

    /**
     * Metodo getIdTipoPlazo
     * @return BigDecimal
     */
    public BigDecimal getIdTipoPlazo() {
        return idTipoPlazo;
    }

    /**
     * Metodo setDiasPlazoDocumentacion
     * @param diasPlazoDocumentacion
     */
    public void setDiasPlazoDocumentacion(final Integer diasPlazoDocumentacion) {
        this.diasPlazoDocumentacion = diasPlazoDocumentacion;
    }

    /**
     * Metodo getDiasPlazoDocumentacion
     * @return Integer
     */
    public Integer getDiasPlazoDocumentacion() {
        return diasPlazoDocumentacion;
    }

    /**
     * Metodo setDiasPlazoProrroga
     * @param diasPlazoProrroga
     */
    public void setDiasPlazoProrroga(final Integer diasPlazoProrroga) {
        this.diasPlazoProrroga = diasPlazoProrroga;
    }

    /**
     * Metodo getDiasPlazoProrroga
     * @return Integer
     */
    public Integer getDiasPlazoProrroga() {
        return diasPlazoProrroga;
    }

     /**
      * Metodo setProrroga
      * @param prorroga
      */
     public void setProrroga(final Boolean prorroga) {
         this.prorroga = prorroga;
     }

     /**
      * Metodo getProrroga
      * @return Boolean
      */
     public Boolean getProrroga() {
         return prorroga;
     }

    /**
     * Metodo setPlazoEmisionResolucion
     * @param plazoEmisionResolucion
     */
    public void setPlazoEmisionResolucion(final Integer plazoEmisionResolucion) {
        this.plazoEmisionResolucion = plazoEmisionResolucion;
    }

    /**
     * Metodo getAbreviatura
     * @return Integer
     */
    public Integer getPlazoEmisionResolucion() {
        return plazoEmisionResolucion;
    }

     /**
      * Metodo setIdTipoPlazoResolucion
      * @param idTipoPlazoResolucion
      */
     public void setIdTipoPlazoResolucion(final BigDecimal idTipoPlazoResolucion) {
         this.idTipoPlazoResolucion = idTipoPlazoResolucion;
     }

     /**
      * Metodo getIdTipoPlazoResolucion
      * @return BigDecimal
      */
     public BigDecimal getIdTipoPlazoResolucion() {
         return idTipoPlazoResolucion;
     }
}
