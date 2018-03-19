package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecebAccionPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecebAccionPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.HistorialAccionesMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestaAccionSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

/**
 * @author sergio.vaca
 *
 */
@Repository("fecebAccionPropuestaDao")
public class FecebAccionPropuestaDaoImpl extends BaseJDBCDao<FecebAccionPropuesta> implements FecebAccionPropuestaDao {

    private static final long serialVersionUID = 1L;

    private static final String SELECT = "SELECT ";
    private static final String SALTO_LINEA_COMA_INICIAL = ", \n";
    private static final String SALTO_LINEA = " \n";
    private static final String FROM_FECEB_ACCION_PROPUESTA_BITACORA = "FROM FECEB_ACCION_PROPUESTA BITACORA \n";
    private static final String WHERE_TRUE = "WHERE 1=1 ";
    private static final String AND_BITACORA_ID_PROPUESTA = "AND BITACORA.ID_PROPUESTA = ? \n";

    @Override
    public void insert(FecebAccionPropuesta accionPropuesta) {
        if (accionPropuesta.getIdAccionPropuesta() == null) {
            accionPropuesta.setIdAccionPropuesta(getConsecutivo());
        }
        String query = "INSERT INTO FECEB_ACCION_PROPUESTA (ID_ACCION_PROPUESTA, ID_PROPUESTA, ID_DETALLE_ACCION, ID_ACCION, ID_ACCION_ORIGEN, OBSERVACIONES, FECHA_HORA,ID_EMPLEADO) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        List<Object> parametros = new ArrayList<Object>();
        parametros.add(accionPropuesta.getIdAccionPropuesta());
        parametros.add(accionPropuesta.getIdPropuesta());
        parametros.add(accionPropuesta.getIdDetalleAccion());
        parametros.add(accionPropuesta.getIdAccion());
        parametros.add(accionPropuesta.getIdAccionOrigen());
        parametros.add(accionPropuesta.getObservaciones());
        parametros.add(accionPropuesta.getFechaHora());
        parametros.add(accionPropuesta.getIdEmpleado());
        getJdbcTemplateBase().update(query, parametros.toArray());
    }

