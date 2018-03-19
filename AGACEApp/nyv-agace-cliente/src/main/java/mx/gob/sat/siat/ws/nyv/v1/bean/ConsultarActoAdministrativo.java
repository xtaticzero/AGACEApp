package mx.gob.sat.siat.ws.nyv.v1.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultarActoAdministrativo complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="consultarActoAdministrativo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folioActoAdministrativo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarActoAdministrativo", propOrder = {
    "folioActoAdministrativo"
})
public class ConsultarActoAdministrativo {

    protected String folioActoAdministrativo;

    /**
     * Gets the value of the folioActoAdministrativo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFolioActoAdministrativo() {
        return folioActoAdministrativo;
    }

    /**
     * Sets the value of the folioActoAdministrativo property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFolioActoAdministrativo(String value) {
        this.folioActoAdministrativo = value;
    }

}
