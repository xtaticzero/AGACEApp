/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl;

import java.math.BigDecimal;
import java.util.List;
import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.FecetDocumentoDao;
import mx.gob.sat.siat.feagace.modelo.dao.common.mapper.FecetDocumentoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jose.aguilar
 */
@Repository("fecetDocumentoDao")
public class FecetDocumentoDaoImpl extends BaseJDBCDao<FecetDocumento> implements FecetDocumentoDao {

    private static final String SQL_SELECT = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, ID_INSUMO, ID_PROPUESTA, ID_ORDEN, RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN FROM " + getTableName();
    private static final long serialVersionUID = 3683416315670597942L;

    @Override
    public void insertar(FecetDocumento fecetDocumento) {
        fecetDocumento.setIdDocumento(getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOCUMENTO.NEXTVAL FROM DUAL", BigDecimal.class));
        StringBuilder query = new StringBuilder();
        fecetDocumento.setRutaArchivo(fecetDocumento.getRutaArchivo() + fecetDocumento.getNombre());
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_DOCUMENTO, ID_TIPO_DOCUMENTO, ID_INSUMO, ID_PROPUESTA, ID_ORDEN, RUTA_ARCHIVO, FECHA_CREACION, FECHA_FIN) VALUES ( ?, ?, ?, ?, ?, ?, SYSDATE, ? )");
        getJdbcTemplateBase().update(query.toString(), fecetDocumento.getIdDocumento(), fecetDocumento.getIdTipoDocumento(),
                fecetDocumento.getIdInsumo(), fecetDocumento.getIdPropuesta(), fecetDocumento.getIdOrden(), fecetDocumento.getRutaArchivo(), fecetDocumento.getFechaBaja());
    }

    @Override
    public void update(FecetDocumento fececDocumento) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_DOCUMENTO = ?, ID_TIPO_DOCUMENTO = ?, ID_INSUMO = ?, ID_PROPUESTA = ?, ID_ORDEN = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, FECHA_FIN = ? WHERE ID_DOCUMENTO = ?");
        getJdbcTemplateBase().update(query.toString(), fececDocumento.getIdDocumento(), fececDocumento.getIdTipoDocumento(), fececDocumento.getIdInsumo(),
                fececDocumento.getIdPropuesta(), fececDocumento.getIdOrden(), fececDocumento.getRutaArchivo(), fececDocumento.getFechaCreacion(),
                fececDocumento.getFechaBaja(), fececDocumento.getIdDocumento());

    }

    public static String getTableName() {
        return "FECET_DOCUMENTO";
    }

    @Override
    public List<FecetDocumento> obtenerDocumentosByIdInsumo(BigDecimal idInsumo) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_INSUMO = ? AND FECHA_FIN IS NULL");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocumentoMapper(), idInsumo);
    }
}
