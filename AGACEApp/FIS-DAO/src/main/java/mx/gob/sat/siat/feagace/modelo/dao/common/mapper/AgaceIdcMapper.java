/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.common.AgaceIdc;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class AgaceIdcMapper implements ParameterizedRowMapper<AgaceIdc> {

    /**
     * Este atributo corresponde al nombre de la columna ID_IDC en la tabla
     * AGACE_IDC
     */
    private static final String COLUMN_ID_IDC = "ID_IDC";

    /**
     * Este atributo corresponde al nombre de la columna ID_ORDEN en la tabla
     * AGACE_IDC
     */
    private static final String COLUMN_ID_ORDEN = "ID_ORDEN";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_AGENTE_ADUANAL
     * en la tabla AGACE_IDC
     */
    private static final String COLUMN_NOMBRE_AGENTE_ADUANAL = "NOMBRE_AGENTE_ADUANAL";

    /**
     * Este atributo corresponde al nombre de la columna RFC_AGENTE_ADUANAL en
     * la tabla AGACE_IDC
     */
    private static final String COLUMN_RFC_AGENTE_ADUANAL = "RFC_AGENTE_ADUANAL";

    /**
     * Este atributo corresponde al nombre de la columna EMAIL_AGENTE_ADUANAL en
     * la tabla AGACE_IDC
     */
    private static final String COLUMN_EMAIL_AGENTE_ADUANAL = "EMAIL_AGENTE_ADUANAL";

    /**
     * Este atributo corresponde al nombre de la columna NOMBRE_CONTRIBUYENTE en
     * la tabla AGACE_IDC
     */
    private static final String COLUMN_NOMBRE_CONTRIBUYENTE = "NOMBRE_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna RFC_CONTRIBUYENTE en la
     * tabla AGACE_IDC
     */
    private static final String COLUMN_RFC_CONTRIBUYENTE = "RFC_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna EMAIL_CONTRIBUYENTE en
     * la tabla AGACE_IDC
     */
    private static final String COLUMN_EMAIL_CONTRIBUYENTE = "EMAIL_CONTRIBUYENTE";

    /**
     * Este atributo corresponde al nombre de la columna
     * NOMBRE_REPRESENTANTE_LEGAL en la tabla AGACE_IDC
     */
    private static final String COLUMN_NOMBRE_REPRESENTANTE_LEGAL = "NOMBRE_REPRESENTANTE_LEGAL";

    /**
     * Este atributo corresponde al nombre de la columna
     * EMAIL_REPRESENTANTE_LEGAL en la tabla AGACE_IDC
     */
    private static final String COLUMN_EMAIL_REPRESENTANTE_LEGAL = "EMAIL_REPRESENTANTE_LEGAL";

    /**
     * Metodo mapRow Hace un mapeo y set de la datos de la tabla AGACE_ORDEN
     *
     * @param rs
     * @param rowNum
     * @return AgaceIdc
     * @throws SQLException
     */
    public AgaceIdc mapRow(ResultSet rs, int rowNum) throws SQLException {
        AgaceIdc dto = new AgaceIdc();
        dto.setIdIdc(rs.getLong(COLUMN_ID_IDC));
        dto.setIdOrden(rs.getLong(COLUMN_ID_ORDEN));
        dto.setNombreAgenteAduanal(rs.getString(COLUMN_NOMBRE_AGENTE_ADUANAL));
        dto.setRfcAgenteAduanal(rs.getString(COLUMN_RFC_AGENTE_ADUANAL));
        dto.setEmailAgenteAduanal(rs.getString(COLUMN_EMAIL_AGENTE_ADUANAL));
        dto.setNombreContribuyente(rs.getString(COLUMN_NOMBRE_CONTRIBUYENTE));
        dto.setRfcContribuyente(rs.getString(COLUMN_RFC_CONTRIBUYENTE));
        dto.setEmailContribuyente(rs.getString(COLUMN_EMAIL_CONTRIBUYENTE));
        dto.setNombreRepresentanteLegal(rs.getString(COLUMN_NOMBRE_REPRESENTANTE_LEGAL));
        dto.setEmailRepresentanteLegal(rs.getString(COLUMN_EMAIL_REPRESENTANTE_LEGAL));

        return dto;
    }

}
