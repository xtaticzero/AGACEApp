/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacoraPk;

public interface FecetBitacoraDao {

    /**
     * Method 'getIdConsecutivo'
     *
     * @return BigDecimal
     */
    BigDecimal getIdConsecutivo();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetBitacoraPk
     */
    FecetBitacoraPk insert(FecetBitacora dto);

    /**
     * Updates a single row in the FECET_BITACORA table.
     */
    void update(FecetBitacoraPk pk, FecetBitacora dto);

    /**
     * Deletes a single row in the FECET_BITACORA table.
     */
    void delete(FecetBitacoraPk pk);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_BITACORA = :idBitacora'.
     */
    FecetBitacora findByPrimaryKey(BigDecimal idBitacora);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * ''.
     */
    List<FecetBitacora> findAll();

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    List<FecetBitacora> findByFeceaOperaciones(BigDecimal idOperacion);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_BITACORA = :idBitacora'.
     */
    List<FecetBitacora> findWhereIdBitacoraEquals(BigDecimal idBitacora);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    List<FecetBitacora> findWhereIdOperacionEquals(BigDecimal idOperacion);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_INTERNO = :idInterno'.
     */
    List<FecetBitacora> findWhereIdInternoEquals(final BigDecimal idInterno);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'ID_REGISTRO = :idRegistro'.
     */
    List<FecetBitacora> findWhereIdRegistroEquals(BigDecimal idRegistro);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'IPMAQUINA = :ipmaquina'.
     */
    List<FecetBitacora> findWhereIpmaquinaEquals(String ipmaquina);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'NOMBREMAQUINA = :nombremaquina'.
     */
    List<FecetBitacora> findWhereNombremaquinaEquals(String nombremaquina);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_USUARIO = :rfcUsuario'.
     */
    List<FecetBitacora> findWhereRfcUsuarioEquals(String rfcUsuario);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_APODERADO_LEGAL = :rfcApoderadoLegal'.
     */
    List<FecetBitacora> findWhereRfcApoderadoLegalEquals(String rfcApoderadoLegal);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_AGENTE_ADUANAL = :rfcAgenteAduanal'.
     */
    List<FecetBitacora> findWhereRfcAgenteAduanalEquals(String rfcAgenteAduanal);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'RFC_REPRESENTANTE_LEGAL = :rfcRepresentanteLegal'.
     */
    List<FecetBitacora> findWhereRfcRepresentanteLegalEquals(String rfcRepresentanteLegal);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'FECHA = :fecha'.
     */
    List<FecetBitacora> findWhereFechaEquals(Date fecha);

    /**
     * Returns all rows from the FECET_BITACORA table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FecetBitacora> findWhereDescripcionEquals(final String descripcion);

    /**
     * Returns the rows from the FECET_BITACORA table that matches the specified
     * primary-key value.
     */
    FecetBitacora findByPrimaryKey(FecetBitacoraPk pk);

}
