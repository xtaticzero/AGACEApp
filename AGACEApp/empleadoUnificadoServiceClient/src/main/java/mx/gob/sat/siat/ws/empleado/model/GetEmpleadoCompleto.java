package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idmodulo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idmodulo",
    "rfc",
    "numeroEmpleado"
})
@XmlRootElement(name = "getEmpleadoCompleto")
public class GetEmpleadoCompleto {

    protected Integer idmodulo;
    @XmlElementRef(name = "RFC", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> rfc;
    @XmlElement(name = "NumeroEmpleado")
    protected Integer numeroEmpleado;

    /**
     * Gets the value of the idmodulo property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdmodulo() {
        return idmodulo;
    }

    /**
     * Sets the value of the idmodulo property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdmodulo(Integer value) {
        this.idmodulo = value;
    }

    /**
     * Gets the value of the rfc property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getRFC() {
        return rfc;
    }

    /**
     * Sets the value of the rfc property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setRFC(JAXBElement<String> value) {
        this.rfc = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the numeroEmpleado property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * Sets the value of the numeroEmpleado property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroEmpleado(Integer value) {
        this.numeroEmpleado = value;
    }

}
