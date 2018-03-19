/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.ordenes.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.FecetRechazoOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetRechazoOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetRechazoOrdenPk;

import org.springframework.stereotype.Repository;

@Repository("fecetRechazoOrdenDao")
public class FecetRechazoOrdenDaoImpl extends BaseJDBCDao<FecetRechazoOrden> implements FecetRechazoOrdenDao {

    /**
     * ESTATUS RECHAZO 1 = RECHAZO CREADA Y/O PENDIENTE DE VALIDAR 2 = RECHAZO
     * VALIDADA 3 = RECHAZO COMPLEMENTADA 4 = RECHAZO RECHAZADA
     */
    private static final String SQL_SELECT = "SELECT ID_RECHAZO_ORDEN, ID_ORDEN, DESCRIPCION, FECHA_RECHAZO, FECHA_ATENCION, RFC_RECHAZO, NOMBRE_ARCHIVO_RECHAZO, RUTA_ARCHIVO_RECHAZO, ESTATUS, RFC_RETRO_AUDITOR, NOMBRE_ARCHIVO_REEMPLAZADO, RUTA_ARCHIVO_REEMPLAZADO FROM ";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRechazoOrdenPk
     */
    public FecetRechazoOrdenPk insert(FecetRechazoOrden dto) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_RECHAZO_ORDEN, ID_ORDEN, DESCRIPCION, FECHA_RECHAZO, FECHA_ATENCION, ID_EMPLEADO_RECHAZO, ESTATUS, ID_DOC_ORDEN ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");

