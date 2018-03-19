/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioContadorAnexosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetOficioRechazoConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.enums.EstatusOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetOficioDao")
public class FecetOficioDaoImpl extends BaseJDBCDao<FecetOficio> implements FecetOficioDao {

    private static final Logger LOG = Logger.getLogger(FecetOficioDaoImpl.class.getName());

    private static final String QUERY_SELECT = "SELECT";
    private static final String QUERY_FROM = "FROM";
    private static final String QUERY_AND_ID_ORDEN = " AND O.ID_ORDEN = ? \n";
    private static final String QUERY_ORDER_BY_FECHA_FIRMA_ASC = " ORDER BY O.FECHA_FIRMA ASC ";
    private static final String SELECT_COUNT_TOTAL_ANEXOS_OFICIO = "   , (SELECT COUNT(0) FROM FECET_OFICIO_ANEXOS A WHERE A.ID_OFICIO = O.ID_OFICIO) TOTAL_ANEXOS_OFICIO \n";
    private static final String TIPO_OFICIO = " T \n";
    private static final String FROM = "   FROM ";
    private static final String SQL_UPDATE = " UPDATE ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String WHERE_ID_OFICIO_PRINCIPAL = " WHERE O.ID_OFICIO_PRINCIPAL = ? \n ";
    private static final String SELECT_COUNT_FROM = "   , (SELECT COUNT(0) FROM ";
    private static final String ALIAS_O_INNER_JOIN = " O INNER JOIN ";
    private static final String JOIN_ON_ID_TIPO_OFICIO = " ON O.ID_TIPO_OFICIO = T.ID_TIPO_OFICIO \n";
    private static final String INNER_JOIN = "   INNER JOIN ";

    private static final StringBuilder ALL_COLUMNS = new StringBuilder(
            " ID_OFICIO, ID_ORDEN, ID_OFICIO_PRINCIPAL, FECHA_CREACION, FECHA_FIRMA, RUTA_ARCHIVO, DOCUMENTO_PDF, FOLIO_NYV, RUTA_ACUSE_NYV, CADENA_ORIGINAL, FIRMA_ELECTRONICA, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, DIAS_RESTANTES_PLAZO, DIAS_HABILES, SUSPENCION_PLAZO, DIAS_RESTANTES_DOCUMENTOS, FECHA_INTEGRA_EXP, ESTADO, ID_ESTATUS, ID_NYV, ID_TIPO_OFICIO, ID_ADMIN_OFICIO, BLN_ACTIVO, NUMERO_OFICIO,NOMBRE_COMPULSADO ");
    private static final StringBuilder ALL_COLUMNS_RELACION = new StringBuilder(
            " O.ID_OFICIO, O.ID_OFICIO_PRINCIPAL, O.FECHA_CREACION, \n")
            .append("   O.FECHA_FIRMA, O.RUTA_ARCHIVO, O.DOCUMENTO_PDF, O.FOLIO_NYV,  \n")
            .append("   O.FECHA_NOTIF_NYV, O.FECHA_NOTIF_CONT, O.FECHA_SURTE_EFECTOS, \n")
            .append("   O.DIAS_RESTANTES_PLAZO, O.DIAS_HABILES, O.SUSPENCION_PLAZO, O.DIAS_RESTANTES_DOCUMENTOS, \n")
            .append("   O.FECHA_INTEGRA_EXP, O.ID_ESTATUS, O.ID_ORDEN, O.CADENA_ORIGINAL, O.FIRMA_ELECTRONICA, O.ID_NYV, O.ID_TIPO_OFICIO, O.ID_ADMIN_OFICIO, O.BLN_ACTIVO, O.NUMERO_OFICIO, O.NOMBRE_COMPULSADO, \n")
            .append("(SELECT CONT.RFC FROM FECET_CONTRIBUYENTE CONT INNER JOIN FECET_COMPULSAS COM ON COM.ID_CONTRIBUYENTE_COMPULSADO = CONT.ID_CONTRIBUYENTE AND O.ID_ORDEN = COM.ID_ORDEN_AUDITADA AND O.ID_OFICIO = COM.ID_OFICIO) RFC_COMPULSADO \n");

