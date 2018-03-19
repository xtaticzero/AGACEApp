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

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececFirmante;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececFirmanteMapper implements ParameterizedRowMapper<FececFirmante> {

    /**
     * Este atributo corresponde al nombre de la columna ID_AUDITOR en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECEC_FIRMANTE
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE en la tabla
     * FECEC_FIRMANTE
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este metodo hace un mapeo de los datos almacenados en la tabla
     * FECET_AUDITOR de acuerdo a su columna correspondiente
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FececFirmante mapRow(ResultSet rs, int rowNum) throws SQLException {
        FececFirmante dto = new FececFirmante();
        dto.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setCorreo(rs.getString(COLUMN_CORREO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        return dto;

    }
}
