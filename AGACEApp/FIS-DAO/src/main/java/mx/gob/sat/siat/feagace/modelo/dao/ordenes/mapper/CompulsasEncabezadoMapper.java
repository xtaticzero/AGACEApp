package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class CompulsasEncabezadoMapper implements ParameterizedRowMapper<FecetCompulsas> {

    public CompulsasEncabezadoMapper() {
        super();
    }

    /**
     * SELECT C.RFC, C.NOMBRE, B.RFC RFC_RL, B.NOMBRE NOMBRE_RL, B.CORREO
     * CORREO_RL \n" + "from FECET_COMPULSAS A, FECET_ASOCIADO B,
     * FECET_CONTRIBUYENTE C\n" + "WHERE B.ID_ASOCIADO = A.ID_ASOCIADO\n" + "AND
     * C.ID_CONTRIBUYENTE = A.ID_CONTRIBUYENTE_COMPULSADO\n" + "AND A.ID_OFICIO
     * = ?
     *
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public FecetCompulsas mapRow(ResultSet resultSet, int i) throws SQLException {
        FecetCompulsas dto = new FecetCompulsas();
        dto.setRfcTerceroCompulsado(resultSet.getString("RFC"));
        dto.setNombreTerceroCompulsado(resultSet.getString("NOMBRE"));
        dto.setRfcAsociado(resultSet.getString("RFC_RL"));
        dto.setNombreAsociado(resultSet.getString("NOMBRE_RL"));
        dto.setCorreoAsociado(resultSet.getString("CORREO_RL"));
        dto.setIdContribuyenteCompulsado(resultSet.getBigDecimal("ID_CONTRIBUYENTE_COMPULSADO"));
        return dto;
    }
}
