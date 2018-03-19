package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDetalleContribuyenteMapper implements ParameterizedRowMapper<FecetDetalleContribuyente> {

    private static final String COLUMN_ID_DETALLE_CONTRIBUYENTE = "ID_DETALLE_CONTRIBUYENTE";
    private static final String COLUMN_RFC_CONTRIBUYENTE = "RFC_CONTRIBUYENTE";
    private static final String COLUMN_FECHA_CONSULTA = "FECHA_CONSULTA";
    private static final String COLUMN_AMPARADO = "AMPARADO";
    private static final String COLUMN_PPEE = "PPEE";

    @Override
    public FecetDetalleContribuyente mapRow(ResultSet resultSet, int i) throws SQLException {
        FecetDetalleContribuyente fecetDetalleContribuyente = new FecetDetalleContribuyente();
        fecetDetalleContribuyente.setIdDetalleContribuyente(resultSet.getBigDecimal(COLUMN_ID_DETALLE_CONTRIBUYENTE));
        fecetDetalleContribuyente.setRfcContribuyente(resultSet.getString(COLUMN_RFC_CONTRIBUYENTE));
        fecetDetalleContribuyente.setFechaConsulta(resultSet.getDate(COLUMN_FECHA_CONSULTA));
        fecetDetalleContribuyente.setAmparado(resultSet.getBigDecimal(COLUMN_AMPARADO));
        fecetDetalleContribuyente.setPpee(resultSet.getBigDecimal(COLUMN_PPEE));
        return fecetDetalleContribuyente;
    }
}
