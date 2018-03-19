package mx.gob.sat.siat.ws.empleado.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfVacacion complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfVacacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VacacionXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}VacacionXml" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfVacacion", propOrder = {
    "vacacion"
})
public class ArrayOfVacacion {

    @XmlElement(name = "Vacacion", nillable = true)
    protected List<VacacionXml> vacacion;

    /**
     * Gets the value of the vacacion property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the vacacion property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVacacion().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VacacionXml }
     *
     *
     */
    public List<VacacionXml> getVacacion() {
        if (vacacion == null) {
            vacacion = new ArrayList<VacacionXml>();
        }
        return this.vacacion;
    }

}
