package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexosProrrogaOrdenDaoImpl;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOrdenConEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

/**
 *
 * @deprecated utilizar FlujoProrrogaOrdenDAO
 *
 */
@Deprecated
@Repository("fecetFlujoProrrogaOrdenDao")
public class FecetFlujoProrrogaOrdenDaoImpl extends BaseJDBCDao<FecetFlujoProrrogaOrden> implements FecetFlujoProrrogaOrdenDao {

    private static final long serialVersionUID = 2371797865467865751L;

    private static final String SQL_SELECT = " SELECT ";
    private static final String SQL_FROM = " FROM ";

    private static final String SQL_ALL_COLUMNS
            = " ID_FLUJO_PRORROGA_ORDEN, ID_PRORROGA_ORDEN, FECHA_CREACION, JUSTIFICACION, JUSTIFICACION_FIRMANTE, APROBADA, ID_ESTATUS, FECHA_RECHAZO_FIRMANTE ";

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_FLUJO_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    private static final StringBuilder SQL_WITH_ESTATUS
            = new StringBuilder("SELECT ").append(" FPR.ID_FLUJO_PRORROGA_ORDEN, FPR.ID_PRORROGA_ORDEN, FPR.FECHA_CREACION, FPR.JUSTIFICACION, ").append(" FPR.JUSTIFICACION_FIRMANTE, FPR.APROBADA, FPR.ID_ESTATUS, FPR.FECHA_RECHAZO_FIRMANTE, ").append(" ESTATUS.ID_ESTATUS,ESTATUS.DESCRIPCION,ESTATUS.MODULO, ").append(" (SELECT COUNT(FARO.ID_ANEXOS_PRORROGA_ORDEN) FROM ").append(FecetAnexosProrrogaOrdenDaoImpl.getTableName()).append(" FARO  ").append(" WHERE FARO.ID_FLUJO_PRORROGA_ORDEN=FPR.ID_FLUJO_PRORROGA_ORDEN ) TOTAL_ANEXOS  ").append(" FROM ").append(getTableName()).append(" FPR ").append(" INNER JOIN ").append(FececEstatusDaoImpl.getTableName()).append(" ESTATUS ").append(" ON FPR.ID_ESTATUS=ESTATUS.ID_ESTATUS ");

    /**
     * Metodo getIdFecetFecetFlujoProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetFlujoProrrogaOrdenPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return BigDecimal
     */
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

    /**
     * Updates a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    public void update(FecetFlujoProrrogaOrden dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET  ID_PRORROGA_ORDEN = ?, FECHA_CREACION = ?, JUSTIFICACION = ?, JUSTIFICACION_FIRMANTE = ?, APROBADA = ?, ID_ESTATUS = ?, FECHA_RECHAZO_FIRMANTE = ? WHERE ID_FLUJO_PRORROGA_ORDEN = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdProrrogaOrden(), dto.getFechaCreacion(),
                dto.getJustificacion(), dto.getJustificacionFirmante(), dto.getAprobada(),
                dto.getIdEstatus(), dto.getFechaRechazoFirmante(), dto.getIdFlujoProrrogaOrden());
    }

    /**
     * Deletes a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    public void delete(final FecetFlujoProrrogaOrden dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE ").append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOrden());
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_FLUJO_PRORROGA_ORDEN";
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    public FecetFlujoProrrogaOrden findByPrimaryKey(final BigDecimal idFlujoProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ?");

        List<FecetFlujoProrrogaOrden> list
                = getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idFlujoProrrogaOrden);

        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    public List<FecetFlujoProrrogaOrden> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" ORDER BY ID_FLUJO_PRORROGA_ORDEN");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper());
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereIdFlujoProrrogaOrdenEquals(final BigDecimal idFlujoProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ? ORDER BY ID_FLUJO_PRORROGA_ORDEN");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idFlujoProrrogaOrden);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrrogaOrden'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereIdProrrogaOrdenEquals(final Date idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_PRORROGA_ORDEN ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idProrrogaOrden);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereFechaCreacionEquals(final Date fechaCreacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), fechaCreacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION = :justificacion'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereJustificacionEquals(final String justificacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION = ? ORDER BY JUSTIFICACION");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), justificacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION_FIRMANTE = :justificacionFirmante'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereJustificacionFirmanteEquals(final String justificacionFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION_FIRMANTE = ? ORDER BY JUSTIFICACION_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), justificacionFirmante);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereAprobadaEquals(final String aprobada) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE APROBADA = ? ORDER BY APROBADA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), aprobada);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_ESTATUS = :idEstatus'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereIdEstatusEquals(final BigDecimal idEstatus) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_ESTATUS = ? ORDER BY ID_ESTATUS ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), idEstatus);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    public List<FecetFlujoProrrogaOrden> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenMapper(), fechaRechazoFirmante);
    }

    /**
     * Returns the rows from the FECET_FLUJO_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    public FecetFlujoProrrogaOrden findByPrimaryKey(final FecetFlujoProrrogaOrden dto) {
        return findByPrimaryKey(dto.getIdFlujoProrrogaOrden());
    }

    /**
     * @param idProrrogaOrden
     * @return ultimo id_flujo_prorroga_orden
     */
    @Override
    public String findLastIdFecetFlujoProrrogaOrden(BigDecimal idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" max(id_flujo_prorroga_orden) id_flujo_prorroga_orden ");
        sql.append(SQL_FROM);
        sql.append(" fecet_flujo_prorroga_orden ");
        sql.append(" where id_prorroga_orden = ? ");
        return getJdbcTemplateBase().queryForObject(sql.toString(), String.class, idProrrogaOrden);
    }

    /**
     *
     * @param idProrrogaOrden
     * @return
     */
    @Override
    public List<FecetFlujoProrrogaOrden> buscarFlujoPorIdProrrogaOrden(BigDecimal idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_WITH_ESTATUS).append(" WHERE FPR.ID_PRORROGA_ORDEN=? ORDER BY FPR.ID_FLUJO_PRORROGA_ORDEN DESC,FPR.FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOrdenConEstatusMapper(),
                idProrrogaOrden);

    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    public void actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOrden(final BigDecimal idProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ID_ESTATUS = ? WHERE ID_PRORROGA_ORDEN = ? AND ID_ESTATUS = ?");

        getJdbcTemplateBase().update(query.toString(),
                Constantes.ESTATUS_RESOLUCION_PRORROGA_CONCLUIDA_RECHAZADA_FIRMANTE,
                idProrrogaOrden, Constantes.ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE);

    }

}
