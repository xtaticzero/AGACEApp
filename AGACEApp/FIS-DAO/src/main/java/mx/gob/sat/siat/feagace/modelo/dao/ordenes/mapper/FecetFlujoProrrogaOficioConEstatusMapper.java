package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;

public class FecetFlujoProrrogaOficioConEstatusMapper implements ParameterizedRowMapper<FecetFlujoProrrogaOficio> {

    /**
     * Este atributo corresponde numero de anexos.
     */
    private static final String TOTAL_ANEXOS = "TOTAL_ANEXOS";

    @Override
    public FecetFlujoProrrogaOficio mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetFlujoProrrogaOficio fecetFlujoProrrogaOficio = new FecetFlujoProrrogaOficioMapper().mapRow(rs, rowNum);
        fecetFlujoProrrogaOficio.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));
        fecetFlujoProrrogaOficio.setTotalAnexos(rs.getInt(TOTAL_ANEXOS));
        return fecetFlujoProrrogaOficio;
    }

}
