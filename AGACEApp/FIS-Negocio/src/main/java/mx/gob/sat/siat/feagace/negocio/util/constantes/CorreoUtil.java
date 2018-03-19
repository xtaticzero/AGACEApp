/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT).
 */

package mx.gob.sat.siat.feagace.negocio.util.constantes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.modelo.util.ConstantesError;
import mx.gob.sat.siat.feagace.negocio.exception.CorreoException;
import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;
import mx.gob.sat.siat.feagace.negocio.util.Propiedades;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.Common;
import mx.gob.sat.siat.feagace.negocio.util.correo.campos.ReportType;

import org.apache.log4j.Logger;


/**
 * Utileria para enviar notificaciones via correo electr�nico.
 *
 * @author Alfredo Ramirez - 749972
 * @version 1.0
 *
 */
public class CorreoUtil {

    private static final String CONTENT_ID = "Content-ID";
    private Map<String, String> data;
    private ReportType reportType;
    
    private Logger logger = Logger.getLogger(CorreoUtil.class);

    private static final String MAIL_SMTP_HOST = Propiedades.get("mail.smtp.host");
    private static final int MAIL_SMTP_PORT = Integer.parseInt(Propiedades.get("mail.smtp.port"));
    private static final String MAIL_SMTP_USER = Propiedades.get("mail.smtp.user");
    private static final String MAIL_TRANSPORT_PROTOCOL = Propiedades.get("mail.transport.protocol");
    private static final String MAIL_SMTP_SSL_ENABLE = Propiedades.get("mail.smtp.ssl.enable");
    private static final String MAIL_DEBUG = Propiedades.get("mail.debug");
    private static final String MAIL_SMTP_MAIL_SENDER = Propiedades.get("mail.smtp.mail.sender");
    private static final String MAIL_SMTP_AUTH = Propiedades.get("mail.smtp.auth");
    private static final String MAIL_SMTP_STARTTLS_ENABLE = Propiedades.get("mail.smtp.starttls.enable");
    private static final String MAIL_PASSWORD = Propiedades.get("mail.smtp.password");


    /**Contructor que recibe s&oacute;lo el tipo de reporte a generarse, en caso de que no sea seteado el tipo
     * de reporte, este constructor lanzar&aacute; una excepci&oacute;n de tipo IllegalArgumentException.
     * <br/>
     * S&iacute; se encuentra en la plantilla del reporte una etiqueta de la siguiente manera:
     * <br/>
     * <blockquote><pre></pre>#{etiqueta}</blockquote>
     * En el objeto data, debe de existir un par de la siguiente manera <pre><"etiqueta","valor"></pre>
     * <br/>
     * <b>Nota: </b> S&iacute; el valor de la etiqueta cuenta con alg&uacute;n elemento extra&ntilde;o, se proceder&aacute;
     * a eliminar cualquier elemento que no sea a, e, i, o, u, "_".
     *
     * @param data              Datos que ser&aacute;n sustituidos por en la plantilla del reporte.
     * @param reportType        Tipo de reporte que se generar&aacute;.
     */
    public CorreoUtil(Map<String, String> data, ReportType reportType) {

        if (reportType == null) {
            throw new IllegalArgumentException(ConstantesError.ERROR_ESPECIFICAR_TIPO_REPORTE);
        } else {
            this.reportType = reportType;
        }

        if (data == null) {
            this.data = new HashMap<String, String>();
        } else {
            this.data = data;
        }
    }


    public void sendNotificacion() throws DocumentoException, CorreoException {
        Properties properties = getProperties();
        Session session = getSession(properties);
        Address[] addresses;
        try {
            addresses = getAddress();
        } catch (AddressException e) {
            throw new CorreoException(ConstantesError.ERROR_DIRECCIONES, e);
        }
        String content;
        String header;
        String footer;

        try {
            header = getReportHeader();
            content = getReportContent(reportType);
            footer = getReportFooter();

            content = header + content + footer;
        } catch (UnsupportedEncodingException e) {
            throw new DocumentoException(ConstantesError.ERROR_CODIFICACION_DOCUMENTO, e);
        } catch (IOException e) {
            throw new DocumentoException(ConstantesError.ERROR_PLANTILLAS_NOTIFICACION, e);
        }

        content = proccessReport(content, data);


        try {
            sendMessage(session, data, properties, addresses, content);
        } catch (MessagingException e) {
            throw new CorreoException(ConstantesError.ERROR_ENVIAR_CORREO, e);
        } catch (IOException e) {
            throw new CorreoException(ConstantesError.ERROR_COMUNICACION, e);
        }
    }

