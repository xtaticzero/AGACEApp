/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetErrorNyv;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetErrorNyvPk;

public interface FecetErrorNyvDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetErrorNyvPk
     */
    FecetErrorNyvPk insert(FecetErrorNyv dto);

    /**
     * Updates a single row in the FECET_ERROR_NYV table.
     */
    void update(FecetErrorNyvPk pk, FecetErrorNyv dto);

    /**
     * Deletes a single row in the FECET_ERROR_NYV table.
     */
    void delete(FecetErrorNyvPk pk);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ERROR_NYV = :idErrorNyv'.
     */
    FecetErrorNyv findByPrimaryKey(final BigDecimal idErrorNyv);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * ''.
     */
    List<FecetErrorNyv> findAll();

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ERROR_NYV = :idErrorNyv'.
     */
    List<FecetErrorNyv> findWhereIdErrorNyvEquals(long idErrorNyv);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    List<FecetErrorNyv> findWhereIdOrdenEquals(long idOrden);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'CODIGO_RESPUESTA = :codigoRespuesta'.
     */
    List<FecetErrorNyv> findWhereCodigoRespuestaEquals(String codigoRespuesta);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'MENSAJE_RESPUESTA = :mensajeRespuesta'.
     */
    List<FecetErrorNyv> findWhereMensajeRespuestaEquals(String mensajeRespuesta);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'FECHA_RESPUESTA = :fechaRespuesta'.
     */
    List<FecetErrorNyv> findWhereFechaRespuestaEquals(Date fechaRespuesta);

    /**
     * Returns all rows from the FECET_ERROR_NYV table that match the criteria
     * 'OBSERVACIONES = :observaciones'.
     */
    List<FecetErrorNyv> findWhereObservacionesEquals(String observaciones);

    /**
     * Returns the rows from the FECET_ERROR_NYV table that matches the
     * specified primary-key value.
     */
    FecetErrorNyv findByPrimaryKey(FecetErrorNyvPk pk);

}
