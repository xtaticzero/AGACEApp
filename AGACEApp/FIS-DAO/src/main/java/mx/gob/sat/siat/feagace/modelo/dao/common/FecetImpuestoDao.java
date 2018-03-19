package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoDescripcion;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuestoPk;

public interface FecetImpuestoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetImpuestoPk
     */
    FecetImpuestoPk insert(FecetImpuesto dto);

    /**
     * Updates a single row in the FECET_IMPUESTO table.
     */
    void update(FecetImpuestoPk pk, FecetImpuesto dto);

    /**
     * Deletes a single row in the FECET_IMPUESTO table.
     */
    void delete(FecetImpuestoPk pk);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_IMPUESTO = :idImpuesto'.
     */
    FecetImpuesto findByPrimaryKey(BigDecimal idImpuesto);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * ''.
     */
    List<FecetImpuesto> findAll();

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    List<FecetImpuesto> findByFececTipoImpuesto(BigDecimal idTipoImpuesto);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_IMPUESTO = :idImpuesto'.
     */
    List<FecetImpuesto> findWhereIdImpuestoEquals(BigDecimal idImpuesto);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetImpuesto> findWhereIdPropuestaEquals(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_PROPUESTA = :idPropuesta'.
     */
    List<FecetImpuestoDescripcion> traeImpuestoDescripcion(BigDecimal idPropuesta);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'ID_TIPO_IMPUESTO = :idTipoImpuesto'.
     */
    List<FecetImpuesto> findWhereIdTipoImpuestoEquals(BigDecimal idTipoImpuesto);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'MONTO = :monto'.
     */
    List<FecetImpuesto> findWhereMontoEquals(BigDecimal monto);

    /**
     * Returns the rows from the FECET_IMPUESTO table that matches the specified
     * primary-key value.
     */
    FecetImpuesto findByPrimaryKey(FecetImpuestoPk pk);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'PERIODO_INICIAL = :periodoInicial'.
     */
    List<FecetImpuesto> findWherePeriodoInicialEquals(final Date periodoInicial);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'PERIODO_FINAL = :periodoFinal'.
     */
    List<FecetImpuesto> findWherePeriodoFinalEquals(final Date periodoFinal);

    /**
     * Returns all rows from the FECET_IMPUESTO table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    List<FecetImpuesto> findWhereFechaBajaEquals(final Date fechaBaja);

    /**
     * Regresa la presuntiva de la suma de los impuestos asignados a una
     * Propuesta.
     */
    BigDecimal getPresuntivaPropuesta(final BigDecimal idPropuesta);

    List<FecetImpuesto> getImpuestosPropuesta(final BigDecimal idPropuesta);

    List<FecetImpuesto> getImpuestosRetroPropuesta(final BigDecimal idRetro);

}
