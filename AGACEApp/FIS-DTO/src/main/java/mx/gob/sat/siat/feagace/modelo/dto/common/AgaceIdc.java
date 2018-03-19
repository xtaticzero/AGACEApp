/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;


public class AgaceIdc implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_IDC in the AGACE_IDC table.
     */
    private Long idIdc;

    /**
     * This attribute maps to the column ID_ORDEN in the AGACE_IDC table.
     */
    private Long idOrden;

    /**
     * This attribute represents whether the primitive attribute idOrden is null.
     */
    private boolean idOrdenNull = true;

    /**
     * This attribute maps to the column NOMBRE_AGENTE_ADUANAL in the AGACE_IDC table.
     */
    private String nombreAgenteAduanal;

    /**
     * This attribute maps to the column RFC_AGENTE_ADUANAL in the AGACE_IDC table.
     */
    private String rfcAgenteAduanal;

    /**
     * This attribute maps to the column EMAIL_AGENTE_ADUANAL in the AGACE_IDC table.
     */
    private String emailAgenteAduanal;

    /**
     * This attribute maps to the column NOMBRE_CONTRIBUYENTE in the AGACE_IDC table.
     */
    private String nombreContribuyente;

    /**
     * This attribute maps to the column RFC_CONTRIBUYENTE in the AGACE_IDC table.
     */
    private String rfcContribuyente;

    /**
     * This attribute maps to the column EMAIL_CONTRIBUYENTE in the AGACE_IDC table.
     */
    private String emailContribuyente;

    /**
     * This attribute maps to the column NOMBRE_REPRESENTANTE_LEGAL in the AGACE_IDC table.
     */
    private String nombreRepresentanteLegal;

    /**
     * This attribute maps to the column RFC_REPRESENTANTE_LEGAL in the AGACE_IDC table.
     */
    private String rfcRepresentanteLegal;

    /**
     * This attribute maps to the column EMAIL_REPRESENTANTE_LEGAL in the AGACE_IDC table.
     */
    private String emailRepresentanteLegal;

    /**
     * Method 'AgaceIdc'
     *
     */
    public AgaceIdc() {
    }

    /**
     * Method 'createPk'
     *
     * @return AgaceIdcPk
     */
    public AgaceIdcPk createPk() {
        return new AgaceIdcPk(idIdc);
    }

    /**
     * Method 'getIdIdc'
     *
     * @return Long
     */
    public Long getIdIdc() {
        return idIdc;
    }

    /**
     * Method 'setIdIdc'
     *
     * @param idIdc
     */
    public void setIdIdc(final Long idIdc) {
        this.idIdc = idIdc;
    }

    /**
     * Method 'getIdOrden'
     *
     * @return Long
     */
    public Long getIdOrden() {
        return idOrden;
    }

    /**
     * Method 'setIdOrden'
     *
     * @param idOrden
     */
    public void setIdOrden(final Long idOrden) {
        this.idOrden = idOrden;
        this.idOrdenNull = false;
    }

    /**
     * Method 'setIdOrdenNull'
     *
     * @param value
     */
    public void setIdOrdenNull(final boolean value) {
        this.idOrdenNull = value;
    }

    /**
     * Method 'isIdOrdenNull'
     *
     * @return boolean
     */
    public boolean isIdOrdenNull() {
        return idOrdenNull;
    }

    /**
     * Method 'getNombreAgenteAduanal'
     *
     * @return String
     */
    public String getNombreAgenteAduanal() {
        return nombreAgenteAduanal;
    }

    /**
     * Method 'setNombreAgenteAduanal'
     *
     * @param nombreAgenteAduanal
     */
    public void setNombreAgenteAduanal(final String nombreAgenteAduanal) {
        this.nombreAgenteAduanal = nombreAgenteAduanal;
    }

    /**
     * Method 'getRfcAgenteAduanal'
     *
     * @return String
     */
    public String getRfcAgenteAduanal() {
        return rfcAgenteAduanal;
    }

    /**
     * Method 'setRfcAgenteAduanal'
     *
     * @param rfcAgenteAduanal
     */
    public void setRfcAgenteAduanal(final String rfcAgenteAduanal) {
        this.rfcAgenteAduanal = rfcAgenteAduanal;
    }

    /**
     * Method 'getEmailAgenteAduanal'
     *
     * @return String
     */
    public String getEmailAgenteAduanal() {
        return emailAgenteAduanal;
    }

    /**
     * Method 'setEmailAgenteAduanal'
     *
     * @param emailAgenteAduanal
     */
    public void setEmailAgenteAduanal(final String emailAgenteAduanal) {
        this.emailAgenteAduanal = emailAgenteAduanal;
    }

    /**
     * Method 'getNombreContribuyente'
     *
     * @return String
     */
    public String getNombreContribuyente() {
        return nombreContribuyente;
    }

    /**
     * Method 'setNombreContribuyente'
     *
     * @param nombreContribuyente
     */
    public void setNombreContribuyente(final String nombreContribuyente) {
        this.nombreContribuyente = nombreContribuyente;
    }

    /**
     * Method 'getRfcContribuyente'
     *
     * @return String
     */
    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    /**
     * Method 'setRfcContribuyente'
     *
     * @param rfcContribuyente
     */
    public void setRfcContribuyente(final String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    /**
     * Method 'getEmailContribuyente'
     *
     * @return String
     */
    public String getEmailContribuyente() {
        return emailContribuyente;
    }

    /**
     * Method 'setEmailContribuyente'
     *
     * @param emailContribuyente
     */
    public void setEmailContribuyente(final String emailContribuyente) {
        this.emailContribuyente = emailContribuyente;
    }

    /**
     * Method 'getNombreRepresentanteLegal'
     *
     * @return String
     */
    public String getNombreRepresentanteLegal() {
        return nombreRepresentanteLegal;
    }

    /**
     * Method 'setNombreRepresentanteLegal'
     *
     * @param nombreRepresentanteLegal
     */
    public void setNombreRepresentanteLegal(final String nombreRepresentanteLegal) {
        this.nombreRepresentanteLegal = nombreRepresentanteLegal;
    }

    /**
     * Method 'getRfcRepresentanteLegal'
     *
     * @return String
     */
    public String getRfcRepresentanteLegal() {
        return rfcRepresentanteLegal;
    }

    /**
     * Method 'setRfcRepresentanteLegal'
     *
     * @param rfcRepresentanteLegal
     */
    public void setRfcRepresentanteLegal(final String rfcRepresentanteLegal) {
        this.rfcRepresentanteLegal = rfcRepresentanteLegal;
    }

    /**
     * Method 'getEmailRepresentanteLegal'
     *
     * @return String
     */
    public String getEmailRepresentanteLegal() {
        return emailRepresentanteLegal;
    }

    /**
     * Method 'setEmailRepresentanteLegal'
     *
     * @param emailRepresentanteLegal
     */
    public void setEmailRepresentanteLegal(final String emailRepresentanteLegal) {
        this.emailRepresentanteLegal = emailRepresentanteLegal;
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.AgaceIdc: ");
        ret.append("idIdc=" + idIdc);
        ret.append(", idOrden=" + idOrden);
        ret.append(", nombreAgenteAduanal=" + nombreAgenteAduanal);
        ret.append(", rfcAgenteAduanal=" + rfcAgenteAduanal);
        ret.append(", emailAgenteAduanal=" + emailAgenteAduanal);
        ret.append(", nombreContribuyente=" + nombreContribuyente);
        ret.append(", rfcContribuyente=" + rfcContribuyente);
        ret.append(", emailContribuyente=" + emailContribuyente);
        ret.append(", nombreRepresentanteLegal=" + nombreRepresentanteLegal);
        ret.append(", rfcRepresentanteLegal=" + rfcRepresentanteLegal);
        ret.append(", emailRepresentanteLegal=" + emailRepresentanteLegal);
        return ret.toString();
    }
}
