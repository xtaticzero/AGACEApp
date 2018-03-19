package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRechazoOrdenMapper implements ParameterizedRowMapper<FecetRechazoOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_ORDEN en la
     * tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_ID_RECHAZO_ORDEN = "ID_RECHAZO_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_ATENCION en la
     * tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_FECHA_ATENCION = "FECHA_ATENCION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RECHAZO en la tabla
     * FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_RFC_RECHAZO = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO_RECHAZO
     * en la tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_NOMBRE_ARCHIVO_RECHAZO = "NOMBRE_ARCHIVO_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO_RECHAZO en
     * la tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_RUTA_ARCHIVO_RECHAZO = "RUTA_ARCHIVO_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RETRO_AUDITOR en la
     * tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_RFC_RETRO_AUDITOR = "RFC_RETRO_AUDITOR";

    /**
     * Este atributo corresponde al nombre de la columna
     * NOMBRE_ARCHIVO_REEMPLAZADO en la tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_NOMBRE_ARCHIVO_REEMPLAZADO = "NOMBRE_ARCHIVO_REEMPLAZADO";

    /**
     * Este atributo corresponde al nombre de la columna
     * RUTA_ARCHIVO_REEMPLAZADO en la tabla FECET_RECHAZO_ORDEN
     */
    private static final String COLUMN_RUTA_ARCHIVO_REEMPLAZADO = "RUTA_ARCHIVO_REEMPLAZADO";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_ORDEN de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRechazoOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRechazoOrden dto = new FecetRechazoOrden();

        dto.setIdRechazoOrden(rs.getBigDecimal(COLUMN_ID_RECHAZO_ORDEN));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));
        dto.setFechaAtencion(rs.getDate(COLUMN_FECHA_ATENCION));
        dto.setRfcRechazo(rs.getString(COLUMN_RFC_RECHAZO));
        dto.setNombreArchivoRechazo(rs.getString(COLUMN_NOMBRE_ARCHIVO_RECHAZO));
        dto.setRutaArchivoRechazo(rs.getString(COLUMN_RUTA_ARCHIVO_RECHAZO));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        dto.setRfcRetroAuditor(rs.getString(COLUMN_RFC_RETRO_AUDITOR));
        dto.setNombreArchivoReemplazado(rs.getString(COLUMN_NOMBRE_ARCHIVO_REEMPLAZADO));
        dto.setRutaArchivoReemplazado(rs.getString(COLUMN_RUTA_ARCHIVO_REEMPLAZADO));
        return dto;

    }
}
