package mx.gob.sat.siat.ws.empleado.bean;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for EmpleadoXml complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="EmpleadoXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="admCentral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="admGral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoMaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellidoPaterno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcionPerfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estatusEmpleado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fechaCreacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="idAdmCentral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idAdmGral" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idArea" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="idSuplencia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroEmpleado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numeroJefeInmediato" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rfc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Empleado", propOrder = {
    "area",
    "admCentral",
    "admGral",
    "apellidoMaterno",
    "apellidoPaterno",
    "correo",
    "descripcionPerfil",
    "estatusEmpleado",
    "fechaBaja",
    "fechaCreacion",
    "idAdmCentral",
    "idAdmGral",
    "idArea",
    "idSuplencia",
    "nombre",
    "numeroEmpleado",
    "numeroJefeInmediato",
    "rfc"
})
public class EmpleadoXml {

    @XmlElementRef(name = "Area", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> area;
    @XmlElementRef(name = "admCentral", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> admCentral;
    @XmlElementRef(name = "admGral", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> admGral;
    @XmlElementRef(name = "apellidoMaterno", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> apellidoMaterno;
    @XmlElementRef(name = "apellidoPaterno", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> apellidoPaterno;
    @XmlElementRef(name = "correo", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> correo;
    @XmlElementRef(name = "descripcionPerfil", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> descripcionPerfil;
    @XmlElementRef(name = "estatusEmpleado", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> estatusEmpleado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCreacion;
    @XmlElementRef(name = "idAdmCentral", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> idAdmCentral;
    protected Integer idAdmGral;
    protected Integer idArea;
    protected Integer idSuplencia;
    @XmlElementRef(name = "nombre", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> nombre;
    protected Integer numeroEmpleado;
    protected Integer numeroJefeInmediato;
    @XmlElementRef(name = "rfc", namespace = "http://schemas.datacontract.org/2004/07/WcfEmpleadosUnificado", type = JAXBElement.class)
    protected JAXBElement<String> rfc;

    /**
     * Gets the value of the area property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setArea(JAXBElement<String> value) {
        this.area = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the admCentral property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAdmCentral() {
        return admCentral;
    }

    /**
     * Sets the value of the admCentral property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAdmCentral(JAXBElement<String> value) {
        this.admCentral = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the admGral property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getAdmGral() {
        return admGral;
    }

    /**
     * Sets the value of the admGral property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setAdmGral(JAXBElement<String> value) {
        this.admGral = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the apellidoMaterno property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Sets the value of the apellidoMaterno property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setApellidoMaterno(JAXBElement<String> value) {
        this.apellidoMaterno = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the apellidoPaterno property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Sets the value of the apellidoPaterno property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setApellidoPaterno(JAXBElement<String> value) {
        this.apellidoPaterno = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the correo property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getCorreo() {
        return correo;
    }

    /**
     * Sets the value of the correo property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setCorreo(JAXBElement<String> value) {
        this.correo = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the descripcionPerfil property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getDescripcionPerfil() {
        return descripcionPerfil;
    }

    /**
     * Sets the value of the descripcionPerfil property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setDescripcionPerfil(JAXBElement<String> value) {
        this.descripcionPerfil = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the estatusEmpleado property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getEstatusEmpleado() {
        return estatusEmpleado;
    }

    /**
     * Sets the value of the estatusEmpleado property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setEstatusEmpleado(JAXBElement<String> value) {
        this.estatusEmpleado = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the fechaBaja property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the value of the fechaBaja property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Gets the value of the fechaCreacion property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets the value of the fechaCreacion property.
     *
     * @param value allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setFechaCreacion(XMLGregorianCalendar value) {
        this.fechaCreacion = value;
    }

    /**
     * Gets the value of the idAdmCentral property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getIdAdmCentral() {
        return idAdmCentral;
    }

    /**
     * Sets the value of the idAdmCentral property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setIdAdmCentral(JAXBElement<String> value) {
        this.idAdmCentral = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the idAdmGral property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdAdmGral() {
        return idAdmGral;
    }

    /**
     * Sets the value of the idAdmGral property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdAdmGral(Integer value) {
        this.idAdmGral = value;
    }

    /**
     * Gets the value of the idArea property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdArea() {
        return idArea;
    }

    /**
     * Sets the value of the idArea property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdArea(Integer value) {
        this.idArea = value;
    }

    /**
     * Gets the value of the idSuplencia property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getIdSuplencia() {
        return idSuplencia;
    }

    /**
     * Sets the value of the idSuplencia property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setIdSuplencia(Integer value) {
        this.idSuplencia = value;
    }

    /**
     * Gets the value of the nombre property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setNombre(JAXBElement<String> value) {
        this.nombre = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the numeroEmpleado property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * Sets the value of the numeroEmpleado property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroEmpleado(Integer value) {
        this.numeroEmpleado = value;
    }

    /**
     * Gets the value of the numeroJefeInmediato property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getNumeroJefeInmediato() {
        return numeroJefeInmediato;
    }

    /**
     * Sets the value of the numeroJefeInmediato property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setNumeroJefeInmediato(Integer value) {
        this.numeroJefeInmediato = value;
    }

    /**
     * Gets the value of the rfc property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public JAXBElement<String> getRfc() {
        return rfc;
    }

    /**
     * Sets the value of the rfc property.
     *
     * @param value allowed object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     *
     */
    public void setRfc(JAXBElement<String> value) {
        this.rfc = ((JAXBElement<String>) value);
    }

}
