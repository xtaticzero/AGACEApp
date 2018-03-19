/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ServiceCatGrupoDeUnidadAdmin {

    String MSG_ERROR_PARAMETROS = "error.parametros";

    Map<Integer, AraceDTO> init(EmpleadoDTO empCompleto) throws CatalogosServiceException;

    void guardarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException;

    void actualizarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException;

    void borrarGrupo(GrupoAdministracion grupo) throws CatalogosServiceException;

    List<GrupoAdministracion> findAllGroups() throws CatalogosServiceException;

    void guardarRegla(Regla regla) throws CatalogosServiceException;

    void actualizarRegla(Regla regla) throws CatalogosServiceException;

    void borrarRegla(Regla regla) throws CatalogosServiceException;

    List<Regla> findAllRules() throws CatalogosServiceException;

    boolean esGrupoAsignado(EmpleadoDTO empleado, BigDecimal idGrupo) throws CatalogosServiceException;

    boolean esReglaAsignada(EmpleadoDTO empleado, ReglaEnum regla) throws CatalogosServiceException;

    List<GrupoUnidadesAdminXGeneral> getLstGruposXGeneralXRegla(EmpleadoDTO empleado, ReglaEnum regla) throws CatalogosServiceException;

    GrupoUnidadesAdminXGeneral getGrupoXGeneralXReglaGrupo(EmpleadoDTO empleado, BigDecimal idGrupo, ReglaEnum regla) throws CatalogosServiceException;

    List<GrupoUnidadesAdminXGeneral> getLstGrupos(EmpleadoDTO empleado) throws CatalogosServiceException;

    Map<ListasAsignacionEnum, List<AraceDTO>> getListasDeAsignacion(EmpleadoDTO empleado, BigDecimal idGrupo, ReglaEnum regla, List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException;

    void guardarGrupoUnidades(GrupoUnidadesAdminXGeneral grupoUnidades) throws CatalogosServiceException;

    void eliminarGrupoUnidades(GrupoUnidadesAdminXGeneral grupoUnidades) throws CatalogosServiceException;

    void llenarDetalleUnidad(Map<Integer, AraceDTO> mapUnidades, List<AraceDTO> lstUnidadSinDetalle) throws CatalogosServiceException;
}
