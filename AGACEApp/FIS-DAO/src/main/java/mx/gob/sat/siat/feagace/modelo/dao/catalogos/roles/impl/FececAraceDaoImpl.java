/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administraci\u00f3 n Tributaria (SAT).
 * Este software contiene informaci\u00f3n propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgaci\u00f3n en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.ClaveFolioOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececAraceMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.roles.FececAraceDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececAracePk;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;

import org.springframework.stereotype.Repository;

@Repository("fececAraceDao")
public class FececAraceDaoImpl extends BaseJDBCDao<FececArace> implements FececAraceDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = 2015127780740098981L;

    /**
     * Este atributo es un SELECT para seleccionar los datoas de las columnas de
     * la tabla FECEC_ARACE
     */
    private static final String SQL_SELECT = "SELECT ID_ARACE, NOMBRE, SEDE, CENTRAL FROM " + getTableName();
    private static final String SQL_SELECT_ENTIDAD = "SELECT SEDE FROM " + getTableName();

    /**
     * Este atributo es una condici\u00f3 n WHERE para la los datos de la tabla
     * FECEC_ARACE
     */
    private static final String SQL_WHERE = " WHERE ID_ARACE = ? ";

    /**
     * x Method 'insert'
     *
     * @param dto
     * @return FececAracePk
     */
    public FececAracePk insert(FececArace dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_ARACE, NOMBRE, SEDE, CENTRAL ) VALUES ( ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdArace(), dto.getNombre(), dto.getSede(),
                dto.getCentral());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_ARACE table.
     */
    public void update(FececAracePk pk, FececArace dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ARACE = ?, NOMBRE = ?, SEDE = ?, CENTRAL = ? WHERE ID_ARACE = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdArace(), dto.getNombre(), dto.getSede(),
                dto.getCentral(), pk.getIdArace());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_ARACE";
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    public FececArace findByPrimaryKey(BigDecimal idArace) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(SQL_WHERE);
        List<FececArace> list = getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), idArace);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     */
    public List<FececArace> findAll() {
        return getJdbcTemplateBase().query(SQL_SELECT, new FececAraceMapper());
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     */
    public List<FececArace> findAllReportes() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE NOT IN (1,17,98)");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper());
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    public List<FececArace> findWhereIdAraceEquals(BigDecimal idArace) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), idArace);

    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececArace> findWhereNombreEquals(String nombre) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE = ? ORDER BY NOMBRE");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria 'SEDE
     * = :sede'.
     */
    public List<FececArace> findWhereSedeEquals(String sede) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE SEDE = ? ORDER BY SEDE");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), sede);

    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria 'SEDE
     * = :entidad'.
     */
    public BigDecimal findWhereEntidadInclude(final String entidad) {

        String likeSql = "%" + entidad + "%";
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);

        query.append(" WHERE SEDE  LIKE ? ORDER BY SEDE");
        List<FececArace> res = getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), likeSql);
        if (res != null && !res.isEmpty()) {
            return res.get(0).getIdArace();
        }
        logger.warn("No se encontro la SEDE");
        return null;
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'CENTRAL = :central'.
     */
    public List<FececArace> findWhereCentralEquals(final Boolean central) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE CENTRAL = ? ORDER BY CENTRAL");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper(), central);

    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE IS NOT IN (1, 17, 98)'.
     */
    public List<FececArace> getUnidadesParaAsignarPropuesta() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE NOT IN (1, 17, 98) ORDER BY ID_ARACE DESC");
        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper());

    }

    /**
     * Returns the rows from the FECEC_ARACE table that matches the specified
     * primary-key value.
     */
    public FececArace findByPrimaryKey(FececAracePk pk) {

        return findByPrimaryKey(pk.getIdArace());

    }

    /**
     * Returns the rows from the FECEC_ESTATUS table that matches the specified
     * ONLY ENTIDAD.
     */
    public List<String> findOnlyEntidad() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_ENTIDAD);
        query.append(" WHERE ID_ARACE IN (58,59,60,69,70,97) ");
        return getJdbcTemplateBase().queryForList(query.toString(), String.class);
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     */
    @Override
    public List<FececArace> findAraceByAuditor(final String rfcAuditor, String condicion) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT FECEC_ARACE.ID_ARACE, FECEC_ARACE.NOMBRE, FECEC_ARACE.SEDE, FECEC_ARACE.CENTRAL FROM FECEC_ARACE ");
        query.append("WHERE FECEC_ARACE.ID_ARACE NOT IN ( ");
        query.append("SELECT FECET_DETALLE_EMPLEADO.ID_ARACE FROM FECET_DETALLE_EMPLEADO INNER JOIN FECEC_EMPLEADO ON FECEC_EMPLEADO.ID_EMPLEADO = FECET_DETALLE_EMPLEADO.ID_EMPLEADO ");
        query.append(" WHERE FECEC_EMPLEADO.RFC = '").append(rfcAuditor).append("') ");
        query.append(" AND FECEC_ARACE.ID_ARACE NOT IN ( ");
        query.append(condicion).append(" )");

        return getJdbcTemplateBase().query(query.toString(), new FececAraceMapper());
    }

    public ClaveFolioOficioDTO obtenerClavesFolioOficio(BigDecimal idArace) {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT AC.ID_ARACE ID_ARACE, CAG.CLAVE CLAVE_ADMON_GRAL, CAA.CLAVE CLAVE_ADMIN_AREA, ");
        query.append(" CSA.CLAVE CLAVE_SUBADMIN_AREA, CJD.CLAVE CLAVE_JEFACTURA_DEPTO ");
        query.append(" FROM FECEC_ADMINISTRACION_CENTRAL AC, FECEC_CVES_ADMON_GENERAL CAG, ");
        query.append(" FECEC_UNIDAD_ADMINISTRATIVA ARA, FECEC_CVES_ADMON_AREA CAA, FECEC_CVES_SUBADMON_AREA CSA, ");
        query.append(" FECEC_CVES_JEFATURA_DPTO CJD  ");
        query.append(" WHERE AC.ID_ARACE = ?");
        query.append(" AND AC.ID_ADMINISTRACION_GENERAL = CAG.ID_ADMINISTRACION_GENERAL ");
        query.append(" AND AC.ID_ARACE = ARA.ID_UNIDAD_ADMINISTRATIVA AND AC.ID_ADMINISTRACION_AREA = CAA.ID_ADMINISTRACION_AREA  ");
        query.append(" AND AC.ID_SUBADMINISTRACION_AREA = CSA.ID_SUBADMINISTRACION_AREA ");
        query.append(" AND AC.ID_JEFATURA_DEPARTAMENTO  = CJD.ID_JEFATURA_DEPARTAMENTO ");

        return getJdbcTemplateBase().queryForObject(query.toString(), new ClaveFolioOficioMapper(), idArace);
    }
}
