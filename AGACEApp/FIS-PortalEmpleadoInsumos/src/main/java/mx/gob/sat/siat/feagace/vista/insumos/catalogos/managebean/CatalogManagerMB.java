/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.vista.insumos.catalogos.managebean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mx.gob.sat.siat.base.constante.ConstantesSesion;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.vista.insumos.catalogos.ControllerGrupoUnidadesXRegla;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@ManagedBean(name = "catalogManagerMB")
@ViewScoped
public class CatalogManagerMB extends ControllerGrupoUnidadesXRegla {

    private static final long serialVersionUID = -1519932557208105943L;

    @PostConstruct
    public void init() {
        try {
            setEmpleadoSession(getAccesoEmpleado(getRFCSession()));
            setMapUnidades(getServiceCatUnidadAdminXModulo().init(getEmpleadoSession()));
            setLstUnidadesAdmin((List<AraceDTO>) getSession().getAttribute(ConstantesSesion.LIST_UNIDADES_ADMIN));
            initModulos();
            initGrupos();
            initReglas();
            initGrupoUnidadesXRegla();
        } catch (Exception ex) {
            addErrorMessage(MSG_ERROR_INIT);
            logger.error(ex.getMessage(), ex);
        }
    }

}
