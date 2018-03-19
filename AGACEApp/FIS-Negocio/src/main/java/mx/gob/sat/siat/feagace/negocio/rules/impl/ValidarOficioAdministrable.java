package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;

@Component
public class ValidarOficioAdministrable extends BaseBusinessRules {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public boolean esAdministrable(FecetOficio oficio) {
        if(oficio.getFecetTipoOficio().getAgrupador() != null){
            return oficio.getFecetTipoOficio().getAgrupador().getBigIdTipoAsociacionOficios().equals(AgrupadorOficiosEnum.ADMINISTRABLES.getBigIdTipoAsociacionOficios()) ? true : false;               
        }
        else{
            return false;
        }
    }
    
    public boolean tienePlazos(FecetOficio oficio){
        return oficio.getAdminOficio().isBlnPlazos();
    }
    
    public boolean tieneProrrogas(FecetOficio oficio){
        return oficio.getAdminOficio().isBlnDocPro();
    }
    
    public boolean tieneDocReq(FecetOficio oficio){
        return oficio.getAdminOficio().isBlnDocReq();
    }
    
    public void eliminaOficiosAdministrablesSinPlazos(List<FecetOficio> listaOficios) {
        List<FecetOficio> listaOficiosAdminSinPlazos = new ArrayList<FecetOficio>();

        for (FecetOficio oficio : listaOficios) {
            if (esAdministrable(oficio) && !tienePlazos(oficio)) {
                listaOficiosAdminSinPlazos.add(oficio);
            }
        }

        for (FecetOficio oficioSinPlazos : listaOficiosAdminSinPlazos) {
            for (Iterator<FecetOficio> iter = listaOficios.iterator(); iter.hasNext();) {
                final FecetOficio oficio = iter.next();
                if (oficio.getIdOficio().equals(oficioSinPlazos.getIdOficio())) {
                    iter.remove();
                }
            }
        }
    }
}
