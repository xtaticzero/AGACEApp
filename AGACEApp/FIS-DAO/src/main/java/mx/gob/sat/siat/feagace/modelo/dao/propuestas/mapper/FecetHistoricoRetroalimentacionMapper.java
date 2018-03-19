package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetHistoricoRetroalimentacionMapper implements ParameterizedRowMapper<FecetRetroalimentacion> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RETROALIMENTACION en
     * la tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_RETROALIMENTACION
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la
     * FECEC_MOTIVO
     */
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la
     * FECEC_MOTIVO
     */
    public static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna TOTAL
     */
    public static final String COLUMN_TOTAL = "TOTAL";

    public FecetRetroalimentacion mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetRetroalimentacion dto = new FecetRetroalimentacion();
        FececMotivo motivo = new FececMotivoMapper().mapRow(rs, rowNum);
        dto.setIdRetroalimentacion(rs.getBigDecimal(COLUMN_ID_RETROALIMENTACION));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setFececMotivo(motivo);
        dto.setIdRetroalimentacionOrigen(dto.getIdRetroalimentacion());
        dto.setNumeroDocRetroalimentacion(rs.getBigDecimal(COLUMN_TOTAL));

        return dto;
    }
}
