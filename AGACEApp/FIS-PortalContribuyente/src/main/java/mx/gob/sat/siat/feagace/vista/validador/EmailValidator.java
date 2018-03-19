package mx.gob.sat.siat.feagace.vista.validador;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    private Pattern pattern;

    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return;
        }

        if (!pattern.matcher(value.toString()).matches()) {
            if (value.equals("")) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Campo Obligatorio"));
            } else {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
                                                              value + " no es un email valido"));
            }
        }
    }
}
