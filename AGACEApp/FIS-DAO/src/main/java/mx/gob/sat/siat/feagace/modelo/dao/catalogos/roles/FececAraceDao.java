/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAracePk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;

public interface FececAraceDao {

    /**
     * * Method 'insert'
     *
     * @param dto
     * @return FececAracePk
     */
    FececAracePk insert(FececArace dto);

    /**
     * Updates a single row in the FECEC_ARACE table.
     */
    void update(FececAracePk pk, FececArace dto);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    FececArace findByPrimaryKey(BigDecimal idArace);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     */
    List<FececArace> findAll();

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     */
    List<FececArace> findAllReportes();

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    List<FececArace> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececArace> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria 'SEDE
     * = :sede'.
     */
    List<FececArace> findWhereSedeEquals(String sede);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria 'SEDE
     * = :entidad'.
     */
    BigDecimal findWhereEntidadInclude(final String entidad);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'CENTRAL = :central'.
     */
    List<FececArace> findWhereCentralEquals(final Boolean central);

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE IS NOT IN (1, 17)'.
     */
    List<FececArace> getUnidadesParaAsignarPropuesta();

    /**
     * Returns the rows from the FECEC_ARACE table that matches the specified
     * primary-key value.
     */
    FececArace findByPrimaryKey(FececAracePk pk);

    /**
     * Returns the rows from the FECEC_ARACE table that matches the specified
     * NOMBRE LIKE 'ARACE%'.
     */
    List<String> findOnlyEntidad();

    /**
     * Returns all rows from the FECEC_ARACE table by RFC_AUDITOR.
     */
    List<FececArace> findAraceByAuditor(final String rfcAuditor, String condicion);

    ClaveFolioOficioDTO obtenerClavesFolioOficio(BigDecimal idArace);
}
