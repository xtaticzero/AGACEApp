package mx.gob.sat.siat.feagace.modelo.dto.common;


import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetDetalleNyV extends BaseModel{
    @SuppressWarnings("compatibility:1184741226434095582")
    private static final long serialVersionUID = 6476348215107928003L;

    private Long idNyV;
    private Long idNyVPadre;
    private String folioNyV;
    private String folioActoAdmvo;
    private Date fecNotificacionNyV;
    private Date fecNotificacionContNyV;
    private Date fecSurteEfectosNyV;
    private Date fecAltaNyV;
    private String rutaAcuseNyv;
    private String rfcNotifica;

    public FecetDetalleNyV() {
        super();
    }
    
    public void setIdNyV(Long idNyV) {
        this.idNyV = idNyV;
    }

    public Long getIdNyV() {
        return idNyV;
    }
    
    public void setFolioActoAdmvo(String folioActoAdmvo) {
        this.folioActoAdmvo = folioActoAdmvo;
    }

    public String getFolioActoAdmvo() {
        return folioActoAdmvo;
    }
   
    public void setFolioNyV(String folioNyV) {
        this.folioNyV = folioNyV;
    }

    public String getFolioNyV() {
        return folioNyV;
    }

    public void setFecNotificacionNyV(Date fecNotificacionNyV) {
        this.fecNotificacionNyV = fecNotificacionNyV != null ? new Date(fecNotificacionNyV.getTime()): null;
    }

    public Date getFecNotificacionNyV() {
        return (fecNotificacionNyV != null) ? (Date) fecNotificacionNyV.clone() : null;
    }

    public void setFecNotificacionContNyV(Date fecNotificacionContNyV) {
        this.fecNotificacionContNyV = fecNotificacionContNyV != null ? new Date(fecNotificacionContNyV.getTime()) : null;
    }

    public Date getFecNotificacionContNyV() {
        return (fecNotificacionContNyV != null) ? (Date)fecNotificacionContNyV.clone() : null;
    }

    public void setFecSurteEfectosNyV(Date fecSurteEfectosNyV) {
        this.fecSurteEfectosNyV = fecSurteEfectosNyV != null ? new Date(fecSurteEfectosNyV.getTime()) : null;
    }

    public Date getFecSurteEfectosNyV() {
        return (fecSurteEfectosNyV != null)? (Date)fecSurteEfectosNyV.clone() : null;
    }

    public void setFecAltaNyV(Date fecAltaNyV) {
        this.fecAltaNyV = (fecAltaNyV != null) ? (Date) fecAltaNyV.clone() : null;        
    }

    public Date getFecAltaNyV() {
        return (fecAltaNyV != null) ? (Date) fecAltaNyV.clone() : null;        
    }

    public String getRutaAcuseNyv() {
        return rutaAcuseNyv;
    }

    public void setRutaAcuseNyv(String rutaAcuseNyv) {
        this.rutaAcuseNyv = rutaAcuseNyv;
    }

	public Long getIdNyVPadre() {
		return idNyVPadre;
	}

	public void setIdNyVPadre(Long idNyVPadre) {
		this.idNyVPadre = idNyVPadre;
	}

	public String getRfcNotifica() {
		return rfcNotifica;
	}

	public void setRfcNotifica(String rfcNotifica) {
		this.rfcNotifica = rfcNotifica;
	}
}
