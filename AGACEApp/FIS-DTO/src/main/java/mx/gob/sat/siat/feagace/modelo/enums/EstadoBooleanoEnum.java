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
public enum EstadoBooleanoEnum {

    TRUE(1, true),
    FALSE(0, false);

    private final int estado;
    private final boolean value;

    private EstadoBooleanoEnum(int estado, boolean value) {
        this.estado = estado;
        this.value = value;
    }

    public int getEstado() {
        return estado;
    }

    public boolean isValue() {
        return value;
    }

}
