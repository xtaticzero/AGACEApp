package mx.gob.sat.siat.ws.empleado.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfINPC complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfINPC">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INPCXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}INPCXml" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfINPC", propOrder = {
    "inpc"
})
public class ArrayOfINPC {

    @XmlElement(name = "INPC", nillable = true)
    protected List<INPCXml> inpc;

    /**
     * Gets the value of the inpc property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the inpc property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getINPC().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link INPCXml }
     *
     *
     */
    public List<INPCXml> getINPC() {
        if (inpc == null) {
            inpc = new ArrayList<INPCXml>();
        }
        return this.inpc;
    }

}
