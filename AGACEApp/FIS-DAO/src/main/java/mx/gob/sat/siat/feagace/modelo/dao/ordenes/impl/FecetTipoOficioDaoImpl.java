package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetTipoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetTipoOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoAsociacionOficiosEnum;

@Repository("fecetTipoOficioDao")
public class FecetTipoOficioDaoImpl extends BaseJDBCDao<FecetTipoOficio> implements FecetTipoOficioDao {

    /**
     *
     */
    private static final long serialVersionUID = 3199028827074230960L;
    private static final String QUERY_SELECT = "SELECT";
    private static final String QUERY_FROM = "FROM";

    private static final String ALL_COLUMNS = " ID_TIPO_OFICIO, NOMBRE, DESCRIPCION, FECHA_CREACION, ID_AGRUPADOR_TIPO_OFICIO ";

    private static final String INSERT_COLUMNS = " (ID_TIPO_OFICIO,NOMBRE,DESCRIPCION,FECHA_CREACION) ";

    private static final String UPDATE_COLUMNS = " NOMBRE=?, DESCRIPCION=?,FECHA_CREACION=? ";

    private static final String SEQ_TIPO_OFICIO = " SELECT FECEQ_PLAZO_OFICIO_METODO.NEXTVAL FROM DUAL ";

    private static final String OFICIOS_ADMINISTRABLES = "SELECT FTO.ID_TIPO_OFICIO, FTO.NOMBRE, FTO.DESCRIPCION, FTO.FECHA_CREACION, FTO.ID_AGRUPADOR_TIPO_OFICIO, FTO.FECHA_FIN, FTO.BLN_ACTIVO ";

    @Override
    public BigDecimal insert(FecetTipoOficio dto) {
        StringBuilder sqlInsert = new StringBuilder("INSERT INTO");
        sqlInsert.append(getTableName());
        sqlInsert.append(INSERT_COLUMNS);
        sqlInsert.append(" VALUES (?,?,?,?)");

        dto.setIdTipoOficio(getJdbcTemplateBase().queryForObject(SEQ_TIPO_OFICIO, BigDecimal.class));

        final int estatusInsert = getJdbcTemplateBase().update(sqlInsert.toString(), dto.getIdTipoOficio(),
                dto.getNombre(), dto.getDescripcion(), dto.getFechaCreacion());

        return new BigDecimal(estatusInsert);

    }

    @Override
    public BigDecimal update(FecetTipoOficio dto) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE");
        sqlUpdate.append(getTableName());
        sqlUpdate.append("SET");
        sqlUpdate.append(UPDATE_COLUMNS);
        sqlUpdate.append(" WHERE ID_TIPO_OFICIO = ? ");

        final int estatusUpdate = getJdbcTemplateBase().update(sqlUpdate.toString(), dto.getNombre(),
                dto.getDescripcion(), dto.getFechaCreacion(), dto.getIdTipoOficio());

