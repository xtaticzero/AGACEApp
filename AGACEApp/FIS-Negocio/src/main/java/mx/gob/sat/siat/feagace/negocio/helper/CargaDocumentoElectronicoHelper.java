package mx.gob.sat.siat.feagace.negocio.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Component
public class CargaDocumentoElectronicoHelper extends BaseHelper {

    private static final long serialVersionUID = -969504080113053137L;

    public FecetAsociado crearAsociado(String rfcContribuyente, ColaboradorVO colaborador, AgaceOrden orden) {
        FecetAsociado fecetAsociado = null;
        if (!colaborador.getRfc().isEmpty() && !colaborador.getNombre().isEmpty()) {
            fecetAsociado = new FecetAsociado();
            fecetAsociado.setIdOrden(orden.getIdOrden());
            fecetAsociado.setRfcContribuyente(rfcContribuyente);
            fecetAsociado.setIdTipoAsociado(new BigDecimal(Constantes.ENTERO_CUATRO));
            fecetAsociado.setNombre(colaborador.getNombre());
            fecetAsociado.setRfc(colaborador.getRfc());
            fecetAsociado.setCorreo(colaborador.getCorreo());
            fecetAsociado.setTipoAsociado(Constantes.CADENA_CERO);
            if (colaborador.isMedioContactoBoolean()) {
                fecetAsociado.setMedioContacto(Constantes.CADENA_UNO);
            } else {
                fecetAsociado.setMedioContacto(Constantes.CADENA_CERO);
            }
            // Buscar a que se refiere este estatus
            fecetAsociado.setEstatus("1");
            fecetAsociado.setMedioContactoBoolean(colaborador.isMedioContactoBoolean());
        }
        return fecetAsociado;
    }

    public void iniciarColaborador(ColaboradorVO colaborador, List<FecetAsociado> lista) {
        if (lista != null && !lista.isEmpty()) {
            colaborador.setAsociado(lista.get(Constantes.ENTERO_CERO));
            colaborador.setRfc(colaborador.getAsociado().getRfc());
            colaborador.setNombre(colaborador.getAsociado().getNombre());
            colaborador.setCorreo(colaborador.getAsociado().getCorreo());
            colaborador.setMedioContactoBoolean(colaborador.getAsociado().isMedioContactoBoolean());
            if (colaborador.getAsociado().getEstatus().equals(Constantes.CADENA_UNO)) {
                colaborador.setVisibleConfirmarColaborador(true);
            }
        } else {
            colaborador.setAsociado(null);
        }
        colaborador.setVisibleBusquedaColaborador(false);
        colaborador.setDeshabilitarCampos(true);
        colaborador.setDeshabilitarCorreo(true);
        colaborador.setDeshabilitarSinColaborador(true);
        colaborador.setDeshabilitarComboColaborador(true);
    }

}
