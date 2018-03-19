/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.constantes;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public enum FormatosFechasEnum {
    FORMATO_DIAGONALES_DDMMYYYY("dd/MM/yyyy"),
    FORMATO_DIAGONAL_DDMYYYY("dd/M/yyyy"),
    FORMATO_24H_HHMM("HH:mm"),
    FORMATO_CADENA_SIN_ESPACIOS_DDMMYYYYHHMM("ddMMyyyyHHmm"),
    FORMATO_FECHA_24H_DIAGONALES("dd/MM/yyyy HH:mm");
    
    private final String formato;

    private FormatosFechasEnum(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }
    
}