    public List<FecebAccionPropuesta> obtenerAccionSinAsociarTabla(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT).append(PropuestaAccionSQL.CAMPOS_BITACORA_ACCION_PROPUESTA)
                .append(SALTO_LINEA_COMA_INICIAL);
        query.append(PropuestaAccionSQL.CAMPOS_ACCIONES_FUNCIONARIO).append(SALTO_LINEA);
        query.append(FROM_FECEB_ACCION_PROPUESTA_BITACORA);
        query.append(PropuestaAccionSQL.INNER_JOIN_FUNCIONARIO_ACCION).append(SALTO_LINEA);
        query.append("WHERE BITACORA.ID_PROPUESTA = ? AND BITACORA.ID_DETALLE_ACCION IS NULL\n ");
        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecebAccionPropuestaMapper(), idPropuesta);
    }

    public List<FecebAccionPropuesta> obtenerAccionAsociarRechazo(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT).append(PropuestaAccionSQL.CAMPOS_BITACORA_ACCION_PROPUESTA)
                .append(SALTO_LINEA_COMA_INICIAL);
        query.append(PropuestaAccionSQL.CAMPOS_ACCIONES_FUNCIONARIO).append(SALTO_LINEA_COMA_INICIAL);
        query.append(
                "RECHAZO.ID_RECHAZO_PROPUESTA,RECHAZO.DESCRIPCION OBSERVACIONES_AUDITOR,MOTIVO.DESCRIPCION MOTIVO_DESCRIPCION, COUNT(RECHAZO.ID_RECHAZO_PROPUESTA) DOCUMENTOS \n");
        query.append(FROM_FECEB_ACCION_PROPUESTA_BITACORA);
        query.append(PropuestaAccionSQL.INNER_JOIN_FUNCIONARIO_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_RECHAZO_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_DOC_RECHAZO).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_MOTIVO_RECHAZO).append(SALTO_LINEA);
        query.append(WHERE_TRUE);

        query.append(obtenerAccionesFuncionario(accionesFuncionarioEnum));

        query.append(AND_BITACORA_ID_PROPUESTA);
        query.append(PropuestaAccionSQL.GROUP_BY_RECHAZO);

        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecebAccionPropuestaMapper(), idPropuesta);
    }

    public List<FecebAccionPropuesta> obtenerAccionAsociarCancelar(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT).append(PropuestaAccionSQL.CAMPOS_BITACORA_ACCION_PROPUESTA)
                .append(SALTO_LINEA_COMA_INICIAL);
        query.append(PropuestaAccionSQL.CAMPOS_ACCIONES_FUNCIONARIO).append(SALTO_LINEA_COMA_INICIAL);
        query.append(
                "CANCELACION.ID_CANCELACION, CANCELACION.OBSERVACIONES OBSERVACIONES_AUDITOR, MOTIVO.DESCRIPCION MOTIVO_DESCRIPCION, COUNT(CANCELACION.ID_CANCELACION) DOCUMENTOS \n");
        query.append(FROM_FECEB_ACCION_PROPUESTA_BITACORA);
        query.append(PropuestaAccionSQL.INNER_JOIN_FUNCIONARIO_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_CANCELACION_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_DOC_CANCELACION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_MOTIVO_CANCELACION).append(SALTO_LINEA);
        query.append(WHERE_TRUE);

        query.append(obtenerAccionesFuncionario(accionesFuncionarioEnum));

        query.append(AND_BITACORA_ID_PROPUESTA);
        query.append(PropuestaAccionSQL.GROUP_BY_CANCELACION);

        return getJdbcTemplateBase().query(query.toString(), new FecebAccionPropuestaMapper(), idPropuesta);
    }

    public List<FecebAccionPropuesta> obtenerAccionAsociarRetroalimentar(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT).append(PropuestaAccionSQL.CAMPOS_BITACORA_ACCION_PROPUESTA)
                .append(SALTO_LINEA_COMA_INICIAL);
        query.append(PropuestaAccionSQL.CAMPOS_ACCIONES_FUNCIONARIO).append(SALTO_LINEA_COMA_INICIAL);
        query.append(
                "RETROALIMENTAR.ID_RETROALIMENTACION, RETROALIMENTAR.DESCRIPCION  OBSERVACIONES_AUDITOR,MOTIVO.DESCRIPCION MOTIVO_DESCRIPCION, COUNT(RETROALIMENTAR.ID_RETROALIMENTACION) DOCUMENTOS \n");
        query.append(FROM_FECEB_ACCION_PROPUESTA_BITACORA);
        query.append(PropuestaAccionSQL.INNER_JOIN_FUNCIONARIO_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_RETROALIMENTACION_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_DOC_RETROALIMENTACION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_MOTIVO_RETROALIMENTACION).append(SALTO_LINEA);
        query.append(WHERE_TRUE);

        query.append(obtenerAccionesFuncionario(accionesFuncionarioEnum));

        query.append(AND_BITACORA_ID_PROPUESTA);
        query.append(PropuestaAccionSQL.GROUP_BY_RETROALIMENTACION);
        logger.error(query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecebAccionPropuestaMapper(), idPropuesta);
    }

    public List<FecebAccionPropuesta> obtenerAccionAsociarTransferir(BigDecimal idPropuesta,
            AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        StringBuilder query = new StringBuilder();
        query.append(SELECT).append(PropuestaAccionSQL.CAMPOS_BITACORA_ACCION_PROPUESTA)
                .append(SALTO_LINEA_COMA_INICIAL);
        query.append(PropuestaAccionSQL.CAMPOS_ACCIONES_FUNCIONARIO).append(SALTO_LINEA_COMA_INICIAL);
        query.append(
                "TRANSFERENCIA.ID_TRANSFERENCIA,TRANSFERENCIA.OBSERVACIONES OBSERVACIONES_AUDITOR, COUNT(TRANSFERENCIA.ID_TRANSFERENCIA) DOCUMENTOS \n");
        query.append(FROM_FECEB_ACCION_PROPUESTA_BITACORA);
        query.append(PropuestaAccionSQL.INNER_JOIN_FUNCIONARIO_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_TRANSFERIR_ACCION).append(SALTO_LINEA);
        query.append(PropuestaAccionSQL.INNER_JOIN_DOC_TRANSFERIR).append(SALTO_LINEA);
        query.append(WHERE_TRUE);

        query.append(obtenerAccionesFuncionario(accionesFuncionarioEnum));

        query.append(AND_BITACORA_ID_PROPUESTA);
        query.append(PropuestaAccionSQL.GROUP_BY_TRANSFERIR);

        return getJdbcTemplateBase().query(query.toString(), new FecebAccionPropuestaMapper(), idPropuesta);
    }

    private String obtenerAccionesFuncionario(AccionesFuncionarioEnum... accionesFuncionarioEnum) {
        StringBuilder query = new StringBuilder();

        if (accionesFuncionarioEnum != null && accionesFuncionarioEnum.length > 0) {
            query.append(" AND BITACORA.ID_ACCION IN (");
            Iterator<AccionesFuncionarioEnum> iterador = Arrays.asList(accionesFuncionarioEnum).iterator();
            while (iterador.hasNext()) {
                query.append(iterador.next().getIdAccion());
                if (iterador.hasNext()) {
                    query.append(", ");
                }
            }
            query.append(" ) \n");
        }

        return query.toString();
    }

    private BigDecimal getConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_ACCION_PROPUESTA.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public List<FecebAccionPropuesta> obtenerAccionNoAprobacion(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();

        query.append(
                "SELECT ID_ACCION_PROPUESTA, ID_PROPUESTA, ID_DETALLE_ACCION, ID_ACCION, ID_ACCION_ORIGEN, OBSERVACIONES, FECHA_HORA,ID_EMPLEADO \n");
        query.append("FROM FECEB_ACCION_PROPUESTA \n");
        query.append("WHERE ID_PROPUESTA = ? \n");
        query.append(" AND ID_ACCION IN (");
        query.append(accionesFuncionarioNoAprobacion());
        query.append(") \n");
        query.append("ORDER BY ID_ACCION_PROPUESTA DESC ");

        return getJdbcTemplateBase().query(query.toString(), new HistorialAccionesMapper(), idPropuesta);
    }

    private String accionesFuncionarioNoAprobacion() {
        StringBuilder acciones = new StringBuilder();

        acciones.append(AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion());
        acciones.append(",");
        acciones.append(AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion());
        acciones.append(",");
        acciones.append(AccionesFuncionarioEnum.NO_APROBACION_CANCELACION.getIdAccion());
        acciones.append(",");
        acciones.append(AccionesFuncionarioEnum.NO_APROBACION_RECHAZO.getIdAccion());
        acciones.append(",");
        acciones.append(AccionesFuncionarioEnum.NO_APROBACION_TRANSFERENCIA.getIdAccion());
        acciones.append(",");
        acciones.append(AccionesFuncionarioEnum.RETROALIMENTACION_NO_APROBADA.getIdAccion());

        return acciones.toString();
    }
}
