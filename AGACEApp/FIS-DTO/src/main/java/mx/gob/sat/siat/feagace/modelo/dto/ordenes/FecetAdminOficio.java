package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetAdminOficio extends BaseModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private BigDecimal idAdminOficio;
    private boolean blnDocReq;
    private boolean blnDocPro;
    private boolean blnPlazos;
    private boolean blnActivo;
    private Date fecha;

    public void setIdAdminOficio(BigDecimal idAdminOficio) {
        this.idAdminOficio = idAdminOficio;
    }

    public BigDecimal getIdAdminOficio() {
        return idAdminOficio;
    }

    public void setBlnDocReq(boolean blnDocReq) {
        this.blnDocReq = blnDocReq;
    }

    public boolean isBlnDocReq() {
        return blnDocReq;
    }

    public void setBlnPlazos(boolean blnPlazos) {
        this.blnPlazos = blnPlazos;
    }

    public boolean isBlnPlazos() {
        return blnPlazos;
    }

    public void setBlnActivo(boolean blnActivo) {
        this.blnActivo = blnActivo;
    }

    public boolean isBlnActivo() {
        return blnActivo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
    }

    public Date getFecha() {
        return (fecha != null) ? (Date) fecha.clone() : null;
    }

    public FecetAdminOficioPk createPk() {
        return new FecetAdminOficioPk(idAdminOficio);
    }

    public void setBlnDocPro(boolean blnDocPro) {
        this.blnDocPro = blnDocPro;
    }

    public boolean isBlnDocPro() {
        return blnDocPro;
    }
}
