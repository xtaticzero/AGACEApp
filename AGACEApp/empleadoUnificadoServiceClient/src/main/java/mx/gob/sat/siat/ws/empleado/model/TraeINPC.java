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
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "anio"
})
@XmlRootElement(name = "TraeINPC")
public class TraeINPC {

    protected Integer anio;

    /**
     * Gets the value of the anio property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setAnio(Integer value) {
        this.anio = value;
    }

}
