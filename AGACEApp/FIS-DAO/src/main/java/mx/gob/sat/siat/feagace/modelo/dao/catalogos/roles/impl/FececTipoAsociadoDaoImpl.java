/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececTipoAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl.FecetAsociadoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociadoPk;

import org.springframework.stereotype.Repository;

@Repository("fececTipoAsociadoDao")
public class FececTipoAsociadoDaoImpl extends BaseJDBCDao<FececTipoAsociado> implements FececTipoAsociadoDao {

    private static final String SQL_SELECT = "SELECT ID_TIPO_ASOCIADO, NOMBRE,  DESCRIPCION FROM ";
    @SuppressWarnings("compatibility:1639484527198700294")
    private static final long serialVersionUID = 1L;

    private String getSqlSelectTipoAosciadoContribuyente() {
        StringBuilder sqlSelectTipoAosciadoContribuyente = new StringBuilder();
        sqlSelectTipoAosciadoContribuyente.append(" SELECT DISTINCT FECEC_TIPO_ASOCIADO.ID_TIPO_ASOCIADO, FECEC_TIPO_ASOCIADO.NOMBRE, FECEC_TIPO_ASOCIADO.DESCRIPCION FROM ");
        sqlSelectTipoAosciadoContribuyente.append(getTableName());
        sqlSelectTipoAosciadoContribuyente.append(" INNER JOIN ");
        sqlSelectTipoAosciadoContribuyente.append(FecetAsociadoDaoImpl.getTableName());
        sqlSelectTipoAosciadoContribuyente.append(" ON FECET_ASOCIADO.ID_TIPO_ASOCIADO = FECEC_TIPO_ASOCIADO.ID_TIPO_ASOCIADO ");
        sqlSelectTipoAosciadoContribuyente.append("  WHERE FECET_ASOCIADO.RFC = ? ");
        sqlSelectTipoAosciadoContribuyente.append("  AND FECET_ASOCIADO.FECHA_BAJA IS NULL ");
        sqlSelectTipoAosciadoContribuyente.append(" ORDER BY FECEC_TIPO_ASOCIADO.ID_TIPO_ASOCIADO ");
        return sqlSelectTipoAosciadoContribuyente.toString();
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoAsociadoPk
     */
    public FececTipoAsociadoPk insert(FececTipoAsociado dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_TIPO_ASOCIADO, NOMBRE, DESCRIPCION ) VALUES ( ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdTipoAsociado(), dto.getNombre(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_TIPO_ASOCIADO table.
     */
    public void update(FececTipoAsociadoPk pk, FececTipoAsociado dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_TIPO_ASOCIADO = ?, NOMBRE = ?, DESCRIPCION = ? WHERE ID_TIPO_ASOCIADO = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdTipoAsociado(), dto.getNombre(), dto.getDescripcion(), pk.getIdTipoAsociado());

    }

    /**
     * Deletes a single row in the FECEC_TIPO_ASOCIADO table.
     */
    public void delete(FececTipoAsociadoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_ASOCIADO = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdTipoAsociado());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_TIPO_ASOCIADO";
    }

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    public FececTipoAsociado findByPrimaryKey(BigDecimal idTipoAsociado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_ASOCIADO = ?");
        List<FececTipoAsociado> list
                = getJdbcTemplateBase().query(query.toString(), new FececTipoAsociadoMapper(),
                        idTipoAsociado);

        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria ''.
     */
    public List<FececTipoAsociado> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_TIPO_ASOCIADO");
        return getJdbcTemplateBase().query(query.toString(), new FececTipoAsociadoMapper());

    }

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    public List<FececTipoAsociado> findWhereIdTipoAsociadoEquals(BigDecimal idTipoAsociado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_ASOCIADO = ? ORDER BY ID_TIPO_ASOCIADO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececTipoAsociadoMapper(), idTipoAsociado);

    }

    /**
     * Returns all rows from the FECEC_TIPO_ASOCIADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FececTipoAsociado> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececTipoAsociadoMapper(), descripcion);

    }

    public List<FececTipoAsociado> getTipoAsociadoByContribuyente(String rfc) {

        return getJdbcTemplateBase().query(getSqlSelectTipoAosciadoContribuyente(),
                new FececTipoAsociadoMapper(), rfc);

    }

    /**
     * Returns the rows from the FECEC_TIPO_ASOCIADO table that matches the
     * specified primary-key value.
     */
    public FececTipoAsociado findByPrimaryKey(FececTipoAsociadoPk pk) {
        return findByPrimaryKey(pk.getIdTipoAsociado());
    }
}
