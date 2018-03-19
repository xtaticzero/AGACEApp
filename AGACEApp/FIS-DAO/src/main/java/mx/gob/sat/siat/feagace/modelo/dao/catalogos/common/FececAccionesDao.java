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

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececAcciones;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececAccionesPk;

public interface FececAccionesDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececAccionesPk
     */
    FececAccionesPk insert(FececAcciones dto);

    /**
     * Updates a single row in the FECEC_ACCIONES table.
     */
    void update(FececAccionesPk pk, FececAcciones dto);

    /**
     * Deletes a single row in the FECEC_ACCIONES table.
     */
    void delete(FececAccionesPk pk);

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    FececAcciones findByPrimaryKey(BigDecimal idAccion);

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * ''.
     */
    List<FececAcciones> findAll();

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    List<FececAcciones> findWhereIdAccionEquals(BigDecimal idAccion);

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececAcciones> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececAcciones> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECEC_ACCIONES table that match the criteria
     * 'FECHA = :fecha'.
     */
    List<FececAcciones> findWhereFechaEquals(Date fecha);

    /**
     * Returns the rows from the FECEC_ACCIONES table that matches the specified
     * primary-key value.
     */
    FececAcciones findByPrimaryKey(FececAccionesPk pk);

}
