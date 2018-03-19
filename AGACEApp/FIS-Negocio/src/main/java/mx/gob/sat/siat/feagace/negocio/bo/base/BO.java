package mx.gob.sat.siat.feagace.negocio.bo.base;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

public abstract class BO<T> {

    private Rule<T> rule;
    private Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGruposUnidades;
    private List<GrupoUnidadesAdminXGeneral> lstGruposValidosXRegla;

    public void setRule(Rule<T> rule) {
        this.rule = rule;
    }

    public Rule<T> getRule() {
        return rule;
    }

    public Map<BigDecimal, GrupoUnidadesAdminXGeneral> getMapGruposUnidades() {
        return mapGruposUnidades;
    }

    public void setMapGruposUnidades(Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGruposUnidades) {
        this.mapGruposUnidades = mapGruposUnidades;
    }

    public List<GrupoUnidadesAdminXGeneral> getLstGruposValidosXRegla() {
        return lstGruposValidosXRegla;
    }

    public void setLstGruposValidosXRegla(List<GrupoUnidadesAdminXGeneral> lstGruposValidosXRegla) {
        this.lstGruposValidosXRegla = lstGruposValidosXRegla;
    }

}
