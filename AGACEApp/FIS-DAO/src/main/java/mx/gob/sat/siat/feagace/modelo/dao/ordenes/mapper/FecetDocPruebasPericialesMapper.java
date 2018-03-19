package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocPruebasPericiales;

public class FecetDocPruebasPericialesMapper implements ParameterizedRowMapper<FecetDocPruebasPericiales> {

    /**
     * Este atributo corresponde al nombre de la columna
     * ID_DOC_PRUEBAS_PERICIALES en la tabla FECET_DOC_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_ID_DOC_PRUEBAS_PERICIALES = "ID_DOC_PRUEBAS_PERICIALES";

    /**
     * Este atributo corresponde al nombre de la columna ID_PRUEBAS_PERICIALES
     * en la tabla FECET_DOC_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_ID_PRUEBAS_PERICIALES = "ID_PRUEBAS_PERICIALES";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_DOC_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_PRUEBAS_PERICIALES
     */
    public static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Metodo mapRow Hace un mapeo de los datos en la tabla
     * FECET_DOC_PRUEBAS_PERICIALES
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetDocPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocPruebasPericiales dto = new FecetDocPruebasPericiales();

        dto.setIdDocPruebasPericiales(rs.getBigDecimal(COLUMN_ID_DOC_PRUEBAS_PERICIALES));
        dto.setIdPruebasPericiales(rs.getBigDecimal(COLUMN_ID_PRUEBAS_PERICIALES));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));

        return dto;
    }

}
