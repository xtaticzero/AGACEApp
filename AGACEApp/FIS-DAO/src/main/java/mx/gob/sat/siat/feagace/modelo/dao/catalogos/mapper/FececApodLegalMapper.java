/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececApodLegal;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececApodLegalMapper implements ParameterizedRowMapper<FececApodLegal> {

    /**
     * Este atributo corresponde al nombre de la columna ID_APOD_LEGAL en la
     * tabla FECEC_APOD_LEGAL
     */
    private static final String COLUMN_ID_APOD_LEGAL = "ID_APOD_LEGAL";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_APOD_LEGAL
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_APOD_LEGAL
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CONTRIBUYENTE en la
     * tabla FECEC_APOD_LEGAL
     */
    private static final String COLUMN_RFC_CONTRIBUYENTE = "RFC_CONTRIBUYENTE";

    /**
     * Method 'mapRow' Hace un mapeo y un set con los datos de la tabla
     * FECEC_APOD_LEGAL
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FececApodLegal
     */
    public FececApodLegal mapRow(ResultSet rs, int row) throws SQLException {
        FececApodLegal dto = new FececApodLegal();
        dto.setIdApodLegal(rs.getBigDecimal(COLUMN_ID_APOD_LEGAL));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfcContribuyente(rs.getString(COLUMN_RFC_CONTRIBUYENTE));
        return dto;
    }
}
