package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRechazoOficioMapper implements ParameterizedRowMapper<FecetRechazoOficio> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_OFICIO en la
     * tabla FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_ID_RECHAZO_OFICIO = "ID_RECHAZO_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna ID_OFICIO en la tabla
     * FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_ID_OFICIO = "ID_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna ID_EMPLEADO en la tabla
     * FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_RECHAZO_OFICIO
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_OFICIO de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRechazoOficio mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRechazoOficio dto = new FecetRechazoOficio();

        dto.setIdRechazoOficio(rs.getBigDecimal(COLUMN_ID_RECHAZO_OFICIO));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        return dto;

    }
}
