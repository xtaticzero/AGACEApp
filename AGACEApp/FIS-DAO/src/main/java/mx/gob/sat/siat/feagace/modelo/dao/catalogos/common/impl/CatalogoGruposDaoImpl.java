/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoGruposDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.GrupoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoAdministracion;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("catalogoGruposDao")
public class CatalogoGruposDaoImpl extends BaseJDBCDao<GrupoAdministracion> implements CatalogoGruposDao {

    private static final long serialVersionUID = 1826601668338054334L;

    /**
     * Insertar una nuevo grupo en el catalogo
     *
     * @param grupo
     */
    @Override
    public void insert(GrupoAdministracion grupo) {
        if (grupo != null && grupo.getNombre() != null && grupo.getDescripcion() != null) {
            grupo.setFechaFin(null);
            Object[] args = {grupo.getNombre(), grupo.getDescripcion(), grupo.getCentral()};
            getJdbcTemplateBase().update(SQL_INSERT, args);
        }
    }

    /**
     * Borrar un grupo del catalogo
     *
     * @param grupo
     */
    @Override
    public void delete(GrupoAdministracion grupo) {
        if (grupo != null && grupo.getIdGrupo() != null) {
            Object[] args = {grupo.getIdGrupo()};
            getJdbcTemplateBase().update(SQL_DELETE, args);
        }
    }

    /**
     *
     * @param grupo
     */
    @Override
    public void update(GrupoAdministracion grupo) {
        if (grupo != null && grupo.getIdGrupo() != null && grupo.getNombre() != null && grupo.getDescripcion() != null) {
            grupo.setCentral(grupo.getCentral() != null);
            Object[] args = {grupo.getNombre(), grupo.getDescripcion(), grupo.getCentral(), grupo.getIdGrupo()};
            getJdbcTemplateBase().update(SQL_UPDATE, args);
        }
    }

    /**
     * Busqueta de todos los grupos del catalogo
     *
     * @return
     */
    @Override
    public List<GrupoAdministracion> findAll() {
        return getJdbcTemplateBase().query(SQL_FIND_ALL, new GrupoMapper());
    }

    /**
     * Busqueda de grupo por nombre
     *
     * @param nombre
     * @return
     */
    @Override
    public GrupoAdministracion findByNombre(String nombre) {
        if (nombre != null) {
            List<GrupoAdministracion> lstReglas = getJdbcTemplateBase().query(SQL_FIND_BY_NOMBRE, new GrupoMapper(), nombre);
            if (lstReglas != null && !lstReglas.isEmpty()) {
                return lstReglas.get(BigDecimal.ZERO.intValue());
            }
        }
        return null;
    }

    /**
     * Busqueda de grupo por idGrupo
     *
     * @param idGrupo
     * @return
     */
    @Override
    public GrupoAdministracion findByID(BigDecimal idGrupo) {
        if (idGrupo != null) {
            List<GrupoAdministracion> lstReglas = getJdbcTemplateBase().query(SQL_FIND_BY_NOMBRE, new GrupoMapper(), idGrupo);
            if (lstReglas != null && !lstReglas.isEmpty()) {
                return lstReglas.get(BigDecimal.ZERO.intValue());
            }
        }
        return null;
    }

}
