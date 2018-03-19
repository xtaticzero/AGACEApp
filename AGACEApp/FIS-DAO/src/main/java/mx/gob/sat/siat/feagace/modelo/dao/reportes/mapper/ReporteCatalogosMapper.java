package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteCatalogosModel;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ReporteCatalogosMapper implements ParameterizedRowMapper<ReporteCatalogosModel> {

    public ReporteCatalogosModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReporteCatalogosModel dto = new ReporteCatalogosModel();
        dto.setCatalogoNombre(rs.getString("NOMBRE"));
        dto.setCatalogoDescripcion(rs.getString("NOMBRE"));
        return dto;
    }
}
