package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.ContadorInsumosSubAdmin;

public class ContadorInsumosSubAdminMapper implements ParameterizedRowMapper<ContadorInsumosSubAdmin> {

    private static final String COLUMN_RFC_SUBADMINISTRADOR = "RFC_SUBADMINISTRADOR";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_TOTAL_ASIGNADOS = "TOTAL_ASIGNADOS";

    public ContadorInsumosSubAdmin mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContadorInsumosSubAdmin contador = new ContadorInsumosSubAdmin();

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC_SUBADMINISTRADOR)) {
            contador.setNombre(rs.getString(COLUMN_RFC_SUBADMINISTRADOR));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE)) {
            contador.setNombre(rs.getString(COLUMN_NOMBRE));
        }
        contador.setTotalInsumos(rs.getLong(COLUMN_TOTAL_ASIGNADOS));

        return contador;
    }
}
