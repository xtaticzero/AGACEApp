/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import mx.gob.sat.siat.feagace.modelo.dto.ordenes.ContadorOrdenes;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 * Clase que tiene como objetivo el mapeo de los campos para el dto
 * ContadorOrdenes
 */
public class ContadorOrdenesMapper implements ParameterizedRowMapper<ContadorOrdenes> {

    /**
     * Este metodo hace un mapeo de los datos resultantes del conteo de ordenes
     * en la tabla FECET_ORDEN de acuerdo a su columna correspondiente
     *
     * @param st objeto de tipo ResultSet
     * @param numRow numero de renglon
     * @return objeto de tipo <code>ContadorOrdenes</code>
     * @throws SQLException
     */
    public ContadorOrdenes mapRow(ResultSet st, int numRow) throws SQLException {

        ContadorOrdenes dto = new ContadorOrdenes();
        dto.setTotalFirmarEHO(st.getLong("TOTAL_FIRMAR_EHO"));
        dto.setTotalFirmarORG(st.getLong("TOTAL_FIRMAR_ORG"));
        dto.setTotalFirmarREE(st.getLong("TOTAL_FIRMAR_REE"));
        dto.setTotalFirmarMCA(st.getLong("TOTAL_FIRMAR_MCA"));
        dto.setTotalFirmarUCA(st.getLong("TOTAL_FIRMAR_UCA"));

        dto.setTotalValidarEHO(st.getLong("TOTAL_VALIDAR_EHO"));
        dto.setTotalValidarORG(st.getLong("TOTAL_VALIDAR_ORG"));
        dto.setTotalValidarREE(st.getLong("TOTAL_VALIDAR_REE"));
        dto.setTotalValidarMCA(st.getLong("TOTAL_VALIDAR_MCA"));
        dto.setTotalValidarUCA(st.getLong("TOTAL_VALIDAR_UCA"));

        return dto;
    }
}
