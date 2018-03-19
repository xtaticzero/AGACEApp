package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ReporteEjecutivoMapper implements ParameterizedRowMapper<ReporteEjecutivoDTO> {

    @Override
    public ReporteEjecutivoDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ReporteEjecutivoDTO reporteEjecutivo = new ReporteEjecutivoDTO();
        reporteEjecutivo.setIdMetodo(resultSet.getBigDecimal("ID_METODO"));
        reporteEjecutivo.setMetodo(resultSet.getString("METODO"));
        reporteEjecutivo.setNumeroOrdenes(resultSet.getBigDecimal("NUMERO_ORDENES"));
        return reporteEjecutivo;
    }
}
