package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FeceaDocumentoAdmDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaDocumentoAdmMapper;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;
import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.FececActosAdm;

@Repository("feceaDocumentoAdmDao")
public class FeceaDocumentoAdmDaoImpl extends BaseJDBCDao<DocumentoActoAdministrativo> implements FeceaDocumentoAdmDao {

    /**
     *
     */
    private static final long serialVersionUID = -3759912138007508905L;

    @Override
    public List<DocumentoActoAdministrativo> obtenerDocumentosActoAdm(FececActosAdm actoAdministrativo) {
        List<DocumentoActoAdministrativo> lstDocumentos = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM FECEA_DOCUMENTO_ADM \n");
        sql.append("WHERE ID_NYV = ? AND FECHA_FIN IS NULL");

        lstDocumentos = getJdbcTemplateBase().query(sql.toString(), new FeceaDocumentoAdmMapper(),
                actoAdministrativo.getIdNyv());
        return lstDocumentos != null && !lstDocumentos.isEmpty() ? lstDocumentos : null;
    }

}
