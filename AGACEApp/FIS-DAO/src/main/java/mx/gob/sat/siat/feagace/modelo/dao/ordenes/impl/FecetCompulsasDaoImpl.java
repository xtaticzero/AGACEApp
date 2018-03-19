/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl.FecetContribuyenteDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.common.impl.FeceaMetodoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetCompulsasDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.CompulsasEncabezadoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCompulsasMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetCompulsasOficioOrdenMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsas;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetCompulsasPk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetCompulsasDao")
public class FecetCompulsasDaoImpl extends BaseJDBCDao<FecetCompulsas> implements FecetCompulsasDao {

    private static final long serialVersionUID = 4338745010381980863L;

    private static final StringBuilder SQL_DUAL = new StringBuilder("SELECT FECEQ_COMPULSAS.NEXTVAL FROM DUAL");
    private static final String SQL_INSERT = "INSERT INTO ";
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_COMPULSA = ? ");
    private static final StringBuilder SQL_WHERE_FECHA_BAJA_NULL = new StringBuilder(" WHERE FECHA_BAJA IS NULL ");
    private static final String SQL_INSERT_INTO = " ( ID_COMPULSA, ID_ORDEN_AUDITADA, ID_ORDEN_COMPULSA, ID_CONTRIBUYENTE_COMPULSADO, ID_ASOCIADO, ID_ESTATUS, ID_OFICIO, TIPO_COMPULSA, FECHA_CREACION ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";
    private static final StringBuilder SQL_SELECT = new StringBuilder(
            "SELECT ID_COMPULSA, ID_ORDEN_AUDITADA, ID_ORDEN_COMPULSA, ID_CONTRIBUYENTE_COMPULSADO, ID_ASOCIADO, ID_ESTATUS, ID_OFICIO, TIPO_COMPULSA, FECHA_CREACION FROM ")
            .append(getTableName());
    private static final String SQL_ENCABEZADO_COMPULSAS = "SELECT C.RFC, C.NOMBRE, B.RFC RFC_RL, B.NOMBRE NOMBRE_RL, B.CORREO CORREO_RL, A.ID_CONTRIBUYENTE_COMPULSADO \n"
            + "from FECET_COMPULSAS A, FECET_ASOCIADO B, FECET_CONTRIBUYENTE C\n"
            + "WHERE B.ID_ASOCIADO(+) = A.ID_ASOCIADO\n" + "AND C.ID_CONTRIBUYENTE = A.ID_CONTRIBUYENTE_COMPULSADO\n"
            + "AND A.ID_OFICIO = ?";
    private static final String ACTUALIZA_ESTATUS = "update FECET_COMPULSAS set ID_ESTATUS = ?, ID_ORDEN_COMPULSA = ? where ID_OFICIO = ?";
    private static final String SQL_INNER = " INNER JOIN ";

    private static final String EXISTE_CORREO_ASOCIADO = "select fa.correo from fecet_oficio fo "
            + "inner join fecet_compulsas fc on fc.id_oficio = fo.id_oficio "
            + "left join fecet_asociado fa on fa.id_asociado = fc.id_asociado " + "where fo.id_orden = ? "
            + "and fo.id_oficio = ? " + "and fo.id_tipo_oficio = 1 ";

    private String getSqlColumnsCompulsas() {
        StringBuilder sqlColumnsCompulsas = new StringBuilder();
        sqlColumnsCompulsas.append(" COMPULSA.ID_COMPULSA, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_ORDEN_AUDITADA, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_ORDEN_COMPULSA, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_CONTRIBUYENTE_COMPULSADO, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_ASOCIADO, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_ESTATUS, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_ESTATUS AS ID_ESTATUS_COMPULSA, ");
        sqlColumnsCompulsas.append(" COMPULSA.ID_OFICIO AS ID_OFICIO_COMPULSA, ");
        sqlColumnsCompulsas.append(" COMPULSA.TIPO_COMPULSA ");
        return sqlColumnsCompulsas.toString();
    }

