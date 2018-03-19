package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.sql.OrdenSql;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociadoPk;

@Repository("fecetAsociadoDao")
public class FecetAsociadoDaoImpl extends BaseJDBCDao<FecetAsociado> implements FecetAsociadoDao {

    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = "SELECT ID_ASOCIADO, RFC_CONTRIBUYENTE, ID_ORDEN, ID_TIPO_ASOCIADO, NOMBRE, RFC, CORREO, TIPO_ASOCIADO, MEDIO_CONTACTO, FECHA_BAJA, FECHA_ULTIMA_MOD, FECHA_ULTIMA_MOD_IDC, ESTATUS FROM ";

    private static final String SQL_SELECT2 = "SELECT fas.ID_ASOCIADO, fas.RFC_CONTRIBUYENTE, fas.ID_ORDEN, fas.ID_TIPO_ASOCIADO, fas.NOMBRE, fas.RFC, fas.CORREO, fas.TIPO_ASOCIADO, fas.MEDIO_CONTACTO, fas.FECHA_BAJA, fas.FECHA_ULTIMA_MOD, fas.FECHA_ULTIMA_MOD_IDC, fas.ESTATUS FROM ";

    private static final String UPDATE = "UPDATE ";

    private static final String AND_ID_TIPO_ASOCIADO = " AND ID_TIPO_ASOCIADO = ? ";

    private StringBuilder getSqlActualizaApoderadoLegal() {
        StringBuilder sqlActualizaApoderadoLegal = new StringBuilder();
        sqlActualizaApoderadoLegal.append(UPDATE);
        sqlActualizaApoderadoLegal.append(getTableName());
        sqlActualizaApoderadoLegal
                .append(" SET NOMBRE = ?, RFC = ?, CORREO = ?, MEDIO_CONTACTO = ?, FECHA_ULTIMA_MOD = SYSDATE ");
        sqlActualizaApoderadoLegal.append("  WHERE RFC_CONTRIBUYENTE  = ?  ");
        sqlActualizaApoderadoLegal.append(AND_ID_TIPO_ASOCIADO);
        return sqlActualizaApoderadoLegal;
    }

    private StringBuilder getSqlActualizaColaborador() {
        StringBuilder sqlActualizaColaborador = new StringBuilder();
        sqlActualizaColaborador.append(UPDATE);
        sqlActualizaColaborador.append(getTableName());
        sqlActualizaColaborador
                .append(" SET NOMBRE = ?, RFC = ?, CORREO = ?, MEDIO_CONTACTO = ?, FECHA_ULTIMA_MOD = SYSDATE ");
        sqlActualizaColaborador.append("  WHERE ID_ORDEN  = ?  ");
        sqlActualizaColaborador.append(AND_ID_TIPO_ASOCIADO);
        sqlActualizaColaborador.append(" AND FECHA_BAJA IS NOT NULL ");
        return sqlActualizaColaborador;
    }

    private StringBuilder getSqlEliminaApoderadoLegal() {
        StringBuilder sqlEliminaApoderadoLegal = new StringBuilder();
        sqlEliminaApoderadoLegal.append(UPDATE);
        sqlEliminaApoderadoLegal.append(getTableName());
        sqlEliminaApoderadoLegal.append(" SET FECHA_BAJA = SYSDATE ");
        sqlEliminaApoderadoLegal.append(" WHERE RFC_CONTRIBUYENTE  = ? ");
        sqlEliminaApoderadoLegal.append(AND_ID_TIPO_ASOCIADO);
        return sqlEliminaApoderadoLegal;
    }

    private StringBuilder getSqlEliminaColaborador() {
        StringBuilder sqlEliminaColaborador = new StringBuilder();
        sqlEliminaColaborador.append(UPDATE);
        sqlEliminaColaborador.append(getTableName());
        sqlEliminaColaborador.append(" SET FECHA_BAJA = SYSDATE ");
        sqlEliminaColaborador.append(" WHERE ID_ORDEN  = ? ");
        sqlEliminaColaborador.append(AND_ID_TIPO_ASOCIADO);
        return sqlEliminaColaborador;
    }

