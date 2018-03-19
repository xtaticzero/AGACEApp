package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexosProrrogaOrdenDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl.FecetDocProrrogaOrdenDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl.FecetAsociadoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaContadorDocsMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaOrdenConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetProrrogaOrdenDao")
public class FecetProrrogaOrdenDaoImpl extends BaseJDBCDao<FecetProrrogaOrden> implements FecetProrrogaOrdenDao {

    private static final long serialVersionUID = 1L;

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_PRORROGA_ORDEN
     */
    private static final String SQL_SELECT = "SELECT ";

    private static final String SQL_INNER_ID_PRORROGA_ORDEN = " ON P.ID_PRORROGA_ORDEN = FP.ID_PRORROGA_ORDEN \n";

    private static final String SQL_FROM = " FROM ";

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_ORDER = " ORDER BY ";
    private static final String SQL_ORDER_BY = " ORDER BY P.ID_ORDEN ";

    private static final String NAME_TABLE_PRORROGA_ORDEN = "FECET_FLUJO_PRORROGA_ORDEN";

    private static final String SELECT_NO_REFERENCIA_PRO_ORD = "SELECT NUMERO_REFERENCIA FROM FECET_PRORROGA_ORDEN WHERE ID_PRORROGA_ORDEN = ?";

    private static final String UPDATE_NO_REFERENCIA_PRO_ORD = "UPDATE FECET_PRORROGA_ORDEN  SET NUMERO_REFERENCIA = ? WHERE ID_PRORROGA_ORDEN = ? AND NUMERO_REFERENCIA IS NULL";

