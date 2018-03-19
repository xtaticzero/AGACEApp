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

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegato;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoPk;

public interface FecetAlegatoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAlegatoPk
     */
    FecetAlegatoPk insert(FecetAlegato dto);

    /**
     * Returns all rows from the FecetAlegato table that match the criteria
     * 'IDALEGATO = :idAlegato'.
     */
    FecetAlegato findByPrimaryKey(BigDecimal idAlegato);

    /**
     * Returns all rows from the FecetAlegato table that match the criteria ''.
     */
    List<FecetAlegato> findAll();

    /**
     * Returns all rows from the FecetAlegato table that match the criteria
     * 'IDALEGATO = :idAlegato'.
     */
    List<FecetAlegato> findWhereIdAlegatoEquals(BigDecimal idAlegato);

    /**
     * Returns all rows from the FecetAlegato table that match the criteria
     * 'IDPROMOCION = :idPromocion'.
     */
    List<FecetAlegato> findWhereIdPromocionEquals(BigDecimal idPromocion);

    /**
     * Returns all rows from the FecetAlegato table that match the criteria
     * 'FECHACARGA = :fechaCarga'.
     */
    List<FecetAlegato> findWhereFechaCargaEquals(Date fechaCarga);

    /**
     * Returns all rows from the FecetAlegato table that match the criteria
     * 'RUTAARCHIVO = :rutaArchivo'.
     */
    List<FecetAlegato> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns the rows from the FecetAlegato table that matches the specified
     * primary-key value.
     */
    FecetAlegato findByPrimaryKey(FecetAlegatoPk pk);

}
