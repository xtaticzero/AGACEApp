package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexosProrrogaOficioDaoImpl;
import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOficioConEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoProrrogaOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetFlujoProrrogaOficioDao")
public class FecetFlujoProrrogaOficioDaoImpl extends BaseJDBCDao<FecetFlujoProrrogaOficio> implements FecetFlujoProrrogaOficioDao {

    @SuppressWarnings("compatibility:-4716994645720973294")
    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = " SELECT ";
    private static final String SQL_FROM = " FROM ";

    private static final String SQL_ALL_COLUMNS
            = " ID_FLUJO_PRORROGA_OFICIO, ID_PRORROGA_OFICIO, FECHA_CREACION, JUSTIFICACION, JUSTIFICACION_FIRMANTE, APROBADA, ID_ESTATUS, FECHA_RECHAZO_FIRMANTE ";

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_FLUJO_PRORROGA_OFICIO.NEXTVAL FROM DUAL");

    private static final StringBuilder SQL_WITH_ESTATUS
            = new StringBuilder("SELECT ").append(" FPR.ID_FLUJO_PRORROGA_OFICIO, FPR.ID_PRORROGA_OFICIO, FPR.FECHA_CREACION, FPR.JUSTIFICACION, ").append(" FPR.JUSTIFICACION_FIRMANTE, FPR.APROBADA, FPR.ID_ESTATUS, FPR.FECHA_RECHAZO_FIRMANTE, ").append(" ESTATUS.ID_ESTATUS,ESTATUS.DESCRIPCION,ESTATUS.MODULO, ").append(" (SELECT COUNT(FARO.ID_ANEXOS_PRORROGA_OFICIO) FROM ").append(FecetAnexosProrrogaOficioDaoImpl.getTableName()).append(" FARO  ").append(" WHERE FARO.ID_FLUJO_PRORROGA_OFICIO=FPR.ID_FLUJO_PRORROGA_OFICIO ) TOTAL_ANEXOS  ").append(" FROM ").append(getTableName()).append(" FPR ").append(" INNER JOIN ").append(FececEstatusDaoImpl.getTableName()).append(" ESTATUS ").append(" ON FPR.ID_ESTATUS=ESTATUS.ID_ESTATUS ");

