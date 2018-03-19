/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.impl.FeceaMetodoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceOrdenConMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceOrdenConRelacionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.AgaceOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.helper.OrdenDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.AgaceOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.ContadorOrdenesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.RespuestaOrdenAcuerdosConclusivosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.sql.OrdenSql;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dao.util.ConsultasUtil;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleNyV;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AcuseDTO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrdenPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FiltroOrdenes;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.seguimiento.EstatusOrdenes;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("agaceOrdenDao")
public class AgaceOrdenDaoImpl extends BaseJDBCDao<AgaceOrden> implements AgaceOrdenDao {

    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = "SELECT ID_ORDEN, ID_METODO, ID_REVISION, NUMERO_ORDEN, FECHA_CREACION, FECHA_BAJA, PRIORIDAD, FOLIO_NYV, CADENA_ORIGINAL, FIRMA_ELECTRONICA, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, DIAS_RESTANTES_PLAZO, DIAS_HABILES, SUSPENCION_PLAZO, DIAS_RESTANTES_DOCUMENTOS, SEMAFORO, FECHA_INTEGRA_EXP, ID_CONTRIBUYENTE, ID_ESTATUS, ID_AUDITOR, ID_FIRMANTE, ID_PROPUESTA, ID_REGISTRO_PROPUESTA, FECHA_REACTIVAR_PLAZO, FECHA_SUSPENCION_PLAZO, DIAS_RESOLUCION_DEFINITIVA, FOLIO_OFICIO, BLN_COMPULSA, ID_NYV FROM FECET_ORDEN ";
    private static final String CAMPOS_SQL_SELECT = " SELECT ID_ORDEN, ID_METODO, ID_REVISION, NUMERO_ORDEN, FECHA_CREACION, FECHA_BAJA, PRIORIDAD, FOLIO_NYV, CADENA_ORIGINAL, FIRMA_ELECTRONICA, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, DIAS_RESTANTES_PLAZO, DIAS_HABILES, SUSPENCION_PLAZO, DIAS_RESTANTES_DOCUMENTOS, SEMAFORO, FECHA_INTEGRA_EXP, ID_CONTRIBUYENTE, ID_ESTATUS, ID_AUDITOR, ID_FIRMANTE, ID_PROPUESTA, ID_REGISTRO_PROPUESTA, FECHA_REACTIVAR_PLAZO, FECHA_SUSPENCION_PLAZO, DIAS_RESOLUCION_DEFINITIVA, FOLIO_OFICIO, BLN_COMPULSA, ID_NYV ";
    private static final String SELECT_ABREVIATURA_NOMBRE = "  , METODO.ABREVIATURA,  METODO.NOMBRE, \n ";
    private static final String SELECT_DATOS_CONTRIBUYENTE = "  CONT.RFC, CONT.NOMBRE NOMBRE_CONTRIBUYENTE, CONT.ENTIDAD, CONT.DOMICILIO_FISCAL, CONT.ACTIVIDAD_PREPONDERANTE, CONT.REGIMEN, CONT.SITUACION, CONT.SITUACION_DOMICILIO, CONT.TIPO, \n ";
    private static final String SELECT_ESTATUS_MODULO_DESCRIPCION = "  ESTATUS.MODULO, ESTATUS.DESCRIPCION \n ";
    private static final String FROM = " FROM ";
    private static final String ALIAS_METODO = " METODO \n";
    private static final String JOIN_FECET_CONTRIBUYENTE = " INNER JOIN FECET_CONTRIBUYENTE CONT \n ";

    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE FECHA_BAJA IS NULL AND ID_ORDEN = ? ");

    private static final String SQL_WHERE_SIMPLE = " WHERE ";

    private static final StringBuilder SQL_WHERE_FECHA_BAJA_NULL = new StringBuilder(" WHERE FECHA_BAJA IS NULL ");

    private static final StringBuilder ORDER_BY = new StringBuilder(
            " ORDER BY ORDEN.FECHA_CREACION DESC, ORDEN.PRIORIDAD DESC");

    /**
     * Este atributo corresponde a una condicion UPDATE para el query
     */
    private static final StringBuilder UPDATE = new StringBuilder("UPDATE ");

    private static final String SQL_INNER_JOIN_CONTRIBUYENTE = " INNER JOIN FECET_CONTRIBUYENTE CONT ON ORDEN.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n";

    private static final String SQL_AND_RFC_CONTRIBUYENTE = " AND CONT.RFC = ? \n";

    private static final String SQL_AND_ID_ESTATUS_ORDEN = " AND ORDEN.ID_ESTATUS = ? \n";

    private static final String SQL_INNER_JOIN_ASOCIADO = " INNER JOIN FECET_ASOCIADO ASOCIADO ON ORDEN.ID_ORDEN = ASOCIADO.ID_ORDEN \n";

    private static final String SQL_AND_ID_TIPO_ASOCIADO = " AND ASOCIADO.ID_TIPO_ASOCIADO = ? \n";

    private static final String SQL_AND_RFC_ASOCIADO = " AND ASOCIADO.RFC = ? \n";

    private static final String SQL_AND_FECHA_BAJA_ASOCIADO = " AND ASOCIADO.FECHA_BAJA IS NULL \n";

    private static final String SQL_AND_RFC_ASOCIADO_CONTRIBUYENTE = " AND ASOCIADO.RFC_CONTRIBUYENTE = CONT.RFC \n";

    private static final String SQL_AND_BLN_COMPULSA = " AND ORDEN.BLN_COMPULSA = '0' \n";

    private static final String SQL_AND_ORDER_BY_FECHA_CREACION = " ORDER BY ORDEN.FECHA_CREACION DESC \n ";

    private static final String SQL_AND_ORDER_BY_METODO_ORDEN = "  ORDER BY ID_METODO, NUMERO_ORDEN \n ";

    private static final String SQL_AND_ID_AUDITOR_FIRMANTE = "AND (ORDEN.ID_AUDITOR = ? OR ORDEN.ID_FIRMANTE = ?) \n";

    private static final String AND = " AND ";

    private static final String SQL_CONDICION_ESTATUS = " ORDEN.ID_ESTATUS IN ( {LST_ESTATUS} ) ";

    private static final String LST_ESTATUS = "{LST_ESTATUS}";

    private static final String SQL_CONDICION_ARACES = " PROPUESTA.ID_ARACE IN ( {IDS} )";

    private static final String SQL_CONDICION_WHERE = " WHERE 1=1 AND ORDEN.BLN_COMPULSA = 0 \n";

    private static final String ID_TEMPLATE = "{IDS}";

    private static final String SQL_CONDICION_METODO = " ORDEN.ID_METODO IN ( {LST_METODO} ) ";

    private static final String LST_METODO = "{LST_METODO}";

    private static final String SQL_CONDICION_ID_FIRMANTE = " AND ORDEN.ID_FIRMANTE = ? ";
    private static final String SQL_CONDICION_ID_AUDITOR = " AND ORDEN.ID_AUDITOR = ? ";

    private static final String SQL_INNER_JOIN_PROPUESTA = " INNER JOIN FECET_PROPUESTA PROPUESTA ON PROPUESTA.ID_PROPUESTA = ORDEN.ID_PROPUESTA ";

