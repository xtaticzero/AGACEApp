/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetAlegatoAgenteAduanal;

public class FecetAlegatoAgenteAduanalMapper implements ParameterizedRowMapper<FecetAlegatoAgenteAduanal> {

    private static final String COLUMN_ID_ALEGATO_AGENTE_ADUANAL = "ID_ALEGATO_AGENTE_ADUANAL";
    private static final String COLUMN_ID_PROMOCION_AGENTE_ADUANAL = "ID_PROMOCION_AGENTE_ADUANAL";
    private static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    public FecetAlegatoAgenteAduanal mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetAlegatoAgenteAduanal dto = new FecetAlegatoAgenteAduanal();

        dto.setIdAlegatoAgenteAduanal(rs.getBigDecimal(COLUMN_ID_ALEGATO_AGENTE_ADUANAL));
        dto.setIdPromocionAgeneteAduanal(rs.getBigDecimal(COLUMN_ID_PROMOCION_AGENTE_ADUANAL));
        dto.setFechaCarga(rs.getTimestamp(COLUMN_FECHA_CARGA));
        String ruta = rs.getString(COLUMN_RUTA_ARCHIVO);
        dto.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(ruta));
        dto.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(ruta));
        dto.setFechaFin(rs.getTimestamp(COLUMN_FECHA_FIN));
        dto.setBlnActivo(rs.getShort(COLUMN_BLN_ACTIVO));

        return dto;
    }
}
