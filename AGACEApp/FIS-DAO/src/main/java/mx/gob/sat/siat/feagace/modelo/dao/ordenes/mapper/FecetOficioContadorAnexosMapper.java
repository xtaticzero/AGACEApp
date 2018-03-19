package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;

public class FecetOficioContadorAnexosMapper extends FecetOficioMapper {

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

        oficio.setTotalOficiosDependientes(rs.getInt("TOTAL_OFICIOS_DEPENDIENTES"));
        oficio.setTotalAnexosOficio(rs.getInt("TOTAL_ANEXOS_OFICIO"));

        return oficio;
    }

}
