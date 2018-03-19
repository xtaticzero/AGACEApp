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

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrdenPk;

public interface FecetRechazoOrdenDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoOrdenPk
     */
    FecetRechazoOrdenPk insert(FecetRechazoOrden dto);

    /**
     * Updates a single row in the FECET_RECHAZO_ORDEN table.
     */
    void update(FecetRechazoOrdenPk pk, FecetRechazoOrden dto);

    /**
     * Deletes a single row in the FECET_RECHAZO_ORDEN table.
     */
    void delete(FecetRechazoOrdenPk pk);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_RECHAZO_ORDEN = :idRechazoOrden'.
     */
    FecetRechazoOrden findByPrimaryKey(BigDecimal idRechazoOrden);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria ''.
     */
    List<FecetRechazoOrden> findAll();

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_RECHAZO_ORDEN = :idRechazoOrden'.
     */
    List<FecetRechazoOrden> findWhereIdRechazoOrdenEquals(BigDecimal idRechazoOrden);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    List<FecetRechazoOrden> findWhereIdOrdenEquals(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FecetRechazoOrden> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    List<FecetRechazoOrden> findWhereFechaRechazoEquals(Date fechaRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'FECHA_ATENCION = :fechaAtencion'.
     */
    List<FecetRechazoOrden> findWhereFechaAtencionEquals(Date fechaAtencion);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    List<FecetRechazoOrden> findWhereRfcRechazoEquals(String rfcRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'NOMBRE_ARCHIVO_RECHAZO = :nombreArchivoRechazo'.
     */
    List<FecetRechazoOrden> findWhereNombreArchivoRechazoEquals(String nombreArchivoRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RUTA_ARCHIVO_RECHAZO = :rutaArchivoRechazo'.
     */
    List<FecetRechazoOrden> findWhereRutaArchivoRechazoEquals(String rutaArchivoRechazo);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    List<FecetRechazoOrden> findWhereEstatusEquals(String estatus);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RFC_RETRO_AUDITOR = :rfcRetroAuditor'.
     */
    List<FecetRechazoOrden> findWhereRfcRetroAuditorEquals(String rfcRetroAuditor);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'NOMBRE_ARCHIVO_REEMPLAZADO = :nombreArchivoReemplazado'.
     */
    List<FecetRechazoOrden> findWhereNombreArchivoReemplazadoEquals(String nombreArchivoReemplazado);

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RUTA_ARCHIVO_REEMPLAZADO = :rutaArchivoReemplazado'.
     */
    List<FecetRechazoOrden> findWhereRutaArchivoReemplazadoEquals(String rutaArchivoReemplazado);

    /**
     * Returns the rows from the FECET_RECHAZO_ORDEN table that matches the
     * specified primary-key value.
     */
    FecetRechazoOrden findByPrimaryKey(FecetRechazoOrdenPk pk);

    List<FecetRechazoOrden> traeRechazoOrden(BigDecimal idRechazoOrden);

    List<FecetRechazoOrden> traeRechazoOrdenPorEstatus(BigDecimal idRechazoOrden,
            String idEstatus);

}
