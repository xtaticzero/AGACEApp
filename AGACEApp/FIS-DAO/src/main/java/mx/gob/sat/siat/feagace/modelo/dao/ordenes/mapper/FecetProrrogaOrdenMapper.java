/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.existeColumna;
import static mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao.getNameFileFromPath;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class FecetProrrogaOrdenMapper implements ParameterizedRowMapper<FecetProrrogaOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_PRORROGA_ORDEN en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_PRORROGA_ORDEN = "ID_PRORROGA_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CARGA en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FECHA_CARGA = "FECHA_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_ACUSE en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_RUTA_ACUSE = "RUTA_ACUSE";

    /**
     * Este atributo corresponde al nombre de la columna CADENA_CONTRIBUYENTE en
     * la tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_CADENA_CONTRIBUYENTE = "CADENA_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna FIRMA_CONTRIBUYENTE en
     * la tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FIRMA_CONTRIBUYENTE = "FIRMA_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna APROBADA en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_APROBADA = "APROBADA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ASOCIADO_CARGA en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_ASOCIADO_CARGA = "ID_ASOCIADO_CARGA";

    /**
     * Este atributo corresponde al nombre de la columna ID_AUDITOR en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_AUDITOR = "ID_AUDITOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_FIRMANTE en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna RUTA_RESOLUCION en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_RUTA_RESOLUCION = "RUTA_RESOLUCION";

    /**
     * Este atributo corresponde al nombre de la columna CADENA_FIRMANTE en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_CADENA_FIRMANTE = "CADENA_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna FIRMA_FIRMANTE en la
     * tabla FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FIRMA_FIRMANTE = "FIRMA_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_FIRMA en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_FECHA_FIRMA = "FECHA_FIRMA";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

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

    /**
     * Este atributo corresponde al nombre de la columna ID_NYV en la tabla
     * FECET_PRORROGA_ORDEN
     */
    public static final String COLUMN_ID_NYV = "ID_NYV";

    /**
     * Metodo mapRow Hace un mapeo de los datos en la tabla FECET_PRORROGA_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public FecetProrrogaOrden mapRow(ResultSet rs, int rowNum) throws SQLException {
        FecetProrrogaOrden dto = new FecetProrrogaOrden();

        dto.setIdProrrogaOrden(rs.getBigDecimal(COLUMN_ID_PRORROGA_ORDEN));
        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setFechaCarga(rs.getDate(COLUMN_FECHA_CARGA));
        dto.setNombreAcuse(getNameFileFromPath(rs.getString(COLUMN_RUTA_ACUSE)));
        dto.setRutaAcuse(rs.getString(COLUMN_RUTA_ACUSE));
        dto.setCadenaContribuyente(rs.getString(COLUMN_CADENA_CONTRIBUYENTE));
        dto.setFirmaContribuyente(rs.getString(COLUMN_FIRMA_CONTRIBUYENTE));
        dto.setAprobada(rs.getBoolean(COLUMN_APROBADA));
        if (existeColumna(rs, "ESTATUS_FLUJO")) {
            dto.setIdEstatus(rs.getBigDecimal("ESTATUS_FLUJO"));
        } else if (existeColumna(rs, COLUMN_ID_ESTATUS)) {
            dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        }
        dto.setIdAsociadoCarga(rs.getBigDecimal(COLUMN_ID_ASOCIADO_CARGA));
        dto.setIdAuditor(rs.getBigDecimal(COLUMN_ID_AUDITOR));
        dto.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        dto.setNombreResolucion(getNameFileFromPath(rs.getString(COLUMN_RUTA_RESOLUCION)));
        dto.setRutaResolucion(rs.getString(COLUMN_RUTA_RESOLUCION));
        dto.setCadenaFirmante(rs.getString(COLUMN_CADENA_FIRMANTE));
        dto.setFirmaFirmante(rs.getString(COLUMN_FIRMA_FIRMANTE));
        dto.setFechaFirma(rs.getDate(COLUMN_FECHA_FIRMA));
        dto.setFolioNyV(rs.getString(COLUMN_FOLIO_NYV));
        dto.setFechaNotifNyV(rs.getDate(COLUMN_FECHA_NOTIF_NYV));
        dto.setFechaNotifCont(rs.getDate(COLUMN_FECHA_NOTIF_CONT));
        dto.setFechaSuerteEfectos(rs.getDate(COLUMN_FECHA_SURTE_EFECTOS));

        if (existeColumna(rs, COLUMN_ID_NYV)) {
            FecetDetalleNyV detalle = new FecetDetalleNyV();
            detalle.setIdNyV(rs.getLong(COLUMN_ID_NYV));
            dto.setFecetDetalleNyV(detalle);
        }

        return dto;
    }
}
