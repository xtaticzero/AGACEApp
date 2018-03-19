package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.ValidarRegistrosException;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class ConsultarReimprimirDocumentosRules extends BaseBusinessRules {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public List<AgaceOrden> validarRegistroOrdenes(final List<AgaceOrden> listAgaceOrden) throws ValidarRegistrosException {
        if (validarRegistroOrdenesEmpty(listAgaceOrden)) {
            throw new ValidarRegistrosException("inexistencia.registros");
        }
        return listAgaceOrden;
    }

    public boolean validarRegistroOrdenesEmpty(final List<AgaceOrden> listAgaceOrden){
      return listAgaceOrden == null || listAgaceOrden.isEmpty();
  }
    
    
    public boolean validaMetodo(BigDecimal idMetodo, BigDecimal idTipoOficio) {
        if (idMetodo.equals(Constantes.ORG)) {
            return validaORG(idTipoOficio);
        } else if (idMetodo.equals(Constantes.REE)) {
            return validaREE(idTipoOficio);
        } else if (idMetodo.equals(Constantes.EHO)) {
            return validaEHO(idTipoOficio);
        } else if ((idMetodo.equals(Constantes.UCA))) {
            return validaUCA(idTipoOficio);
        } else if ((idMetodo.equals(Constantes.MCA))) {
            return validaMCA(idTipoOficio);
        }
        return false;
    }

    private boolean validaORG(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { validaORGParteUno(idTipoOficio), validaORGParteDos(idTipoOficio),
                            idTipoOficio.equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL),
                            idTipoOficio.equals(Constantes.OFICIO_AVISO_CIRCUNSTANCIAL),
                            };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaORGParteUno(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_CONCLUSION_REVISION_ESCRITOS),
                            idTipoOficio.equals(Constantes.OFICIO_CANCELACION_ORDEN),
                            idTipoOficio.equals(Constantes.OFICIO_SUSPENSION_PADRON) };
        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaORGParteDos(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_RESOLUCION_DEFINITIVA), idTipoOficio.equals(Constantes.OFICIO_SIN_OBSERVACIONES),
                            
                            idTipoOficio.equals(Constantes.OFICIO_AVISO_CONTRIBUYENTE) };
        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaREE(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_RESOLUCION_DEFINITIVA), idTipoOficio.equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL),                            
                            idTipoOficio.equals(Constantes.OFICIO_AVISO_CONTRIBUYENTE),
                            idTipoOficio.equals(Constantes.OFICIO_AVISO_CIRCUNSTANCIAL)};
        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaEHO(BigDecimal idTipoOficio) {

        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_RESOLUCION_DEFINITIVA), idTipoOficio.equals(Constantes.OFICIO_PRUEBAS_DESAHOGO),
                            idTipoOficio.equals(Constantes.OFICIO_AVISO_CONTRIBUYENTE),
                            idTipoOficio.equals(Constantes.OFICIO_COMPULSA_INTERNACIONAL),
                            };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaUCA(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_CONCLUSION), idTipoOficio.equals(Constantes.OFICIO_OTRAS_AUTORIDADES) };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaMCA(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(Constantes.OFICIO_CONCLUSION)};

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

}
