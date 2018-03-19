package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocProrrogaOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocProrrogaOficioMapper implements ParameterizedRowMapper<FecetDocProrrogaOficio> {

    private static final String COLUMN_ID_DOC_PRORROGA_OFICIO = "ID_DOC_PRORROGA_OFICIO";
    private static final String COLUMN_ID_PRORROGA_OFICIO = "ID_PRORROGA_OFICIO";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    @Override
    public FecetDocProrrogaOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetDocProrrogaOficio dto = new FecetDocProrrogaOficio();

        dto.setIdDocProrrogaOficio(rs.getBigDecimal(COLUMN_ID_DOC_PRORROGA_OFICIO));
        dto.setIdProrrogaOficio(rs.getBigDecimal(COLUMN_ID_PRORROGA_OFICIO));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        return dto;
    }
}
