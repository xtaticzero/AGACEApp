package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.RespuestaOrdenAcuerdoConclusivoDTO;

public class RespuestaOrdenAcuerdosConclusivosMapper
        implements ParameterizedRowMapper<RespuestaOrdenAcuerdoConclusivoDTO> {

    private static final String COLUMN_ID_ARACE = "ID_ARACE";
    private static final String COLUMN_NUMERO_ORDEN = "NUMERO_ORDEN";
    private static final String COLUMN_RFC = "RFC";
    private static final String COLUMN_NOMBRE = "NOMBRE";
    private static final String COLUMN_ESTATUS = "ESTATUS";
    private static final String COLUMN_VENCIMIENTO = "VENCIMIENTO";
    private static final String COLUMN_TIPO_ORDEN = "TIPO_ORDEN";
    private static final String COLUMN_FECHA_INICIO_PERIODO = "FECHA_INICIO_PERIODO";
    private static final String COLUMN_FECHA_FIN_PERIODO = "FECHA_FIN_PERIODO";
    private static final String COLUMN_ACTIVIDAD_PREPONDERANTE = "ACTIVIDAD_PREPONDERANTE";

    @Override
    public RespuestaOrdenAcuerdoConclusivoDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        RespuestaOrdenAcuerdoConclusivoDTO respuestaOrdenAcuerdoConclusivoDTO = new RespuestaOrdenAcuerdoConclusivoDTO();
        respuestaOrdenAcuerdoConclusivoDTO.setIdUnidadAdministrativa(resultSet.getInt(COLUMN_ID_ARACE));
        respuestaOrdenAcuerdoConclusivoDTO.setNumeroOrden(resultSet.getString(COLUMN_NUMERO_ORDEN));
        respuestaOrdenAcuerdoConclusivoDTO.setRfcContribuyente(resultSet.getString(COLUMN_RFC));
        respuestaOrdenAcuerdoConclusivoDTO.setNombreContribuyente(resultSet.getString(COLUMN_NOMBRE));
        respuestaOrdenAcuerdoConclusivoDTO.setEstatus(resultSet.getString(COLUMN_ESTATUS));
        respuestaOrdenAcuerdoConclusivoDTO.setVencimiento(resultSet.getDate(COLUMN_VENCIMIENTO));
        respuestaOrdenAcuerdoConclusivoDTO.setTipoOrden(resultSet.getString(COLUMN_TIPO_ORDEN));
        respuestaOrdenAcuerdoConclusivoDTO.setEjercicioInicio(resultSet.getDate(COLUMN_FECHA_INICIO_PERIODO));
        respuestaOrdenAcuerdoConclusivoDTO.setEjercicioFin(resultSet.getDate(COLUMN_FECHA_FIN_PERIODO));
        respuestaOrdenAcuerdoConclusivoDTO.setActividadPreponderante(resultSet.getString(COLUMN_ACTIVIDAD_PREPONDERANTE));
        return respuestaOrdenAcuerdoConclusivoDTO;
    }

}
