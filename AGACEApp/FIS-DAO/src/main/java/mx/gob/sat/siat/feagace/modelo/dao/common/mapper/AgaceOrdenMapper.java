/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;

public class AgaceOrdenMapper implements ParameterizedRowMapper<AgaceOrden> {

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna ID_METODO en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_METODO = "ID_METODO";

    /**
     * Este atributo corresponde al nombre de la columna ID_REVISION en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_REVISION = "ID_REVISION";

    /**
     * Este atributo corresponde al nombre de la columna NUMERO_ORDEN en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_NUMERO_ORDEN = "NUMERO_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_CREACION en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_BAJA en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_FECHA_BAJA = "FECHA_BAJA";

    /**
     * Este atributo corresponde al nombre de la columna PRIORIDAD en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_PRIORIDAD = "PRIORIDAD";

    /**
     * Este atributo corresponde al nombre de la columna FOLIO_NYV en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_FOLIO_NYV = "FOLIO_NYV";

    /**
     * Este atributo corresponde al nombre de la columna CADENA_ORIGINAL en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_CADENA_ORIGINAL = "CADENA_ORIGINAL";

    /**
     * Este atributo corresponde al nombre de la columna FIRMA_ELECTRONICA en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_FIRMA_ELECTRONICA = "FIRMA_ELECTRONICA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_NOTIF_NYV en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_NOTIF_NYV = "FECHA_NOTIF_NYV";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_NOTIF_CONT en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_NOTIF_CONT = "FECHA_NOTIF_CONT";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_SURTE_EFECTOS en
     * la tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_SURTE_EFECTOS = "FECHA_SURTE_EFECTOS";

    /**
     * Este atributo corresponde al nombre de la columna DIAS_RESTANTES_PLAZO en
     * la tabla FECET_ORDEN
     */
    private static final String COLUMN_DIAS_RESTANTES_PLAZO = "DIAS_RESTANTES_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna DIAS_HABILES en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_DIAS_HABILES = "DIAS_HABILES";

    /**
     * Este atributo corresponde al nombre de la columna SUSPENCION_PLAZO en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_SUSPENCION_PLAZO = "SUSPENCION_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna
     * DIAS_RESTANTES_DOCUMENTOS en la tabla FECET_ORDEN
     */
    private static final String COLUMN_DIAS_RESTANTES_DOCUMENTOS = "DIAS_RESTANTES_DOCUMENTOS";

    /**
     * Este atributo corresponde al nombre de la columna SEMAFORO en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_SEMAFORO = "SEMAFORO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_INTEGRA_EXP en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_INTEGRA_EXP = "FECHA_INTEGRA_EXP";

    /**
     * Este atributo corresponde al nombre de la columna ID_CONTRIBUYENTE en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_ID_CONTRIBUYENTE = "ID_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_ESTATUS en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";

    /**
     * Este atributo corresponde al nombre de la columna ID_AUDITOR en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_AUDITOR = "ID_AUDITOR";

    /**
     * Este atributo corresponde al nombre de la columna ID_FIRMANTE en la tabla
     * FECET_ORDEN
     */
    private static final String COLUMN_ID_FIRMANTE = "ID_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna ID_PROPUESTA en la
     * tabla FECET_ORDEN
     */
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna ID_REGISTRO_PROPUESTA
     * en la tabla FECET_ORDEN
     */
    private static final String COLUMN_ID_REGISTRO_PROPUESTA = "ID_REGISTRO_PROPUESTA";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_REACTIVAR_PLAZO
     * en la tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_REACTIVAR_PLAZO = "FECHA_REACTIVAR_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna FECHA_SUSPENCION_PLAZO
     * en la tabla FECET_ORDEN
     */
    private static final String COLUMN_FECHA_SUSPENCION_PLAZO = "FECHA_SUSPENCION_PLAZO";

    /**
     * Este atributo corresponde al nombre de la columna
     * DIAS_RESOLUCION_DEFINITIVA en la tabla FECET_ORDEN
     */
    private static final String COLUMN_DIAS_RESOLUCION_DEFINITIVA = "DIAS_RESOLUCION_DEFINITIVA";

    private static final String COLUMN_FOLIO_OFICIO = "FOLIO_OFICIO";

    private static final String COLUMN_BLN_COMPULSA = "BLN_COMPULSA";

    private static final String ID_NYV = "ID_NYV";

