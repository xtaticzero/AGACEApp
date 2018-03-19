package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.insumos.FececPrioridad;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececPrioridadMapper implements ParameterizedRowMapper<FececPrioridad> {

    private static final String COLUMN_ID_PRIORIDAD = "ID_PRIORIDAD";
    private static final String COLUMN_ID_GENERAL = "ID_GENERAL";
    private static final String COLUMN_VALOR = "VALOR";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public FececPrioridad mapRow(ResultSet resultSet, int i) throws SQLException {
        FececPrioridad fececPrioridad = new FececPrioridad();
        fececPrioridad.setIdPrioridad(resultSet.getBigDecimal(COLUMN_ID_PRIORIDAD));
        fececPrioridad.setValor(resultSet.getString(COLUMN_VALOR));
        fececPrioridad.setFechaInicio(resultSet.getDate(COLUMN_FECHA_INICIO));
        fececPrioridad.setFechaFin(resultSet.getDate(COLUMN_FECHA_FIN));
        fececPrioridad.setBlnActivo(resultSet.getBigDecimal(COLUMN_BLN_ACTIVO));

        if (UtileriasMapperDao.existeColumna(resultSet, COLUMN_ID_GENERAL)) {
            fececPrioridad.setIdGeneral(resultSet.getBigDecimal(COLUMN_ID_GENERAL));
        }
        if (UtileriasMapperDao.existeColumna(resultSet, COLUMN_DESCRIPCION)) {
            fececPrioridad.setDescripcion(resultSet.getString(COLUMN_DESCRIPCION));
        }
        return fececPrioridad;
    }
}