    private String getSqlColumnsOrden() {
        StringBuilder sqlColumnsOrden = new StringBuilder();
        sqlColumnsOrden.append(" ORDEN.ID_ORDEN, ");
        sqlColumnsOrden.append(" ORDEN.ID_METODO, ");
        sqlColumnsOrden.append(" ORDEN.ID_REVISION, ");
        sqlColumnsOrden.append(" ORDEN.ID_NYV, ");
        sqlColumnsOrden.append(" ORDEN.NUMERO_ORDEN, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_CREACION, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_BAJA, ");
        sqlColumnsOrden.append(" ORDEN.PRIORIDAD, ");
        sqlColumnsOrden.append(" ORDEN.FOLIO_NYV, ");
        sqlColumnsOrden.append(" ORDEN.CADENA_ORIGINAL, ");
        sqlColumnsOrden.append(" ORDEN.FIRMA_ELECTRONICA, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_NOTIF_NYV, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_NOTIF_CONT, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_SURTE_EFECTOS, ");
        sqlColumnsOrden.append(" ORDEN.DIAS_RESTANTES_PLAZO, ");
        sqlColumnsOrden.append(" ORDEN.DIAS_HABILES, ");
        sqlColumnsOrden.append(" ORDEN.SUSPENCION_PLAZO, ");
        sqlColumnsOrden.append(" ORDEN.DIAS_RESTANTES_DOCUMENTOS, ");
        sqlColumnsOrden.append(" ORDEN.SEMAFORO, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_INTEGRA_EXP, ");
        sqlColumnsOrden.append(" ORDEN.ID_CONTRIBUYENTE, ");
        sqlColumnsOrden.append(" ORDEN.ID_ESTATUS, ");
        sqlColumnsOrden.append(" ORDEN.ID_ESTATUS AS ID_ESTATUS_ORDEN, ");
        sqlColumnsOrden.append(" ORDEN.ID_AUDITOR, ");
        sqlColumnsOrden.append(" ORDEN.ID_FIRMANTE, ");
        sqlColumnsOrden.append(" ORDEN.ID_PROPUESTA, ");
        sqlColumnsOrden.append(" ORDEN.ID_REGISTRO_PROPUESTA, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_REACTIVAR_PLAZO, ");
        sqlColumnsOrden.append(" ORDEN.FECHA_SUSPENCION_PLAZO, ");
        sqlColumnsOrden.append(" ORDEN.DIAS_RESOLUCION_DEFINITIVA, ");
        sqlColumnsOrden.append(" ORDEN.FOLIO_OFICIO, ");
        sqlColumnsOrden.append(" ORDEN.BLN_COMPULSA ");
        return sqlColumnsOrden.toString();
    }

    private String getSqlColumnsContribuyente() {
        StringBuilder sqlColumnsContribuyente = new StringBuilder();
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.RFC, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.NOMBRE NOMBRE_CONTRIBUYENTE, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.ENTIDAD, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.DOMICILIO_FISCAL, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.REGIMEN, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.SITUACION, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.SITUACION_DOMICILIO, ");
        sqlColumnsContribuyente.append(" FECET_CONTRIBUYENTE.TIPO ");
        return sqlColumnsContribuyente.toString();
    }

    private String getSqlColumnsMetodo() {
        StringBuilder sqlColumnsMetodo = new StringBuilder();
        sqlColumnsMetodo.append(" METODO.ABREVIATURA, ");
        sqlColumnsMetodo.append(" METODO.NOMBRE ");
        return sqlColumnsMetodo.toString();
    }

