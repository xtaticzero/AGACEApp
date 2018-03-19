package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetBitacora;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetBitacoraMapper implements ParameterizedRowMapper<FecetBitacora> {

    /**
     * Este atributo corresponde al nombre de la columna ID_BITACORA en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_ID_BITACORA = "ID_BITACORA";

    /**
     * Este atributo corresponde al nombre de la columna ID_OPERACION en la
     * tabla FECET_BITACORA
     */
    private static final String COLUMN_ID_OPERACION = "ID_OPERACION";

    /**
     * Este atributo corresponde al nombre de la columna ID_INTERNO en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_ID_INTERNO = "ID_INTERNO";

    /**
     * Este atributo corresponde al nombre de la columna IPMAQUINA en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_IPMAQUINA = "IPMAQUINA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBREMAQUINA en la
     * tabla FECET_BITACORA
     */
    private static final String COLUMN_NOMBREMAQUINA = "NOMBREMAQUINA";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_RFC_USUARIO en
     * la tabla FECET_BITACORA
     */
    private static final String COLUMN_RFC_USUARIO = "RFC_USUARIO";

    /**
     * Este atributo corresponde al nombre de la columna
     * COLUMN_RFC_APODERADO_LEGAL en la tabla FECET_BITACORA
     */
    private static final String COLUMN_RFC_APODERADO_LEGAL = "RFC_APODERADO_LEGAL";

    /**
     * Este atributo corresponde al nombre de la columna COLUMN_RFC_AGENTE
     * ADUANAL en la tabla FECET_BITACORA
     */
    private static final String COLUMN_RFC_AGENTE_ADUANAL = "RFC_AGENTE_ADUANAL";

    /**
     * Este atributo corresponde al nombre de la columna
     * COLUMN_RFC_REPRESENTANTE_LEGAL en la tabla FECET_BITACORA
     */
    private static final String COLUMN_RFC_REPRESENTANTE_LEGAL = "RFC_REPRESENTANTE_LEGAL";

    /**
     * Este atributo corresponde al nombre de la columna FECHA en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_FECHA = "FECHA";

    /**
     * Este atributo corresponde al nombre de la columna DESCRIPCION en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO en la tabla
     * FECET_BITACORA
     */
    private static final String COLUMN_ID_REGISTRO = "ID_REGISTRO";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FecetBitacora mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetBitacora dto = new FecetBitacora();

        dto.setIdBitacora(rs.getBigDecimal(COLUMN_ID_BITACORA));
        dto.setIdOperacion(rs.getBigDecimal(COLUMN_ID_OPERACION));
        dto.setIdInterno(rs.getBigDecimal(COLUMN_ID_INTERNO));
        dto.setIpmaquina(rs.getString(COLUMN_IPMAQUINA));
        dto.setNombremaquina(rs.getString(COLUMN_NOMBREMAQUINA));
        dto.setRfcUsuario(rs.getString(COLUMN_RFC_USUARIO));
        dto.setRfcApoderadoLegal(rs.getString(COLUMN_RFC_APODERADO_LEGAL));
        dto.setRfcAgenteAduanal(rs.getString(COLUMN_RFC_AGENTE_ADUANAL));
        dto.setRfcRepresentanteLegal(rs.getString(COLUMN_RFC_REPRESENTANTE_LEGAL));
        dto.setFecha(rs.getTimestamp(COLUMN_FECHA));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setIdRegistro(rs.getString(COLUMN_ID_REGISTRO));

        return dto;
    }
}
