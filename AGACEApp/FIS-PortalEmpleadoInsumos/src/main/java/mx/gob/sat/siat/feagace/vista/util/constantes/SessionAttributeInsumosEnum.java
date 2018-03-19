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
public enum SessionAttributeInsumosEnum {

    MSG_ERROR_SESSION("mensaje");

    private final String attributeName;

    private SessionAttributeInsumosEnum(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

}
