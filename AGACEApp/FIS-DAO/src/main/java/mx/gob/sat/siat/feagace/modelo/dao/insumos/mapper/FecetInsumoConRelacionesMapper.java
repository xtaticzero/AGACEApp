package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class FecetInsumoConRelacionesMapper extends FecetInsumoMapper {

    /**
     * Metodo mapRow Hace un mapeo de los datos en el contador
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetInsumo insumo = super.mapRow(rs, rowNum);

        if (insumo.getIdSubprograma() != null) {
            try {
                FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
                subprograma.setDescripcion(rs.getString("DESCRIPCION_SUBPROGRAMA"));
                insumo.setFececSubprograma(subprograma);
            } catch (Exception e) {
                insumo.setFececSubprograma(null);
            }
        }

        if (insumo.getIdContribuyente() != null) {
            try {
                FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
                insumo.setFecetContribuyente(contribuyente);
            } catch (Exception e) {
                insumo.setFecetContribuyente(null);
            }
        }

        if (insumo.getIdEstatus() != null) {
            try {
                FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);
                estatus.setDescripcion(rs.getString("DESCRIPCION_ESTATUS"));
                insumo.setFececEstatus(estatus);
            } catch (Exception e) {
                insumo.setFececEstatus(null);
            }

        }

        if (insumo.getIdSector() != null) {
            try {
                FececSector fececSector = new FececSectorMapper().mapRow(rs, rowNum);
                fececSector.setDescripcion(rs.getString("DESCRIPCION_SECTOR"));
                insumo.setFececSector(fececSector);
            } catch (Exception e) {
                insumo.setFececSector(null);
            }
        }

        return insumo;
    }

}
