/**
 * Todos los Derechos Reservados 2013 SAT. Servicio de Administracion Tributaria
 * (SAT). Este software contiene informacion propiedad exclusiva del SAT
 * considerada Confidencial. Queda totalmente prohibido su uso o divulgacion en
 * forma parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumoPk;
import java.util.Date;
import java.util.List;

public interface FecetDocrechazoinsumoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocrechazoinsumoPk
     */
    FecetDocrechazoinsumoPk insert(FecetDocrechazoinsumo dto);

    /**
     * Updates a single row in the FECET_DOCrechazoINSUMO table.
     */
    void update(FecetDocrechazoinsumoPk pk, FecetDocrechazoinsumo dto);

    /**
     * Deletes a single row in the FECET_DOCrechazoINSUMO table.
     */
    void delete(FecetDocrechazoinsumoPk pk);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'ID_DOCrechazoINSUMO = :idDocrechazoinsumo'.
     */
    FecetDocrechazoinsumo findByPrimaryKey(BigDecimal idDocrechazoinsumo);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria ''.
     */
    List<FecetDocrechazoinsumo> findAll();

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idrechazoInsumo'.
     */
    List<FecetDocrechazoinsumo> findByFecetRechazoInsumo(BigDecimal idrechazoInsumo);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'ID_DOCrechazoINSUMO = :idDocrechazoinsumo'.
     */
    List<FecetDocrechazoinsumo> findWhereIdDocrechazoinsumoEquals(BigDecimal idDocrechazoinsumo);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idrechazoInsumo'.
     */
    List<FecetDocrechazoinsumo> findWhereIdRechazoInsumoEquals(BigDecimal idRechazoInsumo);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    List<FecetDocrechazoinsumo> findWhereRutaArchivoEquals(String rutaArchivo);

    /**
     * Returns all rows from the FECET_DOCrechazoINSUMO table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    List<FecetDocrechazoinsumo> findWhereFechaCreacionEquals(Date fechaCreacion);

    /**
     * Returns the rows from the FECET_DOCrechazoINSUMO table that matches the
     * specified primary-key value.
     */
    FecetDocrechazoinsumo findByPrimaryKey(FecetDocrechazoinsumoPk pk);

}
