/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class FecetTransferenciaDetalleMapper implements RowMapper<FecetTransferencia> {

    private static final String ID_TRANSFERENCIA = "ID_TRANSFERENCIA";
    private static final String ID_PROPUESTA = "ID_PROPUESTA";
    private static final String ID_ARACE_ORIGEN = "ID_ARACE_ORIGEN";
    private static final String ID_ARACE_DESTINO = "ID_ARACE_DESTINO";
    private static final String ID_EMPLEADO = "ID_EMPLEADO";
    private static final String ID_ESTATUS = "ID_ESTATUS";
    private static final String TRANSFERENCIA_BLN_ESTATUS = "TRANSFERENCIA_BLN_ESTATUS";
    private static final String ID_DOC_TRANSFERENCIA = "ID_DOC_TRANSFERENCIA";
    private static final String RUTA_ARCHIVO = "RUTA_ARCHIVO";
    private static final String OBSERVACIONES = "OBSERVACIONES";

    @Override
    public FecetTransferencia mapRow(ResultSet rs, int i) throws SQLException {
        FecetTransferencia transferencia = new FecetTransferencia();

        do {
            if (rs.getBigDecimal(ID_TRANSFERENCIA).equals(transferencia.getIdTransferencia())) {
                obtenerDocumento(rs, transferencia.getLstDocumentos());
            } else {
                transferencia = new FecetTransferencia();

                transferencia.setIdPropuesta(rs.getBigDecimal(ID_PROPUESTA));
                transferencia.setIdEmpleado(rs.getBigDecimal(ID_EMPLEADO));
                transferencia.setIdAraceOrigen(rs.getBigDecimal(ID_ARACE_ORIGEN));
                transferencia.setIdAraceDestino(rs.getBigDecimal(ID_ARACE_DESTINO));
                transferencia.setEstatus(rs.getBigDecimal(ID_ESTATUS));
                transferencia.setIdTransferencia(rs.getBigDecimal(ID_TRANSFERENCIA));
                transferencia.setBlnEstatus(rs.getBigDecimal(TRANSFERENCIA_BLN_ESTATUS));
                transferencia.setObservaciones(rs.getString(OBSERVACIONES));
                transferencia.setLstDocumentos(new ArrayList<FecetDocTransferencia>());

                obtenerDocumento(rs, transferencia.getLstDocumentos());

            }
        } while (rs.next());

        return transferencia;

    }

    private void obtenerDocumento(ResultSet rs, List<FecetDocTransferencia> lstDocExpedientes) throws SQLException {

        FecetDocTransferencia doc = new FecetDocTransferencia();

        doc.setIdPropuesta(rs.getBigDecimal(ID_PROPUESTA));
        doc.setRutaArchivo(rs.getString(RUTA_ARCHIVO));
        doc.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath((rs.getString(RUTA_ARCHIVO))));
        doc.setIdDocTransferencia(rs.getBigDecimal(ID_DOC_TRANSFERENCIA));

        lstDocExpedientes.add(doc);

    }

}
