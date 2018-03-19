package mx.gob.sat.siat.feagace.negocio.rules.impl;

import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidarDuplicidadCrearInsumoBO;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

public enum ValidarDuplicidadCrearInsumoRule implements Rule<ValidarDuplicidadCrearInsumoBO> {
    VALIDA_RFC {

        @Override
        public boolean process(ValidarDuplicidadCrearInsumoBO bo) {
            if (bo.getFecetInsumoReferencia().getFecetContribuyente().getRfc().equalsIgnoreCase(bo.getFecetInsumo().getFecetContribuyente().getRfc())) {
                bo.setRule(VALIDA_PERIODO_INICIO);
                return true;
            } else {
                bo.setState(true);
                return false;
            }
        }
    },
    VALIDA_SUBPROGRAMA {

        @Override
        public boolean process(ValidarDuplicidadCrearInsumoBO bo) {
            if (bo.getFecetInsumoReferencia().getFececSubprograma().getIdSubprograma().equals(bo.getFecetInsumo().getFececSubprograma().getIdSubprograma())) {
                bo.setState(false);
                bo.setMensaje("");
            } else {
                bo.setState(true);
            }
            return false;
        }
    },
    VALIDA_PERIODO_INICIO {

        @Override
        public boolean process(ValidarDuplicidadCrearInsumoBO bo) {

            if (bo.getFecetInsumoReferencia().getFechaInicioPeriodo().equals(bo.getFecetInsumo().getFechaInicioPeriodo())) {
                bo.setRule(VALIDA_PERIODO_FIN);
                return true;
            } else {
                bo.setState(true);
                return false;
            }

        }
    },
    VALIDA_PERIODO_FIN {

        @Override
        public boolean process(ValidarDuplicidadCrearInsumoBO bo) {

            if (bo.getFecetInsumoReferencia().getFechaFinPeriodo().equals(bo.getFecetInsumo().getFechaFinPeriodo())) {
                bo.setRule(VALIDA_SUBPROGRAMA);
                return true;
            } else {
                bo.setState(true);
                return false;
            }

        }
    };

}
