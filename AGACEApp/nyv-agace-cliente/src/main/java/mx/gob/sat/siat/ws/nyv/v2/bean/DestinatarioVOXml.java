package mx.gob.sat.siat.ws.nyv.v2.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for destinatarioVO complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="destinatarioVO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="razonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entidadFederativa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colonia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroExterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroInterior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entreCalleUno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entreCalleDos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cveAlr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cveCrh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cveUds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "destinatarioVO", propOrder = {
    "nombre",
    "apellidoPaterno",
    "apellidoMaterno",
    "razonSocial",
    "entidadFederativa",
    "municipio",
    "colonia",
    "localidad",
    "calle",
    "numeroExterior",
    "numeroInterior",
    "entreCalleUno",
    "entreCalleDos",
    "cp",
    "cveAlr",
    "cveCrh",
    "cveUds"
})
public class DestinatarioVOXml {

    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String razonSocial;
    protected String entidadFederativa;
    protected String municipio;
    protected String colonia;
    protected String localidad;
    protected String calle;
    protected String numeroExterior;
    protected String numeroInterior;
    protected String entreCalleUno;
    protected String entreCalleDos;
    protected String cp;
    protected String cveAlr;
    protected String cveCrh;
    protected String cveUds;

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
     * Gets the value of the apellidoPaterno property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setApellidoPaterno(String value) {
        this.apellidoPaterno = value;
    }

    /**
     * Gets the value of the apellidoMaterno property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setApellidoMaterno(String value) {
        this.apellidoMaterno = value;
    }

    /**
     * Gets the value of the razonSocial property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the entidadFederativa property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEntidadFederativa() {
        return entidadFederativa;
    }

    /**
     * Sets the value of the entidadFederativa property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEntidadFederativa(String value) {
        this.entidadFederativa = value;
    }

    /**
     * Gets the value of the municipio property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * Sets the value of the municipio property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setMunicipio(String value) {
        this.municipio = value;
    }

    /**
     * Gets the value of the colonia property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Sets the value of the colonia property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setColonia(String value) {
        this.colonia = value;
    }

    /**
     * Gets the value of the localidad property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the calle property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the value of the calle property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCalle(String value) {
        this.calle = value;
    }

    /**
     * Gets the value of the numeroExterior property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumeroExterior() {
        return numeroExterior;
    }

    /**
     * Sets the value of the numeroExterior property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumeroExterior(String value) {
        this.numeroExterior = value;
    }

    /**
     * Gets the value of the numeroInterior property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNumeroInterior() {
        return numeroInterior;
    }

    /**
     * Sets the value of the numeroInterior property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNumeroInterior(String value) {
        this.numeroInterior = value;
    }

    /**
     * Gets the value of the entreCalleUno property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEntreCalleUno() {
        return entreCalleUno;
    }

    /**
     * Sets the value of the entreCalleUno property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEntreCalleUno(String value) {
        this.entreCalleUno = value;
    }

    /**
     * Gets the value of the entreCalleDos property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getEntreCalleDos() {
        return entreCalleDos;
    }

    /**
     * Sets the value of the entreCalleDos property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setEntreCalleDos(String value) {
        this.entreCalleDos = value;
    }

    /**
     * Gets the value of the cp property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets the value of the cp property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCp(String value) {
        this.cp = value;
    }

    /**
     * Gets the value of the cveAlr property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCveAlr() {
        return cveAlr;
    }

    /**
     * Sets the value of the cveAlr property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCveAlr(String value) {
        this.cveAlr = value;
    }

    /**
     * Gets the value of the cveCrh property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCveCrh() {
        return cveCrh;
    }

    /**
     * Sets the value of the cveCrh property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCveCrh(String value) {
        this.cveCrh = value;
    }

    /**
     * Gets the value of the cveUds property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCveUds() {
        return cveUds;
    }

    /**
     * Sets the value of the cveUds property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCveUds(String value) {
        this.cveUds = value;
    }

}
