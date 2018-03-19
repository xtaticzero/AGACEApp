package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;

public class FecetRetroalimentacionInsumoConMotivoMapper extends FecetRetroalimentacionInsumoMapper {

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

        if (retroalimentacion.getIdMotivo() != null) {
            try {
                FececMotivo motivo = new FececMotivoMapper().mapRow(rs, rowNum);
                motivo.setDescripcion(rs.getString("DESCRIPCION_MOTIVO"));
                retroalimentacion.setFececMotivo(motivo);
            } catch (Exception e) {
                retroalimentacion.setFececMotivo(null);
            }
        }

        return retroalimentacion;
    }

}
