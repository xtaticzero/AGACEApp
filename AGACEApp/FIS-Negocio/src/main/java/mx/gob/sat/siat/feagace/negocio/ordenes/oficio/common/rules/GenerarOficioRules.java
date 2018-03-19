package mx.gob.sat.siat.feagace.negocio.ordenes.oficio.common.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.validador.BaseBusinessRules;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.AgaceOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetOficio;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TiposOficiosOrdenesEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;
import mx.gob.sat.siat.feagace.negocio.common.plazos.service.PlazosServiceOrdenes;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;

@Component("generarOficioRules")
public class GenerarOficioRules extends BaseBusinessRules {

    private static final long serialVersionUID = 67474999125688267L;

    @Autowired
    private transient PlazosServiceOrdenes plazosService;

    public boolean validarVisualizacionTabsCompulsas(AgaceOrden orden) {
        boolean[] condiciones = new boolean[] {
                ((orden.getIdMetodo().equals(Constantes.ORG) || orden.getIdMetodo().equals(Constantes.REE) || (orden.getIdMetodo().equals(Constantes.EHO)))) };
        return ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones);

    }

    public boolean validarVisualizacionTab(AgaceOrden orden, TiposOficiosOrdenesEnum tipoOficio, List<FecetOficio> oficiosEnProceso) {
        for (FecetOficio oficio : oficiosEnProceso) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(tipoOficio.getBigIdTipoOficio())) {
                return false;
            }
        }
        return plazosService.validarPlazoSolicitarOficio(orden, tipoOficio.getBigIdTipoOficio());
    }

    public boolean validarVisulizarSolicitudOficio(AgaceOrden orden, TiposOficiosOrdenesEnum tipoOficio) {
        return plazosService.validarPlazoSolicitarOficio(orden, tipoOficio.getBigIdTipoOficio());
    }

    public boolean validarVisualizacionTabPruebasPericiales(AgaceOrden orden, TiposOficiosOrdenesEnum tipoOficio,
            List<FecetPruebasPericiales> listPruebasPericiales, List<FecetPruebasPericiales> listPruebasPericialesConRelacion,
            List<FecetPruebasPericiales> listPruebasPericialesPendientes) {
        boolean flag = true;
        if (tipoOficio.equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO)) {
            if (listPruebasPericiales.isEmpty()) {
                flag = false;
            } else {
                if (!listPruebasPericialesConRelacion.isEmpty()) {
                    flag = false;
                } else {
                    if (!listPruebasPericialesPendientes.isEmpty()) {
                        flag = false;
                    }
                }

            }
        }
        return flag;
    }

    public boolean validarVisualizacionTabPruebasPericiales(AgaceOrden orden, TiposOficiosOrdenesEnum tipoOficio,
            List<FecetPruebasPericiales> existeSolicitud, List<FecetPruebasPericiales> existeSolicitudAprobada) {
        boolean flag = true;
        if (tipoOficio.equals(TiposOficiosOrdenesEnum.PRUEBAS_PERICIALES_DESAHOGO)) {
            if (existeSolicitud.isEmpty()) {
                flag = false;
            } else {
                if (existeSolicitudAprobada.isEmpty()) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean validaOficioTerminaProceso(List<FecetOficio> oficiosEnProceso) {
        for (FecetOficio oficio : oficiosEnProceso) {
            if (ValidacionParametrosUtil.seCumpleAlgunaCondicion(new boolean[] {
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.CONCLUSION.getBigIdTipoOficio()),
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.CAMBIO_METODO.getBigIdTipoOficio()),
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.RESOLUCION_DEFINITIVA.getBigIdTipoOficio()),
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.SIN_OBSERVACIONES.getBigIdTipoOficio()),
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.CANCELACION_ORDEN.getBigIdTipoOficio()),
                    oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.CONCLUSION_REVISION_ESCRITOS_IMP.getBigIdTipoOficio()) })) {
                return true;
            }
        }
        return false;
    }

    public boolean validarVisualizacionIntegraExpediente(AgaceOrden orden) {
        if (orden.getIdMetodo().equals(Constantes.EHO) && orden.getDiasRestantesDocumentos() == 0 && orden.getFechaIntegraExp() == null) {
            return true;
        }
        return false;
    }

    public boolean validarVisualizacionReactivarPlazo(AgaceOrden orden, List<FecetOficio> oficiosEnProceso) {
        if (orden.getIdMetodo().equals(Constantes.REE)) {
            for (FecetOficio oficio : oficiosEnProceso) {
                if ((oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio())
                        || oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio()))
                        && oficio.getDiasRestantesDocumentos().equals(BigDecimal.ZERO) && orden.getFechaReactivarPlazo() == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean validarVisualizacionReactivarPlazoAcuerdoConclusivo(AgaceOrden orden) {
        return false;
    }

    public boolean validarVisualizarTabAvisoContribuyenteOpcional(FecetOficio oficioSeleccionado, List<FecetOficio> listaOficios) {
        List<BigDecimal> listaAvisoContribuyente = new ArrayList<BigDecimal>();
        boolean isVisible = false;
        listaAvisoContribuyente.add(TiposOficiosOrdenesEnum.PRUEBAS_DESAHOGO.getBigIdTipoOficio());

        listaAvisoContribuyente.add(TiposOficiosOrdenesEnum.COMPULSA_INTERNACIONAL.getBigIdTipoOficio());
        listaAvisoContribuyente.add(TiposOficiosOrdenesEnum.COMPULSA_TERCEROS.getBigIdTipoOficio());
        listaAvisoContribuyente.add(TiposOficiosOrdenesEnum.OTRAS_AUTORIDADES.getBigIdTipoOficio());
        if (listaAvisoContribuyente.contains(oficioSeleccionado.getFecetTipoOficio().getIdTipoOficio())) {
            isVisible = true;
        }
        return isVisible;
    }

    public boolean validarOficioAvisoCircunstancial(List<FecetOficio> oficiosEnProceso) {
        boolean flag = false;
        for (FecetOficio oficio : oficiosEnProceso) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.AVISO_CIRCUNSTANCIAL.getBigIdTipoOficio())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean validaEhoFechaExpediente(AgaceOrden orden) {
        return (orden.getIdMetodo().equals(new BigDecimal(TipoMetodoEnum.EHO.getId())) && orden.getFechaIntegraExp() != null);
    }

    public boolean validaOficioOREFirmada(List<FecetOficio> oficios) {
        boolean flag = false;
        for (FecetOficio oficio : oficios) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO.getBigIdTipoOficio())
                    && oficio.getFechaFirma() != null) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean validaOficioORE(List<FecetOficio> oficios) {
        boolean flag = false;
        for (FecetOficio oficio : oficios) {
            if (oficio.getFecetTipoOficio().getIdTipoOficio().equals(TiposOficiosOrdenesEnum.OBSERVACIONES_REVISION_ESCRITORIO.getBigIdTipoOficio())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
