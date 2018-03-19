/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetContadorInsumosMapper implements
        ParameterizedRowMapper<FecetContadorInsumos> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_RETROALIMENTACION_INSUMO = "ID_RETROALIMENTACION_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RETROALIMENTACION
     * en la tabla FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_FECHA_RETROALIMENTACION = "FECHA_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_MOTIVO_SUBADMINISTRADOR = "MOTIVO_SUBADMINISTRADOR";

    /**
     * Este atributo corresponde al alias de la columna CONTADOR en la tabla
     * FECET_RETROALIMENTACION_INSUMO
     */
    public static final String COLUMN_CONTADOR = "CONTADOR";

    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return FeceaMetodo
     * @throws SQLException
     */
    public FecetContadorInsumos mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetContadorInsumos dto = new FecetContadorInsumos();

        dto.setIdRetroalimentacionInsumo(rs
                .getBigDecimal(COLUMN_ID_RETROALIMENTACION_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setFechaRetroalimentacion(rs
                .getTimestamp(COLUMN_FECHA_RETROALIMENTACION));
        dto.setDescripcion(rs.getString(COLUMN_MOTIVO_SUBADMINISTRADOR));
        dto.setContador(rs.getInt(COLUMN_CONTADOR));

        return dto;
    }
}
