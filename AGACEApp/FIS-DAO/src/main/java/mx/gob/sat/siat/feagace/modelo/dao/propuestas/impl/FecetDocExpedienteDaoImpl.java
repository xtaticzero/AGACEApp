package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocExpedienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocExpedienteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpedientePk;

import org.springframework.stereotype.Repository;

@Repository("fecetDocExpedienteDao")
public class FecetDocExpedienteDaoImpl extends BaseJDBCDao<FecetDocExpediente> implements FecetDocExpedienteDao {

    @SuppressWarnings("compatibility:-3729043479882671423")
    private static final long serialVersionUID = -2970420769592505505L;

    private static final String SQL_SELECT
            = "SELECT ID_DOC_EXPEDIENTE, ID_PROPUESTA, RUTA_ARCHIVO, FECHA_CREACION FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocExpedientePk
     */
    @Override
    public FecetDocExpedientePk insert(FecetDocExpediente dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_DOC_EXPEDIENTE, ID_PROPUESTA, RUTA_ARCHIVO, FECHA_CREACION,BLN_ACTIVO ) VALUES ( FECEQ_DOC_EXPEDIENTE.NEXTVAL, ?, ?, ?, ?)");
        getJdbcTemplateBase().update(query.toString(), dto.getIdPropuesta(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getBlnActivo());

        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_DOC_EXPEDIENTE table.
     *
     * @param pk
     * @param dto
     */
    @Override
    public void update(FecetDocExpedientePk pk, FecetDocExpediente dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_DOC_EXPEDIENTE = ?, ID_PROPUESTA = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ? WHERE ID_DOC_EXPEDIENTE = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocExpediente(), dto.getIdPropuesta(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), pk.getIdDocExpediente());

    }

    /**
     * Deletes a single row in the FECET_DOC_EXPEDIENTE table.
     *
     * @param pk
     */
    @Override
    public void delete(FecetDocExpedientePk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_DOC_EXPEDIENTE = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocExpediente());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_EXPEDIENTE";
    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_DOC_EXPEDIENTE = :idDocExpediente'.
     *
     * @param idDocExpediente
     * @return
     */
    @Override
    public FecetDocExpediente findByPrimaryKey(BigDecimal idDocExpediente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_DOC_EXPEDIENTE = ?");
        List<FecetDocExpediente> list
                = getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), idDocExpediente);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria ''.
     *
     * @return
     */
    @Override
    public List<FecetDocExpediente> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_DOC_EXPEDIENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_DOC_EXPEDIENTE = :idDocExpediente'.
     *
     * @param idDocExpediente
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereIdDocExpedienteEquals(BigDecimal idDocExpediente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_DOC_EXPEDIENTE = ? ORDER BY ID_DOC_EXPEDIENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), idDocExpediente);

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), idPropuesta);

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'NOMBRE = :nombre'.
     *
     * @param nombre
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), nombre);

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     *
     * @param rutaArchivo
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     *
     * @param fechaCreacion
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), fechaCreacion);

    }

    /**
     * Returns the rows from the FECET_DOC_EXPEDIENTE table that matches the
     * specified primary-key value.
     *
     * @param pk
     * @return
     */
    @Override
    public FecetDocExpediente findByPrimaryKey(FecetDocExpedientePk pk) {
        return findByPrimaryKey(pk.getIdDocExpediente());
    }

    /**
     * Returns all rows from the FECET_DOC_EXPEDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    @Override
    public List<FecetDocExpediente> findWhereIdPropuestaEqualsOrderByFecha(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpedienteMapper(), idPropuesta);

    }
}
