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
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAdminOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;

public class FecetOficioMapper implements ParameterizedRowMapper<FecetOficio> {

    private static final String COLUMN_ID_OFICIO = "ID_OFICIO";
    private static final String COLUMN_ID_OFICIO_PRINCIPAL = "ID_OFICIO_PRINCIPAL";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_FECHA_FIRMA = "FECHA_FIRMA";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_DOCUMENTO_PDF = "DOCUMENTO_PDF";
    private static final String COLUMN_FOLIO_NYV = "FOLIO_NYV";
    private static final String COLUMN_FECHA_NOTIF_NYV = "FECHA_NOTIF_NYV";
    private static final String COLUMN_FECHA_NOTIF_CONT = "FECHA_NOTIF_CONT";
    private static final String COLUMN_FECHA_SURTE_EFECTOS = "FECHA_SURTE_EFECTOS";
    private static final String COLUMN_DIAS_RESTANTES_PLAZO = "DIAS_RESTANTES_PLAZO";
    private static final String COLUMN_DIAS_HABILES = "DIAS_HABILES";
    private static final String COLUMN_SUSPENCION_PLAZO = "SUSPENCION_PLAZO";
    private static final String COLUMN_DIAS_RESTANTES_DOCUMENTOS = "DIAS_RESTANTES_DOCUMENTOS";
    private static final String COLUMN_FECHA_INTEGRA_EXP = "FECHA_INTEGRA_EXP";
    private static final String COLUMN_ID_ESTATUS = "ID_ESTATUS";
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";
    private static final String COLUMN_CADENA_ORIGINAL = "CADENA_ORIGINAL";
    private static final String COLUMN_FIRMA_ELECTRONICA = "FIRMA_ELECTRONICA";
    private static final String COLUMN_ID_NYV = "ID_NYV";
    private static final String COLUMN_ID_TIPO_OFICIO = "ID_TIPO_OFICIO";
    private static final String COLUMN_RUTA_ACUSE_NYV = "RUTA_ACUSE_NYV";
    private static final String COLUMN_ID_AGRUPADOR_TIPO_OFICIO = "ID_AGRUPADOR_TIPO_OFICIO";
    private static final String COLUMN_ID_ADMIN_OFICIO = "ID_ADMIN_OFICIO";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_NUMERO_OFICIO = "NUMERO_OFICIO";
    private static final String COLUMN_ID_TIPO_EMPLADO = "ID_TIPO_EMPLEADO";
    private static final String COLUMN_FECHA_HORA = "FECHA_HORA";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";

    @Override
    public FecetOficio mapRow(ResultSet rs, int i) throws SQLException {
        FecetOficio oficio = new FecetOficio();
        oficio.setIdOficio(rs.getBigDecimal(COLUMN_ID_OFICIO));
        oficio.setIdOficioPrincipal(rs.getBigDecimal(COLUMN_ID_OFICIO_PRINCIPAL));
        oficio.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        oficio.setFechaFirma(rs.getTimestamp(COLUMN_FECHA_FIRMA));
        oficio.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        oficio.setRutaArchivo(rs.getString(COLUMN_RUTA_ARCHIVO));
        oficio.setDocumentoPdf(rs.getString(COLUMN_DOCUMENTO_PDF));
        oficio.setFolioNyv(rs.getString(COLUMN_FOLIO_NYV));
        oficio.setFechaNotifNyv(rs.getDate(COLUMN_FECHA_NOTIF_NYV));
        oficio.setFechaNotifCont(rs.getDate(COLUMN_FECHA_NOTIF_CONT));
        oficio.setFechaSurteEfectos(rs.getDate(COLUMN_FECHA_SURTE_EFECTOS));
        oficio.setDiasRestantesPlazo(rs.getBigDecimal(COLUMN_DIAS_RESTANTES_PLAZO));
        oficio.setDiasHabiles(rs.getString(COLUMN_DIAS_HABILES));
        oficio.setSuspencionPlazo(rs.getString(COLUMN_SUSPENCION_PLAZO));
        oficio.setDiasRestantesDocumentos(rs.getBigDecimal(COLUMN_DIAS_RESTANTES_DOCUMENTOS));
        oficio.setFechaIntegraExp(rs.getDate(COLUMN_FECHA_INTEGRA_EXP));
        oficio.setIdEstatus(rs.getBigDecimal(COLUMN_ID_ESTATUS));
        oficio.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        oficio.setCadenaOriginal(rs.getString(COLUMN_CADENA_ORIGINAL));
        oficio.setFirmaElectronica(rs.getString(COLUMN_FIRMA_ELECTRONICA));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_NYV)) {
            FecetDetalleNyV detalle = new FecetDetalleNyV();
            detalle.setIdNyV(rs.getLong(COLUMN_ID_NYV));
            if (UtileriasMapperDao.existeColumna(rs, COLUMN_RUTA_ACUSE_NYV)) {
                detalle.setRutaAcuseNyv(rs.getString(COLUMN_RUTA_ACUSE_NYV));
                oficio.setNombreAcuseNyv(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ACUSE_NYV)));
                oficio.setRutaAcuseNyv(rs.getString(COLUMN_RUTA_ACUSE_NYV));
            }
            oficio.setFecetDetalleNyV(detalle);
            oficio.setIdNyV(rs.getLong(COLUMN_ID_NYV));
        }
        FecetTipoOficio tipoOficio = new FecetTipoOficio();
        tipoOficio.setIdTipoOficio(rs.getBigDecimal(COLUMN_ID_TIPO_OFICIO));
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_AGRUPADOR_TIPO_OFICIO)
                && rs.getBigDecimal(COLUMN_ID_AGRUPADOR_TIPO_OFICIO) != null) {
            tipoOficio.setAgrupador(
                    AgrupadorOficiosEnum.parse(rs.getBigDecimal(COLUMN_ID_AGRUPADOR_TIPO_OFICIO).intValue()));
        }

        oficio.setFecetTipoOficio(tipoOficio);

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_ADMIN_OFICIO)) {
            FecetAdminOficio oficioAdmin = new FecetAdminOficio();
            if (rs.getBigDecimal(COLUMN_ID_ADMIN_OFICIO) != null) {
                oficio.setIdAdminOficio(rs.getBigDecimal(COLUMN_ID_ADMIN_OFICIO));
                oficioAdmin = new FecetAdminOficioMapper().mapRow(rs, i);
                oficio.setAdminOficio(oficioAdmin);
            } else {
                oficioAdmin.setBlnDocReq(true);
                oficioAdmin.setBlnDocPro(true);
                oficioAdmin.setBlnPlazos(true);
                oficio.setAdminOficio(null);
            }
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_BLN_ACTIVO)) {
            oficio.setBlnActivo(isActive(rs.getString(COLUMN_BLN_ACTIVO)));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NUMERO_OFICIO)) {
            oficio.setNumeroOficio(rs.getString(COLUMN_NUMERO_OFICIO));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_TIPO_EMPLADO)) {
            oficio.setDescripcionAccion(rs.getBigDecimal(COLUMN_ID_TIPO_EMPLADO).toString());
        }
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_FECHA_HORA)) {
            oficio.setFechaHora(rs.getTimestamp(COLUMN_FECHA_HORA));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_EMPLEADO)) {
            oficio.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        }

        return oficio;
    }

    private boolean isActive(String element) {
        return element != null && !"0".equals(element);
    }
}
