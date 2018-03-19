/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import static mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestaPendienteSQL.UPDATE_ESTADO_X_ID_PROPUESTA;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPropPendienteDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPropPendienteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestaPendienteSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendiente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropPendientePk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;

@Repository("fecetPropPendienteDao")
public class FecetPropPendienteDaoImpl extends BaseJDBCDao<FecetPropPendiente> implements FecetPropPendienteDao {

    private static final long serialVersionUID = 3468702295887353923L;

    private static final String SQL_SELECT = "SELECT FECET_PROP_PENDIENTE.ID_PROP_PENDIENTE, FECET_PROP_PENDIENTE.ID_PROPUESTA, FECET_PROP_PENDIENTE.FECHA_CREACION,FECET_DOC_PROP_PENDIENTE.ID_DOC_PENDIENTE ,FECET_DOC_PROP_PENDIENTE.RUTA_ARCHIVO,FECET_PROP_PENDIENTE.OBSERVACIONES,FECET_PROP_PENDIENTE.RFC_CREACION,FECET_PROP_PENDIENTE.ESTATUS "
            + "FROM " + getTableName() + " INNER JOIN " + getTableNameDoc()
            + " on (FECET_DOC_PROP_PENDIENTE.ID_PROP_PENDIENTE=FECET_PROP_PENDIENTE.ID_PROP_PENDIENTE)";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPropPendientePk
     */
    @Override
    public FecetPropPendientePk insert(FecetPropPendiente dto) {
        dto.setIdPropPendiente(getConsecutivoPendiente());
        String query = PropuestaPendienteSQL.INSERT_X_PARAMETROS
                .replace(PropuestaPendienteSQL.ID_PROP_PENDIENTE, dto.getIdPropPendiente().toString())
                .replace(PropuestaPendienteSQL.ID_PROPUESTA, dto.getIdPropuesta().toString())
                .replace(PropuestaPendienteSQL.RFC_CREACION, dto.getRfcCreacion())
                .replace(PropuestaPendienteSQL.OBSERVACIONES, dto.getObservaciones())
                .replace(PropuestaPendienteSQL.ESTATUS, dto.getEstatus().toString());

        getJdbcTemplateBase().update(query);
        return dto.createPk();

    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPropPendientePk
     */
    @Override
    public FecetPropPendientePk insertDoc(FecetPropPendiente dto) {
        dto.setIdDocPendiente(getConsecutivoDocPendiente());
        String query2 = PropuestaPendienteSQL.INSERT_X_PARAMETROS_DOC
                .replace(PropuestaPendienteSQL.ID_DOC_PENDIENTE, dto.getIdDocPendiente().toString())
                .replace(PropuestaPendienteSQL.ID_PROP_PENDIENTE, dto.getIdPropPendiente().toString())
                .replace(PropuestaPendienteSQL.RUTA_ARCHIVO, dto.getRutaArchivo())
                .replace(PropuestaPendienteSQL.BLN_ARCHIVO, dto.getEstatus().toString())
                .replace(PropuestaPendienteSQL.FECHA_CREACION, dto.getFechaCreacion().toString());

        getJdbcTemplateBase().update(query2);
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_PROP_PENDIENTE table.
     */
    @Override
    public void update(FecetPropPendientePk pk, FecetPropPendiente dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(
                " SET ID_PROP_PENDIENTE = ?, ID_PROPUESTA = ?, FECHA_CREACION = ?, RFC_CREACION = ?, OBSERVACIONES = ?, ESTATUS = ? WHERE ID_PROP_PENDIENTE = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdPropPendiente(), dto.getIdPropuesta(),
                dto.getFechaCreacion(), dto.getRfcCreacion(), dto.getObservaciones(), dto.getEstatus(),
                pk.getIdPropPendiente());

    }

    /**
     * Deletes a single row in the FECET_PROP_PENDIENTE table.
     */
    @Override
    public void delete(FecetPropPendientePk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_PROP_PENDIENTE = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdPropPendiente());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PROP_PENDIENTE";
    }

    public static String getTableNameDoc() {
        return "FECET_DOC_PROP_PENDIENTE";
    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROP_PENDIENTE = :idPropPendiente'.
     */
    @Override
    public FecetPropPendiente findByPrimaryKey(BigDecimal idPropPendiente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROP_PENDIENTE = ?");

        List<FecetPropPendiente> list = getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(),
                idPropPendiente);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria ''.
     */
    @Override
    public List<FecetPropPendiente> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_PROP_PENDIENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper());

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROP_PENDIENTE = :idPropPendiente'.
     */
    @Override
    public List<FecetPropPendiente> findWhereIdPropPendienteEquals(BigDecimal idPropPendiente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROP_PENDIENTE = ? ORDER BY ID_PROP_PENDIENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), idPropPendiente);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    @Override
    public List<FecetPropPendiente> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), idPropuesta);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     *
     * @param idPropuesta
     * @return
     */
    @Override
    public FecetPropPendiente obtienePendienteIdPropuestaEquals(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE " + getTableName() + ".ID_PROPUESTA = ? ORDER BY " + getTableName() + ".ID_PROPUESTA");
        List<FecetPropPendiente> list = getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(),
                idPropuesta);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     *
     * @param fechaCreacion
     * @return
     */
    @Override
    public List<FecetPropPendiente> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), fechaCreacion);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'RFC_CREACION = :rfcCreacion'.
     *
     * @param rfcCreacion
     * @return
     */
    @Override
    public List<FecetPropPendiente> findWhereRfcCreacionEquals(String rfcCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_CREACION = ? ORDER BY RFC_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), rfcCreacion);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'OBSERVACIONES = :observaciones'.
     *
     * @param observaciones
     * @return
     */
    @Override
    public List<FecetPropPendiente> findWhereObservacionesEquals(String observaciones) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE OBSERVACIONES = ? ORDER BY OBSERVACIONES");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), observaciones);

    }

    /**
     * Returns all rows from the FECET_PROP_PENDIENTE table that match the
     * criteria 'ESTATUS = :estatus'.
     *
     * @param estatus
     * @return
     */
    @Override
    public List<FecetPropPendiente> findWhereEstatusEquals(String estatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FecetPropPendienteMapper(), estatus);

    }

    /**
     * Returns the rows from the FECET_PROP_PENDIENTE table that matches the
     * specified primary-key value.
     *
     * @param pk
     */
    @Override
    public FecetPropPendiente findByPrimaryKey(FecetPropPendientePk pk) {
        return findByPrimaryKey(pk.getIdPropPendiente());
    }

    @Override
    public int updateEstadoByIdPropuesta(BigDecimal idPropuesta, EstadoBooleanodeRegistroEnum estadoRegistro) {
        String query = UPDATE_ESTADO_X_ID_PROPUESTA.replace(PropuestaPendienteSQL.ID_PROPUESTA, idPropuesta.toString())
                .replace(PropuestaPendienteSQL.ESTATUS, estadoRegistro.getEstado().toString());
        return getJdbcTemplateBase().update(query);
    }

    @Override
    public BigDecimal getConsecutivoPendiente() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_PROP_PENDIENTE.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public BigDecimal getConsecutivoDocPendiente() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_PROP_PENDIENTE.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }
}
