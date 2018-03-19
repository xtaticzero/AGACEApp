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
public enum ClvSubModulosAgace {

    INSUMOS(1002, "Insumos"),
    PROPUESTAS(1003, "Propuestas"),
    SEGUIMIENTO(1004, "Seguimiento"),
    REPORTES(1005, "Reportes");

    private final int idClvSubModulo;
    private final String descripcionClv;

    private ClvSubModulosAgace(int idClvSubModulo, String descripcionClv) {
        this.idClvSubModulo = idClvSubModulo;
        this.descripcionClv = descripcionClv;
    }

    public int getIdClvSubModulo() {
        return idClvSubModulo;
    }

    public String getDescripcionClv() {
        return descripcionClv;
    }
}
