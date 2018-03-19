package mx.gob.sat.siat.ws.nyv.client.impl;

import mx.gob.sat.siat.ws.nyv.client.repository.WSNyVV2Repository;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.2.4-b01 Generated
 * source version: 2.2
 *
 */
@WebServiceClient(name = "WSNyVSistema", targetNamespace = "http://webservice.sistema.nyv.siat.sat.gob.mx/", wsdlLocation = "https://peagrdev.siat.sat.gob.mx/ws/nyvxsistemaFaseII/WSNyVSistema?wsdl")
public class WSNyVV2Sistema
        extends Service {

    private final static URL WSNYVSISTEMA_WSDL_LOCATION;
    private final static WebServiceException WSNYVSISTEMA_EXCEPTION;
    private final static QName WSNYVSISTEMA_QNAME = new QName("http://webservice.sistema.nyv.siat.sat.gob.mx/", "WSNyVSistema");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://peagrdev.siat.sat.gob.mx/ws/nyvxsistemaFaseII/WSNyVSistema?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSNYVSISTEMA_WSDL_LOCATION = url;
        WSNYVSISTEMA_EXCEPTION = e;
    }

    public WSNyVV2Sistema() {
        super(__getWsdlLocation(), WSNYVSISTEMA_QNAME);
    }

    public WSNyVV2Sistema(URL wsdlLocation) {
        super(wsdlLocation, WSNYVSISTEMA_QNAME);
    }

    public WSNyVV2Sistema(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return returns WSNyVV2Repository
     */
    @WebEndpoint(name = "WSNyVSistemaPort")
    public WSNyVV2Repository getWSNyVSistemaPort() {
        return super.getPort(new QName("http://webservice.sistema.nyv.siat.sat.gob.mx/", "WSNyVSistemaPort"), WSNyVV2Repository.class);
    }

    /**
     *
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to
     * configure on the proxy. Supported features not in the
     * <code>features</code> parameter will have their default values.
     * @return returns WSNyVV2Repository
     */
    @WebEndpoint(name = "WSNyVSistemaPort")
    public WSNyVV2Repository getWSNyVSistemaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservice.sistema.nyv.siat.sat.gob.mx/", "WSNyVSistemaPort"), WSNyVV2Repository.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSNYVSISTEMA_EXCEPTION != null) {
            throw WSNYVSISTEMA_EXCEPTION;
        }
        return WSNYVSISTEMA_WSDL_LOCATION;
    }

}
