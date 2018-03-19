/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorAsignadosAdministrador;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ContadorAsignadosAdministradorMapper implements ParameterizedRowMapper<ContadorAsignadosAdministrador> {

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE como resultado
     * del query contador de propuestas
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna RFC_ADMINISTRADOR como
     * resultado del query contador de ordenes
     */
    private static final String COLUMN_RFC_ADMINISTRADOR = "RFC_ADMINISTRADOR";

    /**
     * Este atributo corresponde al nombre de la columna TOTAL como resultado
     * del query contador de ordenes
     */
    private static final String COLUMN_TOTAL = "TOTAL";

    /**
     * Este atributo corresponde al nombre de la columna ENTIDAD como resultado
     * del query contador de ordenes
     */
    private static final String COLUMN_ENTIDAD = "ENTIDAD";

    /**
     * Este metodo hace un mapeo de los datos resultantes del conteo de ordenes
     * en la tabla FECET_ORDEN de acuerdo a su columna correspondiente
     *
     * @param st
     * @param numRow
     * @return Map
     * @throws SQLException
     */
    public ContadorAsignadosAdministrador mapRow(ResultSet st, int numRow) throws SQLException {

        ContadorAsignadosAdministrador dto = new ContadorAsignadosAdministrador();
        if (UtileriasMapperDao.existeColumna(st, COLUMN_NOMBRE)) {
            dto.setNombreAdministrador(st.getString(COLUMN_NOMBRE));
        }
        dto.setRfcAdministrador(st.getString(COLUMN_RFC_ADMINISTRADOR));
        dto.setTotalPropuestasAsignadas(st.getInt(COLUMN_TOTAL));
        dto.setEntidad(st.getString(COLUMN_ENTIDAD));
        return dto;
    }
}
