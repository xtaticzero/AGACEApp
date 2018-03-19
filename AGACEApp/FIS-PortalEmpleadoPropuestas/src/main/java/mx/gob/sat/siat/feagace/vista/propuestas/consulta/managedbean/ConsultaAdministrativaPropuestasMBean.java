/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.propuestas.consulta.managedbean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.enums.AgrupadorEstatusPropuestasEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEmpleadoEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoMetodoEnum;
import mx.gob.sat.siat.feagace.negocio.bo.consulta.ConsultaEjecutivaPropuestasRule;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.consulta.ConsultaEjecutivaPropuestasServiceException;
import mx.gob.sat.siat.feagace.vista.propuestas.consulta.ConsultaPropuestasAbstractMBean;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXClaveItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.ComparatorXDescripcionItemFiltro;
import mx.gob.sat.siat.feagace.vista.util.FacesUtil;
import mx.gob.sat.siat.feagace.vista.util.ItemCombo;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ViewScoped
@ManagedBean(name = "consultaAdminPropuestasMB")
public class ConsultaAdministrativaPropuestasMBean extends ConsultaPropuestasAbstractMBean {

    private static final long serialVersionUID = -1286991268397310132L;

    public void consultarXUnidadAdminSeleccionada() {
        setControlDeAcceso(true);
        if (getConsultaPropuestasHelper().isFlgMostrarUnidadesDesahogo()) {
            setUnidadAdminByID(getConsultaPropuestasHelper().getIdUnidadAdminSeleccionada());
            if (getConsultaPropuestasHelper().getUnidadAdminSeleccionada() == null) {
                getConsultaPropuestasHelper().getFiltro().setUnidadAdmtvaDesahogoFiltro(getConsultaPropuestasHelper().getConsultaPropuestasBO().getLstUnidadesAdministrativasDesahogo());
            } else {
                addUnidadesDesagoAlFiltro(getConsultaPropuestasHelper().getUnidadAdminSeleccionada());
            }
        } else if (getConsultaPropuestasHelper().isFlgMostrarOpciones()) {
            if (getConsultaPropuestasHelper().getIdOpcion() == null) {
                FacesUtil.addErrorMessage(null, "Es necesario seleccionar una opci\u00f3n.");
                return;
            }
            getConsultaPropuestasHelper().getConsultaPropuestasBO().setProgramacion(Integer.valueOf("1").equals(getConsultaPropuestasHelper().getIdOpcion()));

            getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_CONSULTA_PROPUESTAS);
            getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
            getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
            getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
        } else if (getConsultaPropuestasHelper().isFlgMostrarComboPerfiles()) {
            try {
                if (getConsultaPropuestasHelper().getConsultaPropuestasBO().getTipoRolSeleccionado() == null) {
                    FacesUtil.addErrorMessage(null, "Es necesario seleccionar un rol.");
                    return;
                }
                getConsultaPropuestasHelper().getConsultaPropuestasBO().setRolEmpleado(getConsultaPropuestasHelper().getConsultaPropuestasBO().getTipoRolSeleccionado());
                switch (getConsultaPropuestasHelper().getConsultaPropuestasBO().getTipoRolSeleccionado()) {
                    case FIRMANTE:
                    case AUDITOR:
                        getConsultaPropuestasHelper().getConsultaPropuestasBO().setProgramacion(false);
                        break;
                    case ADMINISTRADOR:
                    case SUBADMINISTRADOR:
                        getConsultaPropuestasHelper().getConsultaPropuestasBO().setProgramacion(true);
                        break;
                    case CENTRAL:
                        prerenderCentral();
                        return;
                    default:
                        FacesUtil.addErrorMessage(null, "No hay accion seleccionada.");
                        return;

                }
                getConsultaPropuestasHelper().setFlgMostrarComboPerfiles(false);
                getConsultaPropuestasHelper().getConsultaPropuestasBO().setRolEmpleado(getConsultaPropuestasHelper().getConsultaPropuestasBO().getTipoRolSeleccionado());
                getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ESTATUS_DISPONIBLES_CONSULTA_PROPUESTAS);
                getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                getConsultaPropuestasHelper().getConsultaPropuestasBO().setRule(ConsultaEjecutivaPropuestasRule.ROL_EMPLEADO_MAYOR_JERARQUIA_CNSULTA_PROPUESTAS);
                getConsultaPropuestasHelper().getConsultaPropuestasBO().getRule().process(getConsultaPropuestasHelper().getConsultaPropuestasBO());
                getConsultaEjecutivaPropuestasService().validarExistenciaTipoEmpleado(getConsultaPropuestasHelper().getConsultaPropuestasBO().getEmpleadoConsulta(), getConsultaPropuestasHelper().getConsultaPropuestasBO().getTipoRolSeleccionado());
            } catch (EmpleadoServiceException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
        try {
            getConsultaEjecutivaPropuestasService().consultarPropuestas(getConsultaPropuestasHelper().getConsultaPropuestasBO(), getConsultaPropuestasHelper().getFiltro());
            mostrarPanelXCategorias();
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public void consultarXGupoEstatusSeleccionado() {
        setControlDeAcceso(true);
        try {
            if (getConsultaPropuestasHelper().getGrupoEstatusSeleccionado() != null) {
                getConsultaEjecutivaPropuestasService().consultarPropuestasXEstatusoGrupo(getConsultaPropuestasHelper().getConsultaPropuestasBO(), getConsultaPropuestasHelper().getFiltro(), getConsultaPropuestasHelper().getGrupoEstatusSeleccionado().getKey());
                if (isNivelEmpleadoMinimo()) {
                    getConsultaPropuestasHelper().setLstPropuestasResult(getConsultaPropuestasHelper().getConsultaPropuestasBO().getLstPropuestasXFiltro());
                    mostrarPanelPropuestas();
                    return;
                }
                mostrarPanelXSubordinados();
                if (getConsultaPropuestasHelper().getGrupoEstatusSeleccionado().getKey().equals(AgrupadorEstatusPropuestasEnum.PROPUESTA_RECHAZADA)) {
                    addErrorMessage(null, MSJ_PROPUESTAS_RECHAZADAS);
                }
            }
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public Set<ItemCombo> getLstMetodosFiltro() {
        Set<ItemCombo> lstMetodosFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(getConsultaPropuestasHelper().getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : getConsultaPropuestasHelper().getLstPropuestasResult()) {
                lstMetodosFiltro.add(new ItemCombo(propuesta.getFeceaMetodo().getAbreviatura(), propuesta.getFeceaMetodo().getIdMetodo().intValue()));
            }
        }

        return lstMetodosFiltro;
    }

    public Set<ItemCombo> getLstPrioridadFiltro() {
        Set<ItemCombo> lstPrioridadFiltro = new TreeSet<ItemCombo>(new ComparatorXDescripcionItemFiltro());

        if (isListValid(getConsultaPropuestasHelper().getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : getConsultaPropuestasHelper().getLstPropuestasResult()) {
                if (propuesta.getPrioridadSugerida() != null) {
                    lstPrioridadFiltro.add(new ItemCombo(propuesta.getPrioridadSugerida(), Integer.valueOf(propuesta.getPrioridadSugerida())));
                }
            }
        }

        return lstPrioridadFiltro;
    }

    public Set<ItemCombo> getLstUnidadAdministrativaFiltro() {
        Set<ItemCombo> lstUnidadAdministrativaFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(getConsultaPropuestasHelper().getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : getConsultaPropuestasHelper().getLstPropuestasResult()) {
                lstUnidadAdministrativaFiltro.add(new ItemCombo(propuesta.getFececArace().getNombre(), propuesta.getFececArace().getIdArace().intValue()));
            }
        }

        return lstUnidadAdministrativaFiltro;
    }

    public Set<ItemCombo> getLstEstatusFiltro() {
        Set<ItemCombo> lstEstatusFiltro = new TreeSet<ItemCombo>(new ComparatorXClaveItemFiltro());

        if (isListValid(getConsultaPropuestasHelper().getLstPropuestasResult())) {
            for (FecetPropuesta propuesta : getConsultaPropuestasHelper().getLstPropuestasResult()) {
                lstEstatusFiltro.add(new ItemCombo(propuesta.getEstatusXGrupo().getDescripcion(), propuesta.getEstatusXGrupo().getIdGrupo()));
            }
        }

        return lstEstatusFiltro;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean filterByPresuntiva(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        try {
            boolean sentencia = ((Comparable) (((BigDecimal) value).intValue())).compareTo(Integer.valueOf(filterText)) >= 0;

            return sentencia;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public void limpiarFiltros() {
        try {
            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formConsultaEjecutivaPropuestas:tablaDetallePropuestas");
            dataTable.reset();
            if (!dataTable.getFilters().isEmpty()) {
                logger.info("dataTable.getFilters().isEmpty() :" + dataTable.getFilters().isEmpty());
                dataTable.getFilteredValue().clear();
                dataTable.setFilteredValue(null);
                dataTable.setFilters(null);
                dataTable.setFilterMetadata(null);
                dataTable.reset();
            }
        } catch (Exception e) {
            logger.error("no pudo limpiar" + e.getMessage());
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.reset(":formConsultaEjecutivaPropuestas:tablaDetallePropuestas");
        requestContext.update(":formConsultaEjecutivaPropuestas");
    }

    public void consultarTotalGeneral() {
        getConsultaPropuestasHelper().setFlgPantallaGrupoEstatus(true);
        getConsultaPropuestasHelper().setLstPropuestasResult(getConsultaPropuestasHelper().getConsultaPropuestasBO().getLstPropuestasResult());
        mostrarPanelPropuestas();
    }

    public void consultarXEmpleadoSeleccionado() {
        try {
            setControlDeAcceso(true);
            setPantallaTotalEmpleados(true);
            getConsultaEjecutivaPropuestasService().consultarPropuestasXEmpleadoSubordinado(getConsultaPropuestasHelper().getConsultaPropuestasBO(), getConsultaPropuestasHelper().getEmpleadoSeleccionado().getKey());
            getConsultaPropuestasHelper().setLstPropuestasResult(getConsultaPropuestasHelper().getConsultaPropuestasBO().getLstPropuestasXFiltro());
            mostrarPanelPropuestas();
        } catch (ConsultaEjecutivaPropuestasServiceException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public List<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>> getDetalleEstatus() {
        if (getConsultaPropuestasHelper().getConsultaPropuestasBO().getDetalleXEstatus() != null) {
            Set<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>> estatusSet
                    = getConsultaPropuestasHelper().getConsultaPropuestasBO().getDetalleXEstatus().entrySet();
            return new ArrayList<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>>(estatusSet);
        }
        return new ArrayList<Map.Entry<AgrupadorEstatusPropuestasEnum, Integer>>();
    }

    public List<Map.Entry<EmpleadoDTO, Integer>> getDetalleEmpleado() {
        if (getConsultaPropuestasHelper().getConsultaPropuestasBO().getDetalleXEmpleado() != null) {
            Set<Map.Entry<EmpleadoDTO, Integer>> empleadoSet
                    = getConsultaPropuestasHelper().getConsultaPropuestasBO().getDetalleXEmpleado().entrySet();
            return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>(empleadoSet);
        }
        return new ArrayList<Map.Entry<EmpleadoDTO, Integer>>();
    }

    public void regresarDeDetallePropuestas() {
        limpiarFiltros();
        mostrarPanelPropuestas();
    }

    public void regresarDeLstPropuestas() {
        limpiarFiltros();

        if (isNivelEmpleadoMinimo()) {
            mostrarPanelXCategorias();
            return;
        }

        if (getConsultaPropuestasHelper().isFlgPantallaGrupoEstatus()) {
            mostrarPanelXCategorias();
            return;
        }
        if (isPantallaTotalEmpleados()) {
            consultarXGupoEstatusSeleccionado();
            mostrarPanelXSubordinados();
        }
    }

    private boolean isListValid(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }

    private boolean isNivelEmpleadoMinimo() {
        return getConsultaPropuestasHelper().getConsultaPropuestasBO().getRolEmpleado().equals(TipoEmpleadoEnum.AUDITOR)
                || getConsultaPropuestasHelper().getConsultaPropuestasBO().getRolEmpleado().equals(TipoEmpleadoEnum.SUBADMINISTRADOR);
    }

    public boolean getEsORG() {
        return (getConsultaPropuestasHelper().getPropuestaSeleccionada() != null) && (getConsultaPropuestasHelper().getPropuestaSeleccionada().getFeceaMetodo().getIdMetodo().longValue() == (TipoMetodoEnum.ORG.getId()));
    }
}
