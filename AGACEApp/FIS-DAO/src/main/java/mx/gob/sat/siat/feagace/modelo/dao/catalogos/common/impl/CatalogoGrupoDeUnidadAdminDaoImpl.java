/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.CatalogoGrupoDeUnidadAdminDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.GrupoUnidadesAdminXGeneralMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.GrupoUnidadesAdminXGeneral;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.AraceDTO;
import mx.gob.sat.siat.feagace.negocio.util.validacion.ValidacionParametrosUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("catGrupoDeUnidadAdminDao")
public class CatalogoGrupoDeUnidadAdminDaoImpl extends BaseJDBCDao<GrupoUnidadesAdminXGeneral> implements CatalogoGrupoDeUnidadAdminDao {

    private static final long serialVersionUID = -2625687635188013172L;

    /**
     * Insertar un grupo de unidades administrativas
     *
     * @param grupo
     */
    @Override
    public void insert(GrupoUnidadesAdminXGeneral grupo) {
        if (grupo != null && grupo.getIdAdmGral() != null && grupo.getGrupo() != null && grupo.getReglaNegocio() != null) {

            if (grupo.getLstUnidadesAdministrativas() != null && !grupo.getLstUnidadesAdministrativas().isEmpty()) {
                for (AraceDTO unidadAdm : grupo.getLstUnidadesAdministrativas()) {
                    Object[] args = {unidadAdm.getIdArace(), grupo.getIdAdmGral(), grupo.getReglaNegocio().getIdRegla(), grupo.getGrupo().getIdGrupo()};

                    BigDecimal idGrupoUnidadTmp = getCoincidenciaDeRegistro(unidadAdm.getIdArace(), grupo.getIdAdmGral(), grupo.getReglaNegocio().getIdRegla(), grupo.getGrupo().getIdGrupo());
                    if (idGrupoUnidadTmp != null) {
                        activar(idGrupoUnidadTmp);
                        continue;
                    }
                    getJdbcTemplateBase().update(SQL_INSERT, args);
                }
            }
            logger.error("CatalogoGrupoDeUnidadAdminDaoImpl.insert");
            logger.error("La lista de unidades administrativas is null");

        }
    }

    /**
     * Extraer las coincidencias de un grupo de unidades administrativas
     *
     * @param idArace
     * @param idAdmGral
     * @param idRegla
     * @param idGrupo
     * @return
     */
    private BigDecimal getCoincidenciaDeRegistro(Integer idArace, BigDecimal idAdmGral, BigDecimal idRegla, BigDecimal idGrupo) {
        String query = "SELECT GRP_UNID.ID_GRUPO_UNIDAD_GENERAL IDREGISTRO \n"
                + "FROM FECEA_GRUPO_UNIDAD_GENERAL GRP_UNID \n"
                + "WHERE \n"
                + "GRP_UNID.ID_UNIDAD_ADMINISTRATIVA = ? \n"
                + "AND GRP_UNID.ID_GENERAL = ?\n"
                + "AND GRP_UNID.ID_REGLA = ?\n"
                + "AND GRP_UNID.ID_GRUPO = ?";

        Object[] args = {idArace, idAdmGral, idRegla, idGrupo};

        BigDecimal idRegistro = null;
        SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query, args);

        while (srs.next()) {
            idRegistro = srs.getBigDecimal("IDREGISTRO");
        }

