/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetContadorInsumosRechazadosMapper implements
        ParameterizedRowMapper<FecetContadorInsumosRechazados> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_INSUMO en la
     * tabla FECET_RECHAZO_INSUMO
     */
    public static final String COLUMN_ID_RECHAZO_INSUMO = "ID_RECHAZO_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna ID_INSUMO en la tabla
     * FECET_RECHAZO_INSUMO
     */
    public static final String COLUMN_ID_INSUMO = "ID_INSUMO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_RETROALIMENTACION
     * en la tabla FECET_RECHAZO_INSUMO
     */
    public static final String COLUMN_FECHA_RECHAZO = "FECHA_RECHAZO";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_RECHAZO_INSUMO
     */
    public static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al alias de la columna CONTADOR en la tabla
     * FECET_RECHAZO_INSUMO
     */
    public static final String COLUMN_CONTADOR = "CONTADOR";

    /**
     * Metodo mapRow
     *
     * @param rs
     * @param rowNum
     * @return FeceaMetodo
     * @throws SQLException
     */
    public FecetContadorInsumosRechazados mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FecetContadorInsumosRechazados dto = new FecetContadorInsumosRechazados();

        dto.setIdRechazoInsumo(rs
                .getBigDecimal(COLUMN_ID_RECHAZO_INSUMO));
        dto.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        dto.setFechaRechazo(rs
                .getTimestamp(COLUMN_FECHA_RECHAZO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setContador(rs.getInt(COLUMN_CONTADOR));

        return dto;
    }
}
