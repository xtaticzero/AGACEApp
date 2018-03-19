package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;

public class FecetFlujoPruebasPericialesConEstatusMapper implements ParameterizedRowMapper<FecetFlujoPruebasPericiales> {

    /**
     * Este atributo corresponde numero de anexos.
     */
    private static final String TOTAL_ANEXOS = "TOTAL_ANEXOS";

    @Override
    public FecetFlujoPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetFlujoPruebasPericiales flujoPruebasPericiales = new FecetFlujoPruebasPericialesMapper().mapRow(rs, rowNum);
        flujoPruebasPericiales.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));
        flujoPruebasPericiales.setTotalAnexos(rs.getInt(TOTAL_ANEXOS));
        return flujoPruebasPericiales;
    }

}
