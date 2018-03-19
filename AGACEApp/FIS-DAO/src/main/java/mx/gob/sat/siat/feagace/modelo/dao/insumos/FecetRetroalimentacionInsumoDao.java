/**
 * Todos los Derechos Reservados 2013 SAT. Servicio de Administracion Tributaria
 * (SAT). Este software contiene informacion propiedad exclusiva del SAT
 * considerada Confidencial. Queda totalmente prohibido su uso o divulgacion en
 * forma parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumoPk;

public interface FecetRetroalimentacionInsumoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRetroalimentacionInsumoPk
     */
    FecetRetroalimentacionInsumoPk insert(FecetRetroalimentacionInsumo dto);

    /**
     * Updates a single row in the FECET_RETROALIMENTACION_INSUMO table.
     */
    void update(FecetRetroalimentacionInsumoPk pk, FecetRetroalimentacionInsumo dto);

    /**
     * Metodo getIdFecetRetroalimentacionInsumo
     *
     * @return BigDecimal @
     */
    BigDecimal getIdFecetRetroalimentacionInsumo();

    /**
     * Sets estatus to 3 in order to change estatus to retroalimentacion
     * attended
     *
     * @param fechaRetro
     */
    BigDecimal actualizaInsumoRetroAtendida(final BigDecimal idRetroalimentacionInsumo, String motivoAciace, Date fechaRetro);

    /**
     * Deletes a single row in the FECET_RETROALIMENTACION_INSUMO table.
     */
    void delete(FecetRetroalimentacionInsumoPk pk);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_RETROALIMENTACION = :idRetroalimentacion'.
     */
    FecetRetroalimentacionInsumo findByPrimaryKey(BigDecimal idRetroalimentacionInsumo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria ''.
     */
    List<FecetRetroalimentacionInsumo> findAll();

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_RETROALIMENTACION_INSUMO = :idRetroalimentacionInsumo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereIdRetroalimentacionInsumoEquals(
            BigDecimal idRetroalimentacionInsumo);

    /**
     * Returns all rows and counts files from the FECET_RETROALIMENTACION_INSUMO
     * table that match the criteria 'ID_RETROALIMENTACION_INSUMO =
     * :idRetroalimentacionInsumo'.
     */
    List<FecetContadorInsumos> getContadorInsumosPorRetroalimentar(final BigDecimal idInsumo,
            final BigDecimal tipoEmpleado);

    /**
     * Returns all rows and counts files from the FECET_RETROALIMENTACION_INSUMO
     * table that match the criteria 'ID_RETROALIMENTACION_INSUMO =
     * :idRetroalimentacionInsumo'.
     */
    List<FecetContadorInsumos> getContadorInsumosRetroalimentados(final BigDecimal idInsumo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_INSUMO = :idInsumo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereIdInsumoEquals(BigDecimal idPropuestaInsumo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetRetroalimentacionInsumo> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'RFC_RETROALIMENTACION = :rfcRetroalimentacion'.
     */
    List<FecetRetroalimentacionInsumo> findWhereRfcRetroalimentacionInsumoEquals(String rfcRetroalimentacion);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_RETROALIMENTACION = :fechaRetroalimentacion'.
     */
    List<FecetRetroalimentacionInsumo>
            findWhereFechaRetroalimentacionInsumoEquals(Date fechaRetroalimentacion);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ESTATUS = :estatus'.
     */
    List<FecetRetroalimentacionInsumo> findWhereEstatusEquals(String estatus);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereRfcRechazoEquals(String rfcRechazo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereFechaRechazoEquals(Date fechaRechazo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'DESCRIPCION_RECHAZO = :descripcionRechazo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereDescripcionRechazoEquals(final String descripcionRechazo);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_MOTIVO = :descripcionRechazo'.
     */
    List<FecetRetroalimentacionInsumo> findWhereIdMotivoEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_INSUMO = :idInsumo'.
     */
    List<FecetRetroalimentacionInsumo> getRetroalimentacionInsumo(final BigDecimal idInsumo);

    /**
     * Returns the rows from the FECET_RETROALIMENTACION_INSUMO table that
     * matches the specified primary-key value.
     */
    FecetRetroalimentacionInsumo findByPrimaryKey(FecetRetroalimentacionInsumoPk pk);

    List<FecetRetroalimentacionInsumo> getHistoricoRetroalimentacion(FecetInsumo fecetInsumo);

}
