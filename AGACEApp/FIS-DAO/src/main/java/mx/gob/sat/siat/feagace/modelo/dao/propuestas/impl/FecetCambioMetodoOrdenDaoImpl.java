package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetCambioMetodoOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetSuplencia;

import org.springframework.stereotype.Repository;

@Repository("FecetCambioMetodoOrdenDao")
public class FecetCambioMetodoOrdenDaoImpl extends BaseJDBCDao<FecetSuplencia> implements FecetCambioMetodoOrdenDao {

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_CAMBIO_METODO";
    }

    @Override
    public void updateNuevaPropuesta(BigDecimal folio, BigDecimal orden) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_PROPUESTA_NUEVA = ?, ID_ESTATUS=112 where ID_ORDEN_ORIGEN=?  ");

        getJdbcTemplateBase().update(query.toString(), folio, orden);
    }
}
