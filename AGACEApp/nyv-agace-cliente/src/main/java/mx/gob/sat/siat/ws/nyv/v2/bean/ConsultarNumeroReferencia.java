package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for consultarNumeroReferencia complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="consultarNumeroReferencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarNumeroReferencia", propOrder = {
    "numeroReferencia"
})
public class ConsultarNumeroReferencia {

    protected String numeroReferencia;

    /**
     * Gets the value of the numeroReferencia property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    /**
     * Sets the value of the numeroReferencia property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumeroReferencia(String value) {
        this.numeroReferencia = value;
    }

}
