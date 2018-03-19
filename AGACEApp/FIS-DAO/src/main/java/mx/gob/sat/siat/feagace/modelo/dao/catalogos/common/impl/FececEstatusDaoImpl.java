package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececEstatusDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;

import org.springframework.stereotype.Repository;

@Repository("fececEstatusDao")
public class FececEstatusDaoImpl extends BaseJDBCDao<FececEstatus> implements FececEstatusDao {

    private static final String SQL_SELECT = "SELECT ID_ESTATUS, DESCRIPCION, MODULO FROM " + getTableName();

    private static final long serialVersionUID = 1L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEstatusPk
     */
    public FececEstatusPk insert(FececEstatus dto) {

        dto.setIdEstatus(getJdbcTemplateBase().queryForObject("SELECT FECEQ_ESTATUS.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ESTATUS, DESCRIPCION, MODULO ) VALUES ( ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdEstatus(), dto.getDescripcion(), dto.getModulo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_ESTATUS table.
     */
    public void update(FececEstatusPk pk, FececEstatus dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ESTATUS = ?, DESCRIPCION = ?, MODULO = ? WHERE ID_ESTATUS = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdEstatus(), dto.getDescripcion(), dto.getModulo(),
                pk.getIdEstatus());

    }

    /**
     * Deletes a single row in the FECEC_ESTATUS table.
     */
    public void delete(FececEstatusPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_ESTATUS = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdEstatus());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ESTATUS";
    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_ESTATUS = :idEstatus'.
     */
    public FececEstatus findByPrimaryKey(BigDecimal idEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ESTATUS = ?");
        List<FececEstatus> list = getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper(), idEstatus);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria ''.
     */
    public List<FececEstatus> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper());

    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_ESTATUS = :idEstatus'.
     */
    public List<FececEstatus> findWhereIdEstatusEquals(BigDecimal idEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ESTATUS = ? ORDER BY ID_ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper(), idEstatus);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececEstatus> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'MODULO = :modulo'.
     */
    public List<FececEstatus> findWhereModuloEquals(String modulo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE MODULO = ? ORDER BY MODULO");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper(), modulo);

    }

    /**
     * Returns all rows from the FECEC_ESTATUS table that match the criteria
     * 'ID_MODULO = :moduloId'.
     */
    public List<FececEstatus> findWhereModuloId(Integer moduloId) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MODULO = ? ORDER BY MODULO");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper(), moduloId);
    }

    /**
     * Returns the rows from the FECEC_ESTATUS table that matches the specified
     * primary-key value.
     */
    public FececEstatus findByPrimaryKey(FececEstatusPk pk) {
        return findByPrimaryKey(pk.getIdEstatus());
    }

    /**
     * Returns the rows from the FECEC_ESTATUS table that matches the specified
     * ID_ESTATUS 64 -67.
     */
    public List<FececEstatus> findOnlyRechazosPropuestas() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ESTATUS IN(61,64,65,66,67) ORDER BY DESCRIPCION");

        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper());
    }

    public List<FececEstatus> getEstatusByTipoEstatus(TipoEstatusEnum... enums) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        if (enums != null && enums.length > 0) {
            query.append(" WHERE ID_ESTATUS IN (");
            int index = 0;
            for (TipoEstatusEnum tipoEstatusEnum : enums) {
                if (++index != 1) {
                    query.append(", ");
                }
                query.append(tipoEstatusEnum.getId());
            }
            query.append(") ");
        }
        query.append(" ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(), new FececEstatusMapper());
    }

}
