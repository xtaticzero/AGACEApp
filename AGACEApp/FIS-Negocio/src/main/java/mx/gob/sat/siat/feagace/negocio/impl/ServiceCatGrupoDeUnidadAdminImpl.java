/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.impl;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.ServiceCatGrupoDeUnidadAdmin;
import mx.gob.sat.siat.feagace.negocio.ServiceCatBase;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("serviceCatGrupoDeUnidadAdmin")
public class ServiceCatGrupoDeUnidadAdminImpl extends ServiceCatBase implements ServiceCatGrupoDeUnidadAdmin {

    private static final long serialVersionUID = -820056456529035809L;

    @Override
    public void guardarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException {
        try {
            GrupoAdministracion grupoEncontrado = getCatalogoGruposDao().findByNombre(grupo.getNombre().trim().toUpperCase());
            if (grupoEncontrado != null) {
                grupo.setIdGrupo(grupoEncontrado.getIdGrupo());
                grupo.setFechaFin(null);
                getCatalogoGruposDao().update(grupo);
            } else {
                getCatalogoGruposDao().insert(grupo);
            }
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void actualizarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException {
        try {
            getCatalogoGruposDao().update(grupo);
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void borrarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException {
        try {
            getCatalogoGruposDao().delete(grupo);
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public List<GrupoAdministracion> findAllGroups() throws CatalogosServiceException {
        try {
            return getCatalogoGruposDao().findAll();
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void guardarRegla(Regla regla) throws CatalogosServiceException {
        try {
            Regla reglaEncontrada = getCatalogoReglaDao().findByClave(regla.getClave().trim().toUpperCase());
            if (reglaEncontrada != null) {
                regla.setIdRegla(reglaEncontrada.getIdRegla());
                regla.setFechaFin(null);
                getCatalogoReglaDao().update(regla);
            } else {
                getCatalogoReglaDao().insert(regla);
            }
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void actualizarRegla(Regla regla) throws CatalogosServiceException {
        try {
            getCatalogoReglaDao().update(regla);
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void borrarRegla(Regla regla) throws CatalogosServiceException {
        try {
            getCatalogoReglaDao().delete(regla);
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public List<Regla> findAllRules() throws CatalogosServiceException {
        try {
            return getCatalogoReglaDao().findAll();
        } catch (Exception ex) {
            logger.error(MSG_ERROR_PARAMETROS, ex);
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public boolean esGrupoAsignado(EmpleadoDTO empleado, BigDecimal idGrupo) throws CatalogosServiceException {
        try {
            return getCatGrupoDeUnidadAdminDao().asignacionesActivas(empleado.getIdAdmGral() != null ? new BigDecimal(empleado.getIdAdmGral()) : BigDecimal.ZERO, idGrupo, null);
        } catch (Exception ex) {
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public boolean esReglaAsignada(EmpleadoDTO empleado, ReglaEnum regla) throws CatalogosServiceException {
        try {
            return getCatGrupoDeUnidadAdminDao().asignacionesActivas(empleado.getIdAdmGral() != null ? new BigDecimal(empleado.getIdAdmGral()) : BigDecimal.ZERO, null, regla.getNumRegla());
        } catch (Exception ex) {
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public List<GrupoUnidadesAdminXGeneral> getLstGruposXGeneralXRegla(EmpleadoDTO empleado, ReglaEnum regla) throws CatalogosServiceException {
        if (empleado != null && empleado.getIdAdmGral() != null && regla.getNumRegla() != null) {
            List<GrupoUnidadesAdminXGeneral> lstGrupoUnidad = getCatGrupoDeUnidadAdminDao().findByIdAdmGralIdGrupoIdRegla(new BigDecimal(empleado.getIdAdmGral()), null, regla.getNumRegla());
            if (lstGrupoUnidad != null) {
                try {
                    Map<Integer, AraceDTO> mapUnidadesAdmin = getMapAraceDTOFromLstUnidadAdmin(empleado);
                    for (GrupoUnidadesAdminXGeneral grupoUnidad : lstGrupoUnidad) {
                        completarAracesDetalle(mapUnidadesAdmin, grupoUnidad.getLstUnidadesAdministrativas());
                    }
                    return lstGrupoUnidad;
                } catch (EmpleadoServiceException ex) {
                    throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
                }
            }
        }
        throw new CatalogosServiceException(MSG_ERROR_PARAMETROS);
    }

    @Override
    public GrupoUnidadesAdminXGeneral getGrupoXGeneralXReglaGrupo(EmpleadoDTO empleado, BigDecimal idGrupo, ReglaEnum regla) throws CatalogosServiceException {
        if (empleado != null && empleado.getIdAdmGral() != null && regla.getNumRegla() != null) {
            List<GrupoUnidadesAdminXGeneral> lstGrupoUnidad = getCatGrupoDeUnidadAdminDao().findByIdAdmGralIdGrupoIdRegla(new BigDecimal(empleado.getIdAdmGral()), idGrupo, regla.getNumRegla());
            if (lstGrupoUnidad != null) {
                try {
                    Map<Integer, AraceDTO> mapUnidadesAdmin = getMapAraceDTOFromLstUnidadAdmin(empleado);

                    for (GrupoUnidadesAdminXGeneral grupoUnidad : lstGrupoUnidad) {
                        completarAracesDetalle(mapUnidadesAdmin, grupoUnidad.getLstUnidadesAdministrativas());
                        return grupoUnidad;
                    }

                } catch (EmpleadoServiceException ex) {
                    throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
                }
            }
            return null;
        }
        throw new CatalogosServiceException(MSG_ERROR_PARAMETROS);
    }

    @Override
    public List<GrupoUnidadesAdminXGeneral> getLstGrupos(EmpleadoDTO empleado) throws CatalogosServiceException {
        if (empleado != null && empleado.getIdAdmGral() != null) {
            List<GrupoUnidadesAdminXGeneral> lstGrupoUnidad = getCatGrupoDeUnidadAdminDao().findByIdAdmGralIdGrupoIdRegla(new BigDecimal(empleado.getIdAdmGral()), null, null);
            if (lstGrupoUnidad != null) {
                try {
                    Map<Integer, AraceDTO> mapUnidadesAdmin = getMapAraceDTOFromLstUnidadAdmin(empleado);
                    for (GrupoUnidadesAdminXGeneral grupoUnidad : lstGrupoUnidad) {
                        completarAracesDetalle(mapUnidadesAdmin, grupoUnidad.getLstUnidadesAdministrativas());
                    }
                    return lstGrupoUnidad;
                } catch (EmpleadoServiceException ex) {
                    throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
                }
            }
        }
        throw new CatalogosServiceException(MSG_ERROR_PARAMETROS);
    }

    @Override
    public Map<ListasAsignacionEnum, List<AraceDTO>> getListasDeAsignacion(EmpleadoDTO empleado, BigDecimal idGrupo, ReglaEnum regla, List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException {
        Map<ListasAsignacionEnum, List<AraceDTO>> mapLstAsignacion = new EnumMap<ListasAsignacionEnum, List<AraceDTO>>(ListasAsignacionEnum.class);
        try {
            if (empleado != null && idGrupo != null && regla.getNumRegla() != null) {
                List<GrupoUnidadesAdminXGeneral> lstGrupos = getCatGrupoDeUnidadAdminDao().findByIdAdmGralIdGrupoIdRegla(empleado.getIdAdmGral() != null ? new BigDecimal(empleado.getIdAdmGral()) : BigDecimal.ZERO, idGrupo, regla.getNumRegla());
                if (lstGrupos != null && !lstGrupos.isEmpty()) {
                    for (GrupoUnidadesAdminXGeneral grupo : lstGrupos) {
                        return llenarDetalleUnidadAdminXGrupo(grupo.getLstUnidadesAdministrativas(), lstUnidadesXGeneral);
                    }
                } else {
                    return llenarDetalleUnidadAdminXGrupo(null, lstUnidadesXGeneral);
                }
            }

        } catch (Exception ex) {
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
        return mapLstAsignacion;
    }

    @Override
    public void guardarGrupoUnidades(GrupoUnidadesAdminXGeneral grupoUnidades) throws CatalogosServiceException {
        try {
            getCatGrupoDeUnidadAdminDao().insert(grupoUnidades);
        } catch (Exception ex) {
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

    @Override
    public void eliminarGrupoUnidades(GrupoUnidadesAdminXGeneral grupoUnidades) throws CatalogosServiceException {
        try {
            getCatGrupoDeUnidadAdminDao().delete(grupoUnidades);
        } catch (Exception ex) {
            throw new CatalogosServiceException(MSG_ERROR_PARAMETROS, ex);
        }
    }

}
