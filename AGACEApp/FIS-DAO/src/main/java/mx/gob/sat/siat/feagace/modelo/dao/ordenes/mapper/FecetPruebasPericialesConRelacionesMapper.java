package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public class FecetPruebasPericialesConRelacionesMapper extends FecetPruebasPericialesMapper {

    public FecetPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetPruebasPericiales pruebasPericiales = super.mapRow(rs, rowNum);

        if (pruebasPericiales.getIdAsociadoCarga() != null) {
            pruebasPericiales.setFecetAsociado(new FecetAsociadoMapper().mapRow(rs, rowNum));
        }
        pruebasPericiales.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));

        pruebasPericiales.setTotalDocumentosContribuyente(rs.getInt("TOTAL_DOCUMENTOS"));

        pruebasPericiales.setTotalDocumentosRechazo(rs.getInt("TOTAL_DOCUMENTOS_RECHAZO"));

        if (rs.getString("ID_FLUJO_PRUEBAS_PERICIALES") != null) {
            FecetFlujoPruebasPericiales flujo = new FecetFlujoPruebasPericialesMapper().mapRow(rs, rowNum);
            flujo.setIdEstatus(rs.getBigDecimal("ESTATUS_FLUJO"));
            pruebasPericiales.setFlujoPruebasPericiales(flujo);
        }
        return pruebasPericiales;
    }

}
