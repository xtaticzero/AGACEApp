package mx.gob.sat.siat.feagace.negocio.propuestas.rules;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;

import org.springframework.stereotype.Component;

@Component
public class CargaDocumentoElectronicoRules extends BaseBusinessRules {

    private static final long serialVersionUID = -1830334561255287514L;

    public boolean validarPermiteBusquedaAgenteAduanal(FecetPropuesta propuesta) {
        return ("EHO".equals(propuesta.getFeceaMetodo().getAbreviatura()));

    }
}
