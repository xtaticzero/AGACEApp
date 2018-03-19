package mx.gob.sat.siat.feagace.modelo.dao.propuestas.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.FecetDocOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.mapper.FecetDocOrdenVerifProcedenciaMapper;
import mx.gob.sat.siat.feagace.modelo.dao.propuestas.sql.DocOrdenSQL;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocExpediente;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrden;
import mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetDocOrdenPk;
import mx.gob.sat.siat.feagace.modelo.enums.AccionesFuncionarioEnum;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

import org.springframework.stereotype.Repository;

@Repository("fecetDocOrdenDao")
public class FecetDocOrdenDaoImpl extends BaseJDBCDao<FecetDocOrden> implements FecetDocOrdenDao {

    /**
     * Serial
     */
    private static final long serialVersionUID = -4286569048852142114L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetDocOrdenPk
     */
    public FecetDocOrdenPk insert(FecetDocOrden dto) {

        if (dto.getIdDocOrden() == null) {
            dto.setIdDocOrden(
                    getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_ORDEN.NEXTVAL FROM DUAL", BigDecimal.class));
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(
                " ( ID_DOC_ORDEN, ID_ORDEN, RUTA_ARCHIVO, DOCUMENTO_PDF, FECHA_CREACION, ESTATUS, BLN_ACTIVO, FECHA_FIN ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocOrden(), dto.getIdOrden(), dto.getRutaArchivo(),
                dto.getDocumentoPdf(), dto.getFechaCreacion(), dto.getEstatus(), dto.getBlnActivo(), dto.getFechaFin());
        return dto.createPk();
    }

    /**
     * Updates a single row in the FECET_DOC_ORDEN table.
     */
    public void update(FecetDocOrdenPk pk, FecetDocOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(
                " SET ID_DOC_ORDEN = ?, ID_ORDEN = ?, NOMBRE_ARCHIVO = ?, RUTA_ARCHIVO = ?, DOCUMENTO_PDF =?, FECHA_CREACION = ?, ESTATUS = ? WHERE ID_DOC_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), dto.getIdDocOrden(), dto.getIdOrden(), dto.getNombreArchivo(),
                dto.getRutaArchivo(), dto.getDocumentoPdf(), dto.getFechaCreacion(), dto.getEstatus(),
                pk.getIdDocOrden());
    }

    /**
     * Deletes a single row in the FECET_DOC_ORDEN table.
     */
    public void delete(FecetDocOrdenPk pk) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_DOC_ORDEN = ?");
        getJdbcTemplateBase().update(query.toString(), pk.getIdDocOrden());
    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_DOC_ORDEN = :idDocOrden'.
     */
    public FecetDocOrden findByPrimaryKey(BigDecimal idDocOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_DOC_ORDEN = ?");
        List<FecetDocOrden> list = getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idDocOrden);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * ''.
     */
    public List<FecetDocOrden> findAll() {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" ORDER BY ID_DOC_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper());

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    public List<FecetDocOrden> findByFecetOrden(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_ORDEN = ?");
        query.append(" ORDER BY ID_DOC_ORDEN DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_DOC_ORDEN = :idDocOrden'.
     */
    public List<FecetDocOrden> findWhereIdDocOrdenEquals(BigDecimal idDocOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_DOC_ORDEN = ? ORDER BY ID_DOC_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idDocOrden);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden'.
     */
    public List<FecetDocOrden> findWhereIdOrdenEquals(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_ORDEN = ? ORDER BY ID_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'NOMBRE_ARCHIVO = :nombre_archivo'.
     */
    public List<FecetDocOrden> findWhereNombreArchivoEquals(String nombreArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE NOMBRE_ARCHIVO = ? ORDER BY NOMBRE_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), nombreArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'RUTA_ARCHIVO = :rutaArchivo'.
     */
    public List<FecetDocOrden> findWhereRutaArchivoEquals(String rutaArchivo) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE RUTA_ARCHIVO = ? ORDER BY RUTA_ARCHIVO");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), rutaArchivo);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'DOCUMENTO_PDF = :documentoPdf'.
     */
    public List<FecetDocOrden> findWhereDocumentoPdfEquals(String documentoPdf) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE DOCUMENTO_PDF = ? ORDER BY DOCUMENTO_PDF");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), documentoPdf);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetDocOrden> findWhereFechaCreacionEquals(Date fechaCreacion) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), fechaCreacion);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ESTATUS = :estatus'.
     */
    public List<FecetDocOrden> findWhereEstatusEquals(String estatus) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ESTATUS = ? ORDER BY ESTATUS");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), estatus);

    }

    /**
     * Returns the rows from the FECET_DOC_ORDEN table that matches the
     * specified primary-key value.
     */
    public FecetDocOrden findByPrimaryKey(FecetDocOrdenPk pk) {
        return findByPrimaryKey(pk.getIdDocOrden());
    }

    /**
     * Method 'getClaveOrden'
     *
     * @return String consecutivo de la creacion de la CveOrden
     */
    public BigDecimal getClaveDocOrden() {

        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_DOC_ORDEN.NEXTVAL FROM DUAL", BigDecimal.class);

    }

    @Override
    public List<FecetDocExpediente> findWhereIdPropuestaEquals(BigDecimal idPropuesta) {
        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT_PROP);
        query.append(
                " WHERE FECET_ORDEN.ID_PROPUESTA = ? and FECET_DOC_ORDEN.BLN_ACTIVO=1 ORDER BY FECET_DOC_ORDEN.FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenVerifProcedenciaMapper(), idPropuesta);
    }

    @Override
    public List<FecetDocOrden> findByIdPropuesta(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT FECET_DOC_ORDEN.ID_DOC_ORDEN, FECET_DOC_ORDEN.ID_ORDEN, FECET_DOC_ORDEN.RUTA_ARCHIVO, ");
        query.append(
                "FECET_DOC_ORDEN.DOCUMENTO_PDF,FECET_DOC_ORDEN.FECHA_CREACION, FECET_DOC_ORDEN.ESTATUS, FECET_DOC_ORDEN.BLN_ACTIVO FROM FECET_DOC_ORDEN INNER JOIN FECET_ORDEN ON FECET_ORDEN.ID_ORDEN = FECET_DOC_ORDEN.ID_ORDEN ");
        query.append("INNER JOIN FECET_PROPUESTA ON FECET_PROPUESTA.ID_PROPUESTA = FECET_ORDEN.ID_PROPUESTA");
        query.append(" WHERE FECET_ORDEN.ID_PROPUESTA = ? ORDER BY FECET_DOC_ORDEN.FECHA_CREACION DESC");

        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idPropuesta);
    }

    @Override
    public List<FecetDocOrden> accionIdPropuestaHistorial(BigDecimal idPropuesta) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT_PROP_HIST);
        query.append(" WHERE FECET_ORDEN.ID_PROPUESTA = ? ORDER BY FECET_RECHAZO_ORDEN.FECHA_RECHAZO ASC");

        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idPropuesta);
    }

    @Override
    public void updateEstatusDocumento(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_ORDEN SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(" WHERE ID_ORDEN = ?");

        getJdbcTemplateBase().update(query.toString(), idOrden);
    }

    @Override
    public List<FecetDocOrden> findByEstatusActivo(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();

        query.append("SELECT FECET_DOC_ORDEN.ID_DOC_ORDEN, FECET_DOC_ORDEN.ID_ORDEN, FECET_DOC_ORDEN.RUTA_ARCHIVO, ");
        query.append(
                "FECET_DOC_ORDEN.DOCUMENTO_PDF,FECET_DOC_ORDEN.FECHA_CREACION, FECET_DOC_ORDEN.ESTATUS FROM FECET_DOC_ORDEN ");
        query.append("WHERE ID_ORDEN = ? AND BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_UNO);

        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idOrden);
    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden' and DOCUMENTO_PDF = 0.
     */
    public List<FecetDocOrden> findByFecetOrdenActivo(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_ORDEN = ? AND DOCUMENTO_PDF = 0 AND BLN_ACTIVO = 1 ORDER BY FECHA_CREACION DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idOrden);

    }

    /**
     * Returns all rows from the FECET_DOC_ORDEN table that match the criteria
     * 'ID_ORDEN = :idOrden' and DOCUMENTO_PDF = 1.
     */
    public List<FecetDocOrden> findByFecetOrdenPdf(BigDecimal idOrden) {

        StringBuilder query = new StringBuilder();
        query.append(DocOrdenSQL.SQL_SELECT);
        query.append(DocOrdenSQL.TABLE_NAME);
        query.append(" WHERE ID_ORDEN = ? AND DOCUMENTO_PDF = 1 AND BLN_ACTIVO = 1 ORDER BY FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(query.toString(), new FecetDocOrdenMapper(), idOrden);

    }

    @Override
    public void updateEstatusDocFechaFin(Date fechaFin, BigDecimal idDocOrden) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_ORDEN SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_CERO);
        query.append(", FECHA_FIN = ? ");
        query.append(" WHERE ID_DOC_ORDEN = ? ");

        getJdbcTemplateBase().update(query.toString(), fechaFin, idDocOrden);
    }

    public String accionesOrdenes() {
        return AccionesFuncionarioEnum.VALIDACION_FIRMANTE.getIdAccion() + ","
                + AccionesFuncionarioEnum.NO_APROBACION_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.APROBACION_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.FIRMA_ORDEN.getIdAccion() + ","
                + AccionesFuncionarioEnum.APROBACION_VALIDACION.getIdAccion() + ","
                + AccionesFuncionarioEnum.NO_APROBACION_VALIDACION.getIdAccion();
    }

    @Override
    public void updateActivoDocumento(BigDecimal idDocOrden) {

        StringBuilder query = new StringBuilder();

        query.append("UPDATE FECET_DOC_ORDEN SET BLN_ACTIVO = ");
        query.append(Constantes.ENTERO_UNO);
        query.append(" WHERE ID_DOC_ORDEN = ? ");

        getJdbcTemplateBase().update(query.toString(), idDocOrden);
    }

}
