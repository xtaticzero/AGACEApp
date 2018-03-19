package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for bitacoraVO complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="bitacoraVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bitacoraVO", propOrder = {
    "estatus",
    "fechaAlta",
    "numeroEmpleado"
})
public class BitacoraVOXml {

    protected String estatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    protected String numeroEmpleado;

    /**
     * Gets the value of the estatus property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Sets the value of the estatus property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEstatus(String value) {
        this.estatus = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the numeroEmpleado property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * Sets the value of the numeroEmpleado property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumeroEmpleado(String value) {
        this.numeroEmpleado = value;
    }

}
