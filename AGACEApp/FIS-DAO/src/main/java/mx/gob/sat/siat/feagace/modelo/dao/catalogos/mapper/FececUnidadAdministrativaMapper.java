package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FececUnidadAdministrativaMapper implements
        ParameterizedRowMapper<FececUnidadAdministrativa> {

    private static final String COLUMN_ID_UNIDAD_ADMINISTRATIVA = "ID_UNIDAD_ADMINISTRATIVA";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public FececUnidadAdministrativa mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        FececUnidadAdministrativa unidadAdm = new FececUnidadAdministrativa();
        unidadAdm.setIdUnidadAdministrativa(rs.getBigDecimal(COLUMN_ID_UNIDAD_ADMINISTRATIVA));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE)) {
            unidadAdm.setNombre(rs.getString(COLUMN_NOMBRE));
            unidadAdm.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
            unidadAdm.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
            unidadAdm.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
            unidadAdm.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));
        }
        return unidadAdm;
    }
}
