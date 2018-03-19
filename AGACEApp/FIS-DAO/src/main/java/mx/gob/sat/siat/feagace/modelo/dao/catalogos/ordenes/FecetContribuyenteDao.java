package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteCatalogosModel;

public interface FecetContribuyenteDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetContribuyentePk
     */
    FecetContribuyentePk insert(FecetContribuyente dto);

    /**
     * Updates a single row in the FECET_CONTRIBUYENTE table.
     */
    void update(FecetContribuyentePk pk, FecetContribuyente dto);

    /**
     * Deletes a single row in the FECET_CONTRIBUYENTE table.
     */
    void delete(FecetContribuyentePk pk);

    /**
     * Actualiza datos contribuyente.
     */
    void actualizaDatosContribuyente(final FecetContribuyente dto);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ID_CONTRIBUYENTE = :idContribuyente'.
     */
    FecetContribuyente findByPrimaryKey(BigDecimal idContribuyente);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria ''.
     */
    List<FecetContribuyente> findAll();

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ID_CONTRIBUYENTE = :idContribuyente'.
     */
    List<FecetContribuyente> findWhereIdContribuyenteEquals(BigDecimal idContribuyente);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'RFC = :rfc'.
     */
    List<FecetContribuyente> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    List<FecetContribuyente> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'REGIMEN = :regimen'.
     */
    List<FecetContribuyente> findWhereRegimenEquals(String regimen);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'SITUACION = :situacion'.
     */
    List<FecetContribuyente> findWhereSituacionEquals(String situacion);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'TIPO = :tipo'.
     */
    List<FecetContribuyente> findWhereTipoEquals(String tipo);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'SITUACION_DOMICILIO = :situacionDomicilio'.
     */
    List<FecetContribuyente> findWhereSituacionDomicilioEquals(String situacionDomicilio);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'DOMICILIO_FISCAL = :domicilioFiscal'.
     */
    List<FecetContribuyente> findWhereDomicilioFiscalEquals(String domicilioFiscal);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ACTIVIDAD_PREPONDERANTE = :actividadPreponderante'.
     */
    List<FecetContribuyente> findWhereActividadPreponderanteEquals(String actividadPreponderante);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ENTIDAD = :entidad'.
     */
    List<FecetContribuyente> findWhereEntidadEquals(String entidad);

    /**
     * Returns the rows from the FECET_CONTRIBUYENTE table that matches the
     * specified primary-key value.
     */
    FecetContribuyente findByPrimaryKey(FecetContribuyentePk pk);

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ENTIDAD = :entidad'.
     */
    List<ReporteCatalogosModel> findEntidadAll();

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table
     */
    List<ReporteCatalogosModel> findEntidadInsumosAll();

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table
     */
    List<ReporteCatalogosModel> findEntidadPropuestasAll();

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table
     */
    List<ReporteCatalogosModel> findEntidadOrdenesAll();

    /**
     * Returns all rows from the FECET_CONTRIBUYENTE table that match the
     * criteria 'ACTIVIDAD_PREPONDERANTE = :entidad'.
     */
    List<ReporteCatalogosModel> findActividadPrepoderanteAll();

    FecetContribuyente obtenerContribuyenteAuditado(BigDecimal idOrdenCompulsado);
}
