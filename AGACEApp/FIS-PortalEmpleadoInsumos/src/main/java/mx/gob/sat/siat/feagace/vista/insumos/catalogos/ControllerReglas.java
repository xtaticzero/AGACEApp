/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos;

import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import mx.gob.sat.siat.feagace.modelo.enums.ReglaEnum;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ControllerReglas extends ControllerGrupos {

    private static final long serialVersionUID = -756354022274092529L;

    protected void initReglas() {
        try {
            setNewRegla(new Regla());
            setLstReglas(getServiceCatGrupoDeUnidadAdmin().findAllRules());
        } catch (CatalogosServiceException ex) {
            addErrorMessage(MSG_ERROR_INIT);
            logger.error(ex.getMessage(), ex);
        }
    }

    public void ruleOnRowEdit(RowEditEvent event) {
        try {
            Regla regla = ((Regla) event.getObject());
            if (regla != null) {
                getServiceCatGrupoDeUnidadAdmin().actualizarRegla(regla);
                addMessage(MSG_EXITO_GUARDAR);
                setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    public void ruleOnRowDelete() {
        try {
            if (getReglaSeleccionada() != null && reglaSinAsignacion(getReglaSeleccionada())) {
                getServiceCatGrupoDeUnidadAdmin().borrarRegla(getReglaSeleccionada());
                addMessage(MSG_EXITO_ELIMINAR);
                setLstGrupos(getServiceCatGrupoDeUnidadAdmin().findAllGroups());
            } else {
                addWarningMessage(MSG_REGLA_ASIGNADA);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_ACTUALIZACION);
        }
    }

    private boolean reglaSinAsignacion(Regla reglaSeleccion) {
        try {
            if (reglaSeleccion != null) {
                return !getServiceCatGrupoDeUnidadAdmin().esReglaAsignada(getEmpleadoSession(), obtenerReglaEnum(reglaSeleccion));
            }
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    public boolean getFlgBtnGuardarRegla() {
        boolean[] condiciones = new boolean[]{((getNewRegla() != null)), ((getNewRegla().getClave() != null) && (!getNewRegla().getClave().isEmpty())), ((getNewRegla().getDescripcion() != null) && (!getNewRegla().getDescripcion().isEmpty()))};
        return ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones);
    }

    public void addRegla() {
        try {
            getServiceCatGrupoDeUnidadAdmin().guardarRegla(getNewRegla());
            addMessage(MSG_EXITO_GUARDAR);
            setLstReglas(getServiceCatGrupoDeUnidadAdmin().findAllRules());
            setNewRegla(new Regla());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            addErrorMessage(MSG_ERROR_GUARDAR);
        }
    }
    
    protected ReglaEnum obtenerReglaEnum(Regla reglaDeUnidadesSeleccionada) {
        if (reglaDeUnidadesSeleccionada != null && reglaDeUnidadesSeleccionada.getIdRegla() != null) {
            for (ReglaEnum typeRegla : ReglaEnum.values()) {
                if((reglaDeUnidadesSeleccionada.getIdRegla().compareTo(typeRegla.getNumRegla()))==0){
                    return typeRegla;
                }
            }
        }
        
        return ReglaEnum.RNA037;
    }
}
