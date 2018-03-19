package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

public class FecetProrrogaOficioContadorDocsMapper extends FecetProrrogaOficioMapper {

    private static final String COLUMN_TOTAL_PRORROGA_DOCS = "TOTAL_DOC_PRORROGA_OFICIO";

    public FecetProrrogaOficio mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetProrrogaOficio prorroga = super.mapRow(rs, rowNum);
        if (prorroga.getIdAsociadoCarga() != null) {
            FecetAsociado asociado = new FecetAsociadoMapper().mapRow(rs, rowNum);
            prorroga.setFecetAsociado(asociado);
        } else {
            prorroga.setFecetAsociado(null);
        }
        prorroga.setFececEstatus(new FececEstatusMapper().mapRow(rs, rowNum));
        prorroga.setTotalDocumentosContribuyente(rs.getInt(COLUMN_TOTAL_PRORROGA_DOCS));

        return prorroga;
    }
}
