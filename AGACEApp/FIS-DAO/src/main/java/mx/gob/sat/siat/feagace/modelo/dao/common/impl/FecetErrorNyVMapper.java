/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetErrorNyv;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetErrorNyVMapper implements ParameterizedRowMapper<FecetErrorNyv> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ERROR_NYV en la
     * tabla FECET_ERROR_NYV
     */
    private static final String COLUMN_ID_ERROR_NYV = "ID_ERROR_NYV";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIRMA en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_CODIGO_RESPUESTA = "CODIGO_RESPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna MENSAJE_RESPUESTA en la
     * tabla FECET_ERROR_NYV
     */
    private static final String COLUMN_MENSAJE_RESPUESTA = "MENSAJE_RESPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_ERROR_NYV
     */
    private static final String COLUMN_FECHA_RESPUESTA = "FECHA_RESPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna RFC_AUDITOR en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";

    /**
     * Este método hace un mapeo de los datos almacenados en la tabla
     * FECET_REQUERIMIENTO de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetErrorNyv mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetErrorNyv dto = new FecetErrorNyv();
        dto.setIdErrorNyv(rs.getBigDecimal(COLUMN_ID_ERROR_NYV));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setCodigoRespuesta(rs.getString(COLUMN_CODIGO_RESPUESTA));
        dto.setMensajeRespuesta(rs.getString(COLUMN_MENSAJE_RESPUESTA));
        dto.setFechaRespuesta(rs.getDate(COLUMN_FECHA_RESPUESTA));
        dto.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        return dto;

    }
}
