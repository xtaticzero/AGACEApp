/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class GruposModelMB extends ModulosModelMB {

    private static final long serialVersionUID = 6951773339277631671L;

    private List<GrupoAdministracion> lstGrupos;
    private GrupoAdministracion grupoSeleccionado;
    private GrupoAdministracion newGrupo;

    public List<GrupoAdministracion> getLstGrupos() {
        return lstGrupos;
    }

    public void setLstGrupos(List<GrupoAdministracion> lstGrupos) {
        this.lstGrupos = lstGrupos;
    }

    public GrupoAdministracion getGrupoSeleccionado() {
        return grupoSeleccionado;
    }

    public void setGrupoSeleccionado(GrupoAdministracion grupoSeleccionado) {
        this.grupoSeleccionado = grupoSeleccionado;
    }

    public GrupoAdministracion getNewGrupo() {
        return newGrupo;
    }

    public void setNewGrupo(GrupoAdministracion newGrupo) {
        this.newGrupo = newGrupo;
    }

}
