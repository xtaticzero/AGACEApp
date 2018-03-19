package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetPapelesTrabajoDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetPapelesTrabajoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.PapelesTrabajo;

@Repository("fecetPapelesTrabajoDao")
public class FecetPapelesTrabajoDaoImpl extends BaseJDBCDao<PapelesTrabajo> implements FecetPapelesTrabajoDao {

    private static final long serialVersionUID = -4718728289853486517L;
    private static final String ORDER_BY_FECHA_CREACION_DESC = "ORDER BY P.FECHA_CREACION DESC";

    private String getSqlPapelesTrabajo() {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT P.ID_PAPELES_TRABAJO, P.ID_PROPUESTA, P.ID_ORDEN, \n");
        sql.append("P.ID_OFICIO, P.RUTA_ARCHIVO, P.FECHA_CREACION, P.BLN_ACTIVO, \n");
        sql.append("P.FECHA_FIN FROM FECET_PAPELES_TRABAJO P \n");

        return sql.toString();
    }

    private String getTableName() {
        return "FECET_PAPELES_TRABAJO ";
    }

    @Override
    public void insert(PapelesTrabajo dto) {

        if (dto.getIdPapelesTrabajo() == null) {
            dto.setIdPapelesTrabajo(getJdbcTemplateBase()
                    .queryForObject("SELECT FECEQ_PAPELES_TRABAJO.NEXTVAL FROM DUAL", BigDecimal.class));
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(
                " ( ID_PAPELES_TRABAJO, ID_PROPUESTA, ID_ORDEN, ID_OFICIO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, ID_TIPO_OFICIO ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? , ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdPapelesTrabajo(), dto.getIdPropuesta(),
                dto.getIdOrden(), dto.getIdOficio(), dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getBlnActivo(),
                dto.getFechaFin(), dto.getIdTipoOficio());

    }

    @Override
    public List<PapelesTrabajo> getPapelesByIdPropuesta(BigDecimal idPropuesta) {

        StringBuilder consulta = new StringBuilder();

        consulta.append(getSqlPapelesTrabajo());
        consulta.append("WHERE P.ID_PROPUESTA = ? ");
        consulta.append(" AND P.ID_ORDEN IS NULL AND p.ID_OFICIO IS NULL ");
        consulta.append(ORDER_BY_FECHA_CREACION_DESC);

        return getJdbcTemplateBase().query(consulta.toString(), new FecetPapelesTrabajoMapper(), idPropuesta);
    }

    @Override
    public List<PapelesTrabajo> getPapelesOfOrden(BigDecimal idPropuesta, BigDecimal idOrden) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(getSqlPapelesTrabajo());
        consulta.append(
                "WHERE (P.ID_PROPUESTA = ? OR P.ID_ORDEN = ?) AND P.ID_OFICIO IS NULL AND P.ID_TIPO_OFICIO IS NULL AND BLN_ACTIVO = 1 ");
        consulta.append(ORDER_BY_FECHA_CREACION_DESC);
        return getJdbcTemplateBase().query(consulta.toString(), new FecetPapelesTrabajoMapper(), idPropuesta, idOrden);
    }

    @Override
    public List<PapelesTrabajo> getPapelesOfOficio(BigDecimal idOrden, BigDecimal idTipoOficio) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(getSqlPapelesTrabajo());
        consulta.append("WHERE (P.ID_ORDEN = ?) AND P.ID_TIPO_OFICIO = ? AND P.ID_OFICIO IS NULL AND BLN_ACTIVO = 1 ");
        consulta.append(ORDER_BY_FECHA_CREACION_DESC);
        return getJdbcTemplateBase().query(consulta.toString(), new FecetPapelesTrabajoMapper(), idOrden, idTipoOficio);
    }

    @Override
    public List<PapelesTrabajo> getPapelesOfOficioById(BigDecimal idOficio) {
        StringBuilder consulta = new StringBuilder();
        consulta.append(getSqlPapelesTrabajo());
        consulta.append("WHERE P.ID_OFICIO = ? AND BLN_ACTIVO = 1 ");
        consulta.append(ORDER_BY_FECHA_CREACION_DESC);
        return getJdbcTemplateBase().query(consulta.toString(), new FecetPapelesTrabajoMapper(), idOficio);
    }

    @Override
    public void actualizar(PapelesTrabajo dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_PROPUESTA=?, ID_ORDEN = ?, ID_OFICIO = ?,  BLN_ACTIVO = ?, FECHA_FIN = ?");
        query.append(" WHERE ID_PAPELES_TRABAJO = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdPropuesta(), dto.getIdOrden(), dto.getIdOficio(),
                dto.getBlnActivo(), dto.getFechaFin(), dto.getIdPapelesTrabajo());
    }
}
