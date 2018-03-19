package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for documentoGeneradoVO complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="documentoGeneradoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estatusDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="urlDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firma" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaDocumento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cadenaOriginal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cveDocumentoTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="folioSifen" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rfcFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puestoFirmante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaVigenciaFiel" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoGeneradoVO", propOrder = {
    "estatusDocumento",
    "urlDocumento",
    "firma",
    "fechaDocumento",
    "cadenaOriginal",
    "cveDocumentoTipo",
    "folioSifen",
    "rfcFirmante",
    "nombreFirmante",
    "puestoFirmante",
    "fechaVigenciaFiel"
})
public class DocumentoGeneradoVOXml {

    @XmlElement(required = true)
    protected String estatusDocumento;
    @XmlElement(required = true)
    protected String urlDocumento;
    protected String firma;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaDocumento;
    protected String cadenaOriginal;
    @XmlElement(required = true)
    protected String cveDocumentoTipo;
    protected long folioSifen;
    protected String rfcFirmante;
    protected String nombreFirmante;
    protected String puestoFirmante;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaVigenciaFiel;

    /**
     * Gets the value of the estatusDocumento property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEstatusDocumento() {
        return estatusDocumento;
    }

    /**
     * Sets the value of the estatusDocumento property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEstatusDocumento(String value) {
        this.estatusDocumento = value;
    }

    /**
     * Gets the value of the urlDocumento property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrlDocumento() {
        return urlDocumento;
    }

    /**
     * Sets the value of the urlDocumento property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUrlDocumento(String value) {
        this.urlDocumento = value;
    }

    /**
     * Gets the value of the firma property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFirma() {
        return firma;
    }

    /**
     * Sets the value of the firma property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFirma(String value) {
        this.firma = value;
    }

    /**
     * Gets the value of the fechaDocumento property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaDocumento() {
        return fechaDocumento;
    }

    /**
     * Sets the value of the fechaDocumento property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaDocumento(XMLGregorianCalendar value) {
        this.fechaDocumento = value;
    }

    /**
     * Gets the value of the cadenaOriginal property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCadenaOriginal() {
        return cadenaOriginal;
    }

    /**
     * Sets the value of the cadenaOriginal property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCadenaOriginal(String value) {
        this.cadenaOriginal = value;
    }

    /**
     * Gets the value of the cveDocumentoTipo property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCveDocumentoTipo() {
        return cveDocumentoTipo;
    }

    /**
     * Sets the value of the cveDocumentoTipo property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCveDocumentoTipo(String value) {
        this.cveDocumentoTipo = value;
    }

    /**
     * Gets the value of the folioSifen property.
     *
     */
    public long getFolioSifen() {
        return folioSifen;
    }

    /**
     * Sets the value of the folioSifen property.
     *
     */
    public void setFolioSifen(long value) {
        this.folioSifen = value;
    }

    /**
     * Gets the value of the rfcFirmante property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRfcFirmante() {
        return rfcFirmante;
    }

    /**
     * Sets the value of the rfcFirmante property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRfcFirmante(String value) {
        this.rfcFirmante = value;
    }

    /**
     * Gets the value of the nombreFirmante property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNombreFirmante() {
        return nombreFirmante;
    }

    /**
     * Sets the value of the nombreFirmante property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNombreFirmante(String value) {
        this.nombreFirmante = value;
    }

    /**
     * Gets the value of the puestoFirmante property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPuestoFirmante() {
        return puestoFirmante;
    }

    /**
     * Sets the value of the puestoFirmante property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPuestoFirmante(String value) {
        this.puestoFirmante = value;
    }

    /**
     * Gets the value of the fechaVigenciaFiel property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaVigenciaFiel() {
        return fechaVigenciaFiel;
    }

    /**
     * Sets the value of the fechaVigenciaFiel property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaVigenciaFiel(XMLGregorianCalendar value) {
        this.fechaVigenciaFiel = value;
    }

}
