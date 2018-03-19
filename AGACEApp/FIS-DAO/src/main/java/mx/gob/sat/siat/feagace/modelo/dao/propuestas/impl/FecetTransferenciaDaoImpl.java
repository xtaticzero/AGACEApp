package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetTransferenciaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocTransferenciaPendValidMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetTransferenciaContadorMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetTransferenciaDetalleMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetTransferenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.DocTransferenciaSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferencia;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaContador;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetTransferenciaPk;
import mx.gob.sat.siat.feagace.modelo.enums.EstadoBooleanodeRegistroEnum;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetTransferenciaDao")
public class FecetTransferenciaDaoImpl extends BaseJDBCDao<FecetTransferencia> implements FecetTransferenciaDao {

    /**
     * ESTATUS PARA LA TRANSFERENCIA 1 = TRANSFERENCIA CREADA Y/O PENDIENTE DE
     * VALIDAR 2 = TRANSFERENCIA VALIDADA 3 = TRANSFERENCIA RECHAZADA 4 =
     * TRANSFERENCIA REGISTRADA EMEG
     */
    private static final String SQL_SELECT
            = "SELECT ID_TRANSFERENCIA, ID_ARACE_ORIGEN, ID_ARACE_DESTINO, ID_PROPUESTA, FECHA_TRASPASO, OBSERVACIONES, ID_EMPLEADO, BLN_ESTATUS FROM "
            + getTableName();

    private static final String SQL_SELECT_TRANSFER_PEND
            = "SELECT ID_TRANSFERENCIA, ID_ARACE_ORIGEN, ID_ARACE_DESTINO, ID_PROPUESTA, FECHA_TRASPASO, RFC_EMPLEADO, OBSERVACIONES, FECHA_RECHAZO, ID_ESTATUS, ID_EMPLEADO, BLN_ESTATUS FROM "
            + getTableName();

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_TRANSFERENCIA
     */
    private static final String SQL_DUAL = "SELECT FECEQ_TRANSFERENCIA.NEXTVAL FROM DUAL";
    private static final long serialVersionUID = 827357204715098133L;

