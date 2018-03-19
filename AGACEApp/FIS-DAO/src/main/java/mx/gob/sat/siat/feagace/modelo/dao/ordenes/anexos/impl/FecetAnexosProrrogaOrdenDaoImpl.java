package mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.impl;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.anexos.FecetAnexosProrrogaOrdenDao;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexosProrrogaOrdenMapper;
import mx.gob.sat.siat.feagace.modelo.dao.ordenes.mapper.FecetAnexosProrrogaOrdenRelacionEmpleadosMapper;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrden;
import mx.gob.sat.siat.feagace.modelo.dto.ordenes.FecetAnexosProrrogaOrdenPk;

import org.springframework.stereotype.Repository;

@Repository("fecetAnexosProrrogaOrdenDao")
public class FecetAnexosProrrogaOrdenDaoImpl extends BaseJDBCDao<FecetAnexosProrrogaOrden> implements FecetAnexosProrrogaOrdenDao {

    /**
     * Este atributo es un SELECT para seleccionar los datos de la tabla
     * FECET_ANEXOS_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_SELECT
            = new StringBuilder("SELECT ID_ANEXOS_PRORROGA_ORDEN ,ID_FLUJO_PRORROGA_ORDEN, RUTA_ARCHIVO, FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO FROM ");

    /**
     * Este atributo corresponde una funcion DUAL para un Query de la tabla
     * FECET_ANEXOS_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_DUAL
            = new StringBuilder("SELECT FECEQ_ANEXOS_PRORROGA_ORDEN.NEXTVAL FROM DUAL");

    /**
     * este atributo corresponde a una funcion UPDATE para modificar los datos
     * de la tabla FECET_ANEXOS_PRORROGA_ORDEN
     */
    private static final StringBuilder SQL_UPDATE = new StringBuilder(" UPDATE ");

    /**
     * este atributo corresponde a un complemento de funcion SELECT para buscar
     * por criterio de IdOrden
     */
    private static final StringBuilder SQL_INSERT = new StringBuilder("INSERT INTO ");

    private static final StringBuilder JOIN = new StringBuilder(" INNER JOIN ");

    private static final StringBuilder SQL_SELECT_JOIN = new StringBuilder("SELECT ANEXO.ID_ANEXOS_PRORROGA_ORDEN, ANEXO.FECHA_CREACION, ANEXO.RUTA_ARCHIVO,ORDEN.ID_AUDITOR ID_EMPLEADO ");

    @SuppressWarnings("compatibility:-8732160919924816942")
    private static final long serialVersionUID = 723544L;

