package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececMotivo;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecebAccionPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;

public class FecebAccionPropuestaMapper extends FececAccionesFuncionarioMapper {

    private static final String COLUMN_ID_ACCION_PROPUESTA = "ID_ACCION_PROPUESTA";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_DETALLE_ACCION = "ID_DETALLE_ACCION";
    private static final String COLUMN_ID_ACCION = "ID_ACCION";
    private static final String COLUMN_ID_ACCION_ORIGEN = "ID_ACCION_ORIGEN";
    private static final String COLUMN_OBSERVACIONES = "OBSERVACIONES";
    private static final String COLUMN_FECHA_HORA = "FECHA_HORA";
    private static final String COLUMN_ID_EMPLEADO = "ID_EMPLEADO";
    private static final String COLUMN_NUMERO_DOCUMENTOS = "DOCUMENTOS";
    private static final String COLUMN_MOTIVO = "MOTIVO_DESCRIPCION";
    private static final String COLUMN_ID_RETROALIMENTACION = "ID_RETROALIMENTACION";
    private static final String COLUMN_ID_CANCELACION = "ID_CANCELACION";
    private static final String COLUMN_ID_RECHAZO_PROPUESTA = "ID_RECHAZO_PROPUESTA";
    private static final String COLUMN_ID_TRANSFERENCIA = "ID_TRANSFERENCIA";
    private static final String COLUMN_OBSERVACIONES_AUDITOR = "OBSERVACIONES_AUDITOR";
    private static final String NO_APLICA = "N/A";

    @Override
    public FecebAccionPropuesta mapRow(ResultSet rs, int i) throws SQLException {
        FecebAccionPropuesta fecebAccionPropuesta = super.mapRow(rs, i);
        fecebAccionPropuesta.setIdAccionPropuesta(rs.getBigDecimal(COLUMN_ID_ACCION_PROPUESTA));
        fecebAccionPropuesta.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        fecebAccionPropuesta.setIdDetalleAccion(rs.getBigDecimal(COLUMN_ID_DETALLE_ACCION));
        fecebAccionPropuesta.setIdAccion(rs.getBigDecimal(COLUMN_ID_ACCION));
        fecebAccionPropuesta.setIdAccionOrigen(rs.getBigDecimal(COLUMN_ID_ACCION_ORIGEN));
        fecebAccionPropuesta.setObservaciones(rs.getString(COLUMN_OBSERVACIONES));
        if (fecebAccionPropuesta.getObservaciones() == null) {
            fecebAccionPropuesta.setObservaciones(NO_APLICA);
        }

        fecebAccionPropuesta.setFechaHora(rs.getTimestamp(COLUMN_FECHA_HORA));
        fecebAccionPropuesta.setIdEmpleado(rs.getBigDecimal(COLUMN_ID_EMPLEADO));
        FececMotivo fececMotivo = new FececMotivo();
        fececMotivo.setDescripcion(NO_APLICA);
        fecebAccionPropuesta.setNumeroDocumentos(-1);

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_NUMERO_DOCUMENTOS)) {
            fecebAccionPropuesta.setNumeroDocumentos(rs.getInt(COLUMN_NUMERO_DOCUMENTOS));
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_MOTIVO)) {
            fececMotivo.setDescripcion(rs.getString(COLUMN_MOTIVO));
            if (fececMotivo.getDescripcion() == null) {
                fececMotivo.setDescripcion(NO_APLICA);
            }
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_OBSERVACIONES_AUDITOR)) {
            fecebAccionPropuesta.setObservaciones(rs.getString(COLUMN_OBSERVACIONES_AUDITOR));
            if (fecebAccionPropuesta.getObservaciones() == null) {
                fecebAccionPropuesta.setObservaciones(NO_APLICA);
            }
        }
        fecebAccionPropuesta.setFececMotivo(fececMotivo);
        llenarIdTransferencia(rs, fecebAccionPropuesta);
        return fecebAccionPropuesta;
    }

    private void llenarIdTransferencia(ResultSet rs, FecebAccionPropuesta fecebAccionPropuesta) throws SQLException {
        AccionesFuncionarioEnum accionesFuncionarioEnum = AccionesFuncionarioEnum
                .parse(fecebAccionPropuesta.getIdAccion().intValue());
        fecebAccionPropuesta.setAccionFuncionarioEnum(accionesFuncionarioEnum);
        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_RETROALIMENTACION)) {
            fecebAccionPropuesta.setIdRetroalimentacion(fecebAccionPropuesta.getIdDetalleAccion());
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_CANCELACION)) {
            fecebAccionPropuesta.setIdCancelacion(fecebAccionPropuesta.getIdDetalleAccion());
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_RECHAZO_PROPUESTA)) {
            fecebAccionPropuesta.setIdRechazo(fecebAccionPropuesta.getIdDetalleAccion());
        }

        if (UtileriasMapperDao.existeColumna(rs, COLUMN_ID_TRANSFERENCIA)) {
            fecebAccionPropuesta.setIdTransferencia(fecebAccionPropuesta.getIdDetalleAccion());
        }

    }

}
