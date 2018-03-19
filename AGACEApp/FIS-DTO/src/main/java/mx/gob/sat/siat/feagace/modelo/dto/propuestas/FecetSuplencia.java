package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

/**
 * 
 */
public class FecetSuplencia extends BaseModel {

    @SuppressWarnings("compatibility:-616118576887189382")
    private static final long serialVersionUID = -2165039446894859355L;
    
    private BigDecimal idSuplencia;
    private BigDecimal idFirmanteASuplir;
    private BigDecimal idFirmanteSuplente;
    private String rfcSuplente;
    private BigDecimal idMotivoSuplencia;
    private Date fechaInicio;
    private Date fechaFin;

    public void setIdSuplencia(BigDecimal idSuplencia) {
        this.idSuplencia = idSuplencia;
    }

    public BigDecimal getIdSuplencia() {
        return idSuplencia;
    }

    public void setIdFirmanteASuplir(BigDecimal idFirmanteASuplir) {
        this.idFirmanteASuplir = idFirmanteASuplir;
    }

    public BigDecimal getIdFirmanteASuplir() {
        return idFirmanteASuplir;
    }

    public void setIdFirmanteSuplente(BigDecimal idFirmanteSuplente) {
        this.idFirmanteSuplente = idFirmanteSuplente;
    }

    public BigDecimal getIdFirmanteSuplente() {
        return idFirmanteSuplente;
    }

    public void setIdMotivoSuplencia(BigDecimal idMotivoSuplencia) {
        this.idMotivoSuplencia = idMotivoSuplencia;
    }

    public BigDecimal getIdMotivoSuplencia() {
        return idMotivoSuplencia;
    }

    public void setFechaInicio(Date fechaInicio) {        
        this.fechaInicio = (fechaInicio != null) ? (Date)fechaInicio.clone() : null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date)fechaInicio.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setRfcSuplente(String rfcSuplente) {
        this.rfcSuplente = rfcSuplente;
    }

    public String getRfcSuplente() {
        return rfcSuplente;
    }
}
