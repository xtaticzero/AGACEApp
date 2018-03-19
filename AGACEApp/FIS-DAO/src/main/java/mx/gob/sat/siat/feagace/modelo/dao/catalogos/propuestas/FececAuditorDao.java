/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditorPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaRechazoOrden;

public interface FececAuditorDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececAuditorPk
     */
    FececAuditorPk insert(FececAuditor dto);

    /**
     * Updates a single row in the FECEC_AUDITOR table.
     */
    void update(FececAuditorPk pk, FececAuditor dto);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_AUDITOR = :idAuditor'.
     */
    FececAuditor findByPrimaryKey(BigDecimal idAuditor);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria ''.
     */
    List<FececAuditor> findAll();

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_AUDITOR = :idAuditor'.
     */
    List<FececAuditor> findWhereIdAuditorEquals(BigDecimal idAuditor);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'RFC = :rfc'.
     */
    List<FececAuditor> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FececAuditor> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'CORREO = :correo'.
     */
    List<FececAuditor> findWhereCorreoEquals(String correo);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FececAuditor> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    List<FececAuditor> findWhereFechaBajaEquals(Date fechaBaja);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ESTADO = :estado'.
     */
    List<FececAuditor> findWhereEstadoEquals(String estado);

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    List<FececAuditor> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns the rows from the FECEC_AUDITOR table that matches the specified
     * primary-key value.
     */
    FececAuditor findByPrimaryKey(FececAuditorPk pk);

    /**
     * Returns the number of rows updated FECET_RECHAZO_ORDEN
     */
    BigDecimal insertFecetRechazoOrden(FeceaRechazoOrden dto);

    /**
     * Returns the status of the orden is rejected or not.If method returns 1
     * means orden has been rejected.
     *
     * @param idOrden
     * @return
     * @
     */
    int findOrdenRechazoEstatus(BigDecimal idOrden);

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    List<FececAuditor> findWhereIdFirmanteEquals(BigDecimal idFirmante);

    List<FececAuditor> findWhereRfcAuditor(String rfc);

}
