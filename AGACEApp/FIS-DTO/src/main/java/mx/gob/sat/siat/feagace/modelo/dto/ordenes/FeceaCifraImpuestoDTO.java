package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FeceaCifraImpuestoDTO extends BaseModel {

	private static final long serialVersionUID = 1L;

	private BigDecimal idCifraImpuesto;
	
	private FeceaCifraTipoCifraDTO tipoCifra;
	
	private FeceaImpuestoConceptoDTO impuestoConcepto;		

	private Date fechaPago;
	
	private BigDecimal importe;
	
	private BigDecimal actualizaciones;
	
	private BigDecimal multas;
	
	private BigDecimal recargos;
	
	private BigDecimal total;

	private String observaciones;
	
	private Date fechaInicio;
	
	private Date fechaFin;	
	
	private List<FecetDocCifraDTO> listaDocumentosCifra;	
	
	private FecetParcialidadCifraDTO parcialidad;	
	
	private BigDecimal idCifra;
	
	private BigDecimal derivaAntecedente;
	
	private BigDecimal idHistoricoCifra;
	
	public BigDecimal getIdHistoricoCifra() {
        return idHistoricoCifra;
    }

    public void setIdHistoricoCifra(BigDecimal idHistoricoCifra) {
        this.idHistoricoCifra = idHistoricoCifra;
    }

    public BigDecimal getDerivaAntecedente() {
        return derivaAntecedente;
    }

    public void setDerivaAntecedente(BigDecimal derivaAntecedente) {
        this.derivaAntecedente = derivaAntecedente;
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

	public FecetParcialidadCifraDTO getParcialidad() {
		return parcialidad;
	}

	public void setParcialidad(FecetParcialidadCifraDTO parcialidad) {
		this.parcialidad = parcialidad;
	}

	public BigDecimal getIdCifraImpuesto() {
		return idCifraImpuesto;
	}

	public void setIdCifraImpuesto(BigDecimal idCifraImpuesto) {
		this.idCifraImpuesto = idCifraImpuesto;
	}

	public Date getFechaPago() {
		return (fechaPago != null) ? (Date) fechaPago.clone() : null;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = (fechaPago != null) ? (Date) fechaPago.clone() : null;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getActualizaciones() {
		return actualizaciones;
	}

	public void setActualizaciones(BigDecimal actualizaciones) {
		this.actualizaciones = actualizaciones;
	}

	public BigDecimal getMultas() {
		return multas;
	}

	public void setMultas(BigDecimal multas) {
		this.multas = multas;
	}

	public BigDecimal getRecargos() {
		return recargos;
	}

	public void setRecargos(BigDecimal recargos) {
		this.recargos = recargos;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

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
		
	public FeceaCifraTipoCifraDTO getTipoCifra() {
		return tipoCifra;
	}

	public void setTipoCifra(FeceaCifraTipoCifraDTO tipoCifra) {
		this.tipoCifra = tipoCifra;
	}
	
	public FeceaImpuestoConceptoDTO getImpuestoConcepto() {
		return impuestoConcepto;
	}

	public void setImpuestoConcepto(FeceaImpuestoConceptoDTO impuestoConcepto) {
		this.impuestoConcepto = impuestoConcepto;
	}
	
	public List<FecetDocCifraDTO> getListaDocumentosCifra() {
		return listaDocumentosCifra;
	}

	public void setListaDocumentosCifra(List<FecetDocCifraDTO> listaDocumentosCifra) {
		this.listaDocumentosCifra = listaDocumentosCifra;
	}

}
