package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;

public class FecetPruebasPericialesContadorDocMapper extends FecetPruebasPericialesMapper {

    private static final String COLUMN_TOTAL_PRUEBAS_PERICIALES_DOCS = "TOTAL_DOC_PRUEBAS_PERICIALES";

    public FecetPruebasPericiales mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetPruebasPericiales pruebasPericiales = super.mapRow(rs, rowNum);
        pruebasPericiales.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));

        if (pruebasPericiales.getIdAsociadoCarga() != null) {
            FecetAsociado asociado = new FecetAsociadoMapper().mapRow(rs, rowNum);
            pruebasPericiales.setFecetAsociado(asociado);
        } else {
            pruebasPericiales.setFecetAsociado(null);
        }
        pruebasPericiales.setTotalDocumentosContribuyente(rs.getInt(COLUMN_TOTAL_PRUEBAS_PERICIALES_DOCS));
        return pruebasPericiales;
    }

}
