package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class FecetDetalleContribuyente extends BaseModel {

    @SuppressWarnings("compatibility:-4858135951515378697")
    private static final long serialVersionUID = -7146519995777569379L;
    
    private BigDecimal idDetalleContribuyente;
    private String rfcContribuyente;
    private Date fechaConsulta;
    private BigDecimal amparado;
    private BigDecimal ppee;


    public FecetDetalleContribuyente() {
    }


    public void setIdDetalleContribuyente(BigDecimal idDetalleContribuyente) {
        this.idDetalleContribuyente = idDetalleContribuyente;
    }

    public BigDecimal getIdDetalleContribuyente() {
        return idDetalleContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta != null ? (Date)(fechaConsulta.clone()) : null;
    }

    public Date getFechaConsulta() {
        return (fechaConsulta != null) ? (Date) fechaConsulta.clone()
                : null;
        
    }

    public void setAmparado(BigDecimal amparado) {
        this.amparado = amparado;
    }

    public BigDecimal getAmparado() {
        return amparado;
    }

    public void setPpee(BigDecimal ppee) {
        this.ppee = ppee;
    }

    public BigDecimal getPpee() {
        return ppee;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetDetalleEmpleadoPk
     */
    public FecetDetalleContribuyentePk createPk() {
        return new FecetDetalleContribuyentePk(idDetalleContribuyente);
    }
}
