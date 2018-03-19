/*
 * Todos los Derechos Reservados 2013 SAT.
 * Servicio de Administracion Tributaria (SAT).
 * Este software contiene informacion propiedad exclusiva del SAT considerada
 * Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 * parcial o total.
 */
package mx.gob.sat.siat.feagace.modelo.dao.insumos.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.FecetRetroalimentacionDao;
import mx.gob.sat.siat.feagace.modelo.dao.insumos.sql.FecetRetroalimentacionSQL;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocumentosRetroalimentarMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetHistorialRetroalimentacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetHistoricoRetroalimentacionMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetRetroalimentacionMapper;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacion;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRetroalimentacionPk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetRetroalimentacionDao")
public class FecetRetroalimentacionDaoImpl extends BaseJDBCDao<FecetRetroalimentacion>
        implements FecetRetroalimentacionDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = 6114864812271480964L;

    /**
     * ESTATUS PARA LA RETROALIMENTACION 1 = RETROALIMENTACION CREADA Y/O
     * PENDIENTE DE VALIDAR 2 = RETROALIMENTACION VALIDADA 3 = RETROALIMENTACION
     * COMPLEMENTADA 4 = RETROALIMENTACION RECHAZADA
     */
    private static final String SQL_SELECT = "SELECT ID_RETROALIMENTACION, ID_PROPUESTA, DESCRIPCION, FECHA_CREACION, RFC_RETROALIMENTACION, FECHA_RETROALIMENTACION, FECHA_RECHAZO, DESCRIPCION_RECHAZO, ID_EMPLEADO, ID_ESTATUS, BLN_ESTATUS FROM "
            + getTableName();

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_TRANSFERENCIA
     */
    private static final String SQL_DUAL = "SELECT FECEQ_RETROALIMENTACION.NEXTVAL FROM DUAL";

    private static final String INSERT_INTO = "INSERT INTO ";

    /**
     * Method 'insertarRetroalimentacion'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    @Override
    public FecetRetroalimentacion insertarRetroalimentacion(FecetRetroalimentacion dto) {

        dto.setIdRetroalimentacion(getJdbcTemplateBase()
                .queryForObject("SELECT FECEQ_RETROALIMENTACION.NEXTVAL FROM DUAL", BigDecimal.class));

        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO);
        query.append(getTableName());
        query.append(
                " ( ID_RETROALIMENTACION, ID_PROPUESTA, ID_MOTIVO, DESCRIPCION, FECHA_CREACION, RFC_RETROALIMENTACION, FECHA_RETROALIMENTACION, FECHA_RECHAZO, DESCRIPCION_RECHAZO, ID_EMPLEADO, ID_ESTATUS, BLN_ESTATUS ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdRetroalimentacion(), dto.getIdPropuesta(),
                dto.getIdMotivo(), dto.getDescripcion(), dto.getFechaCreacion(), dto.getRfcRetroalimentacion(),
                dto.getFechaRetroalimentacion(), dto.getFechaRechazo(), dto.getDescripcionRechazo(),
                dto.getIdEmpleado(), dto.getEstatus(), dto.getBlnEstatus());

        return dto;

    }

    /**
     * Method 'insertarRetroalimentacion'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    @Override
    public FecetRetroalimentacion insertarRetroalimentacionHistorial(FecetRetroalimentacion dto) {
        dto.setIdRetroalimentacion(getJdbcTemplateBase()
                .queryForObject("SELECT FECEQ_RETROALIMENTACION.NEXTVAL FROM DUAL", BigDecimal.class));

        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO);
        query.append(getTableName());
        query.append(
                " ( ID_RETROALIMENTACION, ID_PROPUESTA, ID_MOTIVO, DESCRIPCION, FECHA_CREACION, RFC_RETROALIMENTACION, FECHA_RETROALIMENTACION, FECHA_RECHAZO, DESCRIPCION_RECHAZO, ID_EMPLEADO, ID_ESTATUS, BLN_ESTATUS,");
        query.append(
                "ID_UNIDAD_ADMINISTRATIVA,ID_SUBPROGRAMA,ID_METODO,ID_REVISION,ID_TIPO_PROPUESTA,ID_CAUSA_PROGRAMACION,ID_SECTOR,FECHA_INICIO_PERIODO,FECHA_FIN_PERIODO,FECHA_PRESENTACION,FECHA_INFORME,PRIORIDAD )");
        query.append("VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdRetroalimentacion(), dto.getIdPropuesta(),
                dto.getIdMotivo(), dto.getDescripcion(), dto.getFechaCreacion(), dto.getRfcRetroalimentacion(),
                dto.getFechaRetroalimentacion(), dto.getFechaRechazo(), dto.getDescripcionRechazo(),
                dto.getIdEmpleado(), dto.getEstatus(), dto.getBlnEstatus(), dto.getIdArace(), dto.getIdSubprograma(),
                dto.getIdMetodo(), dto.getIdRevision(), dto.getIdTipoPropuesta(), dto.getIdCausaProgramacion(),
                dto.getIdSector(), dto.getFechaInicioPeriodo(), dto.getFechaFinPeriodo(), dto.getFechaPresentacion(),
                dto.getFechaInforme(), dto.getPrioridadSugerida());
        return dto;

    }

    /**
     * Method 'insertarDocRetroalimentacion'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    @Override
    public FecetRetroalimentacionPk insertarDocRetroalimentacion(FecetRetroalimentacion dto) {

        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO);
        query.append(getDocTableName());
        query.append(
                " (ID_RETROALIMENTACION, ID_PROPUESTA, RUTA_ARCHIVO, FECHA_CREACION, ID_DOC_RETRO, FECHA_FIN, BLN_ACTIVO ) VALUES (?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdRetroalimentacion(), dto.getIdPropuesta(),
                dto.getRutaArchivo(), dto.getFechaRetroalimentacion(), dto.getIdDocRetroalimentacion(),
                dto.getFechaFin(), dto.getBlnActivo());
        return dto.createPk();

    }

    /**
     * Method 'insertarAsociaRetro'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    @Override
    public FecetRetroalimentacionPk insertarAsociaRetro(FecetRetroalimentacion dto) {

        StringBuilder query = new StringBuilder();
        query.append(INSERT_INTO);
        query.append(getAsoseaTableName());
        query.append(
                " (ID_PROPUESTA_RETRO,ID_PROPUESTA, ID_RETROALIMENTACION, ID_RETROALIMENTACION_ORIGEN ) VALUES (?,?,?,?)");
        getJdbcTemplateBase().update(query.toString(), getConsecutivoRetroalimentarPro(), dto.getIdPropuesta(),
                dto.getIdRetroalimentacion(), dto.getIdRetroalimentacionOrigen());
        return dto.createPk();
    }

    /**
     * Metodo getIdFecetProrrogaPathDirectorio
     *
     * @return BigDecimal @
     */
    @Override
    public BigDecimal getIdFecetRetroalimentacion() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_RETROALIMENTACION";
    }

    /**
     * Method 'getDocTableName'
     *
     * @return String
     */
    public static String getDocTableName() {
        return "FECET_DOC_RETRO_PROPUESTA";
    }

    /**
     * Method 'getDocTableName'
     *
     * @return String
     */
    public static String getAsoseaTableName() {
        return "FECEA_PROPUESTA_RETRO";
    }

    /**
     * Returns all rows from the FECET_RETROALIMENTACION table that match the
     * criteria 'ID_RETROALIMENTACION = :idRetroalimentacion'.
     */
    @Override
    public FecetRetroalimentacion findByPrimaryKey(BigDecimal idRetroalimentacion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RETROALIMENTACION = ?");
        List<FecetRetroalimentacion> list = getJdbcTemplateBase().query(query.toString(),
                new FecetRetroalimentacionMapper(), idRetroalimentacion);
        return list.size() == 0 ? null : list.get(0);

    }

    @Override
    public List<FecetRetroalimentacion> getDocRetroalimetarByIdPropuesta(BigDecimal idPropuesta) {
        return getJdbcTemplateBase().query(FecetRetroalimentacionSQL.DETALLE_RETRO_X_ID_PROPUESTA,
                new FecetHistoricoRetroalimentacionMapper(), idPropuesta);
    }

    @Override
    public List<FecetRetroalimentacion> getDocRetroalimetarByOrigen(BigDecimal idRetroOrigen) {
        return getJdbcTemplateBase().query(FecetRetroalimentacionSQL.DETALLE_RETRO_X_ID_ORIGEN,
                new FecetHistoricoRetroalimentacionMapper(), idRetroOrigen);
    }

    @Override
    public List<FecetRetroalimentacion> getDocRetroalimetarHistorialByIdPropuesta(BigDecimal idPropuesta) {

        return getJdbcTemplateBase().query(FecetRetroalimentacionSQL.DETALLE_RETRO_HISTORIAL_ID_PROPUESTA,
                new FecetHistorialRetroalimentacionMapper(), idPropuesta);

    }

    @Override
    public List<FecetRetroalimentacion> getDocRetroalimetarByIdRetroaliemntacion(BigDecimal idRetroalimentacion) {
        return getJdbcTemplateBase().query(FecetRetroalimentacionSQL.DETALLE_DOC_RETRO_X_ID_RETRO,
                new FecetDocumentosRetroalimentarMapper(), idRetroalimentacion);
    }

    @Override
    public BigDecimal getConsecutivoDocRetroalimentar() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_RETRO_PROPUESTA.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public void updateEstatusRetroalimentacion(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_RETROALIMENTACION SET BLN_ESTATUS = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    /**
     *
     * @param idPropuesta
     * @param estado
     * @return
     */
    @Override
    public BigDecimal getIdRetroalimentacionXEstadoIdPropuesta(final BigDecimal idPropuesta,
            final EstadoBooleanodeRegistroEnum estado) {
        BigDecimal reg = null;
        SqlRowSet srs;
        Object[] params = new Object[]{idPropuesta, estado.getValue()};

        srs = getJdbcTemplateBase().queryForRowSet(FecetRetroalimentacionSQL.OBTENER_ID_RETROALIMENTACION_X_ESTADO,
                params);

        while (srs.next()) {
            reg = srs.getBigDecimal("ID_RETROALIMENTACION");
        }

        return reg;
    }

    @Override
    public int updateEstadoRetroalimentacion(BigDecimal idRetroalimentacion, EstadoBooleanodeRegistroEnum estado) {

        if (idRetroalimentacion != null && estado != null) {

            Object[] params = new Object[]{estado.getValue(), idRetroalimentacion};

            return getJdbcTemplateBase().update(FecetRetroalimentacionSQL.UPDATE_ESTADO_RETROALIMENTACION, params);

        } else {
            return 0;
        }

    }

    @Override
    public void updateEstatusDocRetroalimentacion(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_RETRO_PROPUESTA SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    @Override
    public BigDecimal getConsecutivoRetroalimentarPro() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_PROPUESTA_RETRO.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    @Override
    public void retroAtendida(BigDecimal idRetroalimentacion) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECEA_PROPUESTA_RETRO SET ID_RETROALIMENTACION_ORIGEN=");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_RETROALIMENTACION = ? ");

        getJdbcTemplateBase().update(query.toString(), idRetroalimentacion);
    }

}
