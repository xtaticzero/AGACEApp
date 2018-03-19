package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.exception.NegocioException;
import mx.gob.sat.siat.feagace.negocio.propuestas.carga.CargaMasivaPropuestasService;
import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

import org.springframework.stereotype.Component;

@Component
public class ValidaMetodoHelper implements Serializable {

    @SuppressWarnings("compatibility:676631209374120475")
    private static final long serialVersionUID = 1L;

    public CargaMasivaPropuestasDTO validaMetodoCI(String cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        String metodo = cell.trim();
        if (metodo.length() != Constantes.ENTERO_TRES
                || !metodo.equals(ConstantesPropuestasMasivas.MCA)) {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            return cargaMasivaPropuestasDTO;
        } else {
            BigDecimal idMetodo;
            try {
                idMetodo = cargaMasivaPropuestasService.validaMetodo(cell.trim());
            } catch (NegocioException e) {
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
                return cargaMasivaPropuestasDTO;
            }
            cargaMasivaPropuestasDTO.getFecetPropuesta().setIdMetodo(idMetodo);
            cargaMasivaPropuestasDTO.setMetodoString(cell.trim());
            cargaMasivaPropuestasDTO.setValida(true);
        }
        return cargaMasivaPropuestasDTO;
    }

    public CargaMasivaPropuestasDTO validaMetodo(String cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO,
            CargaMasivaPropuestasService cargaMasivaPropuestasService) {
        if (cell.trim().length() == Constantes.ENTERO_TRES) {
            try {
                BigDecimal idMetodo = cargaMasivaPropuestasService.validaMetodo(cell.trim());
                cargaMasivaPropuestasDTO.getFecetPropuesta().setIdMetodo(idMetodo);
                cargaMasivaPropuestasDTO.setMetodoString(cell.trim());
                cargaMasivaPropuestasDTO.setValida(true);
            } catch (NegocioException e) {
                cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
                cargaMasivaPropuestasDTO.setValida(false);
                return cargaMasivaPropuestasDTO;
            }

        } else {
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);
            cargaMasivaPropuestasDTO.setValida(false);
            return cargaMasivaPropuestasDTO;
        }
        return cargaMasivaPropuestasDTO;

    }
}
