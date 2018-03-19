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
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionInsumoDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetContadorInsumosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetRetroalimentacionInsumoConMotivoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetRetroalimentacionInsumoHistorial;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.mapper.FecetRetroalimentacionInsumoMapper;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetContadorInsumos;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo;
import mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumoPk;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetRetroalimentacionInsumoDao")
public class FecetRetroalimentacionInsumoDaoImpl extends BaseJDBCDao<FecetRetroalimentacionInsumo>
        implements FecetRetroalimentacionInsumoDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -7102301801219616703L;

    /**
     * ESTATUS PARA LA RETROALIMENTACION 1 = RETROALIMENTACION CREADA Y/O
     * PENDIENTE DE VALIDAR 2 = RETROALIMENTACION VALIDADA 3 = RETROALIMENTACION
     * COMPLEMENTADA 4 = RETROALIMENTACION RECHAZADA
     */
    private static final String SQL_SELECT = "SELECT ID_RETROALIMENTACION_INSUMO, ID_INSUMO, ID_MOTIVO, MOTIVO_ACIACE, MOTIVO_SUBADMINISTRADOR, FECHA_CREACION, RFC_RETROALIMENTACION, FECHA_RETROALIMENTACION, ESTATUS, RFC_RECHAZO, FECHA_RECHAZO, DESCRIPCION_RECHAZO FROM "
            + getTableName();

    private static final String SQL_SELECT_COUNTER = "SELECT FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO, FECET_RETROALIMENTACION_INSUMO.FECHA_RETROALIMENTACION, FECET_RETROALIMENTACION_INSUMO.MOTIVO_ACIACE, FECET_RETROALIMENTACION_INSUMO.MOTIVO_SUBADMINISTRADOR,  COUNT(FECET_DOCRETROINSUMO.ID_DOCRETROINSUMO) AS CONTADOR, FECET_RETROALIMENTACION_INSUMO.ID_INSUMO FROM FECET_INSUMO ";

    private String getSqlSelectMotivo() {
        StringBuilder sqlSelectMotivo = new StringBuilder();
        sqlSelectMotivo.append(
                "SELECT FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO, FECET_RETROALIMENTACION_INSUMO.ID_INSUMO, FECET_RETROALIMENTACION_INSUMO.MOTIVO_SUBADMINISTRADOR, FECET_RETROALIMENTACION_INSUMO.FECHA_CREACION");
        sqlSelectMotivo.append(
                ", FECET_RETROALIMENTACION_INSUMO.RFC_RETROALIMENTACION, FECET_RETROALIMENTACION_INSUMO.FECHA_RETROALIMENTACION");
        sqlSelectMotivo.append(
                ", FECET_RETROALIMENTACION_INSUMO.ESTATUS, FECET_RETROALIMENTACION_INSUMO.RFC_RECHAZO, FECET_RETROALIMENTACION_INSUMO.FECHA_RECHAZO");
        sqlSelectMotivo.append(
                ", FECET_RETROALIMENTACION_INSUMO.DESCRIPCION_RECHAZO, FECEC_MOTIVO.ID_MOTIVO, FECEC_MOTIVO.DESCRIPCION AS DESCRIPCION_MOTIVO");
        sqlSelectMotivo.append(", FECEC_MOTIVO.TIPO_MOTIVO FROM ");
        sqlSelectMotivo.append(getTableName());
        sqlSelectMotivo.append(
                " LEFT JOIN FECEC_MOTIVO ON FECET_RETROALIMENTACION_INSUMO.ID_MOTIVO = FECEC_MOTIVO.ID_MOTIVO  WHERE FECET_RETROALIMENTACION_INSUMO.ID_INSUMO = ?");
        return sqlSelectMotivo.toString();
    }

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_TRANSFERENCIA
     */
    private static final String SQL_DUAL = "SELECT FECEQ_RETROALIMENTACION_INSUMO.NEXTVAL FROM DUAL";

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetRetroalimentacionInsumoPk
     */
    public FecetRetroalimentacionInsumoPk insert(FecetRetroalimentacionInsumo dto) {

        if (dto.getIdRetroalimentacionInsumo() == null) {
            dto.setIdRetroalimentacionInsumo(getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class));
        }

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(
                " ( ID_RETROALIMENTACION_INSUMO, ID_INSUMO, MOTIVO_ACIACE, MOTIVO_SUBADMINISTRADOR, FECHA_CREACION, RFC_RETROALIMENTACION, ESTATUS, RFC_RECHAZO, FECHA_RECHAZO, DESCRIPCION_RECHAZO, ID_MOTIVO, ID_UNIDAD_ADMINISTRATIVA, ID_SUBPROGRAMA, ID_SECTOR, FECHA_INICIO_PERIODO, FECHA_FIN_PERIODO, ID_PRIORIDAD ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
        logger.error(query.toString());
        getJdbcTemplateBase().update(query.toString(), dto.getIdRetroalimentacionInsumo(), dto.getIdInsumo(),
                dto.getMotivoAciace(), dto.getMotivoSubadministrador(), dto.getFechaCreacion(),
                dto.getRfcRetroalimentacion(), dto.getEstatus(), dto.getRfcRechazo(), dto.getFechaRechazo(),
                dto.getDescripcionRechazo(), dto.getIdMotivo(), dto.getIdUnidadAdministrativa(), dto.getIdSubprograma(),
                dto.getIdSector(), dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(), dto.getIdPrioridad());
        return dto.createPk();

    }

    /**
     * Metodo getIdFecetProrrogaPathDirectorio
     *
     * @return BigDecimal @
     */
    public BigDecimal getIdFecetRetroalimentacionInsumo() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class);

    }

    /**
     * Updates a single row in the FECET_RETROALIMENTACION_INSUMO table.
     */
    public void update(FecetRetroalimentacionInsumoPk pk, FecetRetroalimentacionInsumo dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(
                " SET ID_RETROALIMENTACION_INSUMO = ?, ID_INSUMO = ?, ID_MOTIVO = ?, MOTIVO_SUBADMINISTRADOR = ?, MOTIVO_ACIACE = ?, FECHA_CREACION = ?, RFC_RETROALIMENTACION = ?,");
        query.append(
                " FECHA_RETROALIMENTACION = ?, ESTATUS = ?, RFC_RECHAZO = ?, FECHA_RECHAZO = ?, DESCRIPCION_RECHAZO = ?, ID_UNIDAD_ADMINISTRATIVA = ?, ID_SUBPROGRAMA = ?,");
        query.append(
                " ID_SECTOR = ?, FECHA_INICIO_PERIODO = ?, FECHA_FIN_PERIODO = ?, ID_PRIORIDAD = ? WHERE ID_RETROALIMENTACION_INSUMO = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdRetroalimentacionInsumo(), dto.getIdInsumo(),
                dto.getIdMotivo(), dto.getMotivoSubadministrador(), dto.getMotivoAciace(), dto.getFechaCreacion(),
                dto.getRfcRetroalimentacion(), dto.getFechaRetroalimentacion(), dto.getEstatus(), dto.getRfcRechazo(),
                dto.getFechaRechazo(), dto.getDescripcionRechazo(), dto.getIdUnidadAdministrativa(),
                dto.getIdSubprograma(), dto.getIdSector(), dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(),
                dto.getIdPrioridad(), pk.getIdRetroalimentacionInsumo());

    }

    /**
     * Updates a single row in the FECET_COMP_TERCERO table to reactivate plazo.
     */
    public BigDecimal actualizaInsumoRetroAtendida(final BigDecimal idRetroalimentacionInsumo, String motivoAciace,
            Date fechaRetro) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE ").append(getTableName());
        query.append(
                " SET ESTATUS = 3, FECHA_RETROALIMENTACION = ?, MOTIVO_ACIACE = ? WHERE ID_RETROALIMENTACION_INSUMO = ? ");

        return getJdbcTemplateBase().update(query.toString(), fechaRetro, motivoAciace, idRetroalimentacionInsumo) <= 0
                ? Constantes.BIG_DECIMAL_CERO : idRetroalimentacionInsumo;

    }

    /**
     * Deletes a single row in the FECET_RETROALIMENTACION_INSUMO table.
     */
    public void delete(FecetRetroalimentacionInsumoPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_RETROALIMENTACION_INSUMO = ?");

        getJdbcTemplateBase().update(query.toString(), pk.getIdRetroalimentacionInsumo());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_RETROALIMENTACION_INSUMO";
    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_RETROALIMENTACION_INSUMO = :idRetroalimentacionInsumo'.
     */
    public FecetRetroalimentacionInsumo findByPrimaryKey(BigDecimal idRetroalimentacionInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RETROALIMENTACION_INSUMO = ?");
        List<FecetRetroalimentacionInsumo> list = getJdbcTemplateBase().query(query.toString(),
                new FecetRetroalimentacionInsumoMapper(), idRetroalimentacionInsumo);
        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria ''.
     */
    public List<FecetRetroalimentacionInsumo> findAll() {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_RETROALIMENTACION_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper());
    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_RETROALIMENTACION_INSUMO = :idRetroalimentacionInsumo'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereIdRetroalimentacionInsumoEquals(
            BigDecimal idRetroalimentacionInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RETROALIMENTACION_INSUMO = ? ORDER BY ID_RETROALIMENTACION_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(),
                idRetroalimentacionInsumo);

    }

    /**
     * Cuenta todas los documentos que corresponden a cada retroalimentacion.
     */
    public List<FecetContadorInsumos> getContadorInsumosPorRetroalimentar(final BigDecimal idInsumo,
            final BigDecimal tipoEmpleado) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_COUNTER);
        query.append(
                " INNER JOIN FECET_RETROALIMENTACION_INSUMO ON FECET_INSUMO.ID_INSUMO = FECET_RETROALIMENTACION_INSUMO.ID_INSUMO \n");
        query.append(
                " INNER JOIN FECET_DOCRETROINSUMO ON FECET_DOCRETROINSUMO.ID_RETROALIMENTACION_INSUMO = FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO \n");
        query.append(
                " WHERE FECET_INSUMO.ID_INSUMO = ? AND FECET_DOCRETROINSUMO.ID_TIPO_EMPLEADO = ? AND FECET_RETROALIMENTACION_INSUMO.ESTATUS = 2  \n");
        query.append(
                " GROUP BY FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO, FECET_RETROALIMENTACION_INSUMO.FECHA_RETROALIMENTACION, \n");
        query.append(
                " FECET_RETROALIMENTACION_INSUMO.MOTIVO_SUBADMINISTRADOR, FECET_RETROALIMENTACION_INSUMO.MOTIVO_ACIACE, FECET_RETROALIMENTACION_INSUMO.ID_INSUMO \n");
        query.append(" ORDER BY FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO DESC ");

        return getJdbcTemplateBase().query(query.toString(), new FecetContadorInsumosMapper(), idInsumo, tipoEmpleado);
    }

    /**
     * Cuenta todas los documentos que corresponden a cada retroalimentacion.
     */
    public List<FecetContadorInsumos> getContadorInsumosRetroalimentados(final BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_COUNTER);
        query.append(
                " INNER JOIN FECET_RETROALIMENTACION_INSUMO ON FECET_INSUMO.ID_INSUMO = FECET_RETROALIMENTACION_INSUMO.ID_INSUMO \n");
        query.append(
                " INNER JOIN FECET_DOCRETROINSUMO ON FECET_DOCRETROINSUMO.ID_RETROALIMENTACION_INSUMO = FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO \n");
        query.append(" WHERE FECET_INSUMO.ID_INSUMO = ? AND FECET_RETROALIMENTACION_INSUMO.ESTATUS IN (1,3) \n");
        query.append(
                " GROUP BY FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO, FECET_RETROALIMENTACION_INSUMO.FECHA_RETROALIMENTACION, \n");
        query.append(
                " FECET_RETROALIMENTACION_INSUMO.MOTIVO_SUBADMINISTRADOR, FECET_RETROALIMENTACION_INSUMO.MOTIVO_SUBADMINISTRADOR, FECET_RETROALIMENTACION_INSUMO.ID_INSUMO \n");
        query.append(" ORDER BY FECET_RETROALIMENTACION_INSUMO.ID_RETROALIMENTACION_INSUMO DESC ");

        return getJdbcTemplateBase().query(query.toString(), new FecetContadorInsumosMapper(), idInsumo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_INSUMO = :idInsumo'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereIdInsumoEquals(BigDecimal idInsumo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_INSUMO = ? ORDER BY ID_INSUMO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), idInsumo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), fechaCreacion);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'RFC_RETROALIMENTACION = :rfcRetroalimentacion'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereRfcRetroalimentacionInsumoEquals(String rfcRetroalimentacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_RETROALIMENTACION = ? ORDER BY RFC_RETROALIMENTACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(),
                rfcRetroalimentacion);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_RETROALIMENTACION = :fechaRetroalimentacion'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereFechaRetroalimentacionInsumoEquals(Date fechaRetroalimentacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RETROALIMENTACION = ? ORDER BY FECHA_RETROALIMENTACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(),
                fechaRetroalimentacion);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ESTATUS = :estatus'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereEstatusEquals(String estatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), estatus);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'RFC_RECHAZO = :rfcRechazo'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereRfcRechazoEquals(String rfcRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_RECHAZO = ? ORDER BY RFC_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), rfcRechazo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'FECHA_RECHAZO = :fechaRechazo'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereFechaRechazoEquals(Date fechaRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RECHAZO = ? ORDER BY FECHA_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), fechaRechazo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'DESCRIPCION_RECHAZO = :descripcionRechazo'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereDescripcionRechazoEquals(String descripcionRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION_RECHAZO = ? ORDER BY DESCRIPCION_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(),
                descripcionRechazo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetRetroalimentacionInsumo> findWhereIdMotivoEquals(BigDecimal idMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO = ? ORDER BY ID_MOTIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoMapper(), idMotivo);

    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION_INSUMO table that match
     * the criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetRetroalimentacionInsumo> getRetroalimentacionInsumo(final BigDecimal idInsumo) {

        return getJdbcTemplateBase().query(getSqlSelectMotivo(), new FecetRetroalimentacionInsumoConMotivoMapper(),
                idInsumo);

    }

    /**
     * Returns the rows from the FECET_RETROALIMENTACION_INSUMO table that
     * matches the specified primary-key value.
     */
    public FecetRetroalimentacionInsumo findByPrimaryKey(FecetRetroalimentacionInsumoPk pk) {
        return findByPrimaryKey(pk.getIdRetroalimentacionInsumo());
    }

    @Override
    public List<FecetRetroalimentacionInsumo> getHistoricoRetroalimentacion(FecetInsumo fecetInsumo) {
        StringBuilder query = new StringBuilder();
        query.append(
                "SELECT RINS.ID_RETROALIMENTACION_INSUMO, RINS.ID_INSUMO, RINS.ID_MOTIVO, RINS.MOTIVO_SUBADMINISTRADOR, RINS.MOTIVO_ACIACE, RINS.FECHA_CREACION, RINS.RFC_RETROALIMENTACION\n");
        query.append(
                ", RINS.FECHA_RETROALIMENTACION, RINS.ESTATUS, RINS.RFC_RECHAZO, RINS.FECHA_RECHAZO, RINS.DESCRIPCION_RECHAZO, RINS.ID_UNIDAD_ADMINISTRATIVA, RINS.ID_SUBPROGRAMA\n");
        query.append(
                ", RINS.ID_SECTOR, RINS.FECHA_INICIO_PERIODO, RINS.FECHA_FIN_PERIODO, RINS.ID_PRIORIDAD, PRIORIDAD.VALOR VALOR_PRIORIDAD, SUB.DESCRIPCION AS DESC_SUBPROGRAMA,  SEC.DESCRIPCION AS DESC_SECTOR\n");
        query.append(
                ", (SELECT COUNT(SOL.ID_DOCRETROINSUMO) FROM FECET_DOCRETROINSUMO SOL WHERE RINS.ID_RETROALIMENTACION_INSUMO = SOL.ID_RETROALIMENTACION_INSUMO AND SOL.ID_TIPO_EMPLEADO = 5) AS SOLICITUDES \n");
        query.append("   FROM FECET_RETROALIMENTACION_INSUMO RINS  \n");

        query.append(" LEFT JOIN FECEC_SUBPROGRAMA SUB ON RINS.ID_SUBPROGRAMA = SUB.ID_SUBPROGRAMA\n");
        query.append(" LEFT JOIN FECEC_SECTOR SEC ON RINS.ID_SECTOR = SEC.ID_SECTOR\n");
        query.append(" INNER JOIN FECEC_PRIORIDAD PRIORIDAD ON RINS.ID_PRIORIDAD = PRIORIDAD.ID_PRIORIDAD \n");
        query.append("WHERE RINS.ID_INSUMO = ?  \n");
        query.append(
                "   GROUP BY(RINS.ID_RETROALIMENTACION_INSUMO, RINS.ID_INSUMO, RINS.ID_MOTIVO, RINS.MOTIVO_SUBADMINISTRADOR, RINS.MOTIVO_ACIACE, RINS.FECHA_CREACION, RINS.RFC_RETROALIMENTACION\n");
        query.append(
                ", RINS.FECHA_RETROALIMENTACION, RINS.ESTATUS, RINS.RFC_RECHAZO, RINS.FECHA_RECHAZO, RINS.DESCRIPCION_RECHAZO, RINS.ID_UNIDAD_ADMINISTRATIVA, RINS.ID_SUBPROGRAMA\n");
        query.append(
                ", RINS.ID_SECTOR, RINS.FECHA_INICIO_PERIODO, RINS.FECHA_FIN_PERIODO, RINS.ID_PRIORIDAD, PRIORIDAD.VALOR, SUB.DESCRIPCION, SEC.DESCRIPCION)\n");
        query.append("ORDER BY FECHA_RETROALIMENTACION DESC");

        return getJdbcTemplateBase().query(query.toString(), new FecetRetroalimentacionInsumoHistorial(),
                fecetInsumo.getIdInsumo());
    }
}