    private static final StringBuilder SQL_ALL_COLUMNS = new StringBuilder(
            "   ID_PRORROGA_ORDEN, ID_ORDEN, FECHA_CARGA, RUTA_ACUSE, ")
            .append("   CADENA_CONTRIBUYENTE, FIRMA_CONTRIBUYENTE, APROBADA, ID_ASOCIADO_CARGA, ")
            .append("   ID_AUDITOR, ID_FIRMANTE, RUTA_RESOLUCION, ")
            .append("   CADENA_FIRMANTE, FIRMA_FIRMANTE, FECHA_FIRMA, ID_ESTATUS, FOLIO_NYV, ")
            .append(" FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, ").append("   FECHA_SURTE_EFECTOS, ID_NYV ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    private static final StringBuilder SQL_LEFT_JOIN = new StringBuilder(" LEFT JOIN ");

    private static final StringBuilder SQL_FP = new StringBuilder(" FP ");

    private static final StringBuilder SQL_UPDATE_PRORROGA_RESOLUCION = new StringBuilder(
            " SET RUTA_RESOLUCION = ?, ID_AUDITOR = ?  WHERE ID_PRORROGA_ORDEN = ? ");

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    private static final StringBuilder SQL_UPDATE_PRORROGA = new StringBuilder(" SET ID_ORDEN = ?,  FECHA_CARGA = ?, ")
            .append(" RUTA_ACUSE = ?, CADENA_CONTRIBUYENTE = ?, FIRMA_CONTRIBUYENTE = ?, ")
            .append(" APROBADA = ?, ID_ASOCIADO_CARGA = ?, ID_AUDITOR = ?, ID_FIRMANTE = ?, ")
            .append(" RUTA_RESOLUCION = ?, CADENA_FIRMANTE = ?, FIRMA_FIRMANTE = ?, ")
            .append(" FECHA_FIRMA = ?, ID_ESTATUS = ?, FOLIO_NYV = ?, ")
            .append(" FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ? WHERE ID_PRORROGA_ORDEN = ?");

    /**
     * este atributo corresponde a una funcion SELECT para traer datos de tabla
     * FECET_PRORROGA_ORDEN
     */
    private static final StringBuilder SELECT_PRORROGAS_RELACIONES = new StringBuilder(" SELECT \n")
            .append(" P.ID_PRORROGA_ORDEN, P.ID_ORDEN, P.FECHA_CARGA, P.RUTA_ACUSE, P.CADENA_CONTRIBUYENTE, \n")
            .append(" P.FIRMA_CONTRIBUYENTE, P.APROBADA, P.ID_ASOCIADO_CARGA, P.ID_AUDITOR, P.ID_FIRMANTE, \n")
            .append(" P.RUTA_RESOLUCION, P.CADENA_FIRMANTE, P.FIRMA_FIRMANTE, P.FECHA_FIRMA, \n")
            .append(" P.ID_ESTATUS, P.FOLIO_NYV, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, \n")
            .append(" AC.ID_ASOCIADO, AC.ID_ORDEN, AC.ID_TIPO_ASOCIADO, AC.NOMBRE, AC.RFC, AC.CORREO, AC.TIPO_ASOCIADO, AC.MEDIO_CONTACTO, \n")
            .append(" AC.FECHA_BAJA, AC.FECHA_ULTIMA_MOD, AC.FECHA_ULTIMA_MOD_IDC, AC.ESTATUS, AC.RFC_CONTRIBUYENTE, \n")
            .append(" (SELECT COUNT(D.ID_DOC_PRORROGA_ORDEN) FROM ").append(FecetDocProrrogaOrdenDaoImpl.getTableName())
            .append(" D WHERE D.ID_PRORROGA_ORDEN = P.ID_PRORROGA_ORDEN) TOTAL_DOCUMENTOS, \n")
            .append(" (SELECT COUNT(A.ID_ANEXOS_PRORROGA_ORDEN) FROM ")
            .append(FecetAnexosProrrogaOrdenDaoImpl.getTableName())
            .append(" A WHERE A.ID_FLUJO_PRORROGA_ORDEN = FP.ID_FLUJO_PRORROGA_ORDEN) TOTAL_DOCUMENTOS_RECHAZO \n ")
            .append(" , E.ID_ESTATUS, E.DESCRIPCION, E.MODULO, E.ID_MODULO")
            .append(" , FP.ID_FLUJO_PRORROGA_ORDEN, FP.ID_PRORROGA_ORDEN, FP.FECHA_CREACION, FP.JUSTIFICACION, FP.JUSTIFICACION_FIRMANTE, FP.APROBADA AS FLUJO_APROBADA, FP.ID_ESTATUS ESTATUS_FLUJO, FP.FECHA_RECHAZO_FIRMANTE")
            .append(" FROM ").append(getTableName()).append(" P ").append(SQL_LEFT_JOIN)
            .append(FecetAsociadoDaoImpl.getTableName()).append(" AC ")
            .append(" ON P.ID_ASOCIADO_CARGA = AC.ID_ASOCIADO \n").append(" INNER JOIN ")
            .append(FececEstatusDaoImpl.getTableName()).append(" E ").append(" ON P.ID_ESTATUS = E.ID_ESTATUS \n");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_WHERE_ORDER_FIRMANTE = new StringBuilder(
            " WHERE ID_ORDEN = ? AND APROBADA IS NOT NULL ORDER BY ID_ORDEN ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    /**
     * este atributo corresponde a un complemento de funcion DELETE para
     * eliminar por criterio de IdOrden
     */
    private static final StringBuilder SQL_DELETE = new StringBuilder("DELETE FROM ");

    /**
     * Este metodo es un SELECT para obtener los datos de un contador del numero
     * de pruebas y alegatos
     */
    private StringBuilder getSqlSelectContadorDocProrroga() {
        StringBuilder query = new StringBuilder();

        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PRO.ID_PRORROGA_ORDEN, \n");
        query.append("    PRO.ID_ORDEN, \n");
        query.append("    PRO.FECHA_CARGA, \n");
        query.append("    PRO.RUTA_ACUSE,\n");
        query.append("    PRO.CADENA_CONTRIBUYENTE, \n");
        query.append("    PRO.FIRMA_CONTRIBUYENTE, \n");
        query.append("    PRO.APROBADA, \n");
        query.append("    PRO.ID_ASOCIADO_CARGA, \n");
        query.append("    PRO.ID_AUDITOR, \n");
        query.append("    PRO.ID_FIRMANTE, \n");
        query.append("    PRO.RUTA_RESOLUCION, \n");
        query.append("    PRO.CADENA_FIRMANTE, \n");
        query.append("    PRO.FIRMA_FIRMANTE,\n");
        query.append("    PRO.FECHA_FIRMA, \n");
        query.append("    PRO.ID_ESTATUS, \n");
        query.append("    PRO.FOLIO_NYV, \n");
        query.append("    PRO.FECHA_NOTIF_NYV, \n");
        query.append("    PRO.FECHA_NOTIF_CONT, \n");
        query.append("    PRO.FECHA_SURTE_EFECTOS, \n");
        query.append("    PRO.ID_NYV, \n");
        query.append("    (SELECT COUNT(0) FROM ");
        query.append(FecetDocProrrogaOrdenDaoImpl.getTableName());
        query.append(" DOCPRO WHERE PRO.ID_PRORROGA_ORDEN = DOCPRO.ID_PRORROGA_ORDEN) TOTAL_DOC_PRORROGA,  \n");
        query.append("    ASOCIADO.ID_ASOCIADO, \n");
        query.append("    ASOCIADO.RFC_CONTRIBUYENTE, \n");
        query.append("    ASOCIADO.RFC, \n");
        query.append("    ASOCIADO.ID_ORDEN, \n");
        query.append("    ASOCIADO.ID_TIPO_ASOCIADO, \n");
        query.append("    ASOCIADO.NOMBRE, \n");
        query.append("    ASOCIADO.CORREO, \n");
        query.append("    ASOCIADO.TIPO_ASOCIADO, \n");
        query.append("    ASOCIADO.FECHA_BAJA, \n");
        query.append("    ASOCIADO.FECHA_ULTIMA_MOD, \n");
        query.append("    ASOCIADO.FECHA_ULTIMA_MOD_IDC, \n");
        query.append("    ASOCIADO.MEDIO_CONTACTO, \n");
        query.append("    ASOCIADO.ESTATUS, \n");
        query.append("    ESTATUS.ID_ESTATUS, \n");
        query.append("    ESTATUS.DESCRIPCION, \n");
        query.append("    ESTATUS.MODULO, \n");
        query.append("    ESTATUS.ID_MODULO \n");

        query.append("FROM ");
        query.append(getTableName());
        query.append(" PRO \n");
        query.append(" INNER JOIN FECEC_ESTATUS ESTATUS \n");
        query.append("   ON PRO.ID_ESTATUS = ESTATUS.ID_ESTATUS \n");
        query.append(" LEFT JOIN FECET_ASOCIADO ASOCIADO \n");
        query.append("   ON PRO.ID_ASOCIADO_CARGA = ASOCIADO.ID_ASOCIADO \n");
        return query;
    }

    private static final StringBuilder SQL_WHERE_ORDEN_PRORROGA = new StringBuilder(
            " WHERE PRO.ID_ORDEN = ? ORDER BY PRO.ID_PRORROGA_ORDEN ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetProrrogaOrdenPk
     */
    @Override
    public FecetProrrogaOrdenPk insert(FecetProrrogaOrden dto) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdProrrogaOrden(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaAcuse(), dto.getCadenaContribuyente(), dto.getFirmaContribuyente(), dto.getAprobada(),
                dto.getIdAsociadoCarga(), dto.getIdAuditor(), dto.getIdFirmante(), dto.getRutaResolucion(),
                dto.getCadenaFirmante(), dto.getFirmaFirmante(), dto.getFechaFirma(), dto.getIdEstatus(),
                dto.getFolioNyV(), dto.getFechaNotifNyV(), dto.getFechaNotifCont(), dto.getFechaSuerteEfectos(), null);
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    @Override
    public void update(FecetProrrogaOrdenPk pk, FecetProrrogaOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRORROGA);

        getJdbcTemplateBase().update(query.toString(), dto.getIdOrden(), dto.getFechaCarga(), dto.getRutaAcuse(),
                dto.getCadenaContribuyente(), dto.getFirmaContribuyente(), dto.getAprobada(), dto.getIdAsociadoCarga(),
                dto.getIdAuditor(), dto.getIdFirmante(), dto.getRutaResolucion(), dto.getCadenaFirmante(),
                dto.getFirmaFirmante(), dto.getFechaFirma(), dto.getIdEstatus(), dto.getFolioNyV(),
                dto.getFechaNotifNyV(), dto.getFechaNotifCont(), dto.getFechaSuerteEfectos(), pk.getIdProrrogaOrden());

    }

    /**
     * @param dto
     */
    @Override
    public void updateProrrogaFirma(FecetProrrogaOrden dto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRORROGA);
        getJdbcTemplateBase().update(query.toString(), dto.getIdOrden(), dto.getFechaCarga(), dto.getRutaAcuse(),
                dto.getCadenaContribuyente(), dto.getFirmaContribuyente(), dto.getAprobada(), dto.getIdAsociadoCarga(),
                dto.getIdAuditor(), dto.getIdFirmante(), dto.getRutaResolucion(), dto.getCadenaFirmante(),
                dto.getFirmaFirmante(), dto.getFechaFirma(), dto.getIdEstatus(), dto.getFolioNyV(),
                dto.getFechaNotifNyV(), dto.getFechaNotifCont(), dto.getFechaSuerteEfectos(), dto.getIdProrrogaOrden());
    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN in column VALIDADA .
     */
    @Override
    public void validaProrrogaAuditor(final BigDecimal idProrrogaOrden, final String estatusAprobada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET APROBADA = ");
        query.append(estatusAprobada).append(" where ID_PRORROGA_ORDEN = ").append(idProrrogaOrden.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN in column ESTADO .
     */
    @Override
    public void validaProrrogaFirmante(final BigDecimal idProrroga, final String estatusValidada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ESTADO = ");
        query.append(estatusValidada).append(" where ID_PRORROGA_ORDEN = ").append(idProrroga.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Deletes a single row in the FECET_PRORROGA_ORDEN table.
     */
    @Override
    public void delete(FecetProrrogaOrdenPk pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdProrrogaOrden());

    }

    /**
     * Metodo getIdFecetProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal @
     */
    @Override
    public BigDecimal getIdFecetProrrogaOrdenPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PRORROGA_ORDEN";
    }

    /**
     * Metodo buscaUltimaProrrogaPorOrden
     *
     * @param orden
     * @return @
     */
    @Override
    public List<FecetProrrogaOrden> buscaUltimaProrrogaPorOrden(AgaceOrden orden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName());
        query.append(" WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRORROGA_ORDEN WHERE ID_ORDEN = ?)");

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), orden.getIdOrden());

    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    @Override
    public void actualizarPorEstadoYFechaMax(FecetProrrogaOrden prorroga, AgaceOrden orden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_UPDATE).append(getTableName());
        query.append(" SET ID_ESTATUS = ? WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRORROGA_ORDEN ");
        query.append(" WHERE ID_ORDEN = ?)");

        getJdbcTemplateBase().update(query.toString(), prorroga.getIdEstatus(), orden.getIdOrden());

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrroga'.
     */
    @Override
    public FecetProrrogaOrden findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_ORDEN = ?");
        List<FecetProrrogaOrden> list = getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(),
                idProrroga);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * ID_ORDEN sea igual al que se le esta mandando
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrden(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRORROGA_ORDEN)
                .append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN).append(" WHERE P.ID_ORDEN = ? ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea Firmada
     */
    @Override
    public List<FecetProrrogaOrden> getHistoricoProrrogaPorOrden(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES).append(" INNER JOIN ").append(NAME_TABLE_PRORROGA_ORDEN)
                .append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN);
        query.append(" WHERE P.ID_ORDEN = ? ");
        query.append(" ORDER BY P.ID_PRORROGA_ORDEN, FP.ID_FLUJO_PRORROGA_ORDEN ");

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuydo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea Firmada
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrdenFirmada(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES);
        query.append(SQL_LEFT_JOIN).append(NAME_TABLE_PRORROGA_ORDEN);
        query.append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN);
        query.append(" WHERE P.ID_ORDEN = ? \n");
        query.append(" AND FP.ID_ESTATUS = ? \n");
        query.append(SQL_ORDER_BY);
        logger.debug(" [PRORROGAS DE LA ORDEN] ");
        logger.debug("SQL : {} ", query.toString());
        logger.debug("PARAMS : {} ", idOrden);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden,
                EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus());

    }

    /**
     * @param idOrden
     * @return List FecetProrrogaOrden pendientes de firmar
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrdenPorFirmar(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRORROGA_ORDEN)
                .append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN);
        query.append(" WHERE P.ID_ORDEN = ? \n");
        query.append(" AND FP.ID_ESTATUS in ( ? ) \n");
        query.append(SQL_ORDER_BY);
        logger.debug(" {} ", query);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden,
                EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());
    }

    /**
     * @param idOrden
     * @return List FecetProrrogaOrden Prorrogas Fimadas
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrdenFirmante(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(SQL_WHERE_ORDER_FIRMANTE);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    @Override
    public List<FecetProrrogaOrden> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" ORDER BY ID_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper());

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRORROGA_ORDEN = :idProrroga'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_PRORROGA_ORDEN = ? ORDER BY ID_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idProrroga);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereIdOrdenEquals(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_CARGA = :fechaCarga'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereFechaCargaEquals(Date fechaCarga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), fechaCarga);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereAprobadaEquals(final String aprobada) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE APROBADA = ? ORDER BY APROBADA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), aprobada);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_ASOCIADO_CARGA = :idContribuyente'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_ASOCIADO_CARGA = ? ORDER BY ID_ASOCIADO_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idContribuyente);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_AUDITOR = :idAuditor'.
     */
    public List<FecetProrrogaOrden> findWhereIdAuditorEquals(final BigDecimal idAuditor) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_AUDITOR = ? ORDER BY ID_AUDITOR");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idAuditor);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ID_FIRMANTE = :idFirmante'.
     */
    public List<FecetProrrogaOrden> findWhereIdFirmanteEquals(final BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_FIRMANTE = ? ORDER BY ID_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idFirmante);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_FIRMA = :fechaFirma'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereFechaFirmaEquals(Date fechaFirma) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE FECHA_FIRMA = ? ORDER BY FECHA_FIRMA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), fechaFirma);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_ORDEN table that match the
     * criteria 'ESTADO = :estado'.
     */
    @Override
    public List<FecetProrrogaOrden> findWhereIdEstatusIdOrdenEquals(final BigDecimal estado, BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ? AND ID_ESTATUS = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idOrden, estado);
    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuyo valor del
     * 'ID_ORDEN sea igual al que se le está mandando
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendiente(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRORROGA_ORDEN)
                .append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN).append(" WHERE P.ID_ORDEN = ? AND P.ID_ESTATUS <> ? ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden,
                Constantes.ESTATUS_RESOLUCION_PRORROGA_FIRMADA_ENVIADA_NYV);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_ORDEN cuyo valor del
     * 'ID_ORDEN sea igual al que se le esta mandando
     */
    @Override
    public List<FecetProrrogaOrden> getProrrogaPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SELECT_PRORROGAS_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRORROGA_ORDEN)
                .append(SQL_FP);
        query.append(SQL_INNER_ID_PRORROGA_ORDEN);
        query.append(" WHERE P.ID_ORDEN = ? ");
        query.append(" AND P.ID_ESTATUS = ? ");
        query.append(" AND (FP.ID_FLUJO_PRORROGA_ORDEN IS NULL OR FP.ID_ESTATUS = ? ) ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenConRelacionesMapper(), idOrden,
                EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus(),
                EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus());
    }

    /**
     * Returns the rows from the FECET_PRORROGA_ORDEN table that matches the
     * specified primary-key value.
     */
    public FecetProrrogaOrden findByPrimaryKey(FecetProrrogaOrdenPk pk) {
        return findByPrimaryKey(pk.getIdProrrogaOrden());
    }

    public List<FecetProrrogaOrden> getProrrogaContadorDoc(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(getSqlSelectContadorDocProrroga());
        query.append(SQL_WHERE_ORDEN_PRORROGA);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaContadorDocsMapper(), idOrden);

    }

    @Override
    public List<FecetProrrogaOrden> findWhereIdOrdenEstatusEquals(final BigDecimal idOrden,
            final BigDecimal... idsEstatus) {
        logger.debug("[findWhereIdOrdenEstatusEquals]");
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(SQL_WHERE).append(" ID_ORDEN = ?");
        sql.append(SQL_AND).append(" APROBADA = 1 ");
        sql.append(SQL_AND).append(" ID_ESTATUS IN ( ");
        int cont = 0;
        for (BigDecimal estatus : idsEstatus) {
            sql.append(estatus);
            if (cont++ < idsEstatus.length - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        sql.append(SQL_ORDER).append(" FECHA_SURTE_EFECTOS DESC ");
        return getJdbcTemplateBase().query(sql.toString(), new FecetProrrogaOrdenMapper(), idOrden);
    }

    @Override
    public void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus) {
        logger.debug("[updateFechasNotificacion]");
        Object[] parameters = {fechaNotificacionCont, fechaSurteEfectos, idEstatus, idOrden};
        StringBuilder sql = new StringBuilder(SQL_UPDATE);
        sql.append(getTableName()).append(" SET FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, ID_ESTATUS = ? ")
                .append(SQL_WHERE).append(" ID_ORDEN = ? ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        getJdbcTemplateBase().update(sql.toString(), parameters);
    }

    @Override
    public void guardarFirma(FirmaDTO firma) {
        ProrrogaDocsVO dto = (ProrrogaDocsVO) firma.getObjectoFirma();
        StringBuilder query = new StringBuilder();
        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        getJdbcTemplateBase().update(query.toString(), dto.getProrrogaOrden().getIdProrrogaOrden(),
                dto.getProrrogaOrden().getIdOrden(), dto.getProrrogaOrden().getFechaCarga(),
                dto.getProrrogaOrden().getRutaAcuse(), dto.getProrrogaOrden().getCadenaContribuyente(),
                dto.getProrrogaOrden().getFirmaContribuyente(), dto.getProrrogaOrden().getAprobada(),
                dto.getProrrogaOrden().getIdAsociadoCarga(), dto.getProrrogaOrden().getIdAuditor(),
                dto.getProrrogaOrden().getIdFirmante(), dto.getProrrogaOrden().getRutaResolucion(),
                dto.getProrrogaOrden().getCadenaFirmante(), dto.getProrrogaOrden().getFirmaFirmante(),
                dto.getProrrogaOrden().getFechaFirma(), dto.getProrrogaOrden().getIdEstatus(),
                dto.getProrrogaOrden().getFolioNyV(), dto.getProrrogaOrden().getFechaNotifNyV(),
                dto.getProrrogaOrden().getFechaNotifCont(), dto.getProrrogaOrden().getFechaSuerteEfectos(), null);
    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    public void updateProrrogaResolucion(FecetProrrogaOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRORROGA_RESOLUCION);

        getJdbcTemplateBase().update(query.toString(), dto.getRutaResolucion(), dto.getIdAuditor(),
                dto.getIdProrrogaOrden());

    }

    @Override
    public List<FecetProrrogaOrden> getProrrogaPendienteAprobPorOrden(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ? AND ID_ESTATUS = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOrdenMapper(), idOrden,
                EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus());
    }

    @Override
    public String obtenerNumeroReferenciaProrOrden(Long idProOrden) {
        return getJdbcTemplateBase().queryForObject(SELECT_NO_REFERENCIA_PRO_ORD, String.class, idProOrden);
    }

    @Override
    public void updateNumeroReferenciaProrOrden(String noReferencia, Long idProOrden) {
        getJdbcTemplateBase().update(UPDATE_NO_REFERENCIA_PRO_ORD, noReferencia, idProOrden);
    }

}
