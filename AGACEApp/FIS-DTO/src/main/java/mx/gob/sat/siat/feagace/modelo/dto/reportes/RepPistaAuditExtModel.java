package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class RepPistaAuditExtModel extends BaseModel {

    @SuppressWarnings("compatibility:5395966697015019236")
    private static final long serialVersionUID = -8105273291697081620L;
    
    private String rfcContribuyente;
    private String rfcRepLegal;
    private String rfcApodLegal;
    private String rfcAgentAduanal;
    private String rfcApodLegalRepLegal;
    private String numOrden;
    private String idRegistro;

    private Date fechaBusqInicio;
    private Date fechaBusqFin;
    private Date fechaInicio;
    private Date fechaFin;

    private boolean pnlTabla;
    private boolean pnlBusqueda;
    
    private boolean campoActivoRfcContribuyente;
    private boolean campoActivoRfcRepLegal;
    private boolean campoActivoRfcApodLegal;
    private boolean campoActivoRfcAgentAduanal;
    private boolean campoActivoRfcApodLegalRepLegal;
    private boolean campoActivoNumOreden;
    private boolean campoActivoIdRegistro;
    private boolean campoActivoCanlendar;
    
    public RepPistaAuditExtModel() {
        super();
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

    public void setNumOrden(String numOreden) {
        this.numOrden = numOreden;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setFechaBusqInicio(Date fechaBusqInicio) {
        this.fechaBusqInicio = (fechaBusqInicio != null) ? (Date) fechaBusqInicio.clone() : null;
    }

    public Date getFechaBusqInicio() {
        return (fechaBusqInicio != null) ? (Date) fechaBusqInicio.clone() : null;
    }

    public void setFechaBusqFin(Date fechaBusqFin) {
        this.fechaBusqFin = (fechaBusqFin != null) ? (Date) fechaBusqFin.clone() : null;
    }

    public Date getFechaBusqFin() {
        return (fechaBusqFin != null) ? (Date) fechaBusqFin.clone() : null;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public Date getFechaInicio() {
        return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public Date getFechaFin() {
        return (fechaFin != null) ? (Date) fechaFin.clone() : null;
    }

    public void setPnlTabla(boolean pnlTabla) {
        this.pnlTabla = pnlTabla;
    }

    public boolean isPnlTabla() {
        return pnlTabla;
    }

    public void setPnlBusqueda(boolean pnlBusqueda) {
        this.pnlBusqueda = pnlBusqueda;
    }

    public boolean isPnlBusqueda() {
        return pnlBusqueda;
    }

    public void setCampoActivoRfcContribuyente(boolean campoActivoRfcContribuyente) {
        this.campoActivoRfcContribuyente = campoActivoRfcContribuyente;
    }

    public boolean isCampoActivoRfcContribuyente() {
        return campoActivoRfcContribuyente;
    }

    public void setCampoActivoRfcRepLegal(boolean campoActivoRfcRepLegal) {
        this.campoActivoRfcRepLegal = campoActivoRfcRepLegal;
    }

    public boolean isCampoActivoRfcRepLegal() {
        return campoActivoRfcRepLegal;
    }

    public void setCampoActivoRfcApodLegal(boolean campoActivoRfcApodLegal) {
        this.campoActivoRfcApodLegal = campoActivoRfcApodLegal;
    }

    public boolean isCampoActivoRfcApodLegal() {
        return campoActivoRfcApodLegal;
    }

    public void setCampoActivoRfcAgentAduanal(boolean campoActivoRfcAgentAduanal) {
        this.campoActivoRfcAgentAduanal = campoActivoRfcAgentAduanal;
    }

    public boolean isCampoActivoRfcAgentAduanal() {
        return campoActivoRfcAgentAduanal;
    }

    public void setCampoActivoRfcApodLegalRepLegal(boolean campoActivoRfcApodLegalRepLegal) {
        this.campoActivoRfcApodLegalRepLegal = campoActivoRfcApodLegalRepLegal;
    }

    public boolean isCampoActivoRfcApodLegalRepLegal() {
        return campoActivoRfcApodLegalRepLegal;
    }

    public void setCampoActivoNumOreden(boolean campoActivoNumOreden) {
        this.campoActivoNumOreden = campoActivoNumOreden;
    }

    public boolean isCampoActivoNumOreden() {
        return campoActivoNumOreden;
    }

    public void setCampoActivoIdRegistro(boolean campoActivoIdRegistro) {
        this.campoActivoIdRegistro = campoActivoIdRegistro;
    }

    public boolean isCampoActivoIdRegistro() {
        return campoActivoIdRegistro;
    }

    public void setCampoActivoCanlendar(boolean campoActivoCanlendar) {
        this.campoActivoCanlendar = campoActivoCanlendar;
    }

    public boolean isCampoActivoCanlendar() {
        return campoActivoCanlendar;
    }
}
