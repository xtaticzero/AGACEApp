package mx.gob.sat.siat.feagace.negocio.rules.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetProrrogaOficio;
import mx.gob.sat.siat.feagace.modelo.enums.ordenes.prorrogas.EstatusProrroga;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class ValidarMetodoProrrogasRule extends BaseBusinessRules {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient ValidarOficioAdministrable validarOficioAdministrable;

    public boolean validaMetodoProrrogaOrden(AgaceOrden orden) {
        return orden.getFeceaMetodo().getIdMetodo().equals(Constantes.ORG) ||
            orden.getFeceaMetodo().getIdMetodo().equals(Constantes.UCA) ||
            orden.getFeceaMetodo().getIdMetodo().equals(Constantes.MCA);
    }
    
    public boolean validarDocResProrroga(FecetProrrogaOficio prorroga) {
        return prorroga.getRutaResolucion() != null &&
               !prorroga.getFececEstatus().getIdEstatus().equals(EstatusProrroga.PRORROGA_PENDIENTE_APROBACION.getBigIdEstatus());
    }
    
    public boolean validaMetodoProrrogaOficio(AgaceOrden orden, FecetOficio oficio) {
        boolean flag = false;
        if (validaMetodoProrrogaOrden(orden)) {
            flag = true;
        } else {
            if (validarOficioAdministrable.esAdministrable(oficio) && validarOficioAdministrable.tieneProrrogas(oficio)) {
                flag = true;
            }
        }
        return flag;
    }
}
