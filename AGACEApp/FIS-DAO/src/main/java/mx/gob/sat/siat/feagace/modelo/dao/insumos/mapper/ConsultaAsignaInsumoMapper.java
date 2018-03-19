package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;

public class ConsultaAsignaInsumoMapper extends FecetInsumoMapper {

    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetInsumo insumo = super.mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
        FececSector sector = new FececSectorMapper().mapRow(rs, rowNum);
        FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);
        insumo.setFecetContribuyente(contribuyente);
        insumo.setFececSubprograma(subprograma);
        insumo.setFececSector(sector);
        sector.setDescripcion(rs.getString("DESCRIPCION_SECTOR"));
        insumo.setFececSubprograma(subprograma);
        insumo.setFececSector(sector);
        insumo.setFececEstatus(estatus);
        FecetReasignacionInsumo reasignacion = new FecetReasignacionInsumo();
        reasignacion.setMotivoRechazo(rs.getString("MOTIVO_RECHAZO"));
        insumo.setFecetReasignacionInsumo(reasignacion);
        if (insumo.getIdArace() != null) {
            try {
                FececUnidadAdministrativa fececUnidadAdministrativa = new FececUnidadAdministrativa();
                fececUnidadAdministrativa.setIdUnidadAdministrativa(rs.getBigDecimal("ID_UNIDAD_ADMINISTRATIVA"));
                fececUnidadAdministrativa.setNombre(rs.getString("NOMBRE_UNIDAD"));
                fececUnidadAdministrativa.setDescripcion(rs.getString("UNIDAD_ADM_DESC"));
                insumo.setFececUnidadAdministrativa(fececUnidadAdministrativa);
            } catch (Exception e) {
                insumo.setFececUnidadAdministrativa(new FececUnidadAdministrativa());
            }
        }

        return insumo;
    }
}
