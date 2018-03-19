/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public enum IdentificadorProcesoEnum {
    // Procesos de Contribuyente
    //RETROALIMENTACION_MARBETES("MYP00002"),

    
    PE_AUTORIZAR_Y_RETROALIMENTAR_PROPUESTAS("FECE00007");

    /**
     * String con el identificador
     */
    private final String identificadorPortal;

    /**
     * Constructor IDENTIFICADOR_PROCESO
     */
    IdentificadorProcesoEnum(String descripcion) {
        this.identificadorPortal = descripcion;
    }

    public String getIdentificadorPortal() {
        return identificadorPortal;
    }
    
}
