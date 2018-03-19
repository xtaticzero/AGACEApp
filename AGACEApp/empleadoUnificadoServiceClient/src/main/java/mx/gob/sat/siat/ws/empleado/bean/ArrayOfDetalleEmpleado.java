package mx.gob.sat.siat.ws.empleado.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ArrayOfDetalleEmpleado complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="ArrayOfDetalleEmpleado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetalleEmpleadoXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}DetalleEmpleadoXml" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetalleEmpleado", propOrder = {
    "detalleEmpleado"
})
public class ArrayOfDetalleEmpleado {

    @XmlElement(name = "DetalleEmpleado", nillable = true)
    protected List<DetalleEmpleadoXml> detalleEmpleado;

    /**
     * Gets the value of the detalleEmpleado property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the detalleEmpleado property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetalleEmpleado().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetalleEmpleadoXml }
     *
     *
     */
    public List<DetalleEmpleadoXml> getDetalleEmpleado() {
        if (detalleEmpleado == null) {
            detalleEmpleado = new ArrayList<DetalleEmpleadoXml>();
        }
        return this.detalleEmpleado;
    }

}
