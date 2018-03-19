package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSubprogramaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprogramaPk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fececSubprogramaDao")
public class FececSubprogramaDaoImpl extends BaseJDBCDao<FececSubprograma>
        implements FececSubprogramaDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -2197755080414836001L;

    private static final String SQL_SELECT = "SELECT ID_SUBPROGRAMA, ID_GENERAL,CLAVE, DESCRIPCION FROM "
            + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubprogramaPk
     */
    public FececSubprogramaPk insert(FececSubprograma dto) {

        dto.setIdSubprograma(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_SUBPROGRAMA.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_SUBPROGRAMA, CLAVE, DESCRIPCION ) VALUES ( ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getClave(),
                dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_SUBPROGRAMA table.
     */
    public void update(FececSubprogramaPk pk, FececSubprograma dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_SUBPROGRAMA = ?, CLAVE = ?, DESCRIPCION = ? WHERE ID_SUBPROGRAMA = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSubprograma(),
                dto.getClave(), dto.getDescripcion(), pk.getIdSubprograma());

    }

    /**
     * Deletes a single row in the FECEC_SUBPROGRAMA table.
     */
    public void delete(FececSubprogramaPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_SUBPROGRAMA = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdSubprograma());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_SUBPROGRAMA";
    }

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'ID_SUBPROGRAMA = :idSubprograma'.
     */
    public FececSubprograma findByPrimaryKey(BigDecimal idSubprograma) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SUBPROGRAMA = ?");
        List<FececSubprograma> list = getJdbcTemplateBase().query(
                query.toString(), new FececSubprogramaMapper(), idSubprograma);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * ''.
     */
    public List<FececSubprograma> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_SUBPROGRAMA");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubprogramaMapper());

    }

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'ID_SUBPROGRAMA = :idSubprograma'.
     */
    public List<FececSubprograma> findWhereIdSubprogramaEquals(
            BigDecimal idSubprograma) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SUBPROGRAMA = ? ORDER BY ID_SUBPROGRAMA");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubprogramaMapper(), idSubprograma);

    }

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'CLAVE = :clave'.
     */
    public List<FececSubprograma> findWhereClaveEquals(String clave) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE CLAVE = ? AND BLN_ACTIVO = 1 ORDER BY CLAVE");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubprogramaMapper(), clave);

    }

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececSubprograma> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSubprogramaMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_SUBPROGRAMA table that matches the
     * specified primary-key value.
     */
    public FececSubprograma findByPrimaryKey(FececSubprogramaPk pk) {
        return findByPrimaryKey(pk.getIdSubprograma());
    }

    @Override
    public List<FececSubprograma> findActivos(BigDecimal idGeneral) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE BLN_ACTIVO = ? AND ID_GENERAL = ?  ORDER BY ID_SUBPROGRAMA");

        return getJdbcTemplateBase().query(query.toString(),
                new FececSubprogramaMapper(), Constantes.ENTERO_UNO, idGeneral);
    }

}
