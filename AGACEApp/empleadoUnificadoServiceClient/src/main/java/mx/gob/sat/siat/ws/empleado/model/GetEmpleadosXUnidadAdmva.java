package mx.gob.sat.siat.ws.empleado.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="ClaveALAF" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClavePerfil" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ClaveModulo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "idmodulo",
    "claveALAF",
    "clavePerfil",
    "claveModulo"
})
@XmlRootElement(name = "getEmpleadosXUnidadAdmva")
public class GetEmpleadosXUnidadAdmva {

    protected Integer idmodulo;
    @XmlElement(name = "ClaveALAF")
    protected Integer claveALAF;
    @XmlElement(name = "ClavePerfil")
    protected Integer clavePerfil;
    @XmlElement(name = "ClaveModulo")
    protected Integer claveModulo;

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

    /**
     * Gets the value of the claveALAF property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getClaveALAF() {
        return claveALAF;
    }

    /**
     * Sets the value of the claveALAF property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setClaveALAF(Integer value) {
        this.claveALAF = value;
    }

    /**
     * Gets the value of the clavePerfil property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getClavePerfil() {
        return clavePerfil;
    }

    /**
     * Sets the value of the clavePerfil property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setClavePerfil(Integer value) {
        this.clavePerfil = value;
    }

    /**
     * Gets the value of the claveModulo property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getClaveModulo() {
        return claveModulo;
    }

    /**
     * Sets the value of the claveModulo property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setClaveModulo(Integer value) {
        this.claveModulo = value;
    }

}
