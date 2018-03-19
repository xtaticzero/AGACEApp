package mx.gob.sat.siat.feagace.modelo.dto.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetReasignacionInsumo extends BaseModel {

    /**
     * Serial
     */
    private static final long serialVersionUID = -4414805325128457854L;

    /**
     * This attribute maps to the column ID_REASIGNACION in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private BigDecimal idReasignacion;

    /**
     * This attribute maps to the column ID_INSUMO in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private BigDecimal idInsumo;

    /**
     * This attribute maps to the column RFC_ADMINISTRADOR_ORIGEN in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private String rfcAdministradorOrigen;

    /**
     * This attribute maps to the column RFC_ADMINISTRADOR_DESTINO in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private String rfcAdministradorDestino;

    /**
     * This attribute maps to the column MOTIVO in the FECET_REASIGNACION_INSUMO
     * table.
     */
    private String motivo;

    /**
     * This attribute maps to the column MOTIVO_RECHAZO in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private String motivoRechazo;

    /**
     * This attribute maps to the column ESTATUS in the
     * FECET_REASIGNACION_INSUMO table.
     */
    private BigDecimal estatus;

    private String nombreAdministradorOrigen;
    private String nombreAdministradorDestino;

    private String idRegistroInsumo;

    private Integer blnActivo;
    
    public Integer getBlnActivo() {
        return blnActivo;
    }

    public void setBlnActivo(Integer blnActivo) {
        this.blnActivo = blnActivo;
    }

    /**
     * Method 'FecetReasignacionInsumo'
     *
     */
    public FecetReasignacionInsumo() {
    }

    /**
     * Method 'getIdReasignacion'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdReasignacion() {
        return idReasignacion;
    }

    /**
     * Method 'setIdReasignacion'
     *
     * @param idReasignacion
     */
    public void setIdReasignacion(BigDecimal idReasignacion) {
        this.idReasignacion = idReasignacion;
    }

    /**
     * Method 'getIdInsumo'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdInsumo() {
        return idInsumo;
    }

    /**
     * Method 'setIdInsumo'
     *
     * @param idInsumo
     */
    public void setIdInsumo(BigDecimal idInsumo) {
        this.idInsumo = idInsumo;
    }

    /**
     * Method 'getRfcAdministradorOrigen'
     *
     * @return BigDecimal
     */
    public String getRfcAdministradorOrigen() {
        return rfcAdministradorOrigen;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetInsumoPk
     */
    public FecetReasignacionInsumoPk createPk() {
        return new FecetReasignacionInsumoPk(idReasignacion);
    }

    /**
     * Method 'setRfcAdministradorOrigen'
     *
     * @param rfcAdministradorOrigen
     */
    public void setRfcAdministradorOrigen(String rfcAdministradorOrigen) {
        this.rfcAdministradorOrigen = rfcAdministradorOrigen;
    }

    /**
     * Method 'getRfcAdministradorDestino'
     *
     * @return BigDecimal
     */
    public String getRfcAdministradorDestino() {
        return rfcAdministradorDestino;
    }

    /**
     * Method 'setRfcAdministradorDestino'
     *
     * @param rfcAdministradorDestino
     */
    public void setRfcAdministradorDestino(String rfcAdministradorDestino) {
        this.rfcAdministradorDestino = rfcAdministradorDestino;
    }

    /**
     * Method 'getMotivo'
     *
     * @return String
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Method 'setMotivo'
     *
     * @param motivo
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Method 'getMotivoRechazo'
     *
     * @return String
     */
    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    /**
     * Method 'setMotivoRechazo'
     *
     * @param motivoRechazo
     */
    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    /**
     * Method 'getEstatus'
     *
     * @return BigDecimal
     */
    public BigDecimal getEstatus() {
        return estatus;
    }

    /**
     * Method 'setEstatus'
     *
     * @param estatus
     */
    public void setEstatus(BigDecimal estatus) {
        this.estatus = estatus;
    }

    public void setNombreAdministradorOrigen(String nombreAdministradorOrigen) {
        this.nombreAdministradorOrigen = nombreAdministradorOrigen;
    }

    public String getNombreAdministradorOrigen() {
        return nombreAdministradorOrigen;
    }

    public void setNombreAdministradorDestino(String nombreAdministradorDestino) {
        this.nombreAdministradorDestino = nombreAdministradorDestino;
    }

    public String getNombreAdministradorDestino() {
        return nombreAdministradorDestino;
    }

    public void setIdRegistroInsumo(String idRegistroInsumo) {
        this.idRegistroInsumo = idRegistroInsumo;
    }

    public String getIdRegistroInsumo() {
        return idRegistroInsumo;
    }
}
