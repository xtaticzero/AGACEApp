/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import mx.gob.sat.siat.base.constante.ConstantesSesion;
import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.EmpleadoService;
import mx.gob.sat.siat.feagace.negocio.ServiceCatGrupoDeUnidadAdmin;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class AccesoUsuarioMBAbstract extends BaseManagedBean {

    private static final long serialVersionUID = -1176989363895073478L;
    
    @ManagedProperty(value = "#{empleadoService}")
    private transient EmpleadoService empleadoService;
    
    @ManagedProperty(value = "#{serviceCatGrupoDeUnidadAdmin}")
    private ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin;
    
    private EmpleadoDTO empleadoSession;
    private Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos;

    public EmpleadoDTO getAccesoEmpleado(String rfcEmpleado) {
        if (rfcEmpleado != null && !rfcEmpleado.isEmpty()) {
            try {
                EmpleadoDTO empleado = empleadoService.getEmpleadoCompleto(rfcEmpleado);
                getSession().setAttribute(ConstantesSesion.EMPLEADO_FIS_INTEGRAL, empleado);
                getSession().setAttribute(ConstantesSesion.LIST_UNIDADES_ADMIN, empleadoService.getUnidadesAdministrativasXGeneral(empleado));
                getSession().setAttribute(ConstantesSesion.MAP_GRUPOS_UNIDADES_ADMIN, obtenerMapGruposDeUnidades(empleado));
                return empleado;
            } catch (EmpleadoServiceException ex) {
                addErrorMessage(ex.getMessage());
                logger.error(ex.getMessage(), ex);
            }
        }
        return null;
    }
    
    public Map<BigDecimal, GrupoUnidadesAdminXGeneral> obtenerMapGruposDeUnidades(EmpleadoDTO empleadoDto) {
        mapGrupos = new HashMap<BigDecimal, GrupoUnidadesAdminXGeneral>();
        try {
            List<GrupoUnidadesAdminXGeneral> lstGrupos = serviceCatGrupoDeUnidadAdmin.getLstGrupos(empleadoDto);
            if (lstGrupos != null && !lstGrupos.isEmpty()) {
                for (GrupoUnidadesAdminXGeneral grupo : lstGrupos) {
                    mapGrupos.put(grupo.getGrupo().getIdGrupo(), grupo);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return mapGrupos;
    }

    public EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public void setEmpleadoService(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }
    
    public EmpleadoDTO getEmpleadoSession() {
        return empleadoSession;
    }

    public void setEmpleadoSession(EmpleadoDTO empleadoSession) {
        this.empleadoSession = empleadoSession;
    }

    public Map<BigDecimal, GrupoUnidadesAdminXGeneral> getMapGrupos() {
        return mapGrupos;
    }

    public void setMapGrupos(Map<BigDecimal, GrupoUnidadesAdminXGeneral> mapGrupos) {
        this.mapGrupos = mapGrupos;
    }
    
    public ServiceCatGrupoDeUnidadAdmin getServiceCatGrupoDeUnidadAdmin() {
        return serviceCatGrupoDeUnidadAdmin;
    }

    public void setServiceCatGrupoDeUnidadAdmin(ServiceCatGrupoDeUnidadAdmin serviceCatGrupoDeUnidadAdmin) {
        this.serviceCatGrupoDeUnidadAdmin = serviceCatGrupoDeUnidadAdmin;
    }
}
