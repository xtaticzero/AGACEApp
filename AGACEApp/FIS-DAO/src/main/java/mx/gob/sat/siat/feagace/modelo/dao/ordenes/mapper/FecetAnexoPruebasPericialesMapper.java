package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexoPruebasPericiales;

public class FecetAnexoPruebasPericialesMapper implements ParameterizedRowMapper<FecetAnexoPruebasPericiales> {

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_ANEXO_PRUEBAS_PERICIALES en la tabla FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_ID_ANEXO_PRUEBAS_PERICIALES = "ID_ANEXO_PRUEBAS_PERICIALES";

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_FLUJO_PRUEBAS_PERICIALES en la tabla FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_ID_FLUJO_PRUEBAS_PERICIALES = "ID_FLUJO_PRUEBAS_PERICIALES";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna BLN_ACTIVO en la tabla
     * FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIN en la tabla
     * FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    /**
     * Este atributo corresponde al nombre de la columna TIPO_ANEXO en la tabla
     * FECET_ANEXO_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_TIPO_ANEXO = "TIPO_ANEXO";

    /**
     * Metodo mapRow Hace un mapeo de los datos en la tabla
     * FECET_ANEXO_PRUEBAS_PERICIALES
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetAnexoPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAnexoPruebasPericiales dto = new FecetAnexoPruebasPericiales();

        dto.setIdAnexoPruebasPericiales(rs.getBigDecimal(COLUMN_ID_ANEXO_PRUEBAS_PERICIALES));
        dto.setIdFlujoPruebasPericiales(rs.getBigDecimal(COLUMN_ID_FLUJO_PRUEBAS_PERICIALES));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));
        dto.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        dto.setTipoAnexo(rs.getString(COLUMN_TIPO_ANEXO));

        return dto;
    }

}
