/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.UnidadAdminModuloDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.UnidadAdminModuloMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.ModuloUnidadAdmin;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanoEnum;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("unidadAdminModuloDao")
public class UnidadAdminModuloDaoImpl extends BaseJDBCDao<ModuloUnidadAdmin> implements UnidadAdminModuloDao {

    private static final long serialVersionUID = 5779810357029982321L;

    @Override
    public void insert(ModuloUnidadAdmin unidadModulo) {

        boolean[] arrArgument = {unidadModulo != null
            && unidadModulo.getModulo() != null
            && unidadModulo.getUnidadGeneral() != null
            && unidadModulo.getUnidadAdministrativa() != null};

        if (ValidacionParametrosUtil.seCumplenTodasLasCondicion(arrArgument)) {
            Object[] params = {unidadModulo.getUnidadAdministrativa().getIdArace(),
                unidadModulo.getModulo().getIdModulo(),
                unidadModulo.getUnidadGeneral(), EstadoBooleanoEnum.TRUE.getEstado()};

            getJdbcTemplateBase().update(SQL_INSERT, params);
        }
    }

    @Override
    public void delete(ModuloUnidadAdmin unidadModulo) {
        if (unidadModulo != null) {
            getJdbcTemplateBase().update(SQL_DELETE, unidadModulo.getIdModuloUnidad());
        }
    }

    @Override
    public void update(ModuloUnidadAdmin unidadModulo) {
        if (unidadModulo != null) {
            getJdbcTemplateBase().update(SQL_UPDATE,
                    unidadModulo.getModulo().getIdModulo(),
                    unidadModulo.getUnidadAdministrativa().getIdArace(),
                    unidadModulo.getBlnActivo() != null && unidadModulo.getBlnActivo() ? 1 : 0,
                    unidadModulo.getFechaFin(),
                    unidadModulo.getIdModuloUnidad());
        }
    }

    @Override
    public Integer count(ModuloUnidadAdmin unidadModulo) {
        Integer numeroDeClaves = null;
        SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(SQL_SELECT_COUNT, unidadModulo.getModulo().getIdModulo(), unidadModulo.getUnidadGeneral(), unidadModulo.getUnidadAdministrativa().getIdArace());

        while (srs.next()) {
            numeroDeClaves = srs.getInt(SQL_CONDITION_COUNT);
        }

        return numeroDeClaves;
    }

    @Override
    public ModuloUnidadAdmin getIdModuloUnidad(ModuloUnidadAdmin unidadModulo) {
        SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(SQL_ID_TABLE, unidadModulo.getModulo().getIdModulo(), unidadModulo.getUnidadGeneral(), unidadModulo.getUnidadAdministrativa().getIdArace());

        while (srs.next()) {
            unidadModulo.setIdModuloUnidad(srs.getBigDecimal("ID_ADACE_MODULO"));
        }

        return unidadModulo;

    }

    @Override
    public List<ModuloUnidadAdmin> findAll(BigDecimal idUnidadGeneral) {
        if (idUnidadGeneral != null) {
            return getJdbcTemplateBase().query(SQL_FIND_ALL.replace(SQL_CONDITION_MODULO, ""), new UnidadAdminModuloMapper(), idUnidadGeneral);
        }
        return new ArrayList<ModuloUnidadAdmin>();
    }

    @Override
    public List<ModuloUnidadAdmin> findByModulo(BigDecimal idUnidadGeneral, BigDecimal idModulo) {
        if (idUnidadGeneral != null) {
            return getJdbcTemplateBase().query(SQL_FIND_ALL.replace(SQL_CONDITION_MODULO, SQL_MODULO), new UnidadAdminModuloMapper(), idUnidadGeneral, idModulo);
        }
        return new ArrayList<ModuloUnidadAdmin>();
    }

}
