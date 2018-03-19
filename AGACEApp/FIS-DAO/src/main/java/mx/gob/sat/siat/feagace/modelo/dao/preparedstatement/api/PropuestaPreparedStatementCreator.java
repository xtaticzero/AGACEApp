/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.preparedstatement.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dao.util.constantes.TipoDaoEnum;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class PropuestaPreparedStatementCreator implements PreparedStatementCreator {

    private static final Logger LOG = Logger.getLogger(PropuestaPreparedStatementCreator.class);

    private FecetPropuesta propuesta;
    private static final int PARAMETRO_INICIAL = 1;

    /**
     *
     * @param propuesta
     */
    public PropuestaPreparedStatementCreator(FecetPropuesta propuesta) {
        super();
        this.propuesta = propuesta;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        boolean fail = true;
        PreparedStatement ps = null;
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(PropuestasSQL.INSERTAR, new String[]{TipoDaoEnum.PROPUESTA_DAO.getNombreIdTabla()});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, propuesta.getIdContribuyente());
            ps.setObject(numParametro++, propuesta.getIdArace());
            ps.setObject(numParametro++, propuesta.getIdSubprograma());
            ps.setObject(numParametro++, propuesta.getIdMetodo());
            ps.setObject(numParametro++, propuesta.getIdRevision());
            ps.setObject(numParametro++, propuesta.getIdTipoPropuesta());
            ps.setObject(numParametro++, propuesta.getIdCausaProgramacion());
            ps.setObject(numParametro++, propuesta.getIdSector());
            ps.setObject(numParametro++, propuesta.getIdRegistro());
            ps.setObject(numParametro++, propuesta.getIdEstatus());
            ps.setObject(numParametro++, propuesta.getIdInsumo());
            ps.setObject(numParametro++, propuesta.getIdRegistroInsumo());
            ps.setObject(numParametro++, propuesta.getOrigenPropuestaId());
            ps.setObject(numParametro++, propuesta.getProgramadorId());
            //Se asignan fechas de la propuesta
            ps.setObject(numParametro++, UtileriasMapperDao.getDateLikeSQLFormat(propuesta.getFechaInicioPeriodo()));
            ps.setObject(numParametro++, UtileriasMapperDao.getDateLikeSQLFormat(propuesta.getFechaFinPeriodo()));
            ps.setObject(numParametro++, UtileriasMapperDao.getDateLikeSQLFormat(propuesta.getFechaPresentacion()));
            ps.setObject(numParametro++, UtileriasMapperDao.getDateLikeSQLFormat(propuesta.getFechaInforme()));
            ps.setObject(numParametro++, UtileriasMapperDao.getDateLikeSQLFormat(propuesta.getFechaBaja()));
            //Se asignan parametros de la propuesta
            ps.setObject(numParametro++, propuesta.getConsecutivoAnio());
            ps.setObject(numParametro++, String.valueOf(propuesta.getPrioridadSugerida()));
            ps.setObject(numParametro++, propuesta.getRfcCreacion());
            ps.setObject(numParametro++, propuesta.getRfcAdministrador());
            ps.setObject(numParametro++, propuesta.getRfcSubadministrador());
            ps.setObject(numParametro++, propuesta.getRfcAuditor());
            ps.setObject(numParametro++, propuesta.getRfcFirmante());
            ps.setObject(numParametro++, propuesta.getInformePresentacion());
            fail = false;
        } catch (SQLException ex) {
            LOG.error("No se pudo realizar la incercion:");
            LOG.error(ex);
            throw ex;
        } finally {
            if (fail) {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException warn) {
                    LOG.error(warn);
                    throw warn;
                }
            }
        }
        return ps;
    }

    public FecetPropuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(FecetPropuesta propuesta) {
        this.propuesta = propuesta;
    }

}
