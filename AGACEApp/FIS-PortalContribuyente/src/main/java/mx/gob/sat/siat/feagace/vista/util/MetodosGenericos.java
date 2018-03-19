/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.vista.util;

import javax.faces.context.FacesContext;

/**
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public final class MetodosGenericos {

    private MetodosGenericos() {
        super();
    }

    /**
     * Metodo getParametro Regresa un valor obtenido con un f:param
     *
     * @param parametro
     */
    public static Object getParametro(final String parametro) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(parametro);
    }

    /**
     * Metodo acentuar Regresa el texto con acentuacion en Codigo ASCII
     *
     * @param texto
     */
    public static String acentuar(final String texto) {
        return texto.replace("á", "&aacute;").replace("é", "&eacute;").replace("í", "&iacute;").replace("ó", "&oacute;").replace("ú", "&uacute;").replace("Á",
                "&Aacute;").replace("É",
                        "&Eacute;").replace("Í",
                        "&Iacute;").replace("Ó",
                        "&Oacute;").replace("Ú",
                        "&Uacute;").replace("ñ",
                        "&ntilde;").replace("Ñ",
                        "&Ntilde;");
    }
}
