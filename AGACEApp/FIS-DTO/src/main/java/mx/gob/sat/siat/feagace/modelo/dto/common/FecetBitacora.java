/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetBitacora extends BaseModel implements Serializable {
    
    /**
     * Serial
     */
    @SuppressWarnings("compatibility:-4468418036578302411")
    private static final long serialVersionUID = -6758097432239801421L;

    /**
     * This attribute maps to the column ID_BITACORA in the FECET_BITACORA
     * table.
     */
    private BigDecimal idBitacora;

    /**
     * This attribute maps to the column ID_OPERACION in the FECET_BITACORA
     * table.
     */
    private BigDecimal idOperacion;

    /**
     * This attribute maps to the column ID_TRANSACCION in the FECET_BITACORA
     * table.
     */
    private BigDecimal idInterno;

    /**
     * This attribute maps to the column IPMAQUINA in the FECET_BITACORA table.
     */
    private String ipmaquina;

    /**
     * This attribute maps to the column NOMBREMAQUINA in the FECET_BITACORA
     * table.
     */
    private String nombremaquina;

    /**
     * This attribute maps to the column RFC_USUARIO in the FECET_BITACORA
     * table.
     */
    private String rfcUsuario;

    /**
     * This attribute maps to the column RFC_APODERADO_LEGAL in the
     * FECET_BITACORA table.
     */
    private String rfcApoderadoLegal;

    /**
     * This attribute maps to the column RFC_AGENTE_ADUANAL in the
     * FECET_BITACORA table.
     */
    private String rfcAgenteAduanal;

    /**
     * This attribute maps to the column RFC_REPRESENTANTE_LEGAL in the
     * FECET_BITACORA table.
     */
    private String rfcRepresentanteLegal;

    /**
     * This attribute maps to the column FECHA in the FECET_BITACORA table.
     */
    private Date fecha;

    /**
     * This attribute maps to the column DESCRIPCION in the FECET_BITACORA
     * table.
     */
    private String descripcion;

    /**
     * This attribute maps to the column IDREGISTRO in the FECET_BITACORA table.
     */
    private String idRegistro;

    /**
     * Method 'FecetBitacora'
     * 
     */
    public FecetBitacora() {
    }

    /**
     * Method 'getIdBitacora'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdBitacora() {
        return idBitacora;
    }

    /**
     * Method 'setIdBitacora'
     * 
     * @param idBitacora
     */
    public void setIdBitacora(BigDecimal idBitacora) {
        this.idBitacora = idBitacora;
    }

    /**
     * Method 'getIdOperacion'
     * 
     * @return BigDecimal
     */
    public BigDecimal getIdOperacion() {
        return idOperacion;
    }

    /**
     * Method 'setIdOperacion'
     * 
     * @param idOperacion
     */
    public void setIdOperacion(BigDecimal idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     * Method 'getIpmaquina'
     * 
     * @return String
     */
    public String getIpmaquina() {
        return ipmaquina;
    }

    /**
     * Method 'setIpmaquina'
     * 
     * @param ipmaquina
     */
    public void setIpmaquina(String ipmaquina) {
        this.ipmaquina = ipmaquina;
    }

    /**
     * Method 'getNombremaquina'
     * 
     * @return String
     */
    public String getNombremaquina() {
        return nombremaquina;
    }

    /**
     * Method 'setNombremaquina'
     * 
     * @param nombremaquina
     */
    public void setNombremaquina(String nombremaquina) {
        this.nombremaquina = nombremaquina;
    }

    /**
     * Method 'getRfcUsuario'
     * 
     * @return String
     */
    public String getRfcUsuario() {
        return rfcUsuario;
    }

    /**
     * Method 'setRfcUsuario'
     * 
     * @param rfcUsuario
     */
    public void setRfcUsuario(String rfcUsuario) {
        this.rfcUsuario = rfcUsuario;
    }

    /**
     * Method 'getRfcApoderadoLegal'
     * 
     * @return String
     */
    public String getRfcApoderadoLegal() {
        return rfcApoderadoLegal;
    }

    /**
     * Method 'setRfcApoderadoLegal'
     * 
     * @param rfcApoderadoLegal
     */
    public void setRfcApoderadoLegal(String rfcApoderadoLegal) {
        this.rfcApoderadoLegal = rfcApoderadoLegal;
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
    public void setRfcAgenteAduanal(String rfcAgenteAduanal) {
        this.rfcAgenteAduanal = rfcAgenteAduanal;
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
    public void setRfcRepresentanteLegal(String rfcRepresentanteLegal) {
        this.rfcRepresentanteLegal = rfcRepresentanteLegal;
    }

    /**
     * Method 'getFecha'
     * 
     * @return Date
     */
    public Date getFecha() {
        return (fecha != null) ? (Date) fecha.clone() : null;
    }

    /**
     * Method 'setFecha'
     * 
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
    }

    /**
     * Method 'createPk'
     * 
     * @return FecetBitacoraPk
     */
    public FecetBitacoraPk createPk() {
        return new FecetBitacoraPk(idBitacora);
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdInterno(BigDecimal idInterno) {
        this.idInterno = idInterno;
    }

    public BigDecimal getIdInterno() {
        return idInterno;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }
}
