/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.mapper.FececAuditorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.propuestas.FececAuditorDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FececAuditorPropuestasMapper;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditor;
import mx.gob.sat.siat.feagace.modelo.dto.catalogos.propuestas.FececAuditorPk;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FeceaRechazoOrden;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("fececAuditorDao")
public class FececAuditorDaoImpl extends BaseJDBCDao<FececAuditor> implements FececAuditorDao {

    @SuppressWarnings("compatibility:-6169079845539245484")
    private static final long serialVersionUID = -4490912881696226428L;

    /**
     * Este atributo corresponde un Query para obtener los datos de las columnas
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_AUDITOR, RFC, NOMBRE, CORREO, FECHA_CREACION, FECHA_BAJA, ESTADO, ID_ARACE, ID_FIRMANTE FROM ");

    /**
     * Este atributo corresponde a una condición WHERE para el query
     */
    private static final StringBuilder SQL_WHERE = new StringBuilder(" WHERE ID_AUDITOR = ? ");

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FececAuditorPk
     */
    public FececAuditorPk insert(FececAuditor dto) {

        dto.setIdAuditor(getJdbcTemplateBase().queryForObject("SELECT FECEQ_AUDITOR.NEXTVAL FROM DUAL",
                BigDecimal.class));

        getJdbcTemplateBase().update("INSERT INTO " + getTableName()
                + " ( ID_AUDITOR, RFC, NOMBRE, CORREO, FECHA_CREACION, FECHA_BAJA, ESTADO, ID_ARACE, ID_FIRMANTE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? )",
                dto.getIdAuditor(), dto.getRfc(), dto.getNombre(), dto.getCorreo(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.getEstado(), dto.getIdArace(),
                dto.getIdFirmante());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECEC_AUDITOR table.
     */
    public void update(FececAuditorPk pk, FececAuditor dto) {

        getJdbcTemplateBase().update("UPDATE " + getTableName()
                + " SET ID_AUDITOR = ?, RFC = ?, NOMBRE = ?, CORREO = ?, FECHA_CREACION = ?, FECHA_BAJA = ?, ESTADO = ?, ID_ARACE = ?, ID_FIRMANTE = ? WHERE ID_AUDITOR = ?",
                dto.getIdAuditor(), dto.getRfc(), dto.getNombre(), dto.getCorreo(),
                dto.getFechaCreacion(), dto.getFechaBaja(), dto.getEstado(), dto.getIdArace(),
                dto.getIdFirmante(), pk.getIdAuditor());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECEC_AUDITOR";
    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_AUDITOR = :idAuditor'.
     */
    public FececAuditor findByPrimaryKey(BigDecimal idAuditor) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(SQL_WHERE);
        List<FececAuditor> list = getJdbcTemplateBase().query(query.toString(), new FececAuditorMapper(), idAuditor);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * Returns the status of the orden is rejected or not.If method returns 1
     * means orden has been rejected.
     *
     * @param idOrden
     * @return
     * @
     */
    public int findOrdenRechazoEstatus(BigDecimal idOrden) {
        int count = 0;

        String statement = "SELECT COUNT(1) FROM FECET_ORDEN WHERE ID_ORDEN = ? AND ID_RECHAZO_ORDEN IS NOT NULL";
        count = getJdbcTemplateBase().queryForInt(statement, new Object[]{idOrden});
        return count;

    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return int
     */
    public BigDecimal insertFecetRechazoOrden(FeceaRechazoOrden dto) {
        //TODO - ESTE METODO NO DEBERIA ESTAR AQUI

        dto.setIdRechazoOrden(getJdbcTemplateBase().queryForObject("SELECT FECEQ_RECHAZO_ORDEN.NEXTVAL FROM DUAL",
                BigDecimal.class));

        int insertedRow
                = getJdbcTemplateBase().update("INSERT INTO FECET_RECHAZO_ORDEN " + " ( ID_RECHAZO_ORDEN, ID_ORDEN, DESCRIPCION, FECHA_INICIO,ESTATUS) "
                        + "VALUES ( ?, ?, ?, ?, ?)", dto.getIdRechazoOrden(), dto.getIdOrden(),
                        dto.getDescripcion(), dto.getFechaInicio(), dto.getEstatus());

        if (insertedRow > 0) {
            getJdbcTemplateBase().update("UPDATE FECET_ORDEN SET ID_RECHAZO_ORDEN = ?,FIRMAR_IND = 0"
                    + ",VALIDAR_IND = 0 WHERE ID_ORDEN = ?", dto.getIdRechazoOrden(),
                    dto.getIdOrden());
        }

        return dto.getIdRechazoOrden();

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria ''.
     */
    public List<FececAuditor> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT.toString());
        query.append(getTableName());
        query.append(" ORDER BY ID_AUDITOR");
        return getJdbcTemplateBase().query(query.toString(), new FececAuditorMapper());

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_AUDITOR = :idAuditor'.
     */
    public List<FececAuditor> findWhereIdAuditorEquals(BigDecimal idAuditor) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ID_AUDITOR = ? ORDER BY ID_AUDITOR").toString(),
                new FececAuditorMapper(), idAuditor);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'RFC = :rfc'.
     */
    public List<FececAuditor> findWhereRfcEquals(String rfc) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(getTableName());
        query.append(" WHERE RFC = ? AND FECHA_BAJA IS NULL ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(), new FececAuditorMapper(), rfc);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'NOMBRE = :nombre'.
     */
    public List<FececAuditor> findWhereNombreEquals(String nombre) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE NOMBRE = ? ORDER BY NOMBRE").toString(),
                new FececAuditorMapper(), nombre);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'CORREO = :correo'.
     */
    public List<FececAuditor> findWhereCorreoEquals(String correo) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE CORREO = ? ORDER BY CORREO").toString(),
                new FececAuditorMapper(), correo);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FececAuditor> findWhereFechaCreacionEquals(Date fechaCreacion) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION").toString(),
                new FececAuditorMapper(), fechaCreacion);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'FECHA_BAJA = :fechaBaja'.
     */
    public List<FececAuditor> findWhereFechaBajaEquals(Date fechaBaja) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE FECHA_BAJA = ? ORDER BY FECHA_BAJA").toString(),
                new FececAuditorMapper(), fechaBaja);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ESTADO = :estado'.
     */
    public List<FececAuditor> findWhereEstadoEquals(String estado) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ESTADO = ? ORDER BY ESTADO").toString(),
                new FececAuditorMapper(), estado);

    }

    /**
     * Returns all rows from the FECEC_AUDITOR table that match the criteria
     * 'ID_ARACE = :idArace'.
     */
    public List<FececAuditor> findWhereIdAraceEquals(BigDecimal idArace) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ID_ARACE = ? ORDER BY ID_ARACE").toString(),
                new FececAuditorMapper(), idArace);

    }

    /**
     * Returns all rows from the FECEC_FIRMANTE table that match the criteria
     * 'ID_FIRMANTE = :idFirmante'.
     */
    public List<FececAuditor> findWhereIdFirmanteEquals(BigDecimal idFirmante) {

        return getJdbcTemplateBase().query(SQL_SELECT.append(getTableName()).append(" WHERE ID_FIRMANTE = ? ORDER BY ID_FIRMANTE").toString(),
                new FececAuditorMapper(), idFirmante);

    }

    /**
     * Returns the rows from the FECEC_AUDITOR table that matches the specified
     * primary-key value.
     */
    public FececAuditor findByPrimaryKey(FececAuditorPk pk) {
        return findByPrimaryKey(pk.getIdAuditor());
    }

    @Override
    public List<FececAuditor> findWhereRfcAuditor(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT FECEC_EMPLEADO.ID_EMPLEADO, FECEC_EMPLEADO.NOMBRE, FECEC_EMPLEADO.RFC, FECEC_EMPLEADO.CORREO, ");
        query.append("FECEC_ESTATUS_EMPLEADO.ID_ESTATUS_EMPLEADO, FECEC_EMPLEADO.FECHA_CREACION, FECEC_EMPLEADO.FECHA_BAJA, FECET_DETALLE_EMPLEADO.ID_ARACE, FECEC_RELACION_EMPLEADO.ID_SUBORDINADO ");
        query.append("FROM FECEC_EMPLEADO ");
        query.append("INNER JOIN FECEC_ESTATUS_EMPLEADO ON FECEC_ESTATUS_EMPLEADO.ID_ESTATUS_EMPLEADO = FECEC_EMPLEADO.ID_ESTATUS_EMPLEADO ");
        query.append("INNER JOIN FECET_DETALLE_EMPLEADO ON FECET_DETALLE_EMPLEADO.ID_EMPLEADO = FECEC_EMPLEADO.ID_EMPLEADO ");
        query.append("INNER JOIN FECEC_RELACION_EMPLEADO ON FECEC_RELACION_EMPLEADO.ID_EMPLEADO = FECEC_EMPLEADO.ID_EMPLEADO ");
        query.append("INNER JOIN FECEC_UNIDAD_ADMINISTRATIVA ON FECEC_UNIDAD_ADMINISTRATIVA.ID_UNIDAD_ADMINISTRATIVA = FECET_DETALLE_EMPLEADO.ID_ARACE ");
        query.append("WHERE FECEC_EMPLEADO.RFC = ?");
        query.append("AND FECEC_RELACION_EMPLEADO.ID_TIPO_SUBORDINADO = ").append(Constantes.ENTERO_CERO);
        query.append(" AND FECEC_RELACION_EMPLEADO.ID_TIPO_EMPLEADO = ").append(Constantes.EMPLEADO_AUDITOR);
        return getJdbcTemplateBase().query(query.toString(), new FececAuditorPropuestasMapper(), rfc);
    }

}
