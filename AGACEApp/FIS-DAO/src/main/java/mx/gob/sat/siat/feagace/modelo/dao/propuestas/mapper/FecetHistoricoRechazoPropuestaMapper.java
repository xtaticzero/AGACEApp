package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetHistoricoRechazoPropuestaMapper implements ParameterizedRowMapper<FecetRechazoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECET_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_TOTAL = "TOTAL";

    /**
     * Este atributo corresponde al nombre de la columna ID_MOTIVO en la tabla
     * FECEC_MOTIVO
     */
    private static final String COLUMN_ID_MOTIVO = "ID_MOTIVO";

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_PROPUESTA en
     * la tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_RECHAZO_PROPUESTA = "ID_RECHAZO_PROPUESTA";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_DOC_RECHAZO_PROPUESTA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetRechazoPropuesta dto = new FecetRechazoPropuesta();
        FececMotivo motivo = new FececMotivoMapper().mapRow(rs, rowNum);
        dto.setIdRechazoPropuesta(rs.getBigDecimal(COLUMN_ID_RECHAZO_PROPUESTA));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setIdMotivo(rs.getBigDecimal(COLUMN_ID_MOTIVO));
        dto.setFececMotivo(motivo);
        dto.setNumeroDocumentos(rs.getBigDecimal(COLUMN_TOTAL));

        return dto;
    }
}
