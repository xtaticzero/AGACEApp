package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FeceaPropuestaAccionDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FeceaPropuestaAccionMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FeceaPropuestaAccion;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

/**
 * @author sergio.vaca
 *
 */
@Repository("feceaPropuestaAccionDao")
public class FeceaPropuestaAccionDaoImpl extends BaseJDBCDao<FeceaPropuestaAccion> implements FeceaPropuestaAccionDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static String getTableName() {
        return "FECEA_PROPUESTA_ACCION";
    }

    @Override
    public void insert(FeceaPropuestaAccion propuestaAccion) {
        if (propuestaAccion.getIdPropuestaAccion() == null) {
            propuestaAccion.setIdPropuestaAccion(getConsecutivo());
        }
        String query = "INSERT INTO FECEA_PROPUESTA_ACCION (ID_PROPUESTA_ACCION, ID_PROPUESTA, ID_ACCION, ID_ACCION_ORIGEN) VALUES (?, ?, ?, ?)";
        getJdbcTemplateBase().update(query, propuestaAccion.getIdPropuestaAccion(), propuestaAccion.getIdPropuesta(), propuestaAccion.getIdAccion(), propuestaAccion.getIdAccionOrigen());
    }

    @Override
    public void updateAccionIdPropuesta(BigDecimal idPropuesta, BigDecimal idAccion, BigDecimal idAccionOrigen) {
        StringBuilder query = new StringBuilder();
        List<Object> parametros = new ArrayList<Object>();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_ACCION = ? ");
        parametros.add(idAccion);
        if (idAccionOrigen != null) {
            query.append(", ID_ACCION_ORIGEN = ? ");
            parametros.add(idAccionOrigen);
        }
        query.append(" WHERE ID_PROPUESTA = ?");
        parametros.add(idPropuesta);
        getJdbcTemplateBase().update(query.toString(), parametros.toArray());
    }

    private BigDecimal getConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_PROPUESTA_ACCION.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public List<FeceaPropuestaAccion> getAccionByIdPropuesta(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT FAP.ID_PROPUESTA_ACCION, FAP.ID_PROPUESTA, \n");
        query.append("FAP.ID_ACCION, FAP.ID_ACCION_ORIGEN FROM FECEA_PROPUESTA_ACCION FAP \n");
        query.append("WHERE FAP.ID_PROPUESTA = ? AND FAP.ID_ACCION IN (");
        query.append(AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion());
        query.append(", ");
        query.append(AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion());
        query.append(") ");

        return getJdbcTemplateBase().query(query.toString(), new FeceaPropuestaAccionMapper(), idPropuesta);
    }

    @Override
    public List<FeceaPropuestaAccion> getAccionExistente(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT FAP.ID_PROPUESTA_ACCION, FAP.ID_PROPUESTA, \n");
        query.append("FAP.ID_ACCION, FAP.ID_ACCION_ORIGEN FROM FECEA_PROPUESTA_ACCION FAP \n");
        query.append("WHERE FAP.ID_PROPUESTA = ? ");

        return getJdbcTemplateBase().query(query.toString(), new FeceaPropuestaAccionMapper(), idPropuesta);
    }
}
