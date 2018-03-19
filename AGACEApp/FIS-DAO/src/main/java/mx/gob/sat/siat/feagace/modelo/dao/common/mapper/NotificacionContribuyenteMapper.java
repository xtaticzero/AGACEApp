package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class NotificacionContribuyenteMapper implements ParameterizedRowMapper<OrdenNotificacion> {

    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    private static final String COLUMN_FOLIO_CARGA = "FOLIO_CARGA";

    private static final String COLUMN_NOMBRE_METODO = "NOMBRE_METODO";

    private static final String COLUMN_NUMERO_ORDEN = "NUMERO_ORDEN";

    private static final String COLUMN_NOMBRE = "NOMBRE";

    private static final String COLUMN_RFC = "RFC";

    private static final String COLUMN_FOLIO_OFICIO = "FOLIO_OFICIO";

    /**
     * Metodo mapRow
     *
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public OrdenNotificacion mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrdenNotificacion dto = new OrdenNotificacion();

        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setFolioCarga(rs.getBigDecimal(COLUMN_FOLIO_CARGA));
        dto.setDescripcionMetodo(rs.getString(COLUMN_NOMBRE_METODO));
        dto.setNumeroOrden(rs.getString(COLUMN_NUMERO_ORDEN));
        dto.setNombreContribuyente(rs.getString(COLUMN_NOMBRE));
        dto.setRfcContribuyente(rs.getString(COLUMN_RFC));
        dto.setFolioOficio(rs.getString(COLUMN_FOLIO_OFICIO));

        return dto;
    }
}
