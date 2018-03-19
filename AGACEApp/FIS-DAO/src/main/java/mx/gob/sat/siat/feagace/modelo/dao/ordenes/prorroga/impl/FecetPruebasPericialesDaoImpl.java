package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexoPruebasPericialesDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl.FecetDocPruebasPericialesDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl.FecetAsociadoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPruebasPericialesConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPruebasPericialesContadorDocMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetPruebasPericialesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.common.FirmaDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericialesPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.PruebasPericialesDocsVO;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetPruebasPericialesDao")
public class FecetPruebasPericialesDaoImpl extends BaseJDBCDao<FecetPruebasPericiales> implements FecetPruebasPericialesDao {

    private static final long serialVersionUID = -8701321481670077495L;

    private static final String SQL_SELECT = "SELECT ";

    private static final String SQL_INNER_ID_PRUEBAS_PERICIALES = " ON P.ID_PRUEBAS_PERICIALES = FP.ID_PRUEBAS_PERICIALES \n";

    private static final String SQL_FROM = " FROM ";

    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_AND = " AND ";
    private static final String SQL_ORDER = " ORDER BY ";
    private static final String SQL_ORDER_BY = " ORDER BY P.ID_ORDEN ";

    private static final String NAME_TABLE_PRUEBAS_PERICIALES = "FECET_FLUJO_PRUEBAS_PERICIALES";

