package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

@Component
public class ValidaTipoDeRevisionHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    public void validaTipoRevision(String cell, CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        if (cargaMasivaPropuestasDTO.getMetodoString().equals(ConstantesPropuestasMasivas.ORG)) {
            if (cell.trim().length() == Constantes.ENTERO_TRES) {
                try {
                    if (cargaMasivaPropuestasService.validaRevision(cell.toUpperCase().trim()) != null) {
                        cargaMasivaPropuestasDTO.setTipoRevision(cell.trim());
                        cargaMasivaPropuestasDTO.setValida(true);
                    } else {
                        cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                        cargaMasivaPropuestasDTO.setValida(false);
                    }
                } catch (NegocioException e) {
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
