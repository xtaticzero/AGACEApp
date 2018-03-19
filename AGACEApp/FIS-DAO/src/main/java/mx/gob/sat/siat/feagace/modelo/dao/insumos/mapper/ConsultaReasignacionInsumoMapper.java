package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumo;

public class ConsultaReasignacionInsumoMapper extends FecetInsumoMapper {

    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetInsumo insumo = super.mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
        FececSector sector = new FececSectorMapper().mapRow(rs, rowNum);
        FecetReasignacionInsumo reasignacionInsumo = new FecetReasignacionInsumo();

        reasignacionInsumo.setIdReasignacion(rs.getBigDecimal("ID_REASIGNACION"));
        reasignacionInsumo.setIdRegistroInsumo(rs.getString("ID_REGISTRO"));
        reasignacionInsumo.setIdInsumo(rs.getBigDecimal("ID_INSUMO"));
        reasignacionInsumo.setRfcAdministradorOrigen(rs.getString("RFC_ADMINISTRADOR_ORIGEN"));
        reasignacionInsumo.setRfcAdministradorDestino(rs.getString("RFC_ADMINISTRADOR_DESTINO"));
        reasignacionInsumo.setMotivo(rs.getString("MOTIVO"));
        reasignacionInsumo.setMotivoRechazo(rs.getString("MOTIVO_RECHAZO"));
        reasignacionInsumo.setEstatus(rs.getBigDecimal("ESTATUS"));
        reasignacionInsumo.setBlnActivo(rs.getInt("BLN_ACTIVO"));

        sector.setDescripcion(rs.getString("SECTOR_DESCRIPCION"));
        insumo.setFecetContribuyente(contribuyente);
        insumo.setFececSubprograma(subprograma);
        insumo.setFececSector(sector);
        insumo.setFecetReasignacionInsumo(reasignacionInsumo);

        return insumo;
    }
}
