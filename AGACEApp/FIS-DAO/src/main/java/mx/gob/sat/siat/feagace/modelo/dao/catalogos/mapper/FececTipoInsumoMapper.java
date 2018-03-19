/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececTipoInsumo;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author jose.aguilar
 */
public class FececTipoInsumoMapper implements ParameterizedRowMapper<FececTipoInsumo> {

    private static final String COLUMN_ID_TIPO_INSUMO = "ID_TIPO_INSUMO";
    private static final String COLUMN_DESCRIPCION = "DESCRIPCION";
    //COLUMN_ID_GENERAL  "ID_GENERAL"

    @Override
    public FececTipoInsumo mapRow(ResultSet rs, int i) throws SQLException {
        FececTipoInsumo dto = new FececTipoInsumo();
        dto.setIdTipoInsumo(rs.getBigDecimal(COLUMN_ID_TIPO_INSUMO));
        dto.setDescripcion(rs.getString(COLUMN_DESCRIPCION));
        dto.setIdGeneral(rs.getBigDecimal(COLUMN_ID_TIPO_INSUMO));
        return dto;
    }
}
