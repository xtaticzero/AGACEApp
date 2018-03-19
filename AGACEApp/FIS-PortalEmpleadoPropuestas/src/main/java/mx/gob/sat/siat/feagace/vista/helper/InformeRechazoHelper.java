package mx.gob.sat.siat.feagace.vista.helper;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Component;

@Component
public class InformeRechazoHelper extends BaseHelper {


    public List<BigDecimal> listaRolesAceptadosInformeComite() {
        List<BigDecimal> listaRolesInformeComite = new ArrayList<BigDecimal>();
        listaRolesInformeComite.add(Constantes.USUARIO_PROGRAMADOR);
        listaRolesInformeComite.add(Constantes.USUARIO_SUBADMINISTRADOR);
        return listaRolesInformeComite;
    }
    
    public List<BigDecimal> listaRolesAceptadosRechazosPropuesta() {
        List<BigDecimal> listaRolesRechazoPropuesta = new ArrayList<BigDecimal>();
        listaRolesRechazoPropuesta.add(Constantes.USUARIO_CENTRAL);
        listaRolesRechazoPropuesta.add(Constantes.USUARIO_AUDITOR);
        return listaRolesRechazoPropuesta;
    }
    

    public List<BigDecimal> listaIdAraceInformeComite() {
        List<BigDecimal> listaAdaceInformeAcomite = new ArrayList<BigDecimal>();
        listaAdaceInformeAcomite.addAll(listaIdAraceRegional()); 
        listaAdaceInformeAcomite.add(Constantes.ACPPCE);
        
        return listaAdaceInformeAcomite;
    }
    
    public List<BigDecimal> listaIdAraceRegional() {
        List<BigDecimal> listaAdaceRegional = new ArrayList<BigDecimal>();
        listaAdaceRegional.add(Constantes.ARACE_PACIFICO_NORTE);
        listaAdaceRegional.add(Constantes.ARACE_NORTE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_NORESTE);
        listaAdaceRegional.add(Constantes.ARACE_OCCIDENTE);
        listaAdaceRegional.add(Constantes.ARACE_CENTRO);
        listaAdaceRegional.add(Constantes.ARACE_SUR);    
       return listaAdaceRegional;
    }

    
    
}
