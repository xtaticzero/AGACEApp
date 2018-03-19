package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececUnidadAdministrativaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.ClaveFolioOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececUnidadAdministrativaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ClaveFolioOficioDTO;

@Repository("fececUnidadAdministrativaDao")
public class FececUnidadAdministrativaDaoImpl extends BaseJDBCDao<FececUnidadAdministrativa>
        implements FececUnidadAdministrativaDao {

    private static final long serialVersionUID = 325348891645406782L;

    private static final String SQL_SELECT = "SELECT ID_UNIDAD_ADMINISTRATIVA, NOMBRE, DESCRIPCION, FECHA_INICIO, FECHA_FIN, BLN_ACTIVO FROM "
            + getTableName();

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_UNIDAD_ADMINISTRATIVA";
    }

    @Override
    public List<FececUnidadAdministrativa> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_UNIDAD_ADMINISTRATIVA");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());
    }

    @Override
    public List<FececUnidadAdministrativa> findUnidadesAdministrativasActivas() {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT ADM.ID_UNIDAD_ADMINISTRATIVA");
        query.append(" \n FROM FECEA_ADACE_MODULO ADM");
        query.append(" \n JOIN FECEC_MODULOS MODU ON MODU.ID_MODULO = ADM.ID_MODULO");
        query.append(" \n WHERE ADM.BLN_ACTIVO = 1 AND ADM.ID_MODULO = 1");
        query.append(" \n ORDER BY ADM.ID_UNIDAD_ADMINISTRATIVA");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());
    }

    @Override
    public List<FececUnidadAdministrativa> findUnidadesAdministrativasPropuestas() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE BLN_ACTIVO = 1 AND ID_UNIDAD_ADMINISTRATIVA NOT IN (1, 17, 98)");
        query.append(" ORDER BY ID_UNIDAD_ADMINISTRATIVA");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());
    }

    @Override
    public List<FececUnidadAdministrativa> findUnidadesByAuditor(String condicion) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_UNIDAD_ADMINISTRATIVA NOT IN (");
        query.append(condicion);
        query.append(") ORDER BY ID_UNIDAD_ADMINISTRATIVA ");

        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());
    }

    /**
     * Returns all rows from the FECEC_UNIDAD_ADMINISTRATIVA table that match
     * the criteria 'ID_ARACE IS NOT IN (1, 17, 98)'.
     *
     * @return
     */
    @Override
    public List<FececUnidadAdministrativa> getUnidadesParaAsignarPropuesta() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE NOT IN (1, 17, 98) ORDER BY ID_ARACE DESC");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());

    }

    @Override
    public ClaveFolioOficioDTO obtenerClavesFolioOficio(BigDecimal idArace) {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT AC.ID_ARACE ID_ARACE, CAG.CLAVE CLAVE_ADMON_GRAL, CAA.CLAVE CLAVE_ADMIN_AREA, ");
        query.append(" CSA.CLAVE CLAVE_SUBADMIN_AREA, CJD.CLAVE CLAVE_JEFACTURA_DEPTO ");
        query.append(" FROM FECEC_ADMINISTRACION_CENTRAL AC, FECEC_CVES_ADMON_GENERAL CAG, ");
        query.append(" FECEC_CVES_ADMON_AREA CAA, FECEC_CVES_SUBADMON_AREA CSA, ");
        query.append(" FECEC_CVES_JEFATURA_DPTO CJD  ");
        query.append(" WHERE AC.ID_ARACE = ?");
        query.append(" AND AC.ID_ADMINISTRACION_GENERAL = CAG.ID_ADMINISTRACION_GENERAL ");
        query.append(" AND AC.ID_ADMINISTRACION_AREA = CAA.ID_ADMINISTRACION_AREA  ");
        query.append(" AND AC.ID_SUBADMINISTRACION_AREA = CSA.ID_SUBADMINISTRACION_AREA ");
        query.append(" AND AC.ID_JEFATURA_DEPARTAMENTO  = CJD.ID_JEFATURA_DEPARTAMENTO ");

        return getJdbcTemplateBase().queryForObject(query.toString(), new ClaveFolioOficioMapper(), idArace);
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria ''.
     *
     * @return
     */
    @Override
    public List<FececUnidadAdministrativa> findAllReportes() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_UNIDAD_ADMINISTRATIVA NOT IN (1,17,98)");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper());
    }

    /**
     * Returns all rows from the FECEC_ARACE table that match the criteria
     * 'ID_ARACE = :idArace'.
     *
     * @param idArace
     * @return
     */
    @Override
    public List<FececUnidadAdministrativa> findWhereIdAraceEquals(BigDecimal idArace) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_UNIDAD_ADMINISTRATIVA = ? ORDER BY ID_UNIDAD_ADMINISTRATIVA");
        return getJdbcTemplateBase().query(query.toString(), new FececUnidadAdministrativaMapper(), idArace);

    }
}
