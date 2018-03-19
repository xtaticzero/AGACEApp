package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.sql.EmpleadoSQL;
import mx.gob.sat.siat.feagace.modelo.dao.common.OrdenNotificacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.NotificacionContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.NotificacionDatosContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.OrdenNotificacion;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("ordenNotificacionDao")
public class OrdenNotificacionDaoImpl extends BaseJDBCDao<OrdenNotificacion> implements OrdenNotificacionDao {

    private static final long serialVersionUID = -1358018914178247674L;
    private static final String SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE = "FECEC_METODO.NOMBRE AS NOMBRE_METODO, FECET_ORDEN.NUMERO_ORDEN, FECET_CONTRIBUYENTE.NOMBRE, FECET_CONTRIBUYENTE.RFC, ";
    private static final String JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN = "INNER JOIN FECET_CONTRIBUYENTE ON FECET_ORDEN.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE ";
    private static final String JOIN_FECEC_METODO_ORDEN = "INNER JOIN FECEC_METODO ON FECET_ORDEN.ID_METODO = FECEC_METODO.ID_METODO WHERE FECET_ORDEN.ID_ORDEN = ? ";

    public OrdenNotificacionDaoImpl() {
        super();
    }

    /**
     *
     * @param idOrden
     * @return
     */
    @Override
    public List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden, BigDecimal idPromocion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PROMOCION.ID_PROMOCION AS FOLIO_CARGA, ")
                .append(SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE)
                .append("FECET_ORDEN.FOLIO_OFICIO FROM FECET_PROMOCION INNER JOIN FECET_ORDEN ON FECET_PROMOCION.ID_ORDEN = FECET_ORDEN.ID_ORDEN ")
                .append(JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN)
                .append(JOIN_FECEC_METODO_ORDEN)
                .append("AND FECET_PROMOCION.ID_PROMOCION = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden, idPromocion);
    }

    public List<OrdenNotificacion> findDetalleContribuyenteProrroga(BigDecimal idOrden, BigDecimal idProrroga) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PRORROGA_ORDEN.ID_PRORROGA_ORDEN AS FOLIO_CARGA, ")
                .append(SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE)
                .append("FECET_ORDEN.FOLIO_OFICIO FROM FECET_PRORROGA_ORDEN INNER JOIN FECET_ORDEN ON FECET_PRORROGA_ORDEN.ID_ORDEN = FECET_ORDEN.ID_ORDEN ")
                .append(JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN)
                .append(JOIN_FECEC_METODO_ORDEN)
                .append("AND FECET_PRORROGA_ORDEN.ID_PRORROGA_ORDEN = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden, idProrroga);
    }

    public List<OrdenNotificacion> findDetalleContribuyentePruebaPericial(BigDecimal idOrden, BigDecimal idPruebaPericial) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PRUEBAS_PERICIALES.ID_PRUEBAS_PERICIALES AS FOLIO_CARGA, ")
                .append(SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE)
                .append("FECET_ORDEN.FOLIO_OFICIO FROM FECET_PRUEBAS_PERICIALES INNER JOIN FECET_ORDEN ON FECET_PRUEBAS_PERICIALES.ID_ORDEN = FECET_ORDEN.ID_ORDEN ")
                .append(JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN)
                .append(JOIN_FECEC_METODO_ORDEN)
                .append("AND FECET_PRUEBAS_PERICIALES.ID_PRUEBAS_PERICIALES = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden, idPruebaPericial);
    }

    public List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficio(BigDecimal idOrden, BigDecimal idProrrogaOficio) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PRORROGA_OFICIO.ID_PRORROGA_OFICIO AS FOLIO_CARGA, ")
                .append(SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE)
                .append("FECET_ORDEN.FOLIO_OFICIO FROM FECET_PRORROGA_OFICIO ")
                .append("INNER JOIN FECET_OFICIO ON FECET_PRORROGA_OFICIO.ID_OFICIO = FECET_OFICIO.ID_OFICIO ")
                .append("INNER JOIN FECET_ORDEN ON FECET_OFICIO.ID_ORDEN = FECET_ORDEN.ID_ORDEN ")
                .append(JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN)
                .append(JOIN_FECEC_METODO_ORDEN)
                .append("AND FECET_PRORROGA_OFICIO.ID_PRORROGA_OFICIO = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden, idProrrogaOficio);
    }

