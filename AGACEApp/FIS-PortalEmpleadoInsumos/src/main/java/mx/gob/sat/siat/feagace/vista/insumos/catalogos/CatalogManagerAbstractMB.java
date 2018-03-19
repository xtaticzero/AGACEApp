/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.negocio.ServiceCatUnidadAdminXModulo;
import mx.gob.sat.siat.feagace.vista.common.AccesoUsuarioMBAbstract;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class CatalogManagerAbstractMB extends AccesoUsuarioMBAbstract {

    private static final long serialVersionUID = -7016556542223128213L;

    protected static final String MSG_ERROR_INIT = "Error al cargar información";
    protected static final String MSG_ERROR_ACTUALIZACION = "Error al actualizar el registro";
    protected static final String MSG_ERROR_GUARDAR = "Error al guardar el registro";
    protected static final String MSG_EXITO_GUARDAR = "Se a guardado con éxito el registro";
    protected static final String MSG_EXITO_ELIMINAR = "Se elimino el registro con éxito";
    protected static final String MSG_MODULO_ASIGNADO = "El módulo tiene asignaciones no puede ser eliminado";
    protected static final String MSG_GRUPO_ASIGNADO = "El grupo tiene asignaciones no puede ser eliminado";
    protected static final String MSG_REGLA_ASIGNADA = "La regla tiene asignaciones no puede ser eliminada";

    protected static final int SIN_ASIGNACION = -1;

    @ManagedProperty(value = "#{serviceCatUnidadAdminXModulo}")
    private ServiceCatUnidadAdminXModulo serviceCatUnidadAdminXModulo;

    private Map<Integer, AraceDTO> mapUnidades;
    private List<AraceDTO> lstUnidadesAdmin;

    public ServiceCatUnidadAdminXModulo getServiceCatUnidadAdminXModulo() {
        return serviceCatUnidadAdminXModulo;
    }

    public void setServiceCatUnidadAdminXModulo(ServiceCatUnidadAdminXModulo serviceCatUnidadAdminXModulo) {
        this.serviceCatUnidadAdminXModulo = serviceCatUnidadAdminXModulo;
    }

    public Map<Integer, AraceDTO> getMapUnidades() {
        return mapUnidades;
    }

    public void setMapUnidades(Map<Integer, AraceDTO> mapUnidades) {
        this.mapUnidades = mapUnidades;
    }

    public List<AraceDTO> getLstUnidadesAdmin() {
        return lstUnidadesAdmin;
    }

    public void setLstUnidadesAdmin(List<AraceDTO> lstUnidadesAdmin) {
        this.lstUnidadesAdmin = lstUnidadesAdmin;
    }

}
