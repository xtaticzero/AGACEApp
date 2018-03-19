package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultarListaTiposProceso complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="consultarListaTiposProceso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unidadAdministrativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarListaTiposProceso", propOrder = {
    "unidadAdministrativa"
})
public class ConsultarListaTiposProceso {

    protected String unidadAdministrativa;

    /**
     * Gets the value of the unidadAdministrativa property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    /**
     * Sets the value of the unidadAdministrativa property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUnidadAdministrativa(String value) {
        this.unidadAdministrativa = value;
    }

}
