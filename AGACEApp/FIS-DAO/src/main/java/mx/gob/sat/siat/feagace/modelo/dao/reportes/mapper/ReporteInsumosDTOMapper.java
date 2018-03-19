package mx.gob.sat.siat.feagace.modelo.dao.reportes.mapper;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.VerificarContribuyenteReporteDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ReporteInsumosDTOMapper implements ParameterizedRowMapper<ReporteInsumosDTO> {

    /**
     * Metodo mapRow Hace un mapeo y un set con los datos de las tablas
     * FECET_INSUMO,FECET_CONTRIBUYENTE, FECEC_ESTATUS, FECET_RECHAZO_INSUMO,
     * FECEC_SECTOR, FECEC_SUBPROGRAMA
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public ReporteInsumosDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReporteInsumosDTO dto = new ReporteInsumosDTO();
        VerificarContribuyenteReporteDTO contribuyente = new VerificarContribuyenteReporteDTO();
        dto.setIdRegistro(rs.getString("ID_REGISTRO"));
        dto.setFechaRegistro(rs.getDate("FECHA_REGISTRO") != null ? rs.getDate("FECHA_REGISTRO") : null);
        dto.setFechaPeriodoPropuestoInicio(rs.getDate("FECHA_INICIO_PERIODO") != null ? rs.getDate("FECHA_INICIO_PERIODO") : null);
        dto.setFechaPeriodoPropuestoFin(rs.getDate("FECHA_FIN_PERIODO") != null ? rs.getDate("FECHA_FIN_PERIODO") : null);
        dto.setRfcContribuyente(rs.getString("RFC"));
        dto.setRazonSocial(rs.getString("NOMBRE"));
        dto.setEntidad(rs.getString("ENTIDAD"));
        dto.setEstatus(rs.getString("ESTATUS"));
        dto.setFechaAprobacion(null);
        dto.setFechaRechazo(rs.getDate("FECHA_RECHAZO"));
        dto.setMotivoRechazo(rs.getString("MOTIVO_RECHAZO"));
        dto.setSector(rs.getString("SECTOR"));
        dto.setSubprograma(rs.getString("SUBPROGRAMA"));
        dto.setActividadPrepoderante(rs.getString("ACTIVIDAD_PREPONDERANTE"));
        if (UtileriasMapperDao.existeColumna(rs, "INSUMO_APROBADO")) {
            dto.setFechaAprobacion(rs.getDate("INSUMO_APROBADO"));
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
            contribuyente.setEstatus("PPEE");
            contribuyente.setPpee(true);
        } else {
            contribuyente.setPpee(false);
        }
        dto.setContribuyente(contribuyente);
        return dto;
    }
}