    private static final StringBuilder SELECT_OFICIOS_CON_RELACIONES = new StringBuilder(
            ALL_COLUMNS_RELACION.toString())
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION,  T.ID_AGRUPADOR_TIPO_OFICIO \n")
            .append(SELECT_COUNT_TOTAL_ANEXOS_OFICIO).append(SELECT_COUNT_FROM).append(getTableName())
            .append(" OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO AND OD.ID_ESTATUS = O.ID_ESTATUS) TOTAL_OFICIOS_DEPENDIENTES \n")
            .append("   , NOMBRE_COMPULSADO, (SELECT CONT.RFC FROM FECET_CONTRIBUYENTE CONT INNER JOIN FECET_COMPULSAS COM ON COM.ID_CONTRIBUYENTE_COMPULSADO = CONT.ID_CONTRIBUYENTE AND O.ID_ORDEN = COM.ID_ORDEN_AUDITADA AND O.ID_OFICIO = COM.ID_OFICIO) RFC_COMPULSADO \n")
            .append(FROM).append(getTableName()).append(ALIAS_O_INNER_JOIN)
            .append(FecetTipoOficioDaoImpl.getTableName()).append(TIPO_OFICIO).append(JOIN_ON_ID_TIPO_OFICIO);

    private static final StringBuilder SELECT_OFICIOS_HISTORICO = new StringBuilder(ALL_COLUMNS_RELACION.toString())
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION,  T.ID_AGRUPADOR_TIPO_OFICIO \n")
            .append(SELECT_COUNT_TOTAL_ANEXOS_OFICIO).append(SELECT_COUNT_FROM).append(getTableName())
            .append(" OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO AND OD.ID_ESTATUS = O.ID_ESTATUS) TOTAL_OFICIOS_DEPENDIENTES, \n")
            .append("   FECET_RECHAZO_OFICIO.ID_EMPLEADO, FECET_RECHAZO_OFICIO.DESCRIPCION as ID_TIPO_EMPLEADO,FECET_RECHAZO_OFICIO.FECHA_RECHAZO as FECHA_HORA  \n")
            .append(FROM).append(getTableName()).append(ALIAS_O_INNER_JOIN)
            .append(FecetTipoOficioDaoImpl.getTableName()).append(TIPO_OFICIO).append(JOIN_ON_ID_TIPO_OFICIO)
            .append("   INNER JOIN FECET_ORDEN ON (o.ID_ORDEN = FECET_ORDEN.ID_ORDEN) \n")
            .append("     INNER JOIN FECET_RECHAZO_OFICIO ON (FECET_RECHAZO_OFICIO.ID_OFICIO=o.ID_OFICIO) \n");

    private static final StringBuilder SELECT_RECHAZO_OFICIOS_CON_RELACIONES = new StringBuilder(
            ALL_COLUMNS_RELACION.toString())
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION \n")
            .append(SELECT_COUNT_TOTAL_ANEXOS_OFICIO)
            .append("   , (SELECT COUNT(0) FROM FECET_OFICIO OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO) TOTAL_OFICIOS_DEPENDIENTES \n")
            .append("   , R.ID_RECHAZO_OFICIO, R.ID_OFICIO, R.DESCRIPCION AS DESCRIPCION_RECHAZO, R.FECHA_RECHAZO, R.ID_EMPLEADO, R.ESTATUS \n")
            .append("   , (SELECT COUNT(0) FROM FECET_ANEXOS_RECHAZO_OFICIO RO WHERE RO.ID_RECHAZO_OFICIO = R.ID_RECHAZO_OFICIO) TOTAL_ANEXOS_RECHAZO \n")
            .append(FROM).append(getTableName()).append(" O ").append(INNER_JOIN)
            .append(FecetTipoOficioDaoImpl.getTableName()).append(TIPO_OFICIO).append(JOIN_ON_ID_TIPO_OFICIO)
            .append(INNER_JOIN).append(FecetRechazoOficioDaoImpl.getTableName()).append(" R \n")
            .append("   ON O.ID_OFICIO = R.ID_OFICIO  \n");

