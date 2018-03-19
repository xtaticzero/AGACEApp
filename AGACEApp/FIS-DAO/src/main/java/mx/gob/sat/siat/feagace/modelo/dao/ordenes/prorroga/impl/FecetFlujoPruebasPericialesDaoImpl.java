package mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.impl;

import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl.FecetAnexoPruebasPericialesDaoImpl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.catalogos.common.impl.FececEstatusDaoImpl;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.prorroga.FecetFlujoPruebasPericialesDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoPruebasPericialesConEstatusMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetFlujoPruebasPericialesMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetFlujoPruebasPericiales;
import mx.gob.sat.siat.feagace.modelo.util.Constantes;

@Repository("fecetFlujoPruebasPericialesDao")
public class FecetFlujoPruebasPericialesDaoImpl extends BaseJDBCDao<FecetFlujoPruebasPericiales> implements FecetFlujoPruebasPericialesDao {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String SQL_SELECT = " SELECT ";
    private static final String SQL_FROM = " FROM ";

    private static final String SQL_ALL_COLUMNS
            = " ID_FLUJO_PRUEBAS_PERICIALES, ID_PRUEBAS_PERICIALES, FECHA_CREACION, JUSTIFICACION, JUSTIFICACION_FIRMANTE, APROBADA, ID_ESTATUS, FECHA_RECHAZO_FIRMANTE ";

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_PROMOCION
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_FLUJO_PRUEBAS_PERICIALES.NEXTVAL FROM DUAL");

    private static final StringBuilder SQL_WITH_ESTATUS
            = new StringBuilder("SELECT ").append(" FPR.ID_FLUJO_PRUEBAS_PERICIALES, FPR.ID_PRUEBAS_PERICIALES, FPR.FECHA_CREACION, FPR.JUSTIFICACION, ").append(" FPR.JUSTIFICACION_FIRMANTE, FPR.APROBADA, FPR.ID_ESTATUS, FPR.FECHA_RECHAZO_FIRMANTE, ").append(" ESTATUS.ID_ESTATUS,ESTATUS.DESCRIPCION,ESTATUS.MODULO, ").append(" (SELECT COUNT(FARO.ID_ANEXO_PRUEBA_PERICIAL) FROM ").append(FecetAnexoPruebasPericialesDaoImpl.getTableName()).append(" FARO  ").append(" WHERE FARO.ID_FLUJO_PRUEBAS_PERICIALES=FPR.ID_FLUJO_PRUEBAS_PERICIALES ) TOTAL_ANEXOS  ").append(" FROM ").append(getTableName()).append(" FPR ").append(" INNER JOIN ").append(FececEstatusDaoImpl.getTableName()).append(" ESTATUS ").append(" ON FPR.ID_ESTATUS=ESTATUS.ID_ESTATUS ");

    public static String getTableName() {
        return "FECET_FLUJO_PRUEBAS_PERICIALES";
    }

