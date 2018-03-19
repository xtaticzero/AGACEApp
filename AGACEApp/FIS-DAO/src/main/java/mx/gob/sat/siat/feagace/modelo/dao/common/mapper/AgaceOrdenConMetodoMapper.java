/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececEstatus;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;

public class AgaceOrdenConMetodoMapper extends AgaceOrdenMapper {

    @Override
    public AgaceOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        AgaceOrden orden = super.mapRow(rs, rowNum);
        FececMetodo metodo = new FeceaMetodoMapper().mapRow(rs, rowNum);
        FecetContribuyente contribuyente = new FecetContribuyenteMapper().mapRow(rs, rowNum);
        FececEstatus estatus = new FececEstatusMapper().mapRow(rs, rowNum);

        orden.setFeceaMetodo(metodo);

        try {
            contribuyente.setNombre(rs.getString("NOMBRE_CONTRIBUYENTE"));
            orden.setFecetContribuyente(contribuyente);
        } catch (Exception e) {
            orden.setFecetContribuyente(null);
        }

        orden.setFececEstatus(estatus);

        try {
            FecetAsociado asociado = new FecetAsociadoMapper().mapRow(rs, rowNum);
            orden.setFecetAsociado(asociado);
        } catch (Exception e) {
            orden.setFecetAsociado(null);
        }

        return orden;
    }
}
