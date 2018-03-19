package mx.gob.sat.siat.ws.nyv.v1.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for responseConsulta complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseConsulta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioActo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="respuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaSolicitudDiligencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaSms" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaEmail" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaNotificacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaNotificacionEfectiva" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="urlAvisoNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urlAcuseNotificacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensajeRespuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bitacora" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}bitacoraVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsulta", propOrder = {
    "folioActo",
    "respuesta",
    "estatus",
    "fechaSolicitudDiligencia",
    "fechaSms",
    "fechaEmail",
    "fechaNotificacion",
    "fechaNotificacionEfectiva",
    "urlAvisoNotificacion",
    "urlAcuseNotificacion",
    "codigoRespuesta",
    "mensajeRespuesta",
    "bitacora"
})
public class ResponseConsulta {

    protected String folioActo;
    protected String respuesta;
    protected String estatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSolicitudDiligencia;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSms;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaEmail;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNotificacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaNotificacionEfectiva;
    protected String urlAvisoNotificacion;
    protected String urlAcuseNotificacion;
    protected String codigoRespuesta;
    protected String mensajeRespuesta;
    protected List<BitacoraVOXml> bitacora;

    /**
     * Gets the value of the folioActo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFolioActo() {
        return folioActo;
    }

    /**
     * Sets the value of the folioActo property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFolioActo(String value) {
        this.folioActo = value;
    }

    /**
     * Gets the value of the respuesta property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Sets the value of the respuesta property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRespuesta(String value) {
        this.respuesta = value;
    }

    /**
     * Gets the value of the estatus property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Sets the value of the estatus property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEstatus(String value) {
        this.estatus = value;
    }

    /**
     * Gets the value of the fechaSolicitudDiligencia property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaSolicitudDiligencia() {
        return fechaSolicitudDiligencia;
    }

    /**
     * Sets the value of the fechaSolicitudDiligencia property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaSolicitudDiligencia(XMLGregorianCalendar value) {
        this.fechaSolicitudDiligencia = value;
    }

    /**
     * Gets the value of the fechaSms property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaSms() {
        return fechaSms;
    }

    /**
     * Sets the value of the fechaSms property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaSms(XMLGregorianCalendar value) {
        this.fechaSms = value;
    }

    /**
     * Gets the value of the fechaEmail property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaEmail() {
        return fechaEmail;
    }

    /**
     * Sets the value of the fechaEmail property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaEmail(XMLGregorianCalendar value) {
        this.fechaEmail = value;
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
     * Gets the value of the fechaNotificacionEfectiva property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaNotificacionEfectiva() {
        return fechaNotificacionEfectiva;
    }

    /**
     * Sets the value of the fechaNotificacionEfectiva property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaNotificacionEfectiva(XMLGregorianCalendar value) {
        this.fechaNotificacionEfectiva = value;
    }

    /**
     * Gets the value of the urlAvisoNotificacion property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrlAvisoNotificacion() {
        return urlAvisoNotificacion;
    }

    /**
     * Sets the value of the urlAvisoNotificacion property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUrlAvisoNotificacion(String value) {
        this.urlAvisoNotificacion = value;
    }

    /**
     * Gets the value of the urlAcuseNotificacion property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrlAcuseNotificacion() {
        return urlAcuseNotificacion;
    }

    /**
     * Sets the value of the urlAcuseNotificacion property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUrlAcuseNotificacion(String value) {
        this.urlAcuseNotificacion = value;
    }

    /**
     * Gets the value of the codigoRespuesta property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Sets the value of the codigoRespuesta property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCodigoRespuesta(String value) {
        this.codigoRespuesta = value;
    }

    /**
     * Gets the value of the mensajeRespuesta property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    /**
     * Sets the value of the mensajeRespuesta property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMensajeRespuesta(String value) {
        this.mensajeRespuesta = value;
    }

    /**
     * Gets the value of the bitacora property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the bitacora property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBitacora().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BitacoraVOXml }
     *
     *
     */
    public List<BitacoraVOXml> getBitacora() {
        if (bitacora == null) {
            bitacora = new ArrayList<BitacoraVOXml>();
        }
        return this.bitacora;
    }

}
