package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOrdenPk;

import org.springframework.stereotype.Repository;

@Repository("fecetDocProrrogaOrdenDao")
public class FecetDocProrrogaOrdenDaoImpl extends BaseJDBCDao<FecetDocProrrogaOrden> implements FecetDocProrrogaOrdenDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_DOC_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_DOC_PRORROGA_ORDEN, ID_PRORROGA_ORDEN, RUTA_ARCHIVO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_DOC_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_DOC_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * este atributo corresponde a un complemento de funcion DELETE para
     * eliminar por criterio de IdOrden
     */
    private static final StringBuilder SQL_DELETE = new StringBuilder("DELETE FROM ");

    @SuppressWarnings("compatibility:7189099924535831078")
    private static final long serialVersionUID = 1L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocProrrogaOrdenPk
     */
    public FecetDocProrrogaOrdenPk insert(FecetDocProrrogaOrden dto) {

        if (dto.getIdDocProrrogaOrden() == null) {
            dto.setIdDocProrrogaOrden(getIdFecetDocProrrogaOrdenPathDirectorio());
        }

        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_DOC_PRORROGA_ORDEN, ID_PRORROGA_ORDEN, RUTA_ARCHIVO ) VALUES (?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocProrrogaOrden(), dto.getIdProrrogaOrden(),
                dto.getRutaArchivo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_DOC_PRORROGA_ORDEN table.
     */
    public void update(FecetDocProrrogaOrdenPk pk, FecetDocProrrogaOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_DOC_PRORROGA_ORDEN = ?, ").append(
                "ID_PRORROGA_ORDEN = ?, RUTA_ARCHIVO = ? WHERE ID_DOC_PRORROGA_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocProrrogaOrden(), dto.getIdProrrogaOrden(),
                dto.getRutaArchivo(), pk.getIdDocProrrogaOrden());

    }

    /**
     * Deletes a single row in the FECET_DOC_PRORROGA_ORDEN table.
     */
    public void delete(FecetDocProrrogaOrdenPk pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocProrrogaOrden());

    }

    /**
     * Metodo getIdFecetDocProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetDocProrrogaOrdenPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_PRORROGA_ORDEN";
    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_ORDEN table that match the
     * criteria 'ID_DOC_PRORROGA_ORDEN = :idProrroga'.
     */
    public FecetDocProrrogaOrden findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ?");
        List<FecetDocProrrogaOrden> list = getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrroga);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    public List<FecetDocProrrogaOrden> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_ORDEN table that match the
     * criteria 'ID_DOC_PRORROGA_ORDEN = :idProrroga'.
     */
    public List<FecetDocProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrroga);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_ORDEN table that match the
     * criteria 'ID_DOC_PRORROGA_ORDEN = :idProrroga'.
     */
    public List<FecetDocProrrogaOrden> findWhereIdDocProrrogaOrdenEquals(final BigDecimal idDocProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_ORDEN = ? ORDER BY ID_DOC_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idDocProrrogaOrden);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrrogaOrden'.
     */
    public List<FecetDocProrrogaOrden> findWhereIdProrrogaOrdenEquals(final BigDecimal idProrrogaOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_PRORROGA_ORDEN");
        logger.trace("Obtener documentacion contribuyente Prorroga");
        logger.trace(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOrdenMapper(), idProrrogaOrden);

    }

    /**
     * Returns the rows from the FECET_DOC_PRORROGA_ORDEN table that matches the
     * specified primary-key value.
     */
    public FecetDocProrrogaOrden findByPrimaryKey(final FecetDocProrrogaOrdenPk pk) {
        return findByPrimaryKey(pk.getIdDocProrrogaOrden());
    }

}
