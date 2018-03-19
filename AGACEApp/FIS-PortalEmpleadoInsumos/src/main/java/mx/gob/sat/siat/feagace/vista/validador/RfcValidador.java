package mx.gob.sat.siat.feagace.vista.validador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import mx.gob.sat.siat.feagace.vista.util.Expresiones;

@FacesValidator("rfcValidator")
public class RfcValidador implements Validator {
    
    private static final String INFO = "INFO";
    
    private static final String INFO_FORMATO = "Formato: PM=XXXaammddHOMOCLAVE, PF=XXXXaammddHOMOCLAVE";
    
    /**
     * Class constructor.
     */
    public RfcValidador() {
    }

    public void validaLongitud(String sRFC) {

        if (sRFC == null || (sRFC.length() != Expresiones.RFCM_LONGITUD && sRFC.length() != Expresiones.RFC_LONGITUD)) {
            throw new ValidatorException(this.getMessage(INFO, 
                                                         "El RFC debe tener 12 ó 13 posiciones según sea el caso"));
        }
    }

    public void validaRFCFisica(String sRFC) {
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(Expresiones.RFC_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            throw new ValidatorException(this.getMessage(INFO, INFO_FORMATO));
        } else {
            pattern = Pattern.compile(Expresiones.RFC_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                throw new ValidatorException(this.getMessage(INFO,
                                                             "Formato correcto a\u00f1o[aa]-mes[mm]-d\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos"));
            } else {
                pattern = Pattern.compile(Expresiones.RFC_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    throw new ValidatorException(this.getMessage(INFO, 
                                                                 "El RFC no debe contener caracteres especiales"));
                } else {
                    pattern = Pattern.compile(Expresiones.RFC_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        throw new ValidatorException(this.getMessage(INFO, 
                                                                    INFO_FORMATO));
                    }
                }
            }
        }
    }


    public void validaRFCMoral(String sRFC) {
        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(Expresiones.RFCM_PATTERN_1BLOQUE);
        matcher = pattern.matcher(sRFC);
        if (!matcher.matches()) {
            throw new ValidatorException(this.getMessage(INFO, INFO_FORMATO));
        } else {
            pattern = Pattern.compile(Expresiones.RFCM_PATTERN_2BLOQUE);
            matcher = pattern.matcher(sRFC);
            if (!matcher.matches()) {
                throw new ValidatorException(this.getMessage(INFO,
                                                             "Formato correcto a\u00f1o[aa]-mes[mm]-d\u00eda[dd] deben ser v\u00e1lidos y num\u00e9ricos"));
            } else {
                pattern = Pattern.compile(Expresiones.RFCM_PATTERN_3BLOQUE);
                matcher = pattern.matcher(sRFC);
                if (!matcher.matches()) {
                    throw new ValidatorException(this.getMessage(INFO, 
                                                                 "El RFC no debe contener caracteres especiales"));
                } else {
                    pattern = Pattern.compile(Expresiones.RFCM_PATTERN_4BLOQUE);
                    matcher = pattern.matcher(sRFC);
                    if (!matcher.matches()) {
                        throw new ValidatorException(this.getMessage(INFO, 
                                                                    INFO_FORMATO));
                    }
                }
            }
        }
    }

    /**
     * Procesa por medio de filtros (expresiones regulares) la entrada de datos que se obtiene
     * de la vista, valida que el texto ingresado tenga formato de RFC, en caso contrario
     * genera un mensaje de error a nivel de pantalla.
     *
     * @param  facesContext el contexto de JSF
     * @param  uiComponent el objeto de componentes de JSF
     * @param  value objeto con propiedad de la vista
     * @return void
     */
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) {
        String sRFC = (String)value;
        sRFC = sRFC.toUpperCase();

        validaLongitud(sRFC);
        if (sRFC.length() == Expresiones.RFC_LONGITUD) {
            validaRFCFisica(sRFC);
        } else if (sRFC.length() == Expresiones.RFCM_LONGITUD) {
            validaRFCMoral(sRFC);
        }
    }
    
    private FacesMessage getMessage(final String tipo, final String error) {
        FacesMessage msg = new FacesMessage(tipo, error);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }
}
