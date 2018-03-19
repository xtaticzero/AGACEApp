package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;

public class FecetTipoOficioMapper implements ParameterizedRowMapper<FecetTipoOficio> {

    private static final String COLUMN_ID_TIPO_OFICIO = "ID_TIPO_OFICIO";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_ID_AGRUPADOR = "ID_AGRUPADOR_TIPO_OFICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";

    @Override
    public FecetTipoOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetTipoOficio tipoOficio = new FecetTipoOficio();
        tipoOficio.setIdTipoOficio(rs.getBigDecimal(COLUMN_ID_TIPO_OFICIO));
        tipoOficio.setNombre(rs.getString(COLUMN_NOMBRE));
        tipoOficio.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        tipoOficio.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_AGRUPADOR)) {
            tipoOficio.setAgrupador(AgrupadorOficiosEnum.parse(rs.getInt(COLUMN_ID_AGRUPADOR)));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA_FIN)) {
            tipoOficio.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_ACTIVO)) {
            tipoOficio.setBlnActivo(rs.getBigDecimal(COLUMN_BLN_ACTIVO));
        }

        return tipoOficio;
    }
}
