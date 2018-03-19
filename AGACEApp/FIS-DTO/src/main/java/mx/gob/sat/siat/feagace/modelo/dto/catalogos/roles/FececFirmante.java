/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FececFirmante extends BaseModel {
    
    private static final long serialVersionUID = 1L;

    /**
     * This attribute maps to the column ID_FIRMANTE in the FECEC_FIRMANTE
     * table.
     */
    private BigDecimal idFirmante;

    /**
     * This attribute maps to the column RFC in the FECEC_FIRMANTE table.
     */
    private String rfc;

    /**
     * This attribute maps to the column NOMBRE in the FECEC_FIRMANTE table.
     */
    private String nombre;

    /**
     * This attribute maps to the column FECHA_CREACION in the FECEC_FIRMANTE
     * table.
     */
    private Date fechaCreacion;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECEC_FIRMANTE table.
     */
    private Date fechaBaja;

    /**
     * This attribute maps to the column ID_ARACE in the FECEC_FIRMANTE table.
     */
    private BigDecimal idArace;

    /**
     * This attribute maps to the column CORREO in the FECEC_FIRMANTE table.
     */
    private String correo;

    /**
     * Method 'getIdFirmante'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdFirmante() {
        return idFirmante;
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
     * Method 'getFechaCreacion'
     * 
     * @return Date
     */
    public Date getFechaCreacion() {
        return (fechaCreacion != null) ? (Date) fechaCreacion.clone() : null;
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
        return (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
    }

    /**
     * Method 'setFechaBaja'
     * 
     * @param fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = (fechaBaja != null) ? (Date) fechaBaja.clone() : null;
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
     * Method 'createPk'
     * 
     * @return FececFirmantePk
     */
    public FececFirmantePk createPk() {
        return new FececFirmantePk(idFirmante);
    }

    /**
     * Method 'toString'
     * 
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.dto.FececFirmante: ");
        ret.append("idFirmante=").append(idFirmante);
        ret.append(", rfc=").append(rfc);
        ret.append(", nombre=").append(nombre);
        ret.append(", fechaCreacion=").append(fechaCreacion);
        ret.append(", fechaBaja=").append(fechaBaja);
        ret.append(", idArace=").append(idArace);
        ret.append(", correo=").append(correo);
        return ret.toString();
    }

}
