package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ProgramadorPropuestaAsignadaDTO;

import org.apache.log4j.Logger;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ProgramadorPropuestaAsignadaMapper implements ParameterizedRowMapper<ProgramadorPropuestaAsignadaDTO> {

    private static final Logger LOG = Logger.getLogger(ProgramadorPropuestaAsignadaMapper.class);

    @Override
    public ProgramadorPropuestaAsignadaDTO mapRow(ResultSet resultSet, int i) {
        ProgramadorPropuestaAsignadaDTO programadorPropuestaAsignadaDTO = new ProgramadorPropuestaAsignadaDTO();
        try {
            programadorPropuestaAsignadaDTO.setIdEmpleado(resultSet.getBigDecimal("ID_PROGRAMADOR"));
            programadorPropuestaAsignadaDTO.setTotalPropuestas(resultSet.getInt("NUMERO"));
        } catch (SQLException e) {
            LOG.error("Error al obtener los datos de propuestas asignadas a programador");
        }
        return programadorPropuestaAsignadaDTO;
    }
}