    private static final StringBuilder SELECT_OFICIOS_CON_RELACIONES_NYV_CONSULTA_OFICIO = new StringBuilder(
            ALL_COLUMNS_RELACION.toString())
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION, T.ID_AGRUPADOR_TIPO_OFICIO \n")
            .append("   , AD.ID_ADMIN_OFICIO, AD.BLN_DOC_REQ, AD.BLN_DOC_PRO, AD.BLN_PLAZOS, AD.BLN_ACTIVO AS ADMIN_ACTIVO, AD.FECHA \n")
            .append(SELECT_COUNT_TOTAL_ANEXOS_OFICIO).append(SELECT_COUNT_FROM).append(getTableName())
            .append(" OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO AND OD.ID_ESTATUS IN (114, 115) ) TOTAL_OFICIOS_DEPENDIENTES \n")
            .append("   , N.RUTA_ACUSE_NYV \n").append(FROM).append(getTableName()).append(ALIAS_O_INNER_JOIN)
            .append(FecetTipoOficioDaoImpl.getTableName()).append(TIPO_OFICIO).append(JOIN_ON_ID_TIPO_OFICIO)
            .append("   LEFT JOIN FECET_DETALLE_NYV N ON O.ID_NYV = N.ID_NYV \n").append(INNER_JOIN)
            .append(FecetAdminOficioDaoImpl.getTableName()).append(" AD \n")
            .append("   ON O.ID_ADMIN_OFICIO = AD.ID_ADMIN_OFICIO \n");

    private static final StringBuilder SELECT_OFICIOS_CON_RELACIONES_NYV_CONSULTA_OFICIO_POR_FIRMAR = new StringBuilder(
            ALL_COLUMNS_RELACION.toString())
            .append("   , T.ID_TIPO_OFICIO, T.NOMBRE, T.DESCRIPCION, T.FECHA_CREACION, T.ID_AGRUPADOR_TIPO_OFICIO \n")
            .append("   , AD.ID_ADMIN_OFICIO, AD.BLN_DOC_REQ, AD.BLN_DOC_PRO, AD.BLN_PLAZOS, AD.BLN_ACTIVO AS ADMIN_ACTIVO, AD.FECHA \n")
            .append(SELECT_COUNT_TOTAL_ANEXOS_OFICIO).append(SELECT_COUNT_FROM).append(getTableName())
            .append(" OD WHERE OD.ID_OFICIO_PRINCIPAL = O.ID_OFICIO AND OD.ID_ESTATUS IN (113) ) TOTAL_OFICIOS_DEPENDIENTES \n")
            .append("   , N.RUTA_ACUSE_NYV \n").append(FROM).append(getTableName()).append(ALIAS_O_INNER_JOIN)
            .append(FecetTipoOficioDaoImpl.getTableName()).append(TIPO_OFICIO).append(JOIN_ON_ID_TIPO_OFICIO)
            .append("   LEFT JOIN FECET_DETALLE_NYV N ON O.ID_NYV = N.ID_NYV \n").append(INNER_JOIN)
            .append(FecetAdminOficioDaoImpl.getTableName()).append(" AD \n")
            .append("   ON O.ID_ADMIN_OFICIO = AD.ID_ADMIN_OFICIO \n");

    private static final StringBuilder SELECT_OFICIOS_ADMIN = new StringBuilder(ALL_COLUMNS_RELACION.toString())
            .append("   , AD.ID_ADMIN_OFICIO, AD.BLN_DOC_REQ, AD.BLN_DOC_PRO, AD.BLN_PLAZOS, AD.BLN_ACTIVO AS ADMIN_ACTIVO, AD.FECHA \n")
            .append(FROM).append(getTableName()).append(" O ").append(INNER_JOIN)
            .append(FecetAdminOficioDaoImpl.getTableName()).append(" AD \n")
            .append("   ON O.ID_ADMIN_OFICIO = AD.ID_ADMIN_OFICIO \n");

