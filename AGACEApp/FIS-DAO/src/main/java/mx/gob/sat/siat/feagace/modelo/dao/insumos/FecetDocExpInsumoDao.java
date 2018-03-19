/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumoPk;

public interface FecetDocExpInsumoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocExpInsumoPk
     */
    FecetDocExpInsumoPk insert(FecetDocExpInsumo dto);

    /**
     * Updates a single row in the FECET_DOC_EXP_INSUMO table.
     */
    void update(FecetDocExpInsumoPk pk, FecetDocExpInsumo dto);

    /**
     * Deletes a single row in the FECET_DOC_EXP_INSUMO table.
     */
    void delete(FecetDocExpInsumoPk pk);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_DOC_EXP_INSUMO = :idDocExpInsumo'.
     */
    FecetDocExpInsumo findByPrimaryKey(BigDecimal idDocExpInsumo);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria ''.
     */
    List<FecetDocExpInsumo> findAll();

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    List<FecetDocExpInsumo> findByFecetInsumo(BigDecimal idInsumo);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_DOC_EXP_INSUMO = :idDocExpInsumo'.
     */
    List<FecetDocExpInsumo> findWhereIdDocExpInsumoEquals(BigDecimal idDocExpInsumo);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    List<FecetDocExpInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetDocExpInsumo> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetDocExpInsumo> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns the rows from the FECET_DOC_EXP_INSUMO table that matches the
     * specified primary-key value.
     */
    FecetDocExpInsumo findByPrimaryKey(FecetDocExpInsumoPk pk);

}
