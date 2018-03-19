/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;

import mx.gob.sat.siat.feagace.modelo.dao.common.AgaceIdcDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceIdcMapper;
import static mx.gob.sat.siat.feagace.modelo.dao.common.sql.AgaceIdcSQL.SQL_GET_ID_BY_NOMBRE;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.AgaceIdc;
import mx.gob.sat.siat.feagace.modelo.dto.common.AgaceIdcPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAraceEnum;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("agaceIdcDao")
public class AgaceIdcDaoImpl extends BaseJDBCDao<FececMetodo> implements AgaceIdcDao {

    /**
     * Este atributo es un SELECT para seleccionadar las columnas de la tabla
     * AGACE_IDC_DAO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_IDC, ID_ORDEN, NOMBRE_AGENTE_ADUANAL, RFC_AGENTE_ADUANAL, EMAIL_AGENTE_ADUANAL, NOMBRE_CONTRIBUYENTE, RFC_CONTRIBUYENTE, EMAIL_CONTRIBUYENTE, NOMBRE_REPRESENTANTE_LEGAL, RFC_REPRESENTANTE_LEGAL, EMAIL_REPRESENTANTE_LEGAL FROM ").append(getTableName());

    /**
     * Este atributo corresponde a una condición WHERE de la tabla FECEA_METODO
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_IDC = ?");

    /**
     * Method 'insert'
     *
     *
     * @param dto
     * @return AgaceIdcPk
     */
    public AgaceIdcPk insert(AgaceIdc dto) {

        getJdbcTemplateBase().update("INSERT INTO " + getTableName() + " ( ID_IDC, ID_ORDEN, NOMBRE_AGENTE_ADUANAL, RFC_AGENTE_ADUANAL, EMAIL_AGENTE_ADUANAL, NOMBRE_CONTRIBUYENTE, RFC_CONTRIBUYENTE, EMAIL_CONTRIBUYENTE, NOMBRE_REPRESENTANTE_LEGAL, RFC_REPRESENTANTE_LEGAL, EMAIL_REPRESENTANTE_LEGAL ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", dto.getIdIdc(), dto.isIdOrdenNull() ? null : dto.getIdOrden(), dto.getNombreAgenteAduanal(), dto.getRfcAgenteAduanal(), dto.getEmailAgenteAduanal(), dto.getNombreContribuyente(), dto.getRfcContribuyente(), dto.getEmailContribuyente(), dto.getNombreRepresentanteLegal(), dto.getRfcRepresentanteLegal(), dto.getEmailRepresentanteLegal());
        return dto.createPk();

    }

