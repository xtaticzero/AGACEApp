package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevision;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececRevisionPk;

public interface FececRevisionDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececRevisionPk
     */
    FececRevisionPk insert(FececRevision dto);

    /**
     * Updates a single row in the FECEC_REVISION table.
     */
    void update(FececRevisionPk pk, FececRevision dto);

    /**
     * Deletes a single row in the FECEC_REVISION table.
     */
    void delete(FececRevisionPk pk);

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    FececRevision findByPrimaryKey(BigDecimal idRevision);

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * ''.
     */
    List<FececRevision> findAll();

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    List<FececRevision> findWhereIdRevisionEquals(BigDecimal idRevision);

    /**
     * Returns all rows from the FECEC_REVISION table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececRevision> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns DESCRIPCION from the FECEC_REVISION table that match the criteria
     * 'ID_REVISION = :idRevision'.
     */
    String getDescripcionRevision(final BigDecimal idRevision);

    /**
     * Returns the rows from the FECEC_REVISION table that matches the specified
     * primary-key value.
     */
    FececRevision findByPrimaryKey(FececRevisionPk pk);

}
