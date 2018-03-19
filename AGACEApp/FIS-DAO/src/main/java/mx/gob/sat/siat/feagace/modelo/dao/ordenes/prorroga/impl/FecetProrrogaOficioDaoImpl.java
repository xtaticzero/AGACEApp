package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexosProrrogaOficioDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl.FecetDocProrrogaOficioDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl.FecetAsociadoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaOficioConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaOficioContadorDocsMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetProrrogaOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetProrrogaOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ProrrogaDocsVO;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusFlujoProrroga;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;

@Repository("fecetProrrogaOficioDao")
public class FecetProrrogaOficioDaoImpl extends BaseJDBCDao<FecetProrrogaOficio> implements FecetProrrogaOficioDao {

    private static final long serialVersionUID = 1L;

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_PRORROGA_OFICIO
     */
    private static final String SQL_SELECT = "SELECT ";

    private static final String WHERE_P_ID_OFICIO = " WHERE P.ID_OFICIO = ? ";

    private static final String SQL_FROM = " FROM ";
    private static final String SQL_ORDER = " ORDER BY ";

    private static final String SQL_ORDER_BY = " ORDER BY P.ID_OFICIO ";

    private static final StringBuilder SQL_ALL_COLUMNS = new StringBuilder(
            " ID_PRORROGA_OFICIO, ID_OFICIO, FECHA_CARGA, RUTA_ACUSE, ")
            .append("   CADENA_CONTRIBUYENTE, FIRMA_CONTRIBUYENTE, APROBADA, ID_ASOCIADO_CARGA, ")
            .append("   ID_AUDITOR, ID_FIRMANTE, RUTA_RESOLUCION, ")
            .append("   CADENA_FIRMANTE, FIRMA_FIRMANTE, FECHA_FIRMA, ID_ESTATUS, FOLIO_NYV, ")
            .append(" FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, ").append("   FECHA_SURTE_EFECTOS, ID_NYV ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PRORROGA_OFICIO.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    private static final String UPDATE = " UPDATE ";
    private static final String WHERE = " WHERE ";

    private static final String SELECT_NO_REFERENCIA_PRO_OFI = "SELECT NUMERO_REFERENCIA FROM FECET_PRORROGA_OFICIO WHERE ID_PRORROGA_OFICIO = ?";

    private static final String UPDATE_NO_REFERENCIA_PRO_OFI = "UPDATE FECET_PRORROGA_OFICIO SET NUMERO_REFERENCIA = ? WHERE ID_PRORROGA_OFICIO = ? AND NUMERO_REFERENCIA IS NULL";

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    private static final StringBuilder SQL_UPDATE_PRORROGA = new StringBuilder(" SET ID_OFICIO = ?,  FECHA_CARGA = ?, ")
            .append(" RUTA_ACUSE = ?, CADENA_CONTRIBUYENTE = ?, FIRMA_CONTRIBUYENTE = ?, ")
            .append(" APROBADA = ?, ID_ASOCIADO_CARGA = ?, ID_AUDITOR = ?, ID_FIRMANTE = ?, ")
            .append(" RUTA_RESOLUCION = ?, CADENA_FIRMANTE = ?, FIRMA_FIRMANTE = ?, ")
            .append(" FECHA_FIRMA = ?, ID_ESTATUS = ?, FOLIO_NYV = ?, ")
            .append(" FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ? WHERE ID_PRORROGA_OFICIO = ?");

    private static final StringBuilder SQL_UPDATE_PRORROGA_RESOLUCION = new StringBuilder(
            " SET RUTA_RESOLUCION = ?, ID_AUDITOR = ?  WHERE ID_PRORROGA_OFICIO = ? ");

    /**
     * este atributo corresponde a una funcion SELECT para traer datos de tabla
     * FECET_PRORROGA_OFICIO
     */
    private static final StringBuilder SELECT_PRORROGAS_RELACIONES = new StringBuilder(" SELECT \n")
            .append(" P.ID_PRORROGA_OFICIO, P.ID_OFICIO, P.FECHA_CARGA, P.RUTA_ACUSE, P.CADENA_CONTRIBUYENTE, \n")
            .append(" P.FIRMA_CONTRIBUYENTE, P.APROBADA, P.ID_ASOCIADO_CARGA, P.ID_AUDITOR, P.ID_FIRMANTE, \n")
            .append(" P.RUTA_RESOLUCION, P.CADENA_FIRMANTE, P.FIRMA_FIRMANTE, P.FECHA_FIRMA, \n")
            .append(" P.ID_ESTATUS, P.FOLIO_NYV, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, \n")
            .append(" AC.ID_ASOCIADO, AC.ID_ORDEN, AC.ID_TIPO_ASOCIADO, AC.NOMBRE, AC.RFC, AC.CORREO, AC.TIPO_ASOCIADO, AC.MEDIO_CONTACTO, \n")
            .append(" AC.FECHA_BAJA, AC.FECHA_ULTIMA_MOD, AC.FECHA_ULTIMA_MOD_IDC, AC.ESTATUS, AC.RFC_CONTRIBUYENTE, \n")
            .append(" (SELECT COUNT(D.ID_DOC_PRORROGA_OFICIO) FROM ")
            .append(FecetDocProrrogaOficioDaoImpl.getTableName())
            .append(" D WHERE D.ID_PRORROGA_OFICIO = P.ID_PRORROGA_OFICIO) TOTAL_DOCUMENTOS, \n")
            .append(" (SELECT COUNT(A.ID_ANEXOS_PRORROGA_OFICIO) FROM ")
            .append(FecetAnexosProrrogaOficioDaoImpl.getTableName())
            .append(" A WHERE A.ID_FLUJO_PRORROGA_OFICIO = FP.ID_FLUJO_PRORROGA_OFICIO) TOTAL_DOCUMENTOS_RECHAZO \n ")
            .append(" , E.ID_ESTATUS, E.DESCRIPCION, E.MODULO, E.ID_MODULO")
            .append(" , FP.ID_FLUJO_PRORROGA_OFICIO, FP.ID_PRORROGA_OFICIO, FP.FECHA_CREACION, FP.JUSTIFICACION, FP.JUSTIFICACION_FIRMANTE, FP.APROBADA AS FLUJO_APROBADA, FP.ID_ESTATUS ESTATUS_FLUJO, FP.FECHA_RECHAZO_FIRMANTE")
            .append(" FROM ").append(getTableName()).append(" P LEFT JOIN ").append(FecetAsociadoDaoImpl.getTableName())
            .append(" AC ").append(" ON P.ID_ASOCIADO_CARGA = AC.ID_ASOCIADO \n").append(" INNER JOIN ")
            .append(FececEstatusDaoImpl.getTableName()).append(" E ").append(" ON P.ID_ESTATUS = E.ID_ESTATUS \n")
            .append(" LEFT JOIN ").append(FecetFlujoProrrogaOficioDaoImpl.getTableName()).append(" FP ")
            .append(" ON P.ID_PRORROGA_OFICIO = FP.ID_PRORROGA_OFICIO \n");

    private static final StringBuilder SELECT_PRORROGAS_RELACIONES_HISTORICO = new StringBuilder(" SELECT \n")
            .append(" P.ID_PRORROGA_OFICIO, P.ID_OFICIO, P.FECHA_CARGA, P.RUTA_ACUSE, P.CADENA_CONTRIBUYENTE, \n")
            .append(" P.FIRMA_CONTRIBUYENTE, P.APROBADA, P.ID_ASOCIADO_CARGA, P.ID_AUDITOR, P.ID_FIRMANTE, \n")
            .append(" P.RUTA_RESOLUCION, P.CADENA_FIRMANTE, P.FIRMA_FIRMANTE, P.FECHA_FIRMA, \n")
            .append(" P.ID_ESTATUS, P.FOLIO_NYV, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, \n")
            .append(" AC.ID_ASOCIADO, AC.ID_ORDEN, AC.ID_TIPO_ASOCIADO, AC.NOMBRE, AC.RFC, AC.CORREO, AC.TIPO_ASOCIADO, AC.MEDIO_CONTACTO, \n")
            .append(" AC.FECHA_BAJA, AC.FECHA_ULTIMA_MOD, AC.FECHA_ULTIMA_MOD_IDC, AC.ESTATUS, AC.RFC_CONTRIBUYENTE, \n")
            .append(" (SELECT COUNT(D.ID_DOC_PRORROGA_OFICIO) FROM ")
            .append(FecetDocProrrogaOficioDaoImpl.getTableName())
            .append(" D WHERE D.ID_PRORROGA_OFICIO = P.ID_PRORROGA_OFICIO) TOTAL_DOCUMENTOS, \n")
            .append(" (SELECT COUNT(A.ID_ANEXOS_PRORROGA_OFICIO) FROM ")
            .append(FecetAnexosProrrogaOficioDaoImpl.getTableName())
            .append(" A WHERE A.ID_FLUJO_PRORROGA_OFICIO = FP.ID_FLUJO_PRORROGA_OFICIO) TOTAL_DOCUMENTOS_RECHAZO \n ")
            .append(" , E.ID_ESTATUS, E.DESCRIPCION, E.MODULO, E.ID_MODULO")
            .append(" , FP.ID_FLUJO_PRORROGA_OFICIO, FP.ID_PRORROGA_OFICIO, FP.FECHA_CREACION, FP.JUSTIFICACION, FP.JUSTIFICACION_FIRMANTE, FP.APROBADA, FP.ID_ESTATUS ESTATUS_FLUJO, FP.FECHA_RECHAZO_FIRMANTE")
            .append(" FROM ").append(getTableName()).append(" P LEFT JOIN ").append(FecetAsociadoDaoImpl.getTableName())
            .append(" AC ").append(" ON P.ID_ASOCIADO_CARGA = AC.ID_ASOCIADO \n").append(" INNER JOIN ")
            .append(FecetFlujoProrrogaOficioDaoImpl.getTableName()).append(" FP ")
            .append(" ON P.ID_PRORROGA_OFICIO = FP.ID_PRORROGA_OFICIO \n").append(" INNER JOIN ")
            .append(FececEstatusDaoImpl.getTableName()).append(" E ").append(" ON FP.ID_ESTATUS = E.ID_ESTATUS \n");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    private StringBuilder getSqlSelectContadorDocProrrogaOficio() {
        StringBuilder query = new StringBuilder();
        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PRO.ID_PRORROGA_OFICIO, \n");
        query.append("    PRO.ID_OFICIO, \n");
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
        query.append("    (SELECT COUNT(0) FROM ");
        query.append(FecetDocProrrogaOficioDaoImpl.getTableName());
        query.append(
                " DOCPRO WHERE PRO.ID_PRORROGA_OFICIO = DOCPRO.ID_PRORROGA_OFICIO) TOTAL_DOC_PRORROGA_OFICIO,  \n");
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
        query.append("    ESTATUS.MODULO \n");
        query.append("FROM ");
        query.append(getTableName());
        query.append(" PRO \n");
        query.append(" LEFT JOIN FECET_ASOCIADO ASOCIADO \n");
        query.append("   ON PRO.ID_ASOCIADO_CARGA = ASOCIADO.ID_ASOCIADO \n");
        query.append(" LEFT JOIN FECEC_ESTATUS ESTATUS \n");
        query.append("   ON PRO.ID_ESTATUS = ESTATUS.ID_ESTATUS \n");

        return query;
    }

    private static final StringBuilder SQL_WHERE_OFICIO_PRORROGA = new StringBuilder(
            " WHERE PRO.ID_OFICIO = ? ORDER BY PRO.ID_PRORROGA_OFICIO ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetProrrogaOficioPk
     */
    @Override
    public FecetProrrogaOficio insert(FecetProrrogaOficio dto) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdProrrogaOficio(), dto.getIdOficio(),
                dto.getFechaCarga(), dto.getRutaAcuse(), dto.getCadenaContribuyente(), dto.getFirmaContribuyente(),
                dto.getAprobada(), dto.getIdAsociadoCarga(), dto.getIdAuditor(), dto.getIdFirmante(),
                dto.getRutaResolucion(), dto.getCadenaFirmante(), dto.getFirmaFirmante(), dto.getFechaFirma(),
                dto.getIdEstatus(), dto.getFolioNyV(), dto.getFechaNotifNyV(), dto.getFechaNotifCont(),
                dto.getFechaSuerteEfectos(), dto.getIdNyV());
        return dto;

    }

