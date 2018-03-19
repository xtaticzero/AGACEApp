package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRechazoInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetContadorInsumosRechazadosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetRechazoInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumosRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRechazoInsumoPk;

@Repository("fecetRechazoInsumoDao")
public class FecetRechazoInsumoDaoImpl extends BaseJDBCDao<FecetRechazoInsumo>
        implements FecetRechazoInsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = 3919201548988943653L;

    private static final String SQL_SELECT = "SELECT ID_RECHAZO_INSUMO, ID_INSUMO, DESCRIPCION, FECHA_RECHAZO, RFC_RECHAZO FROM "
            + getTableName();

    private static final String SQL_SELECT_COUNTER = "SELECT FECET_RECHAZO_INSUMO.ID_RECHAZO_INSUMO, FECET_RECHAZO_INSUMO.FECHA_RECHAZO, FECET_RECHAZO_INSUMO.DESCRIPCION, COUNT (FECET_DOCRECHAZOINSUMO.ID_DOCRECHAZOINSUMO) AS CONTADOR, FECET_RECHAZO_INSUMO.ID_INSUMO FROM FECET_INSUMO ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoInsumoPk
     */
    public FecetRechazoInsumoPk insert(FecetRechazoInsumo dto) {

        dto.setIdRechazoInsumo(getJdbcTemplateBase().queryForObject(
                "SELECT FECEQ_RECHAZO_INSUMO.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_RECHAZO_INSUMO, ID_INSUMO, DESCRIPCION, FECHA_RECHAZO, RFC_RECHAZO ) VALUES ( ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoInsumo(), dto.getIdInsumo(),
                dto.getDescripcion(), dto.getFechaRechazo(),
                dto.getRfcRechazo());
        return dto.createPk();

    }

    /**
     * Cuenta todas los documentos del rechazo que corresponden a cada insumo.
     */
    public List<FecetContadorInsumosRechazados> getContadorRechazo(
            final BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_COUNTER);
        query.append(" INNER JOIN FECET_RECHAZO_INSUMO ON FECET_INSUMO.ID_INSUMO = FECET_RECHAZO_INSUMO.ID_INSUMO \n");
        query.append(" INNER JOIN FECET_DOCRECHAZOINSUMO ON FECET_DOCRECHAZOINSUMO.ID_RECHAZO_INSUMO = FECET_RECHAZO_INSUMO.ID_RECHAZO_INSUMO \n");
        query.append(" WHERE FECET_INSUMO.ID_INSUMO = ?  \n");
        query.append(" GROUP BY FECET_RECHAZO_INSUMO.ID_RECHAZO_INSUMO, FECET_RECHAZO_INSUMO.FECHA_RECHAZO, FECET_RECHAZO_INSUMO.DESCRIPCION, FECET_RECHAZO_INSUMO.ID_INSUMO \n");
        query.append(" ORDER BY FECET_RECHAZO_INSUMO.ID_RECHAZO_INSUMO DESC \n");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetContadorInsumosRechazadosMapper(), idInsumo);

    }

    /**
     * Updates a single row in the FECET_RECHAZO_INSUMO table.
     */
    public void update(FecetRechazoInsumoPk pk, FecetRechazoInsumo dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_RECHAZO_INSUMO = ?, ID_PROPUESTA = ?, DESCRIPCION = ?, FECHA_RECHAZO = ?, RFC_RECHAZO = ? WHERE ID_RECHAZO_INSUMO = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoInsumo(), dto.getIdInsumo(),
                dto.getDescripcion(), dto.getFechaRechazo(),
                dto.getRfcRechazo(), pk.getIdRechazoInsumo());

    }

    /**
     * Deletes a single row in the FECET_RECHAZO_INSUMO table.
     */
    public void delete(FecetRechazoInsumoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_INSUMO = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdRechazoInsumo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_RECHAZO_INSUMO";
    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    public FecetRechazoInsumo findByPrimaryKey(BigDecimal idRechazoInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_INSUMO = ?");
        List<FecetRechazoInsumo> list = getJdbcTemplateBase().query(
                query.toString(), new FecetRechazoInsumoMapper(),
                idRechazoInsumo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria ''.
     */
    public List<FecetRechazoInsumo> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_RECHAZO_INSUMO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper());

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_RECHAZO_INSUMO = :idRechazoInsumo'.
     */
    public List<FecetRechazoInsumo> findWhereIdRechazoInsumoEquals(
            BigDecimal idRechazoInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_INSUMO = ? ORDER BY ID_RECHAZO_INSUMO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), idRechazoInsumo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetRechazoInsumo> findWhereIdPropuestaEquals(
            BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), idPropuesta);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    public List<FecetRechazoInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_INSUMO = ? ORDER BY ID_INSUMO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), idInsumo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FecetRechazoInsumo> findWhereDescripcionEquals(
            String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    public List<FecetRechazoInsumo> findWhereFechaRechazoEquals(
            Date fechaRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RECHAZO = ? ORDER BY FECHA_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), fechaRechazo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_INSUMO table that match the
     * criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    public List<FecetRechazoInsumo> findWhereRfcRechazoEquals(String rfcRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_RECHAZO = ? ORDER BY RFC_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoInsumoMapper(), rfcRechazo);

    }

    /**
     * Returns the rows from the FECET_RECHAZO_INSUMO table that matches the
     * specified primary-key value.
     */
    public FecetRechazoInsumo findByPrimaryKey(FecetRechazoInsumoPk pk) {
        return findByPrimaryKey(pk.getIdRechazoInsumo());
    }

}