    private static final StringBuilder SELECT_OFICIOS_ASOCIADOS_ORDEN = new StringBuilder(
            "SELECT O.ID_OFICIO, O.ID_OFICIO_PRINCIPAL, O.FECHA_CREACION, O.FECHA_FIRMA, O.RUTA_ARCHIVO, O.DOCUMENTO_PDF, O.FOLIO_NYV, O.FECHA_NOTIF_NYV, O.FECHA_NOTIF_CONT, O.FECHA_SURTE_EFECTOS,")
            .append(" O.DIAS_RESTANTES_PLAZO, O.DIAS_HABILES, O.SUSPENCION_PLAZO, O.DIAS_RESTANTES_DOCUMENTOS, O.FECHA_INTEGRA_EXP, O.ID_ESTATUS, O.ID_ORDEN, O.CADENA_ORIGINAL, O.FIRMA_ELECTRONICA, O.ID_NYV, ")
            .append(" O.ID_TIPO_OFICIO, O.ID_ADMIN_OFICIO, O.BLN_ACTIVO, O.NUMERO_OFICIO ")
            .append(" FROM FECET_OFICIO O ").append(" INNER JOIN FECET_ORDEN FO ")
            .append(" ON FO.ID_ORDEN = O.ID_ORDEN ");

    private static final StringBuilder INSERT_COLUMNS = new StringBuilder("(").append(ALL_COLUMNS).append(" )");

    private static final String SEQ_TIPO_OFICIO = " SELECT FECEQ_OFICIO.NEXTVAL FROM DUAL ";

    private static final StringBuilder UPDATE_COLUMNS_OFICIO = new StringBuilder(
            " ID_ESTATUS = ? , FECHA_FIRMA = ? , CADENA_ORIGINAL = ? , FIRMA_ELECTRONICA = ? ");

    private static final String SELECT_NO_REFERENCIA_OFICIO = "SELECT NUMERO_REFERENCIA FROM FECET_OFICIO WHERE ID_OFICIO = ?";

    private static final String UPDATE_NO_REFERENCIA_OFICIO = "UPDATE FECET_OFICIO SET NUMERO_REFERENCIA = ? WHERE ID_OFICIO = ? AND NUMERO_REFERENCIA IS NULL";

    private static final long serialVersionUID = 1L;

    @Override
    public BigDecimal getIdOficio() {
        return getJdbcTemplateBase().queryForObject(SEQ_TIPO_OFICIO, BigDecimal.class);
    }

    @Override
    public BigDecimal insert(FecetOficio dto) {
        StringBuilder sqlInsert = new StringBuilder("INSERT INTO");
        sqlInsert.append(getTableName());
        sqlInsert.append(INSERT_COLUMNS);
        sqlInsert.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?,?,?,?,?,?)");

        if (dto.getIdOficio() == null) {
            dto.setIdOficio(getIdOficio());
        }

        logger.error(sqlInsert.toString());

        final int estatusInsert = getJdbcTemplateBase().update(sqlInsert.toString(), dto.getIdOficio(),
                dto.getIdOrden(), dto.getIdOficioPrincipal(), dto.getFechaCreacion(), dto.getFechaFirma(),
                dto.getRutaArchivo(), dto.getDocumentoPdf(), dto.getFolioNyv(), dto.getRutaAcuseNyv(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getFechaNotifNyv(), dto.getFechaNotifCont(),
                dto.getFechaSurteEfectos(), dto.getDiasRestantesPlazo(), dto.getDiasHabiles(), dto.getSuspencionPlazo(),
                dto.getDiasRestantesDocumentos(), dto.getFechaIntegraExp(), dto.getIdEstatus(), dto.getIdNyV(),
                dto.getFecetTipoOficio().getIdTipoOficio(), dto.getIdAdminOficio(),
                dto.getFecetTipoOficio().getBlnActivo(), null, dto.getNombreCompulsado());

        return new BigDecimal(estatusInsert);

    }

