/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dto.catalogos.common;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.domain.BaseModel;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class GrupoUnidadesAdminXGeneral extends BaseModel {

    private static final long serialVersionUID = 650134375739056713L;

    private BigDecimal idAdmGral;
    private GrupoAdministracion grupo;
    private Regla reglaNegocio;
    private List<AraceDTO> lstUnidadesAdministrativas;

    public BigDecimal getIdAdmGral() {
        return idAdmGral;
    }

    public void setIdAdmGral(BigDecimal idAdmGral) {
        this.idAdmGral = idAdmGral;
    }

    public GrupoAdministracion getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoAdministracion grupo) {
        this.grupo = grupo;
    }

    public Regla getReglaNegocio() {
        return reglaNegocio;
    }

    public void setReglaNegocio(Regla reglaNegocio) {
        this.reglaNegocio = reglaNegocio;
    }

    public List<AraceDTO> getLstUnidadesAdministrativas() {
        return lstUnidadesAdministrativas;
    }

    public void setLstUnidadesAdministrativas(List<AraceDTO> lstUnidadesAdministrativas) {
        this.lstUnidadesAdministrativas = lstUnidadesAdministrativas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idAdmGral != null ? this.idAdmGral.hashCode() : 0);
        hash = 89 * hash + (this.grupo != null ? this.grupo.hashCode() : 0);
        hash = 89 * hash + (this.lstUnidadesAdministrativas != null ? this.lstUnidadesAdministrativas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoUnidadesAdminXGeneral other = (GrupoUnidadesAdminXGeneral) obj;
        if (this.idAdmGral != other.idAdmGral && (this.idAdmGral == null || !this.idAdmGral.equals(other.idAdmGral))) {
            return false;
        }
        if (this.grupo != other.grupo && (this.grupo == null || !this.grupo.equals(other.grupo))) {
            return false;
        }
        if (this.lstUnidadesAdministrativas != null && !this.lstUnidadesAdministrativas.isEmpty() && other.lstUnidadesAdministrativas!=null && !other.lstUnidadesAdministrativas.isEmpty()) {
            return this.lstUnidadesAdministrativas.containsAll(other.lstUnidadesAdministrativas);
        }
        return true;
    }

    

    
}
