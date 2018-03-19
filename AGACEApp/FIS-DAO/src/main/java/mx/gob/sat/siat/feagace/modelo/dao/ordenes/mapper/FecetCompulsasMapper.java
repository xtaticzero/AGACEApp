package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetCompulsasMapper implements ParameterizedRowMapper<FecetCompulsas> {

    /**
     * Este atributo corresponde al nombre de la columna ID_COMPULSA en la tabla
     * FECET_COMPULSAS
     */
    public static final String COLUMN_ID_COMPULSA = "ID_COMPULSA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN_AUDITADA en la
     * tabla FECET_COMPULSAS
     */
    public static final String COLUMN_ID_ORDEN_AUDITADA = "ID_ORDEN_AUDITADA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN_COMPULSA en la
     * tabla FECET_COMPULSAS
     */
    public static final String COLUMN_ID_ORDEN_COMPULSA = "ID_ORDEN_COMPULSA";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_CONTRIBUYENTE_COMPULSADO en la tabla FECET_COMPULSAS
     */
    public static final String COLUMN_ID_CONTRIBUYENTE_COMPULSADO = "ID_CONTRIBUYENTE_COMPULSADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_ASOCIADO en la tabla
     * FECET_COMPULSAS
     */
    public static final String COLUMN_ID_ASOCIADO = "ID_ASOCIADO";
    ;

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla FECET_COMPULSAS
     */
    public static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna ID_OFICIO en la tabla
     * FECET_COMPULSAS
     */
    public static final String COLUMN_ID_OFICIO = "ID_OFICIO";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_COMPULSA en la
     * tabla FECET_COMPULSAS
     */
    public static final String COLUMN_TIPO_COMPULSA = "TIPO_COMPULSA";

    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Metodo mapRow Hacde un mapeo de los datos en la tabla FECET_COMPULSAS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetCompulsas mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetCompulsas dto = new FecetCompulsas();

        dto.setIdCompulsa(rs.getBigDecimal(COLUMN_ID_COMPULSA));
        dto.setIdOrdenAuditada(rs.getBigDecimal(COLUMN_ID_ORDEN_AUDITADA));
        dto.setIdOrdenCompulsa(rs.getBigDecimal(COLUMN_ID_ORDEN_COMPULSA));
        dto.setIdContribuyenteCompulsado(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE_COMPULSADO));
        dto.setIdAsociado(rs.getBigDecimal(COLUMN_ID_ASOCIADO));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setTipoCompulsa(rs.getString(COLUMN_TIPO_COMPULSA));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        return dto;
    }

}
