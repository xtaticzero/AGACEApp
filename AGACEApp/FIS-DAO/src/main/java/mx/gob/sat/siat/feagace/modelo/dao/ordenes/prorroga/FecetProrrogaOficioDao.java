package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dao.common.firma.FirmaDAO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public interface FecetProrrogaOficioDao extends FirmaDAO {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetProrrogaOficioPk
     */
    FecetProrrogaOficio insert(FecetProrrogaOficio dto);

    /**
     * Metodo getIdFecetProrrogaOficioPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    BigDecimal getIdFecetProrrogaOficioPathDirectorio();

    /**
     *
     * /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    void updateProrrogaResolucion(FecetProrrogaOficio dto);

    /**
     * @param fecetProrrogaOficio
     */
    void updateProrrogaFirma(FecetProrrogaOficio fecetProrrogaOficio);

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO in column APROBADA .
     */
    void validaProrrogaAuditor(final BigDecimal idProrrogaOficio, final String estatusAprobada);

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO in column ESTADO .
     */
    void validaProrrogaFirmante(final BigDecimal idProrroga, final String estatusValidada);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_PRORROGA = :idProrroga'.
     */
    FecetProrrogaOficio findByPrimaryKey(BigDecimal idProrroga);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria ''.
     */
    List<FecetProrrogaOficio> findAll();

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_PRORROGA = :idProrroga'.
     */
    List<FecetProrrogaOficio> findWhereIdProrrogaEquals(BigDecimal idProrroga);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'FECHA_CARGA = :fechaCarga'.
     */
    List<FecetProrrogaOficio> findWhereFechaCargaEquals(Date fechaCarga);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'VALIDADA = :validada'.
     */
    List<FecetProrrogaOficio> findWhereAprobadaEquals(final String aprobada);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_CONTRIBUYENTE = :idContribuyente'.
     */
    List<FecetProrrogaOficio> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_AUDITOR = :idAuditor'.
     */
    List<FecetProrrogaOficio> findWhereIdAuditorEquals(final BigDecimal idAuditor);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_FIRMANTE = :idFirmante'.
     */
    List<FecetProrrogaOficio> findWhereIdFirmanteEquals(final BigDecimal idFirmante);

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'FECHA_FIRMA = :fechaFirma'.
     */
    List<FecetProrrogaOficio> findWhereFechaFirmaEquals(Date fechaFirma);

    /**
     * Metodo buscaUltimaProrrogaPorOficio
     *
     * @param oficio
     * @return List FeceProrroga
     * @
     */
    List<FecetProrrogaOficio> buscaUltimaProrrogaPorOficio(FecetOficio oficio);

    /**
     * Metodo actualizarPorEstadoYFechaMax
     *
     * @param prorroga
     * @param oficio
     * @
     */
    void actualizarPorEstadoYFechaMax(FecetProrrogaOficio prorroga, FecetOficio oficio);

    List<FecetProrrogaOficio> getProrrogaOficioContadorDoc(final BigDecimal idOficio);

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_OFICIO cuyo valor del
     * 'ID_OFICIO sea igual al que se le esta mandando
     */
    List<FecetProrrogaOficio> getProrrogaPorOficioEstatusPendienteAuditor(final BigDecimal idOficio);

    /**
     * Obtiene todos los datos de la tabla FECET_PRORROGA_OFICIO que esten
     * asociados al oficio y con el idEstatus que se envie como parametro. que
     * se manda como parametro.
     *
     * @param idOficio
     * @param idEstatus
     * @return
     */
    List<FecetProrrogaOficio> getProrrogaPorOficio(BigDecimal idOficio, BigDecimal idEstatus);

    List<FecetProrrogaOficio> getProrrogaPorOficioFirmadas(BigDecimal idOficio);

    List<FecetProrrogaOficio> getProrrogaPorOficioPendientes(BigDecimal idOficio);

    List<FecetProrrogaOficio> getHistoricoProrrogaPorOficio(final BigDecimal idOficio);

    /**
     * Metodo para actualziar los campos de FECHA_NOTIF_CONT y
     * FECHA_SURTE_EFECTOS
     *
     * @author eolf
     * @param idProrrogaOficio
     * @param fechaNotificacionCont
     * @param fechaSurteEfectos
     * @param idEstatus
     */
    void updateFechasNotificacion(final BigDecimal idProrrogaOficio, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus);

    /**
     * Metodo para obtener las prorrogas del oficio con el esatus especificado
     *
     * @author eolf
     * @param idOficio
     * @param idEstatus
     * @return
     */
    List<FecetProrrogaOficio> getProrrogaPorOficioEstatus(final BigDecimal idOficio, final BigDecimal idEstatus);

    String obtenerNumeroReferenciaProrOfi(Long idProOficio);

    void updateNumeroReferenciaProrOfi(String noReferencia, Long idProOficio);

}
