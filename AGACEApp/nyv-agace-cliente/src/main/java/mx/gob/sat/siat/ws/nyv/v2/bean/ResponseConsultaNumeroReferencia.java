package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for responseConsultaNumeroReferencia complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseConsultaNumeroReferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioActoAdmin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rfcContribuyente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreContribuyente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaNotificacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="estatusNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notificadoPor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsultaNumeroReferencia", propOrder = {
    "folioActoAdmin",
    "rfcContribuyente",
    "nombreContribuyente",
    "fechaNotificacion",
    "estatusNotificacion",
    "notificadoPor"
})
public class ResponseConsultaNumeroReferencia {

    protected String folioActoAdmin;
    protected String rfcContribuyente;
    protected String nombreContribuyente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNotificacion;
    protected String estatusNotificacion;
    protected String notificadoPor;

    /**
     * Gets the value of the folioActoAdmin property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFolioActoAdmin() {
        return folioActoAdmin;
    }

    /**
     * Sets the value of the folioActoAdmin property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFolioActoAdmin(String value) {
        this.folioActoAdmin = value;
    }

    /**
     * Gets the value of the rfcContribuyente property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    /**
     * Sets the value of the rfcContribuyente property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRfcContribuyente(String value) {
        this.rfcContribuyente = value;
    }

    /**
     * Gets the value of the nombreContribuyente property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    /**
     * Sets the value of the nombreContribuyente property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNombreContribuyente(String value) {
        this.nombreContribuyente = value;
    }

    /**
     * Gets the value of the fechaNotificacion property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * Sets the value of the fechaNotificacion property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaNotificacion(XMLGregorianCalendar value) {
        this.fechaNotificacion = value;
    }

    /**
     * Gets the value of the estatusNotificacion property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEstatusNotificacion() {
        return estatusNotificacion;
    }

    /**
     * Sets the value of the estatusNotificacion property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEstatusNotificacion(String value) {
        this.estatusNotificacion = value;
    }

    /**
     * Gets the value of the notificadoPor property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNotificadoPor() {
        return notificadoPor;
    }

    /**
     * Sets the value of the notificadoPor property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNotificadoPor(String value) {
        this.notificadoPor = value;
    }

}
