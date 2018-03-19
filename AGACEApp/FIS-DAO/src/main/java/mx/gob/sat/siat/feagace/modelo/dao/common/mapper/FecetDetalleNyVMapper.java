package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.existeColumna;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;

public class FecetDetalleNyVMapper implements ParameterizedRowMapper<FecetDetalleNyV> {

    private static final String COLUMN_ID_NYV = "ID_NYV";
    private static final String COLUMN_FOLIO_NYV = "FOLIO_NYV";
    private static final String COLUMN_FOLIO_ACTO_ADMVO = "FOLIO_ACTO_ADMVO";
    private static final String COLUMN_FECHA_NOTIF_NYV = "FECHA_NOTIF_NYV";
    private static final String COLUMN_FECHA_NOTIF_CONT = "FECHA_NOTIF_CONT";
    private static final String COLUMN_FECHA_SURTE_EFECTOS = "FECHA_SURTE_EFECTOS";
    private static final String COLUMN_FECHA_ALTA = "FECHA_ALTA";
    private static final String COLUMN_ID_NYV_PADRE = "ID_NYV_PADRE";
    private static final String COLUMN_RFC_NOTIFICA = "RFC_NOTIFICA";
    private static final String COLUMN_RUTA_ACUSE_NYV = "RUTA_ACUSE_NYV";

    public FecetDetalleNyVMapper() {
        super();
    }

    @Override
    public FecetDetalleNyV mapRow(ResultSet rs, int i) throws SQLException {
        FecetDetalleNyV detalle = new FecetDetalleNyV();

        if (existeColumna(rs, COLUMN_ID_NYV)) {
            detalle.setIdNyV(rs.getLong(COLUMN_ID_NYV));
        }
        if (existeColumna(rs, COLUMN_FOLIO_NYV)) {
            detalle.setFolioNyV(rs.getString(COLUMN_FOLIO_NYV));
        }
        if (existeColumna(rs, COLUMN_FOLIO_ACTO_ADMVO)) {
            detalle.setFolioActoAdmvo(rs.getString(COLUMN_FOLIO_ACTO_ADMVO));
        }
        if (existeColumna(rs, COLUMN_FECHA_NOTIF_NYV)) {
            detalle.setFecNotificacionNyV(rs.getDate(COLUMN_FECHA_NOTIF_NYV));
        }
        if (existeColumna(rs, COLUMN_FECHA_NOTIF_CONT)) {
            detalle.setFecNotificacionContNyV(rs.getDate(COLUMN_FECHA_NOTIF_CONT));
        }
        if (existeColumna(rs, COLUMN_FECHA_SURTE_EFECTOS)) {
            detalle.setFecSurteEfectosNyV(rs.getDate(COLUMN_FECHA_SURTE_EFECTOS));
        }
        if (existeColumna(rs, COLUMN_FECHA_ALTA)) {
            detalle.setFecAltaNyV(rs.getDate(COLUMN_FECHA_ALTA));
        }
        if (existeColumna(rs, COLUMN_ID_NYV_PADRE)) {
            detalle.setIdNyVPadre(rs.getLong(COLUMN_ID_NYV_PADRE));
        }
        if (existeColumna(rs, COLUMN_RFC_NOTIFICA)) {
            detalle.setRfcNotifica(rs.getString(COLUMN_RFC_NOTIFICA));
        }
        if (existeColumna(rs, COLUMN_RUTA_ACUSE_NYV)) {
            detalle.setRutaAcuseNyv(rs.getString(COLUMN_RUTA_ACUSE_NYV));
        }

        return detalle;
    }
}
