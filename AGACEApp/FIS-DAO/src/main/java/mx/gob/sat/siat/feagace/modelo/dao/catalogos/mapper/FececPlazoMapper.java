/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececPlazo;

public class FececPlazoMapper implements ParameterizedRowMapper<FececPlazo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PLAZO en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_ID_PLAZO = "ID_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna DIAS_PLAZO en la tabla
     * FECET_ERROR_NYV
     */
    private static final String COLUMN_DIAS_PLAZO = "DIAS_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna DIAS_HABILES en la
     * tabla FECET_ERROR_NYV
     */
    private static final String COLUMN_DIAS_HABILES = "DIAS_HABILES";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_ERROR_NYV
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este método hace un mapeo de los datos almacenados en la tabla
     * FECET_REQUERIMIENTO de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececPlazo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececPlazo dto = new FececPlazo();
        dto.setIdPlazo(rs.getBigDecimal(COLUMN_ID_PLAZO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setDiasPlazo(rs.getInt(COLUMN_DIAS_PLAZO));
        dto.setDiasHabiles(rs.getString(COLUMN_DIAS_HABILES));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        return dto;

    }
}
