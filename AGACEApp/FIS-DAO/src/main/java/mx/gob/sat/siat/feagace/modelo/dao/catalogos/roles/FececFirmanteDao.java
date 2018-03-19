/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmantePk;

public interface FececFirmanteDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececFirmantePk
     */
    FececFirmantePk insert(FececFirmante dto);

    /**
     * Updates a single row in the FECEC_FIRMANTE table.
     */
    void update(FececFirmantePk pk, FececFirmante dto);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    FececFirmante findByPrimaryKey(BigDecimal idFirmante);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * ''.
     */
    List<FececFirmante> findAll();

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    List<FececFirmante> findWhereIdFirmanteEquals(BigDecimal idFirmante);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'RFC = :rfc'.
     */
    List<FececFirmante> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececFirmante> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FececFirmante> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    List<FececFirmante> findWhereFechaBajaEquals(Date fechaBaja);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    List<FececFirmante> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'CORREO = :correo'.
     */
    List<FececFirmante> findWhereCorreoEquals(String correo);

    /**
     * Returns the rows from the FECEC_FIRMANTE table that matches the specified
     * primary-key value.
     */
    FececFirmante findByPrimaryKey(FececFirmantePk pk);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    List<FececFirmante> findWhereFirmanteAuditor(BigDecimal idFirmante);

}