    private StringBuilder getSqlApoderadoLegal() {
        StringBuilder sqlApoderadoLegal = new StringBuilder();
        sqlApoderadoLegal.append(SQL_SELECT);
        sqlApoderadoLegal.append(getTableName());
        sqlApoderadoLegal.append(" WHERE RFC_CONTRIBUYENTE = ? ");
        sqlApoderadoLegal.append(AND_ID_TIPO_ASOCIADO);
        sqlApoderadoLegal.append(" AND FECHA_BAJA IS NULL ");
        return sqlApoderadoLegal;
    }

    private StringBuilder getSqlColaborador() {
        StringBuilder sqlColaborador = new StringBuilder();
        sqlColaborador.append(SQL_SELECT);
        sqlColaborador.append(getTableName());
        sqlColaborador.append(" WHERE ID_TIPO_ASOCIADO = ? ");
        sqlColaborador.append(" AND ID_ORDEN = ? ");
        sqlColaborador.append(" AND FECHA_BAJA IS NULL ");
        return sqlColaborador;
    }

    private StringBuilder getSqlRepresentanteAgente() {
        String sqlVerificaDocto = "SELECT ID_ASOCIADO, RFC_CONTRIBUYENTE, FECET_ASOCIADO.ID_ORDEN, ID_TIPO_ASOCIADO, NOMBRE, RFC, CORREO, TIPO_ASOCIADO, MEDIO_CONTACTO, FECET_ASOCIADO.FECHA_BAJA, FECHA_ULTIMA_MOD, FECHA_ULTIMA_MOD_IDC, ESTATUS FROM ";
        String joinOrden = " FECET_ASOCIADO INNER JOIN FECET_ORDEN ON (FECET_ASOCIADO.ID_ORDEN = FECET_ORDEN.ID_ORDEN) ";
        StringBuilder sqlRepresentante = new StringBuilder();
        sqlRepresentante.append(sqlVerificaDocto);
        sqlRepresentante.append(joinOrden);
        sqlRepresentante.append(" WHERE ID_TIPO_ASOCIADO = ? ");
        sqlRepresentante.append(" AND ID_PROPUESTA = ? ");
        sqlRepresentante.append(" AND FECET_ASOCIADO.FECHA_BAJA IS NULL ");
        return sqlRepresentante;
    }

    private StringBuilder getSqlIdAsociado() {
        StringBuilder sqlIdAsociado = new StringBuilder();
        sqlIdAsociado.append("SELECT ID_ASOCIADO FROM ");
        sqlIdAsociado.append(getTableName());
        sqlIdAsociado.append(" WHERE RFC = ? ");
        sqlIdAsociado.append(AND_ID_TIPO_ASOCIADO);
        sqlIdAsociado.append(" AND RFC_CONTRIBUYENTE = ? ");
        sqlIdAsociado.append(" AND FECHA_BAJA IS NULL ");
        return sqlIdAsociado;
    }

    private StringBuilder getSqlConfirmaColaborador() {
        StringBuilder sqlConfirmaColaborador = new StringBuilder();
        sqlConfirmaColaborador.append(UPDATE);
        sqlConfirmaColaborador.append(getTableName());
        sqlConfirmaColaborador.append(" SET ESTATUS = 2 ");
        sqlConfirmaColaborador.append(" WHERE RFC_CONTRIBUYENTE  = ? ");
        sqlConfirmaColaborador.append(" AND RFC = ? ");
        sqlConfirmaColaborador.append(" AND ID_ORDEN = ? ");
        sqlConfirmaColaborador.append(AND_ID_TIPO_ASOCIADO);
        return sqlConfirmaColaborador;
    }

    @Override
    public FecetAsociadoPk insert(FecetAsociado dto) {
        if (dto.getIdAsociado() == null) {
            dto.setIdAsociado(
                    getJdbcTemplateBase().queryForObject("SELECT FECEQ_ASOCIADO.NEXTVAL FROM DUAL", BigDecimal.class));
        }

        getJdbcTemplateBase().update(
                "INSERT INTO " + getTableName()
                + " ( ID_ASOCIADO, RFC_CONTRIBUYENTE, ID_ORDEN, ID_TIPO_ASOCIADO, NOMBRE, RFC, CORREO, TIPO_ASOCIADO, MEDIO_CONTACTO, FECHA_BAJA, FECHA_ULTIMA_MOD, FECHA_ULTIMA_MOD_IDC, ESTATUS ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ? )",
                dto.getIdAsociado(), dto.getRfcContribuyente(), dto.getIdOrden(), dto.getIdTipoAsociado(),
                dto.getNombre(), dto.getRfc(), dto.getCorreo(), dto.getTipoAsociado(), dto.getMedioContacto(),
                dto.getFechaBaja(), dto.getFechaUltimaModIdc(), dto.getEstatus());
        return dto.createPk();
    }

