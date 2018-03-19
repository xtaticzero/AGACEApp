/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.preparedstatement;

import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.preparedstatement.api.PropuestaPreparedStatementCreator;
import mx.gob.sat.siat.feagace.modelo.dao.util.constantes.TipoDaoEnum;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetPropuesta;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public final class PreparedStatementCreatorFactory {

    private static final Logger LOG = Logger.getLogger(PropuestaPreparedStatementCreator.class);

    private static final int CASE_1 = 1;

    private PreparedStatementCreatorFactory() {
    }

    public static PreparedStatementCreator getStatementCreator(TipoDaoEnum tipo, Object param) throws SQLException {
        PreparedStatementCreator result;
        int valorTipo = tipo.getIdTipo();

        try {
            switch (valorTipo) {
                case CASE_1:
                    result = new PropuestaPreparedStatementCreator((FecetPropuesta) param);
                    break;
                default:
                    result = null;
                    break;
            }
        } catch (ClassCastException ex) {
            LOG.error("El Tipo de insert no corresponde a su parametro");
            LOG.error(ex);
            result = null;
        }

        return result;
    }
}
