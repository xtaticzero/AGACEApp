package mx.gob.sat.siat.feagace.negocio.propuestas;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececArace;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteEjecutivoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.common.ReporteGerencialDTO;


public interface ReportesService {

    /**
     * Regresa metodos de ordenes
     *
     * @return List<FeceaMetodo>
     */
    List<FececMetodo> getMetodos();

    List<FececArace> getAreas();

    List<ReporteEjecutivoDTO> consultaReporteEjecutivo(Date rangoInicio, Date rangoFin, BigDecimal idMetodo,
            BigDecimal idArea,
            BigDecimal idEstatus);

    List<ReporteGerencialDTO> consultaReporteGerencial(BigDecimal metodo, BigDecimal status,
            BigDecimal area);

    List<ReporteGerencialDTO> consultaReporteGerencial(String cveOrden);

}
