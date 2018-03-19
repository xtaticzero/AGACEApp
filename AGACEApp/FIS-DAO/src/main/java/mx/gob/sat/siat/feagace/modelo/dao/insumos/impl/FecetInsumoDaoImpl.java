/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.helper.InsumoDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.ConsultaAsignaInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.ContadorAsignadosAdministradorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.ContadorInsumosSubAdminMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetInsumoConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetInsumoContriSecSubAdmonEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoContSubproEstMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoConteoAdministradorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoRechazoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.InsumoRetroalimentacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.sql.FecetInsumoSQL;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumoPk;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FiltroInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.RetroalimentacionInsumoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenCargaMasivaDTO;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetInsumoDao")
public class FecetInsumoDaoImpl extends BaseJDBCDao<FecetInsumo> implements FecetInsumoDao {

    private static final long serialVersionUID = 3550227197800282054L;
    private static final String UPDATE = "UPDATE ";

    @Override
    public FecetInsumoPk insert(FecetInsumo dto) {

        if (dto.getIdInsumo() == null) {
            dto.setIdInsumo(getJdbcTemplateBase().queryForObject("SELECT FECEQ_INSUMO.NEXTVAL FROM DUAL", BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" ( ID_INSUMO, ID_CONTRIBUYENTE, ID_UNIDAD_ADMINISTRATIVA, ID_SUBPROGRAMA, ID_SECTOR, ID_REGISTRO, FECHA_INICIO_PERIODO, FECHA_FIN_PERIODO, PRIORIDAD, FECHA_CREACION, FECHA_BAJA, RFC_CREACION, RFC_ADMINISTRADOR, RFC_SUBADMINISTRADOR, ID_ESTATUS, ID_PRIORIDAD, ID_TIPO_INSUMO, JUSTIFICACION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdInsumo(), dto.getIdContribuyente(), dto.getIdArace(), dto.getIdSubprograma(), dto.getIdSector(), dto.getIdRegistro(), dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(), dto.getPrioridad(), dto.getFechaCreacion(), dto.getFechaBaja(), dto.getRfcCreacion(), dto.getRfcAdministrador(), dto.getRfcSubadministrador(), dto.getIdEstatus(), dto.getIdPrioridad(), dto.getFececTipoInsumo().getIdTipoInsumo(),dto.getJustificacion());
        return dto.createPk();

    }

    @Override
    public BigDecimal update(FecetInsumoPk pk, FecetInsumo dto) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" SET ID_INSUMO = ?, ID_CONTRIBUYENTE = ?, ID_UNIDAD_ADMINISTRATIVA = ?, ID_SUBPROGRAMA = ?, ID_SECTOR = ?, ID_REGISTRO = ?, FECHA_INICIO_PERIODO = ?, FECHA_FIN_PERIODO = ?, PRIORIDAD = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, RFC_CREACION = ?, RFC_ADMINISTRADOR = ?, RFC_SUBADMINISTRADOR = ?, ID_ESTATUS = ?, ID_PRIORIDAD = ?, ID_TIPO_INSUMO = ?, JUSTIFICACION = ? WHERE ID_INSUMO = ?");
        return getJdbcTemplateBase().update(query.toString(), dto.getIdInsumo(),
                dto.getIdContribuyente(), dto.getIdArace(),
                dto.getIdSubprograma(), dto.getIdSector(),
                dto.getIdRegistro(), dto.getFechaInicioPeriodo(),
                dto.getFechaFinPeriodo(), dto.getPrioridad(), dto.getFechaCreacion(),
                dto.getFechaBaja(), dto.getRfcCreacion(), dto.getRfcAdministrador(),
                dto.getRfcSubadministrador(), dto.getIdEstatus(), dto.getIdPrioridad(), dto.getIdTipoInsumo(), dto.getJustificacion(),
                pk.getIdInsumo()) <= 0 ? Constantes.BIG_DECIMAL_CERO : dto.getIdInsumo();

    }

    @PistaAuditoria
    @Override
    public String actualizarInsumoRegistro(FecetInsumo insumo) {
        update(insumo.createPk(), insumo);
        return insumo.getIdRegistro();
    }

    @Override
    public FecetInsumo findByPrimaryKey(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT);
        query.append(" FROM ");
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" WHERE ID_INSUMO = ?");
        List<FecetInsumo> list = getJdbcTemplateBase().query(query.toString(), new FecetInsumoMapper(), idInsumo);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public List<FecetInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT);
        query.append(FecetInsumoSQL.FROM);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" WHERE ID_INSUMO = ? ORDER BY ID_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoMapper(), idInsumo);
    }

    /**
     * Returns all rows from the FECET_INSUMO table that match the criteria .
     */
    @Override
    public List<FecetInsumo> buscarSeguimientoInsumoPorEstatus(BigDecimal estatus) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_ESTATUS_INSUMOS);
        query.append(" WHERE FECET_INSUMO.ID_ESTATUS = ? \n ");
        query.append(" ORDER BY FECET_INSUMO.PRIORIDAD DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoConRelacionesMapper(), estatus);

    }

    @Override
    public List<FecetInsumo> buscarSeguimientoInsumoPorEstatusRFCCreacion(BigDecimal estatus, String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_ESTATUS_INSUMOS);
        if (Constantes.INSUMO_RECHAZADO.equals(estatus)) {
            query.append(" INNER JOIN FECET_RECHAZO_INSUMO ON FECET_RECHAZO_INSUMO.ID_INSUMO = FECET_INSUMO.ID_INSUMO ");
            query.append(" WHERE    FECET_INSUMO.ID_ESTATUS = ? \n");
            query.append(" AND FECET_INSUMO.RFC_CREACION = ? \n ");
            query.append(" AND FECET_INSUMO.FECHA_BAJA IS NULL \n ");
            query.append(" ORDER BY PRIORIDAD.VALOR, FECET_RECHAZO_INSUMO.FECHA_RECHAZO DESC ");
        } else {
            query.append(" WHERE    FECET_INSUMO.ID_ESTATUS = ? \n");
            query.append(" AND FECET_INSUMO.RFC_CREACION = ? \n ");
            query.append(" AND FECET_INSUMO.FECHA_BAJA IS NULL \n ");
            query.append(" ORDER BY PRIORIDAD.VALOR ASC, FECET_INSUMO.FECHA_CREACION ASC ");
        }
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoConRelacionesMapper(), estatus, rfc);

    }

    /**
     * @param rfc
     * @param estatus
     * @return
     */
    @Override
    public List<FecetInsumo> buscarSeguimientoInsumoPorRFCCreacion(String rfc, BigDecimal... estatus) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_ESTATUS_INSUMOS);
        query.append(" WHERE FECET_INSUMO.RFC_CREACION = ? \n");
        if (estatus != null && estatus.length > 0) {
            query.append(" AND FECET_INSUMO.ID_ESTATUS  IN (");
            Iterator<BigDecimal> iterador = Arrays.asList(estatus).iterator();
            while (iterador.hasNext()) {
                query.append(iterador.next());
                if (iterador.hasNext()) {
                    query.append(", ");
                }
            }
            query.append(" ) \n");
        }
        query.append(" ORDER BY PRIORIDAD.VALOR, FECET_INSUMO.FECHA_CREACION ASC");
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoConRelacionesMapper(), rfc);

    }

    @Override
    public List<ContadorAsignadosAdministrador> getInsumosAsignadosAdministradores(List<String> lstRfcAdmin) {
        StringBuilder query = new StringBuilder();
        String parameters = InsumoDaoHelper.getSQLRFCCreacion(lstRfcAdmin);

        query.append("SELECT RFC_ADMINISTRADOR, \n"
                + "CON.ENTIDAD, COUNT(INSUMO.RFC_ADMINISTRADOR) AS TOTAL\n"
                + "FROM FECET_INSUMO INSUMO\n"
                + "JOIN FECET_CONTRIBUYENTE CON ON INSUMO.ID_CONTRIBUYENTE = CON.ID_CONTRIBUYENTE \n"
                + "WHERE INSUMO.ID_REGISTRO <> 'SIN FOLIO' \n"
                + "AND RFC_ADMINISTRADOR IN ({RFC_RFC_ADMINISTRADOR})\n"
                + "GROUP BY RFC_ADMINISTRADOR, CON.ENTIDAD \n"
                + "ORDER BY RFC_ADMINISTRADOR, ENTIDAD");
        return getJdbcTemplateBase().query(query.toString().replace("{RFC_RFC_ADMINISTRADOR}", parameters), new ContadorAsignadosAdministradorMapper());

    }

    @Override
    public List<FecetInsumo> getInsumosAdministrador(final String rfc) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT_CONTRIBUYENTE);
        query.append(" WHERE P.RFC_ADMINISTRADOR = ? \n");
        query.append(" ORDER BY P.ID_INSUMO ASC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoContriSecSubAdmonEstatusMapper(), rfc);

    }

    @Override
    public List<FecetInsumo> getInsumosAdministradorEntidad(String rfc, String entidad) {
        return getJdbcTemplateBase().query(FecetInsumoSQL.SQL_INSUMO_ADMINISTRADOR_ENTIDAD, new FecetInsumoContriSecSubAdmonEstatusMapper(), rfc, entidad);
    }

    @Override
    public List<FecetInsumo> consultarInsumosContSubproEst(final String rfcSubadministrador) {
        StringBuilder query = new StringBuilder();
        List<Object> parametros = new ArrayList<Object>();
        parametros.add(Constantes.INSUMO_ASIGNADO_A_SUBADMINISTRADOR);
        parametros.add(Constantes.INSUMO_RETROALIMENTADO);
        parametros.add(Constantes.INSUMO_ACEPTADO);
        parametros.add(rfcSubadministrador);
        query.append(FecetInsumoSQL.SQL_SELECT_INSUMO_CONTRIBUYENTE_SUBPROGRAMA_ESTATUS);
        query.append("\n WHERE (INSUMO.ID_ESTATUS = ? OR INSUMO.ID_ESTATUS = ? OR INSUMO.ID_INSUMO IN ( ");
        query.append("\n\t SELECT AUX.ID_INSUMO FROM FECET_INSUMO AUX LEFT JOIN FECET_PROPUESTA PROP ON PROP.ID_INSUMO = AUX.ID_INSUMO ");
        query.append("\n\t WHERE INSUMO.RFC_SUBADMINISTRADOR = AUX.RFC_SUBADMINISTRADOR ");
        query.append("\n\t AND AUX.ID_ESTATUS = ? ");
        query.append("\n\t AND PROP.ID_PROPUESTA IS NULL)) ");
        query.append("\n AND INSUMO.RFC_SUBADMINISTRADOR = ? ");
        query.append(" ORDER BY FECEC_PRIORIDAD.VALOR ASC, INSUMO.FECHA_CREACION ASC");
        return getJdbcTemplateBase().query(query.toString(), new InsumoContSubproEstMapper(), parametros.toArray());
    }

    @Override
    public BigDecimal actualizarEstatus(final BigDecimal idInsumo, final int idEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" SET ID_ESTATUS = ? WHERE ID_INSUMO = ?");
        return getJdbcTemplateBase().update(query.toString(), idEstatus, idInsumo) <= 0
                ? Constantes.BIG_DECIMAL_CERO : idInsumo;
    }

    @Override
    public BigDecimal actualizarEstatusFechaAprobacion(final BigDecimal idInsumo, final Date fechaAprobacion, final int idEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" SET ID_ESTATUS = ?, FECHA_APROBADO= ? WHERE ID_INSUMO = ?");
        return getJdbcTemplateBase().update(query.toString(), idEstatus, fechaAprobacion, idInsumo) <= 0
                ? Constantes.BIG_DECIMAL_CERO : idInsumo;
    }

    @Override
    public boolean actualizarFechaInicioPlazo(final BigDecimal idInsumo, boolean existePlazo) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" SET PRIORIDAD = 1");
        if (existePlazo) {
            query.append(", FECHA_INICIO_PLAZO = SYSDATE ");
        }
        query.append(" WHERE ID_INSUMO = ? ");
        query.append(" AND FECHA_INICIO_PLAZO IS NULL ");
        return getJdbcTemplateBase().update(query.toString(), idInsumo) > 0;
    }

    @Override
    public BigDecimal getFolioDisponible() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_ID_REGISTRO_INSUMO.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public List<FecetInsumo> traeInsumosSinAsignados(final String rfcAdministrador) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT_INSUMOS_NO_ASIGNADOS);
        query.append(FecetInsumoSQL.SQL_CONDICION);
        query.append(FecetInsumoSQL.SQL_CONDICION2);
        query.append(FecetInsumoSQL.SQL_CONDICION3);
        query.append(FecetInsumoSQL.SQL_JOIN_PRIORIDAD);
        query.append(FecetInsumoSQL.SQL_JOIN_ESTATUS);
        query.append(FecetInsumoSQL.SQL_JOIN_REASIGNACION);
        query.append(FecetInsumoSQL.SQL_JOIN_TIPO_INSUMO);        
        query.append("  WHERE FECET_INSUMO.ID_ESTATUS IN (1,6,7) AND FECET_INSUMO.RFC_ADMINISTRADOR = ? \n");
        query.append(" AND FECET_INSUMO.RFC_SUBADMINISTRADOR IS NULL ORDER BY FECET_INSUMO.ID_PRIORIDAD ASC, FECET_INSUMO.FECHA_CREACION \n");
        return getJdbcTemplateBase().query(query.toString(), new ConsultaAsignaInsumoMapper(), rfcAdministrador);

    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorSubAdministradores() {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SELECT);
        query.append("  EMP.NOMBRE \n");
        query.append("  , COUNT(P.RFC_SUBADMINISTRADOR) TOTAL_ASIGNADOS\n");
        query.append("FROM FECEC_EMPLEADO EMP INNER JOIN FECET_DETALLE_EMPLEADO DE \n");
        query.append("ON EMP.ID_EMPLEADO = DE.ID_EMPLEADO \n");
        query.append("LEFT JOIN FECET_INSUMO P \n");
        query.append("ON P.RFC_SUBADMINISTRADOR = EMP.RFC \n");
        query.append("WHERE DE.ID_TIPO_EMPLEADO = 4 AND DE.ID_CENTRAL = 1  AND EMP.ID_ESTATUS_EMPLEADO = 1 \n");
        query.append("GROUP BY EMP.NOMBRE, EMP.RFC \n");
        query.append("ORDER BY TOTAL_ASIGNADOS DESC");
        return getJdbcTemplateBase().query(query.toString(), new ContadorInsumosSubAdminMapper());

    }

    @Override
    public List<ContadorInsumosSubAdmin> getContadorSubAdministradoresByAdministrador(List<String> rfcs) {
        StringBuilder query = new StringBuilder();
        query.append("    SELECT INS.RFC_SUBADMINISTRADOR, COUNT(INS.ID_INSUMO) TOTAL_ASIGNADOS \n");
        query.append("      FROM FECET_INSUMO INS \n");
        query.append("        WHERE INS.RFC_SUBADMINISTRADOR  IN (");
        query.append(InsumoDaoHelper.getSQLRFCCreacion(rfcs));
        query.append(") \n AND INS.ID_ESTATUS IN (4) \n");
        query.append("  GROUP BY INS.RFC_SUBADMINISTRADOR ");

        return getJdbcTemplateBase().query(query.toString(), new ContadorInsumosSubAdminMapper());
    }

    @Override
    public List<FecetInsumo> buscarAntecedentesInsumos(String rfc, FecetPropuesta dto) {
        StringBuilder sql = new StringBuilder();

        Object[] params = new Object[]{rfc, Constantes.ID50, Constantes.ID64, Constantes.ID65,
            Constantes.ID66, Constantes.ID67, dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(),
            dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo()};

        sql.append("SELECT ins.ID_INSUMO, ins.ID_CONTRIBUYENTE, ins.ID_UNIDAD_ADMINISTRATIVA, ins.ID_SUBPROGRAMA, ins.ID_SECTOR, ins.ID_REGISTRO, \n");
        sql.append("ins.FECHA_INICIO_PERIODO, ins.FECHA_FIN_PERIODO, ins.FECHA_CREACION, ins.PRIORIDAD, ins.FECHA_BAJA, ins.RFC_CREACION, \n");
        sql.append("ins.RFC_ADMINISTRADOR, ins.RFC_SUBADMINISTRADOR, ins.ID_ESTATUS, ins.ID_PRIORIDAD, contr.RFC, est.DESCRIPCION, sub.DESCRIPCION DESCRIPCION_SUBPROGRAMA \n");
        sql.append("FROM FECET_INSUMO ins ");
        sql.append("INNER JOIN FECET_CONTRIBUYENTE contr ON (ins.ID_CONTRIBUYENTE = contr.ID_CONTRIBUYENTE) ");
        sql.append("INNER JOIN FECEC_ESTATUS est ON (est.ID_ESTATUS = ins.ID_ESTATUS) ");
        sql.append("INNER JOIN FECEC_SUBPROGRAMA sub ON (sub.ID_SUBPROGRAMA = ins.ID_SUBPROGRAMA) ");
        sql.append("WHERE contr.RFC = ? AND ins.ID_ESTATUS NOT IN (?,?,?,?,?) ");
        sql.append(" AND TRUNC(ins.FECHA_INICIO_PERIODO) BETWEEN ");
        sql.append("TRUNC(?)").append(" AND TRUNC(?)\n");
        sql.append(" AND TRUNC(ins.FECHA_FIN_PERIODO) BETWEEN ");
        sql.append("TRUNC(?)").append(" AND TRUNC(?)\n");
        return getJdbcTemplateBase().query(sql.toString(), new FecetInsumoMapper(), params);
    }

    @Override
    public List<FecetInsumo> buscarAntecedentesInsumosPeriodoExacto(String rfc, FecetPropuesta dto) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        params.add(rfc);
        params.add(Constantes.INSUMO_AGREGADO);
        params.add(Constantes.INSUMO_RECHAZADO);
        params.add(Constantes.INSUMO_SIN_ADMINISTRADOR);
        params.add(dto.getFechaInicioPeriodo());
        params.add(dto.getFechaFinPeriodo());

        sql.append("SELECT INS.ID_INSUMO, INS.ID_CONTRIBUYENTE, INS.ID_UNIDAD_ADMINISTRATIVA, INS.ID_SUBPROGRAMA, INS.ID_SECTOR, INS.ID_REGISTRO, \n");
        sql.append("INS.FECHA_INICIO_PERIODO, INS.FECHA_FIN_PERIODO, INS.FECHA_CREACION, INS.PRIORIDAD, INS.FECHA_BAJA, INS.RFC_CREACION, \n");
        sql.append("INS.RFC_ADMINISTRADOR, INS.RFC_SUBADMINISTRADOR, INS.ID_ESTATUS, INS.ID_PRIORIDAD \n");
        sql.append("FROM FECET_INSUMO INS ");
        sql.append("INNER JOIN FECET_CONTRIBUYENTE CONTR ON (INS.ID_CONTRIBUYENTE = CONTR.ID_CONTRIBUYENTE) ");
        sql.append("WHERE CONTR.RFC = ? AND INS.ID_ESTATUS NOT IN (?,?,?) \n ");
        sql.append(" AND TRUNC(INS.FECHA_INICIO_PERIODO) = TRUNC(?) \n ");
        sql.append(" AND TRUNC(INS.FECHA_FIN_PERIODO) = TRUNC(?)  \n ");

        if (dto.getIdInsumo() != null && dto.getIdInsumo().compareTo(BigDecimal.ZERO) > 0) {
            sql.append(" AND INS.ID_INSUMO <> ? \n ");
            params.add(dto.getIdInsumo());
        }

        if (dto.getIdArace() != null && dto.getIdArace().compareTo(BigDecimal.ZERO) > 0) {
            sql.append(" AND INS.ID_UNIDAD_ADMINISTRATIVA = ? \n");
            params.add(dto.getIdArace());
        }

        if (dto.getIdSubprograma() != null && dto.getIdSubprograma().compareTo(BigDecimal.ZERO) > 0) {
            sql.append(" AND INS.ID_SUBPROGRAMA = ? \n");
            params.add(dto.getIdSubprograma());
        }

        return getJdbcTemplateBase().query(sql.toString(), new FecetInsumoMapper(), params.toArray());
    }

    @Override
    public boolean existeRegistroDuplicado(FecetInsumo insumo, String rfc) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT ID_INSUMO, FECET_INSUMO.ID_CONTRIBUYENTE, ID_UNIDAD_ADMINISTRATIVA, ID_SUBPROGRAMA, ID_SECTOR, ID_REGISTRO, FECHA_INICIO_PERIODO, FECHA_FIN_PERIODO, PRIORIDAD, FECHA_CREACION, FECHA_BAJA, RFC_CREACION, RFC_ADMINISTRADOR, RFC_SUBADMINISTRADOR, ID_ESTATUS, ID_PRIORIDAD");
        query.append("\n FROM FECET_INSUMO ");
        query.append("\n JOIN FECET_CONTRIBUYENTE CON ON CON.ID_CONTRIBUYENTE = FECET_INSUMO.ID_CONTRIBUYENTE AND CON.RFC = ? ");
        query.append("\n WHERE ID_SUBPROGRAMA = ? ");
        query.append("\n AND TRUNC(FECHA_INICIO_PERIODO) = TRUNC(?) ");
        query.append("\n AND TRUNC(FECHA_FIN_PERIODO) = TRUNC(?) ");
        query.append("\n AND ID_INSUMO <> ? ");

        List<FecetInsumo> registros = getJdbcTemplateBase().query(query.toString(), new FecetInsumoMapper(), rfc, insumo.getIdSubprograma(),
                insumo.getFechaInicioPeriodo(), insumo.getFechaFinPeriodo(), insumo.getIdInsumo());
        return registros != null && !registros.isEmpty();
    }

    @Override
    public Date obtenerFechaRechazoInsumo(BigDecimal idInsumo) {
        Date fecha;
        StringBuilder query = new StringBuilder();
        Object[] params = new Object[]{idInsumo};
        query.append(" SELECT FRI.FECHA_RECHAZO FROM FECET_INSUMO FI ");
        query.append(" INNER JOIN FECET_RECHAZO_INSUMO FRI ON FRI.ID_INSUMO = FI.ID_INSUMO ");
        query.append(" WHERE FI.ID_INSUMO = ? ");
        try {
            fecha = getJdbcTemplateBase().queryForObject(query.toString(), params, Date.class);
        } catch (Exception e) {
            fecha = null;
        }
        return fecha;
    }

    @Override
    public Date obtenerFechaAprobacionInsumo(BigDecimal idInsumo) {
        Date fecha;
        StringBuilder query = new StringBuilder();
        Object[] params = new Object[]{idInsumo};
        query.append(" SELECT FI.FECHA_APROBADO FROM FECET_INSUMO FI ");
        query.append(" WHERE FI.ID_INSUMO = ? ");
        try {
            fecha = getJdbcTemplateBase().queryForObject(query.toString(), params, Date.class);
        } catch (Exception e) {
            fecha = null;
        }
        return fecha;
    }

    /**
     * Returns all rows from the FECET_INSUMO table that match the criteria
     * 'ID_ESTATUS = :idEstatus'.
     */
    @Override
    public List<FecetInsumo> obtenerInsumosANotificarCambioSemaforo() {

        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT);
        query.append(FecetInsumoSQL.FROM);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" WHERE FECHA_INICIO_PLAZO IS NOT NULL ");
        query.append(" ORDER BY ID_INSUMO ASC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetInsumoMapper());

    }

    @Override
    public List<FecetInsumo> consultaEjecutiva(FiltroInsumos filtroDeBusqueda) {
        String query;
        StringBuilder sqlQuery = new StringBuilder(FecetInsumoSQL.HEDER_DETALLE_INSUMO);
        if (filtroDeBusqueda.isCentralACPPCEReg()) {
            sqlQuery.append(FecetInsumoSQL.FILTRO_RFC_CREACION_MULTIPLE.replace(FecetInsumoSQL.RFC_CREACION, InsumoDaoHelper.getSQLRFCCreacion(filtroDeBusqueda.getRfcUsuarioACIACE())));
        }
        sqlQuery.append(FecetInsumoSQL.FILTRO_UNIDAD_ADMINISTRATIVA);
        sqlQuery.append(FecetInsumoSQL.FILTRO_ESTATUS);

        if (filtroDeBusqueda.getEstusDeDocumentos() == null) {
            filtroDeBusqueda.setEstusDeDocumentos(EstadoBooleanoEnum.TRUE);
        }

        sqlQuery.append(FecetInsumoSQL.FILTRO_ESTATUS_DOC_EXP_INSUMO.replace(FecetInsumoSQL.ESTATUS_BLN_DOC_EXP_INSUMO, String.valueOf(filtroDeBusqueda.getEstusDeDocumentos().getEstado())));

        TipoEmpleadoEnum tipoEmpleado = InsumoDaoHelper.obtenerRolEmpleadoConsultaInsumo(filtroDeBusqueda.getEmpleadoConsultaFiltro());
        if (tipoEmpleado != null) {
            int tipoConsulta = (int) tipoEmpleado.getId();

            switch (tipoConsulta) {
                case InsumoDaoHelper.TIPO_CONSULTA_CENTRAL:
                    break;
                case InsumoDaoHelper.TIPO_CONSULTA_ADMINISTRADOR:

                    if (!filtroDeBusqueda.isCentralACPPCEReg() && !filtroDeBusqueda.isVisibilidadACIACE()) {
                        sqlQuery.append(FecetInsumoSQL.FILTRO_RFC_ADMINISTRADOR);
                        query = sqlQuery.toString();
                        query = query.replace(FecetInsumoSQL.RFC_ADMINISTRADOR, FecetInsumoSQL.PARAMETRO_QUERY);
                        sqlQuery = new StringBuilder(query);
                    }
                    break;
                case InsumoDaoHelper.TIPO_CONSULTA_SUBADMINISTRADOR:
                    if (!filtroDeBusqueda.isVisibilidadACIACE()) {
                        sqlQuery.append(FecetInsumoSQL.FILTRO_RFC_SUBADMINISTRADOR);
                        query = sqlQuery.toString();
                        query = query.replace(FecetInsumoSQL.RFC_SUBADMINISTRADOR, FecetInsumoSQL.PARAMETRO_QUERY);
                        sqlQuery = new StringBuilder(query);
                    }
                    break;
                case InsumoDaoHelper.TIPO_CONSULTA_USUARIO_ACIACE:
                    sqlQuery.append(FecetInsumoSQL.FILTRO_RFC_CREACION);
                    query = sqlQuery.toString();
                    query = query.replace(FecetInsumoSQL.RFC_CREACION, FecetInsumoSQL.PARAMETRO_QUERY);
                    sqlQuery = new StringBuilder(query);
                    break;
                default:
                    return new ArrayList<FecetInsumo>();
            }
            sqlQuery.append(FecetInsumoSQL.FOOTER_DETALLE_INSUMO);
            query = sqlQuery.toString();

            query = query.replace(FecetInsumoSQL.ESTATUS,
                    InsumoDaoHelper.getSQLEstatusInsumo(filtroDeBusqueda.getEstatusFiltro()));
            query = query.replace(FecetInsumoSQL.UNIDADES_ADMINISTRATIVAS,
                    InsumoDaoHelper.getSQLUnidadesAdministrativas(filtroDeBusqueda.getUnidadAdmtvaDesahogoFiltro()));

            if (query.contains(FecetInsumoSQL.PARAMETRO_QUERY)) {
                for (List<FecetInsumo> lstCont : getJdbcTemplateBase().query(query, new InsumoDetalleMapper(), filtroDeBusqueda.getEmpleadoConsultaFiltro().getRfc())) {
                    orderLstInsumo(lstCont);
                    return lstCont;
                }
            } else {
                for (List<FecetInsumo> lstCont : getJdbcTemplateBase().query(query, new InsumoDetalleMapper())) {
                    orderLstInsumo(lstCont);
                    return lstCont;
                }
            }
        }
        return new ArrayList<FecetInsumo>();
    }

    private void orderLstInsumo(List<FecetInsumo> lstResult) {
        Collections.sort(lstResult, new Comparator<FecetInsumo>() {
            @Override
            public int compare(FecetInsumo insumo1, FecetInsumo insumo2) {
                return insumo2.getPrioridad().compareTo(insumo1.getPrioridad());
            }
        });
    }

    @Override
    public Integer countInsumosXEstatus(String rfcEmpleado,
            TipoEmpleadoEnum tipoEmpleado,
            List<TipoEstatusEnum> estatusInsumo,
            List<AraceDTO> lstUnidAdministrativas,
            FiltroInsumos filtroDeBusqueda,
            boolean isAciace) {

        String query;
        StringBuilder sqlQuery = new StringBuilder(FecetInsumoSQL.HEADER_COUNT_INSUMOS);
        sqlQuery.append(FecetInsumoSQL.CONDICION_UNIDAD_ADMINISTRATIVA);

        if (filtroDeBusqueda.isCentralACPPCEReg()) {
            sqlQuery.append(FecetInsumoSQL.FILTRO_RFC_CREACION_MULTIPLE.replace(FecetInsumoSQL.RFC_CREACION, InsumoDaoHelper.getSQLRFCCreacion(filtroDeBusqueda.getRfcUsuarioACIACE())));
        }
        query = InsumoDaoHelper.getQueryCount(sqlQuery, tipoEmpleado, isAciace, filtroDeBusqueda);
        query = query.replace(FecetInsumoSQL.ESTATUS,
                InsumoDaoHelper.getSQLEstatusInsumo(estatusInsumo));
        query = query.replace(FecetInsumoSQL.UNIDADES_ADMINISTRATIVAS,
                InsumoDaoHelper.getSQLUnidadesAdministrativas(lstUnidAdministrativas));

        Integer numeroDeClaves = null;
        if (query.contains(FecetInsumoSQL.PARAMETRO_QUERY)) {
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query, rfcEmpleado);
            while (srs.next()) {
                numeroDeClaves = srs.getInt(FecetInsumoSQL.COLUM_TOTAL);
            }
            return numeroDeClaves;
        } else {
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query);
            while (srs.next()) {
                numeroDeClaves = srs.getInt(FecetInsumoSQL.COLUM_TOTAL);
            }
            return numeroDeClaves;
        }
    }

    @Override
    public Integer countInsumosXEmpleado(String rfcEmpleado,
            TipoEmpleadoEnum tipoEmpleado,
            List<TipoEstatusEnum> lstEstatusInsumo,
            List<AraceDTO> lstUnidAdministrativas,
            boolean isAciace) {

        String query;
        StringBuilder sqlQuery = new StringBuilder(FecetInsumoSQL.HEADER_COUNT_INSUMOS);

        sqlQuery.append(FecetInsumoSQL.CONDICION_UNIDAD_ADMINISTRATIVA);

        query = InsumoDaoHelper.getQueryCount(sqlQuery, tipoEmpleado, isAciace, null);
        query = query.replace(FecetInsumoSQL.ESTATUS,
                InsumoDaoHelper.getSQLEstatusInsumo(lstEstatusInsumo));
        query = query.replace(FecetInsumoSQL.UNIDADES_ADMINISTRATIVAS,
                InsumoDaoHelper.getSQLUnidadesAdministrativas(lstUnidAdministrativas));

        int numeroDeClaves = 0;
        if (query.contains(FecetInsumoSQL.PARAMETRO_QUERY)) {
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query, rfcEmpleado);
            while (srs.next()) {
                numeroDeClaves = srs.getInt(FecetInsumoSQL.COLUM_TOTAL);
            }
            return numeroDeClaves;
        } else {
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query);
            while (srs.next()) {
                numeroDeClaves = srs.getInt(FecetInsumoSQL.COLUM_TOTAL);
            }
            return numeroDeClaves;
        }
    }

    @Override
    public FecetInsumo getRetroalimemtacionesDeInsumo(FecetInsumo insumo) {
        if (insumo != null && insumo.getIdInsumo() != null) {
            List<RetroalimentacionInsumoDTO> lstRetro = getJdbcTemplateBase().query(FecetInsumoSQL.SQL_RETROALIMENTACIONES_DE_INSUMO,
                    new InsumoRetroalimentacionMapper(),
                    insumo.getIdInsumo());
            insumo.setLstRetroalimentacion(lstRetro);
            return insumo;

        }
        return null;
    }

    @Override
    public FecetInsumo getRechazoDeInsumo(FecetInsumo insumo) {
        if (insumo != null && insumo.getIdInsumo() != null) {
            for (List<FecetRechazoInsumo> lstRechazoInsumo : getJdbcTemplateBase().query(FecetInsumoSQL.SQL_RECHAZO_INSUMO, new InsumoRechazoMapper(), insumo.getIdInsumo())) {
                for (FecetRechazoInsumo rechazo : lstRechazoInsumo) {
                    insumo.setRechazoInsumo(rechazo);
                    return insumo;
                }
            }
            insumo.setRechazoInsumo(null);
            return insumo;
        }
        return null;
    }

    @Override
    public List<BigDecimal> getNumEmpleadosUnidadEstadoTipoEmpleado(BigDecimal idUnidadAdministrativa, String estado, BigDecimal idTipoEmpleado) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT AEE.ID_EMPLEADO ");
        query.append("FROM FECEA_ADMON_EDO_EMP AEE ");
        query.append("INNER JOIN FECEA_ADMINISTRACION_ESTADO AE ON AE.ID_ADMINISTRACION_ESTADO = AEE.ID_ADMINISTRACION_ESTADO ");
        query.append("INNER JOIN FECEC_ESTADOS EDO ON EDO.ID_ESTADOS = AE.ID_ESTADOS ");
        query.append("WHERE AEE.ID_TIPO_EMPLEADO = ?");
        query.append("  AND AEE.ID_UNIDAD_ADMINISTRATIVA = ?");
        query.append("  AND CONVERT(UPPER(TRANSLATE(TRIM(EDO.NOMBRE),UNISTR('\u00F1\u00D1'),UNISTR('\u006E\u004E'))),'US7ASCII') ");
        query.append("    = CONVERT(UPPER(TRANSLATE(TRIM(?),UNISTR('\u00F1\u00D1'),UNISTR('\u006E\u004E'))),'US7ASCII')");
        query.append(" AND AEE.BLN_ACTIVO = 1 ");
        List<BigDecimal> list = getJdbcTemplateBase().queryForList(query.toString(), BigDecimal.class, idTipoEmpleado, idUnidadAdministrativa, estado);

        if (list == null || list.isEmpty()) {
            query.setLength(0);
            query.append("SELECT DISTINCT AEE.ID_EMPLEADO ");
            query.append("FROM FECEA_ADMON_EDO_EMP AEE ");
            query.append("INNER JOIN FECEA_ADMINISTRACION_ESTADO AE ON AE.ID_ADMINISTRACION_ESTADO = AEE.ID_ADMINISTRACION_ESTADO ");
            query.append("INNER JOIN FECEC_ESTADOS EDO ON EDO.ID_ESTADOS = AE.ID_ESTADOS ");
            query.append("WHERE AEE.ID_TIPO_EMPLEADO = ?");
            query.append(" AND AEE.ID_UNIDAD_ADMINISTRATIVA = ? ");
            query.append(" AND AEE.BLN_ACTIVO = 1 ");
            list = getJdbcTemplateBase().queryForList(query.toString(), BigDecimal.class, idTipoEmpleado, idUnidadAdministrativa);

        }

        return list;
    }

    @Override
    public Map<String, Long> obtenerContadorAdministadores(List<TipoEstatusEnum> estatus, List<String> rfcs) {
        Map<String, Long> resultados = new LinkedHashMap<String, Long>();
        if (rfcs != null && !rfcs.isEmpty() && estatus != null && !estatus.isEmpty()) {
            String query = FecetInsumoSQL.TOTAL_INSUMO_ADMINISTRADOR;
            query = query.replace(FecetInsumoSQL.ESTATUS, InsumoDaoHelper.getSQLEstatusInsumo(estatus));
            query = query.replace(FecetInsumoSQL.RFC_CREACION, InsumoDaoHelper.getSQLRFCCreacion(rfcs));
            List<Map<String, Long>> resultado = getJdbcTemplateBase().query(query, new InsumoConteoAdministradorMapper());
            for (Map<String, Long> map : resultado) {
                resultados.putAll(map);
                break;
            }
        }
        return resultados;
    }

    @Override
    public void insertaResumenMasiva(final ResumenCargaMasivaDTO resumenDTO, String identificador) {
        try {
            StringBuilder query = new StringBuilder();
            query.append(FecetInsumoSQL.INSERT_RESUMEN_MASIVA);
            getJdbcTemplateBase().update(query.toString(), identificador, resumenDTO.getTotalRegistros(), resumenDTO.getIdOrigen());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public FecetInsumo findByIdRegistro(String idRegistro) {
        StringBuilder query = new StringBuilder();
        query.append(FecetInsumoSQL.SQL_SELECT);
        query.append(" FROM ");
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" WHERE ID_REGISTRO = ?");
        List<FecetInsumo> list = getJdbcTemplateBase().query(query.toString(), new FecetInsumoMapper(), idRegistro);
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public void updateFechaInicioPlazo(FecetInsumo insumo) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(FecetInsumoSQL.TABLE_NAME);
        query.append(" SET FECHA_INICIO_PLAZO = ? ");
        query.append(" WHERE ID_INSUMO = ? ");

        getJdbcTemplateBase().update(query.toString(), insumo.getFechaInicioPlazo(), insumo.getIdInsumo());

    }

}
