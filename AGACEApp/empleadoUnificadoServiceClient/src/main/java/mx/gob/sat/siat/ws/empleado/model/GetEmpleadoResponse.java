package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.EmpleadoXml;

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
 *         &lt;element name="getEmpleadoResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}EmpleadoXml" minOccurs="0"/>
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
    "getEmpleadoResult"
})
@XmlRootElement(name = "getEmpleadoResponse")
public class GetEmpleadoResponse {

    @XmlElementRef(name = "getEmpleadoResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<EmpleadoXml> getEmpleadoResult;

    /**
     * Gets the value of the getEmpleadoResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link EmpleadoXml }{@code >}
     *
     */
    public JAXBElement<EmpleadoXml> getGetEmpleadoResult() {
        return getEmpleadoResult;
    }

    /**
     * Sets the value of the getEmpleadoResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link EmpleadoXml }{@code >}
     *
     */
    public void setGetEmpleadoResult(JAXBElement<EmpleadoXml> value) {
        this.getEmpleadoResult = ((JAXBElement<EmpleadoXml>) value);
    }

}
