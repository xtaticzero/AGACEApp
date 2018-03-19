package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececSectorDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSectorPk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fececSectorDao")
public class FececSectorDaoImpl extends BaseJDBCDao<FececSector> implements
        FececSectorDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = 805682782526248586L;
    private static final String SQL_SELECT = "SELECT ID_SECTOR, ID_GENERAL, DESCRIPCION FROM "
            + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSectorPk
     */
    public FececSectorPk insert(FececSector dto) {

        dto.setIdSector(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_SECTOR.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_SECTOR, DESCRIPCION ) VALUES ( ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSector(),
                dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_SECTOR table.
     */
    public void update(FececSectorPk pk, FececSector dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_SECTOR = ?, DESCRIPCION = ? WHERE ID_SECTOR = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdSector(),
                dto.getDescripcion(), pk.getIdSector());

    }

    /**
     * Deletes a single row in the FECEC_SECTOR table.
     */
    public void delete(FececSectorPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_SECTOR = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdSector());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_SECTOR";
    }

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'ID_SECTOR = :idSector'.
     */
    public FececSector findByPrimaryKey(BigDecimal idSector) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SECTOR = ?");
        List<FececSector> list = getJdbcTemplateBase().query(query.toString(),
                new FececSectorMapper(), idSector);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria ''.
     */
    public List<FececSector> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_SECTOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSectorMapper());

    }

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'ID_SECTOR = :idSector'.
     */
    public List<FececSector> findWhereIdSectorEquals(BigDecimal idSector) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_SECTOR = ? ORDER BY ID_SECTOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSectorMapper(), idSector);

    }

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececSector> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? AND BLN_ACTIVO = 1 ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSectorMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_SECTOR table that matches the specified
     * primary-key value.
     */
    public FececSector findByPrimaryKey(FececSectorPk pk) {
        return findByPrimaryKey(pk.getIdSector());
    }

    @Override
    public List<FececSector> findActivos(BigDecimal idGeneral) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE BLN_ACTIVO = ? AND ID_GENERAL = ? ORDER BY ID_SECTOR");
        return getJdbcTemplateBase().query(query.toString(),
                new FececSectorMapper(), Constantes.ENTERO_UNO, idGeneral);

    }
}
