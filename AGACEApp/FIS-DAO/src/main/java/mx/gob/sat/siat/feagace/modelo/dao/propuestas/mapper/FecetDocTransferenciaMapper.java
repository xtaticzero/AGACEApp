/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FecetDocTransferenciaMapper implements RowMapper<FecetDocTransferencia> {

    @Override
    public FecetDocTransferencia mapRow(ResultSet rs, int i) throws SQLException {
        FecetDocTransferencia docTransferencia = new FecetDocTransferencia();

        docTransferencia.setIdPropuesta(rs.getBigDecimal("ID_PROPUESTA"));
        docTransferencia.setIdTrnsferencia(rs.getBigDecimal("ID_TRANSFERENCIA"));
        docTransferencia.setIdDocTransferencia(rs.getBigDecimal("ID_DOC_TRANSFERENCIA"));

        String ruta = rs.getString("RUTA_ARCHIVO");
        docTransferencia.setRutaArchivo(ruta);
        docTransferencia.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath(ruta));

        docTransferencia.setFechaCreacion(rs.getDate("FECHA_CREACION"));

        return docTransferencia;
    }

}
