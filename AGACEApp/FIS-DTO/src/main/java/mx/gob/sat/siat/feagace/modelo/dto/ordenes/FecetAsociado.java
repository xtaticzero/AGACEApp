/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.io.InputStream;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetAsociado extends BaseModel {


    @SuppressWarnings("compatibility:3112530827389156343")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_ASOCIADO in the FECEC_ASOCIADOS
     * table.
     */
    private BigDecimal idAsociado;

    /**
     * This attribute maps to the column ID_CONTRIBUYENTE in the FECEC_ASOCIADOS
     * table.
     */
    private String rfcContribuyente;

    /**
     * This attribute maps to the column ID_ORDEN in the FECEC_ASOCIADOS table.
     */
    private BigDecimal idOrden;

    /**
     * This attribute maps to the column ID_TIPO_ASOCIADO in the FECEC_ASOCIADOS
     * table.
     */
    private BigDecimal idTipoAsociado;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_ASOCIADOS table.
     */
    private String nombre;

    /**
     * This attribute maps to the column RFC in the FECEC_ASOCIADOS table.
     */
    private String rfc;

    /**
     * This attribute maps to the column CORREO in the FECEC_ASOCIADOS table.
     */
    private String correo;

    /**
     * This attribute maps to the column TIPO_ASOCIADO in the FECEC_ASOCIADOS
     * table.
     */
    private String tipoAsociado;

    /**
     * This attribute maps to the column MEDIO_CONTACTO in the FECEC_ASOCIADOS
     * table.
     */
    private String medioContacto;

    /**
     * This attribute maps to the column MEDIO_CONTACTO in the FECEC_ASOCIADOS
     * table.
     */
    private boolean medioContactoBoolean;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECEC_ASOCIADOS
     * table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column FECHA_ULTIMA_MOD in the FECEC_ASOCIADOS
     * table.
     */
    private Date fechaUltimaMod;

    /**
     * This attribute maps to the column FECHA_ULTIMA_MOD_IDC in the
     * FECEC_ASOCIADOS table.
     */
    private Date fechaUltimaModIdc;

    private String estatus;

    private transient InputStream archivo;

    /**
     * Method 'FececAsociados'
     *
     */
    public FecetAsociado() {
    }

    /**
     * Method 'getIdAsociado'
     *
     * @return long
     */
    public BigDecimal getIdAsociado() {
        return idAsociado;
    }

    /**
     * Method 'setIdAsociado'
     *
     * @param idAsociado
     */
    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
    }

    /**
     * Method 'getNombre'
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method 'setNombre'
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method 'getRfc'
     *
     * @return String
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Method 'setRfc'
     *
     * @param rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Method 'getCorreo'
     *
     * @return String
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Method 'setCorreo'
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Method 'getIdTipoAsociado'
     *
     * @return long
     */
    public BigDecimal getIdTipoAsociado() {
        return idTipoAsociado;
    }

    /**
     * Method 'setIdTipoAsociado'
     *
     * @param idTipoAsociado
     */
    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
        this.idTipoAsociado = idTipoAsociado;
    }

    /**
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return fechaBaja != null ? (Date)fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? (Date)fechaBaja.clone() : null;
    }

    /**
     * Method 'createPk'
     *
     * @return FececAsociadosPk
     */
    public FecetAsociadoPk createPk() {
        return new FecetAsociadoPk(idAsociado);
    }


    public void setIdOrden(BigDecimal idOrden) {
        this.idOrden = idOrden;
    }

    public BigDecimal getIdOrden() {
        return idOrden;
    }

    public void setTipoAsociado(String tipoAsociado) {
        this.tipoAsociado = tipoAsociado;
    }

    public String getTipoAsociado() {
        return tipoAsociado;
    }

    public void setMedioContacto(String medioContacto) {
        this.medioContacto = medioContacto;
    }

    public String getMedioContacto() {
        return medioContacto;
    }

    public void setFechaUltimaMod(Date fechaUltimaMod) {
        this.fechaUltimaMod = fechaUltimaMod != null ? (Date)fechaUltimaMod.clone() : null;
    }

    public Date getFechaUltimaMod() {
        return fechaUltimaMod != null ? (Date)fechaUltimaMod.clone() : null;
    }

    public void setArchivo(InputStream archivo) {
        this.archivo = archivo;
    }

    public InputStream getArchivo() {
        return archivo;
    }

    public void setFechaUltimaModIdc(Date fechaUltimaModIdc) {
        this.fechaUltimaModIdc = fechaUltimaModIdc != null ? (Date)fechaUltimaModIdc.clone() : null;
    }

    public Date getFechaUltimaModIdc() {
        return fechaUltimaModIdc != null ? (Date)fechaUltimaModIdc.clone() : null;
    }

    public void setMedioContactoBoolean(boolean medioContactoBoolean) {
        this.medioContactoBoolean = medioContactoBoolean;
    }

    public boolean isMedioContactoBoolean() {
        return medioContactoBoolean;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }
}
