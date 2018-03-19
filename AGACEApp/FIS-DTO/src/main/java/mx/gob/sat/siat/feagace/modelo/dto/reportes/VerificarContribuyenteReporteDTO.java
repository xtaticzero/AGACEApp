package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class VerificarContribuyenteReporteDTO extends BaseModel{

    @SuppressWarnings("compatibility:9213078934490953803")
    private static final long serialVersionUID = 1L;
    
    private String estatus;
    private String rfcConsulta;
    private Date fechaConsulta;
    private boolean amparado;
    private boolean ppee;

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setRfcConsulta(String rfcConsulta) {
        this.rfcConsulta = rfcConsulta;
    }

    public String getRfcConsulta() {
        return rfcConsulta;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta != null ? new Date(fechaConsulta.getTime()): null;
    }

    public Date getFechaConsulta() {
        return (fechaConsulta != null) ? (Date) fechaConsulta.clone() : null;
    }

    public void setAmparado(boolean amparado) {
        this.amparado = amparado;
    }

    public boolean isAmparado() {
        return amparado;
    }

    public void setPpee(boolean ppee) {
        this.ppee = ppee;
    }

    public boolean isPpee() {
        return ppee;
    }
}
