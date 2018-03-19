/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumoPk;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FiltroInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

public interface FecetInsumoDao {

    FecetInsumoPk insert(FecetInsumo dto);

    BigDecimal update(FecetInsumoPk pk, FecetInsumo dto);

    FecetInsumo findByPrimaryKey(BigDecimal idInsumo);

    List<FecetInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo);

    List<FecetInsumo> buscarSeguimientoInsumoPorEstatus(BigDecimal estatus);

    List<ContadorAsignadosAdministrador> getInsumosAsignadosAdministradores(List<String> lstRfcAdmin);

    List<FecetInsumo> getInsumosAdministrador(final String rfc);

    List<FecetInsumo> getInsumosAdministradorEntidad(final String rfc, final String entidad);

    List<FecetInsumo> consultarInsumosContSubproEst(final String rfcSubadministrador);

    BigDecimal actualizarEstatus(BigDecimal idInsumo, final int idEstatus);

    BigDecimal actualizarEstatusFechaAprobacion(final BigDecimal idInsumo, final Date fechaAprobacion, final int idEstatus);

    BigDecimal getFolioDisponible();

    List<FecetInsumo> traeInsumosSinAsignados(final String rfcAdministrador);

    List<ContadorInsumosSubAdmin> getContadorSubAdministradores();

    List<FecetInsumo> buscarSeguimientoInsumoPorEstatusRFCCreacion(BigDecimal estatus, String rfc);

    List<ContadorInsumosSubAdmin> getContadorSubAdministradoresByAdministrador(List<String> rfcs);

    List<FecetInsumo> buscarSeguimientoInsumoPorRFCCreacion(String rfc, BigDecimal... estatus);

    List<FecetInsumo> buscarAntecedentesInsumos(String rfc, FecetPropuesta dto);

    List<FecetInsumo> buscarAntecedentesInsumosPeriodoExacto(String rfc, FecetPropuesta dto);

    boolean actualizarFechaInicioPlazo(final BigDecimal idInsumo, boolean existePlazo);

    boolean existeRegistroDuplicado(FecetInsumo insumo, String rfc);

    Date obtenerFechaRechazoInsumo(BigDecimal idInsumo);

    Date obtenerFechaAprobacionInsumo(BigDecimal idInsumo);

    List<FecetInsumo> obtenerInsumosANotificarCambioSemaforo();

    List<FecetInsumo> consultaEjecutiva(FiltroInsumos filtroDeBusqueda);

    Integer countInsumosXEstatus(String rfcEmpleado, TipoEmpleadoEnum tipoEmpleado, List<TipoEstatusEnum> estatusInsumo,
            List<AraceDTO> lstUnidAdministrativas, FiltroInsumos filtroDeBusqueda, boolean isAciace);

    Integer countInsumosXEmpleado(String rfcEmpleado, TipoEmpleadoEnum tipoEmpleado,
            List<TipoEstatusEnum> lstEstatusInsumo, List<AraceDTO> lstUnidAdministrativas, boolean isAciace);

    FecetInsumo getRetroalimemtacionesDeInsumo(FecetInsumo insumo);

    FecetInsumo getRechazoDeInsumo(FecetInsumo insumo);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.INSUMO_CREADO)
    String actualizarInsumoRegistro(FecetInsumo insumo);

    List<BigDecimal> getNumEmpleadosUnidadEstadoTipoEmpleado(BigDecimal idUnidadAdministrativa, String estado, BigDecimal idTipoEmpleado);

    Map<String, Long> obtenerContadorAdministadores(List<TipoEstatusEnum> estatus, List<String> rfcs);

    void insertaResumenMasiva(final ResumenCargaMasivaDTO resumenDTO, String identificador);

    FecetInsumo findByIdRegistro(String idRegistro);

    void updateFechaInicioPlazo(FecetInsumo ionusmo);
}
