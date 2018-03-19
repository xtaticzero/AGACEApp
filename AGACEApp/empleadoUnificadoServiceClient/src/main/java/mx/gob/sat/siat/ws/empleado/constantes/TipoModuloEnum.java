/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.empleado.constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum TipoModuloEnum {

    AGACE(32, "AGACE"),
    AGAFF(33, "AGAFF");

    private final int idModulo;
    private final String descripcion;

    private TipoModuloEnum(int idModulo, String descripcion) {
        this.idModulo = idModulo;
        this.descripcion = descripcion;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
