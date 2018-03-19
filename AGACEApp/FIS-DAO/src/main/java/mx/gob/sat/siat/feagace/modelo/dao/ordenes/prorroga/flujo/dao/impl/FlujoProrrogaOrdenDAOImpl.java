package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.flujo.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexosProrrogaOrdenDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOrdenConEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.flujo.dao.FlujoProrrogaOrdenDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("flujoProrrogaOrdenDAO")
public class FlujoProrrogaOrdenDAOImpl extends BaseJDBCDao<FecetFlujoProrrogaOrden> implements FlujoProrrogaOrdenDAO {

    private static final long serialVersionUID = 2371797865467865751L;

    private static final String SQL_SELECT = " SELECT ";
    private static final String SQL_FROM = " FROM ";

    private static final String SQL_ALL_COLUMNS
            = " ID_FLUJO_PRORROGA_ORDEN, ID_PRORROGA_ORDEN, FECHA_CREACION, JUSTIFICACION, JUSTIFICACION_FIRMANTE, APROBADA, ID_ESTATUS, FECHA_RECHAZO_FIRMANTE ";

    private static final String SQL_DUAL = "SELECT FECEQ_FLUJO_PRORROGA_ORDEN.NEXTVAL FROM DUAL";

    private static final StringBuilder SQL_WITH_ESTATUS
            = new StringBuilder("SELECT ").append(" FPR.ID_FLUJO_PRORROGA_ORDEN, FPR.ID_PRORROGA_ORDEN, FPR.FECHA_CREACION, FPR.JUSTIFICACION, ").append(" FPR.JUSTIFICACION_FIRMANTE, FPR.APROBADA, FPR.ID_ESTATUS, FPR.FECHA_RECHAZO_FIRMANTE, ").append(" ESTATUS.ID_ESTATUS,ESTATUS.DESCRIPCION,ESTATUS.MODULO, ").append(" (SELECT COUNT(FARO.ID_ANEXOS_PRORROGA_ORDEN) FROM ").append(FecetAnexosProrrogaOrdenDaoImpl.getTableName()).append(" FARO  ").append(" WHERE FARO.ID_FLUJO_PRORROGA_ORDEN=FPR.ID_FLUJO_PRORROGA_ORDEN ) TOTAL_ANEXOS  ").append(" FROM ").append(getTableName()).append(" FPR ").append(" INNER JOIN ").append(FececEstatusDaoImpl.getTableName()).append(" ESTATUS ").append(" ON FPR.ID_ESTATUS=ESTATUS.ID_ESTATUS ");

    public static String getTableName() {
        return "FECET_FLUJO_PRORROGA_ORDEN";
    }

    public BigDecimal getIdFecetFlujoProrrogaOrdenPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class);
    }

    public BigDecimal insert(FecetFlujoProrrogaOrden dto) {
        if (dto.getIdFlujoProrrogaOrden() == null) {
            dto.setIdFlujoProrrogaOrden(getIdFecetFlujoProrrogaOrdenPathDirectorio());
        }
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getTableName()).append(" ( ").append(SQL_ALL_COLUMNS).append(" ) ");
        sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ");
        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOrden(), dto.getIdProrrogaOrden(),
                dto.getFechaCreacion(), dto.getJustificacion(), dto.getJustificacionFirmante(),
                dto.getAprobada(), dto.getIdEstatus(), dto.getFechaRechazoFirmante());
        return dto.getIdFlujoProrrogaOrden();
    }

    public void update(FecetFlujoProrrogaOrden dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET  ID_PRORROGA_ORDEN = ?, FECHA_CREACION = ?, JUSTIFICACION = ?, JUSTIFICACION_FIRMANTE = ?, APROBADA = ?, ID_ESTATUS = ?, FECHA_RECHAZO_FIRMANTE = ? WHERE ID_FLUJO_PRORROGA_ORDEN = ? ");
        getJdbcTemplateBase().update(sql.toString(), dto.getIdProrrogaOrden(), dto.getFechaCreacion(),
                dto.getJustificacion(), dto.getJustificacionFirmante(), dto.getAprobada(),
                dto.getIdEstatus(), dto.getFechaRechazoFirmante(), dto.getIdFlujoProrrogaOrden());
    }

    public void updateFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOrden(final BigDecimal idProrrogaOrden) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ID_ESTATUS = ? WHERE ID_PRORROGA_ORDEN = ? AND ID_ESTATUS = ?");
        getJdbcTemplateBase().update(query.toString(),
                Constantes.ESTATUS_RESOLUCION_PRORROGA_CONCLUIDA_RECHAZADA_FIRMANTE,
                idProrrogaOrden, Constantes.ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE);

    }

    public void delete(final FecetFlujoProrrogaOrden dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE ").append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ? ");
        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOrden());
    }

    public FecetFlujoProrrogaOrden findByPrimaryKey(final BigDecimal idFlujoProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ?");
        List<FecetFlujoProrrogaOrden> list = getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idFlujoProrrogaOrden);
        return list.size() == 0 ? null : list.get(0);
    }

    public List<FecetFlujoProrrogaOrden> buscarFlujoPorIdProrrogaOrden(BigDecimal idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_WITH_ESTATUS).append(" WHERE FPR.ID_PRORROGA_ORDEN=? ORDER BY FPR.ID_FLUJO_PRORROGA_ORDEN DESC,FPR.FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenConEstatusMapper(),
                idProrrogaOrden);
    }

    public List<FecetFlujoProrrogaOrden> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" ORDER BY ID_FLUJO_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper());
    }

    public List<FecetFlujoProrrogaOrden> findWhereFechaCreacionEquals(final Date fechaCreacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), fechaCreacion);
    }

    public List<FecetFlujoProrrogaOrden> findWhereJustificacionEquals(final String justificacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION = ? ORDER BY JUSTIFICACION");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), justificacion);
    }

    public List<FecetFlujoProrrogaOrden> findWhereJustificacionFirmanteEquals(final String justificacionFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION_FIRMANTE = ? ORDER BY JUSTIFICACION_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), justificacionFirmante);
    }

    public List<FecetFlujoProrrogaOrden> findWhereAprobadaEquals(final String aprobada) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE APROBADA = ? ORDER BY APROBADA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), aprobada);
    }

    public List<FecetFlujoProrrogaOrden> findWhereIdEstatusEquals(final BigDecimal idEstatus) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_ESTATUS = ? ORDER BY ID_ESTATUS ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idEstatus);
    }

    public List<FecetFlujoProrrogaOrden> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), fechaRechazoFirmante);
    }

    public FecetFlujoProrrogaOrden findByPrimaryKey(final FecetFlujoProrrogaOrden dto) {
        return findByPrimaryKey(dto.getIdFlujoProrrogaOrden());
    }

    public Long findLastIdFecetFlujoProrrogaOrden(BigDecimal idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" max(id_flujo_prorroga_orden) id_flujo_prorroga_orden ");
        sql.append(SQL_FROM);
        sql.append(" fecet_flujo_prorroga_orden ");
        sql.append(" where id_prorroga_orden = ? ");
        return getJdbcTemplateBase().queryForObject(sql.toString(), Long.class, idProrrogaOrden);
    }

}