    /**
     * Method 'insert'
     *
     * @param dto
     * @return FecetAnexosProrrogaOrdenPk
     */
    public FecetAnexosProrrogaOrdenPk insert(FecetAnexosProrrogaOrden dto) {

        dto.setIdAnexosProrrogaOrden(getIdFecetAnexosProrrogaOrdenPathDirectorio());
        StringBuilder query = new StringBuilder();
        query.append(SQL_INSERT).append(getTableName());
        query.append(" ( ID_ANEXOS_PRORROGA_ORDEN, ID_FLUJO_PRORROGA_ORDEN, RUTA_ARCHIVO, ").append(
                "FECHA_CREACION, BLN_ACTIVO, FECHA_FIN, TIPO_ANEXO )").append(
                        " VALUES ( ?, ?, ?, ?, ?, ?, ? )");
        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosProrrogaOrden(), dto.getIdFlujoProrrogaOrden(),
                dto.getRutaArchivo(), dto.getFechaCreacion(),
                dto.getBlnActivo(), dto.getFechaFin(), dto.getTipoAnexo());
        return dto.createPk();

    }

    /**
     * Updates a single row in the FECET_ANEXOS_PRORROGA_ORDEN table.
     */
    public void update(FecetAnexosProrrogaOrdenPk pk, FecetAnexosProrrogaOrden dto) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_UPDATE).append(getTableName()).append(" SET ID_ANEXOS_PRORROGA_ORDEN = ?, ").append(
                "ID_FLUJO_PRORROGA_ORDEN = ?, RUTA_ARCHIVO = ?, FECHA_CREACION = ?, BLN_ACTIVO = ?, ").append(
                        "FECHA_FIN = ?, TIPO_ANEXO = ? WHERE ID_ANEXOS_PRORROGA_ORDEN = ? ");

        getJdbcTemplateBase().update(query.toString(), dto.getIdAnexosProrrogaOrden(), dto.getIdFlujoProrrogaOrden(),
                dto.getRutaArchivo(), dto.getFechaCreacion(),
                dto.getBlnActivo(), dto.getFechaFin(), dto.getTipoAnexo(), pk.getIdAnexosProrrogaOrden());

    }

    /**
     * Metodo getIdFecetAnexosProrrogaOrdenPathDirectorio
     *
     * @return BigDecimal
     * @
     */
    public BigDecimal getIdFecetAnexosProrrogaOrdenPathDirectorio() {
        return getJdbcTemplateBase().queryForObject(SQL_DUAL.toString(), BigDecimal.class);
    }

    /**
     * Method 'getTableName'
     *
     * @return String
     */
    public static String getTableName() {
        return "FECET_ANEXOS_PRORROGA_ORDEN";
    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXOS_PRORROGA_ORDEN = :idAnexosProrrogaOrden'.
     */
    public FecetAnexosProrrogaOrden findByPrimaryKey(BigDecimal idAnexosProrrogaOrden) {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_ANEXOS_PRORROGA_ORDEN = ?");
        List<FecetAnexosProrrogaOrden> list = getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOrdenMapper(), idAnexosProrrogaOrden);
        return list.size() == 0 ? null : list.get(0);

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria ''.
     */
    public List<FecetAnexosProrrogaOrden> findAll() {

        StringBuilder query = new StringBuilder();

        query.append(SQL_SELECT).append(getTableName()).append(" ORDER BY ID_ANEXOS_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOrdenMapper());

    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_ANEXOS_PRORROGA_ORDEN = :idAnexosProrrogaOrden'.
     */
    public List<FecetAnexosProrrogaOrden> findWhereIdProrrogaEquals(BigDecimal idAnexosProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ? ORDER BY ID_ANEXOS_PRORROGA_ORDEN");
        logger.info("Anexos de resolucion de prorroga {} ", query.toString());
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOrdenMapper(), idAnexosProrrogaOrden);

    }

    /**
     * Returns the rows from the FECET_ANEXOS_PRORROGA_ORDEN table that matches
     * the specified primary-key value.
     */
    public FecetAnexosProrrogaOrden findByPrimaryKey(FecetAnexosProrrogaOrdenPk pk) {
        return findByPrimaryKey(pk.getIdAnexosProrrogaOrden());
    }

    /**
     * Returns all rows from the FECET_ANEXOS_PRORROGA_ORDEN table that match
     * the criteria 'ID_FLUJO_PRORROGA_ORDEN = :idFlujoProrrogaOrden'.
     */
    public List<FecetAnexosProrrogaOrden> findWhereIdFlujoProrrogaOrdenEquals(final BigDecimal idFlujoProrrogaOrden) {

        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT).append(getTableName()).append(" WHERE ID_FLUJO_PRORROGA_ORDEN = ? ORDER BY ID_FLUJO_PRORROGA_ORDEN");
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOrdenMapper(), idFlujoProrrogaOrden);

    }

    /**
     * @param idAnexosProrrogaOrden
     * @return List anexos de prorroga agragados por el Auditor
     */
    public List<FecetAnexosProrrogaOrden> obtenerAnexosResolucionProrrogaAuditor(BigDecimal idAnexosProrrogaOrden) {
        StringBuilder query = new StringBuilder();
        query.append(SQL_SELECT_JOIN);
        query.append("FROM FECET_ANEXOS_PRORROGA_ORDEN ANEXO ");
        query.append(JOIN).append(" FECET_FLUJO_PRORROGA_ORDEN FLUJO ON FLUJO.ID_FLUJO_PRORROGA_ORDEN = ANEXO.ID_FLUJO_PRORROGA_ORDEN ");
        query.append(JOIN).append(" FECET_PRORROGA_ORDEN PRORROGA ON PRORROGA.ID_PRORROGA_ORDEN = FLUJO.ID_PRORROGA_ORDEN ");
        query.append(JOIN).append(" FECET_ORDEN ORDEN ON ORDEN.ID_ORDEN = PRORROGA.ID_ORDEN  ");
        query.append("  WHERE ANEXO.ID_FLUJO_PRORROGA_ORDEN = ? ORDER BY ANEXO.ID_ANEXOS_PRORROGA_ORDEN ");
        logger.debug("[Anexos de resolucion de prorroga agregados por el auditor]");
        logger.debug("SQL: {} ", query.toString());
        logger.debug("PARAMS: {} ", idAnexosProrrogaOrden);
        return getJdbcTemplateBase().query(query.toString(), new FecetAnexosProrrogaOrdenRelacionEmpleadosMapper(), idAnexosProrrogaOrden);

    }
}
