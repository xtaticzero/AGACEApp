package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ContadorPropuestasAsignadas;

public class ContadorPropAsignadasMapper implements ParameterizedRowMapper<ContadorPropuestasAsignadas> {

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_ASIGNADAS_FIRMANTE = "TOTAL_ASIGNADAS_FIRMANTE";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_RETROALIMENTADAS_ATENDER = "TOTAL_RETROALIMENTADAS_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_FIRMANTE_ATENDER = "TOTAL_FIRMANTE_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_EMISION_ATENDER = "TOTAL_EMISION_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_CANCELADAS_ATENDER = "TOTAL_CANCELADAS_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_TRANSFERIDAS_ATENDER = "TOTAL_TRANSFERIDAS_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_RECHAZADAS_ATENDER = "TOTAL_RECHAZADAS_ATENDER";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_RETRO_VALIDACION = "TOTAL_RETRO_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_FIRMANTE_VALIDACION = "TOTAL_FIRMANTE_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_EMISION_VALIDACION = "TOTAL_EMISION_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_CANCELADAS_VALIDACION = "TOTAL_CANCELADAS_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_TRANSFERIDAS_VALIDACION = "TOTAL_TRANSFERIDAS_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_RECHAZADAS_VALIDACION = "TOTAL_RECHAZADAS_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_PROGRAMACION_VALIDACION = "TOTAL_PROGRAMACION_VALIDACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_CONCLUIDAS_CANCELACION = "TOTAL_CONCLUIDAS_CANCELACION";

    /**
     * Este atributo corresponde al nombre de la columna
     * TOTAL_ASIGNADAS_FIRMANTE en el Contador de Propuestas
     */
    private static final String COLUMN_TOTAL_CONCLUIDAS_RECHAZO = "TOTAL_CONCLUIDAS_RECHAZO";

    public ContadorPropuestasAsignadas mapRow(ResultSet rs, int numRow) throws SQLException {

        ContadorPropuestasAsignadas dto = new ContadorPropuestasAsignadas();

        dto.setTotalAsignadasFirmante(rs.getLong(COLUMN_TOTAL_ASIGNADAS_FIRMANTE));
        dto.setTotalRetroAtender(rs.getLong(COLUMN_TOTAL_RETROALIMENTADAS_ATENDER));
        dto.setTotalFirmanteAtender(rs.getLong(COLUMN_TOTAL_FIRMANTE_ATENDER));
        dto.setTotalEmisionAtender(rs.getLong(COLUMN_TOTAL_EMISION_ATENDER));
        dto.setTotalCanceladasAtender(rs.getLong(COLUMN_TOTAL_CANCELADAS_ATENDER));
        dto.setTotalTransferidasAtender(rs.getLong(COLUMN_TOTAL_TRANSFERIDAS_ATENDER));
        dto.setTotalRechazadasAtender(rs.getLong(COLUMN_TOTAL_RECHAZADAS_ATENDER));

        dto.setTotalRetroValidacion(rs.getLong(COLUMN_TOTAL_RETRO_VALIDACION));
        dto.setTotalFirmanteValidacion(rs.getLong(COLUMN_TOTAL_FIRMANTE_VALIDACION));
        dto.setTotalEmisionValidacion(rs.getLong(COLUMN_TOTAL_EMISION_VALIDACION));
        dto.setTotalCancelacionValidacion(rs.getLong(COLUMN_TOTAL_CANCELADAS_VALIDACION));
        dto.setTotalTransferenciaValidacion(rs.getLong(COLUMN_TOTAL_TRANSFERIDAS_VALIDACION));
        dto.setTotalRechazoValidacion(rs.getLong(COLUMN_TOTAL_RECHAZADAS_VALIDACION));

        dto.setTotalProgramacionValidacion(rs.getLong(COLUMN_TOTAL_PROGRAMACION_VALIDACION));

        dto.setTotalConcluidasCancelacion(rs.getLong(COLUMN_TOTAL_CONCLUIDAS_CANCELACION));
        dto.setTotalConcluidasRechazo(rs.getLong(COLUMN_TOTAL_CONCLUIDAS_RECHAZO));

        return dto;
    }

}
