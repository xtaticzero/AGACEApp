package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetDocumentosRechazoMapper implements ParameterizedRowMapper<FecetRechazoPropuesta> {

    /**
     * Este atributo corresponde al nombre de la columna ID_RECHAZO_PROPUESTA en
     * la tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_RECHAZO_PROPUESTA = "ID_RECHAZO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ARCHIVO en la
     * tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_DOC_RECHAZO_PROPUESTA
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    @Override
    public FecetRechazoPropuesta mapRow(ResultSet rs, int rowNum) throws SQLException {

        FecetRechazoPropuesta documentoRechazo = new FecetRechazoPropuesta();

        documentoRechazo.setIdRechazoPropuesta(rs.getBigDecimal(COLUMN_ID_RECHAZO_PROPUESTA));
        documentoRechazo.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        documentoRechazo.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        documentoRechazo.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        documentoRechazo.setFechaRechazo(rs.getDate(COLUMN_FECHA_CREACION));

        return documentoRechazo;
    }
}
