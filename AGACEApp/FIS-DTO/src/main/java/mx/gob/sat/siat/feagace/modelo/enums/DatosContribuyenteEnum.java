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
public enum DatosContribuyenteEnum {
    DATOS_CONTRIBUYENTE_ACTUALIZADOS("DATOS_CONTRIBUYENTE_ACTUALIZADOS"),
    DATOS_CONTRIBUYENTE_ANTERIOR("DATOS_CONTRIBUYENTE_ANTERIOR");
    
    private final String llaveMap;

    private DatosContribuyenteEnum(String llaveMap) {
        this.llaveMap = llaveMap;
    }

    public String getLlaveMap() {
        return llaveMap;
    }
}
