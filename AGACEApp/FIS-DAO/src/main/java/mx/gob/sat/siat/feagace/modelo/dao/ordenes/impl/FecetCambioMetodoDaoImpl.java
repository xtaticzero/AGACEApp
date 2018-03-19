package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCambioMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCambioMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCambioMetodoPk;

import org.springframework.stereotype.Repository;

@Repository("fecetCambioMetodoDao")
public class FecetCambioMetodoDaoImpl extends BaseJDBCDao<FecetCambioMetodo> implements FecetCambioMetodoDao {

    @SuppressWarnings("compatibility:7699252872362040707")
    private static final long serialVersionUID = 1L;

    private static final StringBuilder SQL_SELECT_COLUMNS
            = new StringBuilder(" ID_CAMBIO_METODO, ID_ORDEN_ORIGEN, ID_ORDEN_NUEVA, ID_METODO_NUEVO, ID_OFICIO, ID_ESTATUS, FECHA_CREACION, ID_PROPUESTA_NUEVA  ");

    private static final StringBuilder SQL_SELECT_COLUMNS_JOIN
            = new StringBuilder(" ORIGEN.ID_CAMBIO_METODO, ORIGEN.ID_ORDEN_ORIGEN, ORIGEN.ID_ORDEN_NUEVA, ORIGEN.ID_METODO_NUEVO, ORIGEN.ID_OFICIO, ORIGEN.ID_ESTATUS, ORIGEN.FECHA_CREACION, ORIGEN.ID_PROPUESTA_NUEVA, NUEVO.NOMBRE NOMBRE_METODO_NUEVO ");

    private static final StringBuilder SQL_FROM_TABLE_JOIN
            = new StringBuilder(" FROM FECET_CAMBIO_METODO ORIGEN INNER JOIN FECEC_METODO NUEVO ON ORIGEN.ID_METODO_NUEVO = NUEVO.ID_METODO ");

    private static final StringBuilder SQL_COLUMN_ESTATUS_UPDATE
            = new StringBuilder(" ID_ESTATUS = ?  ");

    private static final StringBuilder SQL_FROM_TABLE = new StringBuilder(" FROM ").append(getTableName());

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetCambioMetodoPk
     */
    public FecetCambioMetodoPk insert(FecetCambioMetodo dto) {

        if (dto.getIdCambioMetodo() == null) {
            dto.setIdCambioMetodo(getJdbcTemplateBase().queryForObject("SELECT FECEQ_CAMBIO_METODO.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append(" INSERT INTO ").append(getTableName());
        sqlInsert.append("(").append(SQL_SELECT_COLUMNS).append(")");
        sqlInsert.append(" VALUES ").append("( ?,?,?,?,?,?,?, ? )");
        getJdbcTemplateBase().update(sqlInsert.toString(), dto.getIdCambioMetodo(), dto.getIdOrdenOrigen(),
                dto.getIdOrdenNueva(), dto.getIdMetodoNuevo(), dto.getIdOficio(),
                dto.getIdEstatus(), dto.getFechaCreacion(), dto.getIdPropuestaNueva());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_CAMBIO_METODO table.
     */
    public void update(FecetCambioMetodo dto) {
        StringBuilder sqlUpdate = new StringBuilder();
        sqlUpdate.append("UPDATE ").append(getTableName());
        sqlUpdate.append(" SET ").append(SQL_COLUMN_ESTATUS_UPDATE).append(" WHERE ID_ORDEN_ORIGEN = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), dto.getIdEstatus(), dto.getIdOrdenOrigen());

    }

    /**
     * Deletes a single row in the FECET_CAMBIO_METODO table.
     */
    public void delete(FecetCambioMetodoPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_CAMBIO_METODO = ?",
                pk.getIdCambioMetodo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_CAMBIO_METODO";
    }

    /**
     * Returns all rows from the FECET_CAMBIO_METODO table that match the
     * criteria 'ID_CAMBIO_METODO = :idCambioMetodo'.
     */
    public FecetCambioMetodo findByPrimaryKey(BigDecimal idCambioMetodo) {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT_COLUMNS);
        sqlSelect.append(SQL_FROM_TABLE).append("  WHERE ID_CAMBIO_METODO = ? ");
        List<FecetCambioMetodo> list
                = getJdbcTemplateBase().query(sqlSelect.toString(), new FecetCambioMetodoMapper(), idCambioMetodo);
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_CAMBIO_METODO table that match the
     * criteria ''.
     */
    public List<FecetCambioMetodo> findAll() {
        StringBuilder sqlSelect = new StringBuilder();
        sqlSelect.append(SQL_SELECT_COLUMNS);
        sqlSelect.append(SQL_FROM_TABLE).append(" ORDER BY ID_CAMBIO_METODO ");
        return getJdbcTemplateBase().query(sqlSelect.toString(), new FecetCambioMetodoMapper());

    }

    @Override
    public FecetCambioMetodo getDatosGenerales(BigDecimal idOrden) {
        FecetCambioMetodo cambioMetodo = null;
        StringBuilder sqlInner = new StringBuilder();
        sqlInner.append("SELECT ");
        sqlInner.append(SQL_SELECT_COLUMNS_JOIN);
        sqlInner.append(SQL_FROM_TABLE_JOIN);
        sqlInner.append(" WHERE ORIGEN.ID_ORDEN_ORIGEN = ? ");
        try {
            cambioMetodo
                    = getJdbcTemplateBase().queryForObject(sqlInner.toString(), new FecetCambioMetodoMapper(), idOrden);
        } catch (Exception e) {
            logger.error("Erros getDatosGenerales " + e.getMessage(), e);
        }
        return cambioMetodo;

    }
}