    private String getSqlColumnsOficio() {
        StringBuilder sqlColumnsOficio = new StringBuilder();
        sqlColumnsOficio.append(" OFICIO.ID_OFICIO, ");
        sqlColumnsOficio.append(" OFICIO.ID_OFICIO_PRINCIPAL, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_CREACION, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_FIRMA, ");
        sqlColumnsOficio.append(" OFICIO.RUTA_ARCHIVO, ");
        sqlColumnsOficio.append(" OFICIO.DOCUMENTO_PDF, ");
        sqlColumnsOficio.append(" OFICIO.FOLIO_NYV, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_NOTIF_NYV, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_NOTIF_CONT, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_SURTE_EFECTOS, ");
        sqlColumnsOficio.append(" OFICIO.DIAS_RESTANTES_PLAZO, ");
        sqlColumnsOficio.append(" OFICIO.DIAS_HABILES, ");
        sqlColumnsOficio.append(" OFICIO.SUSPENCION_PLAZO, ");
        sqlColumnsOficio.append(" OFICIO.DIAS_RESTANTES_DOCUMENTOS, ");
        sqlColumnsOficio.append(" OFICIO.FECHA_INTEGRA_EXP, ");
        sqlColumnsOficio.append(" OFICIO.ID_ESTATUS, ");
        sqlColumnsOficio.append(" OFICIO.ID_ESTATUS AS ID_ESTATUS_OFICIO, ");
        sqlColumnsOficio.append(" OFICIO.ID_ORDEN AS ID_OFICIO_ORDEN, ");
        sqlColumnsOficio.append(" OFICIO.CADENA_ORIGINAL, ");
        sqlColumnsOficio.append(" OFICIO.FIRMA_ELECTRONICA, ");
        sqlColumnsOficio.append(" OFICIO.ID_NYV, ");
        sqlColumnsOficio.append(" OFICIO.NUMERO_OFICIO, ");
        sqlColumnsOficio.append(" OFICIO.ID_TIPO_OFICIO ");
        return sqlColumnsOficio.toString();
    }

    private String getSqlColumnsTipoOficio() {
        StringBuilder sqlColumnsTipoOficio = new StringBuilder();
        sqlColumnsTipoOficio.append(" TIPO.ID_TIPO_OFICIO, ");
        sqlColumnsTipoOficio.append(" TIPO.NOMBRE NOMBRE_TIPO, ");
        sqlColumnsTipoOficio.append(" TIPO.DESCRIPCION, ");
        sqlColumnsTipoOficio.append(" TIPO.FECHA_CREACION, ");
        sqlColumnsTipoOficio.append(" TIPO.ID_AGRUPADOR_TIPO_OFICIO ");
        return sqlColumnsTipoOficio.toString();
    }

    private String getSqlColumnsEstatus() {
        StringBuilder sqlColumnsEstatus = new StringBuilder();
        sqlColumnsEstatus.append(" ESTATUS.MODULO, ");
        sqlColumnsEstatus.append(" ESTATUS.DESCRIPCION ");
        return sqlColumnsEstatus.toString();
    }

    @Override
    public FecetCompulsasPk insert(FecetCompulsas dto) {
        StringBuilder sb = new StringBuilder();
        if (dto.getIdCompulsa() == null) {
            dto.setIdCompulsa(getIdComulsa());
        }
        sb.append(SQL_INSERT);
        sb.append(getTableName());
        sb.append(SQL_INSERT_INTO);
        getJdbcTemplateBase().update(sb.toString(), dto.getIdCompulsa(), dto.getIdOrdenAuditada(),
                dto.getIdOrdenCompulsa(), dto.getIdContribuyenteCompulsado(), dto.getIdAsociado(), dto.getIdEstatus(),
                dto.getIdOficio(), dto.getTipoCompulsa(), dto.getFechaCreacion());

        return dto.createPk();

    }

    @Override
    public void update(FecetCompulsasPk pk, FecetCompulsas dto) {
        getJdbcTemplateBase().update(
                "UPDATE " + getTableName()
                + " SET ID_COMPULSA = ?, ID_ORDEN_AUDITADA = ?, ID_ORDEN_COMPULSA = ?, ID_CONTRIBUYENTE_COMPULSADO = ?, ID_ASOCIADO = ?, ID_ESTATUS = ?, ID_OFICIO = ?, TIPO_COMPULSA = ?, FECHA_CREACION = ? WHERE ID_COMPULSA = ?",
                dto.getIdCompulsa(), dto.getIdOrdenAuditada(), dto.getIdOrdenCompulsa(),
                dto.getIdContribuyenteCompulsado(), dto.getIdAsociado(), dto.getIdEstatus(), dto.getIdOficio(),
                dto.getTipoCompulsa(), dto.getFechaCreacion(), pk.getIdCompulsa());
    }

