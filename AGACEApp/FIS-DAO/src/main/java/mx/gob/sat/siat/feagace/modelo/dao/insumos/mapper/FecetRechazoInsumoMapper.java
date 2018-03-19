package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetRechazoInsumoMapper implements ParameterizedRowMapper<FecetRechazoInsumo> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_INSUMO en la
     * tabla FECET_RECHAZO_INSUMO
     */
    private static final String COLUMN_ID_RECHAZO_INSUMO = "ID_RECHAZO_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_RECHAZO_INSUMO
     */
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RECHAZO_INSUMO
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_RECHAZO en la tabla
     * FECET_RECHAZO_INSUMO
     */
    private static final String COLUMN_RFC_RECHAZO = "RFC_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RECHAZO en la
     * tabla FECET_RECHAZO_INSUMO
     */
    private static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_RECHAZO_INSUMO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRechazoInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetRechazoInsumo dto = new FecetRechazoInsumo();

        dto.setIdRechazoInsumo(rs.getBigDecimal(COLUMN_ID_RECHAZO_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setRfcRechazo(rs.getString(COLUMN_RFC_RECHAZO));
        dto.setFechaRechazo(rs.getDate(COLUMN_FECHA_RECHAZO));

        return dto;
    }
}
