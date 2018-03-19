package mx.gob.sat.siat.feagace.negocio.reportes;

import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececUnidadAdministrativa;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.GraficaValoresDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteInsumosDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReporteOrdenesDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportePropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.dto.reportes.ReportesVO;


public interface GenerarReportesService {
    List<ReporteInsumosDTO> generarReporteInsumos(ReportesVO vo);
    List<GraficaValoresDTO> graficaInsumosCero(ReportesVO vo);
    List<GraficaValoresDTO> graficaInsumosUno(ReportesVO vo);
    List<GraficaValoresDTO> graficaInsumosDos(ReportesVO vo);
    List<GraficaValoresDTO> graficaInsumosTres(ReportesVO vo);
    List<ReportePropuestasDTO> generarReportePropuestas(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion, List<FececUnidadAdministrativa> lstUnidades);
    List<GraficaValoresDTO> graficaPropuestasCero(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaPropuestasUno(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaPropuestasDos(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaPropuestasTres(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<ReporteOrdenesDTO> generarReporteOrdenes(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaOrdenesCero(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaOrdenesUno(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaOrdenesDos(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
    List<GraficaValoresDTO> graficaOrdenesTres(ReportesVO vo,EmpleadoDTO empleado,List<EmpleadoDTO> listaEmpleadosCreacion);
}
