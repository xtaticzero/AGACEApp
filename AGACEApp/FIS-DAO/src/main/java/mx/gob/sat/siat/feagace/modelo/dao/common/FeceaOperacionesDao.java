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

import mx.gob.sat.siat.feagace.modelo.dto.common.FeceaOperaciones;
import mx.gob.sat.siat.feagace.modelo.dto.common.FeceaOperacionesPk;

public interface FeceaOperacionesDao {

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
     * @return FeceaOperacionesPk
     */
    FeceaOperacionesPk insert(FeceaOperaciones dto);

    /**
     * Updates a single row in the FECEA_OPERACIONES table.
     */
    void update(FeceaOperacionesPk pk, FeceaOperaciones dto);

    /**
     * Deletes a single row in the FECEA_OPERACIONES table.
     */
    void delete(FeceaOperacionesPk pk);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    FeceaOperaciones findByPrimaryKey(BigDecimal idOperacion);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * ''.
     */
    List<FeceaOperaciones> findAll();

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    List<FeceaOperaciones> findByFececAcciones(BigDecimal idAccion);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_MODULO = :idModulo'.
     */
    List<FeceaOperaciones> findByFececModulos(BigDecimal idModulo);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    List<FeceaOperaciones> findByFececSubmodulos(BigDecimal idSubmodulo);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_OPERACION = :idOperacion'.
     */
    List<FeceaOperaciones> findWhereIdOperacionEquals(BigDecimal idOperacion);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_MODULO = :idModulo'.
     */
    List<FeceaOperaciones> findWhereIdModuloEquals(BigDecimal idModulo);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_SUBMODULO = :idSubmodulo'.
     */
    List<FeceaOperaciones> findWhereIdSubmoduloEquals(BigDecimal idSubmodulo);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'ID_ACCION = :idAccion'.
     */
    List<FeceaOperaciones> findWhereIdAccionEquals(BigDecimal idAccion);

    /**
     * Returns all rows from the FECEA_OPERACIONES table that match the criteria
     * 'FECHA = :fecha'.
     */
    List<FeceaOperaciones> findWhereFechaEquals(Date fecha);

    /**
     * Returns the rows from the FECEA_OPERACIONES table that matches the
     * specified primary-key value.
     */
    FeceaOperaciones findByPrimaryKey(FeceaOperacionesPk pk);

}
