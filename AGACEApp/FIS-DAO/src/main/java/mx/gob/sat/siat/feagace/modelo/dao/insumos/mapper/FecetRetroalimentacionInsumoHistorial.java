package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

public class FecetRetroalimentacionInsumoHistorial extends FecetRetroalimentacionInsumoMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRetroalimentacionInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetRetroalimentacionInsumo retroalimentacion = super.mapRow(rs, rowNum);

        retroalimentacion.setDescripcionSector(rs.getString("DESC_SECTOR"));
        retroalimentacion.setDescripcionSubprograma(rs.getString("DESC_SUBPROGRAMA"));
        retroalimentacion.setNumeroSolicitudes(rs.getBigDecimal("SOLICITUDES"));
        retroalimentacion.setValorPrioridad(rs.getString("VALOR_PRIORIDAD"));
        return retroalimentacion;
    }

}
