package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="idAdmGral" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "idAdmGral"
})
@XmlRootElement(name = "getUnidadesAdministrativasPorGeneral")
public class GetUnidadesAdministrativasPorGeneral {

    protected Integer idAdmGral;

    /**
     * Gets the value of the idAdmGral property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdAdmGral() {
        return idAdmGral;
    }

    /**
     * Sets the value of the idAdmGral property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdAdmGral(Integer value) {
        this.idAdmGral = value;
    }

}
