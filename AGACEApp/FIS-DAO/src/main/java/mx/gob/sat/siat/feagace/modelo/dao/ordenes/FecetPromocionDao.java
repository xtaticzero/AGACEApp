/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocion;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionPk;

public interface FecetPromocionDao extends FirmaDAO {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPromocionPk
     */
    FecetPromocionPk insert(FecetPromocion dto);

    /**
     * Updates a single row in the FECET_PROMOCION table.
     */
    void update(FecetPromocionPk pk, FecetPromocion dto);

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'ID_PROMOCION = :idPromocion'.
     */
    FecetPromocion findByPrimaryKey(BigDecimal idPromocion);

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * ''.
     */
    List<FecetPromocion> findAll();

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'ID_PROMOCION = :idPromocion'.
     */
    List<FecetPromocion> findWhereIdPromocionEquals(BigDecimal idPromocion);

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'IDORDEN = :idOrden'.
     */
    List<FecetPromocion> findWhereIdOrdenEquals(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'FECHA_CARGA = :fechaCarga'.
     */
    List<FecetPromocion> findWhereFechaCargaEquals(Date fechaCarga);

    /**
     * Returns all rows from the FECET_PROMOCION table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetPromocion> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns the rows from the FECET_PROMOCION table that matches the
     * specified primary-key value.
     */
    FecetPromocion findByPrimaryKey(FecetPromocionPk pk);

    /**
     * Metodo buscarPromocionRfcUsuario
     *
     * @param rfcUsuario
     * @return List FecetPromocion
     * @
     */
    List<FecetPromocion> buscarPromocionRfcUsuario(String rfcUsuario);

    /**
     * Metodo buscarPromocionNombreUsuario
     *
     * @param nombreUsuario
     * @return List FecetPromocion
     * @
     */
    List<FecetPromocion> buscarPromocionNombreUsuario(String nombreUsuario);

    /**
     * Returns all rows from the EXTEMPORANEA table that match the criteria
     * 'EXTEMPORANEA = :extemporanea'.
     */
    List<FecetPromocion> buscarPromocionExtemporenea(String extemporanea);

    /**
     * @param idOrden
     * @return List FecetPromocion
     * @
     */
    List<FecetPromocion> getPromocionContadorPruebasAlegatos(final BigDecimal idOrden);

    /**
     * Metodo getIdFecetPromocionPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetPromocionPathDirectorio();

    /**
     * Metodo insertarRegistroGuardarArchivoPromocion
     *
     * @param dto
     * @return FecetPromocionPk
     * @
     */
    FecetPromocionPk insertarRegistroGuardarArchivoPromocion(FecetPromocion dto);

    BigDecimal getIdPromocion();
}
