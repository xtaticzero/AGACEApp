package mx.gob.sat.siat.feagace.modelo.dao.reportes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;

public interface ReportesOrdenesDao {

    List<ReporteOrdenesDTO> findReporteOrdenesGerencial(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaOrdenesCero(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaOrdenesUno(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaOrdenesDos(ReportesVO vo, String condicion);

    List<GraficaValoresDTO> graficaOrdenesTres(ReportesVO vo, String condicion);
}
