package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.math.BigDecimal;
import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import org.springframework.stereotype.Component;

@Component
public class ValidarVistaAsociadoRule extends BaseBusinessRules {


    @SuppressWarnings("compatibility:278665156022588992")
    private static final long serialVersionUID = 1L;

    public boolean validaVistaApoderadoLegal(BigDecimal idTipoAsociado) {
        return (idTipoAsociado.equals(Constantes.ID_CONTRIBUYENTE) ||
                idTipoAsociado.equals(Constantes.ID_REPRESENTANTE_LEGAL));
    }
    
    public boolean validaVistaRepresentanteLegal(BigDecimal idTipoAsociado) {
        return (idTipoAsociado.equals(Constantes.ID_CONTRIBUYENTE));
    }
    
    public boolean validaVistaApoderadoLegalRL(BigDecimal idTipoAsociado) {
        return (idTipoAsociado.equals(Constantes.ID_CONTRIBUYENTE) ||
                idTipoAsociado.equals(Constantes.ID_REPRESENTANTE_LEGAL));
    }
    
    public boolean validaVistaAgenteAduanual(BigDecimal idTipoAsociado) {
        return (idTipoAsociado.equals(Constantes.ID_CONTRIBUYENTE) ||
                idTipoAsociado.equals(Constantes.ID_REPRESENTANTE_LEGAL));
    }
}
