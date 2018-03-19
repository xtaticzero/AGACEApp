/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ControllerGrupos extends ControllerModulos {

    private static final long serialVersionUID = 4003835953624758601L;

    protected void initGrupos() {
        try {
            setNewGrupo(new GrupoAdministracion());
            setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
        } catch (CatalogosServiceException ex) {
            addErrorMessage(MSG_ERROR_INIT);
            logger.error(ex.getMessage(), ex);
        }
    }

    public void grupOnRowEdit(RowEditEvent event) {
        try {
            GrupoAdministracion grupo = ((GrupoAdministracion) event.getObject());
            if (grupo != null) {
                getServiceCatGrupoDeUnidadAdmin().actualizarGrupo(grupo);
                addMessage(MSG_EXITO_GUARDAR);
                setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    public void groupOnRowDelete() {
        try {
            if (getGrupoSeleccionado() != null && grupoSinAsignacion(getGrupoSeleccionado())) {
                getServiceCatGrupoDeUnidadAdmin().borrarGrupo(getGrupoSeleccionado());
                addMessage(MSG_EXITO_ELIMINAR);
                setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
            } else {
                addWarningMessage(MSG_MODULO_ASIGNADO);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    private boolean grupoSinAsignacion(GrupoAdministracion grupoSeleccion) {
        try {
            if (grupoSeleccion != null) {
                return !getServiceCatGrupoDeUnidadAdmin().esGrupoAsignado(getEmpleadoSession(), grupoSeleccion.getIdGrupo());
            }
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    public boolean getFlgBtnGuardarGrupo() {
        return ((getNewGrupo() != null) && (getNewGrupo().getNombre() != null) && (!getNewGrupo().getNombre().isEmpty()) && (getNewGrupo().getDescripcion() != null));
    }

    public void addGrupo() {
        try {
            getServiceCatGrupoDeUnidadAdmin().guardarGrupo(getNewGrupo());
            addMessage(MSG_EXITO_GUARDAR);
            setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
            setNewGrupo(new GrupoAdministracion());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_GUARDAR);
        }
    }

}
