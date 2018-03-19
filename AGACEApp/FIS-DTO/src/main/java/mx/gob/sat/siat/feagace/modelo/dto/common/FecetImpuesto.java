/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */

package mx.gob.sat.siat.feagace.modelo.dto.common;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececConcepto;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececTipoImpuesto;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;


public class FecetImpuesto extends BaseModel {

    @SuppressWarnings("compatibility:-4908475907731806040")
    private static final long serialVersionUID = 397982933660766893L;
    
    private static final int CONSTANT_HASCODE = 59;

    /**
     * This attribute maps to the column ID_IMPUESTO in the FECET_IMPUESTO table.
     */
    private BigDecimal idImpuesto;

    /**
     * This attribute maps to the column ID_PROPUESTA in the FECET_IMPUESTO table.
     */
    private BigDecimal idPropuesta;

    /**
     * This attribute maps to the column ID_TIPO_IMPUESTO in the FECET_IMPUESTO table.
     */
    private BigDecimal idTipoImpuesto;

    /**
     * This attribute maps to the column MONTO in the FECET_IMPUESTO table.
     */
    private BigDecimal monto;

    /**
     * This attribute maps to the column PERIODO_INICIAL in the FECET_IMPUESTO table.
     */
    private Date periodoInicial;

    /**
     * This attribute maps to the column PERIODO_FINAL in the FECET_IMPUESTO table.
     */
    private Date periodoFinal;

    /**
     * This attribute maps to the column FECHA_BAJA in the FECET_IMPUESTO table.
     */
    private Date fechaBaja;

    private FececTipoImpuesto fececTipoImpuesto;
    
    private FececConcepto fececConcepto;
    
    private BigDecimal idConcepto;

    private BigDecimal idRetroalimentacion;

    /**
     * Method 'FecetImpuesto'
     *
     */
    public FecetImpuesto() {
    }

    /**
     * Method 'getIdImpuesto'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdImpuesto() {
        return idImpuesto;
    }

    /**
     * Method 'setIdImpuesto'
     *
     * @param idImpuesto
     */
    public void setIdImpuesto(final BigDecimal idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    /**
     * Method 'getIdPropuesta'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdPropuesta() {
        return idPropuesta;
    }

    /**
     * Method 'setIdPropuesta'
     *
     * @param idPropuesta
     */
    public void setIdPropuesta(final BigDecimal idPropuesta) {
        this.idPropuesta = idPropuesta;
    }

    /**
     * Method 'getIdTipoImpuesto'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    /**
     * Method 'setIdTipoImpuesto'
     *
     * @param idTipoImpuesto
     */
    public void setIdTipoImpuesto(final BigDecimal idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    /**
     * Method 'getMonto'
     *
     * @return BigDecimal
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * Method 'setMonto'
     *
     * @param monto
     */
    public void setMonto(final BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * Method 'getPeriodoInicial'
     *
     * @return Date
     */
    public Date getPeriodoInicial() {
        return (periodoInicial != null) ? (Date)periodoInicial.clone() : null;
    }

    /**
     * Method 'setPeriodoInicial'
     *
     * @param periodoInicial
     */
    public void setPeriodoInicial(final Date periodoInicial) {
        this.periodoInicial = periodoInicial != null ? new Date(periodoInicial.getTime()) : null;
    }

    /**
     * Method 'getPeriodoFinal'
     *
     * @return String
     */
    public Date getPeriodoFinal() {
        return (periodoFinal != null) ? (Date)periodoFinal.clone() : null;
    }

    /**
     * Method 'setPeriodoFinal'
     *
     * @param periodoFinal
     */
    public void setPeriodoFinal(final Date periodoFinal) {
        this.periodoFinal = periodoFinal != null ? new Date(periodoFinal.getTime()) : null;
    }

    /**
     * Method 'getFechaBaja'
     *
     * @return Date
     */
    public Date getFechaBaja() {
        return (fechaBaja != null) ? (Date)fechaBaja.clone() : null;
    }

    /**
     * Method 'setPeriodoFinal'
     *
     * @param periodoFinal
     */
    public void setFechaBaja(final Date fechaBaja) {
        this.fechaBaja = fechaBaja != null ? new Date(fechaBaja.getTime()) : null;
    }

    public void setFececTipoImpuesto(FececTipoImpuesto fececTipoImpuesto) {
        this.fececTipoImpuesto = fececTipoImpuesto;
    }

    public FececTipoImpuesto getFececTipoImpuesto() {
        return fececTipoImpuesto;
    }
    
    public void setIdConcepto(BigDecimal idConcepto) {
        this.idConcepto = idConcepto;
    }

    public BigDecimal getIdConcepto() {
        return idConcepto;
    }

    /**
     * Method 'createPk'
     *
     * @return FecetImpuestoPk
     */
    public FecetImpuestoPk createPk() {
        return new FecetImpuestoPk(idImpuesto);
    }

    /**
     * Method 'toString'
     *
     * @return String
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("mx.gob.sat.siat.feagace.modelo.dto.FecetImpuesto: ");
        ret.append("idImpuesto=" + idImpuesto);
        ret.append(", idPropuesta=" + idPropuesta);
        ret.append(", idTipoImpuesto=" + idTipoImpuesto);
        ret.append(", monto=" + monto);
        ret.append(", periodoInicial=" + periodoInicial);
        ret.append(", periodoFinal=" + periodoFinal);
        ret.append(", fechaBaja=" + fechaBaja);
        return ret.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FecetImpuesto other = (FecetImpuesto) obj;
        if (this.idImpuesto != other.idImpuesto && (this.idImpuesto == null || !this.idImpuesto.equals(other.idImpuesto))) {
            return false;
        }
        if (this.idPropuesta != other.idPropuesta && (this.idPropuesta == null || !this.idPropuesta.equals(other.idPropuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Constantes.ENTERO_SIETE;
        hash = CONSTANT_HASCODE * hash + (this.idImpuesto != null ? this.idImpuesto.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.idPropuesta != null ? this.idPropuesta.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.idTipoImpuesto != null ? this.idTipoImpuesto.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.monto != null ? this.monto.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.periodoInicial != null ? this.periodoInicial.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.periodoFinal != null ? this.periodoFinal.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.fechaBaja != null ? this.fechaBaja.hashCode() : 0);
        hash = CONSTANT_HASCODE * hash + (this.fececTipoImpuesto != null ? this.fececTipoImpuesto.hashCode() : 0);
        return hash;
    }

	public FececConcepto getFececConcepto() {
		return fececConcepto;
	}

	public void setFececConcepto(FececConcepto fececConcepto) {
		this.fececConcepto = fececConcepto;
	}

	public BigDecimal getIdRetroalimentacion() {
		return idRetroalimentacion;
	}

	public void setIdRetroalimentacion(BigDecimal idRetroalimentacion) {
		this.idRetroalimentacion = idRetroalimentacion;
	}


    
    
    
    
}