    /**
     * Metodo getIdFecetFecetFlujoPruebasPericialesPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetFlujoPruebasPericialesPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'insert'
     *
     * @param dto
     * @return BigDecimal
     */
    public BigDecimal insert(FecetFlujoPruebasPericiales dto) {

        if (dto.getIdFlujoPruebasPericiales() == null) {
            dto.setIdFlujoPruebasPericiales(getIdFecetFlujoPruebasPericialesPathDirectorio());
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(getTableName()).append(" ( ").append(SQL_ALL_COLUMNS).append(" ) ");
        sql.append(" VALUES ( ?, ?, ?, ?, ?, ?, ?, ? ) ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoPruebasPericiales(), dto.getIdPruebasPericiales(),
                dto.getFechaCreacion(), dto.getJustificacion(), dto.getJustificacionFirmante(),
                dto.getAprobada(), dto.getIdEstatus(), dto.getFechaRechazoFirmante());
        return dto.getIdFlujoPruebasPericiales();
    }

    /**
     * Updates a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    public void update(FecetFlujoPruebasPericiales dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(getTableName());
        sql.append(" SET  ID_PRUEBAS_PERICIALES = ?, FECHA_CREACION = ?, JUSTIFICACION = ?, JUSTIFICACION_FIRMANTE = ?, APROBADA = ?, ID_ESTATUS = ?, FECHA_RECHAZO_FIRMANTE = ? WHERE ID_FLUJO_PRUEBAS_PERICIALES = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdPruebasPericiales(), dto.getFechaCreacion(),
                dto.getJustificacion(), dto.getJustificacionFirmante(), dto.getAprobada(),
                dto.getIdEstatus(), dto.getFechaRechazoFirmante(), dto.getIdFlujoPruebasPericiales());
    }

    /**
     * Deletes a single row in the FECET_FLUJO_PRORROGA_ORDEN table.
     */
    public void delete(final FecetFlujoPruebasPericiales dto) {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE ").append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRUEBAS_PERICIALES = ? ");

        getJdbcTemplateBase().update(sql.toString(), dto.getIdFlujoPruebasPericiales());
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRUEBAS_PERICIALES = :idFlujoPruebasPericiales'.
     */
    public FecetFlujoPruebasPericiales findByPrimaryKey(final BigDecimal idFlujoPruebasPericiales) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRUEBAS_PERICIALES = ?");

        List<FecetFlujoPruebasPericiales> list
                = getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), idFlujoPruebasPericiales);

        return list.size() == 0 ? null : list.get(0);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria ''.
     */
    public List<FecetFlujoPruebasPericiales> findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" ORDER BY ID_FLUJO_PRUEBAS_PERICIALES");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper());
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_FLUJO_PRUEBAS_PERICIALES = :idFlujoPruebasPericiales'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereIdFlujoPruebasPericialesEquals(final BigDecimal idFlujoPruebasPericiales) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_FLUJO_PRUEBAS_PERICIALES = ? ORDER BY ID_FLUJO_PRUEBAS_PERICIALES");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), idFlujoPruebasPericiales);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_PRUEBAS_PERICIALES = :idPruebasPericiales'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereIdPruebasPericialesEquals(final Date idPruebasPericiales) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_PRUEBAS_PERICIALES = ? ORDER BY ID_PRUEBAS_PERICIALES ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), idPruebasPericiales);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_CREACION = :fechaCreacion'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereFechaCreacionEquals(final Date fechaCreacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_CREACION = ? ORDER BY FECHA_CREACION ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), fechaCreacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION = :justificacion'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereJustificacionEquals(final String justificacion) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION = ? ORDER BY JUSTIFICACION");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), justificacion);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'JUSTIFICACION_FIRMANTE = :justificacionFirmante'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereJustificacionFirmanteEquals(final String justificacionFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE JUSTIFICACION_FIRMANTE = ? ORDER BY JUSTIFICACION_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), justificacionFirmante);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'APROBADA = :aprobada'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereAprobadaEquals(final String aprobada) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE APROBADA = ? ORDER BY APROBADA ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), aprobada);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'ID_ESTATUS = :idEstatus'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereIdEstatusEquals(final BigDecimal idEstatus) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE ID_ESTATUS = ? ORDER BY ID_ESTATUS ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), idEstatus);
    }

    /**
     * Returns all rows from the FECET_FLUJO_PRORROGA_ORDEN table that match the
     * criteria 'FECHA_RECHAZO_FIRMANTE = :fechaRechazoFirmante'.
     */
    public List<FecetFlujoPruebasPericiales> findWhereFechaRechazoFirmanteEquals(final Date fechaRechazoFirmante) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT).append(SQL_ALL_COLUMNS).append(SQL_FROM).append(getTableName());
        sql.append(" WHERE FECHA_RECHAZO_FIRMANTE = ? ORDER BY FECHA_RECHAZO_FIRMANTE ");

        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesMapper(), fechaRechazoFirmante);
    }

    /**
     * Returns the rows from the FECET_FLUJO_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    public FecetFlujoPruebasPericiales findByPrimaryKey(final FecetFlujoPruebasPericiales dto) {
        return findByPrimaryKey(dto.getIdFlujoPruebasPericiales());
    }

    /**
     * @param idPruebasPericiales
     * @return ultimo ID_FLUJO_PRUEBAS_PERICIALES
     */
    @Override
    public String findLastIdFecetFlujoPruebasPericiales(BigDecimal idPruebasPericiales) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_SELECT);
        sql.append(" max(ID_FLUJO_PRUEBAS_PERICIALES) ID_FLUJO_PRUEBAS_PERICIALES ");
        sql.append(SQL_FROM);
        sql.append(" fecet_flujo_pruebas_periciales ");
        sql.append(" where ID_PRUEBAS_PERICIALES = ? ");
        return getJdbcTemplateBase().queryForObject(sql.toString(), String.class, idPruebasPericiales);
    }

    /**
     *
     * @param idPruebasPericiales
     * @return
     */
    @Override
    public List<FecetFlujoPruebasPericiales> buscarFlujoPorIdPruebasPericiales(BigDecimal idPruebasPericiales) {
        StringBuilder sql = new StringBuilder();
        sql.append(SQL_WITH_ESTATUS).append(" WHERE FPR.ID_PRUEBAS_PERICIALES=? ORDER BY FPR.ID_FLUJO_PRUEBAS_PERICIALES DESC,FPR.FECHA_CREACION DESC");
        return getJdbcTemplateBase().query(sql.toString(), new FecetFlujoPruebasPericialesConEstatusMapper(),
                idPruebasPericiales);

    }

    /**
     * Updates a single row in the FECET_PRORROGA_ORDEN table.
     */
    public void actualizarFlujoConcluidoRechazadoPorFirmanteByIdPruebasPericiales(final BigDecimal idPruebasPericiales) {

        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(getTableName()).append(" SET ID_ESTATUS = ? WHERE ID_PRUEBAS_PERICIALES = ? AND ID_ESTATUS = ?");

        getJdbcTemplateBase().update(query.toString(),
                Constantes.ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_CONCLUIDA_RECHAZADA_FIRMANTE,
                idPruebasPericiales, Constantes.ESTATUS_RESOLUCION_PRUEBAS_PERICIALES_RECHAZADA_FIRMANTE);

    }

    public List<FecetFlujoPruebasPericiales> obtenerFlujoPruebaById(BigDecimal idPruebaPericial) {
        StringBuilder query = new StringBuilder();
        query.append(" SELECT  FPR.ID_FLUJO_PRUEBAS_PERICIALES, FPR.ID_PRUEBAS_PERICIALES, FPR.FECHA_CREACION, FPR.JUSTIFICACION, FPR.JUSTIFICACION_FIRMANTE, ");
        query.append(" FPR.APROBADA, FPR.ID_ESTATUS, FPR.FECHA_RECHAZO_FIRMANTE,  ESTATUS.ID_ESTATUS,ESTATUS.DESCRIPCION,ESTATUS.MODULO, ");
        query.append(" (SELECT COUNT(*) FROM FECET_ANEXO_PRUEBAS_PERICIALES FARO WHERE FARO.ID_FLUJO_PRUEBAS_PERICIALES = FPR.ID_FLUJO_PRUEBAS_PERICIALES ) TOTAL_ANEXOS ");
        query.append("\n FROM FECET_FLUJO_PRUEBAS_PERICIALES FPR ");
        query.append("\n INNER JOIN FECEC_ESTATUS ESTATUS  ON FPR.ID_ESTATUS=ESTATUS.ID_ESTATUS ");
        query.append("\n WHERE FPR.ID_PRUEBAS_PERICIALES = ? ");
        query.append("\n ORDER BY FPR.ID_FLUJO_PRUEBAS_PERICIALES DESC,FPR.FECHA_CREACION DESC ");
        return getJdbcTemplateBase().query(query.toString(), new FecetFlujoPruebasPericialesMapper(), idPruebaPericial);
    }

}
