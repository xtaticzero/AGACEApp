package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

import org.springframework.stereotype.Component;

@Component
public class ValidaTipoDeRevisionHelper implements Serializable {

    @SuppressWarnings("compatibility:-5822543040499850356")
    private static final long serialVersionUID = 1L;

    public void validaTipoRevision(String cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        if (cargaMasivaPropuestasDTO.getMetodoString().equals(ConstantesPropuestasMasivas.ORG)) {
            if (cell.trim().length() == Constantes.ENTERO_TRES) {

                boolean[] condiciones = new boolean[]{
                    cell.trim().equals(ConstantesPropuestasMasivas.CGA), 
                    cell.trim().equals(ConstantesPropuestasMasivas.COM), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GPF), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GRM), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GAD), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GIF), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GIM), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GDV), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GRF), 
                    cell.trim().equals(ConstantesPropuestasMasivas.CGE), 
                    cell.trim().equals(ConstantesPropuestasMasivas.CGR), 
                    cell.trim().equals(ConstantesPropuestasMasivas.GSD), 
                    cell.trim().equals(ConstantesPropuestasMasivas.CCG)};                

                if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(condiciones)) {
                    cargaMasivaPropuestasDTO.setTipoRevision(cell.trim());
                    cargaMasivaPropuestasDTO.setValida(true);
                } else {
                    cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                    cargaMasivaPropuestasDTO.setValida(false);
                }
            } else {
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
            }
        } else if (cell.trim().length() != 0) {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
        }
    }

}
