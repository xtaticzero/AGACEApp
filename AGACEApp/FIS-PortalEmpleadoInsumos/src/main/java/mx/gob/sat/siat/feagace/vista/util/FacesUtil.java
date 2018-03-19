/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.vista.util;

import java.text.MessageFormat;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Utileria para agregar mensajes en pantalla.
 * @author Ra&uacute;l Corona Zurita
 */
public final class FacesUtil {
    
    private static final Logger LOGGER = Logger.getLogger(FacesUtil.class);
    
    private FacesUtil(){
        }

    /**
     * Agrega un mensaje de error sin detalle.
     * @param clientId, identificador del componente.
     * @param msg mensaje a mostrar.
     */
    public static void addWarningMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null));
    }
    
    /**
     * Agrega un mensaje de error sin detalle.
     * @param clientId, identificador del componente.
     * @param msg mensaje a mostrar.
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    /**
     * Agrega un mensaje de informaci&oacute;n sin detalle.
     * @param clientId identificador del componente.
     * @param msg mensaje a mostrar.
     */
    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    
    /**
     * Agrega un mensaje de error con detalle.
     * @param clientId identificador del componente
     * @param msg mensaje a msotrar.
     * @param detalleMsg detalle del mensaje.
     */
    public static void addErrorMessage(String clientId, String msg, String detalleMsg) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, detalleMsg));
    }

    /**
     * Obtiene un mensaje del archivo de propiedades de una clave enviada.
     * @param key clave a buscar.
     * @param params uno varios objetos que determinan el formato del mensaje.
     * @return String con el mensaje obtenido.
     */
    public static String getMessageResourceString(String key, Object... params) {
        String value = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msj");

        value = formatMessage(bundle, key, params);
        return value;
    }
    
    /**
     * Agrega un mensaje de informaci&oacute;n con detalle.
     * @param clientId identificador del componente.
     * @param msg mensaje a mostrar.
     * @param detalle detalle del mensaje.
     */
    public static void addInfoMessage(String clientId, String msg, String detalle) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detalle));
    }

    private static String formatMessage(ResourceBundle bundle, String key, Object... params) {
        String text = null;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
            LOGGER.error("Error en ", e);
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        }
        return text;
    }
}
