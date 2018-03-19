/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;

public interface FecetRechazoOficioDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoOficio
     */
    FecetRechazoOficio insert(FecetRechazoOficio dto);

    /**
     * Updates a single row in the FECET_RECHAZO_OFICIO table.
     */
    void update(FecetRechazoOficio dto);

    /**
     * Deletes a single row in the FECET_RECHAZO_OFICIO table.
     */
    void delete(final FecetRechazoOficio dto);

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_RECHAZO_OFICIO = :idRechazoOficio'.
     */
    FecetRechazoOficio findByPrimaryKey(BigDecimal idRechazoOficio);

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria ''.
     */
    List<FecetRechazoOficio> findAll();

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_RECHAZO_OFICIO = :idRechazoOficio'.
     */
    List<FecetRechazoOficio> findWhereIdRechazoOficioEquals(BigDecimal idRechazoOficio);

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    List<FecetRechazoOficio> findWhereIdOficioEquals(BigDecimal idOrden);

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    List<FecetRechazoOficio> findWhereEstatusEquals(String estatus);

    /**
     * Returns the rows from the FECET_RECHAZO_OFICIO table that matches the
     * specified primary-key value.
     */
    FecetRechazoOficio findByPrimaryKey(final FecetRechazoOficio dto);

    List<FecetRechazoOficio> traeRechazoOficio(BigDecimal idRechazoOficio);

}
