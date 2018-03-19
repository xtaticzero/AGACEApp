/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececFeriadosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.FececFeriadosDao;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriados;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.ordenes.FececFeriadosPk;

import org.springframework.stereotype.Repository;

@Repository("fececFeriadosDao")
public class FececFeriadosDaoImpl extends BaseJDBCDao<FececFeriados> implements FececFeriadosDao {

    private static final Integer CINCO = 5;

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECEC_FERIADO
     */
    private static final String SQL_SELECT = "SELECT ID_FERIADO, FECHA, DESCRIPCION FROM " + getTableName();

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececFeriadosPk
     */
    public FececFeriadosPk insert(FececFeriados dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_FERIADO, FECHA, DESCRIPCION ) VALUES ( ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdFeriado(),
                dto.getFecha(), dto.getDescripcion());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_FERIADOS table.
     */
    public void update(FececFeriadosPk pk, FececFeriados dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_FERIADO = ?, FECHA = ?, DESCRIPCION = ? WHERE ID_FERIADO = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdFeriado(), dto.getFecha(), dto.getDescripcion(), pk.getIdFeriado());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_FERIADOS";
    }

    /**
     * Returns all rows from the FECEC_FERIADOS table that match the criteria
     * 'IDFERIADO = :idFeriado'.
     */
    public FececFeriados findByPrimaryKey(BigDecimal idFeriado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE IDFERIADO = ?");
        List<FececFeriados> list
                = getJdbcTemplateBase().query(query.toString(), new FececFeriadosMapper(), idFeriado);
        return list.isEmpty() ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECEC_FERIADOS table that match the criteria
     * ''.
     */
    public List<FececFeriados> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_FERIADO");
        return getJdbcTemplateBase().query(query.toString(), new FececFeriadosMapper());

    }

    /**
     * Returns all rows from the FECEC_FERIADOS table that match the criteria
     * 'IDFERIADO = :idFeriado'.
     */
    public List<FececFeriados> findWhereIdFeriadoEquals(BigDecimal idFeriado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE IDFERIADO = ? ORDER BY IDFERIADO");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFeriadosMapper(), idFeriado);

    }

    /**
     * Returns all rows from the FECEC_FERIADOS table that match the criteria
     * 'FECHA = :fecha'.
     */
    public List<FececFeriados> findWhereFechaEquals(Date fecha) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA = ? ORDER BY FECHA");
        return getJdbcTemplateBase().query(query.toString(), new FececFeriadosMapper(),
                fecha);

    }

    /**
     * Returns all rows from the FECEC_FERIADOS table that match the criteria
     * 'DESCRIPCION = :descripcion'.
     */
    public List<FececFeriados> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFeriadosMapper(), descripcion);

    }

    /**
     * Returns the rows from the FECEC_FERIADOS table that matches the specified
     * primary-key value.
     */
    public FececFeriados findByPrimaryKey(FececFeriadosPk pk) {

        return findByPrimaryKey(pk.getIdFeriado());

    }

    /**
     * Returns all rows from the FECEC_FERIADOS table.
     */
    public List<FececFeriados> buscarDiasFestivosContributenteCargaDocumentos(final Date fecha,
            int diasHabiles) {

        int diasSabadoDomingo = ((Integer) (diasHabiles / CINCO)) * 2;
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA BETWEEN ? AND (? + ?) ORDER BY FECHA");
        return getJdbcTemplateBase().query(query.toString(),
                new FececFeriadosMapper(), fecha, diasHabiles + diasSabadoDomingo, fecha);

    }

}
