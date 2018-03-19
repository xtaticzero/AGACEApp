/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.feagace.modelo.dao.helper.PropuestaDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.ContadorInsumosSubAdminMapper;
import mx.gob.sat.siat.feagace.modelo.dao.preparedstatement.PreparedStatementCreatorFactory;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.AsignarFrimanteAuditorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ConsultaAsignaPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropRechPendValidMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropRechVerifProcMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropTransferPendValidMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaCambioMetodoOrden;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropuestaResumenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ProgramadorPropuestaAsignadaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.PropuestaOrigenCentralRegionalesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dao.util.constantes.TipoDaoEnum;
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
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetPropuestaDao")
public class FecetPropuestaDaoImpl extends BaseJDBCDao<FecetPropuesta> implements FecetPropuestaDao {

    public static final int PROPUESTA_RECHAZADA_VALIDACION_FIRMANTE = 133;

    protected static final String SELECT = " SELECT \n";
    protected static final String AND = " AND ";
    protected static final String UPDATE = " UPDATE ";
    protected static final int TIPO_CONSULTA_EJECUTIVA = 1;
    protected static final int TIPO_CONSULTA_CENTRAL_REGIONAL = 2;
    protected static final int TIPO_CONSULTA_ACPPCE = 3;

    private static final long serialVersionUID = 8321400655337867513L;

