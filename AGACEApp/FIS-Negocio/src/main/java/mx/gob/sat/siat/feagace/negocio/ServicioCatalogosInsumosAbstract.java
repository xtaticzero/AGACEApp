/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio;

import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FececModulosDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.UnidadAdminModuloDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import mx.gob.sat.siat.feagace.negocio.exception.EmpleadoServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ServicioCatalogosInsumosAbstract extends EmpleadoServiceImpl {

    private static final long serialVersionUID = 8797580573863192616L;

    @Autowired
    @Qualifier("modulosDaoImpl")
    private FececModulosDao modulosDaoImpl;

    @Autowired
    @Qualifier("unidadAdminModuloDao")
    private UnidadAdminModuloDao unidadAdminModuloDao;

    public Map<Integer, AraceDTO> init(EmpleadoDTO empCompleto) throws CatalogosServiceException {
        if (empCompleto != null && empCompleto.getIdAdmGral() != null) {
            try {
                return getMapAraceDTOFromLstUnidadAdmin(empCompleto);
            } catch (EmpleadoServiceException ex) {
                throw new CatalogosServiceException("init", ex);
            }
        }
        return null;
    }

    public FececModulosDao getModulosDaoImpl() {
        return modulosDaoImpl;
    }

    public void setModulosDaoImpl(FececModulosDao modulosDaoImpl) {
        this.modulosDaoImpl = modulosDaoImpl;
    }

    public UnidadAdminModuloDao getUnidadAdminModuloDao() {
        return unidadAdminModuloDao;
    }

    public void setUnidadAdminModuloDao(UnidadAdminModuloDao unidadAdminModuloDao) {
        this.unidadAdminModuloDao = unidadAdminModuloDao;
    }

}
