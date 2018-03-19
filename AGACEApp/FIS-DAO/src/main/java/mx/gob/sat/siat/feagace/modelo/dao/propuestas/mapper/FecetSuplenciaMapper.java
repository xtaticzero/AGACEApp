package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que mapea el resultado de la consulta a la base de datos a un objeto de
 * tipo <code>FecetSuplencia</code>
 *
 * @author Luis Rodriguez
 * @version 1.0
 */
public class FecetSuplenciaMapper implements ParameterizedRowMapper<FecetSuplencia> {

    @Override
    public FecetSuplencia mapRow(ResultSet rs, int i) throws SQLException {
        FecetSuplencia suplencia = new FecetSuplencia();
        suplencia.setIdSuplencia(rs.getBigDecimal("ID_SUPLENCIA"));
        suplencia.setIdFirmanteASuplir(rs.getBigDecimal("ID_FIRMANTE_A_SUPLIR"));
        suplencia.setIdFirmanteSuplente(rs.getBigDecimal("ID_FIRMANTE_SUPLENTE"));
        suplencia.setIdMotivoSuplencia(rs.getBigDecimal("ID_MOTIVO_SUPLENCIA"));
        suplencia.setFechaInicio(rs.getDate("FECHA_INICIO"));
        suplencia.setFechaFin(rs.getDate("FECHA_FIN"));
        return suplencia;
    }
}
