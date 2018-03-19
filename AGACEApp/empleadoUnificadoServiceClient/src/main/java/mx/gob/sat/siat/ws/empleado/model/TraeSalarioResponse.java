package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.SalarioMinimoXml;

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
 *         &lt;element name="TraeSalarioResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}SalarioMinimoXml" minOccurs="0"/>
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
    "traeSalarioResult"
})
@XmlRootElement(name = "TraeSalarioResponse")
public class TraeSalarioResponse {

    @XmlElementRef(name = "TraeSalarioResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<SalarioMinimoXml> traeSalarioResult;

    /**
     * Gets the value of the traeSalarioResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link SalarioMinimoXml }{@code >}
     *
     */
    public JAXBElement<SalarioMinimoXml> getTraeSalarioResult() {
        return traeSalarioResult;
    }

    /**
     * Sets the value of the traeSalarioResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link SalarioMinimoXml }{@code >}
     *
     */
    public void setTraeSalarioResult(JAXBElement<SalarioMinimoXml> value) {
        this.traeSalarioResult = ((JAXBElement<SalarioMinimoXml>) value);
    }

}
