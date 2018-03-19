/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ModulosModelMB extends CatalogManagerAbstractMB {

    private static final long serialVersionUID = -5653439695346277712L;

    private List<FececModulos> lstModulos;
    private Integer moduloSelecion;    
    private List<AraceDTO> lstUnidadesSrc;
    private List<AraceDTO> lstUnidadesTarget;
    private List<ModuloUnidadAdmin> lstModuloUnidadesAdmin;
    private DualListModel<AraceDTO> lstUnidadesAdminDualModel;
    private FececModulos moduloNew;
    private FececModulos moduloSeleccion;

    public List<FececModulos> getLstModulos() {
        return lstModulos;
    }

    public void setLstModulos(List<FececModulos> lstModulos) {
        this.lstModulos = lstModulos;
    }

    public List<ModuloUnidadAdmin> getLstModuloUnidadesAdmin() {
        return lstModuloUnidadesAdmin;
    }

    public void setLstModuloUnidadesAdmin(List<ModuloUnidadAdmin> lstModuloUnidadesAdmin) {
        this.lstModuloUnidadesAdmin = lstModuloUnidadesAdmin;
    }

    public DualListModel<AraceDTO> getLstUnidadesAdminDualModel() {
        return lstUnidadesAdminDualModel;
    }

    public void setLstUnidadesAdminDualModel(DualListModel<AraceDTO> lstUnidadesAdminDualModel) {
        this.lstUnidadesAdminDualModel = lstUnidadesAdminDualModel;
    }

    public List<AraceDTO> getLstUnidadesSrc() {
        return lstUnidadesSrc;
    }

    public void setLstUnidadesSrc(List<AraceDTO> lstUnidadesSrc) {
        this.lstUnidadesSrc = lstUnidadesSrc;
    }

    public List<AraceDTO> getLstUnidadesTarget() {
        return lstUnidadesTarget;
    }

    public void setLstUnidadesTarget(List<AraceDTO> lstUnidadesTarget) {
        this.lstUnidadesTarget = lstUnidadesTarget;
    }

    public Integer getModuloSelecion() {
        return moduloSelecion;
    }

    public void setModuloSelecion(Integer moduloSelecion) {
        this.moduloSelecion = moduloSelecion;
    }

    public FececModulos getModuloNew() {
        return moduloNew;
    }

    public void setModuloNew(FececModulos moduloNew) {
        this.moduloNew = moduloNew;
    }

    public FececModulos getModuloSeleccion() {
        return moduloSeleccion;
    }

    public void setModuloSeleccion(FececModulos moduloSeleccion) {
        this.moduloSeleccion = moduloSeleccion;
    }

}
