package mx.gob.sat.siat.ws.empleado.client.log;

import java.io.ByteArrayOutputStream;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class ServiceLogHandler implements SOAPHandler<SOAPMessageContext> {

    protected final Logger logger = Logger.getLogger(getClass());

    @Override
    public boolean handleMessage(final SOAPMessageContext context) {
        final SOAPMessage msg = context.getMessage();
        final boolean request = ((Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY));
        if (request) {
            // This is a request message.
            logMessage(msg);
        } else {
            // This is the response message
            logMessage(msg);
        }
        return true;
    }

    @Override
    public boolean handleFault(final SOAPMessageContext context) {
        logMessage(context.getMessage());
        return true;
    }

    private void logMessage(final SOAPMessage msg) {
        try {
            // Write the message to the output stream
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            msg.writeTo(baos);
            logger.debug(baos.toString());
            baos.close();
        } catch (final Exception e) {
            logger.error("Caught exception: " + e.getMessage(), e);
        }
    }

    @Override
    public void close(final MessageContext context) {
        // Not required for logging
    }

    @Override
    public Set<QName> getHeaders() {
        // Not required for logging
        return null;
    }
}
