package mx.gob.sat.siat.feagace.negocio.common.plazos.service;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;

public interface PlazosServiceInsumos {

    void inicializarInsumoConPlazos(List<FecetInsumo> insumo);

    boolean inicializarPlazo(BigDecimal idInsumo);

    void enviaNotificacionesCambioSemaforo();

    void validarReactivacionPlazo(FecetInsumo fecetInsumo);

    void validarSuspensionPlazoReasignar(FecetInsumo fecetInsumo);
    
    void validarSuspensionPlazoRetroalimentar(FecetInsumo fecetInsumo);
    
    
}