    @PistaAuditoria
    @Override
    public String insertaAgenteAduanal(FecetAsociado dto) {
        insert(dto);
        return dto.getRfc();
    }

    @Override
    public void update(FecetAsociadoPk pk, FecetAsociado dto) {
        StringBuilder update = new StringBuilder();
        update.append(UPDATE);
        update.append(getTableName());
        update.append(
                " SET ID_ASOCIADO = ?, RFC_CONTRIBUYENTE = ?, ID_ORDEN = ?, ID_TIPO_ASOCIADO = ?, NOMBRE = ?, RFC = ?, CORREO = ?, TIPO_ASOCIADO = ?, FECHA_BAJA = ?, FECHA_ULTIMA_MOD = ?, FECHA_ULTIMA_MOD_IDC = ? WHERE ID_ASOCIADO = ?");

        getJdbcTemplateBase().update(update.toString(), dto.getIdAsociado(), dto.getRfcContribuyente(),
                dto.getIdOrden(), dto.getIdTipoAsociado(), dto.getNombre(), dto.getRfc(), dto.getCorreo(),
                dto.getTipoAsociado(), dto.getMedioContacto(), dto.getFechaBaja(), dto.getFechaUltimaMod(),
                dto.getFechaUltimaModIdc(), pk.getIdAsociado());
    }

    @Override
    public void delete(FecetAsociadoPk pk) {
        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_ASOCIADO = ?", pk.getIdAsociado());
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ASOCIADO";
    }

