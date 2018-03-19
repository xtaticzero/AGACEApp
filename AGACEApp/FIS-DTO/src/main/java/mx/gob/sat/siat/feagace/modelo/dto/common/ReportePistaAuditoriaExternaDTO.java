package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReportePistaAuditoriaExternaDTO extends BaseModel{

    @SuppressWarnings("compatibility:-857450806391683999")
    private static final long serialVersionUID = 8607731031164702772L;
    
    private Date fechaYHora;
    private String noCertiDigFiel;
    private String rfcContribuyente;
    private String rfcRepLegal;
    private String rfcApodLegal;
    private String rfcAgentAduanal;
    private String rfcApodLegalRepLegal;
    private String nomCertiDig;
    private String ipMaquina;
    private String modIngreso;
    private String operRealizada;
    
    private String numOreden;
    private String idRegistro;
    private Date fechaBusqInicio;
    private Date fechaBusqFin;
    
    public ReportePistaAuditoriaExternaDTO() {
        super();
    }


    public void setFechaYHora(Date fechaYHora) {
        this.fechaYHora = fechaYHora != null ? new Date(fechaYHora.getTime()) : null;
    }

    public Date getFechaYHora() {
        return (fechaYHora != null) ? (Date) fechaYHora.clone() : null;
    }

    public void setNoCertiDigFiel(String noCertiDigFiel) {
        this.noCertiDigFiel = noCertiDigFiel;
    }

    public String getNoCertiDigFiel() {
        return noCertiDigFiel;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setRfcRepLegal(String rfcRepLegal) {
        this.rfcRepLegal = rfcRepLegal;
    }

    public String getRfcRepLegal() {
        return rfcRepLegal;
    }

    public void setRfcApodLegal(String rfcApodLegal) {
        this.rfcApodLegal = rfcApodLegal;
    }

    public String getRfcApodLegal() {
        return rfcApodLegal;
    }

    public void setRfcAgentAduanal(String rfcAgentAduanal) {
        this.rfcAgentAduanal = rfcAgentAduanal;
    }

    public String getRfcAgentAduanal() {
        return rfcAgentAduanal;
    }

    public void setRfcApodLegalRepLegal(String rfcApodLegalRepLegal) {
        this.rfcApodLegalRepLegal = rfcApodLegalRepLegal;
    }

    public String getRfcApodLegalRepLegal() {
        return rfcApodLegalRepLegal;
    }

    public void setNomCertiDig(String nomCertiDig) {
        this.nomCertiDig = nomCertiDig;
    }

    public String getNomCertiDig() {
        return nomCertiDig;
    }

    public void setIpMaquina(String ipMaquina) {
        this.ipMaquina = ipMaquina;
    }

    public String getIpMaquina() {
        return ipMaquina;
    }

    public void setModIngreso(String modIngreso) {
        this.modIngreso = modIngreso;
    }

    public String getModIngreso() {
        return modIngreso;
    }

    public void setOperRealizada(String operRealizada) {
        this.operRealizada = operRealizada;
    }

    public String getOperRealizada() {
        return operRealizada;
    }

    public void setNumOreden(String numOreden) {
        this.numOreden = numOreden;
    }

    public String getNumOreden() {
        return numOreden;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setFechaBusqInicio(Date fechaBusqInicio) {
        this.fechaBusqInicio = fechaBusqInicio != null ? new Date(fechaBusqInicio.getTime()) : null;
    }

    public Date getFechaBusqInicio() {
        return (fechaBusqInicio != null) ? (Date) fechaBusqInicio.clone() :null;
    }

    public void setFechaBusqFin(Date fechaBusqFin) {
        this.fechaBusqFin = fechaBusqFin != null ? new Date(fechaBusqFin.getTime()) : null;
    }

    public Date getFechaBusqFin() {
        return (fechaBusqFin != null) ? (Date) fechaBusqFin.clone() : null;
    }

}
