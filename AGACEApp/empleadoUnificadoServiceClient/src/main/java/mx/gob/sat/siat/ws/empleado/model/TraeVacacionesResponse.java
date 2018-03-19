package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mx.gob.sat.siat.ws.empleado.bean.ArrayOfVacacion;

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
 *         &lt;element name="TraeVacacionesResult" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfVacacion" minOccurs="0"/>
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
    "traeVacacionesResult"
})
@XmlRootElement(name = "TraeVacacionesResponse")
public class TraeVacacionesResponse {

    @XmlElementRef(name = "TraeVacacionesResult", namespace = "http://tempuri.org/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfVacacion> traeVacacionesResult;

    /**
     * Gets the value of the traeVacacionesResult property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfVacacion }{@code >}
     *
     */
    public JAXBElement<ArrayOfVacacion> getTraeVacacionesResult() {
        return traeVacacionesResult;
    }

    /**
     * Sets the value of the traeVacacionesResult property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfVacacion }{@code >}
     *
     */
    public void setTraeVacacionesResult(JAXBElement<ArrayOfVacacion> value) {
        this.traeVacacionesResult = ((JAXBElement<ArrayOfVacacion>) value);
    }

}