    private Properties getProperties() {


        Properties properties = new Properties();
        properties.put(Constantes.MAIL_SMTP_HOST, MAIL_SMTP_HOST);
        properties.put(Constantes.MAIL_SMTP_PORT, MAIL_SMTP_PORT);

        properties.put(Constantes.MAIL_SMTP_USER, MAIL_SMTP_USER);
        properties.put(Constantes.MAIL_TRANSPORT_PROT, MAIL_TRANSPORT_PROTOCOL);
        properties.put(Constantes.MAIL_SMTP_SSL_ENABLE, MAIL_SMTP_SSL_ENABLE);
        properties.put(Constantes.MAIL_DEBUG, MAIL_DEBUG);

        properties.put(Constantes.MAIL_SMTP_MAIL_SENDER, MAIL_SMTP_MAIL_SENDER);
        properties.put(Constantes.MAIL_SMTP_AUTH, MAIL_SMTP_AUTH);
        properties.put(Constantes.MAIL_SMTP_STARTTLS_ENABLE, MAIL_SMTP_STARTTLS_ENABLE);
        properties.put(Constantes.MAIL_PASSWORD, MAIL_PASSWORD);

        return properties;
    }


    private Session getSession(Properties properties) {
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MAIL_SMTP_USER, MAIL_PASSWORD);
                }
            });
        return session;
    }

    private Address[] getAddress() throws AddressException {
        Address[] addresses = null;

        addresses = new Address[] { new InternetAddress("opelayoa@gmail.com") };
        return addresses;

    }

    private String getReportContent(ReportType reportType) throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        String linea;
        BufferedReader br;
        br =
 new BufferedReader(new InputStreamReader(new FileInputStream("c:\\siat\\fece\\correos\\plantillas\\" + reportType.getName()),
                                          "UTF-8"));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    private String getReportHeader() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        String linea;
        BufferedReader br;
        br =
 new BufferedReader(new InputStreamReader(new FileInputStream("c:\\siat\\fece\\correos\\plantillas\\" + "layout\\header.html"),
                                          "UTF-8"));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    private String getReportFooter() throws IOException {
        StringBuilder stringBuilder = new StringBuilder("");
        String linea;
        BufferedReader br;
        br =
 new BufferedReader(new InputStreamReader(new FileInputStream("c:\\siat\\fece\\correos\\plantillas\\" + "layout\\footer.html"),
                                          "UTF-8"));

        while ((linea = br.readLine()) != null) {
            stringBuilder.append(linea);
        }
        br.close();
        return stringBuilder.toString();
    }

    private String proccessReport(String content, Map<String, String> data) {
        Iterator iterator = data.entrySet().iterator();
        String contenido = content;
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            logger.info(content.indexOf(new StringBuilder("<%").append(pair.getKey().toString()).append("%>").toString()));
            contenido = contenido.replace("<%" + pair.getKey().toString() + "%>", pair.getValue().toString());
        }

        return contenido;
    }

    private void sendMessage(Session session, Map<String, String> data, Properties properties, Address[] addresses,
                             String content) throws MessagingException, IOException {
        Message mensaje = new MimeMessage(session);

        /**
         * Se rellenan los atributos y el contenido
         * Asunto
         */
        mensaje.setSubject(data.get(Common.SUBJECT.toString()));
        /** Emisor del mensaje */
        mensaje.setFrom(new InternetAddress(properties.get(Constantes.MAIL_SMTP_MAIL_SENDER).toString()));
        /** Receptor de mensaje */
        /**mensaje.addRecipient(Message.RecipientType.TO,
         * new InternetAddress((String)props.get(Constantes.MAIL_REMITENTE)));*/
        mensaje.addRecipients(Message.RecipientType.TO, addresses);
        /** Fecha de envio */
        mensaje.setSentDate(new Date());

        /** Crear un Multipart de tipo multipart/related */
        Multipart multipart = new MimeMultipart("related");

        /**Rellenar el MimeBodyPart con el fichero e indicar que es un fichero HTML */
        BodyPart texto = new MimeBodyPart();
        texto.setContent(content, "text/html; charset=UTF-8");
        multipart.addBodyPart(texto);

        /** Procesar la imagen */
        MimeBodyPart imagenSHCP = new MimeBodyPart();
        imagenSHCP.attachFile("c:\\siat\\fece\\correos\\imagenes\\logoSHCP.png");
        imagenSHCP.setHeader(CONTENT_ID, "<logoSHCP>");
        multipart.addBodyPart(imagenSHCP);

        MimeBodyPart imagenSAT = new MimeBodyPart();
        imagenSAT.attachFile("c:\\siat\\fece\\correos\\imagenes\\logoSAT.jpg");
        imagenSAT.setHeader(CONTENT_ID, "<logoSAT>");
        multipart.addBodyPart(imagenSAT);

        MimeBodyPart imagenSAT1 = new MimeBodyPart();
        imagenSAT1.attachFile("c:\\siat\\fece\\correos\\imagenes\\SAT.gif");
        imagenSAT1.setHeader(CONTENT_ID, "<logoSATVerde>");
        multipart.addBodyPart(imagenSAT1);

        mensaje.setContent(multipart);

        Transport.send(mensaje);
    }
}
