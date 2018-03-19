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
 * Java class for INPCXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="INPCXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Anio" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Indice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Mes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Publicacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INPC", propOrder = {
    "anio",
    "indice",
    "mes",
    "publicacion"
})
public class INPCXml {

    @XmlElement(name = "Anio")
    protected Integer anio;
    @XmlElement(name = "Indice")
    protected BigDecimal indice;
    @XmlElement(name = "Mes")
    protected Integer mes;
    @XmlElement(name = "Publicacion")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar publicacion;

    /**
     * Gets the value of the anio property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setAnio(Integer value) {
        this.anio = value;
    }

    /**
     * Gets the value of the indice property.
     *
     * @return possible object is {@link BigDecimal }
     *
     */
    public BigDecimal getIndice() {
        return indice;
    }

    /**
     * Sets the value of the indice property.
     *
     * @param value allowed object is {@link BigDecimal }
     *
     */
    public void setIndice(BigDecimal value) {
        this.indice = value;
    }

    /**
     * Gets the value of the mes property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setMes(Integer value) {
        this.mes = value;
    }

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

}
