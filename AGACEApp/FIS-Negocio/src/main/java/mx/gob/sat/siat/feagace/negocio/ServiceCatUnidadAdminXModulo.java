/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ServiceCatUnidadAdminXModulo {

    String MSG_ERROR_USR_INVALID = "El usuario no tiene una Administración General valida";
    String MSG_ERROR_SQL_DAO = "sql";
    String MSG_ERROR_UNIDAD_NO_VALIDA = "unidad.no.valida";

    Map<Integer, AraceDTO> init(EmpleadoDTO empCompleto) throws CatalogosServiceException;

    List<FececModulos> getLstModulos(EmpleadoDTO empCompleto) throws CatalogosServiceException;

    List<ModuloUnidadAdmin> getLstUnidaesAdminXGeneralModulo(BigDecimal idUnidadGeneral) throws CatalogosServiceException;

    List<ModuloUnidadAdmin> getLstUnidaesAdminXGeneralModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo) throws CatalogosServiceException;

    List<AraceDTO> getLstUnidadesAsignadasModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo, Map<Integer, AraceDTO> mapUnidadesAdmin)throws CatalogosServiceException;
    
    void guardarRelacionUnidadModulo(ModuloUnidadAdmin relacionUnidadModulo) throws CatalogosServiceException;
    
    void guardarRelacionUnidadModulo(List<AraceDTO> lstDesAsignadas, List<AraceDTO> lstAsignadas, Integer idUnidAdminGral, Integer idModulo) throws CatalogosServiceException;

    void agregarModulo(FececModulos modulo) throws CatalogosServiceException;

    void eliminarModulo(FececModulos modulo) throws CatalogosServiceException;

    void actualizar(FececModulos modulo) throws CatalogosServiceException;
    
    void llenarDetalleUnidadAdmin(Map<Integer, AraceDTO> mapUnidades, List<ModuloUnidadAdmin> lstModuloUnidad) throws CatalogosServiceException;
    
    Map<ListasAsignacionEnum,List<AraceDTO>> getListasDeAsignacion(List<ModuloUnidadAdmin> lstUnidadesXModulo,List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException;
}
