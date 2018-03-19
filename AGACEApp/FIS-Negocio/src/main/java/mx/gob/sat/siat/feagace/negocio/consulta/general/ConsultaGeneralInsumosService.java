package mx.gob.sat.siat.feagace.negocio.consulta.general;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;

public interface ConsultaGeneralInsumosService {

    List<FecetInsumo> obtenerDetalleInsumos(BigDecimal idUnidadAdmonRegistro, BigDecimal idUnidadAdmonRecepcion, int tipoConsulta);

    List<FecetInsumo> obtenerDetalleInsumosAdmon(List<EmpleadoDTO> empleados, TipoEstatusEnum estatus, String condicion, int tipoConsulta);

    List<FecetInsumo> obtenerDetalleInsumosSubAdmon(EmpleadoDTO admon, List<EmpleadoDTO> empleados, TipoEstatusEnum estatus, String condicion, int tipoConsulta);

    Map<TipoEstatusEnum, Integer> obtenerResumenInsumos(List<FecetInsumo> insumos);

    Map<EmpleadoDTO, Integer> obtenerResumenInsumosAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos);

    Map<EmpleadoDTO, Integer> obtenerResumenInsumosSubAdmon(List<EmpleadoDTO> empleados, List<FecetInsumo> insumos);

    Map<SemaforoEnum, Integer> obtenerResumenInsumosSemaforos(List<FecetInsumo> insumos);

    List<FecetInsumo> filtrarXEstatus(List<FecetInsumo> insumos, TipoEstatusEnum estatus);

    List<FecetInsumo> filtrarXSemaforo(List<FecetInsumo> insumos, SemaforoEnum semaforo);

    List<FecetInsumo> filtroPlazoParaConcluir(List<FecetInsumo> insumos, Integer plazoXConcluir);

    List<EmpleadoDTO> obtenerAdmonXUnidadAdmon(BigDecimal idUnidadAmon);

    List<EmpleadoDTO> obtenerSubAdmonXUnidadAdmon(BigDecimal idUnidadAmon);

    Map<TipoEstatusEnum, Integer> obtenerResumenInsumosAsignados(List<FecetInsumo> insumos);

    List<EmpleadoDTO> obtenerSubAdmin(EmpleadoDTO empleado);

    void consultaHistoricoInsumos(FecetInsumo insumoAConsultar);

}
