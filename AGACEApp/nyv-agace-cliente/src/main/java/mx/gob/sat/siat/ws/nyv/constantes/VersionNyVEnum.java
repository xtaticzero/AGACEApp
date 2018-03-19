/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.ws.nyv.constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum VersionNyVEnum {

    FASE_I(1, "Fase I"),
    FASE_II(2, "Fase II"),
    MOCK(3, "MockService");

    private final int idVersion;
    private final String descripcion;

    private VersionNyVEnum(int idModulo, String descripcion) {
        this.idVersion = idModulo;
        this.descripcion = descripcion;
    }

    public int getIdVersion() {
        return idVersion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
