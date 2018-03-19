/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ContadortPropuestasDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.helper.PropuestasDaoHelper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.ContadorPropuestasDaoSql;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.roles.EmpleadoDTO;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FiltroPropuestas;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("contadorPropuestasDao")
public class ContadorPropuestasDaoImpl extends BaseJDBCDao<FecetPropuesta> implements ContadortPropuestasDao {

    private static final long serialVersionUID = -7214200612534175549L;

    @Override
    public Integer countPropuestasXRfcCreacionEstatus(FiltroPropuestas filtroDao) {
        if (filtroDao != null && filtroDao.getLstProgramadores() != null && filtroDao.getEstatusFiltro() != null) {
            List<String> lstRfc = new ArrayList<String>();
            for (EmpleadoDTO programador : filtroDao.getLstProgramadores()) {
                lstRfc.add(programador.getRfc());
            }

            String query = ContadorPropuestasDaoSql.SQL_COUNT_PROP_X_RFCCREACION_ESTATUS.replace(ContadorPropuestasDaoSql.CONDICION_RFC_CREACION, PropuestasDaoHelper.getSQLRFCCreacion(lstRfc));

            if (filtroDao.getUnidadAdmtvaDesahogoFiltro() != null && !filtroDao.getUnidadAdmtvaDesahogoFiltro().isEmpty()) {
                query = query.replace(ContadorPropuestasDaoSql.CONDICION_ID_ARACE, PropuestasDaoHelper.getSQLIdArace(filtroDao.getUnidadAdmtvaDesahogoFiltro()));
            } else {
                query = query.replace("AND P.ID_ARACE IN ( {ID_ARACE} )", "");
            }

            if (!filtroDao.getEstatusFiltro().isEmpty()) {
                query = query.replace(ContadorPropuestasDaoSql.CONDICION_ID_ESTATUS, PropuestasDaoHelper.getSQLEstatus(filtroDao.getEstatusFiltro()));
            } else {
                query = query.replace("AND P.ID_ESTATUS IN ({ID_ESTATUS})", "");
            }

            if (filtroDao.getLstAccionesPropuesta() != null) {
                query = query.replace(ContadorPropuestasDaoSql.INNER, ContadorPropuestasDaoSql.SQL_INNER_ACCION_PROPUESTAS);
                query = query.concat(ContadorPropuestasDaoSql.SQL_CONDICION_ID_ACCION).replace(ContadorPropuestasDaoSql.CONDICION_ID_ACCION, PropuestasDaoHelper.getSQLAcciones(filtroDao.getLstAccionesPropuesta()));
            } else {
                query = query.replace(ContadorPropuestasDaoSql.INNER, "");
            }

            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query);

            while (srs.next()) {
                return srs.getInt(ContadorPropuestasDaoSql.COLUM_TOTAL);
            }

        }
        return 0;
    }

    @Override
    public Integer countPropuestasXIdCreacionEstatus(FiltroPropuestas filtroDao) {
        if (filtroDao != null && filtroDao.getLstProgramadores() != null && filtroDao.getEstatusFiltro() != null) {
            List<Integer> lstIds = new ArrayList<Integer>();
            for (EmpleadoDTO programador : filtroDao.getLstProgramadores()) {
                lstIds.add(programador.getIdEmpleado().intValue());
            }

            String query = ContadorPropuestasDaoSql.SQL_COUNT_PROP_X_ID_PROGRAMADOR_ESTATUS.replace(ContadorPropuestasDaoSql.CONDICION_ID_PROGRAMADOR, PropuestasDaoHelper.getSQLIdProgramador(lstIds));

            if (filtroDao.getUnidadAdmtvaDesahogoFiltro() != null && !filtroDao.getUnidadAdmtvaDesahogoFiltro().isEmpty()) {
                query = query.replace(ContadorPropuestasDaoSql.CONDICION_ID_ARACE, PropuestasDaoHelper.getSQLIdArace(filtroDao.getUnidadAdmtvaDesahogoFiltro()));
            } else {
                query = query.replace("AND P.ID_ARACE IN ( {ID_ARACE} )", "");
            }

            if (!filtroDao.getEstatusFiltro().isEmpty()) {
                query = query.replace(ContadorPropuestasDaoSql.CONDICION_ID_ESTATUS, PropuestasDaoHelper.getSQLEstatus(filtroDao.getEstatusFiltro()));
            } else {
                query = query.replace("AND P.ID_ESTATUS IN ({ID_ESTATUS})", "");
            }

            if (filtroDao.getLstAccionesPropuesta() != null) {
                query = query.replace(ContadorPropuestasDaoSql.INNER, ContadorPropuestasDaoSql.SQL_INNER_ACCION_PROPUESTAS);
                query = query.concat(ContadorPropuestasDaoSql.SQL_CONDICION_ID_ACCION).replace(ContadorPropuestasDaoSql.CONDICION_ID_ACCION, PropuestasDaoHelper.getSQLAcciones(filtroDao.getLstAccionesPropuesta()));
            } else {
                query = query.replace(ContadorPropuestasDaoSql.INNER, "");
            }

            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(query);

            while (srs.next()) {
                return srs.getInt(ContadorPropuestasDaoSql.COLUM_TOTAL);
            }

        }
        return 0;
    }

}
