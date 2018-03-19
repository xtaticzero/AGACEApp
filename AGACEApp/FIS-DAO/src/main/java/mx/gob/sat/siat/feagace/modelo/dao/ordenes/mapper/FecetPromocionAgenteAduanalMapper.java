/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPromocionAgenteAduanal;

/**
 * @author sergio.vaca
 *
 */
public class FecetPromocionAgenteAduanalMapper implements ParameterizedRowMapper<FecetPromocionAgenteAduanal> {

    private static final String COLUMN_ID_PROMOCION_AGENTE_ADUANAL = "ID_PROMOCION_AGENTE_ADUANAL";
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";
    private static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_ID_ASOCIADO_CARGA = "ID_ASOCIADO_CARGA";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    private static final String COLUMN_TOTAL_DOCUMENTOS = "TOTAL_DOCUMENTOS";

    @Override
    public FecetPromocionAgenteAduanal mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetPromocionAgenteAduanal agente = new FecetPromocionAgenteAduanal();
        String rutaArchivo = rs.getString(COLUMN_RUTA_ARCHIVO);

        agente.setIdPromocionAgenteAduanal(rs.getBigDecimal(COLUMN_ID_PROMOCION_AGENTE_ADUANAL));
        agente.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        agente.setFechaCarga(rs.getTimestamp(COLUMN_FECHA_CARGA));
        agente.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rutaArchivo));
        agente.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(rutaArchivo));
        agente.setIdAsociadoCarga(rs.getBigDecimal(COLUMN_ID_ASOCIADO_CARGA));
        agente.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_TOTAL_DOCUMENTOS)) {
            agente.setContadorPruebasAlegatos(rs.getInt(COLUMN_TOTAL_DOCUMENTOS));
        }

        return agente;
    }
}
