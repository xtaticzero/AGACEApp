package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FecetContribuyenteMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FecetContribuyenteDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.sql.OrdenSql;
import mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper.ReporteCatalogosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyentePk;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteCatalogosModel;

import org.springframework.stereotype.Repository;

@Repository("fecetContribuyenteDao")
public class FecetContribuyenteDaoImpl extends BaseJDBCDao<FecetContribuyente> implements FecetContribuyenteDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -1017286802185125091L;

    private static final String SQL_SELECT
            = "SELECT ID_CONTRIBUYENTE, RFC, NOMBRE, REGIMEN, SITUACION, TIPO, SITUACION_DOMICILIO, DOMICILIO_FISCAL, ACTIVIDAD_PREPONDERANTE, ENTIDAD FROM "
            + getTableName();

    private static final String SQL_SELECT_ALIAS
            = "SELECT CONTRIBUYENTE.ID_CONTRIBUYENTE, CONTRIBUYENTE.RFC, CONTRIBUYENTE.NOMBRE, CONTRIBUYENTE.REGIMEN, CONTRIBUYENTE.SITUACION, CONTRIBUYENTE.TIPO, CONTRIBUYENTE.SITUACION_DOMICILIO, CONTRIBUYENTE.DOMICILIO_FISCAL, CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, CONTRIBUYENTE.ENTIDAD ";

    @Override
    public FecetContribuyentePk insert(FecetContribuyente dto) {

        dto.setIdContribuyente(getJdbcTemplateBase().queryForObject("SELECT FECEQ_CONTRIBUYENTE.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_CONTRIBUYENTE, RFC, NOMBRE, REGIMEN, SITUACION, TIPO, SITUACION_DOMICILIO, DOMICILIO_FISCAL, ACTIVIDAD_PREPONDERANTE, ENTIDAD ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(),
                dto.getIdContribuyente(), dto.getRfc(), dto.getNombre(), dto.getRegimen(),
                dto.getSituacion(), dto.getTipo(), dto.getSituacionDomicilio(),
                dto.getDomicilioFiscal(), dto.getActividadPreponderante(), dto.getEntidad());
        return dto.createPk();

    }

    @Override
    public void update(FecetContribuyentePk pk, FecetContribuyente dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_CONTRIBUYENTE = ?, RFC = ?, NOMBRE = ?, REGIMEN = ?, SITUACION = ?, TIPO = ?, SITUACION_DOMICILIO = ?, DOMICILIO_FISCAL = ?, ACTIVIDAD_PREPONDERANTE = ?, ENTIDAD = ? WHERE ID_CONTRIBUYENTE = ?");

        getJdbcTemplateBase().update(query.toString(),
                dto.getIdContribuyente(), dto.getRfc(), dto.getNombre(), dto.getRegimen(),
                dto.getSituacion(), dto.getTipo(), dto.getSituacionDomicilio(),
                dto.getDomicilioFiscal(), dto.getActividadPreponderante(), dto.getEntidad(),
                pk.getIdContribuyente());

    }

    @Override
    public void actualizaDatosContribuyente(final FecetContribuyente dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET NOMBRE = ?, REGIMEN = ?, SITUACION = ?, TIPO = ?, SITUACION_DOMICILIO = ?, DOMICILIO_FISCAL = ?, ACTIVIDAD_PREPONDERANTE = ?, ENTIDAD = ? WHERE RFC = ? ");

        getJdbcTemplateBase().update(query.toString(),
                dto.getNombre(), dto.getRegimen(), dto.getSituacion(), dto.getTipo(),
                dto.getSituacionDomicilio(), dto.getDomicilioFiscal(), dto.getActividadPreponderante(),
                dto.getEntidad(), dto.getRfc());

    }

    @Override
    public void delete(FecetContribuyentePk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_CONTRIBUYENTE = ?");

        getJdbcTemplateBase().update(query.toString(),
                pk.getIdContribuyente());

    }

    public static String getTableName() {
        return "FECET_CONTRIBUYENTE";
    }

    @Override
    public FecetContribuyente findByPrimaryKey(BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CONTRIBUYENTE = ?");

        List<FecetContribuyente> list
                = getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper(),
                        idContribuyente);
        return list.size() == 0 ? null : list.get(0);

    }

    @Override
    public List<FecetContribuyente> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_CONTRIBUYENTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper());

    }

    @Override
    public List<FecetContribuyente> findWhereIdContribuyenteEquals(BigDecimal idContribuyente) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_CONTRIBUYENTE = ? ORDER BY ID_CONTRIBUYENTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), idContribuyente);

    }

    @Override
    public List<FecetContribuyente> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper(), rfc);

    }

    @Override
    public List<FecetContribuyente> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper(),
                nombre);

    }

    @Override
    public List<FecetContribuyente> findWhereRegimenEquals(String regimen) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE REGIMEN = ? ORDER BY REGIMEN");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), regimen);

    }

    @Override
    public List<FecetContribuyente> findWhereSituacionEquals(String situacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE SITUACION = ? ORDER BY SITUACION");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), situacion);

    }

    @Override
    public List<FecetContribuyente> findWhereTipoEquals(String tipo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE TIPO = ? ORDER BY TIPO");
        return getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper(),
                tipo);

    }

    @Override
    public List<FecetContribuyente> findWhereSituacionDomicilioEquals(String situacionDomicilio) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE SITUACION_DOMICILIO = ? ORDER BY SITUACION_DOMICILIO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), situacionDomicilio);

    }

    @Override
    public List<FecetContribuyente> findWhereDomicilioFiscalEquals(String domicilioFiscal) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DOMICILIO_FISCAL = ? ORDER BY DOMICILIO_FISCAL");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), domicilioFiscal);

    }

    @Override
    public List<FecetContribuyente> findWhereActividadPreponderanteEquals(String actividadPreponderante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ACTIVIDAD_PREPONDERANTE = ? ORDER BY ACTIVIDAD_PREPONDERANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), actividadPreponderante);

    }

    @Override
    public List<FecetContribuyente> findWhereEntidadEquals(String entidad) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ENTIDAD = ? ORDER BY ENTIDAD");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetContribuyenteMapper(), entidad);

    }

    @Override
    public FecetContribuyente findByPrimaryKey(FecetContribuyentePk pk) {
        return findByPrimaryKey(pk.getIdContribuyente());
    }

    @Override
    public List<ReporteCatalogosModel> findEntidadAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT ENTIDAD AS NOMBRE FROM FECET_CONTRIBUYENTE ORDER BY ENTIDAD ASC");
        return getJdbcTemplateBase().query(query.toString(), new ReporteCatalogosMapper());
    }

    @Override
    public List<ReporteCatalogosModel> findEntidadInsumosAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT contribuyente.ENTIDAD AS NOMBRE FROM FECET_INSUMO insumos\n");
        query.append(" INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (insumos.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE) ORDER BY contribuyente.ENTIDAD ASC");
        return getJdbcTemplateBase().query(query.toString(), new ReporteCatalogosMapper());
    }

    @Override
    public List<ReporteCatalogosModel> findEntidadPropuestasAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT contribuyente.ENTIDAD AS NOMBRE FROM FECET_PROPUESTA propuesta\n");
        query.append(" INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (propuesta.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE) ORDER BY contribuyente.ENTIDAD ASC");
        return getJdbcTemplateBase().query(query.toString(), new ReporteCatalogosMapper());
    }

    @Override
    public List<ReporteCatalogosModel> findEntidadOrdenesAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT contribuyente.ENTIDAD AS NOMBRE FROM FECET_ORDEN orden\n");
        query.append(" INNER JOIN FECET_CONTRIBUYENTE contribuyente ON (orden.ID_CONTRIBUYENTE=contribuyente.ID_CONTRIBUYENTE) ORDER BY contribuyente.ENTIDAD ASC");
        return getJdbcTemplateBase().query(query.toString(), new ReporteCatalogosMapper());
    }

    @Override
    public List<ReporteCatalogosModel> findActividadPrepoderanteAll() {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT ACTIVIDAD_PREPONDERANTE AS NOMBRE FROM FECET_CONTRIBUYENTE ORDER BY ACTIVIDAD_PREPONDERANTE ASC");
        return getJdbcTemplateBase().query(query.toString(), new ReporteCatalogosMapper());
    }

    @Override
    public FecetContribuyente obtenerContribuyenteAuditado(BigDecimal idOrdenCompulsado) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_ALIAS);
        query.append(" FROM ");
        query.append(getTableName());
        query.append(" CONTRIBUYENTE ");
        query.append(" INNER JOIN ");
        query.append(OrdenSql.TABLE_NAME);
        query.append(" ORDEN ON ");
        query.append(" CONTRIBUYENTE.ID_CONTRIBUYENTE = ORDEN.ID_CONTRIBUYENTE ");
        query.append(" WHERE ORDEN.ID_ORDEN = ?");
        List<FecetContribuyente> list
                = getJdbcTemplateBase().query(query.toString(), new FecetContribuyenteMapper(),
                        idOrdenCompulsado);
        return list.size() == 0 ? null : list.get(0);

    }
}
