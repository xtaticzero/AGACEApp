package mx.gob.sat.siat.feagace.modelo.dao.common;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;

public interface ReportesDao {

    List<ReporteEjecutivoDTO> consultaReporteEjecutivo(Date rangoInicio, Date rangoFin, BigDecimal idMetodo,
            BigDecimal idArea, BigDecimal idEstatus);

    List<ReporteGerencialDTO> consultaReporteGerencial(BigDecimal metodo, BigDecimal status,
            BigDecimal area);

    List<ReporteGerencialDTO> consultaReporteGerencialOrden(String cveOrden);

}
