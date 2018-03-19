package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ReporteGerencialMapper implements ParameterizedRowMapper<ReporteGerencialDTO> {

    @Override
    public ReporteGerencialDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        ReporteGerencialDTO reporteGerencialDTO = new ReporteGerencialDTO();
        reporteGerencialDTO.setCveOrden(resultSet.getString("CVE_ORDEN"));
        reporteGerencialDTO.setCveRegistro(resultSet.getString("CVE_REGISTRO"));
        reporteGerencialDTO.setMetodo(resultSet.getString("METODO"));
        reporteGerencialDTO.setRfcContribuyente(resultSet.getString("RFC_CONTRIBUYENTE"));
        reporteGerencialDTO.setNombrecontribuyente(resultSet.getString("NOMBRE_CONTRIBUYENTE"));
        reporteGerencialDTO.setRfcRepresentanteLegal(resultSet.getString("RFC_REP_LEGAL"));
        reporteGerencialDTO.setNombreRepresentanteLegal(resultSet.getString("NOMBRE_REP_LEGAL"));
        reporteGerencialDTO.setRfcApoderadoLegal(resultSet.getString("RFC_APOD_LEGAL"));
        reporteGerencialDTO.setNombreApoderadoLegal(resultSet.getString("NOMBRE_APOD_LEGAL"));
        reporteGerencialDTO.setRfcAgenteAduanal(resultSet.getString("RFC_AGTE_ADUANAL"));
        reporteGerencialDTO.setNombreAgenteAduanal(resultSet.getString("NOMBRE_AGTE_ADUANAL"));
        reporteGerencialDTO.setNombreAuditor(resultSet.getString("AREA"));
        reporteGerencialDTO.setNombreAuditor(resultSet.getString("NOMBRE_AUDITOR"));
        reporteGerencialDTO.setIdEstatus(resultSet.getBigDecimal("ID_ESTATUS"));
        reporteGerencialDTO.setIdFecetRequerimiento(resultSet.getBigDecimal("ID_REQUERIMIENTO"));
        reporteGerencialDTO.setIdFecetAmplPlazo(resultSet.getBigDecimal("ID_AMPL_PLAZO"));
        reporteGerencialDTO.setIdFecetCartaInv(resultSet.getBigDecimal("ID_CARTA_INV"));
        reporteGerencialDTO.setIdFecetComplement(resultSet.getBigDecimal("ID_COMPLEMENT"));
        reporteGerencialDTO.setIdFecetObserv(resultSet.getBigDecimal("ID_OBSERV"));
        reporteGerencialDTO.setIdFecetConclusion(resultSet.getBigDecimal("ID_CONCLUSION"));
        reporteGerencialDTO.setFechaRegistro(resultSet.getDate("FECHA_CARGA"));
        reporteGerencialDTO.setFechaEnvioNotificacion(resultSet.getDate("FECHA_NOTIF_NYV"));
        reporteGerencialDTO.setFechaEnvio(resultSet.getDate("FECHA_NOTIF_CONT"));
        reporteGerencialDTO.setIdOrden(resultSet.getBigDecimal("ID_ORDEN"));
        return reporteGerencialDTO;
    }
}
