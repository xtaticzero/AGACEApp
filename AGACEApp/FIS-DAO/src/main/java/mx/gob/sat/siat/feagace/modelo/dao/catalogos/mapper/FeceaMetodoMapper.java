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

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FeceaMetodoMapper implements ParameterizedRowMapper<FececMetodo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_METODO en la tabla
     * FECEA_METODO
     */
    public static final String COLUMN_ID_METODO = "ID_METODO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEA_METODO
     */
    public static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna ABREVIATURA en la tabla
     * FECEA_METODO
     */
    public static final String COLUMN_ABREVIATURA = "ABREVIATURA";

    /**
     * Este atributo corresponde al nombre de la columna DURACION_PLAZO_ORDEN en
     * la tabla FECEA_METODO
     */
    public static final String COLUMN_DURACION_PLAZO_ORDEN = "DURACION_PLAZO_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_PLAZO en la
     * tabla FECEA_METODO
     */
    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return FeceaMetodo
     * @throws SQLException
     */
    public FececMetodo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececMetodo dto = new FececMetodo();

        dto.setIdMetodo(rs.getBigDecimal(COLUMN_ID_METODO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setAbreviatura(rs.getString(COLUMN_ABREVIATURA));

        return dto;
    }
}
