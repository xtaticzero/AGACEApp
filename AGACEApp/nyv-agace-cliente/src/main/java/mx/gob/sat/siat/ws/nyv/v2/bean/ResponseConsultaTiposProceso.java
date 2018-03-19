package mx.gob.sat.siat.ws.nyv.v2.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for responseConsultaTiposProceso complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="responseConsultaTiposProceso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipoProceso" type="{http://webservice.sistema.nyv.siat.sat.gob.mx/}tipoProcesoVO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseConsultaTiposProceso", propOrder = {
    "tipoProceso"
})
public class ResponseConsultaTiposProceso {

    protected List<TipoProcesoVOXml> tipoProceso;

    /**
     * Gets the value of the tipoProceso property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the tipoProceso property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTipoProceso().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoProcesoVOXml }
     *
     *
     */
    public List<TipoProcesoVOXml> getTipoProceso() {
        if (tipoProceso == null) {
            tipoProceso = new ArrayList<TipoProcesoVOXml>();
        }
        return this.tipoProceso;
    }

}
