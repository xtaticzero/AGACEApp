package mx.gob.sat.siat.feagace.modelo.dto.ordenes;

import java.math.BigDecimal;
import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class PerfilContribuyenteVO extends BaseModel {


    @SuppressWarnings("compatibility:4862428346518210790")
    private static final long serialVersionUID = 1L;
    private String rfc;
    private BigDecimal idTipoAsociado;
    private String nombreRol;
    private BigDecimal idAsociado;
    private String nombre;
    private String rfcContribuyente;
    
    public PerfilContribuyenteVO() {
        super();
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getRfc() {
        return rfc;
    }

    public void setIdTipoAsociado(BigDecimal idTipoAsociado) {
        this.idTipoAsociado = idTipoAsociado;
    }

    public BigDecimal getIdTipoAsociado() {
        return idTipoAsociado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setIdAsociado(BigDecimal idAsociado) {
        this.idAsociado = idAsociado;
    }

    public BigDecimal getIdAsociado() {
        return idAsociado;
    }
}
