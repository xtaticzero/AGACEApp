/*
 * To change this license header"; choose License Headers in Project Properties.
 * To change this template file"; choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import mx.gob.sat.siat.feagace.modelo.dao.util.UtileriasMapperDao;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocrechazoinsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class InsumoRechazoMapper implements RowMapper<List<FecetRechazoInsumo>> {

    private static final String RECHAZO_INSUMO_ID = "ID_RECHAZO_INSUMO";
    private static final String INSUMO_ID = "ID_INSUMO";
    private static final String RECHAZO_INSUMO_DESCRIPCION = "RECHAZO_INSUMO_DESCRIPCION";
    private static final String RECHAZO_INSUMO_FECHA_RECHAZO = "RECHAZO_FECHA_RECHAZO";
    private static final String RECHAZO_INSUMO_RFC_RECHAZO = "RECHAZO_RFC_RECHAZO";
    private static final String DOC_RECHAZO_ID_DOCRECHAZOINSUMO = "DOC_RECHAZO_ID";
    private static final String DOC_RECHAZO_RUTA_ARCHIVO = "DOC_RUTA_ARCHIVO";
    private static final String DOC_RECHAZO_FECHA_CREACION = "DOC_FECHA_CREACION";

    @Override
    public List<FecetRechazoInsumo> mapRow(ResultSet rs, int i) throws SQLException {
        List<FecetRechazoInsumo> lstRetroalimentaciones = new ArrayList<FecetRechazoInsumo>();
        FecetRechazoInsumo rechazoInsumo = new FecetRechazoInsumo();

        do {
            if (rs.getBigDecimal(RECHAZO_INSUMO_ID).equals(rechazoInsumo.getIdRechazoInsumo())) {
                obtenerDocumento(rs, rechazoInsumo.getLstDocsRechazoInsumo());
            } else {
                if (rechazoInsumo.getIdInsumo() != null) {
                    lstRetroalimentaciones.add(rechazoInsumo);
                }
                rechazoInsumo = new FecetRechazoInsumo();

                rechazoInsumo.setIdRechazoInsumo(rs.getBigDecimal(RECHAZO_INSUMO_ID));
                rechazoInsumo.setIdInsumo(rs.getBigDecimal(INSUMO_ID));
                rechazoInsumo.setDescripcion(rs.getString(RECHAZO_INSUMO_DESCRIPCION));
                rechazoInsumo.setFechaRechazo(rs.getTimestamp(RECHAZO_INSUMO_FECHA_RECHAZO));
                rechazoInsumo.setRfcRechazo(rs.getString(RECHAZO_INSUMO_RFC_RECHAZO));

                rechazoInsumo.setLstDocsRechazoInsumo(new ArrayList<FecetDocrechazoinsumo>());
                obtenerDocumento(rs, rechazoInsumo.getLstDocsRechazoInsumo());
            }
        } while (rs.next());
        lstRetroalimentaciones.add(rechazoInsumo);
        return lstRetroalimentaciones;
    }

    private void obtenerDocumento(ResultSet rs, List<FecetDocrechazoinsumo> lstDocsRechazoInsumo) throws SQLException {
        FecetDocrechazoinsumo doc = new FecetDocrechazoinsumo();

        doc.setIdDocrechazoinsumo(rs.getBigDecimal(DOC_RECHAZO_ID_DOCRECHAZOINSUMO));
        doc.setIdRechazoInsumo(rs.getBigDecimal(RECHAZO_INSUMO_ID));
        doc.setRutaArchivo(rs.getString(DOC_RECHAZO_RUTA_ARCHIVO));

        if (lstDocsRechazoInsumo != null && !lstDocsRechazoInsumo.contains(doc)) {
            doc.setNombreArchivo(UtileriasMapperDao.getNameFileFromPath((rs.getString(DOC_RECHAZO_RUTA_ARCHIVO))));
            doc.setFechaCreacion(rs.getTimestamp(DOC_RECHAZO_FECHA_CREACION));

            lstDocsRechazoInsumo.add(doc);
        }
    }

}
