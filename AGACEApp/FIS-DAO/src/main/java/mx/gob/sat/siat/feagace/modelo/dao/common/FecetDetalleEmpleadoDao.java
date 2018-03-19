/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleadoPk;

public interface FecetDetalleEmpleadoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDetalleEmpleadoPk
     */
    FecetDetalleEmpleadoPk insert(FecetDetalleEmpleado dto);

    /**
     * Updates a single row in the FECET_DETALLE_EMPLEADO table.
     */
    void update(FecetDetalleEmpleadoPk pk, FecetDetalleEmpleado dto);

    /**
     * Deletes a single row in the FECET_DETALLE_EMPLEADO table.
     */
    void delete(FecetDetalleEmpleadoPk pk);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_DETALLE_EMPLEADO = :idDetalleEmpleado'.
     */
    FecetDetalleEmpleado findByPrimaryKey(BigDecimal idDetalleEmpleado);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria ''.
     */
    List<FecetDetalleEmpleado> findAll();

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_DETALLE_EMPLEADO = :idDetalleEmpleado'.
     */
    List<FecetDetalleEmpleado> findWhereIdDetalleEmpleadoEquals(BigDecimal idDetalleEmpleado);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_EMPLEADO = :idEmpleado'.
     */
    List<FecetDetalleEmpleado> findWhereIdEmpleadoEquals(BigDecimal idEmpleado);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoEquals(BigDecimal idTipoEmpleado);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_CENTRAL = :idCentral'.
     */
    List<FecetDetalleEmpleado> findWhereIdCentralEquals(BigDecimal idCentral);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_ARACE = :idArace'.
     */
    List<FecetDetalleEmpleado> findWhereIdAraceEquals(BigDecimal idArace);

    /**
     * Returns the rows from the FECET_DETALLE_EMPLEADO table that matches the
     * specified primary-key value.
     */
    FecetDetalleEmpleado findByPrimaryKey(FecetDetalleEmpleadoPk pk);

    /**
     * Returns all rows from the FECET_DETALLE_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = IdTipoEmpleado AND ID_CENTRAL = IdCentral'.
     */
    List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoIdCentralEquals(BigDecimal idTipoEmpleado, BigDecimal idCentral);

    List<FecetDetalleEmpleado> findWhereIdTipoEmpleadoIdAraceEquals(BigDecimal idTipoEmpleado, BigDecimal idArace);

}
