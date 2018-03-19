package mx.gob.sat.siat.feagace.negocio.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetTipoOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.rules.impl.ValidarOficioAdministrable;

@Component
public class GenerarOficioHelper extends BaseHelper {

    private static final long serialVersionUID = 5083375397143802386L;
    
    @Autowired
    private transient ValidarOficioAdministrable validarOficioAdministrable;
    
    public boolean validarExistenciaOficioDependiente( List<FecetTipoOficio> listaTipoOficio, List<FecetOficio> oficios){
        for(FecetTipoOficio oficioDependiente:listaTipoOficio){
            for(FecetOficio fecetOficio: oficios ){
                if(oficioDependiente.getIdTipoOficio().equals(fecetOficio.getFecetTipoOficio().getIdTipoOficio())){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isMetodoRee(AgaceOrden orden){
        return orden.getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.REE.getId()))==0;
    }
    
    public boolean isMetodoOrg(AgaceOrden orden){
        return orden.getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.ORG.getId()))==0;
    }
    
    public boolean isMetodoUca(AgaceOrden orden){
        return orden.getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.UCA.getId()))==0;
    }
    
    public boolean isMetodoMca(AgaceOrden orden){
        return orden.getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.MCA.getId()))==0;
    }
    
    public boolean isMetodoEho(AgaceOrden orden){
        return orden.getIdMetodo().compareTo(new BigDecimal(TipoMetodoEnum.EHO.getId()))==0;
    }
    
    public boolean isCompulsa(List<FecetOficio> oficios){
        List <TiposOficiosOrdenesEnum> compulsas = new ArrayList<TiposOficiosOrdenesEnum>();
        int contarCompulsas=0;
        boolean flag = false;
        compulsas.add(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS);
        compulsas.add(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL);
        compulsas.add(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES);
        compulsas.add(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE);
        if(!oficios.isEmpty()){
            for(FecetOficio oficio:oficios){
                TiposOficiosOrdenesEnum  tipoOficio = TiposOficiosOrdenesEnum.parse(oficio.getFecetTipoOficio().getIdTipoOficio().intValue());
                if(compulsas.contains(tipoOficio)){
                    ++contarCompulsas;
                }
            }
            
            if(oficios.size()==contarCompulsas){
                flag = true;
            }
        }
        
        return flag;
    }
    
    public void eliminaOficiosAdministrablesSinPlazos(List<FecetOficio> oficiosEnProceso){
       validarOficioAdministrable.eliminaOficiosAdministrablesSinPlazos(oficiosEnProceso);                
    }
    
    
    
}