    private static final String SQL_LEFT_JOIN_CIFRA_1 = " LEFT JOIN ( SELECT ID_ORDEN, SUM(TOTAL) TOTAL, LISTAGG(CIFRA, ', ') WITHIN GROUP (ORDER BY CIFRA) AS DESCRIPCION_CIFRAS FROM ( "
            + " SELECT CIFRA_TOTALES.ID_ORDEN,  CIFRA_TOTALES.CIFRA, (IMPORTES + ACTUALIZACIONES + MULTAS + RECARGOS) AS TOTAL FROM ( "
            + " SELECT DETALLE_CIFRA.ID_ORDEN, CIFRA.CIFRA, SUM (CASE WHEN CIFRA_IMPUESTO.IMPORTE IS NOT NULL THEN CIFRA_IMPUESTO.IMPORTE ELSE 0 END) IMPORTES, "
            + " SUM (CASE WHEN CIFRA_IMPUESTO.ACTUALIZACIONES IS NOT NULL THEN CIFRA_IMPUESTO.ACTUALIZACIONES ELSE 0 END ) ACTUALIZACIONES, "
            + " SUM (CASE WHEN CIFRA_IMPUESTO.MULTAS IS NOT NULL THEN CIFRA_IMPUESTO.MULTAS ELSE 0 END) MULTAS, "
            + " SUM (CASE WHEN CIFRA_IMPUESTO.RECARGOS IS NOT NULL THEN CIFRA_IMPUESTO.RECARGOS ELSE 0 END) RECARGOS "
            + " FROM FECEC_CIFRA CIFRA "
            + " INNER JOIN FECEA_CIFRA_TIPO_CIFRA TIPO_CIFRA on CIFRA.ID_CIFRA = TIPO_CIFRA.ID_CIFRA "
            + " INNER JOIN FECEA_CIFRA_IMPUESTO CIFRA_IMPUESTO on TIPO_CIFRA.ID_CIFRA_TIPO_CIFRA = CIFRA_IMPUESTO.ID_CIFRA_TIPO_CIFRA "
            + " INNER JOIN FECET_CIFRA DETALLE_CIFRA ON CIFRA_IMPUESTO.ID_DETALLE_CIFRA = DETALLE_CIFRA.ID_DETALLE_CIFRA ";

    private static final String SQL_LEFT_JOIN_CIFRA_2 = " GROUP BY CIFRA.CIFRA, DETALLE_CIFRA.ID_ORDEN "
            + " ) CIFRA_TOTALES ) CIFRAS GROUP BY ID_ORDEN ) CIFRAS ON CIFRAS.ID_ORDEN = ORDEN.ID_ORDEN ";

    private static final String SQL_CONDICION_CIFRA = " AND CIFRA.ID_CIFRA IN ( {LST_CIFRA} ) ";

    private static final String LST_CIFRA = " {LST_CIFRA} ";

    private static final String SQL_CONDICION_CIFRA_DESDE = " AND CIFRAS.TOTAL >= {DESDE} ";

    private static final String SQL_CONDICION_CIFRA_HASTA = " AND CIFRAS.TOTAL <= {HASTA}";

    private static final String CIFRA_DESDE = "{DESDE}";

    private static final String CIFRA_HASTA = "{HASTA}";

    private static final String ID_ARACE = " , PROPUESTA.ID_ARACE ";

    private static final String CAMPOS_CIFRAS = ", CIFRAS.DESCRIPCION_CIFRAS, CIFRAS.TOTAL ";

    private static final String HEADER_COUNT_ORDENES = "SELECT " + "COUNT(*) TOTAL \n" + "FROM FECET_ORDEN ORDEN ";

    private static final String COLUM_TOTAL = "TOTAL";

    private static final String SELECT_NO_REFERENCIA_ORDEN = "SELECT NUMERO_REFERENCIA FROM FECET_ORDEN WHERE ID_ORDEN = ?";

    private static final String UPDATE_NO_REFERENCIA_ORDEN = "UPDATE FECET_ORDEN  SET NUMERO_REFERENCIA = ? WHERE ID_ORDEN = ? AND NUMERO_REFERENCIA IS NULL";

    // TODO - Eliminar columnas FECHA_NOTIF_NYV, FECHA_NOTIF_CONT,
    // FECHA_SURTE_EFECTOS, DIAS_RESTANTES_PLAZO, DIAS_RESTANTES_DOCUMENTOS
    // cuando se eliminen de la bd
    private StringBuilder getSqlColumnsOrden() {
        StringBuilder sqlColumnsOrden = new StringBuilder();
        sqlColumnsOrden.append(" ");
        sqlColumnsOrden.append("SELECT \n ");
        sqlColumnsOrden.append(
                "   ORDEN.ID_ORDEN,  ORDEN.ID_METODO,  ORDEN.ID_REVISION, ORDEN.NUMERO_ORDEN, ORDEN.FECHA_CREACION, \n ");
        sqlColumnsOrden.append(
                "   ORDEN.FECHA_BAJA, ORDEN.PRIORIDAD, ORDEN.FOLIO_NYV, ORDEN.CADENA_ORIGINAL, ORDEN.FIRMA_ELECTRONICA, \n ");
        sqlColumnsOrden.append("   ORDEN.FECHA_NOTIF_NYV, ORDEN.FECHA_NOTIF_CONT, \n");
        sqlColumnsOrden.append("   ORDEN.FECHA_SURTE_EFECTOS, ORDEN.DIAS_RESTANTES_PLAZO, ORDEN.DIAS_HABILES, \n ");
        sqlColumnsOrden.append("   ORDEN.SUSPENCION_PLAZO, ORDEN.DIAS_RESTANTES_DOCUMENTOS, ORDEN.SEMAFORO, \n ");
        sqlColumnsOrden.append("   ORDEN.FECHA_INTEGRA_EXP, ORDEN.ID_CONTRIBUYENTE, ORDEN.ID_ESTATUS, \n ");
        sqlColumnsOrden.append(" ORDEN.ID_AUDITOR, ORDEN.ID_FIRMANTE, ORDEN.ID_PROPUESTA, ORDEN.ID_REGISTRO_PROPUESTA");
        sqlColumnsOrden.append(
                ", ORDEN.FECHA_REACTIVAR_PLAZO, ORDEN.FECHA_SUSPENCION_PLAZO, ORDEN.DIAS_RESOLUCION_DEFINITIVA, ORDEN.FOLIO_OFICIO, ORDEN.BLN_COMPULSA, ORDEN.ID_NYV ");

        return sqlColumnsOrden;
    }

    private String getSqlColumnsAsociado() {
        StringBuilder sqlColumnsAsociado = new StringBuilder();
        sqlColumnsAsociado.append(" ASOCIADO.ID_ASOCIADO, ");
        sqlColumnsAsociado.append(" ASOCIADO.ID_ORDEN, ");
        sqlColumnsAsociado.append(" ASOCIADO.ID_TIPO_ASOCIADO, ");
        sqlColumnsAsociado.append(" ASOCIADO.NOMBRE, ");
        sqlColumnsAsociado.append(" ASOCIADO.RFC, ");
        sqlColumnsAsociado.append(" ASOCIADO.CORREO, ");
        sqlColumnsAsociado.append(" ASOCIADO.TIPO_ASOCIADO, ");
        sqlColumnsAsociado.append(" ASOCIADO.MEDIO_CONTACTO, ");
        sqlColumnsAsociado.append(" ASOCIADO.FECHA_BAJA, ");
        sqlColumnsAsociado.append(" ASOCIADO.FECHA_ULTIMA_MOD, ");
        sqlColumnsAsociado.append(" ASOCIADO.FECHA_ULTIMA_MOD_IDC, ");
        sqlColumnsAsociado.append(" ASOCIADO.ESTATUS, ");
        sqlColumnsAsociado.append(" ASOCIADO.RFC_CONTRIBUYENTE ");
        return sqlColumnsAsociado.toString();
    }

