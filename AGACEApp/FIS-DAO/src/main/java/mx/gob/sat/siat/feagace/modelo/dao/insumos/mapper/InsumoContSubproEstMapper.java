package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class InsumoContSubproEstMapper extends FecetInsumoMapper {

    private static final Logger LOGGER = Logger.getLogger(InsumoContSubproEstMapper.class);

    private static final String COLUMN_VALOR_PRIORIDAD = "VALOR_PRIORIDAD";
    private static final String COLUMN_DESCRIPCION_PRIORIDAD = "DESCRIPCION_PRIORIDAD";
    /**
     * Metodo mapRow Hace un mapeo y un set de los datos de la tabla
     * FECET_INSUMO, FECET_CONTRIBUYENTE, FECECSUBPROGRAMA Y FECECESTATUS
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetInsumo insumo = super.mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);
        FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);
        FececSector sector = new FececSectorMapper().mapRow(rs, rowNum);
        insumo.setFecetContribuyente(contribuyente);
        insumo.setFececSubprograma(subprograma);
        try {
            estatus.setDescripcion(rs.getString("DESCRIPCION_ESTATUS"));
            sector.setDescripcion(rs.getString("DESCRIPCION_SECTOR"));
        } catch (Exception e) {
            LOGGER.error("Error setear el elemento");
        }
        insumo.setFececEstatus(estatus);
        insumo.setFececSector(sector);
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_VALOR_PRIORIDAD)) {
            insumo.setValorPrioridad(rs.getString(COLUMN_VALOR_PRIORIDAD));
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_PRIORIDAD)) {
            insumo.setDescripcionPrioridad(rs.getString(COLUMN_DESCRIPCION_PRIORIDAD));
        }
        return insumo;
    }
}
