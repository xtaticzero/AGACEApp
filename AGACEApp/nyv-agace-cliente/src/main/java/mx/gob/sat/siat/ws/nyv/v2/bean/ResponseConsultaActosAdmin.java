package mx.gob.sat.siat.ws.nyv.v2.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for responseConsultaActosAdmin complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseConsultaActosAdmin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actoAdmin" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}actoAdministrativoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsultaActosAdmin", propOrder = {
    "actoAdmin"
})
public class ResponseConsultaActosAdmin {

    protected List<ActoAdministrativoV2VOXml> actoAdmin;

    /**
     * Gets the value of the actoAdmin property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the actoAdmin property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActoAdmin().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActoAdministrativoV2VOXml }
     *
     *
     */
    public List<ActoAdministrativoV2VOXml> getActoAdmin() {
        if (actoAdmin == null) {
            actoAdmin = new ArrayList<ActoAdministrativoV2VOXml>();
        }
        return this.actoAdmin;
    }

}
