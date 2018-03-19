/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececEmpleado;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoEmpleado;

public class FececEmpleadoConAraceTipoMapper extends FececEmpleadoMapper {

    /**
     * Metodo mapRow Hace un mapeo y un set de los datos de la tabla
     * FECEC_EMPLEADO, FECEC_UNIDAD_ADMINISTRATIVA y FECEC_TIPO_EMPLEADO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public FececEmpleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEmpleado empleado = super.mapRow(rs, rowNum);

        empleado.setFececEstatusEmpleado(new FececEstatusEmpleadoMapper().mapRow(rs, rowNum));

        FececTipoEmpleado fececTipoEmpleado = new FececTipoEmpleadoMapper().mapRow(rs, rowNum);
        fececTipoEmpleado.setDescripcion(rs.getString("DESCRIPCION_TIPO_EMPLEADO"));
        empleado.setFececTipoEmpleado(fececTipoEmpleado);

        empleado.setFecetDetalleEmpleado(new FecetDetalleEmpleadoMapper().mapRow(rs, rowNum));

        if (empleado.getFecetDetalleEmpleado().getIdCentral() != null) {
            empleado.setSede(rs.getString("SEDE"));
        }

        if (empleado.getFecetDetalleEmpleado().getIdArace() != null) {
            empleado.setRegional(rs.getString("REGIONAL"));
        }
        return empleado;
    }
}
