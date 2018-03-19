package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ClaveFolioOficioMapper implements ParameterizedRowMapper<ClaveFolioOficioDTO> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ARACE.
     */
    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE_ADMON_GRAL.
     */
    private static final String COLUMN_CLAVE_ADMON_GRAL = "CLAVE_ADMON_GRAL";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE_ADMIN_AREA.
     */
    private static final String COLUMN_CLAVE_ADMIN_AREA = "CLAVE_ADMIN_AREA";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE_SUBADMIN_AREA.
     */
    private static final String COLUMN_CLAVE_SUBADMIN_AREA = "CLAVE_SUBADMIN_AREA";

    /**
     * Este atributo corresponde al nombre de la columna CLAVE_JEFACTURA_DEPTO.
     */
    private static final String COLUMN_CLAVE_JEFACTURA_DEPTO = "CLAVE_JEFACTURA_DEPTO";

    @Override
    public ClaveFolioOficioDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ClaveFolioOficioDTO claveDto = new ClaveFolioOficioDTO();
        claveDto.setIdArace(resultSet.getBigDecimal(COLUMN_ID_ARACE));
        claveDto.setClaveAdministracionGeneral(resultSet.getString(COLUMN_CLAVE_ADMON_GRAL));
        claveDto.setClaveAdministracionArea(resultSet.getString(COLUMN_CLAVE_ADMIN_AREA));
        claveDto.setClaveSubadmistracionArea(resultSet.getString(COLUMN_CLAVE_SUBADMIN_AREA));
        claveDto.setClaveJefaturaDepto(resultSet.getString(COLUMN_CLAVE_JEFACTURA_DEPTO));

        return claveDto;
    }

}