        dto.setIdRechazoOrden(getJdbcTemplateBase().queryForObject("SELECT FECEQ_RECHAZO_ORDEN.NEXTVAL FROM DUAL",
                BigDecimal.class));
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoOrden(), dto.getIdOrden(), dto.getDescripcion(), dto.getFechaRechazo(),
                dto.getFechaAtencion(), dto.getIdEmpleado(), dto.getEstatus(), dto.getIdDocOrden());

        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_RECHAZO_ORDEN table.
     */
    public void update(FecetRechazoOrdenPk pk, FecetRechazoOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_RECHAZO_ORDEN = ?, ID_ORDEN = ?, DESCRIPCION = ?, FECHA_RECHAZO = ?, FECHA_ATENCION = ?, RFC_RECHAZO = ?, NOMBRE_ARCHIVO_RECHAZO = ?, RUTA_ARCHIVO_RECHAZO = ?, ESTATUS = ?, RFC_RETRO_AUDITOR = ?, NOMBRE_ARCHIVO_REEMPLAZADO = ?, RUTA_ARCHIVO_REEMPLAZADO = ? WHERE ID_RECHAZO_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(),
                dto.getIdRechazoOrden(), dto.getIdOrden(), dto.getDescripcion(), dto.getFechaRechazo(),
                dto.getFechaAtencion(), dto.getRfcRechazo(), dto.getNombreArchivoRechazo(),
                dto.getRutaArchivoRechazo(), dto.getEstatus(), dto.getRfcRetroAuditor(),
                dto.getNombreArchivoReemplazado(), dto.getRutaArchivoReemplazado(),
                pk.getIdRechazoOrden());

    }

    /**
     * Deletes a single row in the FECET_RECHAZO_ORDEN table.
     */
    public void delete(FecetRechazoOrdenPk pk) {

        getJdbcTemplateBase().update("DELETE FROM " + getTableName() + " WHERE ID_RECHAZO_ORDEN = ?", pk.getIdRechazoOrden());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_RECHAZO_ORDEN";
    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_RECHAZO_ORDEN = :idRechazoOrden'.
     */
    public FecetRechazoOrden findByPrimaryKey(BigDecimal idRechazoOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_ORDEN = ?");

        List<FecetRechazoOrden> list
                = getJdbcTemplateBase().query(query.toString(), new FecetRechazoOrdenMapper(),
                        idRechazoOrden);
        return list.size() == 0 ? null : list.get(0);

    }

    public List<FecetRechazoOrden> traeRechazoOrden(BigDecimal idRechazoOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_ORDEN = ?");

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoOrdenMapper(),
                idRechazoOrden);

    }

    public List<FecetRechazoOrden> traeRechazoOrdenPorEstatus(BigDecimal idRechazoOrden,
            String idEstatus) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_ORDEN = ? AND ESTATUS = ?");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), idRechazoOrden, idEstatus);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria ''.
     */
    public List<FecetRechazoOrden> findAll() {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" ORDER BY ID_RECHAZO_ORDEN");

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoOrdenMapper());

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_RECHAZO_ORDEN = :idRechazoOrden'.
     */
    public List<FecetRechazoOrden> findWhereIdRechazoOrdenEquals(BigDecimal idRechazoOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_ORDEN = ? ORDER BY ID_RECHAZO_ORDEN");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), idRechazoOrden);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ID_ORDEN = :idOrden'.
     */
    public List<FecetRechazoOrden> findWhereIdOrdenEquals(BigDecimal idOrden) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FecetRechazoOrden> findWhereDescripcionEquals(String descripcion) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    public List<FecetRechazoOrden> findWhereFechaRechazoEquals(Date fechaRechazo) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_RECHAZO = ? ORDER BY FECHA_RECHAZO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), fechaRechazo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'FECHA_ATENCION = :fechaAtencion'.
     */
    public List<FecetRechazoOrden> findWhereFechaAtencionEquals(Date fechaAtencion) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE FECHA_ATENCION = ? ORDER BY FECHA_ATENCION");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), fechaAtencion);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    public List<FecetRechazoOrden> findWhereRfcRechazoEquals(String rfcRechazo) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC_RECHAZO = ? ORDER BY RFC_RECHAZO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), rfcRechazo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'NOMBRE_ARCHIVO_RECHAZO = :nombreArchivoRechazo'.
     */
    public List<FecetRechazoOrden> findWhereNombreArchivoRechazoEquals(String nombreArchivoRechazo) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE_ARCHIVO_RECHAZO = ? ORDER BY NOMBRE_ARCHIVO_RECHAZO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), nombreArchivoRechazo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RUTA_ARCHIVO_RECHAZO = :rutaArchivoRechazo'.
     */
    public List<FecetRechazoOrden> findWhereRutaArchivoRechazoEquals(String rutaArchivoRechazo) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RUTA_ARCHIVO_RECHAZO = ? ORDER BY RUTA_ARCHIVO_RECHAZO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), rutaArchivoRechazo);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    public List<FecetRechazoOrden> findWhereEstatusEquals(String estatus) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), estatus);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RFC_RETRO_AUDITOR = :rfcRetroAuditor'.
     */
    public List<FecetRechazoOrden> findWhereRfcRetroAuditorEquals(String rfcRetroAuditor) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC_RETRO_AUDITOR = ? ORDER BY RFC_RETRO_AUDITOR");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), rfcRetroAuditor);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'NOMBRE_ARCHIVO_REEMPLAZADO = :nombreArchivoReemplazado'.
     */
    public List<FecetRechazoOrden> findWhereNombreArchivoReemplazadoEquals(String nombreArchivoReemplazado) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE NOMBRE_ARCHIVO_REEMPLAZADO = ? ORDER BY NOMBRE_ARCHIVO_REEMPLAZADO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), nombreArchivoReemplazado);

    }

    /**
     * Returns all rows from the FECET_RECHAZO_ORDEN table that match the
     * criteria 'RUTA_ARCHIVO_REEMPLAZADO = :rutaArchivoReemplazado'.
     */
    public List<FecetRechazoOrden> findWhereRutaArchivoReemplazadoEquals(String rutaArchivoReemplazado) {
        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RUTA_ARCHIVO_REEMPLAZADO = ? ORDER BY RUTA_ARCHIVO_REEMPLAZADO");

        return getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoOrdenMapper(), rutaArchivoReemplazado);

    }

    /**
     * Returns the rows from the FECET_RECHAZO_ORDEN table that matches the
     * specified primary-key value.
     */
    public FecetRechazoOrden findByPrimaryKey(FecetRechazoOrdenPk pk) {
        return findByPrimaryKey(pk.getIdRechazoOrden());
    }

}
