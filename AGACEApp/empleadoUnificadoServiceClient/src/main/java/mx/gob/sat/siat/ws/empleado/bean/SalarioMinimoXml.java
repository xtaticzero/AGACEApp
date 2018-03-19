package mx.gob.sat.siat.ws.empleado.bean;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for SalarioMinimoXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="SalarioMinimoXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Publicacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Valor" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalarioMinimo", propOrder = {
    "publicacion",
    "valor"
})
public class SalarioMinimoXml {

    @XmlElement(name = "Publicacion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publicacion;
    @XmlElement(name = "Valor")
    protected BigDecimal valor;

    /**
     * Gets the value of the publicacion property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getPublicacion() {
        return publicacion;
    }

    /**
     * Sets the value of the publicacion property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setPublicacion(XMLGregorianCalendar value) {
        this.publicacion = value;
    }

    /**
     * Gets the value of the valor property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setValor(BigDecimal value) {
        this.valor = value;
    }

}
