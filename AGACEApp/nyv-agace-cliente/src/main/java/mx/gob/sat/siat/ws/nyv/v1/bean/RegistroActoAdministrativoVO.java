package mx.gob.sat.siat.ws.nyv.v1.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for registroActoAdministrativoVO complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="registroActoAdministrativoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claveUnidadAdmin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="claveActoAdmin" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="procesoOrigen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaLimiteDiligencia" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="numeroReferencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroReferenciaOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rfcDestinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="documentos" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}documentoGeneradoVO" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registroActoAdministrativoVO", propOrder = {
    "claveUnidadAdmin",
    "claveActoAdmin",
    "procesoOrigen",
    "fechaLimiteDiligencia",
    "numeroReferencia",
    "numeroReferenciaOrigen",
    "rfcDestinatario",
    "numeroEmpleado",
    "documentos"
})
public class RegistroActoAdministrativoVO {

    @XmlElement(required = true)
    protected String claveUnidadAdmin;
    protected long claveActoAdmin;
    @XmlElement(required = true)
    protected String procesoOrigen;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaLimiteDiligencia;
    @XmlElement(required = true)
    protected String numeroReferencia;
    protected String numeroReferenciaOrigen;
    @XmlElement(required = true)
    protected String rfcDestinatario;
    @XmlElement(required = true)
    protected String numeroEmpleado;
    @XmlElement(required = true)
    protected List<DocumentoGeneradoVOXml> documentos;

    /**
     * Gets the value of the claveUnidadAdmin property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClaveUnidadAdmin() {
        return claveUnidadAdmin;
    }

    /**
     * Sets the value of the claveUnidadAdmin property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setClaveUnidadAdmin(String value) {
        this.claveUnidadAdmin = value;
    }

    /**
     * Gets the value of the claveActoAdmin property.
     *
     */
    public long getClaveActoAdmin() {
        return claveActoAdmin;
    }

    /**
     * Sets the value of the claveActoAdmin property.
     *
     */
    public void setClaveActoAdmin(long value) {
        this.claveActoAdmin = value;
    }

    /**
     * Gets the value of the procesoOrigen property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getProcesoOrigen() {
        return procesoOrigen;
    }

    /**
     * Sets the value of the procesoOrigen property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setProcesoOrigen(String value) {
        this.procesoOrigen = value;
    }

    /**
     * Gets the value of the fechaLimiteDiligencia property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaLimiteDiligencia() {
        return fechaLimiteDiligencia;
    }

    /**
     * Sets the value of the fechaLimiteDiligencia property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaLimiteDiligencia(XMLGregorianCalendar value) {
        this.fechaLimiteDiligencia = value;
    }

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

    /**
     * Gets the value of the numeroReferenciaOrigen property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumeroReferenciaOrigen() {
        return numeroReferenciaOrigen;
    }

    /**
     * Sets the value of the numeroReferenciaOrigen property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumeroReferenciaOrigen(String value) {
        this.numeroReferenciaOrigen = value;
    }

    /**
     * Gets the value of the rfcDestinatario property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRfcDestinatario() {
        return rfcDestinatario;
    }

    /**
     * Sets the value of the rfcDestinatario property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRfcDestinatario(String value) {
        this.rfcDestinatario = value;
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

    /**
     * Gets the value of the documentos property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the documentos property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentos().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoGeneradoVOXml }
     *
     *
     */
    public List<DocumentoGeneradoVOXml> getDocumentos() {
        if (documentos == null) {
            documentos = new ArrayList<DocumentoGeneradoVOXml>();
        }
        return this.documentos;
    }

    public void setDocumentos(List<DocumentoGeneradoVOXml> documentos) {
        this.documentos = documentos;
    }

}
