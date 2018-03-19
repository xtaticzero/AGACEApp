package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleadoPk;

public interface FececTipoEmpleadoDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececTipoEmpleadoPk
     */
    FececTipoEmpleadoPk insert(FececTipoEmpleado dto);

    /**
     * Updates a single row in the FECEC_TIPO_EMPLEADO table.
     */
    void update(FececTipoEmpleadoPk pk, FececTipoEmpleado dto);

    /**
     * Deletes a single row in the FECEC_TIPO_EMPLEADO table.
     */
    void delete(FececTipoEmpleadoPk pk);

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    FececTipoEmpleado findByPrimaryKey(BigDecimal idTipoEmpleado);

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria ''.
     */
    List<FececTipoEmpleado> findAll();

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'ID_TIPO_EMPLEADO = :idTipoEmpleado'.
     */
    List<FececTipoEmpleado> findWhereIdTipoEmpleadoEquals(BigDecimal idTipoEmpleado);

    /**
     * Returns all rows from the FECEC_TIPO_EMPLEADO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    List<FececTipoEmpleado> findWhereDescripcionEquals(String descripcion);

    /**
     * Returns the rows from the FECEC_TIPO_EMPLEADO table that matches the
     * specified primary-key value.
     */
    FececTipoEmpleado findByPrimaryKey(FececTipoEmpleadoPk pk);

}
