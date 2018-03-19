package mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import mx.gob.sat.siat.feagace.modelo.dto.common.nyv.model.DocumentoActoAdministrativo;

public class FeceaDocumentoAdmMapper implements ParameterizedRowMapper<DocumentoActoAdministrativo> {

    private static final String COLUMN_ID_DOCUMENTO_ADM = "ID_DOCUMENTO_ADM";
    private static final String COLUMN_CVE_DOCUMENTO_NYV = "CVE_DOCUMENTO_NYV";
    private static final String COLUMN_TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
    private static final String COLUMN_BLN_RESOLUCION = "BLN_RESOLUCION";
    private static final String COLUMN_ID_NYV = "ID_NYV";
    private static final String COLUMN_FECHA_INICIO = "FECHA_INICIO";
    private static final String COLUMN_FECHA_FIN = "FECHA_FIN";
    private static final String COLUMN_BLN_ACTIVO = "BLN_ACTIVO";
    private static final String COLUMN_CVE_DOCUMENTO_TIPO = "CVE_DOCUMENTO_TIPO";

    @Override
    public DocumentoActoAdministrativo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DocumentoActoAdministrativo documentoActoAdmin = new DocumentoActoAdministrativo();

        documentoActoAdmin.setIdDocumentoAdm(rs.getBigDecimal(COLUMN_ID_DOCUMENTO_ADM));
        documentoActoAdmin.setCveDocumentoNyv(rs.getLong(COLUMN_CVE_DOCUMENTO_NYV));
        documentoActoAdmin.setTipoDocumento(rs.getString(COLUMN_TIPO_DOCUMENTO));
        documentoActoAdmin.setBlnResolucion(rs.getBoolean(COLUMN_BLN_RESOLUCION));
        documentoActoAdmin.setIdNyv(rs.getBigDecimal(COLUMN_ID_NYV));
        documentoActoAdmin.setFechaInicio(rs.getDate(COLUMN_FECHA_INICIO));
        documentoActoAdmin.setFechaFin(rs.getDate(COLUMN_FECHA_FIN));
        documentoActoAdmin.setBlnActivo(rs.getBoolean(COLUMN_BLN_ACTIVO));

        if (rs.getString(COLUMN_CVE_DOCUMENTO_TIPO) != null) {
            documentoActoAdmin.setCveDocumentoTipo(rs.getString(COLUMN_CVE_DOCUMENTO_TIPO));
        }

        return documentoActoAdmin;
    }

}