    private StringBuilder getSqlSelectOrdenesMetodosAsociado() {
        StringBuilder sqlSelectOrdenesMetodos = new StringBuilder();

        sqlSelectOrdenesMetodos.append(getSqlColumnsOrden());
        sqlSelectOrdenesMetodos.append(SELECT_ABREVIATURA_NOMBRE);
        sqlSelectOrdenesMetodos.append(SELECT_DATOS_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(SELECT_ESTATUS_MODULO_DESCRIPCION);
        sqlSelectOrdenesMetodos.append(",");
        sqlSelectOrdenesMetodos.append(getSqlColumnsAsociado());
        sqlSelectOrdenesMetodos.append(FROM).append(AgaceOrdenHelper.getTableName());
        sqlSelectOrdenesMetodos.append(" ORDEN INNER JOIN \n ");
        sqlSelectOrdenesMetodos.append(FeceaMetodoDaoImpl.getTableName());
        sqlSelectOrdenesMetodos.append(ALIAS_METODO);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_METODO = METODO.ID_METODO \n");
        sqlSelectOrdenesMetodos.append(JOIN_FECET_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n ");
        sqlSelectOrdenesMetodos.append(" INNER JOIN FECEC_ESTATUS ESTATUS \n ");
        sqlSelectOrdenesMetodos.append(" ON  ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n ");

        return sqlSelectOrdenesMetodos;
    }

    private StringBuilder getSqlSelectOrdenesMetodos() {
        StringBuilder sqlSelectOrdenesMetodos = new StringBuilder();

        sqlSelectOrdenesMetodos.append(getSqlColumnsOrden());
        sqlSelectOrdenesMetodos.append(SELECT_ABREVIATURA_NOMBRE);
        sqlSelectOrdenesMetodos.append(SELECT_DATOS_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(SELECT_ESTATUS_MODULO_DESCRIPCION);
        sqlSelectOrdenesMetodos.append(FROM).append(AgaceOrdenHelper.getTableName());
        sqlSelectOrdenesMetodos.append(" ORDEN INNER JOIN \n ");
        sqlSelectOrdenesMetodos.append(FeceaMetodoDaoImpl.getTableName());
        sqlSelectOrdenesMetodos.append(ALIAS_METODO);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_METODO = METODO.ID_METODO \n");
        sqlSelectOrdenesMetodos.append(JOIN_FECET_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n ");
        sqlSelectOrdenesMetodos.append(" INNER JOIN FECEC_ESTATUS ESTATUS \n ");
        sqlSelectOrdenesMetodos.append(" ON  ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n ");

        return sqlSelectOrdenesMetodos;
    }

    private StringBuilder getSqlSelectOrdenesConsultaEjecutiva(FiltroOrdenes filtroDao) {
        StringBuilder sqlSelectOrdenesMetodos = new StringBuilder();
        sqlSelectOrdenesMetodos.append(getSqlColumnsOrden());
        sqlSelectOrdenesMetodos.append(SELECT_ABREVIATURA_NOMBRE);
        sqlSelectOrdenesMetodos.append(SELECT_DATOS_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(SELECT_ESTATUS_MODULO_DESCRIPCION);
        sqlSelectOrdenesMetodos.append(ID_ARACE);
        sqlSelectOrdenesMetodos.append(CAMPOS_CIFRAS);
        sqlSelectOrdenesMetodos.append(FROM).append(AgaceOrdenHelper.getTableName());
        sqlSelectOrdenesMetodos.append(" ORDEN INNER JOIN \n ");
        sqlSelectOrdenesMetodos.append(FeceaMetodoDaoImpl.getTableName());
        sqlSelectOrdenesMetodos.append(ALIAS_METODO);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_METODO = METODO.ID_METODO \n");
        sqlSelectOrdenesMetodos.append(JOIN_FECET_CONTRIBUYENTE);
        sqlSelectOrdenesMetodos.append(" ON ORDEN.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n ");
        sqlSelectOrdenesMetodos.append(" INNER JOIN FECEC_ESTATUS ESTATUS \n ");
        sqlSelectOrdenesMetodos.append(" ON  ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n ");
        sqlSelectOrdenesMetodos.append(SQL_INNER_JOIN_PROPUESTA);
        sqlSelectOrdenesMetodos.append(SQL_LEFT_JOIN_CIFRA_1);
        if (filtroDao.getCifraFiltro() != null) {
            sqlSelectOrdenesMetodos.append(SQL_CONDICION_CIFRA.replace(LST_CIFRA,
                    OrdenDaoHelper.getSQLCifrasOrdenes(filtroDao.getCifraFiltro())));
        }
        sqlSelectOrdenesMetodos.append(SQL_LEFT_JOIN_CIFRA_2);
        sqlSelectOrdenesMetodos.append(SQL_CONDICION_WHERE);

        return sqlSelectOrdenesMetodos;
    }

    @Override
    public AgaceOrdenPk insert(AgaceOrden dto) {

        if (dto.getIdOrden() == null) {
            dto.setIdOrden(
                    getJdbcTemplateBase().queryForObject("SELECT FECEQ_ORDEN.NEXTVAL FROM DUAL", BigDecimal.class));
        }

        getJdbcTemplateBase().update(
                "INSERT INTO " + AgaceOrdenHelper.getTableName()
                + " ( ID_ORDEN, ID_METODO, ID_REVISION, NUMERO_ORDEN, FECHA_CREACION, FECHA_BAJA, PRIORIDAD, FOLIO_NYV, CADENA_ORIGINAL, FIRMA_ELECTRONICA, FECHA_NOTIF_NYV, FECHA_NOTIF_CONT, FECHA_SURTE_EFECTOS, DIAS_RESTANTES_PLAZO, DIAS_HABILES, SUSPENCION_PLAZO, DIAS_RESTANTES_DOCUMENTOS, SEMAFORO, FECHA_INTEGRA_EXP, ID_CONTRIBUYENTE, ID_ESTATUS, ID_AUDITOR, ID_FIRMANTE, ID_PROPUESTA, ID_REGISTRO_PROPUESTA, FECHA_REACTIVAR_PLAZO, FECHA_SUSPENCION_PLAZO, DIAS_RESOLUCION_DEFINITIVA, FOLIO_OFICIO, BLN_COMPULSA, ID_NYV ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dto.getIdOrden(), dto.getIdMetodo(), dto.getIdRevision(),
                dto.getNumeroOrden() != null ? dto.getNumeroOrden().toUpperCase() : dto.getNumeroOrden(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.isPrioridadNull() ? null : dto.getPrioridadSugerida(),
                dto.getFolioNYV(), dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getFechaNotifNYV(),
                dto.getFechaNotifCont(), dto.getFechaSurteEfectos(), dto.getDiasRestantesPlazo(), dto.getDiasHabiles(),
                dto.getSuspencionPlazo(), dto.getDiasRestantesDocumentos(), dto.getSemaforo(), dto.getFechaIntegraExp(),
                dto.getIdContribuyente(), dto.getIdEstatus(), dto.getIdAuditor(), dto.getIdFirmante(),
                dto.getIdPropuesta(), dto.getIdRegistroPropuesta(), dto.getFechaReactivarPlazo(),
                dto.getFechaSuspencionPlazo(), dto.getDiasResolucionDefinitiva(), dto.getFolioOficio(),
                dto.isBlnCompulsa(), dto.getIdNyV());

        return dto.createPk();
    }

    @Override
    public void update(AgaceOrdenPk pk, AgaceOrden dto) {

        getJdbcTemplateBase().update(
                UPDATE.append(AgaceOrdenHelper.getTableName())
                .append(" SET ID_ORDEN = ?, ID_METODO = ?, ID_REVISION = ?, NUMERO_ORDEN = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, PRIORIDAD = ?, FOLIO_NYV = ?, CADENA_ORIGINAL = ?, FIRMA_ELECTRONICA = ?, FECHA_NOTIF_NYV = ?, FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, DIAS_RESTANTES_PLAZO = ?, DIAS_HABILES = ?, SUSPENCION_PLAZO = ?, DIAS_RESTANTES_DOCUMENTOS = ?, SEMAFORO = ?, FECHA_INTEGRA_EXP = ?, ID_CONTRIBUYENTE = ?, ID_ESTATUS = ?, ID_AUDITOR = ?, ID_FIRMANTE = ?, ID_PROPUESTA = ?, ID_REGISTRO_PROPUESTA = ?, FECHA_REACTIVAR_PLAZO = ?, FECHA_SUSPENCION_PLAZO = ?, DIAS_RESOLUCION_DEFINITIVA = ?, FOLIO_OFICIO = ?, BLN_COMPULSA = ? WHERE ID_ORDEN = ?")
                .toString(),
                dto.getIdOrden(), dto.getIdMetodo(), dto.getIdRevision(), dto.getNumeroOrden().toUpperCase(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.isPrioridadNull() ? null : dto.getPrioridad(),
                dto.getFolioNYV(), dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getFechaNotifNYV(),
                dto.getFechaNotifCont(), dto.getFechaSurteEfectos(), dto.getDiasRestantesPlazo(), dto.getDiasHabiles(),
                dto.getSuspencionPlazo(), dto.getDiasRestantesDocumentos(), dto.getSemaforo(), dto.getFechaIntegraExp(),
                dto.getIdContribuyente(), dto.getIdEstatus(), dto.getIdAuditor(), dto.getIdFirmante(),
                dto.getIdPropuesta(), dto.getIdRegistroPropuesta(), dto.getFechaReactivarPlazo(),
                dto.getFechaSuspencionPlazo(), dto.getDiasResolucionDefinitiva(), dto.getFolioOficio(),
                dto.isBlnCompulsa(), pk.getIdOrden());

    }

    @Override
    public void updateEstatus(AgaceOrdenPk pk, AgaceOrden dto) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE);
        query.append(AgaceOrdenHelper.getTableName());
        query.append(" SET ID_ESTATUS = ? WHERE ID_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdEstatus(), pk.getIdOrden());
    }

    @Override
    public void updateFechaBaja(AgaceOrdenPk pk) {

        getJdbcTemplateBase().update(UPDATE.append(AgaceOrdenHelper.getTableName())
                .append(" SET FECHA_BAJA = SYSDATE WHERE ID_ORDEN = ?").toString(), pk.getIdOrden());

    }

    @Override
    public void updateFechasValidas(AgaceOrdenPk pk, AgaceOrden dto) {

        getJdbcTemplateBase().update(
                UPDATE.append(AgaceOrdenHelper.getTableName())
                .append(" SET ID_ORDEN = ?, ID_METODO = ?, ID_REVISION = ?, NUMERO_ORDEN = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, PRIORIDAD = ?, FOLIO_NYV = ?, CADENA_ORIGINAL = ?, FIRMA_ELECTRONICA = ?, FECHA_NOTIF_NYV = (SYSDATE - 30), FECHA_NOTIF_CONT = (SYSDATE - 30), FECHA_SURTE_EFECTOS = ?, DIAS_RESTANTES_PLAZO = ?, DIAS_HABILES = ?, SUSPENCION_PLAZO = ?, DIAS_RESTANTES_DOCUMENTOS = ?, SEMAFORO = ?, FECHA_INTEGRA_EXP = ?, ID_CONTRIBUYENTE = ?, ID_ESTATUS = ?, ID_AUDITOR = ?, ID_FIRMANTE = ?, ID_PROPUESTA = ?, ID_REGISTRO_PROPUESTA = ?, FECHA_REACTIVAR_PLAZO = ?, FECHA_SUSPENCION_PLAZO = ?, DIAS_RESOLUCION_DEFINITIVA = ?, FOLIO_OFICIO = ?, BLN_COMPULSA = ? WHERE ID_ORDEN = ?")
                .toString(),
                dto.getIdOrden(), dto.getIdMetodo(), dto.getIdRevision(), dto.getNumeroOrden().toUpperCase(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.isPrioridadNull() ? null : dto.getPrioridad(),
                dto.getFolioNYV(), dto.getCadenaOriginal(), dto.getFirmaElectronica(), dto.getFechaSurteEfectos(),
                dto.getDiasRestantesPlazo(), dto.getDiasHabiles(), dto.getSuspencionPlazo(),
                dto.getDiasRestantesDocumentos(), dto.getSemaforo(), dto.getFechaIntegraExp(), dto.getIdContribuyente(),
                dto.getIdEstatus(), dto.getIdAuditor(), dto.getIdFirmante(), dto.getIdPropuesta(),
                dto.getIdRegistroPropuesta(), dto.getFechaReactivarPlazo(), dto.getFechaSuspencionPlazo(),
                dto.getDiasResolucionDefinitiva(), dto.getFolioOficio(), dto.isBlnCompulsa(), pk.getIdOrden());

    }

    @Override
    public AgaceOrden findByPrimaryKey(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        List<AgaceOrden> list = getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idOrden);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public AgaceOrden findByNumeroOrden(String numeroOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE_SIMPLE);
        query.append("NUMERO_ORDEN = ?");

        List<AgaceOrden> list = getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), numeroOrden);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<AgaceOrden> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" ORDER BY ID_ORDEN").toString(), new AgaceOrdenMapper());

    }

    @Override
    public List<AgaceOrden> findWhereIdOrdenEquals(long idOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" AND ID_ORDEN = ? ORDER BY ID_ORDEN").toString(),
                new AgaceOrdenMapper(), idOrden);

    }

