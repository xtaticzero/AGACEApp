/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.constante.ConstantesAuditoria;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociadoPk;

public interface FecetAsociadoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAsociadoPk
     */
    FecetAsociadoPk insert(FecetAsociado dto);

    /**
     * Updates a single row in the FECEC_ASOCIADOS table.
     */
    void update(FecetAsociadoPk pk, FecetAsociado dto);

    /**
     * Deletes a single row in the FECEC_ASOCIADOS table.
     */
    void delete(FecetAsociadoPk pk);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'ID_ASOCIADO = :idAsociado'.
     */
    FecetAsociado findByPrimaryKey(BigDecimal idAsociado);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * ''.
     */
    List<FecetAsociado> findAll();

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    List<FecetAsociado> findByFececTipoAsociado(BigDecimal idTipoAsociado);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'ID_ASOCIADO = :idAsociado'.
     */
    List<FecetAsociado> findWhereIdAsociadoEquals(BigDecimal idAsociado);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    List<FecetAsociado> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'RFC = :rfc'.
     */
    List<FecetAsociado> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'CORREO = :correo'.
     */
    List<FecetAsociado> findWhereCorreoEquals(String correo);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'ID_TIPO_ASOCIADO = :idTipoAsociado'.
     */
    List<FecetAsociado> findWhereIdTipoAsociadoEquals(BigDecimal idTipoAsociado);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetAsociado> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns all rows from the FECEC_ASOCIADOS table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    List<FecetAsociado> findWhereFechaBajaEquals(Date fechaBaja);

    /**
     * Returns the rows from the FECEC_ASOCIADOS table that matches the
     * specified primary-key value.
     */
    FecetAsociado findByPrimaryKey(FecetAsociadoPk pk);

    List<FecetAsociado> getApoderadoLegalContribuyente(String rfc, BigDecimal idTipoAsociado);

    void actualizaApoderadoLegal(FecetAsociado dto, String rfcContribuyente);

    void eliminaApoderadoLegal(String rfcContribuyente, BigDecimal idTipoAsociado);

    List<String> getContribuyenteTipoAsociado(String rfc, BigDecimal idTipoAsociado);

    List<FecetAsociado> getColaboradorContribuyente(BigDecimal idTipoAsociado, BigDecimal idOrden);

    void actualizaColaborador(FecetAsociado dto, BigDecimal idOrden);

    void eliminaColaborador(BigDecimal idOrden, BigDecimal idTipoAsociado);

    List<FecetAsociado> getRepresentanteAgente(BigDecimal idTipoAsociado, BigDecimal idPropuesta);

    List<BigDecimal> getIdAsociado(String rfc, BigDecimal idTipoAsociado, String rfcContribuyente);

    void confirmaColaborador(String rfcContribuyente, String rfc, BigDecimal idTipoAsociado, BigDecimal idOrden);

    List<FecetAsociado> getRepresentanteAgenteByFecha(BigDecimal idTipoAsociado, BigDecimal idPropuesta);

    List<FecetAsociado> getAsociadosByContribuyente(String rfcContribuyente, BigDecimal idOrden);

    @PistaAuditoria(idOperacion = ConstantesAuditoria.GUARDAR_AGENTE_ADUANAL)
    String insertaAgenteAduanal(FecetAsociado dto);

    List<FecetAsociado> getAsociadosByIdOrdenAndRfc(String rfc, BigDecimal idOrden);

    List<FecetAsociado> getAsociadosByRfcAndFechaBaja(String rfcAsociado, String tipoAsociado);

}
