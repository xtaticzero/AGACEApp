package mx.gob.sat.siat.feagace.negocio.rules.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component
public class ValidarMetodoOrdenAsociadoRule extends BaseBusinessRules {

    private static final long serialVersionUID = 1L;

    public boolean validaMetodo(AgaceOrden ordenSeleccionado) {
        return ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.EHO.getId()));
    }

    public List<String> validaMetodoDespliegueAlegato(AgaceOrden ordenSeleccionado, FecetOficio oficio) {
        List<String> lista = new ArrayList<String>();
        if (ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.ORG.getId()))) {
            lista = validaORG(lista, oficio);
        } else if (ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.REE.getId()))) {
            lista = validaREE(lista, oficio);
        } else if (ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.EHO.getId()))) {
            lista = validaEHO(lista, oficio);
        } else if (ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.UCA.getId()))
                || ordenSeleccionado.getFeceaMetodo().getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.MCA.getId()))) {
            lista = validaUCAMCA(lista, oficio);
        } else {
            lista.add(" ");
            lista.add(" ");
        }
        return lista;
    }

    private List<String> validaORG(List<String> lista, FecetOficio oficio) {
        lista.add(Constantes.LEYENDA_ASOCIADO_DOC_REQ);
        if (oficio != null) {
            if (validaOficioParteUno(oficio) || validaOficioParteDos(oficio)) {
                lista.add(Constantes.LEYENDA_ASOCIADO_DOC_REQ);
            } else {
                lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
            }
        }
        return lista;
    }

    private List<String> validaREE(List<String> lista, FecetOficio oficio) {
        lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
        if (oficio != null) {
            boolean validacionUno = oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.SEGUNDO_REQUERIMIENTO.getBigIdTipoOficio())
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio());
            boolean validacionDos = oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio())
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO.getBigIdTipoOficio());
            if (validacionUno || validacionDos) {
                lista.add(Constantes.LEYENDA_ASOCIADO_DOC_REQ);
            } else {
                lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
            }
        }
        return lista;
    }

    private List<String> validaEHO(List<String> lista, FecetOficio oficio) {
        lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
        if (oficio != null) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio())
                    || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio())) {
                lista.add(Constantes.LEYENDA_ASOCIADO_DOC_REQ);
            } else {
                lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
            }
        }
        return lista;
    }

    private List<String> validaUCAMCA(List<String> lista, FecetOficio oficio) {
        lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
        if (oficio != null) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION.getBigIdTipoOficio()) || oficio
                    .getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.SEGUNDA_UNICA_CARTA_INVITACION_MASIVA.getBigIdTipoOficio())) {
                lista.add(Constantes.LEYENDA_ASOCIADO_ALEGATO);
            } else {
                lista.add(Constantes.LEYENDA_ASOCIADO_DOC_REQ);
            }
        }
        return lista;
    }

    private boolean validaOficioParteUno(FecetOficio oficio) {
        boolean[] condicionesAEvaluar = new boolean[] {
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.SEGUNDO_REQUERIMIENTO.getBigIdTipoOficio()),
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.MULTA.getBigIdTipoOficio()),
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.REQUERIMIENTO_REINCIDENCIA.getBigIdTipoOficio()) };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }

    private boolean validaOficioParteDos(FecetOficio oficio) {
        boolean[] condicionesAEvaluar = new boolean[] {
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio()),
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.AVISO_AL_CONTRIBUYENTE.getBigIdTipoOficio()),
                oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio()) };

        return ValidacionParametrosUtil.seCumpleAlgunaCondicion(condicionesAEvaluar);
    }
}
