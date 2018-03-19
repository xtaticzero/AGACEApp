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
public enum EstadoBooleanodeRegistroEnum {

    ACTIVO(1, '1', "Activo"),
    INACTIVO(0, '0', "Inactivo");

    private final Character estado;
    private final int value;
    private final String descripcion;

    private EstadoBooleanodeRegistroEnum(int value, Character estado, String descripcion) {
        this.estado = estado;
        this.value = value;
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public int getValue() {
        return value;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
