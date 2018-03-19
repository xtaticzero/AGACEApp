package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.VerificarContribuyenteReporteDTO;

public class ReportePropuestasMapper implements ParameterizedRowMapper<ReportePropuestasDTO> {

    public ReportePropuestasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReportePropuestasDTO dto = new ReportePropuestasDTO();
        VerificarContribuyenteReporteDTO contribuyente = new VerificarContribuyenteReporteDTO();
        dto.setRegistroId(rs.getString("ID_REGISTRO"));
        dto.setFechaInicioPeriodo(rs.getDate("FECHA_INICIO_PERIODO"));
        dto.setFechaFinPeriodo(rs.getDate("FECHA_FIN_PERIODO"));
        dto.setRfcContribuyente(rs.getString("RFC"));
        dto.setRazonSocial(rs.getString("RAZON_SOCIAL"));
        dto.setMetodo(rs.getString("ABREVIATURA_METODO") + " - " + rs.getString("NOMBRE_METODO"));
        dto.setEstatus(rs.getString("NOMBRE_ESTATUS"));
        dto.setPresuntiva(rs.getBigDecimal("PREPODERANTE"));
        dto.setTipoRevision(rs.getString("TIPO_REVISION"));
        dto.setRegimen(rs.getString("REGIMEN"));
        dto.setSubprograma(rs.getString("NOMBRE_SUBPROGRAMA"));
        dto.setSector(rs.getString("NOMBRE_SECTOR"));
        dto.setActividadPrepoderante(rs.getString("ACTIVIDAD_PREPONDERANTE"));
        dto.setCausaProgramacion(rs.getString("CAUSA_PROGRAMACION"));

        if (UtileriasMapperDao.existeColumna(rs, "NOMBRE_FIRMANTE")) {
            dto.setFirmante(rs.getString("NOMBRE_FIRMANTE") != null ? rs.getString("NOMBRE_FIRMANTE") : "");
        }

        if (UtileriasMapperDao.existeColumna(rs, "NOMBRE_AUDITOR")) {
            dto.setAuditor(rs.getString("NOMBRE_AUDITOR") != null ? rs.getString("NOMBRE_AUDITOR") : "");
        }

        if (UtileriasMapperDao.existeColumna(rs, "UNIDAD_ADMINISTRATIVA")) {
            dto.setUnidadAdministrativa(rs.getString("UNIDAD_ADMINISTRATIVA"));
        }

        if (UtileriasMapperDao.existeColumna(rs, "RFC_FIRMANTE")) {
            dto.setRfcFirmante(rs.getString("RFC_FIRMANTE") != null ? rs.getString("RFC_FIRMANTE") : "");
        }

        if (UtileriasMapperDao.existeColumna(rs, "RFC_AUDITOR")) {
            dto.setRfcAuditor(rs.getString("RFC_AUDITOR") != null ? rs.getString("RFC_AUDITOR") : "");
        }

        if (UtileriasMapperDao.existeColumna(rs, "ID_ARACE")) {
            dto.setIdArace(rs.getBigDecimal("ID_ARACE"));
        }

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
