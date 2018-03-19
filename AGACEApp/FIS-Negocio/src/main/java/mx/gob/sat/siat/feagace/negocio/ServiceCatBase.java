/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoGrupoDeUnidadAdminDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoGruposDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoReglaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.modelo.enums.catalogos.ListasAsignacionEnum;
import mx.gob.sat.siat.feagace.negocio.ServicioCatalogosInsumosAbstract;
import mx.gob.sat.siat.feagace.negocio.exception.CatalogosServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ServiceCatBase extends ServicioCatalogosInsumosAbstract {

    private static final long serialVersionUID = -2135057773951052945L;

    @Autowired
    @Qualifier("catalogoGruposDao")
    private transient CatalogoGruposDao catalogoGruposDao;

    @Autowired
    @Qualifier("catalogoReglaDao")
    private transient CatalogoReglaDao catalogoReglaDao;

    @Autowired
    @Qualifier("catGrupoDeUnidadAdminDao")
    private transient CatalogoGrupoDeUnidadAdminDao catGrupoDeUnidadAdminDao;

    protected Map<ListasAsignacionEnum, List<AraceDTO>> llenarDetalleUnidadAdminXModulo(List<ModuloUnidadAdmin> lstUnidadesSinDetalle, List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException {

        if (lstUnidadesXGeneral != null && !lstUnidadesXGeneral.isEmpty()) {
            List<AraceDTO> copyOfLstUnidXGeneral = new ArrayList<AraceDTO>(lstUnidadesXGeneral.size());
            try {
                for (int i = 0; i < lstUnidadesXGeneral.size(); i++) {
                    copyOfLstUnidXGeneral.add(new AraceDTO());
                }
                Collections.copy(copyOfLstUnidXGeneral, lstUnidadesXGeneral);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            Map<ListasAsignacionEnum, List<AraceDTO>> mapListas = new EnumMap<ListasAsignacionEnum, List<AraceDTO>>(ListasAsignacionEnum.class);
            if (lstUnidadesSinDetalle == null || lstUnidadesSinDetalle.isEmpty()) {
                mapListas.put(ListasAsignacionEnum.LST_X_ASIGNAR, copyOfLstUnidXGeneral);
                mapListas.put(ListasAsignacionEnum.LST_ASIGNADA, new ArrayList<AraceDTO>());
                return mapListas;
            }

            mapListas.put(ListasAsignacionEnum.LST_X_ASIGNAR, new ArrayList<AraceDTO>());
            mapListas.put(ListasAsignacionEnum.LST_ASIGNADA, new ArrayList<AraceDTO>());

            for (ModuloUnidadAdmin next : lstUnidadesSinDetalle) {
                if (lstUnidadesXGeneral.contains(next.getUnidadAdministrativa()) && !mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).contains(next.getUnidadAdministrativa())) {
                    mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).add(next.getUnidadAdministrativa());
                }
            }

            for (AraceDTO unidad : copyOfLstUnidXGeneral) {
                if (!mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).contains(unidad) && !mapListas.get(ListasAsignacionEnum.LST_X_ASIGNAR).contains(unidad)) {
                    mapListas.get(ListasAsignacionEnum.LST_X_ASIGNAR).add(unidad);
                }
            }

            return mapListas;
        }
        return new EnumMap<ListasAsignacionEnum, List<AraceDTO>>(ListasAsignacionEnum.class);
    }

    protected Map<ListasAsignacionEnum, List<AraceDTO>> llenarDetalleUnidadAdminXGrupo(List<AraceDTO> lstUnidades, List<AraceDTO> lstUnidadesXGeneral) throws CatalogosServiceException {
        if (lstUnidadesXGeneral != null && !lstUnidadesXGeneral.isEmpty()) {
            List<AraceDTO> copyOfLstUnidXGeneral = new ArrayList<AraceDTO>(lstUnidadesXGeneral.size());
            try {
                for (AraceDTO item : lstUnidadesXGeneral) {
                    copyOfLstUnidXGeneral.add(new AraceDTO());
                    logger.info(item.getNombre());
                }
                Collections.copy(copyOfLstUnidXGeneral, lstUnidadesXGeneral);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            Map<ListasAsignacionEnum, List<AraceDTO>> mapListas = new EnumMap<ListasAsignacionEnum, List<AraceDTO>>(ListasAsignacionEnum.class);
            if (lstUnidades == null || lstUnidades.isEmpty()) {
                mapListas.put(ListasAsignacionEnum.LST_X_ASIGNAR, copyOfLstUnidXGeneral);
                mapListas.put(ListasAsignacionEnum.LST_ASIGNADA, new ArrayList<AraceDTO>());
                return mapListas;
            }

            mapListas.put(ListasAsignacionEnum.LST_X_ASIGNAR, new ArrayList<AraceDTO>());
            mapListas.put(ListasAsignacionEnum.LST_ASIGNADA, new ArrayList<AraceDTO>());

            for (AraceDTO next : lstUnidades) {
                if (lstUnidadesXGeneral.contains(next) && !mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).contains(next)) {
                    mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).add(next);
                }
            }

            for (AraceDTO unidad : copyOfLstUnidXGeneral) {
                if (!mapListas.get(ListasAsignacionEnum.LST_ASIGNADA).contains(unidad) && !mapListas.get(ListasAsignacionEnum.LST_X_ASIGNAR).contains(unidad)) {
                    mapListas.get(ListasAsignacionEnum.LST_X_ASIGNAR).add(unidad);
                }
            }

            return mapListas;
        }
        return new EnumMap<ListasAsignacionEnum, List<AraceDTO>>(ListasAsignacionEnum.class);
    }

    public void llenarDetalleUnidadAdmin(Map<Integer, AraceDTO> mapUnidades, List<ModuloUnidadAdmin> lstModuloUnidad) throws CatalogosServiceException {
        if (lstModuloUnidad != null && !lstModuloUnidad.isEmpty()) {
            Iterator<ModuloUnidadAdmin> iterator;
            iterator = lstModuloUnidad.iterator();
            while (iterator.hasNext()) {
                ModuloUnidadAdmin next = iterator.next();
                if (mapUnidades.containsKey(next.getUnidadAdministrativa().getIdArace())) {
                    AraceDTO detalle = mapUnidades.get(next.getUnidadAdministrativa().getIdArace());
                    next.setUnidadAdministrativa(detalle);
                }
            }
        }
    }

    public void llenarDetalleUnidad(Map<Integer, AraceDTO> mapUnidades, List<AraceDTO> lstUnidadSinDetalle) throws CatalogosServiceException {
        if (lstUnidadSinDetalle != null && !lstUnidadSinDetalle.isEmpty()) {
            Iterator<AraceDTO> iterator;
            iterator = lstUnidadSinDetalle.iterator();
            while (iterator.hasNext()) {
                AraceDTO next = iterator.next();
                if (mapUnidades.containsKey(next.getIdArace())) {
                    AraceDTO detalle = mapUnidades.get(next.getIdArace());
                    next.setCentral(detalle.getCentral());
                    next.setNombre(detalle.getNombre());
                    next.setSede(detalle.getSede());
                }
            }
        }
    }

    public CatalogoGrupoDeUnidadAdminDao getCatGrupoDeUnidadAdminDao() {
        return catGrupoDeUnidadAdminDao;
    }

    public void setCatGrupoDeUnidadAdminDao(CatalogoGrupoDeUnidadAdminDao catGrupoDeUnidadAdminDao) {
        this.catGrupoDeUnidadAdminDao = catGrupoDeUnidadAdminDao;
    }

    public CatalogoGruposDao getCatalogoGruposDao() {
        return catalogoGruposDao;
    }

    public void setCatalogoGruposDao(CatalogoGruposDao catalogoGruposDao) {
        this.catalogoGruposDao = catalogoGruposDao;
    }

    public CatalogoReglaDao getCatalogoReglaDao() {
        return catalogoReglaDao;
    }

    public void setCatalogoReglaDao(CatalogoReglaDao catalogoReglaDao) {
        this.catalogoReglaDao = catalogoReglaDao;
    }

}
