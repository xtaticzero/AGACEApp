/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechPendientesValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropRechVerifProcedencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropTransferPendValidacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PropuestaOrigenCentralRegDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;

public interface FecetPropuestaDao {

    BigDecimal insert(FecetPropuesta dto);

    BigDecimal getConsecutivo();

    BigDecimal getConsecutivoAnio(Date fechaInicio, Date fechaFinal);

    BigDecimal getIdRegistroConsecutivo();

    void update(FecetPropuestaPk pk, FecetPropuesta dto);

    void cambiarEstatusPropuestaRechazar(FecetPropuesta dto);

    void cambiarEstatusPropuesta(FecetPropuesta dto);

    List<FecetPropuesta> buscarAntecedentes(final Date fechaInicial, Date fechaFinal, String rfc);

    List<FecetPropuesta> buscarAntecedentesPropuestas(String rfc, FecetPropuesta dto, String estatus);

    List<FecetPropuesta> buscarAntecedentesPropuestas(String rfc, FecetPropuesta dto);

    List<FecetPropuesta> buscarDetalleOficio(String folio);

    List<FecetPropuesta> findWhereIdPropuestaEquals(final BigDecimal idPropuesta);

    List<FecetPropuesta> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente);

    List<FecetPropuesta> findWhereIdRegistroEquals(final String idRegistro);

    List<FecetPropuesta> findWhereRfcSubadministradorAndIdEstatusEquals(String rfcSubadministrador,
            BigDecimal idEstatus);

    List<FecetPropRechVerifProcedencia> obtenerPropRechVerifProc(final BigDecimal idEstatus, String rfcFirmante);

    List<FecetPropRechPendientesValidacion> obtenerPropRechPendValid(final BigDecimal idEstatus, String rfcFirmante);

    List<FecetPropuesta> obtenerPropuestasRechazadasPendientesValid(BigDecimal idEstatus, String rfcFirmante);

    List<FecetPropTransferPendValidacion> obtenerPropTransferPendValid(BigDecimal idEstatus120, BigDecimal idEstatus73,
            String rfcFirmante);

    void actualizarEstatus(BigDecimal idPropuesta, final int idEstatus);

    List<ContadorInsumosSubAdmin> getContadorPropuestasFirmante(final String rfc, final BigDecimal idAdministrador);

    List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarFirmante(final BigDecimal idArace);

    List<PropuestaOrigenCentralRegDTO> getPropuestasRegionalAsignarFirmante(final BigDecimal idArace, List<String> prioridades, boolean esGridInicial,
            final Object... args);

    List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarAuditor(final String rfcFirmante);

    List<FecetPropuesta> traePropuestasPorAnalizar(final String rfcAuditor, List<String> parametros);

    List<ContadorInsumosSubAdmin> getContadorPropuestasSubAdministradores();

    void actualizarEstatusAndSubAdministrador(BigDecimal idPropuesta, int idEstatus, String rfcSubAdmin);

    BigInteger getContadorPropuestasAsignadasPorSubAdmin(String rfcSubAdmin, BigDecimal idEstatus);

    List<FecetPropuesta> findWhereIdEstatusAndRfcAdminArace(final BigDecimal idEstatus, BigDecimal idArace);

    List<FecetPropuesta> getPropuestasXMetodoEstatusArace(String rfcCreacion, TipoEstatusEnum[] lstEstatusPropuesta,
            TipoMetodoEnum tipoMetodo, String prioridad, TipoAraceEnum... tiposArace);

    List<FecetPropuesta> getPropuestasAsignacasCentralARegional(BigDecimal idProgramador,
            TipoEstatusEnum estatusPropuesta, TipoMetodoEnum tipoMetodo, Integer prioridad);

    List<FecetPropuesta> getPropuestasAsignacasCentralARegional(BigDecimal idProgramador,
            TipoEstatusEnum[] estatusPropuesta, String prioridad, TipoMetodoEnum tipoMetodo);

    int actualizaEstadoPropuesta(TipoEstatusEnum tipoEstatus, Date baja, BigDecimal idPropuesta);

    int actualizaEstadoPropuesta(TipoEstatusEnum tipoEstatus, BigDecimal idPropuesta);

    void actualizarPropuestaFirmante(FecetPropuesta propuesta);

    void actualizarPropuestaAuditor(FecetPropuesta propuesta);

    List<FecetPropuesta> getPropuestasPorValidarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo, String rfcEmpleado,
            BigDecimal idAccionOrigen);

    List<ProgramadorPropuestaAsignadaDTO> buscarProgramadorAsignar(String programadores, BigDecimal idCentral);

    void actualizarIdProgramador(final BigDecimal idPropuesta, final BigDecimal idProgramador);

    List<ContadorInsumosSubAdmin> getContadorPropuestasAuditor(final String rfc, final BigDecimal idArace);

    void actualizarEstatusNoAprobadaTransfer(final BigDecimal idPropuesta, final int idEstatus);

    String insertaResumenMasiva(ResumenCargaMasivaDTO resumenDTO);

    List<FecetPropuesta> getPropuestasXCambioMetodo(String rfcCreacion, TipoEstatusEnum[] lstEstatusPropuesta,
            TipoAraceEnum... tiposArace);

    void actualizarRfcAdministrador(final BigDecimal idPropuesta, String rfcAdministrador);

    BigDecimal getIdProgramador(BigDecimal idPropuesta);

    BigDecimal getTipoEmpleadoCreacionByPropuesta(BigDecimal idPropuesta);

    void updatePropuesta(FecetPropuestaPk pk, FecetPropuesta dto, boolean actaulizaNulos);

    List<FecetPropuesta> getPropuestasAsignaSubadminstraACentral(BigDecimal idProgramador,
            TipoEstatusEnum[] estatusPropuesta, TipoMetodoEnum tipoMetodo);

    List<FecetPropuesta> consultaEjecutivaPropuestasXFiltro(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo);

    List<FecetPropuesta> consultaEjecutivaPropuestasXFiltroCentralRegional(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo);

    List<FecetPropuesta> consultaEjecutivaPropuestasAcppce(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo);

    List<FecetPropuesta> consultaPropuestaDetalle(String idRegistro);

    List<PropuestaOrigenCentralRegDTO> getPropuestaOrigenCentralRegional(BigDecimal idEmpledo, BigDecimal idAraceEmpleado);
}