    private static final StringBuilder SQL_ALL_COLUMNS
            = new StringBuilder("   ID_PRUEBAS_PERICIALES, ID_ORDEN, FECHA_CARGA, RUTA_ACUSE, ").append("   CADENA_CONTRIBUYENTE, FIRMA_CONTRIBUYENTE, APROBADA, ID_ASOCIADO_CARGA, ").append("   ID_AUDITOR, ID_FIRMANTE, RUTA_RESOLUCION, ").append("   CADENA_FIRMANTE, FIRMA_FIRMANTE, FECHA_FIRMA, ID_ESTATUS, FOLIO_NYV, ").append(" FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, ").append("   FECHA_SURTE_EFECTOS, ID_NYV ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_PRUEBAS_PERICIALES.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_PRUEBAS_PERICIALES
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    private static final StringBuilder SQL_LEFT_JOIN = new StringBuilder(" LEFT JOIN ");

    private static final StringBuilder SQL_FP = new StringBuilder(" FP ");

    private static final StringBuilder SQL_UPDATE_PRUEBAS_PERICIALES_RESOLUCION
            = new StringBuilder(" SET RUTA_RESOLUCION = ?, ID_AUDITOR = ?  WHERE ID_PRUEBAS_PERICIALES = ? ");

    private static final String SELECT_NO_REFERENCIA_PRU_PER = "SELECT NUMERO_REFERENCIA FROM FECET_PRUEBAS_PERICIALES WHERE ID_PRUEBAS_PERICIALES = ?";

    private static final String UPDATE_NO_REFERENCIA_PRU_PER = "UPDATE FECET_PRUEBAS_PERICIALES SET NUMERO_REFERENCIA = ? WHERE ID_PRUEBAS_PERICIALES = ? AND NUMERO_REFERENCIA IS NULL";

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES table.
     */
    private static final StringBuilder SQL_UPDATE_PRUEBAS_PERICIALES
            = new StringBuilder(" SET ID_ORDEN = ?,  FECHA_CARGA = ?, ").append(" RUTA_ACUSE = ?, CADENA_CONTRIBUYENTE = ?, FIRMA_CONTRIBUYENTE = ?, ").append(" APROBADA = ?, ID_ASOCIADO_CARGA = ?, ID_AUDITOR = ?, ID_FIRMANTE = ?, ").append(" RUTA_RESOLUCION = ?, CADENA_FIRMANTE = ?, FIRMA_FIRMANTE = ?, ").append(" FECHA_FIRMA = ?, ID_ESTATUS = ?, FOLIO_NYV = ?, ").append(" FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ? WHERE ID_PRUEBAS_PERICIALES = ?");

    /**
     * este atributo corresponde a una funcion SELECT para traer datos de tabla
     * FECET_PRUEBAS_PERICIALES
     */
    private static final StringBuilder SELECT_PRUEBAS_PERICIALES_RELACIONES
            = new StringBuilder(" SELECT \n").append(" P.ID_PRUEBAS_PERICIALES, P.ID_ORDEN, P.FECHA_CARGA, P.RUTA_ACUSE, P.CADENA_CONTRIBUYENTE, \n").append(" P.FIRMA_CONTRIBUYENTE, P.APROBADA, P.ID_ASOCIADO_CARGA, P.ID_AUDITOR, P.ID_FIRMANTE, \n")
            .append(" P.RUTA_RESOLUCION, P.CADENA_FIRMANTE, P.FIRMA_FIRMANTE, P.FECHA_FIRMA, \n")
            .append(" P.ID_ESTATUS, P.FOLIO_NYV, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, \n")
            .append(" AC.ID_ASOCIADO, AC.ID_ORDEN, AC.ID_TIPO_ASOCIADO, AC.NOMBRE, AC.RFC, AC.CORREO, AC.TIPO_ASOCIADO, AC.MEDIO_CONTACTO, \n")
            .append(" AC.FECHA_BAJA, AC.FECHA_ULTIMA_MOD, AC.FECHA_ULTIMA_MOD_IDC, AC.ESTATUS, AC.RFC_CONTRIBUYENTE, \n")
            .append(" (SELECT COUNT(D.ID_DOC_PRUEBAS_PERICIALES) FROM ")
            .append(FecetDocPruebasPericialesDaoImpl.getTableName())
            .append(" D WHERE D.ID_PRUEBAS_PERICIALES = P.ID_PRUEBAS_PERICIALES) TOTAL_DOCUMENTOS, \n")
            .append(" (SELECT COUNT(A.ID_ANEXO_PRUEBAS_PERICIALES) FROM ")
            .append(FecetAnexoPruebasPericialesDaoImpl.getTableName())
            .append(" A WHERE A.ID_FLUJO_PRUEBAS_PERICIALES = FP.ID_FLUJO_PRUEBAS_PERICIALES) TOTAL_DOCUMENTOS_RECHAZO \n ")
            .append(" , E.ID_ESTATUS, E.DESCRIPCION, E.MODULO, E.ID_MODULO")
            .append(" , FP.ID_FLUJO_PRUEBAS_PERICIALES, FP.ID_PRUEBAS_PERICIALES, FP.FECHA_CREACION, FP.JUSTIFICACION, FP.JUSTIFICACION_FIRMANTE, FP.APROBADA AS FLUJO_APROBADA, FP.ID_ESTATUS ESTATUS_FLUJO, FP.FECHA_RECHAZO_FIRMANTE")
            .append(" FROM ").append(getTableName())
            .append(" P ")
            .append(SQL_LEFT_JOIN)
            .append(FecetAsociadoDaoImpl.getTableName())
            .append(" AC ")
            .append(" ON P.ID_ASOCIADO_CARGA = AC.ID_ASOCIADO \n")
            .append(" INNER JOIN ")
            .append(FececEstatusDaoImpl.getTableName())
            .append(" E ").append(" ON P.ID_ESTATUS = E.ID_ESTATUS \n");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_WHERE_ORDER_FIRMANTE
            = new StringBuilder(" WHERE ID_ORDEN = ? AND APROBADA IS NOT NULL ORDER BY ID_ORDEN ");

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
    private StringBuilder getSqlSelectContadorDocPruebasPericiales() {
        StringBuilder query = new StringBuilder();

        query.append(" ");
        query.append(" SELECT \n");
        query.append("    PRO.ID_PRUEBAS_PERICIALES, \n");
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
        query.append(FecetDocPruebasPericialesDaoImpl.getTableName());
        query.append(" DOCPRO WHERE PRO.ID_PRUEBAS_PERICIALES = DOCPRO.ID_PRUEBAS_PERICIALES) TOTAL_DOC_PRUEBAS_PERICIALES,  \n");
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

    private static final StringBuilder SQL_WHERE_ORDEN_PRUEBAS_PERICIALES
            = new StringBuilder(" WHERE PRO.ID_ORDEN = ? ORDER BY PRO.ID_PRUEBAS_PERICIALES ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetPruebasPericialesPk
     */
    @Override
    public FecetPruebasPericialesPk insert(FecetPruebasPericiales dto) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdPruebasPericiales(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaAcuse(), dto.getCadenaContribuyente(), dto.getFirmaContribuyente(), dto.getAprobada(), dto.getIdAsociadoCarga(),
                dto.getIdAuditor(), dto.getIdFirmante(), dto.getRutaResolucion(), dto.getCadenaFirmante(), dto.getFirmaFirmante(),
                dto.getFechaFirma(), dto.getIdEstatus(), dto.getFolioNyv(), dto.getFechaNotifNyv(),
                dto.getFechaNotifCont(), dto.getFechaSurteEfectos(), null);
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES table.
     */
    @Override
    public void update(FecetPruebasPericialesPk pk, FecetPruebasPericiales dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRUEBAS_PERICIALES);

        getJdbcTemplateBase().update(query.toString(), dto.getIdOrden(), dto.getFechaCarga(), dto.getRutaAcuse(),
                dto.getCadenaContribuyente(), dto.getFirmaContribuyente(),
                dto.getAprobada(), dto.getIdAsociadoCarga(), dto.getIdAuditor(),
                dto.getIdFirmante(), dto.getRutaResolucion(), dto.getCadenaFirmante(),
                dto.getFirmaFirmante(), dto.getFechaFirma(), dto.getIdEstatus(),
                dto.getFolioNyv(), dto.getFechaNotifNyv(), dto.getFechaNotifCont(),
                dto.getFechaSurteEfectos(), pk.getIdPruebasPericiales());

    }

    /**
     * @param dto
     */
    @Override
    public void updatePruebasPericialesFirma(FecetPruebasPericiales dto) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRUEBAS_PERICIALES);
        getJdbcTemplateBase().update(query.toString(), dto.getIdOrden(), dto.getFechaCarga(),
                dto.getRutaAcuse(), dto.getCadenaContribuyente(), dto.getFirmaContribuyente(),
                dto.getAprobada(), dto.getIdAsociadoCarga(), dto.getIdAuditor(),
                dto.getIdFirmante(), dto.getRutaResolucion(),
                dto.getCadenaFirmante(), dto.getFirmaFirmante(), dto.getFechaFirma(),
                dto.getIdEstatus(), dto.getFolioNyv(),
                dto.getFechaNotifNyv(), dto.getFechaNotifCont(),
                dto.getFechaSurteEfectos(), dto.getIdPruebasPericiales());
    }

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES in column VALIDADA .
     */
    @Override
    public void validaPruebasPericialesAuditor(final BigDecimal idPruebasPericiales, final String estatusAprobada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET APROBADA = ");
        query.append(estatusAprobada).append(" where ID_PRUEBAS_PERICIALES = ").append(idPruebasPericiales.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES in column ESTADO .
     */
    @Override
    public void validaPruebasPericialesFirmante(final BigDecimal idPruebasPericiales, final String estatusValidada) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ESTADO = ");
        query.append(estatusValidada).append(" where ID_PRUEBAS_PERICIALES = ").append(idPruebasPericiales.toString());
        getJdbcTemplateBase().update(query.toString());

    }

    /**
     * Deletes a single row in the FECET_PRUEBAS_PERICIALES table.
     */
    @Override
    public void delete(FecetPruebasPericialesPk pk) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_DELETE).append(getTableName()).append(" WHERE ID_PRUEBAS_PERICIALES = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdPruebasPericiales());

    }

