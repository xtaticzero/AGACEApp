package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FececTipoParcialidadDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetParcialidadCifraDTO;

public class FecetParcialidadCifraMapper implements ParameterizedRowMapper<FecetParcialidadCifraDTO> {

    public static final String COLUMN_ID_PARCIALIDAD_CIFRA = "ID_PARCIALIDAD_CIFRA";

    public static final String COLUMN_ID_CIFRA_IMPUESTO = "ID_CIFRA_IMPUESTO";

    public static final String COLUMN_FECHA_ALTA_PARCIALIDAD = "FECHA_ALTA_PARCIALIDAD";

    public static final String COLUMN_NUMERO_PARCIALIDADES = "NUMERO_PARCIALIDADES";

    public static final String COLUMN_ID_TIPO_PARCIALIDAD = "ID_TIPO_PARCIALIDAD";

    public static final String COLUMN_MONTO_TOTAL_CIFRA = "MONTO_TOTAL_CIFRA";

    public static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";

    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    public static final String COLUMN_TIPO_PARCIALIDAD = "TIPO_PARCIALIDAD";

    @Override
    public FecetParcialidadCifraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetParcialidadCifraDTO parcialidad = new FecetParcialidadCifraDTO();
        parcialidad.setFechaAlta(rs.getDate(COLUMN_FECHA_ALTA_PARCIALIDAD));
        parcialidad.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        parcialidad.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        parcialidad.setIdParcialidadHistorico(rs.getBigDecimal(COLUMN_ID_PARCIALIDAD_CIFRA));
        parcialidad.setMontoTotal(rs.getBigDecimal(COLUMN_MONTO_TOTAL_CIFRA));
        parcialidad.setNumeroParcialidades(rs.getBigDecimal(COLUMN_NUMERO_PARCIALIDADES));
        FececTipoParcialidadDTO tipoParcialidad = new FececTipoParcialidadDTO();
        tipoParcialidad.setIdParcialidad(rs.getBigDecimal(COLUMN_ID_TIPO_PARCIALIDAD));
        tipoParcialidad.setTipoParcialidad(rs.getString(COLUMN_TIPO_PARCIALIDAD));
        parcialidad.setTipoParcialidad(tipoParcialidad);
        return parcialidad;
    }
}