        return idRegistro;
    }

    /**
     * Borrar un grupo de unidades administrativas
     *
     * @param grupo
     */
    @Override
    public void delete(GrupoUnidadesAdminXGeneral grupo) {

        boolean[] condiciones = {grupo != null, grupo.getIdAdmGral() != null, grupo.getGrupo() != null, grupo.getReglaNegocio() != null};

        if (!ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
            return;
        }

        if (grupo.getLstUnidadesAdministrativas() != null && !grupo.getLstUnidadesAdministrativas().isEmpty()) {
            for (AraceDTO unidadAdm : grupo.getLstUnidadesAdministrativas()) {
                BigDecimal idGrupoUnidadTmp = getCoincidenciaDeRegistro(unidadAdm.getIdArace(), grupo.getIdAdmGral(), grupo.getReglaNegocio().getIdRegla(), grupo.getGrupo().getIdGrupo());
                if (idGrupoUnidadTmp != null) {
                    getJdbcTemplateBase().update(SQL_DELETE, idGrupoUnidadTmp);
                }
            }
        }
        logger.error("CatalogoGrupoDeUnidadAdminDaoImpl.delete");
        logger.error("La lista de unidades administrativas is null");
    }

    private void activar(BigDecimal idGrupoUnidadGral) {
        if (idGrupoUnidadGral != null) {
            getJdbcTemplateBase().update(SQL_ACTIVAR, idGrupoUnidadGral);
        }
    }

    @Override
    public void update(GrupoUnidadesAdminXGeneral grupo) {
        boolean[] condiciones = {grupo != null, grupo.getIdAdmGral() != null, grupo.getGrupo() != null, grupo.getReglaNegocio() != null};

        if (!ValidacionParametrosUtil.seCumplenTodasLasCondicion(condiciones)) {
            return;
        }

        if (grupo.getLstUnidadesAdministrativas() != null && !grupo.getLstUnidadesAdministrativas().isEmpty()) {
            for (AraceDTO unidadAdm : grupo.getLstUnidadesAdministrativas()) {
                BigDecimal idGrupoUnidadTmp = getCoincidenciaDeRegistro(unidadAdm.getIdArace(), grupo.getIdAdmGral(), grupo.getReglaNegocio().getIdRegla(), grupo.getGrupo().getIdGrupo());
                if (idGrupoUnidadTmp != null) {
                    getJdbcTemplateBase().update(SQL_UPDATE, grupo.getGrupo().getIdGrupo(), grupo.getReglaNegocio().getIdRegla(), unidadAdm.getIdArace(), idGrupoUnidadTmp);
                }
            }
        }

        logger.error("CatalogoGrupoDeUnidadAdminDaoImpl.update");
        logger.error("La lista de unidades administrativas is null");
    }

    @Override
    public List<GrupoUnidadesAdminXGeneral> findByIdAdmGralIdGrupoIdRegla(BigDecimal idAdmGral, BigDecimal idGrupo, BigDecimal idRegla) {
        StringBuilder query = new StringBuilder(SQL_HEDER_FIND.concat(SQL_WHERE));
        List<Object> parametros = new ArrayList<Object>();
        boolean flgOneParameter = false;

        if (idAdmGral != null) {
            query.append(SQL_PARAMETER_ID_GENERAL);
            parametros.add(idAdmGral);
            flgOneParameter = true;
        }
        if (idGrupo != null) {
            if (flgOneParameter) {
                query.append(SQL_AND);
            }
            query.append(SQL_PARAMETER_ID_GRUPO);
            parametros.add(idGrupo);
            flgOneParameter = true;
        }
        if (idRegla != null) {
            if (flgOneParameter) {
                query.append(SQL_AND);
            }
            query.append(SQL_PARAMETER_ID_REGLA);
            parametros.add(idRegla);
            flgOneParameter = true;
        }

        if (flgOneParameter) {
            query.append(SQL_AND);
        }
        query.append(SQL_REGISTROS_ACTIVOS);
        query.append(SQL_ORDER_BY_IDGRUPO_UNIDAD_GENERAL);

        try {
            List<List<GrupoUnidadesAdminXGeneral>> lstResult = getJdbcTemplateBase().query(query.toString(), new GrupoUnidadesAdminXGeneralMapper(), parametros.toArray());
            if (lstResult != null && !lstResult.isEmpty()) {

                for (GrupoUnidadesAdminXGeneral result : lstResult.get(0)) {
                    parametros = new ArrayList<Object>();
                    parametros.add(idAdmGral);
                    parametros.add(result.getGrupo().getIdGrupo());
                    parametros.add(result.getReglaNegocio().getIdRegla());
                    result.setLstUnidadesAdministrativas(getJdbcTemplateBase().query(SQL_LST_UNIDADES, new RowMapper<AraceDTO>() {

                        @Override
                        public AraceDTO mapRow(ResultSet rs, int i) throws SQLException {
                            AraceDTO unidadAdm = new AraceDTO();
                            unidadAdm.setIdArace(rs.getInt(GRP_UNID_ID_UNIDAD_ADMIN));
                            return unidadAdm;
                        }
                    }, parametros.toArray()));
                }
                return lstResult.get(0);
            }

            return new ArrayList<GrupoUnidadesAdminXGeneral>();
        } catch (Exception empResDatEx) {
            logger.error(empResDatEx.getMessage(), empResDatEx);
            return new ArrayList<GrupoUnidadesAdminXGeneral>();
        }

    }

    @Override
    public boolean asignacionesActivas(BigDecimal idAdmGral, BigDecimal idGrupo, BigDecimal idRegla) {
        if (idAdmGral != null) {
            List<GrupoUnidadesAdminXGeneral> lstResult;
            StringBuilder query = new StringBuilder(SQL_HEDER_FIND.concat(SQL_WHERE).concat(SQL_PARAMETER_ID_GENERAL).concat(SQL_AND));
            List<Object> parametros = new ArrayList<Object>();
            parametros.add(idAdmGral);
            if (idGrupo != null) {
                parametros.add(idGrupo);
                query.append(SQL_PARAMETER_ID_GRUPO);
            } else if (idRegla != null) {
                parametros.add(idRegla);
                query.append(SQL_PARAMETER_ID_REGLA);
            }
            query.append(SQL_AND);
            query.append(SQL_REGISTROS_ACTIVOS);
            lstResult = getJdbcTemplateBase().query(query.toString(), new GrupoUnidadesAdminXGeneralMapper(), parametros.toArray());

            return lstResult != null && !lstResult.isEmpty();
        }
        return false;
    }

}