    /**
     * Metodo getIdFecetPruebasPericialesPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    @Override
    public BigDecimal getIdFecetPruebasPericialesPathDirectorio() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_PRUEBAS_PERICIALES";
    }

    /**
     * Metodo buscaUltimaPruebasPericialesPorOrden
     *
     * @param orden
     * @return
     * @
     */
    @Override
    public List<FecetPruebasPericiales> buscaUltimaPruebasPericialesPorOrden(AgaceOrden orden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName());
        query.append(" WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRUEBAS_PERICIALES WHERE ID_ORDEN = ?)");

        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), orden.getIdOrden());

    }

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES table.
     */
    @Override
    public void actualizarPorEstadoYFechaMax(FecetPruebasPericiales pruebasPericiales, AgaceOrden orden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_UPDATE).append(getTableName());
        query.append(" SET ID_ESTATUS = ? WHERE FECHA_CARGA = (SELECT MAX(FECHA_CARGA) FROM FECET_PRUEBAS_PERICIALES ");
        query.append(" WHERE ID_ORDEN = ?)");

        getJdbcTemplateBase().update(query.toString(), pruebasPericiales.getIdEstatus(), orden.getIdOrden());

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    @Override
    public FecetPruebasPericiales findByPrimaryKey(BigDecimal idPruebasPericiales) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_PRUEBAS_PERICIALES = ?");
        List<FecetPruebasPericiales> list
                = getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idPruebasPericiales);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRUEBAS_PERICIALES cuydo valor
     * del ID_ORDEN sea igual al que se le esta mandando
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrden(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRUEBAS_PERICIALES).append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES).append(" WHERE P.ID_ORDEN = ? ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRUEBAS_PERICIALES cuydo valor
     * del 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea
     * Firmada
     */
    @Override
    public List<FecetPruebasPericiales> getHistoricoPruebasPericialesPorOrden(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES).append(" INNER JOIN ").append(NAME_TABLE_PRUEBAS_PERICIALES).append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES);
        query.append(" WHERE P.ID_ORDEN = ? ");
        query.append(" ORDER BY P.ID_PRUEBAS_PERICIALES, FP.ID_FLUJO_PRUEBAS_PERICIALES ");

        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRUEBAS_PERICIALES cuydo valor
     * del 'ID_ORDEN sea igual al que se le esta mandando y el estatus sea
     * Firmada
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrdenFirmada(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES);
        query.append(SQL_LEFT_JOIN).append(NAME_TABLE_PRUEBAS_PERICIALES);
        query.append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES);
        query.append(" WHERE P.ID_ORDEN = ? \n");
        query.append(" AND FP.ID_ESTATUS = ? \n");
        query.append(SQL_ORDER_BY);

        logger.debug("SQL : {} ", query.toString());
        logger.debug("PARAMS : {} ", idOrden);
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden,
                EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_APROBADA_FIRMANTE.getBigIdEstatus());

    }

    /**
     * @param idOrden
     * @return List FecetPruebasPericiales pendientes de firmar
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrdenPorFirmar(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRUEBAS_PERICIALES).append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES);
        query.append(" WHERE P.ID_ORDEN = ? \n");
        query.append(" AND FP.ID_ESTATUS in ( ? ) \n");
        query.append(SQL_ORDER_BY);
        logger.debug(" {} ", query);
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden,
                EstatusFlujoPruebasPericiales.PRUEBA_PERICIAL_RESUELTA_AUDITOR.getBigIdEstatus());
    }

    /**
     * @param idOrden
     * @return List FecetPruebasPericiales Pruebas Periciales Fimadas
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrdenFirmante(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(SQL_WHERE_ORDER_FIRMANTE);
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria ''.
     */
    @Override
    public List<FecetPruebasPericiales> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" ORDER BY ID_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper());

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereIdPruebasPericialesEquals(BigDecimal idPruebaPericial) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_PRUEBAS_PERICIALES = ? ORDER BY ID_PRUEBAS_PERICIALES");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idPruebaPericial);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereIdOrdenEquals(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'FECHA_CARGA = :fechaCarga'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereFechaCargaEquals(Date fechaCarga) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE FECHA_CARGA = ? ORDER BY FECHA_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), fechaCarga);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereAprobadaEquals(final String aprobada) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE APROBADA = ? ORDER BY APROBADA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), aprobada);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_ASOCIADO_CARGA = :idContribuyente'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereIdContribuyenteEquals(final BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_ASOCIADO_CARGA = ? ORDER BY ID_ASOCIADO_CARGA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idContribuyente);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_AUDITOR = :idAuditor'.
     */
    public List<FecetPruebasPericiales> findWhereIdAuditorEquals(final BigDecimal idAuditor) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_AUDITOR = ? ORDER BY ID_AUDITOR");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idAuditor);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ID_FIRMANTE = :idFirmante'.
     */
    public List<FecetPruebasPericiales> findWhereIdFirmanteEquals(final BigDecimal idFirmante) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE ID_FIRMANTE = ? ORDER BY ID_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idFirmante);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'FECHA_FIRMA = :fechaFirma'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereFechaFirmaEquals(Date fechaFirma) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName()).append(" WHERE FECHA_FIRMA = ? ORDER BY FECHA_FIRMA");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), fechaFirma);

    }

    /**
     * Returns all rows from the FECET_PRUEBAS_PERICIALES table that match the
     * criteria 'ESTADO = :estado'.
     */
    @Override
    public List<FecetPruebasPericiales> findWhereIdEstatusIdOrdenEquals(final BigDecimal estado, BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ? AND ID_ESTATUS = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden, estado);
    }

    /**
     * Regresa todos los datos de la tabla FECET_PRUEBAS_PERICIALES cuyo valor
     * del 'ID_ORDEN sea igual al que se le está mandando
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrdenEstatusPendiente(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRUEBAS_PERICIALES).append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES).append(" WHERE P.ID_ORDEN = ? AND P.ID_ESTATUS <> ? ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden,
                Constantes.ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_FIRMADA_ENVIADA_NYV);

    }

    /**
     * Regresa todos los datos de la tabla FECET_PRUEBAS_PERICIALES cuyo valor
     * del 'ID_ORDEN sea igual al que se le esta mandando
     */
    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPorOrdenEstatusPendienteAuditor(final BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SELECT_PRUEBAS_PERICIALES_RELACIONES).append(SQL_LEFT_JOIN).append(NAME_TABLE_PRUEBAS_PERICIALES).append(SQL_FP);
        query.append(SQL_INNER_ID_PRUEBAS_PERICIALES);
        query.append(" WHERE P.ID_ORDEN = ? ");
        query.append(" AND P.ID_ESTATUS = ? ");
        query.append(" AND (FP.ID_FLUJO_PRUEBAS_PERICIALES IS NULL OR FP.ID_ESTATUS = ? ) ");
        query.append(SQL_ORDER_BY);

        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesConRelacionesMapper(), idOrden,
                EstatusPruebasPericiales.PRUEBAS_PERICIALES_PENDIENTE_APROBACION.getBigIdEstatus(),
                EstatusFlujoPruebasPericiales.RESOLUCION_PRUEBA_PERICIAL_RECHAZADA_FIRMANTE.getBigIdEstatus());
    }

    /**
     * Returns the rows from the FECET_PRUEBAS_PERICIALES table that matches the
     * specified primary-key value.
     */
    public FecetPruebasPericiales findByPrimaryKey(FecetPruebasPericialesPk pk) {
        return findByPrimaryKey(pk.getIdPruebasPericiales());
    }

    public List<FecetPruebasPericiales> getPruebasPericialesContadorDoc(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(getSqlSelectContadorDocPruebasPericiales());
        query.append(SQL_WHERE_ORDEN_PRUEBAS_PERICIALES);
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesContadorDocMapper(), idOrden);

    }

    @Override
    public List<FecetPruebasPericiales> findWhereIdOrdenEstatusEquals(final BigDecimal idOrden,
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
        return getJdbcTemplateBase().query(sql.toString(), new FecetPruebasPericialesMapper(), idOrden);
    }

    @Override
    public void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, final BigDecimal idEstatus) {
        logger.debug("[updateFechasNotificacion]");
        Object[] parameters = {fechaNotificacionCont, fechaSurteEfectos, idEstatus, idOrden};
        StringBuilder sql = new StringBuilder(SQL_UPDATE);
        sql.append(getTableName()).append(" SET FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, ID_ESTATUS = ? ").append(SQL_WHERE).append(" ID_ORDEN = ? ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        getJdbcTemplateBase().update(sql.toString(), parameters);
    }

    @Override
    public void guardarFirma(FirmaDTO firma) {
        PruebasPericialesDocsVO dto = (PruebasPericialesDocsVO) firma.getObjectoFirma();
        StringBuilder query = new StringBuilder();
        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ");
        query.append(SQL_ALL_COLUMNS.toString());
        query.append(" ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        getJdbcTemplateBase().update(query.toString(), dto.getPruebasPericiales().getIdPruebasPericiales(), dto.getPruebasPericiales().getIdOrden(), dto.getPruebasPericiales().getFechaCarga(),
                dto.getPruebasPericiales().getRutaAcuse(), dto.getPruebasPericiales().getCadenaContribuyente(),
                dto.getPruebasPericiales().getFirmaContribuyente(), dto.getPruebasPericiales().getAprobada(), dto.getPruebasPericiales().getIdAsociadoCarga(),
                dto.getPruebasPericiales().getIdAuditor(), dto.getPruebasPericiales().getIdFirmante(),
                dto.getPruebasPericiales().getRutaResolucion(), dto.getPruebasPericiales().getCadenaFirmante(), dto.getPruebasPericiales().getFirmaFirmante(),
                dto.getPruebasPericiales().getFechaFirma(), dto.getPruebasPericiales().getIdEstatus(), dto.getPruebasPericiales().getFolioNyv(),
                dto.getPruebasPericiales().getFechaNotifNyv(),
                dto.getPruebasPericiales().getFechaNotifCont(), dto.getPruebasPericiales().getFechaSurteEfectos(), null);
    }

    /**
     * Updates a single row in the FECET_PRUEBAS_PERICIALES_OFICIO table.
     */
    public void updatePruebasPericialesResolucion(FecetPruebasPericiales dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(SQL_UPDATE_PRUEBAS_PERICIALES_RESOLUCION);

        getJdbcTemplateBase().update(query.toString(), dto.getRutaResolucion(),
                dto.getIdAuditor(), dto.getIdPruebasPericiales());

    }

    @Override
    public List<FecetPruebasPericiales> getPruebasPericialesPendienteAprobPorOrden(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ? AND ID_ESTATUS = ? ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden,
                EstatusPruebasPericiales.PRUEBAS_PERICIALES_PENDIENTE_APROBACION.getBigIdEstatus());
    }

    @Override
    public List<FecetPruebasPericiales> existeSolicitudAprobada(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ? AND APROBADA = 1 ");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden);
    }

    @Override
    public List<FecetPruebasPericiales> existeSolicitudPruebaPericial(final BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        query.append(" WHERE ID_ORDEN = ?");
        return getJdbcTemplateBase().query(query.toString(), new FecetPruebasPericialesMapper(), idOrden);
    }

    @Override
    public String obtenerNumeroReferenciaPruePer(Long idPruebasPer) {
        return getJdbcTemplateBase().queryForObject(SELECT_NO_REFERENCIA_PRU_PER, String.class, idPruebasPer);
    }

    @Override
    public void updateNumeroReferenciaPruePer(String noReferencia, Long idPruebasPer) {
        getJdbcTemplateBase().update(UPDATE_NO_REFERENCIA_PRU_PER, noReferencia, idPruebasPer);
    }

}
