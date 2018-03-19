package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;

public class FecetRetroalimentacionConMotivoMapper extends FecetRetroalimentacionMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetRetroalimentacion mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetRetroalimentacion retroalimentacion = super.mapRow(rs, rowNum);

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
