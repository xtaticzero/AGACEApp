/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT)
 */

package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.util.Enumeration;
import java.util.Properties;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Clase usada para contener las propiedades de la aplicacion en memoria.
 *
 * @author JOSE_SILVA
 * @version 1.0
 */
public final class SpringPropiedadesUtil extends PropertyPlaceholderConfigurer {

    /**
     * Instancia de Log4j para SpringPropiedadesUtil.
     */
    private static final Logger LOG = Logger.getLogger(SpringPropiedadesUtil.class);

    /**
     * Prefijo que deben tener las claves de las propiedades a ser agregadas a mensajesProps.
     */
    private static final String PREFIJO_MENSAJES = "mensaje.";

    /**
     * Propiedades globales de la aplicaci�n.
     */
    private static Properties props;

    /**
     * Propiedades de mensajes globales de la aplicaci�n.
     */
    private static Properties mensajesProps;

    @SuppressWarnings(Constantes.STATIC_ACCESS)
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props){
        LOG.debug("Cargando Propiedades a la aplicaci�n");
        super.processProperties(beanFactory, props);
        this.props = props;
        mensajesProps = removerSubconjunto(props, PREFIJO_MENSAJES, true);
    }

    /**
     * Obtiene el valor de la propiedad para la clave proporcionada.
     *
     * @param clave Clave
     * @return valor
     */
    public static String getProperty(String clave) {
        return props.get(clave).toString();
    }

    /**
     * Obtiene el valor de la propiedad para la clave proporcionada
     * en el properties especificado.
     * @param properties Properties
     * @param clave Clave
     * @return valor
     */
    public static String getProperty(Properties properties, String clave) {
        return properties.get(clave).toString();
    }

    /**
     * Obtiene el valor de la propiedad para la clave, que a su vez est�
     * compuesta por otras clavesIndexadas {i}.
     *
     * @param clave Clave
     * @param clavesIndexadas Claves Indexadas
     * @return valor
     */
    public static String getProperty(String clave, String... clavesIndexadas) {
        return getPropertyDe(props, clave, clavesIndexadas);
    }

    /**
     * Obtiene el valor de la propiedad para la clave, que a su vez est�
     * compuesta por otras clavesIndexadas {i}.
     * en el properties especificado
     * @param properties Properties
     * @param clave Clave
     * @param clavesIndexadas Claves Indexadas
     * @return valor
     */
    public static String getPropertyDe(Properties properties, String clave, String... clavesIndexadas) {
        StringBuilder propiedad = new StringBuilder(properties.get(clave).toString());
        if (propiedad != null && clavesIndexadas != null) {
            StringBuilder indexSB = new StringBuilder(Constantes.PROPIEDADES_PARAMETRO_INDEXADO);
            int k = Constantes.INDICE_MENOS_UNO;
            String propI = null;
            String propIKey = null;
            for (int i = 0; i < clavesIndexadas.length; i++) {
                indexSB.replace(Constantes.INDICE_UNO, Constantes.INDICE_DOS, String.valueOf(i));
                k = propiedad.indexOf(indexSB.toString());
                if (k != Constantes.INDICE_MENOS_UNO) {
                    propIKey = clavesIndexadas[i];
                    propI = properties.get(propIKey).toString();
                    if (propI != null) {
                        propiedad.replace(k, (k + indexSB.length()), propI);
                    }
                }
            }
        }
        return propiedad.toString();
    }

    /**
     * Regresa el mapa de propiedades completo.
     *
     * @return properties
     */
    public static Properties getProperties() {
        return props;
    }

    /**
     * Regresa el mapa de propiedades de mensajes.
     * @return properties
     */
    public static Properties getMensajesProps() {
        return mensajesProps;
    }

    /**
     * Regresa un mensaje formado por el valor de todas las claves, con un
     * separador, si este es proporcionado.
     *
     * @param separador Separador
     * @param claves Claves
     * @return mensaje compuesto
     */
    public static String getMensajeCompuesto(String separador, String... claves) {
        return getMensajeCompuestoDe(props, separador, claves);
    }

    /**
     * Regresa un mensaje formado por el valor de todas las claves, con un
     * separador, si este es proporcionado.
     * en el properties especificado
     * @param properties Properties
     * @param separador Separador
     * @param claves Claves
     * @return mensaje compuesto
     */
    public static String getMensajeCompuestoDe(Properties properties, String separador, String... claves) {
        StringBuilder sb = new StringBuilder();
        if (claves != null) {
            int tamano = claves.length;
            for (int i = Constantes.INDICE_CERO; i < tamano; i++) {
                String clave = claves[i];
                if (clave != null) {
                    sb.append(getProperty(properties, clave));
                    if (i != tamano - Constantes.INDICE_UNO && separador != null) {
                        sb.append(separador);
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Remueve un subconjunto de propiedades del par�metro propiedades cuya
     * clave coincida con el prefijo, retornando el subconjunto encontrado.
     * @param propiedades Propiedades
     * @param prefijo Prefijo
     * @param conPrefijo Con Prefijo
     * @return subconjunto de propiedades
     */
    @SuppressWarnings("rawtypes")
    public static Properties removerSubconjunto(Properties propiedades, String prefijo, boolean conPrefijo) {
        Properties result = new Properties();

        // Validaci�n del prefijo
        if (prefijo == null || prefijo.length() == Constantes.INDICE_CERO) {
            return result;
        }

        // Compara las cadenas con prefix
        String prefixMatch;
        // Compara las cadenas con prefix sin punto al final
        String prefixSelf;
        if (prefijo.charAt(prefijo.length() - Constantes.INDICE_UNO) !=
            Constantes.PUNTO.charAt(Constantes.INDICE_CERO)) {
            // prefix does not end in a dot
            prefixSelf = prefijo;
            prefixMatch = prefijo + Constantes.PUNTO;
        } else {
            // prefix sin terminacion en punto, se remueve de las coincidencias exactas
            prefixSelf = prefijo.substring(Constantes.INDICE_CERO, prefijo.length() - Constantes.INDICE_UNO);
            prefixMatch = prefijo;
        }

        // Agrega todas las coincidencias en properties.
        String key;
        for (Enumeration e = propiedades.propertyNames(); e.hasMoreElements(); ) {
            key = (String)e.nextElement();

            if (conPrefijo) {
                // conserva todo prefix en el resultado, tambi�n copia todas las coincidencias
                if (key.startsWith(prefixMatch) || key.equals(prefixSelf)) {
                    result.put(key, propiedades.remove(key));
                }
            } else {
                // No copia las coincidencias
                if (key.startsWith(prefixMatch)) {
                    result.put(key.substring(prefixMatch.length()), propiedades.remove(key));
                }
            }
        }

        return result;
    }

    /**
     * Obtiene el mensaje con la clave mensajeClave
     * substituyendo los mensajes mensasjeIndexados.
     * @param mensajeClave Mensaje Clave
     * @param mensasjeIndexados Mensasje Indexados
     * @return mensaje
     */
    public static String getMensajeIndexado(String mensajeClave, String... mensasjeIndexados) {
        return getMensajeIndexadoEn(props, mensajeClave, mensasjeIndexados);
    }

    /**
     * Obtiene el mensaje del properties con la clave mensajeClave
     * substituyendo los mensajes mensasjeIndexados.
     * @param properties Propieades
     * @param mensajeClave Mensaje Clave
     * @param mensasjeIndexados Mensasjes Indexados
     * @return mensaje
     */
    public static String getMensajeIndexadoEn(Properties properties, String mensajeClave,
                                              String... mensasjeIndexados) {
        StringBuilder propiedad = new StringBuilder(properties.get(mensajeClave).toString());
        if (propiedad != null && mensasjeIndexados != null) {
            StringBuilder indexSB = new StringBuilder(Constantes.PROPIEDADES_PARAMETRO_INDEXADO);
            int k = Constantes.INDICE_MENOS_UNO;
            for (int i = 0; i < mensasjeIndexados.length; i++) {
                indexSB.replace(Constantes.INDICE_UNO, Constantes.INDICE_DOS, String.valueOf(i));
                k = propiedad.indexOf(indexSB.toString());
                if (k != Constantes.INDICE_MENOS_UNO) {
                    propiedad.replace(k, (k + indexSB.length()), mensasjeIndexados[i]);
                }
            }
        }
        return propiedad.toString();
    }

}
