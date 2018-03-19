package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.ConsecutivoMetodoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;

import org.springframework.stereotype.Repository;

@Repository("consecutivoMetodoPropuestaDao")
public class ConsecutivoMetodoPropuestaDaoImpl extends BaseJDBCDao<FececMetodo> implements ConsecutivoMetodoPropuestaDao {

    @SuppressWarnings("compatibility:-486987079746553938")
    private static final long serialVersionUID = 1L;

    @Override
    public int getConsecutivoEHO() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SECUENCIAL_METODO_EHO.NEXTVAL FROM DUAL", Integer.class);
    }

    @Override
    public int getConsecutivoORG() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SECUENCIAL_METODO_ORG.NEXTVAL FROM DUAL", Integer.class);
    }

    @Override
    public int getConsecutivoUCA() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SECUENCIAL_METODO_UCA.NEXTVAL FROM DUAL", Integer.class);
    }

    @Override
    public int getConsecutivoREE() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SECUENCIAL_METODO_REE.NEXTVAL FROM DUAL", Integer.class);
    }

    @Override
    public int getConsecutivoMCA() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_SECUENCIAL_METODO_MCA.NEXTVAL FROM DUAL", Integer.class);
    }
}
