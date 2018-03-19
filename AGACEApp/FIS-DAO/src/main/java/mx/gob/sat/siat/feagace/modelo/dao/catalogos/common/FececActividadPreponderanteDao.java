package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececActividadPreponderantePk;

public interface FececActividadPreponderanteDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececActividadPreponderantePk
     */
    FececActividadPreponderantePk insert(FececActividadPreponderante dto);

    /**
     * Updates a single row in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    void update(FececActividadPreponderantePk pk, FececActividadPreponderante dto);

    /**
     * Deletes a single row in the FECEC_ACTIVIDAD_PREPONDERANTE table.
     */
    void delete(FececActividadPreponderantePk pk);

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'ID_ACTIVIDAD_PREPONDERANTE = :idActividadPreponderante'.
     */
    FececActividadPreponderante findByPrimaryKey(BigDecimal idActividadPreponderante);

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria ''.
     */
    List<FececActividadPreponderante> findAll();

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'ID_ACTIVIDAD_PREPONDERANTE = :idActividadPreponderante'.
     */
    List<FececActividadPreponderante> findWhereIdActividadPreponderanteEquals(BigDecimal idActividadPreponderante);

    /**
     * Returns all rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that match
     * the criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececActividadPreponderante> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_ACTIVIDAD_PREPONDERANTE table that
     * matches the specified primary-key value.
     */
    FececActividadPreponderante findByPrimaryKey(FececActividadPreponderantePk pk);

}
