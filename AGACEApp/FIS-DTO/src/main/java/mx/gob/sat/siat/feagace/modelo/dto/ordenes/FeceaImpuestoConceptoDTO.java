package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;

public class FeceaImpuestoConceptoDTO extends BaseModel {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal idImpuestoConcepto;
	
	private FececTipoImpuesto impuesto;
	
	private FececConcepto concepto;
	
	public BigDecimal getIdImpuestoConcepto() {
		return idImpuestoConcepto;
	}

	public void setIdImpuestoConcepto(BigDecimal idImpuestoConcepto) {
		this.idImpuestoConcepto = idImpuestoConcepto;
	}

	public FececTipoImpuesto getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(FececTipoImpuesto impuesto) {
		this.impuesto = impuesto;
	}

	public FececConcepto getConcepto() {
		return concepto;
	}

	public void setConcepto(FececConcepto concepto) {
		this.concepto = concepto;
	}


}
