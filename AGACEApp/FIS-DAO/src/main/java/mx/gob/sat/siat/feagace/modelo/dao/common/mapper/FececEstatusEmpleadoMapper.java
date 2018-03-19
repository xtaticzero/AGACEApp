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

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatusEmpleado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececEstatusEmpleadoMapper implements ParameterizedRowMapper<FececEstatusEmpleado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS_EMPLEADO en
     * la tabla FECEC_ESTATUS_EMPLEADO
     */
    private static final String COLUMN_ID_ESTATUS_EMPLEADO = "ID_ESTATUS_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_ESTATUS_EMPLEADO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECEC_AUDITOR de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececEstatusEmpleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececEstatusEmpleado dto = new FececEstatusEmpleado();
        dto.setIdEstatusEmpleado(rs.getBigDecimal(COLUMN_ID_ESTATUS_EMPLEADO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        return dto;

    }
}
