/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetDocExpInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetDocExpInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumoPk;

@Repository("fecetDocExpInsumoDao")
public class FecetDocExpInsumoDaoImpl extends BaseJDBCDao<FecetDocExpInsumo> implements
        FecetDocExpInsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -4286569048852142114L;

    private static final String SQL_SELECT = "SELECT ID_DOC_EXP_INSUMO, ID_INSUMO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocExpInsumoPk
     */
    public FecetDocExpInsumoPk insert(FecetDocExpInsumo dto) {

        if (dto.getIdDocExpInsumo() == null) {
            dto.setIdDocExpInsumo(getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_EXP_INSUMO.NEXTVAL FROM DUAL", BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        dto.setRutaArchivo(dto.getRutaArchivo() + dto.getNombre());
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_DOC_EXP_INSUMO, ID_INSUMO, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO ) VALUES ( ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocExpInsumo(), dto.getIdInsumo(), dto.getRutaArchivo(), dto.getFechaCreacion(), dto.isBlnActivo());
        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_DOC_EXP_INSUMO table.
     */
    public void update(FecetDocExpInsumoPk pk, FecetDocExpInsumo dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_DOC_EXP_INSUMO = ?, ID_INSUMO = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, BLN_ACTIVO = ?, FECHA_FIN = ? WHERE ID_DOC_EXP_INSUMO = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocExpInsumo(), dto.getIdInsumo(), dto.getRutaArchivo(), dto.getFechaCreacion(), dto.isBlnActivo(), dto.getFechaFin(), pk.getIdDocExpInsumo());
    }

    /**
     * Deletes a single row in the FECET_DOC_EXP_INSUMO table.
     */
    public void delete(FecetDocExpInsumoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_DOC_EXP_INSUMO = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocExpInsumo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_DOC_EXP_INSUMO";
    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_DOC_EXP_INSUMO = :idDocExpInsumo'.
     */
    public FecetDocExpInsumo findByPrimaryKey(BigDecimal idDocExpInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_DOC_EXP_INSUMO = ?");
        List<FecetDocExpInsumo> list = getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), idDocExpInsumo);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria ''.
     */
    public List<FecetDocExpInsumo> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_DOC_EXP_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    public List<FecetDocExpInsumo> findByFecetInsumo(BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_INSUMO = ? AND BLN_ACTIVO = ?");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), idInsumo, true);

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_DOC_EXP_INSUMO = :idDocExpInsumo'.
     */
    public List<FecetDocExpInsumo> findWhereIdDocExpInsumoEquals(BigDecimal idDocExpInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_DOC_EXP_INSUMO = ? ORDER BY ID_DOC_EXP_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), idDocExpInsumo);

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'ID_INSUMO = :idInsumo'.
     */
    public List<FecetDocExpInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_INSUMO = ? AND BLN_ACTIVO = ? ORDER BY ID_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), idInsumo, true);

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocExpInsumo> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_EXP_INSUMO table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetDocExpInsumo> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocExpInsumoMapper(), fechaCreacion);

    }

    /**
     * Returns the rows from the FECET_DOC_EXP_INSUMO table that matches the
     * specified primary-key value.
     */
    public FecetDocExpInsumo findByPrimaryKey(FecetDocExpInsumoPk pk) {
        return findByPrimaryKey(pk.getIdDocExpInsumo());
    }

}
