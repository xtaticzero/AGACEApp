package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececCausaProgramacionPk;

public interface FececCausaProgramacionDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececCausaProgramacionPk
     */
    FececCausaProgramacionPk insert(FececCausaProgramacion dto);

    /**
     * Updates a single row in the FECEC_CAUSA_PROGRAMACION table.
     */
    void update(FececCausaProgramacionPk pk, FececCausaProgramacion dto);

    /**
     * Deletes a single row in the FECEC_CAUSA_PROGRAMACION table.
     */
    void delete(FececCausaProgramacionPk pk);

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'ID_CAUSA_PROGRAMACION = :idCausaProgramacion'.
     */
    FececCausaProgramacion findByPrimaryKey(BigDecimal idCausaProgramacion);

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria ''.
     */
    List<FececCausaProgramacion> findAll();

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'ID_CAUSA_PROGRAMACION = :idCausaProgramacion'.
     */
    List<FececCausaProgramacion> findWhereIdCausaProgramacionEquals(BigDecimal idCausaProgramacion);

    /**
     * Returns all rows from the FECEC_CAUSA_PROGRAMACION table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececCausaProgramacion> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_CAUSA_PROGRAMACION table that matches the
     * specified primary-key value.
     */
    FececCausaProgramacion findByPrimaryKey(FececCausaProgramacionPk pk);

    List<FececCausaProgramacion> findActivos();

}
