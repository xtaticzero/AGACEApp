/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT) 
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de 
 * la Servicio de Administracion Tributaria (SAT) 
 * Por lo cual no puede ser reproducido, distribuido o 
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT) 
 */

package mx.gob.sat.siat.feagace.vista.util;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

/**
 * Clase encargada de cargar las propiedades de la aplicacion en el System.
 * 
 * @author Ignacio Sandoval (i.sandovalhiguera@tcs.com)
 * @version 1.0
 * 
 */
public class AgaceConfigListener implements ServletContextListener {

    /**
     * Logger de la clase.
     */
    public static final Logger LOG = Logger.getLogger(AgaceConfigListener.class);

    /**
     * Servlet context.
     */
    private ServletContext servletContext;
    
    private static final String AGACE_PROPERTIES = "AGACE_APP";
    private static final String FILE = "file:";

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        servletContext = event.getServletContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        servletContext = event.getServletContext();
        String locationApp = this.getLocation(AGACE_PROPERTIES);      

        try {

            // Seteo la ruta del archivo de propiedades
            System.setProperty(AGACE_PROPERTIES, locationApp);    

            LOG.info("AGACE_PROPERTIES Location: " + System.getProperty(AGACE_PROPERTIES));
            
            // Cargo las propiedades en el context.
            Properties properties = Propiedades.loadProperties(locationApp);

            Iterator<Entry<Object, Object>> it = properties.entrySet().iterator();

            while (it.hasNext()) {
                Entry<Object, Object> entry = it.next();
                servletContext.setAttribute((String) entry.getKey(), (String) entry.getValue());
            }

        } catch (DocumentoException ex) {
            LOG.error(ex.getMessage(), ex);
            throw new IllegalArgumentException(
                    "Par�metro  inv�lido: " + ex.getMessage(), ex);
        }
    }

    /**
     * Metodo encargado de obtener la ubicacion del archivo de configuracion.
     * 
     * @param initParam Parametro del web.xml
     * @return Ubicacion del archivo de configuracion.
     */
    private String getLocation(String initParam) {

        String location = servletContext.getInitParameter(initParam);

        if (location != null) {

            try {

                // Resolve property placeholders before potentially resolving a
                // real path.
                location = ServletContextPropertyUtils.resolvePlaceholders(location, servletContext);

                boolean isUrl = ResourceUtils.isUrl(location);

                // Leave a URL (e.g. "classpath:" or "file:") as-is.
                if (!isUrl) {
                    // Consider a plain file path as relative to the web
                    // application root directory.
                    location = WebUtils.getRealPath(servletContext, location);
                } else if (location.contains(FILE)) {
                    location = location.replace(FILE, "");
                }

            } catch (FileNotFoundException ex) {
                LOG.error(ex.getMessage(), ex);
                throw new IllegalArgumentException("Par�metro ' " + initParam + "' inv�lido: "
                        + ex.getMessage(), ex);
            }
        } else {
            throw new IllegalArgumentException("El par�metro ' " + initParam + "' es NULL.");
        }

        return location;
    }
}
