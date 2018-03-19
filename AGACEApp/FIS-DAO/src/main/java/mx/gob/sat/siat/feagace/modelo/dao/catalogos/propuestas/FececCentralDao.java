package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececCentralPk;

public interface FececCentralDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececCentralPk
     */
    FececCentralPk insert(FececCentral dto);

    /**
     * Updates a single row in the FECEC_CENTRAL table.
     */
    void update(FececCentralPk pk, FececCentral dto);

    /**
     * Deletes a single row in the FECEC_CENTRAL table.
     */
    void delete(FececCentralPk pk);

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_CENTRAL = :idCentral'.
     */
    FececCentral findByPrimaryKey(BigDecimal idCentral);

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria ''.
     */
    List<FececCentral> findAll();

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_CENTRAL = :idCentral'.
     */
    List<FececCentral> findWhereIdCentralEquals(BigDecimal idCentral);

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    List<FececCentral> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'RFC = :rfc'.
     */
    List<FececCentral> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_CENTRAL table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececCentral> findWhereNombreEquals(String nombre);

    /**
     * Returns the rows from the FECEC_CENTRAL table that matches the specified
     * primary-key value.
     */
    FececCentral findByPrimaryKey(FececCentralPk pk);

}
