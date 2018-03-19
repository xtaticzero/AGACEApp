package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.feagace.modelo.dto.common.FecetDetalleContribuyente;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.bo.base.impl.ValidaMediosContactoBO;
import mx.gob.sat.siat.feagace.negocio.rules.Rule;

public enum ValidaMediosContactoRule implements Rule<ValidaMediosContactoBO> {

    CUENTA_MEDIO_CONTACTO {
        public boolean process(ValidaMediosContactoBO validaMediosContactoBO) {
            if(validaMediosContactoBO.isValidaSoloMediosContacto()) {
                validaMediosContactoBO.setRule(CONSULTA_SOLO_MEDIO_CONTACTO);
                return true;
            }
            if (validaMediosContactoBO.getMediosComunicacion() == null
                    || validaMediosContactoBO.getMediosComunicacion().size() < 1) {
                validaMediosContactoBO.setRule(RFC_NO_MEDIOS_CONTACTO);

            } else {
                if (validaMediosContactoBO.getMediosComunicacion().get(0)
                        .getTipoMedio() == 0) {
                    validaMediosContactoBO.setRule(RFC_NO_MEDIOS_CONTACTO);
                } else {
                    validaMediosContactoBO.setRule(VALIDA_AMPARADO);
                }

            }
            return true;
        }
    },
    RFC_NO_MEDIOS_CONTACTO {
        public boolean process(ValidaMediosContactoBO validaMediosContactoBO) {
            validaMediosContactoBO
                    .setMessage(Constantes.MESANJE_NO_MEDIOS_CONTACTO);
            validaMediosContactoBO.setFlag(false);
            return false;
        }
    },
    VALIDA_AMPARADO {
        public boolean process(ValidaMediosContactoBO validaMediosContactoBO) {
            if (validaMediosContactoBO.getMediosComunicacion().get(0)
                    .getAmparado() == 0) {
                validaMediosContactoBO.setAmparado(false);
            } else if (validaMediosContactoBO.getMediosComunicacion().get(0)
                    .getAmparado() == 1) {
                validaMediosContactoBO.setAmparado(true);
            }
            validaMediosContactoBO.setRule(PROCESA_RESULTADO);
            return true;
        }
    },
    CONSULTA_SOLO_MEDIO_CONTACTO {
        public boolean process(ValidaMediosContactoBO validaMediosContactoBO) {
            if (validaMediosContactoBO.getMediosComunicacion() == null
                    || validaMediosContactoBO.getMediosComunicacion().size() < 1) {
                validaMediosContactoBO.setRule(RFC_NO_MEDIOS_CONTACTO);

            } else {
                if (validaMediosContactoBO.getMediosComunicacion().get(0)
                        .getTipoMedio() == 0) {
                    validaMediosContactoBO.setRule(RFC_NO_MEDIOS_CONTACTO);
                } else {
                    validaMediosContactoBO.setFlag(true);
                    return false;                    
                }
            }
            return true;
        }
    },
    
    
    PROCESA_RESULTADO {
        public boolean process(ValidaMediosContactoBO validaMediosContactoBO) {
            FecetDetalleContribuyente fecetDetalleContribuyente = new FecetDetalleContribuyente();
            fecetDetalleContribuyente
                    .setRfcContribuyente(validaMediosContactoBO.getRfc());
            fecetDetalleContribuyente.setFechaConsulta(new Date());
            if (validaMediosContactoBO.isAmparado()
                    && validaMediosContactoBO.isPPEE()) {
                validaMediosContactoBO.setFlag(false);
                fecetDetalleContribuyente.setAmparado(BigDecimal.ONE);
                fecetDetalleContribuyente.setPpee(BigDecimal.ONE);
                validaMediosContactoBO
                        .setMessage(Constantes.MENSAJE_CONTRIBUYENTE_PPEE_AMPARADO);
            } else if (validaMediosContactoBO.isAmparado()) {
                validaMediosContactoBO.setFlag(false);
                validaMediosContactoBO
                        .setMessage(Constantes.MESANJE_CONTRIBUYENTE_AMPARADO);
                fecetDetalleContribuyente.setAmparado(BigDecimal.ONE);
                fecetDetalleContribuyente.setPpee(BigDecimal.ZERO);
            } else if (validaMediosContactoBO.isPPEE()) {
                validaMediosContactoBO.setFlag(false);
                validaMediosContactoBO
                        .setMessage(Constantes.MENSAJE_CONTRIBUYENTE_PPEE);
                fecetDetalleContribuyente.setAmparado(BigDecimal.ZERO);
                fecetDetalleContribuyente.setPpee(BigDecimal.ONE);
            } else {
                validaMediosContactoBO.setFlag(true);
                fecetDetalleContribuyente.setAmparado(BigDecimal.ZERO);
                fecetDetalleContribuyente.setPpee(BigDecimal.ZERO);
            }
            FecetDetalleContribuyente detalleContribuyente;

            detalleContribuyente = validaMediosContactoBO
                    .getFecetDetalleContribuyenteDao().findByRfc(
                            validaMediosContactoBO.getRfc());
            if (detalleContribuyente == null) {
                validaMediosContactoBO.getFecetDetalleContribuyenteDao()
                        .insert(fecetDetalleContribuyente);
            } else {
                fecetDetalleContribuyente
                        .setIdDetalleContribuyente(detalleContribuyente
                                .getIdDetalleContribuyente());
                validaMediosContactoBO.getFecetDetalleContribuyenteDao()
                        .update(fecetDetalleContribuyente);
            }

            return false;
        }
    };
}
