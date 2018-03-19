/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FecetDocumento;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author jose.aguilar
 */
public class FecetDocumentoMapper implements ParameterizedRowMapper<FecetDocumento> {

    private static final String COLUMN_ID_DOCUMENTO = "ID_DOCUMENTO";
    private static final String COLUMN_ID_TIPO_DOCUMENTO = "ID_TIPO_DOCUMENTO";
    private static final String COLUMN_ID_INSUMO = "ID_INSUMO";
    private static final String COLUMN_ID_PROPUESTA = "ID_PROPUESTA";
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";
    private static final String COLUMN_RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String COLUMN_FECHA_CREACION = "FECHA_CREACION";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";

    @Override
    public FecetDocumento mapRow(ResultSet rs, int i) throws SQLException {
        FecetDocumento fecetDocumento = new FecetDocumento();
        fecetDocumento.setIdDocumento(rs.getBigDecimal(COLUMN_ID_DOCUMENTO));
        fecetDocumento.setIdTipoDocumento(rs.getBigDecimal(COLUMN_ID_TIPO_DOCUMENTO));
        fecetDocumento.setIdInsumo(rs.getBigDecimal(COLUMN_ID_INSUMO));
        fecetDocumento.setIdPropuesta(rs.getBigDecimal(COLUMN_ID_PROPUESTA));
        fecetDocumento.setIdOrden(rs.getBigDecimal(COLUMN_ID_ORDEN));
        fecetDocumento.setRutaArchivo(UtileriasMapperDao.getPathFromAbsolutePath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        fecetDocumento.setNombre(UtileriasMapperDao.getNameFileFromPath(rs.getString(COLUMN_RUTA_ARCHIVO)));
        fecetDocumento.setFechaCreacion(rs.getTimestamp(COLUMN_FECHA_CREACION));
        fecetDocumento.setFechaBaja(rs.getTimestamp(COLUMN_FECHA_FIN));
        return fecetDocumento;
    }

}