    /**
     * @param dto
     */
    @Override
    public void updateProrrogaFirma(FecetProrrogaOficio dto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRORROGA);
        getJdbcTemplateBase().update(query.toString(), dto.getIdOficio(), dto.getFechaCarga(), dto.getRutaAcuse(),
                dto.getCadenaContribuyente(), dto.getFirmaContribuyente(), dto.getAprobada(), dto.getIdAsociadoCarga(),
                dto.getIdAuditor(), dto.getIdFirmante(), dto.getRutaResolucion(), dto.getCadenaFirmante(),
                dto.getFirmaFirmante(), dto.getFechaFirma(), dto.getIdEstatus(), dto.getFolioNyV(),
                dto.getFechaNotifNyV(), dto.getFechaNotifCont(), dto.getFechaSuerteEfectos(),
                dto.getIdProrrogaOficio());
    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    public void updateProrrogaResolucion(FecetProrrogaOficio dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRORROGA_RESOLUCION);

        getJdbcTemplateBase().update(query.toString(), dto.getRutaResolucion(), dto.getIdAuditor(),
                dto.getIdProrrogaOficio());

    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO in column VALIDADA .
     */
    @Override
    public void validaProrrogaAuditor(final BigDecimal idProrrogaOrden, final String estatusAprobada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET APROBADA = ");
        query.append(estatusAprobada).append(" where ID_PRORROGA_OFICIO = ").append(idProrrogaOrden.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO in column ESTADO .
     */
    @Override
    public void validaProrrogaFirmante(final BigDecimal idProrroga, final String estatusValidada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ESTADO = ");
        query.append(estatusValidada).append(" where ID_PRORROGA_OFICIO = ").append(idProrroga.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Metodo getIdFecetProrrogaOficioPathDirectorio
     *
     * @return BigDecimal @
     */
    @Override
    public BigDecimal getIdFecetProrrogaOficioPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PRORROGA_OFICIO";
    }

    /**
     * Metodo buscaUltimaProrrogaPorOficio
     *
     * @param oficio
     * @return @
     */
    @Override
    public List<FecetProrrogaOficio> buscaUltimaProrrogaPorOficio(final FecetOficio oficio) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName());
        query.append(" WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRORROGA_OFICIO WHERE ID_OFICIO = ?)");

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), oficio.getIdOficio());

    }

    /**
     * Updates a single row in the FECET_PRORROGA_OFICIO table.
     */
    @Override
    public void actualizarPorEstadoYFechaMax(final FecetProrrogaOficio prorroga, final FecetOficio oficio) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_UPDATE).append(getTableName());
        query.append(" SET ID_ESTATUS = ? WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRORROGA_OFICIO ");
        query.append(" WHERE ID_OFICIO = ?)");

        getJdbcTemplateBase().update(query.toString(), prorroga.getIdEstatus(), oficio.getIdOficio());

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_PRORROGA_OFICIO = :idProrroga'.
     */
    @Override
    public FecetProrrogaOficio findByPrimaryKey(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRORROGA_OFICIO = ?");
        List<FecetProrrogaOficio> list = getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(),
                idProrroga);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria ''.
     */
    @Override
    public List<FecetProrrogaOficio> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" ORDER BY ID_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper());

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_PRORROGA_OFICIO = :idProrroga'.
     */
    @Override
    public List<FecetProrrogaOficio> findWhereIdProrrogaEquals(BigDecimal idProrroga) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_PRORROGA_OFICIO = ? ORDER BY ID_PRORROGA_OFICIO");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), idProrroga);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'FECHA_CARGA = :fechaCarga'.
     */
    @Override
    public List<FecetProrrogaOficio> findWhereFechaCargaEquals(Date fechaCarga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), fechaCarga);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    public List<FecetProrrogaOficio> findWhereAprobadaEquals(final String aprobada) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE APROBADA = ? ORDER BY APROBADA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), aprobada);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_ASOCIADO_CARGA = :idContribuyente'.
     */
    public List<FecetProrrogaOficio> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_ASOCIADO_CARGA = ? ORDER BY ID_ASOCIADO_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), idContribuyente);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_AUDITOR = :idAuditor'.
     */
    public List<FecetProrrogaOficio> findWhereIdAuditorEquals(final BigDecimal idAuditor) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_AUDITOR = ? ORDER BY ID_AUDITOR");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), idAuditor);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'ID_FIRMANTE = :idFirmante'.
     */
    @Override
    public List<FecetProrrogaOficio> findWhereIdFirmanteEquals(final BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE ID_FIRMANTE = ? ORDER BY ID_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), idFirmante);

    }

    /**
     * Returns all rows from the FECET_PRORROGA_OFICIO table that match the
     * criteria 'FECHA_FIRMA = :fechaFirma'.
     */
    @Override
    public List<FecetProrrogaOficio> findWhereFechaFirmaEquals(Date fechaFirma) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName())
                .append(" WHERE FECHA_FIRMA = ? ORDER BY FECHA_FIRMA");
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioMapper(), fechaFirma);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_OFICIO cuyo valor del
     * 'ID_OFICIO sea igual al que se le esta mandando
     */
    @Override
    public List<FecetProrrogaOficio> getProrrogaPorOficioEstatusPendienteAuditor(final BigDecimal idOficio) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES);
        query.append(WHERE_P_ID_OFICIO);
        query.append(" AND P.ID_ESTATUS = ? AND ((FP.ID_FLUJO_PRORROGA_OFICIO IS NULL) OR (FP.ID_ESTATUS = ?)) ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioConRelacionesMapper(), idOficio,
                EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus(),
                EstatusFlujoProrroga.RESOLUCION_PRORROGA_RECHAZADA_FIRMANTE.getBigIdEstatus());

    }

    @Override
    public List<FecetProrrogaOficio> getProrrogaPorOficio(BigDecimal idOficio, BigDecimal idEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_PRORROGAS_RELACIONES);
        query.append(WHERE_P_ID_OFICIO);
        query.append(" AND P.ID_ESTATUS = ? AND P.FECHA_SURTE_EFECTOS IS NOT NULL ");
        query.append(" ORDER BY P.ID_PRORROGA_OFICIO ");
        logger.trace("SQL PRORROGAS POR OFICIO {} ", query.toString());
        logger.trace("ESTATUS {} ", idEstatus);
        logger.trace("OFICIO {} ", idOficio);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioConRelacionesMapper(), idOficio,
                idEstatus);

    }

    /**
     * @param idOficio
     * @return FEcetProrrogasOficio Firmadas por el firmante
     */
    @Override
    public List<FecetProrrogaOficio> getProrrogaPorOficioFirmadas(BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_PRORROGAS_RELACIONES);
        query.append(WHERE_P_ID_OFICIO);
        query.append(" AND FP.ID_ESTATUS = ? ");
        query.append(" ORDER BY P.ID_PRORROGA_OFICIO ");
        logger.debug(" [PRORROGAS DEL OFICIO] ");
        logger.debug("SQL : {} ", query.toString());
        logger.debug("PARAMS : {} ", idOficio);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioConRelacionesMapper(), idOficio,
                EstatusFlujoProrroga.RESOLUCION_PRORROGA_APROBADA_FIRMANTE.getBigIdEstatus());

    }

    /**
     * @param idOficio
     * @return FecetProrrogasOficio Pendientes de firma de firmante
     */
    @Override
    public List<FecetProrrogaOficio> getProrrogaPorOficioPendientes(BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT_PRORROGAS_RELACIONES);
        query.append(WHERE_P_ID_OFICIO);
        query.append(" AND FP.ID_ESTATUS IN ( ? ) ");
        query.append(" ORDER BY P.FECHA_SURTE_EFECTOS ");
        logger.info(" SQL PRORROGAS Pendientes de firma de firmante {} ", query.toString());
        logger.info("OFICIO {} ", idOficio);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioConRelacionesMapper(), idOficio,
                EstatusFlujoProrroga.PRORROGA_RESUELTA_AUDITOR.getBigIdEstatus());

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRORROGA_OFICIO cuydo valor del
     * 'ID_OFICIO sea igual al que se le esta mandando y el estatus sea Firmada
     */
    @Override
    public List<FecetProrrogaOficio> getHistoricoProrrogaPorOficio(final BigDecimal idOficio) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRORROGAS_RELACIONES_HISTORICO);
        query.append(WHERE_P_ID_OFICIO);
        query.append(" ORDER BY P.ID_PRORROGA_OFICIO, FP.ID_FLUJO_PRORROGA_OFICIO ");

        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioConRelacionesMapper(), idOficio);

    }

    @Override
    public List<FecetProrrogaOficio> getProrrogaOficioContadorDoc(final BigDecimal idOficio) {
        StringBuilder query = new StringBuilder();

        query.append(getSqlSelectContadorDocProrrogaOficio());
        query.append(SQL_WHERE_OFICIO_PRORROGA);
        return getJdbcTemplateBase().query(query.toString(), new FecetProrrogaOficioContadorDocsMapper(), idOficio);
    }

    @Override
    public void updateFechasNotificacion(final BigDecimal idProrrogaOficio, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus) {
        logger.debug("[updateFechasNotificacion]");
        Object[] parameters = {fechaNotificacionCont, fechaSurteEfectos, idEstatus, idProrrogaOficio};
        StringBuilder sql = new StringBuilder(UPDATE);
        sql.append(getTableName()).append(" SET FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, ID_ESTATUS = ? ")
                .append(WHERE).append(" ID_PRORROGA_OFICIO = ? ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        getJdbcTemplateBase().update(sql.toString(), parameters);
    }

    @Override
    public List<FecetProrrogaOficio> getProrrogaPorOficioEstatus(final BigDecimal idOficio,
            final BigDecimal idEstatus) {
        logger.debug("[getProrrogaPorOficioEstatus]");
        Object[] parameters = {idOficio, idEstatus};
        StringBuilder sql = new StringBuilder(SQL_SELECT);
        sql.append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_OFICIO = ? ")
                .append(" AND ID_ESTATUS = ? ").append(SQL_ORDER).append(" FECHA_SURTE_EFECTOS DESC ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        return getJdbcTemplateBase().query(sql.toString(), new FecetProrrogaOficioMapper(), parameters);
    }

    @Override
    public void guardarFirma(FirmaDTO firma) {
        ProrrogaDocsVO dto = (ProrrogaDocsVO) firma.getObjectoFirma();
        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getProrrogaOficio().getIdProrrogaOficio(),
                dto.getProrrogaOficio().getIdOficio(), dto.getProrrogaOficio().getFechaCarga(),
                dto.getProrrogaOficio().getRutaAcuse(), dto.getProrrogaOficio().getCadenaContribuyente(),
                dto.getProrrogaOficio().getFirmaContribuyente(), dto.getProrrogaOficio().getAprobada(),
                dto.getProrrogaOficio().getIdAsociadoCarga(), dto.getProrrogaOficio().getIdAuditor(),
                dto.getProrrogaOficio().getIdFirmante(), dto.getProrrogaOficio().getRutaResolucion(),
                dto.getProrrogaOficio().getCadenaFirmante(), dto.getProrrogaOficio().getFirmaFirmante(),
                dto.getProrrogaOficio().getFechaFirma(), dto.getProrrogaOficio().getIdEstatus(),
                dto.getProrrogaOficio().getFolioNyV(), dto.getProrrogaOficio().getFechaNotifNyV(),
                dto.getProrrogaOficio().getFechaNotifCont(), dto.getProrrogaOficio().getFechaSuerteEfectos(),
                dto.getProrrogaOficio().getIdNyV());

    }

    @Override
    public String obtenerNumeroReferenciaProrOfi(Long idProOficio) {
        return getJdbcTemplateBase().queryForObject(SELECT_NO_REFERENCIA_PRO_OFI, String.class, idProOficio);
    }

    @Override
    public void updateNumeroReferenciaProrOfi(String noReferencia, Long idProOficio) {
        getJdbcTemplateBase().update(UPDATE_NO_REFERENCIA_PRO_OFI, noReferencia, idProOficio);
    }
}