    @Override
    public void updateFirmante(FecetOficio dto) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE");
        sqlUpdate.append(getTableName());
        sqlUpdate.append("SET");
        sqlUpdate.append(UPDATE_COLUMNS_OFICIO);
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), dto.getIdEstatus(), dto.getFechaFirma(),
                dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getIdOficio());

    }

    public static String getTableName() {
        return " FECET_OFICIO ";
    }

    @Override
    public FecetOficio getOficioById(BigDecimal idOficio) {
        StringBuilder sqlById = new StringBuilder();
        sqlById.append(QUERY_SELECT);
        sqlById.append(ALL_COLUMNS);
        sqlById.append(QUERY_FROM);
        sqlById.append(getTableName());
        sqlById.append("WHERE ID_OFICIO = ? ");
        List<FecetOficio> list = getJdbcTemplateBase().query(sqlById.toString(), new FecetOficioMapper(), idOficio);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<FecetOficio> getOficioByIdOrdenIdEstatus(final BigDecimal idOrden, final BigDecimal... idsEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES_NYV_CONSULTA_OFICIO);
        query.append(" WHERE O.ID_ORDEN = ? AND O.ID_OFICIO_PRINCIPAL IS NULL AND O.ID_ESTATUS IN(");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            query.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        logger.debug(" QRY : [{}] ", query.toString());

        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public List<FecetOficio> getOficioByIdOrdenIdEstatusPorFirmar(final BigDecimal idOrden,
            final BigDecimal... idsEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES_NYV_CONSULTA_OFICIO_POR_FIRMAR);
        query.append(" WHERE O.ID_ORDEN = ? AND O.ID_OFICIO_PRINCIPAL IS NULL AND O.ID_ESTATUS IN(");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            query.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        logger.debug(" QRY : [{}] ", query.toString());

        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public List<FecetOficio> getOficioByIdOrden(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_ORDEN = ? AND O.ID_OFICIO_PRINCIPAL IS NULL ");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public List<FecetOficio> getOficioByIdOficio(final BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE  O.ID_OFICIO = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOficio);
    }

    @Override
    public List<FecetOficio> getOficioIdOrdenHistorial(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_HISTORICO);
        query.append(
                " WHERE O.ID_ORDEN = ? AND O.ID_OFICIO_PRINCIPAL IS NULL ORDER BY FECET_RECHAZO_OFICIO.FECHA_RECHAZO ASC");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public FecetOficio getOficioCompulsa(BigDecimal idOrdenAuditada, BigDecimal idOrdenCompulsa) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" INNER JOIN FECET_COMPULSAS COMPULSAS ");
        query.append(" ON COMPULSAS.ID_OFICIO = O.ID_OFICIO ");
        query.append(" AND COMPULSAS.ID_ORDEN_AUDITADA = ? ");
        query.append(" AND COMPULSAS.ID_ORDEN_COMPULSA = ? ");
        List<FecetOficio> list = getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(),
                idOrdenAuditada, idOrdenCompulsa);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<FecetOficio> getOficioByIdOficioIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_OFICIO_PRINCIPAL = ? AND O.ID_ESTATUS = ? AND O.ID_OFICIO_PRINCIPAL IS NOT NULL ");

        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOficio, idEstatus);
    }

    @Override
    public List<FecetOficio> getOficioByIdOrdenRechazados(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_RECHAZO_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_ORDEN = ? AND O.ID_ESTATUS = ?");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioRechazoConRelacionesMapper(), idOrden,
                Constantes.ESTATUS_OFICIO_RECHAZADO);
    }

    @Override
    public FecetOficio getOficioObservaciones(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_TIPO_OFICIO = ? \n");
        query.append(QUERY_AND_ID_ORDEN);
        query.append(" AND O.ID_ESTATUS = ? ");
        query.append(QUERY_ORDER_BY_FECHA_FIRMA_ASC);

        List<FecetOficio> list = getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(),
                TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO.getBigIdTipoOficio(), idOrden,
                EstatusOficiosOrdenesEnum.OFICIO_NOTIFICADO_CONTRIBUYENTE.getBigIdEstatus());
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<FecetOficio> getOficiosDependientesByIdOficioPrincipal(final BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_OFICIO_PRINCIPAL = ? AND O.ID_OFICIO_PRINCIPAL IS NOT NULL ");
        logger.trace("SQL OFICIOS DEPENDIENTES {} ", query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOficio);
    }

    @Override
    public List<FecetOficio> getOficioByIdOrdenEstatusPendienteOFirmadoONotificadoNoSurteEfectos(
            final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(
                " WHERE O.ID_ORDEN = ? AND O.ID_ESTATUS IN (?, ?) OR (O.ID_ESTATUS = ? AND O.FECHA_SURTE_EFECTOS > SYSDATE) ");

        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden,
                Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA, Constantes.ESTATUS_OFICIO_FIRMADO_ENVIADO_NYV,
                Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE);
    }

    @Override
    @Deprecated
    public List<FecetOficio> getOficiosPorOrdenEnProcesoOVencidos(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(
                " WHERE O.ID_ORDEN = ? AND O.ID_ESTATUS = ? AND O.FECHA_SURTE_EFECTOS <= SYSDATE AND O.ID_ESTATUS NOT IN (?) ");

        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden,
                Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE, Constantes.ESTATUS_OFICIO_RECHAZADO);
    }

    @Override
    public List<FecetOficio> getOficiosPorOrden(final BigDecimal idOrden, final BigDecimal... idsEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_ORDEN = ? AND O.ID_ESTATUS IN (");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            query.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public List<FecetOficio> getOficiosByIdEstatus(BigDecimal... idsEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_ESTATUS IN (");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            query.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                query.append(",");
            }
        }
        query.append(")");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper());

    }

    public List<FecetOficio> getOficioDependienteByIdEstatusOficioPrincipalTipoOficio(
            final BigDecimal idOficioPrincipal, final BigDecimal idTipoOficio, final BigDecimal idEstatus) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(QUERY_SELECT).append(ALL_COLUMNS).append(QUERY_FROM).append(getTableName());
        sqlWhere.append(" WHERE ID_OFICIO_PRINCIPAL = ? \n ");
        sqlWhere.append(" AND ID_TIPO_OFICIO = ? \n ");
        sqlWhere.append(" AND ID_ESTATUS = ? \n ");

        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetOficioMapper(), idOficioPrincipal,
                idTipoOficio, idEstatus);
    }

    /**
     * @param idOficio
     * @param idEstatus
     * @return listFecetOficio
     */
    @Override
    public List<FecetOficio> getOficiosDependientesPorIdEstatus(final BigDecimal idOficio, final BigDecimal idEstatus) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(QUERY_SELECT);
        sqlWhere.append(SELECT_OFICIOS_CON_RELACIONES);
        sqlWhere.append(WHERE_ID_OFICIO_PRINCIPAL);
        sqlWhere.append(" AND O.ID_ESTATUS = ? ");

        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetOficioContadorAnexosMapper(), idOficio,
                idEstatus);
    }

    @Override
    public List<FecetOficio> getOficiosDependientesFirmados(final BigDecimal idOficio) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(QUERY_SELECT);
        sqlWhere.append(SELECT_OFICIOS_CON_RELACIONES);
        sqlWhere.append(WHERE_ID_OFICIO_PRINCIPAL);
        sqlWhere.append(" AND O.ID_ESTATUS in( ? , ? ) ");

        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetOficioContadorAnexosMapper(), idOficio,
                Constantes.ESTATUS_OFICIO_FIRMADO_ENVIADO_NOTIFACION,
                Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE);
    }

    @Override
    public void updatePlazosOficio(FecetOficio dto) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE FECET_OFICIO SET FECHA_SURTE_EFECTOS = ?,");
        sql.append(" DIAS_RESTANTES_PLAZO = ?,");
        sql.append(" DIAS_RESTANTES_DOCUMENTOS = ?,");
        sql.append(" DIAS_HABILES = ?,");
        sql.append(" ID_ESTATUS = ? ");
        sql.append(" WHERE ID_OFICIO = ?");
        getJdbcTemplateBase().update(sql.toString(), dto.getFechaSurteEfectos(), dto.getDiasRestantesPlazo(),
                dto.getDiasRestantesDocumentos(), dto.getDiasHabiles(), dto.getIdEstatus(), dto.getIdOficio());

    }

    @Override
    public void updateFechasNotificacion(final BigDecimal idOficio, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus) {
        LOG.debug("[updateFechasNotificacion]");
        Object[] parameters = {fechaNotificacionCont, fechaSurteEfectos, idEstatus, idOficio};
        StringBuilder sql = new StringBuilder(SQL_UPDATE);
        sql.append(getTableName()).append(" SET FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, ID_ESTATUS = ? ")
                .append(SQL_WHERE).append(" ID_OFICIO = ? ");
        if (LOG.isDebugEnabled()) {
            LOG.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        getJdbcTemplateBase().update(sql.toString(), parameters);
    }

    @Override
    public List<FecetOficio> obtenerOficioNoAdministrableByIdOrden(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(
                " WHERE O.ID_ORDEN = ? AND (T.ID_AGRUPADOR_TIPO_OFICIO IS NULL OR T.ID_AGRUPADOR_TIPO_OFICIO != 2 ) ");
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public List<FecetOficio> obtenerOficioByIdOrdenOficio(final BigDecimal idOrden,
            final List<BigDecimal> listIdsOficio) {
        LOG.debug("[obtenerOficioByIdOrdenOficio]");
        final int n1 = 1;
        StringBuilder sb = new StringBuilder();
        for (BigDecimal id : listIdsOficio) {
            sb.append(id).append(",");
        }
        Object[] parameters = {idOrden, Constantes.ESTATUS_OFICIO_FIRMADO_ENVIADO_NYV,
            Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE};
        StringBuilder sql = new StringBuilder();
        sql.append(QUERY_SELECT).append(SELECT_OFICIOS_CON_RELACIONES).append(SQL_WHERE)
                .append(" O.ID_ORDEN = ? AND T.ID_TIPO_OFICIO IN ( ")
                .append(sb.toString().substring(0, sb.length() - n1)).append(" )").append(" AND O.ID_ESATUS IN (?, ?) ")
                .append(" ORDER BY O.FECHA_SURTE_EFECTOS ASC ");
        if (LOG.isDebugEnabled()) {
            LOG.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        return getJdbcTemplateBase().query(sql.toString(), new FecetOficioConRelacionesMapper(), parameters);
    }

    @Override
    public void updateOficioIdNyV(FecetOficio oficio, FecetDetalleNyV nYvResult) {
        StringBuilder sql = new StringBuilder();
        String setWhere = " SET ID_NYV = ?  WHERE ID_OFICIO = ? ";
        sql.append(SQL_UPDATE);
        sql.append(getTableName());
        sql.append(setWhere);
        getJdbcTemplateBase().update(sql.toString(), nYvResult.getIdNyV(), oficio.getIdOficio());
    }

    @Override
    public void updateNumeroOficio(FecetOficio oficio, String numeroOficio) {
        StringBuilder sql = new StringBuilder();
        String setWhere = " SET NUMERO_OFICIO = ?  WHERE ID_OFICIO = ? ";
        sql.append(SQL_UPDATE);
        sql.append(getTableName());
        sql.append(setWhere);
        getJdbcTemplateBase().update(sql.toString(), numeroOficio, oficio.getIdOficio());
    }

    @Override
    public List<FecetOficio> getOficioPorIdTipoOficioYorden(BigDecimal idOrden, TiposOficiosOrdenesEnum oficio) {
        StringBuilder sql = new StringBuilder();
        sql.append(QUERY_SELECT).append(ALL_COLUMNS).append(QUERY_FROM).append(getTableName());
        sql.append(SQL_WHERE).append(" ID_ORDEN = ? AND ID_TIPO_OFICIO = ? AND ID_ESTATUS IN (?, ?)");
        sql.append(" ORDER BY FECHA_FIRMA ASC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetOficioMapper(), idOrden, oficio.getIdTipoOficio(),
                Constantes.ESTATUS_OFICIO_FIRMADO_ENVIADO_NYV, Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE);
    }

    @Override
    public List<FecetOficio> getOficiosDependientesPorIdEstatusContribuyente(final BigDecimal idOficio) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(QUERY_SELECT);
        sqlWhere.append(SELECT_OFICIOS_CON_RELACIONES);
        sqlWhere.append(" WHERE ");
        sqlWhere.append(" O.ID_OFICIO_PRINCIPAL = ? \n ");
        sqlWhere.append(" AND O.FECHA_FIRMA IS NOT NULL \n ");
        sqlWhere.append(" AND O.ID_ESTATUS in( ? , ? , ?,?) ");

        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetOficioContadorAnexosMapper(), idOficio,
                Constantes.ESTATUS_OFICIO_FIRMADO_ENVIADO_NYV, Constantes.ESTATUS_OFICIO_NOTIFICADO_CONTRIBUYENTE,
                Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA, Constantes.ESTATUS_OFICIO_RECHAZADO);
    }

    @Override
    public void updateBlnActivoCero(BigDecimal idOficio) {
        StringBuilder sql = new StringBuilder();
        String setWhere = " SET BLN_ACTIVO = 0 WHERE ID_OFICIO = ? ";
        sql.append(SQL_UPDATE);
        sql.append(getTableName());
        sql.append(setWhere);
        getJdbcTemplateBase().update(sql.toString(), idOficio);
    }

    public List<FecetOficio> getOficiosAdministrablesPorIdOrden(BigDecimal idOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(QUERY_SELECT).append(SELECT_OFICIOS_ADMIN);
        sql.append(" INNER JOIN ");
        sql.append(FecetTipoOficioDaoImpl.getTableName());
        sql.append(" TOF ");
        sql.append(" ON O.ID_TIPO_OFICIO = TOF.ID_TIPO_OFICIO ");
        sql.append(" INNER JOIN FECEC_AGRUPADOR_TIPO_OFICIO  ATO");
        sql.append(" ON TOF.ID_AGRUPADOR_TIPO_OFICIO = ATO.ID_AGRUPADOR_TIPO_OFICIO ");
        sql.append("\n WHERE O.ID_ORDEN = ? ");
        sql.append("\n AND TOF.ID_AGRUPADOR_TIPO_OFICIO = 2  ");
        sql.append("\n AND O.BLN_ACTIVO = 1");

        return getJdbcTemplateBase().query(sql.toString(), new FecetOficioMapper(), idOrden);
    }

    public String accionesOrdenes() {
        return AccionesFuncionarioEnum.VALIDACION_FIRMANTE.getIdAccion() + ","
                + AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.APROBACION_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.FIRMA_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.APROBACION_VALIDACION.getIdAccion() + ","
                + AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion();
    }

    @Override
    public List<FecetOficio> getOficiosAsociadosByOrden(BigDecimal idOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SELECT_OFICIOS_ASOCIADOS_ORDEN);
        sql.append(" WHERE O.ID_ORDEN = ? AND O.ID_ESTATUS IN (");
        sql.append(Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA).append(" ) ORDER BY ID_OFICIO DESC ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetOficioMapper(), idOrden);
    }

    @Override
    public void updateOficioByIdOrden(BigDecimal idOrden) {
        StringBuilder sqlUpdate = new StringBuilder();

        sqlUpdate.append("UPDATE FECET_OFICIO SET BLN_ACTIVO = ");
        sqlUpdate.append(Constantes.ENTERO_CERO);
        sqlUpdate.append(" WHERE ID_ORDEN = ? ");
        sqlUpdate.append(" AND BLN_ACTIVO =  ");
        sqlUpdate.append(Constantes.CERO);
        sqlUpdate.append(" AND ID_ESTATUS = ");
        sqlUpdate.append(Constantes.ESTATUS_OFICIO_PENDIENTE_FIRMA);

        getJdbcTemplateBase().update(sqlUpdate.toString(), idOrden);
    }

    @Override
    public void updateOficioByIdOficio(BigDecimal idOficio) {
        StringBuilder sqlUpdate = new StringBuilder();

        sqlUpdate.append("UPDATE FECET_OFICIO SET BLN_ACTIVO = ");
        sqlUpdate.append(Constantes.ENTERO_UNO);
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");

        getJdbcTemplateBase().update(sqlUpdate.toString(), idOficio);

    }

    @Override
    public void updateFechaFirma(FecetOficio oficio) {
        StringBuilder sqlUpdate = new StringBuilder();

        sqlUpdate.append("UPDATE FECET_OFICIO SET FECHA_FIRMA = ?");
        sqlUpdate.append(" WHERE ID_OFICIO = ? ");
        getJdbcTemplateBase().update(sqlUpdate.toString(), oficio.getFechaFirma(), oficio.getIdOficio());
    }

    @Override
    public List<FecetOficio> getOficioActivosByIdOrden(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(QUERY_SELECT);
        query.append(SELECT_OFICIOS_CON_RELACIONES);
        query.append(" WHERE O.ID_ORDEN = ? AND O.BLN_ACTIVO = ");
        query.append(Constantes.UNO);
        return getJdbcTemplateBase().query(query.toString(), new FecetOficioConRelacionesMapper(), idOrden);
    }

    @Override
    public String obtenerNumeroReferenciaOficio(Long idOficio) {
        return getJdbcTemplateBase().queryForObject(SELECT_NO_REFERENCIA_OFICIO, String.class, idOficio);
    }

    @Override
    public void updateNumeroReferenciaOficio(String noReferencia, Long idOficio) {
        getJdbcTemplateBase().update(UPDATE_NO_REFERENCIA_OFICIO, noReferencia, idOficio);
    }

}
