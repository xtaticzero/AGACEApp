package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.List;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

import org.springframework.stereotype.Component;


@Component
public class ValidarProrrogasEstatusRule extends BaseBusinessRules {

    private static final long serialVersionUID = 5038974135420952477L;

    public boolean validarGenerarProrroga(List<FecetProrrogaOrden> prorrogas) {
        for (FecetProrrogaOrden prorroga : prorrogas) {
            if (prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus()) ||
                prorroga.getAprobada()) {
                return false;
            }
        }
        return true;
    }

    public boolean validarGenerarProrrogaOficio(List<FecetProrrogaOficio> prorrogas) {
        for (FecetProrrogaOficio prorroga : prorrogas) {
            if (prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus()) ||
                prorroga.getAprobada()) {
                return false;
            }
        }
        return true;
    }

    public boolean validaMetodoOficio(List<FecetProrrogaOficio> prorrogas) {
        boolean flag = false;
        for (FecetProrrogaOficio prorroga : prorrogas) {
            boolean[] condicionesAEvaluar =
                new boolean[] { prorroga.getFececEstatus().getIdEstatus().equals(Constantes.ESTATUS_PRORROGA_PENDIENTE_APROBACION),
                                prorroga.getFececEstatus().getIdEstatus().equals(Constantes.ESTATUS_RESOLUCION_PRORROGA_RECHAZADA_AUDITOR) };
            if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean validarDocResProrroga(FecetProrrogaOrden prorroga) {
        return prorroga.getRutaResolucion() != null &&
               !prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus()) ?
               true : false;
    }

}
