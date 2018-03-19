/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.enums;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum CausaRechazoPropuestaEnum {

    POR_IMPROCEDENCIA_LEGAL(1L, "Por improcedencia legal"),
    POR_NO_ENCONTRARSE_VIGENTE_LA_IRREGULARIDAD(5L, "Por no encontrarse vigente la irregularidad"),
    POR_MODIFICACION_DEL_MARCO_LEGAL_APLICABLE_IRREGULARIDAD(6L, "Por modificación del marco legal aplicable a la irregularidad");
    
    /**
     * ID
     */
    private final long id;

    /**
     * DESCRIPCION
     */
    private final String descripcion;

    private CausaRechazoPropuestaEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getId() {
        return id;
    }
    
}
