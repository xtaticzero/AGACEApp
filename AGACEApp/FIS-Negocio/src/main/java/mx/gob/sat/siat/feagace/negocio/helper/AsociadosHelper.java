package mx.gob.sat.siat.feagace.negocio.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.FececTipoAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.common.FecetContribuyente;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ColaboradorVO;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAsociado;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorOficiosEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

public class AsociadosHelper {

    public FececTipoAsociado creaPerfilContribuyente() {
        FececTipoAsociado contribuyente = new FececTipoAsociado();
        contribuyente.setIdTipoAsociado(Constantes.ID_CONTRIBUYENTE);
        contribuyente.setNombre(Constantes.CONTRIBUYENTE_COMBO);
        contribuyente.setDescripcion(Constantes.CONTRIBUYENTE_COMBO);
        return contribuyente;
    }

    public ColaboradorVO setApoderadoLegal(ColaboradorVO apoderadoLegal, List<FecetAsociado> listaAL) {
        if (listaAL != null && !listaAL.isEmpty()) {
            apoderadoLegal.setAsociado(listaAL.get(Constantes.ENTERO_CERO));
            apoderadoLegal.setRfc(apoderadoLegal.getAsociado().getRfc());
            apoderadoLegal.setNombre(apoderadoLegal.getAsociado().getNombre());
            apoderadoLegal.setCorreo(apoderadoLegal.getAsociado().getCorreo());
            apoderadoLegal.setMedioContactoBoolean(apoderadoLegal.getAsociado().isMedioContactoBoolean());

        } else {
            apoderadoLegal.setSinColaborador(true);
            apoderadoLegal.setAsociado(null);
        }
        apoderadoLegal.setDeshabilitarCampos(true);
        apoderadoLegal.setVisibleBusquedaColaborador(false);
        apoderadoLegal.setDeshabilitarCorreo(true);
        apoderadoLegal.setDeshabilitarSinColaborador(true);
        apoderadoLegal.setDeshabilitarBotonBusquedaColaborador(false);
        return apoderadoLegal;
    }

    public ColaboradorVO configuraColaborador(ColaboradorVO colaborador, FecetContribuyente contribuyenteIDC) {
        colaborador.setVisibleBusquedaColaborador(false);
        colaborador.setNombre(contribuyenteIDC.getNombre());
        colaborador.setRfc(contribuyenteIDC.getRfc());
        colaborador.setDeshabilitarCampos(true);
        colaborador.setDeshabilitarCorreo(true);
        return colaborador;
    }

    public ColaboradorVO nuevaBusquedaColaborador(ColaboradorVO colaborador) {
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
        return colaborador;
    }

    public ColaboradorVO ordenSinColaborador(ColaboradorVO colaborador) {
        if (colaborador.isSinColaborador()) {
            colaborador.setRfc("");
            colaborador.setNombre("");
            colaborador.setCorreo("");
            colaborador.setMedioContactoBoolean(false);
            colaborador.setVisibleBusquedaColaborador(true);
            colaborador.setDeshabilitarCampos(true);
            colaborador.setDeshabilitarCorreo(true);
            colaborador.setDeshabilitarComboColaborador(true);
            colaborador.setDeshabilitarBotonBusquedaColaborador(true);
        } else {
            colaborador.setDeshabilitarCampos(false);
            colaborador.setDeshabilitarCorreo(false);
            colaborador.setDeshabilitarComboColaborador(false);
            colaborador.setDeshabilitarBotonBusquedaColaborador(false);
        }
        return colaborador;
    }

    public FecetAsociado llenaColaborador(String rfcContribuyente, ColaboradorVO colaborador, AgaceOrden orden) {
        FecetAsociado fecetAsociado = new FecetAsociado();
        if (!colaborador.getRfc().equals("") || !colaborador.getNombre().equals("")) {
            fecetAsociado.setRfcContribuyente(rfcContribuyente);
            if (orden != null) {
                fecetAsociado.setIdOrden(orden.getIdOrden());
            } else {
                fecetAsociado.setIdOrden(null);
            }
            fecetAsociado.setIdTipoAsociado(colaborador.getTipoAsociado());
            fecetAsociado.setNombre(colaborador.getNombre());
            fecetAsociado.setRfc(colaborador.getRfc());
            fecetAsociado.setCorreo(colaborador.getCorreo());
            fecetAsociado.setTipoAsociado(Constantes.CADENA_CERO);
            if (colaborador.isMedioContactoBoolean()) {
                fecetAsociado.setMedioContacto(Constantes.CADENA_UNO);
            } else {
                fecetAsociado.setMedioContacto(Constantes.CADENA_CERO);
            }
            fecetAsociado.setEstatus("2");
            fecetAsociado.setMedioContactoBoolean(colaborador.isMedioContactoBoolean());
            fecetAsociado.setFechaBaja(null);
            fecetAsociado.setFechaUltimaMod(null);
            fecetAsociado.setFechaUltimaModIdc(null);
        } else {
            fecetAsociado = null;
        }
        return fecetAsociado;
    }

    public String contruyeMensajeDesplegar(String nombreTipo) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_1);
        mensaje.append(nombreTipo);
        mensaje.append(Constantes.EXITO_GUARDADO_ASOCIADO_2);
        return mensaje.toString();
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

    public ColaboradorVO seteaCamposTxt(ColaboradorVO colaborador, boolean flag) {
        colaborador.setDeshabilitarCampos(flag);
        colaborador.setVisibleBusquedaColaborador(flag);
        return colaborador;
    }

    public ColaboradorVO setColaborador(ColaboradorVO colaborador, List<FecetAsociado> lista) {
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
            colaborador.setSinColaborador(true);
            colaborador.setAsociado(null);
        }
        colaborador.setVisibleBusquedaColaborador(false);
        colaborador.setDeshabilitarCampos(true);
        colaborador.setDeshabilitarCorreo(true);
        colaborador.setDeshabilitarSinColaborador(true);
        colaborador.setDeshabilitarComboColaborador(true);
        return colaborador;
    }

    public List<FecetOficio> eliminaOficioMedidasApremio(List<FecetOficio> listaOficio) {
        List<FecetOficio> listaOficiosMedidasApremio = new ArrayList<FecetOficio>();
        for (FecetOficio oficio : listaOficio) {
            if (oficio.getFecetTipoOficio().getAgrupador() != null 
                    && oficio.getFecetTipoOficio().getAgrupador().getBigIdTipoAsociacionOficios().equals(AgrupadorOficiosEnum.MEDIDAS_APREMIO.getBigIdTipoAsociacionOficios())) {
                listaOficiosMedidasApremio.add(oficio);
            }
        }
        for (FecetOficio oficioMedidas : listaOficiosMedidasApremio) {
            for (Iterator<FecetOficio> iter = listaOficio.iterator(); iter.hasNext();) {
                final FecetOficio oficio = iter.next();
                if (oficio.getIdOficio().equals(oficioMedidas.getIdOficio())) {
                    iter.remove();
                }
            }
        }
        return listaOficio;
    }

}
