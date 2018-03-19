/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.DocumentoVista;

/**
 * @author Sergio.vaca
 *
 */
public class DocumentoVistaMapper implements ParameterizedRowMapper<DocumentoVista> {

    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    @Override
    public DocumentoVista mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentoVista docto = new DocumentoVista();
        docto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        String ruta = rs.getString(COLUMN_RUTA_ARCHIVO);
        docto.setRuta(UtileriasMapperDao.getPathFromAbsolutePath(ruta));
        docto.setNombre(UtileriasMapperDao.getNameFileFromPath(ruta));
        return docto;
    }

}
