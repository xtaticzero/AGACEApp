package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;


public class CargaMasivaPropuestasDTO extends BaseModel{
    @SuppressWarnings("compatibility:6576507007695838197")
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("compatibility:3972841856270872712")

    private FecetPropuesta fecetPropuesta;
    private List<FecetImpuesto> fecetImpuestos;
    private boolean estatus;
    private BigDecimal unidadAdministrativa;
    private String descripcionError;
    private String rfcContribuyente;
    private String tipoRevision;
    private FecetContribuyente fecetContribuyente;
    private String periodoInicio;
    private String periodoFin;
    private String presuntivaFormat;
    private int row;
    private int cell;
    private BigDecimal presuntivo;
    private List<String> advertencias;
    private String segundoCaracter;
    private Map<TipoEmpleadoEnum, BigDecimal> unidadesCorreo;
    private boolean existeProgramador;
    private String descripcionAddImpuesto;


    private String metodoString;
    private boolean valida;

    public void setFecetPropuesta(FecetPropuesta fecetPropuesta) {
        this.fecetPropuesta = fecetPropuesta;
    }

    public FecetPropuesta getFecetPropuesta() {
        return fecetPropuesta;
    }

    public void setFecetImpuestos(List<FecetImpuesto> fecetImpuestos) {
        this.fecetImpuestos = fecetImpuestos;
    }

    public List<FecetImpuesto> getFecetImpuestos() {
        return fecetImpuestos;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setFecetContribuyente(FecetContribuyente fecetContribuyente) {
        this.fecetContribuyente = fecetContribuyente;
    }

    public FecetContribuyente getFecetContribuyente() {
        return fecetContribuyente;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getCell() {
        return cell;
    }

    public void setPresuntivo(BigDecimal presuntivo) {
        this.presuntivo = presuntivo;
    }

    public BigDecimal getPresuntivo() {
        return presuntivo;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoFin(String periodoFin) {
        this.periodoFin = periodoFin;
    }

    public String getPeriodoFin() {
        return periodoFin;
    }

    public void setPresuntivaFormat(String presuntivaFormat) {
        this.presuntivaFormat = presuntivaFormat;
    }

    public String getPresuntivaFormat() {
        return presuntivaFormat;
    }

    public void setMetodoString(String metodoString) {
        this.metodoString = metodoString;
    }

    public String getMetodoString() {
        return metodoString;
    }


    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public boolean isValida() {
        return valida;
    }

    public void setUnidadAdministrativa(BigDecimal unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public BigDecimal getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setTipoRevision(String tipoRevision) {
        this.tipoRevision = tipoRevision;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }
    
    public void setDescripcionAddImpuesto(String descripcionAddImpuesto) {
        this.descripcionAddImpuesto = descripcionAddImpuesto;
    }

    public String getDescripcionAddImpuesto() {
        return descripcionAddImpuesto;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        //151214 EOLF: Para el creador del metodo, como recomendacion: 
        //Al sobreecribir el metodo equals NO debe usarse instanceof a menos que la clase a comparar sea final
        //Referencia: http://www.javacodegeeks.com/2015/09/using-methods-common-to-all-objects.html#methods
        if (!(obj instanceof CargaMasivaPropuestasDTO)) {
            return false;
        }
        CargaMasivaPropuestasDTO cargaMasivaPropuestasDTOObj = (CargaMasivaPropuestasDTO)obj;
        if (this.getFecetContribuyente().getRfc().equals(cargaMasivaPropuestasDTOObj.getFecetContribuyente().getRfc()) &&
            this.getUnidadAdministrativa().equals(cargaMasivaPropuestasDTOObj.getUnidadAdministrativa()) &&
            this.getFecetPropuesta().getIdSubprograma().equals(cargaMasivaPropuestasDTOObj.getFecetPropuesta().getIdSubprograma()) &&
            this.getFecetPropuesta().getIdMetodo().equals(cargaMasivaPropuestasDTOObj.getFecetPropuesta().getIdMetodo()) &&
            this.getFecetPropuesta().getIdSubprograma().equals(cargaMasivaPropuestasDTOObj.getFecetPropuesta().getIdSubprograma()) &&
            this.getFecetPropuesta().getFechaInicioPeriodo().compareTo(cargaMasivaPropuestasDTOObj.getFecetPropuesta().getFechaInicioPeriodo()) ==
            0 &&
            this.getFecetPropuesta().getFechaFinPeriodo().compareTo(cargaMasivaPropuestasDTOObj.getFecetPropuesta().getFechaFinPeriodo()) ==
            0) {
            return true;
            }
        return false;
    }

    /**
     * Si se sobreescribe el metodo "equals" debe agregarse y sobreescribirse tambien el metodo "hashCode"
     * @author eolf
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.getFecetContribuyente().getRfc() == null) ? 0 : this.getFecetContribuyente().getRfc().hashCode());
        result = prime * result + ((this.getUnidadAdministrativa() == null) ? 0 : this.getUnidadAdministrativa().hashCode());
        result = prime * result + ((this.getFecetPropuesta().getIdSubprograma() == null) ? 0 : this.getFecetPropuesta().getIdSubprograma().hashCode());
        result = prime * result + ((this.getFecetPropuesta().getIdMetodo() == null) ? 0 : this.getFecetPropuesta().getIdMetodo().hashCode());
        result = prime * result + ((this.getFecetPropuesta().getFechaInicioPeriodo() == null) ? 0 : this.getFecetPropuesta().getFechaInicioPeriodo().hashCode());
        result = prime * result + ((this.getFecetPropuesta().getFechaFinPeriodo() == null) ? 0 : this.getFecetPropuesta().getFechaFinPeriodo().hashCode());
        return result;
    }

    public List<String> getAdvertencias() {
        return advertencias;
    }

    public void setAdvertencias(List<String> advertencias) {
        this.advertencias = advertencias;
    }

    public String getSegundoCaracter() {
        return segundoCaracter;
    }

    public void setSegundoCaracter(String segundoCaracter) {
        this.segundoCaracter = segundoCaracter;
    }

    public Map<TipoEmpleadoEnum, BigDecimal> getUnidadesCorreo() {
        return unidadesCorreo;
    }

    public void setUnidadesCorreo(Map<TipoEmpleadoEnum, BigDecimal> unidadesCorreo) {
        this.unidadesCorreo = unidadesCorreo;
    }

    public boolean isExisteProgramador() {
        return existeProgramador;
    }

    public void setExisteProgramador(boolean existeProgramador) {
        this.existeProgramador = existeProgramador;
    }
}
