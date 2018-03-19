/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocRetroPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.DocRetroalimentacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.DocRetroalimentacionSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.DocRetroalimentacionDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("fecetDocRetroPropuestaDao")
public class FecetDocRetroPropuestaDaoImpl extends
        BaseJDBCDao<DocRetroalimentacionDTO> implements FecetDocRetroPropuestaDao {

    private static final long serialVersionUID = -6732804346918134548L;

    /**
     *
     * @param docRetro
     * @return
     */
    @Override
    public int insert(DocRetroalimentacionDTO docRetro) {

        Object[] params = new Object[]{docRetro.getIdRetroalimentacion(),
            docRetro.getIdPropuesta(),
            docRetro.getRutaArchivo(),
            docRetro.getBlnEstatus().getValue()};

        return getJdbcTemplateBase().update(DocRetroalimentacionSQL.INSERT, params);

    }

    public List<DocRetroalimentacionDTO> obtenerDocumentoByIdRetro(BigDecimal idRetro) {
        StringBuilder query = new StringBuilder();
        query.append("select ID_RETROALIMENTACION, ID_PROPUESTA, RUTA_ARCHIVO, FECHA_CREACION, ID_DOC_RETRO FROM FECET_DOC_RETRO_PROPUESTA \n");
        query.append("WHERE ID_RETROALIMENTACION = ? \n");

        return getJdbcTemplateBase().query(query.toString(), new DocRetroalimentacionMapper(), idRetro);
    }

}
