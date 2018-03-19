/**
 *
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.documentos.FecetDocAsociadoDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetDocAsociadoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetDocAsociado;

/**
 * @author sergio.vaca
 *
 */
@Repository("fecetDocAsociadoDao")
public class FecetDocAsociadoDaoImpl extends BaseJDBCDao<FecetDocAsociado> implements FecetDocAsociadoDao {

    private static final long serialVersionUID = 1L;

    @Override
    public List<FecetDocAsociado> obtenerDocumentosPorAsociado(BigDecimal idAsociado) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT ID_DOC_ASOCIADO, ID_ASOCIADO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN ");
        query.append("\n FROM FECET_DOC_ASOCIADO ");
        query.append(" WHERE ID_ASOCIADO = ? ");
        query.append(" AND BLN_ACTIVO = 1 ");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocAsociadoMapper(), idAsociado);
    }

    @Override
    public void insertar(FecetDocAsociado dto) {
        if (dto.getIdDocAsociado() == null) {
            dto.setIdDocAsociado(getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_ASOCIADO.NEXTVAL FROM DUAL",
                    BigDecimal.class));
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO FECET_DOC_ASOCIADO (ID_DOC_ASOCIADO, ID_ASOCIADO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN) \n");
        query.append("VALUES (?,?,?,?,?,?)");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocAsociado(), dto.getIdAsociado(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getBlnActivo(), dto.getFechaFin());
    }

}
