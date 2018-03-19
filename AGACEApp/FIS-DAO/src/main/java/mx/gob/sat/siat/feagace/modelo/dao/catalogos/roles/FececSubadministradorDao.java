package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubadministradorPk;

public interface FececSubadministradorDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececSubadministradorPk
     */
    FececSubadministradorPk insert(FececSubadministrador dto);

    /**
     * Updates a single row in the FECEC_SUBADMINISTRADOR table.
     */
    void update(FececSubadministradorPk pk, FececSubadministrador dto);

    /**
     * Deletes a single row in the FECEC_SUBADMINISTRADOR table.
     */
    void delete(FececSubadministradorPk pk);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_SUBADMINISTRADOR = :idSubadministrador'.
     */
    FececSubadministrador findByPrimaryKey(BigDecimal idSubadministrador);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria ''.
     */
    List<FececSubadministrador> findAll();

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    List<FececSubadministrador> findByFececAdministrador(BigDecimal idAdministrador);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_SUBADMINISTRADOR = :idSubadministrador'.
     */
    List<FececSubadministrador> findWhereIdSubadministradorEquals(BigDecimal idSubadministrador);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    List<FececSubadministrador> findWhereIdAdministradorEquals(BigDecimal idAdministrador);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'RFC = :rfc'.
     */
    List<FececSubadministrador> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_SUBADMINISTRADOR table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    List<FececSubadministrador> findWhereNombreEquals(String nombre);

    /**
     * Returns the rows from the FECEC_SUBADMINISTRADOR table that matches the
     * specified primary-key value.
     */
    FececSubadministrador findByPrimaryKey(FececSubadministradorPk pk);

}
