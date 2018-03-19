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

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsasPk;

public interface FecetCompulsasDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetCompulsasPk
     */
    FecetCompulsasPk insert(FecetCompulsas dto);

    /**
     * Updates a single row in the FECET_COMPULSAS table.
     */
    void update(FecetCompulsasPk pk, FecetCompulsas dto);

    /**
     * Returns all rows from the FECET_COMPULSAS table that match the criteria
     * 'ID_COMPULSA = :idCompulsa'.
     */
    FecetCompulsas findByPrimaryKey(BigDecimal idCompulsa);

    FecetCompulsas getCompulsaPorOficio(BigDecimal idOficio);

    List<FecetCompulsas> getCompulsasOrden(String rfc);

    FecetCompulsas getDatosGenerales(BigDecimal idOficio);

    boolean actualizaEstatusCompulsa(BigDecimal idOficio, BigDecimal estatus,
            BigDecimal idOrden);

    boolean existeCorreoAsociadoCompulsaTerceros(BigDecimal idOficio, BigDecimal idOrden);

    FecetCompulsas findByIdCompulsaOrden(BigDecimal idOrdenCompulsa);
}
