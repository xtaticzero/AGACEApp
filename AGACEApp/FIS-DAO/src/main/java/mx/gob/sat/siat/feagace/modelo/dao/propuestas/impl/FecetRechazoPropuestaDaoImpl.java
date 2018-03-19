package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececSubprogramaDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl.FececMotivoDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.ordenes.impl.FecetContribuyenteDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetRechazoPropuestaDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.ConsultaRechazoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetContadorPropuestasRechazadosMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocumentosRechazoMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetHistoricoRechazoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetRechazoPropuestaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetRechazoPropuestaVerifProcedenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.PropuestasSQL;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.RechazoPropuestaSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.ConsultaInformeComiteRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetContadorPropuestasRechazados;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuestaPk;
import mx.gob.sat.siat.feagace.modelo.enums.TipoEstatusEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetRechazoPropuestaDao")
public class FecetRechazoPropuestaDaoImpl extends BaseJDBCDao<FecetRechazoPropuesta>
        implements FecetRechazoPropuestaDao {

    private static final long serialVersionUID = 3324107398582969445L;

    private static final String SQL_SELECT = "SELECT ID_RECHAZO_PROPUESTA, ID_PROPUESTA, ID_MOTIVO, DESCRIPCION, FECHA_RECHAZO, FECHA_INFORME_RECHAZO, RFC_RECHAZO, RUTA_ARCHIVO, TIPO, ESTATUS, DESCRIPCION_FIRMANTE, FECHA_RECHAZO_FIRMANTE, RFC_RECHAZO_FIRMANTE, NOMBRE_ARCHIVO_FIRMANTE, RUTA_ARCHIVO_FIRMANTE FROM "
            + getTableName();

    private static final String SQL_DUAL = "SELECT FECEQ_RECHAZO_PROPUESTA.NEXTVAL FROM DUAL";

    @Override
    public FecetRechazoPropuestaPk insert(FecetRechazoPropuesta dto) {
        if (dto.getIdRechazoPropuesta() == null) {
            dto.setIdRechazoPropuesta(getJdbcTemplateBase()
                    .queryForObject("SELECT FECEQ_RECHAZO_PROPUESTA.NEXTVAL FROM DUAL", BigDecimal.class));
        }

        StringBuilder queryRechazo = new StringBuilder();

        queryRechazo.append("INSERT INTO ").append(getTableName());
        queryRechazo.append(
                " ( ID_RECHAZO_PROPUESTA, ID_PROPUESTA, ID_MOTIVO, DESCRIPCION,FECHA_INFORME_RECHAZO,ID_EMPLEADO,FECHA_RECHAZO,RFC_RECHAZO,ID_ESTATUS ,BLN_ESTATUS) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(queryRechazo.toString(), dto.getIdRechazoPropuesta(), dto.getIdPropuesta(),
                dto.getIdMotivo(), dto.getDescripcion(), dto.getFechaInformeRechazo(), dto.getIdEmpleado(),
                dto.getFechaRechazo(), dto.getRfcRechazo(), dto.getEstatus(), dto.getBlnEstatus());

        return dto.createPk();

    }

    @Override
    public FecetRechazoPropuestaPk insertarRechazoPropuesta(FecetRechazoPropuesta dto) {
        StringBuilder queryRechazo = new StringBuilder();

        queryRechazo.append("INSERT INTO ").append(getTableName());
        queryRechazo.append(
                " ( ID_RECHAZO_PROPUESTA, ID_PROPUESTA, ID_MOTIVO, DESCRIPCION,FECHA_INFORME_RECHAZO,ID_EMPLEADO,FECHA_RECHAZO,RFC_RECHAZO,ID_ESTATUS ,BLN_ESTATUS) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");

        getJdbcTemplateBase().update(queryRechazo.toString(), dto.getIdRechazoPropuesta(), dto.getIdPropuesta(),
                dto.getIdMotivo(), dto.getDescripcion(), dto.getFechaInformeRechazo(), dto.getIdEmpleado(),
                dto.getFechaRechazo(), dto.getRfcRechazo(), dto.getEstatus(), dto.getBlnEstatus());

        return dto.createPk();

    }

    @Override
    public FecetRechazoPropuestaPk insertDoc(FecetRechazoPropuesta dto) {

        StringBuilder queryDoc = new StringBuilder();

        queryDoc.append("INSERT INTO ").append(getTableNameDoc());
        queryDoc.append(
                " ( ID_RECHAZO_PROPUESTA, ID_PROPUESTA,RUTA_ARCHIVO,FECHA_CREACION, ID_DOC_RECHAZO, FECHA_FIN, BLN_ACTIVO) VALUES ( ?, ?, ?, ?, ?, ?, ?)");

        getJdbcTemplateBase().update(queryDoc.toString(), dto.getIdRechazoPropuesta(), dto.getIdPropuesta(),
                dto.getRutaArchivo(), dto.getFechaCreacion(), dto.getIdDocRechazo(), dto.getFechaFin(),
                dto.getBlnActivo());

        return dto.createPk();

    }

    @Override
    public void update(FecetRechazoPropuestaPk pk, FecetRechazoPropuesta dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getTableName());
        query.append(
                " SET ID_RECHAZO_PROPUESTA = ?, ID_PROPUESTA = ?, ID_MOTIVO = ?, DESCRIPCION = ?, FECHA_RECHAZO = ?, FECHA_INFORME_RECHAZO = ?, RFC_RECHAZO = ?, RUTA_ARCHIVO = ?, TIPO = ?, ESTATUS = ?, DESCRIPCION_FIRMANTE = ?, FECHA_RECHAZO_FIRMANTE = ?, RFC_RECHAZO_FIRMANTE = ?, NOMBRE_ARCHIVO_FIRMANTE = ?, RUTA_ARCHIVO_FIRMANTE = ? WHERE ID_RECHAZO_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), dto.getIdRechazoPropuesta(), dto.getIdPropuesta(),
                dto.getIdMotivo(), dto.getDescripcion(), dto.getFechaRechazo(), dto.getFechaInformeRechazo(),
                dto.getRfcRechazo(), dto.getRutaArchivo(), dto.getTipo(), dto.getEstatus());

    }

    @Override
    public void delete(FecetRechazoPropuestaPk pk) {

        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getTableName());
        query.append(" WHERE ID_RECHAZO_PROPUESTA = ?");

        getJdbcTemplateBase().update(query.toString(), pk.getIdRechazoPropuesta());

    }

    public static String getTableName() {
        return "FECET_RECHAZO_PROPUESTA";
    }

    public static String getTableNameDoc() {
        return "FECET_DOC_RECHAZO_PROPUESTA";
    }

    @Override
    public FecetRechazoPropuesta findByPrimaryKey(BigDecimal idRechazoPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_PROPUESTA = ?");
        List<FecetRechazoPropuesta> list = getJdbcTemplateBase().query(query.toString(),
                new FecetRechazoPropuestaMapper(), idRechazoPropuesta);
        return list.isEmpty() ? null : list.get(0);

    }

    @Override
    public List<FecetRechazoPropuesta> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" ORDER BY ID_RECHAZO_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper());

    }

    @Override
    public List<FecetRechazoPropuesta> findByFececMotivo(BigDecimal idMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO = ?");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), idMotivo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereIdRechazoPropuestaEquals(BigDecimal idRechazoPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_RECHAZO_PROPUESTA = ? ORDER BY ID_RECHAZO_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), idRechazoPropuesta);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_PROPUESTA = ? ORDER BY ID_PROPUESTA");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), idPropuesta);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereIdMotivoEquals(BigDecimal idMotivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ID_MOTIVO = ? ORDER BY ID_MOTIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), idMotivo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereDescripcionEquals(String descripcion) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION = ? ORDER BY DESCRIPCION");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), descripcion);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereFechaRechazoEquals(Date fechaRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RECHAZO = ? ORDER BY FECHA_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), fechaRechazo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereFechaInformeRechazoEquals(Date fechaInformeRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_INFORME_RECHAZO = ? ORDER BY FECHA_INFORME_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), fechaInformeRechazo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereRfcRechazoEquals(String rfcRechazo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_RECHAZO = ? ORDER BY RFC_RECHAZO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), rfcRechazo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), rutaArchivo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereTipoEquals(String tipo) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE TIPO = ? ORDER BY TIPO");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), tipo);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereEstatusEquals(String estatus) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), estatus);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereDescripcionFirmanteEquals(String descripcionFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE DESCRIPCION_FIRMANTE = ? ORDER BY DESCRIPCION_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), descripcionFirmante);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereFechaRechazoFirmanteEquals(Date fechaRechazoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), fechaRechazoFirmante);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereRfcRechazoFirmanteEquals(String rfcRechazoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RFC_RECHAZO_FIRMANTE = ? ORDER BY RFC_RECHAZO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), rfcRechazoFirmante);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereNombreArchivoFirmanteEquals(String nombreArchivoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE NOMBRE_ARCHIVO_FIRMANTE = ? ORDER BY NOMBRE_ARCHIVO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), nombreArchivoFirmante);

    }

    @Override
    public List<FecetRechazoPropuesta> findWhereRutaArchivoFirmanteEquals(String rutaArchivoFirmante) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT);
        query.append(" WHERE RUTA_ARCHIVO_FIRMANTE = ? ORDER BY RUTA_ARCHIVO_FIRMANTE");
        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaMapper(), rutaArchivoFirmante);

    }

    @Override
    public List<ConsultaInformeComiteRechazoPropuesta> findAllRechazos(String rfc, String idEntidad,
            String idActividadPreponderante, BigDecimal idEstatus, String condicionEmpleado) {
        StringBuilder query = new StringBuilder();
        query.append(
                "SELECT FECET_PROPUESTA.ID_REGISTRO, FECET_CONTRIBUYENTE.RFC, FECET_CONTRIBUYENTE.NOMBRE, FECEC_ESTATUS.DESCRIPCION AS ESTATUS, FECET_CONTRIBUYENTE.REGIMEN, ");
        query.append(
                "FECEC_SUBPROGRAMA.CLAVE SUBPROGRAMA_CLAVE, FECEC_SUBPROGRAMA.DESCRIPCION SUBPROGRAMA_DESCRIPCION, FECET_CONTRIBUYENTE.ENTIDAD, FECET_CONTRIBUYENTE.TIPO TIPO_CONTRIBUYENTE, ");
        query.append(
                "FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE, FECET_PROPUESTA.FECHA_INFORME, FECET_PROPUESTA.FECHA_PRESENTACION, FECET_RECHAZO_PROPUESTA.RFC_RECHAZO, FECEC_MOTIVO.DESCRIPCION AS CAUSA ");
        query.append("FROM ");
        query.append(FecetContribuyenteDaoImpl.getTableName());
        query.append(" INNER JOIN ");
        query.append(PropuestasSQL.NAME_TABLE);
        query.append(" ON FECET_CONTRIBUYENTE.ID_CONTRIBUYENTE = FECET_PROPUESTA.ID_CONTRIBUYENTE ");
        query.append("INNER JOIN ");
        query.append(FececSubprogramaDaoImpl.getTableName());
        query.append(" ON FECET_PROPUESTA.ID_SUBPROGRAMA = FECEC_SUBPROGRAMA.ID_SUBPROGRAMA ");
        query.append("INNER JOIN ");
        query.append(FececEstatusDaoImpl.getTableName());
        query.append(" ON FECET_PROPUESTA.ID_ESTATUS = FECEC_ESTATUS.ID_ESTATUS ");
        query.append("LEFT JOIN ");
        query.append(FecetRechazoPropuestaDaoImpl.getTableName());
        query.append(" ON FECET_PROPUESTA.ID_PROPUESTA = FECET_RECHAZO_PROPUESTA.ID_PROPUESTA ");
        query.append("LEFT JOIN ");
        query.append(FececMotivoDaoImpl.getTableName());
        query.append(" ON FECET_RECHAZO_PROPUESTA.ID_MOTIVO = FECEC_MOTIVO.ID_MOTIVO ");
        query.append("WHERE FECET_PROPUESTA.ID_ESTATUS IN(61,64,65,66,67) ");
        query.append(condicionEmpleado);

        if (!(rfc == null || rfc.isEmpty())) {
            query.append(" AND UPPER(FECET_CONTRIBUYENTE.RFC) = '");
            query.append(rfc.toUpperCase());
            query.append("'");
        }
        if (!(idEntidad == null || idEntidad.equals(Constantes.COMBO_SELECCIONA_CADENA))) {
            query.append(" AND UPPER(FECET_CONTRIBUYENTE.ENTIDAD) = '");
            query.append(idEntidad.toUpperCase());
            query.append("'");
        }
        if (!(idActividadPreponderante == null
                || idActividadPreponderante.equals(Constantes.COMBO_SELECCIONA.toString()))) {
            query.append(" AND FECET_CONTRIBUYENTE.ACTIVIDAD_PREPONDERANTE = '");
            query.append(idActividadPreponderante);
            query.append("'");
        }
        if (!(idEstatus == null || idEstatus.equals(Constantes.COMBO_SELECCIONA))) {

            query.append(" AND FECET_PROPUESTA.ID_ESTATUS = '");
            query.append(idEstatus);
            query.append("'");
        }

        return getJdbcTemplateBase().query(query.toString(), new ConsultaRechazoPropuestaMapper());
    }

    @Override
    public List<FecetContadorPropuestasRechazados> getContadorRechazo(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT RECHAZO.ID_RECHAZO_PROPUESTA, RECHAZO.FECHA_RECHAZO, FECEC_MOTIVO.DESCRIPCION, RECHAZO.ID_PROPUESTA, RECHAZO.DESCRIPCION OBSERVACIONES, ");
        query.append(
                "\n (SELECT COUNT (*) FROM FECET_DOC_RECHAZO_PROPUESTA  WHERE ID_RECHAZO_PROPUESTA = RECHAZO.ID_RECHAZO_PROPUESTA AND BLN_ESTATUS = RECHAZO.BLN_ESTATUS) AS CONTADOR ");
        query.append("\n FROM FECET_RECHAZO_PROPUESTA RECHAZO ");
        query.append("\n INNER JOIN FECEC_MOTIVO ON RECHAZO.ID_MOTIVO = FECEC_MOTIVO.ID_MOTIVO ");
        query.append("\n WHERE RECHAZO.ID_PROPUESTA = ? ");
        query.append("\n ORDER BY RECHAZO.ID_RECHAZO_PROPUESTA DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetContadorPropuestasRechazadosMapper(),
                idPropuesta);

    }

    @Override
    public List<FecetContadorPropuestasRechazados> getContadorRechazo(BigDecimal idPropuesta, TipoEstatusEnum estatus,
            BigDecimal blnEstatus) {
        StringBuilder query = new StringBuilder();
        query.append(
                " SELECT RECHAZO.ID_RECHAZO_PROPUESTA, RECHAZO.FECHA_RECHAZO, FECEC_MOTIVO.DESCRIPCION, RECHAZO.ID_PROPUESTA, RECHAZO.DESCRIPCION OBSERVACIONES, ");
        query.append(
                "\n (SELECT COUNT (*) FROM FECET_DOC_RECHAZO_PROPUESTA  WHERE ID_RECHAZO_PROPUESTA = RECHAZO.ID_RECHAZO_PROPUESTA AND BLN_ESTATUS = RECHAZO.BLN_ESTATUS) AS CONTADOR ");
        query.append("\n FROM FECET_RECHAZO_PROPUESTA RECHAZO ");
        query.append("\n INNER JOIN FECEC_MOTIVO ON RECHAZO.ID_MOTIVO = FECEC_MOTIVO.ID_MOTIVO ");
        query.append("\n WHERE RECHAZO.ID_PROPUESTA = ? ");
        query.append("\n AND RECHAZO.ID_ESTATUS = ? ");
        query.append("\n AND RECHAZO.BLN_ESTATUS = ? ");
        query.append("\n ORDER BY RECHAZO.ID_RECHAZO_PROPUESTA DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetContadorPropuestasRechazadosMapper(), idPropuesta,
                estatus.getId(), blnEstatus);

    }

    @Override
    public BigDecimal getIdRechazoPropuesta() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_RECHAZO_PROPUESTA.NEXTVAL FROM DUAL",
                BigDecimal.class);

    }

    @Override
    public FecetRechazoPropuesta findByPrimaryKey(FecetRechazoPropuestaPk pk) {
        return findByPrimaryKey(pk.getIdRechazoPropuesta());
    }

    @Override
    public BigDecimal getIdFecetPropuestaRechazo() {

        return getJdbcTemplateBase().queryForObject(SQL_DUAL, BigDecimal.class);

    }

    @Override
    public List<FecetRechazoPropuesta> getDocRechazadosByIdPropuesta(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT FECET_DOC_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA, FECET_RECHAZO_PROPUESTA.FECHA_RECHAZO AS FECHA_CREACION, FECEC_MOTIVO.ID_MOTIVO, FECEC_MOTIVO.DESCRIPCION, FECEC_MOTIVO.TIPO_MOTIVO, COUNT(FECET_DOC_RECHAZO_PROPUESTA.RUTA_ARCHIVO) TOTAL FROM \n");
        query.append(getTableNameDoc());
        query.append(" INNER JOIN FECET_RECHAZO_PROPUESTA ON FECET_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA = FECET_DOC_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA \n");
        query.append(" INNER JOIN FECEC_MOTIVO ON FECEC_MOTIVO.ID_MOTIVO = FECET_RECHAZO_PROPUESTA.ID_MOTIVO \n");
        query.append(" WHERE FECET_DOC_RECHAZO_PROPUESTA.ID_PROPUESTA = ? \n");
        query.append(" GROUP BY FECET_DOC_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA, FECEC_MOTIVO.ID_MOTIVO, FECEC_MOTIVO.DESCRIPCION, FECEC_MOTIVO.TIPO_MOTIVO, FECET_RECHAZO_PROPUESTA.FECHA_RECHAZO \n");
        query.append(" ORDER BY FECET_RECHAZO_PROPUESTA.FECHA_RECHAZO DESC");

        return getJdbcTemplateBase().query(query.toString(), new FecetHistoricoRechazoPropuestaMapper(), idPropuesta);
    }

    @Override
    public List<FecetRechazoPropuesta> getDocRechazadosByIdRechazo(BigDecimal idRechazoPropuesta) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT FECET_DOC_RECHAZO_PROPUESTA.ID_RECHAZO_PROPUESTA, FECET_DOC_RECHAZO_PROPUESTA.ID_PROPUESTA, ");
        query.append("FECET_DOC_RECHAZO_PROPUESTA.RUTA_ARCHIVO, FECET_DOC_RECHAZO_PROPUESTA.FECHA_CREACION ");
        query.append("FROM FECET_DOC_RECHAZO_PROPUESTA WHERE ID_RECHAZO_PROPUESTA = ?");

        return getJdbcTemplateBase().query(query.toString(), new FecetDocumentosRechazoMapper(), idRechazoPropuesta);
    }

    @Override
    public List<FecetRechazoPropuesta> findWhereIdPropuestaEqualsOrderByFecha(BigDecimal idPropuesta,
            BigDecimal idArace, BigDecimal idTipoEmpleado) {

        Object[] params = new Object[]{idArace, idTipoEmpleado, idPropuesta};
        StringBuilder query = new StringBuilder();
        query.append(RechazoPropuestaSQL.SQL_SELECT_X_IDPROPUESTA);

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaVerifProcedenciaMapper(), params);
    }

    @Override
    public BigDecimal getConsecutivoDocRechazo() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_RECHAZO_PROPUESTA.NEXTVAL FROM DUAL",
                BigDecimal.class);
    }

    @Override
    public void updateEstatusRechazo(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_RECHAZO_PROPUESTA SET BLN_ESTATUS = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    @Override
    public void updateEstatusDocRechazo(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_RECHAZO_PROPUESTA SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_PROPUESTA = ? ");

        getJdbcTemplateBase().update(query.toString(), idPropuesta);
    }

    @Override
    public List<FecetRechazoPropuesta> findRechazosByIdPropuestaEquals(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(RechazoPropuestaSQL.SQL_SELECT_RECHAZOS_X_IDPROPUESTA);

        return getJdbcTemplateBase().query(query.toString(), new FecetRechazoPropuestaVerifProcedenciaMapper(),
                idPropuesta);
    }
}
