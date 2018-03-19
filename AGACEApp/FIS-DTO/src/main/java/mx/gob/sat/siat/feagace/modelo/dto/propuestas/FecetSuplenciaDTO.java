package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetSuplenciaDTO extends BaseModel {
    @SuppressWarnings("compatibility:-3818783300454505914")
    private static final long serialVersionUID = 1L;

    private BigDecimal idSuplencia;
    private BigDecimal idFirmanteASuplir;
    private BigDecimal idFrimanteSuplente;
    private BigDecimal idMotivoSuplencia;
    private BigDecimal idStatus;
    private String estatusDesc;
    private String motivoSuplenciaDesc;
    private String nombreFirmanteASuplir;
    private String nombreFirmanteSuplente;
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

    public void setIdFrimanteSuplente(BigDecimal idFrimanteSuplente) {
        this.idFrimanteSuplente = idFrimanteSuplente;
    }

    public BigDecimal getIdFrimanteSuplente() {
        return idFrimanteSuplente;
    }

    public void setIdMotivoSuplencia(BigDecimal idMotivoSuplencia) {
        this.idMotivoSuplencia = idMotivoSuplencia;
    }

    public BigDecimal getIdMotivoSuplencia() {
        return idMotivoSuplencia;
    }

    public void setIdStatus(BigDecimal idStatus) {
        this.idStatus = idStatus;
    }

    public BigDecimal getIdStatus() {
        return idStatus;
    }

    public void setEstatusDesc(String estatusDesc) {
        this.estatusDesc = estatusDesc;
    }

    public String getEstatusDesc() {
        return estatusDesc;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date)fechaInicio.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date)fechaFin.clone() : null;
    }

    public void setMotivoSuplenciaDesc(String motivoSuplenciaDesc) {
        this.motivoSuplenciaDesc = motivoSuplenciaDesc;
    }

    public String getMotivoSuplenciaDesc() {
        return motivoSuplenciaDesc;
    }

    public void setNombreFirmanteASuplir(String nombreFirmanteASuplir) {
        this.nombreFirmanteASuplir = nombreFirmanteASuplir;
    }

    public String getNombreFirmanteASuplir() {
        return nombreFirmanteASuplir;
    }

    public void setNombreFirmanteSuplente(String nombreFirmanteSuplente) {
        this.nombreFirmanteSuplente = nombreFirmanteSuplente;
    }

    public String getNombreFirmanteSuplente() {
        return nombreFirmanteSuplente;
    }
}
