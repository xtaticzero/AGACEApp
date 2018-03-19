package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprogramaPk;

public interface FececSubprogramaDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubprogramaPk
     */
    FececSubprogramaPk insert(FececSubprograma dto);

    /**
     * Updates a single row in the FECEC_SUBPROGRAMA table.
     */
    void update(FececSubprogramaPk pk, FececSubprograma dto);

    /**
     * Deletes a single row in the FECEC_SUBPROGRAMA table.
     */
    void delete(FececSubprogramaPk pk);

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'ID_SUBPROGRAMA = :idSubprograma'.
     */
    FececSubprograma findByPrimaryKey(BigDecimal idSubprograma);

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * ''.
     */
    List<FececSubprograma> findAll();

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'ID_SUBPROGRAMA = :idSubprograma'.
     */
    List<FececSubprograma> findWhereIdSubprogramaEquals(BigDecimal idSubprograma);

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'CLAVE = :clave'.
     */
    List<FececSubprograma> findWhereClaveEquals(String clave);

    /**
     * Returns all rows from the FECEC_SUBPROGRAMA table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececSubprograma> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_SUBPROGRAMA table that matches the
     * specified primary-key value.
     */
    FececSubprograma findByPrimaryKey(FececSubprogramaPk pk);

    List<FececSubprograma> findActivos(BigDecimal idGeneral);

}
