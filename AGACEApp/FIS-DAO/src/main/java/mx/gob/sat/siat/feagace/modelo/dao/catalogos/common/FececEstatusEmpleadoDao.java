/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleadoPk;

public interface FececEstatusEmpleadoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEstatusEmpleadoPk
     */
    FececEstatusEmpleadoPk insert(FececEstatusEmpleado dto);

    /**
     * Updates a single row in the FECEC_ESTATUS_EMPLEADO table.
     */
    void update(FececEstatusEmpleadoPk pk, FececEstatusEmpleado dto);

    /**
     * Deletes a single row in the FECEC_ESTATUS_EMPLEADO table.
     */
    void delete(FececEstatusEmpleadoPk pk);

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'ID_ESTATUS_EMPLEADO = :idEstatusEmpleado'.
     */
    FececEstatusEmpleado findByPrimaryKey(BigDecimal idEstatusEmpleado);

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria ''.
     */
    List<FececEstatusEmpleado> findAll();

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'ID_ESTATUS_EMPLEADO = :idEstatusEmpleado'.
     */
    List<FececEstatusEmpleado> findWhereIdEstatusEmpleadoEquals(BigDecimal idEstatusEmpleado);

    /**
     * Returns all rows from the FECEC_ESTATUS_EMPLEADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececEstatusEmpleado> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_ESTATUS_EMPLEADO table that matches the
     * specified primary-key value.
     */
    FececEstatusEmpleado findByPrimaryKey(FececEstatusEmpleadoPk pk);

}
