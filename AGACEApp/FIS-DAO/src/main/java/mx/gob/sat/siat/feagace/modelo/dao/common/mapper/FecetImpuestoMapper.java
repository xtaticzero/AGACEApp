package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetImpuesto;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetImpuestoMapper implements ParameterizedRowMapper<FecetImpuesto> {

    /**
     * Este atributo corresponde al nombre de la columna ID_IMPUESTO en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_ID_IMPUESTO = "ID_IMPUESTO";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_IMPUESTO
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_IMPUESTO en la
     * tabla FECET_IMPUESTO
     */
    private static final String COLUMN_ID_TIPO_IMPUESTO = "ID_TIPO_IMPUESTO";

    /**
     * Este atributo corresponde al nombre de la columna MONTO en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_MONTO = "MONTO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_ID_CONCEPTO = "ID_CONCEPTO";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_IMPUESTO
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetImpuesto mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetImpuesto dto = new FecetImpuesto();

        dto.setIdImpuesto(rs.getBigDecimal(COLUMN_ID_IMPUESTO));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdTipoImpuesto(rs.getBigDecimal(COLUMN_ID_TIPO_IMPUESTO));
        dto.setMonto(rs.getBigDecimal(COLUMN_MONTO));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setIdConcepto(rs.getBigDecimal(COLUMN_ID_CONCEPTO));
        return dto;
    }
}
