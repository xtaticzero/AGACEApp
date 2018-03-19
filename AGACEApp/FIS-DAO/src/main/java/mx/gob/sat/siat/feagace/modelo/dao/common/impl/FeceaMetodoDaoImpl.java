/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.common.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FeceaMetodoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.common.FeceaMetodoDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.common.FececMetodo;

import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("feceaMetodoDao")
public class FeceaMetodoDaoImpl extends BaseJDBCDao<FececMetodo> implements FeceaMetodoDao {

    @SuppressWarnings("compatibility:2195198891249726641")
    private static final long serialVersionUID = 1L;

    /**
     * Este atributo corresponde a un SELECT para seleccionar las columnas de ls
     * talba FECEA_METODO
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_METODO, ABREVIATURA, NOMBRE FROM ").append(getTableName());

    private static final StringBuilder SQL_SELECT_WHERE_ID_METODO
            = new StringBuilder("SELECT ID_METODO, ABREVIATURA, NOMBRE FROM FECEC_METODO WHERE ID_METODO = ?");

    /**
     * Metodo findAll() Obtiene todos los datos de la tabla FECET_ORDEN
     *
     * @return List
     * @
     */
    @Override
    public List<FececMetodo> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" WHERE ID_METODO NOT IN(6) AND BLN_ACTIVO = 1");
        return getJdbcTemplateBase().query(sql.toString() + "ORDER BY ID_METODO", new FeceaMetodoMapper());

    }

    /**
     * Metodo getMetodosCambioMetodo() Obtiene todos los datos de la tabla
     * FECET_ORDEN sin UCA la cual cambia de metodo a EHO, ORG o REE
     *
     * @return List
     * @
     */
    @Override
    public List<FececMetodo> getOpcionesCambioMetodo() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(" WHERE ID_METODO IN (?, ?, ?) ").toString() + " ORDER BY ID_METODO",
                new FeceaMetodoMapper(), Constantes.EHO, Constantes.ORG, Constantes.REE);

    }

    /**
     * Metodo getAbreviaturaMetodo() Obtiene la abreviatura del metodo a EHO,
     * ORG, REE, UCA
     *
     * @return String
     * @
     */
    @Override
    public String getAbreviaturaMetodo(final BigDecimal idMetodo) {

        return getJdbcTemplateBase().queryForObject("SELECT ABREVIATURA FROM " + getTableName() + " WHERE ID_METODO = ?",
                String.class, idMetodo);

    }

    /**
     * Metodo getTableName Asigna nombre de la tabla
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_METODO";
    }

    @Override
    public List<FececMetodo> findWhereIdMetodo(final BigDecimal idMetodo) {

        return getJdbcTemplateBase().query(SQL_SELECT_WHERE_ID_METODO.toString(), new FeceaMetodoMapper(),
                idMetodo);

    }

    @Override
    public List<FececMetodo> findWhereAbreviaturaEquals(String abreviatura) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        return getJdbcTemplateBase().query(sql.append(" WHERE ABREVIATURA = ?").toString(), new FeceaMetodoMapper(),
                abreviatura);

    }

    /**
     * Metodo findPropuestas() Obtiene todos los datos de la tabla FECET_ORDEN
     *
     * @return List
     * @
     */
    @Override
    public List<FececMetodo> findPropuestas() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" WHERE ID_METODO NOT IN(5) AND BLN_ACTIVO = 1");
        return getJdbcTemplateBase().query(sql.toString() + " ORDER BY ID_METODO", new FeceaMetodoMapper());
    }
}
