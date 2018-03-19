package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.AsignarSuplenciaAFirmanteDAO;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.AsignaSuplenciaAFirmanteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.AsignarSuplenciaAFirmanteModel;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;

@Repository("AsignarSuplenciaAFirmanteDAO")
public class AsignarSuplenciaAFirmanteDAOImpl extends BaseJDBCDao<FecetSuplenciaDTO> implements AsignarSuplenciaAFirmanteDAO {

    private static final long serialVersionUID = 1L;

    private static final String SQL_BUSCAR_LISTA_SUPLENCIA
            = "SELECT S.ID_SUPLENCIA, S.ID_FIRMANTE_A_SUPLIR, S.ID_FIRMANTE_SUPLENTE, "
            + "S.ID_MOTIVO_SUPLENCIA, S.FECHA_INICIO, S.FECHA_FIN, S.ID_ESTATUS_SUPLENCIA, "
            + "ES.DESCRIPCION AS ESTATUS_DESC, MS.DESCRIPCION AS MOTIVO_DESC FROM FECET_SUPLENCIA S "
            + "INNER JOIN FECEC_ESTATUS_SUPLENCIA ES ON S.ID_ESTATUS_SUPLENCIA = ES.ID_ESTATUS_SUPLENCIA "
            + "INNER JOIN FECEC_MOTIVO_SUPLENCIA MS ON S.ID_MOTIVO_SUPLENCIA = MS.ID_MOTIVO_SUPLENCIA ";
    private static final String SQL_CANCELA_SUPLENCIA
            = "UPDATE FECET_SUPLENCIA SET ID_ESTATUS_SUPLENCIA = ? WHERE ID_SUPLENCIA = ?";
    private static final String SQL_TERMINA_SUPLENCIA
            = "UPDATE FECET_SUPLENCIA SET ID_ESTATUS_SUPLENCIA = 5 WHERE TRUNC(FECHA_FIN) = "
            + "TRUNC(SYSDATE) AND ID_ESTATUS_SUPLENCIA = 1";
    private static final String SQL_CANCELAR_SUPLENCIA
            = "UPDATE FECET_SUPLENCIA SET ID_ESTATUS_SUPLENCIA = 3 WHERE TRUNC(FECHA_FIN) = "
            + "TRUNC(SYSDATE) AND ID_ESTATUS_SUPLENCIA = 4";
    private static final String SQL_ACTIVA_SUPLENCIA
            = "UPDATE FECET_SUPLENCIA SET ID_ESTATUS_SUPLENCIA = 1 WHERE TRUNC(FECHA_INICIO) = "
            + "TRUNC(SYSDATE) AND ID_ESTATUS_SUPLENCIA = 2";
    private static final String BUSCAR_SUPLENCIA_SIMILAR_SUPLENTE
            = "SELECT ID_SUPLENCIA FROM  FECET_SUPLENCIA WHERE ID_FIRMANTE_SUPLENTE = ? AND (((FECHA_INICIO <= ?\n"
            + "AND FECHA_FIN >= ?) \n"
            + "OR (FECHA_INICIO <= ?\n"
            + "AND FECHA_FIN >= ?))\n"
            + "OR ((FECHA_INICIO >= ?\n"
            + "AND FECHA_INICIO <= ?) \n"
            + "OR (FECHA_FIN >= ?\n"
            + "AND FECHA_FIN <= ?)))\n"
            + "AND ID_ESTATUS_SUPLENCIA NOT IN(3, 4, 5)";
    private static final String BUSCAR_SUPLENCIA_SIMILAR_A_SUPLIR
            = "SELECT ID_SUPLENCIA FROM  FECET_SUPLENCIA WHERE ID_FIRMANTE_A_SUPLIR = ? AND (((FECHA_INICIO <= ?\n"
            + "AND FECHA_FIN >= ?) \n"
            + "OR (FECHA_INICIO <= ?\n"
            + "AND FECHA_FIN >= ?))\n"
            + "OR ((FECHA_INICIO >= ?\n"
            + "AND FECHA_INICIO <= ?) \n"
            + "OR (FECHA_FIN >= ?\n"
            + "AND FECHA_FIN <= ?)))\n"
            + "AND ID_ESTATUS_SUPLENCIA NOT IN(3, 4, 5)";

    private static final String SQL_BUSCAR_SUPLENCIA = "SELECT S.ID_SUPLENCIA, S.ID_FIRMANTE_A_SUPLIR, S.ID_FIRMANTE_SUPLENTE, "
            + "S.ID_MOTIVO_SUPLENCIA, S.FECHA_INICIO, S.FECHA_FIN, S.ID_ESTATUS_SUPLENCIA, "
            + "ES.DESCRIPCION AS ESTATUS_DESC, MS.DESCRIPCION AS MOTIVO_DESC FROM FECET_SUPLENCIA S "
            + "INNER JOIN FECEC_ESTATUS_SUPLENCIA ES ON S.ID_ESTATUS_SUPLENCIA = ES.ID_ESTATUS_SUPLENCIA "
            + "INNER JOIN FECEC_MOTIVO_SUPLENCIA MS ON S.ID_MOTIVO_SUPLENCIA = MS.ID_MOTIVO_SUPLENCIA "
            + "where S.ID_SUPLENCIA = ?";

