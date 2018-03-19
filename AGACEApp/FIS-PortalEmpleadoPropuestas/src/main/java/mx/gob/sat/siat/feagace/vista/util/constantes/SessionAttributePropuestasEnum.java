/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.util.constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum SessionAttributePropuestasEnum {

    MSG_ERROR_SESSION("mensaje");

    private final String attributeName;

    private SessionAttributePropuestasEnum(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

}
