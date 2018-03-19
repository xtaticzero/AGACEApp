package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfEmpleado;

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
 *         &lt;element name="InformacionEmpleadosResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfEmpleado" minOccurs="0"/>
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
    "informacionEmpleadosResult"
})
@XmlRootElement(name = "InformacionEmpleadosResponse")
public class InformacionEmpleadosResponse {

    @XmlElementRef(name = "InformacionEmpleadosResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEmpleado> informacionEmpleadosResult;

    /**
     * Gets the value of the informacionEmpleadosResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public JAXBElement<ArrayOfEmpleado> getInformacionEmpleadosResult() {
        return informacionEmpleadosResult;
    }

    /**
     * Sets the value of the informacionEmpleadosResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public void setInformacionEmpleadosResult(JAXBElement<ArrayOfEmpleado> value) {
        this.informacionEmpleadosResult = ((JAXBElement<ArrayOfEmpleado>) value);
    }

}