    public static String getTableName() {
        return "FECET_COMPULSAS";
    }

    @Override
    public FecetCompulsas findByPrimaryKey(BigDecimal idCompulsa) {

        List<FecetCompulsas> list = getJdbcTemplateBase().query(SQL_SELECT.append(SQL_WHERE).toString(),
                new FecetCompulsasMapper(), idCompulsa);
        return list.isEmpty() ? null : list.get(0);

    }

    @Override
    public FecetCompulsas findByIdCompulsaOrden(BigDecimal idOrdenCompulsa) {

        List<FecetCompulsas> list = getJdbcTemplateBase().query(SQL_SELECT.append(" WHERE ID_ORDEN_COMPULSA = ?").toString(),
                new FecetCompulsasMapper(), idOrdenCompulsa);
        return list.isEmpty() ? null : list.get(0);

    }

    public FecetCompulsas findByPrimaryKey(FecetCompulsasPk pk) {
        return findByPrimaryKey(pk.getIdCompulsa());
    }

    public BigDecimal getIdComulsa() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    @Override
    public List<FecetCompulsas> getCompulsasOrden(String rfc) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(" SELECT \n");
        sqlWhere.append(getSqlColumnsCompulsas()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsOrden()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsMetodo()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsContribuyente()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsEstatus()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsOficio()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsTipoOficio()).append("\n");
        sqlWhere.append(" FROM ");
        sqlWhere.append(getTableName());
        sqlWhere.append(" COMPULSA ");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(AgaceOrdenHelper.getTableName());
        sqlWhere.append(" ORDEN ON");
        sqlWhere.append(" COMPULSA.ID_ORDEN_COMPULSA = ORDEN.ID_ORDEN ");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FeceaMetodoDaoImpl.getTableName());
        sqlWhere.append(" METODO \n");
        sqlWhere.append(" ON ORDEN.ID_METODO = METODO.ID_METODO \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FececEstatusDaoImpl.getTableName());
        sqlWhere.append(" ESTATUS \n");
        sqlWhere.append(" ON ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FecetOficioDaoImpl.getTableName());
        sqlWhere.append(" OFICIO ");
        sqlWhere.append(" ON COMPULSA.ID_OFICIO = OFICIO.ID_OFICIO \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FecetTipoOficioDaoImpl.getTableName());
        sqlWhere.append(" TIPO ");
        sqlWhere.append(" ON TIPO.ID_TIPO_OFICIO = OFICIO.ID_TIPO_OFICIO \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FecetContribuyenteDaoImpl.getTableName());
        sqlWhere.append(" FECET_CONTRIBUYENTE ON ");
        sqlWhere.append(" ORDEN.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(" FECET_DETALLE_NYV NYV ON");
        sqlWhere.append(" ORDEN.ID_NYV = NYV.ID_NYV \n");
        sqlWhere.append(SQL_WHERE_FECHA_BAJA_NULL).append("\n");
        sqlWhere.append(" AND ORDEN.ID_ESTATUS IN (?,?) \n");
        sqlWhere.append(" AND FECET_CONTRIBUYENTE.RFC = ? \n");
        sqlWhere.append(" AND COMPULSA.ID_ESTATUS = ? \n");
        sqlWhere.append(
                " AND TO_DATE(TO_CHAR(NYV.FECHA_SURTE_EFECTOS,'DD/MM/YYYY'),'DD/MM/YYYY')  <= TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') \n");
        sqlWhere.append(" AND ORDEN.BLN_COMPULSA = '1' \n");
        sqlWhere.append(" ORDER BY ORDEN.FECHA_CREACION DESC");

        logger.error(sqlWhere.toString());
        return getJdbcTemplateBase().query(sqlWhere.toString(), new FecetCompulsasOficioOrdenMetodoMapper(),
                Constantes.ESTATUS_FIRMADO_ENVIADO_NOTIFICACION, Constantes.ESTATUS_ORDEN_NOTIFICADA_CONTRIBUYENTE, rfc,
                Constantes.ESTATUS_COMPULSA_APROBADA_FIRMANTE);
    }

    @Override
    public FecetCompulsas getDatosGenerales(BigDecimal idOficio) {
        FecetCompulsas compulsa = null;
        try {
            compulsa = getJdbcTemplateBase().queryForObject(SQL_ENCABEZADO_COMPULSAS, new CompulsasEncabezadoMapper(),
                    idOficio);
        } catch (Exception e) {
            logger.error("Erros getDatosGenerales " + e.getMessage(), e);
        }
        return compulsa;
    }

    public FecetCompulsas getCompulsaPorOficio(BigDecimal idOficio) {
        StringBuilder sqlWhere = new StringBuilder();
        sqlWhere.append(" SELECT \n");
        sqlWhere.append(getSqlColumnsCompulsas()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsOrden()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsMetodo()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsContribuyente()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsEstatus()).append("\n");
        sqlWhere.append(",");
        sqlWhere.append(getSqlColumnsOficio()).append("\n");
        sqlWhere.append(" FROM ");
        sqlWhere.append(getTableName());
        sqlWhere.append(" COMPULSA ");
        sqlWhere.append(" INNER JOIN  ");
        sqlWhere.append(AgaceOrdenHelper.getTableName());
        sqlWhere.append(" ORDEN ON");
        sqlWhere.append(" COMPULSA.ID_ORDEN_COMPULSA = ORDEN.ID_ORDEN ");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FeceaMetodoDaoImpl.getTableName());
        sqlWhere.append(" METODO \n");
        sqlWhere.append(" ON ORDEN.ID_METODO = METODO.ID_METODO \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FececEstatusDaoImpl.getTableName());
        sqlWhere.append(" ESTATUS \n");
        sqlWhere.append(" ON ORDEN.ID_ESTATUS = ESTATUS.ID_ESTATUS \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FecetOficioDaoImpl.getTableName());
        sqlWhere.append(" OFICIO ");
        sqlWhere.append(" ON COMPULSA.ID_OFICIO = OFICIO.ID_OFICIO \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(FecetContribuyenteDaoImpl.getTableName());
        sqlWhere.append(" FECET_CONTRIBUYENTE ON ");
        sqlWhere.append(" ORDEN.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE \n");
        sqlWhere.append(SQL_INNER);
        sqlWhere.append(" FECET_DETALLE_NYV NYV ON");
        sqlWhere.append(" ORDEN.ID_NYV = NYV.ID_NYV \n");
        sqlWhere.append(" WHERE COMPULSA.ID_OFICIO = ? \n");
        List<FecetCompulsas> compulsas = getJdbcTemplateBase().query(sqlWhere.toString(),
                new FecetCompulsasOficioOrdenMetodoMapper(), idOficio);
        return compulsas.isEmpty() ? null : compulsas.get(0);
    }

    @Override
    public boolean actualizaEstatusCompulsa(BigDecimal idOficio, BigDecimal estatus, BigDecimal idOrden) {
        boolean actualizado = false;
        try {
            if (getJdbcTemplateBase().update(ACTUALIZA_ESTATUS, estatus, idOrden, idOficio) > 0) {
                actualizado = true;
            }
        } catch (Exception e) {
            logger.error("Erros getDatosGenerales " + e.getMessage(), e);
        }
        return actualizado;
    }

    public boolean existeCorreoAsociadoCompulsaTerceros(BigDecimal idOficio, BigDecimal idOrden) {
        boolean actualizado = true;
        Object[] parameters = new Object[]{idOrden, idOficio};
        try {
            String correo = getJdbcTemplateBase().queryForObject(EXISTE_CORREO_ASOCIADO, parameters, String.class);
            if (correo != null && !correo.equals("")) {
                actualizado = false;
            }
        } catch (Exception e) {
            logger.error("Erros getDatosGenerales " + e.getMessage(), e);
        }
        return actualizado;
    }

}
