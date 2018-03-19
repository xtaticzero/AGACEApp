package mx.gob.sat.siat.feagace.vista.helper;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.vista.BaseManagedBean;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;

import org.springframework.stereotype.Component;

@Component("cargaDocumentoElectronicoMBHelper")
public class CargaDocumentoElectronicoMBHelper extends BaseManagedBean {

    private static final long serialVersionUID = 8678853775251595779L;

    public void configurarColaborador(ColaboradorVO colaborador, FecetContribuyente contribuyenteIDC) {
        colaborador.setVisibleBusquedaColaborador(false);
        colaborador.setNombre(contribuyenteIDC.getNombre());
        colaborador.setRfc(contribuyenteIDC.getRfc());
        colaborador.setDeshabilitarCampos(true);
        colaborador.setDeshabilitarCorreo(true);
    }

    public String construyeMensajeNoMediosContacto(ColaboradorVO colaborador) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(getMessageResourceString("label.confirm.correo.colaborador"));
        mensaje.append(" ");
        mensaje.append(colaborador.getNombreTipoAsociado());
        mensaje.append("?");
        return mensaje.toString();
    }

    public String construyeMensajeNoEnvioCorreo(String colaborador) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(getMessageResourceString("label.confirm.aviso.correo.colaborador.1"));
        mensaje.append(" ");
        mensaje.append(colaborador);
        mensaje.append(" ");
        mensaje.append(getMessageResourceString("label.confirm.aviso.correo.colaborador.2"));
        return mensaje.toString();
    }

    public void iniciarNuevaBusquedaColaborador(ColaboradorVO colaborador) {
        colaborador.setRfc("");
        colaborador.setNombre("");
        colaborador.setCorreo("");
        colaborador.setMedioContactoBoolean(false);
        colaborador.setSinColaborador(false);
        colaborador.setVisibleBusquedaColaborador(true);
        colaborador.setDeshabilitarCampos(false);
        colaborador.setDeshabilitarCorreo(false);
        colaborador.setDeshabilitarSinColaborador(false);
        colaborador.setVisibleConfirmarColaborador(false);
        colaborador.setDeshabilitarComboColaborador(false);
    }

    public ColaboradorVO validaEmailInputText(ColaboradorVO colaborador, boolean flag) {
        colaborador.setDeshabilitarCampos(true);
        if (flag) {
            colaborador.setDeshabilitarCorreo(false);
        } else {
            colaborador.setDeshabilitarCorreo(true);
        }
        return colaborador;
    }

    public String validaAceptarAdjuntar(List<FecetDocOrden> ordenesArchivoCargado, boolean isMuestraOficio, List<FecetOficio> oficiosArchivoCargado,
            BigDecimal tipoOficioSeleccionado, ColaboradorVO agenteAduanalVO, boolean muestraAgenteAduanal) {

        Long conversor = -1L;
        BigDecimal tOficio = new BigDecimal(conversor);

        if (ordenesArchivoCargado != null && ordenesArchivoCargado.size() > 0) {
            if (isMuestraOficio) {
                if (oficiosArchivoCargado != null && oficiosArchivoCargado.size() > 0) {
                    if (tipoOficioSeleccionado != null && !tipoOficioSeleccionado.equals(tOficio)) {
                        return validaAgenteAduanal(agenteAduanalVO, muestraAgenteAduanal);
                    } else {
                        return "Debes seleccionar un tipo de oficio";
                    }
                } else {
                    return validaAgenteAduanal(agenteAduanalVO, muestraAgenteAduanal);
                }
            } else {
                return validaAgenteAduanal(agenteAduanalVO, muestraAgenteAduanal);
            }
        } else {
            return "Debes adjuntar un Documento Orden";
        }
    }

    public String validaAgenteAduanal(ColaboradorVO agenteAduanalVO, boolean muestraAgenteAduanal) {
        if (muestraAgenteAduanal) {
            return agenteAduanalVO.getNombre() != null && agenteAduanalVO.getRfc() != null ? null : "Debes agregar un Agente Aduanal";
        } else {
            return null;
        }
        
    }

}
