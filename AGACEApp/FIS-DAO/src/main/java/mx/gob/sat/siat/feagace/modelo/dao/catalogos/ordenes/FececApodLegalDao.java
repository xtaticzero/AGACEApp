/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegal;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegalPk;

public interface FececApodLegalDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececApodLegalPk
     */
    FececApodLegalPk insert(FececApodLegal dto);

    /**
     * Updates a single row in the FECEA_APODLEGAL table.
     */
    void update(FececApodLegalPk pk, FececApodLegal dto);

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'ID_APODLEGAL = :idApodLegal'.
     */
    FececApodLegal findByPrimaryKey(BigDecimal idApodLegal);

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * ''.
     */
    List<FececApodLegal> findAll();

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'ID_APODLEGAL = :idApodLegal'.
     */
    List<FececApodLegal> findWhereIdApodLegalEquals(BigDecimal idApodLegal);

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'RFC = :rfc'.
     */
    List<FececApodLegal> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececApodLegal> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEA_APODLEGAL table that match the criteria
     * 'RFC_CONTRIBUYENTE = :rfcContribuyente'.
     */
    List<FececApodLegal> findWhereRfcContribuyenteEquals(String rfcContribuyente);

    /**
     * Returns the rows from the FECEA_APODLEGAL table that matches the
     * specified primary-key value.
     */
    FececApodLegal findByPrimaryKey(FececApodLegalPk pk);

}
