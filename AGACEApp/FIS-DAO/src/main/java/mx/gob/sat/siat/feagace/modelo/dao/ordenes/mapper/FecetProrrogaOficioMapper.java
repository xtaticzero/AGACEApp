package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.getNameFileFromPath;
import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.existeColumna;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetProrrogaOficioMapper implements ParameterizedRowMapper<FecetProrrogaOficio> {

    private static final String COLUMN_ID_PRORROGA_OFICIO = "ID_PRORROGA_OFICIO";
    private static final String COLUMN_ID_OFICIO = "ID_OFICIO";
    private static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";
    private static final String COLUMN_RUTA_ACUSE = "RUTA_ACUSE";
    private static final String COLUMN_CADENA_CONTRIBUYENTE = "CADENA_CONTRIBUYENTE";
    private static final String COLUMN_FIRMA_CONTRIBUYENTE = "FIRMA_CONTRIBUYENTE";
    private static final String COLUMN_APROBADA = "APROBADA";
    private static final String COLUMN_ID_ASOCIADO_CARGA = "ID_ASOCIADO_CARGA";
    private static final String COLUMN_ID_AUDITOR = "ID_AUDITOR";
    private static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";
    private static final String COLUMN_RUTA_RESOLUCION = "RUTA_RESOLUCION";
    private static final String COLUMN_CADENA_FIRMANTE = "CADENA_FIRMANTE";
    private static final String COLUMN_FIRMA_FIRMANTE = "FIRMA_FIRMANTE";
    private static final String COLUMN_FECHA_FIRMA = "FECHA_FIRMA";
    private static final String COLUMN_ID_NYV = "ID_NYV";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * @param rs
     * @param i
     * @return FecetProrrogaOficio
     * @throws SQLException
     */
    /**
     * Este atributo corresponde al nombre de la columna FOLIO_NYV en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FOLIO_NYV = "FOLIO_NYV";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_NOTIF_NYV en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FECHA_NOTIF_NYV = "FECHA_NOTIF_NYV";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_NOTIF_CONT en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FECHA_NOTIF_CONT = "FECHA_NOTIF_CONT";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_SURTE_EFECTOS en
     * la tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FECHA_SURTE_EFECTOS = "FECHA_SURTE_EFECTOS";

    @Override
    public FecetProrrogaOficio mapRow(ResultSet rs, int i) throws SQLException {

        FecetProrrogaOficio dto = new FecetProrrogaOficio();

        dto.setIdProrrogaOficio(rs.getBigDecimal(COLUMN_ID_PRORROGA_OFICIO));
        dto.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setNombreAcuse(getNameFileFromPath(rs.getString(COLUMN_RUTA_ACUSE)));
        dto.setRutaAcuse(rs.getString(COLUMN_RUTA_ACUSE));
        dto.setCadenaContribuyente(rs.getString(COLUMN_CADENA_CONTRIBUYENTE));
        dto.setFirmaContribuyente(rs.getString(COLUMN_FIRMA_CONTRIBUYENTE));
        dto.setAprobada(rs.getBoolean(COLUMN_APROBADA));
        dto.setIdAsociadoCarga(rs.getBigDecimal(COLUMN_ID_ASOCIADO_CARGA));
        dto.setIdAuditor(rs.getBigDecimal(COLUMN_ID_AUDITOR));
        dto.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        dto.setNombreResolucion(getNameFileFromPath(rs.getString(COLUMN_RUTA_RESOLUCION)));
        dto.setRutaResolucion(rs.getString(COLUMN_RUTA_RESOLUCION));
        dto.setCadenaFirmante(rs.getString(COLUMN_CADENA_FIRMANTE));
        dto.setFirmaFirmante(rs.getString(COLUMN_FIRMA_FIRMANTE));
        dto.setFechaFirma(rs.getDate(COLUMN_FECHA_FIRMA));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setFolioNyV(rs.getString(COLUMN_FOLIO_NYV));
        dto.setFechaNotifNyV(rs.getDate(COLUMN_FECHA_NOTIF_NYV));
        dto.setFechaNotifCont(rs.getDate(COLUMN_FECHA_NOTIF_CONT));
        dto.setFechaSuerteEfectos(rs.getDate(COLUMN_FECHA_SURTE_EFECTOS));

        if (existeColumna(rs, COLUMN_ID_NYV)) {
            FecetDetalleNyV detalleNyV = new FecetDetalleNyV();
            detalleNyV.setIdNyV(rs.getLong(COLUMN_ID_NYV));
            dto.setFecetDetalleNyV(detalleNyV);
            dto.setIdNyV(rs.getLong(COLUMN_ID_NYV));
        }

        return dto;
    }
}