    @Override
    public FecetAsociado findByPrimaryKey(BigDecimal idAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ASOCIADO = ?");
        List<FecetAsociado> list = getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), idAsociado);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<FecetAsociado> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_ASOCIADO");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper());
    }

    @Override
    public List<FecetAsociado> findByFececTipoAsociado(BigDecimal idTipoAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_ASOCIADO = ?");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), idTipoAsociado);
    }

    @Override
    public List<FecetAsociado> findWhereIdAsociadoEquals(BigDecimal idAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ASOCIADO = ? ORDER BY ID_ASOCIADO");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), idAsociado);
    }

    @Override
    public List<FecetAsociado> findWhereNombreEquals(String nombre) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), nombre);
    }

    @Override
    public List<FecetAsociado> findWhereRfcEquals(String rfc) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), rfc);
    }

    @Override
    public List<FecetAsociado> findWhereCorreoEquals(String correo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE CORREO = ? ORDER BY CORREO");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), correo);
    }

    @Override
    public List<FecetAsociado> findWhereIdTipoAsociadoEquals(BigDecimal idTipoAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_TIPO_ASOCIADO = ? ORDER BY ID_TIPO_ASOCIADO");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), idTipoAsociado);
    }

    @Override
    public List<FecetAsociado> findWhereFechaCreacionEquals(Date fechaCreacion) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), fechaCreacion);
    }

    @Override
    public List<FecetAsociado> findWhereFechaBajaEquals(Date fechaBaja) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_BAJA = ? ORDER BY FECHA_BAJA");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), fechaBaja);
    }

    @Override
    public List<FecetAsociado> getApoderadoLegalContribuyente(String rfc, BigDecimal idTipoAsociado) {
        return getJdbcTemplateBase().query(getSqlApoderadoLegal().toString(), new FecetAsociadoMapper(), rfc,
                idTipoAsociado);
    }

    @Override
    public List<FecetAsociado> getColaboradorContribuyente(BigDecimal idTipoAsociado, BigDecimal idOrden) {
        return getJdbcTemplateBase().query(getSqlColaborador().toString(), new FecetAsociadoMapper(), idTipoAsociado,
                idOrden);
    }

    @Override
    public void actualizaApoderadoLegal(FecetAsociado dto, String rfcContribuyente) {

        getJdbcTemplateBase().update(getSqlActualizaApoderadoLegal().toString(), dto.getNombre(), dto.getRfc(),
                dto.getCorreo(), dto.getMedioContacto(), rfcContribuyente, dto.getIdTipoAsociado());
    }

    @Override
    public void eliminaApoderadoLegal(String rfcContribuyente, BigDecimal idTipoAsociado) {
        getJdbcTemplateBase().update(getSqlEliminaApoderadoLegal().toString(), rfcContribuyente, idTipoAsociado);
    }

    @Override
    public List<String> getContribuyenteTipoAsociado(String rfc, BigDecimal idTipoAsociado) {
        return getJdbcTemplateBase().query(OrdenSql.SQL_TIPO_ASOCIADO_CONTRIBUYENTE, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int i) throws SQLException {
                return rs.getString("RFC_CONTRIBUYENTE");
            }
        }, rfc, idTipoAsociado);
    }

    @Override
    public void actualizaColaborador(FecetAsociado dto, BigDecimal idOrden) {
        getJdbcTemplateBase().update(getSqlActualizaColaborador().toString(), dto.getNombre(), dto.getRfc(),
                dto.getCorreo(), dto.getMedioContacto(), idOrden, dto.getIdTipoAsociado());
    }

    @Override
    public void eliminaColaborador(BigDecimal idOrden, BigDecimal idTipoAsociado) {
        getJdbcTemplateBase().update(getSqlEliminaColaborador().toString(), idOrden, idTipoAsociado);
    }

    @Override
    public FecetAsociado findByPrimaryKey(FecetAsociadoPk pk) {
        return findByPrimaryKey(pk.getIdAsociado());
    }

    @Override
    public List<FecetAsociado> getRepresentanteAgente(BigDecimal idTipoAsociado, BigDecimal idPropuesta) {
        return getJdbcTemplateBase().query(getSqlRepresentanteAgente().toString(), new FecetAsociadoMapper(),
                idTipoAsociado, idPropuesta);
    }

    @Override
    public List<BigDecimal> getIdAsociado(String rfc, BigDecimal idTipoAsociado, String rfcContribuyente) {
        return getJdbcTemplateBase().queryForList(getSqlIdAsociado().toString(), BigDecimal.class, rfc, idTipoAsociado,
                rfcContribuyente);
    }

    @Override
    public void confirmaColaborador(String rfcContribuyente, String rfc, BigDecimal idTipoAsociado,
            BigDecimal idOrden) {
        getJdbcTemplateBase().update(getSqlConfirmaColaborador().toString(), rfcContribuyente, rfc, idOrden,
                idTipoAsociado);
    }

    @Override
    public List<FecetAsociado> getRepresentanteAgenteByFecha(BigDecimal idTipoAsociado, BigDecimal idPropuesta) {
        StringBuilder sql = new StringBuilder();
        sql.append(getSqlRepresentanteAgente());
        sql.append(" ORDER BY FECHA_ULTIMA_MOD DESC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetAsociadoMapper(), idTipoAsociado, idPropuesta);
    }

    @Override
    public List<FecetAsociado> getAsociadosByContribuyente(String rfcContribuyente, BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" \n WHERE RFC_CONTRIBUYENTE = ?");
        query.append(" \n AND (ID_ORDEN = ? OR (ID_TIPO_ASOCIADO = 1 AND ID_ORDEN IS NULL)) ");
        query.append(" \n AND FECHA_BAJA IS NULL ");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), rfcContribuyente, idOrden);
    }

    @Override
    public List<FecetAsociado> getAsociadosByIdOrdenAndRfc(String rfc, BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT2);
        query.append(getTableName()).append(" fas");
        query.append(" INNER JOIN FECET_ORDEN orden ON orden.ID_ORDEN = fas.ID_ORDEN ");
        query.append(" \n WHERE");
        query.append(" fas.RFC = ? AND orden.ID_ORDEN = ? ORDER BY fas.RFC");
        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), rfc, idOrden);
    }

    @Override
    public List<FecetAsociado> getAsociadosByRfcAndFechaBaja(String rfcAsociado, String tipoAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT2);
        query.append(getTableName()).append(" fas");
        query.append(" \n WHERE");
        query.append(" fas.RFC = ? AND fas.FECHA_BAJA IS NULL AND fas.ID_TIPO_ASOCIADO IN (");
        query.append(tipoAsociado).append(") \n");
        query.append("  and fas.ID_ORDEN IS NOT NULL");

        logger.info(query.toString());

        return getJdbcTemplateBase().query(query.toString(), new FecetAsociadoMapper(), rfcAsociado);

    }
}
