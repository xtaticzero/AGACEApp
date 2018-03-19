/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRetroalimentacionMapper implements
        ParameterizedRowMapper<FecetRetroalimentacion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RETROALIMENTACION
     * en la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_RFC_RETROALIMENTACION = "RFC_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RETROALIMENTACION
     * en la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_FECHA_RETROALIMENTACION = "FECHA_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RECHAZO en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_RFC_RECHAZO = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO_RECHAZO
     * en la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_NOMBRE_ARCHIVO_RECHAZO = "NOMBRE_ARCHIVO_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO_RECHAZO en
     * la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_RUTA_ARCHIVO_RECHAZO = "RUTA_ARCHIVO_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION_RECHAZO en
     * la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_DESCRIPCION_RECHAZO = "DESCRIPCION_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna ID_EMPLEADO en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ESTATUS en la tabla
     * FECET_RETROALIMENTACION
     */
    public static final String COLUMN_BLN_ESTATUS = "BLN_ESTATUS";

    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return FeceaMetodo
     * @throws SQLException
     */
    public FecetRetroalimentacion mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetRetroalimentacion dto = new FecetRetroalimentacion();

        dto.setIdRetroalimentacion(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setRfcRetroalimentacion(rs.getString(COLUMN_RFC_RETROALIMENTACION));
        dto.setFechaRetroalimentacion(rs.getTimestamp(COLUMN_FECHA_RETROALIMENTACION));
        dto.setFechaRechazo(rs.getTimestamp(COLUMN_FECHA_RECHAZO));
        dto.setDescripcionRechazo(rs.getString(COLUMN_DESCRIPCION_RECHAZO));
        dto.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        dto.setEstatus(rs.getBigDecimal(COLUMN_ESTATUS));
        dto.setBlnEstatus(rs.getBigDecimal(COLUMN_BLN_ESTATUS));

        return dto;
    }
}
