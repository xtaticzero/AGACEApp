package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececMotivoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivoPk;

import org.springframework.stereotype.Repository;

@Repository("fececMotivoDao")
public class FececMotivoDaoImpl extends BaseJDBCDao<FececMotivo> implements FececMotivoDao {

    private static final String SQL_SELECT = "SELECT ID_MOTIVO, DESCRIPCION, TIPO_MOTIVO FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececMotivoPk
     */
    public FececMotivoPk insert(FececMotivo dto) {

        dto.setIdMotivo(getJdbcTemplateBase().queryForObject("SELECT FECEQ_MOTIVO.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_MOTIVO, DESCRIPCION, TIPO_MOTIVO ) VALUES ( ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdMotivo(), dto.getDescripcion(), dto.getTipoMotivo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_MOTIVO table.
     */
    public void update(FececMotivoPk pk, FececMotivo dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_MOTIVO = ?, DESCRIPCION = ?, TIPO_MOTIVO = ? WHERE ID_MOTIVO = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdMotivo(), dto.getDescripcion(), dto.getTipoMotivo(), pk.getIdMotivo());

    }

    /**
     * Deletes a single row in the FECEC_MOTIVO table.
     */
    public void delete(FececMotivoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_MOTIVO = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdMotivo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_MOTIVO";
    }

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'ID_MOTIVO = :idMotivo'.
     */
    public FececMotivo findByPrimaryKey(BigDecimal idMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO = ?");
        List<FececMotivo> list = getJdbcTemplateBase().query(query.toString(), new FececMotivoMapper(), idMotivo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria ''.
     */
    public List<FececMotivo> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_MOTIVO");
        return getJdbcTemplateBase().query(query.toString(), new FececMotivoMapper());

    }

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'ID_MOTIVO = :idMotivo'.
     */
    public List<FececMotivo> findWhereIdMotivoEquals(BigDecimal idMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO = ? ORDER BY ID_MOTIVO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececMotivoMapper(), idMotivo);

    }

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececMotivo> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececMotivoMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECEC_MOTIVO table that match the criteria
     * 'TIPO_MOTIVO = :tipoMotivo'.
     */
    public List<FececMotivo> findWhereTipoMotivoEquals(String tipoMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE TIPO_MOTIVO = ? ORDER BY TIPO_MOTIVO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececMotivoMapper(), tipoMotivo);

    }

    /**
     * Returns the rows from the FECEC_MOTIVO table that matches the specified
     * primary-key value.
     */
    public FececMotivo findByPrimaryKey(FececMotivoPk pk) {
        return findByPrimaryKey(pk.getIdMotivo());
    }

}
