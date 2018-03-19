package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;

public class FecetOficioConRelacionesMapper extends FecetOficioMapper {

    private static final String COLUMN_TOTAL_ANEXOS_OFICIO = "TOTAL_ANEXOS_OFICIO";
    private static final String COLUMN_TOTAL_OFICIOS_DEPENDIENTES = "TOTAL_OFICIOS_DEPENDIENTES";
    private static final String COLUMN_NOMBRE_COMPULSADO = "NOMBRE_COMPULSADO";
    private static final String COLUMN_RFC_COMPULSADO = "RFC_COMPULSADO";

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetOficio mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetOficio oficio = super.mapRow(rs, rowNum);

        FecetTipoOficio fecetTipoOficio = new FecetTipoOficioMapper().mapRow(rs, rowNum);

        oficio.setFecetTipoOficio(fecetTipoOficio);

        oficio.setTotalOficiosDependientes(rs.getInt(COLUMN_TOTAL_OFICIOS_DEPENDIENTES));
        oficio.setTotalAnexosOficio(rs.getInt(COLUMN_TOTAL_ANEXOS_OFICIO));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NOMBRE_COMPULSADO)) {
            oficio.setNombreCompulsado(rs.getString(COLUMN_NOMBRE_COMPULSADO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_RFC_COMPULSADO)) {
            oficio.setRfcCompulsado(rs.getString(COLUMN_RFC_COMPULSADO));
        }

        return oficio;
    }

}
