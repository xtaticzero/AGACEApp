package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoCompletoXml;

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
 *         &lt;element name="getEmpleadoCompletoResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}EmpleadoCompletoXml" minOccurs="0"/>
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
    "getEmpleadoCompletoResult"
})
@XmlRootElement(name = "getEmpleadoCompletoResponse")
public class GetEmpleadoCompletoResponse {

    @XmlElementRef(name = "getEmpleadoCompletoResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<EmpleadoCompletoXml> getEmpleadoCompletoResult;

    /**
     * Gets the value of the getEmpleadoCompletoResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link EmpleadoCompletoXml }{@code >}
     *
     */
    public JAXBElement<EmpleadoCompletoXml> getGetEmpleadoCompletoResult() {
        return getEmpleadoCompletoResult;
    }

    /**
     * Sets the value of the getEmpleadoCompletoResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link EmpleadoCompletoXml }{@code >}
     *
     */
    public void setGetEmpleadoCompletoResult(JAXBElement<EmpleadoCompletoXml> value) {
        this.getEmpleadoCompletoResult = ((JAXBElement<EmpleadoCompletoXml>) value);
    }

}
