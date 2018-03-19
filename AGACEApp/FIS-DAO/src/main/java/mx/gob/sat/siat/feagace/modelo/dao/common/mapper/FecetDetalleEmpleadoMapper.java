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

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleEmpleado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDetalleEmpleadoMapper implements ParameterizedRowMapper<FecetDetalleEmpleado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DETALLE_EMPLEADO en
     * la tabla FECE_DETALLE_EMPLEADO
     */
    private static final String COLUMN_ID_DETALLE_EMPLEADO = "ID_DETALLE_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_EMPLEADO en la tabla
     * FECE_DETALLE_EMPLEADO
     */
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_EMPLEADO en la
     * tabla FECE_DETALLE_EMPLEADO
     */
    private static final String COLUMN_ID_TIPO_EMPLEADO = "ID_TIPO_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_CENTRAL en la tabla
     * FECE_DETALLE_EMPLEADO
     */
    private static final String COLUMN_ID_CENTRAL = "ID_CENTRAL";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECE_DETALLE_EMPLEADO
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECEC_AUDITOR de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetDetalleEmpleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDetalleEmpleado dto = new FecetDetalleEmpleado();
        dto.setIdDetalleEmpleado(rs.getBigDecimal(COLUMN_ID_DETALLE_EMPLEADO));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setIdTipoEmpleado(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLEADO));
        dto.setIdCentral(rs.getBigDecimal(COLUMN_ID_CENTRAL));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));

        return dto;

    }
}
