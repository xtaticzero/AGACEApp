/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececPlazo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececPlazoPk;

public interface FececPlazoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececPlazoPk
     */
    FececPlazoPk insert(FececPlazo dto);

    /**
     * Updates a single row in the FECEC_PLAZO table.
     */
    void update(FececPlazoPk pk, FececPlazo dto);

    /**
     * Deletes a single row in the FECEC_PLAZO table.
     */
    void delete(FececPlazoPk pk);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'ID_PLAZO = :idPlazo'.
     */
    FececPlazo findByPrimaryKey(BigDecimal idPlazo);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria ''.
     */
    List<FececPlazo> findAll();

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'ID_PLAZO = :idPlazo'.
     */
    List<FececPlazo> findWhereIdPlazoEquals(BigDecimal idPlazo);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececPlazo> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DIAS_PLAZO = :diasPlazo'.
     */
    List<FececPlazo> findWhereDiasPlazoEquals(Integer diasPlazo);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'DIAS_HABILES = :diasHabiles'.
     */
    List<FececPlazo> findWhereDiasHabilesEquals(String diasHabiles);

    /**
     * Returns all rows from the FECEC_PLAZO table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FececPlazo> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns the rows from the FECEC_PLAZO table that matches the specified
     * primary-key value.
     */
    FececPlazo findByPrimaryKey(FececPlazoPk pk);

}
