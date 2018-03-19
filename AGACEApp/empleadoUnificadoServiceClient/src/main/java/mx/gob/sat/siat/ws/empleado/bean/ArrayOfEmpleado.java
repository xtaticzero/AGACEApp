package mx.gob.sat.siat.ws.empleado.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfEmpleado complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfEmpleado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmpleadoXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}EmpleadoXml" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEmpleado", propOrder = {
    "empleado"
})
public class ArrayOfEmpleado {

    @XmlElement(name = "Empleado", nillable = true)
    protected List<EmpleadoXml> empleado;

    /**
     * Gets the value of the empleado property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the empleado property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmpleado().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmpleadoXml }
     *
     *
     */
    public List<EmpleadoXml> getEmpleado() {
        if (empleado == null) {
            empleado = new ArrayList<EmpleadoXml>();
        }
        return this.empleado;
    }

}