    public List<OrdenNotificacion> findDetalleContribuyenteProrrogaOficioCompulsa(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT CONTRIBUYENTE.NOMBRE, CONTRIBUYENTE.RFC ")
                .append("FROM FECET_CONTRIBUYENTE CONTRIBUYENTE ")
                .append("INNER JOIN FECET_ORDEN ORDEN ON CONTRIBUYENTE.ID_CONTRIBUYENTE = ORDEN.ID_CONTRIBUYENTE ")
                .append("AND ORDEN.ID_ORDEN = ?");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionDatosContribuyenteMapper(), idOrden);
    }

    @Override
    public List<OrdenNotificacion> findDetalleContribuyente(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PROMOCION.ID_PROMOCION AS FOLIO_CARGA,\n"
                + " FECEC_METODO.NOMBRE AS NOMBRE_METODO, FECET_ORDEN.NUMERO_ORDEN, FECET_CONTRIBUYENTE.NOMBRE, FECET_CONTRIBUYENTE.RFC,\n"
                + " FECET_ORDEN.FOLIO_OFICIO FROM FECET_PROMOCION INNER JOIN FECET_ORDEN ON FECET_PROMOCION.ID_ORDEN = FECET_ORDEN.ID_ORDEN\n"
                + " INNER JOIN FECET_CONTRIBUYENTE ON FECET_ORDEN.ID_CONTRIBUYENTE = FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE\n"
                + " INNER JOIN FECEC_METODO ON FECET_ORDEN.ID_METODO = FECEC_METODO.ID_METODO WHERE FECET_ORDEN.ID_ORDEN = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden);
    }

    public List<OrdenNotificacion> findDetalleContribuyentePromocionOficio(BigDecimal idOrden, BigDecimal idPromocionOficio) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_ORDEN.ID_ORDEN, FECET_PROMOCION_OFICIO.ID_PROMOCION_OFICIO AS FOLIO_CARGA, ")
                .append(SELECT_METODO_CAMPOS_Y_CONTRIBUYENTE)
                .append("FECET_ORDEN.FOLIO_OFICIO FROM FECET_PROMOCION_OFICIO INNER JOIN FECET_OFICIO ON FECET_PROMOCION_OFICIO.ID_OFICIO = FECET_OFICIO.ID_OFICIO ")
                .append("INNER JOIN FECET_ORDEN ON FECET_OFICIO.ID_ORDEN = FECET_ORDEN.ID_ORDEN ")
                .append(JOIN_FECET_CONTRIBUYENTE_FECET_ORDEN)
                .append(JOIN_FECEC_METODO_ORDEN)
                .append("AND FECET_PROMOCION_OFICIO.ID_PROMOCION_OFICIO = ? AND ROWNUM = 1");
        return getJdbcTemplateBase().query(query.toString(), new NotificacionContribuyenteMapper(), idOrden, idPromocionOficio);
    }

    /**
     *
     * @param idOrden
     * @param idAuditor
     * @return
     */
    @Override
    public List<String> getLstNombreAuditor(BigDecimal idAuditor, BigDecimal idOrden) {
        Object[] sqlParams = new Object[]{idAuditor, idOrden};
        return getJdbcTemplateBase().query(EmpleadoSQL.GET_AUDITOR, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(EmpleadoSQL.COLUM_NOMBRE);
            }
        }, sqlParams);
    }

    /**
     *
     * @param idOrden
     * @param idFirmante
     * @return
     */
    @Override
    public List<String> getLstCorreoFirmante(BigDecimal idFirmante, BigDecimal idOrden) {
        Object[] sqlParams = new Object[]{idFirmante, idOrden};
        return getJdbcTemplateBase().query(EmpleadoSQL.GET_CORREO_FIRMANTE, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(EmpleadoSQL.COLUM_CORREO);
            }
        }, sqlParams);
    }

    /**
     *
     * @param idAuditor
     * @param idOrden
     * @return
     */
    @Override
    public List<String> getLstCorreoAuditor(BigDecimal idAuditor, BigDecimal idOrden) {
        Object[] sqlParams = new Object[]{idAuditor, idOrden};
        return getJdbcTemplateBase().query(EmpleadoSQL.GET_CORREO_FIRMANTE, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(EmpleadoSQL.COLUM_CORREO);
            }
        }, sqlParams);
    }

    /**
     *
     * @param idOficio
     * @return
     */
    @Override
    public List<String> getLstIdOrdenxOficio(BigDecimal idOficio) {
        Object[] sqlParams = new Object[]{idOficio};
        return getJdbcTemplateBase().query(EmpleadoSQL.GET_IDOFICIO_XIDOFICIO, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString(EmpleadoSQL.COLUM_ORDEN);
            }
        }, sqlParams);
    }

}