    /**
     * Metodo getIdFecetProrrogaPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetTransferencia() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class);

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableNameDoc() {
        return "FECET_DOC_TRANSFERENCIA";
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    public FecetTransferenciaPk insert(FecetTransferencia dto) {

        dto.setIdTransferencia(getJdbcTemplateBase().queryForObject("SELECT FECEQ_TRANSFERENCIA.NEXTVAL FROM DUAL",
                BigDecimal.class));
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableName());
        query.append(" ( ID_TRANSFERENCIA, ID_ARACE_ORIGEN, ID_ARACE_DESTINO, ID_PROPUESTA, FECHA_TRASPASO, RFC_EMPLEADO, OBSERVACIONES, FECHA_RECHAZO, ID_ESTATUS, ID_EMPLEADO, BLN_ESTATUS ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdTransferencia(), dto.getIdAraceOrigen(),
                dto.getIdAraceDestino(), dto.getIdPropuesta(), dto.getFechaTraspaso(), dto.getRfc(),
                dto.getObservaciones(), dto.getFechaRechazo(), dto.getEstatus(), dto.getIdEmpleado(),
                dto.getBlnEstatus());
        return dto.createPk();

    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetTransferenciaPk
     */
    public FecetTransferenciaPk insertDocTransferencia(FecetTransferencia dto) {

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getTableNameDoc());
        query.append(" ( ID_DOC_TRANSFERENCIA, ID_TRANSFERENCIA, RUTA_ARCHIVO, FECHA_CREACION, ID_PROPUESTA, FECHA_FIN, BLN_ACTIVO ) VALUES ( ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(query.toString(), dto.getIdDocTransferencia(), dto.getIdTransferencia(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getIdPropuesta(), dto.getFechaFin(), dto.getBlnActivo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_TRANSFERENCIA table.
     */
    public void update(FecetTransferenciaPk pk, FecetTransferencia dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_TRANSFERENCIA = ?, ID_ARACE_ORIGEN = ?, ID_ARACE_DESTINO = ?, ID_PROPUESTA = ?, FECHA_TRASPASO = ?, RFC_EMPLEADO = ?, OBSERVACIONES = ?, FECHA_RECHAZO = ?, ID_ESTATUS = ?, ID_EMPLEADO = ?, BLN_ESTATUS = ?");
        getJdbcTemplateBase().update(query.toString(),
                dto.getIdTransferencia(), dto.getIdAraceOrigen(), dto.getIdAraceDestino(),
                dto.getIdPropuesta(), dto.getFechaTraspaso(), dto.getRfc(), dto.getObservaciones(),
                dto.getFechaRechazo(), dto.getEstatus(), dto.getIdEmpleado(), dto.getBlnEstatus(),
                pk.getIdTransferencia());
    }

    /**
     * Deletes a single row in the FECET_TRANSFERENCIA table.
     */
    public void delete(FecetTransferenciaPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_TRANSFERENCIA = ?");

        getJdbcTemplateBase().update(query.toString(),
                pk.getIdTransferencia());

    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_TRANSFERENCIA";
    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_TRANSFERENCIA = :idTransferencia'.
     */
    public FecetTransferencia findByPrimaryKey(BigDecimal idTransferencia) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_TRANSFERENCIA = ?");
        List<FecetTransferencia> list
                = getJdbcTemplateBase().query(query.toString(), new FecetTransferenciaMapper(),
                        idTransferencia);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria ''.
     */
    public List<FecetTransferencia> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_TRANSFERENCIA");
        return getJdbcTemplateBase().query(SQL_SELECT + " ORDER BY ID_TRANSFERENCIA", new FecetTransferenciaMapper());

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_TRANSFERENCIA = :idTransferencia'.
     */
    public List<FecetTransferencia> findWhereIdTransferenciaEquals(BigDecimal idTransferencia) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_TRANSFERENCIA = ? ORDER BY ID_TRANSFERENCIA");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), idTransferencia);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_ARACE_ORIGEN = :idAraceOrigen'.
     */
    public List<FecetTransferencia> findWhereIdAraceOrigenEquals(BigDecimal idAraceOrigen) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE_ORIGEN = ? ORDER BY ID_ARACE_ORIGEN");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), idAraceOrigen);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_ARACE_DESTINO = :idAraceDestino'.
     */
    public List<FecetTransferencia> findWhereIdAraceDestinoEquals(BigDecimal idAraceDestino) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_ARACE_DESTINO = ? ORDER BY ID_ARACE_DESTINO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), idAraceDestino);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'FECHA_TRASPASO = :fechaTraspaso'.
     */
    public List<FecetTransferencia> findWhereFechaTraspasoEquals(Date fechaTraspaso) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_TRASPASO = ? ORDER BY FECHA_TRASPASO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), fechaTraspaso);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RFC = :rfc'.
     */
    public List<FecetTransferencia> findWhereRfcEquals(String rfc) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC = ? ORDER BY RFC");
        return getJdbcTemplateBase().query(query.toString(), new FecetTransferenciaMapper(), rfc);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetTransferencia> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'DESCRIPCION = :descripcion'.
     */
    public List<FecetTransferencia> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), descripcion);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'OBSERVACIONES = :observaciones'.
     */
    public List<FecetTransferencia> findWhereObservacionesEquals(final String observaciones) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE OBSERVACIONES = ? ORDER BY OBSERVACIONES");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), observaciones);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    public List<FecetTransferencia> findWhereEstatusEquals(final String estatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), estatus);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ESTATUS = :estatus'.
     */
    public List<FecetTransferencia> findWhereRfcFirmanteEquals(final String rfcFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_FIRMANTE = ? ORDER BY RFC_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), rfcFirmante);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'DESCRIPCION_FIRMANTE = :descripcionFirmante'.
     */
    public List<FecetTransferencia> findWhereDescripcionFirmanteEquals(String descripcionFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION_FIRMANTE = ? ORDER BY DESCRIPCION_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), descripcionFirmante);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    public List<FecetTransferencia> findWhereFechaRechazoFirmanteEquals(Date fechaRechazoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), fechaRechazoFirmante);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'NOMBRE_ARCHIVO_FIRMANTE = :nombreArchivoFirmante'.
     */
    public List<FecetTransferencia> findWhereNombreArchivoFirmanteEquals(String nombreArchivoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE_ARCHIVO_FIRMANTE = ? ORDER BY NOMBRE_ARCHIVO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), nombreArchivoFirmante);

    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'RUTA_ARCHIVO_FIRMANTE = :rutaArchivoFirmante'.
     */
    public List<FecetTransferencia> findWhereRutaArchivoFirmanteEquals(String rutaArchivoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RUTA_ARCHIVO_FIRMANTE = ? ORDER BY RUTA_ARCHIVO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), rutaArchivoFirmante);

    }

    /**
     * Returns the rows from the FECET_TRANSFERENCIA table that matches the
     * specified primary-key value.
     */
    public FecetTransferencia findByPrimaryKey(FecetTransferenciaPk pk) {
        return findByPrimaryKey(pk.getIdTransferencia());
    }

    /**
     * Method 'getIdDocTransferencia'
     *
     * @return BigDecimal
     */
    public BigDecimal getIdDocTransferencia() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOCTRANSFERENCIA.NEXTVAL FROM DUAL", BigDecimal.class);

    }

    @Override
    public int actualizarEstadoTransferencia(Long idTransferencia, EstadoBooleanodeRegistroEnum estadoBln) {

        String querey = "UPDATE FECET_TRANSFERENCIA SET BLN_ESTATUS = {ID_ESTATUS} WHERE ID_TRANSFERENCIA = ?";

        return getJdbcTemplateBase().update(querey.replace("{ID_ESTATUS}", estadoBln.getEstado().toString()), idTransferencia);
    }

    /**
     * Returns all rows from the FECET_TRANSFERENCIA table that match the
     * criteria 'ID_PROPUESTA = :idPropuesta'.
     */
    public List<FecetTransferencia> findWhereIdPropuestaEqualsTransfPendientes(final BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_TRANSFER_PEND);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_TRANSFERENCIA DESC");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetTransferenciaMapper(), idPropuesta);

    }

    public List<FecetTransferencia> findDocWhereIdTransferenciaEquals(BigDecimal idTransferencia) {
        String sql = "SELECT ID_DOC_TRANSFERENCIA, ID_TRANSFERENCIA, RUTA_ARCHIVO "
                + " FROM FECET_DOC_TRANSFERENCIA ";
        StringBuilder query = new StringBuilder();
        query.append(sql);
        query.append(" WHERE ID_TRANSFERENCIA = ? ORDER BY FECHA_CREACION DESC ");
        return getJdbcTemplateBase().query(query.toString(),
                new FecetDocTransferenciaPendValidMapper(), idTransferencia);
    }

    /**
     * Updates field ID_TRANSFERENCIA_NUEVA in the FECET_TRANSFERENCIA table.
     */
    public void updateIdPropuestaNueva(BigDecimal idPropuestaNueva, BigDecimal idTransferencia) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET ID_TRANSFERENCIA_NUEVA = ?  WHERE ID_TRANSFERENCIA = ? ");
        getJdbcTemplateBase().update(query.toString(), idPropuestaNueva, idTransferencia);
    }

    @Override
    public FecetTransferencia obtenerTransferenciaXIdPropuestaBlnEstatus(BigDecimal idPropuesta, EstadoBooleanodeRegistroEnum estDoctos) {
        Object[] params = new Object[]{idPropuesta, estDoctos.getValue()};

        try {
            return getJdbcTemplateBase().queryForObject(DocTransferenciaSQL.OBTENER_TRANSFERENCIA_X_IDPROPUESTA_BLNESTATUS, params, new FecetTransferenciaDetalleMapper());
        } catch (EmptyResultDataAccessException e) {
            FecetTransferencia transferencia = new FecetTransferencia();
            transferencia.setLstDocumentos(new ArrayList<FecetDocTransferencia>());
            return (transferencia);
        }

    }

    @Override
    public void updateEstatusTransferencia(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_TRANSFERENCIA SET BLN_ESTATUS = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    @Override
    public void updateEstatusDocTransferencia(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_TRANSFERENCIA SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    /**
     * Updates fields FECHA_RECHAZO, MOTIVO_RECHAZO in the FECET_TRANSFERENCIA
     * table.
     */
    public void updateTransferenciaRechazo(Date fechaRechazo, BigDecimal idMotivo, BigDecimal idTransferencia) {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(" SET FECHA_RECHAZO = ?, MOTIVO_RECHAZO = ? WHERE ID_TRANSFERENCIA = ? ");
        getJdbcTemplateBase().update(query.toString(), fechaRechazo, idMotivo, idTransferencia);
    }

    public List<FecetTransferenciaContador> obtenerTransferenciasByIdPropuesta(BigDecimal idPropuesta, TipoEstatusEnum estatus, BigDecimal blnEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT TRANSFERENCIA.ID_TRANSFERENCIA, TRANSFERENCIA.ID_ARACE_ORIGEN, TRANSFERENCIA.ID_ARACE_DESTINO, ");
        query.append("\n TRANSFERENCIA.ID_PROPUESTA, TRANSFERENCIA.FECHA_TRASPASO, TRANSFERENCIA.RFC_EMPLEADO, TRANSFERENCIA.OBSERVACIONES, ");
        query.append("\n TRANSFERENCIA.FECHA_RECHAZO, TRANSFERENCIA.ID_ESTATUS, TRANSFERENCIA.ID_EMPLEADO, TRANSFERENCIA.BLN_ESTATUS, ");
        query.append("\n TRANSFERENCIA.ID_TRANSFERENCIA_NUEVA, TRANSFERENCIA.MOTIVO_RECHAZO, ");
        query.append("\n (SELECT COUNT(*) FROM FECET_DOC_TRANSFERENCIA WHERE ID_TRANSFERENCIA = TRANSFERENCIA.ID_TRANSFERENCIA AND BLN_ESTATUS = TRANSFERENCIA.BLN_ESTATUS) AS TOTAL_DOCUMENTOS ");
        query.append("\n FROM FECET_TRANSFERENCIA TRANSFERENCIA ");
        query.append("\n WHERE TRANSFERENCIA.ID_PROPUESTA = ? ");
        query.append("\n AND TRANSFERENCIA.ID_ESTATUS = ?");
        query.append("\n AND TRANSFERENCIA.BLN_ESTATUS = ?");

        return getJdbcTemplateBase().query(query.toString(), new FecetTransferenciaContadorMapper(), idPropuesta, estatus.getId(), blnEstatus);
    }
}
