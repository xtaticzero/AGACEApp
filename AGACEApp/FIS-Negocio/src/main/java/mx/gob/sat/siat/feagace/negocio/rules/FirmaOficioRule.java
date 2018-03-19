package mx.gob.sat.siat.feagace.negocio.rules;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class FirmaOficioRule extends BaseBusinessRules {

    private static final long serialVersionUID = -4173156819246269937L;

    public boolean validarOficioSuspension(FecetOficio oficio) {
        boolean[] condicionesAEvaluar;

        if (oficio.getFecetTipoOficio().getAgrupador() != null) {
            condicionesAEvaluar = new boolean[]{
                !TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio()),
                !TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio()),
                !TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio()),
                !oficio.getFecetTipoOficio().getAgrupador().equals(AgrupadorOficiosEnum.MEDIDAS_APREMIO)};
        } else {
            condicionesAEvaluar = new boolean[]{
                !TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio()),
                !TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio()),
                !TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL.getBigIdTipoOficio().equals(oficio.getFecetTipoOficio().getIdTipoOficio())};
        }
        return ValidacionParametrosUtil.seCumplenTodasLasCondicion(condicionesAEvaluar);
    }

    public boolean validarIsOficioMedidaApremio(FecetOficio oficio) {
        return oficio.getFecetTipoOficio().getAgrupador() != null && oficio.getFecetTipoOficio().getAgrupador().equals(AgrupadorOficiosEnum.MEDIDAS_APREMIO);
    }

    public boolean validarIsOficioCompulsa(FecetOficio oficio) {
        return oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio())
                || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio());
    }
}
