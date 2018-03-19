package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.ConsultaInformeComiteRechazoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ConsultaInformeComiteMapper extends ConsultaInformeComiteRechazoMapper implements ParameterizedRowMapper<ConsultaInformeComiteRechazoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna RFC_CREACION en la
     * tabla FECET_PROPUESTA
     */
    private static final String COLUMN_PROGRAMADOR_SUBADMINISTRADOR = "RFC_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_EXPEDIENTE
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    public ConsultaInformeComiteRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        ConsultaInformeComiteRechazoPropuesta dto = super.mapRow(rs, rowNum);
        dto.setRfcCreacion(rs.getString(COLUMN_PROGRAMADOR_SUBADMINISTRADOR));

        FecetDocExpediente expediente = new FecetDocExpediente();
        expediente.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        String ruta = expediente.getRutaArchivo();
        String[] divisionRuta = ruta.split("/");
        int indice = divisionRuta.length;
        expediente.setNombre(divisionRuta[--indice]);
        dto.setExpediente(expediente);
        return dto;
    }

}
