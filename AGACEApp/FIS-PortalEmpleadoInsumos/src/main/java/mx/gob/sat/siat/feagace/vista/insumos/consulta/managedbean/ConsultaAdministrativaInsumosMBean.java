/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.consulta.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaInsumosServiceException;
import mx.gob.sat.siat.feagace.negocio.util.constantes.AcppceOpcionesEnum;
import mx.gob.sat.siat.feagace.negocio.util.constantes.SemaforoEnum;
import mx.gob.sat.siat.feagace.vista.helper.ItemCombo;
import mx.gob.sat.siat.feagace.vista.insumos.consulta.ConsultaInsumosAbstractMBean;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXClaveItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXDescripcionItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ViewScoped
@ManagedBean(name = "consultaAdminInsumosMB")
public class ConsultaAdministrativaInsumosMBean extends ConsultaInsumosAbstractMBean {

    private static final long serialVersionUID = -2764695774068651452L;

    @PostConstruct
    public void init() {
        logger.info("se inicializa un objeto nuevo");
    }

    public void consultarXUnidadAdminSeleccionada() {
        setControlDeAcceso(true);
        setUnidadAdminByID(getConsultaInsumosHelper().getIdUnidadAdminSeleccionada());
        if (getConsultaInsumosHelper().getUnidadAdminSeleccionada() != null) {
            addUnidadesDesagoAlFiltro(getConsultaInsumosHelper().getUnidadAdminSeleccionada());
        }
        try {
            getConsultaEjecutivaInsumosService().consultarInsumos(getConsultaInsumosHelper().getConsultaInsumosBO(), getConsultaInsumosHelper().getFiltro());
            mostrarPanelEstatusSemaforo();
        } catch (ConsultaEjecutivaInsumosServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultaCentralACPPCE() {
        if (getConsultaInsumosHelper().getConsultaInsumosBO().isAdministradorACPPCE()) {
            getConsultaInsumosHelper().setOpcionSelccionada(AcppceOpcionesEnum.REGISTRADOS);
        }
        if (getConsultaInsumosHelper().getOpcionSelccionada() == null) {
            FacesUtil.addErrorMessage(null, "Es necesario seleccionar una opci\u00f3n.");
            return;
        } else if (AcppceOpcionesEnum.REGISTRADOS.equals(getConsultaInsumosHelper().getOpcionSelccionada())) {
            setUnidadAdminByID(getConsultaInsumosHelper().getIdUnidadAdminSeleccionada());
            if (getConsultaInsumosHelper().getUnidadAdminSeleccionada() != null) {
                addUnidadesDesagoAlFiltro(getConsultaInsumosHelper().getUnidadAdminSeleccionada());
            }
        } else {
            setUnidadAdminByID(obtenerUnidadAdministrativaEmpleado().getIdArace());
            addUnidadesDesagoAlFiltro(obtenerUnidadAdministrativaEmpleado());
        }
        getConsultaInsumosHelper().getConsultaInsumosBO().setIsAciace(AcppceOpcionesEnum.REGISTRADOS.equals(getConsultaInsumosHelper().getOpcionSelccionada()));
        consultarInsumos();
        mostrarPanelEstatusSemaforo();
    }

    public void consultarXPlazoSeleccionado() {
        setControlDeAcceso(true);
        try {
            getConsultaEjecutivaInsumosService().getInsumosPlazoXConcluir(getConsultaInsumosHelper().getConsultaInsumosBO(), getConsultaInsumosHelper().getPlazoSeleccionado());
            getConsultaInsumosHelper().setLstInsumosResult(getConsultaInsumosHelper().getConsultaInsumosBO().getLstInsumosXFiltro());
            setConsultaXPlazo(true);
            mostrarPanelInsumos();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private void setUnidadAdminByID(Integer idUnidadAdmin) {
        getConsultaInsumosHelper().setUnidadAdminSeleccionada(null);
        logger.info(String.valueOf(idUnidadAdmin));
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getDetalleCategoriaEmpleado() {
        if (getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEmpleado() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> empleadosSet
                    = getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEmpleado().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(empleadosSet);
        }
        return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>();
    }

    public List<Map.Entry<TipoEstatusEnum, Integer>> getDetalleEstatus() {
        if (getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEstatus() != null) {
            Set<Map.Entry<TipoEstatusEnum, Integer>> estatusSet
                    = getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEstatus().entrySet();
            return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>(estatusSet);
        }
        return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>();
    }

    public List<Map.Entry<TipoEstatusEnum, Integer>> getDetalleEstatusFiltrado() {
        if (getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEstatusFiltrado() != null) {
            Set<Map.Entry<TipoEstatusEnum, Integer>> estatusSet
                    = getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXEstatusFiltrado().entrySet();

            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("formConsultaXEstatusCentral:panelEstatusFiltrado:tblEstatusFiltrados");

            return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>(estatusSet);
        }
        return new ArrayList<Map.Entry<TipoEstatusEnum, Integer>>();
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getDetalleSemaforo() {
        if (getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforo() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> semaforoSet
                    = getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforo().entrySet();
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(semaforoSet);
        }
        return new ArrayList<Map.Entry<SemaforoEnum, Integer>>();
    }

    public List<Map.Entry<SemaforoEnum, Integer>> getDetalleSemaforoFiltrado() {
        getConsultaInsumosHelper().setFlgMostrarLstSemaforoFiltrado(false);
        if (getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforoFiltrado() != null) {
            Set<Map.Entry<SemaforoEnum, Integer>> semaforoSet
                    = getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforoFiltrado().entrySet();
            getConsultaInsumosHelper().setFlgMostrarLstSemaforoFiltrado(validarTablaSemaforosFiltrados());
            return new ArrayList<Map.Entry<SemaforoEnum, Integer>>(semaforoSet);
        }
        getConsultaInsumosHelper().setFlgMostrarLstSemaforoFiltrado(validarTablaSemaforosFiltrados());
        return new ArrayList<Map.Entry<SemaforoEnum, Integer>>();
    }

    private boolean validarTablaSemaforosFiltrados() {
        return getConsultaInsumosHelper().getSemaforoSeleccionado() != null
                && getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforoFiltrado() != null
                && !getConsultaInsumosHelper().getConsultaInsumosBO().getDetalleXSemaforoFiltrado().isEmpty();
    }

    public void consultarDetalleInsumo() {

        if (getConsultaInsumosHelper().getInsumosSeleccionado() != null) {
            try {
                getConsultaEjecutivaInsumosService().injectarPistaDetalleInsumo(getConsultaInsumosHelper().getInsumosSeleccionado());
                if (getConsultaInsumosHelper().getInsumosSeleccionado() != null) {
                    getConsultaEjecutivaInsumosService().consultaHistoricoInsumos(getConsultaInsumosHelper().getConsultaInsumosBO(), getConsultaInsumosHelper().getInsumosSeleccionado());
                    if (getConsultaInsumosHelper().getInsumosSeleccionado().getRechazoInsumo() != null) {
                        getConsultaInsumosHelper().setLstRechazo(new ArrayList<FecetRechazoInsumo>());
                        getConsultaInsumosHelper().getLstRechazo().add(getConsultaInsumosHelper().getInsumosSeleccionado().getRechazoInsumo());
                    }
                }
            } catch (Exception e) {
                addMessage(e.getMessage());
            }
            mostrarPanelDetalleInsumo();
        }
    }

    public void consultaHistoricoInsumo() {
        try {
            if (getConsultaInsumosHelper().getInsumosSeleccionado() != null) {
                getConsultaEjecutivaInsumosService().consultaHistoricoInsumos(getConsultaInsumosHelper().getConsultaInsumosBO(), getConsultaInsumosHelper().getInsumosSeleccionado());
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dlgInsumosRetroalimentados').show();");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void regresarDeDetalleInsumo() {
        limpiarFiltros();

        if (isPantallaTotalEmpleados()) {
            mostrarPanelXCategorias();
            setPantallaTotalEmpleados(false);
            return;
        }

        if ((getConsultaInsumosHelper().getConsultaInsumosBO().getRolEmpleado().equals(TipoEmpleadoEnum.VALIDADOR_INSUMOS)) || (getConsultaInsumosHelper().getConsultaInsumosBO().getRolEmpleado().equals(TipoEmpleadoEnum.USUARIO_INSUMOS)) || getConsultaInsumosHelper().isFlgPaginaEstatusSemaforos()) {
            if (isPantallaEstatusFiltrado()) {
                mostrarPanelEstatusFiltrado();
            } else {
                mostrarPanelEstatusSemaforo();
            }
        } else {
            if (isPantallaEstatusFiltrado()) {
                mostrarPanelEstatusFiltrado();
            } else {
                mostrarPanelXCategorias();
            }
        }
    }

    public void regresarPantallaLstInsumos() {
        limpiarFiltros();
        getConsultaInsumosHelper().setInsumosSeleccionado(null);
        mostrarPanelInsumos();
    }

    public void consultarTotalGeneral() {
        getConsultaInsumosHelper().setFlgPaginaEstatusSemaforos(!isPantallaEstatusFiltrado());
        getConsultaInsumosHelper().setLstInsumosResult(getConsultaInsumosHelper().getConsultaInsumosBO().getLstInsumoResult());
        mostrarPanelInsumos();
    }

    public void consultarTotalSemaforosFiltro() {
        getConsultaInsumosHelper().setLstInsumosResult(getConsultaInsumosHelper().getConsultaInsumosBO().getLstInsumosXFiltro());
        mostrarPanelInsumos();
    }

    public Set<ItemCombo> getLstValoresFiltroPlazos() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());
        if (isListValid(getConsultaInsumosHelper().getLstInsumosResult())) {
            for (FecetInsumo insumo : getConsultaInsumosHelper().getLstInsumosResult()) {
                ItemCombo item = new ItemCombo();
                int plazo = insumo.getPlazoRestante();
                item.setValor(plazo);
                switch (plazo) {
                    case PLAZO_INICIAL:
                        item.setDescripcion("Sin atender");
                        break;
                    case 0:
                        item.setDescripcion("Plazo vencido");
                        break;
                    case UNO:
                        item.setDescripcion(plazo + DESCRIPCION_PLAZO);
                        break;
                    default:
                        item.setDescripcion(plazo + DESCRIPCION_PLAZO + "s");
                        break;
                }
                lstFiltro.add(item);
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroPrioridad() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());
        if (isListValid(getConsultaInsumosHelper().getLstInsumosResult())) {
            for (FecetInsumo insumo : getConsultaInsumosHelper().getLstInsumosResult()) {
                lstFiltro.add(new ItemCombo(insumo.getPrioridadDto().getValor(), insumo.getPrioridadDto().getIdPrioridad().intValue()));
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroEstatus() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());
        if (isListValid(getConsultaInsumosHelper().getLstInsumosResult())) {
            for (FecetInsumo insumo : getConsultaInsumosHelper().getLstInsumosResult()) {
                lstFiltro.add(new ItemCombo(insumo.getFececEstatus().getDescripcion(), (insumo.getFececEstatus().getIdEstatus().intValue())));
            }
        }
        return lstFiltro;
    }

    public Set<ItemCombo> getLstValoresFiltroUnidadAdministrativa() {
        Set<ItemCombo> lstFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());
        if (isListValid(getConsultaInsumosHelper().getLstInsumosResult())) {
            for (FecetInsumo insumo : getConsultaInsumosHelper().getLstInsumosResult()) {
                lstFiltro.add(new ItemCombo(insumo.getFececUnidadAdministrativa().getNombre(), insumo.getFececUnidadAdministrativa().getIdUnidadAdministrativa().intValue()));
            }
        }
        return lstFiltro;
    }

    public void seleccionarRetroalimentacion() {
        if (getConsultaInsumosHelper().getRetroalimentacionSeleccionada() != null) {
            logger.error("no es nulla la retro");
        }
    }

    public void seleccionarRechazo() {
        if (getConsultaInsumosHelper().getRechazoSeleccionado() != null) {
            logger.error("no es nulla la retro");
        }
    }

    private boolean isListValid(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }

}
