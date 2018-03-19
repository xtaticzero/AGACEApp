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
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececModulosMapper implements ParameterizedRowMapper<FececModulos> {

    /**
     * Este atributo corresponde al nombre de la columna ID_MODULO en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_ID_MODULO = "ID_MODULO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_FECHA = "FECHA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECEC_MOTIVO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public FececModulos mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececModulos dto = new FececModulos();

        dto.setIdModulo(rs.getBigDecimal(COLUMN_ID_MODULO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setFecha(rs.getTimestamp(COLUMN_FECHA));
        dto.setFechaBaja(UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA_BAJA) ? rs.getTimestamp(COLUMN_FECHA_BAJA) : null);

        return dto;
    }
}
