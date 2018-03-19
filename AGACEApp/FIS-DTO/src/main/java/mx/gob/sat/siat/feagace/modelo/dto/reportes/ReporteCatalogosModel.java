package mx.gob.sat.siat.feagace.modelo.dto.reportes;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.domain.BaseModel;


public class ReporteCatalogosModel extends BaseModel{

    @SuppressWarnings("compatibility:-8805725592019375591")
    private static final long serialVersionUID = 5523289862590682153L;
    
    private Integer catalogoId;
    private BigDecimal catalogoReporteId;
    private String catalogoNombre;
    private String catalogoDescripcion;

    public void setCatalogoReporteId(BigDecimal catalogoReporteId) {
        this.catalogoReporteId = catalogoReporteId;
    }

    public BigDecimal getCatalogoReporteId() {
        return catalogoReporteId;
    }

    public void setCatalogoNombre(String catalogoNombre) {
        this.catalogoNombre = catalogoNombre;
    }

    public String getCatalogoNombre() {
        return catalogoNombre;
    }

    public void setCatalogoDescripcion(String catalogoDescripcion) {
        this.catalogoDescripcion = catalogoDescripcion;
    }

    public String getCatalogoDescripcion() {
        return catalogoDescripcion;
    }

    public void setCatalogoId(Integer catalogoId) {
        this.catalogoId = catalogoId;
    }

    public Integer getCatalogoId() {
        return catalogoId;
    }
}
