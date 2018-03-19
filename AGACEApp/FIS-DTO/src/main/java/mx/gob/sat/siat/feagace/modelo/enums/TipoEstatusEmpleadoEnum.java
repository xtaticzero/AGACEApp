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
public enum TipoEstatusEmpleadoEnum {

    ACTIVO(1L, "ACTIVO"),
    BAJA_TEMPORAL(2L, "BAJA TEMPORAL"),
    BAJA_DEFINITIVA(3L, "BAJA DEFINITIVA"),
    INCAPACIDAD(4L, "INCAPACIDAD"),
    VACACIONES(5L, "VACACIONES");
    
    /**
     * ID_ESTATUS
     */
    private final long id;

    /**
     * DESCRIPCION__ESTATUS
     */
    private final String descripcion;

    private TipoEstatusEmpleadoEnum(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
