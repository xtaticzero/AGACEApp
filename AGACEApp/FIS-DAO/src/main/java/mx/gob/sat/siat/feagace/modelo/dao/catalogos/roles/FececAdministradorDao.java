package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministrador;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAdministradorPk;

public interface FececAdministradorDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececAdministradorPk
     */
    FececAdministradorPk insert(FececAdministrador dto);

    /**
     * Updates a single row in the FECEC_ADMINISTRADOR table.
     */
    void update(FececAdministradorPk pk, FececAdministrador dto);

    /**
     * Deletes a single row in the FECEC_ADMINISTRADOR table.
     */
    void delete(FececAdministradorPk pk);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    FececAdministrador findByPrimaryKey(BigDecimal idAdministrador);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria ''.
     */
    List<FececAdministrador> findAll();

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    List<FececAdministrador> findByFececCentral(BigDecimal idCentral);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ADMINISTRADOR = :idAdministrador'.
     */
    List<FececAdministrador> findWhereIdAdministradorEquals(BigDecimal idAdministrador);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_ARACE = :idArace'.
     */
    List<FececAdministrador> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    List<FececAdministrador> findWhereIdCentralEquals(BigDecimal idCentral);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'RFC = :rfc'.
     */
    List<FececAdministrador> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_ADMINISTRADOR table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    List<FececAdministrador> findWhereNombreEquals(String nombre);

    /**
     * Returns the rows from the FECEC_ADMINISTRADOR table that matches the
     * specified primary-key value.
     */
    FececAdministrador findByPrimaryKey(FececAdministradorPk pk);

}
