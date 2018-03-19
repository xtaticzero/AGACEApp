package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrdenPk;

public interface FecetProrrogaOrdenDao extends FirmaDAO {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetProrrogaOrdenPk
     */
    FecetProrrogaOrdenPk insert(FecetProrrogaOrden dto);

    /**
     * Metodo getIdFecetProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetProrrogaOrdenPathDirectorio();

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    void update(FecetProrrogaOrdenPk pk, FecetProrrogaOrden dto);

    void updateProrrogaFirma(FecetProrrogaOrden dto);

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN in column APROBADA .
     */
    void validaProrrogaAuditor(final BigDecimal idProrrogaOrden, final String estatusAprobada);

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN in column ESTADO .
     */
    void validaProrrogaFirmante(final BigDecimal idProrroga, final String estatusValidada);

    /**
     * Deletes a single row in the FECET_PRORROGA_ORDEN table.
     */
    void delete(FecetProrrogaOrdenPk pk);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA = :idProrroga'.
     */
    FecetProrrogaOrden findByPrimaryKey(BigDecimal idProrroga);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    List<FecetProrrogaOrden> findAll();

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA = :idProrroga'.
     */
    List<FecetProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    List<FecetProrrogaOrden> findWhereIdOrdenEquals(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_CARGA = :fechaCarga'.
     */
    List<FecetProrrogaOrden> findWhereFechaCargaEquals(Date fechaCarga);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_ESTATUS = :idEstatus'.
     */
    List<FecetProrrogaOrden> findWhereIdEstatusIdOrdenEquals(final BigDecimal estado, BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'VALIDADA = :validada'.
     */
    List<FecetProrrogaOrden> findWhereAprobadaEquals(final String aprobada);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_CONTRIBUYENTE = :idContribuyente'.
     */
    List<FecetProrrogaOrden> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_AUDITOR = :idAuditor'.
     */
    List<FecetProrrogaOrden> findWhereIdAuditorEquals(final BigDecimal idAuditor);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_FIRMANTE = :idFirmante'.
     */
    List<FecetProrrogaOrden> findWhereIdFirmanteEquals(final BigDecimal idFirmante);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_FIRMA = :fechaFirma'.
     */
    List<FecetProrrogaOrden> findWhereFechaFirmaEquals(Date fechaFirma);

    /**
     * Returns the rows from the FECET_PRORROGA_ORDEN table that matches the
     * specified primary-key value.
     */
    FecetProrrogaOrden findByPrimaryKey(FecetProrrogaOrdenPk pk);

    /**
     * Metodo buscaUltimaProrrogaPorOrden
     *
     * @param orden
     * @return List FeceProrroga
     * @
     */
    List<FecetProrrogaOrden> buscaUltimaProrrogaPorOrden(AgaceOrden orden);

    /**
     * Metodo actualizarPorEstadoYFechaMax
     *
     * @param prorroga
     * @param orden
     * @
     */
    void actualizarPorEstadoYFechaMax(FecetProrrogaOrden prorroga, AgaceOrden orden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * ID_ORDEN sea igual al que se le esta mandando
     */
    List<FecetProrrogaOrden> getProrrogaPorOrden(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea Firmada
     */
    List<FecetProrrogaOrden> getHistoricoProrrogaPorOrden(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea por
     * Firmar
     */
    List<FecetProrrogaOrden> getProrrogaPorOrdenPorFirmar(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea Firmada
     */
    List<FecetProrrogaOrden> getProrrogaPorOrdenFirmada(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * ID_ORDEN sea igual al que se le esta mandando
     */
    List<FecetProrrogaOrden> getProrrogaPorOrdenFirmante(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le está mandando
     */
    List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendiente(final BigDecimal idOrden);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando
     */
    List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden);

    List<FecetProrrogaOrden> getProrrogaContadorDoc(final BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_ORDEN = :idOrden' AND ID_ESTATUS = :idEstatus .
     *
     * @author eolf
     */
    List<FecetProrrogaOrden> findWhereIdOrdenEstatusEquals(final BigDecimal idOrden, final BigDecimal... idEstatus);

    /**
     * Metodo para actualziar los campos de FECHA_NOTIF_CONT y
     * FECHA_SURTE_EFECTOS
     *
     * @author eolf
     * @param idOrden
     * @param fechaNotificacionCont
     * @param fechaSurteEfectos
     * @param idEstatus
     */
    void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus);

    void updateProrrogaResolucion(FecetProrrogaOrden dto);

    List<FecetProrrogaOrden> getProrrogaPendienteAprobPorOrden(final BigDecimal idOrden);

    String obtenerNumeroReferenciaProrOrden(Long idProOrden);

    void updateNumeroReferenciaProrOrden(String noReferencia, Long idProOrden);

}
