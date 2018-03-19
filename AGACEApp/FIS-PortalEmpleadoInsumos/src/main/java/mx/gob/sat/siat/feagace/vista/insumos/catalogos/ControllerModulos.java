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
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececModulos;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ControllerModulos extends GrupoUnidadReglaModelMB {

    private static final long serialVersionUID = 4444139227582749819L;

    protected void initModulos() {
        try {
            setModuloNew(new FececModulos("", ""));
            setLstModulos(getServiceCatUnidadAdminXModulo().getLstModulos(getEmpleadoSession()));
            setLstModuloUnidadesAdmin(getServiceCatUnidadAdminXModulo().getLstUnidaesAdminXGeneralModulo((getEmpleadoSession().getIdAdmGral() != null ? new BigDecimal(getEmpleadoSession().getIdAdmGral()) : BigDecimal.ONE)));
            getServiceCatUnidadAdminXModulo().llenarDetalleUnidadAdmin(getMapUnidades(), getLstModuloUnidadesAdmin());
            setLstUnidadesAdminDualModel(new DualListModel<AraceDTO>(new ArrayList<AraceDTO>(), new ArrayList<AraceDTO>()));
        } catch (CatalogosServiceException ex) {
            logger.error(ex.getMessage(), ex);
            addErrorMessage(MSG_ERROR_INIT);
            logger.error(ex.getMessage(), ex);
        }
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            FececModulos modulo = ((FececModulos) event.getObject());
            if (modulo != null) {
                getServiceCatUnidadAdminXModulo().actualizar(modulo);
                addMessage(MSG_EXITO_GUARDAR);
                setLstModulos(getServiceCatUnidadAdminXModulo().getLstModulos(getEmpleadoSession()));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    public void onRowDelete() {
        try {
            if (getModuloSeleccion() != null && moduloSinAsignacion(getModuloSeleccion())) {
                getServiceCatUnidadAdminXModulo().eliminarModulo(getModuloSeleccion());
                addMessage(MSG_EXITO_ELIMINAR);
                setLstModulos(getServiceCatUnidadAdminXModulo().getLstModulos(getEmpleadoSession()));
            } else {
                addWarningMessage(MSG_MODULO_ASIGNADO);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    private boolean moduloSinAsignacion(FececModulos moduloSeleccion) {
        try {
            List<ModuloUnidadAdmin> lstUnidadXModulo = getServiceCatUnidadAdminXModulo().getLstUnidaesAdminXGeneralModulo(new BigDecimal(getEmpleadoSession().getIdAdmGral()), moduloSeleccion.getIdModulo());
            return !(lstUnidadXModulo != null && !lstUnidadXModulo.isEmpty());
        } catch (CatalogosServiceException ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    public void onTransfer(TransferEvent event) {
        logger.info("onTransfer");
    }

    public void cargarUnidadesDeModulo() {
        if (getModuloSelecion() != null && getModuloSelecion() > 0) {

            try {

                List<ModuloUnidadAdmin> lstUnidadXModulo = getServiceCatUnidadAdminXModulo().
                        getLstUnidaesAdminXGeneralModulo(new BigDecimal(getEmpleadoSession().getIdAdmGral()), getModuloSeleccionado(getModuloSelecion()).getIdModulo());

                getServiceCatUnidadAdminXModulo().llenarDetalleUnidadAdmin(getMapUnidades(), lstUnidadXModulo);

                Map<ListasAsignacionEnum, List<AraceDTO>> mapLst = getServiceCatUnidadAdminXModulo().
                        getListasDeAsignacion(lstUnidadXModulo, getLstUnidadesAdmin());

                setLstUnidadesSrc(mapLst.get(ListasAsignacionEnum.LST_X_ASIGNAR));
                setLstUnidadesTarget(mapLst.get(ListasAsignacionEnum.LST_ASIGNADA));
                setLstUnidadesAdminDualModel(new DualListModel<AraceDTO>(mapLst.get(ListasAsignacionEnum.LST_X_ASIGNAR), mapLst.get(ListasAsignacionEnum.LST_ASIGNADA)));
                RequestContext.getCurrentInstance().update(":formUnidadXGeneral:pickListUnidadModulo");

            } catch (CatalogosServiceException ex) {
                logger.error(ex.getMessage(), ex);
                addErrorMessage(ex.getMessage());
            }
        } else {
            setLstUnidadesAdminDualModel(new DualListModel<AraceDTO>(new ArrayList<AraceDTO>(), new ArrayList<AraceDTO>()));
        }
    }

    public void agregarUnidades() {
        setModuloSelecion(BigDecimal.ZERO.intValue());
        cargarUnidadesDeModulo();
    }

    public void guardarRelacion() {
        try {
            getServiceCatUnidadAdminXModulo().guardarRelacionUnidadModulo(getLstUnidadesAdminDualModel().getSource(), getLstUnidadesAdminDualModel().getTarget(), getEmpleadoSession().getIdAdmGral(), getModuloSelecion());
            setLstModuloUnidadesAdmin(getServiceCatUnidadAdminXModulo().getLstUnidaesAdminXGeneralModulo((getEmpleadoSession().getIdAdmGral() != null ? new BigDecimal(getEmpleadoSession().getIdAdmGral()) : BigDecimal.ONE)));
            getServiceCatUnidadAdminXModulo().llenarDetalleUnidadAdmin(getMapUnidades(), getLstModuloUnidadesAdmin());
            RequestContext.getCurrentInstance().update(":formUnidadXGeneral:tblUnidadXModulo");
            addMessage(MSG_EXITO_GUARDAR);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(e.getMessage());
        }

    }

    private FececModulos getModuloSeleccionado(Integer idModulo) {
        if (idModulo != null) {
            for (FececModulos modulo : getLstModulos()) {
                if (modulo.getIdModulo().intValue() == idModulo) {
                    return modulo;
                }
            }
        }

        FececModulos mod = new FececModulos();
        mod.setIdModulo(BigDecimal.ONE);
        return mod;

    }

    public boolean getFlgBtnGuardatRelacion() {
        return getLstUnidadesAdminDualModel() != null && getModuloSelecion() != null && getModuloSelecion() > 0;
    }

    public boolean getFlgBtnGuardarModulo() {
        return ((getModuloNew() != null) && (getModuloNew().getNombre() != null) && (!getModuloNew().getNombre().isEmpty()));
    }

    public boolean getFlgBtnRelacion() {
        return getLstModulos() != null && !getLstModulos().isEmpty();
    }

    public void addModulo() {
        try {
            getServiceCatUnidadAdminXModulo().agregarModulo(getModuloNew());
            addMessage(MSG_EXITO_GUARDAR);
            setLstModulos(getServiceCatUnidadAdminXModulo().getLstModulos(getEmpleadoSession()));
            setModuloNew(new FececModulos("", ""));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_GUARDAR);
        }
    }

}