    private static final String COLUMN_ID_ARACE = "ID_ARACE";

    private static final String COLUMN_DESCRIPCION_CIFRAS = "DESCRIPCION_CIFRAS";

    private static final String COLUMN_TOTAL_CIFRAS = "TOTAL";

    @Override
    public AgaceOrden mapRow(ResultSet rs, int rowNum) throws SQLException {

        AgaceOrden dto = new AgaceOrden();

        dto.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        dto.setIdMetodo(rs.getBigDecimal(COLUMN_ID_METODO));
        dto.setIdRevision(rs.getBigDecimal(COLUMN_ID_REVISION));
        dto.setNumeroOrden(rs.getString(COLUMN_NUMERO_ORDEN));
        dto.setFechaCreacion(rs.getDate(COLUMN_FECHA_CREACION));
        dto.setFechaBaja(rs.getDate(COLUMN_FECHA_BAJA));
        dto.setPrioridad(rs.getBoolean(COLUMN_PRIORIDAD));
        dto.setPrioridadSugerida(rs.getString(COLUMN_PRIORIDAD));
        dto.setFolioNYV(rs.getString(COLUMN_FOLIO_NYV));
        dto.setCadenaOriginal(rs.getString(COLUMN_CADENA_ORIGINAL));
        dto.setFirmaElectronica(rs.getString(COLUMN_FIRMA_ELECTRONICA));
        dto.setFechaNotifNYV(rs.getDate(COLUMN_FECHA_NOTIF_NYV));
        dto.setFechaNotifCont(rs.getDate(COLUMN_FECHA_NOTIF_CONT));
        dto.setFechaSurteEfectos(rs.getDate(COLUMN_FECHA_SURTE_EFECTOS));
        dto.setDiasRestantesPlazo(rs.getInt(COLUMN_DIAS_RESTANTES_PLAZO));
        dto.setDiasHabiles(rs.getBoolean(COLUMN_DIAS_HABILES));
        dto.setSuspencionPlazo(rs.getBoolean(COLUMN_SUSPENCION_PLAZO));
        dto.setDiasRestantesDocumentos(rs.getInt(COLUMN_DIAS_RESTANTES_DOCUMENTOS));
        dto.setSemaforo(rs.getInt(COLUMN_SEMAFORO));
        dto.setFechaIntegraExp(rs.getDate(COLUMN_FECHA_INTEGRA_EXP));
        dto.setIdContribuyente(rs.getBigDecimal(COLUMN_ID_CONTRIBUYENTE));
        dto.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        dto.setIdAuditor(rs.getBigDecimal(COLUMN_ID_AUDITOR));
        dto.setIdFirmante(rs.getBigDecimal(COLUMN_ID_FIRMANTE));
        dto.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        dto.setIdRegistroPropuesta(rs.getString(COLUMN_ID_REGISTRO_PROPUESTA));
        dto.setFechaReactivarPlazo(rs.getDate(COLUMN_FECHA_REACTIVAR_PLAZO));
        dto.setFechaSuspencionPlazo(rs.getDate(COLUMN_FECHA_SUSPENCION_PLAZO));
        dto.setDiasResolucionDefinitiva(rs.getInt(COLUMN_DIAS_RESOLUCION_DEFINITIVA));
        dto.setFolioOficio(rs.getString(COLUMN_FOLIO_OFICIO));
        dto.setBlnCompulsa(rs.getBoolean(COLUMN_BLN_COMPULSA));

        if (UtileriasMapperDao.existeColumna(rs, ID_NYV)) {
            FecetDetalleNyV detalle = new FecetDetalleNyV();
            detalle.setIdNyV(rs.getLong(ID_NYV));
            dto.setFecetDetalleNyV(detalle);
            dto.setIdNyV(rs.getLong(ID_NYV));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_ARACE)) {
            dto.setIdArace(rs.getBigDecimal(COLUMN_ID_ARACE));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_DESCRIPCION_CIFRAS)) {
            dto.setDescripcionCifras(rs.getString(COLUMN_DESCRIPCION_CIFRAS));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_TOTAL_CIFRAS)) {
            dto.setTotalCifras(rs.getBigDecimal(COLUMN_TOTAL_CIFRAS));
        }

        dto.setFecetContribuyente(new FecetContribuyenteMapper().mapRow(rs, rowNum));

        return dto;

    }
}
