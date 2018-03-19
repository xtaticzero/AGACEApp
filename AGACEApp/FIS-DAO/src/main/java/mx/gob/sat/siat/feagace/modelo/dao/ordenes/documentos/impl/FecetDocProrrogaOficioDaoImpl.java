package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocProrrogaOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;

import org.springframework.stereotype.Repository;

@Repository("fecetDocProrrogaOficioDao")
public class FecetDocProrrogaOficioDaoImpl extends BaseJDBCDao<FecetDocProrrogaOficio> implements FecetDocProrrogaOficioDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_DOC_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_DOC_PRORROGA_OFICIO, ID_PRORROGA_OFICIO, RUTA_ARCHIVO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_DOC_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_DOC_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocProrrogaOficioPk
     */
    public BigDecimal insert(FecetDocProrrogaOficio dto) {

        if (dto.getIdDocProrrogaOficio() == null) {
            dto.setIdDocProrrogaOficio(getIdFecetDocProrrogaOficioPathDirectorio());
        }

        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_DOC_PRORROGA_OFICIO, ID_PRORROGA_OFICIO, RUTA_ARCHIVO ) VALUES (?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocProrrogaOficio(), dto.getIdProrrogaOficio(),
                dto.getRutaArchivo());
        return dto.getIdDocProrrogaOficio();

    }

    /**
     * Updates a single row in the FECET_DOC_PRORROGA_OFICIO table.
     */
    public void update(FecetDocProrrogaOficio dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_DOC_PRORROGA_OFICIO = ?, ").append(
                "ID_PRORROGA_OFICIO = ?, RUTA_ARCHIVO = ? WHERE ID_DOC_PRORROGA_OFICIO = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocProrrogaOficio(), dto.getIdProrrogaOficio(),
                dto.getRutaArchivo(), dto.getIdDocProrrogaOficio());

    }

    /**
     * Metodo getIdFecetDocProrrogaOficioPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetDocProrrogaOficioPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_PRORROGA_OFICIO";
    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'ID_DOC_PRORROGA_OFICIO = :idProrroga'.
     */
    public FecetDocProrrogaOficio findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_OFICIO = ?");
        List<FecetDocProrrogaOficio> list = getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), idProrroga);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria ''.
     */
    public List<FecetDocProrrogaOficio> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_DOC_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'ID_DOC_PRORROGA_OFICIO = :idProrroga'.
     */
    public List<FecetDocProrrogaOficio> findWhereIdProrrogaEquals(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_OFICIO = ? ORDER BY ID_DOC_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), idProrroga);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'ID_DOC_PRORROGA_OFICIO = :idProrroga'.
     */
    public List<FecetDocProrrogaOficio> findWhereIdDocProrrogaOficioEquals(final BigDecimal idDocProrrogaOficio) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_DOC_PRORROGA_OFICIO = ? ORDER BY ID_DOC_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), idDocProrrogaOficio);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'ID_PRORROGA_OFICIO = :idProrrogaOficio'.
     */
    public List<FecetDocProrrogaOficio> findWhereIdProrrogaOficioEquals(final BigDecimal idProrrogaOficio) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_OFICIO = ? ORDER BY ID_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), idProrrogaOficio);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'NOMBRE_ARCHIVO = :nombreArchivo'.
     */
    public List<FecetDocProrrogaOficio> findWhereNombreAcuseEquals(final String nombreArchivo) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE NOMBRE_ARCHIVO = ? ORDER BY NOMBRE_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), nombreArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_PRORROGA_OFICIO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocProrrogaOficio> findWhereRutaAcuseEquals(final String rutaArchivo) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocProrrogaOficioMapper(), rutaArchivo);

    }

    /**
     * Returns the rows from the FECET_DOC_PRORROGA_OFICIO table that matches
     * the specified primary-key value.
     */
    public FecetDocProrrogaOficio findByPrimaryKey(final FecetDocProrrogaOficio dto) {
        return findByPrimaryKey(dto.getIdDocProrrogaOficio());
    }

}
