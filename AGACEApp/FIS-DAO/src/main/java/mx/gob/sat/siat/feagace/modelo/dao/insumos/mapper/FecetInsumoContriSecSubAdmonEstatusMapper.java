/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSectorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececSubprogramaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececSector;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececSubprograma;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public class FecetInsumoContriSecSubAdmonEstatusMapper extends FecetInsumoMapper {

    /**
     * Metodo mapRow Hace un mapeo y un set de los datos de la tabla
     * FECET_INSUMO y FECET_CONTRIBUYENTE
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetInsumo mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetInsumo insumo = super.mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececSector sector = new FececSectorMapper().mapRow(rs, rowNum);
        FececSubprograma subprograma = new FececSubprogramaMapper().mapRow(rs, rowNum);

        FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);

        insumo.setFecetContribuyente(contribuyente);

        insumo.setFececSector(sector);

        subprograma.setDescripcion(rs.getString("DESCRIPCION_SUBPROGRAMA"));
        insumo.setFececSubprograma(subprograma);

        estatus.setDescripcion(rs.getString("DESCRIPCION_ESTATUS"));
        insumo.setFececEstatus(estatus);

        if (UtileriasMapperDao.existeColumna(rs, "VALOR_PRIORIDAD")) {
            insumo.setValorPrioridad(rs.getString("VALOR_PRIORIDAD"));
        }
        if (UtileriasMapperDao.existeColumna(rs, "DESCRIPCION_PRIORIDAD")) {
            insumo.setDescripcionPrioridad(rs.getString("DESCRIPCION_PRIORIDAD"));
        }
        if (UtileriasMapperDao.existeColumna(rs, "JUSTIFICACION")) {
            insumo.setJustificacion(rs.getString("JUSTIFICACION"));
        }
        //
        if (UtileriasMapperDao.existeColumna(rs, "TIPO_INSUMO_DESC")) {
            insumo.setFececTipoInsumo(new FececTipoInsumo());
            insumo.getFececTipoInsumo().setIdTipoInsumo(UtileriasMapperDao.existeColumna(rs, "ID_TIPO_INSUMO")?BigDecimal.ZERO:rs.getBigDecimal("ID_TIPO_INSUMO"));
            insumo.getFececTipoInsumo().setDescripcion(rs.getString("TIPO_INSUMO_DESC"));
        }

        return insumo;
    }
}
