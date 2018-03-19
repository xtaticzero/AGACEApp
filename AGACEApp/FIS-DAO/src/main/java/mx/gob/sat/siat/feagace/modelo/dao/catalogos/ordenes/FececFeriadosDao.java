/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriadosPk;

public interface FececFeriadosDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececFeriadosPk
     */
    FececFeriadosPk insert(FececFeriados dto);

    /**
     * Updates a single row in the FECECFERIADOS table.
     */
    void update(FececFeriadosPk pk, FececFeriados dto);

    /**
     * Returns all rows from the FECECFERIADOS table that match the criteria
     * 'IDFERIADO = :idFeriado'.
     */
    FececFeriados findByPrimaryKey(BigDecimal idFeriado);

    /**
     * Returns all rows from the FECECFERIADOS table that match the criteria ''.
     */
    List<FececFeriados> findAll();

    /**
     * Returns all rows from the FECECFERIADOS table that match the criteria
     * 'IDFERIADO = :idFeriado'.
     */
    List<FececFeriados> findWhereIdFeriadoEquals(BigDecimal idFeriado);

    /**
     * Returns all rows from the FECECFERIADOS table that match the criteria
     * 'FECHA = :fecha'.
     */
    List<FececFeriados> findWhereFechaEquals(Date fecha);

    /**
     * Returns all rows from the FECECFERIADOS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    List<FececFeriados> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECECFERIADOS table that matches the specified
     * primary-key value.
     */
    FececFeriados findByPrimaryKey(FececFeriadosPk pk);

    /**
     * Metodo buscarDiasFestivosContributenteCargaDocumentos
     *
     * @param fecha
     * @param diasHabiles
     * @return List
     * @
     */
    List<FececFeriados> buscarDiasFestivosContributenteCargaDocumentos(final Date fecha,
            int diasHabiles);
}
