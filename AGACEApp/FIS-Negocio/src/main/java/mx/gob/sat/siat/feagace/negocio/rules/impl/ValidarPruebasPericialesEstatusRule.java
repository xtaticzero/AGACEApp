package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.pruebaspericiales.EstatusPruebasPericiales;

@Component
public class ValidarPruebasPericialesEstatusRule extends BaseBusinessRules {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public boolean validarDocResPruebasPericiales(FecetPruebasPericiales pruebasPericiales) {
        return pruebasPericiales.getRutaResolucion() != null &&
               !pruebasPericiales.getFececEstatus().getIdEstatus().equals(EstatusPruebasPericiales.PRUEBAS_PERICIALES_PENDIENTE_APROBACION.getBigIdEstatus()) ?
               true : false;
    }
    
    public boolean validarGenerarPruebasPericiales(List<FecetPruebasPericiales> pruebasPericiales) {
        for (FecetPruebasPericiales pruebaPericial : pruebasPericiales) {
            if (pruebaPericial.getFececEstatus().getIdEstatus().equals(EstatusPruebasPericiales.PRUEBAS_PERICIALES_PENDIENTE_APROBACION.getBigIdEstatus()) ||
                    pruebaPericial.getAprobada()) {
                return false;
            }
        }
        return true;
    }

}
