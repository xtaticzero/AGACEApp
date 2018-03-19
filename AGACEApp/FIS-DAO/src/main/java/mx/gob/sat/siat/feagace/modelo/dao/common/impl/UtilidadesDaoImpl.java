/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import mx.gob.sat.siat.feagace.modelo.dao.common.UtilidadesDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("utilidadesDao")
public class UtilidadesDaoImpl implements UtilidadesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Metodo setJdbcTemplate
     *
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Metod getJdbcTemplate
     *
     * @return JdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public BigDecimal getConsecutivoCargaMasiva() {
        return jdbcTemplate.queryForObject("SELECT FECEQ_CARGA_MASIVA_EXP.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public BigDecimal getConsecutivoDoctoCargaMasivaInsumos() {
        return jdbcTemplate.queryForObject("SELECT FECEQ_RMI_FOLIO_DOC.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public BigDecimal getConsecutivoCargaMasivaInsumos() {
        return jdbcTemplate.queryForObject("SELECT FECEQ_RMI_ID_CARGA.NEXTVAL FROM DUAL", BigDecimal.class);
    }
}
