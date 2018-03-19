/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ControllerGrupoUnidadesXRegla extends ControllerReglas {

    private static final long serialVersionUID = 1299360697721319569L;

    public void initGrupoUnidadesXRegla() {
        try {
            setIdGrupoSeleccionado(SIN_ASIGNACION);
            setIdReglaSeleccionada(SIN_ASIGNACION);
            setNewGrupoUnidades(new GrupoUnidadesAdminXGeneral());
            setLstGrupoUnidadesXRegla(getServiceCatGrupoDeUnidadAdmin().getLstGrupos(getEmpleadoSession()));
            setLstUnidadesAdminXGrupoDualModel(new DualListModel<AraceDTO>(new ArrayList<AraceDTO>(), new ArrayList<AraceDTO>()));
        } catch (CatalogosServiceException ex) {
            addErrorMessage(MSG_ERROR_INIT);
            logger.error(ex.getMessage(), ex);
        }
    }

    public void getGrupoByIdSeleccionado() {
        if (getIdGrupoSeleccionado() != null && getIdGrupoSeleccionado() != -1) {
            setGrupoDeUnidadesSeleccionado(validaGrupoById(getIdGrupoSeleccionado()));
        }
    }

    private GrupoAdministracion validaGrupoById(Integer idGrupoSeleccionado) {
        if (getLstGrupos() != null && !getLstGrupos().isEmpty()) {
            GrupoAdministracion grupTmp = new GrupoAdministracion();
            grupTmp.setIdGrupo(new BigDecimal(idGrupoSeleccionado));

            for (GrupoAdministracion grup : getLstGrupos()) {
                if (grup.getIdGrupo().intValue() == idGrupoSeleccionado) {
                    return grup;
                }
            }

        }
        return new GrupoAdministracion();
    }

    public void getReglaByIdSeleccionado() {
        if (getIdReglaSeleccionada() != null && getIdReglaSeleccionada() != -1) {
            setReglaDeUnidadesSeleccionada(validaReglaById(getIdReglaSeleccionada()));
        }
    }

    private Regla validaReglaById(Integer idReglaSeleccionada) {
        if (getLstReglas() != null && !getLstReglas().isEmpty()) {
            for (Regla regla : getLstReglas()) {
                if (regla.getIdRegla().intValue() == idReglaSeleccionada) {
                    return regla;
                }
            }

        }
        return new Regla();
    }

    public boolean isFlgBtnGuardatRelacionGrupo() {
        getReglaByIdSeleccionado();
        getGrupoByIdSeleccionado();

        return getGrupoDeUnidadesSeleccionado() != null && getIdGrupoSeleccionado() != SIN_ASIGNACION && getReglaDeUnidadesSeleccionada() != null && getIdReglaSeleccionada() != SIN_ASIGNACION;
    }

    public void cargarUnidadesDeGrupo() {
        try {
            if (isFlgBtnGuardatRelacionGrupo()) {
                setNewGrupoUnidades(
                        getServiceCatGrupoDeUnidadAdmin().getGrupoXGeneralXReglaGrupo(
                                getEmpleadoSession(),
                                getGrupoDeUnidadesSeleccionado().getIdGrupo(),
                                obtenerReglaEnum(getReglaDeUnidadesSeleccionada())));
                if (getNewGrupoUnidades() == null) {
                    setNewGrupoUnidades(new GrupoUnidadesAdminXGeneral());
                }
                getNewGrupoUnidades().setGrupo(getGrupoDeUnidadesSeleccionado());
                getNewGrupoUnidades().setReglaNegocio(getReglaDeUnidadesSeleccionada());

                getNewGrupoUnidades().setIdAdmGral(
                        getNewGrupoUnidades().getIdAdmGral() != null
                                ? getNewGrupoUnidades().getIdAdmGral()
                                : new BigDecimal(getEmpleadoSession().getIdAdmGral()));

                Map<ListasAsignacionEnum, List<AraceDTO>> mapLstAsignacion = getServiceCatGrupoDeUnidadAdmin().getListasDeAsignacion(
                        getEmpleadoSession(),
                        getGrupoDeUnidadesSeleccionado().getIdGrupo(),
                        obtenerReglaEnum(getReglaDeUnidadesSeleccionada()),
                        getLstUnidadesAdmin());
                getServiceCatGrupoDeUnidadAdmin().llenarDetalleUnidad(getMapUnidades(), mapLstAsignacion.get(ListasAsignacionEnum.LST_ASIGNADA));
                setLstUnidadesAdminXGrupoDualModel(new DualListModel<AraceDTO>(mapLstAsignacion.get(ListasAsignacionEnum.LST_X_ASIGNAR), mapLstAsignacion.get(ListasAsignacionEnum.LST_ASIGNADA)));
            } else {
                setNewGrupoUnidades(new GrupoUnidadesAdminXGeneral());
                setLstUnidadesAdminXGrupoDualModel(new DualListModel<AraceDTO>(new ArrayList<AraceDTO>(), new ArrayList<AraceDTO>()));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            setLstUnidadesAdminXGrupoDualModel(new DualListModel<AraceDTO>(new ArrayList<AraceDTO>(), new ArrayList<AraceDTO>()));
        }
    }

    public void agregarUnidadesAGrupo() {
        setIdGrupoSeleccionado(SIN_ASIGNACION);
        setIdReglaSeleccionada(SIN_ASIGNACION);
        cargarUnidadesDeGrupo();
    }

    public void guardarRelacionGrupo() {
        try {
            if (getNewGrupoUnidades() != null && getNewGrupoUnidades().getGrupo() != null) {
                if (!getLstUnidadesAdminXGrupoDualModel().getTarget().isEmpty()) {
                    getNewGrupoUnidades().setLstUnidadesAdministrativas(getLstUnidadesAdminXGrupoDualModel().getTarget());
                    getServiceCatGrupoDeUnidadAdmin().guardarGrupoUnidades(getNewGrupoUnidades());
                }

                if (!getLstUnidadesAdminXGrupoDualModel().getSource().isEmpty()) {
                    getNewGrupoUnidades().setLstUnidadesAdministrativas(getLstUnidadesAdminXGrupoDualModel().getSource());
                    getServiceCatGrupoDeUnidadAdmin().eliminarGrupoUnidades(getNewGrupoUnidades());
                }
                setLstGrupoUnidadesXRegla(getServiceCatGrupoDeUnidadAdmin().getLstGrupos(getEmpleadoSession()));
                addMessage(MSG_EXITO_GUARDAR);
                return;
            }
            addMessage(MSG_ERROR_GUARDAR);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            addMessage(MSG_ERROR_GUARDAR);
        }
    }

}
