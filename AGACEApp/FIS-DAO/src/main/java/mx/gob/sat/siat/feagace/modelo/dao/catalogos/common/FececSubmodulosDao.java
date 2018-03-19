/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSubmodulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSubmodulosPk;

public interface FececSubmodulosDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubmodulosPk
     */
    FececSubmodulosPk insert(FececSubmodulos dto);

    /**
     * Updates a single row in the FECEC_SUBMODULOS table.
     */
    void update(FececSubmodulosPk pk, FececSubmodulos dto);

    /**
     * Deletes a single row in the FECEC_SUBMODULOS table.
     */
    void delete(FececSubmodulosPk pk);

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    FececSubmodulos findByPrimaryKey(BigDecimal idSubmodulo);

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * ''.
     */
    List<FececSubmodulos> findAll();

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    List<FececSubmodulos> findWhereIdSubmoduloEquals(BigDecimal idSubmodulo);

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececSubmodulos> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececSubmodulos> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_SUBMODULOS table that match the criteria
     * 'FECHA = :fecha'.
     */
    List<FececSubmodulos> findWhereFechaEquals(Date fecha);

    /**
     * Returns the rows from the FECEC_SUBMODULOS table that matches the
     * specified primary-key value.
     */
    FececSubmodulos findByPrimaryKey(FececSubmodulosPk pk);

}
