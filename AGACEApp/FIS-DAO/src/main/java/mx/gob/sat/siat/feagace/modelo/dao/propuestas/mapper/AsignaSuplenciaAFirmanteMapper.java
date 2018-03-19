package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplenciaDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class AsignaSuplenciaAFirmanteMapper implements ParameterizedRowMapper<FecetSuplenciaDTO> {

    public static final String COLUMN_ID_SUPLENCIA = "ID_SUPLENCIA";
    public static final String COLUMN_ID_FIRMANTE_A_SUPLIR = "ID_FIRMANTE_A_SUPLIR";
    public static final String COLUMN_ID_FIRMANTE_SUPLENTE = "ID_FIRMANTE_SUPLENTE";
    public static final String COLUMN_ID_MOTIVO_SUPLENCIA = "ID_MOTIVO_SUPLENCIA";
    public static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    public static final String ID_ESTATUS_SUPLENCIA = "ID_ESTATUS_SUPLENCIA";
    public static final String COLUMN_ESTATUS_DESC = "ESTATUS_DESC";
    public static final String COLUMN_MOTIVO_DESC = "MOTIVO_DESC";

    public FecetSuplenciaDTO mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetSuplenciaDTO dto = new FecetSuplenciaDTO();

        dto.setIdSuplencia(rs.getBigDecimal(COLUMN_ID_SUPLENCIA));
        dto.setIdFirmanteASuplir(rs.getBigDecimal(COLUMN_ID_FIRMANTE_A_SUPLIR));
        dto.setIdFrimanteSuplente(rs.getBigDecimal(COLUMN_ID_FIRMANTE_SUPLENTE));
        dto.setIdMotivoSuplencia(rs.getBigDecimal(COLUMN_ID_MOTIVO_SUPLENCIA));
        dto.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setIdStatus(rs.getBigDecimal(ID_ESTATUS_SUPLENCIA));
        dto.setEstatusDesc(rs.getString(COLUMN_ESTATUS_DESC));
        dto.setMotivoSuplenciaDesc(rs.getString(COLUMN_MOTIVO_DESC));
        return dto;
    }
}
