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
 *         &lt;element name="idmodulo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "idmodulo"
})
@XmlRootElement(name = "getUnidadesAdministrativas")
public class GetUnidadesAdministrativas {

    protected Integer idmodulo;

    /**
     * Gets the value of the idmodulo property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdmodulo() {
        return idmodulo;
    }

    /**
     * Sets the value of the idmodulo property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdmodulo(Integer value) {
        this.idmodulo = value;
    }

}
