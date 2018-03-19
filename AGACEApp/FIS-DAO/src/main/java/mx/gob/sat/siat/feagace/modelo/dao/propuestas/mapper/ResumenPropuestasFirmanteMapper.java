package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ResumenPropuestasFirmante;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ResumenPropuestasFirmanteMapper implements ParameterizedRowMapper<ResumenPropuestasFirmante> {

    @Override
    public ResumenPropuestasFirmante mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResumenPropuestasFirmante resumenPropuestas = new ResumenPropuestasFirmante();

        resumenPropuestas.setTotalCanceladasValidar(rs.getLong("CANCELADAS_VALIDAR"));
        resumenPropuestas.setTotalCanceladasNoAprobadas(rs.getLong("CANCELADADAS_NO_APROBAR"));
        resumenPropuestas.setTotalCanceladasRetroalimentadas(rs.getLong("CANCELADAS_EMISION"));

        resumenPropuestas.setTotalRechazadasValidar(rs.getLong("RECHAZADAS_VALIDAR"));
        resumenPropuestas.setTotalRechazadasNoAprobadas(rs.getLong("RECHAZADAS_NO_APROBAR"));
        resumenPropuestas.setTotalRechazadasRetroalimentadas(rs.getLong("RECHAZADAS_EMISION"));

        resumenPropuestas.setTotalTransferidasValidar(rs.getLong("TRANSFERIDAS_VALIDAR"));
        resumenPropuestas.setTotalTransferidasNoAprobadas(rs.getLong("TRANSFERIDAS_NO_APROBAR"));
        resumenPropuestas.setTotalTransferidasRetroalimentadas(rs.getLong("TRANSFERIDAS_EMISION"));

        resumenPropuestas.setTotalRetroalimentadasValidar(rs.getLong("RETROALIMENTADAS_VALIDAR"));
        resumenPropuestas.setTotalRetroalimentadasNoAprobadas(rs.getLong("RETROALIMENTADAS_NO_APROBAR"));
        resumenPropuestas.setTotalRetroalimentadas(rs.getLong("RETROALIMENTADAS_EMISION"));
        resumenPropuestas.setTotalRetroalimentadasInformadas(rs.getLong("RETROALIMENTADAS_INFORMADAS"));

        resumenPropuestas.setTotalRevisionNoAprobadas(rs.getLong("REVISION_NO_APROBADA"));
        resumenPropuestas.setTotalRevisionRetroalimentadas(rs.getLong("REVISION_EMISION"));
        resumenPropuestas.setTotalRevisionInformadas(rs.getLong("REVISION_INFORMADA"));

        return resumenPropuestas;
    }

}