    @Override
    public List<AgaceOrden> obtenerOrden(BigDecimal idOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" AND ID_ORDEN = ? ORDER BY ID_ORDEN").toString(),
                new AgaceOrdenMapper(), idOrden);

    }

    @Override
    public List<AgaceOrden> findWhereIdMetodoEquals(long idMetodo) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" AND ID_METODO = ? ORDER BY ID_METODO").toString(),
                new AgaceOrdenMapper(), idMetodo);

    }

    @Override
    public List<AgaceOrden> findWhereNumeroOrdenEquals(String numeroOrden) {
        StringBuilder sql = new StringBuilder();
        sql.append(OrdenSql.SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(OrdenSql.SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND ORD.NUMERO_ORDEN = ? ORDER BY ORD.NUMERO_ORDEN").toString(),
                new AgaceOrdenMapper(), numeroOrden);

    }

    @Override
    public List<AgaceOrden> findWhereFechaCreacionEquals(Date fechaCreacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND FECHA_CREACION = ? ORDER BY FECHA_CREACION").toString(), new AgaceOrdenMapper(),
                fechaCreacion);

    }

    @Override
    public List<AgaceOrden> findWhereFechaBajaEquals(Date fechaBaja) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" AND FECHA_BAJA = ? ORDER BY FECHA_BAJA").toString(),
                new AgaceOrdenMapper(), fechaBaja);

    }

    @Override
    public List<AgaceOrden> findWherePrioridadEquals(Boolean prioridad) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL).append(" AND PRIORIDAD = ? ORDER BY PRIORIDAD").toString(),
                new AgaceOrdenMapper(), prioridad);

    }

    @Override
    public List<AgaceOrden> findWhereFechaNotifNYVEquals(Date fechaNotifNYV) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND FECHA_NOTIF_NYV = ? ORDER BY FECHA_NOTIF_NYV").toString(), new AgaceOrdenMapper(),
                fechaNotifNYV);

    }

    @Override
    public List<AgaceOrden> findWhereFechaNotifContEquals(Date fechaNotifCont) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND FECHA_NOTIF_CONT = ? ORDER BY FECHA_NOTIF_CONT").toString(), new AgaceOrdenMapper(),
                fechaNotifCont);

    }

    @Override
    public List<AgaceOrden> findWhereFechaIntegraExpEquals(Date fechaIntegraExp) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND FECHA_INTEGRA_EXP = ? ORDER BY FECHA_INTEGRA_EXP").toString(), new AgaceOrdenMapper(),
                fechaIntegraExp);

    }

    @Override
    public List<AgaceOrden> findWhereFechaSurteEfectosEquals(final Date fechaSurteEfectos) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(
                sql.append(SQL_WHERE_FECHA_BAJA_NULL)
                .append(" AND FECHA_SURTE_EFECTOS = ? ORDER BY FECHA_SURTE_EFECTOS").toString(),
                new AgaceOrdenMapper(), fechaSurteEfectos);

    }

    @Override
    public List<AgaceOrden> findWhereEstatusEquals(final BigDecimal idEstatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE_FECHA_BAJA_NULL);
        query.append(" AND ID_ESTATUS = ? ORDER BY ID_ESTATUS");

        return getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idEstatus);

    }

    @Override
    public AgaceOrden findByPrimaryKey(AgaceOrdenPk pk) {
        return findByPrimaryKey(pk.getIdOrden());
    }

    @Override
    public AgaceOrden findByIdPropuesta(final BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE_FECHA_BAJA_NULL);
        query.append(" AND ID_PROPUESTA = ?");
        List<AgaceOrden> list = null;
        list = getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idPropuesta);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<AgaceOrden> getListaAuditor(final String rfcAuditor) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(SQL_WHERE_FECHA_BAJA_NULL).append("\n");
        sqlWhere.append(ORDER_BY);

        return getJdbcTemplateBase().query(getSqlSelectOrdenesMetodos().append(sqlWhere).toString(),
                new AgaceOrdenConMetodoMapper(), rfcAuditor);

    }

    @Override
    public List<AgaceOrden> getOrdenesReimprimirDocumentacion() {

        return getJdbcTemplateBase().query(getSqlSelectOrdenesMetodos().append(SQL_WHERE_FECHA_BAJA_NULL).toString(),
                new AgaceOrdenConMetodoMapper());

    }

    @Override
    public List<AgaceOrden> getOrdenesSeguimientoAuditor(final BigDecimal idEmpleado) {

        StringBuilder sqlWhere = new StringBuilder();

        sqlWhere.append(getSqlSelectOrdenesMetodos());
        sqlWhere.append(SQL_WHERE_FECHA_BAJA_NULL).append("\n");
        sqlWhere.append(" AND ORDEN.ID_AUDITOR = ? \n");
        sqlWhere.append(" AND ORDEN.ID_ESTATUS NOT IN (?, ?)");
        sqlWhere.append(" AND ORDEN.BLN_COMPULSA = 0 ");
        sqlWhere.append(SQL_AND_ORDER_BY_FECHA_CREACION);

        return getJdbcTemplateBase().query(sqlWhere.toString(), new AgaceOrdenConMetodoMapper(), idEmpleado,
                EstatusOrdenes.CONCLUIDA.getIdEstatus(), EstatusOrdenes.CONCLUIDA_POR_CAMBIO_METODO.getIdEstatus());

    }

    @Override
    public List<AgaceOrden> getPropuestasOrdenesPorAsociado(final BigDecimal idEmpleado) {

        StringBuilder sqlWhere = new StringBuilder();

        sqlWhere.append(getSqlColumnsOrden().toString().replaceAll("ORDEN.ID_PROPUESTA, ORDEN.ID_REGISTRO_PROPUESTA",
                "PROPUESTAS.ID_PROPUESTA, PROPUESTAS.ID_REGISTRO ID_REGISTRO_PROPUESTA"));
        sqlWhere.append(SELECT_ABREVIATURA_NOMBRE);
        sqlWhere.append(SELECT_DATOS_CONTRIBUYENTE);
        sqlWhere.append(SELECT_ESTATUS_MODULO_DESCRIPCION);
        sqlWhere.append(FROM).append(PropuestasSQL.NAME_TABLE);
        sqlWhere.append(" PROPUESTAS INNER JOIN \n ");
        sqlWhere.append(FeceaMetodoDaoImpl.getTableName());
        sqlWhere.append(ALIAS_METODO);
        sqlWhere.append(" ON PROPUESTAS.ID_METODO = METODO.ID_METODO \n");
        sqlWhere.append(JOIN_FECET_CONTRIBUYENTE);
        sqlWhere.append(" ON PROPUESTAS.ID_CONTRIBUYENTE = CONT.ID_CONTRIBUYENTE \n ");
        sqlWhere.append(" INNER JOIN FECET_ASOCIADOS_FUNCIONARIOS ASOCIADOS \n ");
        sqlWhere.append(" ON PROPUESTAS.ID_PROPUESTA = ASOCIADOS.ID_PROPUESTA \n ");
        sqlWhere.append(" LEFT JOIN FECET_ORDEN ORDEN \n ");
        sqlWhere.append(" ON ORDEN.ID_PROPUESTA = PROPUESTAS.ID_PROPUESTA \n ");
        sqlWhere.append(" LEFT JOIN FECEC_ESTATUS ESTATUS \n ");
        sqlWhere.append(" ON ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n ");
        sqlWhere.append(" WHERE ASOCIADOS.ID_EMPLEADO = ? AND ASOCIADOS.BLN_ACTIVO = ?\n");
        sqlWhere.append(SQL_AND_ORDER_BY_FECHA_CREACION);

        return getJdbcTemplateBase().query(sqlWhere.toString(), new AgaceOrdenConMetodoMapper(), idEmpleado, Constantes.UNO);
    }

    @Override
    public List<AgaceOrden> getOrdenesContribuyente(final String rfcContribuyente, BigDecimal idEstatus) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(getSqlSelectOrdenesMetodos());
        sqlWhere.append(SQL_INNER_JOIN_CONTRIBUYENTE);
        sqlWhere.append(SQL_WHERE_FECHA_BAJA_NULL).append("\n");
        sqlWhere.append(SQL_AND_ID_ESTATUS_ORDEN);
        sqlWhere.append(SQL_AND_RFC_CONTRIBUYENTE);
        sqlWhere.append(SQL_AND_BLN_COMPULSA);
        sqlWhere.append(SQL_AND_ORDER_BY_FECHA_CREACION);
        return getJdbcTemplateBase().query(sqlWhere.toString(), new AgaceOrdenConMetodoMapper(), idEstatus,
                rfcContribuyente);
    }

    @Override
    public List<AgaceOrden> getOrdenesAsociado(final String rfcAsociado, BigDecimal idTipoAsociado,
            String rfcContribuyente) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(getSqlSelectOrdenesMetodosAsociado());
        sqlWhere.append(SQL_INNER_JOIN_CONTRIBUYENTE);
        sqlWhere.append(SQL_INNER_JOIN_ASOCIADO);
        sqlWhere.append(" WHERE ORDEN.FECHA_BAJA IS NULL ").append("\n");
        sqlWhere.append("  AND ORDEN.ID_ESTATUS IN (?,?,?,?)");
        sqlWhere.append(SQL_AND_RFC_ASOCIADO);
        sqlWhere.append(SQL_AND_ID_TIPO_ASOCIADO);
        sqlWhere.append(SQL_AND_RFC_ASOCIADO_CONTRIBUYENTE);
        sqlWhere.append(SQL_AND_RFC_CONTRIBUYENTE);
        sqlWhere.append(SQL_AND_FECHA_BAJA_ASOCIADO);
        sqlWhere.append(SQL_AND_ORDER_BY_FECHA_CREACION);

        return getJdbcTemplateBase().query(sqlWhere.toString(), new AgaceOrdenConMetodoMapper(), EstatusOrdenes.ENVIADO_NOTIFICACION_CONTRIBUYENTE.getBigIdEstatus(),
                EstatusOrdenes.NOTIFICADO_AL_CONTRIBUYENTE.getBigIdEstatus(),
                EstatusOrdenes.CONCLUIDA.getBigIdEstatus(),
                EstatusOrdenes.CONCLUIDA_POR_CAMBIO_METODO.getBigIdEstatus(),
                rfcAsociado, idTipoAsociado, rfcContribuyente);
    }

    @Override
    public void updateFolioNyV(AcuseDTO acuseDto) {
        getJdbcTemplateBase().update(UPDATE.append(AgaceOrdenHelper.getTableName())
                .append(" SET FOLIO_NYV = ? WHERE ID_ORDEN = ?").toString(), acuseDto.getFolioNyV(),
                acuseDto.getFolioActaAdministrativo());
    }

    @Override
    public Long getClaveOrden() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_CVE_ORDEN.NEXTVAL FROM DUAL", Long.class);
    }

    @Override
    public BigDecimal getNextOValueOrden() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_ORDEN.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public List<AgaceOrden> traeOrdenesSeguimientoAuditor(String rfcAuditor) {

        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(AgaceOrdenHelper.getSqlSelectOrdenContribuyente()).append("\n");
        sqlWhere.append(SQL_WHERE_FECHA_BAJA_NULL).append("\n");
        sqlWhere.append("AND FECET_ORDEN.ID_ESTATUS = 5");

        return getJdbcTemplateBase().query(sqlWhere.toString() + " ORDER BY FECET_ORDEN.FECHA_CREACION DESC",
                new AgaceOrdenConRelacionesMapper());

    }

    @Override
    public void updatePlazosOrden(AgaceOrden dto) {
        StringBuilder sql = new StringBuilder();
        sql.append(UPDATE);
        sql.append(AgaceOrdenHelper.getTableName());
        sql.append(
                " SET  FECHA_SURTE_EFECTOS = ?, DIAS_RESTANTES_PLAZO = ?, DIAS_RESTANTES_DOCUMENTOS = ?, DIAS_HABILES = ?, ID_ESTATUS = ? WHERE ID_ORDEN = ? ");
        getJdbcTemplateBase().update(sql.toString(), dto.getFechaSurteEfectos(), dto.getDiasRestantesPlazo(),
                dto.getDiasRestantesDocumentos(), dto.getDiasHabiles(), dto.getIdEstatus(), dto.getIdOrden());

    }

    @Override
    public List<ContadorOrdenes> numeroOrdenesValidarFirmar(BigDecimal idEmpleado, String rfcFirmante) {
        Object[] params = new Object[Constantes.ENTERO_DIEZ];
        for (int i = 0; i < params.length; i++) {
            params[i] = rfcFirmante;
        }
        return getJdbcTemplateBase().query(AgaceOrdenHelper.getSqlSelectContadorOrdenes(), params,
                new ContadorOrdenesMapper());
    }

    @Override
    public void integraExpediente(final AgaceOrden orden, final Date fechaExpediente) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(AgaceOrdenHelper.getTableName())
                .append(" SET FECHA_INTEGRA_EXP = ? WHERE ID_ORDEN = ? ");

        getJdbcTemplateBase().update(query.toString(), fechaExpediente, orden.getIdOrden());
    }

    @Override
    public void reactivaPlazoREE(final AgaceOrden orden) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(AgaceOrdenHelper.getTableName())
                .append(" SET FECHA_REACTIVAR_PLAZO = ?, ID_ESTATUS = ? WHERE ID_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(), new Date(), Constantes.ESTATUS_ORDEN_REACTIVADA_COMPULSAS_REE,
                orden.getIdOrden());
    }

    @Override
    public void reactivaPlazoAcuerdoConclusivo(final AgaceOrden orden) {
        StringBuilder query = new StringBuilder();
        query.append(UPDATE).append(AgaceOrdenHelper.getTableName())
                .append(" SET FECHA_REACTIVAR_PLAZO = ?, ID_ESTATUS = ? WHERE ID_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(), new Date(),
                Constantes.ESTATUS_ORDEN_REACTIVADA_ACUERDO_CONCLUSIVO, orden.getIdOrden());
    }

    @Override
    public List<AgaceOrden> getOrdenesPorFirmarPorMetodo(BigDecimal idEstatus, BigDecimal idMetodo,
            BigDecimal idEmpleado) {
        StringBuilder query = new StringBuilder();
        String querySelectFrom = "SELECT FO.ID_ORDEN, FO.ID_METODO, FO.ID_REVISION, FO.NUMERO_ORDEN, FO.FECHA_CREACION, FO.FECHA_BAJA, FP.PRIORIDAD, FO.FOLIO_NYV, "
                + " FO.CADENA_ORIGINAL, FO.FIRMA_ELECTRONICA, FO.FECHA_NOTIF_NYV, FO.FECHA_NOTIF_CONT, FO.FECHA_SURTE_EFECTOS, FO.DIAS_RESTANTES_PLAZO, "
                + " FO.DIAS_HABILES, FO.SUSPENCION_PLAZO, FO.DIAS_RESTANTES_DOCUMENTOS, FO.SEMAFORO, FO.FECHA_INTEGRA_EXP, FO.ID_CONTRIBUYENTE, FP.ID_ESTATUS, FO.ID_AUDITOR, "
                + " FO.ID_FIRMANTE, FO.ID_PROPUESTA, FO.ID_REGISTRO_PROPUESTA, FO.FECHA_REACTIVAR_PLAZO, FO.FECHA_SUSPENCION_PLAZO, FO.DIAS_RESOLUCION_DEFINITIVA, FO.FOLIO_OFICIO, FO.BLN_COMPULSA "
                + " FROM FECET_ORDEN FO INNER JOIN FECET_PROPUESTA FP ON (FO.ID_PROPUESTA = FP.ID_PROPUESTA) ";
        String queryWhere = " WHERE FO.FECHA_BAJA IS NULL AND FP.ID_ESTATUS = ? AND FO.ID_METODO = ? AND FO.ID_FIRMANTE = ? ORDER BY FP.PRIORIDAD, FP.FECHA_CREACION ";
        query.append(querySelectFrom);
        query.append(queryWhere);
        return getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idEstatus, idMetodo, idEmpleado);
    }

    @Override
    public List<AgaceOrden> obtenerOrdenesFirmadas(BigDecimal idEstatus, BigDecimal idEmpleado, BigDecimal idMetodo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE_FECHA_BAJA_NULL);
        query.append(" AND ID_ESTATUS = ? AND ID_FIRMANTE = ? AND ID_METODO = ? ORDER BY FECHA_CREACION DESC ");
        return getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idEstatus, idEmpleado, idMetodo);
    }

    @Override
    public void updateNumOrdenAndNumFolio(String numOrden, String folioOficio, BigDecimal idPropuesta,
            final int idEstatus) {
        StringBuilder query = new StringBuilder();
        Object[] parameters = {numOrden, folioOficio, idEstatus, idPropuesta};
        query.append(UPDATE);
        query.append(AgaceOrdenHelper.getTableName());
        query.append(" SET NUMERO_ORDEN = ? ");
        query.append(", FOLIO_OFICIO = ? ");
        query.append(", ID_ESTATUS = ? ");
        query.append(" WHERE ID_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), parameters);
    }

    @Override
    public List<AgaceOrden> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ?");

        return getJdbcTemplateBase().query(query.toString(), new AgaceOrdenMapper(), idPropuesta);
    }

    @Override
    public void updateFechasNotificacion(final BigDecimal idOrden, final Date fechaNotificacionCont,
            final Date fechaSurteEfectos, int idEstatus) {
        logger.debug("[updateFechasNotificacion]");
        Object[] parameters = {fechaNotificacionCont, fechaSurteEfectos, idEstatus, idOrden};
        StringBuilder sql = new StringBuilder(UPDATE);
        sql.append(AgaceOrdenHelper.getTableName())
                .append(" SET FECHA_NOTIF_CONT = ?, FECHA_SURTE_EFECTOS = ?, ID_ESTATUS = ? ").append(SQL_WHERE_SIMPLE)
                .append(" ID_ORDEN = ? ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        getJdbcTemplateBase().update(sql.toString(), parameters);
    }

    @Override
    public long getCantidadMaximaOrdenesNotificadas() {
        logger.debug("[getCantidadMaximaOrdenesNotificadas]");
        StringBuilder sql = new StringBuilder(" SELECT COUNT(ID_ORDEN) ");
        sql.append(" FROM FECET_ORDEN ").append(" WHERE FECHA_SURTE_EFECTOS IS NOT NULL ")
                .append(" AND FECHA_BAJA IS NULL ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString()));
        }
        return getJdbcTemplateBase().queryForObject(sql.toString(), Long.class);
    }

    @Override
    public List<AgaceOrden> getOrdenesNotificadasPaginado(int limiteInicial, int limiteFinal,
            final List<Integer> idsOrdenesQuitar) {
        logger.debug("[getOrdenesNotificadasPaginado]");
        final int n2 = 2;
        Object[] parameters = {limiteInicial, limiteFinal};
        if (idsOrdenesQuitar != null && !idsOrdenesQuitar.isEmpty()) {
            parameters[n2] = idsOrdenesQuitar.toArray();
        }
        StringBuilder sql = new StringBuilder(CAMPOS_SQL_SELECT);
        sql.append(" FROM ( ").append(CAMPOS_SQL_SELECT).append(" FROM FECET_ORDEN ")
                .append(" WHERE FECHA_SURTE_EFECTOS IS NOT NULL ");
        if (idsOrdenesQuitar != null && !idsOrdenesQuitar.isEmpty()) {
            sql.append(" AND ID_ORDEN NOT IN( ? ) ");
        }
        sql.append(" ORDER BY ID_ORDEN ASC ").append(" ) ").append(" WHERE ROWNUM > ? AND ROWNUM <= ? ");
        if (logger.isDebugEnabled()) {
            logger.debug(ConsultasUtil.armarValoresConsulta(sql.toString(), parameters));
        }
        return getJdbcTemplateBase().query(sql.toString(), new AgaceOrdenMapper(), parameters);
    }

    @Override
    public void updateDatosDeNyv(AgaceOrden dto) {
        StringBuilder sql = new StringBuilder();
        String setWhere = " SET ID_ESTATUS = ?, ID_FIRMANTE = ? WHERE ID_ORDEN = ? ";
        sql.append(UPDATE);
        sql.append(AgaceOrdenHelper.getTableName());
        sql.append(setWhere);
        getJdbcTemplateBase().update(sql.toString(), dto.getIdEstatus(), dto.getIdFirmante(), dto.getIdOrden());

    }

    @Override
    public void updateOrdenIdNyV(AgaceOrden orden, FecetDetalleNyV nYvResult) {
        StringBuilder sql = new StringBuilder();
        String setWhere = " SET ID_NYV = ? WHERE ID_ORDEN = ? ";

        sql.append(UPDATE);
        sql.append(AgaceOrdenHelper.getTableName());
        sql.append(setWhere);
        getJdbcTemplateBase().update(sql.toString(), nYvResult.getIdNyV(), orden.getIdOrden());
    }

    @Override
    public List<AgaceOrden> getOrdenesContribuyenteCriterio(final String numeroOrden, final String idRegistroPropuesta,
            final String rfcContribuyente, final String idEmpleado, final Integer idArace) {
        StringBuilder query = new StringBuilder();
        List<Object> parametros = new ArrayList<Object>();
        query.append(getSqlSelectOrdenesMetodos());
        query.append(SQL_INNER_JOIN_PROPUESTA);

        query.append("\n WHERE ORDEN.FECHA_BAJA IS NULL \n");

        if (idArace != null) {
            query.append(" AND PROPUESTA.ID_ARACE in (");
            query.append(idArace);
            query.append(" ) \n");
        }

        if (idEmpleado != null) {
            query.append(SQL_AND_ID_AUDITOR_FIRMANTE);
            parametros.add(idEmpleado);
            parametros.add(idEmpleado);
        }

        if (StringUtils.isNotBlank(numeroOrden)) {
            query.append(" AND ORDEN.NUMERO_ORDEN = ? \n");
            parametros.add(numeroOrden);
        }

        if (StringUtils.isNotBlank(idRegistroPropuesta)) {
            query.append(" AND ORDEN.ID_REGISTRO_PROPUESTA = ? \n");
            parametros.add(idRegistroPropuesta);
        }

        if (StringUtils.isNotBlank(rfcContribuyente)) {
            query.append(" AND CONT.RFC = ?  \n");
            parametros.add(rfcContribuyente);
        }

        query.append(SQL_AND_ORDER_BY_METODO_ORDEN);
        return getJdbcTemplateBase().query(query.toString(), new AgaceOrdenConMetodoMapper(), parametros.toArray());
    }

    @Override
    public List<AgaceOrden> getOrdenesAsociadoConsulta(final String rfcAsociado, BigDecimal idTipoAsociado,
            String rfcContribuyente) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(getSqlSelectOrdenesMetodosAsociado());
        sqlWhere.append(SQL_INNER_JOIN_CONTRIBUYENTE);
        sqlWhere.append(SQL_INNER_JOIN_ASOCIADO);
        sqlWhere.append(" WHERE ORDEN.FECHA_BAJA IS NULL ").append("\n");
        sqlWhere.append(SQL_AND_RFC_ASOCIADO);
        sqlWhere.append(SQL_AND_ID_TIPO_ASOCIADO);
        sqlWhere.append(SQL_AND_RFC_ASOCIADO_CONTRIBUYENTE);
        sqlWhere.append(SQL_AND_RFC_CONTRIBUYENTE);
        sqlWhere.append(SQL_AND_FECHA_BAJA_ASOCIADO);
        sqlWhere.append(SQL_AND_ORDER_BY_METODO_ORDEN);

        return getJdbcTemplateBase().query(sqlWhere.toString(), new AgaceOrdenConMetodoMapper(), rfcAsociado,
                idTipoAsociado, rfcContribuyente);
    }

    @Override
    public RespuestaOrdenAcuerdoConclusivoDTO consultaOrdenAcuerdosConclusivos(String numeroOrden) {
        List<RespuestaOrdenAcuerdoConclusivoDTO> lista;

        lista = getJdbcTemplateBase().query(OrdenSql.SQL_CONSULTA_ORDEN_AC,
                new RespuestaOrdenAcuerdosConclusivosMapper(), numeroOrden);
        if (lista == null || lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }

    }

    @Override
    public List<AgaceOrden> consultaOrdenes(FiltroOrdenes filtroDao) {
        if (filtroDao != null) {
            int tipoEmpConsulta = 0;
            if (filtroDao.isConsultaEmpleado() && filtroDao.getTipoEmpleadoConsulta() != null) {
                tipoEmpConsulta = (int) filtroDao.getTipoEmpleadoConsulta().getId();
            }
            StringBuilder queryOrden = new StringBuilder(getSqlSelectOrdenesConsultaEjecutiva(filtroDao));
            if (filtroDao.getEstatusFiltro() != null && !filtroDao.getEstatusFiltro().isEmpty()) {
                queryOrden.append(AND);
                queryOrden.append(SQL_CONDICION_ESTATUS.replace(LST_ESTATUS,
                        OrdenDaoHelper.getSQLEstatusOrdenes(filtroDao.getEstatusFiltro())));
            } else {
                if (filtroDao.getGruposDeEstatusValidos() != null) {
                    List<TipoEstatusEnum> listaTotalEstatus = new ArrayList<TipoEstatusEnum>();
                    for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : filtroDao
                            .getGruposDeEstatusValidos().entrySet()) {
                        List<TipoEstatusEnum> listaEstatus = grupoEstatus.getValue();
                        for (TipoEstatusEnum estatus : listaEstatus) {
                            listaTotalEstatus.add(estatus);
                        }
                    }
                    if (listaTotalEstatus.size() > 0) {
                        queryOrden.append(AND);
                        queryOrden.append(SQL_CONDICION_ESTATUS.replace(LST_ESTATUS,
                                OrdenDaoHelper.getSQLEstatusOrdenes(listaTotalEstatus)));
                    }
                }
            }
            if (filtroDao.getMetodoFiltro() != null) {
                queryOrden.append(AND);
                queryOrden.append(SQL_CONDICION_METODO.replace(LST_METODO,
                        OrdenDaoHelper.getSQLMetodosOrdenes(filtroDao.getMetodoFiltro())));
            }
            if (filtroDao.getUnidadAdmtvaDesahogoFiltro() != null) {
                queryOrden.append(AND);
                queryOrden.append(SQL_CONDICION_ARACES.replace(ID_TEMPLATE,
                        OrdenDaoHelper.getSQLUnidadesAdministrativas(filtroDao.getUnidadAdmtvaDesahogoFiltro())));
            }
            if (filtroDao.isConsultaCifras()) {
                if (filtroDao.getCifraDe() != null) {
                    queryOrden
                            .append(SQL_CONDICION_CIFRA_DESDE.replace(CIFRA_DESDE, filtroDao.getCifraDe().toString()));
                }
                if (filtroDao.getCifraHasta() != null) {
                    queryOrden.append(
                            SQL_CONDICION_CIFRA_HASTA.replace(CIFRA_HASTA, filtroDao.getCifraHasta().toString()));
                }
            }
            if (filtroDao.isConsultaEmpleado()) {
                if (Constantes.USUARIO_FIRMANTE.equals(new BigDecimal(tipoEmpConsulta))) {
                    queryOrden.append(SQL_CONDICION_ID_FIRMANTE);
                    return getJdbcTemplateBase().query(queryOrden.toString(), new AgaceOrdenConMetodoMapper(),
                            filtroDao.getEmpleadoConsultaFiltro().getIdEmpleado() != null
                                    ? filtroDao.getEmpleadoConsultaFiltro().getIdEmpleado()
                                    : filtroDao.getEmpleadoConsultaFiltro().getDetalleEmpleado().get(0)
                                    .getIdEmpleado());
                } else if (Constantes.USUARIO_AUDITOR.equals(new BigDecimal(tipoEmpConsulta))) {
                    queryOrden.append(SQL_CONDICION_ID_AUDITOR);
                    return getJdbcTemplateBase().query(queryOrden.toString(), new AgaceOrdenConMetodoMapper(),
                            filtroDao.getEmpleadoConsultaFiltro().getIdEmpleado() != null
                                    ? filtroDao.getEmpleadoConsultaFiltro().getIdEmpleado()
                                    : filtroDao.getEmpleadoConsultaFiltro().getDetalleEmpleado().get(0)
                                    .getIdEmpleado());
                }
            } else {
                return getJdbcTemplateBase().query(queryOrden.toString(), new AgaceOrdenConMetodoMapper());
            }
        }
        return new ArrayList<AgaceOrden>();
    }

    @Override
    public Integer countOrdenesXMetodo(TipoMetodoEnum metodo, List<AraceDTO> lstUnidAdministrativas,
            BigDecimal idEmpleado, BigDecimal idTipoEmpleado,
            Map<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatusValidos) {
        List<TipoMetodoEnum> lstMetodo = new ArrayList<TipoMetodoEnum>();
        lstMetodo.add(metodo);
        Integer numeroDeClaves = null;
        SqlRowSet srs;

        StringBuilder sqlQuery = new StringBuilder(HEADER_COUNT_ORDENES);
        sqlQuery.append(SQL_INNER_JOIN_PROPUESTA);
        sqlQuery.append(SQL_CONDICION_WHERE);
        if (grupoEstatusValidos != null) {
            List<TipoEstatusEnum> listaTotalEstatus = new ArrayList<TipoEstatusEnum>();
            for (Map.Entry<AgrupadorEstatusOrdenesEnum, List<TipoEstatusEnum>> grupoEstatus : grupoEstatusValidos
                    .entrySet()) {
                List<TipoEstatusEnum> listaEstatus = grupoEstatus.getValue();
                for (TipoEstatusEnum estatus : listaEstatus) {
                    listaTotalEstatus.add(estatus);
                }
            }
            if (listaTotalEstatus.size() > 0) {
                sqlQuery.append(AND);
                sqlQuery.append(SQL_CONDICION_ESTATUS.replace(LST_ESTATUS,
                        OrdenDaoHelper.getSQLEstatusOrdenes(listaTotalEstatus)));
            }
        }
        sqlQuery.append(AND);
        sqlQuery.append(SQL_CONDICION_METODO.replace(LST_METODO, OrdenDaoHelper.getSQLMetodosOrdenes(lstMetodo)));
        sqlQuery.append(AND);
        sqlQuery.append(SQL_CONDICION_ARACES.replace(ID_TEMPLATE,
                OrdenDaoHelper.getSQLUnidadesAdministrativas(lstUnidAdministrativas)));
        if (idEmpleado != null) {
            if (Constantes.USUARIO_FIRMANTE.equals(idTipoEmpleado)) {
                sqlQuery.append(SQL_CONDICION_ID_FIRMANTE);

            } else if (Constantes.USUARIO_AUDITOR.equals(idTipoEmpleado)) {
                sqlQuery.append(SQL_CONDICION_ID_AUDITOR);
            }
            srs = getJdbcTemplateBase().queryForRowSet(sqlQuery.toString(), idEmpleado);
        } else {
            srs = getJdbcTemplateBase().queryForRowSet(sqlQuery.toString());
        }
        while (srs.next()) {
            numeroDeClaves = srs.getInt(COLUM_TOTAL);
        }
        return numeroDeClaves;
    }

    @Override
    public BigDecimal obtenerIdAraceFromPropuesta(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT P.ID_ARACE FROM FECET_PROPUESTA P \n");
        query.append(" INNER JOIN FECET_ORDEN O ON P.ID_PROPUESTA = O.ID_PROPUESTA\n");
        query.append(" WHERE O.ID_ORDEN = ? \n");
        return getJdbcTemplateBase().queryForObject(query.toString(), BigDecimal.class, idOrden);
    }

    @Override
    public void updateAuditorFrimante(BigDecimal idPropuesta, BigDecimal idAuditor, BigDecimal idFirmante) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(AgaceOrdenHelper.getTableName());
        query.append(" SET ID_AUDITOR = ? , ID_FIRMANTE = ? WHERE ID_PROPUESTA = ?");
        getJdbcTemplateBase().update(query.toString(), idAuditor, idFirmante, idPropuesta);

    }

    @Override
    public String obtenerNumeroReferenciaOrden(Long idOrden) {
        return getJdbcTemplateBase().queryForObject(SELECT_NO_REFERENCIA_ORDEN, String.class, idOrden);
    }

    @Override
    public void updateNumeroReferenciaOrden(String noReferencia, Long idOrden) {
        getJdbcTemplateBase().update(UPDATE_NO_REFERENCIA_ORDEN, noReferencia, idOrden);
    }
}
