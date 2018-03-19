package mx.gob.sat.siat.ws.nyv.v1.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for actoAdministrativoVO complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="actoAdministrativoVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prefijoReferencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}tipoDocumentoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actoAdministrativoVO", propOrder = {
    "id",
    "nombre",
    "descripcion",
    "prefijoReferencia",
    "documento"
})
public class ActoAdministrativoV1VOXml {

    protected long id;
    protected String nombre;
    protected String descripcion;
    protected String prefijoReferencia;
    protected List<TipoDocumentoV1VOXml> documento;

    /**
     * Gets the value of the id property.
     *
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nombre property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the descripcion property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the prefijoReferencia property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getPrefijoReferencia() {
        return prefijoReferencia;
    }

    /**
     * Sets the value of the prefijoReferencia property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setPrefijoReferencia(String value) {
        this.prefijoReferencia = value;
    }

    /**
     * Gets the value of the documento property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the documento property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumento().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoDocumentoV1VOXml }
     *
     *
     */
    public List<TipoDocumentoV1VOXml> getDocumento() {
        if (documento == null) {
            documento = new ArrayList<TipoDocumentoV1VOXml>();
        }
        return this.documento;
    }

}
