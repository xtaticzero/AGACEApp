package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.VerificarContribuyenteReporteDTO;

public class ReporteOrdenesMapper implements ParameterizedRowMapper<ReporteOrdenesDTO> {

    @Override
    public ReporteOrdenesDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReporteOrdenesDTO dto = new ReporteOrdenesDTO();
        VerificarContribuyenteReporteDTO contribuyente = new VerificarContribuyenteReporteDTO();

        dto.setFechaInicioPeriodo(rs.getDate("FECHA_INICIO_PERIODO") != null ? rs.getDate("FECHA_INICIO_PERIODO") : null);
        dto.setFechaFinPeriodo(rs.getDate("FECHA_FIN_PERIODO") != null ? rs.getDate("FECHA_FIN_PERIODO") : null);
        dto.setNumeroOrden(rs.getString("NUMERO_ORDEN"));
        dto.setFechaRegistro(rs.getDate("FECHA_CREACION") != null ? rs.getDate("FECHA_CREACION") : null);
        dto.setMetodo(rs.getString("NOMBRE_METODO"));
        dto.setRfcContribuyente(rs.getString("RFC"));
        dto.setRazonSocial(rs.getString("RAZON_SOCIAL"));
        dto.setIdUnidadAdministrativa(rs.getBigDecimal("ID_UNIDAD_ADMINISTRATIVA"));
        dto.setEstatus(rs.getString("NOMBRE_ESTATUS"));
        dto.setIdAuditor(rs.getBigDecimal("ID_AUDITOR") != null ? rs.getBigDecimal("ID_AUDITOR") : null);
        dto.setIdFirmante(rs.getBigDecimal("ID_FIRMANTE") != null ? rs.getBigDecimal("ID_FIRMANTE") : null);
        dto.setFechaFirma(rs.getDate("FECHA_FIRMA") != null ? rs.getDate("FECHA_FIRMA") : null);
        dto.setFechaEnvioNotificacion(rs.getDate("FECHA_ENVIO_NOT") != null ? rs.getDate("FECHA_ENVIO_NOT") : null);
        dto.setSubprograma(rs.getString("NOMBRE_SUBPROGRAMA"));
        dto.setSector(rs.getString("NOMBRE_SECTOR"));
        dto.setActividadPrepoderante(rs.getString("ACTIVIDAD_PREPONDERANTE"));
        contribuyente.setRfcConsulta(rs.getString("RFC_CONTRIBUYENTE") != null ? rs.getString("RFC_CONTRIBUYENTE") : null);
        contribuyente.setFechaConsulta(rs.getDate("FECHA_CONSULTA") != null ? rs.getDate("FECHA_CONSULTA") : null);
        contribuyente.setEstatus(null);
        if (rs.getBigDecimal("AMPARADO") != null && rs.getBigDecimal("AMPARADO").compareTo(BigDecimal.ZERO) > 0) {
            contribuyente.setAmparado(true);
            contribuyente.setEstatus("Amparado");
        } else {
            contribuyente.setAmparado(false);
        }
        if (rs.getBigDecimal("PPEE") != null && rs.getBigDecimal("PPEE").compareTo(BigDecimal.ZERO) > 0) {
            contribuyente.setPpee(true);
            contribuyente.setEstatus("PPEE");
        } else {
            contribuyente.setPpee(false);
        }
        dto.setContribuyente(contribuyente);
        return dto;
    }
}
