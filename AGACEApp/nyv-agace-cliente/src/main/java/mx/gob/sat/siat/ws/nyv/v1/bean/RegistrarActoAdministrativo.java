package mx.gob.sat.siat.ws.nyv.v1.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for registrarActoAdministrativo complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="registrarActoAdministrativo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registroActoAdministrativo" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}registroActoAdministrativoVO" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarActoAdministrativo", propOrder = {
    "registroActoAdministrativo"
})
public class RegistrarActoAdministrativo {

    protected RegistroActoAdministrativoVO registroActoAdministrativo;

    /**
     * Gets the value of the registroActoAdministrativo property.
     *
     * @return possible object is {@link RegistroActoAdministrativoVO }
     *
     */
    public RegistroActoAdministrativoVO getRegistroActoAdministrativo() {
        return registroActoAdministrativo;
    }

    /**
     * Sets the value of the registroActoAdministrativo property.
     *
     * @param value allowed object is {@link RegistroActoAdministrativoVO }
     *
     */
    public void setRegistroActoAdministrativo(RegistroActoAdministrativoVO value) {
        this.registroActoAdministrativo = value;
    }

}
