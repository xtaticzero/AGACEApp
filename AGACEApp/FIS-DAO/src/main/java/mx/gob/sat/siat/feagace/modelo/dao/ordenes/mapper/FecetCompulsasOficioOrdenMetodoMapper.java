package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceOrdenConMetodoMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;

public class FecetCompulsasOficioOrdenMetodoMapper extends FecetCompulsasMapper {

    public static final String COLUMN_ID_ESTATUS_COMPULSA = "ID_ESTATUS_COMPULSA";
    public static final String COLUMN_ID_ESTATUS_ORDEN = "ID_ESTATUS_ORDEN";
    public static final String COLUMN_ID_ESTATUS_OFICIO = "ID_ESTATUS_OFICIO";
    public static final String COLUMN_ID_OFICIO_COMPULSA = "ID_OFICIO_COMPULSA";
    public static final String COLUMN_ID_OFICIO_ORDEN = "ID_OFICIO_ORDEN";
    public static final String COLUMN_NOMBRE_TIPO = "NOMBRE_TIPO";

    @Override
    public FecetCompulsas mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetCompulsas compulsa = super.mapRow(rs, rowNum);
        FecetOficio oficio = new FecetOficioMapper().mapRow(rs, rowNum);
        AgaceOrden orden = new AgaceOrdenConMetodoMapper().mapRow(rs, rowNum);

        compulsa.setOrden(orden);
        compulsa.setOficio(oficio);

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_ESTATUS_COMPULSA)) {
            compulsa.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS_COMPULSA));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_ESTATUS_ORDEN)) {
            compulsa.getOrden().setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS_ORDEN));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_ESTATUS_OFICIO)) {
            compulsa.getOficio().setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS_OFICIO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_OFICIO_COMPULSA)) {
            compulsa.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO_COMPULSA));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_OFICIO_ORDEN)) {
            compulsa.getOficio().setIdOrden(rs.getBigDecimal(COLUMN_ID_OFICIO_ORDEN));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE_TIPO)) {
            FecetTipoOficio tipo = new FecetTipoOficioMapper().mapRow(rs, rowNum);
            tipo.setNombre(rs.getString(COLUMN_NOMBRE_TIPO));
            compulsa.getOficio().setFecetTipoOficio(tipo);
        }

        return compulsa;
    }
}
