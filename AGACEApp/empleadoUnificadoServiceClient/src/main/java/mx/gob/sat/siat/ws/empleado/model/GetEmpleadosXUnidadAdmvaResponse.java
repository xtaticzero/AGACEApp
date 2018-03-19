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
 *         &lt;element name="getEmpleadosXUnidadAdmvaResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfEmpleado" minOccurs="0"/>
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
    "getEmpleadosXUnidadAdmvaResult"
})
@XmlRootElement(name = "getEmpleadosXUnidadAdmvaResponse")
public class GetEmpleadosXUnidadAdmvaResponse {

    @XmlElementRef(name = "getEmpleadosXUnidadAdmvaResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEmpleado> getEmpleadosXUnidadAdmvaResult;

    /**
     * Gets the value of the getEmpleadosXUnidadAdmvaResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public JAXBElement<ArrayOfEmpleado> getGetEmpleadosXUnidadAdmvaResult() {
        return getEmpleadosXUnidadAdmvaResult;
    }

    /**
     * Sets the value of the getEmpleadosXUnidadAdmvaResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public void setGetEmpleadosXUnidadAdmvaResult(JAXBElement<ArrayOfEmpleado> value) {
        this.getEmpleadosXUnidadAdmvaResult = ((JAXBElement<ArrayOfEmpleado>) value);
    }

}
