package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.cifras;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocCifraDTO;

public class FecetDocCifraHistoricoMapper implements ParameterizedRowMapper<FecetDocCifraDTO> {

    public static final String COLUMN_ID_DOC_CIFRA = "ID_DOC_CIFRA_HISTORICO";

    public static final String COLUMN_ID_CIFRA_IMPUESTO = "ID_CIFRA_IMPUESTO";

    public static final String RUTA_ARCHIVO = "RUTA_ARCHIVO";

    public static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    public static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    @Override
    public FecetDocCifraDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetDocCifraDTO docCifra = new FecetDocCifraDTO();
        docCifra.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        docCifra.setFechaFinal(rs.getDate(COLUMN_FECHA_FIN));
        docCifra.setRutaArchivo(rs.getString(RUTA_ARCHIVO));
        docCifra.setIdDocumentoCifra(rs.getBigDecimal(COLUMN_ID_DOC_CIFRA));
        docCifra.setNombre(UtileriasMapperDao.getNameFileFromPath((rs.getString(RUTA_ARCHIVO))));
        return docCifra;
    }

}