    private static final String SUPLENTE_NO_DISPONIBLE = " SELECT ID_ESTATUS_SUPLENCIA FROM FECET_SUPLENCIA "
            + "WHERE ID_FIRMANTE_SUPLENTE = ? and ID_ESTATUS_SUPLENCIA IN (1,2,4) "
            + "AND (((FECHA_INICIO <= ? AND FECHA_FIN >= ?) OR (FECHA_INICIO <= ? AND FECHA_FIN >= ?)) OR ((FECHA_INICIO >=? AND FECHA_INICIO <= ?)"
            + "   OR (FECHA_FIN >= ?  AND FECHA_FIN <= ?)))";

    public void insertar(FecetSuplenciaDTO dto) {
        dto.setIdSuplencia(getJdbcTemplateBase().queryForObject("SELECT FECEQ_SUPLENCIA.NEXTVAL FROM  DUAL",
                BigDecimal.class));

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append("  (ID_SUPLENCIA, ID_FIRMANTE_A_SUPLIR, ID_FIRMANTE_SUPLENTE, ID_MOTIVO_SUPLENCIA, FECHA_INICIO, FECHA_FIN, ID_ESTATUS_SUPLENCIA)");
        query.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSuplencia(), dto.getIdFirmanteASuplir(),
                dto.getIdFrimanteSuplente(), dto.getIdMotivoSuplencia(), dto.getFechaInicio(),
                dto.getFechaFin(), dto.getIdStatus());

    }

    @Override
    public void cancelarSuplencia(String estadoCancelado, String idSuplencia) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_CANCELA_SUPLENCIA);
        getJdbcTemplateBase().update(query.toString(), estadoCancelado, idSuplencia);
    }

    @Override
    public List<FecetSuplenciaDTO> findListaSuplencia(StringBuffer arreglo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_BUSCAR_LISTA_SUPLENCIA);
        query.append("WHERE ID_FIRMANTE_SUPLENTE IN (").append(arreglo)
                .append(") ORDER BY S.ID_SUPLENCIA");
        logger.error(query.toString());
        logger.debug(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new AsignaSuplenciaAFirmanteMapper());
    }

    @Override
    public FecetSuplenciaDTO findSuplencia(BigDecimal idSuplencia) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_BUSCAR_SUPLENCIA);
        return getJdbcTemplateBase().queryForObject(query.toString(), new AsignaSuplenciaAFirmanteMapper(), idSuplencia);
    }

    public static String getTableName() {
        return "FECET_SUPLENCIA";
    }

    @Override
    public void cancelarSuplenciaScheduler() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_CANCELAR_SUPLENCIA);
        getJdbcTemplateBase().update(query.toString());
    }

    @Override
    public void activarSuplenciaScheduler() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_ACTIVA_SUPLENCIA);
        getJdbcTemplateBase().update(query.toString());
    }

    @Override
    public void terminarSuplenciaScheduler() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_TERMINA_SUPLENCIA);
        getJdbcTemplateBase().update(query.toString());
    }

    @Override
    public BigDecimal buscarSuplenciaRegistradaFirmanteSuplente(BigDecimal idEmpleado, Date fechaInicio, Date fechaFin) {
        StringBuilder query = new StringBuilder();
        query.append(BUSCAR_SUPLENCIA_SIMILAR_SUPLENTE);

        List<BigDecimal> res
                = getJdbcTemplateBase().queryForList(query.toString(),
                        BigDecimal.class,
                        idEmpleado,
                        fechaInicio,
                        fechaInicio,
                        fechaFin,
                        fechaFin,
                        fechaInicio,
                        fechaFin,
                        fechaInicio,
                        fechaFin);
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public BigDecimal buscarSuplenciaRegistradaFirmanteASuplir(AsignarSuplenciaAFirmanteModel asignarSuplenciaAFirmanteModel) {
        StringBuilder query = new StringBuilder();
        query.append(BUSCAR_SUPLENCIA_SIMILAR_A_SUPLIR);

        List<BigDecimal> res
                = getJdbcTemplateBase().queryForList(query.toString(), BigDecimal.class,
                        asignarSuplenciaAFirmanteModel.getFirmanteASuplir().getIdEmpleado(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaInicio(),
                        asignarSuplenciaAFirmanteModel.getNuevaSuplencia().getFechaFin());
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    public BigDecimal cargaFirmantesSuplentesDisponibles(BigDecimal idEmpleado, Date fechaInicio, Date fechaFin) {
        StringBuilder query = new StringBuilder();
        query.append(SUPLENTE_NO_DISPONIBLE);

        logger.error(query.toString());
        List<BigDecimal> res
                = getJdbcTemplateBase().queryForList(query.toString(),
                        BigDecimal.class,
                        idEmpleado,
                        fechaInicio,
                        fechaInicio,
                        fechaFin,
                        fechaFin,
                        fechaInicio,
                        fechaFin,
                        fechaInicio,
                        fechaFin);
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

}
