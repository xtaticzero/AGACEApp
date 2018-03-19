package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocTercero;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocTerceroMapper implements ParameterizedRowMapper<FecetDocTercero> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_TERCERO en la
     * tabla FECET_DOC_TERCERO
     */
    private static final String COLUMN_ID_DOC_TERCERO = "ID_DOC_TERCERO";

    /**
     * Este atributo corresponde al nombre de la columna ID_COMP_TERCERO en la
     * tabla FECET_DOC_TERCERO
     */
    private static final String COLUMN_ID_COMP_TERCERO = "ID_COMP_TERCERO";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_DOC_TERCERO
     */
    private static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_TERCERO
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_TERCERO
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CARGA en la tabla
     * FECET_DOC_TERCERO
     */
    private static final String COLUMN_RFC_CARGA = "RFC_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna ACUSE en la tabla
     * FECET_DOC_TERCERO
     */
    private static final String COLUMN_ACUSE = "ACUSE";

    /**
     * Method 'mapRow'
     *
     * @param rs
     * @param row
     * @throws SQLException
     * @return FecetDocTercero
     */
    public FecetDocTercero mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetDocTercero dto = new FecetDocTercero();

        dto.setIdDocTercero(rs.getBigDecimal(COLUMN_ID_DOC_TERCERO));
        dto.setIdCompTercero(rs.getBigDecimal(COLUMN_ID_COMP_TERCERO));
        dto.setNombreArchivo(rs.getString(COLUMN_NOMBRE_ARCHIVO));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        dto.setRfcCarga(rs.getString(COLUMN_RFC_CARGA));
        dto.setAcuse(rs.getString(COLUMN_ACUSE));

        return dto;
    }
}
