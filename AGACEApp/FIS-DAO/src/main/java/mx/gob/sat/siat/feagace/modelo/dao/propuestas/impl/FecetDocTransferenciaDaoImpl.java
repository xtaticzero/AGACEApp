/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocTransferenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.DocTransferenciaSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
@Repository("fecetDocTransferenciaDao")
public class FecetDocTransferenciaDaoImpl extends
        BaseJDBCDao<FecetDocTransferencia> implements FecetDocTransferenciaDao {

    private static final long serialVersionUID = 7141983268007986411L;

    @Override
    public int insert(FecetDocTransferencia docTransferencia) {

        Object[] parameters = new Object[]{docTransferencia.getIdPropuesta(),
            docTransferencia.getIdTrnsferencia(),
            docTransferencia.getNombreArchivo(),
            docTransferencia.getRutaArchivo(),
            docTransferencia.getBlnActivo().getValue()};

        return getJdbcTemplateBase().update(DocTransferenciaSQL.INSERT_DOC_TRANSFERENCIA,
                parameters);

    }

    @Override
    public List<FecetDocTransferencia> getDocTransferenciaXPropuestaTransferencia(Long idPropuesta, Long idTrnsferencia) {
        Object[] parameters;
        parameters = new Object[]{idPropuesta, idTrnsferencia};

        return getJdbcTemplateBase().query(DocTransferenciaSQL.ARCHIVOS_TRANSFERENCIA_X_IDPROPUESTA_IDTRANSFERENCIA,
                parameters, new FecetDocTransferenciaMapper());
    }

    public List<FecetDocTransferencia> obtenerDocumentosByIdTransferencia(BigDecimal idTransferencia, BigDecimal blnEstatus) {
        return getJdbcTemplateBase().query(DocTransferenciaSQL.ARCHIVOS_TRANSFERENCIA_X_ID_TRANSFERENCIA_ESTATUS, new FecetDocTransferenciaMapper(), idTransferencia, blnEstatus);
    }

    public List<FecetDocTransferencia> obtenerDocumentosAllByIdTransferencia(BigDecimal idTransferencia) {
        return getJdbcTemplateBase().query(DocTransferenciaSQL.ARCHIVOS_TRANSFERENCIA_X_ID_TRANSFERENCIA, new FecetDocTransferenciaMapper(), idTransferencia);
    }

}
