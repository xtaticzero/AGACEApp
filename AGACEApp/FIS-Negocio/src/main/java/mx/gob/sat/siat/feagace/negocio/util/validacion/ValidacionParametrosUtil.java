/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util.validacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.gob.sat.siat.feagace.negocio.util.ExpresionesUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ValidacionParametrosUtil {

    public static boolean seCumpleAlgunaCondicion(boolean[] condiciones) {
        boolean respuesta = false;
        if (condiciones != null && condiciones.length > 0) {

            for (boolean condicion : condiciones) {
                respuesta = respuesta || condicion;
                if (respuesta) {
                    return respuesta;
                }
            }

            return respuesta;
        } else {
            return false;
        }
    }

    public static boolean seCumplenTodasLasCondicion(boolean[] condiciones) {
        boolean respuesta = true;
        if (condiciones != null && condiciones.length > 0) {

            for (boolean condicion : condiciones) {
                respuesta = respuesta && condicion;
                if (!respuesta) {
                    return respuesta;
                }
            }

            return respuesta;
        } else {
            return false;
        }
    }

    public static boolean esAlfanumerico(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        Pattern pat = Pattern.compile(ExpresionesUtil.ALFANUMERIRO_FOLIO_PATTERN);
        Matcher mat = pat.matcher(cadena);
        return mat.matches();
    }
}
