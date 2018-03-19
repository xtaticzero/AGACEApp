package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetContribuyenteMapper implements ParameterizedRowMapper<FecetContribuyente> {

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_IMPUESTO
     */
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna RFC en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_RFC = "RFC";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_NOMBRE = "NOMBRE";

    /**
     * Este atributo corresponde al nombre de la columna TIPO en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_TIPO = "TIPO";

    /**
     * Este atributo corresponde al nombre de la columna SITUACION en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_SITUACION = "SITUACION";

    /**
     * Este atributo corresponde al nombre de la columna DOMICILIO_FISCAL en la
     * tabla FECET_IMPUESTO
     */
    private static final String COLUMN_DOMICILIO_FISCAL = "DOMICILIO_FISCAL";

    /**
     * Este atributo corresponde al nombre de la columna SITUACION_DOMICILIO en
     * la tabla FECET_IMPUESTO
     */
    private static final String COLUMN_SITUACION_DOMICILIO = "SITUACION_DOMICILIO";

    /**
     * Este atributo corresponde al nombre de la columna ENTIDAD en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_ENTIDAD = "ENTIDAD";

    /**
     * Este atributo corresponde al nombre de la columna REGIMEN en la tabla
     * FECET_IMPUESTO
     */
    private static final String COLUMN_REGIMEN = "REGIMEN";

    /**
     * Este atributo corresponde al nombre de la columna ACTIVIDAD_PREPONDERANTE
     * en la tabla FECET_IMPUESTO
     */
    private static final String COLUMN_ACTIVIDAD_PREPONDERANTE = "ACTIVIDAD_PREPONDERANTE";

    @Override
    public FecetContribuyente mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetContribuyente dto = new FecetContribuyente();

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_CONTRIBUYENTE)) {
            dto.setIdContribuyente(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC)) {
            dto.setRfc(rs.getString(COLUMN_RFC));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE)) {
            dto.setNombre(rs.getString(COLUMN_NOMBRE));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_TIPO)) {
            dto.setTipo(rs.getString(COLUMN_TIPO));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_SITUACION)) {
            dto.setSituacion(rs.getString(COLUMN_SITUACION));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DOMICILIO_FISCAL)) {
            dto.setDomicilioFiscal(rs.getString(COLUMN_DOMICILIO_FISCAL));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_SITUACION_DOMICILIO)) {
            dto.setSituacionDomicilio(rs.getString(COLUMN_SITUACION_DOMICILIO));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ENTIDAD)) {
            dto.setEntidad(rs.getString(COLUMN_ENTIDAD));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_REGIMEN)) {
            dto.setRegimen(rs.getString(COLUMN_REGIMEN));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ACTIVIDAD_PREPONDERANTE)) {
            dto.setActividadPreponderante(rs.getString(COLUMN_ACTIVIDAD_PREPONDERANTE));
        }

        return dto;
    }
}
