package mx.gob.sat.siat.feagace.negocio.rules.impl;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class ValidarMetodoPruebasPericialesRule extends BaseBusinessRules {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public boolean validaMetodo(AgaceOrden orden) {
        return orden.getFeceaMetodo().getIdMetodo().equals(Constantes.REE);
    }

}
