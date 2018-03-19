package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececTipoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececTipoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

public class ConsultaAsignaPropuestaMapper extends FecetPropuestaMapper {

    public FecetPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPropuesta propuesta = super.mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
        FececSector sector = new FececSectorMapper().mapRow(rs, rowNum);
        FececMetodo metodo = new FeceaMetodoMapper().mapRow(rs, rowNum);
        FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);
        FececTipoPropuesta tipoPropuesta = new FececTipoPropuestaMapper().mapRow(rs, rowNum);
        propuesta.setFecetContribuyente(contribuyente);
        propuesta.setFececSubprograma(subprograma);
        propuesta.setFececSector(sector);
        sector.setDescripcion(rs.getString("DESCRIPCION_SECTOR"));
        propuesta.setFececSubprograma(subprograma);
        propuesta.setFececSector(sector);
        propuesta.setFeceaMetodo(metodo);
        propuesta.setFececEstatus(estatus);
        estatus.setDescripcion(rs.getString("DESCRIPCION_ESTATUS"));
        propuesta.setFececEstatus(estatus);
        propuesta.setPresuntiva(rs.getBigDecimal("PRESUNTIVA"));
        propuesta.setFececTipoPropuesta(tipoPropuesta);
        tipoPropuesta.setDescripcion(rs.getString("DESCRIPCION_TIPO_PROPUESTA"));
        propuesta.setFececTipoPropuesta(tipoPropuesta);
        propuesta.setPrioridadSugerida(rs.getString("PRIORIDAD"));

        return propuesta;
    }
}
