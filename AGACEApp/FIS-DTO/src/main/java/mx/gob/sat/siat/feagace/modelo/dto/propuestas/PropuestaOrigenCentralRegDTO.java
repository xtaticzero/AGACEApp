package mx.gob.sat.siat.feagace.modelo.dto.propuestas;

import java.io.Serializable;

import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

public class PropuestaOrigenCentralRegDTO extends BaseModel implements Serializable {

    @SuppressWarnings("compatibility:-2067587877842276495")
    private static final long serialVersionUID = 1L;
    private FecetPropuesta propuesta;
    private FecetContribuyente contribuyente;
    private FececMetodo metodo;
    private FececSubprograma subprograma;
    private FececArace arace;
    private FececSector sector;
    private FececTipoPropuesta tipoPropuesta;

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

    public FecetContribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(FecetContribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    public FececMetodo getMetodo() {
        return metodo;
    }

    public void setMetodo(FececMetodo metodo) {
        this.metodo = metodo;
    }

    public FececSubprograma getSubprograma() {
        return subprograma;
    }

    public void setSubprograma(FececSubprograma subprograma) {
        this.subprograma = subprograma;
    }

    public FececArace getArace() {
        return arace;
    }

    public void setArace(FececArace arace) {
        this.arace = arace;
    }

    public FececSector getSector() {
        return sector;
    }

    public void setSector(FececSector sector) {
        this.sector = sector;
    }

    public FececTipoPropuesta getTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(FececTipoPropuesta tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }
    
    
    
}
