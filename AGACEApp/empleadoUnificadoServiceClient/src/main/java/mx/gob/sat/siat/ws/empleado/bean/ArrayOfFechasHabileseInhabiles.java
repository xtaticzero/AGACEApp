package mx.gob.sat.siat.ws.empleado.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfFechasHabileseInhabiles complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfFechasHabileseInhabiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FechasHabileseInhabilesXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}FechasHabileseInhabilesXml" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFechasHabileseInhabiles", propOrder = {
    "fechasHabileseInhabiles"
})
public class ArrayOfFechasHabileseInhabiles {

    @XmlElement(name = "FechasHabileseInhabiles", nillable = true)
    protected List<FechasHabileseInhabilesXml> fechasHabileseInhabiles;

    /**
     * Gets the value of the fechasHabileseInhabiles property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the fechasHabileseInhabiles property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFechasHabileseInhabiles().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FechasHabileseInhabilesXml }
     *
     *
     */
    public List<FechasHabileseInhabilesXml> getFechasHabileseInhabiles() {
        if (fechasHabileseInhabiles == null) {
            fechasHabileseInhabiles = new ArrayList<FechasHabileseInhabilesXml>();
        }
        return this.fechasHabileseInhabiles;
    }

}
