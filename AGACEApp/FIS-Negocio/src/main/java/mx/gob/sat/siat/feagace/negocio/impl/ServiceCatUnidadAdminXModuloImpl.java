/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.ServiceCatBase;
import mx.gob.sat.siat.feagace.negocio.ServiceCatUnidadAdminXModulo;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Service("serviceCatUnidadAdminXModulo")
public class ServiceCatUnidadAdminXModuloImpl extends ServiceCatBase implements ServiceCatUnidadAdminXModulo {

    private static final long serialVersionUID = -2285677288263036608L;

    @Override
    public List<FececModulos> getLstModulos(EmpleadoDTO empCompleto) throws CatalogosServiceException {
        if (empCompleto != null && empCompleto.getIdAdmGral() != 0) {
            try {
                return getModulosDaoImpl().findAll();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        } else {
            logger.error(MSG_ERROR_USR_INVALID);
            throw new CatalogosServiceException("");
        }
    }

    @Override
    public List<ModuloUnidadAdmin> getLstUnidaesAdminXGeneralModulo(BigDecimal idUnidadGeneral) throws CatalogosServiceException {
        if (idUnidadGeneral != null) {
            try {
                return getUnidadAdminModuloDao().findAll(idUnidadGeneral);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        } else {
            logger.error(MSG_ERROR_USR_INVALID);
            throw new CatalogosServiceException("");
        }
    }

    @Override
    public List<ModuloUnidadAdmin> getLstUnidaesAdminXGeneralModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo) throws CatalogosServiceException {
        if (idUnidadGeneral != null) {
            try {
                return getUnidadAdminModuloDao().findByModulo(idUnidadGeneral, idModulo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        } else {
            logger.error(MSG_ERROR_USR_INVALID);
            throw new CatalogosServiceException("");
        }
    }

    @Override
    public List<AraceDTO> getLstUnidadesAsignadasModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo, Map<Integer, AraceDTO> mapUnidadesAdmin) throws CatalogosServiceException {
        List<AraceDTO> lstUnidadesAsignadas = new ArrayList<AraceDTO>();
        List<ModuloUnidadAdmin> lstUnidadModulo = getLstUnidaesAdminXGeneralModulo(idUnidadGeneral, idModulo);
        if (lstUnidadModulo != null && !lstUnidadModulo.isEmpty() && mapUnidadesAdmin != null) {
            for (ModuloUnidadAdmin unidModul : lstUnidadModulo) {
                unidModul.setUnidadAdministrativa(obtenerUnidadValida(unidModul.getUnidadAdministrativa().getIdArace(), mapUnidadesAdmin));
            }
        }
        return lstUnidadesAsignadas;
    }

    @Override
    public void guardarRelacionUnidadModulo(ModuloUnidadAdmin relacionUnidadModulo) throws CatalogosServiceException {
        if (relacionUnidadModulo != null && relacionUnidadModulo.getModulo() != null && relacionUnidadModulo.getUnidadAdministrativa() != null) {
            try {
                Integer numRegistros = getUnidadAdminModuloDao().count(relacionUnidadModulo);
                if (numRegistros > 0) {
                    getUnidadAdminModuloDao().getIdModuloUnidad(relacionUnidadModulo);
                    if (relacionUnidadModulo.getFechaFin() != null) {
                        relacionUnidadModulo.setBlnActivo(false);
                    }
                    getUnidadAdminModuloDao().update(relacionUnidadModulo);
                    return;
                }
                getUnidadAdminModuloDao().insert(relacionUnidadModulo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        }
    }

    @Override
    public void guardarRelacionUnidadModulo(List<AraceDTO> lstDesAsignadas, List<AraceDTO> lstAsignadas, Integer idUnidAdminGral, Integer idModulo) throws CatalogosServiceException {
        if (idUnidAdminGral != null && idModulo != null) {
            try {
                if (lstAsignadas != null && !lstAsignadas.isEmpty()) {
                    for (AraceDTO asignada : lstAsignadas) {
                        guardarRelacionUnidadModulo(crearModuloUnidad(asignada.getIdArace(), idModulo, idUnidAdminGral, EstadoBooleanoEnum.TRUE.isValue(), null));
                    }
                }
                if (lstDesAsignadas != null && !lstDesAsignadas.isEmpty()) {
                    Date fechaFin = new Date();
                    for (AraceDTO desAsignada : lstDesAsignadas) {
                        guardarRelacionUnidadModulo(crearModuloUnidad(desAsignada.getIdArace(), idModulo, idUnidAdminGral, EstadoBooleanoEnum.FALSE.isValue(), fechaFin));
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        }
    }

    private ModuloUnidadAdmin crearModuloUnidad(Integer idArace, Integer idModulo, Integer idUnidAdminGral, boolean blnActivo, Date fechaFin) {
        ModuloUnidadAdmin moduloUnidad = new ModuloUnidadAdmin();
        moduloUnidad.setUnidadGeneral(new BigDecimal(idUnidAdminGral));
        moduloUnidad.setModulo(new FececModulos());
        moduloUnidad.getModulo().setIdModulo(new BigDecimal(idModulo));
        moduloUnidad.setUnidadAdministrativa(new AraceDTO());
        moduloUnidad.getUnidadAdministrativa().setIdArace(idArace);
        moduloUnidad.setBlnActivo(blnActivo);
        moduloUnidad.setFechaFin(fechaFin);
        return moduloUnidad;
    }

    @Override
    public void agregarModulo(FececModulos modulo) throws CatalogosServiceException {
        if (modulo != null && modulo.getNombre() != null) {
            try {
                FececModulos newModule = getModulosDaoImpl().getModuloXNombre(modulo);
                if (newModule != null && newModule.getIdModulo() != null) {
                    newModule.setDescripcion(modulo.getDescripcion());
                    newModule.setFechaBaja(null);
                    getModulosDaoImpl().update(newModule);
                } else {
                    getModulosDaoImpl().insert(modulo);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        }
    }

    @Override
    public void eliminarModulo(FececModulos modulo) throws CatalogosServiceException {
        if (modulo != null && modulo.getIdModulo() != null) {
            try {
                getModulosDaoImpl().delete(modulo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        }
    }

    @Override
    public void actualizar(FececModulos modulo) throws CatalogosServiceException {
        if (modulo != null && modulo.getIdModulo() != null && modulo.getNombre() != null) {
            try {
                getModulosDaoImpl().update(modulo);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new CatalogosServiceException(MSG_ERROR_SQL_DAO, e);
            }
        }
    }

    private AraceDTO obtenerUnidadValida(Integer idArace, Map<Integer, AraceDTO> mapUnidadesAdmin) throws CatalogosServiceException {
        if (idArace != null && mapUnidadesAdmin != null && mapUnidadesAdmin.containsKey(idArace)) {
            return mapUnidadesAdmin.get(idArace);
        }
        throw new CatalogosServiceException(MSG_ERROR_UNIDAD_NO_VALIDA);
    }

    @Override
    public Map<ListasAsignacionEnum, List<AraceDTO>> getListasDeAsignacion(List<ModuloUnidadAdmin> lstUnidadesXModulo, List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException {
        return llenarDetalleUnidadAdminXModulo(lstUnidadesXModulo, lstUnidadesXGeneral);
    }

}
