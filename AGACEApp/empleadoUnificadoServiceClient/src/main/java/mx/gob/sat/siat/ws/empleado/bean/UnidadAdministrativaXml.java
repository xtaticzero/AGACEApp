package mx.gob.sat.siat.ws.empleado.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for UnidadAdministrativaXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="UnidadAdministrativaXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CENTRAL" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CLAVE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NOMBRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEDE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnidadAdministrativa", propOrder = {
    "central",
    "clave",
    "id",
    "nombre",
    "sede"
})
public class UnidadAdministrativaXml {

    @XmlElement(name = "CENTRAL")
    protected Boolean central;
    @XmlElementRef(name = "CLAVE", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> clave;
    @XmlElement(name = "ID")
    protected Integer id;
    @XmlElementRef(name = "NOMBRE", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> nombre;
    @XmlElementRef(name = "SEDE", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> sede;

    /**
     * Gets the value of the central property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isCENTRAL() {
        return central;
    }

    /**
     * Sets the value of the central property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setCENTRAL(Boolean value) {
        this.central = value;
    }

    /**
     * Gets the value of the clave property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCLAVE() {
        return clave;
    }

    /**
     * Sets the value of the clave property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCLAVE(JAXBElement<String> value) {
        this.clave = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setID(Integer value) {
        this.id = value;
    }

    /**
     * Gets the value of the nombre property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNOMBRE() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNOMBRE(JAXBElement<String> value) {
        this.nombre = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the sede property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getSEDE() {
        return sede;
    }

    /**
     * Sets the value of the sede property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setSEDE(JAXBElement<String> value) {
        this.sede = ((JAXBElement<String>) value);
    }

}
