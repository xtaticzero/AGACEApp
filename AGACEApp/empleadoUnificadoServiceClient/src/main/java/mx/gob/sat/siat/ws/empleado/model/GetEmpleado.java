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
 *         &lt;element name="NumeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Empleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idSistema" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "numeroEmpleado",
    "empleado",
    "idSistema"
})
@XmlRootElement(name = "getEmpleado")
public class GetEmpleado {

    @XmlElement(name = "NumeroEmpleado")
    protected Integer numeroEmpleado;
    @XmlElementRef(name = "Empleado", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<String> empleado;
    protected Integer idSistema;

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

    /**
     * Gets the value of the empleado property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEmpleado() {
        return empleado;
    }

    /**
     * Sets the value of the empleado property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEmpleado(JAXBElement<String> value) {
        this.empleado = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the idSistema property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdSistema() {
        return idSistema;
    }

    /**
     * Sets the value of the idSistema property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdSistema(Integer value) {
        this.idSistema = value;
    }

}
