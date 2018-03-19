/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.constants;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum IdentificadorProcesoEnum {
    
    AGACE_VALIDAR_Y_RETROALIMENTAR("FECET00011","Validación y Retroalimentación de Propuestas");

    /**
     * String con el identificador
     */
    private final String identificador;
    
    /**
     * String descripcion del identificador
     */
    private final String descripcion;

    /**
     * Constructor IDENTIFICADOR_PROCESO
     *
     * @param descripcion del elemento
     */
    IdentificadorProcesoEnum(String identificador, String descripcion) {
        this.identificador = identificador;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador del catalogo asociado al proceso
     *
     * @return identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * Obtiene la descripcion del catalogo
     *
     * @return descripci&oacute;n
     */
    public String getDescripcion() {
        return descripcion;
    }
}