    @Override
    public BigDecimal insert(FecetPropuesta dto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            PreparedStatementCreator statement
                    = PreparedStatementCreatorFactory.getStatementCreator(TipoDaoEnum.PROPUESTA_DAO, dto);
            getJdbcTemplateBase().update(statement, keyHolder);
            dto.setIdPropuesta(new BigDecimal(keyHolder.getKey().doubleValue()));
        } catch (SQLException ex) {
            logger.error("No se inserto el registro de la propuesta ID_REGISTRO: ".concat(dto.getIdRegistro()), ex);
        }
        return dto.getIdPropuesta();
    }

    @Override
    public BigDecimal getConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_PROPUESTA.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public BigDecimal getConsecutivoAnio(Date fechaInicio, Date fechaFinal) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT MAX(CONSECUTIVO_ANO)");
        sql.append("FROM ");
        sql.append(PropuestasSQL.NAME_TABLE);
        sql.append(" WHERE TRUNC(FECHA_CREACION) BETWEEN ? AND ?");
        return getJdbcTemplateBase().queryForObject(sql.toString(), BigDecimal.class, fechaInicio, fechaFinal);
    }

    @Override
    public BigDecimal getIdRegistroConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_ID_REGISTRO_PROPUESTA.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public void update(FecetPropuestaPk pk, FecetPropuesta dto) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_PROPUESTA = ?, ID_CONTRIBUYENTE = ?, ID_ARACE = ?, ID_SUBPROGRAMA = ?, ID_METODO = ?, ID_REVISION = ?, ID_TIPO_PROPUESTA = ?, ID_CAUSA_PROGRAMACION = ?, ID_SECTOR = ?, ID_REGISTRO = ?, FECHA_INICIO_PERIODO = ?, FECHA_FIN_PERIODO = ?, FECHA_PRESENTACION = ?, FECHA_INFORME = ?, PRIORIDAD = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, RFC_CREACION = ?, RFC_ADMINISTRADOR = ?, RFC_SUBADMINISTRADOR = ?, ID_ESTATUS = ?, RFC_FIRMANTE = ?, RFC_AUDITOR = ?, INFORME_PRESENTACION = ?, ID_INSUMO = ?, ID_ORIGEN = ?, CONSECUTIVO_ANO = ? WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdPropuesta(), dto.getIdContribuyente(), dto.getIdArace(),
                dto.getIdSubprograma(), dto.getIdMetodo(), dto.getIdRevision(),
                dto.getIdTipoPropuesta(), dto.getIdCausaProgramacion(), dto.getIdSector(),
                dto.getIdRegistro(), dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(),
                dto.getFechaPresentacion(), dto.getFechaInforme(), dto.getPrioridadSugerida(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.getRfcCreacion(),
                dto.getRfcAdministrador(), dto.getRfcSubadministrador(), dto.getIdEstatus(),
                dto.getRfcFirmante(), dto.getRfcAuditor(), dto.getInformePresentacion(),
                dto.getIdInsumo(), dto.getOrigenPropuestaId(), dto.getConsecutivoAnio(),
                pk.getIdPropuesta());

    }

    @Override
    public void updatePropuesta(FecetPropuestaPk pk, FecetPropuesta dto, boolean actualizaNulos) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_PROPUESTA = ?, ID_CONTRIBUYENTE = ?, ID_ARACE = ?, ID_SUBPROGRAMA = ?, ID_METODO = ?, ID_REVISION = ?, ID_TIPO_PROPUESTA = ?, ID_CAUSA_PROGRAMACION = ?, ID_SECTOR = ?, ID_REGISTRO = ?, FECHA_INICIO_PERIODO = ?, FECHA_FIN_PERIODO = ? ");

        List<Object> parametros = new ArrayList<Object>();
        parametros.add(dto.getIdPropuesta());
        parametros.add(dto.getIdContribuyente());
        parametros.add(dto.getIdArace());
        parametros.add(dto.getIdSubprograma());
        parametros.add(dto.getIdMetodo());
        parametros.add(dto.getIdRevision());
        parametros.add(dto.getIdTipoPropuesta());
        parametros.add(dto.getIdCausaProgramacion());
        parametros.add(dto.getIdSector());
        parametros.add(dto.getIdRegistro());
        parametros.add(dto.getFechaInicioPeriodo());
        parametros.add(dto.getFechaFinPeriodo());

        if (!actualizaNulos) {
            if (dto.getFechaPresentacion() != null) {
                query.append(", FECHA_PRESENTACION = ? ");
                parametros.add(dto.getFechaPresentacion());
            }

            if (dto.getFechaInforme() != null) {
                query.append(", FECHA_INFORME = ?");
                parametros.add(dto.getFechaInforme());
            }
        } else {
            query.append(", FECHA_PRESENTACION = ? ");
            parametros.add(dto.getFechaPresentacion());
            query.append(", FECHA_INFORME = ?");
            parametros.add(dto.getFechaInforme());
        }

        parametros.add(dto.getPrioridadSugerida());
        parametros.add(dto.getFechaCreacion());
        parametros.add(dto.getFechaBaja());
        parametros.add(dto.getRfcCreacion());
        parametros.add(dto.getRfcAdministrador());
        parametros.add(dto.getRfcSubadministrador());
        parametros.add(dto.getIdEstatus());
        parametros.add(dto.getRfcFirmante());
        parametros.add(dto.getRfcAuditor());
        parametros.add(dto.getInformePresentacion());
        parametros.add(pk.getIdPropuesta());

        query.append(" , PRIORIDAD = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, RFC_CREACION = ?, RFC_ADMINISTRADOR = ?, RFC_SUBADMINISTRADOR = ?, ID_ESTATUS = ?, RFC_FIRMANTE = ?, RFC_AUDITOR = ?, INFORME_PRESENTACION = ? ");
        query.append(" WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), parametros.toArray());

    }

    @Override
    public void cambiarEstatusPropuesta(FecetPropuesta dto) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ? WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdEstatus(), dto.getIdPropuesta());

    }

    @Override
    public void actualizarEstatus(final BigDecimal idPropuesta, final int idEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ? WHERE ID_PROPUESTA = ?");
        getJdbcTemplateBase().update(query.toString(), idEstatus, idPropuesta);

    }

    @Override
    public String insertaResumenMasiva(final ResumenCargaMasivaDTO resumenDTO) {
        String identificador = "";
        try {
            identificador = getJdbcTemplateBase().execute(
                    new CallableStatementCreator() {
                        @Override
                        public CallableStatement createCallableStatement(Connection con) {
                            CallableStatement cs = null;
                            try {
                                cs = con.prepareCall("{? = call FECESK_GENERA_IDSECUENCIAL.FECESF_SECUENCIALPROPMCAS(?)}");
                                cs.registerOutParameter(1, Types.VARCHAR);
                                cs.setInt(2, resumenDTO.getIdOrigen());
                            } catch (SQLException e) {
                                logger.error(e.getMessage(), e);
                            }
                            return cs;
                        }
                    },
                    new CallableStatementCallback<String>() {
                        @Override
                        public String doInCallableStatement(CallableStatement cs) {
                            String result = "";
                            try {
                                cs.execute();
                                result = cs.getString(1);
                            } catch (SQLException e) {
                                logger.error(e.getMessage(), e);
                            }
                            return result;
                        }
                    }
            );
            StringBuilder query = new StringBuilder();
            query.append(PropuestasSQL.INSERT_RESUMEN_MASIVA);
            getJdbcTemplateBase().update(query.toString(), identificador,
                    resumenDTO.getTotalRegistros(), resumenDTO.getIdOrigen());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return identificador;
    }

    @Override
    public void actualizarRfcAdministrador(final BigDecimal idPropuesta, String rfcAdministrador) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET RFC_ADMINISTRADOR = ? WHERE ID_PROPUESTA = ?");
        getJdbcTemplateBase().update(query.toString(), rfcAdministrador, idPropuesta);
    }

    @Override
    public BigDecimal getIdProgramador(BigDecimal idPropuesta) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ID_EMPLEADO FROM FECET_PROPUESTA FP INNER JOIN FECEC_EMPLEADO FE ON (FP.RFC_CREACION = FE.RFC) ");
        sql.append(" WHERE ID_PROPUESTA = ? ");
        return getJdbcTemplateBase().queryForObject(sql.toString(), BigDecimal.class, idPropuesta);
    }

    @Override
    public BigDecimal getTipoEmpleadoCreacionByPropuesta(BigDecimal idPropuesta) {
        String sqlSelectJoin = "SELECT FDE.ID_TIPO_EMPLEADO FROM FECET_PROPUESTA FP INNER JOIN FECEC_EMPLEADO FE ON (FP.RFC_CREACION = FE.RFC) "
                + " INNER JOIN FECET_DETALLE_EMPLEADO FDE ON (FE.ID_EMPLEADO = FDE.ID_EMPLEADO) ";
        StringBuilder sql = new StringBuilder();
        sql.append(sqlSelectJoin);
        sql.append(" WHERE FP.ID_PROPUESTA = ? ");
        return getJdbcTemplateBase().queryForObject(sql.toString(), BigDecimal.class, idPropuesta);
    }

    @Override
    public int actualizaEstadoPropuesta(TipoEstatusEnum tipoEstatus, BigDecimal idPropuesta) {
        return getJdbcTemplateBase().update(
                PropuestasSQL.UPDATE_ESTADO_PROPUESTA,
                tipoEstatus.getId(), idPropuesta);
    }

    @Override
    public int actualizaEstadoPropuesta(TipoEstatusEnum tipoEstatus, Date baja, BigDecimal idPropuesta) {

        return getJdbcTemplateBase().update(
                PropuestasSQL.UPDATE_ESTADO_PROPUESTA_BAJA,
                tipoEstatus.getId(), baja, idPropuesta);

    }

    @Override
    public void cambiarEstatusPropuestaRechazar(FecetPropuesta dto) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ?, RFC_FIRMANTE = ?, RFC_AUDITOR = ? WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdEstatus(), dto.getRfcFirmante(), dto.getRfcAuditor(),
                dto.getIdPropuesta());

    }

    @Override
    public List<FecetPropuesta> buscarAntecedentes(final Date fechaInicial, Date fechaFinal, String rfc) {
        List<FecetPropuesta> antecedentes = null;
        StringBuilder query = new StringBuilder();
        Object[] params = new Object[]{rfc, fechaInicial, fechaFinal,
            Constantes.ID50, Constantes.ID64, Constantes.ID65, Constantes.ID66, Constantes.ID67};

        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_HEDER);
        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_INNER);
        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_WHERE);

        logger.error(query.toString());

        try {
            antecedentes = getJdbcTemplateBase().query(query.toString(),
                    new FecetPropuestaConRelacionesMapper(), params);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return antecedentes;

    }

    @Override
    public List<FecetPropuesta> buscarAntecedentesPropuestas(String rfc, FecetPropuesta dto) {
        List<FecetPropuesta> antecedentes = null;
        StringBuilder query = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        params.add(rfc);
        params.add(dto.getFechaInicioPeriodo());
        params.add(dto.getFechaFinPeriodo());
        params.add(Constantes.ID50);
        params.add(Constantes.ID64);
        params.add(Constantes.ID65);
        params.add(Constantes.ID66);
        params.add(Constantes.ID67);

        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_HEDER);
        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_INNER);
        query.append(PropuestasSQL.SQL_ANTECEDENTES_AGACE_WHERE_PERIODO_EXACTO);

        if (dto.getIdArace() != null && dto.getIdArace().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND FECET_PROPUESTA.ID_ARACE = ? \n");
            params.add(dto.getIdArace());
        }
        if (dto.getIdSubprograma() != null && dto.getIdSubprograma().compareTo(BigDecimal.ZERO) > 0) {
            query.append(" AND FECET_PROPUESTA.ID_SUBPROGRAMA = ? \n");
            params.add(dto.getIdSubprograma());
        }

        try {
            antecedentes = getJdbcTemplateBase().query(query.toString(), new FecetPropuestaConRelacionesMapper(), params.toArray());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return antecedentes;

    }

    @Override
    public List<FecetPropuesta> buscarAntecedentesPropuestas(String rfc, FecetPropuesta dto, String estatus) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        params.add(rfc);
        params.add(Constantes.ID50);
        params.add(Constantes.ID64);
        params.add(Constantes.ID65);
        params.add(Constantes.ID66);
        params.add(Constantes.ID67);
        sql.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_DETALLE_HEADER);
        sql.append(PropuestasSQL.SQL_ANTECEDENTES_PROPUESTA_WHERE);

        if (dto.getIdArace() != null && dto.getIdArace().compareTo(BigDecimal.ZERO) > 0) {
            sql.append("\n AND PROPUESTA.ID_ARACE = ? ");
            params.add(dto.getIdArace());
        }
        if (dto.getIdSubprograma() != null && dto.getIdSubprograma().compareTo(BigDecimal.ZERO) > 0) {
            sql.append("\n AND PROPUESTA.ID_SUBPROGRAMA = ? ");
            params.add(dto.getIdSubprograma());
        }
        if (dto.getIdMetodo() != null && dto.getIdMetodo().compareTo(BigDecimal.ZERO) > 0) {
            sql.append("\n AND PROPUESTA.ID_METODO = ?");
            params.add(dto.getIdMetodo());
        }
        sql.append("\n AND TRUNC(PROPUESTA.FECHA_INICIO_PERIODO) BETWEEN TRUNC(?) AND TRUNC(?) ");
        params.add(dto.getFechaInicioPeriodo());
        params.add(dto.getFechaFinPeriodo());
        sql.append("\n AND TRUNC(PROPUESTA.FECHA_FIN_PERIODO) BETWEEN TRUNC(?) AND TRUNC(?) ");
        params.add(dto.getFechaInicioPeriodo());
        params.add(dto.getFechaFinPeriodo());
        logger.info(sql.toString());
        return getJdbcTemplateBase().query(sql.toString(), new FecetPropuestaResumenMapper(), params.toArray());
    }

    @Override
    public List<FecetPropuesta> buscarDetalleOficio(String folio) {
        StringBuilder query = new StringBuilder();

        query.append(PropuestasSQL.SQL_DETALLE_OFICIO_HEDER);
        query.append(PropuestasSQL.SQL_DETALLE_OFICIO_BODY);

        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaConRelacionesMapper(), folio);

    }

    @Override
    public List<FecetPropuesta> findWhereIdPropuestaEquals(final BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaMapper(), idPropuesta);

    }

    @Override
    public List<FecetPropuesta> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" WHERE ID_CONTRIBUYENTE = ? ORDER BY ID_CONTRIBUYENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaMapper(), idContribuyente);

    }

    @Override
    public List<FecetPropuesta> findWhereIdRegistroEquals(final String idRegistro) {

        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" WHERE ID_REGISTRO = ? ORDER BY ID_REGISTRO");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaMapper(), idRegistro);

    }

    @Override
    public List<FecetPropuesta> findWhereRfcSubadministradorAndIdEstatusEquals(String rfcSubadministrador, BigDecimal idEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" WHERE RFC_SUBADMINISTRADOR = ? AND ID_ESTATUS = ? ORDER BY PRIORIDAD asc, FECHA_CREACION ASC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaMapper(), rfcSubadministrador, idEstatus);

    }

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarFirmante(final BigDecimal idArace) {
        StringBuilder query = PropuestaDaoHelper.getPropuestasAsignarFirmanteQuery(idArace, false);

        query.append(" AND FECET_PROPUESTA.PRIORIDAD IN (");
        query.append(Constantes.CADENA_UNO);
        query.append(",");
        query.append(Constantes.CADENA_DOS);
        query.append(")");
        query.append(" ORDER BY  FECET_PROPUESTA.PRIORIDAD ASC, FECET_PROPUESTA.FECHA_CREACION");

        logger.info(query.toString());
        try {
            return getJdbcTemplateBase().queryForObject(query.toString(), new AsignarFrimanteAuditorMapper());
        } catch (EmptyResultDataAccessException ex) {
            logger.error(ex.getMessage());
            return new ArrayList<PropuestaOrigenCentralRegDTO>();
        }

    }

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestasRegionalAsignarFirmante(BigDecimal idArace, List<String> prioridades, boolean esGridInicial,
            final Object... args) {
        String query;
        if (esGridInicial) {
            StringBuilder queryStrB = PropuestaDaoHelper.getPropuestasRegionalAsignarFirmanteQuery(idArace, false);
            queryStrB.append(" AND FECET_PROPUESTA.PRIORIDAD IN (");
            int cont = 0;
            for (String prioridad : prioridades) {
                queryStrB.append(prioridad);
                if (cont++ < prioridades.size() - 1) {
                    queryStrB.append(",");
                }
            }
            queryStrB.append(")");
            queryStrB.append(" ORDER BY  FECET_PROPUESTA.PRIORIDAD ASC, FECET_PROPUESTA.FECHA_CREACION");
            query = queryStrB.toString();
        } else {
            query = PropuestaDaoHelper.getPropuestasAsignarFirmantePrior3Query(PropuestaDaoHelper.getPropuestasRegionalAsignarFirmanteQuery(idArace, true),
                    prioridades, args);
        }
        logger.info(query);
        try {
            return getJdbcTemplateBase().queryForObject(query, new AsignarFrimanteAuditorMapper());
        } catch (EmptyResultDataAccessException ex) {
            logger.error(ex.getMessage());
            return new ArrayList<PropuestaOrigenCentralRegDTO>();
        }
    }

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestasAsignarAuditor(final String rfcFirmante) {
        StringBuilder query = new StringBuilder();

        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_HEDER_UA);
        query.append(PropuestasSQL.SQL_PROPUESTA_FIRMANTE_JOIN_UA);
        query.append("\n WHERE FECET_PROPUESTA.RFC_AUDITOR IS NULL");
        query.append("\n AND FECET_PROPUESTA.ID_ESTATUS = ? ");
        query.append("\n AND FECET_PROPUESTA.RFC_FIRMANTE = ?");
        query.append("\n AND FECET_IMPUESTO.FECHA_BAJA IS NULL ");
        query.append("\n AND FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL");
        query.append("\n ORDER BY  FECET_PROPUESTA.PRIORIDAD ASC, FECET_PROPUESTA.FECHA_CREACION ASC ");
        try {
            return getJdbcTemplateBase().queryForObject(query.toString(), new AsignarFrimanteAuditorMapper(), Constantes.ESTATUS_REGISTRO_ASIGNADO_FIRMANTE, rfcFirmante);
        } catch (EmptyResultDataAccessException ex) {
            logger.error(ex.getMessage());
            return new ArrayList<PropuestaOrigenCentralRegDTO>();
        }

    }

    @Override
    public List<FecetPropuesta> traePropuestasPorAnalizar(final String rfcAuditor, List<String> parametros) {
        StringBuilder query = new StringBuilder();

        query.append(PropuestasSQL.SQL_PROPUESTA_ANALIZAR_HEDER);
        query.append(PropuestasSQL.SQL_PROPUESTA_ANALIZAR_INNER);

        if (!parametros.get(0).equals("0") && parametros.get(1).equals("0") && parametros.get(2).equals("0")) {
            query.append(" WHERE FECET_PROPUESTA.ID_ESTATUS = '").append(parametros.get(0)).append("' ");
        }

        if (!parametros.get(0).equals("0") && !parametros.get(1).equals("0") && parametros.get(2).equals("0")) {
            query.append(" WHERE FECET_PROPUESTA.ID_ESTATUS IN ('").append(parametros.get(0)).append("','").append(parametros.get(1)).append("') ");
        }

        if (!parametros.get(0).equals("0") && parametros.get(1).equals("0") && !parametros.get(2).equals("0")) {
            query.append("\n INNER JOIN FECEA_PROPUESTA_ACCION ON FECEA_PROPUESTA_ACCION.ID_PROPUESTA = FECET_PROPUESTA.ID_PROPUESTA ");
            query.append(" WHERE FECET_PROPUESTA.ID_ESTATUS = '").append(parametros.get(0)).append("' ");
            query.append(" AND FECEA_PROPUESTA_ACCION.ID_ACCION = '").append(parametros.get(2)).append("' ");
        }

        query.append(" AND FECET_PROPUESTA.RFC_AUDITOR = '").append(rfcAuditor).append("' ");
        query.append(PropuestasSQL.SQL_GRUP_BY_PROPUESTAS).append(" \n");
        query.append("ORDER BY FECET_PROPUESTA.PRIORIDAD ASC, FECET_PROPUESTA.FECHA_CREACION DESC \n");

        return getJdbcTemplateBase().query(query.toString(), new ConsultaAsignaPropuestaMapper());
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuestasFirmante(final String rfc, final BigDecimal idArace) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT COUNT(*) as TOTAL_ASIGNADOS FROM FECET_PROPUESTA P WHERE P.RFC_FIRMANTE = ? AND P.ID_ARACE = ?  \n");

        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new ContadorInsumosSubAdminMapper(), rfc, idArace);

    }

    @Override
    public List<PropuestaOrigenCentralRegDTO> getPropuestaOrigenCentralRegional(BigDecimal idEmpledo, BigDecimal idAraceEmpleado) {
        StringBuilder query = new StringBuilder();

        Object[] sqlParams = new Object[]{
            idEmpledo,
            TipoEstatusEnum.PROPUESTA_REGISTRADA.getId(),
            idAraceEmpleado

        };

        query.append(PropuestasSQL.SQL_ORIGEN_CENTRAL_REGIONALES_HEDER);
        query.append(PropuestasSQL.SQL_ORIGEN_CENTRAL_REGIONALES_WHERE);
        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(),
                new PropuestaOrigenCentralRegionalesMapper(), sqlParams);

    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuestasSubAdministradores() {
        StringBuilder query = new StringBuilder();

        query.append(SELECT);
        query.append("  EMP.NOMBRE \n");
        query.append("  , COUNT(P.RFC_ADMINISTRADOR) TOTAL_ASIGNADOS\n");
        query.append("FROM FECEC_EMPLEADO EMP INNER JOIN FECET_DETALLE_EMPLEADO DE \n");
        query.append("ON EMP.ID_EMPLEADO = DE.ID_EMPLEADO \n");
        query.append("LEFT JOIN FECET_PROPUESTA P \n");
        query.append("ON P.RFC_ADMINISTRADOR = EMP.RFC \n");
        query.append("WHERE DE.ID_TIPO_EMPLEADO = 4 AND DE.ID_CENTRAL = 1  AND EMP.ID_ESTATUS_EMPLEADO = 1 \n");
        query.append("GROUP BY EMP.NOMBRE, EMP.RFC \n");
        query.append("ORDER BY TOTAL_ASIGNADOS DESC");

        return getJdbcTemplateBase().query(query.toString(), new ContadorInsumosSubAdminMapper());

    }

    @Override
    public void actualizarEstatusAndSubAdministrador(BigDecimal idPropuesta, int idEstatus, String rfcSubAdmin) {

        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ?, RFC_SUBADMINISTRADOR=? WHERE ID_PROPUESTA = ?");
        getJdbcTemplateBase().update(query.toString(), idEstatus, rfcSubAdmin, idPropuesta);

    }

    @Override
    public List<FecetPropRechVerifProcedencia> obtenerPropRechVerifProc(BigDecimal idEstatus, String rfcFirmante) {
        String query = "SELECT PROP.ID_PROPUESTA, PROP.ID_REGISTRO, CONT.RFC, PROP.PRIORIDAD, MET.NOMBRE, NVL(SUM(IMP.MONTO),0) PRESUNTIVA "
                + "FROM FECET_PROPUESTA PROP INNER JOIN FECET_CONTRIBUYENTE CONT ON (PROP.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE) "
                + "INNER JOIN FECEC_METODO MET ON (PROP.ID_METODO = MET.ID_METODO) "
                + "LEFT OUTER JOIN FECET_IMPUESTO IMP ON (PROP.ID_PROPUESTA = IMP.ID_PROPUESTA) "
                + "WHERE PROP.RFC_FIRMANTE = ? AND PROP.ID_ESTATUS = ? "
                + "GROUP BY PROP.ID_PROPUESTA, PROP.ID_REGISTRO, CONT.RFC, PROP.PRIORIDAD, MET.NOMBRE "
                + "ORDER BY PROP.PRIORIDAD DESC ";

        return getJdbcTemplateBase().query(query, new FecetPropRechVerifProcMapper(), rfcFirmante, idEstatus);
    }

    @Override
    public List<FecetPropRechPendientesValidacion> obtenerPropRechPendValid(BigDecimal idEstatus, String rfcFirmante) {
        String query = "SELECT PROP.ID_PROPUESTA, CONT.RFC, PROP.PRIORIDAD, MET.ABREVIATURA, SUM(IMP.MONTO) PRESUNTIVA "
                + "FROM FECET_PROPUESTA PROP, FECET_CONTRIBUYENTE CONT, FECEC_METODO MET, FECET_IMPUESTO IMP "
                + "WHERE PROP.RFC_FIRMANTE = ?  "
                + "AND PROP.ID_ESTATUS = ? "
                + "AND PROP.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE "
                + "AND PROP.ID_METODO = MET.ID_METODO "
                + "AND IMP.ID_PROPUESTA = PROP.ID_PROPUESTA "
                + "GROUP BY PROP.ID_PROPUESTA, CONT.RFC, PROP.PRIORIDAD, MET.ABREVIATURA "
                + "ORDER BY PROP.ID_PROPUESTA ";
        return getJdbcTemplateBase().query(query, new FecetPropRechPendValidMapper(), rfcFirmante, idEstatus);
    }

    @Override
    public List<FecetPropuesta> obtenerPropuestasRechazadasPendientesValid(BigDecimal idEstatus, String rfcFirmante) {
        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT_PROPUESTAS_RECHAZADAS_X_VALID);
        query.append(" INNER JOIN    FECET_CONTRIBUYENTE ON ( FECET_PROPUESTA.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE)  \n");
        query.append(" INNER JOIN   FECEC_SUBPROGRAMA ON (FECET_PROPUESTA.ID_SUBPROGRAMA = FECEC_SUBPROGRAMA.ID_SUBPROGRAMA) \n");
        query.append(" INNER JOIN  FECEC_ESTATUS ON (  FECET_PROPUESTA.ID_ESTATUS = FECEC_ESTATUS.ID_ESTATUS) \n");
        query.append(" INNER JOIN FECEC_SECTOR ON (   FECET_PROPUESTA.ID_SECTOR = FECEC_SECTOR.ID_SECTOR) \n");
        query.append(" INNER JOIN FECEC_METODO ON (   FECET_PROPUESTA.ID_METODO = FECEC_METODO.ID_METODO)  \n");
        query.append(" LEFT OUTER JOIN FECET_IMPUESTO ON (FECET_PROPUESTA.ID_PROPUESTA = FECET_IMPUESTO.ID_PROPUESTA) \n");
        query.append(" WHERE FECET_PROPUESTA.RFC_FIRMANTE = ? AND FECET_PROPUESTA.ID_ESTATUS = ? \n");
        query.append(" GROUP BY FECET_PROPUESTA.ID_PROPUESTA, FECET_PROPUESTA.ID_CONTRIBUYENTE, FECET_PROPUESTA.ID_ARACE, FECET_PROPUESTA.ID_SUBPROGRAMA, FECET_PROPUESTA.ID_METODO, FECET_PROPUESTA.ID_REVISION, FECET_PROPUESTA.ID_TIPO_PROPUESTA, FECET_PROPUESTA.ID_CAUSA_PROGRAMACION, FECET_PROPUESTA.ID_SECTOR, FECET_PROPUESTA.ID_REGISTRO, FECET_PROPUESTA.FECHA_INICIO_PERIODO, FECET_PROPUESTA.FECHA_FIN_PERIODO, FECET_PROPUESTA.FECHA_PRESENTACION, FECET_PROPUESTA.FECHA_INFORME, FECET_PROPUESTA.PRIORIDAD, FECET_PROPUESTA.FECHA_CREACION, FECET_PROPUESTA.FECHA_BAJA, FECET_PROPUESTA.RFC_CREACION, FECET_PROPUESTA.RFC_ADMINISTRADOR, FECET_PROPUESTA.RFC_SUBADMINISTRADOR, FECET_PROPUESTA.ID_ESTATUS, FECET_PROPUESTA.RFC_AUDITOR, FECET_PROPUESTA.RFC_FIRMANTE, FECET_PROPUESTA.INFORME_PRESENTACION, FECET_PROPUESTA.ID_INSUMO, FECET_PROPUESTA.ID_REGISTRO_INSUMO, FECET_PROPUESTA.ID_ORIGEN, FECEC_SUBPROGRAMA.ID_SUBPROGRAMA, FECEC_SUBPROGRAMA.CLAVE, FECEC_SUBPROGRAMA.DESCRIPCION, FECEC_SUBPROGRAMA.DESCRIPCION, FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE, FECET_CONTRIBUYENTE.RFC, FECET_CONTRIBUYENTE.NOMBRE, FECET_CONTRIBUYENTE.REGIMEN, FECET_CONTRIBUYENTE.SITUACION, FECET_CONTRIBUYENTE.TIPO, FECET_CONTRIBUYENTE.SITUACION_DOMICILIO, FECET_CONTRIBUYENTE.DOMICILIO_FISCAL, FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, FECET_CONTRIBUYENTE.ENTIDAD, FECEC_ESTATUS.ID_ESTATUS, FECEC_ESTATUS.DESCRIPCION, FECEC_ESTATUS.MODULO, FECEC_SECTOR.ID_SECTOR, FECEC_SECTOR.DESCRIPCION, FECEC_METODO.ID_METODO, FECEC_METODO.ABREVIATURA, FECEC_METODO.NOMBRE \n");
        query.append(" ORDER BY FECET_PROPUESTA.PRIORIDAD DESC \n");

        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaConRelacionesMapper(), rfcFirmante, idEstatus);
    }

    @Override
    public List<FecetPropTransferPendValidacion> obtenerPropTransferPendValid(BigDecimal idEstatus120, BigDecimal idEstatus73,
            String rfcFirmante) {
        String query = "SELECT PROP.ID_PROPUESTA, PROP.ID_REGISTRO, CONT.RFC, PROP.PRIORIDAD, MET.NOMBRE, AR.NOMBRE ADACE_DESTINO, NVL(SUM(IMP.MONTO),0) PRESUNTIVA "
                + "FROM FECET_PROPUESTA PROP, FECET_CONTRIBUYENTE CONT, FECEC_METODO MET, FECET_IMPUESTO IMP, FECET_TRANSFERENCIA TRANS, FECEC_UNIDAD_ADMINISTRATIVA AR "
                + "WHERE PROP.RFC_FIRMANTE = ? "
                + "AND (PROP.ID_ESTATUS = ? OR PROP.ID_ESTATUS = ?) "
                + "AND PROP.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE "
                + "AND PROP.ID_METODO = MET.ID_METODO "
                + "AND IMP.ID_PROPUESTA(+) = PROP.ID_PROPUESTA "
                + "AND TRANS.ID_PROPUESTA = PROP.ID_PROPUESTA "
                + "AND TRANS.ID_ARACE_DESTINO = AR.ID_UNIDAD_ADMINISTRATIVA AND BLN_ESTATUS = 1 "
                + "GROUP BY PROP.ID_PROPUESTA, PROP.ID_REGISTRO, CONT.RFC, PROP.PRIORIDAD, MET.NOMBRE, AR.NOMBRE "
                + "ORDER BY PROP.PRIORIDAD DESC ";
        return getJdbcTemplateBase().query(query, new FecetPropTransferPendValidMapper(), rfcFirmante, idEstatus120, idEstatus73);
    }

    @Override
    public BigInteger getContadorPropuestasAsignadasPorSubAdmin(String rfcSubAdmin, BigDecimal idEstatus) {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT COUNT(*) PROPUESTAS_ASIGNADAS ");
        query.append(" FROM FECET_PROPUESTA  ");
        query.append(" WHERE RFC_SUBADMINISTRADOR =  '");
        query.append(rfcSubAdmin);
        query.append("' AND ID_ESTATUS = ");
        query.append(idEstatus);

        return getJdbcTemplateBase().queryForObject(query.toString(), BigInteger.class);

    }

    @Override
    public List<FecetPropuesta> findWhereIdEstatusAndRfcAdminArace(final BigDecimal idEstatus, BigDecimal idArace) {
        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_SELECT);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" WHERE ID_ESTATUS = ? AND (ID_ARACE = ? ");
        if (idArace.equals(Constantes.PPCE)) {
            query.append("OR ID_ARACE = 19 OR ID_ARACE = 20 ) ");
        } else {
            query.append(") ");
        }
        query.append(" ORDER BY PRIORIDAD ASC, FECHA_CREACION ASC");
        logger.error(query + "\n" + idEstatus + "\n" + idArace);
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaMapper(), idEstatus, idArace);

    }

    @Override
    public void actualizarPropuestaFirmante(FecetPropuesta propuesta) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ?, RFC_FIRMANTE = ? WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), propuesta.getFececEstatus().getIdEstatus(),
                propuesta.getRfcFirmante(),
                propuesta.getIdPropuesta());
    }

    @Override
    public void actualizarPropuestaAuditor(FecetPropuesta propuesta) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ?, RFC_AUDITOR = ? WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), propuesta.getFececEstatus().getIdEstatus(),
                propuesta.getRfcAuditor(),
                propuesta.getIdPropuesta());
    }

    @Override
    public List<FecetPropuesta> getPropuestasXMetodoEstatusArace(String rfcCreacion, TipoEstatusEnum[] lstEstatusPropuesta, TipoMetodoEnum tipoMetodo, String prioridad, TipoAraceEnum... tiposArace) {
        Object[] sqlParams;

        if (tipoMetodo == null) {
            sqlParams = new Object[]{rfcCreacion};
        } else {
            sqlParams = new Object[]{rfcCreacion, tipoMetodo.getId(), prioridad};

        }

        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_DETALLE_PROPUESTA_HEDER);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_PROPUESTA);
        query.append(" WHERE ");
        query.append(" FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL  AND ");
        query.append(" FECET_PROPUESTA.RFC_CREACION = ? ");

        if (lstEstatusPropuesta != null && lstEstatusPropuesta.length > 0) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_ESTATUS.replace(PropuestasSQL.LST_ESTATUS, PropuestaDaoHelper.getParametrosEstatus(lstEstatusPropuesta)));
        }
        if (tipoMetodo != null) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_METODO);
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_PRIORIDAD);
        }
        query.append(AND);
        query.append(PropuestasSQL.SQL_CONDICION_ARACES);
        query.append(PropuestasSQL.SQL_DETALLE_PROPUESTA_FOOTER);

        for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString().replace(PropuestasSQL.ID_TEMPLATE, PropuestaDaoHelper.getParametroAraces(tiposArace)), new FecetPropuestaDetalleMapper(), sqlParams)) {
            logger.debug(query.toString().replace(PropuestasSQL.ID_TEMPLATE, PropuestaDaoHelper.getParametroAraces(tiposArace)));

            Collections.sort(lstCont, new Comparator<FecetPropuesta>() {

                @Override
                public int compare(FecetPropuesta propuesta, FecetPropuesta propuestaB) {
                    return propuesta.getPrioridadSugerida().compareTo(propuestaB.getPrioridadSugerida());
                }
            });

            return lstCont;
        }
        return new ArrayList<FecetPropuesta>();
    }

    @Override
    public List<FecetPropuesta> getPropuestasXCambioMetodo(String rfcCreacion, TipoEstatusEnum[] lstEstatusPropuesta, TipoAraceEnum... tiposArace) {

        Object[] sqlParams = {};

        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_DETALLE_PROPUESTA_CAMBIO_METODO_HEDER);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_CAMBIO_METODO_PROPUESTA);
        query.append(" WHERE ");
        query.append(" FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL  AND  FECET_CAMBIO_METODO.ID_ESTATUS=131 and ");
        query.append(PropuestasSQL.SQL_CONDICION_ARACES);
        query.append(PropuestasSQL.SQL_DETALLE_PROPUESTA_FOOTER);
        for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString().replace(PropuestasSQL.ID_TEMPLATE, PropuestaDaoHelper.getParametroAraces(tiposArace)), new FecetPropuestaCambioMetodoOrden(), sqlParams)) {
            logger.error(query.toString().replace(PropuestasSQL.ID_TEMPLATE, PropuestaDaoHelper.getParametroAraces(tiposArace)));
            logger.error("Tipos Arace : ", PropuestaDaoHelper.getParametroAraces(tiposArace));

            Collections.sort(lstCont, new Comparator<FecetPropuesta>() {

                @Override
                public int compare(FecetPropuesta propuesta1, FecetPropuesta propuesta2) {
                    return propuesta1.getPrioridadSugerida().compareTo(propuesta2.getPrioridadSugerida());
                }

            });

            return lstCont;
        }

        return new ArrayList<FecetPropuesta>();
    }

    @Override
    public List<FecetPropuesta> getPropuestasAsignacasCentralARegional(BigDecimal idProgramador,
            TipoEstatusEnum estatusPropuesta, TipoMetodoEnum tipoMetodo, Integer prioridad) {
        Object[] sqlParams;

        if (tipoMetodo == null) {
            sqlParams = new Object[]{idProgramador, estatusPropuesta.getId()};
        } else {
            sqlParams = new Object[]{idProgramador, estatusPropuesta.getId(), tipoMetodo.getId()};

        }

        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_DETALLE_PROPUESTA_HEDER);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_PROPUESTA);
        query.append(" WHERE FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL ");
        query.append(" AND FECET_PROPUESTA.ID_PROGRAMADOR = ? ");
        query.append(" AND FECET_PROPUESTA.ID_ESTATUS = ? ");
        if (tipoMetodo != null) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_METODO);
        }

        if (prioridad != null) {
            query.append(AND);
            query.append("FECET_PROPUESTA.PRIORIDAD <> ");
            query.append(prioridad);
        }

        query.append(PropuestasSQL.SQL_DETALLE_PROPUESTA_FOOTER);

        logger.error(query.toString());

        for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new FecetPropuestaDetalleMapper(), sqlParams)) {

            Collections.sort(lstCont, new Comparator<FecetPropuesta>() {

                @Override
                public int compare(FecetPropuesta propuesta1, FecetPropuesta propuesta2) {
                    return propuesta1.getPrioridadSugerida().compareTo(propuesta2.getPrioridadSugerida());
                }

            });
            return lstCont;
        }
        return new ArrayList<FecetPropuesta>();
    }

    @Override
    public List<FecetPropuesta> getPropuestasAsignacasCentralARegional(BigDecimal idProgramador, TipoEstatusEnum[] estatusPropuesta, String prioridad, TipoMetodoEnum tipoMetodo) {
        Object[] sqlParams;

        if (tipoMetodo == null) {
            sqlParams = new Object[]{idProgramador};
        } else {
            sqlParams = new Object[]{idProgramador, tipoMetodo.getId(), prioridad};

        }

        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_DETALLE_PROPUESTA_HEDER);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_PROPUESTA);
        query.append(" WHERE FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL ");
        query.append(" AND FECET_PROPUESTA.ID_PROGRAMADOR = ? ");

        if (estatusPropuesta != null && estatusPropuesta.length > 0) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_ESTATUS.replace(PropuestasSQL.LST_ESTATUS, PropuestaDaoHelper.getParametrosEstatus(estatusPropuesta)));
        }

        if (tipoMetodo != null) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_METODO);
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_PRIORIDAD);

        }
        query.append(PropuestasSQL.SQL_DETALLE_PROPUESTA_FOOTER);
        logger.debug(query.toString());
        for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new FecetPropuestaDetalleMapper(), sqlParams)) {

            return lstCont;
        }

        return new ArrayList<FecetPropuesta>();
    }

    @Override
    public List<FecetPropuesta> getPropuestasAsignaSubadminstraACentral(BigDecimal idProgramador, TipoEstatusEnum[] estatusPropuesta, TipoMetodoEnum tipoMetodo) {
        Object[] sqlParams;

        if (tipoMetodo == null) {
            sqlParams = new Object[]{idProgramador};
        } else {
            sqlParams = new Object[]{idProgramador, tipoMetodo.getId()};

        }

        StringBuilder query = new StringBuilder(PropuestasSQL.SQL_DETALLE_PROPUESTA_HEDER);
        query.append(PropuestasSQL.SQL_INNER_DETALLE_PROPUESTA);
        query.append(" WHERE FECET_IMPUESTO.FECHA_BAJA IS NULL and FECET_IMPUESTO.ID_RETROALIMENTACION IS NULL ");
        query.append(" AND FECET_PROPUESTA.ID_PROGRAMADOR = ? ");

        if (estatusPropuesta != null && estatusPropuesta.length > 0) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_ESTATUS.replace(PropuestasSQL.LST_ESTATUS, PropuestaDaoHelper.getParametrosEstatus(estatusPropuesta)));
        }

        if (tipoMetodo != null) {
            query.append(AND);
            query.append(PropuestasSQL.SQL_CONDICION_METODO);
        }
        query.append(PropuestasSQL.SQL_DETALLE_PROPUESTA_FOOTER);

        for (List<FecetPropuesta> lstCont : getJdbcTemplateBase().query(query.toString(), new FecetPropuestaDetalleMapper(), sqlParams)) {

            Collections.sort(lstCont, new Comparator<FecetPropuesta>() {

                @Override
                public int compare(FecetPropuesta propuesta1, FecetPropuesta propuesta2) {
                    return propuesta2.getPrioridad().compareTo(propuesta1.getPrioridad());
                }

            });

            return lstCont;
        }

        return new ArrayList<FecetPropuesta>();
    }

    @Override
    public List<FecetPropuesta> getPropuestasPorValidarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo,
            String rfcEmpleado, BigDecimal idAccionOrigen) {
        List<Object> parametros = new ArrayList<Object>();
        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_DETALLE_HEADER);
        if (idMetodo != null) {
            query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_ACCION_NULL);
            query.append(PropuestasSQL.SQL_CONDICION_POR_VALIDAR);
            query.append(" AND PROPUESTA.ID_METODO = ? ");
            parametros.add(idEstatus);
            parametros.add(rfcEmpleado);
            parametros.add(idMetodo);
        } else if (idAccionOrigen != null) {
            query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_ACCION);
            query.append(PropuestasSQL.SQL_CONDICION_POR_VALIDAR);
            parametros.add(idAccionOrigen);
            parametros.add(idEstatus);
            parametros.add(rfcEmpleado);
        }
        query.append(PropuestasSQL.SQL_CONDICION_POR_VALIDAR_ORDER);
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaResumenMapper(), parametros.toArray());
    }

    @Override
    public List<ProgramadorPropuestaAsignadaDTO> buscarProgramadorAsignar(String programadores, BigDecimal idCentral) {
        String query = "SELECT ID_PROGRAMADOR, COUNT(FECET_PROPUESTA.ID_PROGRAMADOR) NUMERO "
                + " FROM FECET_PROPUESTA  WHERE ID_PROGRAMADOR IN ("
                + programadores
                + ") "
                + " AND ID_ARACE = ?"
                + " GROUP BY ID_PROGRAMADOR ORDER BY NUMERO ASC";

        logger.error(query);
        return getJdbcTemplateBase().query(query, new ProgramadorPropuestaAsignadaMapper(), idCentral);
    }

    @Override
    public void actualizarIdProgramador(final BigDecimal idPropuesta, final BigDecimal idProgramador) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_PROGRAMADOR = ? WHERE ID_PROPUESTA = ? ");
        getJdbcTemplateBase().update(query.toString(), idProgramador, idPropuesta);
    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorPropuestasAuditor(String rfc, BigDecimal idArace) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*)  as  TOTAL_ASIGNADOS  FROM FECET_PROPUESTA P WHERE P.RFC_AUDITOR = ? AND P.ID_ARACE = ? ");
        return getJdbcTemplateBase().query(query.toString(), new ContadorInsumosSubAdminMapper(), rfc, idArace);
    }

    @Override
    public void actualizarEstatusNoAprobadaTransfer(final BigDecimal idPropuesta, final int idEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" SET ID_ESTATUS = ? WHERE ID_PROPUESTA = ?");
        getJdbcTemplateBase().update(query.toString(), idEstatus, idPropuesta);
    }

    @Override
    public List<FecetPropuesta> consultaEjecutivaPropuestasXFiltro(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo) {
        return PropuestaDaoHelper.consultaPropuestas(getJdbcTemplateBase(), filtroDao, grupo, TIPO_CONSULTA_EJECUTIVA);
    }

    @Override
    public List<FecetPropuesta> consultaEjecutivaPropuestasXFiltroCentralRegional(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo) {
        return PropuestaDaoHelper.consultaPropuestas(getJdbcTemplateBase(), filtroDao, grupo, TIPO_CONSULTA_CENTRAL_REGIONAL);
    }

    @Override
    public List<FecetPropuesta> consultaEjecutivaPropuestasAcppce(FiltroPropuestas filtroDao, AgrupadorEstatusPropuestasEnum grupo) {
        return PropuestaDaoHelper.consultaPropuestas(getJdbcTemplateBase(), filtroDao, grupo, TIPO_CONSULTA_ACPPCE);
    }

    @Override
    public List<FecetPropuesta> consultaPropuestaDetalle(String idRegistro) {
        StringBuilder query = new StringBuilder();
        query.append(PropuestasSQL.SQL_RESUMEN_PROPUESTA_FIRMANTE_DETALLE_HEADER);
        query.append(" WHERE PROPUESTA.ID_REGISTRO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropuestaResumenMapper(), idRegistro);
    }
}
