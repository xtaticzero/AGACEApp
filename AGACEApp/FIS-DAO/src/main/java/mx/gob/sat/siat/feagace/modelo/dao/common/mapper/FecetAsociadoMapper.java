package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetAsociadoMapper implements ParameterizedRowMapper<FecetAsociado> {

    /**
     * Este atributo corresponde al nombre de la columna ID_EMPLEADO en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_ID_ASOCIADO = "ID_ASOCIADO";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_ASOCIADOS
     */
    private static final String COLUMN_RFC_CONTRIBUYENTE = "RFC_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_TIPO_ASOCIADO en la
     * tabla FECET_ASOCIADOS
     */
    private static final String COLUMN_ID_TIPO_ASOCIADO = "ID_TIPO_ASOCIADO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna CORREO en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_CORREO = "CORREO";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_ASOCIADO en la
     * tabla FECET_ASOCIADOS
     */
    private static final String COLUMN_TIPO_ASOCIADO = "TIPO_ASOCIADO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_ASOCIADOS
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_ULTIMA_MOD en la
     * tabla FECET_ASOCIADOS
     */
    private static final String COLUMN_FECHA_ULTIMA_MOD = "FECHA_ULTIMA_MOD";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_ULTIMA_MOD_IDC en
     * la tabla FECET_ASOCIADOS
     */
    private static final String COLUMN_FECHA_ULTIMA_MOD_IDC = "FECHA_ULTIMA_MOD_IDC";

    private static final String COLUMN_MEDIO_CONTACTO = "MEDIO_CONTACTO";

    private static final String COLUMN_ESTATUS = "ESTATUS";

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de la tabla
     * FECET_ASOCIADOS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetAsociado mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAsociado dto = new FecetAsociado();
        dto.setIdAsociado(rs.getBigDecimal(COLUMN_ID_ASOCIADO));
        dto.setRfcContribuyente(rs.getString(COLUMN_RFC_CONTRIBUYENTE));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setIdTipoAsociado(rs.getBigDecimal(COLUMN_ID_TIPO_ASOCIADO));
        dto.setNombre(rs.getString(COLUMN_NOMBRE));
        dto.setRfc(rs.getString(COLUMN_RFC));
        dto.setCorreo(rs.getString(COLUMN_CORREO));
        dto.setTipoAsociado(rs.getString(COLUMN_TIPO_ASOCIADO));
        dto.setMedioContacto(rs.getString(COLUMN_MEDIO_CONTACTO) == null ? "0" : rs.getString(COLUMN_MEDIO_CONTACTO));
        dto.setFechaBaja(rs.getTimestamp(COLUMN_FECHA_BAJA));
        dto.setFechaUltimaMod(rs.getTimestamp(COLUMN_FECHA_ULTIMA_MOD));
        dto.setFechaUltimaModIdc(rs.getTimestamp(COLUMN_FECHA_ULTIMA_MOD_IDC));
        dto.setEstatus(rs.getString(COLUMN_ESTATUS));
        if (dto.getMedioContacto().equals("0")) {
            dto.setMedioContactoBoolean(false);
        } else {
            dto.setMedioContactoBoolean(true);
        }
        return dto;
    }
}
