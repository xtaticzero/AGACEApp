package mx.gob.sat.siat.feagace.negocio.helper;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.negocio.util.ExpresionesUtil;

import org.springframework.stereotype.Component;

@Component
public class ValidaRFCCargasMasivasHelper implements Serializable {

    private static final long serialVersionUID = -6990943345731681711L;

    private static final String INFO_FORMATO = "Formato: PM=XXXaammddHOMOCLAVE, PF=XXXXaammddHOMOCLAVE";

    public void validaPersonaMoralFisica(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        if (cargaMasivaPropuestasDTO.getRfcContribuyente().length() == ExpresionesUtil.RFC_LONGITUD) {
            validaRFCFisica(cargaMasivaPropuestasDTO);
        } else if (cargaMasivaPropuestasDTO.getRfcContribuyente().length() == ExpresionesUtil.RFCM_LONGITUD) {
            validaRFCMoral(cargaMasivaPropuestasDTO);
        }
    }

    public CargaMasivaPropuestasDTO validaLongitud(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {

        if (cargaMasivaPropuestasDTO.getRfcContribuyente().length() != ExpresionesUtil.RFCM_LONGITUD
                && cargaMasivaPropuestasDTO.getRfcContribuyente().length() != ExpresionesUtil.RFC_LONGITUD) {
            cargaMasivaPropuestasDTO.setDescripcionError("El RFC debe tener 12 \u00F3 13 posiciones seg\u00FAn sea el caso");
            cargaMasivaPropuestasDTO.setValida(false);
        }
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaRFCFisica(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        Pattern pattern;
        Matcher matcher;
        String sRFC = cargaMasivaPropuestasDTO.getRfcContribuyente().toUpperCase();
        pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            cargaMasivaPropuestasDTO.setDescripcionError(INFO_FORMATO);
            cargaMasivaPropuestasDTO.setValida(false);
        } else {
            pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                cargaMasivaPropuestasDTO.setDescripcionError("A\u00f1o[aa]-Mes[mm]-D\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos");
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    cargaMasivaPropuestasDTO.setDescripcionError("El RFC no debe contener caracteres especiales");
                    cargaMasivaPropuestasDTO.setValida(false);
                } else {
                    pattern = Pattern.compile(ExpresionesUtil.RFC_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        cargaMasivaPropuestasDTO.setDescripcionError(INFO_FORMATO);
                        cargaMasivaPropuestasDTO.setValida(false);
                    }
                }
            }
        }
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaRFCMoral(CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        Pattern pattern;
        Matcher matcher;
        String sRFC = cargaMasivaPropuestasDTO.getRfcContribuyente().toUpperCase();
        pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            cargaMasivaPropuestasDTO.setDescripcionError(INFO_FORMATO);
            cargaMasivaPropuestasDTO.setValida(false);
        } else {
            pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                cargaMasivaPropuestasDTO.setDescripcionError("A\u00f1o[aa]-Mes[mm]-D\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos");
                cargaMasivaPropuestasDTO.setValida(false);
            } else {
                pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    cargaMasivaPropuestasDTO.setDescripcionError("El RFC no debe contener caracteres especiales");
                    cargaMasivaPropuestasDTO.setValida(false);
                } else {
                    pattern = Pattern.compile(ExpresionesUtil.RFCM_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        cargaMasivaPropuestasDTO.setDescripcionError(INFO_FORMATO);
                        cargaMasivaPropuestasDTO.setValida(false);
                    }
                }
            }
        }
        return cargaMasivaPropuestasDTO;
    }

}
