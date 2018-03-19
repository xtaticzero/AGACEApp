package mx.gob.sat.siat.ws.empleado.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DetalleEmpleadoXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="DetalleEmpleadoXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCentral" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idPerfil" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idPuesto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idRol" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idUnidadAdmin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleEmpleado", propOrder = {
    "idCentral",
    "idPerfil",
    "idPuesto",
    "idRol",
    "idUnidadAdmin"
})
public class DetalleEmpleadoXml {

    protected Integer idCentral;
    protected Integer idPerfil;
    protected Integer idPuesto;
    protected Integer idRol;
    protected Integer idUnidadAdmin;

    /**
     * Gets the value of the idCentral property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdCentral() {
        return idCentral;
    }

    /**
     * Sets the value of the idCentral property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdCentral(Integer value) {
        this.idCentral = value;
    }

    /**
     * Gets the value of the idPerfil property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdPerfil() {
        return idPerfil;
    }

    /**
     * Sets the value of the idPerfil property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdPerfil(Integer value) {
        this.idPerfil = value;
    }

    /**
     * Gets the value of the idPuesto property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdPuesto() {
        return idPuesto;
    }

    /**
     * Sets the value of the idPuesto property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdPuesto(Integer value) {
        this.idPuesto = value;
    }

    /**
     * Gets the value of the idRol property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdRol() {
        return idRol;
    }

    /**
     * Sets the value of the idRol property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdRol(Integer value) {
        this.idRol = value;
    }

    /**
     * Gets the value of the idUnidadAdmin property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdUnidadAdmin() {
        return idUnidadAdmin;
    }

    /**
     * Sets the value of the idUnidadAdmin property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdUnidadAdmin(Integer value) {
        this.idUnidadAdmin = value;
    }

}
