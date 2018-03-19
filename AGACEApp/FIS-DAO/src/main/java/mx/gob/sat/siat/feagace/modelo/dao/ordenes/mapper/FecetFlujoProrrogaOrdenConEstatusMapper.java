package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOrden;

public class FecetFlujoProrrogaOrdenConEstatusMapper implements ParameterizedRowMapper<FecetFlujoProrrogaOrden> {

    /**
     * Este atributo corresponde numero de anexos.
     */
    private static final String TOTAL_ANEXOS = "TOTAL_ANEXOS";

    @Override
    public FecetFlujoProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetFlujoProrrogaOrden flujoProrrogaOrden = new FecetFlujoProrrogaOrdenMapper().mapRow(rs, rowNum);
        flujoProrrogaOrden.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));
        flujoProrrogaOrden.setTotalAnexos(rs.getInt(TOTAL_ANEXOS));
        return flujoProrrogaOrden;
    }

}
