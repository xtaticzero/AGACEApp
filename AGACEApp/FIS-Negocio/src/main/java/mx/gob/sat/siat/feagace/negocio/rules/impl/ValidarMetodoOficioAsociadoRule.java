package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class ValidarMetodoOficioAsociadoRule extends BaseBusinessRules {

    private static final long serialVersionUID = 1L;

    public boolean validaMetodoOficioDocNoReq(AgaceOrden orden, BigDecimal idTipoOficio) {
        if (orden.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))) {
            return validaORG(idTipoOficio);
        } else if (orden.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.REE.getId()))) {
            return validaREE(idTipoOficio);
        } else if (orden.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.EHO.getId()))) {
            return validaEHO(idTipoOficio);
        } else if ((orden.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.UCA.getId())))) {
            return validaUCA(idTipoOficio);
        } else if ((orden.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.MCA.getId())))) {
            return validaMCA(idTipoOficio);
        }
        return false;
    }

    private boolean validaORG(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { validaORGParteUno(idTipoOficio), validaORGParteDos(idTipoOficio) };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaORGParteUno(BigDecimal idTipoOficio) {

        boolean[] condicionesAEvaluar =
            new boolean[] {idTipoOficio.equals(TiposOficiosOrdenesEnum.CONCLUSION_REVISION_ESCRITOS_IMP.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.SIN_OBSERVACIONES.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.CANCELACION_ORDEN.getBigIdTipoOficio()) };
        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaORGParteDos(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(TiposOficiosOrdenesEnum.SUSPENSION_EN_PADRON_IMP_EXP.getBigIdTipoOficio()), 
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.CONCLUSION.getBigIdTipoOficio()), 
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())};

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaREE(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio()),                            
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                            idTipoOficio.equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio())};

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaEHO(BigDecimal idTipoOficio) {

        boolean[] condicionesAEvaluar =
            new boolean[] { idTipoOficio.equals(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.PRUEBAS_DESAHOGO.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio()),
                    idTipoOficio.equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())};

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaUCA(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
                new boolean[] { idTipoOficio.equals(TiposOficiosOrdenesEnum.CONCLUSION.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.CAMBIO_METODO.getBigIdTipoOficio())};

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaMCA(BigDecimal idTipoOficio) {
        boolean[] condicionesAEvaluar =
                new boolean[] { idTipoOficio.equals(TiposOficiosOrdenesEnum.CONCLUSION.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.MEDIDAS_DE_APREMIO.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                        idTipoOficio.equals(TiposOficiosOrdenesEnum.CAMBIO_METODO.getBigIdTipoOficio())};
        
        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }
}
