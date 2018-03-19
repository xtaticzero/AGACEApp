/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FecetDetalleEmpleadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetDetalleEmpleadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleadoPk;

@Repository("fecetDetalleEmpleadoDao")
public class FecetDetalleEmpleadoDaoImpl extends BaseJDBCDao<FecetDetalleEmpleado> implements FecetDetalleEmpleadoDao {

    private static final long serialVersionUID = 8752942943085202788L;

    private static final StringBuilder SQL_SELECT = new StringBuilder(
            "SELECT ID_DETALLE_EMPLEADO, ID_EMPLEADO, ID_TIPO_EMPLEADO, ID_CENTRAL, ID_ARACE FROM ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDetalleEmpleadoPk
     */
    public FecetDetalleEmpleadoPk insert(FecetDetalleEmpleado dto) {

        getJdbcTemplateBase().update(
                "INSERT INTO " + getTableName()
                + " ( ID_DETALLE_EMPLEADO, ID_EMPLEADO, ID_TIPO_EMPLEADO, ID_CENTRAL, ID_ARACE ) VALUES ( ?, ?, ?, ?, ? )",
                dto.getIdDetalleEmpleado(), dto.getIdEmpleado(), dto.getIdTipoEmpleado(), dto.getIdCentral(),
                dto.getIdArace());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_DETALLE_EMPLEADO table.
     */
    public void update(FecetDetalleEmpleadoPk pk, FecetDetalleEmpleado dto) {

        getJdbcTemplateBase().update(
                "UPDATE " + getTableName()
                + " SET ID_DETALLE_EMPLEADO = ?, ID_EMPLEADO = ?, ID_TIPO_EMPLEADO = ?, ID_CENTRAL = ?, ID_ARACE = ? WHERE ID_DETALLE_EMPLEADO = ?",
                dto.getIdDetalleEmpleado(), dto.getIdEmpleado(), dto.getIdTipoEmpleado(), dto.getIdCentral(),
                dto.getIdArace(), pk.getIdDetalleEmpleado());

    }

    /**
     * Deletes a single row in the FECET_DETALLE_EMPLEADO table.
     */
    public void delete(FecetDetalleEmpleadoPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_DETALLE_EMPLEADO = ?",
                pk.getIdDetalleEmpleado());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DETALLE_EMPLEADO";
    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_DETALLE_EMPLEADO = :idDetalleEmpleado'.
     */
    public FecetDetalleEmpleado findByPrimaryKey(BigDecimal idDetalleEmpleado) {

        List<FecetDetalleEmpleado> list = getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_DETALLE_EMPLEADO = ?").toString(),
                new FecetDetalleEmpleadoMapper(), idDetalleEmpleado);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria ''.
     */
    public List<FecetDetalleEmpleado> findAll() {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" ORDER BY ID_DETALLE_EMPLEADO").toString(),
                new FecetDetalleEmpleadoMapper());

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_DETALLE_EMPLEADO = :idDetalleEmpleado'.
     */
    public List<FecetDetalleEmpleado> findWhereIdDetalleEmpleadoEquals(BigDecimal idDetalleEmpleado) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName())
                .append(" WHERE ID_DETALLE_EMPLEADO = ? ORDER BY ID_DETALLE_EMPLEADO").toString(),
                new FecetDetalleEmpleadoMapper(), idDetalleEmpleado);

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_EMPLEADO = :idEmpleado'.
     */
    public List<FecetDetalleEmpleado> findWhereIdEmpleadoEquals(BigDecimal idEmpleado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_EMPLEADO = ? ORDER BY ID_EMPLEADO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDetalleEmpleadoMapper(), idEmpleado);

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    public List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoEquals(BigDecimal idTipoEmpleado) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName())
                .append(" WHERE ID_TIPO_EMPLEADO = ? ORDER BY ID_TIPO_EMPLEADO").toString(),
                new FecetDetalleEmpleadoMapper(), idTipoEmpleado);

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    public List<FecetDetalleEmpleado> findWhereIdCentralEquals(BigDecimal idCentral) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_CENTRAL = ? ORDER BY ID_CENTRAL").toString(),
                new FecetDetalleEmpleadoMapper(), idCentral);

    }

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_ARACE = :idArace'.
     */
    public List<FecetDetalleEmpleado> findWhereIdAraceEquals(BigDecimal idArace) {

        return getJdbcTemplateBase().query(
                SQL_SELECT.append(getTableName()).append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE").toString(),
                new FecetDetalleEmpleadoMapper(), idArace);

    }

    /**
     * Returns the rows from the FECET_DETALLE_EMPLEADO table that matches the
     * specified primary-key value.
     */
    public FecetDetalleEmpleado findByPrimaryKey(FecetDetalleEmpleadoPk pk) {
        return findByPrimaryKey(pk.getIdDetalleEmpleado());
    }

    public List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoIdCentralEquals(BigDecimal idTipoEmpleado,
            BigDecimal idCentral) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_EMPLEADO = ? AND ID_CENTRAL = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetDetalleEmpleadoMapper(), idTipoEmpleado,
                idCentral);
    }

    @Override
    public List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoIdAraceEquals(BigDecimal idTipoEmpleado,
            BigDecimal idArace) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_EMPLEADO = ? AND ID_ARACE = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetDetalleEmpleadoMapper(), idTipoEmpleado, idArace);
    }

}
