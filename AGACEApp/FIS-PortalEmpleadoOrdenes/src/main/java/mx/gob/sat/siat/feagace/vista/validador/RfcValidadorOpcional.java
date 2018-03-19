package mx.gob.sat.siat.feagace.vista.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.sat.siat.feagace.vista.util.Expresiones;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class RfcValidadorOpcional {

    /**
     * Class constructor.
     */
    public RfcValidadorOpcional() {
    }


    public boolean validaLongitud(final String sRFC, final String componente) {

        if ((sRFC.length() > 0 && sRFC.length() != Expresiones.RFCM_LONGITUD) && sRFC.length() != Expresiones.RFC_LONGITUD) {
            FacesUtil.addErrorMessage(componente, "*El RFC debe tener 13 ó 12 posiciones según sea el caso");
            return false;
        }
        return true;
    }


    public boolean validaRFCFisica(String sRFC, String componente) {

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(Expresiones.RFC_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            FacesUtil.addErrorMessage(componente, "*Las primeras 4 posiciones deben ser letras");
            return false;

        } else {
            pattern = Pattern.compile(Expresiones.RFC_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                FacesUtil.addErrorMessage(componente, "Formato correcto a\u00f1o[aa]-mes[mm]-d\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos");
                return false;
            } else {
                pattern = Pattern.compile(Expresiones.RFC_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    FacesUtil.addErrorMessage(componente, "*El RFC no debe contener caracteres especiales");
                    return false;

                } else {
                    pattern = Pattern.compile(Expresiones.RFC_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        FacesUtil.addErrorMessage(componente, "*El último digito debe ser numérico o la letra A");
                        return false;

                    }
                }

            }

        }
        return true;

    }


    public boolean validaRFCMoral(String sRFC, String componente) {

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(Expresiones.RFCM_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            FacesUtil.addErrorMessage(componente, "Las primeras 3 posiciones deben ser letras");
            return false;

        } else {
            pattern = Pattern.compile(Expresiones.RFCM_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                FacesUtil.addErrorMessage(componente, "Formato correcto a\u00f1o[aa]-mes[mm]-d\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos");
                return false;
            } else {
                pattern = Pattern.compile(Expresiones.RFCM_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    FacesUtil.addErrorMessage(componente, "El RFC no debe contener caracteres especiales");
                    return false;

                } else {
                    pattern = Pattern.compile(Expresiones.RFCM_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        FacesUtil.addErrorMessage(componente, "El último digito debe ser numérico o la letra A");
                        return false;

                    }
                }

            }

        }
        return true;

    }
}
