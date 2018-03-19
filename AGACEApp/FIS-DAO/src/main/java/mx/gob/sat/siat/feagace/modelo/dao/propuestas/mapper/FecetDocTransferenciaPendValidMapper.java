package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocTransferenciaPendValidMapper implements ParameterizedRowMapper<FecetTransferencia> {

    /**
     * Este atributo corresponde al nombre de la columna ID_DOC_TRANSFERENCIA en
     * la tabla FECET_DOC_TRANSFERENCIA
     */
    static final String COLUMN_ID_DOC_TRANSFERENCIA = "ID_DOC_TRANSFERENCIA";

    /**
     * Este atributo corresponde al nombre de la columna ID_TRANSFERENCIA en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_ID_TRANSFERENCIA = "ID_TRANSFERENCIA";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_ARCHIVO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_NOMBRE_ARCHIVO = "NOMBRE_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_TRANSFERENCIA
     */
    static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    public FecetTransferencia mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetTransferencia dto = new FecetTransferencia();
        dto.setIdDocTransferencia(rs.getBigDecimal(COLUMN_ID_DOC_TRANSFERENCIA));
        dto.setIdTransferencia(rs.getBigDecimal(COLUMN_ID_TRANSFERENCIA));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        dto.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));

        return dto;
    }
}
