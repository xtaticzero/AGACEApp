package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfINPC;

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
 *         &lt;element name="TraeINPCResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfINPC" minOccurs="0"/>
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
    "traeINPCResult"
})
@XmlRootElement(name = "TraeINPCResponse")
public class TraeINPCResponse {

    @XmlElementRef(name = "TraeINPCResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfINPC> traeINPCResult;

    /**
     * Gets the value of the traeINPCResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfINPC }{@code >}
     *
     */
    public JAXBElement<ArrayOfINPC> getTraeINPCResult() {
        return traeINPCResult;
    }

    /**
     * Sets the value of the traeINPCResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfINPC }{@code >}
     *
     */
    public void setTraeINPCResult(JAXBElement<ArrayOfINPC> value) {
        this.traeINPCResult = ((JAXBElement<ArrayOfINPC>) value);
    }

}