    /**
     * Metodo getIdFecetFecetFlujoProrrogaOficioPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetFlujoProrrogaOficioPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetFlujoProrrogaOficio
     */
    public FecetFlujoProrrogaOficio insert(FecetFlujoProrrogaOficio dto) {

        if (dto.getIdFlujoProrrogaOficio() == null) {
            dto.setIdFlujoProrrogaOficio(getIdFecetFlujoProrrogaOficioPathDirectorio());
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getTableName()).append(" ( ").append(SQL_ALL_COLUMNS).append(" ) ");
        sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOficio(), dto.getIdProrrogaOficio(),
                dto.getFechaCreacion(), dto.getJustificacion(), dto.getJustificacionFirmante(),
                dto.getAprobada(), dto.getIdEstatus(), dto.getFechaRechazoFirmante());
        return dto;
    }

    /**
     * Updates a single row in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    public void update(FecetFlujoProrrogaOficio dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET ID_FLUJO_PRORROGA_OFICIO = ?, ID_PRORROGA_OFICIO = ?, FECHA_CREACION = ?, JUSTIFICACION = ?, JUSTIFICACION_FIRMANTE = ?, APROBADA = ?, ID_ESTATUS = ?, FECHA_RECHAZO_FIRMANTE = ? WHERE ID_FLUJO_PRORROGA_OFICIO = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOficio(), dto.getIdProrrogaOficio(),
                dto.getFechaCreacion(), dto.getJustificacion(), dto.getJustificacionFirmante(),
                dto.getAprobada(), dto.getIdEstatus(), dto.getFechaRechazoFirmante(),
                dto.getIdFlujoProrrogaOficio());
    }

    /**
     * Deletes a single row in the FECET_FLUJO_PRORROGA_OFICIO table.
     */
    public void delete(final FecetFlujoProrrogaOficio dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE ").append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_OFICIO = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoProrrogaOficio());
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_FLUJO_PRORROGA_OFICIO";
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    public FecetFlujoProrrogaOficio findByPrimaryKey(final BigDecimal idFlujoProrrogaOficio) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_OFICIO = ?");

        List<FecetFlujoProrrogaOficio> list
                = getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), idFlujoProrrogaOficio);

        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria ''.
     */
    public List<FecetFlujoProrrogaOficio> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" ORDER BY ID_FLUJO_PRORROGA_OFICIO");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper());
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_FLUJO_PRORROGA_OFICIO = :idFlujoProrrogaOficio'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereIdFlujoProrrogaOficioEquals(final BigDecimal idFlujoProrrogaOficio) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRORROGA_OFICIO = ? ORDER BY ID_FLUJO_PRORROGA_OFICIO");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(),
                idFlujoProrrogaOficio);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_PRORROGA_OFICIO = :idProrrogaOficio'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereIdProrrogaOficioEquals(final BigDecimal idProrrogaOficio) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_PRORROGA_OFICIO = ? ORDER BY ID_PRORROGA_OFICIO ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), idProrrogaOficio);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereFechaCreacionEquals(final Date fechaCreacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), fechaCreacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'JUSTIFICACION = :justificacion'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereJustificacionEquals(final String justificacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION = ? ORDER BY JUSTIFICACION");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), justificacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'JUSTIFICACION_FIRMANTE = :justificacionFirmante'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereJustificacionFirmanteEquals(final String justificacionFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION_FIRMANTE = ? ORDER BY JUSTIFICACION_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(),
                justificacionFirmante);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'APROBADA = :aprobada'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereAprobadaEquals(final String aprobada) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE APROBADA = ? ORDER BY APROBADA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), aprobada);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_ESTATUS = :idEstatus'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereIdEstatusEquals(final BigDecimal idEstatus) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_ESTATUS = ? ORDER BY ID_ESTATUS ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), idEstatus);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    public List<FecetFlujoProrrogaOficio> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioMapper(), fechaRechazoFirmante);

    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_OFICIO table that match
     * the criteria 'ID_PRORROGA_OFICIO = :idProrrogaOficio'.
     *
     * @param idProrrogaOficio
     * @return
     */
    public List<FecetFlujoProrrogaOficio> buscarFlujoPorIdProrrogaOficio(BigDecimal idProrrogaOficio) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_WITH_ESTATUS).append(" WHERE FPR.ID_PRORROGA_OFICIO=? ORDER BY FPR.ID_FLUJO_PRORROGA_OFICIO DESC,FPR.FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoProrrogaOficioConEstatusMapper(),
                idProrrogaOficio);
    }

    /**
     * Returns the rows from the FECET_FLUJO_PRORROGA_OFICIO table that matches
     * the specified primary-key value.
     */
    public FecetFlujoProrrogaOficio findByPrimaryKey(final FecetFlujoProrrogaOficio dto) {
        return findByPrimaryKey(dto.getIdFlujoProrrogaOficio());
    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    public void actualizarFlujoConcluidoRechazadoPorFirmanteByIdProrrogaOficio(final BigDecimal idProrrogaOficio) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ID_ESTATUS = ? WHERE ID_PRORROGA_OFICIO = ? AND ID_ESTATUS = ?");

        getJdbcTemplateBase().update(query.toString(),
                Constantes.ESTATUS_RESOLUCION_PRORROGA_CONCLUIDA_RECHAZADA_FIRMANTE,
                idProrrogaOficio, Constantes.ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE);

    }

    /**
     * @param idProrrogaOrden
     * @return ultimo id_flujo_prorroga_oficio
     */
    @Override
    public String findLastIdFecetFlujoProrrogaOficio(BigDecimal idProrrogaOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" max(id_flujo_prorroga_oficio) id_flujo_prorroga_oficio ");
        sql.append(SQL_FROM);
        sql.append(" fecet_flujo_prorroga_oficio ");
        sql.append(" where id_prorroga_oficio = ? ");
        logger.debug("SQL : {} ", sql.toString());
        logger.debug("PARAMS : {} ", idProrrogaOrden);
        return getJdbcTemplateBase().queryForObject(sql.toString(), String.class, idProrrogaOrden);
    }
}
