package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEmpleadoAciace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececEmpleadoAciacePk;

public interface FececEmpleadoAciaceDao {

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececEmpleadoAciacePk
     */
    FececEmpleadoAciacePk insert(FececEmpleadoAciace dto);

    /**
     * Updates a single row in the FECEC_EMPLEADO_ACIACE table.
     */
    void update(FececEmpleadoAciacePk pk, FececEmpleadoAciace dto);

    /**
     * Deletes a single row in the FECEC_EMPLEADO_ACIACE table.
     */
    void delete(FececEmpleadoAciacePk pk);

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'ID_EMPLEADO_ACIACE = :idEmpleadoAciace'.
     */
    FececEmpleadoAciace findByPrimaryKey(BigDecimal idEmpleadoAciace);

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria ''.
     */
    List<FececEmpleadoAciace> findAll();

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'ID_EMPLEADO_ACIACE = :idEmpleadoAciace'.
     */
    List<FececEmpleadoAciace> findWhereIdEmpleadoAciaceEquals(BigDecimal idEmpleadoAciace);

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'RFC = :rfc'.
     */
    List<FececEmpleadoAciace> findWhereRfcEquals(String rfc);

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'NOMBRE = :nombre'.
     */
    List<FececEmpleadoAciace> findWhereNombreEquals(String nombre);

    /**
     * Returns all rows from the FECEC_EMPLEADO_ACIACE table that match the
     * criteria 'CORREO = :correo'.
     */
    List<FececEmpleadoAciace> findWhereCorreoEquals(final String correo);

    /**
     * Returns the rows from the FECEC_EMPLEADO_ACIACE table that matches the
     * specified primary-key value.
     */
    FececEmpleadoAciace findByPrimaryKey(FececEmpleadoAciacePk pk);

}
