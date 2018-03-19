package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSectorPk;

public interface FececSectorDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSectorPk
     */
    FececSectorPk insert(FececSector dto);

    /**
     * Updates a single row in the FECEC_SECTOR table.
     */
    void update(FececSectorPk pk, FececSector dto);

    /**
     * Deletes a single row in the FECEC_SECTOR table.
     */
    void delete(FececSectorPk pk);

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'ID_SECTOR = :idSector'.
     */
    FececSector findByPrimaryKey(BigDecimal idSector);

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria ''.
     */
    List<FececSector> findAll();

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'ID_SECTOR = :idSector'.
     */
    List<FececSector> findWhereIdSectorEquals(BigDecimal idSector);

    /**
     * Returns all rows from the FECEC_SECTOR table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececSector> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_SECTOR table that matches the specified
     * primary-key value.
     */
    FececSector findByPrimaryKey(FececSectorPk pk);

    List<FececSector> findActivos(BigDecimal idGeneral);

}
