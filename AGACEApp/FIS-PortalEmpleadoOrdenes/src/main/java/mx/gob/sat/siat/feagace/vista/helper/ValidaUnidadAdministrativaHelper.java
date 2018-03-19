package mx.gob.sat.siat.feagace.vista.helper;

import java.io.Serializable;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaMasivaPropuestasDTO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.enumunidadadmon.UnidadAdministrativaEnum;

import mx.gob.sat.siat.feagace.vista.util.ConstantesPropuestasMasivas;

import org.springframework.stereotype.Component;

@Component
public class ValidaUnidadAdministrativaHelper implements Serializable {

    @SuppressWarnings("compatibility:5355251674581142920")
    private static final long serialVersionUID = 1L;

    public CargaMasivaPropuestasDTO validaUnidadAdministrativa(String cell,
            CargaMasivaPropuestasDTO cargaMasivaPropuestasDTO) {
        boolean unidadValida = true;
        BigDecimal unidadEmpleado = new BigDecimal(cargaMasivaPropuestasDTO.getFecetPropuesta().getEmpleadoDto()
                .getDetalleEmpleado().get(0).getCentral().getIdArace());
        if (unidadEmpleado
                .equals(Constantes.ACPPCE)) {
            if (cell.trim().equals(UnidadAdministrativaEnum.ACAOCE.getDescUnidad())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(UnidadAdministrativaEnum.ACAOCE.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ACOECE.getDescUnidad())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(UnidadAdministrativaEnum.ACOECE.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_PACIFICO_NORTE.getDescUnidad())) {
                cargaMasivaPropuestasDTO
                        .setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_PACIFICO_NORTE.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_NORTE_CENTRO.getDescUnidad())) {
                cargaMasivaPropuestasDTO
                        .setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_NORTE_CENTRO.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_NOROESTE.getDescUnidad())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_NOROESTE.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_OCCIDENTE.getDescUnidad())) {
                cargaMasivaPropuestasDTO
                        .setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_OCCIDENTE.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_CENTRO.getDescUnidad())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_CENTRO.getIdUnidad());
            } else if (cell.trim().equals(UnidadAdministrativaEnum.ADACE_SUR.getDescUnidad())) {
                cargaMasivaPropuestasDTO.setUnidadAdministrativa(UnidadAdministrativaEnum.ADACE_SUR.getIdUnidad());
            } else {
                unidadValida = false;
                cargaMasivaPropuestasDTO.setDescripcionError("Unidad Administrativa no encontrada");
            }
        } else if (contieneUnidad(cell.trim())) {
            cargaMasivaPropuestasDTO.setUnidadAdministrativa(unidadEmpleado);
        } else {
            unidadValida = false;
            cargaMasivaPropuestasDTO.setDescripcionError(ConstantesPropuestasMasivas.MSJ_CAMPO_NO_VALIDO);

        }
        cargaMasivaPropuestasDTO.setValida(unidadValida);
        return cargaMasivaPropuestasDTO;
    }

    public boolean contieneUnidad(String unidadSeleccionada) {
        if (!unidadSeleccionada.equals(UnidadAdministrativaEnum.ACAOCE.getDescUnidad())
                && !unidadSeleccionada.equals(UnidadAdministrativaEnum.ACOECE.getDescUnidad())
                && unidadSeleccionada.trim().equals("")) {
            return true;
        }
        return false;
    }

}
