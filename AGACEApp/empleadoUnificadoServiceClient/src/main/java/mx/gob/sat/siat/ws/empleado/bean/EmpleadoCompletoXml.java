package mx.gob.sat.siat.ws.empleado.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for EmpleadoCompletoXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="EmpleadoCompletoXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EmpleadoXml" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}EmpleadoXml" minOccurs="0"/>
 *         &lt;element name="ListaJefesSuperiores" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfEmpleado" minOccurs="0"/>
 *         &lt;element name="ListaSubordinados" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfEmpleado" minOccurs="0"/>
 *         &lt;element name="listaDetalleEmpleado" type="{http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado}ArrayOfDetalleEmpleado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmpleadoCompleto", propOrder = {
    "empleado",
    "listaJefesSuperiores",
    "listaSubordinados",
    "listaDetalleEmpleado"
})
public class EmpleadoCompletoXml {

    @XmlElementRef(name = "Empleado", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<EmpleadoXml> empleado;
    @XmlElementRef(name = "ListaJefesSuperiores", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEmpleado> listaJefesSuperiores;
    @XmlElementRef(name = "ListaSubordinados", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<ArrayOfEmpleado> listaSubordinados;
    @XmlElementRef(name = "listaDetalleEmpleado", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<ArrayOfDetalleEmpleado> listaDetalleEmpleado;

    /**
     * Gets the value of the empleado property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link EmpleadoXml }{@code >}
     *
     */
    public JAXBElement<EmpleadoXml> getEmpleado() {
        return empleado;
    }

    /**
     * Sets the value of the empleado property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link EmpleadoXml }{@code >}
     *
     */
    public void setEmpleado(JAXBElement<EmpleadoXml> value) {
        this.empleado = ((JAXBElement<EmpleadoXml>) value);
    }

    /**
     * Gets the value of the listaJefesSuperiores property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public JAXBElement<ArrayOfEmpleado> getListaJefesSuperiores() {
        return listaJefesSuperiores;
    }

    /**
     * Sets the value of the listaJefesSuperiores property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public void setListaJefesSuperiores(JAXBElement<ArrayOfEmpleado> value) {
        this.listaJefesSuperiores = ((JAXBElement<ArrayOfEmpleado>) value);
    }

    /**
     * Gets the value of the listaSubordinados property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public JAXBElement<ArrayOfEmpleado> getListaSubordinados() {
        return listaSubordinados;
    }

    /**
     * Sets the value of the listaSubordinados property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfEmpleado }{@code >}
     *
     */
    public void setListaSubordinados(JAXBElement<ArrayOfEmpleado> value) {
        this.listaSubordinados = ((JAXBElement<ArrayOfEmpleado>) value);
    }

    /**
     * Gets the value of the listaDetalleEmpleado property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ArrayOfDetalleEmpleado }{@code >}
     *
     */
    public JAXBElement<ArrayOfDetalleEmpleado> getListaDetalleEmpleado() {
        return listaDetalleEmpleado;
    }

    /**
     * Sets the value of the listaDetalleEmpleado property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link ArrayOfDetalleEmpleado }{@code >}
     *
     */
    public void setListaDetalleEmpleado(JAXBElement<ArrayOfDetalleEmpleado> value) {
        this.listaDetalleEmpleado = ((JAXBElement<ArrayOfDetalleEmpleado>) value);
    }

}
