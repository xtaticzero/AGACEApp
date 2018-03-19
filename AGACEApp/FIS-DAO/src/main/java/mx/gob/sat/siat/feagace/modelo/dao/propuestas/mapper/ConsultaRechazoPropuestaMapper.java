package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.ConsultaInformeComiteRechazoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ConsultaRechazoPropuestaMapper extends ConsultaInformeComiteRechazoMapper implements ParameterizedRowMapper<ConsultaInformeComiteRechazoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ESTATUS en la tabla
     * FECET_PROPUESTA
     */
    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECEC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_PROGRAMADOR_SUBADMINISTRADOR = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECEC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_MOTIVO = "CAUSA";

    public ConsultaInformeComiteRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConsultaInformeComiteRechazoPropuesta dto = super.mapRow(rs, rowNum);
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        dto.setRfcCreacion(rs.getString(COLUMN_PROGRAMADOR_SUBADMINISTRADOR));
        dto.setCausaMotivo(rs.getString(COLUMN_MOTIVO));

        return dto;

    }
}
