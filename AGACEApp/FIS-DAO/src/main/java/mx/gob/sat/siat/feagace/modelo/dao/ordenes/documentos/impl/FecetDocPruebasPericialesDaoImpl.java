package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocPruebasPericialesMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericialesPk;

@Repository("fecetDocPruebasPericialesDao")
public class FecetDocPruebasPericialesDaoImpl extends BaseJDBCDao<FecetDocPruebasPericiales> implements FecetDocPruebasPericialesDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_DOC_PRUEBAS_PERICIALES
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_DOC_PRUEBAS_PERICIALES, ID_PRUEBAS_PERICIALES, RUTA_ARCHIVO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     *
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_DOC_PRUEBAS_PERICIALES.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_DOC_PRUEBAS_PERICIALES
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

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocPruebasPericialesPk
     */
    public FecetDocPruebasPericialesPk insert(FecetDocPruebasPericiales dto) {

        if (dto.getIdDocPruebasPericiales() == null) {
            dto.setIdDocPruebasPericiales(getIdFecetDocPruebasPericialesPathDirectorio());
        }

        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_DOC_PRUEBAS_PERICIALES, ID_PRUEBAS_PERICIALES, RUTA_ARCHIVO ) VALUES (?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocPruebasPericiales(), dto.getIdPruebasPericiales(),
                dto.getRutaArchivo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_DOC_PRUEBAS_PERICIALES table.
     */
    public void update(FecetDocPruebasPericialesPk pk, FecetDocPruebasPericiales dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_DOC_PRUEBAS_PERICIALES = ?, ").append(
                "ID_PRUEBAS_PERICIALES = ?, RUTA_ARCHIVO = ? WHERE ID_DOC_PRUEBAS_PERICIALES = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocPruebasPericiales(), dto.getIdPruebasPericiales(),
                dto.getRutaArchivo(), pk.getIdDocPruebasPericiales());

    }

    /**
     * Deletes a single row in the FECET_DOC_PRUEBAS_PERICIALES table.
     */
    public void delete(FecetDocPruebasPericialesPk pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_DOC_PRUEBAS_PERICIALES = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocPruebasPericiales());

    }

    /**
     * Metodo getIdFecetDocPruebasPericialesPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetDocPruebasPericialesPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_PRUEBAS_PERICIALES";
    }

    /**
     * Returns all rows from the FECET_DOC_PRUEBAS_PERICIALES table that match
     * the criteria 'ID_DOC_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    public FecetDocPruebasPericiales findByPrimaryKey(BigDecimal idPruebasPericiales) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRUEBAS_PERICIALES = ?");
        List<FecetDocPruebasPericiales> list = getJdbcTemplateBase().query(query.toString(), new FecetDocPruebasPericialesMapper(), idPruebasPericiales);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_PRUEBAS_PERICIALES table that match
     * the criteria ''.
     */
    public List<FecetDocPruebasPericiales> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_DOC_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocPruebasPericialesMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_PRUEBAS_PERICIALES table that match
     * the criteria 'ID_DOC_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    public List<FecetDocPruebasPericiales> findWhereIdPruebasPericialesEquals(BigDecimal idPruebasPericiales) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRUEBAS_PERICIALES = ? ORDER BY ID_DOC_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocPruebasPericialesMapper(), idPruebasPericiales);

    }

    /**
     * Returns all rows from the FECET_DOC_PRUEBAS_PERICIALES table that match
     * the criteria 'ID_DOC_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    public List<FecetDocPruebasPericiales> findWhereIdDocPruebasPericialesEquals(final BigDecimal idDocPruebasPericiales) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRUEBAS_PERICIALES = ? ORDER BY ID_DOC_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocPruebasPericialesMapper(), idDocPruebasPericiales);

    }

    /**
     * Returns the rows from the FECET_DOC_PRUEBAS_PERICIALES table that matches
     * the specified primary-key value.
     */
    public FecetDocPruebasPericiales findByPrimaryKey(final FecetDocPruebasPericialesPk pk) {
        return findByPrimaryKey(pk.getIdDocPruebasPericiales());
    }

}
