/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOficioDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetRechazoOficioMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOficio;

import org.springframework.stereotype.Repository;

@Repository("fecetRechazoOficioDao")
public class FecetRechazoOficioDaoImpl extends BaseJDBCDao<FecetRechazoOficio> implements FecetRechazoOficioDao {

    /**
     * ESTATUS RECHAZO 1 = RECHAZO CREADA Y/O PENDIENTE DE VALIDAR 2 = RECHAZO
     * VALIDADA 3 = RECHAZO COMPLEMENTADA 4 = RECHAZO RECHAZADA
     */
    private static final String SQL_SELECT = "SELECT ID_RECHAZO_OFICIO, ID_OFICIO, ID_EMPLEADO, DESCRIPCION, FECHA_RECHAZO, ESTATUS FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoOficioPk
     */
    public FecetRechazoOficio insert(FecetRechazoOficio dto) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_RECHAZO_OFICIO, ID_OFICIO, DESCRIPCION, FECHA_RECHAZO , ID_EMPLEADO, ESTATUS ) VALUES ( ?, ?, ?, ?, ?, ? )");

        dto.setIdRechazoOficio(getJdbcTemplateBase().queryForObject("SELECT FECEQ_RECHAZO_OFICIO.NEXTVAL FROM DUAL",
                BigDecimal.class));
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoOficio(), dto.getIdOficio(), dto.getDescripcion(), dto.getFechaRechazo(), dto.getIdEmpleado(),
                dto.getEstatus());

        return dto;
    }

    /**
     * Updates a single row in the FECET_RECHAZO_OFICIO table.
     */
    public void update(FecetRechazoOficio dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_RECHAZO_OFICIO = ?, ID_OFICIO = ?, ID_EMPLEADO = ?, DESCRIPCION = ?, FECHA_RECHAZO = ?, ESTATUS = ? WHERE ID_RECHAZO_OFICIO = ?");

        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoOficio(), dto.getIdOficio(), dto.getIdEmpleado(), dto.getDescripcion(),
                dto.getFechaRechazo(), dto.getEstatus(), dto.getIdRechazoOficio());

    }

    /**
     * Deletes a single row in the FECET_RECHAZO_OFICIO table.
     */
    public void delete(FecetRechazoOficio dto) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_RECHAZO_OFICIO = ?", dto.getIdRechazoOficio());

    }

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_ANEXOS_PRORROGA_OFICIO
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_RECHAZO_OFICIO.NEXTVAL FROM DUAL");

    /**
     * Metodo getIdFecetOficioAnexosPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetRechazoPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_RECHAZO_OFICIO";
    }

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_RECHAZO_OFICIO = :idRechazoOficio'.
     */
    public FecetRechazoOficio findByPrimaryKey(BigDecimal idRechazoOficio) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_OFICIO = ?");

        List<FecetRechazoOficio> list
                = getJdbcTemplateBase().query(query.toString(), new FecetRechazoOficioMapper(),
                        idRechazoOficio);
        return list.size() == 0 ? null : list.get(0);

    }

    public List<FecetRechazoOficio> traeRechazoOficio(BigDecimal idRechazoOficio) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_OFICIO = ?");

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoOficioMapper(),
                idRechazoOficio);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria ''.
     */
    public List<FecetRechazoOficio> findAll() {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_RECHAZO_OFICIO");

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoOficioMapper());

    }

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_RECHAZO_OFICIO = :idRechazoOficio'.
     */
    public List<FecetRechazoOficio> findWhereIdRechazoOficioEquals(BigDecimal idRechazoOficio) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_OFICIO = ? ORDER BY ID_RECHAZO_OFICIO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOficioMapper(), idRechazoOficio);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ID_OFICIO = :idOrden'.
     */
    public List<FecetRechazoOficio> findWhereIdOficioEquals(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_OFICIO = ? ORDER BY ID_OFICIO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOficioMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_OFICIO table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    public List<FecetRechazoOficio> findWhereEstatusEquals(String estatus) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOficioMapper(), estatus);

    }

    /**
     * Returns the rows from the FECET_RECHAZO_OFICIO table that matches the
     * specified primary-key value.
     */
    public FecetRechazoOficio findByPrimaryKey(final FecetRechazoOficio dto) {
        return findByPrimaryKey(dto.getIdRechazoOficio());
    }

}
