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

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececFeriadosMapper implements ParameterizedRowMapper<FececFeriados> {

    /**
     * Este atributo corresponde al nombre de la columna ID_FERIADO en la tabla
     * FECEC_FERIADOS
     */
    public static final String COLUMN_ID_FERIADO = "ID_FERIADO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECEC_FERIADOS
     */
    public static final String COLUMN_FECHA = "FECHA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_FERIADOS
     */
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Metodo rowMap Hace un mapeo y un set con los datos de la tabla
     * FECEC_FERIADOS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececFeriados mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececFeriados dto = new FececFeriados();

        dto.setIdFeriado(rs.getBigDecimal(COLUMN_ID_FERIADO));
        dto.setFecha(rs.getDate(COLUMN_FECHA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        return dto;
    }
}
