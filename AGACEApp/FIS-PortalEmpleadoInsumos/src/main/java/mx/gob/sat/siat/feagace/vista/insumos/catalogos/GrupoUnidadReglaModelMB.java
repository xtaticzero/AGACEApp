/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class GrupoUnidadReglaModelMB extends ReglasModelMB {

    private static final long serialVersionUID = -4688188610427396726L;

    private Integer idGrupoSeleccionado;
    private Integer idReglaSeleccionada;
    private List<GrupoUnidadesAdminXGeneral> lstGrupoUnidadesXRegla;
    private DualListModel<AraceDTO> lstUnidadesAdminXGrupoDualModel;
    private GrupoUnidadesAdminXGeneral newGrupoUnidades;
    private GrupoUnidadesAdminXGeneral grupoUnidadesXReglaSeleccionado;
    private GrupoAdministracion grupoDeUnidadesSeleccionado;
    private Regla reglaDeUnidadesSeleccionada;

    public Integer getIdGrupoSeleccionado() {
        return idGrupoSeleccionado;
    }

    public void setIdGrupoSeleccionado(Integer idGrupoSeleccionado) {
        this.idGrupoSeleccionado = idGrupoSeleccionado;
    }

    public Integer getIdReglaSeleccionada() {
        return idReglaSeleccionada;
    }

    public void setIdReglaSeleccionada(Integer idReglaSeleccionada) {
        this.idReglaSeleccionada = idReglaSeleccionada;
    }

    public List<GrupoUnidadesAdminXGeneral> getLstGrupoUnidadesXRegla() {
        return lstGrupoUnidadesXRegla;
    }

    public void setLstGrupoUnidadesXRegla(List<GrupoUnidadesAdminXGeneral> lstGrupoUnidadesXRegla) {
        this.lstGrupoUnidadesXRegla = lstGrupoUnidadesXRegla;
    }

    public DualListModel<AraceDTO> getLstUnidadesAdminXGrupoDualModel() {
        return lstUnidadesAdminXGrupoDualModel;
    }

    public void setLstUnidadesAdminXGrupoDualModel(DualListModel<AraceDTO> lstUnidadesAdminXGrupoDualModel) {
        this.lstUnidadesAdminXGrupoDualModel = lstUnidadesAdminXGrupoDualModel;
    }

    public GrupoUnidadesAdminXGeneral getNewGrupoUnidades() {
        return newGrupoUnidades;
    }

    public void setNewGrupoUnidades(GrupoUnidadesAdminXGeneral newGrupoUnidades) {
        this.newGrupoUnidades = newGrupoUnidades;
    }

    public GrupoUnidadesAdminXGeneral getGrupoUnidadesXReglaSeleccionado() {
        return grupoUnidadesXReglaSeleccionado;
    }

    public void setGrupoUnidadesXReglaSeleccionado(GrupoUnidadesAdminXGeneral grupoUnidadesXReglaSeleccionado) {
        this.grupoUnidadesXReglaSeleccionado = grupoUnidadesXReglaSeleccionado;
    }

    public GrupoAdministracion getGrupoDeUnidadesSeleccionado() {
        return grupoDeUnidadesSeleccionado;
    }

    public void setGrupoDeUnidadesSeleccionado(GrupoAdministracion grupoDeUnidadesSeleccionado) {
        this.grupoDeUnidadesSeleccionado = grupoDeUnidadesSeleccionado;
    }

    public Regla getReglaDeUnidadesSeleccionada() {
        return reglaDeUnidadesSeleccionada;
    }

    public void setReglaDeUnidadesSeleccionada(Regla reglaDeUnidadesSeleccionada) {
        this.reglaDeUnidadesSeleccionada = reglaDeUnidadesSeleccionada;
    }

}
