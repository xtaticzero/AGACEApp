package mx.gob.sat.siat.ws.nyv.v1.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for registrarActoAdministrativoResponse complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="registrarActoAdministrativoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}responseRegistro" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarActoAdministrativoResponse", propOrder = {
    "_return"
})
public class RegistrarActoAdministrativoResponse {

    @XmlElement(name = "return")
    protected ResponseRegistro _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link ResponseRegistro }
     *
     */
    public ResponseRegistro getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link ResponseRegistro }
     *
     */
    public void setReturn(ResponseRegistro value) {
        this._return = value;
    }

}
