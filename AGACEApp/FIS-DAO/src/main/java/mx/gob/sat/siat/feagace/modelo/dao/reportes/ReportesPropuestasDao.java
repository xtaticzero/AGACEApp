package mx.gob.sat.siat.feagace.modelo.dao.reportes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

public interface ReportesPropuestasDao {

    List<ReportePropuestasDTO> findReportePropuestasGerencial(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaPropuestasCero(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaPropuestasUno(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaPropuestasDos(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaPropuestasTres(ReportesVO vo, String condicion);
}