        return new BigDecimal(estatusUpdate);

    }

    public static String getTableName() {
        return " FECEC_TIPO_OFICIO ";
    }

    @Override
    public FecetTipoOficio getTipoOficioById(BigDecimal idOficio) {
        StringBuilder sqlById = new StringBuilder();
        sqlById.append(QUERY_SELECT);
        sqlById.append(ALL_COLUMNS);
        sqlById.append(QUERY_FROM);
        sqlById.append(getTableName());
        sqlById.append("WHERE ID_TIPO_OFICIO = ? ");
        List<FecetTipoOficio> list = getJdbcTemplateBase().query(sqlById.toString(), new FecetTipoOficioMapper(),
                idOficio);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<FecetTipoOficio> getTipoOficioByIdDependiente(BigDecimal idOficio, BigDecimal idMetodo,
            TipoAsociacionOficiosEnum tipoAsociacion) {
        StringBuilder sqlByIdDependiente = new StringBuilder();
        sqlByIdDependiente.append(QUERY_SELECT);
        sqlByIdDependiente.append(ALL_COLUMNS);
        sqlByIdDependiente.append(QUERY_FROM);
        sqlByIdDependiente.append(getTableName());
        sqlByIdDependiente.append("WHERE ID_TIPO_OFICIO IN (SELECT FECEA_TIPO_OFICIO.ID_TIPO_OFICIO_PRINCIPAL \n");
        sqlByIdDependiente.append("FROM FECEA_TIPO_OFICIO \n");
        sqlByIdDependiente.append(
                "WHERE FECEA_TIPO_OFICIO.ID_TIPO_OFICIO=? AND FECEA_TIPO_OFICIO.ID_METODO = ? AND FECEA_TIPO_OFICIO.ID_TIPO_ASOCIACION = ? )");
        return getJdbcTemplateBase().query(sqlByIdDependiente.toString(), new FecetTipoOficioMapper(), idOficio,
                idMetodo, tipoAsociacion.getIdTipoAsociacionOficios());
    }

    @Override
    public List<FecetTipoOficio> getTipoOficioByIdMetodo(BigDecimal idMetodo) {
        StringBuilder sqlByIdMetodo = new StringBuilder();
        sqlByIdMetodo.append(QUERY_SELECT);
        sqlByIdMetodo.append(ALL_COLUMNS);
        sqlByIdMetodo.append(QUERY_FROM);
        sqlByIdMetodo.append(getTableName());
        sqlByIdMetodo.append("WHERE ID_TIPO_OFICIO IN (SELECT ID_TIPO_OFICIO \n");
        sqlByIdMetodo.append("FROM FECEA_METODO_TIPO_OFICIO \n");
        sqlByIdMetodo.append("WHERE ID_METODO=? AND BLN_ACTIVO = 1)\n");
        return getJdbcTemplateBase().query(sqlByIdMetodo.toString(), new FecetTipoOficioMapper(), idMetodo);
    }

    @Override
    public List<FecetTipoOficio> getTipoOficioByIdAgrupacionEstatus(int idAgrupacion, BigDecimal idOrden,
            BigDecimal estatus) {
        StringBuilder sqlByIdMetodo = new StringBuilder();
        Object[] param = new Object[]{idAgrupacion, idOrden, estatus};
        sqlByIdMetodo.append(QUERY_SELECT);
        sqlByIdMetodo.append(ALL_COLUMNS);
        sqlByIdMetodo.append(QUERY_FROM);
        sqlByIdMetodo.append(getTableName());
        sqlByIdMetodo.append("WHERE ID_AGRUPADOR_TIPO_OFICIO = ? ");
        sqlByIdMetodo.append("and ID_TIPO_OFICIO not in( ");
        sqlByIdMetodo.append("select fof.ID_TIPO_OFICIO from fecet_orden fo, fecet_oficio fof ");
        sqlByIdMetodo.append("where fof.id_orden = fo.id_orden ");
        sqlByIdMetodo.append("and fo.id_orden = ? ");
        sqlByIdMetodo.append("and fof.id_estatus <> ?) ");
        return getJdbcTemplateBase().query(sqlByIdMetodo.toString(), new FecetTipoOficioMapper(), param);
    }

    @Override
    public List<FecetTipoOficio> findAll() {
        StringBuilder sqlFindAll = new StringBuilder();
        sqlFindAll.append(QUERY_SELECT);
        sqlFindAll.append(ALL_COLUMNS);
        sqlFindAll.append(QUERY_FROM);
        sqlFindAll.append(getTableName());
        return getJdbcTemplateBase().query(sqlFindAll.toString(), new FecetTipoOficioMapper());
    }

    @Override
    public List<FecetTipoOficio> findOficiosAdministrables() {
        StringBuilder sqlOficiosAdmins = new StringBuilder();
        sqlOficiosAdmins.append(OFICIOS_ADMINISTRABLES);
        sqlOficiosAdmins.append("FROM FECEC_TIPO_OFICIO FTO ");
        sqlOficiosAdmins.append(
                "INNER JOIN FECEC_AGRUPADOR_TIPO_OFICIO ATO ON ATO.ID_AGRUPADOR_TIPO_OFICIO = FTO.ID_AGRUPADOR_TIPO_OFICIO ");
        sqlOficiosAdmins.append("WHERE ATO.ID_AGRUPADOR_TIPO_OFICIO = ");
        sqlOficiosAdmins.append(AgrupadorOficiosEnum.ADMINISTRABLES.getIdAgrupadorOficios());
        sqlOficiosAdmins.append(" AND FTO.BLN_ACTIVO = 1");

        return getJdbcTemplateBase().query(sqlOficiosAdmins.toString(), new FecetTipoOficioMapper());
    }

    @Override
    public List<FecetTipoOficio> getOficiosAdminXActualizar(String condicion) {
        StringBuilder sqlOficiosAdmins = new StringBuilder();
        sqlOficiosAdmins.append(OFICIOS_ADMINISTRABLES);
        sqlOficiosAdmins.append("FROM FECEC_TIPO_OFICIO FTO ");
        sqlOficiosAdmins.append(
                "INNER JOIN FECEC_AGRUPADOR_TIPO_OFICIO ATO ON ATO.ID_AGRUPADOR_TIPO_OFICIO = FTO.ID_AGRUPADOR_TIPO_OFICIO ");
        sqlOficiosAdmins.append("WHERE ATO.ID_AGRUPADOR_TIPO_OFICIO = ");
        sqlOficiosAdmins.append(AgrupadorOficiosEnum.ADMINISTRABLES.getIdAgrupadorOficios());
        sqlOficiosAdmins.append(" AND FTO.BLN_ACTIVO = 1 ");
        sqlOficiosAdmins.append(" AND FTO.ID_TIPO_OFICIO IN (");
        sqlOficiosAdmins.append(condicion).append(") ");

        return getJdbcTemplateBase().query(sqlOficiosAdmins.toString(), new FecetTipoOficioMapper());
    }

    @Override
    public List<FecetTipoOficio> getOficiosAdminByMetodo(BigDecimal idMetodo, String condicion1, String condicion2) {

        StringBuilder sqlOficiosByMetodo = new StringBuilder();
        sqlOficiosByMetodo.append(OFICIOS_ADMINISTRABLES);
        sqlOficiosByMetodo.append("FROM FECEC_TIPO_OFICIO FTO ");
        sqlOficiosByMetodo
                .append("INNER JOIN FECEA_METODO_TIPO_OFICIO FMTO ON FMTO.ID_TIPO_OFICIO = FTO.ID_TIPO_OFICIO ");
        sqlOficiosByMetodo.append("WHERE FMTO.ID_METODO IN (").append(idMetodo).append(") ");
        sqlOficiosByMetodo.append("AND FMTO.BLN_ACTIVO IN (").append(condicion1).append(") ");
        sqlOficiosByMetodo.append("AND FTO.ID_AGRUPADOR_TIPO_OFICIO = ");
        sqlOficiosByMetodo.append(AgrupadorOficiosEnum.ADMINISTRABLES.getIdAgrupadorOficios());
        sqlOficiosByMetodo.append(" AND FTO.BLN_ACTIVO IN (").append(condicion2).append(") ");

        return getJdbcTemplateBase().query(sqlOficiosByMetodo.toString(), new FecetTipoOficioMapper());
    }

}
