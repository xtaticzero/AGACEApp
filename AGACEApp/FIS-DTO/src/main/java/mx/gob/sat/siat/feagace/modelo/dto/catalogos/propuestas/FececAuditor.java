/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececAuditor extends BaseModel {
    
    @SuppressWarnings("compatibility:5199920054470055561")
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_AUDITOR in the FECEC_AUDITOR table.
     */
    private BigDecimal idAuditor;

    /**
     * This attribute maps to the column RFC in the FECEC_AUDITOR table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_AUDITOR table.
     */
    private String nombre;

    /**
     * This attribute maps to the column CORREO in the FECEC_AUDITOR table.
     */
    private String correo;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECEC_AUDITOR table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECEC_AUDITOR table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column ESTADO in the FECEC_AUDITOR table.
     */
    private String estado;

    /**
     * This attribute maps to the column ID_ARACE in the FECEC_AUDITOR table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column ID_FIRMANTE in the FECEC_FIRMANTE table.
     */
    private BigDecimal idFirmante;

    /**
     * Method 'FececAuditor'
     *
     */
    public FececAuditor() {
    }

    /**
     * Method 'getIdAuditor'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdAuditor() {
        return idAuditor;
    }

    /**
     * Method 'setIdAuditor'
     *
     * @param idAuditor
     */
    public void setIdAuditor(BigDecimal idAuditor) {
        this.idAuditor = idAuditor;
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
     * Method 'getFechaCreacion'
     *
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date)fechaCreacion.clone() : null;
    }

    /**
     * Method 'setFechaCreacion'
     *
     * @param fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? new Date(fechaCreacion.getTime()) : null;
    }

    /**
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     *
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    /**
     * Method 'getEstado'
     *
     * @return String
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Method 'setEstado'
     *
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Method 'getIdArace'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdArace() {
        return idArace;
    }

    /**
     * Method 'setIdArace'
     *
     * @param idArace
     */
    public void setIdArace(BigDecimal idArace) {
        this.idArace = idArace;
    }

    /**
     * Method 'setIdFirmante'
     *
     * @param idFirmante
     */
    public void setIdFirmante(BigDecimal idFirmante) {
        this.idFirmante = idFirmante;
    }

    /**
     * Method 'getIdFirmante'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdFirmante() {
        return idFirmante;
    }

    /**
     * Method 'createPk'
     *
     * @return FececAuditorPk
     */
    public FececAuditorPk createPk() {
        return new FececAuditorPk(idAuditor);
    }
    
}
