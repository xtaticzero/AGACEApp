package mx.gob.sat.siat.feagace.vista.helper;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

public class AsociadosMBHelper {

    public String construyeMensajeNoIdc(String tipoColaborador) {
        String mensaje = "";
        if (tipoColaborador.equals(Constantes.JSF_APODERADO_LEGAL)) {
            mensaje = FacesUtil.getMessageResourceString("label.aviso.no.apoderadoLegal");
        } else {
            mensaje = FacesUtil.getMessageResourceString("label.aviso.no.colaborador");
        }
        return mensaje;
    }

    public String construyeMensajeNoMediosContacto(ColaboradorVO colaborador) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(FacesUtil.getMessageResourceString("label.confirm.correo.colaborador"));
        mensaje.append(" ");
        mensaje.append(colaborador.getNombreTipoAsociado());
        mensaje.append("?");
        return mensaje.toString();
    }

    public String construyeMensajeNoEnvioCorreo(String colaborador) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(FacesUtil.getMessageResourceString("label.confirm.aviso.correo.colaborador.1"));
        mensaje.append(" ");
        mensaje.append(colaborador);
        mensaje.append(" ");
        mensaje.append(FacesUtil.getMessageResourceString("label.confirm.aviso.correo.colaborador.2"));
        return mensaje.toString();
    }

    public ColaboradorVO confirmaGuardado(ColaboradorVO colaborador) {
        colaborador.setDeshabilitarCampos(true);
        colaborador.setDeshabilitarCorreo(true);
        colaborador.setDeshabilitarSinColaborador(true);
        colaborador.setVisibleBusquedaColaborador(false);
        colaborador.setDeshabilitarBotonBusquedaColaborador(false);
        return colaborador;
    }
    
    public ColaboradorVO respaldaColaborador(ColaboradorVO colaborador){
        ColaboradorVO colaboradorOrginal = new ColaboradorVO();
        colaboradorOrginal.setAsociado(colaborador.getAsociado());
        colaboradorOrginal.setCorreo(colaborador.getCorreo());
        colaboradorOrginal.setDeshabilitarBotonBusquedaColaborador(colaborador.isDeshabilitarBotonBusquedaColaborador());
        colaboradorOrginal.setDeshabilitarCampos(colaborador.isDeshabilitarCampos());
        colaboradorOrginal.setDeshabilitarComboColaborador(colaborador.isDeshabilitarComboColaborador());
        colaboradorOrginal.setDeshabilitarCorreo(colaborador.isDeshabilitarCorreo());
        colaboradorOrginal.setDeshabilitarSinColaborador(colaborador.isDeshabilitarSinColaborador());
        colaboradorOrginal.setListaMediosContacto(colaborador.getListaMediosContacto());
        colaboradorOrginal.setMedioContactoBoolean(colaborador.isMedioContactoBoolean());
        colaboradorOrginal.setNombre(colaborador.getNombre());
        colaboradorOrginal.setNombreTipoAsociado(colaborador.getNombreTipoAsociado());
        colaboradorOrginal.setRfc(colaborador.getRfc());
        colaboradorOrginal.setSinColaborador(colaborador.isSinColaborador());
        colaboradorOrginal.setTipoAsociado(colaborador.getTipoAsociado());
        colaboradorOrginal.setVisibleBusquedaColaborador(colaborador.isVisibleBusquedaColaborador());
        colaboradorOrginal.setVisibleConfirmarColaborador(colaborador.isVisibleConfirmarColaborador());        
        return colaboradorOrginal;
    }
    
    public boolean validaCamposColaborador(ColaboradorVO colaborador){
        if(!colaborador.isSinColaborador()){
            return (colaborador.getRfc().equals("") || colaborador.getNombre().equals("")) ? true : false;    
        }
        else{
            return false;
        }
        
    }
}
