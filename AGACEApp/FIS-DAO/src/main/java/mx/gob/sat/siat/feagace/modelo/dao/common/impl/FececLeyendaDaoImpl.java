package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.FececLeyendaDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FececLeyendaMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.FececLeyenda;

@Repository("fececLeyendaDao")
public class FececLeyendaDaoImpl extends
        BaseJDBCDao<FececLeyenda> implements FececLeyendaDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String SQL_SELECT = "SELECT ID_LEYENDA,NOMBRE_LEYENDA,DESCRIPCION,FECHA_INICIO,FECHA_FIN,BLN_ACTIVO FROM FECEC_LEYENDAS \n";

    public List<FececLeyenda> obtenerLeyendaById(BigDecimal... idLeyenda) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);

        if (idLeyenda != null && idLeyenda.length > 0) {
            query.append(" WHERE ID_LEYENDA  IN (");
            Iterator<BigDecimal> iterador = Arrays.asList(idLeyenda).iterator();
            while (iterador.hasNext()) {
                query.append(iterador.next());
                if (iterador.hasNext()) {
                    query.append(", ");
                }
            }
            query.append(" ) \n");
        }
        query.append("ORDER BY ID_LEYENDA ASC");
        return getJdbcTemplateBase().query(query.toString(), new FececLeyendaMapper());
    }

}