    /**
     * Updates a single row in the AGACE_IDC table.
     */
    public void update(AgaceIdcPk pk, AgaceIdc dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName() + " SET ID_IDC = ?, ID_ORDEN = ?, NOMBRE_AGENTE_ADUANAL = ?, RFC_AGENTE_ADUANAL = ?, EMAIL_AGENTE_ADUANAL = ?, NOMBRE_CONTRIBUYENTE = ?, RFC_CONTRIBUYENTE = ?, EMAIL_CONTRIBUYENTE = ?, NOMBRE_REPRESENTANTE_LEGAL = ?, RFC_REPRESENTANTE_LEGAL = ?, EMAIL_REPRESENTANTE_LEGAL = ? WHERE ID_IDC = ?", dto.getIdIdc(), dto.getIdOrden(), dto.getNombreAgenteAduanal(), dto.getRfcAgenteAduanal(), dto.getEmailAgenteAduanal(), dto.getNombreContribuyente(), dto.getRfcContribuyente(), dto.getEmailContribuyente(), dto.getNombreRepresentanteLegal(), dto.getRfcRepresentanteLegal(), dto.getEmailRepresentanteLegal(), pk.getIdIdc());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static final String getTableName() {
        return "AGACE_IDC";
    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria 'ID_IDC
     * = :idIdc'.
     */
    public AgaceIdc findByPrimaryKey(long idIdc) {

        List<AgaceIdc> list = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(),
                new AgaceIdcMapper(), idIdc);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria ''.
     */
    public List<AgaceIdc> findAll() {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" ORDER BY ID_IDC").toString(),
                new AgaceIdcMapper());

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    public List<AgaceIdc> findByAgaceOrden(long idOrden) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_ORDEN = ?").toString(), new AgaceIdcMapper(), idOrden);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria 'ID_IDC
     * = :idIdc'.
     */
    public List<AgaceIdc> findWhereIdIdcEquals(long idIdc) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_IDC = ? ORDER BY ID_IDC").toString(),
                new AgaceIdcMapper(), idIdc);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    public List<AgaceIdc> findWhereIdOrdenEquals(long idOrden) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN").toString(),
                new AgaceIdcMapper(), idOrden);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_AGENTE_ADUANAL = :nombreAgenteAduanal'.
     */
    public List<AgaceIdc> findWhereNombreAgenteAduanalEquals(String nombreAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE NOMBRE_AGENTE_ADUANAL = ? ORDER BY NOMBRE_AGENTE_ADUANAL").toString(),
                new AgaceIdcMapper(), nombreAgenteAduanal);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_AGENTE_ADUANAL = :rfcAgenteAduanal'.
     */
    public List<AgaceIdc> findWhereRfcAgenteAduanalEquals(String rfcAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE RFC_AGENTE_ADUANAL = ? ORDER BY RFC_AGENTE_ADUANAL").toString(),
                new AgaceIdcMapper(), rfcAgenteAduanal);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_AGENTE_ADUANAL = :emailAgenteAduanal'.
     */
    public List<AgaceIdc> findWhereEmailAgenteAduanalEquals(String emailAgenteAduanal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE EMAIL_AGENTE_ADUANAL = ? ORDER BY EMAIL_AGENTE_ADUANAL").toString(),
                new AgaceIdcMapper(), emailAgenteAduanal);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_CONTRIBUYENTE = :nombreContribuyente'.
     */
    public List<AgaceIdc> findWhereNombreContribuyenteEquals(String nombreContribuyente) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE NOMBRE_CONTRIBUYENTE = ? ORDER BY NOMBRE_CONTRIBUYENTE").toString(),
                new AgaceIdcMapper(), nombreContribuyente);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_CONTRIBUYENTE = :rfcContribuyente'.
     */
    public List<AgaceIdc> findWhereRfcContribuyenteEquals(String rfcContribuyente) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE RFC_CONTRIBUYENTE = ? ORDER BY RFC_CONTRIBUYENTE").toString(),
                new AgaceIdcMapper(), rfcContribuyente);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_CONTRIBUYENTE = :emailContribuyente'.
     */
    public List<AgaceIdc> findWhereEmailContribuyenteEquals(String emailContribuyente) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE EMAIL_CONTRIBUYENTE = ? ORDER BY EMAIL_CONTRIBUYENTE").toString(),
                new AgaceIdcMapper(), emailContribuyente);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'NOMBRE_REPRESENTANTE_LEGAL = :nombreRepresentanteLegal'.
     */
    public List<AgaceIdc> findWhereNombreRepresentanteLegalEquals(String nombreRepresentanteLegal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE NOMBRE_REPRESENTANTE_LEGAL = ? ORDER BY NOMBRE_REPRESENTANTE_LEGAL").toString(),
                new AgaceIdcMapper(), nombreRepresentanteLegal);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'RFC_REPRESENTANTE_LEGAL = :rfcRepresentanteLegal'.
     */
    public List<AgaceIdc> findWhereRfcRepresentanteLegalEquals(String rfcRepresentanteLegal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE RFC_REPRESENTANTE_LEGAL = ? ORDER BY RFC_REPRESENTANTE_LEGAL").toString(),
                new AgaceIdcMapper(), rfcRepresentanteLegal);

    }

    /**
     * Returns all rows from the AGACE_IDC table that match the criteria
     * 'EMAIL_REPRESENTANTE_LEGAL = :emailRepresentanteLegal'.
     */
    public List<AgaceIdc> findWhereEmailRepresentanteLegalEquals(String emailRepresentanteLegal) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(
                " WHERE EMAIL_REPRESENTANTE_LEGAL = ? ORDER BY EMAIL_REPRESENTANTE_LEGAL").toString(),
                new AgaceIdcMapper(), emailRepresentanteLegal);

    }

    /**
     * Returns the rows from the AGACE_IDC table that matches the specified
     * primary-key value.
     */
    @Override
    public AgaceIdc findByPrimaryKey(AgaceIdcPk pk) {
        return findByPrimaryKey(pk.getIdIdc());
    }

    @Override
    public TipoAraceEnum getIdAraceXNombre(String nobreArace) {
        Object[] sqlParams = {nobreArace};

        List<Long> lstIds = getJdbcTemplateBase().query(SQL_GET_ID_BY_NOMBRE, new RowMapper<Long>() {
            @Override
            public Long mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getLong("ID");
            }
        }, sqlParams);

        for (Long idArace : lstIds) {
            for (TipoAraceEnum tipo : TipoAraceEnum.values()) {
                if (idArace.equals(tipo.getId())) {
                    return tipo;
                }
            }
        }

        return null;
    }
}
