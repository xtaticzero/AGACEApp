package mx.gob.sat.siat.ws.empleado.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for VacacionXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="VacacionXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NombreResolucionMiscelanea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroRegla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroResolucionModif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="d_descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="f_final" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="f_inicial" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="f_publicacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaPublicacionDof" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Vacacion", propOrder = {
    "nombreResolucionMiscelanea",
    "numeroRegla",
    "numeroResolucionModif",
    "dDescripcion",
    "fFinal",
    "fInicial",
    "fPublicacion",
    "fechaPublicacionDof"
})
public class VacacionXml {

    @XmlElementRef(name = "NombreResolucionMiscelanea", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> nombreResolucionMiscelanea;
    @XmlElementRef(name = "NumeroRegla", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> numeroRegla;
    @XmlElementRef(name = "NumeroResolucionModif", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> numeroResolucionModif;
    @XmlElementRef(name = "d_descripcion", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> dDescripcion;
    @XmlElement(name = "f_final")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fFinal;
    @XmlElement(name = "f_inicial")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fInicial;
    @XmlElement(name = "f_publicacion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fPublicacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPublicacionDof;

    /**
     * Gets the value of the nombreResolucionMiscelanea property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNombreResolucionMiscelanea() {
        return nombreResolucionMiscelanea;
    }

    /**
     * Sets the value of the nombreResolucionMiscelanea property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNombreResolucionMiscelanea(JAXBElement<String> value) {
        this.nombreResolucionMiscelanea = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the numeroRegla property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNumeroRegla() {
        return numeroRegla;
    }

    /**
     * Sets the value of the numeroRegla property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNumeroRegla(JAXBElement<String> value) {
        this.numeroRegla = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the numeroResolucionModif property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNumeroResolucionModif() {
        return numeroResolucionModif;
    }

    /**
     * Sets the value of the numeroResolucionModif property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNumeroResolucionModif(JAXBElement<String> value) {
        this.numeroResolucionModif = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the dDescripcion property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDDescripcion() {
        return dDescripcion;
    }

    /**
     * Sets the value of the dDescripcion property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDDescripcion(JAXBElement<String> value) {
        this.dDescripcion = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the fFinal property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFFinal() {
        return fFinal;
    }

    /**
     * Sets the value of the fFinal property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFFinal(XMLGregorianCalendar value) {
        this.fFinal = value;
    }

    /**
     * Gets the value of the fInicial property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFInicial() {
        return fInicial;
    }

    /**
     * Sets the value of the fInicial property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFInicial(XMLGregorianCalendar value) {
        this.fInicial = value;
    }

    /**
     * Gets the value of the fPublicacion property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFPublicacion() {
        return fPublicacion;
    }

    /**
     * Sets the value of the fPublicacion property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFPublicacion(XMLGregorianCalendar value) {
        this.fPublicacion = value;
    }

    /**
     * Gets the value of the fechaPublicacionDof property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaPublicacionDof() {
        return fechaPublicacionDof;
    }

    /**
     * Sets the value of the fechaPublicacionDof property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaPublicacionDof(XMLGregorianCalendar value) {
        this.fechaPublicacionDof = value;
    }

}
