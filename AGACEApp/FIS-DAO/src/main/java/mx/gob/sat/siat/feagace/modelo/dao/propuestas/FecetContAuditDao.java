/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAudit;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContAuditPk;

public interface FecetContAuditDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetContAuditPk
     */
    FecetContAuditPk insert(FecetContAudit dto);

    /**
     * Updates a single row in the FECET_CONT_AUDIT table.
     *
     * @param pk
     * @param dto
     */
    void update(FecetContAuditPk pk, FecetContAudit dto);

    /**
     * Updates a single row in the FECET_CONT_AUDIT table.
     *
     * @param idContAudit
     * @param nombreArchivo
     * @param rutaArchivo
     */
    void updateFirmado(final BigDecimal idContAudit, final String nombreArchivo,
            final String rutaArchivo);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'ID_CONT_AUDIT = :idContAudit'.
     *
     * @param idContAudit
     * @return
     */
    FecetContAudit findByPrimaryKey(BigDecimal idContAudit);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * ''.
     *
     * @return
     */
    List<FecetContAudit> findAll();

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'ID_CONT_AUDIT = :idContAudit'.
     *
     * @param idContAudit
     * @return
     */
    List<FecetContAudit> findWhereIdContAuditEquals(BigDecimal idContAudit);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'FECHA_CARGA = :fechaCarga'.
     *
     * @param fechaCarga
     * @return
     */
    List<FecetContAudit> findWhereFechaCargaEquals(Date fechaCarga);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombreArchivo'.
     *
     * @param nombreArchivo
     * @return
     */
    List<FecetContAudit> findWhereNombreArchivoEquals(String nombreArchivo);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     *
     * @param rutaArchivo
     * @return
     */
    List<FecetContAudit> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'RFC_AUDITOR = :rfcAuditor'.
     *
     * @param rfcAuditor
     * @return
     */
    List<FecetContAudit> findWhereRfcAuditorEquals(String rfcAuditor);

    /**
     * Returns all rows from the FECET_CONT_AUDIT table that match the criteria
     * 'NOMBRE_AUDITOR = :nombreAuditor'.
     *
     * @param nombreAuditor
     * @return
     */
    List<FecetContAudit> findWhereNombreAuditorEquals(String nombreAuditor);

    /**
     * Returns the rows from the FECET_CONT_AUDIT table that matches the
     * specified primary-key value.
     *
     * @param pk
     * @return
     */
    FecetContAudit findByPrimaryKey(FecetContAuditPk pk);

}
