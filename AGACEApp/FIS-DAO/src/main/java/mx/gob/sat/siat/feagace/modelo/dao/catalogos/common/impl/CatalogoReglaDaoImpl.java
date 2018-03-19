/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoReglaDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.ReglaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.Regla;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("catalogoReglaDao")
public class CatalogoReglaDaoImpl extends BaseJDBCDao<Regla> implements CatalogoReglaDao {

    private static final long serialVersionUID = 7171591656506662037L;

    /**
     * Insertar una nueva regla en el catalogo
     *
     * @param regla
     */
    @Override
    public void insert(Regla regla) {
        if (regla != null && regla.getClave() != null && regla.getDescripcion() != null) {
            regla.setFechaFin(null);
            Object[] args = {regla.getClave(), regla.getDescripcion(), regla.getFechaFin()};
            getJdbcTemplateBase().update(SQL_INSERT, args);
        }
    }

    /**
     * Borrar una regla del catalogo
     *
     * @param regla
     */
    @Override
    public void delete(Regla regla) {
        if (regla != null && regla.getIdRegla() != null) {
            Object[] args = {regla.getIdRegla()};
            getJdbcTemplateBase().update(SQL_DELETE, args);
        }
    }

    /**
     * Actualiza regla
     *
     * @param regla
     */
    @Override
    public void update(Regla regla) {
        if (regla != null && regla.getIdRegla() != null && regla.getClave() != null && regla.getDescripcion() != null) {
            Object[] args = {regla.getClave(), regla.getDescripcion(), regla.getIdRegla()};
            getJdbcTemplateBase().update(SQL_UPDATE, args);
        }
    }

    /**
     * Busqueta de todas las reglas del catalogo
     *
     * @return
     */
    @Override
    public List<Regla> findAll() {
        return getJdbcTemplateBase().query(SQL_FIND_ALL, new ReglaMapper());
    }

    /**
     * Busqueda de Regla por clave
     *
     * @param clave
     * @return
     */
    @Override
    public Regla findByClave(String clave) {
        if (clave != null) {
            List<Regla> lstReglas = getJdbcTemplateBase().query(SQL_FIND_BY_CLAVE, new ReglaMapper(), clave);
            if (lstReglas != null && !lstReglas.isEmpty()) {
                return lstReglas.get(BigDecimal.ZERO.intValue());
            }
        }
        return null;
    }

    /**
     * Busqueda de Regla por idRegla
     *
     * @param idRegla
     * @return
     */
    @Override
    public Regla findByID(BigDecimal idRegla) {
        if (idRegla != null) {
            List<Regla> lstReglas = getJdbcTemplateBase().query(SQL_FIND_BY_IDREGLA, new ReglaMapper(), idRegla);
            if (lstReglas != null && !lstReglas.isEmpty()) {
                return lstReglas.get(BigDecimal.ZERO.intValue());
            }
        }
        return null;
    }

}
