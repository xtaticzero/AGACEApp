package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public class FecetProrrogaOficioConRelacionesMapper extends FecetProrrogaOficioMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetProrrogaOficio mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetProrrogaOficio prorroga = super.mapRow(rs, rowNum);

        if (prorroga.getIdAsociadoCarga() != null) {
            prorroga.setFecetAsociado(new FecetAsociadoMapper().mapRow(rs, rowNum));
        }
        prorroga.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));

        prorroga.setTotalDocumentosContribuyente(rs.getInt("TOTAL_DOCUMENTOS"));

        prorroga.setTotalDocumentosRechazo(rs.getInt("TOTAL_DOCUMENTOS_RECHAZO"));

        if (rs.getString("ID_FLUJO_PRORROGA_OFICIO") != null) {
            FecetFlujoProrrogaOficio flujo = new FecetFlujoProrrogaOficioMapper().mapRow(rs, rowNum);
            flujo.setIdEstatus(rs.getBigDecimal("ESTATUS_FLUJO"));
            prorroga.setFecetFlujoProrrogaOficio(flujo);
        }
        return prorroga;
    }

}
