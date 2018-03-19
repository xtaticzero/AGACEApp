package mx.gob.sat.siat.feagace.modelo.dao.reportes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

public interface ReportesInsumosDao {

    List<ReporteInsumosDTO> findReporteInsumosGerencial(ReportesVO vo);

    List<GraficaValoresDTO> graficaInsumosCero(ReportesVO vo);

    List<GraficaValoresDTO> graficaInsumosUno(ReportesVO vo);

    List<GraficaValoresDTO> graficaInsumosDos(ReportesVO vo);

    List<GraficaValoresDTO> graficaInsumosTres(ReportesVO vo);
}
