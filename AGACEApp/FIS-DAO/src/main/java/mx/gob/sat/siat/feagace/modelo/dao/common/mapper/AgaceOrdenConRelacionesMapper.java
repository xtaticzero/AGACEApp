package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

public class AgaceOrdenConRelacionesMapper extends AgaceOrdenMapper {

    public AgaceOrden mapRow(ResultSet rs, int rowNum) throws SQLException {

        AgaceOrden orden = super.mapRow(rs, rowNum);

        if (orden.getIdContribuyente() != null) {
            try {
                FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
                orden.setFecetContribuyente(contribuyente);
            } catch (Exception e) {
                orden.setFecetContribuyente(null);
            }
        }

        return orden;
    }
}
