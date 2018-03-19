package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetCifraDTO extends BaseModel {
	
	private static final long serialVersionUID = 1L;
	
	public Date getFechaInicio() {
		return (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = (fechaInicio != null) ? (Date) fechaInicio.clone() : null;
	}

	public Date getFechaFin() {
		return (fechaFin != null) ? (Date) fechaFin.clone() : null;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = (fechaFin != null) ? (Date) fechaFin.clone() : null;
	}
	
	public List<FeceaCifraImpuestoDTO> getCifras() {
		return cifras;
	}

	public void setCifras(List<FeceaCifraImpuestoDTO> cifras) {
		this.cifras = cifras;
	}
	
	public BigDecimal getIdCifra() {
		return idCifra;
	}

	public void setIdCifra(BigDecimal idCifra) {
		this.idCifra = idCifra;
	}
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getFechaPago() {
		return (fechaPago != null) ? (Date) fechaPago.clone() : null;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = (fechaPago != null) ? (Date) fechaPago.clone() : null;
	}		
	
	public FeceaCifraTipoCifraDTO getTipoCifra() {
		return tipoCifra;
	}

	public void setTipoCifra(FeceaCifraTipoCifraDTO tipoCifra) {
		this.tipoCifra = tipoCifra;
	}
	
    public BigDecimal getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(BigDecimal consecutivo) {
        this.consecutivo = consecutivo;
    }    
    
    public int getCifra() {
        return cifra;
    }

    public void setCifra(int cifra) {
        this.cifra = cifra;
    }
    
    private int cifra;

    private FeceaCifraTipoCifraDTO tipoCifra;

	private BigDecimal total;
	
	private Date fechaPago;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
    private List<FeceaCifraImpuestoDTO> cifras;
	
	private BigDecimal idCifra;	
	
	private BigDecimal consecutivo;

}
